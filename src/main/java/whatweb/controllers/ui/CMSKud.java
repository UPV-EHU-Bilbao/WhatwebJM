package whatweb.controllers.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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

    @FXML
    private Button bilatuId;

    @FXML
    private TableColumn<Orrialde, Date> lastUpdateId;

    private ObservableList<Orrialde> lista;
    private App app;
    private OrrialdeaKud ok = OrrialdeaKud.getInstantzia();
    private List<Orrialde> orrialdeak;


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
        lastUpdateId.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));

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
        setComboBoxa();

    }

    public void kargatu() throws MalformedURLException, SQLException {
        tableId.getItems().clear();
        orrialdeak= ok.lortuOrrialdeak(); //orrialdeak ditugu
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

    @FXML
    void bilatuClick(ActionEvent event) throws MalformedURLException, SQLException {
        String zerBilatu = comboBoxId.getValue();
        String bilaketa = urlField.getText();
        System.out.println("comboBoxId "+zerBilatu);
        orrialdeak.clear();

        orrialdeak = ok.bilatuOrrialdeak(zerBilatu,bilaketa);
        setLista(orrialdeak);

    }

    void setComboBoxa(){
        ObservableList<String> aukerak = FXCollections.observableArrayList();
        aukerak.addAll("CMS","CMS Bertsioa", "URL");
        comboBoxId.setItems(aukerak);
        comboBoxId.getSelectionModel().selectFirst();
    }

    public void setMainApp(App a) {
        app = a;
    }
}
