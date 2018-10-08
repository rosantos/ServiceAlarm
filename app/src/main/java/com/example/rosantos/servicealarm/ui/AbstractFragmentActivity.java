package com.example.rosantos.servicealarm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.rosantos.servicealarm.R;
import com.example.rosantos.servicealarm.ServiceAlarmApplication;

public abstract class AbstractFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ServiceAlarmApplication.getInstance().inject(this);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

}
