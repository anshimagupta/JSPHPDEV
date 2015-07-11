package com.example.anshimag.mortgagecalculator;
/**
 * Class for displaying the Values calculated
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.anshimag.mortgagecalculator.database.DBHelper;
import com.example.anshimag.mortgagecalculator.model.Mortgage;

import java.util.Arrays;
import java.util.List;

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        Double monthlyPayment = intent.getDoubleExtra(InputsActivity.MONTHLY_PAYMENT, 0);
        TextView resultTextView = (TextView) findViewById(R.id.monthlyPayment);
        resultTextView.setText(String.valueOf(monthlyPayment));

        Double allPayment = intent.getDoubleExtra(InputsActivity.TOTAL_PAYMENT, 0);
        resultTextView = (TextView) findViewById(R.id.totalPayment);
        resultTextView.setText(String.valueOf(allPayment));

        String payOffYear = intent.getStringExtra(InputsActivity.PAY_OFF_DATE);
        resultTextView = (TextView) findViewById(R.id.payOffDate);
        resultTextView.setText(payOffYear);

        List<Mortgage> mortgageList = DBHelper.getInstance(this).getAllMortgages();
        System.out.println(Arrays.toString(mortgageList.toArray()));
        resultTextView = (TextView) findViewById(R.id.allMortgagesText);
        resultTextView.setText(Arrays.toString(mortgageList.toArray()));
    }
}
