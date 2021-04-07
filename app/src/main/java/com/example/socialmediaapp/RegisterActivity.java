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

public class RegisterActivity extends AppCompatActivity {
    
    EditText emailet,passwordet;
    Button Registerbt;
    TextView log;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        emailet = findViewById(R.id.emailet);
        passwordet = findViewById(R.id.passwordet);
        Registerbt = findViewById(R.id.Registerbt);
        log = findViewById(R.id.Log);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
        
        Registerbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                String email = emailet.getText().toString();
                String password = passwordet.getText().toString();
                
                if (TextUtils.isEmpty(email)){

                    emailet.setError("Please Enter Your Email");
                    return;


                }else if (TextUtils.isEmpty(password)){

                    passwordet.setError("Please Enter Your Password");
                    return;
                }else if (password.length() <6  ){

                    Toast.makeText(RegisterActivity.this, "Password should contain at least 6 characters", Toast.LENGTH_SHORT).show();

                }else {

                    Signinwithemailandpassword(email,password);

                }
                
            }
        });
        
    }

    private void Signinwithemailandpassword(String email, String password) {

        progressDialog.setTitle("Account creating");
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    SendusertoLoginActivity();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                e.printStackTrace();
                Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    private void SendusertoLoginActivity() {

        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}