package whatweb.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import whatweb.App;

public class MainKud {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> cmsId;

    @FXML
    private Button serverId;

    @FXML
    private Button whatWebId;

    @FXML
    private TextField urlField;

    @FXML
    private ComboBox<String> comboBoxId;

    @FXML
    private Button addURLId;

    @FXML
    private TableView<?> tableId;

    @FXML
    private TableColumn<?, URL> urlId;

    @FXML
    private TableColumn<?, String> httpServerId;

    @FXML
    private TableColumn<?, String> conutryId;

    @FXML
    private TableColumn<?, String> emailId;

    @FXML
    private TableColumn<?, String> ipId;

    @FXML
    private TableColumn<?, String> cmsVersionId;

    @FXML
    private TableColumn<?, Date> lastUpdateId;

    private App app;

    @FXML
    void addCick(ActionEvent event) { //URL berri bat gehitu

    }

    @FXML
    void cmsClick(ActionEvent event) { //CMS botoia klikatu

    }

    @FXML
    void serverClick(ActionEvent event) { //Zerbitzari botoia klikatu

    }

    @FXML
    void whatWebClick(ActionEvent event) { //WhatWeb botoia klikatu

    }

    @FXML
    void urlGehituClick(ActionEvent event) {

    }


    @FXML
    void initialize() {
      comboBoxId.getItems().addAll("Bizkaia","Gipuzkoa","Araba");
    }

    public void setMainApp(App m) {
        app = m;
    }
}
