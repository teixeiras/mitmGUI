package Mitmgui.Network.Requests;

import Mitmgui.Managers.PropertiesManager;
import Mitmgui.Models.SettingsModel;
import Mitmgui.Network.JSONReader;

import java.io.IOException;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class SettingsRequest extends JSONReader<SettingsModel> {
    public SettingsModel getSettings() throws IOException {
        return readJsonFromUrl("http://"+PropertiesManager.shared.getURL()+"/settings", SettingsModel.class);
    }
}
