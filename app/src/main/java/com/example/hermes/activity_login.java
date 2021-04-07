package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_login extends AppCompatActivity {
    private Button newUserButton;
    private EditText userName;
    private EditText userPassword;
    private String password = "";
    private String username = "";
    private Button loginButton;
    boolean isValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        newUserButton = (Button) findViewById(R.id.newUserButton);
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityLogin();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = userName.getText().toString();
                password = userPassword.getText().toString();
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(activity_login.this, "Please enter username and password", Toast.LENGTH_LONG).show();
                }else {
                    isValid = validate(username, password);
                    if (!isValid) {
                    Toast.makeText(activity_login.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                }
                else {
                    startActivity(new Intent(activity_login.this, MessageActivity.class));
                }}
            }});
        }

    class Credentials {
        String name = "UM8";
        String password = "Hermes";
    }
    public void openActivityLogin() {
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }
        private boolean validate(String username, String password){
            Credentials credentials = new Credentials();
            if(username.equals(credentials.name) && password.equals(credentials.password)) {
                return true;
            }
            return false;
        }
}

