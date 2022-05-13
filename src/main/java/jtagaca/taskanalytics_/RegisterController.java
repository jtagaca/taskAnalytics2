package jtagaca.taskanalytics_;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static javax.swing.JOptionPane.showMessageDialog;

public class RegisterController {

    @FXML
    TextField registerUserName;

    @FXML
    TextField registerPassword;

    private void msgbox(String s){
        JOptionPane.showMessageDialog(null, s);
    }


    private boolean validateForm() {
        boolean nameIsValid = registerUserName.getText().matches("\\w{2,9}[a-zA-Z0-9]$");
        if (!nameIsValid) {
            msgbox("Please enter a valid username with at least a length of 2 or more characters with at least 2 numbers or letters");
            return false;
        }
        return true;
    }

    @FXML
     private void onRegisterClicked() throws IOException {

//            QUESTION, unable to make a call
            if (APIBridge.Register(registerUserName.getText(), registerPassword.getText()) == true) {
                registerUserName.setText("");
                registerPassword.setText("");
            navigateToLogin();
            };




    }
    @FXML
    private void navigateToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskAnalyzer.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = (Stage) registerUserName.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
