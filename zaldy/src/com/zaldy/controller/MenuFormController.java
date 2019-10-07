package com.zaldy.controller;

import com.zaldy.Main;
import com.zaldy.dao.CategoryDaoImpl;
import com.zaldy.dao.MenuDaoImpl;
import com.zaldy.entity.category;
import com.zaldy.entity.menu;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuFormController implements Initializable {
    public MenuItem onCat;
    public TextField txtID;
    public TextField txtName;
    public TextField txtPrice;
    public CheckBox checkbox;
    public ComboBox <category> combobox;
    public Button btnSave;
    public Button btnReset;
    public Button btnUpdate;
    public Button btnDelete;
    public TextArea txtDesc;
    public TableView<menu> tableview;
    public TableColumn<menu,Integer> colID;
    public TableColumn<menu,String> colName;
    public TableColumn<menu,String> colcat;
    public TableColumn <menu,Boolean>colRecom;
    private ObservableList<menu> menus;
    private ObservableList<category> categories;
    private MenuDaoImpl menuDao;
    private CategoryDaoImpl categoryDao;
    int hitung;
    public menu selectedItem;




    public MenuDaoImpl getMenuDao(){
        if(menuDao == null){
            menuDao = new MenuDaoImpl();
        }
        return menuDao;
    }
    public CategoryDaoImpl getCategoryDao(){
        if(categoryDao == null){
            categoryDao = new CategoryDaoImpl();
        }
        return categoryDao;
    }


    public ObservableList<menu> getmenus() {
        if (menus == null){
            menus = FXCollections.observableArrayList();
            menus.addAll(getMenuDao().showAll());
        }
        return menus;
    }

    public ObservableList<category> getCategories() {
        if (categories == null){
            categories= FXCollections.observableArrayList();
            categories.addAll(getCategoryDao().showAll());
        }

        return categories;
    }


    public void catAct(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/CategoryForm.fxml"));
            VBox root = loader.load();
            CategoryFormController controller = loader.getController();
            controller.setMenucontroller(this);
            Stage mainStage = new Stage();
            mainStage.initModality(Modality.WINDOW_MODAL);
            mainStage.setTitle("Category Management");
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveAct(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (txtName.getText().isEmpty() || txtID.getText().isEmpty() || combobox.getValue() == null) {
            alert.setTitle("Error");
            alert.setContentText("Please fill category/name/id");
            alert.show();
        } else {
            menu i = new menu();
            i.setName(txtName.getText());
            hitung = (int) menus.stream().filter(p -> p.getName().equalsIgnoreCase(txtName.getText())).count();
            if (hitung > 0) {
                alert.setTitle("Error");
                alert.setContentText("Duplicate item name");
                alert.show();
            } else {
                i.setId(Integer.parseInt(txtID.getText()));
                i.setName(txtName.getText().trim());
                i.setPrice(Double.valueOf(txtPrice.getText().trim()));
                i.setDescription(txtDesc.getText());
                i.setRecomended(Boolean.valueOf(checkbox.getText()));
                i.setCategory((combobox.getValue()));
//                menus.add(i);
                getMenuDao().addData(i);
                getmenus().add(i);
            }
        }
    }

    public void ResetAct(ActionEvent actionEvent) {
        clearForm();
    }

    public void UpdateAct(ActionEvent actionEvent) {
        if (!txtName.getText().trim().isEmpty()) {
            selectedItem.setName((txtName.getText().trim()));
            selectedItem.setCategory(combobox.getValue());
            getMenuDao().updateData(selectedItem);

            refresh();
            clearForm();
        }
    }

    public void DeleteAct(ActionEvent actionEvent) {

        Alert deleteconfirm = new Alert(Alert.AlertType.CONFIRMATION);
        deleteconfirm.setContentText("yakin?");
        deleteconfirm.setTitle("HAPUSSSS");
        deleteconfirm.showAndWait();
        if (deleteconfirm.getResult() == ButtonType.OK){
            selectedItem.setId(Integer.parseInt(txtID.getText()));
            getMenuDao().deleteData(selectedItem);
            getmenus().clear();
            getmenus().addAll(getMenuDao().showAll());
            tableview.refresh();
            clearForm();
        }
    }

    @Override
    public void initialize (URL location, ResourceBundle resources){
        tableview.setItems(getmenus());
        combobox.setItems(this.getCategories());
        colID.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        colcat.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory().getName()));
        colRecom.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().getRecomended()));

    }


    public void refresh(){
        getmenus().clear();
        getmenus().addAll(getMenuDao().showAll());
    }



    public void clearForm(){
        txtID.clear();
        txtName.clear();
        txtPrice.clear();
        txtDesc.clear();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
        tableview.getSelectionModel().clearSelection();
        txtID.setDisable(false);
        selectedItem = null;

    }

    public void tableClicked(MouseEvent mouseEvent) {
        selectedItem = tableview.getSelectionModel().getSelectedItem();
        txtID.setText(String.valueOf(selectedItem.getId()));
        txtName.setText(selectedItem.getName());
        txtID.setDisable(true);
        btnDelete.setDisable(false);
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
    }
}


