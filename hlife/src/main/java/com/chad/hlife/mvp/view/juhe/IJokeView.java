package com.chad.hlife.mvp.view.juhe;

import com.chad.hlife.entity.juhe.JokeInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IJokeView extends IBaseView {

    void onJokeInfo(JokeInfo jokeInfo);

    void onMoreJokeInfo(JokeInfo jokeInfo);
}
