module com.example.doancoso1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jdk.jdi;

    opens com.example.doancoso1 to javafx.fxml;
    exports com.example.doancoso1;

    opens Module to javafx.base;
}