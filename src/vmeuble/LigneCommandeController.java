/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmeuble;

import static java.awt.PageAttributes.ColorType.COLOR;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class LigneCommandeController implements Initializable {

 @FXML
 private Label idmeuble,qte,prixi,prixt;
@FXML
Circle valide;
    
 public  static int qteA;
public static float prixiA,prixtA;
public static int valideA;
public static String idmeubleA;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idmeuble.setText(idmeubleA+"");
        qte.setText(qteA+"");
        prixi.setText(prixiA+"");
        prixt.setText("total:"+prixtA);
        if(valideA==1)
        {
            valide.setFill(Color.GREEN);
        }
        else
        {
             valide.setFill(Color.RED);
        }
        // TODO
    }    
   
   

}
