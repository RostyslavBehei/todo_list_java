package org.example.model;

import org.example.constant.TaskStatus;

public class PersonalTask extends Task {

    public PersonalTask(int id, String title, String description, TaskStatus taskStatus) {
        super(id, title, description, taskStatus);
    }

    @Override
    public String toString() {
        return "PersonalTask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", taskStatus=" + getTaskStatus() +
                '}';
    }
}
