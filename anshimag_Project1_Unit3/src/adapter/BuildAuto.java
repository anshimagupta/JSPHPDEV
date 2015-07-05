package adapter;

/**
 *
 */
public class BuildAuto extends ProxyAutomobile implements EditConcurrently, UpdateAuto, CreateOption, CreateAuto {

    @Override
    public float getOptionChoicePrice(String modelName, String opsetName) {
        return 0;
    }


}
