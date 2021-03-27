/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmeuble;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class LoginController implements Initializable {

   public void gestionDuPersonnel(ActionEvent event) throws IOException
    {
            VMeuble.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre GestionDuRestaurant
            Parent parent = FXMLLoader.load(getClass().getResource("GestionPersonelle.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            VMeuble.stage = stage;
            stage.setTitle("Gestion du personnel");
    }
   public void gestionStocks(ActionEvent event) throws IOException
    {
            VMeuble.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre GestionDuRestaurant
            Parent parent = FXMLLoader.load(getClass().getResource("GestionStock.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            VMeuble.stage = stage;
            stage.setTitle("Gestion stocks");
    }
    public void commandes(ActionEvent event) throws IOException
    {
            VMeuble.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre GestionDuRestaurant
            Parent parent = FXMLLoader.load(getClass().getResource("Commande.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            VMeuble.stage = stage;
            stage.setTitle("Commandes");
            CommandeController.pp= parent;
    }
      public void Deconnexion(ActionEvent event) throws IOException
    {
            VMeuble.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre GestionDuRestaurant
            Parent parent = FXMLLoader.load(getClass().getResource("VMfxml.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            VMeuble.stage = stage;
            stage.setTitle("Login");
    }
     
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }




    
    
}
