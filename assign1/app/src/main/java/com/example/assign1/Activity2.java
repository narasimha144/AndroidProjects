package com.example.assign1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {
    public static final String SUM_RESULT = "com.example.assign1.SUM";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();

    }

    /** Called when user taps the Calculate Sum button*/
    public void calculateSum(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        EditText editText1 = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText editText2 = (EditText) findViewById(R.id.editTextTextPersonName2);

        String num1 = editText1.getText().toString();
        int n1 = Integer.parseInt(num1);

        String num2 = editText2.getText().toString();
        int n2 = Integer.parseInt(num2);

        int s = n1+n2;
        String sum = String.valueOf(s);

        intent.putExtra(SUM_RESULT, sum);
        startActivity(intent);
    }
}