package jtagaca.taskanalytics_;

public class User {
    private String username;
//    array of todo
    private Todo[] todos;

    public User(String username, Todo[] todos) {
        this.username = username;
//        while there is todos in the todos array from above then add them to the todos array in this class
        for (int i = 0; i < todos.length; i++) {
            this.todos[i] = new Todo(todos[i].getTitle(), todos[i].getTimespent());
        }

//        this.todos = todos;

    }



}

