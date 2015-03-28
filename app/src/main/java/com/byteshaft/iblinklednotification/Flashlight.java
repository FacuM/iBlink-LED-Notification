package com.byteshaft.iblinklednotification;

import android.content.Context;
import android.content.ContextWrapper;

import com.byteshaft.ezflashlight.CameraStateChangeListener;

@SuppressWarnings("deprecation")
public class Flashlight extends ContextWrapper implements CameraStateChangeListener {

    private com.byteshaft.ezflashlight.Flashlight flashlight = null;

    public Flashlight(Context context) {
        super(context);
    }

    void startBlinking() {
        flashlight = new com.byteshaft.ezflashlight.Flashlight(this);
        flashlight.setOnCameraStateChangedListener(this);
        flashlight.initializeCamera();
    }

    @Override
    public void onCameraOpened() {

    }

    @Override
    public void onCameraViewSetup() {
        new BlinkerThread(flashlight).blink();
    }

    @Override
    public void onCameraBusy() {
        CallStateListener.isCallIncoming = false;
    }

    @Override
    public void onFlashlightTurnedOn() {

    }

    @Override
    public void onFlashlightTurnedOff() {

    }
}
