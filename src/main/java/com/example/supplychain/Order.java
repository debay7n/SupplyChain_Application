package com.example.supplychain;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {

    void placeOrder(String productID) throws SQLException {
        ResultSet res =HelloApplication.connection.executeQuery("Select max(orderID) from orders");
        int orderID = 0;
        if(res.next())
            orderID = res.getInt("max(orderID)") + 1;

        String query = String.format("Insert into orders values(%s, %s, '%s')",orderID, productID,HelloApplication.emailId);
        int response = HelloApplication.connection.executeUpdate(query);
        if(response>0) {
            System.out.println("ORDER IS PLACED");

            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("ORDER HAS BEEN PLACED");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }

}
