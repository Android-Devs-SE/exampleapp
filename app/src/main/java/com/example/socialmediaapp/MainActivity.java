package com.example.socialmediaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.toolbar);





    }

    @Override
    protected void onStart()
    {
        super.onStart();


        if(FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        } else {

            startActivity(new Intent(MainActivity.this,PostActivity.class));
            finish();


        }

    }

}







