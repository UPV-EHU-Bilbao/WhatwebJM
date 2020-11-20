package whatweb.controllers.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import whatweb.App;
import whatweb.controllers.db.OrrialdeaKud;
import whatweb.model.Orrialde;

public class CMSKud {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Orrialde> tableId;

    @FXML
    private TableColumn<Orrialde, URL> urlId;

    @FXML
    private TableColumn<Orrialde, String> httpServerId;

    @FXML
    private TableColumn<Orrialde, String> countryID;

    @FXML
    private TableColumn<Orrialde, String> emailId;

    @FXML
    private TableColumn<Orrialde, String> ipId;

    @FXML
    private TableColumn<Orrialde, String> cmsId;

    @FXML
    private ComboBox<String> comboBoxId;

    @FXML
    private Button addURLID;

    @FXML
    private TextField urlField;

    @FXML
    private TableColumn<Orrialde, String> cmsVersionId;

    private List<Orrialde> lista = new ArrayList<>();
    private App app;

    @FXML
    void urlGehituClick(ActionEvent event) throws SQLException {

    }


    @FXML
    void initialize() {
        //Zutabeak ez erakutsi
        emailId.setVisible(false);
        ipId.setVisible(false);
        countryID.setVisible(false);
        httpServerId.setVisible(false);

        //Errenkadak balioak hartu
        urlId.setCellValueFactory(new PropertyValueFactory<>("url"));
        httpServerId.setCellValueFactory(new PropertyValueFactory<>("httpServer"));
        countryID.setCellValueFactory(new PropertyValueFactory<>("country"));
        emailId.setCellValueFactory(new PropertyValueFactory<>("email"));
        ipId.setCellValueFactory(new PropertyValueFactory<>("ip"));
        cmsId.setCellValueFactory(new PropertyValueFactory<>("cms"));
        cmsVersionId.setCellValueFactory(new PropertyValueFactory<>("cmsVersion"));


    }

    public void setLista(List<Orrialde> lista) {
        this.lista = lista;
    }

    public void setMainApp(App a) {
        app = a;
    }
}
