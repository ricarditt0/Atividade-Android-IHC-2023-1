package com.example.atividade4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private EditText txt_x,txt_y,txt_z,light_value,proximity_value;
    private SensorManager mSensorManager;
    private SensorManager lSensorManeger;
    private SensorManager pSensorManeger;
    private Sensor mAccelerometer;
    private Sensor light;
    private Sensor proximity;
    private Button getGPSBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        lSensorManeger = (SensorManager) getSystemService(SENSOR_SERVICE);
        light = lSensorManeger.getDefaultSensor(Sensor.TYPE_LIGHT);

        pSensorManeger = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximity = pSensorManeger.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        txt_x = findViewById(R.id.txt_X);
        txt_y = findViewById(R.id.txt_Y);
        txt_z = findViewById(R.id.txt_Z);

        light_value = findViewById(R.id.luminosidade_txt);

        proximity_value = findViewById(R.id.proximity_txt);

        if (light != null) {
            lSensorManeger.registerListener(MainActivity.this, light,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            light_value.setText("Light sensor not supported");
        }
        getGPSBtn = (Button) findViewById(R.id.getGPSBtn);
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        getGPSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker g = new GPSTracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double longi = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: " + lat + "LONG: " +
                            longi, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        lSensorManeger.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        pSensorManeger.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
        lSensorManeger.unregisterListener(this);
        pSensorManeger.unregisterListener(this);
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
        }
        if(sensorEvent.sensor.getType()== Sensor.TYPE_LIGHT) {
            String l = "Luminosidade: " + sensorEvent.values[0];
            light_value.setText(l);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){
            String prox = "Proximidade: " + sensorEvent.values[0];
            proximity_value.setText(prox);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}