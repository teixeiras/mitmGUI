package Mitmgui;

import Mitmgui.Managers.PreferencesManager;
import Mitmgui.UI.AlertHelper;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.pmw.tinylog.Logger;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = (Parent)loader.load();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, PreferencesManager.shared.getMainWidth(), PreferencesManager.shared.getMainHeight()));
            primaryStage.show();
            MainController controller = (MainController)loader.getController();
            controller.setStageAndSetupListeners(primaryStage);


        } catch (Exception e) {
            AlertHelper.exception("Error loading UI", "We could not load fxml UI for main", e);
            Logger.error(e);
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
