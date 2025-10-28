package com.keyin.todo;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int MAX_USERS = 10;
    private static final User[] USERS = new User[MAX_USERS];
    private static int userCount = 0;

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        runMenu();
    }

    private static void runMenu() {
        while (true) {
            System.out.println("\n=== To-Do List Manager ===");
            System.out.println("1) Add User");
            System.out.println("2) Add Task to User");
            System.out.println("3) Mark Task Completed (by index)");
            System.out.println("4) View Tasks for a User");
            System.out.println("5) View All Users & Tasks");
            System.out.println("6) List Users");
            System.out.println("0) Exit");
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> menuAddUser();
                case 2 -> menuAddTask();
                case 3 -> menuMarkCompleted();
                case 4 -> menuViewTasksForUser();
                case 5 -> printAllUsersAndTasks();
                case 6 -> listUsers();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void menuAddUser() {
        if (userCount >= MAX_USERS) {
            System.out.println("Cannot add user: capacity reached.");
            return;
        }
        String name = promptNonEmpty("Enter new user's name: ");
        if (findUser(name) != null) {
            System.out.println("Cannot add user: name already exists.");
            return;
        }
        addUser(new User(name));
        System.out.println("User '" + name + "' added.");
    }

    private static void menuAddTask() {
        if (userCount == 0) {
            System.out.println("No users yet. Add a user first.");
            return;
        }
        listUsers();
        String name = promptNonEmpty("Enter user name to add a task to: ");
        User u = findUser(name);
        if (u == null) {
            System.out.println("User not found.");
            return;
        }
        String desc = promptNonEmpty("Enter task description: ");
        u.addTask(desc);
        System.out.println("Task added to " + u.getName() + ".");
    }

    private static void menuMarkCompleted() {
        if (userCount == 0) {
            System.out.println("No users yet.");
            return;
        }
        listUsers();
        String name = promptNonEmpty("Enter user name: ");
        User u = findUser(name);
        if (u == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println("Current tasks:");
        u.printTasks();
        int idx = readInt("Enter 1-based task index to mark completed: ");
        boolean ok = u.completeTaskByIndex(idx);
        System.out.println(ok ? "Task marked completed." : "Invalid index.");
    }

    private static void menuViewTasksForUser() {
        if (userCount == 0) {
            System.out.println("No users yet.");
            return;
        }
        listUsers();
        String name = promptNonEmpty("Enter user name to view tasks: ");
        User u = findUser(name);
        if (u == null) {
            System.out.println("User not found.");
            return;
        }
        u.printTasks();
    }

    private static boolean addUser(User user) {
        if (userCount >= MAX_USERS) {
            return false;
        }
        if (findUser(user.getName()) != null) {
            return false;
        }
        USERS[userCount++] = user;
        return true;
    }

    private static User findUser(String name) {
        for (int i = 0; i < userCount; i++) {
            if (USERS[i].getName().equals(name)) return USERS[i];
        }
        return null;
    }

    private static void printAllUsersAndTasks() {
        if (userCount == 0) {
            System.out.println("(no users yet)");
            return;
        }
        System.out.println("\n=== To-Do Lists ===");
        Arrays.stream(USERS, 0, userCount).forEach(User::printTasks);
    }

    private static void listUsers() {
        if (userCount == 0) {
            System.out.println("(no users yet)");
            return;
        }
        System.out.println("Users:");
        for (int i = 0; i < userCount; i++) {
            System.out.println("  - " + USERS[i].getName());
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = SC.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String promptNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = SC.nextLine();
            if (line != null && !line.trim().isBlank()) {
                return line.trim();
            }
            System.out.println("Input cannot be empty. Try again.");
        }
    }
}
