package Mitmgui.Network;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pmw.tinylog.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class JSONReader<T> {


    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public <T> T readJsonFromUrl(String url, Class base) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) (new URL(url).openConnection());

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cookie", CookiesManager.validCookie());
            connection.connect();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String jsonText = readAll(rd);
                Logger.debug(jsonText);
                T json = (T) mapper.readValue(jsonText, base);
                return json;
            } finally {
                connection.getInputStream().close();
            }
        } catch (IOException e) {
            Logger.error(e);
        }

        return null;
    }

}
