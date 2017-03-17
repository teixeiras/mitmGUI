package Mitmgui.Network;

import Mitmgui.Managers.AppManager;
import Mitmgui.Managers.PropertiesManager;
import Mitmgui.Models.Flows.FlowModel;
import org.pmw.tinylog.Logger;

import java.awt.Desktop;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class FetchAPI {
    public static String appendUri(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());

        return newUri.toString();
    }
    private HttpURLConnection connection(String partialUrl, String type) throws Exception {
        try {
            String url = "http://"+ PropertiesManager.shared.getURL()+partialUrl;
            for (String cookie : CookiesManager.shared.cookieList()) {
                url = appendUri(url, cookie);
            }
            HttpURLConnection connection = (HttpURLConnection)(new URL(url).openConnection());

            Logger.info(connection.getURL().toString());
            connection.setRequestMethod(type);

            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cookie", CookiesManager.validCookie());
            connection.connect();
            Logger.info("Response:" + connection.getResponseCode());
            return connection;

        } catch (IOException e) {
            Logger.error(e);
            throw e;
        }
        catch (URISyntaxException e) {
            Logger.error(e);
            throw e;
        }
    }

    public void GET(String url) throws Exception {
        HttpURLConnection connection = this.connection(url, "GET");

    }

    public void POST(String url) throws Exception {
        HttpURLConnection connection = this.connection(url, "POST");

    }

    public void PUT(String url,  byte[] data) throws Exception {
        HttpURLConnection connection = this.connection(url, "PUT");

    }


    public void resume(FlowModel flow) throws Exception {
        POST("/flows/"+flow.getId()+"/resume");
    }

    public void resumeAll() throws Exception {
        POST("/flows/resume");
    }

    public void  kill(FlowModel flow) throws Exception {
        POST("/flows/"+flow.getId()+"/kill");
    }

    public void  killAll() throws Exception {
        POST("/flows/kill");
    }


    public void  remove(FlowModel flow) throws Exception {
        POST("/flows/"+flow.getId());
    }

    public void duplicate(FlowModel flow) throws Exception {
        POST("/flows/"+flow.getId()+"/duplicate");
    }

    public void replay(FlowModel flow) throws Exception {
        POST("/flows/"+flow.getId()+"/replay");
    }

    public void  revert(FlowModel flow) throws Exception {
        POST("/flows/"+flow.getId()+"/revert");
    }

    public void  update(FlowModel flow,  byte[] data) throws Exception {
        PUT("/flows/"+flow.getId(), data);
    }

    public void uploadFile(URL url, byte[] postData) throws Exception {
        try{
            int    postDataLength = postData.length;

            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty("Cookie", CookiesManager.validCookie());

            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                wr.write( postData );
            }
        }catch (Exception e) {
            Logger.error(e);
            throw e;
        }
    }
    public void  uploadContent(FlowModel flow, byte[] postData, String type) throws Exception{
        URL url = new URL("http://"+ PropertiesManager.shared.getURL()+
                "/flows/"+flow.getId()+"/"+type+"/content");
        uploadFile(url, postData);
   }


    public void  clear() throws Exception {
        POST("/clear");
    }

    public void  downloadDump() throws Exception {

        try{
            HttpURLConnection connection = this.connection("/flows/dump", "POST");
            File file = new File(AppManager.homeDirectory(), "flow.dump");
            FileOutputStream fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream is = null;
            is = connection.getInputStream();
            byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
            int n;
            while ( (n = is.read(byteChunk)) > 0 ) {
                baos.write(byteChunk, 0, n);
            }


            fop.write(baos.toByteArray());
            fop.flush();
            fop.close();
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(file.getParent()));
            }


        } catch(Exception e) {
            Logger.error(e);
            throw e;
        }

    }

    public void  uploadDump(File file) throws Exception {
        URL url = new URL("http://" + PropertiesManager.shared.getURL() + "/flows/dump");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = new FileInputStream(file);
        byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
        int n;
        while ((n = is.read(byteChunk)) > 0) {
            baos.write(byteChunk, 0, n);
        }

        uploadFile(url, baos.toByteArray());
    }

}
