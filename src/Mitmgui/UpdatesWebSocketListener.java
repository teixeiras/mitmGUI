package Mitmgui;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class UpdatesWebSocketListener {

    public UpdatesWebSocketListener() {
        WebSocketClient mWs = new WebSocketClient( new URI( "ws://socket.example.com:1234" ), new Draft_10() )
        {
            @Override
            public void onMessage( String message ) {
                /*JSONObject obj = new JSONObject(message);
                String channel = obj.getString("channel");*/
            }

            @Override
            public void onOpen( ServerHandshake handshake ) {
                System.out.println( "opened connection" );
            }

            @Override
            public void onClose( int code, String reason, boolean remote ) {
                System.out.println( "closed connection" );
            }

            @Override
            public void onError( Exception ex ) {
                ex.printStackTrace();
            }

        };
        mWs.connect();
        /*JSONObject obj = new JSONObject();
        obj.put("event", "addChannel");
        obj.put("channel", "ok_btccny_ticker");
        String message = obj.toString();
        //send message
        mWs.send(message);
    */

        mWs.close();
    }



}

