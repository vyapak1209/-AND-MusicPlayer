package com.example.vyapak.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    CheckBox rememberMe;
    SharedPreferences sharedPreferences;
    String currentUser = null;
    SharedPreferences currUser = this.getSharedPreferences("com.example.vyapak.musicplayer", Context.MODE_PRIVATE);

    private void login(){
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if(rememberMe.isSelected()) {
            sharedPreferences = this.getSharedPreferences("com.example.vyapak.musicplayer", Context.MODE_PRIVATE);

            sharedPreferences.edit().putString("username", username).commit();
            sharedPreferences.edit().putString("password", password).commit();
            currUser.edit().putString("currUser", username);

            startActivity(new Intent(this, PlayerActivity.class).putExtra("username", username));
        }else{
            startActivity(new Intent(this, PlayerActivity.class).putExtra("username", username));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPass);
        loginButton = findViewById(R.id.buttonLogin);
        rememberMe = findViewById(R.id.checkBoxRem);

        loginButton.setOnClickListener(this);

        if(currUser.getString("username", null) != null){
            startActivity(new Intent(this, PlayerActivity.class).putExtra("username", currentUser));
        }

    }

    @Override
    public void onClick(View view) {
        if(view == loginButton){
            login();
        }
    }
}
