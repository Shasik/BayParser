package mainpackage.view;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import mainpackage.PSVM;
import mainpackage.groupparse.ControllerParseGroup;
import mainpackage.personparse.ControllerParsePerson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ViewController {
    @FXML private Label timeLabel;
    @FXML private Label fileNameLabel;
    @FXML private Label reminderTimeLabel;
    @FXML private Label actionLabelRadioButton;
    @FXML private TextField publicName;
    @FXML private Label countMembersLabel;
    @FXML private ProgressBar progressBarForParse;

    private int selectRadio = 1;

    public int getSelectRadio() {
        return selectRadio;
    }

    public void setSelectRadio(int selectRadio) {
        this.selectRadio = selectRadio;
    }

    private Stage mainStage;

    private PSVM psvm;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setPsvm(PSVM psvm) {
        this.psvm = psvm;
    }

    @FXML
    protected void handleButtonClickAction(ActionEvent event) throws IOException, InterruptedException, ClientException, ApiException {
        switch (getSelectRadio()) {
            case 1:
                ControllerParseGroup controllerParseGroup = new ControllerParseGroup();

                controllerParseGroup.setPublicNameInParser(publicName.getText());

                countMembersLabel.textProperty().bind(controllerParseGroup.messageProperty());
                progressBarForParse.progressProperty().bind(controllerParseGroup.progressProperty());

                Thread th = new Thread(controllerParseGroup);
                th.setDaemon(true);
                th.start();
                break;
            case 2:
                ControllerParsePerson controllerParsePerson = new ControllerParsePerson();

                controllerParsePerson.setPersonLinkInParser(publicName.getText().split("/", 2)[1]);

                countMembersLabel.textProperty().bind(controllerParsePerson.messageProperty());
                progressBarForParse.progressProperty().bind(controllerParsePerson.progressProperty());

                Thread threadPerson = new Thread(controllerParsePerson);
                threadPerson.setDaemon(true);
                threadPerson.start();
                break;
        }
    }

    public void handleButtonRadioGroupParse(ActionEvent actionEvent) {
        actionLabelRadioButton.setText("������� ����� ������� � ������� /public_address");
        fileNameLabel.setText("������ ����� ��������� � ���� � ������ \"public_address.txt\"");
        setSelectRadio(1);
    }

    public void handleButtonRadioPersonParse(ActionEvent actionEvent) {
        actionLabelRadioButton.setText("������� ����� �������� � ������� /person_address");
        fileNameLabel.setText("������ ����� ��������� � ���� � ������ \"LastName_FirstName.txt\"");
        setSelectRadio(2);
    }

    public void handleButtonRadioPeopleParse(ActionEvent actionEvent) {
        actionLabelRadioButton.setText("������� ����� ������� � ������� /public_address");
        fileNameLabel.setText("������ ����� ��������� � ���� � ������ \"public_address\"");
        setSelectRadio(3);
    }

    public void handleHyperLinkGroupRadio(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(mainStage);
        alert.setTitle("Information");
        alert.setHeaderText("������� ������");
        alert.setContentText("���� ����� ��������� ������� ������ ������" +
                "\r\n�� �������� ������ ����� ��������� ��� id ������ ������" +
                "\r\n� �������� � ����");
        alert.showAndWait();
    }

    public void handleHyperLinkPersonRadio(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(mainStage);
        alert.setTitle("Information");
        alert.setHeaderText("������� ��������");
        alert.setContentText("���� ����� ��������� ������� ������ ��������" +
                "\r\n� ��������� �������� ������������� ��� id ������" +
                "\r\n� ���������� � ����");
        alert.showAndWait();
    }

    public void handleHyperLinkPeopleRadio(ActionEvent actionEvent) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.initOwner(mainStage);
//        alert.setTitle("Information");
//        alert.setHeaderText("������� ����� � ������");
//        alert.setContentText("���� ����� ��������� ������� ������� �� ������ ������" +
//                "\r\n�� �������� ������ ������������� id ����������" +
//                "\r\n����� � ������� ��������� ������������� ���" +
//                "\r\n���" +
//                "\r\n�������" +
//                "\r\n���\r\n" +
//                "\r\n��������� ������ ������������, ���� �������" +
//                "\r\n������" +
//                "\r\n�����" +
//                "\r\n���� ��������" +
//                "\r\n�������" +
//                "\r\n������� ����������� �������� ��(���� �� � �������)" +
//                "\r\n��������� �������" +
//                "\r\n�������� �������" +
//                "\r\n�������� ���������" +
//                "\r\nid �������(���/����, ������/�������)" +
//                "\r\nSkype" +
//                "\r\nInstagram");
//
//        alert.showAndWait();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainStage);
        alert.setTitle("Error!");
        alert.setHeaderText("����� ����������� � ��������� �������");
        alert.setContentText("����� ����������� � ��������� �������");
        alert.showAndWait();
    }

    public void handleClickOnAuthor(ActionEvent actionEvent) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("http://vk.com/bayovchik"));
    }
}