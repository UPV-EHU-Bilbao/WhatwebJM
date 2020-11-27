/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package whatweb;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import whatweb.controllers.db.DBKud;
import whatweb.controllers.db.OrrialdeaKud;
import whatweb.controllers.ui.*;
import whatweb.model.Orrialde;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

import static javafx.application.Application.launch;

public class App extends Application {

    private Parent mainUI;
    private Parent nabUI;

    private Stage stage;
    private Stage stageNab;

    private MainKud mainKud;
    private WWKud whatWebKud;
    private CMSKud cmsKud;
    private ServerKud serverKud;
    private NabKud nabKud;

    private Scene mainScene;


    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        pantailakKargatu();
        mainScene = new Scene(mainUI,1000,732);
        stage.initStyle(StageStyle.UNDECORATED);
        hasieraKargatu();

    }

    private void pantailakKargatu() throws IOException {

        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/Main.fxml"));

        mainKud = new MainKud(this); //  setMain() metodoa ekidituz
        whatWebKud = new WWKud();
        cmsKud = new CMSKud(this);
        serverKud= new ServerKud();
        nabKud = new NabKud(this);

        Callback<Class<?>, Object> controllerFactory = type -> {
            if (type == MainKud.class) {
                return mainKud ;
            } else if (type == WWKud.class) {
                return whatWebKud;
            } else if (type == CMSKud.class) {
                return cmsKud;
            } else if (type == ServerKud.class) {
                return serverKud;
            } else if (type == NabKud.class){
                return nabKud;
            }
            else {
                // default behavior for controllerFactory:
                try {
                    return type.newInstance();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    throw new RuntimeException(exc); // fatal, just bail...
                }
            }
        };

        loaderMain.setControllerFactory(controllerFactory);
        mainUI =  (Parent) loaderMain.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void hasieraKargatu(){
        stage.setTitle("What Web");
        stage.setScene(mainScene);
        stage.show();
    }

    public void cmsTablaEguneratu() throws MalformedURLException, SQLException {
        cmsKud.kargatu();
    }

    public void erakNab(String helbidea) {
        nabKud.setHelbidea(helbidea);
        mainKud.nabigatzaileaErakutsi();
    }
    public void cmsErakutsi(){
        mainKud.cmsErakusti();
    }

    public void serverErakutsi() {
        cmsKud.serverErakutsi();
    }
}
