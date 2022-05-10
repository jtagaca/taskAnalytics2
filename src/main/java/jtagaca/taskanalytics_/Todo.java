package jtagaca.taskanalytics_;

public class Todo {
    private String title;
    private int timespent;

    public Todo(String title, int timespent) {
        this.title = title;
        this.timespent = timespent;
    }

    public String getTitle() {
        return title;
    }

    public int getTimespent() {
        return timespent;
    }
}
