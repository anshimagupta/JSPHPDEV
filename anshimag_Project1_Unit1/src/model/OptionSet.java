package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class OptionSet implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Option options[];

    protected OptionSet() {
        super();
    }

    protected OptionSet(String name, int size) {
        this.name = name;
        this.options = new Option[size];
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected Option[] getOptions() {
        return options;
    }

    protected void setOpt(int index, Option opt) {
        this.options[index] = opt;
    }

    @SuppressWarnings("serial")
    public class Option implements Serializable {
        private String name;
        float price;

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

        @Override
        public String toString() {
            return
            name + " price = $" + price + '\n';
        }


    }

    protected void setOptionValues(int optIndex, String name,
                                   float price) {
        Option option = new Option(name, price);
        this.setOpt(optIndex, option);
    }

    //Delete Options
    public void deleteOpt(int optNameIndex) {
            this.getOptions()[optNameIndex] = null;

    }

    //Update Price of the optName
    protected void updateOpt(String optName, float price) {
        for (int j = 0; j < this.getOptions().length; j++) {
            if (this.getOptions()[j].getName().equals(optName)) {
                this.getOptions()[j].setPrice(price);
                break;
            }
        }
    }

    public Option findOptWithName(String optName) {
        int optIndex = -1;

        for (int j = 0; j < getOptions().length; j++) {
            if (getOptions()[j].getName().equals(optName)) {
                optIndex = j;
                break;
            }
        }
        return getOptions()[optIndex];
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "\n" + "\n" + name  + Arrays.toString(options);
    }

    protected void updateOptionSetValues() {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < options.length; i++) {
            options[i].setName(scanner.nextLine());
        }

    }


}
