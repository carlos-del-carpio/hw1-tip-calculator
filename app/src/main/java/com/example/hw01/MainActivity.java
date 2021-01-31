package com.example.hw01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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

        //Setting some default attributes
        tipRadioGroup.check(R.id.ten_percent);
        customTipSeekBar.setMax(50);
        customTipSeekBar.setProgress(20);
        customTipValue.setText(String.valueOf(customTipSeekBar.getProgress()) + " %");


        /**
         * Event listener to update tip and total when bill total is updated
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

        customTipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}