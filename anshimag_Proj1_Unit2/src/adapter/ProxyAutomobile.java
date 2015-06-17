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

    //Automobile saved as LinkedHashMap
    private LinkedHashMap<String, Automobile> autoMobileShop = new LinkedHashMap<String, Automobile>();

    public void buildAuto(String modelName, String fileName) {
        ReadFile reader = new ReadFile();
        try {
            autoMobileShop.put(modelName, reader.buildAutomobileObject(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAuto(String modelName){
        System.out.println(autoMobileShop.get(modelName).print());
    }

    public void updateOptionSetName(String modelName, String opsetName, String newName){
        autoMobileShop.get(modelName).updateOptionSetName(opsetName, newName);
    }

    public void updateOptionPrice(String modelName, String opsetName, String optName, float newPrice){
        autoMobileShop.get(modelName).updateOptionPrice(opsetName, optName, newPrice);
    }

    public void setOptionChoice(String modelName, String opsetName, String optName){
        autoMobileShop.get(modelName).setOptionChoice(opsetName, optName);
    }

    public String getOptionChoice(String modelName, String opsetName){
        return autoMobileShop.get(modelName).getOptionChoice(opsetName);
    }

    public float getOptionChoicePrice(String modelName, String opsetName){
        return autoMobileShop.get(modelName).getOptionChoicePrice(opsetName);
    }
}
