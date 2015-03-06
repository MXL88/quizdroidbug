package edu.washington.mxl.quizdroid;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michelle on 2/16/2015.
 */
public class QuizApp extends Application implements TopicRepository {
    private QuizApp instance;
    private int score;
    private List<HashMap<String, List<String>>> listOfTopics;
    private List<String> questions = new ArrayList<String>();
    private int currTopic;
    private Topic topic;


    public QuizApp() {
        if (instance == null) {
            instance = this;
        } else {
            throw new RuntimeException("Cannot create two instances of QuizApp");
        }
    }

    @Override
    public void onCreate() {
        Log.i("QuizApp", "An instance of QuizApp has been created");

    }

    public QuizApp getInstance() {
        return instance;
    }

    public void setBase() {
        listOfTopics = new ArrayList<HashMap<String, List<String>>>();
        List<String> listOptions = new ArrayList<String>();
        listOptions.add("a. 3");
        listOptions.add("b. 4");
        listOptions.add("c. 5");
        listOptions.add("d. 6");
        HashMap qA1 = new HashMap<String, List<String>>();
        setMathQ("1 + 2 = ?");
        qA1.put(questions.get(0), listOptions);
        List<String> corrAns1 = new ArrayList<String>();
        corrAns1.add("3");
        qA1.put("corrAns", corrAns1);
        List<String> correctAns1 = new ArrayList<String>();
        correctAns1.add(0 + "");
        qA1.put("correct", correctAns1);
        List<String> longD1 = new ArrayList<String>();
        longD1.add("We will only cover addition.");
        qA1.put("longD", longD1);
        List<String> shortD1 = new ArrayList<String>();
        shortD1.add("1 question total about addition");
        qA1.put("shortD", shortD1);
        listOfTopics.add(qA1);

        List<String> listOptions2 = new ArrayList<String>();
        listOptions2.add("a. mA");
        listOptions2.add("b. A/m");
        listOptions2.add("c. m/A");
        listOptions2.add("d. miA");
        HashMap qA2 = new HashMap<String, List<String>>();
        setPhysicsQ("F = ?");
        qA2.put(questions.get(1), listOptions2);
        List<String> corrAns2 = new ArrayList<String>();
        corrAns2.add("mA");
        qA2.put("corrAns", corrAns2);
        List<String> correctAns2 = new ArrayList<String>();
        correctAns2.add(0 + "");
        qA2.put("correct", correctAns2);
        List<String> longD2 = new ArrayList<String>();
        longD2.add("We will only cover F = mA.");
        qA2.put("longD", longD2);
        List<String> shortD2 = new ArrayList<String>();
        shortD2.add("1 question total about force");
        qA2.put("shortD", shortD2);
        listOfTopics.add(qA2);

        List<String> listOptions3 = new ArrayList<String>();
        listOptions3.add("a. Tom Cruise");
        listOptions3.add("b. Kamala Khan");
        listOptions3.add("c. George Lucas");
        listOptions3.add("d. Michelle Le");
        HashMap qA3 = new HashMap<String, List<String>>();
        setMarvelQ("Who is the best Marvel hero?");
        qA3.put(questions.get(2), listOptions3);
        List<String> corrAns3 = new ArrayList<String>();
        corrAns3.add("Kamala Khan");
        qA3.put("corrAns", corrAns3);
        List<String> correctAns3 = new ArrayList<String>();
        correctAns3.add(1 + "");
        qA3.put("correct", correctAns3);
        List<String> longD3 = new ArrayList<String>();
        longD3.add("We will only cover Kamala Khan.");
        qA3.put("longD", longD3);
        List<String> shortD3 = new ArrayList<String>();
        shortD3.add("1 question total about Kamala Khan");
        qA3.put("shortD", shortD3);

        listOfTopics.add(qA3);
    }

    public void setTopic(int index) {
        currTopic = index;
    }

    public String getQ() {
        return questions.get(currTopic);
    }

    public List<String> getA() {
        return listOfTopics.get(currTopic).get(questions.get(currTopic));
    }

    public int getCorrAns() {
        return listOfTopics.get(currTopic).get("correct").get(0).charAt(0);
    }

    public String getCorrectAns() {
        return listOfTopics.get(currTopic).get("corrAns").get(0);
    }

    /*
        topic.setLongDescr(listOfTopics.get(currTopic).get("longD").get(0));
        topic.setShortDescr(listOfTopics.get(currTopic).get("shortD").get(0));
        List<Quiz> quizzes = new ArrayList<Quiz>();
        Quiz q = new Quiz();
        q.setQuestion(questions.get(currTopic));
        q.setAnswers(listOfTopics.get(currTopic).get(questions.get(currTopic)));
        q.setCorrAns(listOfTopics.get(currTopic).get("correct").get(0).charAt(0));
        quizzes.add(q);
        topic.setQuizzes(quizzes);
     */


    public HashMap<String, List<String>> getMath() {
        return listOfTopics.get(0);
    }
    public void setMathQ(String q) {questions.add(q); }
    public String getMathQ() {
        return questions.get(0);
    }
    public String getMathA() {
        return "3";
    }
    public String getMathLongDescr() { return "We will only cover addition."; }
    public String getMathShortDescr() { return "1 question total about addition"; }
    public int getCorrectMathAns() { return 0;  }

    public HashMap<String, List<String>> getPhysics() {
        return listOfTopics.get(1);
    }
    public String getPhysicsQ() {
        return questions.get(1);
    }
    public void setPhysicsQ(String q) {questions.add(q); }
    public String getPhysicsA() {
        return "mA";
    }
    public String getPhysicsLongDescr() { return "We will only cover F = mA."; }
    public String getPhysicsShortDescr() { return "1 question total about forcce"; }
    public int getPhysicsCorrectAns() { return 0;  }

    public HashMap<String, List<String>> getMarvel() {
        return listOfTopics.get(2);
    }
    public void setMarvelQ(String q) {questions.add(q); }
    public String getMarvelQ() {
        return questions.get(2);
    }
    public String getMarvelA() {
        return "Kamala Khan";
    }
    public String getMarvelLongDescr() { return "We will only cover Kamala Khan."; }
    public String getMarvelShortDescr() { return "1 question total about Kamala Khan"; }
    public int getMarvelCorrectAns() { return 1;  }

    public void addScore() {
        score++;
    }
    public void clearScore() {
        score = 0;
    }

}


