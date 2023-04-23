package concurrentetallerudpcliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Client extends Thread {
    private DatagramPacket in;
    private DatagramPacket out;
    private DatagramSocket socket;
    
    public Client() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendData() {
        try {
            String info = JOptionPane.showInputDialog("Digita una letra: ");
            out = new DatagramPacket(info.getBytes(), 0, info.getBytes().length, InetAddress.getLocalHost(), 7777);
            socket.send(out);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void getData() {
        try {
            byte[] buffer = new byte[200];
            in = new DatagramPacket(buffer, 0, buffer.length);
            socket.receive(in);
            String info = new String(in.getData());
            System.out.println(info.trim());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    public void run() {
        while (true) {
            getData();
        }
    }







}
