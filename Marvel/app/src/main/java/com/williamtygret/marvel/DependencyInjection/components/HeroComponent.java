package com.williamtygret.marvel.DependencyInjection.components;

import com.williamtygret.marvel.DependencyInjection.module.HeroModule;
import com.williamtygret.marvel.DependencyInjection.scope.PerActivity;
import com.williamtygret.marvel.modules.home.MainActivity;

import dagger.Component;

/**
 * Created by williamtygret on 8/5/17.
 */
@PerActivity
@Component(modules = HeroModule.class, dependencies = ApplicationComponent.class)
public interface HeroComponent {

    void inject(MainActivity activity);

}
