// This app tracks responses to a survey question.
//
package com.example.hl4350hb.surveyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Initialize widget variables.
    TextView mQuestionLabel;
    Button mYesButton;
    Button mNoButton;
    Button mResetButton;
    TextView mYesLabel;
    TextView mNoLabel;

    // Initialize static variables.
    Integer yesCount;
    Integer noCount;

    // Creates static keys for bundling.
    private final static String YES_KEY = "yes something";
    private final static String NO_KEY = "no key goes here";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Checks if bundle exists.
        if (savedInstanceState != null) {
            yesCount = savedInstanceState.getInt(YES_KEY);
            noCount = savedInstanceState.getInt(NO_KEY);
        }
        // Sets values if null.
        if (yesCount == null) {
            yesCount = 0;
        }
        if (noCount == null) {
            noCount = 0;
        }

        // Generates widgets.
        mQuestionLabel = (TextView) findViewById(R.id.survey_questions);
        mYesButton = (Button) findViewById(R.id.yes_button);
        mNoButton = (Button) findViewById(R.id.no_button);
        mResetButton = (Button) findViewById(R.id.reset_button);
        mYesLabel = (TextView) findViewById(R.id.yes_textview);
        mNoLabel = (TextView) findViewById(R.id.no_textview);

        // Runs update function.
        updateCounters();

        // Yes button event listener.
        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Updates counter.
                yesCount++;
                updateCounters();
            }
        });

        // No button event listener.
        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Updates counter.
                noCount++;
                updateCounters();
            }
        });

        // Reset button event listener.
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clears variables and updates widget strings.
                yesCount = 0;
                noCount = 0;
                updateCounters();
            }
        });
    }

    // Function to set widget labels' text to counter values.
    private void updateCounters() {
        mYesLabel.setText("Yes: " + yesCount);
        mNoLabel.setText("No: " + noCount);
    }

    // Bundle saver.
    @Override
    protected void onSaveInstanceState(Bundle outBundle) {
        outBundle.putInt(YES_KEY, yesCount);
        outBundle.putInt(NO_KEY, noCount);
    }
}
