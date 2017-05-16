package mainpackage.view;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import mainpackage.PSVM;
import mainpackage.parser.groupparse.ControllerParseGroup;
//import mainpackage.parser.peopleparse.ControllerParsePeople;
import mainpackage.parser.personparse.ControllerParsePerson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
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

                controllerParseGroup.setPublicNameInParser(publicName.getText().split("/", 2)[1]);

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
//            case 3:
//                ControllerParsePeople controllerParsePeople = new ControllerParsePeople();
//
//                String type = controllerParsePeople.getTypeScreenName(publicName.getText().split("/", 2)[1]);
//
//                switch (type) {
//                    case "user":
//                        ControllerParsePerson controllerParsePersonInPeople = new ControllerParsePerson();
//
//                        controllerParsePersonInPeople.setPersonLinkInParser(publicName.getText().split("/", 2)[1]);
//
//                        controllerParsePeople.setFileNameBegin(controllerParsePersonInPeople.getFileName());
//
//                        countMembersLabel.textProperty().bind(controllerParsePersonInPeople.messageProperty());
//                        progressBarForParse.progressProperty().bind(controllerParsePersonInPeople.progressProperty());
//
//                        Thread threadPersonInPeople = new Thread(controllerParsePersonInPeople);
//
//                        threadPersonInPeople.setDaemon(true);
//                        threadPersonInPeople.start();
//                        break;
//                    case "group":
//                        ControllerParseGroup controllerParseGroupInPeople = new ControllerParseGroup();
//
//                        controllerParseGroupInPeople.setPublicNameInParser(publicName.getText().split("/", 2)[1]);
//
//                        controllerParsePeople.setFileNameBegin(publicName.getText().split("/", 2)[1] + ".txt");
//
//                        countMembersLabel.textProperty().bind(controllerParseGroupInPeople.messageProperty());
//                        progressBarForParse.progressProperty().bind(controllerParseGroupInPeople.progressProperty());
//
//                        Thread threadGroupInPeople = new Thread(controllerParseGroupInPeople);
//
//                        threadGroupInPeople.setDaemon(true);
//                        threadGroupInPeople.start();
//                        break;
//                    default:
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.initOwner(mainStage);
//                        alert.setTitle("ERROR!");
//                        alert.setHeaderText("ERROR!");
//                        alert.setContentText("�������� �����");
//                        alert.showAndWait();
//                        break;
//                }
//
//                countMembersLabel.textProperty().bind(controllerParsePeople.messageProperty());
//                progressBarForParse.progressProperty().bind(controllerParsePeople.progressProperty());
//
//                Thread threadPeople = new Thread(controllerParsePeople);
//                threadPeople.setDaemon(true);
//                threadPeople.start();
//                break;
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
        actionLabelRadioButton.setText("������� ����� ������� ��� �������� � ������� /LINK");
        fileNameLabel.setText("������ ����� ��������� � ���� � ������ \"LINK.txt\"");
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
//        alert.setHeaderText("���������� ������� �� ��������� ����������");
//        alert.setContentText("���� ����� ��������� ������� ������� �� ������ ������/������ ������������" +
//                "\r\n�� �������� ������/������ ������������ ������������� id ����������" +
//                "\r\n����� � ������� ���������, ������� ���������(���� ������ �������), ������������� ���" +
//                "\r\n���" +
//                "\r\n�������" +
//                "\r\n���\r\n" +
//                "\r\n������� ����������� �������� ��(���� �� � �������)" +
//                "\r\n��������� ������ ������������, ���� �������" +
//                "\r\n������" +
//                "\r\n�����" +
//                "\r\n���� ��������" +
//                "\r\n�������" +
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

    public void handlerUnLoginButton(ActionEvent actionEvent) {
        File file = new File("autologin");
        file.delete();
        System.exit(0);
    }
}