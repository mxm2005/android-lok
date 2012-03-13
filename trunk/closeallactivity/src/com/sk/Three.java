package com.sk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Three extends Comston {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button = (Button) findViewById(R.id.button1);
        button.setText("第三个activity");
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                getApplicationContext().sendBroadcast(new Intent("finish"));
            }
        });
    }

}
