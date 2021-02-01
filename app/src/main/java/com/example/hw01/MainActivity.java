package com.example.hw01;

import androidx.annotation.experimental.Experimental;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
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

import org.w3c.dom.Text;

import java.util.EmptyStackException;

public class MainActivity extends AppCompatActivity {
    String TAG;
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

        //Setting some default attributes
        tipRadioGroup.check(R.id.ten_percent);
        customTipSeekBar.setMax(50);
        customTipSeekBar.setProgress(20);
        customTipValue.setText(String.valueOf(customTipSeekBar.getProgress()) + " %");


        /**
         * Event listener to update tip and total when bill total is updated and preset tip % are selected
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
                    Double tipAmount = 0.0;
                    Double total = 0.0;
                    final Double tenPercentTip = 0.10;
                    final Double fifteenPercentTip = 0.15;
                    final Double eighteenPercentTip = 0.18;

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

                    tipAmount *= Double.valueOf(String.valueOf(userEnteredBillValue.getText()));
                    total = Double.valueOf(String.valueOf(userEnteredBillValue.getText())) + tipAmount;
                    tipTotal.setText(String.valueOf(tipAmount));
                    billTotal.setText(String.valueOf(total));

                } catch (Exception e) {
                    tipTotal.setText("");
                    billTotal.setText("");
                    Toast.makeText(MainActivity.this, "Enter Bill Total", Toast.LENGTH_SHORT).show();
                }
            }
        });


        /**
         * Event listener to update tip and total with seekbar updates
         */
        customTipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            Double tipAmount = 0.0;
            Double total = 0.0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    tipRadioGroup.check(R.id.custom_option);
                    customTipValue.setText(String.valueOf(customTipSeekBar.getProgress()) + " %");
                    tipAmount = Double.valueOf(String.valueOf(customTipSeekBar.getProgress())) / 100;

                    tipAmount *= Double.valueOf(String.valueOf(userEnteredBillValue.getText()));
                    total = Double.valueOf(String.valueOf(userEnteredBillValue.getText())) + tipAmount;
                    tipTotal.setText(String.valueOf(tipAmount));
                    billTotal.setText(String.valueOf(total));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Enter Bill Total", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        /**
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