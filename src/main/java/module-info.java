module com.biksue.snowjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.biksue.snowjavafx to javafx.fxml;
    exports com.biksue.snowjavafx;
}