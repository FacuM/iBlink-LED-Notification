package com.byteshaft.iblinklednotification;


import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CallStateListener extends PhoneStateListener {

    public static boolean sIsCallIncoming;
    private static Flashlight mFlashlight;
    private Context mContext;

    public CallStateListener(Context context) {
        mContext = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        // We want the mFlashlight thread object to be created
        // only once, that's why declare it a static and only
        // initialize Flashlight if the object is null.
        if (mFlashlight == null) {
            mFlashlight = new Flashlight(mContext);
        }
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                sIsCallIncoming = true;
                mFlashlight.startBlinking();
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                // The loop in Flashlight.java will just break if this
                // variable is set to false.
                sIsCallIncoming = false;
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                sIsCallIncoming = false;
                break;
        }
    }
}
