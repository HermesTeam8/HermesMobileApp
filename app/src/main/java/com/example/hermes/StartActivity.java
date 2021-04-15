package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {
    private ImageView logo;
    private LinearLayout linearLayout;

    private Button newUserButton, registerBtn, loginBtn;
//    private EditText userName;
////  boolean isValid = false;
//    private EditText userPassword;
//    private String password = "";
//    private String username = "";
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, NewUserActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();
        }
    }
}

//        newUserButton = (Button) findViewById(R.id.newUserButton);
//        userName = (EditText) findViewById(R.id.userName);
//        userPassword = (EditText) findViewById(R.id.userPassword);
//        loginButton = (Button) findViewById(R.id.loginButton);
//        newUserButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openActivityLogin();
//            }
//        });
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                username = userName.getText().toString();
//                password = userPassword.getText().toString();
//                if(username.isEmpty() || password.isEmpty()){
//                    Toast.makeText(StartActivity.this, "Please enter username and password", Toast.LENGTH_LONG).show();
//                }else {
//                    isValid = validate(username, password);
//                    if (!isValid) {
//                    Toast.makeText(StartActivity.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    startActivity(new Intent(StartActivity.this, MessageActivity.class));
//                }}
//            }});
//        }

//
//
//    class Credentials {
//        String name = "UM8";
//        String password = "Hermes";
//    }
//    public void openActivityLogin() {
//        //replaced newUserActivty with MessageActivity
//        Intent intent = new Intent(this, NewUserActivity.class);
//        startActivity(intent);
//    }
//        private boolean validate(String username, String password){
//            Credentials credentials = new Credentials();
//            if(username.equals(credentials.name) && password.equals(credentials.password)) {
//                return true;
//            }
//            return false;
//        }
//}

