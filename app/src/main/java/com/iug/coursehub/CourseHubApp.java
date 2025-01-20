package com.iug.coursehub;

import android.app.Application;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.prefs.AppPrefs;

public class CourseHubApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CoursesRepository.initDb(this);

        AppPrefs.initPrefs(this);
        AppPrefs.saveAdminEmail();
        AppPrefs.saveAdminPassword();
    }
}
