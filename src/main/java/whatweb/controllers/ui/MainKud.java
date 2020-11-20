package whatweb.controllers.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import whatweb.App;
import whatweb.controllers.db.OrrialdeaKud;
import whatweb.model.Orrialde;

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


    public MainKud() {
        System.out.println("MainKud instantziatu");
    }

    @FXML
    void initialize() throws SQLException {
       cmsAPid.toFront();
        OrrialdeaKud orkud= OrrialdeaKud.getInstantzia();
        List<Orrialde> orrialdeak= new ArrayList<Orrialde>();
        orrialdeak=orkud.lortuOrrialdeak(); //orrialdeak ditugu
    }

    private void setLista(List<Orrialde> list){
        app.setLista(list);
    }
}
