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
        SharedPreferences sharedPreferences = getPreferenceManager();
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
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean("sms_blink", false);
    }

    void enableSmsBlink(boolean enable) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean("sms_blink", enable).apply();
    }

    SharedPreferences getPreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    int[] getSelectedPattern() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        String currentSelectedPattern = sharedPreferences.getString("blink_pattern", "normal");

        switch (currentSelectedPattern) {
            case "normal":
                return new int[] {150, 300, 150, 300, 150, 750};
            case "slow":
                return new int[] {300, 300, 300, 300, 300, 750};
            case "fast":
                return new int[] {100, 200, 100, 200, 100, 750};
            case "very_fast":
                return new int[] {50, 100, 50, 100, 50, 750};
            default:
                return new int[] {150, 300, 150, 300, 150, 750};
        }
    }

    void savePatternSetting(String pattern) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putString("blink_pattern", pattern).apply();
    }
}
