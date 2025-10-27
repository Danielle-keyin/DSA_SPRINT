package com.keyin.todo;
package com.keyin.todo;

import java.util.Arrays;

public class Main {

    private static final int MAX_USERS = 10;
    private static final User[] USERS = new User[MAX_USERS];
    private static int userCount = 0;

    public static void main(String[] args) {

        addUser(new User("George"));
        addUser(new User("Gina"));

        findUser("George").addTask("Buy groceries");
        findUser("George").addTask("Finish Java assignment");
        findUser("George").addTask("Email instructor");

        findUser("Gina").addTask("Book dentist appointment");
        findUser("Gina").addTask("Pay electricity bill");

        findUser("George").completeTaskByDescription("Finish Java assignment");
        findUser("Gina").completeTaskByIndex(2);

        printAllUsersAndTasks();

        boolean ok = addUser(new User("George")); // should be false (duplicate)
        System.out.println("Adding duplicate 'George' successful? " + ok);
    }

    private static boolean addUser(User user) {
        if (userCount >= MAX_USERS) {
            System.out.println("Cannot add user: maximum capacity reached.");
            return false;
        }
        if (findUser(user.getName()) != null) {
            System.out.println("Cannot add user: name already exists (" + user.getName() + ").");
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
        System.out.println("=== To-Do Lists ===");
        Arrays.stream(USERS, 0, userCount).forEach(User::printTasks);
    }
}
