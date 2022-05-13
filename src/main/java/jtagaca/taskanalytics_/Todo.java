package jtagaca.taskanalytics_;

public class Todo {
    private final String date;
    private String title;
    private float timespent;

    public Todo(String title, float timespent, String date) {
        this.title = title;
        this.timespent = timespent;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public float getTimespent() {
        return timespent;
    }


    public Object getDate() {
        return date;
    }
}
