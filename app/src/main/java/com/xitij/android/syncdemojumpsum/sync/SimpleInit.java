package com.xitij.android.syncdemojumpsum.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;



/**
 * Created by KP on 27/04/14.
 */
public class SimpleInit extends AsyncTask<Void,Void,Void> {

    String TAG = "AsyncInitSetup";
    private Context ctx;
    public SimpleInit(Context ctx){
        this.ctx = ctx;
    }
    @Override
    protected Void doInBackground(Void... params) {

        try{
          //  Parse.initialize(ctx, "LffRESOQWVoTvX0EiwogUA04MJsWA1lZzEkcomAa", "M72t00HAJTujxXaT6isdzUqce4DxfBBBxNuKUe31");

            SyncUtil syncUtils = new SyncUtil(ctx);
         //   String email = syncUtils.getGmailAccount();
           // String pwd = "1234567890";

            if(syncUtils.isSyncAccountExists()){
                Log.i(TAG, "SyncAdapter is already exist");
            }
            else{
                syncUtils.createSyncAccount("dd.droid@live.com","welcome123","dd.droid@live.com");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
