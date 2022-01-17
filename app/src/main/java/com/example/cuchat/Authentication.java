package com.example.cuchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication extends AppCompatActivity {

    VideoView video;
    Button login, signUp;

    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //Check if user exist already
        if(firebaseUser != null) {
            Intent intent = new Intent(Authentication.this, HomePage.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


        signUp = findViewById(R.id.loginBtn);
        login = findViewById(R.id.signUpBtn);

        login.setOnClickListener(v -> startActivity(new Intent(Authentication.this, Login.class)));

        signUp.setOnClickListener(v -> startActivity(new Intent(Authentication.this, Register.class)));

        video = findViewById(R.id.bgVideo);
        String path = "android.resource://com.example.cuchat/" + R.raw.background_video;
        Uri vid = Uri.parse(path);

        video.setVideoURI(vid);
        video.start();

        video.setOnPreparedListener(mp -> mp.setLooping(true));



    }

    @Override
    protected void onResume() {
        video.resume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        video.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        video.stopPlayback();
        super.onDestroy();
    }
}