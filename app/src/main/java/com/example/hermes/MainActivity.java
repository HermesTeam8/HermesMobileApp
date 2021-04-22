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

    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

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

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Information");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1: snapshot.getChildren()) {
                    Information info = snapshot.getValue(Information.class);
                    String txt = info.getEmail() + ": " + info.getEmail();
                    list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

