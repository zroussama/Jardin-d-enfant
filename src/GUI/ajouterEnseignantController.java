/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Enseignant;
import Services.serviceEnseignant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ghass
 */
public class ajouterEnseignantController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField txNom;
    @FXML
    private TextField txPrenom;
    @FXML
    private TextField txCIN;
    @FXML
    private TextField txSalaire;
    @FXML
    private TextField txEvaluation;
    @FXML
    private TextField txAbsence;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private Button btnAnnuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerEnseignant(MouseEvent event) {
        serviceEnseignant se  = new serviceEnseignant();
        Enseignant e = new Enseignant(txNom.getText(),txPrenom.getText(),txEvaluation.getText(),Integer.parseInt(txCIN.getText()),Integer.parseInt(txAbsence.getText()),Float.parseFloat(txSalaire.getText()));
        
        try {
            se.ajouterEnseignant(e);
        } catch (SQLException ex) {
            Logger.getLogger(ajouterEnseignantController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Formulaire_enseignant.fxml"));
        
            Parent root;
        try {
            root = loader.load();
            ajouterEnseignantController c = loader.getController();
            btnEnvoyer.getScene().setRoot(root);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Enseignant ajouté avec succée !");
            alert.showAndWait();
        
        } catch (IOException ex) {
            Logger.getLogger(ajouterEnseignantController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
    }

    @FXML
    private void btnAnnuler(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("afficherEnseignant.fxml"));
        Scene t = new Scene(tableViewParent);
        Stage a = (Stage)((Node)event.getSource()).getScene().getWindow();
        a.setScene(t);
        a.show() ;
    }
    }
    

