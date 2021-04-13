package com.example.whyatttriv;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "triviaDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TRIVIA = "Trivia";
    private static final String ID = "id";
    private static final String Topic = "Question";
    private static final String Option1 = "choice1";
    private static final String Option2 = "choice2";
    private static final String Option3 = "choice3";
    private static final String Option4 = "choice4";
    private static final String Answer = "correct";

    public DatabaseManager(Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public void onCreate( SQLiteDatabase db ) {
        // build sql create statement

        String sqlCreate = "create table " + TABLE_TRIVIA + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + Topic;
        sqlCreate += " text, " + Option1;
        sqlCreate += " text, " + Option2;
        sqlCreate += " text, " + Option3;
        sqlCreate += " text, " + Option4;
        sqlCreate += " text, " + Answer + " text) ";

        db.execSQL( sqlCreate );
    }

    public void onUpgrade( SQLiteDatabase db,
                           int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_TRIVIA);
        // Re-create tables
        onCreate( db );
    }

    public void insert(Trivia question) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_TRIVIA;
        sqlInsert += " values( null, '" + question.getTopic( );
        sqlInsert += "', '" + question.getOp1( );
        sqlInsert += "', '" + question.getOp2( );
        sqlInsert += "', '" + question.getOp3( );
        sqlInsert += "', '" + question.getOp4( );
        sqlInsert += "', '" + question.getAns( ) + "' )";

        db.execSQL( sqlInsert );
        db.close( );
    }

    public void deleteById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_TRIVIA;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL( sqlDelete );
        db.close( );
    }

    public void updateById( int id, String newTopic, String oP1, String oP2, String oP3, String oP4, String ans) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_TRIVIA;
        sqlUpdate += " set " + Topic + " = '" + newTopic  + "', ";
        sqlUpdate += Option1 + " = '" + oP1 + "', ";
        sqlUpdate += Option2 + " = '" + oP2 + "', ";
        sqlUpdate += Option3 + " = '" + oP3 + "', ";
        sqlUpdate += Option4 + " = '" + oP4 + "', ";
        sqlUpdate += Answer + " = '" + ans + "'";
        sqlUpdate += " where " + ID + " = " + id;
        db.execSQL( sqlUpdate );

        db.close( );
    }

    public ArrayList<Trivia> selectAll( ) {
        String sqlQuery = "select * from " + TABLE_TRIVIA;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Trivia> trivia = new ArrayList<Trivia>( );
        while( cursor.moveToNext( ) ) {

            Trivia curTopic = new Trivia(  cursor.getInt(0),
                    cursor.getString( 1 ), cursor.getString( 2 ),
                    cursor.getString( 3 ), cursor.getString( 4 ),
                    cursor.getString( 5 ), cursor.getString( 6 ));
            trivia.add( curTopic );


        }
        db.close( );
        return trivia;
    }

    public void DeleteRecords(){
        SQLiteDatabase db = this.getWritableDatabase( );
        db.execSQL("DELETE FROM " + TABLE_TRIVIA);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_TRIVIA + "'");
        db.close();

    }
    public Trivia selectById( int id ) {
        String sqlQuery = "select * from " + TABLE_TRIVIA;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        Trivia topic = null;

        if( cursor.moveToFirst( ) )
            topic = new Trivia( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getString( 2 ),
                    cursor.getString( 3 ), cursor.getString( 4 ),
                    cursor.getString( 5 ), cursor.getString( 6 ));
        return topic;
    }
}

