package Mitmgui;

import Mitmgui.Managers.PreferencesManager;
import Mitmgui.Managers.TrayIconManager;
import Mitmgui.UI.AlertHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
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
