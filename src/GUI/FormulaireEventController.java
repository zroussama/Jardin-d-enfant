/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Events;
import Services.serviceEvent;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ghass
 */
public class FormulaireEventController implements Initializable {

    @FXML
    private TextArea txProgramme;
    @FXML
    private TextField txTitre;
    @FXML
    private TextField txRegion;
    @FXML
    private TextField txnbrPar;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnAnnuler;
    @FXML
    private TextField txtddj;
    @FXML
    private TextField txtddm;
    @FXML
    private TextField txtdda;
    @FXML
    private TextField txtdfj;
    @FXML
    private TextField txtdfm;
    @FXML
    private TextField txtdfa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void envoyerEvent(MouseEvent event){
        serviceEvent se = new serviceEvent();
        String datedebut =txtdda.getText()+"-"+txtddm.getText()+"-"+txtddj.getText();
        String datefin =txtdfa.getText()+"-"+txtdfm.getText()+"-"+txtdfj.getText();
        Events e = new Events(txTitre.getText(),txProgramme.getText(),txRegion.getText(),datedebut,datefin,Integer.parseInt(txnbrPar.getText()));
        se.ajouterEvent(e);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Formulaire_event.fxml"));
        
    }

    @FXML
    private void btnValider(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Evénement ajouté avec succée !");
            alert.showAndWait();
        
    }

    @FXML
    private void btnAnnuler(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("afficherEvent.fxml"));
        Scene t = new Scene(tableViewParent);
        Stage a = (Stage)((Node)event.getSource()).getScene().getWindow();
        a.setScene(t);
        a.show() ;
    }
    
}
