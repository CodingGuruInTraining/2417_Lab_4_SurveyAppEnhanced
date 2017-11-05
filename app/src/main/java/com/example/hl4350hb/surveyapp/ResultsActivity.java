package com.example.hl4350hb.surveyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get reference to the Intent that launched this activity.
        Intent launchIntent = getIntent();

        // Retrieves counts from MainActivity.
        int yesCount = launchIntent.getIntExtra(MainActivity.YES_KEY, 0);
        int noCount = launchIntent.getIntExtra(MainActivity.NO_KEY, 0);

        // References widgets.
        TextView yesView = (TextView) findViewById(R.id.yeses);
        TextView noView = (TextView) findViewById(R.id.noes);

        // Updates TextViews with received counts.
        String yesText = yesView.getText().toString();
        yesView.setText(yesText.replace("XX", yesCount + ""));

        String noText = noView.getText().toString();
        noView.setText(noText.replace("XX", noCount + ""));
    }
}
