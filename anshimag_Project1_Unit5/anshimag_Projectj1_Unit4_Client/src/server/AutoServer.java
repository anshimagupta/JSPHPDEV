package server;

import model.Automobile;

/**
 * Created by anshima on 6/27/15.
 */
public interface AutoServer {

    public void addCarModelOptions(String modelName, Automobile automobile);
    public String listAllModelNames();
    public Automobile printSelectedAuto(String modelName);

}
