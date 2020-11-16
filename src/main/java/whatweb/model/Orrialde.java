package whatweb.model;

import java.net.URL;
import java.util.Date;

public class Orrialde {

    //Sartu printeatu nahi diren atributuak (uste dut taulan daudenak direla)

    private String cms;
    private String cmsVersion;
    private URL url;
    private String httpServer;
    private String country;
    private String email;
    private String ip;

    public Orrialde(String cms, String cmsVersion, URL url, String httpServer, String country, String email, String ip, Date lastUpdate) {
        this.cms = cms;
        this.cmsVersion = cmsVersion;
        this.url = url;
        this.httpServer = httpServer;
        this.country = country;
        this.email = email;
        this.ip = ip;
        this.lastUpdate = lastUpdate;
    }

    private Date lastUpdate; //Zalantzan

}
