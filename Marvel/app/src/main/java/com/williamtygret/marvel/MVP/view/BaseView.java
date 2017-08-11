package com.williamtygret.marvel.MVP.view;

import com.williamtygret.marvel.MVP.model.Hero;

import java.util.List;

/**
 * Created by williamtygret on 8/5/17.
 */
public interface BaseView {
    void onShowToast(String message);
    void onShowDialog(String message);
    void onHideDialog();
    void onHeroLoaded(List<Hero> heroes);
}
