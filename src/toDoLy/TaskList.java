package toDoLy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

class TaskList implements Serializable {
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
    TaskList() {
        list = new ArrayList<>();
    }

    /**
     * add new task to the end of list
     *
     * @param t task object
     */
    void addTask(Task t) {
        list.add(t);
        undone++;
    }

    /**
     * remove task from list
     *
     * @param i task index
     */
    void removeTask(int i) {
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
    String getTask(int i) {
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
    void setTitle(int i, String s) {
        list.get(i).setTitle(s);
    }

    /**
     * set task category
     *
     * @param i task index
     * @param s new category
     */
    void setCategory(int i, String s) {
        list.get(i).setCategory(s);
    }

    /**
     * make task done
     *
     * @param i task index
     */
    void setDone(int i) {
        list.get(i).setStatus(true);
        done++;
        undone--;
    }

    /**
     * make task undone
     *
     * @param i task index
     */
    void setUndone(int i) {
        list.get(i).setStatus(false);
        done--;
        undone++;
    }

    /**
     * sort list by date
     */
    void sortByDate() {
        list.sort(Comparator.comparing(Task::getDate));
    }

    /**
     * sort list by category
     */
    void sortByCategory() {
        list.sort(Comparator.comparing(Task::getCategory));
    }

    /**
     * @return number of done tasks
     */
    int tasksDone() {
        return done;
    }

    /**
     * @return number of undone tasks
     */
    int tasksUndone() {
        return undone;
    }

    /**
     * @return list size
     */
    int size() {
        return done + undone;
    }

}