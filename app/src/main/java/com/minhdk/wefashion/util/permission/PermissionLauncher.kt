package com.minhdk.wefashion.util.permission

interface PermissionLauncher {

    abstract val permissions: List<String>
    var triggerRequest: ((List<String>) -> Unit)?
    var onResult: ((Boolean) -> Unit)?
    abstract suspend fun request(): Boolean

}