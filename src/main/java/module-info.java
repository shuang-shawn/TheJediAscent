module ca.bcit.comp2522.termproject._2522202210termprojectstarwars {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens ca.bcit.comp2522.termproject._2522202210termprojectstarwars to javafx.fxml;
    exports ca.bcit.comp2522.termproject._2522202210termprojectstarwars;
}