package driver;

/**
 * Written by: Anshima Gupta
 * The driver tests if the option and price is updated
 * correctly when accessed by two different threads
 */

import adapter.BuildAuto;
import adapter.EditConcurrently;
import scale.EditOptions;

import java.io.IOException;
import java.io.PrintWriter;


public class Driver {
    public static PrintWriter printWriter;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        printWriter = new PrintWriter("log.txt", "UTF-8");
        System.out.println("TEST FOR MULTITHREADING");
        System.out.println("=======================");
        BuildAuto testAutoScale = new BuildAuto();
        testAutoScale.buildAuto();
        testAutoScale.printAuto();

        EditConcurrently editConcurrently = new BuildAuto();

        //Update Option
        EditOptions editOptions1 = new EditOptions(1, editConcurrently);
        editOptions1.start();
        EditOptions editOptions2 = new EditOptions(1, editConcurrently);
        editOptions2.start();

        //Update Option Price
        EditOptions editOptions3 = new EditOptions(2, editConcurrently);
        editOptions3.start();
        EditOptions editOptions4 = new EditOptions(2, editConcurrently);
        editOptions4.start();
    }
}