package com.example.supplychain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {
    @FXML
    Button loginbutton;
    @FXML
    Label email;
    @FXML
    TextField searchtext;
    @FXML
    Button logoutbutton;

    @FXML


    public void initialize(){
        if(!HelloApplication.emailId.equals("")){
            loginbutton.setOpacity(0);
            email.setText("Hello! "+HelloApplication.emailId);

        }
    }

    @FXML
    public void login(MouseEvent event) throws IOException {

        AnchorPane loginpage = FXMLLoader.load((getClass().getResource("LoginPage.fxml")));
        HelloApplication.root.getChildren().add(loginpage);

    }
    @FXML
    public void search(MouseEvent event) throws IOException, SQLException {

        header header = new header();

        ProductPage products = new ProductPage();
        AnchorPane productPane = new AnchorPane();
        productPane.getChildren().add(products.showProductsbyName(searchtext.getText()));
        productPane.setLayoutX(150);
        productPane.setLayoutY(130);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root, productPane);
    }

    @FXML
    public void logout(MouseEvent event) throws IOException, SQLException {

      if(logoutbutton.getOpacity()==0) {
          logoutbutton.setOpacity(1);
            logoutbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    HelloApplication.emailId ="";
                    logoutbutton.setOpacity(0);
                    try {
                        ProductPage products = new ProductPage();
                        header header = new header();

                        ListView<HBox> productList = products.showProducts();

                        AnchorPane productPane = new AnchorPane();
                        productPane.setLayoutX(150);
                        productPane.setLayoutY(130);
                        productPane.getChildren().add(productList);
                        HelloApplication.root.getChildren().clear();
                        HelloApplication.root.getChildren().addAll(header.root,productPane);
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }

                }
            });

      }
      else
          logoutbutton.setOpacity(0);

    }


    }


