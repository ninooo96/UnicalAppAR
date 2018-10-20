package com.ninooo96.unicalappar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import eu.bitm.NominatimReverseGeocoding.NominatimReverseGeocodingJAPI;

import static android.hardware.SensorManager.AXIS_X;
import static android.hardware.SensorManager.AXIS_Z;
import static android.hardware.SensorManager.remapCoordinateSystem;

public class AddressActivity extends AppCompatActivity implements LocationListener, SensorEventListener {
    public float distance;
    public TextView addr, bearing, azimuth;
    private String providerId = LocationManager.GPS_PROVIDER;
    private LocationManager locationManager = null;
    private static final int MIN_DIST = 0;
    private static final int MIN_PERIOD = 100;
    private float[] result = new float[1];
    private static float bear;
    private Location inizioPonte = new Location(LocationManager.GPS_PROVIDER);

    //Orientation
    public static SensorManager mSensorManager;
    public static Sensor accelerometer;
    public static Sensor magnetometer;
    public static float[] mAccelerometer = null;
    public static float[] mGeomagnetic = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Orientation
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        setContentView(R.layout.activity_orientation);
//        dirZ = findViewById(R.id.directionZ);

        //Address
        setContentView(R.layout.activity_address);
        addr = findViewById(R.id.address);
        bearing = findViewById(R.id.bearing);
        azimuth = findViewById(R.id.azimuth);
        Location.distanceBetween(39.356235, 16.226965,39.366869, 16.225389, result);
        System.out.println("km "+result[0]);
        inizioPonte.setLatitude(39.356235);
        inizioPonte.setLongitude(16.226965);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Orientation
        mSensorManager.unregisterListener(this, accelerometer);
        mSensorManager.unregisterListener(this, magnetometer);

        //Address
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Orientation
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);

        //Address
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //activity per attivare il gps
        if (!locationManager.isProviderEnabled(providerId)) {
            Intent gpsOptionsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(gpsOptionsIntent);
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Dai il permesso all'applicazione di usare la geolocalizzazione
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                System.out.println("ciaoo");

            }
            locationManager.requestLocationUpdates(providerId, MIN_PERIOD, MIN_DIST, this);

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        updateGUI(location);
        Location test = new Location(providerId);
        test.setLatitude(39.357263);//39.357263, 16.226821
        test.setLongitude(16.226821);
        distance = test.distanceTo(inizioPonte);
        System.out.println(distance+" Distanza dall'inizio del ponte");
        String cubo = getCubo();
        Toast t = Toast.makeText(this, cubo ,Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private void updateGUI(Location location)
    {
        double latitude=location.getLatitude();
        double longitude=location.getLongitude();
        Location loc = new Location(LocationManager.GPS_PROVIDER);
        double loc1 = 82.420000;
        double loc2 = 114.240000; //49.395686, 16.059422
        loc.setLatitude(loc1); //82.420000, 114.240000 polo nord
        loc.setLongitude(loc2);
        String msg="Ci troviamo in coordinate ("+latitude+","+longitude+")";
        String address = (new ReverseGeocoding(this)).getCompleteAddress(latitude, longitude);
//        String address = (new ReverseGeocoding(this)).getAddressOSM(39.364166, 16.225764);
        addr.setText(address);
        /**Guardare appunti sul quadernino. loc sono le coordinate del polo nord magnetico*/
        bear = location.bearingTo(loc);
        bearing.setText("Bearing: "+Float.toString(bear));
        System.out.println(bear+"  "+address);
//        TextView txt= (TextView) findViewById(R.id.locationText);
//        txt.setText(msg);

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
                System.out.println(azimuth+" dirz");
                this.azimuth.setText(azimuth+"");
//                dirZ.setText("Z= "+azimuth);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public String getCubo(){
        float tmp = distance/(float)25.75;
        System.out.println("Cuboooo "+Math.round(tmp));
        System.out.println(distance+" distance");
        return Math.round(tmp)+"";
    }


//    public AddressActivity(){
//        boh();
//    }
//
//    public List<Address> boh(){
//        Geocoder geo = new Geocoder(this, Locale.ITALY);
//        try {
//            addresses = geo.getFromLocation(38.357065, 15.843158,2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return addresses;
//    }
//    public static void main(String[] args){
////        NominatimReverseGeocodingJAPI nominatim1 = new NominatimReverseGeocodingJAPI(18);
//        new AddressActivity();
//        System.out.print.get(0));ln(addresses
//
////        System.out.println(nominatim1.getAdress(38.357065, 15.843158));
//    }
}
