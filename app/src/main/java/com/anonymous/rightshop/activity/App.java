package com.anonymous.rightshop.activity;

import android.app.Application;

import com.anonymous.rightshop.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Seravek.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
