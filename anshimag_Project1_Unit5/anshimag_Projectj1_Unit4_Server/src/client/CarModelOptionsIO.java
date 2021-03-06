package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by anshima on 6/28/15.
 */
public class CarModelOptionsIO {
    public Properties readDataFromFile() {
        System.out.println("Enter the path and filename for the file which contains the Properties" +
                "of the Automobile");

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
