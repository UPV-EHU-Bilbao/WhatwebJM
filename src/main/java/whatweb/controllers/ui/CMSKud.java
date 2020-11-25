package whatweb.controllers.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

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
    private TableColumn<Orrialde, String> urlId;

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
                    String helbidea = cell.getTableView().getSelectionModel().getSelectedItem().getUrl();
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

        urlId.setCellFactory(kol -> {
            TableCell<Orrialde, String> cell = defaultTextFieldCellFactory.call(kol);

            cell.setOnMouseClicked(event -> {
                if (! cell.isEmpty()) {
                    String helbidea = cell.getTableView().getSelectionModel().getSelectedItem().getUrl();
                    app.erakNab(helbidea);
                }
            });

            return cell ;
        });

        kargatu();
        setComboBoxa();

    }

    public CMSKud(App a){this.app = a;}

    public void kargatu() throws MalformedURLException, SQLException {
        tableId.getItems().clear();
        orrialdeak= ok.lortuOrrialdeak(); //orrialdeak ditugu
        setLista(orrialdeak);

    }

    private void ezabatuHelbidea(String helbidea) throws SQLException {
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



}
