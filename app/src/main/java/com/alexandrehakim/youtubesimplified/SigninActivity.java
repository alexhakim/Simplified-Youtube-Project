package com.alexandrehakim.youtubesimplified;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.textfield.TextInputLayout;

public class SigninActivity extends AppCompatActivity {

    ImageView clearImageView;
    TextInputLayout emailTextInputLayout, passwordTextInputLayout;
    Button signinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        clearImageView = findViewById(R.id.clearImageView);
        emailTextInputLayout = findViewById(R.id.emailTextInputLayout);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        signinButton = findViewById(R.id.signinButton);

        clearImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // TODO: on successful signin, onBackPressed()




    }
}