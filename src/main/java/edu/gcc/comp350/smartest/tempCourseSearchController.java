package edu.gcc.comp350.smartest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import javafx.util.Callback;

import java.util.List;

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
    ListView<String> listResults = new ListView<>();
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
    public void onEnter(ActionEvent ae){
        listResults.getItems().clear();
        //items.removeAll();
        // course code, name, professor, time
        String query = searchBar.getText();
        //label.setText(query);
        //list.setItems(items);
        Main.search.modifyQuery(query);
        //label.setText(Main.search.getResults().toString());
        //System.out.println(Main.search.getResults().toString());
        //System.out.println(Main.search.getResults().toString());
        List<Course> results = Main.search.getResults();
        listResults.setItems(items);
        for (Course result : results) {
            // todo: change to pretty toString
            items.add(result.getCourseCode()
                + " (" + result.getName() + ")  --  "
                + result.getProfessor() + "  --  "
                + result.getDays() + " "
                + result.getStartTimes() + " - "
                + result.getEndTimes());
        }

        listResults.setFixedCellSize(50);

        listResults.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new XCell();
            }
        });
    }

    // this code from https://stackoverflow.com/questions/15661500/javafx-listview-item-with-an-image-button
    static class XCell extends ListCell<String> {

        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        PopupWindow popupWindow = new PopupWindow() {};
        Button getDetailsButton = new Button("DETAILS");
        String lastItem;

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, getDetailsButton);
            HBox.setHgrow(pane, Priority.ALWAYS);
            getDetailsButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(lastItem + " : " + event);
                    Popup popup = new Popup();
                    //Scene sc = new Scene();

                    popup.show(popupWindow);
                }
            });
            getDetailsButton.setTranslateY(8);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                label.setText(item!=null ? item : "<null>");
                setGraphic(hbox);
            }
        }
    }




}
