package driver;

/**
 * Written by: Anshima Gupta
 * The driver tests if the option and price is updated
 * correctly when accessed by two different threads
 *
 *
 * 	   /**
 *  Here we create 2 threads to modify the same object.
 *  E.g. If we are modifying the option set name (operation no. 0),
 *  current option set name will first be displayed.
 *  <p>
 *  Case 1: with synchronization statement:
 *  Assume thread 0 enters first, it will lock the auto object,
 *  and changes the thread name, say from 'Color' to 'Color0'.
 *  When thread 1 enters and tries to edit the option set name,
 *  the displayed option set names will reflect the changes made in thread 0
 *  that is, one won't see option set 'Color', but see 'Color0'.
 *  <p>
 *  Case 2: without synchronization statement:
 *  Both threads can reach the object during the process.
 *  The "faster" thread finishes the update correctly (say from "Color" to "Color0").
 *  The "slower" thread fails to update the value (say from "Color" to "Color1").
 *  This is because the option set name has already been edited to "Color0" by faster thread.
 *  When the slower thread tries to update the "Color" option set name,
 *  it finds that the "Color" option set name is no longer there (the value is now "Color0"),
 *  thus an exception will be thrown.
 *  <p>
 *  With this, we show that removing synchronization will cause data corruption.
 */

import adapter.BuildAuto;
import adapter.EditConcurrently;
import scale.EditOptions;

import java.io.IOException;
import java.io.PrintWriter;


public class Driver {
    public static PrintWriter printWriter;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        printWriter = new PrintWriter("log.txt", "UTF-8");
        System.out.println("TEST FOR MULTITHREADING");
        System.out.println("=======================");
        BuildAuto testAutoScale = new BuildAuto();
        testAutoScale.buildAuto();
        testAutoScale.printAuto();

        EditConcurrently editConcurrently = new BuildAuto();

        /**
         *  Update Optios
         *  Use two threads to update the same Option Name
         */


        EditOptions editOptions1 = new EditOptions(1, editConcurrently);
        EditOptions editOptions2 = new EditOptions(1, editConcurrently);

        editOptions1.run();
        testAutoScale.printAuto();
        editOptions2.run();
        testAutoScale.printAuto();


        /**
         * Update Option Price
         * Use two threads to update the option price simultaneously
        */

        EditOptions editOptions3 = new EditOptions(2, editConcurrently);
        editOptions3.start();
        EditOptions editOptions4 = new EditOptions(2, editConcurrently);
        editOptions4.start();
    }
}