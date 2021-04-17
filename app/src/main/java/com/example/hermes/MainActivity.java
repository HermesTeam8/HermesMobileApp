package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView logo;
    private ListView listView;

    private Button logout, savetoDatabase;
    private EditText edit;

//    private Button newUserButton, registerBtn, loginBtn;
//    private EditText userName;
////  boolean isValid = false;
//    private EditText userPassword;
//    private String password = "";
//    private String username = "";
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        logout = findViewById(R.id.logout);
        edit = findViewById(R.id.editTextMessage);
        savetoDatabase = findViewById(R.id.button5);
        listView = findViewById(R.id.linear_layout2);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, StartHere.class));
            }
//        });
//        linearLayout = findViewById(R.id.linear_layout);
//        registerBtn = findViewById(R.id.registerButton);
//        loginBtn = findViewById(R.id.loginButton);
//
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
//                        Intent.FLAG_ACTIVITY_CLEAR_TOP));
//            }
//        });
//
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
//                        | Intent.FLAG_ACTIVITY_CLEAR_TOP));
//            }
//        });
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//            startActivity(new Intent(MainActivity.this, StartHere.class));
//            finish();
//        }
//    }
        });

        savetoDatabase.setOnClickListener(new View.OnClickListener() {
             @Override
                public void onClick(View v) {
                    String txt_Message = edit.getText().toString();
                    if (txt_Message.isEmpty()) {
                        Toast.makeText(MainActivity.this, "no message entered", Toast.LENGTH_SHORT).show();
                    } else {
                        FirebaseDatabase.getInstance().getReference().child("Messages").push().child("sentMessages").setValue(txt_Message);
                    }
                }
        });

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Messages");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1: snapshot.getChildren()) {
                    list.add(snapshot1.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
                //created database with branch called Social apps, and value set to abcd
                // FirebaseDatabase.getInstance().getReference().child("SocialApps").child("Messages").setValue("abcd");
//                HashMap < String, Object > map = new HashMap<>();
//        map.put("Name", "Billy");
//        map.put("Email", "sample2@gmail.com");
//
//        FirebaseDatabase.getInstance().getReference().child("SocialApps").child("usersTesting").updateChildren(map);
//
//    }
//}


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

