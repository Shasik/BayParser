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
            alert.setTitle("������");
            alert.setHeaderText("��� �� ����� �� ���");
            alert.setContentText("��������� ������������ ����� ������/������, ����������� � ���������\r\n� ��������� �������");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(mainStage);
            alert.setTitle("Hello!");
            alert.setHeaderText("�����������");
            alert.setContentText("������, " + authorizationOnVkCom.getVkFirstName() + "!");
            alert.showAndWait();
            mainStage.close();
        }
    }

    public void handleCancelButtonClicked(ActionEvent actionEvent) throws IOException, URISyntaxException, InterruptedException {
        System.exit(0);
    }

}