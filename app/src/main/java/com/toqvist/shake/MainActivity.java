package com.toqvist.shake;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener sel = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {

                TextView axisX = findViewById(R.id.axisX);
                TextView axisY = findViewById(R.id.axisY);
                TextView axisZ = findViewById(R.id.axisZ);

                axisX.setText("X: " + event.values[0]);
                axisY.setText("Y: " + event.values[1]);
                axisZ.setText("Z: " + event.values[2]);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }


        };

        sensorManager.registerListener(sel, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }
}