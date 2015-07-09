package server;

import java.io.IOException;
import java.net.ServerSocket;
/**
 * Everytime a client connects to the server, the time is printed
 */

public class AutoServerSocket extends Thread{

    private int serverPort;
    private ServerSocket serverSocket=null;
    public AutoServerSocket(int serverPort) {
        this.serverPort = serverPort;
    }

    public void run() {
        boolean listening = true;
        try {
            serverSocket = new ServerSocket(serverPort);
            while (listening) {
                new AutoServerSocketThread(serverSocket.accept()).start();
                System.out.println("New client connection at Port: " + serverPort + " at time: " +
                        System.currentTimeMillis() );
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + serverPort);
            System.exit(-1);
        }
    }
}
