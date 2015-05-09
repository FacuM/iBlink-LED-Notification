package com.byteshaft.iblinklednotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.byteshaft.ezflashlight.CameraStateChangeListener;

public class Blinker extends BroadcastReceiver implements CameraStateChangeListener {

    private final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private final String CALL_ACTION = "com.byteshaft.iblinklednotification.CALL_RECEIVED";
    private com.byteshaft.ezflashlight.Flashlight mFlashlight;
    private int mPatternRecursionCounter = 0;
    private String mIntentAction;

    @Override
    public void onReceive(Context context, Intent intent) {
        Helpers helpers = new Helpers(context.getApplicationContext());
        mIntentAction = intent.getAction();
        if (mIntentAction.equals(SMS_ACTION) ||
                mIntentAction.equals(CALL_ACTION)) {
            if (helpers.isSmsBlinkingEnabled() || helpers.isCallBlinkingEnabled()) {
                mFlashlight = new com.byteshaft.ezflashlight.Flashlight(context);
                mFlashlight.setOnCameraStateChangedListener(this);
                mFlashlight.initializeCamera();
            }
        }
    }

    public void blinkForCall() {
        final int pattern[] = {50, 100, 50, 100, 300};
        if (mPatternRecursionCounter > pattern.length - 1 && CallStateListener.isCallIncoming()) {
            mPatternRecursionCounter = 0;
        } else if (mPatternRecursionCounter > pattern.length - 1) {
            mFlashlight.releaseAllResources();
            return;
        }
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mPatternRecursionCounter % 2 != 0 && CallStateListener.isCallIncoming()) {
                    mFlashlight.turnOn();
                } else {
                    mFlashlight.turnOff();

                }
                mPatternRecursionCounter += 1;
                blinkForCall();
            }
        }, pattern[mPatternRecursionCounter]);
    }

    public void blinkForSMS() {
        final int pattern[] = {100, 50, 100, 50, 150, 50};
        if (mPatternRecursionCounter > pattern.length - 1) {
            mFlashlight.releaseAllResources();
            return;
        }
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mPatternRecursionCounter % 2 != 0) {
                    mFlashlight.turnOn();
                } else {
                    mFlashlight.turnOff();

                }
                mPatternRecursionCounter += 1;
                blinkForSMS();
            }
        }, pattern[mPatternRecursionCounter]);
    }

    @Override
    public void onCameraOpened() {

    }

    @Override
    public void onCameraViewSetup() {
        if (mIntentAction.equals(SMS_ACTION)) {
            blinkForSMS();
        } else if (mIntentAction.equals(CALL_ACTION)) {
            blinkForCall();
        }
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
