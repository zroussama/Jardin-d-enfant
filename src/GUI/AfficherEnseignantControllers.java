/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Enseignant;
import Entities.Events;
import Services.serviceEnseignant;
import Services.serviceEvent;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;


public class AfficherEnseignantControllers implements Initializable {

    @FXML
    private TableView<Enseignant> tableEnseignant;
    @FXML
    private TableColumn<Enseignant, Integer> coloneID;
    @FXML
    private TableColumn<Enseignant, String> coloneNom;
    @FXML
    private TableColumn<Enseignant, String> colonePrenom;
    @FXML
    private TableColumn<Enseignant, Integer> coloneCIN;
    @FXML
    private TableColumn<Enseignant, Float> coloneSalaire;
    @FXML
    private TableColumn<Enseignant, Integer> coloneAbsence;
    @FXML
    private TableColumn<Enseignant, String> coloneModifier;
    @FXML
    private TableColumn<Enseignant, String> coloneSupprimer;
    @FXML
    private Button btnValider;
    @FXML
    private TextField txNom;
    @FXML
    private TextField txCIN;
    @FXML
    private TextField txAbsence;
    @FXML
    private TextField txPrenom;
    @FXML
    private TextField txSalaire;
    @FXML
    private TextField txEvaluation;
    @FXML
    private TableColumn<Enseignant, String> coloneEvaluation;
    @FXML
    private Label lbModifier;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            serviceEnseignant se = new serviceEnseignant();
            ObservableList<Enseignant> list = FXCollections.observableArrayList();
            
            list = se.readAll();
            
            txNom.setVisible(false);
            txPrenom.setVisible(false);
            txCIN.setVisible(false);
            txEvaluation.setVisible(false);
            txAbsence.setVisible(false);
            txSalaire.setVisible(false);
            btnValider.setVisible(false);
            lbModifier.setVisible(false);
             btnAnnuler.setVisible(false);
            
            coloneID.setCellValueFactory(new PropertyValueFactory<>("ID_enseignant"));
            coloneNom.setCellValueFactory(new PropertyValueFactory<>("Nom")  );
            colonePrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
            coloneCIN.setCellValueFactory(new PropertyValueFactory<>("CIN"));
            coloneSalaire.setCellValueFactory(new PropertyValueFactory<>("Salaire"));
            coloneEvaluation.setCellValueFactory(new PropertyValueFactory<>("Evaluation"));
            coloneAbsence.setCellValueFactory(new PropertyValueFactory<>("Absence"));
            
