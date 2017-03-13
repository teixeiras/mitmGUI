package Mitmgui.Network.Requests;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class FetchRequestContent {

    String url = "http://" + Settings.instance().defaultHost + "/flows/" + System.Uri.EscapeDataString(id) + "/" + request + "/content";

}
