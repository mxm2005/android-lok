package com.Snake;

import android.app.Activity;
import android.os.Bundle;

public class Snake extends Activity {
    SnakeLayout mSnake;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mSnake = (SnakeLayout) findViewById(R.id.my_snake);
        mSnake.addRec(R.drawable.png1);
        mSnake.addRec(R.drawable.png2);
        mSnake.addRec(R.drawable.png3);
        mSnake.addRec(R.drawable.png4);
        mSnake.addRec(R.drawable.png5);
        mSnake.addRec(R.drawable.png6);
        mSnake.addRec(R.drawable.png7);
        mSnake.addRec(R.drawable.png8);
        mSnake.addRec(R.drawable.png9);
        mSnake.addRec(R.drawable.png10);
        mSnake.addRec(R.drawable.png11);
        mSnake.addRec(R.drawable.png12);
        mSnake.addRec(R.drawable.png13);
        mSnake.addRec(R.drawable.png14);
        mSnake.addRec(R.drawable.png15);
        mSnake.addRec(R.drawable.png16);
        mSnake.addRec(R.drawable.png17);
        mSnake.addRec(R.drawable.png18);
        mSnake.addRec(R.drawable.png19);
        mSnake.addRec(R.drawable.png20);
        mSnake.addRec(R.drawable.png21);
        mSnake.addRec(R.drawable.png22);
        mSnake.addRec(R.drawable.png23);
        mSnake.addRec(R.drawable.png24);
        mSnake.addRec(R.drawable.png25);
        mSnake.addRec(R.drawable.png26);
        mSnake.addRec(R.drawable.png27);
        mSnake.addRec(R.drawable.png28);
        mSnake.Init();
    }
}