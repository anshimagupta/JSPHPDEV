package adapter;

/**
 * Created by anshima on 6/12/15.
 */
public class BuildAuto extends ProxyAutomobile implements  CreateAuto, UpdateAuto {

    @Override
    public void updateOptionPrice(String modelName, String optionName, String Option, float newPrice) {

    }
}
