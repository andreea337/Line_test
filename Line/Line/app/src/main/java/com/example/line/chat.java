package com.example.line;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class chat extends AppCompatActivity {
    ImageView msendButton;
    EditText mmsgEdt;
    RecyclerView recyclerView;
    List<Msg> msgList = new ArrayList<>();
    private DatabaseReference reference;
    private FirebaseRecyclerAdapter<Msg, msgholder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        itemSetting();

        //set recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(msgList);
        recyclerView.setAdapter(adapter);

        msendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mmsgEdt.getText().toString();
                //判斷內容不是空的
                if(!"".equals(content)){
                    Msg msg = new Msg(content, 2);
                    reference.push()
                            .setValue(msg);
                    msgList.add(msg);
                    //更新adapter
                    adapter.notifyItemInserted(msgList.size()-1);
                    //要求recyclerView布局将消息刷新
                    recyclerView.scrollToPosition(msgList.size()-1);
                    mmsgEdt.setText("");
                }
            }
        });
    }
    private void displayChatMsg() {

        try {
            adapter = new FirebaseRecyclerAdapter<Msg, msgholder>
                    (Msg.class, R.layout.message, msgholder.class, reference.limitToLast(10)) {

                public msgholder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(context).inflate(R.layout.message, parent, false);
                    msgholder holder = new msgholder(view);

                    return holder;
                }


                @Override
                protected void populateViewHolder(msgholder viewHolder, Msg model, final int position) {
                    viewHolder.setValues(model);
                    viewHolder.img_avatar_other.setOnClickListener(v -> showInfo(position));
                    viewHolder.img_avatar_user.setOnClickListener(v -> showInfo(position));

                }
            };

            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void send(){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void itemSetting(){
        reference = FirebaseDatabase.getInstance().getReference();
        mmsgEdt = findViewById(R.id.msgEdt);
        msendButton = findViewById(R.id.sendBtn);
        recyclerView = findViewById(R.id.messageRecyclerView);
    }

}