package com.taskapp.logic;

import java.time.LocalDate;
import java.util.List;

import com.taskapp.dataaccess.LogDataAccess;
import com.taskapp.dataaccess.TaskDataAccess;
import com.taskapp.dataaccess.UserDataAccess;
import com.taskapp.model.Log;
import com.taskapp.model.Task;
import com.taskapp.model.User;

public class TaskLogic {
    private final TaskDataAccess taskDataAccess;
    private final LogDataAccess logDataAccess;
    private final UserDataAccess userDataAccess;

    public TaskLogic() {
        taskDataAccess = new TaskDataAccess();
        logDataAccess = new LogDataAccess();
        userDataAccess = new UserDataAccess();
    }

    /**
     * 自動採点用に必要なコンストラクタのため、皆さんはこのコンストラクタを利用・削除はしないでください
     * @param taskDataAccess
     * @param logDataAccess
     * @param userDataAccess
     */
    public TaskLogic(TaskDataAccess taskDataAccess, LogDataAccess logDataAccess, UserDataAccess userDataAccess) {
        this.taskDataAccess = taskDataAccess;
        this.logDataAccess = logDataAccess;
        this.userDataAccess = userDataAccess;
    }

    /**
     * 全てのタスクを表示します。
     *
     * @see com.taskapp.dataaccess.TaskDataAccess#findAll()
     * @param loginUser ログインユーザー
     */
    public void showAll(User loginUser) {
        List<Task> tasks = taskDataAccess.findAll(); // タスク一覧を取得
        for (Task task : tasks) {
            User assignedUser = userDataAccess.findByCode(task.getRepUserCode()); // 担当者を取得
            String assignedUserName = assignedUser.getName();
            
            // 担当者がログインユーザーの場合は特別なメッセージを表示
            if (assignedUser.getId() == loginUser.getId()) {
                assignedUserName = "あなたが担当しています";
            }
    
            // ステータスの表示変換
            String status;
            switch (task.getStatus()) {
                case 0:
                    status = "未着手";
                    break;
                case 1:
                    status = "着手中";
                    break;
                case 2:
                    status = "完了";
                    break;
                default:
                    status = "不明";
            }
    
            // タスク情報を表示
            System.out.println(String.format("%d. タスク名：%s, 担当者名：%s, ステータス：%s", 
                task.getCode(), task.getName(), assignedUserName, status));
        }
    }

    // ステータスの数値を文字列に変換
    private String getStatus(int status) {
        switch (status) {
            case 0: return "未着手";
            case 1: return "着手中";
            case 2: return "完了";
            default: return "不明";
        }
    }

    /**
     * 新しいタスクを保存します。
     *
     * @see com.taskapp.dataaccess.UserDataAccess#findByCode(int)
     * @see com.taskapp.dataaccess.TaskDataAccess#save(com.taskapp.model.Task)
     * @see com.taskapp.dataaccess.LogDataAccess#save(com.taskapp.model.Log)
     * @param code タスクコード
     * @param name タスク名
     * @param repUserCode 担当ユーザーコード
     * @param loginUser ログインユーザー
     * @throws AppException ユーザーコードが存在しない場合にスローされます
     */
    public void save(int taskCode, String taskName, int assignedUserCode, User loginUser) {
        Task newTask = new Task(taskCode, taskName, 0, assignedUserCode); // 新規タスク（ステータスは0）
        taskDataAccess.save(newTask); // tasks.csv に保存
    
        Log newLog = new Log(taskCode, loginUser.getId(), 0, LocalDate.now().toString());
        logDataAccess.save(newLog); // logs.csv に保存
    }
}
    /**
     * タスクのステータスを変更します。
     *
     * @see com.taskapp.dataaccess.TaskDataAccess#findByCode(int)
     * @see com.taskapp.dataaccess.TaskDataAccess#update(com.taskapp.model.Task)
     * @see com.taskapp.dataaccess.LogDataAccess#save(com.taskapp.model.Log)
     * @param code タスクコード
     * @param status 新しいステータス
     * @param loginUser ログインユーザー
     * @throws AppException タスクコードが存在しない、またはステータスが前のステータスより1つ先でない場合にスローされます
     */
    // public void changeStatus(int code, int status,
    //                         User loginUser) throws AppException {
    // }

    /**
     * タスクを削除します。
     *
     * @see com.taskapp.dataaccess.TaskDataAccess#findByCode(int)
     * @see com.taskapp.dataaccess.TaskDataAccess#delete(int)
     * @see com.taskapp.dataaccess.LogDataAccess#deleteByTaskCode(int)
     * @param code タスクコード
     * @throws AppException タスクコードが存在しない、またはタスクのステータスが完了でない場合にスローされます
     */
    // public void delete(int code) throws AppException {
    // }