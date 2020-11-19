package whatweb.controllers.db;


import whatweb.model.Orrialde;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrrialdeaKud {

    //Singleton patroia

    private static final OrrialdeaKud instantzia = new OrrialdeaKud();

    public static OrrialdeaKud getInstantzia(){return instantzia;}

    private DBKud dbkud = DBKud.getInstantzia();

    private OrrialdeaKud(){}



    public List<Orrialde> getOrrialdeak() throws SQLException { //Datu basean dauden eskaera guztiak hartu
        String eskaera = "Hemen eskaera sartu behar dugu";
        List<Orrialde> emaitza = new ArrayList<>();
        ResultSet rs = dbkud.execSQL(eskaera);
        while (rs.next()) {
            //Datu basean atrbitu bakoitzak duen izena jarri
            String cms = rs.getString("");
            String cmsVersion = rs.getString("");
            URL url = rs.getURL("");
            String httpServer = rs.getString("");
            String country = rs.getString("");
            String email = rs.getString("");
            String ip = rs.getString("");
            Date lastUpdate = rs.getDate("");

            Orrialde o = new Orrialde(cms,cmsVersion,url,httpServer,country,email,ip,lastUpdate);
            emaitza.add(o);
        }
        return emaitza;
    }

    public void txertatuDatuak(String query){
        dbkud.execSQL(query);

    }

}
