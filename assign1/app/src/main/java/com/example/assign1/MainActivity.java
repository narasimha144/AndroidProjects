package com.example.assign1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String sum = intent.getStringExtra(Activity2.SUM_RESULT);

        TextView textView = findViewById(R.id.textView);
        textView.setText(sum);
    }

    /** Called when a user taps the launch activity 2 button */
    public void launchActivity2(View view) {
        // do something in response to it
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

}