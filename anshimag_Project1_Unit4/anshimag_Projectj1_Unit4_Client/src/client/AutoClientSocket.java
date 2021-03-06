package client;

import model.Automobile;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

/**
 * This class first creates a connection with the server. It then reads the property file 
 * and send the object to server. The client selects a particular model and then selects the
 * options. It is finally displayed with the car it configured along with the prices
 */

public class AutoClientSocket extends Thread {
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Socket clientSocket;
    private String host;
    private int clientPort;
    private Boolean moreData;
    private Automobile automobile;
    private String response;

    public AutoClientSocket(String host, int clientPort) {
        this.host = host;
        this.clientPort = clientPort;
    }

    public void run() {
        Properties properties;
        CreateCarModelOptions carModelOptions = new CreateCarModelOptions();
        properties = carModelOptions.readDataFromFile();

        System.out.println("Loaded properties into memory");

        if (openConnection()) {
            sendPropertyObject(properties);
            receiveResponse();
            carModelOptions = new CreateCarModelOptions();
            properties = carModelOptions.readDataFromFile();
            sendPropertyObject(properties);
            receiveResponse();
        }
        ConfigureCarModel chooseCarModel = new ConfigureCarModel();
        listAllModelNames();
        sendSelectedModel();
        receiveSelectedModel();
        chooseCarModel.configCar(automobile);
        closeSession();
    }


    public boolean openConnection() {
        try {
            clientSocket = new Socket(host, clientPort);
        } catch (IOException socketError) {
            System.err.println("Unable to connect to " + host);

        }
        try {
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void receiveResponse() {
        String userInput = " ";
        try {
            userInput = bufferedReader.readLine();
            System.out.println(userInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPropertyObject(Properties properties) {
        try {
            objectOutputStream.writeObject(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendConfigOption(String option) {
        String opt = option;
        if (opt.equalsIgnoreCase("0")) {
            printWriter.println(opt);
        } else {
            printWriter.println(opt);
        }
    }

    public void listAllModelNames() {
        String strInput;
        try {
            System.out.println("All available models as below: ");
            while (!(strInput = bufferedReader.readLine()).equals("---")) {
                System.out.println(strInput);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void sendSelectedModel() {
        System.out.println("Please enter your desired model name:");
        Scanner model_sc = new Scanner(System.in);
        String modelSelected = model_sc.nextLine();
        PrintWriter out = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(modelSelected);
    }

    public void receiveSelectedModel() {
        try {
            automobile = (Automobile) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeSession() {
        try {
            bufferedReader = null;
            printWriter = null;
            objectInputStream.close();
            objectOutputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}