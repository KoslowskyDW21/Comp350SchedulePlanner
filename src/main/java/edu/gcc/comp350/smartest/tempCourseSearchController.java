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
    @FXML
    private Label label;

    @FXML
    private TextField searchBar;

    //@FXML
    //ListView<String> listResults = new ListView<>();
    //ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    ListView<Course> listRes = new ListView<>();
    ObservableList<Course> items2 = FXCollections.observableArrayList();


    public void initialize() {
        // TODO
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
        stage.show();
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


    // this code from https://stackoverflow.com/questions/15661500/javafx-listview-item-with-an-image-button
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
