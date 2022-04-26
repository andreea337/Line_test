package com.example.line;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.LongFunction;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageButton mBtn_Login;
    TextView memail, mpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemSetting();
        mBtn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login(){
        db.collection("account")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot doc : task.getResult()){
                                String a=doc.getString("id");
                                String b=doc.getString("pwd");
                                String a1=memail.getText().toString().trim();
                                String b1=mpwd.getText().toString().trim();
                                if(a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
                                    Log.d("tag", "ddddddd");
//                                    Toast.makeText(MainActivity.this, c + " Welcome", Toast.LENGTH_LONG).show();
//                                    //-------------------------------------------------------------------------
                                    Intent chat = new Intent(MainActivity.this, chat.class);
                                    startActivity(chat);
                                    break;
                                }else{
                                    Toast.makeText(MainActivity.this, "Cannot login,incorrect Email and Password", Toast.LENGTH_SHORT).show();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "My Toast", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Cannot login,incorrect Email and Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("tag", e.toString());
            }
        });
    }

    public void itemSetting(){
        mBtn_Login = findViewById(R.id.btn_login);
        memail = findViewById(R.id.sid);
        mpwd = findViewById(R.id.pwd);
    }
}



