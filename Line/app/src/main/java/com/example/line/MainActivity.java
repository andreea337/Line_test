package com.example.line;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends AppCompatActivity {

    ImageView msendButton;
    EditText mmsgEdt;
    RecyclerView recyclerView;
    List<Msg> msgList = new ArrayList<>();
    private static final String TAG = "DocSnippets";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void itemSetting(){
        mmsgEdt = findViewById(R.id.msgEdt);
        msendButton = findViewById(R.id.sendBtn);
        recyclerView = findViewById(R.id.messageRecyclerView);
    }
}






//        db.collection("java123")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        for(QueryDocumentSnapshot doc : task.getResult()){
//                            Map<String, Object> data = new HashMap<>();
//                            data.put("check", doc.get("check"));
//
//                            db.collection("初日001").document(doc.getId()).set(data);
//                            Log.d(TAG, "OK");
//                        }
//                    }
//                });

//        CollectionReference cities = db.collection("students").document("ann").collection("day");
//        Map<String, Object> data = new HashMap<>();
//        data.put("database", "DB221");
////        data.put("日文", "初日011");
////        data.put("國文", "大一國文5班");
////        data.put("英文", "進修英文");
//        data.put("java", "JAVA101");
////        data.put("python", "PY211");
//        cities.document("6").set(data);
//        cities.document("7").set(data);
//        Log.d(TAG,"OK");



