package com.taskapp.dataaccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.taskapp.model.User;

public class UserDataAccess {
    private final String filePath;

    public UserDataAccess() {
        filePath = "app/src/main/resources/users.csv";
    }

    /**
     * 自動採点用に必要なコンストラクタのため、皆さんはこのコンストラクタを利用・削除はしないでください
     * @param filePath
     */
    public UserDataAccess(String filePath) {
        this.filePath = filePath;
    }

    /**
     * メールアドレスとパスワードを基にユーザーデータを探します。
     * @param email メールアドレス
     * @param password パスワード
     * @return 見つかったユーザー、またはnull
     */
    public User findByEmailAndPassword(String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    String storedEmail = values[2];
                    String storedPassword = values[3];
                    if (storedEmail.equals(email) && storedPassword.equals(password)) {
                        return new User(
                            Integer.parseInt(values[0]), // Code
                            values[1]
                        );
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * コードを基にユーザーデータを取得します。
     * @param userCode 取得するユーザーのコード
     * @return 見つかったユーザー、またはnull
     */
    public User findByCode(int userCode) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // ヘッダー行をスキップ
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                // 値を適切に取り出す
                int code = Integer.parseInt(values[0].trim()); // ユーザーコード
                String name = values[1].trim(); // ユーザー名

                if (code == userCode) {
                    return new User(code, name); // 一致するユーザーを返す
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("ユーザーコードのフォーマットが不正です: " + e.getMessage());
        }
        return null; // ユーザーが見つからなかった場合は null を返す
    }

    /**
     * メールアドレスを基にユーザーデータを探します。
     * @param email メールアドレス
     * @return 見つかったユーザー、またはnull
     */
    public User findByEmail(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // ヘッダー行をスキップ
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    String storedEmail = values[2].trim(); // メールアドレス
                    if (storedEmail.equals(email)) {
                        return new User(
                            Integer.parseInt(values[0]), // Code
                            values[1].trim() // 名前
                        );
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // ユーザーが見つからなかった場合は null を返す
    }
}
