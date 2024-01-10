module com.example.lab4good {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires annotations;

    opens com.example.lab4good to javafx.fxml;
    exports com.example.lab4good;
    exports com.example.lab4good.domain;
    opens com.example.lab4good.domain to javafx.fxml;
}