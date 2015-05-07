package com.byteshaft.iblinklednotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.byteshaft.ezflashlight.CameraStateChangeListener;

public class CallBilnker extends BroadcastReceiver implements CameraStateChangeListener {
    private com.byteshaft.ezflashlight.Flashlight mFlashlight;

    @Override
    public void onReceive(Context context, Intent intent) {
        Helpers hlepers = new Helpers(context.getApplicationContext());

        Toast.makeText(context.getApplicationContext(), "Received", Toast.LENGTH_SHORT).show();
        if (hlepers.isSmsBlinkingEnabled()) {
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
