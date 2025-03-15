module duke {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.fxml;

    exports lebum.main;
    exports lebum.command;
    exports lebum.exception;
    exports lebum.task;

    opens lebum.main to javafx.graphics;
}