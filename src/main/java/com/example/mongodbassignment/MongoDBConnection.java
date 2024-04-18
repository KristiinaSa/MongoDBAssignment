package com.example.mongodbassignment;

import java.util.Properties;
import java.io.InputStream;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;
import org.bson.Document;

public class MongoDBConnection{

    public MongoCollection connectToDatabase(){
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "config.properties";
            input = MongoDBConnection.class.getClassLoader().getResourceAsStream(filename);
            if(input==null){
                System.out.println("Sorry, unable to find " + filename);
                return null;
            }

            //load a properties file from class path
            prop.load(input);

            // get the property value
            String mongoUri = prop.getProperty("mongo.uri");
            ConnectionString connection = new ConnectionString(mongoUri);
            MongoClient mongoClient = MongoClients.create(connection);
            MongoDatabase database = mongoClient.getDatabase("mydb");
            System.out.println("Connected to the database");
            MongoCollection<Document> collection = database.getCollection("people");
            return collection;
        } catch (Exception e) {
            System.out.println("Error connecting to MongoDB");
            e.printStackTrace();
            return null;
        }
    }

    public String addPerson(int id, String name, int age, String city){
        try {

            MongoCollection<Document> collection = connectToDatabase();

            Document document = new Document("name", "MongoDB")
                    .append("id", id)
                    .append("name", name)
                    .append("age", age)
                    .append("city", city);

            collection.insertOne(document);
            System.out.println("Document added successfully");
            return "Document added successfully";
        } catch (Exception e) {
            System.out.println("Error connecting to MongoDB");
            e.printStackTrace();
            return "Error connecting to MongoDB";
        }
    }

    public String deletePerson(int id){
        try {

            MongoCollection<Document> collection = connectToDatabase();

            Document document = new Document("id", id);

            collection.deleteOne(document);
            System.out.println("Document deleted successfully");
            return "Document deleted successfully";
        } catch (Exception e) {
            System.out.println("Error connecting to MongoDB");
            e.printStackTrace();
            return "Error connecting to MongoDB";
        }
    }

    // Add the updatePerson method here
    public String updatePerson(int id, String name, int age, String city){
        try {
            MongoCollection<Document> collection = connectToDatabase();

            Document document = new Document("id", id)
                    .append("name", name)
                    .append("age", age)
                    .append("city", city);

            collection.updateOne(new Document("id", id), new Document("$set", document));
            System.out.println("Document updated successfully");
            return "Document updated successfully";
        } catch (Exception e) {
            System.out.println("Error connecting to MongoDB");
            e.printStackTrace();
            return "Error connecting to MongoDB";
        }
    }

public String readPerson(int id){
        try {
            MongoCollection<Document> collection = connectToDatabase();

            Document document = new Document("id", id);

            document = collection.find(document).first();
            System.out.println("Document read successfully");
            return "Found document: " + document.toJson();
        } catch (Exception e) {
            System.out.println("Error connecting to MongoDB");
            e.printStackTrace();
            return "Error connecting to MongoDB";
        }
    }

}
