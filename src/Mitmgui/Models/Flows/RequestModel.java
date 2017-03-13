package Mitmgui.Models.Flows;

import java.util.List;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class RequestModel {
    private String method;
    private String scheme;
    private String host;
    private String port;
    private String path;
    private String http_version;
    private List<List<String>> headers;


    private String contentLength;
    private String contentHash;
    private Double timestamp_start;
    private Double timestamp_end;
    private Boolean is_replay;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttp_version() {
        return http_version;
    }

    public void setHttp_version(String http_version) {
        this.http_version = http_version;
    }

    public List<List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(List<List<String>> headers) {
        this.headers = headers;
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentHash() {
        return contentHash;
    }

    public void setContentHash(String contentHash) {
        this.contentHash = contentHash;
    }

    public Double getTimestamp_start() {
        return timestamp_start;
    }

    public void setTimestamp_start(Double timestamp_start) {
        this.timestamp_start = timestamp_start;
    }

    public Double getTimestamp_end() {
        return timestamp_end;
    }

    public void setTimestamp_end(Double timestamp_end) {
        this.timestamp_end = timestamp_end;
    }

    public Boolean getIs_replay() {
        return is_replay;
    }

    public void setIs_replay(Boolean is_replay) {
        this.is_replay = is_replay;
    }
}
