package com.byteshaft.iblinklednotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends Activity implements Switch.OnCheckedChangeListener,
        Button.OnClickListener {
    private Helpers mHelpers;
    private Switch mCallSwitch;
    private Switch mSmsSwitch;
    private Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelpers = new Helpers(getApplicationContext());
        setContentView(R.layout.activity_main);
        closeButton = (Button) findViewById(R.id.close_button);
        closeButton.setOnClickListener(this);
        mCallSwitch = (Switch) findViewById(R.id.call_switch);
        mSmsSwitch = (Switch) findViewById(R.id.sms_switch);
        mCallSwitch.setOnCheckedChangeListener(this);
        mSmsSwitch.setOnCheckedChangeListener(this);
        setFinishOnTouchOutside(false);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.call_switch:
                mHelpers.enableCallBlink(isChecked);
                if (isChecked) {
                    if (FlashlightService.getInstance() == null) {
                        startService(new Intent(this, FlashlightService.class));
                    }
                } else {
                    stopService(new Intent(this, FlashlightService.class));
                }
                mHelpers.saveServiceStateEnabled(isChecked);
                break;
            case R.id.sms_switch:
                mHelpers.enableSmsBlink(isChecked);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCallSwitch.setChecked(mHelpers.isCallBlinkingEnabled());
        mSmsSwitch.setChecked(mHelpers.isSmsBlinkingEnabled());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_button:
                finish();
        }

    }
}
