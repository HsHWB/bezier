package com.huehn.sense.beziertouch.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.huehn.sense.beziertouch.R;
import com.huehn.sense.beziertouch.view.BezierLoveLayout;

public class LoveActivity extends AppCompatActivity {

    private BezierLoveLayout bezierLoveLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        initView();
        initData();
    }

    private void initView(){
        bezierLoveLayout = findViewById(R.id.bezier_love_layout);
    }

    private void initData(){
        bezierLoveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bezierLoveLayout.onClick();
            }
        });
    }
}
