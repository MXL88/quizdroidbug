package edu.washington.mxl.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapOfQuestions implements Serializable {
    public int score;
    public List<HashMap<String, List<String>>> listOfTopics;
    private List<String> questions = new ArrayList<String>();

    public MapOfQuestions() {
        listOfTopics = new ArrayList<HashMap<String, List<String>>>();
        List<String> listOptions = new ArrayList<String>();
        listOptions.add("a. 3");
        listOptions.add("b. 4");
        listOptions.add("c. 5");
        listOptions.add("d. 6");
        HashMap qA1 = new HashMap<String, List<String>>();
        setMathQ("1 + 2 = ?");
        qA1.put(questions.get(0), listOptions);
        List<String> correctAns1 = new ArrayList<String>();
        correctAns1.add(0 + "");
        qA1.put("correct", correctAns1);
        listOfTopics.add(qA1);

        List<String> listOptions2 = new ArrayList<String>();
        listOptions2.add("a. mA");
        listOptions2.add("b. A/m");
        listOptions2.add("c. m/A");
        listOptions2.add("d. miA");
        HashMap qA2 = new HashMap<String, List<String>>();
        setPhysicsQ("F = ?");
        qA2.put(questions.get(1), listOptions2);
        List<String> correctAns2 = new ArrayList<String>();
        correctAns2.add(0 + "");
        qA2.put("correct", correctAns2);
        listOfTopics.add(qA2);

        List<String> listOptions3 = new ArrayList<String>();
        listOptions3.add("a. Tom Cruise");
        listOptions3.add("b. Kamala Khan");
        listOptions3.add("c. George Lucas");
        listOptions3.add("d. Michelle Le");
        HashMap qA3 = new HashMap<String, List<String>>();
        setMarvelQ("Who is the best Marvel hero?");
        qA3.put(questions.get(2), listOptions3);
        List<String> correctAns3 = new ArrayList<String>();
        correctAns3.add(1 + "");
        listOfTopics.add(qA3);
        qA3.put("correct", correctAns3);
    }


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

    public void addScore() {
        score++;
    }

    public void clearScore() {
        score = 0;
    }
}