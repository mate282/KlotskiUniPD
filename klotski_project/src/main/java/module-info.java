module com.klotski.klotski_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
            
                            
    opens com.klotski.klotski_project to javafx.fxml;
    exports com.klotski.klotski_project;
    opens com.klotski.view to javafx.fxml;
    opens com.klotski.controller to javafx.fxml;
}