package com.example.line;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_chat extends RecyclerView.Adapter<Adapter_chat.ViewHolder> {
    // 儲存要顯示的資料
    private List<Msg> msgList;
    String name;
    // ViewHolder 是把項目中所有的 View 物件包起來。
    // 它在 onCreateViewHolder() 中使用。
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView messageTextView_right;
        public TextView timeTextView_right;
        public TextView messageTextView_left;
        public TextView timeTextView_left;
        LinearLayout left_layout;
        LinearLayout right_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView_left = (TextView)itemView.findViewById(R.id.messageTextView_left);
            timeTextView_left = (TextView)itemView.findViewById(R.id.timeTextView_left);
            messageTextView_right = (TextView)itemView.findViewById(R.id.messageTextView_right);
            timeTextView_right = (TextView)itemView.findViewById(R.id.timeTextView_right);
            left_layout = (LinearLayout)itemView.findViewById(R.id.left_layout);
            right_layout = (LinearLayout)itemView.findViewById(R.id.right_layout);
        }
    }
    // 建構式，用來接收外部程式傳入的項目資料。

    public Adapter_chat(List<Msg> msgList, String name) {
        this.name = name;
        this.msgList = msgList;
    }

    // RecyclerView會呼叫這個方法，我們必須建立好項目的ViewHolder物件，
    // 然後傳回給RecyclerView。
    @NonNull
    @Override
    public Adapter_chat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 建立一個 view。
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_view_item,
                parent, false);

        // 建立這個 view 的 ViewHolder。
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    // RecyclerView會呼叫這個方法，我們必須把項目資料填入ViewHolder物件。
    @Override
    public void onBindViewHolder(@NonNull Adapter_chat.ViewHolder holder, int position) {
        // 把資料設定給 ViewHolder
        Msg msg = msgList.get(position);
        if(!msg.name.equals(name)){
            holder.left_layout.setVisibility(View.VISIBLE);
            holder.right_layout.setVisibility(View.GONE);
            holder.messageTextView_left.setText(msg.getContent());
            holder.timeTextView_left.setText(msg.getTime());
        }
        else {
            holder.right_layout.setVisibility(View.VISIBLE);
            holder.left_layout.setVisibility(View.GONE);
            holder.messageTextView_right.setText(msg.getContent());
            holder.timeTextView_right.setText(msg.getTime());
        }
    }

    // RecyclerView會呼叫這個方法，我們要傳回總共有幾個項目。
    @Override
    public int getItemCount() {
        return msgList.size();
    }
}

