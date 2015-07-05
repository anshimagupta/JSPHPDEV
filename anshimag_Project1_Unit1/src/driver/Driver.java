package driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import util.*;
import model.Automotive;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        String autoName = "Focus Wagon ZTW";
        float basePrice = 18445;
        int optionSetSize = 5;

        Automotive fordZTW = new Automotive(autoName, basePrice, optionSetSize);
        
        ReadFile reader = new ReadFile();
        fordZTW = reader.buildAutomotiveObject("AutomotiveOption.txt", fordZTW);

        FileIO serDe = new FileIO();
        serDe.serializeAuto(fordZTW, "auto.ser");
        Automotive newFordZTW = serDe.deserializeAuto("auto.ser");
        System.out.print(newFordZTW);

        //Find Option Set
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nEnter the name of the Option Set");
        System.out.println(fordZTW.findOpsetWithName(scanner.nextLine()));


        System.out.println("CHECK IF AN OPTION EXISTS OR NOT");
        System.out.println("\nEnter the name of the Option Set");
        String opsetName = scanner.nextLine();
        System.out.println("\nEnter the name of the Option");
        String optionName = scanner.nextLine();
        System.out.println(fordZTW.findOptionWithName(opsetName, optionName));

        //Update Option
        System.out.println("UPDATE AN OPTION SET");
        System.out.println("\n\nEnter the name of the Option Set");
        String oldOpsetName = scanner.nextLine();
        System.out.println("\n\nEnter the new name of the Option Set");
        String newOpsetName = scanner.next();
        fordZTW.updateOpsetName(oldOpsetName, newOpsetName);
        System.out.println(fordZTW);

        //Update Options
        System.out.println("UPDATE OPTIONS");
        fordZTW.updateOpsetNames();
        System.out.println(fordZTW);

        //Delete Option Set Name
        System.out.println("DELETE OPSET NAME \n" + "Enter the name of the Option Set to be deleted");
        String opsetNameDel = scanner.nextLine();
        fordZTW.deleteOpset(opsetNameDel);
        System.out.println(fordZTW);

        //Delete Options
        System.out.println("DELETE OPTION \n " + "Enter Index of the Option Set to be deleted");
        int newOpsetNameIndex = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter index of option to be deleted");
        int optionNameIndex  = Integer.parseInt(scanner.nextLine());
        fordZTW.deleteOption(newOpsetNameIndex, optionNameIndex);
        System.out.println(fordZTW);
    }
}