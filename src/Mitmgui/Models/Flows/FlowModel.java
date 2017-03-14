package Mitmgui.Models.Flows;

import Mitmgui.Models.OperationModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class FlowModel extends OperationModel {

    public class ErrorModel {
        private String msg;
        private String timestamp;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
    private Boolean intercepted;

    private ErrorModel error;

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

    public ErrorModel getError() {
        return error;
    }

    public void setError(ErrorModel error) {
        this.error = error;
    }


    private StringProperty path= new SimpleStringProperty(this, "path");;
    public final StringProperty pathProperty(){

        path.set(request.getPath());
        return path;
    }

    private StringProperty method = new SimpleStringProperty(this, "method");
    public final StringProperty methodProperty(){

        method.set(this.request.getMethod());
        return method;
    }

    private StringProperty status= new SimpleStringProperty(this, "status");
    public final StringProperty statusProperty(){

        status.set(this.getIntercepted()+"");
        return status;
    }

    private StringProperty size = new SimpleStringProperty(this, "size");
    public final StringProperty sizeProperty(){

        if (response != null) {
            size.set(response.getContentLength());
        }
        return size;
    }

    private StringProperty time= new SimpleStringProperty(this, "time");
    public final StringProperty timeProperty(){
        if (response != null) {
            time.set(response.getTimestamp_end() - response.getTimestamp_start() + "");
        }
        return time;
    }


}
