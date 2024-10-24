package com.taskapp;

import com.taskapp.ui.TaskUI;

public class Main {
    public static void main(String[] args) {
        TaskUI taskUI = new TaskUI();  // TaskUI クラスのインスタンスを作成
        taskUI.inputLogin();           // ログイン処理を実行
        taskUI.displayMenu();          // メニューを表示
    }
}
