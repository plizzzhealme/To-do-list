package com.github.plizzzhealme.todolist.todoly;

import com.github.plizzzhealme.todolist.tasklist.TaskList;

import java.io.*;
import java.util.Scanner;

class Main {

    private static final String FILE = "todo.list";
    private static final String WELCOME_MESSAGE = "Welcome to ToDoLy";
    private static final String PICK_AN_EDIT_OPTION = "Pick an option: 1.Change title 2.Change category 3.Mark done 4.Mark undone 5.Remove";
    private static final String PICK_AN_OPTION = "Pick an option:\n1.Show task list\n2.Add new task\n3.Edit task\n4.Save and quit";
    private static final String PICK_SORT_TYPE = "Pick an option: 1.Sort by category 2.Sort by date";
    private static final String INPUT_NEW_TITLE = "Input new title";
    private static final String INPUT_NEW_CATEGORY = "Input new category";
    private static final String INPUT_POSITIVE_NUMBER = "You have to enter a positive number. Try again";
    private static final String PICK_TASK = "Choose a task to edit";
    private static final String CATEGORY_CHANGED = "Task category changed";
    private static final String TITLE_CHANGED = "Task title changed";
    private static final String STATUS_CHANGED = "Task status changed";
    private static final String TASK_REMOVED = "Task removed";
    private static final String TASK_ADDED = "Task added";
    private static final String EMPTY_LIST = "Your task list is empty";
    private static final String NO_OPTION = "You have not chosen any option";
    private static final String LIST_ELEMENTS = "You have %d tasks to do and %d tasks are done!%n";
    private static final String LIST_SIZE = "Your task list contains only %d elements! Try again!%n";

    public static void main(String[] args) throws IOException {
        TaskList taskList = readFromFile();
        welcomeMessage(taskList);
        boolean workFlag = true;
        while (workFlag) {
            print(PICK_AN_OPTION);
            int option = scanNumber();
            switch (option) {
                case 1:
                    sort(taskList);
                    printTaskList(taskList);
                    break;
                case 2:
                    addTask(taskList);
                    break;
                case 3:
                    editTask(taskList);
                    break;
                case 4:
                    writeToFile(taskList);
                    workFlag = false;
                    break;
                default:
                    print(NO_OPTION);
            }
        }
    }

    private static void editTask(TaskList taskList) {
        if (taskList.size() == 0) {
            print(EMPTY_LIST);
        } else {
            print(PICK_TASK);
            int taskIndex;
            while ((taskIndex = scanNumber()) > taskList.size()) {
                System.out.printf(LIST_SIZE, taskList.size());
            }
            print(PICK_AN_EDIT_OPTION);
            int option = scanNumber();
            switch (option) {
                case 1:
                    taskList.setTitle(taskIndex - 1, scanString(INPUT_NEW_TITLE));
                    print(TITLE_CHANGED);
                    break;
                case 2:
                    taskList.setCategory(taskIndex - 1, scanString(INPUT_NEW_CATEGORY));
                    print(CATEGORY_CHANGED);
                    break;
                case 3:
                    taskList.setDone(taskIndex - 1);
                    print(STATUS_CHANGED);
                    break;
                case 4:
                    taskList.setUndone(taskIndex - 1);
                    print(STATUS_CHANGED);
                    break;
                case 5:
                    taskList.removeTask(taskIndex - 1);
                    print(TASK_REMOVED);
                    break;
                default:
                    print(NO_OPTION);
            }
        }
    }

    private static void addTask(TaskList taskList) {
        taskList.addTask(scanString(INPUT_NEW_TITLE), scanString(INPUT_NEW_CATEGORY));
        print(TASK_ADDED);
    }

    private static void welcomeMessage(TaskList list) {
        print(WELCOME_MESSAGE);
        System.out.printf(LIST_ELEMENTS, list.tasksUndone(), list.tasksDone());
    }

    private static void printTaskList(TaskList list) {
        int n = list.size();
        if (n == 0) {
            print(EMPTY_LIST);
        } else {
            for (int i = 0; i < n; i++) {
                print(i + 1 + ". " + list.getTask(i));
            }
        }
    }

    private static TaskList readFromFile() {
        try (var fis = new FileInputStream(FILE);
             var ois = new ObjectInputStream(fis)) {
            return (TaskList) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            return new TaskList();
        }
    }

    private static void writeToFile(TaskList list) throws IOException {
        try (var fos = new FileOutputStream(FILE);
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(list);
            oos.flush();
        }
    }

    private static void sort(TaskList list) {
        print(PICK_SORT_TYPE);
        int sortType = scanNumber();
        if (sortType == 1) {
            list.sortByCategory();
        } else {
            list.sortByDate();
        }
    }

    private static int scanNumber() {
        Scanner in = new Scanner(System.in);
        int number;
        do {
            while (!in.hasNextInt()) {
                print(INPUT_POSITIVE_NUMBER);
                in.next();
            }
            number = in.nextInt();
            if (number < 1) {
                print(INPUT_POSITIVE_NUMBER);
            }
        } while (number <= 0);
        return number;
    }

    private static void print(String arg) {
        System.out.println(arg);
    }

    private static String scanString(String msg) {
        print(msg);
        Scanner in = new Scanner(System.in);
        return in.next();
    }
}