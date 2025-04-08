package org.example.service;

import org.example.constant.TaskStatus;
import org.example.exeption.InvalidTaskDateException;
import org.example.model.PersonalTask;
import org.example.model.Task;
import org.example.model.WorkTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceImplTest {

    private TaskServiceImpl taskServiceImpl;

    @BeforeEach
    void setUp() {
        taskServiceImpl = new TaskServiceImpl();
    }

    @Test
    @DisplayName("Add general task")
    void addTask() throws InvalidTaskDateException {
        taskServiceImpl.addTask("Title", "Description", TaskStatus.TODO);

        List<Task> tasks = taskServiceImpl.getTasks();
        assertEquals(1, tasks.size());

        Task task = tasks.getFirst();
        assertEquals("Title", task.getTitle());
        assertEquals("Description", task.getDescription());
        assertEquals(TaskStatus.TODO, task.getTaskStatus());
    }

    @Test
    @DisplayName("Add work task and check class")
    void addWorkTask() {
        taskServiceImpl.addWorkTask("Work", "Desc", TaskStatus.IN_PROGRESS);

        List<Task> tasks = taskServiceImpl.getTasks();
        assertEquals(1, tasks.size());
        assertTrue(tasks.getFirst() instanceof WorkTask);
    }

    @Test
    @DisplayName("Add personal task and check class")
    void addPersonalTask() {
        taskServiceImpl.addPersonalTask("Home", "Clean up", TaskStatus.TODO);

        List<Task> tasks = taskServiceImpl.getTasks();
        assertEquals(1, tasks.size());
        assertTrue(tasks.getFirst() instanceof PersonalTask);
    }

    @Test
    @DisplayName("Get all tasks")
    void getTasks() throws InvalidTaskDateException {
        taskServiceImpl.addTask("Task 1", "Desc", TaskStatus.TODO);
        assertEquals(1, taskServiceImpl.getTasks().size());
    }

    @Test
    @DisplayName("Get only work tasks")
    void getWorkTasks() {
        taskServiceImpl.addWorkTask("Work", "Project", TaskStatus.TODO);
        assertEquals(1, taskServiceImpl.getWorkTasks().size());
    }

    @Test
    @DisplayName("Get only personal tasks")
    void getPersonalTask() {
        taskServiceImpl.addPersonalTask("Chill", "Netflix", TaskStatus.TODO);
        assertEquals(1, taskServiceImpl.getPersonalTask().size());
    }

    @Test
    @DisplayName("Remove a task by ID")
    void removeTask() throws InvalidTaskDateException {
        taskServiceImpl.addTask("To delete", "Desc", TaskStatus.TODO);
        int id = taskServiceImpl.getTasks().getFirst().getId();

        taskServiceImpl.removeTask(id);
        assertTrue(taskServiceImpl.getTasks().isEmpty());
    }

    @Test
    @DisplayName("Remove all tasks")
    void removeAllTasks() throws InvalidTaskDateException {
        taskServiceImpl.addTask("Task 1", "Desc", TaskStatus.TODO);
        taskServiceImpl.addTask("Task 2", "Desc", TaskStatus.TODO);

        taskServiceImpl.removeAllTasks();
        assertTrue(taskServiceImpl.getTasks().isEmpty());
    }

    @Test
    @DisplayName("Change task status")
    void changeTaskStatus() throws InvalidTaskDateException {
        taskServiceImpl.addTask("Change me", "Desc", TaskStatus.TODO);
        int id = taskServiceImpl.getTasks().get(0).getId();

        taskServiceImpl.changeTaskStatus(id, TaskStatus.COMPLETED);
        assertEquals(TaskStatus.COMPLETED, taskServiceImpl.getTasks().get(0).getTaskStatus());
    }

    @Test
    @DisplayName("Search task by keyword")
    void searchTask() throws InvalidTaskDateException {
        taskServiceImpl.addTask("Find me", "This is the description", TaskStatus.TODO);

        List<Task> result = taskServiceImpl.searchTask("find");
        assertEquals(1, result.size());
        assertEquals(taskServiceImpl.getTasks().get(0), result.get(0));
    }

    @Test
    @DisplayName("Get task by ID")
    void getTaskById() throws InvalidTaskDateException {
        taskServiceImpl.addTask("ID Test", "Check by ID", TaskStatus.TODO);
        Task expected = taskServiceImpl.getTasks().get(0);

        Task actual = taskServiceImpl.getTaskById(expected.getId());
        assertEquals(expected, actual);
    }
}
