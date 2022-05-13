package jtagaca.taskanalytics_;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserAnalyticView implements Initializable {

    //    todo will be the series then add new will be the date
    Todo[] todos;
    User user = new User();

    @FXML
    StackedBarChart stackChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        User tempUser = LoginController.getUser();



        JSONArray tempUser = new JSONArray();
        tempUser= APIBridge.getUser(LoginController.getUser().getUsername());
//            Build up the user using a get variable from the APIBridge
//            new object

        if (tempUser!=null) {
            Todo [] todos = new Todo[tempUser.length()];
            for(int i = 0; i < tempUser.length(); i++){
                JSONObject jobj = null;
                try {
                    jobj = tempUser.getJSONObject(i);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                try {
                    if (jobj.get("timespent").toString().equals("null")){
                        try {
                            todos[i] = new Todo(jobj.get("todo").toString(), 0, null);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        todos[i] = (new Todo(jobj.get("todo").toString(), Float.parseFloat(jobj.get("timespent").toString()),jobj.get("date").toString()));
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            user= new User(LoginController.getUser().getUsername(), todos);
        }
        else
        {
            user= new User(LoginController.getUser().getUsername(), null);
        }


        if (user.getTodos() != null) {
//        make a unique set of todos
//        get all the names of todos
            ArrayList<String> MaptodoList = new ArrayList<>();
            ArrayList<String> todoList = new ArrayList<>();
            todos = user.getTodos();
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

    public void backNavigate(ActionEvent actionEvent) throws IOException {
//        navigate to UserView
        FXMLLoader fxmlLoader = new FXMLLoader(TaskAnalyzer.class.getResource("UserView.fxml"));
//       QUESTION error here
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        UserController userController  = fxmlLoader.getController();
        Stage stage = (Stage) stackChart.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
}
