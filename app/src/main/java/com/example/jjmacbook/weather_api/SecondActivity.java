package com.example.jjmacbook.weather_api;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class SecondActivity extends AppCompatActivity {

    private TextView city;
    private TextView longitud;
    private TextView latitud;
    private TextView temp;
    private TextView descripcion;
    private TextView viento;
    private TextView humedad;
    RequestQueue requestQueue;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pd = new ProgressDialog(SecondActivity.this);
        pd.setMessage("Cargando...");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();

        city = (TextView) findViewById(R.id.textViewCity2);
        longitud = (TextView) findViewById(R.id.textViewLong);
        latitud = (TextView) findViewById(R.id.textViewLat);
        temp = (TextView) findViewById(R.id.textViewTemp);
        descripcion = (TextView) findViewById(R.id.textViewDesc);
        viento = (TextView) findViewById(R.id.textViewVel);
        humedad = (TextView) findViewById(R.id.textViewHum);

        requestQueue = Volley.newRequestQueue(this);

        Bundle b = getIntent().getExtras();

        if (b != null){

            String ciudad = b.getString("ciudad");

            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + ciudad + "&appid=ENTER-YOUR-API-ID";

            JsonObjectRequest myrequest = new JsonObjectRequest(Request.Method.GET, url, null,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                String city_api = response.getString("name");
                                JSONObject country = response.getJSONObject("sys");
                                String country_api = country.getString("country");
                                city.setText(city_api + ", " + country_api);

                                JSONObject coord = response.getJSONObject("coord");
                                String lon = coord.getString("lon");
                                String lat = coord.getString("lat");
                                longitud.setText(lon);
                                latitud.setText(lat);

                                JSONObject main = response.getJSONObject("main");
                                double temperatura = main.getDouble("temp");
                                int humidity = main.getInt("humidity");
                                temp.setText(conv(temperatura) + " ºC");
                                humedad.setText(humidity + "%");

                                JSONArray weatherArray = response.getJSONArray("weather");
                                JSONObject weather = weatherArray.getJSONObject(0);
                                String description_main = weather.getString("main");
                                String description = weather.getString("description");
                                descripcion.setText(description_main + ", " + description);

                                JSONObject wind = response.getJSONObject("wind");
                                double viento_vel = wind.getDouble("speed");
                                viento.setText(viento_vel + " m/sec");
                                pd.dismiss();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Volley", "error");
                        }
                    });
            requestQueue.add(myrequest);
        }else{
            Toast.makeText(SecondActivity.this, "Campo ciudad vacío", Toast.LENGTH_SHORT).show();
        }
    }

    public double conv(double kelvin){

        double centigrado = kelvin - 273.15;
        return Math.rint(centigrado*10)/10;
    }
}
