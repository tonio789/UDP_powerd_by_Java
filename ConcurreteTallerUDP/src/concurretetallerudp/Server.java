package concurretetallerudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private DatagramPacket out;
    private DatagramPacket in;
    private DatagramSocket socket;
    public static char sc = ' ';
    public int count = 0;

    public Server() {
        try {
            socket = new DatagramSocket(7777);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendData(DatagramPacket client, String info) {
        try {
            out = new DatagramPacket(info.getBytes(), 0, info.getBytes().length, client.getAddress(), client.getPort());
            socket.send(out);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarArreglo() {

        Juego juego = new Juego();
        for (int i = 0; i < juego.palabra.length(); i++) {
            juego.ArregloChar[i] = juego.palabra.charAt(i);
            juego.ArregloCharGuion[i] = '_';
        }
    }

    public void getData() {
        try {

            byte[] buffer = new byte[2000];
            in = new DatagramPacket(buffer, 0, buffer.length);
            socket.receive(in);
            String info = new String(in.getData());
            info.trim();

            Juego juego = new Juego();
            if (count == 0) {
                juego.llenarArreglo();
                count++;
            }

            sc = info.charAt(0);

            for (int i = 0; i < juego.palabra.length(); i++) {
                if (sc == juego.ArregloChar[i]) {
                    juego.ArregloCharGuion[i] = sc;
                }
            }

            if (Arrays.equals(juego.ArregloCharGuion, juego.ArregloChar)) {
                sendData(in, "*****YOU WIN*****");
            } else {
                sendData(in, "La palabra es de " + juego.palabra.length() + " letras: ");
                sendData(in, String.valueOf(juego.ArregloCharGuion));
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
