package com.example.arduino4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WiFiActivity2 extends AppCompatActivity {


    Connection connection;
    String address;
    int port;

    EditText editText1, editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi2);

        Button button = (Button)findViewById(R.id.button);
        Button button1 = (Button)findViewById(R.id.button1);
         editText1 =(EditText)findViewById(R.id.editText1);
         editText2 =(EditText)findViewById(R.id.editText2);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address = editText1.getText().toString();
                String a = editText2.getText().toString();
                port = Integer.parseInt(a);
                connection = new Connection(address, port);
                connection.Connect();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connection.Send("sss");
            }
        });
    }

}
