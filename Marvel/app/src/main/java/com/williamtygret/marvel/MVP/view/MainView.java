package com.williamtygret.marvel.MVP.view;

import com.williamtygret.marvel.MVP.model.Hero;

import java.util.List;

/**
 * Created by williamtygret on 8/5/17.
 */
public interface MainView extends BaseView{
    void onHeroLoaded(List<Hero> heros);
    void onShowDialog(String message);
    void onHideDialog();
    void onShowToast(String message);

    void onClearItems();
}
