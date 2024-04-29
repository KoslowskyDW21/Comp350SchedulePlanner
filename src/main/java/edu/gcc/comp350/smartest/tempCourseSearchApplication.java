package edu.gcc.comp350.smartest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class tempCourseSearchApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CourseSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Hiiii!");
        stage.setScene(scene);
        stage.show();
        //PopupWindow popupWindow = new PopupWindow() {};
        //Window window = new Window(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
