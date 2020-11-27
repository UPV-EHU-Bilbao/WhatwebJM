package whatweb.controllers.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import whatweb.App;

public class IrudiKud {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView irudiaId;

    private Image irudia;

    private App app;

    public IrudiKud(App a) { app = a;}

    @FXML
    void initialize() {

    }

    public void setImage(Image i) {
        irudia = i;
        irudiaId.setImage(irudia);
    }
}
