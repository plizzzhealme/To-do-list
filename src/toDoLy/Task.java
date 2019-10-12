package toDoLy;

import java.io.Serializable;
import java.time.LocalDate;

class Task implements Serializable {

    /**
     * Task title
     */
    private String title;

    /**
     * Task status (done or not)
     * true - done, false - undone
     */
    private boolean status;

    /**
     * Task category
     */
    private String category;

    /**
     * Task add date
     */
    private final LocalDate date;

    /**
     * When creating an object, date is set automatically
     */
    Task() {
        date = LocalDate.now();
    }

    /**
     * Print task
     * @return output format: date [title] Category: [category] - DONE/UNDONE
     */
    public String toString () {
        return "[" + date + "] [" + title + "] Category: [" + category + "] - " + (status ? "DONE" : "UNDONE");
    }

    /**
     * Set task title
     * @param t task title
     */
    void setTitle(String t) {
        title = t;
    }

    /**
     * Set task status
     * @param s true - done, false - undone
     */
    void setStatus(boolean s) {
        status = s;
    }

    /**
     * Check task status
     * @return true if done, false if undone
     */
    boolean isDone() {
        return status;
    }

    /**
     * Get task add date
     * @return task add date
     */
    LocalDate getDate() {
        return date;
    }

    /**
     * Get task category
     * @return task category
     */
    String getCategory() {
        return category;
    }

    /**
     * Set task category
     *
     * @param c category name
     */
    void setCategory(String c) {
        category = c;
    }
}