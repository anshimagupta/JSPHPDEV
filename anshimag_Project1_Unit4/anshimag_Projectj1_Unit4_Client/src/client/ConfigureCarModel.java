package client;

import model.Automobile;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by anshima on 6/28/15.
 */
public class ConfigureCarModel {
    public String askIfConfigRequired() {
        System.out.print("Do you want to configure your car? "
        + "\n1. Yes" + "\n2. No");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        return response;
    }

    public void configCar(Automobile automobile) {

        StringBuffer stringBuffer = new StringBuffer("Below is the Configuration of the car" +
                "you have chosen. ");
        stringBuffer.append("Car Model: " + automobile.getModelName());
        stringBuffer.append("\n");
        stringBuffer.append("Car Make: " + automobile.getMake());
        stringBuffer.append("\n");
        stringBuffer.append("Base Price: " + automobile.getBasePrice());
        stringBuffer.append("\n");

        System.out.println("The Options are as follow: ");
        System.out.println(automobile.print());
        Iterator<String> iterator = automobile.getOptionSets().keySet().iterator();
        while (iterator.hasNext()) {
            String opsetName = iterator.next();
            System.out.println("Enter Option Choice");
            Scanner scanner = new Scanner(System.in);
            String selectedOption = scanner.nextLine();
            automobile.setOptionChoice(opsetName, selectedOption);
            stringBuffer.append(opsetName);
            stringBuffer.append("\n");
            stringBuffer.append(automobile.getOptionChoice(opsetName) + "\n");
        }
        System.out.println(stringBuffer.toString());
    }
}

