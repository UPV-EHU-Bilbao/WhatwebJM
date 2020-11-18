package whatweb.controllers.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import whatweb.App;

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
    private AnchorPane whatwebAPid;
    private App app;

    @FXML
    void cmsClick(ActionEvent event) {
        cmsAPid.toFront();
    }

    @FXML
    void serverClick(ActionEvent event) {

    }

    @FXML
    void whatWebClick(ActionEvent event) {
        whatwebAPid.toFront();
    }

    public void setMainApp(App a) {
        app = a;
    }


    @FXML
    void initialize() {
       cmsAPid.toFront();
    }
}
