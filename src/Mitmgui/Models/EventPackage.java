package Mitmgui.Models;

import Mitmgui.Models.Events.EventsModel;
import Mitmgui.Models.Flows.FlowModel;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class EventPackage extends SimplePackageModel {
    private EventsModel data;

    public EventsModel getData() {
        return data;
    }

    public void setData(EventsModel data) {
        this.data = data;
    }
}
