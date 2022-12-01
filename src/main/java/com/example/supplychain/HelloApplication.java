package com.example.supplychain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;
    public static Group root;
    public static String emailId;

    @Override
    public void start(Stage stage) throws SQLException, IOException {
        emailId = "";
        connection = new DatabaseConnection();
        root = new Group();
        header head = new header();
        ProductPage products = new ProductPage();
        ListView<HBox> productList = products.showProducts();

        AnchorPane productPane = new AnchorPane();
        productPane.setLayoutX(150);
        productPane.setLayoutY(130);
        productPane.getChildren().add(productList);

        root.getChildren().addAll(head.root, productPane);


        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("SHOPiFY");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e ->{
            try {
                connection.con.close();
                System.out.println("CONNECTION CLOSED");
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}