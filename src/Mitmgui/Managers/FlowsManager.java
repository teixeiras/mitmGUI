package Mitmgui.Managers;

import Mitmgui.Models.FlowPackage;
import Mitmgui.Models.Flows.FlowModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.pmw.tinylog.Logger;

import java.util.ListIterator;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class FlowsManager {


    public static FlowsManager shared = new FlowsManager();
    private static String ADD = "FLOWS_ADD";
    private static String UPDATE = "FLOWS_UPDATE";
    private static String REMOVE = "FLOWS_REMOVE";
    private static String RECEIVE = "FLOWS_RECEIVE";
    private static String SELECT = "FLOWS_SELECT";
    private static String SET_FILTER = "FLOWS_SET_FILTER";
    private static String SET_SORT = "FLOWS_SET_SORT";
    private static String SET_HIGHLIGHT = "FLOWS_SET_HIGHLIGHT";
    private static String REQUEST_ACTION = "FLOWS_REQUEST_ACTION";
    private static String CMD_UPDATE = "update";
    private static String CMD_ADD = "add";
    private final ObservableList<FlowModel> data = FXCollections.observableArrayList();
    private TableView table;

    public ObservableList<FlowModel> getData() {
        return data;
    }

    public void addFlow(FlowModel flowPackage) {
        this.data.add(flowPackage);
    }

    public void addFlow(FlowPackage flowPackage) {
        if (flowPackage.getCmd().equals(CMD_ADD)) {
            this.data.add(flowPackage.getData());
        } else {
            int index = 0;
            boolean hasFound = false;
            try {
                for (ListIterator<FlowModel> it = data.listIterator(); it.hasNext(); index++) {
                    FlowModel model = it.next();
                    if (model.getId().equals(flowPackage.getData().getId())) {
                        data.set(index, flowPackage.getData());
                        hasFound = true;
                    }
                }
            } catch (Exception e) {
                Logger.error(e);
            }

            if (!hasFound) {
                this.data.add(flowPackage.getData());
            }
        }
        this.table.refresh();

    }


    public void setTable(TableView table) {
        this.table = table;
        table.setItems(FlowsManager.shared.getData());
    }

    public void clear() {
        data.clear();
    }
}
