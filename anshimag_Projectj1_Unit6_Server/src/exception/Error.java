package exception;

public enum Error {

    FILE_ABSENT(0, "File name is missing"),
    MAKE_ABSENT(1, "Make of the Automobile is missing"),
    MODEL_ABSENT(2, "Model of the Automobile is missing"),
    BASEPRICE_ABSENT(3, "BasePrice of the Automobile is missing"),
    OPTIONS_ABSENT(4, "One or more Options of the Automobile is missing"),
    MATCH_NOT_FOUND(5, "The option name you have typed does not match existing Options");

    private final int code;
    private final String description;

    Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
