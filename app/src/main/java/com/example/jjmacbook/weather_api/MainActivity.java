package com.example.jjmacbook.weather_api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText city;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (EditText) findViewById(R.id.editTextCity);
        btn = (Button) findViewById(R.id.buttonWeather);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ciudad = city.getText().toString();

                if (ciudad != null && !ciudad.equals("")){

                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    i.putExtra("ciudad", ciudad);
                    startActivity(i);

                }else{

                    Toast.makeText(MainActivity.this, "Por favor, introduzca una ciudad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
