
package ChatMulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import javax.swing.JTextArea;
import org.json.JSONObject;
     
public class Connection extends Thread {
    JTextArea txtArea;
    MulticastSocket sock;
    
    public Connection (MulticastSocket sock, JTextArea txtArea){
        this.txtArea = txtArea;
        this.sock = sock;
    }
    
    @Override
    public void run(){
        while(!Thread.interrupted()){
            try {
                byte [] data = new byte[65507];
                DatagramPacket dataPkt = new DatagramPacket(data, data.length);
                sock.receive(dataPkt);
                
                String message = new String(data, "UTF-8");
                
                JSONObject jsonObj = new JSONObject(message);
                
                txtArea.append(jsonObj.getString("time") +" " + 
                        jsonObj.getString("date") + " " +
                        jsonObj.getString("username") + " => " +
                        jsonObj.getString("message") + "\n");
                           
            } catch (IOException e) {
                Thread.interrupted();
            }
        }
    }
        
}
