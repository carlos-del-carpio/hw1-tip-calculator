/**
 * Assignment #: HW01
 * File name: hw01.zip
 * Student: Carlos Del Carpio
 */

package com.example.hw01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    EditText userEnteredBillValue;
    RadioGroup tipRadioGroup;
    SeekBar customTipSeekBar;
    TextView customTipValue;
    TextView tipTotal;
    TextView billTotal;
    Button exit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEnteredBillValue = findViewById(R.id.bill_value);
        tipRadioGroup = findViewById(R.id.tip_percent_options);
        customTipSeekBar = findViewById(R.id.custom_tip);
        customTipValue = findViewById(R.id.custom_tip_value);
        tipTotal = findViewById(R.id.tip_value);
        billTotal = findViewById(R.id.bill_total);
        exit_button = findViewById(R.id.exit_button);

        //Setting default attributes
        tipRadioGroup.check(R.id.ten_percent);
        customTipSeekBar.setMax(50);
        customTipSeekBar.setProgress(20);
        customTipValue.setText(customTipSeekBar.getProgress() + String.format(getResources().getString(R.string.space_percent)));


        /*
          Event listener to update tip and total when bill total is updated and preset tip % are selected
         */
        userEnteredBillValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    double tipAmount = 0.0;
                    double total = 0.0;
                    final double tenPercentTip = 0.10;
                    final double fifteenPercentTip = 0.15;
                    final double eighteenPercentTip = 0.18;

                    /* Switches on the different radio buttons user can choose and sets tip percent */
                    switch (tipRadioGroup.getCheckedRadioButtonId()) {
                        case R.id.ten_percent :
                            tipAmount = tenPercentTip;
                            break;
                        case R.id.fifteen_percent :
                            tipAmount = fifteenPercentTip;
                            break;
                        case R.id.eighteen_percent :
                            tipAmount = eighteenPercentTip;
                            break;
                        case R.id.custom_option :
                            tipAmount = Double.parseDouble(String.valueOf(customTipSeekBar.getProgress())) / 100;
                    }

                    tipAmount *= Double.parseDouble(String.valueOf(userEnteredBillValue.getText()));
                    total = Double.parseDouble(String.valueOf(userEnteredBillValue.getText())) + tipAmount;
                    tipTotal.setText(String.valueOf(tipAmount));
                    billTotal.setText(String.valueOf(total));

                } catch (Exception e) {
                    tipTotal.setText("");
                    billTotal.setText("");
                    Toast.makeText(MainActivity.this, String.format(getResources().getString(R.string.enter_bill_total)), Toast.LENGTH_SHORT).show();
                }
            }
        });


        /*
         * Event listener to update tip and total with SeekBar updates
         */
        customTipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            Double tipAmount = 0.0;
            Double total = 0.0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    customTipValue.setText(customTipSeekBar.getProgress() + String.format(getResources().getString(R.string.space_percent)));
                    tipAmount = Double.parseDouble(String.valueOf(customTipSeekBar.getProgress())) / 100;

                    tipAmount *= Double.parseDouble(String.valueOf(userEnteredBillValue.getText()));
                    total = Double.parseDouble(String.valueOf(userEnteredBillValue.getText())) + tipAmount;
                    tipTotal.setText(String.valueOf(tipAmount));
                    billTotal.setText(String.valueOf(total));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, String.format(getResources().getString(R.string.enter_bill_total)), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /*
         * Event listener to update tip and total when user selects different radio buttons
         */
        tipRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                try {
                    double tipAmount = 0.0;
                    double total = 0.0;
                    final double tenPercentTip = 0.10;
                    final double fifteenPercentTip = 0.15;
                    final double eighteenPercentTip = 0.18;

                    /* Switches on the different radio buttons user can choose and sets tip percent */
                    switch (checkedId) {
                        case R.id.ten_percent :
                            tipAmount = tenPercentTip;
                            break;
                        case R.id.fifteen_percent :
                            tipAmount = fifteenPercentTip;
                            break;
                        case R.id.eighteen_percent :
                            tipAmount = eighteenPercentTip;
                            break;
                        case R.id.custom_option :
                            tipAmount = Double.parseDouble(String.valueOf(customTipSeekBar.getProgress())) / 100;
                    }

                    tipAmount *= Double.parseDouble(String.valueOf(userEnteredBillValue.getText()));
                    total = Double.parseDouble(String.valueOf(userEnteredBillValue.getText())) + tipAmount;
                    tipTotal.setText(String.valueOf(tipAmount));
                    billTotal.setText(String.valueOf(total));

                } catch (Exception e) {
                    tipTotal.setText("");
                    billTotal.setText("");
                    Toast.makeText(MainActivity.this, String.format(getResources().getString(R.string.enter_bill_total)), Toast.LENGTH_SHORT).show();
                }
            }
        });


        /*
         * On click listener to exit app to home screen.
         */
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
    }
}