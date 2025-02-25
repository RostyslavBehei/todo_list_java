package org.example.model;

import org.example.constant.TaskStatus;

public class WorkTask extends Task {

    public WorkTask(int id, String title, String description, TaskStatus taskStatus) {
        super(id, title, description, taskStatus);
    }

    @Override
    public String toString() {
        return "WorkTask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", taskStatus=" + getTaskStatus() +
                '}';
    }
}
