package com.example.socialmediaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText logemailet,logpasswordet;
    Button Loginbt;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logemailet = findViewById(R.id.logemailet);
        logpasswordet = findViewById(R.id.logpasswordet);
        Loginbt = findViewById(R.id.login);
        register = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });


        Loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String logemail = logemailet.getText().toString();
                String logpassword = logpasswordet.getText().toString();

                if (TextUtils.isEmpty(logemail)) {

                    logemailet.setError("Please Enter Your Email");
                    return;


                } else if (TextUtils.isEmpty(logpassword)) {

                    logpasswordet.setError("Please Enter Your Password");
                    return;

                } else if (logpassword.length() < 6) {

                    Toast.makeText(LoginActivity.this, "Password should contain at least 6 characters", Toast.LENGTH_SHORT).show();


                }else {

                    Login(logemail,logpassword);

                }
            }

        });


    }

    private void Login(String logemail, String logpassword) {

        progressDialog.setTitle(" Login");
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(logemail,logpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent Loginintent = new Intent(LoginActivity.this,SetupActivity.class);
                    Loginintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Loginintent);
                    finish();
                    progressDialog.dismiss();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                e.printStackTrace();
                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }

}