
package jtagaca.taskanalytics_;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

public class LoginController {
    @FXML
    TextField txtUserName;

    @FXML
    VBox vboxMessage;
//    if I click register then I should go to a different scene
    @FXML
    PasswordField txtPassword;


    @FXML
    Button btnLogin;

    @FXML
    Button btnRegister;

//    a socket is basically a way to talk to one other device to another


    //    is socket shared ?
//    THis happens whenever we start the app
    @FXML
    private void initialize() {
        vboxMessage.setManaged(false);
        vboxMessage.setVisible(false);
    }



    @FXML
    private void onLoginClicked() throws IOException {

        if (APIBridge.Login(txtUserName.getText(), txtPassword.getText())) {

//            Build up the user using a get variable from the APIBridge
//            new object

//            I need TODO object and the userName
            JsonObject tempUser = new JsonObject();
            tempUser = APIBridge.getUser(txtUserName.getText(), txtPassword.getText());
//            User user = new User();
            txtUserName.setText("");
            txtPassword.setText("");

        }

//    private void openChatView(Client client) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(FXChatApplication.class.getResource("chat-view.fxml"));
//
//
//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//
//        ChatViewController chatViewController = fxmlLoader.getController();
//        chatViewController.setClient (client);
////        what is this doing ?
//
////        stage is the entire window
////        grabbing the textview object, any of the nodes on the scene can be used
//        Stage stage = (Stage) txtUserName.getScene().getWindow();
//        stage.setOnHidden(event -> {
//            client.closeEverything();
//        });
//        stage.setScene(scene);
//    }

    }
    @FXML
    private void onRegisterClicked() throws IOException {
//        send to RegisterView
        FXMLLoader fxmlLoader = new FXMLLoader(TaskAnalyzer.class.getResource("RegisterView.fxml"));
//       QUESTION error here
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        RegisterController reigisterController = fxmlLoader.getController();
                Stage stage = (Stage) txtUserName.getScene().getWindow();

                stage.setScene(scene);
    }
    private boolean validateForm() {
        boolean nameIsValid = txtUserName.getText().matches("\\w{2,9}[a-zA-Z0-9]$");
        if (nameIsValid) {
            return true;
        } else {
            vboxMessage.setManaged(true);
            vboxMessage.setVisible(true);
            txtUserName.setStyle("-fx-border-color:#FF0000");
            return false;
        }
    }

}