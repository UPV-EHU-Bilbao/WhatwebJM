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
            //Datu basean atributu bakoitzak duen izena jarri (Ez dakit ondo dauden)
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

    public String getInformazioa(String url) throws SQLException {
        ResultSet rs1, rs2;
        String targetidlortuquery= "select target_id from targets where target='"+url+"' and status=200";
        rs1=dbkud.execSQL(targetidlortuquery); //honekin target-aren id-a lortzen dugu

        String stringkontatuquery ="select count(*) as zenbat from scans where target_id="+rs1.getInt("target_id");
        rs2=dbkud.execSQL(stringkontatuquery); //honekin id berdinarekin(beraz, web berdinaren) zenbat string dauden lortzen dugu
        int countString= rs2.getInt("zenbat");
        //honen ondoren string horiek nola desberdingu ikusi behar dugu.


        String emaitza="";
        return emaitza;
    }

}
