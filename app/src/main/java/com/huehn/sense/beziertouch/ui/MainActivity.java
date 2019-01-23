package com.huehn.sense.beziertouch.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huehn.sense.beziertouch.view.BezierTouchView;
import com.huehn.sense.beziertouch.R;

public class MainActivity extends AppCompatActivity {

    private BezierTouchView bezierLine;
    private BezierTouchView bezierQuadTo;
    private RelativeLayout relativeLayout;
    private TextView loveText;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        initView();
    }

    private void initView(){
        relativeLayout = findViewById(R.id.parent_layout);
        bezierLine = findViewById(R.id.bezier_line);
        bezierQuadTo = findViewById(R.id.bezier_quadto);
        loveText = findViewById(R.id.bezier_love);

        bezierLine.setQuad(false);
        bezierQuadTo.setQuad(true);

        updateWidth(bezierLine);
        updateWidth(bezierQuadTo);
        setListener();
    }

    private void setListener(){
        loveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void updateWidth(View view){
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = this.width / 2;
        view.setLayoutParams(layoutParams);
    }
}
