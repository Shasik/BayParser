package mainpackage.view.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LoginPageController {
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML

    private Stage mainStage;
    private boolean isAuthorized = false;

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
            isAuthorized = true;
            alert.showAndWait();
            mainStage.close();
        }
    }

    public void handleCancelButtonClicked(ActionEvent actionEvent) throws IOException, URISyntaxException, InterruptedException {
        System.exit(0);
    }

    public void handleAuthThrVKCOMButtonClicked(ActionEvent actionEvent) throws IOException {
        Desktop.getDesktop().browse(URI.create("https://oauth.vk.com/authorize?client_id=6022600&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,wall,offline,groups,email&response_type=token&v=5.64"));



        mainStage.close();
    }
}