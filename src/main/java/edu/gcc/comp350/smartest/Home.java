package edu.gcc.comp350.smartest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Button schedule;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Main.mainUser == null) {
            schedule.setStyle("-fx-background-color: #626262;");
            schedule.setDisable(true);
        }
        else {
            schedule.setDisable(false);
        }
    }
    public void switchToInfo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/info.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void test() {
        System.out.println("It works.");
    }
}
