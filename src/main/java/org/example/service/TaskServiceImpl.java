package org.example.service;

import org.example.api.TaskManager;
import org.example.constant.TaskStatus;
import org.example.exeption.InvalidTaskDateException;
import org.example.exeption.InvalidTaskStateException;
import org.example.model.PersonalTask;
import org.example.model.Task;
import org.example.model.WorkTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskServiceImpl implements TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private static int taskId = 0;

    @Override
    public void addTask(String title, String description, TaskStatus taskStatus) throws InvalidTaskDateException {
        if (title == null || title.isEmpty()) {
            throw new InvalidTaskDateException("Task must have a title!");
        }
        if (description == null || description.isEmpty()) {
            throw new InvalidTaskDateException("Task must have a description!");
        }
        if (taskStatus == null) {
            throw new InvalidTaskStateException("Task must have a status!");
        }
        tasks.add(new Task(taskId, title, description, taskStatus));
        taskId++;
    }

    @Override
    public void addWorkTask(String title, String description, TaskStatus taskStatus) {
        tasks.add(new WorkTask(taskId, title, description, taskStatus));
        taskId++;
    }

    @Override
    public void addPersonalTask(String title, String description, TaskStatus taskStatus) {
        tasks.add(new PersonalTask(taskId, title, description, taskStatus));
        taskId++;
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public List<WorkTask> getWorkTasks() {
        List<WorkTask> workTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof WorkTask) {
                workTasks.add((WorkTask) task);
            }
        }
        return workTasks;
    }

    @Override
    public List<PersonalTask> getPersonalTask() {
        List<PersonalTask> personalTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof PersonalTask) {
                personalTasks.add((PersonalTask) task);
            }
        }
        return personalTasks;
    }

    @Override
    public void removeTask(int id) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == id) {
                iterator.remove();
                return;
            }
        }
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public void changeTaskStatus(int id, TaskStatus taskStatus) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTaskStatus(taskStatus);
                return;
            }
        }
    }

    @Override
    public List<Task> searchTask(String keyword) {
        List<Task> searchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                searchedTasks.add(task);
            }
        }
        return searchedTasks;
    }

    @Override
    public Task getTaskById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
    }
}
