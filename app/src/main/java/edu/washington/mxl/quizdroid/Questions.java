package edu.washington.mxl.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class Questions extends ActionBarActivity {
    private List<HashMap<String, List<String>>> listOfQA = new ArrayList<HashMap<String,List<String>>>();
    private int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent launch = getIntent();
        int id = launch.getIntExtra("id", -1);

        Log.i("Intent", "got Intent");



        final MapOfQuestions mapOfQuestions = new MapOfQuestions();
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();



        String q = "";
        String corrAns = "";

        if (id == 0) {
            map = mapOfQuestions.getMath();
            q = mapOfQuestions.getMathQ();
            corrAns = mapOfQuestions.getMathA();
        } else if (id == 1) {
            map = mapOfQuestions.getPhysics();
            q = mapOfQuestions.getPhysicsQ();
            corrAns = mapOfQuestions.getPhysicsA();
        } else if (id == 2) {
            map = mapOfQuestions.getMarvel();
            q = mapOfQuestions.getMarvelQ();
            corrAns = mapOfQuestions.getMarvelA();
        }

        final String ans = map.get("correct").get(0);

        List<String> options = map.get(q);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        final Button submit = (Button) findViewById(R.id.submit);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedID = rg.getCheckedRadioButtonId();
                if (checkedID != -1) {
                    submit.setEnabled(true);
                }
            }
        });

        final String ansCorr = corrAns;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submitted = new Intent(Questions.this, QuestionActivity.class);
                Toast.makeText(Questions.this, check + "", Toast.LENGTH_SHORT).show();
                if ((rg.getCheckedRadioButtonId() + "").equals(ans)) {
                    submitted.putExtra("correct", true);
                    mapOfQuestions.addScore();
                } else {
                    submitted.putExtra("correct", false);
                }

                submitted.putExtra("ans", ansCorr);
                submitted.putExtra("map", mapOfQuestions);
                startActivity(submitted);
            }
        });

        TextView ques = (TextView) findViewById(R.id.question);
        // Radio Buttons
        for (int j = 0; j < 2; j++) {
            if (j != 0) {
                ques.setText(q);

                RadioButton button;
                int k = 0;
                for (String opt : options) {
                    button = new RadioButton(this);
                    button.setText(opt);
                    button.setId(k);
                    rg.addView(button);
                    k++;
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_questions, menu);


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
