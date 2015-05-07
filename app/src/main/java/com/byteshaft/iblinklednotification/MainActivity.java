package com.byteshaft.iblinklednotification;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends ActionBarActivity implements Switch.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Switch mCallSwitch = (Switch) findViewById(R.id.call_switch);
        Switch smsSwitch = (Switch) findViewById(R.id.sms_switch);
        mCallSwitch.setOnCheckedChangeListener(this);
        smsSwitch.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.call_switch:
                if (isChecked) {
                    if (FlashlightService.getInstance() == null) {
                        startService(new Intent(this, FlashlightService.class));
                        saveServiceStateEnabled(true);
                    }
                } else {
                    stopService(new Intent(this, FlashlightService.class));
                    saveServiceStateEnabled(false);
                }
                break;
            case R.id.sms_switch:
                enableSmsBlink(isChecked);
        }
    }

    private void saveServiceStateEnabled(boolean ENABLED) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putBoolean("enabled", ENABLED).apply();
    }

    private void enableSmsBlink(boolean enable) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putBoolean("smsblink", enable).apply();
    }

}
