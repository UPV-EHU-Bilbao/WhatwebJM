package whatweb.controllers.ui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import whatweb.App;
import whatweb.model.Orrialde;

public class MainKud {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Orrialde, String> cmsId;

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
    private TableView<Orrialde> tableId;

    @FXML
    private TableColumn<Orrialde, URL> urlId;

    @FXML
    private TableColumn<Orrialde, String> httpServerId;

    @FXML
    private TableColumn<Orrialde, String> countryId;

    @FXML
    private TableColumn<Orrialde, String> emailId;

    @FXML
    private TableColumn<Orrialde, String> ipId;

    @FXML
    private TableColumn<Orrialde, String> cmsVersionId;

    @FXML
    private TableColumn<Orrialde, Date> lastUpdateId;

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
        app.whatWebErakutsi();
    }

    @FXML
    void urlGehituClick(ActionEvent event) {

    }


    @FXML
    void initialize() {
    }

    public void setMainApp(App m) {
        app = m;
    }
}
