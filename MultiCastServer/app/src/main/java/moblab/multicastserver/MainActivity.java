package moblab.multicastserver;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        new Client().execute("");
    }
}

class Client extends AsyncTask<String, String, String> {


    protected String doInBackground(String... urls) {

        try {
            InetAddress addr = InetAddress.getByName("224.0.0.3");
            DatagramSocket serverSocket = new DatagramSocket();
            for (int i = 0; i < 5; i++) {

                String msg = "Sent message no " + i;
                DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),

                        msg.getBytes().length, addr, 8888);

                serverSocket.send(msgPacket);

                Log.d("TESTE", "Server sent packet with msg: " + msg);

                Thread.sleep(500);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}