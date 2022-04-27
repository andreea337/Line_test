package com.example.line;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MsgHolder extends RecyclerView.ViewHolder {
    private TextView txvMsg;
    private TextView txvTime;

    public MsgHolder(@NonNull View v) {
        super(v);
        txvMsg = (TextView) v.findViewById(R.id.messageTextView_left);
        txvTime = (TextView) v.findViewById(R.id.timeTextView_left);
    }

    public void setValues(Msg msg) {
        txvMsg.setText(msg.getContent());
        txvTime.setText(msg.getTime());

    }
}