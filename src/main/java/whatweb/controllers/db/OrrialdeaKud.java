package whatweb.controllers.db;


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
        o.setUrl(new URL(url));

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

    public void ezabatuHelbidea(URL helbidea) throws SQLException {
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

    public List<Orrialde> bilatuOrrialdeak(String zerBilatu, String bilaketa) throws SQLException, MalformedURLException {
        String targetlortu = "select target,string,lastUpdate from targets join scans on targets.target_id=scans.target_id where targets.status=200 and scans.string like '%"+bilaketa+"%';";
        ResultSet rs;
        rs=dbkud.execSQL(targetlortu);

        List<Orrialde> emaitza = new ArrayList<Orrialde>();
        while(rs.next()){
            String url= rs.getString("target");
            emaitza.add(getInformazioa(url));
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