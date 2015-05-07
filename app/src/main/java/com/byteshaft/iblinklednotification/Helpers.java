package com.byteshaft.iblinklednotification;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Helpers extends ContextWrapper{
    public Helpers(Context base) {
        super(base);
    }

    boolean isSmsBlinkingEnabled() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getBoolean("smsblink", false);
    }
}
