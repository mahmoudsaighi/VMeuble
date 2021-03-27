/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmeuble;

import com.jfoenix.controls.JFXListView;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class CommandeController implements Initializable {
    
    @FXML
    TableView<Commande> table4;
    @FXML
    TableColumn<Commande,String> id , username;
    @FXML
    JFXListView commandelist;
    @FXML
    private Label prixF;
    @FXML
     Label numero;
    @FXML
    Pane print;
    @FXML 
    AnchorPane lolo;
   
    
    public static Parent pp;
    
    
    /**
     * Initializes the controller class.
     */
       

    public void AfficherCommande(ActionEvent event) throws IOException, SQLException {
        
        Connection cnx = VMeuble.connection;
        ArrayList<Commande> commande = new ArrayList<>();
        java.sql.Statement myStmt = cnx.createStatement();

        try {

            ResultSet myRs = myStmt.executeQuery("SELECT DISTINCT `idcommande`, `username` FROM `commande`;");

            while (myRs.next()) {
                Commande p = new Commande(myRs.getInt("idcommande"), myRs.getString("username"));

                commande.add(p);
                
                
            }
            id.setCellValueFactory(new PropertyValueFactory<Commande , String>("id"));
            username.setCellValueFactory(new PropertyValueFactory<Commande , String>("username"));
            // Caster la list a une list Observable
            ObservableList<Commande> listObservable = FXCollections.observableArrayList(commande);
            table4.setItems(listObservable);
            
        } catch (SQLException ex) {
            Logger.getLogger(VMeuble.class.getName()).log(Level.SEVERE, null, ex);
        }
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
       public void imprimer(ActionEvent event) 
       {    Node root = null;
           PrinterJob printerJob = PrinterJob.createPrinterJob();
if (printerJob != null) {
  PageLayout pageLayout = printerJob.getPrinter().createPageLayout(Paper.A3,PageOrientation.LANDSCAPE,100,0,0,0);
lolo.setVisible((false));
print.setLayoutX(200);
  boolean success = printerJob.printPage(pageLayout, pp);
  if (success) {
    printerJob.endJob();
    lolo.setVisible(true);
    print.setLayoutX(400);
  }
}
       }
       public void afficherINFOcommande(ActionEvent event) throws IOException, SQLException
       { float prixfinal=0;
            if (table4.getSelectionModel().isSelected(table4.getSelectionModel().getSelectedIndex()))
            {Commande c=table4.getSelectionModel().getSelectedItem();
            Connection cnx = VMeuble.connection;
            commandelist.getItems().clear();
            
            //execute sql query
            java.sql.Statement myStmt = cnx.createStatement();
            java.sql.Statement myStmt2 = cnx.createStatement();
            java.sql.Statement myStmt3 = cnx.createStatement();
                System.out.println(c.getId());
             ResultSet myRs = myStmt.executeQuery("SELECT `idmeuble`, `qte` , `valider` from commande where idcommande= "+c.getId()+";");
             while(myRs.next())
             {
                   
                  LigneCommandeController.qteA=myRs.getInt("qte");  
                  LigneCommandeController.valideA=myRs.getInt("valider");
                  ResultSet myRs2=myStmt2.executeQuery("SELECT `prix`,`descriptif` from meuble where idmeuble ="+myRs.getInt("idmeuble")+";" );
               if(myRs2.next()){
                  LigneCommandeController.prixiA= myRs2.getFloat("prix");
                  LigneCommandeController.prixtA=myRs2.getFloat("prix")*myRs.getInt("qte");
                  LigneCommandeController.idmeubleA=myRs2.getString("descriptif");
                  if(LigneCommandeController.valideA==1)
                  prixfinal+=LigneCommandeController.prixtA;
               }
                ResultSet myRs3=myStmt3.executeQuery("SELECT `numTel` from client where username='"+c.getUsername()+"';" );
                if(myRs3.next())
                {
                    numero.setText("num Client:"+myRs3.getString("numTel"));
                }
                  Parent Lc=FXMLLoader.load(getClass().getResource("LigneCommande.fxml"));
                  
                 
                  commandelist.getItems().add(Lc);
                  
                  
                        
             }
             prixF.setText("Total:"+prixfinal+"$");
             
            
             
            }
       }
     /*  public void imprimer (ActionEvent event) throws IOException, AWTException{
           
               Dimension d = new Dimension(700, 700);
               Robot robot = new Robot();
               Rectangle rectangle = new Rectangle (d);
               BufferedImage image = robot.createScreenCapture(rectangle);
               WritableImage myImage = SwingFXUtils.toFXImage(image, null);
               
               display.setImage(myImage);
           
       }
       public void print(final Node node, Printer printer) {
       
        PrinterJob job = PrinterJob.createPrinterJob();
        job.setPrinter(printer);
        if (job != null) {
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }
    }  */  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
}
