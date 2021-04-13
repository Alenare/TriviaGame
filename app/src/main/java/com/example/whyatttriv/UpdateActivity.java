package com.example.whyatttriv;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//import android.support.v7.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity  {
    DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseManager( this );
        updateView( );
    }

    // Build a View dynamically with all the topics
    public void updateView( ) {
        ArrayList<Trivia> triv = dbManager.selectAll( );

        if( triv.size( ) > 0 ) {
            ScrollView scrollView = new ScrollView( this );
            GridLayout grid = new GridLayout( this );
            grid.setRowCount( triv.size( ));
            grid.setColumnCount( 1 );

            // create arrays of components
            TextView [] ids = new TextView[triv.size( )];
            EditText [][] topicDetails = new EditText[triv.size( )][8];
            Button [] buttons = new Button[triv.size( )];
            ButtonHandler bh = new ButtonHandler( );

            // retrieve width of screen
            Point size = new Point( );
            getWindowManager( ).getDefaultDisplay( ).getSize( size );
            int width = size.x;
            int i = 0;

            for (Trivia tAccess : triv ) {
                // create the TextView for the candy's id
                ids[i] = new TextView( this );
                ids[i].setGravity( Gravity.CENTER );
                ids[i].setText( "" + tAccess.getID( ) );


                topicDetails[i][0] = new EditText( this );
                topicDetails[i][1] = new EditText( this );
                topicDetails[i][2] = new EditText( this );
                topicDetails[i][3] = new EditText( this );
                topicDetails[i][4] = new EditText( this );
                topicDetails[i][5] = new EditText( this );
                topicDetails[i][0].setText( tAccess.getTopic( ) );
                topicDetails[i][1].setText( tAccess.getOp1( ) );
                topicDetails[i][2].setText( tAccess.getOp2( ) );
                topicDetails[i][3].setText( tAccess.getOp3( ) );
                topicDetails[i][4].setText( tAccess.getOp4( ) );
                topicDetails[i][5].setText( tAccess.getAns( ) );
                topicDetails[i][0].setId( 10 * tAccess.getID( ) );
                topicDetails[i][1].setId( 10 * tAccess.getID( ) + 1 );
                topicDetails[i][2].setId( 10 * tAccess.getID( ) + 2 );
                topicDetails[i][3].setId( 10 * tAccess.getID( ) + 3 );
                topicDetails[i][4].setId( 10 * tAccess.getID( ) + 4 );
                topicDetails[i][5].setId( 10 * tAccess.getID( ) + 5 );

                // create the button
                buttons[i] = new Button( this );
                buttons[i].setText( "Update" );
                buttons[i].setId( tAccess.getID( ) );

                // set up event handling
                buttons[i].setOnClickListener( bh );

                // add the elements to grid

                grid.addView( topicDetails[i][0], ( int ) ( width * .9),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( topicDetails[i][1], ( int ) ( width * .9),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( topicDetails[i][2], ( int ) ( width * .9),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( topicDetails[i][3], ( int ) ( width * .9),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( topicDetails[i][4], ( int ) ( width * .9),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( topicDetails[i][5], ( int ) ( width * .9),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( buttons[i], ( int ) ( width * .35 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }
            scrollView.addView( grid );
            setContentView( scrollView );
        }
            // create ScrollView and GridLayout
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            int topicId = v.getId( );
            EditText theTopic = ( EditText ) findViewById( 10 * topicId );
            EditText option1 = ( EditText ) findViewById( 10 * topicId + 1 );
            EditText option2 = ( EditText ) findViewById( 10 * topicId + 2 );
            EditText option3 = ( EditText ) findViewById( 10 * topicId + 3 );
            EditText option4 = ( EditText ) findViewById( 10 * topicId + 4 );
            EditText correctAns = ( EditText ) findViewById( 10 * topicId + 5);

            //utd: Up To Date
            String newTopic = theTopic.getText( ).toString( );
            String oP1 = option1.getText( ).toString( );
            String oP2 = option2.getText( ).toString( );
            String oP3 = option3.getText( ).toString( );
            String oP4 = option4.getText( ).toString( );
            String ans =  correctAns.getText().toString();

            // update candy in database

            dbManager.updateById(topicId,newTopic,oP1,oP2,oP3,oP4,ans);
            Toast.makeText( UpdateActivity.this, "Topic updated", Toast.LENGTH_SHORT ).show( );
            updateView();
        }
    }
}

