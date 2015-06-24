package model;
/**
 * This class creates the automobile based on the
 * information provided in the text file.
 */

import exception.AutomobileException;
import exception.Error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Automobile implements Serializable {


    //As per the UML class diagram
    private String make;
    private String model;
    private final LinkedHashMap<String, OptionSet> optionSetLinkedHashMap;
    private float basePrice;
    private Option choice;

    public Automobile() {
        super();
        model = null;
        basePrice = 0;
        optionSetLinkedHashMap = new LinkedHashMap<String, OptionSet>();
    }

    public Automobile(String name, float price) {
        this.model = name;
        this.basePrice = price;
        optionSetLinkedHashMap = new LinkedHashMap<String, OptionSet>();
    }

    //Getters
    public synchronized String getMake() {
        return make;
    }

    public synchronized String getModel() {
        return model;
    }

    public synchronized float getBaseprice() {
        return basePrice;
    }

    public synchronized OptionSet getOpset(String name) {
        return optionSetLinkedHashMap.get(name);
    }

    //Setters
    public synchronized void setMake(String make) {
        this.make = make;
    }

    public synchronized void setName(String name) {
        this.model = name;
    }

    public synchronized void setBaseprice(float baseprice) {
        this.basePrice = baseprice;
    }

    public synchronized String getOptionChoice(String optionChoiceName) {
        return optionSetLinkedHashMap.get(optionChoiceName).getOptionChoice().getName();
    }

    public synchronized float getOptionChoicePrice(String opsetName) {
        return optionSetLinkedHashMap.get(opsetName).getOptionChoice().getPrice();
    }

    public synchronized LinkedHashMap<String, OptionSet> getOptionSetLinkedHashMap() {
        return optionSetLinkedHashMap;
    }

    public synchronized float getTotalPrice() {
        float totalPrice = (float) 0.0;
        for (String key : optionSetLinkedHashMap.keySet()) {
            totalPrice = totalPrice + optionSetLinkedHashMap.get(key).getOptionChoice().getPrice();
        }
        return totalPrice;
    }


    //CRUD
    //-------------------------------FIND METHODS-----------
    public synchronized OptionSet findOpset(String opSetName) {
        return optionSetLinkedHashMap.get(opSetName);
    }

    public synchronized Option findOpt(String optName) {
        for (String key : optionSetLinkedHashMap.keySet()) {
            OptionSet optionSetList = optionSetLinkedHashMap.get(key);
            for (int i = 0; i < optionSetList.getOptionArrayList().size(); i++) {
                if (optionSetList.getOptionArrayList().get(i).getName().equals(optName)) {
                    return optionSetList.getOptionArrayList().get(i);
                }
            }
        }
        return null;
    }

    //------------------------CREATE METHODS---------------------------------------
    public synchronized void setOpsetValues(String name) {
        OptionSet opset = new OptionSet(name);
        optionSetLinkedHashMap.put(name, opset);
    }

    public synchronized void setOptionChoice(String opsetName, String optionName) {
        optionSetLinkedHashMap.get(opsetName).setOptionChoice(optionName);
    }

    public synchronized void setOptionValues(String opsetName, String name, float price) {
        Option opt = new Option();
        opt.setName(name);
        opt.setPrice(price);
        this.optionSetLinkedHashMap.get(opsetName).getOptionArrayList().add(opt);
    }

    //------------------------UPDATE METHODS---------------------------------------
    public synchronized void updateOptionSetName(String opsetName, String newName) {
        String error;
        try {
            if (!optionSetLinkedHashMap.containsKey(opsetName)) {
                throw new AutomobileException(Error.MATCH_NOT_FOUND);
            } else {
                optionSetLinkedHashMap.put(newName, optionSetLinkedHashMap.get(opsetName));
                optionSetLinkedHashMap.remove(opsetName);
                System.out.println("");
            }
        } catch (AutomobileException e) {
            error = e.fix();
            System.out.println(error);
        }

    }

    public synchronized void updateOptionPrice(String opsetName, String optName, float newPrice) {
        try{
            if(!optionSetLinkedHashMap.containsKey(opsetName))
                throw new AutomobileException(Error.MATCH_NOT_FOUND);
            else {
                OptionSet optionSet = optionSetLinkedHashMap.get(opsetName);
                boolean optNameExists = false;
                for (int j = 0; j < optionSet.getOptionArrayList().size(); j++) {
                    if (optionSet.getOptionArrayList().get(j).getName().equals(optName)) {
                        optionSet.getOptionArrayList().get(j).setPrice(newPrice);
                        optNameExists = true;
                        break;
                    }
                }

            }
        } catch (AutomobileException e) {
            e.fix();
        }
    }


    // inner class of OptionSet
    private class OptionSet implements Serializable {
        private String name;
        private ArrayList<Option> optionArrayList;
        private Option optionChoice;


        protected OptionSet() {
            super();
            name = null;
            optionArrayList = new ArrayList<Option>();
        }

        OptionSet(String name) {
            this.name = name;
            optionArrayList = new ArrayList<Option>();

        }


        protected synchronized void setName(String name) {
            this.name = name;
        }

        synchronized ArrayList<Option> getOptionArrayList() {
            return optionArrayList;
        }

        protected synchronized void setOptionArrayList(ArrayList<Option> optionArrayList) {
            this.optionArrayList = optionArrayList;
        }

        synchronized Option getOptionChoice() {
            return optionChoice;
        }

        synchronized void setOptionChoice(String name) {
            for (Option anOptionArrayList : optionArrayList) {
                if (anOptionArrayList.getName().equals(name)) {
                    this.optionChoice = anOptionArrayList;
                }
            }
        }


        synchronized String print() {
            StringBuilder stringBuffer = new StringBuilder(name);
            for (Option anOptionArrayList : optionArrayList) {
                stringBuffer.append("\n");
                stringBuffer.append("\t");
                stringBuffer.append(anOptionArrayList.print());
            }
            return stringBuffer.toString();
        }

    }

    // Nested class of Option
    private class Option implements Serializable {
        private String name;
        private float price;

        public synchronized String getName() {
            return name;
        }

        synchronized void setName(String name) {
            this.name = name;
        }

        synchronized float getPrice() {
            return price;
        }

        synchronized void setPrice(float price) {
            this.price = price;
        }

        synchronized String print() {
            return name + " - Price: $" + price;
        }
    }

    public synchronized void deleteOpt(String opSetName, String optName) {
        OptionSet optionSetList = optionSetLinkedHashMap.get(opSetName);
        for (int i = 0; i < optionSetList.getOptionArrayList().size(); i++) {
            if (optionSetList.getOptionArrayList().get(i).getName().equals(optName)) {
                optionSetList.getOptionArrayList().remove(i);
                break;
            }
        }
    }

    public synchronized String print() {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("Make: ");
        stringBuffer.append(make);
        stringBuffer.append("\nModel: ");
        stringBuffer.append(model);
        stringBuffer.append("\nBasePrice: $");
        stringBuffer.append(basePrice);

        for (String key : optionSetLinkedHashMap.keySet()) {
            OptionSet optSetList = optionSetLinkedHashMap.get(key);
            stringBuffer.append("\n");
            stringBuffer.append(optSetList.print());
        }

        return stringBuffer.toString();
    }
}