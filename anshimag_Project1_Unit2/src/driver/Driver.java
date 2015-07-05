package driver;

/**
 * Written by: Anshima Gupta
 */

import adapter.BuildAuto;
import util.FileIO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class Driver {

    public static PrintWriter printWriter;
    public static void main(String[] args) throws IOException, ClassNotFoundException, FileNotFoundException {
        printWriter = new PrintWriter("log.txt", "UTF-8");

        System.out.println("------------- PART A -------------");
        //Case 1: Create and Print Auto Instance
        System.out.println("Test Case 1: Create and Print Auto Instance");
        BuildAuto createAndPrint = new BuildAuto();
        createAndPrint.buildAuto("testA1", "AutomobileOption.txt");
        System.out.println("");
        createAndPrint.printAuto("testA1");

        //Case 2: Set and Update Functions
        System.out.print("\nTEST CASE 2: Check update functions");
        System.out.println("Update Option Set Name 'Transmission ' to 'Transmission Mode' ");
        BuildAuto testBuildAuto = new BuildAuto();
        testBuildAuto.buildAuto("testA2", "AutomobileOption.txt");
        testBuildAuto.updateOptionSetName("testA2", "Transmission", "Transmission Mode");
        testBuildAuto.updateOptionPrice("testA2", "Side Impact Air Bags", "present", 1000);
        System.out.print("\n");
        testBuildAuto.printAuto("testA2");

        //Case 4: Make Missing
        System.out.println("\nTEST CASE 3: [EXCEPTION] Make field missing");
        testBuildAuto.buildAuto("test4", "AutomobileOption_Make_Absent.txt");
        testBuildAuto.printAuto("test4");

        //Case 5: Model Missing
        System.out.print("\nTEST CASE 4: [EXCEPTION] Model field missing");
        testBuildAuto.buildAuto("test5", "AutomobileOption_Model_Absent.txt");
        testBuildAuto.printAuto("test5");

        //Case 6: Base Price missing
        System.out.print("\nTEST CASE 5: [EXCEPTION] Base Price field missing");
        testBuildAuto.buildAuto("test6", "AutomobileOption_BasePrice_Absent.txt");
        testBuildAuto.printAuto("test6");

        //Case 7: Options Missing
        System.out.print("\nTEST CASE 6: [EXCEPTION] Options missing");
        testBuildAuto.buildAuto("test7", "AutomobileOption_Options_Absent.txt");
        testBuildAuto.printAuto("test7");

        System.out.println("\n------------- PART B -------------");

        testBuildAuto.buildAuto("BMW", "AutomobileOption.txt");
        testBuildAuto.setOptionChoice("BMW", "Color", "Cloud 9 White Clearcoat");
        System.out.println(testBuildAuto.getOptionChoice("BMW", "Color"));
        System.out.println("PRICE IS:");
        System.out.println(testBuildAuto.getOptionChoicePrice("BMW", "Color", "Fort Knox Gold Clearcoat Metallic"));

        testBuildAuto.buildAuto("MUSTANG", "MustangAutomobileOption.txt");
        FileIO fileIO = new FileIO();
        fileIO.serializeAutomotive(testBuildAuto, "testBuildAuto.dat");
        BuildAuto buildAuto = fileIO.deserializeAutomotive("testBuildAuto.dat");
        System.out.println("");
        buildAuto.printAuto("MUSTANG");

        //Case 3: File Name missing
        System.out.println("TEST CASE 7: [EXCEPTION]File Name Missing");
        printWriter.close();
        testBuildAuto.buildAuto("test3", "");
        System.out.print("\n");
        testBuildAuto.printAuto("testA3");
    }
}