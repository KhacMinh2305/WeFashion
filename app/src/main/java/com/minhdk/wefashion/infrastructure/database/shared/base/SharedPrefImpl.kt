package com.minhdk.wefashion.infrastructure.database.shared.base

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val key: String
): SharedPref {

    private val sharedPref = context.getSharedPreferences(key, Context.MODE_PRIVATE)

    private val editor = sharedPref.edit()

    override fun putInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPref.getInt(key, defaultValue)
    }

    override fun putLong(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return sharedPref.getLong(key, defaultValue)
    }

    override fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value).apply()
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPref.getFloat(key, defaultValue)
    }

    override fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(key, defaultValue)
    }

    override fun putString(key: String, value: String?) {
        editor.putString(key, value).apply()
    }

    override fun getString(key: String, defaultValue: String?): String? {
        return sharedPref.getString(key, defaultValue)
    }

    override fun remove(key: String) {
        editor.remove(key).apply()
    }

    override fun clear() {
        editor.clear().apply()
    }

    override fun contains(key: String): Boolean {
        return sharedPref.contains(key)
    }
}