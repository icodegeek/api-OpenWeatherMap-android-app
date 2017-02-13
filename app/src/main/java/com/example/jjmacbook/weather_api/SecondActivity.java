package com.example.jjmacbook.weather_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle b = getIntent().getExtras();

        if (b != null){

            String ciudad = b.getString("ciudad");
            Toast.makeText(SecondActivity.this, ciudad, Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(SecondActivity.this, "Campo ciudad vacío", Toast.LENGTH_SHORT).show();
        }
    }
}
