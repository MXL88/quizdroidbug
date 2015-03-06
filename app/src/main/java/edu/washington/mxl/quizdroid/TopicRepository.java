package edu.washington.mxl.quizdroid;

/**
 * Created by Michelle on 2/16/2015.
 */
public interface TopicRepository {

    public String getMathLongDescr();
    public String getMathShortDescr();
    public int getCorrectMathAns();
    public String getMathQ();
    public String getMathA();

    public String getPhysicsLongDescr();
    public String getPhysicsShortDescr();
    public int getPhysicsCorrectAns();
    public String getPhysicsQ();
    public String getPhysicsA();

    public String getMarvelLongDescr();
    public String getMarvelShortDescr();
    public int getMarvelCorrectAns();
    public String getMarvelQ();
    public String getMarvelA();




}
