package com.taskapp.model;

public class User {
    private int id;      // ユーザーのID
    private String name; // ユーザー名

    // コンストラクタ
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String string, String string2, int i) {
        //TODO Auto-generated constructor stub
    }

    // IDを取得するメソッド
    public int getId() {
        return id; // ユーザーのIDを返す
    }

    // ユーザー名を取得するメソッド
    public String getName() {
        return name; // ユーザー名を返す
    }

    public Object getEmail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
    }

    public Object getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    public int getUserCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserCode'");
    }

    // 追加のメソッド（必要に応じて）
}
