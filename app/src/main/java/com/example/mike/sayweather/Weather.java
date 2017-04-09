package com.example.mike.sayweather;

import android.util.JsonReader;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.in;

/**
 * Created by Mike on 08-Apr-17.
 */

public class Weather {
    private double temp;

    public Weather() throws IOException {
        URL openWM = new URL("http://api.openweathermap.org/data/2.5/weather?id=6087824&units=metric&APPID=549aa289fff643f349d6b649fadbc145");
        HttpURLConnection urlConnection = (HttpURLConnection) openWM.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        reader.beginArray();
        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            if(name.equals("main")){
                reader.beginObject();
                while(reader.hasNext()){
                    String name2 = reader.nextName();
                    if(name2.equals("temp")){
                        temp = reader.nextDouble();
                    }
                }
                reader.endObject();
            }
        }
        reader.endObject();
        reader.endArray();
    }
    public double getTemp(){
        return temp;
    }

}
