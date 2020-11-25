package whatweb.controllers.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import whatweb.App;
import whatweb.controllers.db.OrrialdeaKud;
import whatweb.model.Orrialde;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

public class ServerKud {
    private ObservableList<Orrialde> lista;
    private App app;
    @FXML
    private TableView<Orrialde> taulaurl;

    @FXML
    private TableColumn<Orrialde, String> webzutabe;

    @FXML
    private Button Eguneratu;

    @FXML
    void onClickEguneratu(ActionEvent event) throws MalformedURLException, SQLException {
       kargatu();
    }

    public void setMainApp(App p) { app = p; }

    public ServerKud() {

    }
    @FXML
    void initialize() throws MalformedURLException, SQLException {
        webzutabe.setCellValueFactory(new PropertyValueFactory<>("url"));
        kargatu();
    }
    void kargatu() throws MalformedURLException, SQLException {
        OrrialdeaKud ok= OrrialdeaKud.getInstantzia();
        lista = ok.urlAkLortu();
        setLista(lista);
    }

    public void setLista(List<Orrialde> plista) throws MalformedURLException, SQLException {
        OrrialdeaKud ok= OrrialdeaKud.getInstantzia();
        lista = ok.urlAkLortu();
        lista= FXCollections.observableArrayList();
        lista.addAll(plista);
        taulaurl.setItems(lista);



    }
}
