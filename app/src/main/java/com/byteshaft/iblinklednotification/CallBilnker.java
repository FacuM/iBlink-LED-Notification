package com.byteshaft.iblinklednotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.byteshaft.ezflashlight.*;

public class CallBilnker extends BroadcastReceiver implements CameraStateChangeListener {
    private com.byteshaft.ezflashlight.Flashlight mFlashlight;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context.getApplicationContext(), "Received", Toast.LENGTH_SHORT).show();
        if (isSmsBlinkingEnabled(context)) {
            mFlashlight = new com.byteshaft.ezflashlight.Flashlight(context);
            mFlashlight.setOnCameraStateChangedListener(this);
            mFlashlight.initializeCamera();
        }
    }

    public void blinkingMode() {
        mFlashlight.turnOn();
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mFlashlight.turnOff();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mFlashlight.turnOn();
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mFlashlight.releaseAllResources();
    }

    private boolean isSmsBlinkingEnabled(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean("smsblink", false);
    }

    @Override
    public void onCameraOpened() {

    }

    @Override
    public void onCameraViewSetup() {
        blinkingMode();
    }

    @Override
    public void onCameraBusy() {

    }

    @Override
    public void onFlashlightTurnedOn() {

    }

    @Override
    public void onFlashlightTurnedOff() {

    }
}
