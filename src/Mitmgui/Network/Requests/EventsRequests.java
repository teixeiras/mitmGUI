package Mitmgui.Network.Requests;

import Mitmgui.Managers.PropertiesManager;
import Mitmgui.Models.Events.EventsModel;
import Mitmgui.Network.JSONReader;

import java.io.IOException;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class EventsRequests extends JSONReader<EventsModel[]> {
    public EventsModel[] getEvents() throws IOException {
        return readJsonFromUrl("http://"+ PropertiesManager.shared.getURL()+"/events", EventsModel[].class);
    }
}
