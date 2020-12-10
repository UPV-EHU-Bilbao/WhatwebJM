package whatweb.controllers.db;

import whatweb.model.Orrialde;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class DBKud {
    Connection conn = null;

    private void conOpen(String dbpath) {
        try {
            fitxategiaSortu(dbpath);
           String pathprop= System.getProperty("user.home")+File.separator+dbpath;
            String url = "jdbc:sqlite:"+ pathprop ;
            conn = DriverManager.getConnection(url);

            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server " + e);
        }
    }

public void fitxategiaSortu(String dbpath) throws IOException {
    String pathprop= System.getProperty("user.home")+File.separator+dbpath;
    File fitx= new File(pathprop);
    if(!fitx.exists() || fitx.length()==0){
        fitx.createNewFile();
        sortuDB();
    }
    }

    private void conClose() {

        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        System.out.println("Database connection terminated");

    }

    private ResultSet query(Statement s, String query) {

        ResultSet rs = null;

        try {
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    // singleton patroia
    private static DBKud instantzia = new DBKud();

    private DBKud(){

        Properties properties = null;
        InputStream in = null;

        try {
            in = this.getClass().getResourceAsStream("/setup.properties");
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.conOpen(properties.getProperty("dbpath"));

    }

    public static DBKud getInstantzia() {
        return instantzia;
    }

    private List<String> sortuDB() throws IOException {
        List<String> processes = new LinkedList<String>();
        Properties properties=new Properties();
        InputStream in= null;
        in = this.getClass().getResourceAsStream("/setup.properties");
        properties.load(in);
        try {
            String line;
            Process p;

            String exek="";
            if(System.getProperty("os.name").toLowerCase().contains("win")) {
                exek = "wsl whatweb --colour=never --log-sql-create=/mnt/c"+properties.getProperty("dbfilepath")+"datubase.txt ";
                p = Runtime.getRuntime().exec(exek);
            }else{
                exek= "./whatweb --colour=never --log-sql-create=datubase.txt ikasten.io";
                p = Runtime.getRuntime().exec(exek,null, new File("/opt/WhatWeb"));
            }


            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                processes.add(line);
            }
            BufferedReader reader;
            if(System.getProperty("os.name").toLowerCase().contains("win")) {
                reader = new BufferedReader(new FileReader( //fitxategia irakurtzen dugu
                        "c:\\"+properties.getProperty("dbfilepath")+"datubase.txt"));
            }else{
                reader = new BufferedReader(new FileReader( "datubase.txt")); //fitxategia irakurtzen dugu

            }
            String sqlAgindu = reader.readLine();
            Boolean aurkitua = false;
            while (sqlAgindu != null) {
                if(aurkitua){
                    txertatu(sqlAgindu); //linea bakoitza datu baseak exekutatzen dugu
                }else{
                    if(sqlAgindu.contains("200")){

                        String lortuUrl = sqlAgindu.split("//")[1].split("/")[0].split("'\\)")[0];
                        txertatu(sqlAgindu); //linea bakoitza datu baseak exekutatzen dugu
                        aurkitua=true;
                    }
                }
                sqlAgindu = reader.readLine();
            }
            input.close();
            reader.close();
        } catch (Exception err) {
            err.printStackTrace();
        }


       Files.deleteIfExists( new File(properties.getProperty("dbfilepath")).toPath());
        return processes;
    }

    private void txertatu(String agindu) throws SQLException { //DATU BASEAN GORDE SCANERRAREN DATUAK
        //irakurri insertak
        // konpondu queryak
        //datu basean gorde
        agindu=agindu.replace(" IGNORE", " OR IGNORE");
        System.out.println(agindu);
        OrrialdeaKud orkud= OrrialdeaKud.getInstantzia();
        orkud.txertatuDatuak(agindu);

    }

    public ResultSet execSQL(String query) {
        int count = 0;
        Statement s = null;
        ResultSet rs = null;

        try {
            s = (Statement) conn.createStatement();
            if (query.toLowerCase().indexOf("select") == 0) {
                // select agindu bat
                rs = this.query(s, query);

            } else {
                // update, delete, create agindu bat
                count = s.executeUpdate(query);
                System.out.println(count + " rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }


}
