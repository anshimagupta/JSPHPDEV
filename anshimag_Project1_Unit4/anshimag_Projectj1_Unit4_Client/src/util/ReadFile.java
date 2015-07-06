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
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
        automobile.setModelName(autoModel);

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
                    automobile.setOptionValue(opsetName, optName, optPrice);
                }
            }
        }
        bufferedReader.close();
        return automobile;
    }

    /**
     * This method loads the properties into memory
     * from a file and then returns the Automobile
     * Object.
     * @param properties object
     * @return
     * @throws IOException
     */
    public Automobile buildAutomobileFromProperties(Properties properties) throws IOException {
        String carMake = properties.getProperty("CarMake");
        if (!carMake.equals(null)) {
            String carModel = properties.getProperty("CarModel");
            float basePrice = Float.parseFloat(properties.getProperty("BasePrice"));
            String option1 = properties.getProperty("Option1");
            String optionValue1a = properties.getProperty("OptionValue1a");
            float optionValue1aPrice = Float.parseFloat(properties.getProperty("OptionValue1aPrice"));
            String optionValue1b = properties.getProperty("OptionValue1b");
            float optionValue1bPrice = Float.parseFloat(properties.getProperty("OptionValue1bPrice"));

            String option2 = properties.getProperty("Option2");
            String optionValue2a = properties.getProperty("OptionValue2a");
            float optionValue2aPrice = Float.parseFloat(properties.getProperty("OptionValue2aPrice"));
            String optionValue2b = properties.getProperty("OptionValue2b");
            float optionValue2bPrice = Float.parseFloat(properties.getProperty("OptionValue2bPrice"));


//            String option3 = properties.getProperty("Option3");
//            String optionValue3a = properties.getProperty("OptionsValue3a");
//            float optionValue3aPrice = Float.parseFloat(properties.getProperty("OptionValue3aPrice"));
//            String optionValue3b = properties.getProperty("OptionsValue3b");
//            float optionValue3bPrice = Float.parseFloat(properties.getProperty("OptionValue3bPrice"));
//
//            String option4 = properties.getProperty("Option4");
//            String optionValue4a = properties.getProperty("OptionsValue4a");
//            float optionValue4aPrice = Float.parseFloat(properties.getProperty("OptionValue4aPrice"));
//            String optionValue4b = properties.getProperty("OptionsValue1b");
//            float optionValue4bPrice = Float.parseFloat(properties.getProperty("OptionValue4bPrice"));
//
//            String option5 = properties.getProperty("Option5");
//            String optionValue5a = properties.getProperty("OptionsValue5a");
//            float optionValue5aPrice = Float.parseFloat(properties.getProperty("OptionValue5aPrice"));
//            String optionValue5b = properties.getProperty("OptionsValue5b");
//            float optionValue5bPrice = Float.parseFloat(properties.getProperty("OptionValue5bPrice"));
//            String optionValue5c = properties.getProperty("OptionsValue5c");
//            float optionValue5cPrice = Float.parseFloat(properties.getProperty("OptionValue5cPrice"));

            automobile.setModelName(carModel);
            automobile.setMake(carMake);
            automobile.setBasePrice(basePrice);
            automobile.setOpsetValues(option1);
            automobile.setOptionValue(option1, optionValue1a, optionValue1aPrice);
            automobile.setOptionValue(option1, optionValue1b, optionValue1bPrice);

            automobile.setOpsetValues(option2);
            automobile.setOptionValue(option2, optionValue2a, optionValue2aPrice);
            automobile.setOptionValue(option2, optionValue2b, optionValue2bPrice);

//            automobile.setOpsetValues(option3);
//            automobile.setOptionValue(option3, optionValue3a, optionValue3aPrice);
//            automobile.setOptionValue(option3, optionValue3b, optionValue3bPrice);
//
//            automobile.setOpsetValues(option4);
//            automobile.setOptionValue(option4, optionValue4a, optionValue4aPrice);
//            automobile.setOptionValue(option4, optionValue4b, optionValue4bPrice);
//
//            automobile.setOpsetValues(option5);
//            automobile.setOptionValue(option5, optionValue5a, optionValue5aPrice);
//            automobile.setOptionValue(option5, optionValue5b, optionValue5bPrice);
//            automobile.setOptionValue(option5, optionValue5c, optionValue5cPrice);

        }
        return automobile;
    }

}