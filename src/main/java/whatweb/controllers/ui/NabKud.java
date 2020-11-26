package whatweb.controllers.ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import org.w3c.dom.events.MouseEvent;
import whatweb.App;
import whatweb.model.Orrialde;

public class NabKud {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FontAwesomeIconView cromoId;

    @FXML
    private FontAwesomeIconView morcillaId;


    @FXML
    private Label cromoLabel;

    @FXML
    private Label morcillaLabel;

    private String url;
    private Boolean nab = false;
    private App app;
    @FXML
    void initialize(){

    }
    @FXML
    void cromoClonck() {
        nab =true;
        urlIrakurri();
    }

    @FXML
    void morcillaClonck() {
        urlIrakurri();
    }

    public NabKud(App a){
        this.app = a;
    }

    public void urlIrakurri() {
        List<String> processes = new LinkedList<String>();
        try {
            String line;
            Process p = null;
            String exek = "";
            if(!nab){
                exek = "firefox " + url;
            }
            else{
                exek = "google-chrome " + url;
            }
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                exek = "wsl "+exek;
            }

            p = Runtime.getRuntime().exec(exek);
            app.cmsErakutsi();

            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                processes.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setHelbidea(String helbidea) {
        this.url = helbidea;
    }
}