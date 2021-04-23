package com.example.hermes;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//NOT IN USE CURRENTLY. USING AS A REFERENCE TO SEE HOW MESSAGES ARE BEING SENT TO FIREBASE
public class MessageActivity extends AppCompatActivity {

    private  EditText editMessage;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button continueBtn;
    private String TAG = "SENT: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        editMessage = (EditText) findViewById(R.id.editMessage);
        continueBtn = (Button) findViewById(R.id.button);

        String message = editMessage.getText().toString();

        Map<String, Object> messages = new HashMap<>();
        messages.put(TAG, message);
        db.collection("Messages")
                .add(messages);

        db.collection("ALL MESSAGES SENT").document("added message").set(messages)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MessageActivity.this, "message was sent", Toast.LENGTH_SHORT);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MessageActivity.this, "error, not sent", Toast.LENGTH_SHORT);
                        Log.d(TAG, e.toString());
                    }
                });

//
//        Map<String, Object> messages = new HashMap<>();
//        messages.put("firstMessage", "hello");
//        messages.put("secondMessage", "world");
//
//        db.collection("Messages")
//                .add(messages);


    }
}