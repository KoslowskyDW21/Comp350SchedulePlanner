package edu.gcc.comp350.smartest;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

public class ScheduleController implements Initializable {
    ZonedDateTime dateFocus;
    ZonedDateTime today;

    Schedule schedule = Main.mainUser.savedSchedules.getFirst();

    Course c;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    public void onRemoveClick(ActionEvent event){
        schedule.removeCourse(c);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText("FALL");
        month.setText("2024");

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();


        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 5; j++) {
                StackPane stackPane = new StackPane();
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
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
                            c = curr;
                            String start = curr.getStartTimes().substring(0, 5);
                            String end = curr.getEndTimes().substring(0, 5);
                            if(start.charAt(4) == ':'){
                                start = start.substring(0, 4);
                            }
                            if(end.charAt(4) == ':'){
                                end = end.substring(0, 4);
                            }
                            Text date = new Text(curr.getCourseCode() +"\n" + start + "-" + end);
                            double textTranslationY = -(rectangleHeight / 2) * 0.25;
                            date.setTranslateY(textTranslationY);
                            stackPane.getChildren().add(date);

                            //stackPane.setOnMouseClicked(this::courseDetailsPopup);





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


}