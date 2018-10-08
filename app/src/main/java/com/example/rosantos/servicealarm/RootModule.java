package com.example.rosantos.servicealarm;

import dagger.Module;

/**
 * Add all the other modules to this one.
 */
@Module
        (
                includes = {
                        AndroidModule.class,
                        ServiceAlarmModule.class
                }
        )
public class RootModule {
}
