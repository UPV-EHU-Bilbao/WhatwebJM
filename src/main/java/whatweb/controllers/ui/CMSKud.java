package whatweb.controllers.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.util.Callback;
import whatweb.App;
import whatweb.controllers.db.OrrialdeaKud;
import whatweb.model.Orrialde;

public class CMSKud {

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
    private TextField urlField;

    @FXML
    private TableColumn<Orrialde, Date> lastUpdateId;

    @FXML
    private Label wordpressLabel;

    @FXML
    private Label cmsLabel;

    private ObservableList<Orrialde> lista;
    private App app;
    private OrrialdeaKud ok = OrrialdeaKud.getInstantzia();
    private List<Orrialde> orrialdeak;

    private ContextMenu cm = new ContextMenu();

    private MenuItem m1 = new MenuItem("Ezabatu");
    private MenuItem m2 = new MenuItem("Orrialdea ireki");
    private MenuItem m3 = new MenuItem("Screenshoot bat atera");


    public CMSKud(App a) {
        this.app=a;
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
        lastUpdateId.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        emailId.setCellValueFactory(new PropertyValueFactory<>("email"));
        ipId.setCellValueFactory(new PropertyValueFactory<>("ip"));
        countryID.setCellValueFactory(new PropertyValueFactory<>("country"));
        httpServerId.setCellValueFactory(new PropertyValueFactory<>("httpServer"));

        Callback<TableColumn<Orrialde, String>, TableCell<Orrialde, String>> defaultTextFieldCellFactory = TextFieldTableCell.<Orrialde>forTableColumn();

        menuaKargatu();
        kargatu();
        setComboBoxa();

    }

    private void menuaKargatu() {

        m1.setOnAction(col -> { //Ezabatu
            String helbidea = tableId.getSelectionModel().getSelectedItem().getUrl();
            try {
                ezabatuHelbidea(helbidea);
            } catch (SQLException | MalformedURLException throwables) {
                throwables.printStackTrace();
            }
        });

        m2.setOnAction(col -> { //Orrialdea ireki
            String helbidea = tableId.getSelectionModel().getSelectedItem().getUrl();
            app.erakNab(helbidea);
        });

        m3.setOnAction( col -> { //Screenshot-a atera
            Thread task = new Thread(() -> {
                String helbidea = tableId.getSelectionModel().getSelectedItem().getUrl();
                try {
                    takeScreenshoot(helbidea);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            task.start();
        });


        cm.getItems().addAll(m1,m2,m3);
        tableId.setContextMenu(cm); //Taulan Context Menu txertatu
    }

    private void takeScreenshoot(String helbidea) throws IOException { //metodo hau berregin behar da
        URLConnection conn = new URL("http://jonander.xyz:3000/?page="+helbidea).openConnection(); //ez dakit beheko komando beharrezkoa den
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
        }
        String helbideZuzena=""; //string hauek ondo konpondu behar dira oraindik
        if(helbidea.contains("https://")){
            helbideZuzena=helbidea.split("https://")[1];
        }else if(helbidea.contains("http://")){
            helbideZuzena=helbidea.split("http://")[1];
        }
        if(helbideZuzena.charAt(helbideZuzena.length()-1)!='/'){
            helbideZuzena=helbideZuzena+'/';
        }
        System.out.println("http://jonander.xyz:3000/"+helbideZuzena+"kaptura.png");
        Image i = new Image("http://jonander.xyz:3000/"+helbideZuzena+"kaptura.png",800,790,false,false);
        app.irudiaErakutsi(i);
    }

    public void kargatu() throws MalformedURLException, SQLException {
        //emailId.setVisible(false);
        ipId.setVisible(false);
        //countryID.setVisible(false);
        httpServerId.setVisible(false);

        cmsId.setVisible(true);
        cmsVersionId.setVisible(true);

        cmsLabel.setText("CMS");
        wordpressLabel.setText("WordPress, Joomla, phpMyAdmin, Drupal");


        tableId.getItems().clear();
        orrialdeak= ok.lortuOrrialdeak(); //orrialdeak ditugu
        setLista(orrialdeak);

    }

    private void ezabatuHelbidea(String helbidea) throws SQLException, MalformedURLException {
        ok.ezabatuHelbidea(helbidea);
        kargatu();
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

    public void serverErakutsi() {

        emailId.setVisible(false);
        ipId.setVisible(true);
        countryID.setVisible(false);
        httpServerId.setVisible(true);

        cmsId.setVisible(false);
        cmsVersionId.setVisible(false);

        cmsLabel.setText("Server");
        wordpressLabel.setText("");

    }
}
