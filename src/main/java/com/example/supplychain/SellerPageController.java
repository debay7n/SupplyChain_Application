package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {
    @FXML
    TextField name;
    @FXML
    TextField price;
    @FXML
    TextField email;


    public void Add(MouseEvent event) throws SQLException {
        ResultSet res = HelloApplication.connection.executeQuery("Select max(productID) from product");
        int productID = 0;
        if(res.next())
            productID = res.getInt("max(productID)") + 1;
        String query = String.format("Insert Into product values(%s, '%s', %s, '%s')",productID, name.getText(),price.getText(),email.getText());
        int response = HelloApplication.connection.executeUpdate(query);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Product Add");
        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if(response>0){

            dialog.setContentText("PRODUCT ADDED SUCCESSFULLY");
        }
        else {
            dialog.setContentText("PRODUCT NOT ADDED");

        }
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    public void back(MouseEvent event) throws IOException, SQLException {
        AnchorPane backpage = FXMLLoader.load((getClass().getResource("LoginPage.fxml")));
        HelloApplication.root.getChildren().add(backpage);
        ProductPage products = new ProductPage();
        header header = new header();

        ListView<HBox> productList = products.showProducts();

        AnchorPane productPane = new AnchorPane();
        productPane.setLayoutX(150);
        productPane.setLayoutY(130);
        productPane.getChildren().add(productList);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,productPane);
    }
}
