package Mitmgui.Models;

import Mitmgui.Models.Flows.FlowModel;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class EventPackage extends SimplePackageModel {
    private FlowModel data;

    public FlowModel getData() {
        return data;
    }

    public void setData(FlowModel data) {
        this.data = data;
    }
}
