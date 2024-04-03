/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.piegraf_tableview;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLController implements Initializable {

  
    @FXML
    private TableView<Zamestnanci> tbInformace;
    @FXML
    private TableColumn<Zamestnanci, String> jmeno;
    @FXML
    private TableColumn<Zamestnanci, String> prijmeni;
    @FXML
    private TableColumn<Zamestnanci, Integer> plat;
    @FXML
    private Button btnPridej;
    @FXML
    private PieChart pieChartPlaty;

    ObservableList<Zamestnanci> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jmeno.setCellValueFactory(new PropertyValueFactory<Zamestnanci, String>("jmeno"));
        prijmeni.setCellValueFactory(new PropertyValueFactory<Zamestnanci, String>("prijmeni"));
        plat.setCellValueFactory(new PropertyValueFactory<Zamestnanci, Integer>("plat"));

        tbInformace.setItems(list);
    }

    @FXML
    private void Pridej(ActionEvent event) {

        Dialog<Zamestnanci> dialog = new Dialog();
        dialog.setContentText("Pridejte uzivatele");
        dialog.setHeaderText("Dialog pro zadani hodnot");

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        TextField tfJmeno = new TextField();
        TextField tfPrijmeni = new TextField();
        TextField tfPlat = new TextField();

        gridPane.add(new Label("Jmeno"), 0, 0);
        gridPane.add(new Label("Prijmeni"), 0, 1);
        gridPane.add(new Label("Plat"), 0, 2);
        gridPane.add(tfJmeno, 1, 0);
        gridPane.add(tfPrijmeni, 1, 1);
        gridPane.add(tfPlat, 1, 2);

        dialog.getDialogPane().setContent(gridPane);
        ButtonType btnOk = new ButtonType("Ok", ButtonData.OK_DONE);
        ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(btnOk, btnCancel);

        dialog.setResultConverter(new Callback<ButtonType, Zamestnanci>() {
            @Override
            public Zamestnanci call(ButtonType dialogButton) {
                if (dialogButton == btnOk) {
                    String jmenoDialog = tfJmeno.getText().trim();
                    String prijmeniDialog = tfPrijmeni.getText().trim();
                    int platDialog = Integer.parseInt(tfPlat.getText().trim());
                    return new Zamestnanci(jmenoDialog, prijmeniDialog, platDialog);
                }
                return null;
            }
        });
        
        
         Optional<Zamestnanci> result = dialog.showAndWait();
        
          result.ifPresent(novyClovek -> {
        list.add(novyClovek);
    });
     updatePieChart();

    }

    private void updatePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Zamestnanci user : list) {
            pieChartData.add(new PieChart.Data(user.getJmeno(), user.getPlat()));
        }

        pieChartPlaty.setData(pieChartData);
    }
    
}
