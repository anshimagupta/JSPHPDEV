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
    private static final LinkedHashMap<String, Automobile> autoMobileShop = new LinkedHashMap<String, Automobile>();

    public void buildAuto() {
        ReadFile reader = new ReadFile();
        try {
            autoMobileShop.put("Mustang", reader.buildAutomobileObject("MustangAutomobileOption.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAuto() {
        System.out.println(autoMobileShop.get("Mustang").print());
    }

    public void updateOptionSetName(String modelName, String opsetName, String newName) {
        autoMobileShop.get(modelName).updateOptionSetName(opsetName, newName);
    }

    public void updateOptionPrice(String modelName, String opsetName, String optName, float newPrice) {
        autoMobileShop.get(modelName).updateOptionPrice(opsetName, optName, newPrice);
    }

    public void setOptionChoice(String modelName, String opsetName, String optName) {
        autoMobileShop.get(modelName).setOptionChoice(opsetName, optName);
    }

    public String getOptionChoice(String modelName, String opsetName) {
        return autoMobileShop.get(modelName).getOptionChoice(opsetName);
    }

    public float getOptionChoicePrice(String modelName, String opsetName) {
        return autoMobileShop.get(modelName).getOptionChoicePrice(opsetName);
    }

    public void updateOpsetNameCon(String modelName) {
        synchronized (autoMobileShop.get(modelName)) {
            Thread thread = Thread.currentThread();
            System.out.println("\n==========TESTING MULTITHREADING===========");


            Iterator<String> iterator = autoMobileShop.get(modelName).getOptionSetLinkedHashMap().keySet().iterator();
            System.out.println("");
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            System.out.println("UPDATING OPSET NAME");
            System.out.println("\nEnter the option set name you want to edit:");
            Scanner sc_old = new Scanner(System.in);
            String oldOpsetName = sc_old.nextLine();

            System.out.println("\nEnter the new option set name:");
            Scanner sc_new = new Scanner(System.in);
            String newOpsetName = sc_new.nextLine();
            System.out.println(newOpsetName);
            autoMobileShop.get(modelName).updateOptionSetName(oldOpsetName, newOpsetName);

            Iterator<String> iterator1 = autoMobileShop.get(modelName).getOptionSetLinkedHashMap().keySet().iterator();
            System.out.println("\n** Current option set names are:");
            while (iterator1.hasNext()) {
                System.out.println(iterator1.next());
            }
        }
    }

    public void updateOptionCon(String modelName) {
        synchronized (autoMobileShop.get(modelName)) {
            Thread currThread = Thread.currentThread();
            System.out.println("\n==========UPDATING PRICE===========");

            System.out.println("\nEnter the Option Set Name you want to edit");
            Scanner scanner = new Scanner(System.in);
            String oldOpsetName = scanner.nextLine();

            System.out.println("\nEnter the Option Set Name that should it be updated to:");
            Scanner scanner1 = new Scanner(System.in);
            String newOpsetName = scanner1.nextLine();

            System.out.println("\nEnter new option price:");
            Scanner scanner2 = new Scanner(System.in);
            float newOpsetPrice = Float.parseFloat(scanner2.nextLine());
            autoMobileShop.get(modelName).updateOptionPrice(oldOpsetName, newOpsetName, newOpsetPrice);
            System.out.println(autoMobileShop.get(modelName).print());
        }
    }
}
