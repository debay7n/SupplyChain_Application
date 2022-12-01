package com.example.supplychain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    public void hello(MouseEvent event) throws IOException {
        System.out.println("Hello is clicked");
    }
}