package com.ninooo96.unicalappar;//package com.ninooo96.unicalappar;//package com.ninooo96.unicalappar;
//
//import android.app.Activity;
//import android.content.Context;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class MainActivity extends Activity implements SensorEventListener {
//    private SensorManager mSensorManager;
//    float[] aValues = new float[3];
//    float[] mValues = new float[3];
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//
//    }
//    @Override
//    protected void onResume(){
//        super.onResume();
//        Sensor magneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//        if (magneticField != null) {
//            mSensorManager.registerListener((SensorEventListener) this, magneticField,
//                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
//        }
//        Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
//            // get values for each axes X,Y,Z
//            float magX = (event.values[0]);
//            float magY = (event.values[1]);
//            float magZ = (event.values[2]);
////            float magX = calculateOrientation()[0];
////            float magY = calculateOrientation()[1];
////            float magZ = calculateOrientation()[2];
//
//            double magnitude = Math.sqrt((magX * magX) + (magY * magY) + (magZ * magZ));
////            // set value on the screen
////            value.setText(DECIMAL_FORMATTER.format(magnitude) + " \u00B5Tesla");
//            System.out.println("x= "+magX+"y= "+magY+"z= "+magZ+"  "+magnitude);
//        }
//    }
//
//    private float[] calculateOrientation() {
//        float[] values = new float[3];
//        float[] R = new float[9];
//        float[] outR = new float[9];
//
//        SensorManager.getRotationMatrix(R, null, aValues, mValues);
//        SensorManager.remapCoordinateSystem(R,
//                SensorManager.AXIS_X,
//                SensorManager.AXIS_Z,
//                outR);
//
//        SensorManager.getOrientation(outR, values);
//
//        // Convert from Radians to Degrees.
//        values[0] = (float) Math.toDegrees(values[0]);
//        values[1] = (float) Math.toDegrees(values[1]);
//        values[2] = (float) Math.toDegrees(values[2]);
//
//        return values;
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int i) {
//
//    }
//}

//import android.app.Activity;
//import android.content.Context;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
//import android.os.Bundle;
//import android.widget.TextView;
//
//import com.ninooo96.unicalappar.R;
//
//public class MainActivity extends Activity implements SensorEventListener {
//
//    private SensorManager mSensorManager;
//    private final float[] mAccelerometerReading = new float[3];
//    private final float[] mMagnetometerReading = new float[3];
//
//    private final float[] mRotationMatrix = new float[9];
//    private final float[] mOrientationAngles = new float[3];
//    private TextView dirX, dirY, dirZ, tesla;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        dirZ = findViewById(R.id.directionZ);
//        dirY = findViewById(R.id.directionY);
//        dirX = findViewById(R.id.directionX);
//        tesla = findViewById(R.id.tesla);
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        // Do something here if sensor accuracy changes.
//        // You must implement this callback in your code.
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // Get updates from the accelerometer and magnetometer at a constant rate.
//        // To make batch operations more efficient and reduce power consumption,
//        // provide support for delaying updates to the application.
//        //
//        // In this example, the sensor reporting delay is small enough such that
//        // the application receives an update before the system checks the sensor
//        // readings again.
//        Sensor accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        if (accelerometer != null) {
//            mSensorManager.registerListener(this, accelerometer,
//                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
//        }
//        Sensor magneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//        if (magneticField != null) {
//            mSensorManager.registerListener(this, magneticField,
//                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        // Don't receive any more updates from either sensor.
//        mSensorManager.unregisterListener(this);
//    }
//
//    // Get readings from accelerometer and magnetometer. To simplify calculations,
//    // consider storing these readings as unit vectors.
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            System.arraycopy(event.values, 0, mAccelerometerReading,
//                    0, mAccelerometerReading.length);
//
//            System.out.println(event.values[2]);
//
//        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
//            System.arraycopy(event.values, 0, mMagnetometerReading,
//                    0, mMagnetometerReading.length);
//            float magX = event.values[0];
//            float magY = event.values[1];
//            float magZ = event.values[2];
//            dirZ.setText("  Z= "+Float.toString(event.values[2]));
//            dirY.setText("  Y= "+Float.toString(event.values[1]));
//            dirX.setText("  X= "+Float.toString(event.values[0]));
//
//            double magnitude = Math.sqrt((magX * magX) + (magY * magY) + (magZ * magZ));
//            tesla.setText("    tesla= "+magnitude);
//        }
//
//
//
//    }

//    // Compute the three orientation angles based on the most recent readings from
//    // the device's accelerometer and magnetometer.
//    public void updateOrientationAngles() {
//        // Update rotation matrix, which is needed to update orientation angles.
//        SensorManager.getRotationMatrix(mRotationMatrix, null,
//                mAccelerometerReading, mMagnetometerReading);
//
//        // "mRotationMatrix" now has up-to-date information.
//
//        SensorManager.getOrientation(mRotationMatrix, mOrientationAngles);
//
//        // "mOrientationAngles" now has up-to-date information.
//    }
//}

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import static android.hardware.SensorManager.AXIS_X;
import static android.hardware.SensorManager.AXIS_Z;
import static android.hardware.SensorManager.remapCoordinateSystem;

public class OrientationActivity extends Activity implements SensorEventListener {

    public static float swRoll;
    public static float swPitch;
    public static float swAzimuth;


    public static SensorManager mSensorManager;
    public static Sensor accelerometer;
    public static Sensor magnetometer;

    public static float[] mAccelerometer = null;
    public static float[] mGeomagnetic = null;

    private TextView dirX, dirY, dirZ, tesla;

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // onSensorChanged gets called for each sensor so we have to remember the values
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mAccelerometer = event.values;
        }

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mGeomagnetic = event.values;
        }

        if (mAccelerometer != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mAccelerometer, mGeomagnetic);

            if (success) {
                float orientation[] = new float[3];
                remapCoordinateSystem(R, AXIS_X, AXIS_Z, I);
                SensorManager.getOrientation(R, orientation);
                // at this point, orientation contains the azimuth(direction), pitch and roll values.
                double azimuth = 180 * orientation[0] / Math.PI;
                double pitch = 180 * orientation[1] / Math.PI;
                double roll = 180 * orientation[2] / Math.PI;

                double mag = Math.sqrt((azimuth*azimuth)+(pitch*pitch)+(roll*roll));

                dirZ.setText("Z= "+azimuth);
                dirY.setText("  "+mag);
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        setContentView(R.layout.activity_orientation);
        dirZ = findViewById(R.id.directionZ);
        dirY = findViewById(R.id.directionY);
        dirX = findViewById(R.id.directionX);
        tesla = findViewById(R.id.tesla);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, accelerometer);
        mSensorManager.unregisterListener(this, magnetometer);
    }
}

