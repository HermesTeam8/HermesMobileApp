package com.example.hermes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ImageView logo;
    private ListView listView;
    private Button logout, savetoDatabase;
    private EditText edit;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    private CheckBox checkBox1;

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
        checkBox1 = findViewById(R.id.checkBox12);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
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

                    HashMap<String, Object> messageBox = new HashMap<>();
                    messageBox.put("sent", txt_Message);

                    mRootRef.child("Messages").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(messageBox).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            edit.setText("");
                            Toast.makeText(MainActivity.this, "message sent!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void showAlertDialog() {AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("where do you want to send to?");
        String[] items = {"email", "phone number", "Facebook", "Discord", "Twitter"};
        boolean[] checkedItems = {false, false, false, false, false};
        alertDialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialog.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        switch (which) {
                            case 0:
                                if (isChecked)
                                    Toast.makeText(MainActivity.this, "-->email", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                if (isChecked)
                                    Toast.makeText(MainActivity.this, "-->phone number", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                if (isChecked)
                                    Toast.makeText(MainActivity.this, "-->Facebook", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                if (isChecked)
                                    Toast.makeText(MainActivity.this, "-->Discord", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                if (isChecked)
                                    Toast.makeText(MainActivity.this, "-->Twitter", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.setCanceledOnTouchOutside(true);
        alertDialog1.show();
    }
}


/*
    This will print out sent messages that belong to user

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);
       // mRootRef = FirebaseDatabase.getInstance().getReference();

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Messages").child(mAuth.getCurrentUser().getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();
//                for(DataSnapshot snapshot1: snapshot.getChildren()) {
//
//                    //This will print all messages to this screen.
//                    Information info = snapshot.getValue(Information.class);
//                    String txt = info.getEmail() + ": " + info.getEmail();
//                    // list.add(snapshot1.getValue().toString());
//                    list.add(snapshot1.getValue().toString());
//
//
//                }
//                adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }
//}

*/