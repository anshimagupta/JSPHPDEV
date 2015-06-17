package model;
/**
 * This class creates the automobile based on the
 * information provided in the text file.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Automobile implements Serializable {


    //As per the UML class diagram
    private String make;
    private String model;
    private LinkedHashMap<String, OptionSet> optionSetLinkedHashMap;
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
    public String getMake() {
        return make;
    }

    public String getModel(){
        return model;
    }

    public float getBaseprice() {
        return basePrice;
    }

    public OptionSet getOpset(String name) {
        return optionSetLinkedHashMap.get(name);
    }

    //Setters
    public void setMake(String make) {
        this.make = make;
    }

    public void setName(String name) {
        this.model = name;
    }

    public void setBaseprice(float baseprice) {
        this.basePrice = baseprice;
    }

    public String getOptionChoice(String optionChoiceName) {
        return optionSetLinkedHashMap.get(optionChoiceName).getOptionChoice().getName();
    }

    public float getOptionChoicePrice(String opsetName) {
        return optionSetLinkedHashMap.get(opsetName).getOptionChoice().getPrice();
    }

    public float getTotalPrice() {
        float totalPrice = (float) 0.0;
        Iterator<String> iterator = optionSetLinkedHashMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            totalPrice = totalPrice +  optionSetLinkedHashMap.get(key).getOptionChoice().getPrice();
        }
        return totalPrice;
    }


    //CRUD
    //-------------------------------FIND METHODS-----------
    public OptionSet findOpset(String opSetName) {
        return optionSetLinkedHashMap.get(opSetName);
    }
    public Option findOpt(String optName) {
        Iterator<String> iterator = optionSetLinkedHashMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            OptionSet optionSetList = optionSetLinkedHashMap.get(key);
            for(int i = 0; i<optionSetList.getOptionArrayList().size(); i++) {
                if (optionSetList.getOptionArrayList().get(i).getName().equals(optName)) {
                    return optionSetList.getOptionArrayList().get(i);
                }
            }
        }
        return null;
    }

    //------------------------CREATE METHODS---------------------------------------
    public void setOpsetValues(String name) {
        OptionSet opset = new OptionSet(name);
        optionSetLinkedHashMap.put(name, opset);
    }

    public void setOptionChoice(String opsetName, String optionName) {
        optionSetLinkedHashMap.get(opsetName).setOptionChoice(optionName);
    }

    public void setOptionValues(String opsetName, String name, float price) {
        Option opt = new Option();
        opt.setName(name);
        opt.setPrice(price);
        this.optionSetLinkedHashMap.get(opsetName).getOptionArrayList().add(opt);
    }
    //------------------------UPDATE METHODS---------------------------------------
    public void updateOptionSetName(String opsetName, String newName) {
        optionSetLinkedHashMap.get(opsetName).setName(newName);
    }

    public void updateOptionPrice(String opsetName, String optName, float newPrice) {
        OptionSet optionList = optionSetLinkedHashMap.get(opsetName);
        for(int i = 0; i<optionList.getOptionArrayList().size(); i++){
            if(optionList.getOptionArrayList().get(i).getName().equals(optName)){
                optionList.getOptionArrayList().get(i).setPrice(newPrice);
            }
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

        protected OptionSet(String name) {
            this.name = name;
            optionArrayList = new ArrayList<Option>();

        }


        protected void setName(String name) {
            this.name = name;
        }

        protected ArrayList<Option> getOptionArrayList() {
            return optionArrayList;
        }

        protected void setOptionArrayList(ArrayList<Option> optionArrayList) {
            this.optionArrayList = optionArrayList;
        }

        protected Option getOptionChoice() {
            return optionChoice;
        }

        protected void setOptionChoice(String name) {
            for (int i = 0; i < optionArrayList.size(); i++) {
                if (optionArrayList.get(i).getName().equals(name)) {
                    this.optionChoice = optionArrayList.get(i);
                }
            }
        }


        protected String print() {
            StringBuffer stringBuffer = new StringBuffer(name);
            for (int i = 0; i < optionArrayList.size(); i++) {
                stringBuffer.append("\n");
                stringBuffer.append("\t");
                stringBuffer.append(optionArrayList.get(i).print());
            }
            return stringBuffer.toString();
        }

    }

    // Nested class of Option
    private class Option implements Serializable {
        private String name;
        private float price;

        public String getName() {
            return name;
        }

        protected void setName(String name) {
            this.name = name;
        }

        protected float getPrice() {
            return price;
        }

        protected void setPrice(float price) {
            this.price = price;
        }

        protected String print() {
            StringBuffer stringBuffer = new StringBuffer(name);
            stringBuffer.append(" - Price: $");
            stringBuffer.append(price);
            return stringBuffer.toString();
        }
    }

    public void deleteOpt(String opSetName, String optName) {
        OptionSet optionSetList = optionSetLinkedHashMap.get(opSetName);
        for (int i = 0; i < optionSetList.getOptionArrayList().size(); i++) {
            if (optionSetList.getOptionArrayList().get(i).getName().equals(optName)) {
                optionSetList.getOptionArrayList().remove(i);
                break;
            }
        }
    }

    public String print() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Make: ");
        stringBuffer.append(make);
        stringBuffer.append("\nModel: ");
        stringBuffer.append(model);
        stringBuffer.append("\nBasePrice: $");
        stringBuffer.append(basePrice);

        Iterator<String> iterator = optionSetLinkedHashMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            OptionSet optSetList = optionSetLinkedHashMap.get(key);
            stringBuffer.append("\n");
            stringBuffer.append(optSetList.print());
        }

        return stringBuffer.toString();
    }
}