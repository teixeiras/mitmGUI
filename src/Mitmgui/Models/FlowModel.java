package Mitmgui.Models;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class FlowModel {
    private Boolean intercepted;

    private String type;

    private Boolean modified;

    private Boolean marked;

    private ConnectionModel client_conn;

    private ConnectionModel server_conn;

    private RequestModel request;

    private ResponseModel response;

    public Boolean getIntercepted() {
        return intercepted;
    }

    public void setIntercepted(Boolean intercepted) {
        this.intercepted = intercepted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getModified() {
        return modified;
    }

    public void setModified(Boolean modified) {
        this.modified = modified;
    }

    public Boolean getMarked() {
        return marked;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    public ConnectionModel getClient_conn() {
        return client_conn;
    }

    public void setClient_conn(ConnectionModel client_conn) {
        this.client_conn = client_conn;
    }

    public ConnectionModel getServer_conn() {
        return server_conn;
    }

    public void setServer_conn(ConnectionModel server_conn) {
        this.server_conn = server_conn;
    }

    public RequestModel getRequest() {
        return request;
    }

    public void setRequest(RequestModel request) {
        this.request = request;
    }

    public ResponseModel getResponse() {
        return response;
    }

    public void setResponse(ResponseModel response) {
        this.response = response;
    }
}
