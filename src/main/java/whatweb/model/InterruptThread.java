package whatweb.model;

import java.net.HttpURLConnection;
import java.net.URLConnection;

public class InterruptThread implements Runnable {

    HttpURLConnection con;
    public InterruptThread(URLConnection con) {
        this.con = (HttpURLConnection) con;
    }

    public void run() {
        try {
            Thread.sleep(5000); // or Thread.sleep(con.getConnectTimeout())
        } catch (InterruptedException e) {

        }
        con.disconnect();
        System.out.println("Timer thread forcing to quit connection");
    }
}