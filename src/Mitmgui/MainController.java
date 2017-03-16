package Mitmgui;

import Mitmgui.Managers.AppManager;
import Mitmgui.Managers.FlowsManager;
import Mitmgui.Managers.PreferencesManager;
import Mitmgui.Models.Events.EventsModel;
import Mitmgui.Models.Flows.FlowModel;
import Mitmgui.Models.SettingsModel;
import Mitmgui.Network.FetchAPI;
import Mitmgui.Network.Requests.EventsRequests;
import Mitmgui.Network.Requests.FlowsRequests;
import Mitmgui.Network.Requests.SettingsRequest;
import Mitmgui.Network.UpdatesSocketHandler;
import Mitmgui.UI.AlertHelper;
import Mitmgui.UI.FlowDetailsDataSource;
import com.sun.tools.javac.comp.Flow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

import java.io.IOException;


public class MainController{
    Stage stage;
    UpdatesSocketHandler socketHandler;
    FlowDetailsDataSource datasource;

    public MainController() {

    }

    @FXML
    ListView<FlowDetailsDataSource.FlowDetailsRow> detailsListVew;

    @FXML
    private TableView flowTable;

    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void playAllAction(ActionEvent event) {
        try {
            new FetchAPI().resumeAll();
        } catch (IOException e) {
            AlertHelper.exception("Failed trying to resume", "The request resume has failed, try it again, later", e);
        }
    }

    @FXML
    private void downloadAction(ActionEvent event) {
        try {
            new FetchAPI().downloadDump();
        } catch (IOException e) {
            AlertHelper.exception("Failed trying to download", "The request dump has failed, try it again, later", e);
        }
    }

    @FXML
    private void clearAction(ActionEvent event) {
        try {
            new FetchAPI().clear();
            FlowsManager.shared.clear();
        } catch (IOException e) {
            AlertHelper.exception("Failed trying to clear Action", "The request clear has failed, try it again, later", e);
        }
    }

    @FXML
    private void stopAllAction(ActionEvent event) {
        try {
            new FetchAPI().killAll();
        } catch (IOException e) {
            AlertHelper.exception("Failed trying to stop Action", "The request stop has failed, try it again, later", e);
        }
    }

    @FXML
    private void installCertifcateDialog(ActionEvent event) {
        // Button was clicked, do something...
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
        webEngine.load("http://mitm.it/");
        HBox linkBox = new HBox(20);

        Label label = new Label();
        label.setText("http://mitm.it/");
        Image image = AppManager.getQRCode("http://mitm.it/");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        linkBox.getChildren().addAll(imageView, label);
        dialogVbox.getChildren().addAll(linkBox, webView);
        Scene dialogScene = new Scene(dialogVbox, 450, 600);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @FXML
    protected void initialize(){
        populateFlowTable();
        // Implementing the Initializable interface means that this method
        // will be called when the controller instance is created
        socketHandler = new UpdatesSocketHandler();
        socketHandler.connect();
        try {
            SettingsRequest sRequest = new SettingsRequest();
            SettingsModel settings = sRequest.getSettings();
        } catch (IOException e) {
            Logger.error(e);
        }

        try {
            FlowsRequests sRequest = new FlowsRequests ();
            FlowModel[] flows= sRequest.getFlows();
            for (FlowModel model : flows) {
                FlowsManager.shared.addFlow(model);
            }
            flowTable.refresh();
        } catch (IOException e) {
            Logger.error(e);
        }

        try {
            EventsRequests sRequest = new EventsRequests ();
            EventsModel[] events= sRequest.getEvents();
            Logger.debug(events);
        } catch (IOException e) {
            Logger.error(e);
        }
        datasource = new FlowDetailsDataSource(new FlowModel(),detailsListVew);

    };



    public void setStageAndSetupListeners(Stage stage) {
        this.stage = stage;
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            PreferencesManager.shared.setMainWidth(newVal.intValue());
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            PreferencesManager.shared.setMainHeight(newVal.intValue());
        });
    }

    public void populateFlowTable(){

        TableColumn pathCol = new TableColumn("Path");
        pathCol .setCellValueFactory(
                new PropertyValueFactory<FlowModel,String>("path")
        );

        TableColumn methodCol = new TableColumn("Method");
        methodCol .setCellValueFactory(
                new PropertyValueFactory<FlowModel,String>("method")
        );

        TableColumn statusCol = new TableColumn("Status");
        statusCol .setCellValueFactory(
                new PropertyValueFactory<FlowModel,String>("status")
        );

        TableColumn sizeCol = new TableColumn("Size");
        sizeCol .setCellValueFactory(
                new PropertyValueFactory<FlowModel,String>("size")
        );

        TableColumn timeCol = new TableColumn("Time");
        timeCol .setCellValueFactory(
                new PropertyValueFactory<FlowModel,String>("time")
        );

        flowTable.getColumns().addAll(pathCol, methodCol, statusCol, sizeCol, timeCol);

        FlowsManager.shared.setTable(flowTable);
        flowTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if(flowTable.getSelectionModel().getSelectedItem() != null)
                {
                    FlowModel model = (FlowModel) flowTable.getSelectionModel().getSelectedItem();
                    System.out.println("Selected Value" + model);
                }
            }
        });

    }

}
