package com.example.hermes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MessageActivity extends AppCompatActivity {


    private  EditText editMessage;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button continueBtn; //= findViewById(R.id.button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        editMessage = (EditText) findViewById(R.id.editMessage);


        continueBtn = (Button) findViewById(R.id.button);

//
//        Map<String, Object> messages = new HashMap<>();
//        messages.put("firstMessage", "hello");
//        messages.put("secondMessage", "world");
//
//        db.collection("Messages")
//                .add(messages);


    }
}