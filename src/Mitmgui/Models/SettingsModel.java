package Mitmgui.Models;

import java.util.List;

/**
 * Created by fteixeira on 13/03/2017.
 */
public class SettingsModel {

    private String version;
    private String mode;
    private String intercept;
    private Boolean showhost;
    private Boolean no_upstream_cert;
    private Boolean rawtcp;
    private Boolean http2;
    private Boolean websocket;
    private Boolean anticache;
    private Boolean anticomp;
    private Boolean stickyauth;
    private Boolean stickycookie;
    private String stream;
    private List<String> contentViews;
    private String listen_host;
    private String listen_port;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getIntercept() {
        return intercept;
    }

    public void setIntercept(String intercept) {
        this.intercept = intercept;
    }

    public Boolean getShowhost() {
        return showhost;
    }

    public void setShowhost(Boolean showhost) {
        this.showhost = showhost;
    }

    public Boolean getNo_upstream_cert() {
        return no_upstream_cert;
    }

    public void setNo_upstream_cert(Boolean no_upstream_cert) {
        this.no_upstream_cert = no_upstream_cert;
    }

    public Boolean getRawtcp() {
        return rawtcp;
    }

    public void setRawtcp(Boolean rawtcp) {
        this.rawtcp = rawtcp;
    }

    public Boolean getHttp2() {
        return http2;
    }

    public void setHttp2(Boolean http2) {
        this.http2 = http2;
    }

    public Boolean getWebsocket() {
        return websocket;
    }

    public void setWebsocket(Boolean websocket) {
        this.websocket = websocket;
    }

    public Boolean getAnticache() {
        return anticache;
    }

    public void setAnticache(Boolean anticache) {
        this.anticache = anticache;
    }

    public Boolean getAnticomp() {
        return anticomp;
    }

    public void setAnticomp(Boolean anticomp) {
        this.anticomp = anticomp;
    }

    public Boolean getStickyauth() {
        return stickyauth;
    }

    public void setStickyauth(Boolean stickyauth) {
        this.stickyauth = stickyauth;
    }

    public Boolean getStickycookie() {
        return stickycookie;
    }

    public void setStickycookie(Boolean stickycookie) {
        this.stickycookie = stickycookie;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public List<String> getContentViews() {
        return contentViews;
    }

    public void setContentViews(List<String> contentViews) {
        this.contentViews = contentViews;
    }

    public String getListen_host() {
        return listen_host;
    }

    public void setListen_host(String listen_host) {
        this.listen_host = listen_host;
    }

    public String getListen_port() {
        return listen_port;
    }

    public void setListen_port(String listen_port) {
        this.listen_port = listen_port;
    }
}
