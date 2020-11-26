package whatweb.controllers.ui;

import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import whatweb.App;
import whatweb.controllers.db.DBKud;
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
                allProcesses().forEach( line -> {
                    emaitza.append(line+newLine);
                });
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

    public List<String> allProcesses() {
        List<String> processes = new LinkedList<String>();
        try {
            String line;
            String line2;
            Process p = null;
            Process p2 = null;
            String exek= "./whatweb --colour=never --log-sql=insertak.txt "+urlId.getText() ;

            if(System.getProperty("os.name").toLowerCase().contains("win")) {
                exek = "wsl"+exek;
            }

            p = Runtime.getRuntime().exec(exek, null, new File("/opt/WhatWeb"));

            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                processes.add(line);
            }
            BufferedReader reader = new BufferedReader(new FileReader( //fitxategia irakurtzen dugu
                    "/home/ena/insertak.txt"));
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
            Runtime.getRuntime().exec( "rm src/main/resources/insertak.txt"); //sortutako fitxategia ezabatu
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }

        return processes;
    }

    public WWKud() {

    }


}
