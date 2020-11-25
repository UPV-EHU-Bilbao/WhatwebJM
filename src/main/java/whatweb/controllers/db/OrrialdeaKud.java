package whatweb.controllers.db;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import whatweb.controllers.db.DBKud;
import whatweb.model.Orrialde;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrrialdeaKud {

    //Singleton patroia

    private static final OrrialdeaKud instantzia = new OrrialdeaKud();

    public static OrrialdeaKud getInstantzia() {
        return instantzia;
    }

    private DBKud dbkud = DBKud.getInstantzia();

    private OrrialdeaKud() {
    }



    public Orrialde getInformazioa(String url) throws SQLException, MalformedURLException{
        ResultSet rs1, rs2;
        String targetidlortuquery = "select target_id,lastUpdate from targets where target='" + url + "'";
        rs1 = dbkud.execSQL(targetidlortuquery); //honekin target-aren id-a lortzen dugu
        Integer id = rs1.getInt("target_id");
        Orrialde o = new Orrialde();
        o.setLastUpdate(rs1.getString("lastUpdate"));
        String query = "select string from scans where (string like '%WordPress%' or string like '%Joomla%' or string like '%phpMyAdmin%'or string like '%Drupal%') and target_id="+id;
        rs2 = dbkud.execSQL(query);
        if(rs2.next()){  //badaude cms-rik erabiltzen ez duten orrialdeak
            String[] cms = rs2.getString("string").split(" ");
            o.setCms(cms[0]);
            o.setCmsVersion(cms[1]);
        }else{
            o.setCms("Ez da zehazten");
            o.setCmsVersion("-");
        }
        o.setUrl(url);

        return o;
    }

    public List<Orrialde> lortuOrrialdeak() throws SQLException, MalformedURLException{
        String targetlortu = "select target from targets";
        ResultSet rs;
        rs=dbkud.execSQL(targetlortu);

        List<Orrialde> emaitza = new ArrayList<Orrialde>();
        while(rs.next()){
            String url= rs.getString("target");
            emaitza.add(getInformazioa(url));
        }
        return emaitza;
    }

    public void ezabatuHelbidea(String helbidea) throws SQLException {
        String eskaera = "select target_id from targets where target =  '"+helbidea.toString()+"'";
        ResultSet rs = dbkud.execSQL(eskaera);
        Integer targetId = rs.getInt("target_id");
        String delete1= "delete from scans where target_id = "+targetId;
        String delete2= "delete from targets where target_id = "+targetId;
        dbkud.execSQL(delete1);
        dbkud.execSQL(delete2);

    }

    public void txertatuDatuak(String replace) {
        dbkud.execSQL(replace);
    }

    public ObservableList<Orrialde> urlAkLortu() throws SQLException, MalformedURLException {
        String query= "select target from targets ";
        ResultSet rs= dbkud.execSQL(query);
        ObservableList<Orrialde> emaitza= FXCollections.observableArrayList();
        while(rs.next()){
            String targeta= rs.getString("target");
            Orrialde o= new Orrialde();
            o.setUrl(targeta);
             emaitza.add(o);
        }
        return emaitza;
    }

    public List<Orrialde> bilatuOrrialdeak(String zerBilatu, String bilaketa) throws SQLException, MalformedURLException {
        String targetlortu = "select target,string,lastUpdate from targets join scans on targets.target_id=scans.target_id where targets.status=200 and scans.string like '%"+bilaketa+"%';";
        ResultSet rs;
        rs=dbkud.execSQL(targetlortu);

        List<Orrialde> emaitza = new ArrayList<Orrialde>();

        if(!bilaketa.isEmpty()){
            String cmsGabekoak="";
            switch(zerBilatu){
                case "CMS":
                    targetlortu= "select target,string,lastUpdate from targets join scans on targets.target_id=scans.target_id where targets.status=200 and scans.string like '%"+bilaketa+"%'" +
                            " and scans.string like '%WordPress%' or string like '%Joomla%' or string like '%phpMyAdmin%'or string like '%Drupal%'";
                    break;
                case "CMS Bertsioa": //CMS-ren berdina da izan ere datu basean datuak String bakar batean gordetzen dira, e.g.,"WordPress 5.4.2"
                    targetlortu= "select target,string,lastUpdate from targets join scans on targets.target_id=scans.target_id where targets.status=200 and scans.string like '%"+bilaketa+"%'" +
                            " and scans.string like '%WordPress%' or string like '%Joomla%' or string like '%phpMyAdmin%'or string like '%Drupal%'";
                    break;
                case "URL": //bi bilaketa egin behar dira, CMS dutenak eta CMS ez dutenak
                    targetlortu= "select target,string,lastUpdate from targets join scans on targets.target_id=scans.target_id where targets.status=200 and targets.target like '%"+bilaketa+"%'" +
                            " and scans.string like '%WordPress%' or string like '%Joomla%' or string like '%phpMyAdmin%'or string like '%Drupal%'";
                    cmsGabekoak = "select target,string,lastUpdate from targets join scans on targets.target_id=scans.target_id where targets.status=200 and targets.target like '%"+bilaketa+"%' group by scans.target_id";
                    break;
                default:
                    targetlortu= "select target,string,lastUpdate from targets join scans on targets.target_id=scans.target_id where targets.status=200 and scans.string like '%"+bilaketa+"%'" +
                            " and scans.string like '%WordPress%' or string like '%Joomla%' or string like '%phpMyAdmin%'or string like '%Drupal%'";
                    break;
            }
            ResultSet rs1;
            rs=dbkud.execSQL(targetlortu);

            while(rs.next()){
                String url= rs.getString("target");
                emaitza.add(getInformazioa(url));
            }
            if(zerBilatu=="URL"){ //url-ak bilatzen bagaude, CMS gabeko orriak bilatuko ditugu bebai
                rs1=dbkud.execSQL(cmsGabekoak);
                while(rs1.next()){
                    String url= rs1.getString("target");
                    emaitza.add(getInformazioa(url));
                }
            }
        }else{ //bilaketa hutsa badago, webgune guztiak lortuko ditugu
            emaitza= lortuOrrialdeak();
        }
        return emaitza;
    }

    public String idLortu() throws SQLException {
        String lortuId= "select target_id from targets order by target_id desc limit 1";
        ResultSet rs= dbkud.execSQL(lortuId);
        String id = rs.getString("target_id");
        return id;

    }
}