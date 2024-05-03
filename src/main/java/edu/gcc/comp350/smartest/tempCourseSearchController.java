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
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tempCourseSearchController {
    private Stage stage;
    private Scene scene;
    private Parent root;

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


    @FXML
    private Button backButton;
    @FXML
    private Button advancedSearchButton;
    @FXML
    private TextField searchBar;
    @FXML
    ListView<Course> listRes = new ListView<>();
    ObservableList<Course> items2 = FXCollections.observableArrayList();


    @FXML
    private Label startTimeLabel;
    @FXML
    public ChoiceBox<String> startTime = new ChoiceBox<>();
    private String[] startTimesArr = {"8:00", "8:30", "9:00", "9:30",
            "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
            "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
            "18:00", "18:30", "19:00", "19:30", "20:00", "20:30"};

    @FXML
    private Label endTimeLabel;
    @FXML
    private ChoiceBox<String> endTime = new ChoiceBox<>();
    private String[] endTimesArr = {"9:00", "9:30", "10:00", "10:30",
            "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",
            "19:00", "19:30", "20:00", "20:30", "21:00", "21:30"};

    @FXML
    private Label levelMinLabel;
    @FXML
    public ChoiceBox<String> levelMin = new ChoiceBox<>();
    private String[] levelMinArr = {"100", "200", "300", "400"};

    @FXML
    private Label levelMaxLabel;
    @FXML
    private ChoiceBox<String> levelMax = new ChoiceBox<>();
    private String[] levelMaxArr = {"200", "300", "400", "500"};

    @FXML
    private TextField professorTextField = new TextField();

    @FXML
    private ChoiceBox<String> department = new ChoiceBox<>();
    private String[] departmentArr = {"ACCT", "ART", "ASTR", "BIOL", "CHEM",
        "CMIN", "COMM", "COMP", "DESI", "ECON", "EDUC", "ELEE", "ENGL", "ENGR",
        "ENTR", "EXER", "FNCE", "FREN", "GEOL", "GREK", "HEBR", "HIST", "HUMA",
        "INBS", "LATN", "MARK", "MATH", "MECE", "MNGT", "MUSI", "NURS", "PHIL",
        "PHYE", "PHYS", "POLS", "PSYC", "RELI", "ROBO", "SCIC", "SEDU", "SOCI",
        "SOCW", "SPAN", "SSFT", "THEA", "WRIT"};

    @FXML
    private CheckBox daysCheckM;
    @FXML
    private CheckBox daysCheckT;
    @FXML
    private CheckBox daysCheckW;
    @FXML
    private CheckBox daysCheckR;
    @FXML
    private CheckBox daysCheckF;
    private boolean[] daysBools = {true, true, true, true, true};


    public void initialize() {

    }


    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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


    @FXML // onMouseClicked
    protected void startTimeDropDown() {
        if (endTime.getValue() == null) {
            restrictStartTimes(2030);
        }
        else {
            int time = Integer.parseInt(endTime.getValue().replace(":", ""));
            restrictStartTimes(time);
        }
    }
    @FXML // onAction
    protected void startTimeSet() {
        if (startTime.getValue() == null) { return; }

        String temp = startTime.getValue().replace(":", "");
        int tempNum = Integer.parseInt(temp);
        Main.search.getActiveFilters().setStartTime(tempNum);

        //System.out.println(Main.search.getActiveFilters().getStartTime());
    }
    protected void restrictStartTimes(int time) {
        startTime.getItems().clear();

        for (String str : startTimesArr) {
            if (time > Integer.parseInt(str.replace(":", ""))) {
                startTime.getItems().add(str);
            }
        }
    }

    @FXML // onMouseClicked
    protected void endTimeDropDown() {
        if (startTime.getValue() == null) {
            restrictEndTimes(900);
        }
        else {
            int time = Integer.parseInt(startTime.getValue().replace(":", ""));
            restrictEndTimes(time);
        }
    }
    @FXML // onAction
    protected void endTimeSet() {
        if (endTime.getValue() == null) { return; }

        String temp = endTime.getValue().replace(":", "");
        int tempNum = Integer.parseInt(temp);
        Main.search.getActiveFilters().setEndTime(tempNum);

        //System.out.println(Main.search.getActiveFilters().getEndTime());
    }
    protected void restrictEndTimes(int time) {
        endTime.getItems().clear();

        for (String str : endTimesArr) {
            if (time < Integer.parseInt(str.replace(":", ""))) {
                endTime.getItems().add(str);
            }
        }
    }


    @FXML // onMouseClicked
    protected void levelMinDropDown() {
        if (levelMax.getValue() == null) {
            restrictLevelMin(500);
        }
        else {
            int level = Integer.parseInt(levelMax.getValue());
            restrictLevelMin(level);
        }
    }
    @FXML // onAction
    protected void levelMinSet() {
        if (levelMin.getValue() == null) { return; }

        String temp = levelMin.getValue();
        int tempNum = Integer.parseInt(temp);
        Main.search.getActiveFilters().setLevelMin(tempNum);

        //System.out.println(Main.search.getActiveFilters().getLevelMin());
    }
    protected void restrictLevelMin(int level) {
        levelMin.getItems().clear();

        for (String str : levelMinArr) {
            if (level > Integer.parseInt(str)) {
                levelMin.getItems().add(str);
            }
        }
    }

    @FXML // onMouseClicked
    protected void levelMaxDropDown() {
        if (levelMin.getValue() == null) {
            restrictLevelMax(100);
        }
        else {
            int level = Integer.parseInt(levelMin.getValue());
            restrictLevelMax(level);
        }
    }
    @FXML // onAction
    protected void levelMaxSet() {
        if (levelMax.getValue() == null) { return; }

        String temp = levelMax.getValue();
        int tempNum = Integer.parseInt(temp);
        Main.search.getActiveFilters().setLevelMax(tempNum);

        //System.out.println(Main.search.getActiveFilters().getLevelMax());
    }
    protected void restrictLevelMax(int level) {
        levelMax.getItems().clear();

        for (String str : levelMaxArr) {
            if (level < Integer.parseInt(str)) {
                levelMax.getItems().add(str);
            }
        }
    }

    @FXML // onAction
    protected void professorSet() {
        if (professorTextField.getText() == null) { return; }

        String tempProfName = professorTextField.getText();
        Main.search.getActiveFilters().setProfName(tempProfName);

        //System.out.println(Main.search.getActiveFilters().getProfName());
    }

    @FXML // onMouseClicked
    protected void departmentDropDown() {
        if (department.getItems().isEmpty()) {
            department.getItems().addAll(departmentArr);
        }
    }
    @FXML // onAction
    protected void departmentSet() {
        String tempDepartment = department.getValue();
        Main.search.getActiveFilters().setDepartment(tempDepartment);

        //System.out.println(Main.search.getActiveFilters().getDepartment());
    }

    @FXML // onAction
    protected void daysSet() {
        daysBools[0] = daysCheckM.isSelected();
        daysBools[1] = daysCheckT.isSelected();
        daysBools[2] = daysCheckW.isSelected();
        daysBools[3] = daysCheckR.isSelected();
        daysBools[4] = daysCheckF.isSelected();

        Main.search.getActiveFilters().setDays(daysBools);
        //System.out.println(Arrays.toString(Main.search.getActiveFilters().getDays()));
    }

    @FXML
    public void onEnter(ActionEvent ae){
        listRes.getItems().clear();

        // course code, name, professor, time
        String query = searchBar.getText();
        Main.search.modifyQuery(query);
        List<Course> results = Main.search.getResults();

        listRes.setItems(items2);
        items2.addAll(results);
        listRes.setFixedCellSize(50);

        listRes.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {
            @Override
            public ListCell<Course> call(ListView<Course> param) {
                XCell xcell = new XCell();
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
        Button getDetailsButton = new Button("DETAILS");
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
                    courseDetailsPopup(event);
                }
            });
            getDetailsButton.setTranslateY(8);
        }

        @FXML
        private void courseDetailsPopup(ActionEvent event) {
            //Stage popupStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

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
