package model;
/**
 * This class creates the automobile based on the
 * information provided in the text file.
 */
import java.io.Serializable;
import java.util.*;

public class Automobile implements Serializable {

    private static final long serialVersionUID = 1L;
    //Automobile Properties
    private String make;
    private String modelName;
    private float basePrice;
    private Map<String, OptionSet> optionSets;

    //Constructors
    public Automobile() {
        super();
        modelName = null;
        basePrice = 0;
        optionSets = new HashMap<String, OptionSet>();
    }

    public Automobile(String modelName, float basePrice) {
        this.modelName = modelName;
        this.basePrice = basePrice;
        optionSets = new LinkedHashMap<String, OptionSet>();
    }

    //Getters
    public synchronized String getMake() {
        return make;
    }

    public synchronized String getModelName(){
        return modelName;
    }

    public synchronized float getBasePrice() {
        return basePrice;
    }

    public synchronized OptionSet getOpSet(String name) {
        return optionSets.get(name);
    }

    @Override
    public String toString() {
        return "Automobile " +
                "make='" + make + '\t' +
                ", modelName='" + modelName + '\t' +
                ", optionSetMap=" + optionSets.toString() +
                ", basePrice=" + basePrice ;
    }

    //Setters
    public synchronized void setMake(String make) {
        this.make = make;
    }

    public synchronized void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public synchronized void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    //CRUD
    //-------------------------------FIND METHODS-----------
    public synchronized OptionSet findOpset(String opSetName) {
        return optionSets.get(opSetName);
    }


    //------------------------CREATE METHODS---------------------------------------
    public synchronized void setOpsetValues(String name) {
        OptionSet opset = new OptionSet(name);
        optionSets.put(name, opset);
    }

    public synchronized void setOptionChoice(String opsetName, String optionName) {
        optionSets.get(opsetName).setOptionChoice(optionName);
    }


    //------------------------UPDATE METHODS---------------------------------------
    public void updateOptionSetName(String opsetName, String newName) {
        OptionSet optionSet = optionSets.get(opsetName);
        optionSet.setName(newName);
        optionSets.remove(opsetName);
        optionSets.put(newName, optionSet);
    }

    public void updateOptionPrice(String opSetName, String option, float newPrice) {
        optionSets.get(opSetName).updateOptionPrice(option, newPrice);
    }

    public String print() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Make: ");
        stringBuffer.append(make);
        stringBuffer.append("\nModel: ");
        stringBuffer.append(modelName);
        stringBuffer.append("\nBasePrice: $");
        stringBuffer.append(basePrice);

        Iterator<String> iterator = optionSets.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            OptionSet optSetList = optionSets.get(key);
            stringBuffer.append("\n");
            stringBuffer.append(optSetList.print());
        }

        return stringBuffer.toString();
    }

    public synchronized void setOptionValue(String opSetName, String optionName, Float optionPrice){
        optionSets.get(opSetName).setOptionValue(optionName, optionPrice);
    }

    public synchronized String getOptionChoice(String optionChoiceName) {
        return optionSets.get(optionChoiceName).getOptionChoice();
    }

    public synchronized float getOptionChoicePrice(String opsetName, String optionName) {
        return (optionSets.get(opsetName).getOptionChoicePrice(optionName));
    }

    public synchronized void deleteOption(String opSetName, String optionName) {
        optionSets.get(opSetName).deleteOpt(optionName);
    }

    public synchronized Map<String, OptionSet> getOptionSets() {
        return optionSets;
    }

    public synchronized String getOptionSetNames() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : optionSets.keySet()) {
            stringBuilder.append('\n');
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}