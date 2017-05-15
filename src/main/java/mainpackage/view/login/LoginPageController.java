package mainpackage.view.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URISyntaxException;

public class LoginPageController {
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML

    private Stage mainStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void handleOkButtonClicked(ActionEvent actionEvent) throws InterruptedException, URISyntaxException, IOException {
        AuthorizationOnVkCom authorizationOnVkCom = new AuthorizationOnVkCom();

        authorizationOnVkCom.setEmail(loginTextField.getText());
        authorizationOnVkCom.setPass(passwordTextField.getText());

        authorizationOnVkCom.go();

        if (authorizationOnVkCom.getVkFirstName() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(mainStage);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Что то пошло не так");
            alert.setContentText("Проверьте правильность ввода логина/пароля, подключение к интернету\r\nИ повторите попытку");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(mainStage);
            alert.setTitle("Hello!");
            alert.setHeaderText("Приветствие");
            alert.setContentText("Привет, " + authorizationOnVkCom.getVkFirstName() + "!");
            alert.showAndWait();
            mainStage.close();
        }
    }

    public void handleCancelButtonClicked(ActionEvent actionEvent) throws IOException, URISyntaxException, InterruptedException {
        System.exit(0);
    }

}