package com.example.sherif.registrationscreen;

import android.util.Log;

/**
 * Created by Sherif on 16/01/2017.
 */

public class AppLog {
    private static final String APP_TAG = "AudioRecorder";

    public static int logString(String message) {
        return Log.i(APP_TAG, message);
    }
}
