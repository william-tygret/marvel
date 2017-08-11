package com.williamtygret.marvelminviable;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by williamtygret on 8/9/17.
 */
public class HeroDataBase extends SQLiteOpenHelper {
    Context mContext;

    public HeroDataBase(Context context) {
        super(context, "heroes_db", null, 1);
        Log.d("databaseoperations","db was created");
       // mDB = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);
        Log.d("databaseoperations", "table was created");
        //not sure my table was created
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addHero(Hero hero){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,hero.getName());
        contentValues.put(DESCRIPTION,hero.getDesc());
        contentValues.put(THUMBNAIL,hero.getImgURL());
        db.insert(TABLE_NAME, null, contentValues);
        Log.d("databaseoperations", "row was inserted" + contentValues);
        db.close();
    }

    public Cursor getPokemonList(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                POKEMON_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }

//    public Cursor searchType(String query){
//
//        Log.d("searchplaces", "did we get here?");
//        SQLiteDatabase db = this.getReadableDatabase();
//        Log.d("searchplaces", "did we get here also?");
//
//        Cursor cursor = db.query(TABLE_NAME, // a. table
//                POKEMON_COLUMNS, // b. column names
//                NAME,// c. selections
//                new String[]{"%"+query+"%",query}, // d. selections args
//                null, // e. group by
//                null, // f. having
//                null, // g. order by
//                null); // h. limit
//        Log.d("cursor","our cursor is: "+cursor.getPosition());
//        return cursor;
//
//    }


    //these next few methods pull specific data out of the respective columns
    public String getHeroName(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                new String[] {NAME}, // b. column names
                ID + "= ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if(cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(NAME));
        }else{
            return "Description Not Found";
        }
    }

    public String getHeroDescription(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                new String[] {DESCRIPTION}, // b. column names
                ID + "= ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if(cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(DESCRIPTION));
        }else{
            return "Description Not Found";
        }
    }

    public String getHeroImg(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                new String[] {THUMBNAIL}, // b. column names
                ID + "= ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if(cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(THUMBNAIL));
        }else{
            return "Description Not Found";
        }
    }

    public static final String ID = "_id";
    public static final String NAME = "title";
    public static final String DESCRIPTION = "detailDescription";
    public static final String THUMBNAIL = "imageUrl";
    public static final String TABLE_NAME = "heroes_db";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String SELECT_QUERY = "SELECT "+ID+" * FROM " + TABLE_NAME;

    public static final String [] POKEMON_COLUMNS = {ID,NAME,DESCRIPTION,THUMBNAIL};

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key autoincrement not null," +
            NAME + " TEXT," +
            DESCRIPTION + " TEXT," +
            THUMBNAIL + " TEXT)";
    public static final String START ="INSERT INTO "+TABLE_NAME+" VALUES(0,)";


    //making my database accessible in a singleton
    private static HeroDataBase instance;

    public static HeroDataBase getInstance(Context context){
        if(instance == null){
            instance = new HeroDataBase(context);
        }
        return instance;
    }

}
