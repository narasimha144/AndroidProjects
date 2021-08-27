package com.example.assign3version2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private final Context context;
    private final List<Object> surveyQuestions;

    // there is a view holder for each question
    // each question contains a text view and a radio group
    // defined UI for text view and radio group in text_row_item.xml file
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private RadioGroup radioGroup;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.my_textView);
            radioGroup = (RadioGroup) view.findViewById(R.id.my_radioGroup);
        }

    }

    public CustomAdapter(Context context, List<Object> dataSet) {
        this.context = context;
        surveyQuestions = dataSet;
    }

    @Override
    public int getItemCount() {
        return surveyQuestions.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        SurveyQuestion surveyQuestion = (SurveyQuestion) surveyQuestions.get(position);

        String question = surveyQuestion.getQuestion();
        viewHolder.textView.setText(question);

        List<String> options = surveyQuestion.getOptions();
        for(int i=0; i<options.size(); ++i) {
            String option = options.get(i);
            RadioButton radioButton = new RadioButton(context);
            radioButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setText(option);
            radioButton.setTextSize(24);
            radioButton.setId(i);
            viewHolder.radioGroup.addView(radioButton);
        }

    }

}
