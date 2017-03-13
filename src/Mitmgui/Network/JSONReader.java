package Mitmgui.Network;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class JSONReader {


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static <T> T readJsonFromUrl(String url, Class base) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            T json=  (T)mapper.readValue(url, base);
            return json;
        } finally {
            is.close();
        }
    }

}
