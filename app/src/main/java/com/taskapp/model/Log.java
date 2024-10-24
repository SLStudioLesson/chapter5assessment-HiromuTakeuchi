package com.taskapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Log {
    private int taskCode;
    private int changeUserCode;
    private int status;
    private LocalDate changeDate;

    // コンストラクタ
    public Log(int taskCode, int changeUserCode, int status, String dateString) {
        this.taskCode = taskCode;
        this.changeUserCode = changeUserCode;
        this.status = status;
        // 文字列をLocalDateに変換
        this.changeDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public Log(int taskCode, int changeUserCode, int status, LocalDate changeDate) {
        this.taskCode = taskCode;
        this.changeUserCode = changeUserCode;
        this.status = status;
        this.changeDate = changeDate;
    }

    public int getTaskCode() {
        return this.taskCode;
    }

    public int getChangeUserCode() {
        return this.changeUserCode;
    }

    public int getStatus() {
        return this.status;
    }

    public LocalDate getChangeDate() {
        return this.changeDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + taskCode;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Log other = (Log) obj;
        return taskCode == other.taskCode;
    }
}
