package edu.washington.mxl.quizdroid;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;


public class FragmentsHolder extends ActionBarActivity {
    private static MapOfQuestions mapOfQuestions;
    private static String description;
    private static int id;
    private static boolean correct;
    private static String ans;
    private static  String ansCorr;
    private static QuizApp quizApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_holder);
        final Intent launchedMe = getIntent();
        description = launchedMe.getStringExtra("descr");
        id = launchedMe.getIntExtra("id", -1);
        correct = false;

        mapOfQuestions = new MapOfQuestions();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        quizApp = (QuizApp) getApplicationContext();
        quizApp.setTopic(id);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragments_holder, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.activity_descr, container, false);
            TextView dv = (TextView) rootView.findViewById(R.id.descr);
            if (dv != null) {
                dv.setText(description);
            }
            Button beginBtn = (Button) rootView.findViewById(R.id.begin);
            beginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(container.getContext(), "Howdy", Toast.LENGTH_SHORT).show();
                    Log.i("begin btn", "WORKS");
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Log.i("begin btn", "after ft");
                    ft.replace(R.id.container, new QuestionsFragment());
                    Log.i("begin btn", "before commit");
                    ft.commit();
                }
            });
            return rootView;
        }
    }

    public static class QuestionsFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.i("Questions", "ENTERED");
            View rootView = inflater.inflate(R.layout.activity_questions, container, false);

            final RadioGroup rg = (RadioGroup) rootView.findViewById(R.id.rg);
            final Button submit = (Button) rootView.findViewById(R.id.submit);
            final TextView ques = (TextView) rootView.findViewById(R.id.question);

            final MapOfQuestions mapOfQuestions = new MapOfQuestions();
            HashMap<String, List<String>> map = new HashMap<String, List<String>>();

            String q = "";
            String corrAns = "";

//            if (id == 0) {
//                map = mapOfQuestions.getMath();
//                q = mapOfQuestions.getMathQ();
//                corrAns = mapOfQuestions.getMathA();
//            } else if (id == 1) {
//                map = mapOfQuestions.getPhysics();
//                q = mapOfQuestions.getPhysicsQ();
//                corrAns = mapOfQuestions.getPhysicsA();
//            } else if (id == 2) {
//                map = mapOfQuestions.getMarvel();
//                q = mapOfQuestions.getMarvelQ();
//                corrAns = mapOfQuestions.getMarvelA();
//            }
            q = quizApp.getQ();

            ans = quizApp.getCorrAns() + "";

            List<String> options = quizApp.getA();

            corrAns = quizApp.getCorrectAns();

            if (rg != null) {
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int checkedID = rg.getCheckedRadioButtonId();
                        if (checkedID != -1) {
                            submit.setEnabled(true);
                        }
                    }
                });
            }

           ansCorr = corrAns;

            if (submit != null) {
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if ((rg.getCheckedRadioButtonId() + "").equals(ans)) {
                            //submitted.putExtra("correct", true);
                            mapOfQuestions.addScore();
                            correct = true;
                        } else {
                            //submitted.putExtra("correct", false);
                            correct = false;
                        }

//                    submitted.putExtra("ans", ansCorr);
//                    submitted.putExtra("map", mapOfQuestions);
                        Log.i("submit btn", "WORKS");
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        Log.i("submit btn", "after ft");

//                        Bundle args = new Bundle();
//                        args.putString("YourKey", "YourValue");
//                        this.setArguments(args);


                        ft.replace(R.id.container, new SubmitFragment());
                        Log.i("submit btn", "before commit");
                        ft.commit();
                    }
                });
            }


            // Radio Buttons
            for (int j = 0; j < 2; j++) {
                if (j != 0) {
                    ques.setText(q);

                    RadioButton button;
                    int k = 0;
                    for (String opt : options) {
                        button = new RadioButton(getActivity());
                        button.setText(opt);
                        button.setId(k);
                        rg.addView(button);
                        k++;
                    }
                }
            }

            return rootView;
        }
    }

    public static class SubmitFragment extends Fragment {

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.i("Submitted", "ENTERED");
            View rootView = inflater.inflate(R.layout.activity_question, container, false);

            TextView result = (TextView) rootView.findViewById(R.id.result);
            if (correct) {
                result.setText("You got it right!");
                mapOfQuestions.addScore();
            } else {
                result.setText("Correct answer is " + ansCorr);
            }

            Button next = (Button) rootView.findViewById(R.id.next);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent sum = new Intent(QuestionActivity.this, SummaryActivity.class);
//                    sum.putExtra("score", score);
//                    startActivity(sum);
                    Log.i("next btn", "WORKS");
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Log.i("next btn", "after ft");

                    ft.replace(R.id.container, new SummaryFragment());
                    Log.i("next btn", "before commit");
                    ft.commit();
                }
            });
            return rootView;
        }
    }

    public static class SummaryFragment extends Fragment {

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.i("Submitted", "ENTERED");
            View rootView = inflater.inflate(R.layout.activity_summary, container, false);

            TextView summary = (TextView) rootView.findViewById(R.id.summary);
            summary.setText("You got " + mapOfQuestions.score + " / 1");

            return rootView;
        }
    }
}
