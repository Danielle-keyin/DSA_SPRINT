package com.keyin.todo;

public class TaskList {

    private static class Node {
        Task data;
        Node next;
        Node(Task data) { this.data = data; }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addTask(String description) {
        Task t = new Task(description);
        Node n = new Node(t);
        if (head == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    public boolean markCompletedByDescription(String description) {
        if (description == null) return false;
        String needle = description.trim().toLowerCase();
        Node cur = head;
        while (cur != null) {
            if (cur.data.getDescription().toLowerCase().equals(needle)) {
                cur.data.complete();
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean markCompletedByIndex(int oneBasedIndex) {
        if (oneBasedIndex < 1 || oneBasedIndex > size) return false;
        int i = 1;
        Node cur = head;
        while (cur != null) {
            if (i == oneBasedIndex) {
                cur.data.complete();
                return true;
            }
            i++;
            cur = cur.next;
        }
        return false;
    }

    public void printAll() {
        if (head == null) {
            System.out.println("  (no tasks yet)");
            return;
        }
        int i = 1;
        Node cur = head;
        while (cur != null) {
            System.out.printf("  %d. %s%n", i, cur.data.toString());
            cur = cur.next;
            i++;
        }
    }

    public int size() {
        return size;
    }
}
