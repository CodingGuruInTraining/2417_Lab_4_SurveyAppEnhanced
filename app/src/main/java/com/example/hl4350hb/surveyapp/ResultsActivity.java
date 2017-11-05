package com.example.hl4350hb.surveyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    public static final String EXTRA_FROM_RESULT = "here is a delicious extra";

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
        Button resetBtn = (Button) findViewById(R.id.reset_button);
        Button contBtn = (Button) findViewById(R.id.continue_button);

        // Updates TextViews with received counts.
        String yesText = yesView.getText().toString();
        yesView.setText(yesText.replace("XX", yesCount + ""));

        String noText = noView.getText().toString();
        noView.setText(noText.replace("XX", noCount + ""));

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent(false);
            }
        });

        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent(true);
            }
        });
    }

    private void returnIntent(boolean decision) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_FROM_RESULT, decision);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
