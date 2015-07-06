package com.xitij.android.syncdemojumpsum.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by KP on 19/10/14.
 */
public class SimpleSyncAdapterService extends Service {

    private String TAG = "SimpleSyncAdapterService";
    @Override
    public IBinder onBind(Intent intent) {
        SimpleSyncAdapter ssa = new SimpleSyncAdapter(getApplicationContext(),true);
        return ssa.getSyncAdapterBinder();
    }
}
