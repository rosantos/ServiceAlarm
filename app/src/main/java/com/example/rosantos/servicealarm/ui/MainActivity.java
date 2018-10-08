package com.example.rosantos.servicealarm.ui;

import android.view.View;
import android.widget.EditText;

import com.example.rosantos.servicealarm.R;
import com.example.rosantos.servicealarm.service.MyIntentService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by rosantos on 28/02/2015.
 */
@EActivity(R.layout.main_activity)
public class MainActivity extends AbstractFragmentActivity {

    private static final Integer DEFAULT_INTERVAL_SECONDS = 15;
    @ViewById
    EditText txtInterval;

    @AfterViews
    protected void init() {
    }

    @Click
    protected void btnStart(View clickedView) {
        MyIntentService.stopAlarm(this);
        String intervalString = txtInterval.getText().toString();
        MyIntentService.startService(this, intervalString != null && !intervalString.isEmpty()
                ? Integer.valueOf(intervalString)
                : DEFAULT_INTERVAL_SECONDS);
    }

    @Click
    protected void btnStop(View clikedView){
        MyIntentService.stopAlarm(this);
    }
}
