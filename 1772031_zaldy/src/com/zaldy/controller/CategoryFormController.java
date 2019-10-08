package com.zaldy.controller;

import com.zaldy.dao.CategoryDaoImpl;
import com.zaldy.entity.category;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryFormController implements Initializable {
    public Button btnSave;
    public TextField txtID;
    public TextField txtName;
    public TableView<category> tableCat;
    public TableColumn<category, Integer> colID;
    public TableColumn<category, String> colCat;
    private ObservableList<category> categories;
    private CategoryDaoImpl categoryDao;
    private MenuFormController menucontroller;


    public ObservableList<category> getCategories() {
        if (categories == null) {
            categories = FXCollections.observableArrayList();
            categories.addAll(getCategoryDao().showAll());
        }
        return categories;
    }

    public CategoryDaoImpl getCategoryDao() {
        if (categoryDao == null) {
            categoryDao = new CategoryDaoImpl();
        }
        return categoryDao;
    }


    public void saveAct(ActionEvent actionEvent) {
        boolean found = false;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        for (category i : menucontroller.getCategories()) {
            if (i.getName().equals(txtName.getText())) {
                found = true;
            }
        }
        if (found) {
            alert.setContentText("Duplicate category name");
            alert.setTitle("Error");
            alert.show();
        } else {
            if (txtName.getText().isEmpty() || txtID.getText().isEmpty()) {
                alert.setTitle("Error");
                alert.setContentText("Please fill category name");
                alert.show();
            } else {
                category c = new category();
                c.setId(Integer.parseInt(txtID.getText()));
                c.setName(txtName.getText());
                menucontroller.getCategoryDao().addData(c);
                menucontroller.getCategories().add(c);


            }
        }
        txtID.clear();
        txtName.clear();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableCat.setItems(getCategories());
        colID.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colCat.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
    }

    public void setMenucontroller(MenuFormController menuFormController) {
        this.menucontroller = menuFormController;
        tableCat.setItems(menucontroller.getCategories());
    }
}


