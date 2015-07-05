package util;
/**
 * Parse the file and split on delimiters
 * Each option is separated by , and the
 * price by '='
 */

import exception.AutomobileException;
import exception.Error;
import model.Automobile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static exception.Error.FILE_ABSENT;

public class ReadFile {
    private final static int FIRSTINDEX = 0;
    private final static int SECONDINDEX = 1;

    private Automobile automobile = new Automobile();

    public Automobile buildAutomobileObject(String fileName) throws IOException {
        try {
            if (fileName.length() == 0)
                throw new AutomobileException(FILE_ABSENT);
        } catch (AutomobileException e) {
            e.fix();
        }

        FileReader file = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(file);
        boolean eof = false;

        //Field Make
        String make;
        String autoMake = bufferedReader.readLine();
        String[] makeArray = autoMake.split(":");

        try {
            if (makeArray[FIRSTINDEX].equals("Make") && makeArray.length != 2) {
                throw new AutomobileException(Error.MAKE_ABSENT);
            } else {
                make = makeArray[1];
            }
        } catch (AutomobileException e) {
            make = e.fix();
        }
        automobile.setMake(make);


        //Field Model
        String modelName;
        String model = bufferedReader.readLine();
        String[] modelArray = model.split(":");

        try {
            if (modelArray[FIRSTINDEX].equals("Model Name") && modelArray.length != 2) {
                throw new AutomobileException(Error.MODEL_ABSENT);
            } else {
                modelName = modelArray[1];
            }
        } catch (AutomobileException e) {

            modelName = e.fix();
        }
        automobile.setModelName(modelName);

        //Field Baseprice
        float autoBasePrice;
        String basePrice = bufferedReader.readLine();
        String[] basePriceArray = basePrice.split(":");
        try {
            if (basePriceArray[0].equals("BasePrice") && basePriceArray.length != 2)
                throw new AutomobileException(Error.BASEPRICE_ABSENT);
            else {
                autoBasePrice = Float.parseFloat(basePrice.split(":")[1]);
            }
        } catch (AutomobileException e) {
            autoBasePrice = Float.parseFloat(e.fix());

        }
        automobile.setBasePrice(autoBasePrice);

        while (!eof) {
            String line = bufferedReader.readLine();
            if (line == null) {
                eof = true;
            } else {
                String[] opSet = line.split(":");
                String opSetName =  opSet[FIRSTINDEX];
                automobile.setOpsetValues(opSetName);
                //Extract the options from the Option Set
                String[] options =  opSet[SECONDINDEX].split(";");
                for (int optIndex = 0; optIndex < options.length; optIndex++) {
                    String[] opsetSplit = options[optIndex].split("=");
                    String optionName = opsetSplit[FIRSTINDEX];
                    Float optionPrice;
                    try {
                        if (opsetSplit.length != 2)
                            throw new AutomobileException(Error.OPTIONS_ABSENT);
                        else
                            optionPrice = Float.parseFloat(opsetSplit[SECONDINDEX]);
                    } catch (AutomobileException e) {
                        optionPrice = Float.parseFloat(e.fix());
                    }
                   automobile.setOptionValue(opSetName, optionName, optionPrice);
                }
            }
        }
        bufferedReader.close();
        return automobile;
    }

}