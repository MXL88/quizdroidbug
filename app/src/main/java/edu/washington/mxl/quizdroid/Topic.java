package edu.washington.mxl.quizdroid;

import java.io.Serializable;
import java.util.List;

/**
 *
 * (QuizApp) getApplicationContext();
 * Created by Michelle on 2/16/2015.
 */
public class Topic implements Serializable{
    private String title;
    private String shortDescr;
    private String longDescr;
    private List<Quiz> quizzes;

    public Topic() {

    }

    public String getTitle() {return title; }
    public void setTitle(String newTitle) {title = newTitle;}

    public String getShortDescr() { return shortDescr;}
    public void setShortDescr(String newShortDescr) { shortDescr = newShortDescr; }

    public String getLongDescr() { return longDescr;}
    public void setLongDescr(String newLongDescr) { longDescr = newLongDescr; }

    public List<Quiz> getQuizzes() { return quizzes; }
    public void setQuizzes(List<Quiz> newQuizzes) { quizzes = newQuizzes; }
}
