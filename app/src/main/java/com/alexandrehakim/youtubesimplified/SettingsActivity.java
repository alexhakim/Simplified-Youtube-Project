package com.alexandrehakim.youtubesimplified;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;
    FirebaseAuth firebaseAuthWithGoogle;
    private final static int RC_SIGN_IN = 10;

    ImageView clearImageView, photoImageView;
    SignInButton signinButton;
    ListView settingsListView;

    ArrayList<String> settingsOptions = new ArrayList<String>();

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in and update UI.

        FirebaseUser currentUser = firebaseAuthWithGoogle.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("211641203521-p24o94lcg9tgbk6300k846oo79k3rann.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        firebaseAuthWithGoogle = FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        clearImageView = findViewById(R.id.clearImageView);
        signinButton = findViewById(R.id.signinButton);
        settingsListView= findViewById(R.id.settingsListView);
        photoImageView = findViewById(R.id.photoImageView);

        settingsOptions.add("About");
        settingsOptions.add("General");

        ArrayAdapter arrayAdapter = new ArrayAdapter(SettingsActivity.this, android.R.layout.simple_list_item_1, settingsOptions);
        settingsListView.setAdapter(arrayAdapter);

        settingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0){
                    Intent about = new Intent(SettingsActivity.this,AboutActivity.class);
                    startActivity(about);
                } else {
                    Intent general = new Intent(SettingsActivity.this,GeneralActivity.class);
                    startActivity(general);
                }
            }
        });



        clearImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("GoogleSignIn", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI
                Log.w("GoogleSignIn Error", "Google sign in failed", e);
            }
        }
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuthWithGoogle.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("GoogleSignIn", "signInWithCredential:success");
                            FirebaseUser user = firebaseAuthWithGoogle.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("GoogleSignIn Error", "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
       // photoImageView.setImageAlpha(0);
    }
}