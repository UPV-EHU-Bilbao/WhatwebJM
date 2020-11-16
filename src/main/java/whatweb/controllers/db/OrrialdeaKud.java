package whatweb.controllers.db;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrrialdeaKud {

    //Singleton patroia

    private static final OrrialdeaKud instantzia = new OrrialdeaKud();

    private static OrrialdeaKud getInstantzia(){return instantzia;}

    private DBKud dbkud = DBKud.getInstantzia();

    private OrrialdeaKud(){}



    public List<?> getOrrialdeak() throws SQLException { //Datu basean dauden eskaera guztiak hartu
        String eskaera ;
        List<?> emaitza;
        ResultSet rs = dbkud.execSQL(eskaera);
        while (rs.next()) {
            //Behar diren datu guztiak eskuratu eta instantziak sortu
            emaitza.add(?);
        }
        return emaitza;
    }

}
