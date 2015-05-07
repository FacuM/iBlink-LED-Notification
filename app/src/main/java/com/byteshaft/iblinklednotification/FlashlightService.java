package com.byteshaft.iblinklednotification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class FlashlightService extends Service {

    private TelephonyManager mTelephonyManager;
    private static CallStateListener mCallStateListener;
    private static FlashlightService sService;

    public static FlashlightService getInstance() {
        return sService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sService = this;
        mTelephonyManager = getTelephonyManager();
        if (mCallStateListener == null) {
            mCallStateListener = new CallStateListener(this);
        }
        mTelephonyManager.listen(mCallStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sService = null;
        mTelephonyManager.listen(mCallStateListener, PhoneStateListener.LISTEN_NONE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private TelephonyManager getTelephonyManager() {
        return (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
    }
}
