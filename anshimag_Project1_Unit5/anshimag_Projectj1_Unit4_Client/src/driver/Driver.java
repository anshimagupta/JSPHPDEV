package driver;

import client.AutoClientSocket;

import java.io.PrintWriter;

public class Driver{
    public static PrintWriter printWriter;
    public static void main(String args[]){
        System.out.println("Client Side");
        System.out.println("========================");

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        /**
         * The client socket responses to request from the users in terms of:
         * 1. Uploading a new property file
         * 2. Adding more property files
         * 3. Configure a model
         */

        AutoClientSocket clientSocket = new AutoClientSocket("localhost", 2222);
        clientSocket.run();
    }
}