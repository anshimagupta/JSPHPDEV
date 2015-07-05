package adapter;

/**
 * Created by anshima on 6/12/15.
 */

import model.Automobile;
import util.ReadFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;

public abstract class ProxyAutomobile implements Serializable{

    //Automobile models saved as LinkedHashMap
    private LinkedHashMap<String, Automobile> automobileCollection = new LinkedHashMap<String, Automobile>();

    public void buildAuto(String modelName, String fileName) {
        ReadFile reader = new ReadFile();
        try {
            automobileCollection.put(modelName, reader.buildAutomobileObject(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildAuto(String fileName) {

    }

    public void printAuto(String modelName){
        System.out.println(automobileCollection.get(modelName).print());
    }

    public void updateOptionSetName(String modelName, String opsetName, String newName){
        automobileCollection.get(modelName).updateOptionSetName(opsetName, newName);
    }

    public void updateOptionPrice(String modelName, String opsetName, String opName, float newPrice){
        automobileCollection.get(modelName).updateOptionPrice(opsetName, opName, newPrice);
    }

    public void setOptionChoice(String modelName, String opsetName, String optName){
        automobileCollection.get(modelName).setOptionChoice(opsetName, optName);
    }

    public String getOptionChoice(String modelName, String opsetName){
        return automobileCollection.get(modelName).getOptionChoice(opsetName);
    }

    public float getOptionChoicePrice(String modelName, String opsetName, String optionName){
        return automobileCollection.get(modelName).getOptionChoicePrice(opsetName, optionName);
    }
}
