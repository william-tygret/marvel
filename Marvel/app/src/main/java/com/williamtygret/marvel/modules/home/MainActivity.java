package com.williamtygret.marvel.modules.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.williamtygret.marvel.API.HeroAPIService;
import com.williamtygret.marvel.DependencyInjection.components.DaggerHeroComponent;
import com.williamtygret.marvel.DependencyInjection.module.HeroModule;
import com.williamtygret.marvel.MVP.model.Hero;
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

    @Bind(R.id.hero_list) protected RecyclerView mHeroList;
    HeroAdapter mHeroAdapter; public String mSearchQuery="";

    @Inject
    protected HeroPresenter mPresenter;


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeList();
        mPresenter.getHeroes();

//        if(NetworkUtils.isNetAvailable(this)){
//            mPresenter.getHeroes();
//        }else{
//            mPresenter.getHeroesFromDatabase();
//        }

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
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
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
        SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // This is your adapter that will be filtered
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                // **Here you can get the value "query" which is entered in the search box.**
                mSearchQuery = query;
                Log.d("queryyyy","the query is: "+query);
                Log.d("queryyy2","other query is: "+mSearchQuery);


                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return super.onCreateOptionsMenu(menu);
    }

    public String getSearchQuery(String searchQuery){
        mSearchQuery=searchQuery;
        return searchQuery;
    }

}
