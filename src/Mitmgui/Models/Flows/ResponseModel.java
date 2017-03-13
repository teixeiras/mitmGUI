package Mitmgui.Models.Flows;

import java.util.List;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class ResponseModel {
    private String http_version;

    private String status_code;

    private String reason;

    private List<List<String>> headers;

    private String contentLength;

    private String contentHash;

    private Double timestamp_start;

    private Double timestamp_end;

    private Boolean is_replay;

    public String getHttp_version() {
        return http_version;
    }

    public void setHttp_version(String http_version) {
        this.http_version = http_version;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
