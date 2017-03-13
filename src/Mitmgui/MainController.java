package Mitmgui;

import Mitmgui.Managers.FlowsManager;
import Mitmgui.Managers.PreferencesManager;
import Mitmgui.Network.UpdatesSocketHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    Stage stage;
    UpdatesSocketHandler socketHandler;
    public MainController() {

    }

    @FXML protected TableView flowTable;

    @FXML
    protected void initialize(){
        populateFlowTable();

    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Implementing the Initializable interface means that this method
        // will be called when the controller instance is created
        socketHandler = new UpdatesSocketHandler();
        socketHandler.connect();

    }

    public void setStageAndSetupListeners(Stage stage) {

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            PreferencesManager.shared.setMainWidth(newVal.intValue());
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            PreferencesManager.shared.setMainHeight(newVal.intValue());
        });
    }

    public void populateFlowTable(){

        TableColumn pathCol = new TableColumn("Path");
        TableColumn methodCol = new TableColumn("Method");
        TableColumn statusCol = new TableColumn("Status");
        TableColumn sizeCol = new TableColumn("Size");
        TableColumn timeCol = new TableColumn("Time");

        flowTable.getColumns().removeAll();
        flowTable.getColumns().addAll(pathCol, methodCol, statusCol, sizeCol, timeCol);
        flowTable.setItems(FlowsManager.shared.getData());

    }

}
