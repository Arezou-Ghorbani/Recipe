package com.example.recipe.utils

import android.app.Application
import com.example.recipe.R
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

/**Created by Arezou_Ghorbani on 27,August,2023,Arezoughorbaniii@gmail.com**/

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
//        Calligraphy
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder().setDefaultFontPath("fonts/atlas_regular.ttf")
                            .build()
                    )
                ).build()
        )
    }
}
