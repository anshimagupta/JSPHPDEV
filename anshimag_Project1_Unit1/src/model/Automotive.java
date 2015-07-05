package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class Automotive implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private float baseprice;
    private OptionSet opSets[];

    // constructors
    public Automotive() {
        super();
    }

    public Automotive(String name, float price, int size) {
        this.name = name;
        this.baseprice = price;
        this.setOpSets(new OptionSet[size]);

        // instantiate object to avoid NullPointerException
        for (int i = 0; i < this.getOpSets().length; i++) {
            this.getOpSets()[i] = new OptionSet();
        }
    }

    // Getter
    public String getName() {
        return name;
    }

    public float getBaseprice() {
        return baseprice;
    }

    public OptionSet getOpset(int index) {
        return opSets[index];
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }

    public void setBaseprice(float baseprice) {
        this.baseprice = baseprice;
    }

    public void setOpSetValues(int opSetIndex, String opSetName, int optionSize) {
        OptionSet opSet = new OptionSet(opSetName, optionSize);
        opSets[opSetIndex] = opSet;
    }

    public void setOptionValues(int opSetIndex, int optIndex, String name,
                                float price) {
        opSets[opSetIndex].setOptionValues(optIndex, name, price);
    }

    // Find
    public OptionSet findOpsetWithName(String opsetName) {
        int index = -1;
        for (int i = 0; i < opSets.length; i++) {
            if (opSets[i].getName().equals(opsetName)) {
                index = i;
                break;
            }
        }
        return opSets[index];
    }

    public OptionSet.Option findOptionWithName(String opsetName, String optionName) {
        int index = -1;
        for (int i = 0; i < opSets.length; i++) {
            if (opSets[i].getName().equals(opsetName)) {
                return opSets[i].findOptWithName(optionName);
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Automotive" + '\n' +
                "Name=" + name + '\n' +
                "Baseprice= $" + baseprice  +
                Arrays.toString(opSets) ;
    }

    public OptionSet[] getOpSets() {
        return opSets;
    }

    public void setOpSets(OptionSet opSets[]) {
        this.opSets = opSets;
    }

    // Delete an OptionSet
    public void deleteOpset(String opsetName) {
        for (int i = 0; i < this.opSets.length; i++) {
            if (this.opSets[i].getName().equals(opsetName)) {
                this.opSets[i] = null;
                break;
            }
        }
    }

    // Update OptionSet Value
    public void updateOpsetName(String oldOpsetName, String newOpsetName) {
        for (int i = 0; i < this.opSets.length; i++) {
            if (opSets[i].getName().equals(oldOpsetName)) {
                this.opSets[i].setName(newOpsetName);
                break;
            } else {
                System.out.println("No such OptionSet Found");
            }
        }
    }

    // Update OptionSet Names
    public void updateOpsetNames() {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < this.opSets.length; i++) {
            System.out.println("\nEnter new name, old name is " + opSets[i].getName() );
            opSets[i].setName(s.nextLine());
        }
    }

    // Update Options Values
    public void updateOpsetValues(String opsetName) {
        int len = opSets.length;
        for (int i = 0; i < len; i++) {
            if (this.getName().equals(opsetName)) {
                opSets[i].updateOptionSetValues();
            } else {
                System.out.println("No such OptionSet Found");
            }
        }
    }

    public void updateOptionPrice(String opsetName, String option, Float price) {
        int len = opSets.length;
        for (int i = 0; i < len; i++) {
            if (this.getName().equals(opsetName)) {
                opSets[i].updateOpt(option, price);
            } else {
                System.out.println("No such OptionSet Found");
            }
        }
    }

    public void deleteOption(int newOpsetNameIndex, int optionNameIndex) {
        opSets[newOpsetNameIndex].deleteOpt(optionNameIndex);

    }
}