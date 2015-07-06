package driver;

import server.AutoServer;
import server.AutoServerSocket;

import adapter.BuildAuto;

import java.io.PrintWriter;

public class Driver {
    public static PrintWriter printWriter;
    public static void main(String args[]) {

        System.out.println("--------SERVER TESTING-----------");

        /**
         * The server socket responses to request from the client in terms of:
         * 1. Adding new models
         * 2. Retrieve models added
         */

        AutoServerSocket serverSocket = new AutoServerSocket(1234);
        serverSocket.start();
    }
}