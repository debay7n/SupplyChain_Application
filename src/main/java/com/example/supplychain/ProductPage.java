package com.example.supplychain;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductPage {
    ListView<HBox> products;


    ListView<HBox> showProductsbyName(String search) throws SQLException {
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from product");
        products =  new ListView<>();

        Label name  = new Label();
        Label price  = new Label();
        Label id  = new Label();
        HBox details = new HBox();

        name.setMinWidth(70);
        id.setMinWidth(60);
        price.setMinWidth(60);

        name.setText("Name ");
        price.setText("  Price ");
        id.setText("ProductID ");

        details.getChildren().addAll(id, name, price);
        productList.add(details);

        while (res.next()){
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())) {

                Label productName = new Label();
                Label productPrice = new Label();
                Label productID = new Label();
                Button buy = new Button();
                HBox productDetails = new HBox();

                productName.setMinWidth(70);
                productID.setMinWidth(60);
                productPrice.setMinWidth(60);
                buy.setText("Buy");

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailId.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.setContentText("Please LOGIN to Place an Order!");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        } else {
                            Order place = new Order();
                            try {
                                place.placeOrder(productID.getText());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            System.out.println("Item Bought");
                        }
                    }
                });

                productName.setText(res.getString("productName"));
                productPrice.setText(res.getString("price"));
                productID.setText(res.getString("productID"));

                productDetails.getChildren().addAll(productID, productName, productPrice, buy);
                productList.add(productDetails);
            }
        }

        if(productList.size() ==1 ) {

            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Search Result");
            ButtonType type = new ButtonType("Search Again", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("NOT FOUND");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();

        }
        products.setItems(productList);
        return products;

    }


    ListView<HBox> showProducts() throws SQLException {
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from product");
        products =  new ListView<>();

        Label name  = new Label();
        Label price  = new Label();
        Label id  = new Label();
        HBox details = new HBox();

        name.setMinWidth(70);
        id.setMinWidth(60);
        price.setMinWidth(60);

        name.setText("Name ");
        price.setText("  Price ");
       id.setText("ProductID ");

        details.getChildren().addAll(id, name, price);
        productList.add(details);

        while (res.next()){
            Label productName  = new Label();
            Label productPrice  = new Label();
            Label productID  = new Label();
            Button buy = new Button();
            HBox productDetails = new HBox();

            productName.setMinWidth(70);
            productID.setMinWidth(60);
            productPrice.setMinWidth(60);
            buy.setText("Buy");

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(HelloApplication.emailId.equals(""))
                    {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("Please LOGIN to Place an Order!");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }
                    else {
                    Order place = new Order();
                        try {
                            place.placeOrder(productID.getText());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        System.out.println("Item Bought");
                    }
                }
            });

            productName.setText(res.getString("productName"));
            productPrice.setText(res.getString("price"));
            productID.setText(res.getString("productID"));

            productDetails.getChildren().addAll(productID, productName, productPrice, buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return products;

    }

}
