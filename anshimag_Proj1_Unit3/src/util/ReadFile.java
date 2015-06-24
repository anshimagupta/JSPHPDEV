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

    private final Automobile automobile = new Automobile();

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
        String autoMake;
        String make = bufferedReader.readLine();
        String[] makeArray = make.split(":");

        try {
            if (makeArray[0].equals("Make") && makeArray.length != 2) {
                throw new AutomobileException(Error.MAKE_ABSENT);
            } else {
                autoMake = makeArray[1];
            }
        } catch (AutomobileException e) {
            autoMake = e.fix();
        }
        automobile.setMake(autoMake);


        //Field Model
        String autoModel;
        String model = bufferedReader.readLine();
        String[] modelArray = model.split(":");

        try {
            if (modelArray[0].equals("Model Name") && modelArray.length != 2) {
                throw new AutomobileException(Error.MODEL_ABSENT);
            } else {
                autoModel = modelArray[1];
            }
        } catch (AutomobileException e) {

            autoModel = e.fix();
        }
        automobile.setName(autoModel);

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
        automobile.setBaseprice(autoBasePrice);

        int opsetIndex = 0;
        while (!eof) {
            String line = bufferedReader.readLine();
            if (line == null) {
                eof = true;
            } else {
                String[] opset = line.split(":");
                String opsetName = opset[FIRSTINDEX];
                String[] opt = opset[SECONDINDEX].split(";");
                automobile.setOpsetValues(opsetName);
                for (String anOpt : opt) {
                    String[] opsetSplit = anOpt.split("=");
                    String optName = opsetSplit[FIRSTINDEX];
                    String opsetString;
                    try {
                        if (opsetSplit.length != 2)
                            throw new AutomobileException(Error.OPTIONS_ABSENT);
                        else
                            opsetString = opsetSplit[SECONDINDEX];
                    } catch (AutomobileException e) {

                        opsetString = e.fix();
                    }
                    float optPrice = Float.parseFloat(opsetString);
                    automobile.setOptionValues(opsetName, optName, optPrice);
                }
                ++opsetIndex;
            }
        }
        bufferedReader.close();
        return automobile;
    }

}