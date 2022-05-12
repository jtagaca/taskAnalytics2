
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginController {
    static User user = new User();
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

    public static void setUser(Object o) {
        user = (User) o;
    }




//    a socket is basically a way to talk to one other device to another


    //    is socket shared ?
//    THis happens whenever we start the app
    @FXML
    private void initialize() {
        vboxMessage.setManaged(false);
        vboxMessage.setVisible(false);
    }



    @FXML
    private void onLoginClicked() throws IOException, JSONException {
        if (APIBridge.Login(txtUserName.getText(), txtPassword.getText())) {

        JSONArray tempUser = new JSONArray();
        tempUser= APIBridge.getUser(txtUserName.getText());
//            Build up the user using a get variable from the APIBridge
//            new object

//            I need TODO object and the userName


//            User user = new User();
//            navigate to Userview
            Todo [] todos = new Todo[tempUser.length()];
            for(int i = 0; i < tempUser.length(); i++){
                JSONObject jobj = tempUser.getJSONObject(i);
                if (jobj.get("timespent").toString().equals("null")){
                    todos[i] = new Todo(jobj.get("todo").toString(), 0, null);
                } else {
                    todos[i] = (new Todo(jobj.get("todo").toString(), Float.parseFloat(jobj.get("timespent").toString()),jobj.get("date").toString()));
                }
            }
          user= new User(txtUserName.getText(), todos);
            txtUserName.setText("");
            txtPassword.setText("");
            FXMLLoader fxmlLoader = new FXMLLoader(TaskAnalyzer.class.getResource("UserView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            Stage stage = (Stage) txtUserName.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

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

    public static User getUser() {
        return user;
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