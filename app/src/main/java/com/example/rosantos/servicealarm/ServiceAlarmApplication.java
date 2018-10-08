package com.example.rosantos.servicealarm;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;

import dagger.ObjectGraph;

/**
 * Created by rosantos on 11/02/2015.
 */
public class ServiceAlarmApplication extends Application {

    private static ServiceAlarmApplication instance;
    ObjectGraph objectGraph;

    /**
     * Create main application
     */
    public ServiceAlarmApplication() {

    }

    /**
     * Create main application
     *
     * @param context
     */
    public ServiceAlarmApplication(final Context context) {
        this();
        attachBaseContext(context);

    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        // Perform Injection
        objectGraph = ObjectGraph.create(getRootModule());
        objectGraph.inject(this);
        objectGraph.injectStatics();

    }

    private Object getRootModule() {
        return new RootModule();
    }


    /**
     * Create main application
     *
     * @param instrumentation
     */
    public ServiceAlarmApplication(final Instrumentation instrumentation) {
        this();
        attachBaseContext(instrumentation.getTargetContext());
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }


    public static ServiceAlarmApplication getInstance() {
        return instance;
    }

}
