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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                System.out.println("Hello");
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                System.out.println("Hello1");
                Intent deleteIntent=new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            case R.id.action_update:
                System.out.println("Hello2");
                Intent updateIntent = new Intent(this,UpdateActivity.class);
                this.startActivity(updateIntent);
                return true;
            case R.id.action_reset:
                System.out.println("Hello3");
                total=0.0;
                return true;
            case R.id.action_settings:
                System.out.println("Hello4");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void changeScreen( View v ) {
        Intent myIntent = new Intent(this, GameScreen.class);
        this.startActivity(myIntent);
    }


}