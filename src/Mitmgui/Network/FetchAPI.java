package Mitmgui.Network;

import Mitmgui.Managers.AppManager;
import Mitmgui.Managers.PropertiesManager;
import Mitmgui.Models.Flows.FlowModel;
import org.pmw.tinylog.Logger;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class FetchAPI {

    private HttpURLConnection connection(String partialUrl) throws IOException {
        try {

            HttpURLConnection connection = (HttpURLConnection)(new URL("http://"+ PropertiesManager.shared.getURL()+partialUrl).openConnection());

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cookie", CookiesManager.validCookie());
            connection.connect();
            return connection;

        } catch (IOException e) {
            Logger.error(e);
            throw e;
        }
    }

    public void GET(String url) throws IOException {
        HttpURLConnection connection = this.connection(url);

    }

    public void POST(String url) throws IOException {
        HttpURLConnection connection = this.connection(url);

    }

    public void PUT(String url,  byte[] data) throws IOException {
        HttpURLConnection connection = this.connection(url);

    }


    public void resume(FlowModel flow) throws IOException {
        POST("/flows/"+flow.getId()+"/resume");
    }

    public void resumeAll() throws IOException {
        POST("/flows/resume");
    }

    public void  kill(FlowModel flow) throws IOException {
        POST("/flows/"+flow.getId()+"/kill");
    }

    public void  killAll() throws IOException {
        POST("/flows/kill");
    }


    public void  remove(FlowModel flow) throws IOException {
        POST("/flows/"+flow.getId());
    }

    public void duplicate(FlowModel flow) throws IOException {
        POST("/flows/"+flow.getId()+"/duplicate");
    }

    public void replay(FlowModel flow) throws IOException {
        POST("/flows/"+flow.getId()+"/replay");
    }

    public void  revert(FlowModel flow) throws IOException {
        POST("/flows/"+flow.getId()+"/revert");
    }

    public void  update(FlowModel flow,  byte[] data) throws IOException {
        PUT("/flows/"+flow.getId(), data);
    }

    public void uploadFile(URL url, byte[] postData) throws IOException {
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


    public void  clear() throws IOException {
        POST("/clear");
    }

    public void  downloadDump() throws IOException {

        try{
            HttpURLConnection connection = this.connection("/flows/dump");
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
