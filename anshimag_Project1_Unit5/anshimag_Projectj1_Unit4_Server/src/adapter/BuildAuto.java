package adapter;

import model.Automobile;
import server.AutoServer;

/**
 *
 */
public class BuildAuto extends ProxyAutomobile implements UpdateAuto, CreateOption, CreateAuto, AutoServer {

    @Override
    public void buildAuto(String modelName, String fileName, String fileType) {

    }

    @Override
    public float getOptionChoicePrice(String modelName, String opsetName) {
        return 0;
    }
}
