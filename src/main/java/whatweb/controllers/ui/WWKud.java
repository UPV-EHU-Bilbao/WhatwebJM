package whatweb.controllers.ui;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
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
    public void txertatu(String agindu){ //DATU BASEAN GORDE SCANERRAREN DATUAK
    //irakurri insertak
        // konpondu queryak
        //datu basean gorde

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
            if(System.getProperty("os.name").toLowerCase().contains("win")) {
                p = Runtime.getRuntime().exec
                        (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            } else {
                String exek= "whatweb --colour=never --log-sql=src/main/resources/insertak.txt "+urlId.getText() ;
                p = Runtime.getRuntime().exec(exek);
            }
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                processes.add(line);
            }
            BufferedReader reader = new BufferedReader(new FileReader( //fitxategia irakurtzen dugu
                    "src/main/resources/insertak.txt"));
            String sqlAgindu = reader.readLine();
            while (sqlAgindu != null) {
                txertatu(sqlAgindu); //linea bakoitza datu baseak exekutatzen dugu
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
        System.out.println("WhatWebKud instantzia");
    }
}
