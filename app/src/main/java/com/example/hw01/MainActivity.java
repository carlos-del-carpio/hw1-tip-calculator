package com.example.hw01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText userEnteredBillValue = findViewById(R.id.bill_value);
        RadioGroup tipRadioGroup = findViewById(R.id.tip_percent_options);
        SeekBar customTipSeekBar = findViewById(R.id.custom_tip);
        TextView customTipValue = findViewById(R.id.custom_tip_value);
        TextView tipTotal = findViewById(R.id.tip_value);
        TextView billTotal = findViewById(R.id.bill_total);

        userEnteredBillValue.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(userEnteredBillValue.getText() == null) {
                    tipTotal.setText("");
                    billTotal.setText("");
                } else {
                    tipTotal.setText(userEnteredBillValue.getText().toString());
                }
                return false;
            }
        });
    }
}