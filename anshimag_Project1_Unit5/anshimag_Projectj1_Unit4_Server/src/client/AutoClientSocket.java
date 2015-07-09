package client;

import model.Automobile;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

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
        Properties properties = null;
        CarModelOptionsIO carModelOptionsIO = new CarModelOptionsIO();
        if (openConnection()) {

            properties = carModelOptionsIO.readDataFromFile();
            sendPropertyObject(properties);
            receiveResponse();
        }

        ChooseCarModel chooseCarModel = new ChooseCarModel();
        response = chooseCarModel.askIfConfigRequired();
        sendConfigOption(response);

        listAllModelNames();
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
        String userInput = "";
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

    public String listAllModelNames() {
        String modelNames = "";
        try {
            modelNames = bufferedReader.readLine();
            System.out.println("All available models as below: ");
            System.out.println(modelNames);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return modelNames;
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

    public Automobile receiveSelectedModel() {
        try {
            automobile = (Automobile) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return automobile;
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