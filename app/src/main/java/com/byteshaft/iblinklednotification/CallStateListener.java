package com.byteshaft.iblinklednotification;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CallStateListener extends PhoneStateListener {

    private Context mContext;

    public CallStateListener(Context context) {
        mContext = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                Intent intent = new Intent("com.byteshaft.iblinklednotification.CALL_RECEIVED");
                mContext.sendBroadcast(intent);
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                break;
        }
    }
}
