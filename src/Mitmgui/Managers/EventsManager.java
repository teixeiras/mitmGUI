package Mitmgui.Managers;

import Mitmgui.Main;
import Mitmgui.Models.EventPackage;
import Mitmgui.Models.Events.EventsModel;
import Mitmgui.Models.FlowPackage;
import Mitmgui.Models.Flows.FlowModel;
import Mitmgui.UI.FlowDetailsDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.util.Callback;
import org.pmw.tinylog.Logger;

import java.util.ListIterator;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class EventsManager {
    private static String CMD_UPDATE = "update";
    private static String CMD_ADD = "add";
    public static EventsManager shared = new EventsManager();
    ListView listView;

    public void setListView(ListView listView) {
        this.listView = listView;
    }


    private final ObservableList<EventsModel> data = FXCollections.observableArrayList();
    public void addEvent(EventPackage eventPackage) {
        if (eventPackage.getCmd().equals(CMD_ADD)) {
            this.data.add(eventPackage.getData());
        } else {
            int index = 0;
            boolean hasFound = false;
            try {
                for (ListIterator<EventsModel> it = data.listIterator(); it.hasNext(); index++) {
                    EventsModel model = it.next();
                    if (model.getId().equals(eventPackage.getData().getId())) {
                        data.set(index, eventPackage.getData());
                        hasFound = true;
                    }
                }
            }catch (Exception e) {
                Logger.error(e);
            }

            if (!hasFound) {
                this.data.add(eventPackage.getData());
            }
        }
        this.listView.refresh();

    }

    public ObservableList<EventsModel> getData() {
        return data;
    }
}
