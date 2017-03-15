package Mitmgui.UI;

import Mitmgui.Models.Flows.FlowModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


/**
 * Created by teixeiras on 15/03/2017.
 */
public class FlowDetailsDataSource {
    public class FlowDetailsRow {

    }
    public class FlowDetailsTitle extends FlowDetailsRow{

    }
    public class FlowDetailsContent extends FlowDetailsRow{

    }
    ObservableList<FlowDetailsRow> information = FXCollections.observableArrayList();


    public FlowDetailsDataSource(FlowModel model, ListView listView) {
        listView.setItems(information);

        listView.setCellFactory(new Callback<ListView<FlowDetailsRow>, ListCell<FlowDetailsRow>>() {
                                @Override
                                public ListCell<FlowDetailsRow> call(ListView<FlowDetailsRow> list) {
                                    return new FlowDetailsCell();
                                }
                            }
        );

    }
    static class FlowDetailsCell extends ListCell<FlowDetailsRow> {
        @Override
        public void updateItem(FlowDetailsRow item, boolean empty) {
            super.updateItem(item, empty);

            if (item instanceof FlowDetailsTitle) {
                this.setText("AAA");
            } else {
                this.setText("BBBB");
            }

        }
    }



}
