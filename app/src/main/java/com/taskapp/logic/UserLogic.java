package com.taskapp.logic;

import com.taskapp.dataaccess.UserDataAccess;
import com.taskapp.exception.AppException;
import com.taskapp.model.User;

public class UserLogic {
    private final UserDataAccess userDataAccess;

    public UserLogic() {
        userDataAccess = new UserDataAccess();
    }

    /**
     * 自動採点用に必要なコンストラクタのため、皆さんはこのコンストラクタを利用・削除はしないでください
     * @param userDataAccess
     */
    public UserLogic(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    /**
     * ユーザーのログイン処理を行います。
     *
     * @see com.taskapp.dataaccess.UserDataAccess#findByEmailAndPassword(String, String)
     * @param email ユーザーのメールアドレス
     * @param password ユーザーのパスワード
     * @return ログインしたユーザーの情報
     * @throws AppException メールアドレスとパスワードが一致するユーザーが存在しない場合にスローされます
     */
    public User login(String email, String password) throws AppException {
        // データアクセス層からメールアドレスとパスワードでユーザーを検索
        User user = userDataAccess.findByEmailAndPassword(email, password);
        
        if (user == null) {
            // ユーザーが見つからなかった場合に例外をスロー
            throw new AppException("既に登録されているメールアドレス、パスワードを入力してください");
        }
        
        // 見つかったユーザーを返す
        return user;
    }

    public User findUserByCode(int userCode) {
        // UserDataAccess を利用してユーザーを取得
        User user = userDataAccess.findByCode(userCode);
        if (user == null) {
            throw new RuntimeException("ユーザーが見つかりませんでした"); // ユーザーが存在しない場合は例外をスロー
        }
        return user;
    }
}
    