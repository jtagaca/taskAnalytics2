package jtagaca.taskanalytics_;

import javafx.fxml.FXML;

import java.awt.*;

public class UserController {

    @FXML
    TextArea txtAreaView;

    @FXML
    Button btnController;

    StopWatch stopWatchApp = new StopWatch();

    @FXML
    public void onButtonClicked() {
//        txtAreaView.setText(stopWatchApp.getTime());
        stopWatchApp.start();
    }




}