            Callback<TableColumn<Enseignant, String>, TableCell<Enseignant, String>> cellFactory = (param) -> {
                final TableCell<Enseignant, String> cell = new TableCell<Enseignant, String>(){
                    @Override
                    public void updateItem (String item, boolean empty){
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button btnEdit = new Button("Modifier");
                            btnEdit.setOnAction(event -> {
                                txNom.setVisible(true);
                                txPrenom.setVisible(true);
                                txCIN.setVisible(true);
                                txEvaluation.setVisible(true);
                                txAbsence.setVisible(true);
                                txSalaire.setVisible(true);
                                btnValider.setVisible(true);
                                lbModifier.setVisible(true);
                                btnAnnuler.setVisible(true);
                                setCellValue();
                            });
                            setGraphic(btnEdit);
                            setText(null);
                        }
                    }
                };
                return cell;
            };
            
            Callback<TableColumn<Enseignant,String>, TableCell<Enseignant,String>> cellFactory2 = param -> {
                final TableCell<Enseignant,String> cell = new TableCell<Enseignant, String>(){
                    @Override
                    public void updateItem(String item, boolean empty){
                        super.updateItem(item,empty);
                        if (empty){
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button btnDelete = new Button("Supprimer");
                            btnDelete.setOnAction(event -> {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"L'enseignant sera supprimer", ButtonType.YES , ButtonType.NO);
                                alert.showAndWait();
                                if (alert.getResult()== ButtonType.YES){
                                    
                                
                                Enseignant e2 = getTableView().getItems().get(getIndex());
                                
                                se.supprimerEnseignant(e2.getID_enseignant());
                                ObservableList<Enseignant> listUpdate = FXCollections.observableArrayList();
                                try {
                                    listUpdate = se.readAll();
                                } catch (SQLException ex) {
                                    Logger.getLogger(AfficherEnseignantControllers.class.getName()).log(Level.SEVERE, null ,ex);
                                }
                                tableEnseignant.setItems(listUpdate);
                                }
                            });
                            setGraphic(btnDelete);
                            setText(null);
                        }
                    }
                };
                return cell;
            };
            coloneModifier.setCellFactory(cellFactory);
            coloneSupprimer.setCellFactory(cellFactory2);
            tableEnseignant.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEnseignantControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierEvent (MouseEvent event){
        serviceEnseignant se = new serviceEnseignant();
        Enseignant e = tableEnseignant.getItems().get(tableEnseignant.getSelectionModel().getSelectedIndex());
        se.modifierEnseignant(e.getID_enseignant(),txNom.getText(),txPrenom.getText(),Integer.parseInt(txCIN.getText()),Float.parseFloat(txSalaire.getText()),Integer.parseInt(txAbsence.getText()),txEvaluation.getText());
        ObservableList<Enseignant> listUpdate = FXCollections.observableArrayList();
     
        try {
            listUpdate = se.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEnseignantControllers.class.getName()).log(Level.SEVERE, null ,ex);
        }
        
        tableEnseignant.setItems(listUpdate);
        txNom.setText("");
        txPrenom.setText("");
        txCIN.setText("");
        txEvaluation.setText("");
        txAbsence.setText("");
        txSalaire.setText("");
        
        txNom.setVisible(false);
        txPrenom.setVisible(false);
        txCIN.setVisible(false);
        txEvaluation.setVisible(false);
        txAbsence.setVisible(false);
        txSalaire.setVisible(false);
        btnValider.setVisible(false);
        lbModifier.setVisible(false);
        btnAnnuler.setVisible(false);

    }

    public void setCellValue(){
        tableEnseignant.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Enseignant  e = tableEnseignant.getItems().get(tableEnseignant.getSelectionModel().getSelectedIndex());
                txNom.setText(e.getNom());
                txPrenom.setText(e.getPrenom());
                txCIN.setText(String.valueOf(e.getCIN()));
                txAbsence.setText(String.valueOf(e.getAbsence()));
                txSalaire.setText(String.valueOf(e.getSalaire()));
                txEvaluation.setText(e.getEvaluation());
            }
        });
    }

    @FXML
    private void btnAjouter(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Formulaire_enseignant.fxml"));
        Scene t = new Scene(tableViewParent);
        Stage a = (Stage)((Node)event.getSource()).getScene().getWindow();
        a.setScene(t);
        a.show() ;     
    }

    @FXML
    private void btnRetour(ActionEvent event) throws IOException {
          Parent tableViewParent = FXMLLoader.load(getClass().getResource("afficherEnseignant.fxml"));
        Scene t = new Scene(tableViewParent);
        Stage a = (Stage)((Node)event.getSource()).getScene().getWindow();
        a.setScene(t);
        a.show() ;    
    }

    @FXML
    private void btnAnnuler(ActionEvent event) {
        txNom.setVisible(false);
            txPrenom.setVisible(false);
            txCIN.setVisible(false);
            txEvaluation.setVisible(false);
            txAbsence.setVisible(false);
            txSalaire.setVisible(false);
            btnValider.setVisible(false);
            lbModifier.setVisible(false);
             btnAnnuler.setVisible(false);
            
    }

    @FXML
    private void exporterPDF(ActionEvent event) throws DocumentException {
  
        creatpdf();
    }
    private void creatpdf() throws DocumentException {


        serviceEvent se  = new serviceEvent();
        Events a ;
  
        String titre = "Enseignant";
        String out = "C:\\Users\\ghass\\Desktop\\enseignant.pdf"; //path desktop melek
        Document document = new Document();
        StringBuilder boxText = new StringBuilder();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(out));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.open();
        
     
        document.add(new Paragraph("\n\n\n\n\n"));
        document.add(new Paragraph("Enseignant :"));
        PdfPTable table = new PdfPTable(4); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table

        PdfPCell cell1 = new PdfPCell(new Paragraph("Nom"));
        cell1.setBorderColor(BaseColor.BLUE);
        cell1.setPaddingLeft(10);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell2 = new PdfPCell(new Paragraph("Prenom"));
        cell2.setBorderColor(BaseColor.BLUE);
        cell2.setPaddingLeft(5);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell3 = new PdfPCell(new Paragraph("CIN"));
        cell3.setBorderColor(BaseColor.BLUE);
        cell3.setPaddingLeft(5);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell4 = new PdfPCell(new Paragraph("Evaluation"));
        cell3.setBorderColor(BaseColor.BLUE);
        cell3.setPaddingLeft(5);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        document.add(table);
        PdfPTable table1 = new PdfPTable(4); // 3 columns.
        table1.setWidthPercentage(100); //Width 100%
        table1.setSpacingBefore(10f); //Space before table
        table1.setSpacingAfter(10f); //Space after table

        
        
       

        PdfPCell txt_titre = new PdfPCell(new Paragraph(txNom.getText()));
        txt_titre.setBorderColor(BaseColor.BLUE);
        txt_titre.setPaddingLeft(5);
        txt_titre.setHorizontalAlignment(Element.ALIGN_CENTER);
        txt_titre.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell txt_desc = new PdfPCell(new Paragraph(txPrenom.getText()));
        txt_desc.setBorderColor(BaseColor.BLUE);
        txt_desc.setPaddingLeft(5);
        txt_desc.setHorizontalAlignment(Element.ALIGN_CENTER);
        txt_desc.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell txt_prix = new PdfPCell(new Paragraph(txCIN.getText()));
        txt_prix.setBorderColor(BaseColor.BLUE);
        txt_prix.setPaddingLeft(5);
        txt_prix.setHorizontalAlignment(Element.ALIGN_CENTER);
        txt_prix.setVerticalAlignment(Element.ALIGN_MIDDLE);
        
        PdfPCell txt_fin = new PdfPCell(new Paragraph(txEvaluation.getText()));
        txt_fin.setBorderColor(BaseColor.BLUE);
        txt_fin.setPaddingLeft(5);
        txt_fin.setHorizontalAlignment(Element.ALIGN_CENTER);
        txt_fin.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table1.addCell(txt_titre);
        table1.addCell(txt_desc);
        table1.addCell(txt_prix);
        table1.addCell(txt_fin);
        document.add(table1);
  
  
  
        document.close();
       
        System.out.println("Document '" + out + "' generated");
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("PDF générer");
    alert.setHeaderText(null);
    alert.setContentText("PDF générer");
    alert.showAndWait();
}
    }
    
   

