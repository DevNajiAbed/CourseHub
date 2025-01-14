package com.iug.coursehub.data.local.prefs

import android.content.Context
import android.content.SharedPreferences

object AppPrefs {

    private const val PREFS_NAME = "course_hub_prefs"
    private const val KEY_REMEMBER_ME = "remember_me"
    private const val KEY_ADMIN_EMAIL = "admin_email"
    private const val KEY_ADMIN_PASSWORD = "admin_password"
    private const val KEY_CURR_USER_ID = "curr_user_id"

    private lateinit var prefs: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun initPrefs(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = prefs.edit()
    }

    fun saveRememberMe(rememberMe: Boolean): Boolean {
        return editor
            .putBoolean(KEY_REMEMBER_ME, rememberMe)
            .commit()
    }

    fun getRememberMePref(): Boolean {
        return prefs.getBoolean(KEY_REMEMBER_ME, false)
    }
    
    fun saveAdminEmail(): Boolean {
        return editor
            .putString(KEY_ADMIN_EMAIL, "admin@gmail.com")
            .commit()
    }
    
    fun getAdminEmail(): String {
        return prefs.getString(KEY_ADMIN_EMAIL, "") ?: ""
    }
    
    fun saveAdminPassword(): Boolean {
        return editor
            .putString(KEY_ADMIN_PASSWORD, "admin")
            .commit()
    }

    fun getAdminPassword(): String {
        return prefs.getString(KEY_ADMIN_PASSWORD, "") ?: ""
    }
    
    fun saveCurrentUserId(id: Int): Boolean {
        return editor
            .putInt(KEY_CURR_USER_ID, id)
            .commit()
    }

    fun getCurrentUserId(): Int {
        return prefs.getInt(KEY_CURR_USER_ID, 0)
    }
}