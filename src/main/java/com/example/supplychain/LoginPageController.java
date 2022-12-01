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

public class LoginPageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void loginn(MouseEvent event) throws SQLException, IOException {
        String query = String.format("Select * from user where emailID = '%s' AND pass = '%s'", email.getText(), password.getText());
        ResultSet res = HelloApplication.connection.executeQuery(query);

        if(res.next()){
            String userType = res.getString("userType");
            HelloApplication.emailId = res.getString("emailID");

            if(userType.equals("buyer")){
                System.out.println("Logged in as Buyer");
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
            else {
                AnchorPane sellerpage = FXMLLoader.load((getClass().getResource("SellerPage.fxml")));
                HelloApplication.root.getChildren().add(sellerpage);

            }
        }
        else {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Login failed, Please Try Again!!");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }

    public void back(MouseEvent event) throws IOException{
        AnchorPane homepage = FXMLLoader.load((getClass().getResource("header.fxml")));
        HelloApplication.root.getChildren().add(homepage);

    }
}
