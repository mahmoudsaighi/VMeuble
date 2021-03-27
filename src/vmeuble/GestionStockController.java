/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmeuble;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.File;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class GestionStockController implements Initializable {

    @FXML
    AnchorPane ap_afficher;
    @FXML
    AnchorPane ap_home;
    @FXML
    AnchorPane ap_confirme_modifay;
    @FXML
    AnchorPane ap_ajouter;
    @FXML
    AnchorPane ap_modifier;
    @FXML
    AnchorPane ap_supprimer;
    @FXML
    TableView<Meuble> table, table1, table2;
    @FXML
    TableColumn id, id1, id2;
    @FXML
    TableColumn<Meuble,String>  qte, qte1, qte2;
    @FXML
    TableColumn categorie, categorie1, categorie2;
    @FXML
    TableColumn descriptif, descriptif1, descriptif2;
    @FXML
    TableColumn prix, prix1, prix2;
    @FXML
    TableColumn couleur, couleur1, couleur2;
    @FXML
    ComboBox<String> genre, genre1;
    @FXML
    JFXTextField tprix, tprix1;
    @FXML
    JFXTextField tcouleur, tcouleur1;
    @FXML
    JFXTextArea tdescriptif, tdescriptif1;
    @FXML
    JFXTextField tqte, tqte1;
    @FXML
    JFXTextField tfselected;

    int id_perso = 0;
    public    boolean a,b;
   

    public void AfficherMeuble(ActionEvent event) throws IOException, SQLException {
        ap_afficher.toFront();
        Connection cnx = VMeuble.connection;
        ArrayList<Meuble> meuble = new ArrayList<>();
        java.sql.Statement myStmt = cnx.createStatement();

        try {

            ResultSet myRs = myStmt.executeQuery("SELECT `idmeuble`, `categorie`, `descriptif`, `prix`, `couleur`,`qte` FROM `meuble` ;");

            while (myRs.next()) {
              Meuble p=  new Meuble(myRs.getInt("idmeuble"), myRs.getInt("qte"), myRs.getString("categorie"), myRs.getString("descriptif"), myRs.getString("couleur"), myRs.getFloat("prix"));
                
                meuble.add(p);
               
           
                
              
            }
             qte.setCellValueFactory(new PropertyValueFactory<Meuble , String>("qte"));
             ObservableList<Meuble> listObservable = FXCollections.observableArrayList(meuble);
            
            table.setItems(listObservable);
            
            
            // Caster la list a une list Observable
            
                   

        } catch (SQLException ex) {
            Logger.getLogger(VMeuble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void retour(ActionEvent event) {
        ap_home.toFront();
    }

    public void PaneAjouter(ActionEvent event) throws IOException, SQLException {
        ap_ajouter.toFront();

    }

    public void ajouterMeuble(ActionEvent event) throws IOException, SQLException {

      

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de l'ajout");
            alert.setContentText("Voulez vraiment confirmer l'ajout ?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                 if(!(tdescriptif.getText().isEmpty()) && !(tcouleur.getText().isEmpty()) && !(tprix.getText().isEmpty()) && !(tqte.getText().isEmpty()) )
                 {
                try {
                    Connection cnx = VMeuble.connection;
                    //execute sql query
                    java.sql.Statement myStmt = cnx.createStatement();
                        try{
                            Float.parseFloat(tprix.getText());
                            if(Float.parseFloat(tprix.getText())<0)
                          {
                          
            // Header Text: null
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("Erreur");
            alert3.setHeaderText(null);
            alert3.setContentText("Veuillez entrer un chiffe superieur a zero dans le prix ");
            alert3.show(); 
            b=false;
                          }
                            else{
                                b=true;
                            }
                        }catch(NumberFormatException e) {
                     Alert alert2 = new Alert(Alert.AlertType.ERROR);     
            alert2.setTitle("Erreur");
            b=false;
            // Header Text: null
            alert2.setHeaderText(null);
            alert2.setContentText("Veuillez entrer un chiffe dans le prix ");
            alert2.show(); }
                         try{
                          Integer.parseInt(tqte.getText());
                          if(Integer.parseInt(tqte.getText())<0)
                          {
                          alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un chiffe superieur a zero dans la quantité ");
            alert.show();
            a=false;
                          }
                          else{
                               a=true;
                          }
                        }catch(NumberFormatException e) {
                          
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un chiffe superieur a zero dans la quantité ");
            alert.show();
                        a=false;}
                         if(a & b)
                         {
                             
                    myStmt.execute("INSERT INTO `meuble`(`categorie`, `descriptif`, `prix`, `couleur`,`photo`,`qte`) VALUES('"
                            + genre.getValue()
                            + "','" + tdescriptif.getText()
                            + "','" + tprix.getText()
                            + "','" + tcouleur.getText()
                            + "','" + "http://192.168.1.5:8888/Bureau/" + tfselected.getText()
                            + "','" + tqte.getText()
                            + "');");
                         AfficherMeuble(event);
                         }
                          


               
                 

                } catch (SQLException e) {
                }
                
            

        } else{
                 Alert alert4 = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.show(); 
            }     
                 }
            tdescriptif.clear();
            tprix.clear();
            tcouleur.clear();
            tqte.clear();
    }

    public void PaneSupprimer(ActionEvent event) throws IOException, SQLException {
        ap_supprimer.toFront();
        Connection cnx = VMeuble.connection;
        ArrayList<Meuble> meuble = new ArrayList<>();
        java.sql.Statement myStmt3 = cnx.createStatement();

        try {

            ResultSet myRs = myStmt3.executeQuery("SELECT `idmeuble`, `categorie`, `descriptif`, `couleur`, `prix`,  `qte`  FROM `meuble`;");

             while (myRs.next()) {
              Meuble p=  new Meuble(myRs.getInt("idmeuble"), myRs.getInt("qte"), myRs.getString("categorie"), myRs.getString("descriptif"), myRs.getString("couleur"), myRs.getFloat("prix"));
                
                meuble.add(p);
               
           
                
              
            }
             qte1.setCellValueFactory(new PropertyValueFactory<Meuble , String>("qte"));
     
            // Caster la list a une list Observable
            ObservableList<Meuble> listObservable = FXCollections.observableArrayList(meuble);
            table1.setItems(listObservable);

        } catch (SQLException ex) {
            Logger.getLogger(VMeuble.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete() throws SQLException {
        if (table1.getSelectionModel().isSelected(table1.getSelectionModel().getSelectedIndex())) {
            Meuble meuble;
            meuble = table1.getSelectionModel().getSelectedItem();
            int Id = meuble.getId();
            Connection cnx = VMeuble.connection;
            //execute sql query
            java.sql.Statement myStmt = cnx.createStatement();
            

            try { Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de suprression");
            alert.setContentText("Voulez vraiment confirmer la modification ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // myStmt2.execute("UPDATE `tableconnectee` SET `id_serveur`=NULL WHERE `id_serveur`="+Id+";");
                myStmt.execute("DELETE FROM `meuble` WHERE idmeuble=" + Id + ";");
                 table1.getItems().remove(meuble);
            }else {
                // ... user chose CANCEL or closed the dialog
            }
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

    public void ModifierStocks(ActionEvent event) throws IOException, SQLException {
        ap_modifier.toFront();
        Connection cnx = VMeuble.connection;
        ArrayList<Meuble> meuble = new ArrayList<>();
        java.sql.Statement myStmt = cnx.createStatement();

        try {

            ResultSet myRs = myStmt.executeQuery("SELECT `idmeuble`, `categorie`, `descriptif`,  `couleur`, `prix`,`qte` FROM `meuble` ;");

             while (myRs.next()) {
              Meuble p=  new Meuble(myRs.getInt("idmeuble"), myRs.getInt("qte"), myRs.getString("categorie"), myRs.getString("descriptif"), myRs.getString("couleur"), myRs.getFloat("prix"));
                
                meuble.add(p);
               
           
                
              
            }
              qte2.setCellValueFactory(new PropertyValueFactory<Meuble , String>("qte"));
            // Caster la list a une list Observable
            ObservableList<Meuble> listObservable = FXCollections.observableArrayList(meuble);
            table2.setItems(listObservable);

        } catch (SQLException ex) {
            Logger.getLogger(VMeuble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PaneModifier(ActionEvent event) throws IOException, SQLException {
        if (table2.getSelectionModel().isSelected(table2.getSelectionModel().getSelectedIndex())) {
            Meuble meuble;
            meuble = table2.getSelectionModel().getSelectedItem();
            id_perso = meuble.getId();
            ap_confirme_modifay.toFront();

            try {

                Connection cnx = VMeuble.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();
                ResultSet myRs = myStmt.executeQuery("SELECT `categorie`, `descriptif`, `prix`, `couleur`, `qte` FROM `meuble` WHERE  `idmeuble`= " + id_perso + ";");
                if (myRs.next()) {
                    try{
                            Float.parseFloat(tprix.getText());
                        }catch(NumberFormatException e) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);       
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un chiffe dans le prix ");
            alert.show(); }
                    tcouleur1.setText(myRs.getString("couleur"));
                    tdescriptif1.setText(myRs.getString("descriptif"));
                    tprix1.setText(Float.toString(myRs.getInt("prix")));
                    genre1.setValue(myRs.getString("categorie"));
                    tqte1.setText(myRs.getInt("qte")+"");

                }

            } catch (SQLException ex) {
                Logger.getLogger(VMeuble.class.getName()).log(Level.SEVERE, null, ex);
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

    public void ConfirmerModification(ActionEvent event) throws IOException, SQLException {

        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de modification");
            alert.setContentText("Voulez vraiment confirmer la modification ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if(!(tdescriptif1.getText().isEmpty()) && !(tcouleur1.getText().isEmpty()) && !(tprix1.getText().isEmpty()) )
                {
                Connection cnx = VMeuble.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();
                try{
                            Float.parseFloat(tprix.getText());
                            if(Float.parseFloat(tprix.getText())<0)
                          {
                          alert.setTitle("Erreur");
            // Header Text: null
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setHeaderText(null);
            alert3.setContentText("Veuillez entrer un chiffe superieur a zero dans le prix ");
            alert3.show();
            a=false;
                          }
                            else
                                a=true;
                        }catch(NumberFormatException e) {
                     Alert alert2 = new Alert(Alert.AlertType.ERROR);     
            alert2.setTitle("Erreur");
            // Header Text: null
            alert2.setHeaderText(null);
            alert2.setContentText("Veuillez entrer un chiffe dans le prix ");
            alert2.show(); 
                        a=false;}
                         try{
                          Integer.parseInt(tqte.getText());
                          if(Integer.parseInt(tqte.getText())<0)
                          {
                          alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un chiffe superieur a zero dans la quantité ");
            alert.show();
            b=false;
                          }
                          else
                              b=true;
                        }catch(NumberFormatException e) {
                          
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un chiffe superieur a zero dans la quantité ");
            alert.show();
                        b=false;}
                         if(a & b){
                myStmt.execute("UPDATE `meuble` SET categorie='" + genre1.getValue() + "', descriptif='" + tdescriptif1.getText() + "',couleur='" + tcouleur1.getText() + "', prix='" + tprix1.getText()
                        + tqte1.getText() + "' where `idmeuble`= " + id_perso + ";");

                ModifierStocks(event);
                         }

            } else{
                   Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.show(); 
            }
            }else {
                // ... user chose CANCEL or closed the dialog
            }

        } catch (IOException | SQLException e) {

        }
    }

    public void AddImge(ActionEvent event) {

        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            //imageSelected.setText(selectedFile.getName());
            tfselected.setText(selectedFile.getName());
            //System.out.println("le nom :" + selectedFile.getName());
        } else {
            System.out.println("erreur de selection");
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

        genre.getItems().addAll("Cuisine", "Jardin", "Salon", "Chambre");
        genre1.getItems().addAll("Cuisine", "Jardin", "Salon", "Chambre");
        ap_home.toFront();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        descriptif.setCellValueFactory(new PropertyValueFactory<>("descriptif"));
        couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        /////////
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        categorie1.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        descriptif1.setCellValueFactory(new PropertyValueFactory<>("descriptif"));
        couleur1.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        prix1.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qte1.setCellValueFactory(new PropertyValueFactory<>("qte1"));
        /////////
        id2.setCellValueFactory(new PropertyValueFactory<>("id"));
        categorie2.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        descriptif2.setCellValueFactory(new PropertyValueFactory<>("descriptif"));
        couleur2.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        prix2.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qte2.setCellValueFactory(new PropertyValueFactory<>("qte2"));
    }

}
