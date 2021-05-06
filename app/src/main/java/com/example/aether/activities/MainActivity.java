package com.example.aether.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.aether.R;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* inflate the menu; this adds items to the action bar, if it is present */
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_about:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void galleryButtonClick(View target) {
        Intent gallActivity =
                new Intent(getApplicationContext(), ReconstructionListActivity.class);
        startActivity(gallActivity);
    }

    public void scanButtonClick(View target) {
        Intent camActivity = new Intent(getApplicationContext(), CameraActivity.class);
        startActivity(camActivity);
    }
}