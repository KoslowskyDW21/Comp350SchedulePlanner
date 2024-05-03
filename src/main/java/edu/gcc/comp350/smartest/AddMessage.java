package edu.gcc.comp350.smartest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddMessage {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void LoadSchedule(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Schedule.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
