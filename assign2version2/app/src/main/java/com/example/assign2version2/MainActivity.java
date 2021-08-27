package com.example.assign2version2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.rootContainer);
        // Create a text view for survey question
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(R.string.survey_question);
        textView.setTextSize(24);
        textView.setId(0);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setMargins(20,50,0,0);
        textView.setLayoutParams(params1);

        // Create a Radio button for yes option
        RadioButton radioButton1 = new RadioButton(this);
        radioButton1.setLayoutParams(new LinearLayout.LayoutParams(
                                            ViewGroup.LayoutParams.WRAP_CONTENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT));
        radioButton1.setText(R.string.yes_option);
        radioButton1.setTextSize(24);
        radioButton1.setId(1);

        // Create a Radio button for no option
        RadioButton radioButton2 = new RadioButton(this);
        radioButton2.setLayoutParams(new LinearLayout.LayoutParams(
                                            ViewGroup.LayoutParams.WRAP_CONTENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT));
        radioButton2.setText(R.string.no_option);
        radioButton2.setTextSize(24);
        radioButton2.setId(2);

        // Create a Radio group for above buttons
        RadioGroup radioGroup = new RadioGroup(this);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT);

        params2.setMargins(40,40,0,0);
        radioGroup.setLayoutParams(params2);

        if(linearLayout != null) {
            linearLayout.addView(textView);
            radioGroup.addView(radioButton1);
            radioGroup.addView(radioButton2);
            linearLayout.addView(radioGroup);
        }
    }
}