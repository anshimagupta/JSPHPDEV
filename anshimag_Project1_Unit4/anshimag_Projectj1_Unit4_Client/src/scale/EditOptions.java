package scale;

public class EditOptions extends Thread {

    private String modelName;
    private EditConcurrently editConcurrently;
    private int operation;

    public EditOptions(int operation, EditConcurrently editConcurrently) {
        this.modelName = "Mustang";
        this.operation = operation;
        this.editConcurrently = editConcurrently;
    }

    public void run() {
        switch (operation) {
            case 1:
                editConcurrently.updateOpSetNameCon(modelName);
                break;
            case 2:
                editConcurrently.updateOptionPriceCon(modelName);
                break;
        }
    }

}
