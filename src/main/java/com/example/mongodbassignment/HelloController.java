package com.example.mongodbassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ageLabel;

     @FXML
    private Label cityLabel;

     @FXML
     private TextField idInput;

     @FXML
     private TextField nameInput;

     @FXML
     private TextField ageInput;

     @FXML
     private TextField cityInput;

     @FXML
     private Button addButton;

     @FXML
     private Button deleteButton;

     @FXML
     private Button updateButton;

     @FXML
        private Button readButton;

     private MongoDBConnection mongoDBAddition = new MongoDBConnection();

     public void addPerson(){
         try{
             String id = idInput.getText();
             String name = nameInput.getText();
             String age = ageInput.getText();
             String city = cityInput.getText();
             System.out.println("ID: " + id + " Name: " + name + " Age: " + age + " City: " + city);;
             createPopUp(mongoDBAddition.addPerson(Integer.parseInt(id), name, Integer.parseInt(age), city));
             clearFields();
         }catch(Exception e){
             createPopUp("Please enter all the fields");
         }
     }

     public void deletePerson(){
         try{
             String id = idInput.getText();
             System.out.println("ID: " + id + " deleted");
             createPopUp(mongoDBAddition.deletePerson(Integer.parseInt(id)));
             clearFields();
         }catch(Exception e){
             createPopUp("Please enter the ID");
         }
     }

     public void updatePerson(){
         try{
            String id = idInput.getText();
             String name = nameInput.getText();
             String age = ageInput.getText();
             String city = cityInput.getText();
             System.out.println("ID: " + id + " Name: " + name + " Age: " + age + " City: " + city);
             createPopUp(mongoDBAddition.updatePerson(Integer.parseInt(id), name, Integer.parseInt(age), city));
             clearFields();
         }catch(Exception e){
             createPopUp("Please enter all the fields");
         }
     }

     public void readPerson(){
         try{
             String id = idInput.getText();
             System.out.println("ID: " + id);
             createPopUp(mongoDBAddition.readPerson(Integer.parseInt(id)));
             clearFields();
         }catch(Exception e){
             createPopUp("Please enter the ID");
         }
     }

     //method to clean the text fields
     public void clearFields(){
         idInput.clear();
         nameInput.clear();
         ageInput.clear();
         cityInput.clear();
     }

     //method to create a new window
        public void createPopUp(String message){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText(message);

            // Set preferred size for the dialog pane
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setPrefSize(480, 120); // Set your preferred size here

            alert.showAndWait();

        }
}