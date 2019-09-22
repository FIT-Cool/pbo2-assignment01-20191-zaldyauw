package com.zaldy.controller;

import com.zaldy.entity.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
public class CategoryManagementController implements Initializable {
    @FXML
    private TextField txtIdCat;
    @FXML
    private TextField txtNameCat;
    @FXML
    private TableView<Category> tabCategory;
    @FXML
    private TableColumn<Category, String> cTabID;
    @FXML
    private TableColumn<Category, String> cTabName;
    private MainFormController MainController;

    Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private void btnSaveCat(ActionEvent actionEvent) {
        Category cat = new Category();
        cat.setIdCat(Integer.valueOf(txtIdCat.getText().trim()));
        cat.setNameCat(txtNameCat.getText());

        if(cat.getNameCat().equals("")){
            alert.setContentText("Please fill category name");
            alert.showAndWait();
        }
        else{
            boolean found = false;
            for (Category kategori : MainController.categories){
                if(kategori.getNameCat().equals(cat.getNameCat())){
                    found = true;
                    alert.setContentText("Duplicate category name");
                    alert.showAndWait();
                }
            }
            if(!found){
                MainController.getCategories().add(cat);
                txtIdCat.clear();
                txtNameCat.clear();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resource){
        cTabID.setCellValueFactory(data->{
            Category c = data.getValue();
            return new SimpleStringProperty(String.valueOf(c.getIdCat()));
        });
        cTabName.setCellValueFactory(data->{
            Category c = data.getValue();
            return new SimpleStringProperty(c.getNameCat());
        });
    }

    public void setMainController(MainFormController MainController){
        this.MainController = MainController;
        tabCategory.setItems(MainController.getCategories());
    }

    public MainFormController getMainController() {
        return MainController;
    }
}