package com.mad.hera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method called by Registration button
    public void onSignup(View v){
        Button btnSignup = (Button)v;
        Intent in = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(in);
    }

    // Method called by Login button
    public void onLogin(View v){
        Button btnLogin = (Button)v;
        Intent in = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(in);
    }
}
