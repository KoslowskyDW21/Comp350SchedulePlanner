package edu.gcc.comp350.smartest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class tempCourseSearchController {
    @FXML
    private Button button;
    @FXML
    private Button backButton;
    @FXML
    private Button advancedSearchButton;
    @FXML
    private Button searchButton;
    @FXML
    private Label backLabel;
    @FXML
    private Label label;

    @FXML
    private TextField searchBar;

    @FXML
    ListView<String> list;
    ObservableList<String> items = FXCollections.observableArrayList();


    public void initialize() {
        // TODO
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        label.setText("Hello World!");
    }

    @FXML
    protected void onBackButtonClick() {
        backLabel.setText("went back");
    }

    @FXML
    protected void onSearchButtonClick() {
        //String query = searchBar.getText();
        //list.setItems(items);
        //label.setText(query);
        //label.setText("SEARCHHHHHHHHHHHCHHCHC");
    }

    @FXML
    public void onEnter(ActionEvent ae){
        String query = searchBar.getText();
        label.setText(query);
    }

}
