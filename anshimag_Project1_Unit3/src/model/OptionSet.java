package model;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable {
    private String name;
    private ArrayList<Option> options;
    private Option optionChoice;


    protected OptionSet() {
        super();
        name = null;
        options = new ArrayList<Option>();
    }

    protected OptionSet(String name) {
        this.name = name;
        options = new ArrayList<Option>();

    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getOptions() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Option option : options) {
            stringBuilder.append(option.getName());
        }
        return stringBuilder.toString();
    }

    protected void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    protected Option getOptionChoice() {
        return optionChoice;
    }

    protected void setOptionChoice(String name) {
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getName().equals(name)) {
                this.optionChoice = options.get(i);
            }
        }
    }


    protected String print() {
        StringBuffer stringBuffer = new StringBuffer(name);
        for (int i = 0; i < options.size(); i++) {
            stringBuffer.append("\n");
            stringBuffer.append("\t");
            stringBuffer.append(options.get(i).print());
        }
        return stringBuffer.toString();
    }

    public Float getOptionChoicePrice(String optionName) {
        for (Option option : options) {
            if(option.getName().equals(optionName)) {
                return option.getPrice();
            }
        }

        return null;
    }


    private class Option implements Serializable {
        private String name;
        private float price;

        public Option(String name, float price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        protected void setName(String name) {
            this.name = name;
        }

        protected float getPrice() {
            return price;
        }

        protected void setPrice(float price) {
            this.price = price;
        }

        protected String print() {
            StringBuffer stringBuffer = new StringBuffer(name);
            stringBuffer.append(" - Price: $");
            stringBuffer.append(price);
            return stringBuffer.toString();
        }

    }

    public void setOptionValue(String name, float price) {
        Option opt = new Option(name, price);
        options.add(opt);
    }

    public void deleteOpt(String optName) {
        for (Option option : options) {
            if (option.getName().equals(optName)) {
                option = null;
            }
        }
    }

    public void updateOptionPrice(String optName, float newPrice) {
        for (Option option : options) {
            if(option.getName().equals(optName))
                option.setPrice(newPrice);
        }
    }

    public Option findOpt(String optName) {
        for (Option option : options) {
            if(option.getName().equals(optName)){
                return option;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "OptionSet{" +
                "name='" + name + '\'' +
                ", options=" + options +
                ", optionChoice=" + optionChoice +
                '}';
    }
}
