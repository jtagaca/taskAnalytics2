package jtagaca.taskanalytics_;

public class User {
    private String username;
//    array of todo
    private Todo[] todos;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public Todo[] getTodos() {
        return todos;
    }

    public User(String username, Todo[] todos) {
        this.username = username;
//        while there is todos in the todos array from above then add them to the todos array in this class
        this.todos = todos;

//        this.todos = todos;

    }


    public Todo[] getTodo() {
        return todos;
    }
}

