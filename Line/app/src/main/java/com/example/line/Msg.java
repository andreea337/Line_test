package com.example.line;

import java.util.Date;

/*
 我们需要单例保存的数据为：
 1.消息的内容；
 2.消息创建时间
 */
public class Msg {
    private String content;
    private String time;
    public int type;
    //type 1:receive
    //type 2:send
    public Msg(String content, int type){
        this.content =content;
        this.type = type;
        this.time = timeData();
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
    /*
    写一个获取时间的方法
     */
    public String timeData(){
        Date date = new Date();
        String timeData = String.format("%tH",date) + ":" + String.format("%tM",date);
        return timeData;
    }
}