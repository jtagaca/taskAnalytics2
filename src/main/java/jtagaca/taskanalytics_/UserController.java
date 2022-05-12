package jtagaca.taskanalytics_;

import javafx.animation.KeyFrame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    float elapsedTime ;
    User tempUser = new User();
//    @FXML
//    publi
        ObservableList<String> todoList = FXCollections.observableArrayList();
    Todo[] todos;
//    c ComboBox<String> todoCombo;
    public UserController() {

    }

    @FXML
    public ComboBox<String> todoCombo;




//
//    int length = todos.length;
//     for (int i = 0; i < length; i++)
//
//    {
//        todoList.add
//    }

    //make a constructor
//     public UserController(Todo[] todos) {
//         for (int i = 0; i < todos.length; i++) {
////             this.todoList.add(todos[i].getTitle());
//
//     }

@FXML
    Label ChosenTxt;

    @FXML
    Label TimerLabel;
    StopWatch stopWatch = new StopWatch();
    @FXML
    public void comboChanged(ActionEvent actionEvent) {
        String selected = todoCombo.getValue();
        ChosenTxt.setText(selected);

    }
    String areWeRunning;


    @FXML
    Button startButton;

    @FXML
    public void onButtonStart(ActionEvent actionEvent) {

        if (stopWatch.isRunning() == false) {
            stopWatch = new StopWatch(TimerLabel);
        }
        startButton.setDisable(true);
        stopWatch.start();

        stopButton.setDisable(false);
//        interval of every on second to set the label from the get time

    }

    @FXML
    Button stopButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tempUser =LoginController.getUser();
        todos= tempUser.getTodo();
        for (int i = 0; i < todos.length; i++) {
            todoList.add(String.valueOf(todos[i].getTitle()));
        }
        todoCombo.setItems(todoList);
        stopButton.setDisable(true);
        elapsedTime = 0;




    }


    @FXML
    public void onButtonStop(ActionEvent actionEvent) {
        // STOPSHIP: 5/11/22
        stopWatch.stop();
        startButton.setDisable(false);
        stopWatch.getHours();
        elapsedTime=stopWatch.getAllInSeconds();
        elapsedTime=elapsedTime/3600;

        stopButton.setDisable(true);
//        change the text of
    }

    @FXML
    public void Logout(ActionEvent actionEvent) throws IOException {
        LoginController.setUser(null);

//        navigate to login screen
        FXMLLoader fxmlLoader = new FXMLLoader(TaskAnalyzer.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void Save(ActionEvent actionEvent) {
        APIBridge.updateTodo(todoCombo.getValue(), elapsedTime);
    }

    public void AddNewTodo(ActionEvent actionEvent) {
        TextInputDialog tiDialog = new TextInputDialog();
        tiDialog.setTitle("Add New");
        tiDialog.setHeaderText("Todo ");
        tiDialog.setContentText("Name: ");

        Optional<String> result = tiDialog.showAndWait();
        if (result.isPresent()) {
            todoList.add(result.get());

        }
    }
}
