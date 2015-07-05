package adapter;

/**
 * Created by anshima on 6/12/15.
 */

import model.Automobile;
import util.ReadFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

public abstract class ProxyAutomobile implements Serializable {

    //Automobile saved as LinkedHashMap
    private static final LinkedHashMap<String, Automobile> automobiles = new LinkedHashMap<String, Automobile>();

    public void buildAuto() {
        ReadFile reader = new ReadFile();
        try {
            automobiles.put("Mustang", reader.buildAutomobileObject("MustangAutomobileOption.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAuto() {
        System.out.println(automobiles.get("Mustang").print());
    }

    public void updateOptionSetName(String modelName, String opsetName, String newName) {
        automobiles.get(modelName).updateOptionSetName(opsetName, newName);
    }

    public void updateOptionPrice(String modelName, String opsetName, String optName, float newPrice) {
        automobiles.get(modelName).updateOptionPrice(opsetName, optName, newPrice);
    }

    public void setOptionChoice(String modelName, String opsetName, String optName) {
        automobiles.get(modelName).setOptionChoice(opsetName, optName);
    }

    public String getOptionChoice(String modelName, String opsetName) {
        return automobiles.get(modelName).getOptionChoice(opsetName);
    }

    /**
     *
     * @param modelName - The Automobile model which is being currently used
     * @param opsetName - Name of the Opset to be retrieved
     * @param optionName - Name of the option whose price will be updated
     * @return
     */
    public float getOptionChoicePrice(String modelName, String opsetName, String optionName) {
        return automobiles.get(modelName).getOptionChoicePrice(opsetName, optionName);
    }

    /**
     * The user enters the name of the opset to be updated. If the optionset is
     * found, the hash map will be updated with the new name. Methods in optionset
     * will called to set the name
     * @param modelName
     */
    public void updateOpSetNameCon(String modelName) {

            System.out.println("\n==========TESTING MULTITHREADING===========");

            System.out.println("UPDATING OPSET NAME");
            System.out.println("\nEnter the option set name you want to edit:");
            Scanner sc_old = new Scanner(System.in);
            String oldOpsetName = sc_old.nextLine();

            System.out.println("\nEnter the new option set name:");
            Scanner sc_new = new Scanner(System.in);
            String newOpsetName = sc_new.nextLine();
            automobiles.get(modelName).updateOptionSetName(oldOpsetName, newOpsetName);


    }

    /**
     * This method will update the price of an option
     * @param modelName
     */
    public void updateOptionPriceCon(String modelName) {
        synchronized (automobiles.get(modelName)) {
            Thread currThread = Thread.currentThread();
            System.out.println("\n==========UPDATING PRICE===========");

            System.out.println("\nEnter the Option Set Name you want to edit");
            Scanner scanner = new Scanner(System.in);
            String opSetName = scanner.nextLine();

            System.out.println("\nEnter the Options whose Base Price need to be updated");
            String optionName = scanner.nextLine();

            System.out.println("\nEnter new option price:");
            float newOpsetPrice = Float.parseFloat(scanner.nextLine());
            automobiles.get(modelName).updateOptionPrice(opSetName, optionName, newOpsetPrice);
            System.out.println(automobiles.get(modelName).print());
        }
    }
}
