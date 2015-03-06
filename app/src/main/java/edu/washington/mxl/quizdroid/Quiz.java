package edu.washington.mxl.quizdroid;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Michelle on 2/16/2015.
 */
public class Quiz implements Serializable {
    private String question;
    private List<String> answers;
    private int corrAns;

    public Quiz() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String newQuestion) {
        question = newQuestion;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> newAnswers) {
        answers = newAnswers;
    }

    public int getCorrAns() {
        return corrAns;
    }

    public void setCorrAns(int newCorrect) {
        corrAns = newCorrect;
    }
}
