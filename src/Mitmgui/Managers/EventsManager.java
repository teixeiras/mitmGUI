package Mitmgui.Managers;

import Mitmgui.Models.EventPackage;
import Mitmgui.Models.Events.EventsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.pmw.tinylog.Logger;

import java.util.ListIterator;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class EventsManager {
    public static EventsManager shared = new EventsManager();
    private static String CMD_UPDATE = "update";
    private static String CMD_ADD = "add";
    private final ObservableList<EventsModel> data = FXCollections.observableArrayList();
    ListView listView;

    public void setListView(ListView listView) {
        this.listView = listView;
    }

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
            } catch (Exception e) {
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
