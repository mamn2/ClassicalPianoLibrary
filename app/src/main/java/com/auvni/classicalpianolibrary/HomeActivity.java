package com.auvni.classicalpianolibrary;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activateSearchButton();
    }

    private void activateSearchButton() {
        Button searchButton = (Button) findViewById(R.id.SearchBtn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SearchResultsActivity.class));
            }
        });
    }


    /*
    @Override
    protected void onStart() {
        super.onStart();
    }

    private void connected() {

    }


    protected void onStop() {
        super.onStop();
    }
*/


}
