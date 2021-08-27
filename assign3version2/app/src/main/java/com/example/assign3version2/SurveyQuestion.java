package com.example.assign3version2;

import java.util.List;

public class SurveyQuestion {
    private final String question;
    private final String type;
    private final List<String> options;

    public SurveyQuestion(String question, String type, List<String> options) {
        this.question = question;
        this.type = type;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public String getType() {
        return type;
    }

    public List<String> getOptions() {
        return options;
    }

}
