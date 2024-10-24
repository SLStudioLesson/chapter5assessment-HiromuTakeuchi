package com.taskapp.model;

import java.time.LocalDate;

public class Log {
    private int taskCode;
    private int changeUserCode;
    private int status;
    private LocalDate changeDate;
    private int changeUserCode2;
    private int changeUserCode3;

    /**
     * @param taskCode
     * @param changeUserCode
     * @param status
     * @param string
     */
    public Log(int taskCode, int changeUserCode, int status, String string) {
        this.taskCode = taskCode;
        changeUserCode3 = changeUserCode;
        changeUserCode2 = changeUserCode;
        this.changeUserCode = changeUserCode;
        this.status = status;
        this.changeDate = string;
    }

    public Log(int taskCode2, int id, int status2, String string) {
        //TODO Auto-generated constructor stub
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		if (taskCode != other.taskCode)
			return false;
		return true;
	}
}