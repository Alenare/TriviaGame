package com.example.whyatttriv;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private double total;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        Trivia trivia = new Trivia(0,"What is the inside color of a kiwi?","Red","Yellow","Green","White","Green");
        dbManager.insert(trivia);
        trivia = new Trivia(1,"What is the color of a guava?","Pink","Green","Orange","Orange-Red","Pink");
        dbManager.insert(trivia);
        trivia = new Trivia(2,"What is the inside color of a Banana?","Black","Yellow","Green","White","White");
        dbManager.insert(trivia);
        trivia = new Trivia(3,"What is the inside color of a star fruit?","Orange","Yellow","Yellow-Green","White","Yellow-Green");
        dbManager.insert(trivia);
        trivia = new Trivia(4,"Does a dragon fruit have seeds?","Yes","No","Not a fruit","Depends","Yes");
        dbManager.insert(trivia);
        trivia = new Trivia(5,"What type of fruit is a yuzu?","Berry","Cores","Pits","Citrus","Citrus");
        dbManager.insert(trivia);
        trivia = new Trivia(6,"What is the inside color of a dragon fruit?","Red-pink","White","Orange","Tan","White");
        dbManager.insert(trivia);
        trivia = new Trivia(7,"What is the color of a Fig?","Purple","Green","Blue","All of the Above","All of the Above");
        dbManager.insert(trivia);
        trivia = new Trivia(8,"What is the inside color of a Passion fruit?","Yellow","Dark Blue","White","Black","Yellow");
        dbManager.insert(trivia);
        trivia = new Trivia(9,"What is the inside color of a Papaya fruit?","Yellow","Red-Green","Orange","Yellow-Red","Orange");
        dbManager.insert(trivia);
        trivia = new Trivia(10,"What is the color of a Longan fruit?","Brown","Tan","Not a fruit","White-Brown","Brown");
        dbManager.insert(trivia);
        trivia = new Trivia(11,"What is the color of a Lychee fruit?","Red","Orange","Yellow","Not a fruit","Red");
        dbManager.insert(trivia);
        trivia = new Trivia(12,"What is the inside color of a Feijora fruit?","Purple","White","Green-Yellow","Not a fruit","Green-Yellow");
        dbManager.insert(trivia);
        trivia = new Trivia(13,"What is the inside color of a Mesitoya fruit?","Purple","Not a fruit","White","Yellow","Not a fruit");
        dbManager.insert(trivia);
        trivia = new Trivia(14,"What is the color of a Rynge fruit?","Green","Dark Blue","Green-Yellow","Not a fruit","Not a fruit");
        dbManager.insert(trivia);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void onResume( ) {
        super.onResume( );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_add:
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Intent deleteIntent=new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            case R.id.action_update:
                Intent updateIntent = new Intent(this,UpdateActivity.class);
                this.startActivity(updateIntent);
                return true;
            case R.id.action_reset:
                total=0.0;
                return true;
            case R.id.action_settings:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void changeScreen( View v ) {
        Intent myIntent = new Intent(this, GameScreen.class);
        this.startActivity(myIntent);
    }


}