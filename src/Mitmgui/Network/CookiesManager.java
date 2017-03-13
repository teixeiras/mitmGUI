package Mitmgui.Network;

import Mitmgui.UI.AlertHelper;
import org.pmw.tinylog.Logger;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class CookiesManager {
    public static String validCookie() {
        try {
            URLConnection connection = new URL("http://google.com").openConnection();
            List<String> cookies = connection.getHeaderFields().get("Set-Cookie");
            return cookies.get(0);
        } catch(Exception e) {
            AlertHelper.exception("Error connecting server", "Could not connect into the mitmweb server", e);
            Logger.error(e.getStackTrace());
            return "";
        }


    }
}
