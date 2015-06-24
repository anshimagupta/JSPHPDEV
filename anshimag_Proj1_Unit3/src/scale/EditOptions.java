package scale;

import adapter.EditConcurrently;

public class EditOptions extends Thread {

    private final String modelName;
    private final EditConcurrently editConcurrently;
    private final int operation;

    public enum operationToPerform {
        EDITOPSETNAME,
        EDITOPTION
    }

    public EditOptions(int operation, EditConcurrently editConcurrently) {
        this.modelName = "Mustang";
        this.operation = operation;
        this.editConcurrently = editConcurrently;
    }

    public void run() {
        switch (operation) {
            case 1:
                editConcurrently.updateOpsetNameCon(modelName);
                break;
            case 2:
                editConcurrently.updateOptionCon(modelName);
                break;
        }
    }

}
