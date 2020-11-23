package whatweb.controllers.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
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
    private TableColumn<Orrialde, String> cmsVersionId;


    @FXML
    private ComboBox<String> comboBoxId;

    @FXML
    private Button addURLID;

    @FXML
    private TextField urlField;

    @FXML
    private TableColumn<Orrialde, String> ezabatuId;

    private ObservableList<Orrialde> lista;
    private App app;
    private OrrialdeaKud ok = OrrialdeaKud.getInstantzia();


    @FXML
    void urlGehituClick(ActionEvent event) throws SQLException, MalformedURLException {
        kargatu();
    }

    public CMSKud() {
        System.out.println("CMSKud instantzia");
    }


    @FXML
    void initialize() throws MalformedURLException, SQLException {
        emailId.setVisible(false);
        ipId.setVisible(false);
        countryID.setVisible(false);
        httpServerId.setVisible(false);
        cmsId.setCellValueFactory(new PropertyValueFactory<>("cms"));
        urlId.setCellValueFactory(new PropertyValueFactory<>("url"));
        cmsVersionId.setCellValueFactory(new PropertyValueFactory<>("cmsVersion"));
        ezabatuId.setCellValueFactory(new PropertyValueFactory<>("ezabatu"));

        Callback<TableColumn<Orrialde, String>, TableCell<Orrialde, String>> defaultTextFieldCellFactory = TextFieldTableCell.<Orrialde>forTableColumn();

        ezabatuId.setCellFactory(col -> {
            TableCell<Orrialde, String> cell = defaultTextFieldCellFactory.call(col);

            cell.setOnMouseClicked(event -> {
                if (! cell.isEmpty()) {
                    URL helbidea = cell.getTableView().getSelectionModel().getSelectedItem().getUrl();
                    try {
                        ezabatuHelbidea(helbidea);
                        kargatu();
                    } catch (SQLException | MalformedURLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            });

            return cell ;
        });

        kargatu();

    }

    public void kargatu() throws MalformedURLException, SQLException {
        tableId.getItems().clear();
        OrrialdeaKud orkud= OrrialdeaKud.getInstantzia();
        List<Orrialde> orrialdeak= orkud.lortuOrrialdeak(); //orrialdeak ditugu
        setLista(orrialdeak);

    }

    private void ezabatuHelbidea(URL helbidea) throws SQLException {
        ok.ezabatuHelbidea(helbidea);
    }

    public void setLista(List<Orrialde> plista) {
        lista= FXCollections.observableArrayList();
        lista.addAll(plista);
        tableId.setItems(lista);

    }

    public void setMainApp(App a) {
        app = a;
    }
}
