package whatweb.controllers.ui;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import whatweb.App;
import whatweb.controllers.db.OrrialdeaKud;

public class WWKud {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cmsId;

    @FXML
    private Button serverId;

    @FXML
    private Button wwId;

    @FXML
    private TextField urlId;

    @FXML
    private Button eskaneatuFitxId;

    @FXML
    private TextArea logId;

    @FXML
    private Button eskaneatuId;

    @FXML
    private Button dbGordeId;

    private App app;

    @FXML
    private ImageView travoltaId;

    private OrrialdeaKud orkud= OrrialdeaKud.getInstantzia();


    @FXML
    void fileClick(ActionEvent event) throws IOException, SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT fitxategiak", "*.txt")
        );
        File aukeratua = fileChooser.showOpenDialog(eskaneatuFitxId.getScene().getWindow()); //zer sartu metodo honetan
        Reader targetReader = new FileReader(aukeratua);
        BufferedReader reader = new BufferedReader(targetReader);
        String lineaBerria = reader.readLine();

        while (lineaBerria != null) { //uste dut hariak/prozesuak erabili behar direla
            eskaneatuUrl(lineaBerria);
            lineaBerria= reader.readLine();

        }

    }

    @FXML
    void eskaneatuClick(ActionEvent event) {
        if(urlId.getText().equals("")){
            travoltaId.setVisible(true);
            logId.setWrapText(true);
            logId.setText("Mesedez, sartu URL bat");
        }
        else{
            travoltaId.setVisible(false);
            logId.setWrapText(true);
            logId.setText("Itxaron segundu bat mesedez");

            Thread taskThread = new Thread(() -> {
                String newLine = System.getProperty("line.separator");
                final StringBuilder emaitza = new StringBuilder();
                try {
                    allProcesses().forEach( line -> {
                        emaitza.append(line+newLine);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    logId.setText(emaitza.toString());
                });
            });
            taskThread.start();

        }

    }
    private void txertatu(String agindu) throws SQLException { //DATU BASEAN GORDE SCANERRAREN DATUAK
        //irakurri insertak
        // konpondu queryak
        //datu basean gorde
        agindu=agindu.replace(" IGNORE", " OR IGNORE");
        orkud.txertatuDatuak(agindu);
        String id= orkud.idLortu();
        if(agindu.toLowerCase().contains("targets")){
            String data = "update targets set lastUpdate=DATE() where target_id='"+id+"';"; //en vez de la url hay que cambiarlo por la id de la ultima insertada
            orkud.txertatuDatuak(data);
        }
    }


    @FXML
    void initialize() {
        travoltaId.setVisible(false);


    }

    public void setMainApp(App p) { app = p; }

    public List<String> allProcesses() throws IOException {
        List<String> processes;
        processes=eskaneatuUrl(urlId.getText());
        return processes;
    }

    private List<String> eskaneatuUrl(String text) throws IOException {
        List<String> processes = new LinkedList<String>();
        Properties properties=new Properties();
        InputStream in= null;

        in = this.getClass().getResourceAsStream("/setup.properties");
        properties.load(in);
        try {
            String line;
            Process p;
            String exek= "./whatweb --colour=never --log-sql="+properties.getProperty("filepath")+"insertak.txt "+text;

            if(System.getProperty("os.name").toLowerCase().contains("win")) {
                exek = "wsl"+exek;
            }

            System.out.println(exek);
            p = Runtime.getRuntime().exec(exek,null, new File("/opt/WhatWeb"));

            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                processes.add(line);
            }

            BufferedReader reader = new BufferedReader(new FileReader( //fitxategia irakurtzen dugu
                   properties.getProperty("filepath")+"insertak.txt"));
            String sqlAgindu = reader.readLine();

            Boolean aurkitua = false;
            while (sqlAgindu != null) {
                if(aurkitua){
                    System.out.println(sqlAgindu);
                    txertatu(sqlAgindu); //linea bakoitza datu baseak exekutatzen dugu
                }else{
                    if(sqlAgindu.contains("200")){
                        txertatu(sqlAgindu); //linea bakoitza datu baseak exekutatzen dugu
                        aurkitua=true;
                    }
                }
                sqlAgindu = reader.readLine();
            }
            Files.deleteIfExists( new File("/tmp/insertak.txt" ).toPath());
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return processes;
    }


    public WWKud() {

    }


}