package com.example.atividade3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private EditText txt_x,txt_y,txt_z;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        txt_x = (EditText) findViewById(R.id.txt_acc_x);
        txt_y = (EditText) findViewById(R.id.txt_acc_y);
        txt_z = (EditText) findViewById(R.id.txt_acc_z);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()== Sensor.TYPE_ACCELEROMETER) {
            float sensorX = sensorEvent.values[0];
            float sensorY = sensorEvent.values[1];
            float sensorZ = sensorEvent.values[2];

            String stringX = "X: " + sensorX;
            String stringY = "Y: " + sensorY;
            String stringZ = "Z: " + sensorZ;

            txt_x.setText(stringX);
            txt_y.setText(stringY);
            txt_z.setText(stringZ);

            if (Math.abs(sensorX) > 20 || Math.abs(sensorY) > 20 || Math.abs(sensorZ) > 20) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}