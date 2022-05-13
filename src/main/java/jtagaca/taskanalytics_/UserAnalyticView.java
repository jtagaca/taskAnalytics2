package jtagaca.taskanalytics_;

import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
public class UserAnalyticView implements Initializable {

    //    todo will be the series then add new will be the date
    Todo[] todos;

    @FXML
    StackedBarChart stackChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        User tempUser = LoginController.getUser();
//        make a unique set of todos
//        get all the names of todos
        ArrayList<String> MaptodoList = new ArrayList<>();
        ArrayList<String> todoList = new ArrayList<>();
        todos = tempUser.getTodos();
        for (int i = 0; i < todos.length; i++) {
            todoList.add(todos[i].getTitle());
        }
//        make a hashmap of strings
        HashMap<String, Integer> todoMap = new HashMap<>();
        for (int i = 0; i < todos.length; i++) {
            todoMap.put(todos[i].getTitle(), i);
        }

//        remove autoranging


//        for each todo in todos make a series for each todo
        for (int i = 0; i < todoMap.keySet().toArray().length; i++) {
            final XYChart.Series series = new XYChart.Series();
            series.setName(todoMap.keySet().toArray()[i].toString());
            for (int j = 0; j < todos.length; j++) {
                if (todos[j].getTitle().equals(todoMap.keySet().toArray()[i].toString())) {
                    String tempString = (String) todos[j].getDate();
                    series.getData().add(new XYChart.Data(tempString, todos[j].getTimespent()));
                }
            }

//        for each date in todos[i].getDate()

            stackChart.getData().add(series);

        }
    }
}
