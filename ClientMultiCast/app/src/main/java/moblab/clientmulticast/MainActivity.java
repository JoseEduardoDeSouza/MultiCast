package moblab.clientmulticast;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

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
            byte[] buf = new byte[256];

            // Create a new Multicast socket (that will allow other sockets/programs
            // to join it as well.
            MulticastSocket clientSocket = new MulticastSocket(8888);
            //Joint the Multicast group.
            clientSocket.joinGroup(addr);

            while (true) {
                // Receive the information and print it.
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket);

                String msg = new String(buf, 0, buf.length);
                Log.d("RESPOSTA", "Socket 1 received msg: " + msg);
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
