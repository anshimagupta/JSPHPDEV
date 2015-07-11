package com.example.anshimag.mortgagecalculator;
/**
 * This class is for Calculating the fields
 * as per the formula
 */
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MortgageCalculator {
    public static final int ANNUAL = 12;

    public static double calculateMonthlyPayment(
            double loanAmount, int termInYears, double interestRate) {

        interestRate /= 100.0;
        double monthlyRate = interestRate /ANNUAL;
        int termInMonths = termInYears * ANNUAL;
        double monthlyPayment =
                (loanAmount * monthlyRate) /
                        (1 - Math.pow(1 + monthlyRate, -termInMonths));
        return monthlyPayment;
    }

    public static double calculateAllPayment(double monthlyPayment,
                                             Integer mortgageTerm, Float downPayment,
                                             Float propertyInsurance, Float propertyTax) {
        double allPayment = downPayment + ((monthlyPayment * ANNUAL * mortgageTerm) - downPayment)
                + propertyInsurance * ANNUAL + propertyTax * ANNUAL;
        return allPayment;
    }

    public static Date calculatePayOffDate(String firstPaymentDate, int mortgageTerm) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = firstPaymentDate;
        Date date = formatter.parse(dateInString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, mortgageTerm);
        Date newDate = calendar.getTime();
        return newDate;
    }

    /**
     * Clear all the text fields
     * @param group
     */
    public static void clearForm(ViewGroup group)
    {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }

}