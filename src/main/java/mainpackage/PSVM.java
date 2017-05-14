package mainpackage;

import mainpackage.view.login.AuthorizationOnVkCom;
import mainpackage.view.login.LoginPageController;
import mainpackage.view.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PSVM extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
       launch(args);
    }

    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("BayParser");
        this.primaryStage.getIcons().add(new Image("file:resources/images/iconApp2.png"));

        initRootLoginLayout();

        initLoginPage();

        if (AuthorizationOnVkCom.getActor() != null) {
            initRootLayout();
            showMainScene();
        } else {
            System.exit(1);
        }
    }

    public void initLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PSVM.class.getResource("view/login/LoginPage.fxml"));
            AnchorPane loginPage = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("BayParser");
            loginStage.getIcons().add(new Image("file:resources/images/iconApp2.png"));
            loginStage.initModality(Modality.WINDOW_MODAL);
            loginStage.initOwner(primaryStage);



            Scene scene = new Scene(loginPage);
            loginStage.setScene(scene);

            LoginPageController controller = loader.getController();

            controller.setMainStage(loginStage);

            loginStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLoginLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            rootLayout.setPrefHeight(300);
            rootLayout.setPrefWidth(300);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PSVM.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PSVM.class.getResource("view/MainView.fxml"));
            AnchorPane personOverview = loader.load();

            rootLayout.setPrefWidth(450);
            rootLayout.setPrefHeight(400);

            rootLayout.setCenter(personOverview);
            ViewController viewController = loader.getController();
            viewController.setMainStage(primaryStage);
            viewController.setPsvm(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}