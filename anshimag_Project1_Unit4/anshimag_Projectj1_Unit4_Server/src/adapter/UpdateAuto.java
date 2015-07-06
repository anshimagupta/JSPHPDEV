package adapter;

/**
 * Created by anshima on 6/12/15.
 */
public interface UpdateAuto {
    public void updateOptionSetName(String modelName, String optionSetName, String
            newName);

    public void updateOptionPrice(String modelName, String optionName, String
            Option, float newPrice);
}
