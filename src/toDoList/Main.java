package toDoList;

import java.io.*;
import java.util.Scanner;

class Main {

    private static final String EDIT_MESSAGE = "Pick an option: 1.Change title 2.Change category 3.Mark done 4.Mark undone 5.Remove";
    private static final String WELCOME_MESSAGE = "Welcome to ToDoLy";
    private static final String PICK_AN_OPTION_MESSAGE = "Pick an option:\n[1]Show task list\n[2]Add new task\n[3]Edit task\n[4]Save and quit";
    private static String FILE = "temp.out";

    public static void main(String[] args) throws IOException {
        TaskList taskList = readFromFile();
        welcomeMessage(taskList);
        boolean workFlag = true;
        while (workFlag) {
            System.out.println(PICK_AN_OPTION_MESSAGE);
            int option = inputNumber();
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
            }
        }
    }

    private static void editTask(TaskList taskList) {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a task to edit");
        int taskIndex;
        while ((taskIndex = inputNumber()) > taskList.size()) {
            System.out.println("Your task list contains only " + taskList.size() + " elements! Try again!");
        }
        System.out.println(EDIT_MESSAGE);
        int option = inputNumber();
        switch (option) {
            case 1:
                System.out.println("Input new title");
                taskList.setTitle(taskIndex - 1, in.next());
                System.out.println("Task title changed!");
                break;
            case 2:
                System.out.println("Input new category");
                taskList.setCategory(taskIndex - 1, in.next());
                System.out.println("Task category changed!");
                break;
            case 3:
                taskList.setDone(taskIndex - 1);
                System.out.println("Task marked as done!");
                break;
            case 4:
                taskList.setUndone(taskIndex - 1);
                System.out.println("Task marked as undone!");
                break;
            case 5:
                taskList.removeTask(taskIndex - 1);
                System.out.println("Task removed from your list");
                break;
            default:
                System.out.println("You have not chosen any option!");
        }
    }

    private static void addTask(TaskList taskList) {
        Scanner in = new Scanner(System.in);
        Task task = new Task();
        System.out.println("Input task title:");
        task.setTitle(in.next());
        System.out.println("Input task category");
        task.setCategory(in.next());
        taskList.addTask(task);
        System.out.println("New task added to your list");
    }

    private static void welcomeMessage(TaskList list) {
        System.out.println(WELCOME_MESSAGE);
        System.out.printf("You have %d tasks to do and %d tasks are done!\n", list.tasksUndone(), list.tasksDone());
    }

    private static void printTaskList(TaskList list) {
        System.out.println("Task list:");
        int n = list.size();
        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + ". " + list.getTask(i));
        }
    }

    private static TaskList readFromFile() {
        //read task list from file
        try {
            FileInputStream fis = new FileInputStream(FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (TaskList) ois.readObject();
        }
        //create new task list if fail
        catch (IOException | ClassNotFoundException e) {
            return new TaskList();
        }
    }

    private static void writeToFile(TaskList list) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.flush();
        oos.close();
    }

    private static void sort(TaskList list) {
        System.out.println("1 - Sort by category");
        System.out.println("2 - Sort by date");
        int sortType = inputNumber();
        if (sortType == 1) {
            list.sortByCategory();
        } else {
            list.sortByDate();
        }
    }

    /**
     * read and validate numbers from terminal
     *
     * @return positive number
     */
    private static int inputNumber() {
        Scanner in = new Scanner(System.in);
        int number;
        do {
            while (!in.hasNextInt()) {
                System.out.println("You have to enter a positive number! Try again:");
                in.next();
            }
            number = in.nextInt();
            if (number < 1) {
                System.out.println("You have to enter a positive number! Try again:");
            }
        } while (number <= 0);
        return number;
    }
}