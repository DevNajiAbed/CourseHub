package com.iug.coursehub

import android.app.Application
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.prefs.AppPrefs

class CourseHubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        CoursesRepository.initDb(this)
        AppPrefs.apply {
            initPrefs(this@CourseHubApp)
            saveAdminEmail()
            saveAdminPassword()
        }
    }
}