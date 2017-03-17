package Mitmgui.Network.Requests;

import Mitmgui.Managers.PropertiesManager;
import Mitmgui.Network.CookiesManager;
import org.pmw.tinylog.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class FetchRequestContent {

    public byte[] getBinary(String id, String request) {
        String url = "http://" + "http://" + PropertiesManager.shared.getURL() + "/flows/" +
                id + "/" + request + "/content";
        Logger.debug("Requesting" + url);
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) (new URL(url).openConnection());

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cookie", CookiesManager.validCookie());
            connection.connect();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream is = null;
            try {
                is = connection.getInputStream();
                byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
                int n;

                while ((n = is.read(byteChunk)) > 0) {
                    baos.write(byteChunk, 0, n);
                }
                return baos.toByteArray();
            } catch (IOException e) {
                Logger.error(e);
            } finally {
                if (is != null) {
                    is.close();
                }
            }

        } catch (IOException e) {
            Logger.error(e);

        }
        return null;
    }


}
