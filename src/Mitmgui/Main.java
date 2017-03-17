package Mitmgui;

import Mitmgui.Managers.PreferencesManager;
import Mitmgui.Managers.TrayIconManager;
import Mitmgui.UI.AlertHelper;
import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.SimpleWebServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            File file = Paths.get(new URL(getClass().getResource("web").toString()).toURI()).toFile();
            SimpleWebServer server = new SimpleWebServer("localhost", 22891, file, false);
            server.start();

            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = (Parent) loader.load();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, PreferencesManager.shared.getMainWidth(), PreferencesManager.shared.getMainHeight()));
            primaryStage.show();
            primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
            primaryStage.setOnCloseRequest(event -> {
                System.out.println("Stage is closing");
                System.exit(0);
            });
            MainController controller = (MainController) loader.getController();
            controller.setStageAndSetupListeners(primaryStage);

            new TrayIconManager();

        } catch (Exception e) {
            AlertHelper.exception("Error loading UI", "We could not load fxml UI for main", e);
            Logger.error(e);
        }

    }
}
