package com.taskapp.dataaccess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.taskapp.model.Task;

public class TaskDataAccess {

    private final String filePath;

    private final UserDataAccess userDataAccess;

    public TaskDataAccess() {
        filePath = "app/src/main/resources/tasks.csv";
        userDataAccess = new UserDataAccess();
    }

    /**
     * 自動採点用に必要なコンストラクタのため、皆さんはこのコンストラクタを利用・削除はしないでください
     * @param filePath
     * @param userDataAccess
     */
    public TaskDataAccess(String filePath, UserDataAccess userDataAccess) {
        this.filePath = filePath;
        this.userDataAccess = userDataAccess;
    }

    /**
     * CSVから全てのタスクデータを取得します。
     *
     * @see com.taskapp.dataaccess.UserDataAccess#findByCode(int)
     * @return タスクのリスト
     */
    public List<Task> findAll() {
        List<Task> taskList = new ArrayList<>();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("tasks.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            br.readLine(); // ヘッダー行を読み飛ばす
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Task task = new Task(
                    Integer.parseInt(data[0]),  // タスクコード
                    data[1],                    // タスク名
                    Integer.parseInt(data[2]),  // ステータス
                    Integer.parseInt(data[3])   // 担当者コード
                );
                taskList.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }
    
    /**
     * タスクをCSVに保存します。
     * @param task 保存するタスク
     */
    public void save(int taskCode, String taskName, int status, int userCode) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.csv", true))) {
            writer.write(taskCode + "," + taskName + "," + status + "," + userCode);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Task newTask) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}

    

    /**
     * コードを基にタスクデータを1件取得します。
     * @param code 取得するタスクのコード
     * @return 取得したタスク
     */
    // public Task findByCode(int code) {
    //     try () {

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

    /**
     * タスクデータを更新します。
     * @param updateTask 更新するタスク
     */
    // public void update(Task updateTask) {
    //     try () {

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    /**
     * コードを基にタスクデータを削除します。
     * @param code 削除するタスクのコード
     */
    // public void delete(int code) {
    //     try () {

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    /**
     * タスクデータをCSVに書き込むためのフォーマットを作成します。
     * @param task フォーマットを作成するタスク
     * @return CSVに書き込むためのフォーマット文字列
     */
    // private String createLine(Task task) {
    // }