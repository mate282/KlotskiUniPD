module com.example.klotski_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.klotski_project to javafx.fxml;
    exports com.example.klotski_project;
}