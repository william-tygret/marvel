package com.williamtygret.marvel.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

import com.williamtygret.marvel.DependencyInjection.components.ApplicationComponent;
import com.williamtygret.marvel.MVP.model.HeroesResponse;
import com.williamtygret.marvel.MVP.model.Query;
import com.williamtygret.marvel.application.HeroApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by williamtygret on 8/5/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        onViewReady(savedInstanceState,getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent){
        //to be used by child activities
        resolveDaggerDependency();

    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    protected void resolveDaggerDependency() {
    }

    protected void showDialog(String message){
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideDialog(){
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();

        }
    }

    protected ApplicationComponent getApplicationComponent(){
        return ((HeroApplication) getApplication()).getApplicationComponent();
    }



    protected abstract int getContentView();

}
