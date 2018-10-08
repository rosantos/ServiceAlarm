package com.example.rosantos.servicealarm;

/**
 * Dagger module for setting up provides statements.
 * Register all of your entry points below.
 */

import com.example.rosantos.servicealarm.service.MyIntentService_;
import com.example.rosantos.servicealarm.ui.MainActivity_;

import dagger.Module;

@Module
        (
                complete = false,
                injects = {ServiceAlarmApplication.class,
                        MainActivity_.class,
                        MyIntentService_.class})

public class ServiceAlarmModule {



}
