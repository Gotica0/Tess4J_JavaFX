module iu3.tessapp3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires tess4j;


    opens iu3.tessapp3 to javafx.fxml;
    exports iu3.tessapp3;
    exports iu3.tessapp3.controllers;
    opens iu3.tessapp3.controllers to javafx.fxml;
}