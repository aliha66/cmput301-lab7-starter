package com.example.androiduitesting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    // Declare the variables.
    TextView cityNameText;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // Initialize the views
        cityNameText = findViewById(R.id.city_name);
        backButton = findViewById(R.id.button_back);

        // Get the city name from the intent
        String cityName = getIntent().getStringExtra("CITY_NAME");

        // Set the city name in the TextView
        if (cityName != null) {
            cityNameText.setText(cityName);
        }

        // Set the click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
