package Mitmgui.Managers;

import Mitmgui.MainController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class PropertiesManager {
    public static PropertiesManager shared = new PropertiesManager();

    private static String HOST_KEY = "host";
    Properties prop = new Properties();

    public PropertiesManager() {
        InputStream input = null;
        try {
            String filename = "config.properties";
            input = MainController.class.getClassLoader().getResourceAsStream(filename);
        }finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public String getURL() {

        return prop.getProperty(HOST_KEY);
    }
}
