package whatweb.controllers.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button serverId;

    @FXML
    private Button whatWebId;

    @FXML
    private StackPane stackPaneId;

    @FXML
    private AnchorPane cmsAPid;

    @FXML
    private AnchorPane serverAPId;
    @FXML
    private CMSKud cmsController;

    @FXML
    private WWKud whatwebController;

    @FXML
    private AnchorPane whatwebAPid;
    private App app;

    @FXML
    void cmsClick(ActionEvent event) throws MalformedURLException, SQLException, InterruptedException {

        if(event.getSource()==cmsButtonId){
            cmsAPid.toFront();
            cmsAPid.requestFocus();
        }
        else if(event.getSource()==whatWebId) {
            whatwebAPid.toFront();
            whatwebAPid.requestFocus();
            app.cmsTablaEguneratu();
        }else if (event.getSource()==serverId){
            serverAPId.toFront();
            serverAPId.requestFocus();
        }
    }

    @FXML
    void serverClick(ActionEvent event) throws MalformedURLException, SQLException {
        if(event.getSource()==cmsButtonId){
            cmsAPid.toFront();
            cmsAPid.requestFocus();
        }
        else if(event.getSource()==whatWebId) {
            whatwebAPid.toFront();
            whatwebAPid.requestFocus();
            app.cmsTablaEguneratu();
        }else if (event.getSource()==serverId){
            serverAPId.toFront();
            serverAPId.requestFocus();
        }
    }

    @FXML
    void whatWebClick(ActionEvent event) throws MalformedURLException, SQLException {
        whatwebAPid.toFront();
    }

    public void setMainApp(App a) {
        app = a;
    }


    public MainKud(App app) {
        System.out.println("MainKud instantziatu");
    }

    @FXML
    void initialize() throws SQLException, MalformedURLException {
       cmsAPid.toFront();
    }


}
