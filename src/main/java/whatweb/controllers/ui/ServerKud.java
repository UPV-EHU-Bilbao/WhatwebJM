package whatweb.controllers.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import whatweb.App;
import whatweb.model.Orrialde;

public class ServerKud {

    private App app;
    @FXML
    private TableView<Orrialde> taulaurl;

    @FXML
    private TableColumn<Orrialde, String> webzutabe;

    @FXML
    private Button Eguneratu;

    @FXML
    void onClickEguneratu(ActionEvent event) {

    }

    public void setMainApp(App p) { app = p; }

    public ServerKud() {
        System.out.println("ServerKud instantzia");
    }
}
