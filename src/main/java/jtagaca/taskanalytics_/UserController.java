package jtagaca.taskanalytics_;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    User tempUser = new User();
//    @FXML
//    publi
//    c ComboBox<String> todoCombo;
    public UserController() {
        tempUser =LoginController.getUser();
    }

    @FXML
    public ComboBox<String> todoCombo;

    for (int i = 0; i < tempUser.getTodos.length; i++)

    {
        todoList.add
    }

    ObservableList<String> todoList = FXCollections.observableArrayList();



    //make a constructor
//     public UserController(Todo[] todos) {
//         for (int i = 0; i < todos.length; i++) {
////             this.todoList.add(todos[i].getTitle());
//
//     }




    @FXML
    public void comboChanged(ActionEvent actionEvent) {
        String selected = todoCombo.getValue();
        System.out.println(selected);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        todoCombo.setItems(todoList);
    }



}
