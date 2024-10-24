package com.taskapp.model;

public class User {
    private int id;      // ユーザーのID
    private String name; // ユーザー名

    // コンストラクタ
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // IDを取得するメソッド
    public int getId() {
        return id; // ユーザーのIDを返す
    }

    // ユーザー名を取得するメソッド
    public String getName() {
        return name; // ユーザー名を返す
    }

    // 追加のメソッド（必要に応じて）
}
