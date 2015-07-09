package server;

import adapter.BuildAuto;
import model.Automobile;
import util.ReadFile;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class AutoServerSocketThread extends Thread {

    private Socket clientSocket = null;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Boolean moreData = true;
    private Boolean configCar = true;
    private AutoServer autoServer;
    private String modelName;
    private String userInput;

    public AutoServerSocketThread(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {

        prepareForCommunication();
        receivePropertyObject();
        sendResponse();
        receivePropertyObject();
        sendResponse();
        sendAvailableModels();
        receiveSelectedModel();
        sendSelectedModel();
        try {
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendSelectedModel() {
        System.out.println(modelName + " in sendSelectedModel");
        Automobile selectedAutomobile = autoServer.printSelectedAuto(modelName);
        try {
            objectOutputStream.writeObject(selectedAutomobile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendAvailableModels() {
        String allModels = autoServer.listAllModelNames();
        printWriter.write(allModels.trim() + "\n");
        printWriter.write("---" + "\n");
        printWriter.flush();
    }


    private void prepareForCommunication() {
        try {
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            autoServer = new BuildAuto();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receivePropertyObject() {
        try {
            Properties properties = null;
            do {
                properties = (Properties) objectInputStream.readObject();
            } while (properties == null);
            ReadFile readFile = new ReadFile();
            autoServer.addCarModelOptions(properties.getProperty("CarModel"), readFile.buildAutomobileFromProperties(properties));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void sendResponse() {
        String message = "[SERVER SIDE] : The new Automobile Model is successfully added";
        printWriter.write(message + "\n");
        printWriter.flush();
    }

    public void receiveSelectedModel() {
        try {
            userInput = bufferedReader.readLine();
            modelName = userInput;
            System.out.println(modelName + " is the received Model");
            //objectOutputStream.writeObject(modelName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
        clientSocket.close();

    }
}
