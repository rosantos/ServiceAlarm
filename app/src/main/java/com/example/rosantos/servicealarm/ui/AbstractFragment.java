package com.example.rosantos.servicealarm.ui;

import android.os.Bundle;

import com.example.rosantos.servicealarm.ServiceAlarmApplication;


/**
 */
public abstract class AbstractFragment extends android.support.v4.app.Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ServiceAlarmApplication.getInstance().inject(this);
    }
}
