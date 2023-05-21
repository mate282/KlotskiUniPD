module com.unipd.klotski {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                        
    opens com.unipd.klotski to javafx.fxml;
    exports com.unipd.klotski;
}