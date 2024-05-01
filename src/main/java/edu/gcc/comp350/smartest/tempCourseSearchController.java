package edu.gcc.comp350.smartest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tempCourseSearchController {
    @FXML
    public Label courseCode;
    @FXML
    public Label courseName;
    @FXML
    public Label courseProfessor;
    @FXML
    public Label courseDays;
    @FXML
    public Label courseTime;
    @FXML
    public Label courseSemester;
    @FXML
    public Label courseCredits;
    @FXML
    public Label courseNumSeats;
    @FXML
    public Label courseDescription;
    @FXML
    public Label coursePreReqs;
    @FXML
    public Label courseCoReqs;
    @FXML
    private Button addCourseButton;


    //@FXML
    //private Button button;
    @FXML
    private Button backButton;
    @FXML
    private Button advancedSearchButton;
    //@FXML
    //private Button searchButton;
    @FXML
    private Label backLabel;
    //@FXML
    //private Label label;
    @FXML
    private Label testLabel;

    @FXML
    private TextField searchBar;

    //@FXML
    //ListView<String> listResults = new ListView<>();
    //ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    ListView<Course> listRes = new ListView<>();
    ObservableList<Course> items2 = FXCollections.observableArrayList();

    @FXML
    private Label startTimeLabel;
    @FXML
    public ChoiceBox<String> startTime = new ChoiceBox<>();
    //ObservableList<String> startTimes = FXCollections.observableArrayList();
//    ObservableList<String> startTimes = FXCollections.observableArrayList("8:00", "8:30", "9:00", "9:30",
//            "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
//            "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
//            "18:00", "18:30", "19:00", "19:30", "20:00", "20:30");
    private String[] startTimesArr = {"8:00", "8:30", "9:00", "9:30",
            "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
            "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
            "18:00", "18:30", "19:00", "19:30", "20:00", "20:30"};

    @FXML
    private Label endTimeLabel;
    @FXML
    private ChoiceBox<String> endTime = new ChoiceBox<>();
//    ObservableList<String> endTimes = FXCollections.observableArrayList("9:00", "9:30", "10:00", "10:30",
//            "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
//            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",
//            "19:00", "19:30", "20:00", "20:30", "21:00", "21:30");
    private String[] endTimesArr = {"9:00", "9:30", "10:00", "10:30",
            "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",
            "19:00", "19:30", "20:00", "20:30", "21:00", "21:30"};


    public void initialize() {
        // TODO
//        startTimes = FXCollections.observableArrayList("8:00", "8:30", "9:00", "9:30",
//                "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
//                "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
//                "18:00", "18:30", "19:00", "19:30", "20:00", "20:30");
//        endTimes = FXCollections.observableArrayList("9:00", "9:30", "10:00", "10:30",
//                "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
//                "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",
//                "19:00", "19:30", "20:00", "20:30", "21:00", "21:30");

    }

    /*@FXML
    private void handleButtonAction(ActionEvent event) {
        label.setText("Hello World!");
    }*/

    @FXML
    protected void onBackButtonClick() {
        backLabel.setText("went back");
    }


    @FXML
    protected void onAdvancedSearchButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CourseSearchAdvancedSearchPopup.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.setX(832);
        stage.setY(47);
        //startTime.getItems().setAll(startTimesArr);
        //endTime.getItems().setAll(endTimesArr);
//        startTime.setItems(startTimes);
//        ArrayList<String> startTs = new ArrayList<String>(Arrays.asList("8:00", "8:30", "9:00", "9:30",
//                "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
//                "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
//                "18:00", "18:30", "19:00", "19:30", "20:00", "20:30"));
//        startTimes.addAll(startTs);

        //startTime.getItems().setAll(test);

        //startTime = new ChoiceBox<>(startTimes);
        //ChoiceBox<String> startT = (ChoiceBox<String>) sc.lookup("#startTime");
        //startTime.setValue("MEEP!");
        stage.show();
    }

    @FXML
    protected void startTimeDropDown() {
        startTime.getItems().setAll(startTimesArr);
        System.out.println("set");
        if (endTime.getValue() == null) { return; }
        System.out.println("still going");

//        String currEnd = endTime.getValue().replace(":", "");
//        System.out.println(currEnd);
//        int currEndNum = Integer.parseInt(currEnd);
//
//        for (String str : startTime.getItems()) {
//            System.out.println("yeet" + str);
//            if (currEndNum < Integer.parseInt(str.replace(":", ""))) {
//                System.out.println("yup");
//                startTime.getItems().remove(str);
//            }
//        }

//        ObservableList<String> starts = startTime.getItems();
//        for (int i = 0; i < starts.size(); i++) {
//            System.out.println(i);
//            // if end time filter earlier than this option, disable it
//            if (currEndNum < Integer.parseInt(starts.get(i))) {
//                System.out.println("yea bro");
//                startTime.getItems().remove(i);
//            }
//        }

        //startTime.show();
//        for (String str : startTime.getItems()) {
//            System.out.println(str);
//        }
//        testLabel.setText("pReSseD");
    }
    @FXML
    protected void endTimeDropDown() {
        endTime.getItems().setAll(endTimesArr);
        if (startTime.getValue() == null) { return; }

    }
    @FXML
    protected void startTimeSet() {
        //if (startTime.getValue() == null) { return; }
        String temp = startTime.getValue().replace(":", "");
        int tempNum = Integer.parseInt(temp);
        System.out.println(String.valueOf(tempNum));
        Main.search.getActiveFilters().setStartTime(tempNum);
        //testLabel.setText(String.valueOf(Main.search.getActiveFilters().getStartTime()));
        System.out.println("Hey");

        //String currEnd = endTime.getValue().replace(":", "");
        //System.out.println(currEnd);
        //int currEndNum = Integer.parseInt(currEnd);

        if (endTime.getValue() == null) { return; }

        for (String str : endTime.getItems()) {
            System.out.println("yeet" + str);
            if (tempNum > Integer.parseInt(str.replace(":", ""))) {
                System.out.println("yup");
                endTime.getItems().remove(str);
            }
        }
    }
    @FXML
    protected void endTimeSet() {
        if (endTime.getValue() == null) { return; }
        String temp = endTime.getValue().replace(":", "");
        int tempNum = Integer.parseInt(temp);
        System.out.println(String.valueOf(tempNum));
        Main.search.getActiveFilters().setEndTime(tempNum);
        testLabel.setText(String.valueOf(Main.search.getActiveFilters().getEndTime()));
    }

    @FXML
    public void onEnter(ActionEvent ae){
        //listResults.getItems().clear();
        listRes.getItems().clear();

        // course code, name, professor, time
        String query = searchBar.getText();
        Main.search.modifyQuery(query);
        List<Course> results = Main.search.getResults();

        //listResults.setItems(items);
        listRes.setItems(items2);

        /*for (Course result : results) {
            items2.add(result);
            //System.out.println(result.getCourseCode());
            // todo: change to pretty toString
            /*items.add(result.getCourseCode()
                + " (" + result.getName() + ")  --  "
                + result.getProfessor() + "  --  "
                + result.getDays() + " "
                + result.getStartTimes() + " - "
                + result.getEndTimes());
        }*/

        items2.addAll(results);

        //listResults.setFixedCellSize(50);
        listRes.setFixedCellSize(50);

        listRes.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {
//            @Override
//            public ListCell<String> call(ListView<String> param) {
//                XCell xcell = new XCell();
//
//                return xcell;
//            }
            @Override
            public ListCell<Course> call(ListView<Course> param) {
                XCell xcell = new XCell();
                //xcell.lastCourse =

                return xcell;
            }
        });
    }

    @FXML
    public void onAddCourseButtonClick() {
        XCell.onAddCourseButtonClick2();
    }


    // this code adapted from https://stackoverflow.com/questions/15661500/javafx-listview-item-with-an-image-button
    static class XCell extends ListCell<Course> {

        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        //PopupWindow popupWindow = new PopupWindow() {};
        Button getDetailsButton = new Button("DETAILS");
        //String lastItem;
        Course lastCourse;

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, getDetailsButton);
            HBox.setHgrow(pane, Priority.ALWAYS);
            //setStyle("-fx-control-inner-background: " + lastCourse.getColor() + " ;");
            //setStyle("-fx-control-inner-background: " + "blue" + " ;");
            getDetailsButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //System.out.println(lastItem + " : " + event);
                    courseDetailsPopup(event);
                    //Popup popup = new Popup();
                    //Scene sc = new Scene();

                    //popup.show(popupWindow);
                    //popup.show(getScene().getWindow());

                    //Stage popupStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    //FXMLLoader loader = new FXMLLoader(getClass().getResource(""));

                }
            });
            getDetailsButton.setTranslateY(8);
        }

        @FXML
        private void courseDetailsPopup(ActionEvent event) {
            Stage popupStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CourseSearchDetailsPopup.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene sc = new Scene(root);

                stage.setTitle(lastCourse.getCourseCode());
                stage.setScene(sc);


                Label cCode = (Label) sc.lookup("#courseCode");
                Label cName = (Label) sc.lookup("#courseName");
                Label cProfessor = (Label) sc.lookup("#courseProfessor");
                Label cDays = (Label) sc.lookup("#courseDays");
                Label cTime = (Label) sc.lookup("#courseTime");
                Label cSemester = (Label) sc.lookup("#courseSemester");
                Label cCredits = (Label) sc.lookup("#courseCredits");
                Label cNumSeats = (Label) sc.lookup("#courseNumSeats");
                Label cDescription = (Label) sc.lookup("#courseDescription");
                Label cPreReqs = (Label) sc.lookup("#coursePreReqs");
                Label cCoReqs = (Label) sc.lookup("#courseCoReqs");

                cCode.setText(lastCourse.getCourseCode());
                cName.setText(lastCourse.getName());
                cProfessor.setText(lastCourse.getProfessor());
                cDays.setText(lastCourse.getDays());
                cTime.setText(lastCourse.getStartTimes() + " -- " + lastCourse.getEndTimes());
                cSemester.setText(lastCourse.getSemester());
                cCredits.setText("Credits: " + lastCourse.getNumCreditsToString());
                cNumSeats.setText("Seats: " + lastCourse.getNumSeatsToString());
                cDescription.setText(lastCourse.getDescription());
                cPreReqs.setText("");
                cCoReqs.setText("");
                //cPreReqs.setText("Prerequisites: " + lastCourse.getPreReqsToString());
                //cCoReqs.setText("Corequisites: " + lastCourse.getCoReqsToString());


//                courseCode.setText(lastCourse.getCourseCode());
//                courseName.setText(lastCourse.getName());
//                courseProfessor.setText(lastCourse.getProfessor());
//                courseCredits.setText(String.valueOf(lastCourse.getNumCredits()));
//                courseDays.setText(lastCourse.getDays());
//                courseTime.setText(lastCourse.getStartTimes() + lastCourse.getEndTimes());
//                courseDescription.setText(lastCourse.getDescription());


                stage.show();

            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        @FXML
        public static void onAddCourseButtonClick2() {
            // todo: add course to schedule
        }


        /*@FXML
        private void courseDetailsPopup(ActionEvent event) {
            ButtonType addCourseButtonType = new ButtonType("Add Course");

            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    lastCourse.getDescription(), ButtonType.CLOSE);
            alert.getDialogPane().getButtonTypes().add(addCourseButtonType);

            Node close = alert.getDialogPane().lookupButton(ButtonType.CLOSE);
            Node addCourse = alert.getDialogPane().lookupButton(addCourseButtonType);

            alert.setHeight(250);
            alert.setWidth(400);
            alert.setTitle("Course Details");
            alert.setHeaderText(lastCourse.getCourseCode());
            alert.setContentText(lastCourse.getDescription());
            //System.out.println(lastCourse.getDescription());

            //close.setLayoutX(AnchorPane.getBottomAnchor(close));
            addCourse.setTranslateX(-(close.getTranslateX()));
            addCourse.setTranslateY(close.getTranslateY());



            alert.show();
        }*/

        /*@Override
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
        }*/


        //@Override
        protected void updateItem(Course course, boolean empty) {
            super.updateItem(course, empty);
            setText(null);  // No text in label of super class
            if (empty) {
                lastCourse = null;
                setGraphic(null);
            } else {
                lastCourse = course;
                label.setText(course!=null ? String.valueOf(course) : "<null>");
                setGraphic(hbox);
            }
        }

    }




}
