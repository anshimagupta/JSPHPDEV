package adapter;

public interface CreateOption {
    String getOptionChoice(String modelName, String opsetName);

    float getOptionChoicePrice(String modelName, String opsetName);

    void setOptionChoice(String modelName, String opsetName, String optionName);
}
