/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmeuble;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GestionPersonelleController implements Initializable {

    @FXML
    AnchorPane ap_afficher;
    @FXML
    AnchorPane ap_modifier;
    @FXML
    AnchorPane ap_confirme_modifay;
    @FXML
    AnchorPane ap_ajouter;
    @FXML
    AnchorPane ap_home;
    @FXML
    AnchorPane ap_supprimer;
    @FXML
    TableView<Personnel> table, table1, table2;
    @FXML
    TableColumn id, id1, id2;
    @FXML
    TableColumn nom, nom1, nom2;
    @FXML
    TableColumn prenom, prenom1, prenom2;
    @FXML
    TableColumn salaire, salaire1, salaire2;
    @FXML
    TableColumn sexe;
    @FXML
    TableColumn sexe1;
    @FXML
    TableColumn sexe2;
    @FXML
    TextField add_nom, add_nom1;
    @FXML
    TextField add_prenom, add_prenom1;
    @FXML
    TextField add_salaire, add_salaire1;

    @FXML
    ComboBox<String> genre, genre1;

    int id_perso = 0;
    boolean a;

    public void AfficherPersonnel(ActionEvent event) throws IOException, SQLException {
        ap_afficher.toFront();
        Connection cnx = VMeuble.connection;
        ArrayList<Personnel> personnel = new ArrayList<>();
        java.sql.Statement myStmt = cnx.createStatement();

        try {

            ResultSet myRs = myStmt.executeQuery("SELECT `idemploye`, `nom`, `prenom`, `salaire`,`genre` FROM `employe`;");

            while (myRs.next()) {
                Personnel p = new Personnel(myRs.getInt("idemploye"), myRs.getString("nom"), myRs.getString("prenom"), myRs.getFloat("salaire"), myRs.getString("genre"));

                personnel.add(p);

            }

            // Caster la list a une list Observable
            ObservableList<Personnel> listObservable = FXCollections.observableArrayList(personnel);
            table.setItems(listObservable);

        } catch (SQLException ex) {
            Logger.getLogger(VMeuble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PaneAjouter(ActionEvent event) throws IOException, SQLException {
        ap_ajouter.toFront();

    }

    public void ajouterPersonnel(ActionEvent event) throws IOException, SQLException {

        if (true) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de l'ajout");
            alert.setContentText("Voulez vraiment confirmer l'ajout ?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                try {
                    Connection cnx = VMeuble.connection;
                    //execute sql query
                    java.sql.Statement myStmt = cnx.createStatement();
                        try{
                            Float.parseFloat(add_salaire.getText());
                            if(Float.parseFloat(add_salaire.getText())<0)
                          {
                          alert.setTitle("Erreur");
            // Header Text: null
            
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un chiffe superieur a zero dans le salaire");
            alert.show(); 
                          a=false;}
                            else a=true;
                        }catch(NumberFormatException e) {
                          
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un chiffe dans le salaire ");
            alert.show(); a=false;}

                        if(a){
                    myStmt.execute("INSERT INTO `employe`(`nom`, `prenom`, `salaire`,`genre`) VALUES('"
                            + add_nom.getText()
                            + "','" + add_prenom.getText()
                            + "','" + add_salaire.getText()
                            + "','" + genre.getValue()
                            + "');");

                    AfficherPersonnel(event);}
                   // genre.setValue("");

                } catch (SQLException e) {
                }

            } else {
                // ... user chose CANCEL or closed the dialog
            }

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.show();

        }
         add_nom.clear();
            add_prenom.clear();
            add_salaire.clear();
        
    }

    public void PaneSupprimer(ActionEvent event) throws IOException, SQLException {
        ap_supprimer.toFront();
        Connection cnx = VMeuble.connection;
        ArrayList<Personnel> personnel = new ArrayList<>();
        java.sql.Statement myStmt3 = cnx.createStatement();

        try {

            ResultSet myRs = myStmt3.executeQuery("SELECT `idemploye`, `nom`, `prenom`, `salaire`,`genre` FROM `employe`;");

            while (myRs.next()) {
                Personnel p = new Personnel(myRs.getInt("idemploye"), myRs.getString("nom"), myRs.getString("prenom"), myRs.getFloat("salaire"), myRs.getString("genre"));

                personnel.add(p);

            }

            // Caster la list a une list Observable
            ObservableList<Personnel> listObservable = FXCollections.observableArrayList(personnel);
            table1.setItems(listObservable);

        } catch (SQLException ex) {
            Logger.getLogger(VMeuble.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete() throws SQLException {
        if (table1.getSelectionModel().isSelected(table1.getSelectionModel().getSelectedIndex())) {
            Personnel personnel;
            personnel = table1.getSelectionModel().getSelectedItem();
            String nom = personnel.getNom();
            int Id = personnel.getId();
            Connection cnx = VMeuble.connection;
            //execute sql query
            java.sql.Statement myStmt = cnx.createStatement();
            java.sql.Statement myStmt2 = cnx.createStatement();

            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de suprression");
            alert.setContentText("Voulez vraiment confirmer la modification ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // myStmt2.execute("UPDATE `tableconnectee` SET `id_serveur`=NULL WHERE `id_serveur`="+Id+";");
                myStmt.execute("DELETE FROM `employe` WHERE idemploye=" + Id + ";");
                table1.getItems().remove(personnel);
            }else {
            }  // ... user chose CANCEL or closed the dialog
            } catch (SQLException e) {

            }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.show();
        }
    }

    public void retour(ActionEvent event) {
        ap_home.toFront();
    }

    public void ModifierPersonnel(ActionEvent event) throws IOException, SQLException {
        ap_modifier.toFront();
        Connection cnx = VMeuble.connection;
        ArrayList<Personnel> personnel = new ArrayList<>();
        java.sql.Statement myStmt = cnx.createStatement();

        try {

            ResultSet myRs = myStmt.executeQuery("SELECT `idemploye`, `nom`, `prenom`, `salaire`,`genre` FROM `employe`;");

            while (myRs.next()) {
                Personnel p = new Personnel(myRs.getInt("idemploye"), myRs.getString("nom"), myRs.getString("prenom"), myRs.getFloat("salaire"), myRs.getString("genre"));

                personnel.add(p);

            }

            // Caster la list a une list Observable
            ObservableList<Personnel> listObservable = FXCollections.observableArrayList(personnel);
            table2.setItems(listObservable);

        } catch (SQLException ex) {
            Logger.getLogger(VMeuble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PaneModifier(ActionEvent event) throws IOException, SQLException {
        if (table2.getSelectionModel().isSelected(table2.getSelectionModel().getSelectedIndex())) {
            Personnel personnel;
            personnel = table2.getSelectionModel().getSelectedItem();
            id_perso = personnel.getId();
            ap_confirme_modifay.toFront();

            try {
                
                Connection cnx = VMeuble.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();
                ResultSet myRs = myStmt.executeQuery("SELECT `nom`, `prenom`, `salaire`,`genre` FROM `employe` WHERE  `idemploye`= " + id_perso + ";");
                if (myRs.next()) {
                    add_nom1.setText(myRs.getString("nom"));
                    add_prenom1.setText(myRs.getString("prenom"));
                    add_salaire1.setText(Float.toString(myRs.getInt("salaire")));
                    genre1.setValue(myRs.getString("genre"));

                }

            } catch (SQLException ex) {
                Logger.getLogger(VMeuble.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.show();
        }

    }

    public void ConfirmerModification(ActionEvent event) throws IOException, SQLException {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de modification");
            alert.setContentText("Voulez vraiment confirmer la modification ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if(!(add_nom1.getText().isEmpty()) && !(add_prenom1.getText().isEmpty()) && !(salaire.getText().isEmpty()) )
                { 
                Connection cnx = VMeuble.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();
                try{
                            Float.parseFloat(add_salaire.getText());
                            if(Float.parseFloat(add_salaire.getText())<0)
                          {
                          alert.setTitle("Erreur");
            // Header Text: null
            
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un chiffe superieur a zero dans le salaire");
            alert.show(); 
                         a=false; }
                            else a=true;
                        }catch(NumberFormatException e) {
                          
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un chiffe dans le salaire ");
            alert.show(); 
                        a=false;}
                
                if(a){
                myStmt.execute("UPDATE employe SET nom='" + add_nom1.getText() + "', prenom='" + add_prenom1.getText() + "',genre='" + genre1.getValue() + "' ,salaire='" + add_salaire1.getText()
                        + "' where `idemploye`= " + id_perso + ";");

                ModifierPersonnel(event);}

            } else{
                   Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.show(); 
            }} else {
                
            }
            add_nom1.clear();
            add_prenom1.clear();
            add_salaire1.clear();
        } catch (IOException | SQLException e) {

        }
    }

    public void Deconnexion(ActionEvent event) throws IOException {
        VMeuble.stage.close(); // Fermer la fenetre Principale
        // ouvrir la fenetre GestionDuRestaurant
        Parent parent = FXMLLoader.load(getClass().getResource("VMfxml.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        VMeuble.stage = stage;
        stage.setTitle("Accueil");
    }
   public void Retour(ActionEvent event) throws IOException
    {
            VMeuble.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre GestionDuRestaurant
            Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
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

        ap_home.toFront();
        genre.setValue("Homme");
        genre.getItems().addAll("Homme", "Femme");
        genre1.getItems().addAll("Homme", "Femme");
        ////////
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("genre"));
        ////////
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        salaire1.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        sexe1.setCellValueFactory(new PropertyValueFactory<>("genre"));
        ////////
        id2.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom2.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        salaire2.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        sexe2.setCellValueFactory(new PropertyValueFactory<>("genre"));

    }

}
