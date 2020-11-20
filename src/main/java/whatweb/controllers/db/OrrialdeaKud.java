package whatweb.controllers.db;


import whatweb.model.Orrialde;

import java.net.MalformedURLException;
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

    public Orrialde getInformazioa(String url) throws SQLException, MalformedURLException {
        ResultSet rs1, rs2;
        String targetidlortuquery = "select target_id from targets where target='" + url + "' and status=200";
        rs1 = dbkud.execSQL(targetidlortuquery); //honekin target-aren id-a lortzen dugu
        Integer id = rs1.getInt("target_id");
        Orrialde o = new Orrialde();
        String query = "select string from scans where (string like '%WordPress%' or string like '%Joomla%' or string like '%phpMyAdmin%'or string like '%Drupal%') and target_id="+id;
        rs2 = dbkud.execSQL(query);
        String[] cms = rs2.getString("string").split(" ");
        o.setUrl(new URL(url));
        o.setCms(cms[0]);
        o.setCmsVersion(cms[1]);
        return o;
    }

    public List<Orrialde> lortuOrrialdeak() throws SQLException {
        String targetlortu = "select target from targets where status=200";
        ResultSet rs;
        rs=dbkud.execSQL(targetlortu);

        List<Orrialde> emaitza = new ArrayList<Orrialde>();
        while(rs.next()){
            String url= rs.getString("target");
            emaitza.add(getInformazioa(url));
        }
        return emaitza;
    }
}
