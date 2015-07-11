package com.example.anshimag.mortgagecalculator.model;

/**
 * This class is for setting the three fields
 * which are displayed in the next activity
 */
public class Mortgage {
    private String monthlyPayment;
    private String totalPayment;
    private String payOffDate;

    public Mortgage(String monthlyPayment, String totalPayment, String payOffDate) {
        this.monthlyPayment = monthlyPayment;
        this.totalPayment = totalPayment;
        this.payOffDate = payOffDate;
    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public String getPayOffDate() {
        return payOffDate;
    }

    @Override
    public String toString() {
        return " Monthly Payment='" + monthlyPayment + '\t' +
                " Total Payment='" + totalPayment + '\t' +
                " Pay Off Date='" + payOffDate + '\n' ;
    }
}
