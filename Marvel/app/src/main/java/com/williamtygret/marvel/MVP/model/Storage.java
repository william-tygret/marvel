package com.williamtygret.marvel.MVP.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by williamtygret on 8/6/17.
 */
public class Storage extends SQLiteOpenHelper {
    private static final String TAG = Storage.class.getSimpleName();
    Cursor mCursor;

    @Inject
    public Storage(Context context) {
        super(context, "cakes_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addHero(Hero hero) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, hero.getName());
        values.put(HERO_ID, hero.getId());
        values.put(DESCRIPTION, hero.getDescription());
        values.put(THUMBNAIL, hero.getThumbnail());


            db.insert(TABLE_NAME, null, values);


        db.close();
    }

    public Cursor searchType(String query){

        Log.d("searchplaces", "did we get here?");
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("searchplaces", "did we get here also?");

        Cursor cursor = db.query(TABLE_NAME, // a. table
                POKEMON_COLUMNS, // b. column names
                NAME + " LIKE ? ",// c. selections
                new String[]{"%" + query + "%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        Log.d("cursor", "our cursor is: " + cursor.getPosition());
        return cursor;
    }

    public List<Hero> getSavedHeroes() {
        List<Hero> cakeList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();


            Cursor cursor = db.rawQuery(SELECT_QUERY, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    if (cursor.moveToFirst()) {
                        do {
                            Hero cake = new Hero();
                            cake.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                            cake.setId(cursor.getString(cursor.getColumnIndex(HERO_ID)));
                            cake.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                            cake.setThumbnail(cursor.getString(cursor.getColumnIndex(THUMBNAIL)));

                            cakeList.add(cake);

                        } while (cursor.moveToNext());
                    }
                }
            }
        cursor.close();
        return cakeList;
    }

    public static final String ID = "_id";
    public static final String NAME = "title";
    public static final String HERO_ID = "previewDescription";
    public static final String DESCRIPTION = "detailDescription";
    public static final String THUMBNAIL = "imageUrl";
    public static final String TABLE_NAME = "cakes";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String SELECT_QUERY = "SELECT "+ID+" * FROM " + TABLE_NAME;

    public static final String [] POKEMON_COLUMNS = {ID,HERO_ID,NAME,DESCRIPTION,THUMBNAIL};

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "( " +
            ID + " integer primary key autoincrement not null," +
            NAME + " text not null," +
            HERO_ID + " text not null," +
            DESCRIPTION + " text not null," +
            THUMBNAIL + " text not null)";
    public static final String START ="INSERT INTO "+TABLE_NAME+" VALUES(0,)";


    //making my database accessible in a singleton
    private static Storage instance;

    public static Storage getInstance(Context context){
        if(instance == null){
            instance = new Storage(context);
        }
        return instance;
    }


//    public Cursor searchType(String query){
//
//        Log.d("searchplaces", "did we get here?");
//        SQLiteDatabase db = this.getReadableDatabase();
//        Log.d("searchplaces", "did we get here also?");
//
//        mCursor = db.query(TABLE_NAME, // a. table
//                POKEMON_COLUMNS, // b. column names
//                NAME + " LIKE ? ",// c. selections
//                new String[]{"%" + query + "%"}, // d. selections args
//                null, // e. group by
//                null, // f. having
//                null, // g. order by
//                null); // h. limit
//        Log.d("cursor","our cursor is: "+mCursor.getPosition());
//        return mCursor;
//    }



}
