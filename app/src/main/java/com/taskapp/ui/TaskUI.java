package com.taskapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.taskapp.exception.AppException;
import com.taskapp.logic.TaskLogic;
import com.taskapp.logic.UserLogic;
import com.taskapp.model.User;

public class TaskUI {
    private final Scanner scanner;
    private final UserLogic userLogic;
    private final TaskLogic taskLogic;
    private User loginUser;

    public TaskUI() {
        scanner = new Scanner(System.in);
        userLogic = new UserLogic();
        taskLogic = new TaskLogic();
    }

    public void inputNewInformation() {
        String taskCode = "";
        String taskName = "";
        String userCode = "";
        boolean validInput = false;

        while (!validInput) {
            try {
                // タスクコードの入力
                System.out.print("タスクコードを入力してください：");
                taskCode = scanner.nextLine();

                // タスクコードのバリデーション
                if (!isNumeric(taskCode)) {
                    System.out.println("コードは半角の数字で入力してください");
                    continue; // 再入力を促す
                }

                // タスク名の入力
                System.out.print("タスク名を入力してください：");
                taskName = scanner.nextLine();

                // タスク名のバリデーション
                if (taskName.length() > 10) {
                    System.out.println("タスク名は10文字以内で入力してください");
                    continue; // 再入力を促す
                }

                // 担当ユーザーコードの入力
                System.out.print("担当するユーザーのコードを選択してください：");
                userCode = scanner.nextLine();

                // ユーザーコードのバリデーション
                if (!isNumeric(userCode)) {
                    System.out.println("ユーザーのコードは半角の数字で入力してください");
                    continue; // 再入力を促す
                }

                // ユーザーコードの存在チェック
                if (!userLogic.userExists(userCode)) {
                    System.out.println("存在するユーザーコードを入力してください");
                    continue; // 再入力を促す
                }

                // タスクの保存処理
                taskLogic.save(taskCode, taskName, "0", userCode, loginUser); // Status は 0 として保存
                System.out.println(taskName + "の登録が完了しました。");
                validInput = true; // 入力が有効だったのでループを終了
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isNumeric(String inputText) {
        // 正規表現を使用して数字かどうかをチェック
        return inputText.matches("\\d+");
    }

    public void displayMenu() {
        boolean exit = false;

        while (!exit) {
            // メニューを表示
            System.out.println("以下1~3のメニューから好きな選択肢を選んでください。");
            System.out.println("1. タスク一覧");
            System.out.println("2. タスク新規登録");
            System.out.println("3. ログアウト");
            System.out.print("選択肢：");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // 次の行を読み飛ばす

                switch (choice) {
                    case 1:
                        // タスク一覧を表示するメソッドを呼び出す
                        taskLogic.showAll(loginUser);
                        break;
                    case 2:
                        // タスク新規登録メソッドを呼び出す
                        inputNewInformation();
                        break;
                    case 3:
                        // ログアウト処理
                        loginUser = null; // ログアウト処理
                        System.out.println("ログアウトしました。");
                        exit = true; // メニューからの退出
                        break;
                    default:
                        System.out.println("無効な選択です。もう一度選んでください。");
                        break; // ループに戻る
                }
            } catch (Exception e) {
                // 入力が数値でない場合のエラーハンドリング
                System.out.println("無効な入力です。数字を入力してください。");
                scanner.nextLine(); // 入力バッファをクリア
            }
        }
    }

    public void inputLogin() {
        boolean loginSuccessful = false;
        while (!loginSuccessful) {
            // メールアドレスの入力
            System.out.print("メールアドレスを入力してください：");
            String email = scanner.nextLine();

            if (email == null || email.isEmpty()) {
                System.out.println("メールアドレスを正しく入力してください。");
                continue; // 無効な入力の場合は再度入力を促す
            }

            // パスワードの入力
            System.out.print("パスワードを入力してください：");
            String password = scanner.nextLine();

            if (password == null || password.isEmpty()) {
                System.out.println("パスワードを正しく入力してください。");
                continue; // 無効な入力の場合は再度入力を促す
            }

            // UserLogic を使用してログイン
            try {
                loginUser = userLogic.login(email, password);
                System.out.println("ユーザー名：" + loginUser.getName() + "でログインしました。");
                loginSuccessful = true; // ログイン成功
            } catch (AppException e) {
                // ログイン失敗時のメッセージ
                System.out.println("既に登録されているメールアドレス、パスワードを入力してください");
            }
        }
    }
}

    /**
     * タスクのステータス変更または削除を選択するサブメニューを表示します。
     *
     * @see #inputChangeInformation()
     * @see #inputDeleteInformation()
     */
    // public void selectSubMenu() {
    // }

    /**
     * ユーザーからのタスクステータス変更情報を受け取り、タスクのステータスを変更します。
     *
     * @see #isNumeric(String)package com.taskapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.taskapp.exception.AppException;
import com.taskapp.logic.TaskLogic;
import com.taskapp.logic.UserLogic;
import com.taskapp.model.User;

public class TaskUI {
    private final BufferedReader reader;
    private final UserLogic userLogic;
    private final TaskLogic taskLogic;
    private User loginUser;
    private User loggedInUser;

    public TaskUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        userLogic = new UserLogic();
        taskLogic = new TaskLogic();
    }

    public TaskUI(BufferedReader reader, UserLogic userLogic, TaskLogic taskLogic) {
        this.reader = reader;
        this.userLogic = userLogic;
        this.taskLogic = taskLogic;
    }

    public void inputNewInformation() {
        try {
            // タスクコードの入力
            String taskCode;
            while (true) {
                System.out.print("タスクコードを入力してください：");
                taskCode = reader.readLine();
                if (isNumeric(taskCode)) {
                    break;
                } else {
                    System.out.println("コードは半角の数字で入力してください");
                }
            }

            // タスク名の入力
            String taskName;
            while (true) {
                System.out.print("タスク名を入力してください：");
                taskName = reader.readLine();
                if (taskName.length() <= 10) {
                    break;
                } else {
                    System.out.println("タスク名は10文字以内で入力してください");
                }
            }

            // 担当するユーザーコードの入力
            String userCode;
            while (true) {
                System.out.print("担当するユーザーのコードを選択してください：");
                userCode = reader.readLine();
                if (isNumeric(userCode)) {
                    // 例外がスローされない場合は通常処理を実行
                    userLogic.checkUserCode(Integer.parseInt(userCode));
                    break;
                } else {
                    System.out.println("ユーザーのコードは半角の数字で入力してください");
                }
            }

            // タスクの保存
            taskLogic.save(Integer.parseInt(taskCode), taskName, Integer.parseInt(userCode), loginUser);
            System.out.println(taskName + "の登録が完了しました。");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isNumeric(String inputText) {
        try {
            Integer.parseInt(inputText);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void displayMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    while (!exit) {
        // メニューを表示
        System.out.println("以下1~3のメニューから好きな選択肢を選んでください。");
        System.out.println("1. タスク一覧");
        System.out.println("2. タスク新規登録");
        System.out.println("3. ログアウト");
        System.out.print("選択肢：");

        try {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // タスク一覧を表示するメソッドを呼び出す
                    taskLogic.showAll(loginUser);
                    break;
                case 2:
                    // タスク新規登録メソッドを呼び出す
                    inputNewInformation();
                    break;
                case 3:
                    // ログアウト処理
                    loggedInUser = null; // ログアウト処理
                    System.out.println("ログアウトしました。");
                    exit = true; // メニューからの退出
                    break;
                default:
                    System.out.println("無効な選択です。もう一度選んでください。");
                    break; // ループに戻る
            }
        } catch (Exception e) {
            // 入力が数値でない場合のエラーハンドリング
            System.out.println("無効な入力です。数字を入力してください。");
            scanner.next(); // 入力バッファをクリア
        }
    }
    scanner.close(); // ループを抜けたらScannerをクローズ
}


    public void inputLogin() {
    boolean loginSuccessful = false;
    while (!loginSuccessful) {
        try {
            // メールアドレスの入力
            System.out.print("メールアドレスを入力してください：");
            String email = reader.readLine();
            
            if (email == null || email.isEmpty()) {
                System.out.println("メールアドレスを正しく入力してください。");
                continue; // 無効な入力の場合は再度入力を促す
            }

            // パスワードの入力
            System.out.print("パスワードを入力してください：");
            String password = reader.readLine();

            if (password == null || password.isEmpty()) {
                System.out.println("パスワードを正しく入力してください。");
                continue; // 無効な入力の場合は再度入力を促す
            }

            // UserLogic を使用してログイン
            try {
                loginUser = userLogic.login(email, password);
                System.out.println("ユーザー名：" + loginUser.getName() + "でログインしました。");
                loginSuccessful = true; // ログイン成功
            } catch (AppException e) {
                // ログイン失敗時のメッセージ
                System.out.println("既に登録されているメールアドレス、パスワードを入力してください");
            }

        } catch (IOException e) {
            System.out.println("入力エラーが発生しました。再度お試しください。");
            e.printStackTrace();
        }
    }
}
}
     * @see com.taskapp.logic.TaskLogic#changeStatus(int, int, User)
     */
    // public void inputChangeInformation() {
    // }

    /**
     * ユーザーからのタスク削除情報を受け取り、タスクを削除します。
     *
     * @see #isNumeric(String)
     * @see com.taskapp.logic.TaskLogic#delete(int)
     */
    // public void inputDeleteInformation() {
    // }

    /**
     * 指定された文字列が数値であるかどうかを判定します。
     * 負の数は判定対象外とする。
     *
     * @param inputText 判定する文字列
     * @return 数値であればtrue、そうでなければfalse
     */
    // public boolean isNumeric(String inputText) {
    //     return false;
    // }
