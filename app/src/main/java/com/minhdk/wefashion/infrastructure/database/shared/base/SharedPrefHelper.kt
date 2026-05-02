package com.minhdk.wefashion.infrastructure.database.shared.base

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.emptyList
import kotlin.reflect.KClass

open class SharedPrefHelper(
    val accessor: SharedPref
) {

    companion object {
        const val SEPARATOR = "|"
    }

    val gson = Gson()

    private val primitiveTypes: List<KClass<*>> = listOf(
        Int::class, Double::class, Float::class,
        Long::class, Short::class, Byte::class,
        Boolean::class, Char::class
    )

    fun isPrimitive(clazz: KClass<*>)= clazz in primitiveTypes

    fun <T> putListPrimitive(key: String, values: List<T>) {
        val packed = values.joinToString(SEPARATOR) { it.toString() }
        accessor.putString(key, packed)
    }

    inline fun <reified T> getListPrimitive(key: String): List<T> {
        val packed = accessor.getString(key) ?: return emptyList()
        return packed.split(SEPARATOR).map {
            when(T::class) {
                Int::class -> {
                    it.toInt() as T
                }
                Double::class -> {
                    it.toDouble() as T
                }
                Float::class -> {
                    it.toFloat() as T
                }
                Long::class -> {
                    it.toLong() as T
                }
                Short::class -> {
                    it.toShort() as T
                }
                Byte::class -> {
                    it.toByte() as T
                }
                Boolean::class -> {
                    it.toBoolean() as T
                }
                Char::class -> {
                    (it.firstOrNull() ?: return emptyList()) as T
                }
                else -> return emptyList()
            }
        }
    }

    inline fun <reified T> putListObject(key: String, values: List<T>) {
        accessor.putString(key, gson.toJson(values))
    }

    inline fun <reified T> getListObject(key: String): List<T> {
        val json = accessor.getString(key) ?: return emptyList()
        val type = object : TypeToken<List<T>>() {}.type
        return try {
            (gson.fromJson(json, type) as List<T>?) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    inline fun <reified T> putList(key: String, values: List<T>) {
        if(isPrimitive(T::class)) {
            putListPrimitive(key, values)
            return
        }
        putListObject(key, values)
    }

    inline fun <reified T> getList(key: String): List<T> {
        if(isPrimitive(T::class)) {
            return getListPrimitive(key)
        }
        return getListObject(key)
    }

}