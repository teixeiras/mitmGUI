package Mitmgui.Network;

import Mitmgui.Managers.PropertiesManager;
import Mitmgui.UI.AlertHelper;
import org.pmw.tinylog.Logger;

import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class CookiesManager {
    public static CookiesManager shared = new CookiesManager();
    private String token;
    static java.net.CookieManager msCookieManager = new java.net.CookieManager();


    public String refreshToken() {
        try {
            Logger.debug("Request cookies to "+"http://"+ PropertiesManager.shared.getURL());

            URLConnection connection = new URL("http://"+ PropertiesManager.shared.getURL()).openConnection();
            List<String> cookiesHeader = connection.getHeaderFields().get("Set-Cookie");
            Logger.info("Cookie:" + cookiesHeader.get(0));
            token = cookiesHeader.get(0);


            if (cookiesHeader != null) {
                for (String cookie : cookiesHeader) {
                    msCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
                }
            }
        } catch(Exception e) {
            AlertHelper.exception("Error connecting server", "Could not connect into the mitmweb server", e);
            Logger.error(e);
            token = "";
        }
        return token;
    }
    public List<String>cookieList() {
        List<String>cookieList = new ArrayList<>();
        for (HttpCookie cookie : msCookieManager.getCookieStore().getCookies()) {
            cookieList.add(cookie.getName()+"="+cookie.getValue());
        }
        return cookieList;
    }
    public static  String validCookie() {
        if (shared.token == null) {
            shared.refreshToken();
        }
        return shared.token;

    }
}
