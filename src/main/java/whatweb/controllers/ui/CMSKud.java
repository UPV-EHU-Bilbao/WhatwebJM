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

    public CMSKud() {
        System.out.println("CMSKud instantzia");
    }

    @FXML
    void initialize() {

    }
}
