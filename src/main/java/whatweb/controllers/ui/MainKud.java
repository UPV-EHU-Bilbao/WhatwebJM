package whatweb.controllers.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import whatweb.App;
import whatweb.controllers.db.OrrialdeaKud;
import whatweb.model.Orrialde;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainKud {

    @FXML
    private Button cmsButtonId;

    @FXML
    private Button serverButtonId;

    @FXML
    private Button whatWebId;

    @FXML
    private StackPane stackPaneId;

    @FXML
    private AnchorPane cmsAPid;

    @FXML
    private AnchorPane serverAPId;

    @FXML
    private Label xLabel;


    @FXML
    private AnchorPane whatwebAPid;

    @FXML
    private AnchorPane nabigatzaileaId;

    @FXML
    private AnchorPane irudiaPane;

    private App app;

    @FXML
    void cmsClick(ActionEvent event) throws MalformedURLException, SQLException, InterruptedException {

        cmsAPid.toFront();
        cmsAPid.requestFocus();
        app.cmsTablaEguneratu();

    }

    @FXML
    void serverClick(ActionEvent event) throws MalformedURLException, SQLException {
        cmsAPid.toFront();
        cmsAPid.requestFocus();
        app.serverErakutsi();
    }

    @FXML
    void whatWebClick(ActionEvent event) throws MalformedURLException, SQLException {
        whatwebAPid.toFront();
        whatwebAPid.requestFocus();
    }

    @FXML
    void ateraClick(){
        System.exit(0);
    }



    public MainKud(App pApp) {
        this.app=pApp;
    }

    @FXML
    void initialize() {
        cmsAPid.toFront();
    }


    public void nabigatzaileaErakutsi() {
        nabigatzaileaId.toFront();
        nabigatzaileaId.requestFocus();
    }

    public void cmsErakusti() {
        cmsAPid.toFront();
    }

    public void irudiaErakutsi() {
        irudiaPane.toFront();
    }
}
