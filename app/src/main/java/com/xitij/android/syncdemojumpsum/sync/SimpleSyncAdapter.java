package com.xitij.android.syncdemojumpsum.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.GsonBuilder;

import com.xitij.android.syncdemojumpsum.helpers.API;
import com.xitij.android.syncdemojumpsum.model.User;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by KP on 19/10/14.
 */
public class SimpleSyncAdapter extends AbstractThreadedSyncAdapter {

    private String TAG = "SimpleSyncAdapter";
    private final AccountManager mAccountManager;
    private JumpsumUtil parseUtil;
   // private ParseUser parseUser;

    Context ctx;
    public SimpleSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mAccountManager = AccountManager.get(context);
        this.ctx = context;
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        try{
            Log.i(TAG, "Loading Local data to array");
            // create object of parse utility
            parseUtil = new JumpsumUtil();
            // fetch current user
           // parseUser = ParseUser.getCurrentUser();

            // For simplicity I am implementing only single side sync (From Parse to Local)
            // you need to implement by directional sync in this method
            // delete all local todos
            SyncDelete(provider);
            // pull and add to do in local
            SyncAdd(provider);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void SyncDelete(ContentProviderClient provider){
        try{
            int count =provider.delete(SimpleContentProvider.CONTENT_URI_TODO,null,null);
            Log.i(TAG, "Rows Deleted : " + count);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private void SyncAdd(final ContentProviderClient provider){
      /*  try{
            ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery(DatabaseHelper.TABLE_TODO);
            List<ParseObject> allTodos=  parseQuery.find();
            for(ParseObject po : allTodos){
                parseUtil.pullToDo(getContext(),po,provider);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
*/
       //...

        HashMap<String, String> map = new HashMap<>();
        map.put("userid", "1004");

        new API(ctx, map, "SelectPlayerPersonalProfile") {
            @Override
            public void onResult(String result) {


                try {
                    JSONArray array = new JSONArray(result);
                    User currentUser = new GsonBuilder().create().fromJson(array.getJSONObject(0).toString(), User.class);
                    parseUtil.pullToDo(getContext(),currentUser,provider);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTimeOutException() {

            }
        }.call();



       //...
    }
}
