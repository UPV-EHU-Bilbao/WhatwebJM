package whatweb.controllers.db;

import java.io.*;
import java.sql.*;
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
    if(!fitx.exists()){
        fitx.createNewFile();

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
