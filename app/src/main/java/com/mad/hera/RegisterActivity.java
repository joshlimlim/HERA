package com.mad.hera;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText txtName, txtAge, txtEmail, txtPassword;
    Button btnSignup;
    ContactsContract.Profile member;
    RadioGroup mradioGroup;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null){
                    Intent in = new Intent(RegisterActivity.this, SwipeCardActivity.class);
                    startActivity(in);
                    finish();
                    return;
                }
            }
        };
        txtName = (EditText)findViewById(R.id.etUsername);
        txtAge = (EditText)findViewById(R.id.etAge);
        txtEmail = (EditText)findViewById(R.id.etEmail);
        txtPassword = (EditText)findViewById(R.id.etPassword);
        btnSignup = (Button)findViewById(R.id.btnSignup);
        mradioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int age = Integer.parseInt(txtAge.getText().toString().trim());
                final String email = txtEmail.getText().toString();
                final String passWord = txtPassword.getText().toString();
                final String name = txtName.getText().toString();
                int selectedId = mradioGroup.getCheckedRadioButtonId();
                final RadioButton radioButton = (RadioButton) findViewById(selectedId);
                if (radioButton.getText() == null){
                    Toast.makeText(RegisterActivity.this,"Choose a gender!",Toast.LENGTH_LONG).show();
                    return;
                }

                Pattern userPattern = Pattern.compile("^(.+)@(.+)$");
                Matcher userMatcher = userPattern.matcher(email);

                Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*+=])(?=.*[a-zA-Z]).{1,}$");
                Matcher passMatcher = passPattern.matcher(passWord);

                if (userMatcher.matches() && passMatcher.matches()) {
                    Toast tt = Toast.makeText(RegisterActivity.this, "REGEX MATCH", Toast.LENGTH_LONG);
                    tt.show();
                    mAuth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"sign up error",Toast.LENGTH_LONG).show();
                            }else{
                                String userId = mAuth.getCurrentUser().getUid();
                                DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Member").child(userId);
                                Map userInfo = new HashMap<>();
                                userInfo.put("name",name);
                                userInfo.put("sex",radioButton.getText().toString());
                                userInfo.put("profileImageUrl","default");
                                currentUserDb.updateChildren(userInfo);
                            }
                        }
                    });
                } else {
                    Toast tt = Toast.makeText(RegisterActivity.this, "Email or Password Invalid", Toast.LENGTH_LONG);
                    tt.show();
                }


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

    public void onHome(View v) {
        ImageButton imgbtnBack = (ImageButton) v;
        Intent in = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(in);
    }
}