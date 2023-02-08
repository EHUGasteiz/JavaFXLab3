module eus.ehu.eui.javafxlab3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens eus.ehu.eui.javafxlab3 to javafx.fxml;
    exports eus.ehu.eui.javafxlab3;
}