Line 測驗
===
[TOC]

## 前言
- language: Java
- firestore: saving data
- realtime database: chat


## MainActivity
![](https://i.imgur.com/8ownM16.png =200x)
### 登入功能
- 使用firestore儲存帳號密碼
- 和資料庫比對成功後登入


## SelectUser
![](https://i.imgur.com/9DZPREm.png =200x)
### 選擇要和哪一個朋友聊天
- 使用firestore儲存朋友資料
### Adapter_user
- RecyclerView adapter
- 從selectuser抓朋友資料後傳入

## Chatroom
**initial**
![](https://i.imgur.com/9kkzXvD.png =200x)

**send message**
![](https://i.imgur.com/qMemPmB.png =200x)

**receive message**
![](https://i.imgur.com/HHCcB5j.png =200x)

### 聊天室功能
- 上方顯示聊天對象名字
- 左邊顯示接收訊息，右邊顯示傳出訊息
    - 在Adapter_chat中設定左右邊位置
- 訊息格式
    - 顯示realtime database儲存的時間和內容
- 按下send botton
    - 訊息推送到realtime database
    - realtime database更新資料
    - 抓回資料存入msglist後再重新刷新recyclerview
    
### Adapter_user
- RecyclerView adapter
- 一開始會先載入空list，從realtime database抓回資料存入msglist後再重新刷新recyclerview

## Data Model: Msg
儲存訊息的格式
- constructor若沒有時間，會自動抓取現在時間
:::info
EX: 15:30
:::
```java=
    public String content;
    public String time;
    public String name;
```

## Firestore
### login
![](https://i.imgur.com/5y6FWkG.png)
### select user
![](https://i.imgur.com/WlYm0xN.png)
### chat
![](https://i.imgur.com/48hy8GL.png)

## Realtime database
![](https://i.imgur.com/9kDk1U9.png)

### 問題
andy和joy如何在同一個聊天室?
- 當聊天室建立時，在雙方的Firestore chat集合下新增聊天室ID
- 下次進入聊天室時，尋找雙方的chat集合下是否有聊天室ID
- 有的話，在Realtime database搜尋該聊天室ID，並抓取 之前訊息







 
