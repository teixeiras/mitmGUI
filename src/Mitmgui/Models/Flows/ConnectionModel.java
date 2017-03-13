package Mitmgui.Models.Flows;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class ConnectionModel {
    public class Address
    {
        private String[] address;
        private Boolean use_ipv6;

        public String[] getAddress() {
            return address;
        }

        public void setAddress(String[] address) {
            this.address = address;
        }

        public Boolean getUse_ipv6() {
            return use_ipv6;
        }

        public void setUse_ipv6(Boolean use_ipv6) {
            this.use_ipv6 = use_ipv6;
        }

        public Address()
        {
        }
    }
    private Address address;
    private Address ip_address;
    private Address source_address;
    private Boolean ssl_established;
    private String sni;
    private String alpn_proto_negotiated;
    private String timestamp_start;
    private String timestamp_tcp_setup;
    private String timestamp_ssl_setup;
    private String timestamp_end;
    private String via;
    private String clientcert;
    private String cipher_name;

    private String tls_version;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getIp_address() {
        return ip_address;
    }

    public void setIp_address(Address ip_address) {
        this.ip_address = ip_address;
    }

    public Address getSource_address() {
        return source_address;
    }

    public void setSource_address(Address source_address) {
        this.source_address = source_address;
    }

    public Boolean getSsl_established() {
        return ssl_established;
    }

    public void setSsl_established(Boolean ssl_established) {
        this.ssl_established = ssl_established;
    }

    public String getSni() {
        return sni;
    }

    public void setSni(String sni) {
        this.sni = sni;
    }

    public String getAlpn_proto_negotiated() {
        return alpn_proto_negotiated;
    }

    public void setAlpn_proto_negotiated(String alpn_proto_negotiated) {
        this.alpn_proto_negotiated = alpn_proto_negotiated;
    }

    public String getTimestamp_start() {
        return timestamp_start;
    }

    public void setTimestamp_start(String timestamp_start) {
        this.timestamp_start = timestamp_start;
    }

    public String getTimestamp_tcp_setup() {
        return timestamp_tcp_setup;
    }

    public void setTimestamp_tcp_setup(String timestamp_tcp_setup) {
        this.timestamp_tcp_setup = timestamp_tcp_setup;
    }

    public String getTimestamp_ssl_setup() {
        return timestamp_ssl_setup;
    }

    public void setTimestamp_ssl_setup(String timestamp_ssl_setup) {
        this.timestamp_ssl_setup = timestamp_ssl_setup;
    }

    public String getTimestamp_end() {
        return timestamp_end;
    }

    public void setTimestamp_end(String timestamp_end) {
        this.timestamp_end = timestamp_end;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getClientcert() {
        return clientcert;
    }

    public void setClientcert(String clientcert) {
        this.clientcert = clientcert;
    }

    public String getCipher_name() {
        return cipher_name;
    }

    public void setCipher_name(String cipher_name) {
        this.cipher_name = cipher_name;
    }

    public String getTls_version() {
        return tls_version;
    }

    public void setTls_version(String tls_version) {
        this.tls_version = tls_version;
    }
}
