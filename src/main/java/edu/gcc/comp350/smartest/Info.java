package edu.gcc.comp350.smartest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Info implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Label nameLabel;
    @FXML private Label majorLabel;
    @FXML private TextField nameText;
    @FXML private TextField majorText;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Main.mainUser != null) {
            setNameAndMajor();
        }
    }

    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onEditClick() {
        String name = nameText.getText();
        String major = majorText.getText();
        nameLabel.setText("Name: " + name);
        majorLabel.setText("Major: " + major);
        if(Main.mainUser == null) {
            Main.mainUser = new User(0, name, major);
            Main.mainUser.SaveToFile();
        }
        else {
            Main.mainUser.setName(name);
            Main.mainUser.setMajor(major);
            Main.mainUser.SaveToFile();
        }
    }

    private void setNameAndMajor() {
        nameLabel.setText("Name: " + Main.mainUser.getName());
        majorLabel.setText("Major: " + Main.mainUser.getMajor());
    }
}
