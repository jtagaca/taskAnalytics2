package jtagaca.taskanalytics_;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;

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
            msgbox("Please enter a valid username with at least a length of 2 or more characters");
            return false;
        }
        return true;
    }
     private void onRegisterClicked() {
            if (!validateForm()) return;
            APIBridge.LoginUser(registerUserName.getText(), registerPassword.getText());


    }

}
