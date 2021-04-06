package com.example.whyatttriv;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.graphics.Point;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class GameScreen extends AppCompatActivity   {
    private DatabaseManager dbManager;
    static int correct = 0;
    static Boolean hasSelect = false;
    private int numOfQuestions = 0;
    static int questionNum = 0;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        QuestionView();
    }



    public void QuestionView(){
        ArrayList<Trivia> triv = dbManager.selectAll( );
        numOfQuestions = triv.size();
        Trivia tAccess = null;
        int count = 0;

        for(Trivia topic: triv){
            if(count == questionNum){
                tAccess = topic;
                break;
            }else {
                count++;
            }
        }

        if(numOfQuestions > 0 && questionNum < numOfQuestions) {

            LinearLayout.LayoutParams CParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            CParam.gravity = Gravity.CENTER;
            CParam.leftMargin = 50;
            CParam.topMargin = 50;

            LinearLayout.LayoutParams LParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LParam.leftMargin = 120;
            LParam.topMargin = 90;

            LinearLayout linLayout = new LinearLayout(this);
            linLayout.setOrientation(LinearLayout.VERTICAL);

            setContentView(linLayout, CParam);

            LinearLayout.LayoutParams firstView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            setContentView(linLayout, CParam);

            LayoutParams FParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            FParam.leftMargin = 80;
            FParam.topMargin = 80;

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

            System.out.println(tAccess.getTopic());

            question = new TextView(this);
            question.setTextSize(30);
            question.setText(tAccess.getTopic());
            question.setLayoutParams(firstView);
            linLayout.addView(question, LParam);
            choices = new Button(this);
            choices.setBackgroundResource(R.drawable.mybutton1);
            choices.setTextColor(Color.parseColor("#FFFFFF"));
            choices.setTextSize(20);
            choices.setText(tAccess.getOp1());
            if(hasSelect == false) {
                choices.setOnClickListener(bh);
            }
            choices.setLayoutParams(firstView);
            linLayout.addView(choices, FParam);
            choices = new Button(this);
            choices.setBackgroundResource(R.drawable.mybutton1);
            choices.setTextColor(Color.parseColor("#FFFFFF"));
            choices.setTextSize(20);
            choices.setLayoutParams(firstView);
            choices.setText(tAccess.getOp2());
            if(hasSelect == false) {
                choices.setOnClickListener(bh);
            }
            linLayout.addView(choices, FParam);
            choices = new Button(this);
            choices.setBackgroundResource(R.drawable.mybutton1);
            choices.setTextColor(Color.parseColor("#FFFFFF"));
            choices.setTextSize(20);
            choices.setLayoutParams(firstView);
            choices.setText(tAccess.getOp3());
            if(hasSelect == false) {
                choices.setOnClickListener(bh);
            }
            linLayout.addView(choices, FParam);
            choices = new Button(this);
            choices.setBackgroundResource(R.drawable.mybutton1);
            choices.setTextColor(Color.parseColor("#FFFFFF"));
            choices.setTextSize(20);
            choices.setLayoutParams(firstView);
            choices.setText(tAccess.getOp4());
            if(hasSelect == false) {
                choices.setOnClickListener(bh);
            }
            linLayout.addView(choices, FParam);

            home.setTextSize(20);
            home.setBackgroundResource(R.drawable.mybutton2);
            home.setPadding(10, 4, 10, 4);
            home.setLayoutParams(firstView);
            linLayout.addView(home, CParam);

            if(hasSelect == true) {
                // set up event handling
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
        this.finish();
    }

    private class ButtonHandler implements View.OnClickListener {


        public void onClick( View v ) {
            ArrayList<Trivia> triv = dbManager.selectAll( );
            Trivia tAccess = null;

            int count = 0;
            for(Trivia topic: triv){
                if(count == questionNum){
                    tAccess = topic;
                    break;
                }else {
                    count++;
                }
            }
            Button ans = (Button)v;
            String text = ans.getText().toString();
            System.out.println(text);


            if(text.equals(tAccess.getAns())) {
                hasSelect = true;
                correct += 1;
                Toast.makeText( GameScreen.this, "That's the Correct Answer", Toast.LENGTH_LONG ).show( );
                QuestionView();
           }else if(text.equals("Next")){
                if(questionNum == numOfQuestions){
                    ResultView();
                }else{
                    questionNum += 1;
                    hasSelect = false;
                    QuestionView();
                }
            }
        }
    }
}
