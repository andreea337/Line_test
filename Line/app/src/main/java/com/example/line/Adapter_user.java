package com.example.line;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adapter_user extends RecyclerView.Adapter<Adapter_user.ViewHolder> {

    // 儲存要顯示的資料
    private List<String> stringList;
    String user_name;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    // ViewHolder 是把項目中所有的 View 物件包起來。
    // 它在 onCreateViewHolder() 中使用。
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txt_name);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            //update
            int pos = getAdapterPosition();
            //first pos is userid, so pos+1
            String name = stringList.get(pos+1).trim();
            String room = stringList.get(0)+"_"+name;
            //add a chat room for userid_name
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(room, "");
            root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.hasChild(room)){
                        root.updateChildren(map);
                    }

                    Intent chat = new Intent(v.getContext(), Chatroom.class);
                    chat.putExtra("name", name);
                    chat.putExtra("chatroom", room);
                    chat.putExtra("user", user_name);
                    v.getContext().startActivity(chat);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }
    }
    // 建構式，用來接收外部程式傳入的項目資料。
    public Adapter_user(List<String> stringList, String user_name) {
        this.stringList = stringList;
        this.user_name = user_name;
    }

    // RecyclerView會呼叫這個方法，我們必須建立好項目的ViewHolder物件，
    // 然後傳回給RecyclerView。
    @NonNull
    @Override
    public Adapter_user.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 建立一個 view。
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_item,
                parent, false);

        // 建立這個 view 的 ViewHolder。
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    // RecyclerView會呼叫這個方法，我們必須把項目資料填入ViewHolder物件。
    @Override
    public void onBindViewHolder(@NonNull Adapter_user.ViewHolder holder, int position) {
        // 把資料設定給 ViewHolder。
        holder.txtname.setText(stringList.get(position+1));
    }
    // RecyclerView會呼叫這個方法，我們要傳回總共有幾個項目。
    @Override
    public int getItemCount() {
        return stringList.size()-1;
    }
}

