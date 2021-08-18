module com.example.tcss360_triviamaze {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires java.desktop;

    opens com.example.tcss360_triviamaze to javafx.fxml;
    opens com.example.tcss360_triviamaze.programs to javafx.fxml;
    exports com.example.tcss360_triviamaze;
    exports com.example.tcss360_triviamaze.programs;

}



