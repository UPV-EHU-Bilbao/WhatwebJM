package whatweb.controllers.ui;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
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
        DBKud dbkud = DBKud.getInstantzia();
        //dbkud.execSQL(agindu); //ya funciona exeqSQL
        System.out.println(agindu);
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
                        (System.getenv("windir") +"\\system32\\"+"wls whatweb --colour=never --log-sql=src/main/resources/insertak.txt "+urlId.getText());
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
            int id = lortuID();
            boolean insertTarget= true;
            while (sqlAgindu != null) {
                if(insertTarget){ //lehenengo linea denez, target-aren insert a solik baten egingo da eta lehen komandoa izango da
                    insertTarget=false;
                    txertatu(sqlAgindu.replace("IGNORE ", "").replace("targets (status,", "targets (target_id, status,").replace("VALUES ('","VALUES ("+id+",'")); //linea bakoitza datu baseak exekutatzen dugu
                }else{
                    txertatu(sqlAgindu);
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

    private int lortuID() throws SQLException { //este metodo tendria que ir en otra clase
        String query = "select target_id from targets order by target_id DESC limit 1";
        ResultSet rs = DBKud.getInstantzia().execSQL(query);
        if(rs.next()){
            Integer id = rs.getInt("target_id") +1;
            return id;
        }else return 0;
    }

    public WWKud() {
        System.out.println("WhatWebKud instantzia");
    }
}
