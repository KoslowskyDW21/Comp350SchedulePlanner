package edu.gcc.comp350.smartest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Info implements Initializable {
    private Stage stage;
    private Stage popupStage;
    private Scene scene;
    private Parent root;

    @FXML private Label nameLabel;
    @FXML private Label majorLabel;
    @FXML private TextField nameText;
    @FXML private TextField majorText;
    @FXML private ChoiceBox<Course> remove = new ChoiceBox<>();
    @FXML ListView<Course> prevCourses = new ListView<>();

    ObservableList<Course> prevCourse2 = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (Main.mainUser.getUserID() != -1) {
                setNameAndMajor();
            }
            loadCourses();
            prevCourses.setStyle("-fx-control-inner-background: #303030;");
        }
        catch (Exception ignore) {

        }
    }

    public void loadCourses() {
        List<Course> prev = Main.mainUser.getCompletedCourses();
        prevCourses.setItems(prevCourse2);
        prevCourse2.addAll(prev);
    }

    public void addCompletedCourses(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/CourseSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void removeCoursePopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/prevCoursePopup.fxml"));
        Parent popupRoot = loader.load();
        popupStage= new Stage();
        Scene popupScene = new Scene(popupRoot);
        popupStage.setScene(popupScene);

        popupStage.show();
    }

    @FXML
    protected void removeDropDown() {
        if (remove.getItems().isEmpty()) {
            Course[] courses = new Course[Main.mainUser.getCompletedCourses().size()];
            for(int i = 0; i < courses.length; i++) {
                courses[i] = Main.mainUser.getCompletedCourses().get(i);
            }
            remove.getItems().addAll(courses);
        }
    }

    public void remove() {
        Course temp = remove.getValue();
        Main.mainUser.removeTakenCourse(temp);
        Main.mainUser.SaveToFile();
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
        String name = Main.mainUser.getName();
        if(name.isEmpty() || (!name.equals(nameText.getText()) && !nameText.getText().isEmpty())) {
            name = nameText.getText();
        }
        String major = Main.mainUser.getMajor();
        if(major.isEmpty() || (!major.equals(majorText.getText()) && !majorText.getText().isEmpty())) {
            major = majorText.getText();
        }
        nameLabel.setText("Name: " + name);
        majorLabel.setText("Major: " + major);
        if(Main.mainUser.getUserID() == -1) {
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
