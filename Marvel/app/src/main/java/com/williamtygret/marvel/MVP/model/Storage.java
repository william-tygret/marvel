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

/**
 * Created by williamtygret on 8/6/17.
 */
public class Storage extends SQLiteOpenHelper {

    private static final String TAG = Storage.class.getSimpleName();

    private static final String ID ="id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String THUMBNAIL = "thumbnail";
    private static final String RESOURCEURI = "resourceURI";
    private static final String COMICS = "comics";
    private static final String SERIES = "series";
    private static final String STORIES = "stories";
    private static final String TABLE_NAME = "heroes";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String SELECT_QUERY = "SELECT * FROM "+TABLE_NAME;

    private Context mHelperContext;

    public static final String[] HERO_COLUMNS = {ID,NAME,DESCRIPTION,RESOURCEURI};

    public static final String CREATE_TABLE =
            "create table"+TABLE_NAME+ "" +
                    ID+"integer primary key autoincrement not null"+
                    NAME+"text not null"+
                    DESCRIPTION+"text not null"+
                    //THUMBNAIL+"text not null"+
                    RESOURCEURI+"text not null";
                   // COMICS+"text not null"+
                   // SERIES+"text not null"+
                    //STORIES+"text not null"


    private static Storage instance;

    public static Storage getInstance(Context context){
        if(instance == null){
            instance = new Storage(context);
        }
        return instance;
    }

    @Inject
    public Storage(Context context) {
        super(context, "heroes_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            db.execSQL(CREATE_TABLE);
            Log.d("HeroDatabaseHelper", "SQL: "+CREATE_TABLE);
        }catch (android.database.SQLException e){
            Log.d(TAG, e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addHero(Hero hero){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, hero.getName());
        values.put(ID,hero.getId());
        values.put(DESCRIPTION, hero.getDescription());
       // values.put(THUMBNAIL, hero.getThumbnail());

        try {
            db.insert(TABLE_NAME,null,values);
        }catch (android.database.SQLException e){
            Log.d(TAG, e.getMessage());
        }

        db.close();

    }

    public List<Hero> getSavedHeroes() {
        List<Hero> heroList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            Cursor cursor = db.rawQuery(SELECT_QUERY, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    if (cursor.moveToFirst()) {

                        do {
                            Hero hero = new Hero();
                            hero.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                            hero.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                            hero.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                            hero.setResourceURI(cursor.getString(cursor.getColumnIndex(RESOURCEURI)));

                        } while (cursor.moveToNext());
                    }
                }

            }

        } catch (android.database.SQLException e) {
            Log.d(TAG, e.getMessage());
        }
        return heroList;

    }
}
