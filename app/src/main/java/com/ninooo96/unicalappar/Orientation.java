package com.ninooo96.unicalappar;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import static android.content.Context.SENSOR_SERVICE;
import static android.hardware.SensorManager.AXIS_X;
import static android.hardware.SensorManager.AXIS_Z;
import static android.hardware.SensorManager.remapCoordinateSystem;

public class Orientation implements SensorEventListener {

    public static SensorManager mSensorManager;
    public static Sensor accelerometer;
    public static Sensor magnetometer;
    private static Sensor rotationVector;

    public static float[] mAccelerometer = null;
    public static float[] mGeomagnetic = null;
    private Context context;

    private boolean oneSensor;
    private char letteraCubo;
    private int valAzimuth;


    public Orientation(Context context) {
        this.context = context;
        mSensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) == null) {
            if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null || mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) == null)
                Toast.makeText(context, "Il tuo dispositivo non è predisposto dei sensori necessari per l'orientamento", Toast.LENGTH_LONG).show();
            else {
                oneSensor = false;
                accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            }
        } else {
            rotationVector = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            oneSensor = true;
        }
    }

    public char getLetteraCubo() {
        return letteraCubo;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float orientation[] = new float[5];
        float R[] = new float[9];
        float I[] = new float[9];
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(R, event.values);
            remapCoordinateSystem(R, AXIS_X, AXIS_Z, I);
            valAzimuth = (int) (Math.toDegrees(SensorManager.getOrientation(I, orientation)[0]) + 360) % 360;
        } else {
            //il dispositivo non è dotato del ROTATION_VECTOR_SENSOR
           if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                mAccelerometer = event.values;
            }

            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                mGeomagnetic = event.values;
            }

            if (mAccelerometer != null && mGeomagnetic != null) {
                SensorManager.getRotationMatrix(R, I, mAccelerometer, mGeomagnetic);
                remapCoordinateSystem(R, AXIS_X, AXIS_Z, I);
                valAzimuth = (int) (Math.toDegrees(SensorManager.getOrientation(I, orientation)[0]) + 360) % 360;
            }
        }



        if (valAzimuth < 300 && valAzimuth > 220)
            letteraCubo = 'c';
        else if (valAzimuth > 20 && valAzimuth < 100) {
            letteraCubo = 'b';
        } else
            letteraCubo = 'x';
    }

    protected void onResume() {
        if (!oneSensor) {
            mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
            mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
        } else
            mSensorManager.registerListener(this, rotationVector, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        if (!oneSensor) {
            mSensorManager.unregisterListener(this, accelerometer);
            mSensorManager.unregisterListener(this, magnetometer);
        } else
            mSensorManager.unregisterListener(this, rotationVector);
    }
}

