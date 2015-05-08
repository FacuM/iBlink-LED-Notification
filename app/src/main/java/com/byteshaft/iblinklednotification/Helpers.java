package com.byteshaft.iblinklednotification;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Helpers extends ContextWrapper{
    public Helpers(Context base) {
        super(base);
    }

    boolean isCallBlinkingEnabled() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getBoolean("call_blink", false);
    }

    void enableCallBlink(boolean enable) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean("call_blink", enable).apply();
    }

    void saveServiceStateEnabled(boolean enable) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean("enabled", enable).apply();
    }
    boolean isSmsBlinkingEnabled() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getBoolean("sms_blink", false);
    }

    void enableSmsBlink(boolean enable) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean("sms_blink", enable).apply();
    }

    public SharedPreferences getPreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
}
