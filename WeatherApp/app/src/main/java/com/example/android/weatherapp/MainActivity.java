package com.example.android.weatherapp;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.weatherapp.data.Channel;
import com.example.android.weatherapp.data.Item;
import com.example.android.weatherapp.service.WeatherServiceCallback;
import com.example.android.weatherapp.service.YahooWeatherService;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private EditText locationNameEditText;
    private Button buttonSearch;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherIconImageView = (ImageView)findViewById(R.id.weatherIconImageViewer);
        temperatureTextView = (TextView)findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.conditionTextView);
        locationTextView= (TextView)findViewById(R.id.locationTextView);
        locationNameEditText = (EditText)findViewById(R.id.locationEditText);
        buttonSearch = (Button)findViewById(R.id.search_button);


        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.show();

        service.refreshWeather("Ankara");

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.refreshWeather(locationNameEditText.getText().toString());
            }
        });

    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/pic_"+channel.getItem().getCondition().getCode(),null,getPackageName());
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId,null);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);
        locationTextView.setText(service.getLocation());
        temperatureTextView.setText(item.getCondition().getTemperature()+"\u00B0"+channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
    }


}
