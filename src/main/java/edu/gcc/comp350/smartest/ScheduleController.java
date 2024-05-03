package edu.gcc.comp350.smartest;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ScheduleController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Stage popupStage;



    Schedule schedule;

    public int semester;



    @FXML
    ChoiceBox<String> Semester;

    @FXML
    public void changeSemester(ActionEvent event, int currSemester){
        calendar.getChildren().clear();
        if(currSemester == 0){
            semester = 1;
            drawCalendar();
        }
        else{
            semester = 0;
            drawCalendar();
        }
    }

    @FXML
    private FlowPane calendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        semester = 0;
        Semester.setItems(FXCollections.observableArrayList(
                "FALL 2024", "SPRING 2025"));
        Semester.setValue("FALL 2024");
        drawCalendar();
    }

    @FXML
    public void GenerateSlowClick(ActionEvent event, int credits){
        Schedule s = new Schedule();
        for(Course crs : Schedule.createRecommendedScheduleSlow(Main.mainUser.getClassesLeftToTake(), credits)){
            s.addCourse(crs);
        }
        Main.mainUser.savedSchedules.set(semester, s);
        calendar.getChildren().clear();
        drawCalendar();
    }
    @FXML
    public void GenerateFastClick(ActionEvent event, int credits){
        Schedule s = new Schedule();
        for(Course crs : Schedule.createRecommendedSchedule(Main.mainUser.getClassesLeftToTake(), credits)){
            s.addCourse(crs);
        }
        System.out.println(s.getCurrentCourses().size());
        Main.mainUser.savedSchedules.set(semester, s);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    public void onRemoveClick(ActionEvent event, Course curr){
        popupStage.close();
        Main.mainUser.savedSchedules.get(semester).removeCourse(curr);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        schedule = Main.mainUser.savedSchedules.get(semester);
        String r = (String) ((ChoiceBox) Semester).getValue();
        Semester.setOnAction(event ->{
            changeSemester(event, semester);
        });
        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = 375;
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();


        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 5; j++) {
                StackPane stackPane = new StackPane();
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 5) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 11) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);


                for(int k = 0; k < schedule.getCurrentCourses().size(); k++) {
                    Course curr = schedule.getCurrentCourses().get(k);
                    if((curr.parseArr(curr.getStartTimes()))/100 - 8 == i){

                        if(dayCheck(j, curr.getDays())){
                            String start = curr.getStartTimes().substring(0, 5);
                            String end = curr.getEndTimes().substring(0, 5);
                            if(start.charAt(4) == ':'){
                                start = start.substring(0, 4);
                            }
                            if(end.charAt(4) == ':'){
                                end = end.substring(0, 4);
                            }
                            Text date = new Text(curr.getCourseCode() +"\n" + start + "-" + end);
                            date.setFont(Font.font("Arial Bold"));
                            date.setStyle("-fx-text-fill: white;");
                            rectangle.setFill(Paint.valueOf(curr.getCompletionColor()));
                            double textTranslationY = -(rectangleHeight / 2) *.05;
                            date.setTranslateY(textTranslationY);
                            stackPane.getChildren().add(date);
                            stackPane.setOnMouseClicked(event -> courseDetailsPopup(event, curr));





                            /*StackPane stickyNotesPane = new StackPane();
                            stickyNotesPane.setPrefSize(200, 80);
                            stickyNotesPane.setStyle("-fx-background-color: grey;");
                            Text description = new Text(curr.getDescription() +"\n" + "Remove this Course?" + "\n");
                            stickyNotesPane.getChildren().add(description);
                            Button btn = new Button("Remove");
                            btn.setOnAction(this::onRemoveClick
                            );
                            stickyNotesPane.getChildren().add(btn);


                            Popup popup = new Popup();
                            popup.getContent().add(stickyNotesPane);

                            stackPane.hoverProperty().addListener((obs, oldVal, newValue) -> {
                                if (newValue) {
                                    /*Bounds bnds = stackPane.localToScreen(stackPane.getLayoutBounds());
                                    double x = bnds.getMinX() - (stickyNotesPane.getWidth() / 2) + (stackPane.getWidth() / 2);
                                    double y = bnds.getMinY() - stickyNotesPane.getHeight();
                                    popup.show(stackPane, x + 30, y + 25);
                                    courseDetailsPopup(, c);
                                } else {
                                    //popup.hide();
                                }
                            });*/
                        }
                    }

                }
                calendar.getChildren().add(stackPane);
            }
        }
    }


    public boolean dayCheck(int j, String days){
        return (days.contains("M") && j == 0) || (days.contains("T") && j == 1) || (days.contains("W") && j == 2) || (days.contains("R") && j == 3) || (days.contains("F") && j == 4);
    }

    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCourseSearch(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/CourseSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void generatePopup(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/GeneratePopup.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene sc = new Scene(root);
            stage.setScene(sc);

            TextField tf = (TextField) sc.lookup("#cred");
            tf.setText("15");
            Button fast = (Button) sc.lookup("#fast");
            fast.setOnAction(e -> {
                GenerateFastClick(e, Integer.parseInt(tf.getText()));
                stage.close();
            });
            Button slow = (Button) sc.lookup("#slow");
            slow.setOnAction(e -> {
                GenerateSlowClick(e, Integer.parseInt(tf.getText()));
                stage.close();
            });
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void courseDetailsPopup(MouseEvent event, Course curr){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CourseSearchDetailsPopup.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene sc = new Scene(root);

            popupStage = stage;

            stage.setTitle(curr.getCourseCode());
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

            Button removeCourse = (Button) sc.lookup("#addCourseButton");
            removeCourse.setText("Remove Course");
            removeCourse.setMinWidth(200);
            removeCourse.setLayoutX(10);
            removeCourse.setOnAction(event1 -> onRemoveClick(event1, curr));


            cCode.setText(curr.getCourseCode());
            cName.setText(curr.getName());
            cProfessor.setText(curr.getProfessor());
            cDays.setText(curr.getDays());
            cTime.setText(curr.getStartTimes() + " -- " + curr.getEndTimes());
            cSemester.setText(curr.getSemester());
            cCredits.setText("Credits: " + curr.getNumCreditsToString());
            cNumSeats.setText("Seats: " + curr.getNumSeatsToString());
            cDescription.setText(curr.getDescription());
            cPreReqs.setText("");
            cCoReqs.setText("");

            stage.show();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}