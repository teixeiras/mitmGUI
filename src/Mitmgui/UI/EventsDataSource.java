package Mitmgui.UI;

import Mitmgui.Main;
import Mitmgui.Managers.EventsManager;
import Mitmgui.Models.Events.EventsModel;
import Mitmgui.Models.Flows.FlowModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by teixeiras on 15/03/2017.
 */
public class EventsDataSource {
    ListView listView;

    public static String LVL_INFO = "info";
    public static String LVL_DEBUG = "debug";
    public static String LVL_WEB = "web";

    List<String> filters = new ArrayList();

    FilteredList<EventsModel> filteredData = new FilteredList<>(EventsManager.shared.getData(), s -> true);

    public void addFilter(String filter) {
        filters.add(filter);
        this.updateFilter();
    }

    public void removeFilter(String filter) {
        filters.remove(filter);
        this.updateFilter();
    }
    public void setListView(ListView listView) {
        this.listView = listView;
    }


    class EventsModelCell extends ListCell<EventsModel> {
        {
            setMaxWidth(Control.USE_PREF_SIZE);
            prefWidthProperty().bind(listView.widthProperty().subtract(2));
        }
        @Override
        public void updateItem(EventsModel item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null) {
                return;
            }
            this.setText(item.getMessage());


        }
    }

    public void updateFilter() {
        if(filters.size() == 0) {
            filteredData.setPredicate(s -> true);
        }
        else {
            filteredData.setPredicate(s -> {
                for (String filter : filters) {
                    if (s.getLevel().contains(filter)) {
                        return  true;

                    }
                }
                return false;
            });
        }
    }

    public EventsDataSource(ListView listView) {
        this.setListView(listView);

        EventsManager.shared.setListView(listView);
        listView.setItems(filteredData);



        listView.setCellFactory(new Callback<ListView<EventsModel>, ListCell<EventsModel>>() {
                                    @Override
                                    public ListCell<EventsModel> call(ListView<EventsModel> list) {
                                        return new EventsDataSource.EventsModelCell();
                                    }
                                }

        );

    }



}
