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
        actionLabelRadioButton.setText("Введите адрес паблика в формате /public_address");
        fileNameLabel.setText("Данные будут сохранены в файл с именем \"public_address.txt\"");
        setSelectRadio(1);
    }

    public void handleButtonRadioPersonParse(ActionEvent actionEvent) {
        actionLabelRadioButton.setText("Введите адрес человека в формате /person_address");
        fileNameLabel.setText("Данные будут сохранены в файл с именем \"LastName_FirstName.txt\"");
        setSelectRadio(2);
    }

    public void handleButtonRadioPeopleParse(ActionEvent actionEvent) {
        actionLabelRadioButton.setText("Введите адрес паблика в формате /public_address");
        fileNameLabel.setText("Данные будут сохранены в файл с именем \"public_address\"");
        setSelectRadio(3);
    }

    public void handleHyperLinkGroupRadio(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(mainStage);
        alert.setTitle("Information");
        alert.setHeaderText("Парсинг группы");
        alert.setContentText("Этот пункт выполняет парсинг членов группы" +
                "\r\nИз заданной группы будут выдернуты все id членов группы" +
                "\r\nИ помещены в файл");
        alert.showAndWait();
    }

    public void handleHyperLinkPersonRadio(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(mainStage);
        alert.setTitle("Information");
        alert.setHeaderText("Парсинг человека");
        alert.setContentText("Этот пункт выполняет парсинг друзей человека" +
                "\r\nУ заданного человека вытаскиваются все id друзей" +
                "\r\nИ помещаются в файл");
        alert.showAndWait();
    }

    public void handleHyperLinkPeopleRadio(ActionEvent actionEvent) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.initOwner(mainStage);
//        alert.setTitle("Information");
//        alert.setHeaderText("Парсинг людей в группе");
//        alert.setContentText("Этот пункт выполняет парсинг каждого из членов группы" +
//                "\r\nИз заданной группы вытаскиваются id участников" +
//                "\r\nЗатем у каждого участника вытаскиваются его" +
//                "\r\nИмя" +
//                "\r\nФамилия" +
//                "\r\nПол\r\n" +
//                "\r\nСледующие пункты вытягиваются, если указаны" +
//                "\r\nСтрана" +
//                "\r\nГород" +
//                "\r\nДата рождения" +
//                "\r\nВозраст" +
//                "\r\nНаличие возможности отправки ЛС(если не в друзьях)" +
//                "\r\nМобильный телефон" +
//                "\r\nДомашний телефон" +
//                "\r\nСемейное положение" +
//                "\r\nid партнёра(муж/жена, парень/девушка)" +
//                "\r\nSkype" +
//                "\r\nInstagram");
//
//        alert.showAndWait();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainStage);
        alert.setTitle("Error!");
        alert.setHeaderText("Будет реализовано в следующих версиях");
        alert.setContentText("Будет реализовано в следующих версиях");
        alert.showAndWait();
    }

    public void handleClickOnAuthor(ActionEvent actionEvent) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("http://vk.com/bayovchik"));
    }
}