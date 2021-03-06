package com.auvni.classicalpianolibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private String songNames;
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
                try {
                    Intent intent = new Intent(HomeActivity.this, SearchResultsActivity.class);
                    EditText text = (EditText) HomeActivity.this.findViewById(R.id.editText);
                    songNames = text.getText().toString();
                    intent.putExtra("key", songNames);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(HomeActivity.this, "Exception", Toast.LENGTH_SHORT).show();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }
}
