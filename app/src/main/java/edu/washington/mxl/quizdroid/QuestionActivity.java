package edu.washington.mxl.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class QuestionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent launch = getIntent();
        boolean correct = launch.getBooleanExtra("correct", false);
        String ans = launch.getStringExtra("ans");
        MapOfQuestions map = (MapOfQuestions) launch.getSerializableExtra("map");
        if (map == null) {
            Log.i("map", "IS NULL");
        }
        final int score = map.score;

        //Toast.makeText(QuestionActivity.this, "ques act " + correct, Toast.LENGTH_LONG).show();
        TextView result = (TextView) findViewById(R.id.result);
        if (correct) {
            result.setText("You got it right!");
        } else {
            result.setText("Correct answer is " + ans);
        }

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sum = new Intent(QuestionActivity.this, SummaryActivity.class);
                sum.putExtra("score", score);
                startActivity(sum);
            }
        });

        Log.i("Intent", correct + "");

        Toast.makeText(this, "got intent QUES ACT", Toast.LENGTH_LONG).show();

//        MapOfQuestions mapOfQuestions = new MapOfQuestions();
//        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
//
//        String q = "";
//
//        if (id == 0) {
//            map = mapOfQuestions.getMath();
//            q = mapOfQuestions.getMathQ();
//        } else if (id == 1) {
//            map = mapOfQuestions.getPhysics();
//            q = mapOfQuestions.getPhysicsQ();
//        } else if (id == 2) {
//            map = mapOfQuestions.getMarvel();
//            q = mapOfQuestions.getMarvelQ();
//        }

        //String correct = map.get("correct").get(0);

//        Intent launch = getIntent();
//        boolean correct = launch.getBooleanExtra("correct", true);
//
//        Toast.makeText(QuestionActivity.this, "got intent", Toast.LENGTH_LONG).show();
//
//        Log.i("Intent", correct + "");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
