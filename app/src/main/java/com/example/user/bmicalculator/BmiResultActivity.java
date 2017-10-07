package com.example.user.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BmiResultActivity extends AppCompatActivity {

    private TextView t ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);

        t = (TextView) findViewById(R.id.text_view);

        Intent intent = getIntent();
        Double bmi = intent.getDoubleExtra("bmi_value", 0);
        String bmiText = intent.getStringExtra("bmi_text");

        String result = String.format("ค่า BMI ที่ได้คือ %.2f\n\nอยู่ในเกณฑ์ : %s", bmi, bmiText);

        t.setText(result);
    }
}
