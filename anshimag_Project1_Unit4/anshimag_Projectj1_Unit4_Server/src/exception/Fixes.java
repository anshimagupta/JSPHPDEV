package exception;

import java.util.Scanner;

/**
 * Created by anshima on 6/13/15.
 * This class is for fixing the missing parameters
 */
class Fixes {
    public String basePriceAbsent() {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.print("\nEnter the 'Base Price' value: ");
        return userInputScanner.nextLine();
    }

    public String fileAbsent() {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.print("\n You haven't provided the File Name, run the program again");
        return userInputScanner.nextLine();
    }

    public String makeAbsent() {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.print("\nEnter value for 'make':");
        return userInputScanner.nextLine();
    }

    public String modelAbsent() {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.print("\nEnter value for 'Model':");
        return userInputScanner.nextLine();
    }

    public String optionsAbsent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter option price Field: ");
        return scanner.nextLine();
    }

    public String matchNotFound() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the option again, this is case sensitive");
        return scanner.nextLine();
    }

    public String defaultFix() {
        return "Error while processing request";
    }
}
