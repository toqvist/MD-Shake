package com.toqvist.shake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Button spinButton = findViewById(R.id.buttonSpin);
        ImageView starImage = findViewById(R.id.imageStar);

        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                TextView axisX = findViewById(R.id.axisX);
                TextView axisY = findViewById(R.id.axisY);
                TextView axisZ = findViewById(R.id.axisZ);

                axisX.setText("X: " + event.values[0]);
                axisY.setText("Y: " + event.values[1]);
                axisZ.setText("Z: " + event.values[2]);

                starImage.setRotation(event.values[0]);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(sel, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        spinButton.setOnClickListener(new View.OnClickListener() {
            int starY = 0;
            @Override
            public void onClick(View v) {

                int newY = starY + 30;
                if(newY >= 360) {
                    newY = 0;
                }
                starY = newY;
                starImage.setRotationX(starY);
            }
        });

        Button resetButton = findViewById(R.id.buttonReset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sensorManager.unregisterListener(sel);
                sensorManager.registerListener(sel, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);


            }
        });

    }
}