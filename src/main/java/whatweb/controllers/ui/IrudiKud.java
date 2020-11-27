package whatweb.controllers.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IrudiKud {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView irudiaId;

    private Image irudia;

    @FXML
    void initialize() {
        irudiaId.setImage(irudia);
    }

    public void setImage(Image i) {irudia = i; }
}
