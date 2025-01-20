package com.iug.coursehub.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {

    private static String PREFS_NAME = "course_hub_prefs";
    private static String KEY_REMEMBER_ME = "remember_me";
    private static String KEY_ADMIN_EMAIL = "admin_email";
    private static String KEY_ADMIN_PASSWORD = "admin_password";
    private static String KEY_CURR_USER_ID = "curr_user_id";
    private static String KEY_IS_CURR_USER_ADMIN = "is_curr_user_admin";

    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;

    public static void initPrefs(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static boolean saveRememberMe(boolean rememberMe) {
        return editor
            .putBoolean(KEY_REMEMBER_ME, rememberMe)
            .commit();
    }

    public static boolean getRememberMe() {
        return prefs.getBoolean(KEY_REMEMBER_ME, false);
    }

    public static boolean saveAdminEmail() {
        return editor
            .putString(KEY_ADMIN_EMAIL, "admin@gmail.com")
            .commit();
    }

    public static String getAdminEmail() {
        return prefs.getString(KEY_ADMIN_EMAIL, "");
    }

    public static boolean saveAdminPassword() {
        return editor
            .putString(KEY_ADMIN_PASSWORD, "admin")
            .commit();
    }

    public static String getAdminPassword() {
        return prefs.getString(KEY_ADMIN_PASSWORD, "");
    }

    public static boolean saveCurrUserAsAdmin() {
        return editor
            .putBoolean(KEY_IS_CURR_USER_ADMIN, true)
            .commit();
    }

    public static boolean unSaveCurrUserAsAdmin() {
        return editor
            .putBoolean(KEY_IS_CURR_USER_ADMIN, false)
            .commit();
    }

    public static boolean isCurrUserAdmin() {
        return prefs.getBoolean(KEY_IS_CURR_USER_ADMIN, false);
    }

    public static boolean saveCurrentUserId(int id) {
        return editor
            .putInt(KEY_CURR_USER_ID, id)
            .commit();
    }

    public static int getCurrentUserId() {
        return prefs.getInt(KEY_CURR_USER_ID, 0);
    }
}
