package edu.washington.mxl.quizdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;


public class Descr extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_descr);

        final Intent launchedMe = getIntent();
        final int id = launchedMe.getIntExtra("id", -1);

        //Toast.makeText(this,"" + id, Toast.LENGTH_SHORT).show();
        TextView dv = (TextView) findViewById(R.id.descr);
        if (dv != null) {
            dv.setText(launchedMe.getStringExtra("descr"));
        }



//        Toast.makeText(this, launchedMe.getSerializableExtra("serialMap").toString(), Toast.LENGTH_SHORT).show();

//        Button button = (Button) findViewById(R.id.begin);
//        if (button != null) {
//            Log.i("Start button", "NOT NULL");
//
//            Toast.makeText(Descr.this, "NOT NULL", Toast.LENGTH_LONG).show();
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.i("Start button", "Entered ON Click");
//                    Toast.makeText(Descr.this, "got intent", Toast.LENGTH_LONG).show();
//                    Intent questions = new Intent(Descr.this, Questions.class);
//                    questions.putExtra("id", id);
//                    startActivity(questions);
//                }
//            });
//        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_descr, menu);
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
