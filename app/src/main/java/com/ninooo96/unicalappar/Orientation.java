//package com.ninooo96.unicalappar;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import static android.content.Context.SENSOR_SERVICE;
//import static android.hardware.SensorManager.AXIS_X;
//import static android.hardware.SensorManager.AXIS_Z;
//import static android.hardware.SensorManager.remapCoordinateSystem;
//
//public class Orientation implements SensorEventListener {
//        public static SensorManager mSensorManager;
//        public static Sensor accelerometer;
//        public static Sensor magnetometer;
//        public static float[] mAccelerometer = null;
//        public static float[] mGeomagnetic = null;
//
//        private TextView dirZ;
//
//        public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        }
//
//        @Override
//        public void onSensorChanged(SensorEvent event) {
//            // onSensorChanged gets called for each sensor so we have to remember the values
//            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//                mAccelerometer = event.values;
//            }
//
//            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
//                mGeomagnetic = event.values;
//            }
//
//            if (mAccelerometer != null && mGeomagnetic != null) {
//                float R[] = new float[9];
//                float I[] = new float[9];
//                boolean success = SensorManager.getRotationMatrix(R, I, mAccelerometer, mGeomagnetic);
//
//                if (success) {
//                    float orientation[] = new float[3];
//                    remapCoordinateSystem(R, AXIS_X, AXIS_Z, I);
//                    SensorManager.getOrientation(R, orientation);
//                    // at this point, orientation contains the azimuth(direction), pitch and roll values.
//                    double azimuth = 180 * orientation[0] / Math.PI;
//                    double pitch = 180 * orientation[1] / Math.PI;
//                    double roll = 180 * orientation[2] / Math.PI;
//
//                    double mag = Math.sqrt((azimuth*azimuth)+(pitch*pitch)+(roll*roll));
//                    System.out.println(azimuth+" dirz");
//                    dirZ.setText("Z= "+azimuth);
//                }
//            }
//        }
//
//        public Orientation() {
//            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//            accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//            magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//            setContentView(R.layout.activity_orientation);
//            dirZ = findViewById(R.id.directionZ);
//            dirY = findViewById(R.id.directionY);
//            dirX = findViewById(R.id.directionX);
//            tesla = findViewById(R.id.tesla);
//
//
//        }
//
//        @Override
//        protected void onResume() {
//            super.onResume();
//
//            mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
//            mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
//        }
//
//        @Override
//        protected void onPause() {
//            super.onPause();
//            mSensorManager.unregisterListener(this, accelerometer);
//            mSensorManager.unregisterListener(this, magnetometer);
//        }
//
//
//        public void addressActivity(View view) {
//            Intent intentAddress = new Intent(com.ninooo96.unicalappar.OrientationActivity.this, AddressActivity.class);
//            startActivity(intentAddress);
//        }
//    }
//
//
//}
