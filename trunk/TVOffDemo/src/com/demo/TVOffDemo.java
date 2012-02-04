package com.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TVOffDemo extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button b = (Button) findViewById(R.id.Button01);
        b.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                View img = findViewById(R.id.ImageView01);
                img.startAnimation(new TVOffAnimation());
            }
        });
    }
}