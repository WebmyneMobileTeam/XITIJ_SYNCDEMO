package com.xitij.android.syncdemojumpsum;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.SyncStatusObserver;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.xitij.android.syncdemojumpsum.helpers.DatabaseHandler;
import com.xitij.android.syncdemojumpsum.model.User;
import com.xitij.android.syncdemojumpsum.sync.SimpleInit;
import com.xitij.android.syncdemojumpsum.sync.SyncConstants;

import java.util.ArrayList;


public class LauncherActivity extends ActionBarActivity implements SyncStatusObserver {

    private Object mContentProviderHandle;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        txt = (TextView)findViewById(R.id.txt);


/*
        // Creating database.....
        DatabaseHandler dbh = new DatabaseHandler(LauncherActivity.this);
        SQLiteDatabase db = dbh.getReadableDatabase();
*/
        SimpleInit init = new SimpleInit(this);
        init.execute();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mContentProviderHandle = ContentResolver.addStatusChangeListener(ContentResolver.SYNC_OBSERVER_TYPE_ACTIVE, this);

        try {
            DatabaseHandler dbh = new DatabaseHandler(LauncherActivity.this);
            ArrayList<User> users = dbh.getAllToDos();
            User user = users.get(0);
            txt.setText(String.format("%s %s", user.first_name, user.last_name));

        }catch(Exception e){

        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        ContentResolver.removeStatusChangeListener(mContentProviderHandle);
    }
    @Override
    public void onStatusChanged(int i) {
        try {
            DatabaseHandler dbh = new DatabaseHandler(LauncherActivity.this);
            ArrayList<User> users = dbh.getAllToDos();
            User user = users.get(0);
            txt.setText(String.format("%s %s", user.first_name, user.last_name));

        }catch(Exception e){

        }
    }
}
