/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmeuble;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class VMeuble extends Application {

    static Stage stage;
    static Connection connection;

    @Override
    public void start(Stage primaryStage) throws SQLException, IOException {

        // ouvrir la fenetre d'acceuil
        Parent parent = FXMLLoader.load(getClass().getResource("VMfxml.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        // Sauvegarder la fenetre d'acceuil
        stage = primaryStage;
        stage.setTitle("Accueil");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        //connection to database
        java.sql.Connection myConn = DriverManager.getConnection("jdbc:mysql://192.168.43.50:3306/test", "mahmoud", "123");
        connection = (Connection) myConn;
        try {
             
            Connection cnx = connection;
            
            //execute sql query
            Statement myStmt = cnx.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT `username` FROM `user`;");
            if(myRs.next())
            {
                String mah = myRs.getString("username");
                System.out.println(mah);
            }
            
           

      
    }catch (SQLException ex) {
        System.out.println("ereuuur");
        }
        
  launch(args);
}}
