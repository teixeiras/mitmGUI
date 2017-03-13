package Mitmgui.Models.Events;

import Mitmgui.Models.OperationModel;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class EventsModel extends OperationModel {
    private String message;
    private String level;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
