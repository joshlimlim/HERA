package com.mad.hera;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    Button btnLogin;
    //DatabaseReference reff;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null){
                    Intent in = new Intent(LoginActivity.this, SwipeCardActivity.class);
                    startActivity(in);
                    finish();
                    return;
                }
            }
        };

        txtEmail = (EditText)findViewById(R.id.etEmail);
        txtPassword = (EditText)findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = txtEmail.getText().toString();
                final String passWord = txtPassword.getText().toString();

                mAuth.signInWithEmailAndPassword(email, passWord).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"sign in error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }

    public void onSignup(View v){
        Button btnSignup = (Button)v;
        Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(in);
    }

    public  void onHome(View v) {
        ImageButton imgbtnBack = (ImageButton) v;
        Intent in = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(in);
    }
}
