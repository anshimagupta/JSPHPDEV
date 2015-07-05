package model;
/**
 * This class creates the automobile based on the
 * information provided in the text file.
 */
import java.io.Serializable;
import java.util.*;

public class Automobile implements Serializable {

    //Automobile Properties
    private String make;
    private String modelName;
    private float basePrice;
    private Map<String, OptionSet> optionSetMap;

    //Constructors
    public Automobile() {
        super();
        modelName = null;
        basePrice = 0;
        optionSetMap = new HashMap<String, OptionSet>();
    }

    public Automobile(String modelName, float basePrice) {
        this.modelName = modelName;
        this.basePrice = basePrice;
        optionSetMap = new LinkedHashMap<String, OptionSet>();
    }

    //Getters
    public String getMake() {
        return make;
    }

    public String getModelName(){
        return modelName;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public OptionSet getOpSet(String name) {
        return optionSetMap.get(name);
    }

    @Override
    public String toString() {
        return "Automobile " +
                "make='" + make + '\t' +
                ", modelName='" + modelName + '\t' +
                ", optionSetMap=" + optionSetMap.toString() +
                ", basePrice=" + basePrice ;

//        Iterator<String> iterator = optionSetMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            String key = iterator.next();
//            OptionSet optSetList = optionSetMap.get(key);
//            System.out.println(optSetList);
//        }
    }

    //Setters
    public void setMake(String make) {
        this.make = make;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    //CRUD
    //-------------------------------FIND METHODS-----------
    public OptionSet findOpset(String opSetName) {
        return optionSetMap.get(opSetName);
    }


    //------------------------CREATE METHODS---------------------------------------
    public void setOpsetValues(String name) {
        OptionSet opset = new OptionSet(name);
        optionSetMap.put(name, opset);
    }

    public void setOptionChoice(String opsetName, String optionName) {
        optionSetMap.get(opsetName).setOptionChoice(optionName);
    }


    //------------------------UPDATE METHODS---------------------------------------
    public void updateOptionSetName(String opsetName, String newName) {
        optionSetMap.get(opsetName).setName(newName);
    }

    public void updateOptionPrice(String opSetName, String option, float newPrice) {
        optionSetMap.get(opSetName).updateOptionPrice(option, newPrice);
    }

    public String print() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Make: ");
        stringBuffer.append(make);
        stringBuffer.append("\nModel: ");
        stringBuffer.append(modelName);
        stringBuffer.append("\nBasePrice: $");
        stringBuffer.append(basePrice);

        Iterator<String> iterator = optionSetMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            OptionSet optSetList = optionSetMap.get(key);
            stringBuffer.append("\n");
            stringBuffer.append(optSetList.print());
        }

        return stringBuffer.toString();
    }

    public void setOptionValue(String opSetName, String optionName, Float optionPrice){
        optionSetMap.get(opSetName).setOptionValue(optionName, optionPrice);

    }

    public String getOptionChoice(String optionChoiceName) {
        return(optionSetMap.get(optionChoiceName).getOptions());
    }

    public float getOptionChoicePrice(String opsetName, String optionName) {
        return (optionSetMap.get(opsetName).getOptionChoicePrice(optionName));
    }

    public void deleteOption(String opSetName, String optionName) {
        optionSetMap.get(opSetName).deleteOpt(optionName);
    }
}