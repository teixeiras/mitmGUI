package Mitmgui.Network.Requests;

import Mitmgui.Managers.PropertiesManager;
import Mitmgui.Models.Flows.FlowModel;
import Mitmgui.Network.JSONReader;

import java.io.IOException;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class FlowsRequests extends JSONReader<FlowModel[]> {
    public FlowModel[] getFlows() throws IOException {
        return readJsonFromUrl("http://" + PropertiesManager.shared.getURL() + "/flows", FlowModel[].class);
    }

}
