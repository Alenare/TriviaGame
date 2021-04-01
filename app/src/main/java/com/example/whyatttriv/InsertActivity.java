package com.example.whyatttriv;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbManager=new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View v){
        //Retrieve info
        EditText topicEditText = (EditText) findViewById(R.id.input_topic);
        EditText q1EditText = (EditText) findViewById(R.id.input_O1);
        EditText q2EditText = (EditText) findViewById(R.id.input_O2);
        EditText q3EditText = (EditText) findViewById(R.id.input_O3);
        EditText q4EditText = (EditText) findViewById(R.id.input_O4);
        EditText ansEditText = (EditText) findViewById(R.id.input_ans);
        String topic = topicEditText.getText().toString();
        String opt1 = q1EditText.getText().toString();
        String opt2 = q2EditText.getText().toString();
        String opt3 = q3EditText.getText().toString();
        String opt4 = q4EditText.getText().toString();
        String fAns = ansEditText.getText().toString();
        //insert in database
        Trivia trivia = new Trivia(0,topic,opt1,opt2,opt3,opt4,fAns);
        System.out.print("Good initialize");
        dbManager.insert(trivia);
        Toast.makeText(this, "Topic added", Toast.LENGTH_SHORT).show();


        //clear data
        topicEditText.setText("");
        q1EditText.setText("");
        q2EditText.setText("");
        q3EditText.setText("");
        q4EditText.setText("");
        ansEditText.setText("");
    }

    public void goBack(View v){
        System.out.println("Help");
        this.finish();
    }


}