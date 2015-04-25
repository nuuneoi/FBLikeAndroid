package com.inthecheesefactory.lib.fblikeandroid.activity;

import android.accounts.AccountManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.inthecheesefactory.lib.fblike.widget.FBLikeView;
import com.inthecheesefactory.lib.fblikeandroid.R;
import com.inthecheesefactory.lib.fblikeandroid.fragment.MainFragment;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.contentContainer, MainFragment.newInstance())
                                       .commit();
        }
    }

    private void initInstances() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FBLikeView.onActivityResult(requestCode, resultCode, data);
    }

}
