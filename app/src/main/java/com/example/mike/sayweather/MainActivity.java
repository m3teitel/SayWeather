package com.example.mike.sayweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sayWeather(View view) throws IOException {
        Weather w = new Weather();
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(Double.toString(w.getTemp()));
    }

    class Weather {
        private double temp;

        public Weather() throws IOException {
            URL openWM = new URL("http://api.openweathermap.org/data/2.5/weather?id=6087824&units=metric&APPID=549aa289fff643f349d6b649fadbc145");
            HttpURLConnection urlConnection = (HttpURLConnection) openWM.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            reader.beginArray();
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("main")) {
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String name2 = reader.nextName();
                        if (name2.equals("temp")) {
                            temp = reader.nextDouble();
                        }
                    }
                    reader.endObject();
                }
            }
            reader.endObject();
            reader.endArray();
        }

        public double getTemp() {
            return temp;
        }
    }
}
