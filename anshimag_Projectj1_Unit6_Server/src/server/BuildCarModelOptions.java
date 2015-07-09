package server;

import adapter.ProxyAutomobile;
import model.Automobile;

public class BuildCarModelOptions extends ProxyAutomobile implements AutoServer{
    public void addCarModelOptions(String modelName, Automobile automobile) {
        addCarModelOptions(modelName, automobile);
    }

    public String listAllModelNames() {
        return listAllModelNames();
    }

    public Automobile printSelectedAuto(String modelName) {

        return printSelectedAuto(modelName);
    }
}

