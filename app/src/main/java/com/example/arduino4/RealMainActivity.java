package com.example.arduino4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RealMainActivity extends AppCompatActivity {


    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

         textView = (TextView)findViewById(R.id.textView);

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean a = true;
                textView.setSelected(true);

                return false;
            }
        });

        Button btn = (Button)findViewById(R.id.btn_bluetooth);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RealMainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

         btn = (Button)findViewById(R.id.btn_wifi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RealMainActivity.this , WiFiActivity.class);
                startActivity(intent);
            }
        });

         btn = (Button)findViewById(R.id.btn_map);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RealMainActivity.this , MapsActivity.class);
                startActivity(intent);
            }
        });

         btn = (Button)findViewById(R.id.btn_no_drone);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RealMainActivity.this , MapActivity.class);
                startActivity(intent);
            }
        });

    }
}
