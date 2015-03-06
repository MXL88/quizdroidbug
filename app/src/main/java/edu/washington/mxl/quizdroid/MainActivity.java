package edu.washington.mxl.quizdroid;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;


public class MainActivity extends ActionBarActivity {
    private static List<HashMap<String, String>> topicID = new ArrayList<HashMap<String, String>>();
    private static List<HashMap<String, List<String>>> listOfQA = new ArrayList<HashMap<String,List<String>>>();
    private int numOfTopic = 3;

    private Intent alIntent;
    private PendingIntent pendingIntent;
    private AlarmManager mgr;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuizApp quizApp = (QuizApp) getApplicationContext();
        quizApp.setBase();

        mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Log.i("Pref", prefs.getString("prefUrl", "heeey")  + " " );

//        int freq = Integer.parseInt(prefs.getString("prefFrequency", "2"));
//        Log.i("Pref", "freq: " + freq);

//        String freqString = prefs.getString("prefFrequency", "1");
//        Log.i("Pref", "freqString: " + freqString);

//        String mess = prefs.getString("prefUrl", "heeey")  + " " +
//                prefs.getString("prefFrequency", "let's get freqqy");
//        Log.i("Prefs", "mess: " + mess);

        String url = prefs.getString("prefUrl", "heeey");
//        String mess = prefs.getString("prefUrl", "heeey")  + " " +
//                prefs.getString("prefFrequency", "let's get freqqy");


//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt("prefFrequency", freq); // value to store
//        editor.putInt("prefUrl", 2); // value to store
//        editor.commit();

        alIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        alIntent.putExtra("mess", url);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alIntent, 0);
        mgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 3000, 1 * 60000, pendingIntent);

        HashMap<String, String> temp = new HashMap<String, String>();
        temp.put("topic", "Math");
        temp.put("descr", "We will only cover addition. 1 question total");
        topicID.add(temp);

        List<String> listOptions = new ArrayList<String>();
        listOptions.add("3");
        HashMap qA1 = new HashMap<String, List<String>>();
        qA1.put("1 + 2 = ?", listOptions);
        listOfQA.add(qA1);

        HashMap<String, String> temp1 = new HashMap<String, String>();
        temp1.put("topic", "Physics");
        temp1.put("descr", "We will only cover F = mA. 1 question total");
        topicID.add(temp1);

        List<String> listOptions2 = new ArrayList<String>();
        listOptions2.add("mA");
        HashMap qA2 = new HashMap<String, List<String>>();
        qA2.put("F = ?", listOptions2);
        listOfQA.add(qA2);

        HashMap<String, String> temp2 = new HashMap<String, String>();
        temp2.put("topic", "Marvel Super Heroes");
        temp2.put("descr", "We will only cover Kamala Khan. 1 question total");
        topicID.add(temp2);

        List<String> listOptions3 = new ArrayList<String>();
        listOptions.add("Kamala Khan");
        HashMap qA3 = new HashMap<String, List<String>>();
        qA3.put("Who is the best Marvel hero?", listOptions3);
        listOfQA.add(qA3);

        numOfTopic = listOfQA.size();

        // Keys used in Hashmap
        String[] from = { "topic" };
        int[] to = {R.id.topic};

        setContentView(R.layout.activity_main);


        SimpleAdapter simpleAdpt = new SimpleAdapter(this, topicID, R.layout.topics_listview_layout, from, to);
        ListView listView = ( ListView ) findViewById(R.id.listView);
        listView.setAdapter(simpleAdpt);
        // React to user clicks on item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
                Intent descr = new Intent(MainActivity.this, FragmentsHolder.class);
                Log.i("id", "" + id);
                descr.putExtra("id", (int)id);
                descr.putExtra("descr", topicID.get((int) id).get("descr"));

                startActivity(descr);
//              Toast.makeText(MainActivity.this, "Item with id [" + id + "]" + " " + topicID.get((int)id).get("descr"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mgr.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1)
        {
            displayUserSettings();
        }

    }

    private void displayUserSettings()
    {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String  settings = "";

        settings=settings+"Password: " + sharedPrefs.getString("prefUserPassword", "NOPASSWORD");

        settings=settings+"\nRemind For Update:"+ sharedPrefs.getBoolean("prefLockScreen", false);

        settings=settings+"\nUpdate Frequency: "
                + sharedPrefs.getString("prefUpdateFrequency", "NOUPDATE");
    }


    private void addTopic(String topic) {
        HashMap<String, String> temp = new HashMap<String, String>();
        temp.put("topic", topic);
        topicID.add(temp);
        numOfTopic++;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Intent i = new Intent(getApplicationContext(), SettingActivities.class);
//            startActivityForResult(i, 1);
            startActivity(new Intent(this, SettingActivities.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
