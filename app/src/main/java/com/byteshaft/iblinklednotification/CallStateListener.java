package com.byteshaft.iblinklednotification;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CallStateListener extends PhoneStateListener {

    private Context mContext;
    private static boolean sIsCallIncoming;

    public CallStateListener(Context context) {
        mContext = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                sIsCallIncoming = true;
                Intent intent = new Intent("com.byteshaft.iblinklednotification.CALL_RECEIVED");
                mContext.sendBroadcast(intent);
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                sIsCallIncoming = false;
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                sIsCallIncoming = false;
                break;
        }
    }

    static boolean isCallIncoming() {
        return sIsCallIncoming;
    }
}
