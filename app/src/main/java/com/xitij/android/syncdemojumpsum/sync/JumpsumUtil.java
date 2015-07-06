package com.xitij.android.syncdemojumpsum.sync;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.xitij.android.syncdemojumpsum.helpers.DatabaseHandler;
import com.xitij.android.syncdemojumpsum.model.User;

/**
 * Created by Android on 06-07-2015.
 */
public class JumpsumUtil {

    public void pullToDo(Context ctx, User po, ContentProviderClient provider) {
        try {
            ContentValues values = new ContentValues();
            values.put(DatabaseHandler.NAME, po.first_name);
            values.put(DatabaseHandler.LAST_NAME,po.last_name);
            Uri uri = provider.insert(SimpleContentProvider.CONTENT_URI_TODO, values);
          //  Log.i(TAG, "Insert URI :" + uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
