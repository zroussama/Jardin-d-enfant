/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Events;
import Services.serviceEvent;
import Utils.connexionDB;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class homepage extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/afficherEnseignant.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/GUI/afficherEvent.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Enseignant");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DocumentException {
        launch(args);
        
        
    
}}
         
         
