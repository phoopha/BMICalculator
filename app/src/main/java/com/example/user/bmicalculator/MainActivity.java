package com.example.user.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mHeightEditText, mWeightEditText;
    private Button mCalculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHeightEditText = (EditText) findViewById(R.id.height_edit_text);
        mWeightEditText = (EditText) findViewById(R.id.weight_edit_text);
        mCalculateButton = (Button) findViewById(R.id.calculate_button);

        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightText = mHeightEditText.getText().toString();
                // getText return มาเป็น charsequence
                Double height = Double.valueOf(heightText);

                Double weight = Double.valueOf(mWeightEditText.getText().toString());

                Double bmi = weight/((height/100)*(height/100));

                String bmiText = getBmiText(bmi);

                String result = String.format("ค่า BMI ที่ได้คือ %.2f\n\nอยู่ในเกณฑ์ : %s", bmi, bmiText);

                /*String result = "ค่า BMI ที่ได้ คือ " + String.valueOf(bmi);
                result += "\n\n อยู่ในเกณฑ์ : " + bmiText;*/
/*
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("BMI Result");
                dialog.setMessage(result);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // โค้ดที่ต้องการให้ทำงานเมื่อปุ่ม OK บน dialog ถูกคลิ๊ก
                        // finish(); // ปิด Activity ปัจจุบัน
                        mHeightEditText.setText("");
                        mWeightEditText.setText("");
                        mHeightEditText.requestFocus();
                    }
                });
                dialog.show();
*/

                Intent intent = new Intent(MainActivity.this, BmiResultActivity.class);
                intent.putExtra("bmi_value", bmi);
                intent.putExtra("bmi_text", bmiText);
                startActivity(intent); // fire intent
            }
        });

    } // ปิดคลาส onCreate

    private String getBmiText(Double bmi) {
        /*
            bmi < 18.5 : น้ำหนักน้อยกว่าปกติ
            18.5 <= bmi < 25 : น้ำหนักปกติ
            25 <= bmi < 30 : น้ำหนักมากวกว่าปกติ(ท้วม)
            bmi >= 30 : น้ำหนักมากกว่าปกติมาก(อ้วน)
        */
        String bmiText = "";

        if (bmi < 18.5) {
            bmiText = "น้ำหนักน้อยกว่าปกติ";
        }else if (bmi < 25) {
            bmiText = "น้ำหนักปกติ";
        }else if (bmi < 30) {
            bmiText = "น้ำหนักมากกว่าปกติ(ท้วม)";
        }else{
            bmiText = "น้ำหนักมากกว่าปกติ(อ้วน)";
        }
        return bmiText;
    }

    /*
    ********** การสร้าง Listenner มี 3 วิธ๊ **********

    ---------- วิธีที่ 1: ใช้ Inner Class เป็น Listenner ----------

    // คลาส onCreate

    // สร้าง object ที่ทำหน้าที่เป็น Listener ของปุ่ม
    MyListener listener = new MyListener();
    // กำหนด object ที่เป็น Listener ให้กับปุ่ม
    mCalculateButton.setOnClickListener(listener);

    // คลาส MainActivity

    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // โค้ดที่เราต้องการให้ทำงาน เมื่อปุ่มถูกคลิ๊ก
            Toast t = Toast.makeText(
                    MainActivity.this,  // context
                    "Hello",            // ข้อความที่ต้องการแสดงใน Toast
                    Toast.LENGTH_SHORT  // ระยะเวลาในการแสดง Toast
            );
            t.show();
        }
    }

    ---------- วิธีที่ 2: ใช้ Activity เป็น Listenner ----------

    public class MainActivity extends AppCompatActivity implements View.OnClickListener

    // คลาส onCreate
    mCalculateButton.setOnClickListener(this);

    // คลาส MainActivity
    @Override
    public void onClick(View v) {

    }

    --------- วิธีที่ 3: ใช้ Annonymous Inner เป็น Listenner ---------

    ***** อ่านเพิ่มเติมได้ที่ https://docs.google.com/document/d/1-UmPrRJM2slxQ4A44EE_l1OQKMtv3_Rt0sAcff8DwB8/edit
    */

} // ปิดคลาส MainActivity
