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
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class VMfxmlController implements Initializable {

    @FXML
    private JFXTextField user;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField pass;
    
    
    
    
    @FXML
    public void inscrire(ActionEvent event) throws IOException, SQLException {
       
        if (!vide()) {
            if (true) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation de l'inscription");
                alert.setContentText("Voulez vraiment confirmer l'inscription ?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {

                    try {
                        Connection cnx = VMeuble.connection;
                        //execute sql query
                        java.sql.Statement myStmt = cnx.createStatement();

                        myStmt.execute("INSERT INTO `user`(`username`, `motdepasse`, `nom`, `prenom`) VALUES  ('"
                                + user.getText()
                                + "','" + pass.getText()
                                + "','" + nom.getText()
                                + "','" + prenom.getText()                              
                                + "');");

                        
                        VMeuble.stage.close(); // Fermer la fenetre d'acceuil
                        // ouvrir la fenetre Principale
                        Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        stage.setResizable(false);
                        VMeuble.stage = stage;
                        stage.setTitle("Login");

                    } catch (SQLIntegrityConstraintViolationException e) {
                    
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Erreur");
            alert1.setHeaderText("Erreur ");
            alert1.setContentText("deja existant");
            alert1.showAndWait();
                    }

                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            } else {

            }

            clear();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        }
    }
    
     public void clear() throws IOException {
        user.clear();
        nom.clear();
        pass.clear();
        prenom.clear();

    }

    public boolean vide() {
        if (user.getText().isEmpty() || nom.getText().isEmpty() || pass.getText().isEmpty()) {
            return true;

        } else {
            return false;
        }

    }
    
    
    
    @FXML
    public void seConnecter(ActionEvent event) throws IOException {
        
        try {

            Connection cnx = VMeuble.connection;
            //execute sql query
            Statement myStmt = cnx.createStatement();
            ResultSet myRs = myStmt.executeQuery("select motdepasse from user where username='" + username.getText() + "'");

            //results set
            if (myRs.next() && password.getText().equals(myRs.getString("motdepasse"))) {
                VMeuble.stage.close(); // Fermer la fenetre d'acceuil
                // ouvrir la fenetre Principale
                Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);
                VMeuble.stage = stage;
                stage.setTitle("Login");
            } else {
                erreur();
            }
            
        } catch (SQLIntegrityConstraintViolationException e) {

        } catch (SQLException ex) {
            Logger.getLogger(VMeuble.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void erreur() {
        if (username.getText().equals("") || password.getText().equals("")) {
            username.setText("");
            password.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Test Connection");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir les champs!");
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Test Connection");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Identifiant ou mot de passe incorrect!");
            username.setText("");
            password.clear();
            alert.show();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
