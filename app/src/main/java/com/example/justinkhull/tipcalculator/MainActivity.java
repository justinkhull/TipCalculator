package com.example.justinkhull.tipcalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //INPUT FIELDS
    private EditText bill_total_in;
    private EditText num_people_in;
    private EditText custom_tip_in;


    private RadioGroup tipGroup;
    private RadioButton fifteen;
    private RadioButton eighteen;
    private RadioButton twenty;
    private RadioButton custom;

    //BUTTONS
    private Button reset_button;
    private Button calculate_button;

    //TOTALS AT BOTTOM OF SCREEN
    private TextView tip_amount;
    private TextView total_amount;
    private TextView total_per_person;
    private TextView final_tip;
    private TextView final_total;
    private TextView final_each;

    private String displayEach;
    private String displayTip;
    private String displayTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DecimalFormat dollars = new DecimalFormat("#.00");

        //*************
        bill_total_in = (EditText) findViewById(R.id.bill_total_in);
        num_people_in = (EditText) findViewById(R.id.num_people_in);
        custom_tip_in = (EditText) findViewById(R.id.custom_tip_in);
        //custom_tip_in.setEnabled(false);

        tipGroup = (RadioGroup) findViewById(R.id.tipGroup);
        fifteen = (RadioButton) findViewById(R.id.fifteen);
        fifteen.setChecked(true);
        eighteen = (RadioButton) findViewById(R.id.eighteen);
        twenty = (RadioButton) findViewById(R.id.twenty);
        custom = (RadioButton) findViewById(R.id.custom);

        reset_button = (Button) findViewById(R.id.reset_button);
        calculate_button = (Button) findViewById(R.id.calculate_button);

        tip_amount = (TextView) findViewById(R.id.tip_amount);
        total_amount = (TextView) findViewById(R.id.total_amount);
        total_per_person = (TextView) findViewById(R.id.total_per_person);
        final_tip = (TextView) findViewById(R.id.final_tip);
        final_total = (TextView) findViewById(R.id.final_total);
        final_each = (TextView) findViewById(R.id.final_each);



        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                //Context context = getApplicationContext();
                //Toast toast = Toast.makeText(context, "Reset Clicked", 3);
                //toast.show();
                bill_total_in.setText(null);
                num_people_in.setText(null);
                tipGroup.clearCheck();
                fifteen.setChecked(true);
           }
        });

        /*if (custom.isChecked()) {
            custom_tip_in.setEnabled(true);
        }*/

        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();

                //VALIDATE THAT THERE IS INPUT IN EDITTEXTS
                if (validate() == true) {

                    String tipPercent;
                    double billTotal = Double.parseDouble(bill_total_in.getText().toString());
                    int numPeople = Integer.parseInt(num_people_in.getText().toString());

                    double TIP;
                    double TOTAL;
                    double EACH;
                    if (fifteen.isChecked()) {
                        tipPercent = "0.15";
                        TIP = billTotal * Double.parseDouble(tipPercent);
                        displayTip = Double.toString(TIP);
                        final_tip.setText(displayTip);
                        TOTAL = billTotal + TIP;
                        displayTotal = Double.toString(TOTAL);
                        final_total.setText(displayTip);
                        EACH = TOTAL / numPeople;
                        String formatEach = Double.toString(EACH);
                        displayEach = dollars.format(EACH);
                        final_each.setText(displayEach);
                        //Toast tip = Toast.makeText(context, "TIP" + TIP, Toast.LENGTH_SHORT);
                        //tip.show();
                    } else if (eighteen.isChecked()) {

                        tipPercent = "0.18";
                        TIP = billTotal * Double.parseDouble(tipPercent);
                        displayTip = Double.toString(TIP);
                        final_tip.setText(displayTip);
                        TOTAL = billTotal + TIP;
                        displayTotal = Double.toString(TOTAL);
                        final_total.setText(displayTip);
                        EACH = TOTAL / numPeople;
                        String formatEach = Double.toString(EACH);
                        displayEach = dollars.format(EACH);
                        final_each.setText(displayEach);
                        //Toast tip = Toast.makeText(context, "TIP" + TIP, Toast.LENGTH_SHORT);
                        //tip.show();
                    } else if (twenty.isChecked()) {

                        tipPercent = "0.20";
                        TIP = billTotal * Double.parseDouble(tipPercent);
                        displayTip = Double.toString(TIP);
                        final_tip.setText(displayTip);
                        TOTAL = billTotal + TIP;
                        displayTotal = Double.toString(TOTAL);
                        final_total.setText(displayTip);
                        EACH = TOTAL / numPeople;
                        String formatEach = Double.toString(EACH);
                        displayEach = dollars.format(EACH);
                        final_each.setText(displayEach);
                        //Toast tip = Toast.makeText(context, "TIP" + TIP, Toast.LENGTH_SHORT);
                        //tip.show();
                    }
                    //IF CUSTOM TIP SELECTED
                    else if (custom.isChecked()) {
                        //Toast test = Toast.makeText(context, custom_tip_in.getText(), Toast.LENGTH_SHORT);
                        //test.show();

                        String tipEntry = (custom_tip_in.getText().toString());
                        if (TextUtils.isEmpty(tipEntry) == false) {


                            //String tipEntry = (custom_tip_in.getText().toString());
                            Double customTipPercent = Double.parseDouble(tipEntry) / 100;
                            //tipPercent = (custom_tip_in.getText().toString());
                            TIP = billTotal * customTipPercent;
                            displayTip = Double.toString(TIP);
                            final_tip.setText(displayTip);
                            TOTAL = billTotal + TIP;
                            displayTotal = Double.toString(TOTAL);
                            final_total.setText(displayTip);
                            EACH = TOTAL / numPeople;
                            String formatEach = Double.toString(EACH);
                            displayEach = dollars.format(EACH);
                            final_each.setText(displayEach);
                            //Toast tip = Toast.makeText(context, "TIP" + TIP, Toast.LENGTH_SHORT);
                            //tip.show();
                            //}
                        } else if (TextUtils.isEmpty(tipEntry)) {
                            String customTipError = getString(R.string.custim_tip_in_error);
                            custom_tip_in.setError(customTipError);
                            //Toast noCustomTipEntered = Toast.makeText(context, "Please enter a custom tip amount", Toast.LENGTH_SHORT);
                            //noCustomTipEntered.show();
                        }


                    } else {
                        Toast noTipSelected = Toast.makeText(context, context.getString(R.string.noTipSelected), Toast.LENGTH_SHORT);
                        noTipSelected.show();
                    }


                    //tipPercent = tipValue / 100;
                /*Toast tipPercentToast = Toast.makeText(context, "tip percent:" + Double.toString(tipPercent), Toast.LENGTH_SHORT);
                tipPercentToast.show();

                TIP = billTotal + (billTotal * tipPercent);
                final_total.setText(Double.toString(TIP));
                Toast tip = Toast.makeText(context, Double.toString(TIP), Toast.LENGTH_SHORT);
                tip.show();

                TOTAL = billTotal + TIP;
                total_amount.setText(Double.toString(TOTAL));

                EACH = TOTAL / numPeople;
                final_each.setText(Double.toString(EACH));*/


                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("savedEach", displayEach);
        savedInstanceState.putString("savedTip", displayTip);
        savedInstanceState.putString("savedTotal", displayTotal);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String displayEach = savedInstanceState.getString("savedEach");
        final_each.setText(displayEach);
        String displayTip = savedInstanceState.getString("savedTip");
        final_tip.setText(displayTip);
        String displayTotal = savedInstanceState.getString("savedTotal");
        final_total.setText(displayTotal);
    }

    public boolean validate() {
        boolean isValid = true;

        String billTotalValid = bill_total_in.getText().toString();
        //found another way to implement error messages at https://stackoverflow.com/questions/6290531/check-if-edittext-is-empty (Answer by"MilapTank")
        if (TextUtils.isEmpty(billTotalValid)){
            String totalError = getString(R.string.bill_total_in_error);
            bill_total_in.setError(totalError);
            isValid = false;
        }
        else {
            Double billTotalValue = Double.parseDouble(billTotalValid);
            if (billTotalValue < 1) {
                String totalError = getString(R.string.bill_total_in_error);
                bill_total_in.setError(totalError);
                isValid = false;
            }
        }


        String numPeopleValid = num_people_in.getText().toString();
        //Double numPeopleValue = Double.parseDouble(numPeopleValid);
        if (TextUtils.isEmpty(numPeopleValid)) {
            String numPeopleError = getString(R.string.num_people_in_error);
            num_people_in.setError(numPeopleError);
            isValid = false;
        }
        /*else if (numPeopleValue < 1) {
            String numPeopleError = getString(R.string.num_people_in_error);
            num_people_in.setError(numPeopleError);
            isValid = false;
        }*/

        return isValid;
    }
}
