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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    // Variables present in the Login Activity
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

        // Checks if the user logout previously, otherwise it will login to the previous session
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

        // Retrieves all user inputs in the Login Activity
        txtEmail = (EditText)findViewById(R.id.etEmail);
        txtPassword = (EditText)findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = txtEmail.getText().toString();
                final String passWord = txtPassword.getText().toString();

                // Regex for email and password
                Pattern userPattern = Pattern.compile("^(.+)@(.+)$");
                Matcher userMatcher = userPattern.matcher(email);

                Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*+=])(?=.*[a-zA-Z]).{1,}$");
                Matcher passMatcher = passPattern.matcher(passWord);

                // If else check for Regex pass or fail
                if (userMatcher.matches() && passMatcher.matches()) {
                    mAuth.signInWithEmailAndPassword(email, passWord).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Sign in error",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Email or Password Invalid", Toast.LENGTH_LONG).show();
                }



            }

//            @Override
//            public void onClick(View view) {
//                final String email = txtEmail.getText().toString();
//                final String passWord = txtPassword.getText().toString();
//
//                mAuth.signInWithEmailAndPassword(email, passWord).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (!task.isSuccessful()){
//                            Toast.makeText(LoginActivity.this,"Sign in error",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//            }
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
