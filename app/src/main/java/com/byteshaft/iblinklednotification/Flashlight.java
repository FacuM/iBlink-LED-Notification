package com.byteshaft.iblinklednotification;

import android.content.Context;
import android.content.ContextWrapper;

import com.byteshaft.ezflashlight.CameraStateChangeListener;

@SuppressWarnings("deprecation")
public class Flashlight extends ContextWrapper implements CameraStateChangeListener {

    private com.byteshaft.ezflashlight.Flashlight mFlashlight;

    public Flashlight(Context context) {
        super(context);
    }

    void startBlinking() {
        mFlashlight = new com.byteshaft.ezflashlight.Flashlight(this);
        mFlashlight.setOnCameraStateChangedListener(this);
        mFlashlight.initializeCamera();
    }

    @Override
    public void onCameraOpened() {

    }

    @Override
    public void onCameraViewSetup() {
        new BlinkerThread(mFlashlight).blink();
    }

    @Override
    public void onCameraBusy() {
        CallStateListener.sIsCallIncoming = false;
    }

    @Override
    public void onFlashlightTurnedOn() {

    }

    @Override
    public void onFlashlightTurnedOff() {

    }
}
