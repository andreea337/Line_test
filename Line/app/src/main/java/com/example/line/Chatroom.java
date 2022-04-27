package com.example.line;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Chatroom extends AppCompatActivity {
    ImageView msendButton;
    EditText mmsgEdt;
    RecyclerView recyclerView;
    TextView txtname;
    List<Msg> msgList = new ArrayList<>();
    String temp_key;
    private DatabaseReference root;
    Adapter_chat adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        itemSetting();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String room = intent.getStringExtra("chatroom");
        txtname.setText(name);

        //set recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter_chat(msgList, name);
        recyclerView.setAdapter(adapter);

        root = FirebaseDatabase.getInstance().getReference().child(room);
        msendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mmsgEdt.getText().toString();

                Map<String, Object> map = new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);
                Log.d("tag","cccccccc");
                //判斷內容不是空的
                if(!"".equals(content)){
                    //push to realtime
                    Msg msg = new Msg(content, name);
                    DatabaseReference msg_root = root.child(temp_key);
                    msg_root.setValue(msg);
                    Log.d("tag","ddddddddddd");
                    //更新adapter
                    adapter.notifyDataSetChanged();
                    mmsgEdt.setText("");
                    Log.d("tag","eeeeeeeeeeee");
                }
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("tag","aaaaaaaaaaaa");
                conversation(snapshot);
                Log.d("tag","------------");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("tag","bbbbbbbbbbbbb");
                //conversation(snapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private String mesg, time, user;
    public void conversation(DataSnapshot dataSnapshot){
        Iterator i = dataSnapshot.getChildren().iterator();
        Log.d("tag",msgList.size()+"");
        while(i.hasNext()){
            mesg = (String) ((DataSnapshot)i.next()).getValue();
            user = (String) ((DataSnapshot)i.next()).getValue();
            time = (String) ((DataSnapshot)i.next()).getValue();
            msgList.add(new Msg(mesg, user, time));
            Log.d("tag",msgList.size()+"");
        }
        Log.d("tag","1111111111111");
        //更新adapter
        adapter.notifyDataSetChanged();
        //要求recyclerView布局将消息刷新
        recyclerView.scrollToPosition(msgList.size()-1);
        Log.d("tag","22222222222222");
    }
//    public void setRecyclerView(final callback callback){
//        //set recyclerview
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Adapter_chat adapter = new Adapter_chat(msgList);
//        recyclerView.setAdapter(adapter);
//    }
//    public interface callback{
//        void onCallback(boolean value);
//    }

    public void itemSetting(){
        mmsgEdt = findViewById(R.id.msgEdt);
        msendButton = findViewById(R.id.sendBtn);
        recyclerView = findViewById(R.id.messageRecyclerView);
        txtname = findViewById(R.id.txt_chat);
    }

}