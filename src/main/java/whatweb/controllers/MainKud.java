package whatweb.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private ComboBox<?> comboBoxId;

    @FXML
    private Button addURLId;

    @FXML
    private TableView<?> tableId;

    @FXML
    private TableColumn<?, ?> urlId;

    @FXML
    private TableColumn<?, ?> httpServerId;

    @FXML
    private TableColumn<?, ?> conutryId;

    @FXML
    private TableColumn<?, ?> emailId;

    @FXML
    private TableColumn<?, ?> ipId;

    @FXML
    private TableColumn<?, ?> cmsVersionId;

    @FXML
    private TableColumn<?, ?> lastUpdateId;

    @FXML
    void addCick(ActionEvent event) {

    }

    @FXML
    void cmsClick(ActionEvent event) {

    }

    @FXML
    void serverClick(ActionEvent event) {

    }

    @FXML
    void whatWebClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert cmsId != null : "fx:id=\"cmsId\" was not injected: check your FXML file 'Main.fxml'.";
        assert serverId != null : "fx:id=\"serverId\" was not injected: check your FXML file 'Main.fxml'.";
        assert whatWebId != null : "fx:id=\"whatWebId\" was not injected: check your FXML file 'Main.fxml'.";
        assert urlField != null : "fx:id=\"urlField\" was not injected: check your FXML file 'Main.fxml'.";
        assert comboBoxId != null : "fx:id=\"comboBoxId\" was not injected: check your FXML file 'Main.fxml'.";
        assert addURLId != null : "fx:id=\"addURLId\" was not injected: check your FXML file 'Main.fxml'.";
        assert tableId != null : "fx:id=\"tableId\" was not injected: check your FXML file 'Main.fxml'.";
        assert urlId != null : "fx:id=\"urlId\" was not injected: check your FXML file 'Main.fxml'.";
        assert httpServerId != null : "fx:id=\"httpServerId\" was not injected: check your FXML file 'Main.fxml'.";
        assert conutryId != null : "fx:id=\"conutryId\" was not injected: check your FXML file 'Main.fxml'.";
        assert emailId != null : "fx:id=\"emailId\" was not injected: check your FXML file 'Main.fxml'.";
        assert ipId != null : "fx:id=\"ipId\" was not injected: check your FXML file 'Main.fxml'.";
        assert cmsVersionId != null : "fx:id=\"cmsVersionId\" was not injected: check your FXML file 'Main.fxml'.";
        assert lastUpdateId != null : "fx:id=\"lastUpdateId\" was not injected: check your FXML file 'Main.fxml'.";

    }
}
