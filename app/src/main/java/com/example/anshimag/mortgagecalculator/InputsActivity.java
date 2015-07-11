package com.example.anshimag.mortgagecalculator;
/**
 *
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anshimag.mortgagecalculator.database.DBHelper;
import com.example.anshimag.mortgagecalculator.model.Mortgage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;

public class InputsActivity extends Activity {
    public static final String MONTHLY_PAYMENT = "monthlyPayment";
    public static final String TOTAL_PAYMENT = "totalPayment";
    public static final String PAY_OFF_DATE = "payOffYear";
    private static final int ANNUAL = 12;
    private EditText mortgageTermEditText;
    private EditText downPaymentEditText;
    private EditText purchasePriceEditText;
    private EditText interestRateEditText;
    private EditText firstPaymentDateEditText;
    private EditText propertyInsuranceEditText;
    private EditText propertyTaxEditText;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputs);
        init();
    }

    private void init() {
        purchasePriceEditText = (EditText) findViewById(R.id.purchasePrice);
        mortgageTermEditText = (EditText) findViewById(R.id.mortgageTerm);
        downPaymentEditText = (EditText) findViewById(R.id.downPayment);
        interestRateEditText = (EditText) findViewById(R.id.interestRate);
        firstPaymentDateEditText = (EditText) findViewById(R.id.firstPaymentDate);
        propertyInsuranceEditText = (EditText) findViewById(R.id.propertyInsurance);
        propertyTaxEditText = (EditText) findViewById(R.id.propertyTax);
        calculate = (Button) findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (purchasePriceEditText.getText().toString().length() == 0) {
                    Toast.makeText(InputsActivity.this, "You did not enter Purchase Price", LENGTH_SHORT).show();
                }
                else if(downPaymentEditText.getText().toString().length() == 0) {
                    Toast.makeText(InputsActivity.this, "Enter Down Payment Percentage", LENGTH_SHORT).show();
                }
                else {
                    try {
                        calculateMortgage(v);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public void calculateMortgage(View view) throws ParseException {
        float purchasePrice = Float.parseFloat(purchasePriceEditText.getText().toString());
            Float downPaymentRate = Float.parseFloat(downPaymentEditText.getText().toString());
            Integer mortgageTerm = Integer.parseInt(mortgageTermEditText.getText().toString());
            Float interestRate = Float.parseFloat(interestRateEditText.getText().toString());
            Float propertyInsurance = Float.parseFloat(propertyInsuranceEditText.getText().toString());
            Float propertyTax = Float.parseFloat(propertyTaxEditText.getText().toString());
            Float downPayment = purchasePrice - ((downPaymentRate * purchasePrice) / 100);
            String firstPaymentDate = firstPaymentDateEditText.getText().toString();

            double monthlyPayment = MortgageCalculator.calculateMonthlyPayment(
                    downPayment, mortgageTerm, interestRate);
            double monthlyPaymentTotal = monthlyPayment + propertyInsurance / ANNUAL
                    + propertyTax / ANNUAL;

            double allPayment = MortgageCalculator.calculateAllPayment(
                    monthlyPayment, mortgageTerm, downPayment, propertyInsurance, propertyTax);

            Date payOffDate = MortgageCalculator.calculatePayOffDate(firstPaymentDate, mortgageTerm);
            String payOffYear = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
            try {
                payOffYear = simpleDateFormat.format(payOffDate);
            } catch (Exception ex) {
                System.out.println(ex);
            }
            Mortgage mortgage = new Mortgage(Double.toString(monthlyPayment),
                    Double.toString(allPayment), payOffYear);
            ViewGroup group = (ViewGroup) findViewById(android.R.id.content);
            MortgageCalculator.clearForm(group);

            DBHelper.getInstance(this).insertMortgage(mortgage);
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(MONTHLY_PAYMENT, monthlyPaymentTotal);
            intent.putExtra(TOTAL_PAYMENT, allPayment);
            intent.putExtra(PAY_OFF_DATE, payOffYear);
            startActivity(intent);
    }
}
