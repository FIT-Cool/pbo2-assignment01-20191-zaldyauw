package com.zaldy.controller;

import com.zaldy.entity.Category;
import com.zaldy.entity.item;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ModulController implements Initializable {
    public TextField nama;
    public TextField harga;
    public TextField categoryname;
    public TableView<item> table;
    public TableColumn<item,String> col1;
    public TableColumn<item,String> col2;
    public TableColumn<item,String> col3;
    public ComboBox<Category> combobox;
    public Button btnUpdate;
    public Button btnReset;
    public Button btnSave;
    private ObservableList<item> item;
    private ObservableList<Category> Categories;
    int hitung;





    public void savecategoryaction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(categoryname.getText().isEmpty()){
            alert.setTitle("Error");
            alert.setContentText("Please fill category name");
            alert.show();
        }
        else{
            Category c = new Category();
            c.setCategoryname(categoryname.getText());
            hitung=(int) Categories.stream().filter(p ->p.getCategoryname().equalsIgnoreCase(categoryname.getText())).count();
            if(hitung>0){
                alert.setTitle("Error");
                alert.setContentText("Duplicate category name");
                alert.show();
            }
            else {
                Categories.add(c);
                categoryname.clear();
            }
        }

    }

    public void saveaction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (nama.getText().isEmpty() || harga.getText().isEmpty() || combobox.getValue() == null) {
            alert.setTitle("Error");
            alert.setContentText("Please fill category/name/price");
            alert.show();
        } else {
            item i = new item();
            i.setNama(nama.getText());
            hitung = (int) item.stream().filter(p -> p.getNama().equalsIgnoreCase(nama.getText())).count();
            if (hitung > 0) {
                alert.setTitle("Error");
                alert.setContentText("Duplicate item name");
                alert.show();
            } else {
                i.setNama(nama.getText().trim());
                i.setHarga((harga.getText().trim()));
                i.setCategory((combobox.getValue()));
                item.add(i);
            }


        }
    }

    public void updateaction(ActionEvent actionEvent) {
        item i = table.getSelectionModel().getSelectedItem();
        i.setNama(nama.getText());
        i.setHarga(harga.getText());
        i.setCategory((combobox.getValue()));
        table.refresh();
        btnSave.setDisable(false);
        btnReset.setDisable(true);

    }

    public void resetaction(ActionEvent actionEvent) {
        nama.setText("");
        harga.setText("");
        categoryname.setText("");
        combobox.setValue(null);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        item = FXCollections.observableArrayList();
        table.setItems(item);
        Categories = FXCollections.observableArrayList();
        combobox.setItems(Categories);
        col1.setCellValueFactory(data -> {
            item i = data.getValue();
            return new SimpleStringProperty(i.getNama());
        });
        col2.setCellValueFactory(data ->{
            item i = data.getValue();
            return new SimpleStringProperty(i.getHarga());
        });
        col3.setCellValueFactory(data ->{
            item i = data.getValue();
            return new SimpleStringProperty(i.getCategory().getCategoryname());
        });

    }

    public void tableClicked(MouseEvent mouseEvent) {
        item i=table.getSelectionModel().getSelectedItem();
        nama.setText(i.getNama());
        i.setHarga(harga.getText());
        i.setCategory((combobox.getValue()));
        btnUpdate.setDisable(false);
        btnSave.setDisable(true);
    }
}
