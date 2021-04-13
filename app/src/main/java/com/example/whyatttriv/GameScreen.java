package com.example.whyatttriv;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class GameScreen extends AppCompatActivity   {
    private DatabaseManager dbManager;
    private int[] randArray = new int[0];
    static int correct = 0;
    static boolean isSet = false;
    static Boolean hasSelect = false;
    private int numOfQuestions = 0;
    static int questionNum = 0;
    static int qAnswers = 0;
    static int arPosition = 0;
    private Trivia temp;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        QuestionView();
    }

    public void NewLength() {
        randArray = new int[numOfQuestions];
        for(int i = 0; i < numOfQuestions; i++){
            randArray[i] = -1;
        }
    }

    public boolean isUnique(int number){
        boolean test = true;
        int count = 0;

        while(count < randArray.length){
            if(number == randArray[count]){
                test = false;
            }
            count++;
        }

        return test;
    }

    public void QuestionView(){
        ArrayList<Trivia> triv = dbManager.selectAll( );
        numOfQuestions = triv.size();
        Trivia tAccess = null;
        int count = 0;
        int rand = 0;

        if(numOfQuestions > 0 && isSet == false) {
            isSet = true;
            NewLength();
        }

        if(numOfQuestions > 0 && hasSelect != true){
            rand = new Random().nextInt(triv.size());
            questionNum = rand;

             while(isUnique(rand) != true) {
                 rand = new Random().nextInt(triv.size());
                 questionNum = rand;
             }
        }

            for(Trivia topic: triv) {
                if (count == questionNum) {
                    tAccess = topic;
                    temp = topic;
                    break;
                } else {
                    count++;
                }
            }

        if(numOfQuestions > 0 && qAnswers < numOfQuestions) {

            LinearLayout.LayoutParams CParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            CParam.gravity = Gravity.CENTER;
            CParam.leftMargin = 70;
            CParam.topMargin = 70;
            CParam.rightMargin = 80;

            LinearLayout.LayoutParams LParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LParam.leftMargin = 120;
            LParam.topMargin = 90;
            LParam.rightMargin = 80;
            LParam.gravity = Gravity.CENTER;

            LinearLayout linLayout = new LinearLayout(this);
            linLayout.setOrientation(LinearLayout.VERTICAL);

            setContentView(linLayout, CParam);

            LinearLayout.LayoutParams firstView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            setContentView(linLayout, CParam);

            LayoutParams FParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            FParam.leftMargin = 70;
            FParam.topMargin = 70;
            FParam.rightMargin= 80;

            TextView question = new TextView(this);
            Button choices = new Button(this);
            Button home = new Button(this);
            Button next = new Button(this);
            ButtonHandler bh = new ButtonHandler( );

            // create the button
            next = new Button( this );
            next.setText( "Next" );


            // create the button
            home = new Button( this );
            home.setText( "Home" );
            home.setOnClickListener(this::goBack);

            question = new TextView(this);
            question.setTextSize(30);
            question.setText(tAccess.getTopic());
            question.setLayoutParams(firstView);
            question.setGravity(Gravity.CENTER);
            question.setPadding(0,20,20,20);
            linLayout.addView(question, LParam);

            for(int i = 0; i < 4; i++){
                choices = new Button(this);
                choices.setBackgroundResource(R.drawable.mybutton1);
                choices.setTextColor(Color.parseColor("#FFFFFF"));
                choices.setTextSize(20);
                choices.setLayoutParams(firstView);
                if(i == 0) {
                    choices.setText(tAccess.getOp1());
                }else if(i == 1){
                    choices.setText(tAccess.getOp2());
                }else if(i == 2){
                    choices.setText(tAccess.getOp3());
                }else if(i == 3){
                    choices.setText(tAccess.getOp4());
                }
                if (hasSelect == false) {
                    choices.setOnClickListener(bh);
                }
                linLayout.addView(choices, FParam);
            }
            home.setTextSize(20);
            home.setBackgroundResource(R.drawable.mybutton2);
            home.setPadding(10, 4, 10, 4);
            home.setLayoutParams(firstView);
            linLayout.addView(home, CParam);

            if(hasSelect == true) {
                next.setOnClickListener( bh );
            }else {

            }
                // set up event handling
            next.setTextSize(20);
            next.setBackgroundResource(R.drawable.mybutton2);
            next.setPadding(10, 4, 10, 4);
            next.setLayoutParams(firstView);
            linLayout.addView(next, CParam);

        }else{
            ResultView();
        }

    }

    public void ResultView(){

        LinearLayout.LayoutParams CParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        CParam.gravity = Gravity.CENTER;
        CParam.leftMargin = 40;
        CParam.topMargin = 50;

        LinearLayout.LayoutParams LParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LParam.leftMargin = 70;
        LParam.topMargin = 800;

        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);

        setContentView(linLayout, CParam);

        LinearLayout.LayoutParams firstView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        setContentView(linLayout, CParam);

        TextView Results = new TextView(this);
        Button home = new Button(this);
        ButtonHandler bh = new ButtonHandler( );

        // create the button
        home = new Button( this );
        home.setText( "Home" );
        home.setOnClickListener(this::goBack);

        Results = new TextView(this);
        Results.setTextSize(20);
        Results.setText("Congrats you got "+Integer.toString(correct) + " out of " + Integer.toString(numOfQuestions) + " correct!");
        Results.setLayoutParams(firstView);
        linLayout.addView(Results, LParam);
        home.setTextSize(20);
        home.setBackgroundResource(R.drawable.mybutton2);
        home.setPadding(10, 4, 10, 4);
        home.setLayoutParams(firstView);
        linLayout.addView(home, CParam);

    }

    public void goBack(View v){
        questionNum = 0;
        hasSelect = false;
        correct = 0;
        arPosition = 0;
        isSet = false;
        qAnswers = 0;
        randArray = new int[0];
        this.finish();
    }

    private class ButtonHandler implements View.OnClickListener {


        public void onClick( View v ) {
            Button ans = (Button)v;
            String text = ans.getText().toString();

            if(text.equals(temp.getAns())) {
                hasSelect = true;
                correct += 1;
                qAnswers += 1;
                Toast.makeText( GameScreen.this, "That's the Correct Answer", Toast.LENGTH_LONG ).show( );
                randArray[arPosition] = questionNum;
                arPosition++;
                QuestionView();
           }else if(text.equals("Next")){
                if(questionNum == numOfQuestions){
                    ResultView();
                }else{
                    hasSelect = false;
                    QuestionView();
                }
            }else if(!text.equals(temp.getAns())) {
                hasSelect = true;
                qAnswers += 1;
                Toast.makeText(GameScreen.this, "That's the Incorrect Answer", Toast.LENGTH_LONG).show();
                randArray[arPosition] = questionNum;
                arPosition++;
                QuestionView();
            }
        }
    }
}
