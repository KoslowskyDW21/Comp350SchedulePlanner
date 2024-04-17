module Comp350SchedulePlanner.main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens edu.gcc.comp350.smartest to javafx.fxml;
    exports edu.gcc.comp350.smartest;
}