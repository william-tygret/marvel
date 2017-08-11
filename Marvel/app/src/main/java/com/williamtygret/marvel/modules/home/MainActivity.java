package com.williamtygret.marvel.modules.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.williamtygret.marvel.API.HeroAPIService;
import com.williamtygret.marvel.DependencyInjection.components.DaggerHeroComponent;
import com.williamtygret.marvel.DependencyInjection.module.HeroModule;
import com.williamtygret.marvel.MVP.model.Hero;
import com.williamtygret.marvel.MVP.model.MyDescFragment;
import com.williamtygret.marvel.MVP.model.MyListFragment;
import com.williamtygret.marvel.MVP.model.Storage;
import com.williamtygret.marvel.MVP.model.myAdapter;
import com.williamtygret.marvel.MVP.presenter.HeroPresenter;
import com.williamtygret.marvel.MVP.view.MainView;
import com.williamtygret.marvel.R;
import com.williamtygret.marvel.base.BaseActivity;
import com.williamtygret.marvel.base.BasePresenter;
import com.williamtygret.marvel.modules.home.adapter.HeroAdapter;
import com.williamtygret.marvel.utilities.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.hero_list)
    protected RecyclerView mHeroList;
    HeroAdapter mHeroAdapter;
    public String mSearchQuery = "";

    @Inject
    protected HeroPresenter mPresenter;

    CursorAdapter mCursorAdapter;
    Cursor mCursor;

    @Inject
    protected Storage mStorage;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeList();

//        if(savedInstanceState == null){
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.list_container,new MyListFragment())
//                    .add(R.id.edition_container,new MyDescFragment())
//                    .commit();
//        }

//        mPresenter.getHeroesFromDatabase();

        if (NetworkUtils.isNetAvailable(this)) {
            mPresenter.getHeroes();
        } else {
            mPresenter.getHeroesFromDatabase();
        }



//        String dbguys = mStorage.getSavedHeroes().toString();
  //      Log.d("heroesfromDB", "we got from db: " + dbguys);

        mCursor = null;
        mCursorAdapter = new myAdapter(this,mCursor) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return null;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {

            }
        };


    }

    private void initializeList() {
        mHeroList.setHasFixedSize(true);
        mHeroList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mHeroAdapter = new HeroAdapter(getLayoutInflater());
        mHeroList.setAdapter(mHeroAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerHeroComponent.builder()
                .applicationComponent(getApplicationComponent())
                .heroModule(new HeroModule(this))
                .build().inject(this);

    }

    @Override
    public void onHeroLoaded(List<Hero> heros) {
        mHeroAdapter.addHeroes(heros);
       // mHeroAdapter.searchResults(cursor);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {
        mHeroAdapter.clearHeroes();
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
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("querryyy","query iss: "+query);
            //mTextView.setText("Search Pokemon!");

            // SELECT * FROM awesometable WHERE name = ? (or WHERE name LIKE ?)
            Cursor cursor = Storage.getInstance(this).searchType(query);
            Log.d("cursor", "cursor is: " + cursor);

            mCursorAdapter.swapCursor(cursor);
        }
    }



    public String getSearchQuery(String searchQuery) {
        mSearchQuery = searchQuery;
        return searchQuery;
    }

}
