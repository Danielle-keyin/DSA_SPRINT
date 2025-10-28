package com.keyin.todo;

public class User {
    private final String name;
    private final TaskList tasks = new TaskList();

    public User(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void addTask(String description) {
        tasks.addTask(description);
    }

    public boolean completeTaskByDescription(String description) {
        return tasks.markCompletedByDescription(description);
    }

    public boolean completeTaskByIndex(int oneBasedIndex) {
        return tasks.markCompletedByIndex(oneBasedIndex);
    }

    public void printTasks() {
        System.out.println("Tasks for " + name + ":");
        tasks.printAll();
        System.out.println();
    }
}
