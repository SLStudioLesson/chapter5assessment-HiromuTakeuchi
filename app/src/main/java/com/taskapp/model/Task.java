package com.taskapp.model;

public class Task {
    private int code;
    private String name;
    private int status;
    private int repUserCode;

    public Task(int code, String name, int status, int repUserCode) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.repUserCode = repUserCode;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public int getRepUserCode() {
        return repUserCode;
    }
}