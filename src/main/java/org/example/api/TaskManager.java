package org.example.api;

import org.example.constant.TaskStatus;
import org.example.exeption.InvalidTaskDateException;
import org.example.model.PersonalTask;
import org.example.model.Task;
import org.example.model.WorkTask;

import java.util.List;

public interface TaskManager {
    void addTask(String title, String description, TaskStatus status) throws InvalidTaskDateException;
    void addWorkTask(String title, String description, TaskStatus status);
    void addPersonalTask(String title, String description, TaskStatus status);

    List<Task> getTasks();
    List<WorkTask> getWorkTasks();
    List<PersonalTask> getPersonalTask();

    void removeTask(int id);
    void removeAllTasks();

    void changeTaskStatus(int id, TaskStatus taskStatus);

    List<Task> searchTask(String keyword);
    Task getTaskById(int id);
}
