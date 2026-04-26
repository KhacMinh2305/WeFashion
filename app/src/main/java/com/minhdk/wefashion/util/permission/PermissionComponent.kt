package com.minhdk.wefashion.util.permission

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@Composable
fun rememberPermissionLauncher(permissions: List<String>): PermissionLauncher {
    val permissionLauncher by remember { mutableStateOf(
        createPermissionLauncher(permissions)
    )}

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        permissionLauncher.onResult?.invoke(!result.values.any { !it })
    }
    LaunchedEffect(Unit) {
        permissionLauncher.triggerRequest = {
            launcher.launch(permissions.toTypedArray())
        }
    }
    return permissionLauncher
}

private fun createPermissionLauncher(permissions: List<String>): PermissionLauncher {
    return object : PermissionLauncher {
        override val permissions = permissions
        override var triggerRequest: ((List<String>) -> Unit)? = null
        override var onResult: ((Boolean) -> Unit)? = null

        override suspend fun request(): Boolean = suspendCancellableCoroutine { cont ->
            var resumed = false
            onResult = { granted ->
                if (!resumed) {
                    resumed = true
                    cont.resume(granted)
                }
            }
            triggerRequest?.invoke(permissions)

            cont.invokeOnCancellation {
                onResult = null
            }
        }
    }
}