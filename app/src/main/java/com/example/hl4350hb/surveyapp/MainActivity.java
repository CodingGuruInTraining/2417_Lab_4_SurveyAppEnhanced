// This app tracks responses to a survey question.
//
package com.example.hl4350hb.surveyapp;

import android.content.Intent;
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
    Button mNewButton;
//    Button mResetButton;
//    TextView mYesLabel;
//    TextView mNoLabel;

    // Initialize static variables.
    Integer yesCount;
    Integer noCount;
    String question;
    String option1;
    String option2;

    // Creates static keys for bundling and intents.
    protected final static String YES_KEY = "yes something";
    protected final static String NO_KEY = "no key goes here";
    protected final static String Q_KEY = "i have a question for you";
    protected final static String OPT1_KEY = "first order of business";
    protected final static String OPT2_KEY = "there used to be a third option";


    private static final int RESULT_REQUEST_CODE = 0;
    private static final int SURVEY_REQUEST_CODE = 1;

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

        // References widgets.
        mQuestionLabel = (TextView) findViewById(R.id.survey_questions);
        mYesButton = (Button) findViewById(R.id.yes_button);
        mNoButton = (Button) findViewById(R.id.no_button);
        mNewButton = (Button) findViewById(R.id.create_button);

//        mResetButton = (Button) findViewById(R.id.reset_button);
//        mYesLabel = (TextView) findViewById(R.id.yes_textview);
//        mNoLabel = (TextView) findViewById(R.id.no_textview);

//        // Runs update function.
//        updateCounters();

        // Yes button event listener.
        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Updates counter.
                yesCount++;
//                updateCounters();

                sendIntent();
//                Intent launchResults = new Intent(MainActivity.this, ResultsActivity.class);
//                launchResults.putExtra(YES_KEY, yesCount);
//                launchResults.putExtra(NO_KEY, noCount);
//                startActivity(launchResults);
            }
        });

        // No button event listener.
        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Updates counter.
                noCount++;
//                updateCounters();
                sendIntent();
            }
        });

        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchSurvey = new Intent(MainActivity.this, SurveyActivity.class);
                startActivityForResult(launchSurvey, SURVEY_REQUEST_CODE);
            }
        });

        // Reset button event listener.
//        mResetButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Clears variables and updates widget strings.
//                yesCount = 0;
//                noCount = 0;
//                updateCounters();
//            }
//        });
    }

    // Function to set widget labels' text to counter values.
//    private void updateCounters() {
//        mYesLabel.setText("Yes: " + yesCount);
//        mNoLabel.setText("No: " + noCount);
//    }

    // Bundle saver.
    @Override
    protected void onSaveInstanceState(Bundle outBundle) {
        outBundle.putInt(YES_KEY, yesCount);
        outBundle.putInt(NO_KEY, noCount);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_REQUEST_CODE) {
                boolean decision = data.getBooleanExtra(ResultsActivity.EXTRA_FROM_RESULT, false);
                if (decision) {
                    // TODO maybe do something?
                } else {
                    resetCounts();
                }
            } else if (requestCode == SURVEY_REQUEST_CODE) {
                question = data.getStringExtra(SurveyActivity.EXTRA_FROM_SURVEY_QUESTION);
                option1 = data.getStringExtra(SurveyActivity.EXTRA_FROM_SURVEY_OPT1);
                option2 = data.getStringExtra(SurveyActivity.EXTRA_FROM_SURVEY_OPT2);
                mQuestionLabel.setText(question);
                mYesButton.setText(option1);
                mNoButton.setText(option2);
                resetCounts();
            }
        }
    }

    private void sendIntent() {
        Intent launchResults = new Intent(MainActivity.this, ResultsActivity.class);
        launchResults.putExtra(YES_KEY, yesCount);
        launchResults.putExtra(NO_KEY, noCount);
//        launchResults.putExtra(Q_KEY, question);
        launchResults.putExtra(OPT1_KEY, option1);
        launchResults.putExtra(OPT2_KEY, option2);
        startActivityForResult(launchResults, RESULT_REQUEST_CODE);
    }

    private void resetCounts() {
        yesCount = 0;
        noCount = 0;
    }
}
