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

    public void setCms(String cms) {
        this.cms = cms;
    }

    public void setCmsVersion(String cmsVersion) {
        this.cmsVersion = cmsVersion;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setHttpServer(String httpServer) {
        this.httpServer = httpServer;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCms() {
        return cms;
    }

    public String getCmsVersion() {
        return cmsVersion;
    }

    public URL getUrl() {
        return url;
    }

    public String getHttpServer() {
        return httpServer;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getIp() {
        return ip;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

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
