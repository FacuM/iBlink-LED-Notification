package com.byteshaft.iblinklednotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.byteshaft.ezflashlight.CameraStateChangeListener;
import com.byteshaft.ezflashlight.Flashlight;

public class SmsBlinking extends BroadcastReceiver implements CameraStateChangeListener {

    private com.byteshaft.ezflashlight.Flashlight mFlashlight;
    private int i = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Helpers helpers = new Helpers(context.getApplicationContext());
        if (helpers.isSmsBlinkingEnabled()) {
            mFlashlight = new Flashlight(context);
            mFlashlight.setOnCameraStateChangedListener(this);
            mFlashlight.initializeCamera();
        }
    }

    public void blinkingMode() {
        final int pattern[] = {0, 200, 300, 200, 400, 100, 200};
        if (i > pattern.length - 1) {
            mFlashlight.releaseAllResources();
            return;
        }
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i % 2 == 0) {
                    mFlashlight.turnOn();
                } else {
                    mFlashlight.turnOff();

                }
                i += 1;
                blinkingMode();
            }
        }, pattern[i]);
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