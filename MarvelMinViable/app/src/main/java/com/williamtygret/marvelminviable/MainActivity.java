package com.williamtygret.marvelminviable;

import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    ArrayList<Hero> mHeroes;
    HeroAdapter mHeroAdapter;
    FrameLayout mFrameLayout;
    TextView mNameViewerTextView;

    HeroDataBase mHeroDataBase;
    Context mContext = this;
    SQLiteDatabase mSQLiteDatabase;

    ImageView mImageView;

    CursorAdapter mCursorAdapter;

    String query;
    private String urlMarvel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.listView);
        mImageView = (ImageView)findViewById(R.id.imageView);

        mHeroes = new ArrayList<Hero>();
        //mHeroDataBase = new HeroDataBase(getBaseContext());
        mHeroAdapter = new HeroAdapter(this, mHeroes);
        Cursor cursor = null;
        mCursorAdapter = new MyCursorAdapter(this,cursor);
        mListView.setAdapter(mCursorAdapter);

        // SELECT * FROM awesometable WHERE name = ? (or WHERE name LIKE ?)
        Cursor cursor2 = HeroDataBase.getInstance(this).getPokemonList();
        Log.d("cursor", "cursor is: " + cursor2);

        mCursorAdapter.swapCursor(cursor2);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = mCursorAdapter.getCursor();
                Intent intent = new Intent(MainActivity.this, HeroActivity.class);
                cursor.moveToPosition(position);
                int theID = cursor.getInt(cursor.getColumnIndex(HeroDataBase.ID));//ShoppingSQLiteOpenHelper.getInstance(MainActivity.this).getIdByName(itemName);
                intent.putExtra("id", theID);
                startActivityForResult(intent,100);
            }
        });




    }

    //creates my search feature
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;

    }


    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("newintent", "we got here");
        handleIntent(intent);


    }


    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            // where you do the actual database search
            Log.d("handleintent", "we got here");

            query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("queryyy","the query is: "+query);
            urlMarvel = "https://gateway.marvel.com/v1/public/characters?nameStartsWith="+query+"&apikey=fa443fc4b33f7237765dc9a31c13aa7c&ts=12345&hash=bb42fc23b33291300948ce0bfa64a6b3";
            //mTextView.setText("Search Pokemon!");
//            mHeroAdapter.clear();
//            mHeroAdapter.notifyDataSetChanged();
            DownloadAsyncHeroes downloadAsyncHeroes = new DownloadAsyncHeroes();
            downloadAsyncHeroes.execute(urlMarvel);
            Log.d("urlmarvel", "url marvel is: " + query);

//            // SELECT * FROM awesometable WHERE name = ? (or WHERE name LIKE ?)
//            Cursor cursor = HeroDataBase.getInstance(this).getPokemonList();
//            Log.d("cursor", "cursor is: " + cursor);
//
//            mCursorAdapter.swapCursor(cursor);

        }
    }

    public class DownloadAsyncHeroes extends AsyncTask<String, Void, String> {

        String data;
        String name;
        String imgurl;
        JSONObject theObject;
        String desc;

        String nameHero;
        String imgurlHero;
        String descHero;
        HeroDataBase mHeroDataBase;






        @Override
        protected String doInBackground(String... urls) {

            mHeroDataBase = new HeroDataBase(getBaseContext());

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                mHeroDataBase = new HeroDataBase(mContext);

                InputStream inStream = connection.getInputStream();

                data = getInputData(inStream);
                Log.d("StupidGuy", "we got the data " + data);

            } catch (Throwable thro) {
                thro.printStackTrace();
            }


            try {
                JSONObject dataObject = new JSONObject(data);
                Log.d("dataobj","the dataobject is: "+dataObject);
                JSONObject dataPack = dataObject.getJSONObject("data");
                Log.d("dataaaaa","the dataobject is: "+dataPack);
                JSONArray heroArray = dataPack.getJSONArray("results");
                Log.d("heroArray","the dataobject is: "+heroArray);
                for(int counter=1;counter<heroArray.length();counter++){
                    JSONObject theObject = heroArray.getJSONObject(counter);
                    Log.d("theobject","this should be an array of data: "+theObject);
                    JSONObject thumbnail = theObject.getJSONObject("thumbnail");

                    nameHero = theObject.getString("name");
                    String imgurlHerooo = thumbnail.getString("path");
                    imgurlHero = imgurlHerooo;
                    Log.d("imgurl", "the imgurlhero is " + imgurlHero);
                    descHero = theObject.getString("description");


                    Hero coolGuyHero = new Hero("","","");
                    coolGuyHero.setName(nameHero);
                    coolGuyHero.setImgURL(imgurlHero);
                    coolGuyHero.setDesc(descHero);


//                    mHeroDataBase.getWritableDatabase();


//                    mHeroDataBase = new HeroDataBase(mContext);
                    mHeroes.add(coolGuyHero);
                    mHeroDataBase.addHero(coolGuyHero);

                    Log.d("coolguy", "cool guy hero is: " + nameHero);
                    Log.d("coolguy","cool guy hero is: "+coolGuyHero);


                }
                JSONObject firstObj = heroArray.getJSONObject(0);
                Log.d("zerospace","the first object in the array is:" +firstObj);
                name = firstObj.getString("name");
                JSONObject thumbnail = firstObj.getJSONObject("thumbnail");
                desc = thumbnail.getString("path");


                for(int counter=0;counter<heroArray.length();counter++){
                    theObject = heroArray.optJSONObject(counter);
                    Log.d("theObject","the object is: "+theObject);
                    nameHero = theObject.getString("name");
                    JSONObject thumb = theObject.getJSONObject("thumbnail");

                    imgurlHero = thumb.getString("icon");
                    Log.d("imgurlHero", "imgurlhero is "+imgurlHero);
                    //mForecasts.add(dateForecast,maxForecast,minForecast,null);


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("returnData","our return data is: "+data);
            return data;
        }

//        public void addHero(View view){
//            mHeroDataBase = new HeroDataBase(mContext);
//            mSQLiteDatabase = mHeroDataBase.getWritableDatabase();
//            mHeroDataBase.addHero(name,desc,imgurl);
//            Toast.makeText(getBaseContext(),"Data SAved",Toast.LENGTH_LONG).show();
//            mHeroDataBase.close();
//        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);
//            mHeroDataBase = new HeroDataBase(getBaseContext());
            Log.d("iconnnnn", "the icon is: " + imgurl);
            mHeroAdapter.notifyDataSetChanged();



            // SELECT * FROM awesometable WHERE name = ? (or WHERE name LIKE ?)
            Cursor cursor = HeroDataBase.getInstance(MainActivity.this).getPokemonList();
            Log.d("cursor", "cursor is: " + cursor);

            mCursorAdapter.swapCursor(cursor);

        }

        private String getInputData(InputStream inStream) throws IOException {
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

            String data;

            while((data = reader.readLine()) != null){
                builder.append(data);
            }

            reader.close();

            return builder.toString();
        }



    }




}
