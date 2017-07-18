package com.mcivicm.cheshouye;

import android.app.Application;
import android.content.Intent;

import com.cheshouye.api.client.WeizhangIntentService;

/**
 * Created by zlb on 17-7-18.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, WeizhangIntentService.class).putExtra("appId", 2625).putExtra("appKey", "57d82650f212aaf63f1dc955f719b866"));
    }
}
