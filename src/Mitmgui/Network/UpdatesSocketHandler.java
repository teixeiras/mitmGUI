package Mitmgui.Network;

import Mitmgui.Managers.EventsManager;
import Mitmgui.Managers.FlowsManager;
import Mitmgui.Managers.PropertiesManager;
import Mitmgui.Models.EventPackage;
import Mitmgui.Models.FlowPackage;
import Mitmgui.Models.SimplePackageModel;
import Mitmgui.UI.AlertHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;
import org.pmw.tinylog.Logger;

import java.net.URI;
import java.util.HashMap;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class UpdatesSocketHandler {
    private static int TIMEOUT = 30;
    private static String EVENTS = "events";
    private static String FLOWS = "flows";
    ObjectMapper mapper = new ObjectMapper();
    private WebSocketClient mWs;

    public UpdatesSocketHandler() {

    }

    public void connect() {
        try {
            HashMap<String, String> header = new HashMap<String, String>();
            header.put("Cookie", CookiesManager.validCookie());

            mWs = new WebSocketClient(new URI("ws://" + PropertiesManager.shared.getURL() + "/updates"), new Draft_10(), header, TIMEOUT) {
                @Override
                public void onMessage(String message) {
                    Logger.info("Message received:" + message);
                    try {
                        SimplePackageModel simplePackageModel = (SimplePackageModel) mapper.readValue(message, SimplePackageModel.class);
                        if (simplePackageModel.getResource().equals(FLOWS)) {
                            FlowPackage flowPackage = (FlowPackage) mapper.readValue(message, FlowPackage.class);
                            FlowsManager.shared.addFlow(flowPackage);

                        } else if (simplePackageModel.getResource().equals(EVENTS)) {
                            EventPackage eventPackage = (EventPackage) mapper.readValue(message, EventPackage.class);
                            EventsManager.shared.addEvent(eventPackage);
                        } else {
                            Logger.error("This package was not parsed: " + message);
                        }
                    } catch (Exception e) {
                        Logger.error(e);
                    }

                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("opened connection");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("closed connection");
                }

                @Override
                public void onError(Exception ex) {
                    Logger.error(ex);
                    connect();
                    return;
                }

            };
            mWs.connect();

        } catch (Exception e) {
            AlertHelper.exception("Error connecting server", "Could not connect into the mitmweb server", e);
            Logger.error(e);
            connect();

        }

    }

    void close() {
        mWs.close();

    }

}

