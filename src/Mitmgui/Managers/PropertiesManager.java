package Mitmgui.Managers;

import Mitmgui.MainController;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.io.InputStream;
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
            prop.load(input);
        } catch (IOException e) {
            Logger.error(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Logger.error(e);
                }
            } else {
                Logger.error("Could not find config properties");

            }

        }
    }

    public String getURL() {

        return prop.getProperty(HOST_KEY);
    }
}
