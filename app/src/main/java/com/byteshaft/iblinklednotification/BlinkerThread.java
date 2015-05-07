package com.byteshaft.iblinklednotification;

import java.util.Timer;
import java.util.TimerTask;

public class BlinkerThread {

    private com.byteshaft.ezflashlight.Flashlight mFlashlight;

    public BlinkerThread(com.byteshaft.ezflashlight.Flashlight mFlashlight) {
        this.mFlashlight = mFlashlight;
    }

    void blink() {
        Timer mTimer = new Timer();
        // `isCallIncoming` becomes true as soon as an incoming call
        // notification starts and becomes false when call is picked/dropped.
        // So we can rely on it to do our blinking.
        mFlashlight.turnOn();
        mTimer.schedule(getFlashlightOffTimerTask(), 150);
        mTimer.schedule(getFlashlightOnTimerTask(), 150 + 300);
        mTimer.schedule(getFlashlightOffTimerTask(), 150 + 300 + 500);
        // Make sure to release any camera resources so that it
        // can be used by other Apps.
        mFlashlight.releaseAllResources();
    }

    private TimerTask getFlashlightOffTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                mFlashlight.turnOff();
            }
        };
    }

    private TimerTask getFlashlightOnTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                mFlashlight.turnOn();
            }
        };
    }


}
