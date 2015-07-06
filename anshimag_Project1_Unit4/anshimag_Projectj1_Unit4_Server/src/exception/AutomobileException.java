package exception;
/**
 * This class handles custom exceptions
 */

import driver.Driver;

public class AutomobileException extends Exception implements FixAuto {
    private final Error error;

    public AutomobileException(Error error) {
        this.error = error;
        System.out.println("");
        System.err.println(error.toString() + " TS:" + System.currentTimeMillis());
        Driver.printWriter.println(error.toString() + " TS:" + System.currentTimeMillis());
    }

    @Override
    public String fix() {
        Fixes fixes = new Fixes();

        switch (error) {
            case BASEPRICE_ABSENT:
                return fixes.basePriceAbsent();
            case FILE_ABSENT:
                return fixes.fileAbsent();
            case MAKE_ABSENT:
                return fixes.makeAbsent();
            case MODEL_ABSENT:
                return fixes.modelAbsent();
            case OPTIONS_ABSENT:
                return fixes.optionsAbsent();
            case MATCH_NOT_FOUND:
                return fixes.matchNotFound();
            default:
                return fixes.defaultFix();
        }
    }
}
