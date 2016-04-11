package com.hy.lyx.fb.gw.wyx.lks.flyingchess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class ChessBoardAct extends AppCompatActivity {
    RelativeLayout frame;
    Timer screenTimer,closeTimer;
    Button pause,dice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ui setting
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_board);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);//Activity切换动画
        int uiOpts = View.SYSTEM_UI_FLAG_FULLSCREEN;
        getWindow().getDecorView().setSystemUiVisibility(uiOpts);
        //init
        frame=(RelativeLayout)findViewById(R.id.chessBoard);
        screenTimer=new Timer();
        closeTimer=new Timer();
        pause=(Button)findViewById(R.id.pause);
        dice=(Button)findViewById(R.id.dice);
        //trigger
        frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                screenTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        frame.post(new Runnable() {
                            @Override
                            public void run() {
                                int uiOpts = View.SYSTEM_UI_FLAG_FULLSCREEN;
                                getWindow().getDecorView().setSystemUiVisibility(uiOpts);
                            }
                        });
                    }
                }, 3500);
                return true;
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameInfoAct.class);
                startActivity(intent);
                closeTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
        dice.setOnClickListener(new View.OnClickListener() {//throw dice
            @Override
            public void onClick(View v) {
                GameManager.throwDice();
            }
        });
        /////////////////add four plane trigger and when click a plane, we should call game manager :: choosePlane to choose plane
        GameManager.start();
    }
}
