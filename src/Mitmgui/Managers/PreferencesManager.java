package Mitmgui.Managers;

import java.util.prefs.Preferences;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class PreferencesManager {
    static String MAIN_WIDTH_PROPERTY = "MAIN_WIDTH_PROPERTY";
    static String MAIN_HEIGHT_PROPERTY = "MAIN_HEIGHT_PROPERTY";
    public static PreferencesManager shared = new PreferencesManager();

    Preferences prefs = Preferences.userNodeForPackage(this.getClass());


    private int mainWidth;
    private int mainHeight;


    public int getMainWidth() {
        return prefs.getInt(MAIN_WIDTH_PROPERTY, 300);
    }

    public void setMainWidth(int mainWidth) {
        prefs.putInt(MAIN_WIDTH_PROPERTY, mainWidth);
    }

    public int getMainHeight() {
        return prefs.getInt(MAIN_HEIGHT_PROPERTY, 300);
    }

    public void setMainHeight(int mainHeight) {
        prefs.putInt(MAIN_HEIGHT_PROPERTY, mainHeight);
    }
}
