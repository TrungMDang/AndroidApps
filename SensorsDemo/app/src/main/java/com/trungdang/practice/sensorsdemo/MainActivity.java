package com.trungdang.practice.sensorsdemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager mSensorManager;

    private Sensor mProximity;
    private Sensor mTemperature;
    private Sensor mPressure;
    private Sensor mAccelerometer;
    private Sensor mGravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv);

        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);


        StringBuilder message = new StringBuilder(2048);
        message.append("The sensors on this device are: " + sensorList.size()+ "\n");
        for(Sensor sensor : sensorList) {
            message.append("------------------\n");
            message.append(sensor.getName() + "\n");
            message.append(" Type: " +
                    sensorTypes.get(sensor.getType()) + "\n");
//            message.append(" Vendor: " +
//                    sensor.getVendor() + "\n");
//            message.append(" Version: " +
//                    sensor.getVersion() + "\n");
//            try {
//                message.append(" Min Delay: " +
//                        sensor.getMinDelay() + "\n");
//            } catch(NoSuchMethodError e) {} // ignore if not found
//            try {
//                message.append(" FIFO Max Event Count: " +
//                        sensor.getFifoMaxEventCount() + "\n");
//            } catch(NoSuchMethodError e) {} // ignore if not found
//            message.append(" Resolution: " +
//                    sensor.getResolution() + "\n");
//            message.append(" Max Range: " +
//                    sensor.getMaximumRange() + "\n");
//            message.append(" Power: " +
//                    sensor.getPower() + " mA\n");
        }
        tv.setText(message);

        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGravity= mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

    }

    private HashMap<Integer, String> sensorTypes =
            new HashMap<Integer, String>();
    {
        sensorTypes.put(Sensor.TYPE_ACCELEROMETER, "TYPE_ACCELEROMETER");
        sensorTypes.put(Sensor.TYPE_AMBIENT_TEMPERATURE, "TYPE_AMBIENT_TEMPERATURE");
        sensorTypes.put(Sensor.TYPE_GRAVITY, "TYPE_GRAVITY");
        sensorTypes.put(Sensor.TYPE_GYROSCOPE, "TYPE_GYROSCOPE");
        sensorTypes.put(Sensor.TYPE_LIGHT, "TYPE_LIGHT");
        sensorTypes.put(Sensor.TYPE_LINEAR_ACCELERATION, "TYPE LINEAR ACCELERATION");
        sensorTypes.put(Sensor.TYPE_MAGNETIC_FIELD, "TYPE_MAGNETIC_FIELD");
        sensorTypes.put(Sensor.TYPE_PRESSURE, "TYPE_PRESSURE");
        sensorTypes.put(Sensor.TYPE_PROXIMITY, "TYPE PROXIMITY");
        sensorTypes.put(Sensor.TYPE_RELATIVE_HUMIDITY, "TYPE RELATIVE HUMIDITY");
        sensorTypes.put(Sensor.TYPE_ROTATION_VECTOR, "TYPE ROTATION VECTOR");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);
        TextView tv5 = findViewById(R.id.tv5);

        StringBuilder sb = new StringBuilder();
        switch (event.sensor.getType()) {
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                sb.append("Temperature: " + Arrays.toString(event.values));
                break;
            case Sensor.TYPE_PROXIMITY:
                sb.append("Proximity: " + Arrays.toString(event.values));
                tv2.setText(sb.toString());
                break;
            case Sensor.TYPE_PRESSURE:
                sb.append("Pressure: " + Arrays.toString(event.values));
                tv3.setText(sb.toString());
                break;
            case Sensor.TYPE_ACCELEROMETER:
                sb.append("Accelerometer: " + Arrays.toString(event.values));
                tv4.setText(sb.toString());
                break;
            case Sensor.TYPE_GRAVITY:
                sb.append("Gravity: " + Arrays.toString(event.values));
                tv5.setText(sb.toString());
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {



    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
