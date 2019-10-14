package todolist.tasklist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskList implements Serializable {
    /**
     * storage for tasks
     */
    private final ArrayList<Task> list;
    /**
     * number of done tasks in list
     */
    private int done;
    /**
     * number of undone tasks in list
     */
    private int undone;

    /**
     * create empty task list on initiation
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Add new task to the end of list, date is set automatically
     * @param title task title
     * @param category  task category
     */
    public void addTask(String title, String category) {
        Task task = new Task();
        task.setTitle(title);
        task.setCategory(category);
        list.add(task);
        undone++;
    }

    /**
     * remove task from list
     *
     * @param i task index
     */
    public void removeTask(int i) {
        if (i < list.size()) {
            if (list.get(i).isDone()) {
                done--;
            } else {
                undone--;
            }
            list.remove(i);
        }
    }

    /**
     * print task
     *
     * @param i task index
     * @return String with task description or empty String when error
     */
    public String getTask(int i) {
        if (i < list.size()) {
            return list.get(i).toString();
        } else {
            return "";
        }
    }

    /**
     * set task title
     *
     * @param i task index
     * @param s new title
     */
    public void setTitle(int i, String s) {
        list.get(i).setTitle(s);
    }

    /**
     * set task category
     *
     * @param i task index
     * @param s new category
     */
    public void setCategory(int i, String s) {
        list.get(i).setCategory(s);
    }

    /**
     * make task done
     *
     * @param i task index
     */
    public void setDone(int i) {
        list.get(i).setStatus(true);
        done++;
        undone--;
    }

    /**
     * make task undone
     *
     * @param i task index
     */
    public void setUndone(int i) {
        list.get(i).setStatus(false);
        done--;
        undone++;
    }

    /**
     * sort list by date
     */
    public void sortByDate() {
        list.sort(Comparator.comparing(Task::getDate));
    }

    /**
     * sort list by category
     */
    public void sortByCategory() {
        list.sort(Comparator.comparing(Task::getCategory));
    }

    /**
     * @return number of done tasks
     */
    public int tasksDone() {
        return done;
    }

    /**
     * @return number of undone tasks
     */
    public int tasksUndone() {
        return undone;
    }

    /**
     * @return list size
     */
    public int size() {
        return done + undone;
    }

}