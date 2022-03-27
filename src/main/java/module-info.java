module com.example.mymoveforms {
    requires javafx.controls;
    requires javafx.fxml;
    requires kernel;
    requires io;
    requires layout;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires activation;
    requires java.mail;


    opens com.mymovestudio.mymoveforms to javafx.fxml;
    opens com.mymovestudio.mymoveforms.controleur;
    exports com.mymovestudio.mymoveforms;
    exports com.mymovestudio.mymoveforms.controleur;
}