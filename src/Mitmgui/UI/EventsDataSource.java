package Mitmgui.UI;

import Mitmgui.Main;
import Mitmgui.Models.Flows.FlowModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.util.Callback;


/**
 * Created by teixeiras on 15/03/2017.
 */
public class EventsDataSource {
    ObservableList<FlowDetailsRow> information = FXCollections.observableArrayList();
    FlowModel model;
    ListView listView;

    public class FlowDetailsRow {
    }
    public class FlowDetailsTitle extends FlowDetailsRow{

    }
    public class FlowDetailsContent extends FlowDetailsRow{

    }


    public EventsDataSource(FlowModel model, ListView listView) {
        this.model = model;
        this.listView = listView;
        information.addAll(new FlowDetailsTitle(), new FlowDetailsContent());

        listView.setItems(information);

        listView.setCellFactory(new Callback<ListView<FlowDetailsRow>, ListCell<FlowDetailsRow>>() {
                                @Override
                                public ListCell<FlowDetailsRow> call(ListView<FlowDetailsRow> list) {
                                    return new FlowDetailsCell();
                                }
                            }
        );

    }

    class FlowDetailsCell extends ListCell<FlowDetailsRow> {
        {
            setMaxWidth(Control.USE_PREF_SIZE);
            prefWidthProperty().bind(listView.widthProperty().subtract(2));
        }
        @Override
        public void updateItem(FlowDetailsRow item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null) {
                return;
            }
            try {
                if (item instanceof FlowDetailsTitle) {
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("TitleListViewCell.fxml"));
                    Parent root = loader.load();

                    HBox content = (HBox) loader.getNamespace().get("cellContent");
                    Label title = (Label)content.lookup("#title");
                    title.setText("ROME WAS BOUGHT TODAT");
                    content.setMaxWidth(Control.USE_PREF_SIZE);
                    content.prefWidthProperty().bind(listView.widthProperty().subtract(2));
                    setGraphic(content);
                } else {
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("ContentListView.fxml"));
                    Parent root = loader.load();
                    TilePane content = (TilePane) loader.getNamespace().get("cellContent");

                    Label caption = (Label)content.lookup("#caption");
                    caption.setText("Janota");

                    Label value = (Label)content.lookup("#value");
                    value.setText("oia");

                    caption.prefWidthProperty().bind(content.widthProperty().divide(2).subtract(2));
                    value.prefWidthProperty().bind(content.widthProperty().divide(2).subtract(2));
                    content.setMaxWidth(Control.USE_PREF_SIZE);
                    content.prefWidthProperty().bind(listView.widthProperty().subtract(2));

                    setGraphic(content);
                }
            }catch(Exception e) {

            }


        }
    }



}
