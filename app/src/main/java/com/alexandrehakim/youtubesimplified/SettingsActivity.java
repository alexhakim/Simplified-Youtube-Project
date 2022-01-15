package com.alexandrehakim.youtubesimplified;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class SettingsActivity extends AppCompatActivity {

    ImageView clearImageView;
    Button signinButton;
    ListView settingsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        clearImageView = findViewById(R.id.clearImageView);
        signinButton = findViewById(R.id.signinButton);
        settingsListView= findViewById(R.id.settingsListView);

        clearImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // TODO: if user is not signed in, display sign in button and above_signin image

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signinIntent = new Intent(SettingsActivity.this,SigninActivity.class);
                startActivity(signinIntent);
            }
        });


    }
}