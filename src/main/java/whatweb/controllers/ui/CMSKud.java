package whatweb.controllers.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CMSKud {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> tableId;

    @FXML
    private TableColumn<?, ?> urlId;

    @FXML
    private TableColumn<?, ?> httpServerId;

    @FXML
    private TableColumn<?, ?> countryID;

    @FXML
    private TableColumn<?, ?> emailId;

    @FXML
    private TableColumn<?, ?> ipId;

    @FXML
    private TableColumn<?, ?> cmsId;

    @FXML
    private ComboBox<?> comboBoxId;

    @FXML
    private Button addURLID;

    @FXML
    private TextField urlField;

    @FXML
    void urlGehituClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert tableId != null : "fx:id=\"tableId\" was not injected: check your FXML file 'cms.fxml'.";
        assert urlId != null : "fx:id=\"urlId\" was not injected: check your FXML file 'cms.fxml'.";
        assert httpServerId != null : "fx:id=\"httpServerId\" was not injected: check your FXML file 'cms.fxml'.";
        assert countryID != null : "fx:id=\"countryID\" was not injected: check your FXML file 'cms.fxml'.";
        assert emailId != null : "fx:id=\"emailId\" was not injected: check your FXML file 'cms.fxml'.";
        assert ipId != null : "fx:id=\"ipId\" was not injected: check your FXML file 'cms.fxml'.";
        assert cmsId != null : "fx:id=\"cmsId\" was not injected: check your FXML file 'cms.fxml'.";
        assert comboBoxId != null : "fx:id=\"comboBoxId\" was not injected: check your FXML file 'cms.fxml'.";
        assert addURLID != null : "fx:id=\"addURLID\" was not injected: check your FXML file 'cms.fxml'.";
        assert urlField != null : "fx:id=\"urlField\" was not injected: check your FXML file 'cms.fxml'.";

    }
}
