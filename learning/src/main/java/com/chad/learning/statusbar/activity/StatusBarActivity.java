package com.chad.learning.statusbar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chad.learning.R;
import com.chad.learning.statusbar.KitKatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatusBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_kitkat)
    public void onKitKatClick() {
        Intent intent = new Intent(this, KitKatActivity.class);
        startActivity(intent);
    }
}
