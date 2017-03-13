package Mitmgui.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by teixeiras on 12/03/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimplePackageModel {

    private String resource;
    private String cmd;
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
