package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDataBase {
    //class is responsible for managing the SQLite database connection
    private static final String SQLiteDataBase_URL="jdbc:sqlite:articles_results.db";
    private static Connection connection;

    public SQLiteDataBase(){
        try {
            connection = DriverManager.getConnection(SQLiteDataBase_URL);
            System.out.println("Success in connecting to the org.example.SQLiteDataBase!");
        }catch(SQLException exception) {
            System.err.println("There was an error when attempting to connect to the org.example.SQLiteDataBase: "+exception.getMessage());
        }
    }
    //public method to help create a table in the connected org.example.SQLiteDataBase that is going to store the results
    public void createSQLiteTable() {
        String createSQLiteTable= "CREATE TABLE IF NOT EXISTS articles_results (\n"+
                "url TEXT PRIMARY KEY, \n"+"summary TEXT, \n "+"topic TEXT, \n"+"tone TEXT, \n"+"key_information TEXT, \n"+"source_validity TEXT, \n"+"is_useful BOOLEAN"+");";
        try {
            //createStatement method helps process the SQL queries
            connection.createStatement().execute(createSQLiteTable);
            System.out.println("SQLiteTable was generated successfully");
        }catch(SQLException exception) {
            System.err.println("There was an error when attempting to generate the table into SQLite: "+exception.getMessage());
        }
    }
    //public method that helps insert the data into the SQLiteTable
    public static void addingDataToSQLiteTable(String url, String summary, String topic, String tone, String keyInformation, String sourceValid, boolean isUseful) {
        String data="INSERT INTO articles_results (url,summary,topic,tone,key_information,source_validity, is_useful) VALUES (?,?,?,?,?,?,?)";
        try {
            var query =connection.prepareStatement(data);
            query.setString(1, url);
            query.setString(2, summary);
            query.setString(3, topic);
            query.setString(4, tone);
            query.setString(5, keyInformation);
            query.setString(6, sourceValid);
            query.setBoolean(7, isUseful);
            query.executeUpdate();
            System.out.println("Adding data was successful!");
        }catch(SQLException exception) {
            System.err.println("An error occurred while inserting the data into the SQLiteTable: "+ exception.getMessage());
        }
    }
    public static void ceaseConnections() {
        try {
            if(connection!=null) {
                connection.close();
                System.out.println("Connection to the SQLiteTable was ceased");
            }
        }catch(SQLException exception) {
            System.err.println("An error occurred when attempting to close the connection to the SQLiteTable: "+exception.getMessage());
        }
    }


}

