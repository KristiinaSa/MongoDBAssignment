module assignment.mongodbassignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;


    opens com.example.mongodbassignment to javafx.fxml;
    exports com.example.mongodbassignment;
}