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
import Mitmgui.UI.EventsDataSource;
import Mitmgui.UI.FlowDetailsDataSource;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import netscape.javascript.JSObject;
import org.pmw.tinylog.Logger;

import java.io.IOException;


public class MainController {
    Stage stage;
    UpdatesSocketHandler socketHandler;
    FlowDetailsDataSource datasource;
    EventsDataSource eventsDataSource;

    @FXML
    ListView<FlowDetailsDataSource.FlowDetailsRow> detailsListVew;

    @FXML
    private TableView flowTable;

    @FXML
    private VBox requestContainer;

    @FXML
    private VBox responseContainer;

    @FXML
    private ListView responseListView;

    @FXML
    private ListView requestListView;

    @FXML
    private WebView requestViewer;

    @FXML
    private WebView responseViewer;

    @FXML
    private ListView<EventsModel> eventsList;

    public MainController() {

    }

    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void playAllAction(ActionEvent event) {
        try {
            new FetchAPI().resumeAll();
        } catch (Exception e) {
            AlertHelper.exception("Failed trying to resume", "The request resume has failed, try it again, later", e);
        }
    }

    @FXML
    private void onDebugEventAction(ActionEvent event) {
        try {
            CheckBox chk = (CheckBox) event.getSource();
            if (chk.isSelected()) {
                eventsDataSource.addFilter(EventsDataSource.LVL_DEBUG);
            } else {
                eventsDataSource.removeFilter(EventsDataSource.LVL_DEBUG);
            }

        } catch (Exception e) {
            AlertHelper.exception("Failed to add filter ", "There was a technical fail on application", e);
        }
    }

    @FXML
    private void onInfoEventAction(ActionEvent event) {
        try {
            CheckBox chk = (CheckBox) event.getSource();
            if (chk.isSelected()) {
                eventsDataSource.addFilter(EventsDataSource.LVL_INFO);
            } else {
                eventsDataSource.removeFilter(EventsDataSource.LVL_INFO);
            }

        } catch (Exception e) {
            AlertHelper.exception("Failed to add filter ", "There was a technical fail on application", e);
        }
    }

    @FXML
    private void onWebEventAction(ActionEvent event) {
        try {
            CheckBox chk = (CheckBox) event.getSource();
            if (chk.isSelected()) {
                eventsDataSource.addFilter(EventsDataSource.LVL_WEB);
            } else {
                eventsDataSource.removeFilter(EventsDataSource.LVL_WEB);
            }

        } catch (Exception e) {
            AlertHelper.exception("Failed to add filter ", "There was a technical fail on application", e);
        }
    }

    @FXML
    private void downloadAction(ActionEvent event) {
        try {
            new FetchAPI().downloadDump();
        } catch (Exception e) {
            AlertHelper.exception("Failed trying to download", "The request dump has failed, try it again, later", e);
        }
    }

    @FXML
    private void clearAction(ActionEvent event) {
        try {
            new FetchAPI().clear();
            FlowsManager.shared.clear();
        } catch (Exception e) {
            AlertHelper.exception("Failed trying to clear Action", "The request clear has failed, try it again, later", e);
        }
    }

    @FXML
    private void clearEventsActions(ActionEvent event) {
        try {
            new FetchAPI().killAll();
        } catch (Exception e) {
            AlertHelper.exception("Failed trying to stop Action", "The request stop has failed, try it again, later", e);
        }
    }

    @FXML
    private void stopAllAction(ActionEvent event) {
        try {
            new FetchAPI().killAll();
        } catch (Exception e) {
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
    protected void initialize() {
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
            FlowsRequests sRequest = new FlowsRequests();
            FlowModel[] flows = sRequest.getFlows();
            for (FlowModel model : flows) {
                FlowsManager.shared.addFlow(model);
            }
            flowTable.refresh();
        } catch (IOException e) {
            Logger.error(e);
        }

        try {
            EventsRequests sRequest = new EventsRequests();
            EventsModel[] events = sRequest.getEvents();
            Logger.debug(events);
        } catch (IOException e) {
            Logger.error(e);
        }
        datasource = new FlowDetailsDataSource(new FlowModel(), detailsListVew);
        eventsDataSource = new EventsDataSource(eventsList);
        loadWebviews();

    }

    public void loadWebviews() {
        try {
            final WebEngine requestWebEngine = responseViewer.getEngine();
            final WebEngine responseWebEngine = requestViewer.getEngine();

            responseWebEngine.load("http://localhost:22891/index.html");
            requestWebEngine.load("http://localhost:22891/index.html");

            responseWebEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                @Override
                public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                    if (t1 == Worker.State.SUCCEEDED) {
                        JSObject window = (JSObject) responseWebEngine.executeScript("window");
                        window.setMember("app", new JavaApp());
                    }
                }
            });

            requestWebEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                @Override
                public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                    if (t1 == Worker.State.SUCCEEDED) {
                        JSObject window = (JSObject) requestWebEngine.executeScript("window");
                        window.setMember("app", new JavaApp());
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void setStageAndSetupListeners(Stage stage) {
        this.stage = stage;
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            PreferencesManager.shared.setMainWidth(newVal.intValue());
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            PreferencesManager.shared.setMainHeight(newVal.intValue());
        });
    }

    public void populateFlowTable() {

        TableColumn pathCol = new TableColumn("Path");
        pathCol.setCellValueFactory(
                new PropertyValueFactory<FlowModel, String>("path")
        );

        TableColumn methodCol = new TableColumn("Method");
        methodCol.setCellValueFactory(
                new PropertyValueFactory<FlowModel, String>("method")
        );

        TableColumn statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(
                new PropertyValueFactory<FlowModel, String>("status")
        );

        TableColumn sizeCol = new TableColumn("Size");
        sizeCol.setCellValueFactory(
                new PropertyValueFactory<FlowModel, String>("size")
        );

        TableColumn timeCol = new TableColumn("Time");
        timeCol.setCellValueFactory(
                new PropertyValueFactory<FlowModel, String>("time")
        );

        flowTable.getColumns().addAll(pathCol, methodCol, statusCol, sizeCol, timeCol);

        flowTable.setRowFactory(
                new Callback<TableView<FlowModel>, TableRow<FlowModel>>() {
                    @Override
                    public TableRow<FlowModel> call(TableView<FlowModel> tableView) {
                        final TableRow<FlowModel> row = new TableRow<>();
                        final ContextMenu rowMenu = new ContextMenu();
                        MenuItem editItem = new MenuItem("Edit");
                        //editItem.setOnAction(...);
                        MenuItem removeItem = new MenuItem("Delete");
                        removeItem.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                flowTable.getItems().remove(row.getItem());
                            }
                        });
                        rowMenu.getItems().addAll(editItem, removeItem);

                        // only display context menu for non-null items:
                        row.contextMenuProperty().bind(
                                Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                        .then(rowMenu)
                                        .otherwise((ContextMenu) null));
                        return row;
                    }
                });
        FlowsManager.shared.setTable(flowTable);
        flowTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (flowTable.getSelectionModel().getSelectedItem() != null) {
                    FlowModel model = (FlowModel) flowTable.getSelectionModel().getSelectedItem();
                    System.out.println("Selected Value" + model);
                }
            }
        });

    }


    public static class JavaApp {

        public void onClick() {
            System.out.println("Clicked");
        }
    }
}
