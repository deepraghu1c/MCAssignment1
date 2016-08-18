package com.example.zircon.objectivequiz;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    private int randNum;
    private final Random randGen = new Random();
    private boolean ans = false;
    private String numString = "";
    private TextView question;
    private Button trueButton, falseButton, nextButton;
    private static final String TAG = "QuizActivity";


    private void setButtonsEnable(boolean flag) {
        trueButton.setEnabled(flag);
        falseButton.setEnabled(flag);
        nextButton.setEnabled(flag);
    }

    private void setBackGroundColor(int a, int b, int c, int d) {

        trueButton.setBackgroundColor(Color.argb(a, b, c, d));
        falseButton.setBackgroundColor(Color.argb(a, b, c, d));
        nextButton.setBackgroundColor(Color.argb(a, b, c, d));
    }

    private void setTextColor(int a, int b, int c, int d) {

        trueButton.setTextColor(Color.argb(a, b, c, d));
        falseButton.setTextColor(Color.argb(a, b, c, d));
        nextButton.setTextColor(Color.argb(a, b, c, d));
    }

    private void setToInitialState() {
        question.setText(numString);

        setBackGroundColor(26,0,0,0);
        setTextColor(255,0,0,0);
        setButtonsEnable(true);
    }


    private boolean isPrime(int numString) {
        if (numString == 1)
            return false;
        for (int i = 2; i <= (int) Math.sqrt((double) numString); i++) {
            if (numString % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void handleTrue() {
        if (ans) {
            Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
            trueButton.setBackgroundColor(Color.argb(125, 0, 255, 0));
        } else {
            Toast.makeText(getApplicationContext(), "Incorrect Answer, Try Again..", Toast.LENGTH_SHORT).show();
            trueButton.setBackgroundColor(Color.argb(175, 255, 0, 0));
        }
    }


    private void handleFalse() {
        if (!ans) {
            Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
            falseButton.setBackgroundColor(Color.argb(125, 0, 255, 0));
        } else {
            Toast.makeText(getApplicationContext(), "Incorrect Answer, Try Again..", Toast.LENGTH_SHORT).show();
            falseButton.setBackgroundColor(Color.argb(175, 255, 0, 0));
        }
    }

    private void nextQuery(boolean mode) {

        setButtonsEnable(false);

        randNum = randGen.nextInt(1000) + 1;
        numString = " " + Integer.toString(randNum);
        ans = isPrime(randNum);

        if (mode) {
            setToInitialState();
        } else {

            findViewById(R.id.screen).animate().alpha(0f).setDuration(500).withEndAction(new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.screen).animate().alpha(1f).setDuration(500);
                    setToInitialState();
                }
            });

            if (ans)
                Log.i("numString " + Integer.toString(randNum) + " = ", "Prime");
            else
                Log.i("numString " + Integer.toString(randNum) + " = ", "Not Prime");
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.trueButton:
                handleTrue();
                trueButton.setTextColor(Color.argb(255, 255, 255, 255));
                nextQuery(false);
                break;
            case R.id.falseButton:
                handleFalse();
                falseButton.setTextColor(Color.argb(255, 255, 255, 255));
                nextQuery(false);
                break;
            case R.id.nextButton:
                nextButton.setBackgroundColor(Color.argb(255, 71, 83, 200));
                nextButton.setTextColor(Color.argb(255, 255, 255, 255));
                nextQuery(false);
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Inside OnCreate");
        setContentView(R.layout.activity_quiz);

        question = (TextView) findViewById(R.id.question);
        trueButton = (Button) findViewById(R.id.trueButton);
        falseButton = (Button) findViewById(R.id.falseButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        nextQuery(true);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onSaveInstance");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Inside OnPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Inside OnResume");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Inside OnStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }

    @SuppressWarnings("EmptyMethod")
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("Orientation", "Landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i("Orientation", "Portrait");
        }
    }
}
