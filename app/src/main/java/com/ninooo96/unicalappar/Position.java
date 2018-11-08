package com.ninooo96.unicalappar;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import java.util.ListIterator;

import static android.content.Context.LOCATION_SERVICE;

public class Position implements LocationListener{
    private Aule aule;
    private float distance;
    private String providerId = LocationManager.GPS_PROVIDER;
    private LocationManager locationManager = null;
    private static final int MIN_DIST = 0;
    private static final int MIN_PERIOD = 100;
    private float[] result = new float[1];
    private Location inizioPonte = new Location(LocationManager.GPS_PROVIDER);
    private Cubo cubo;

    private ListaCubi lc;
    private boolean firstPosition ;
    private ListIterator li;
    private int notExistCube = -1;
    private int numCubo;
    private Context context;
    private Activity activity;

    public Position(Context context, Activity activity){
        this.context = context;
        this.activity = activity;
//#
        lc = new ListaCubi(context);
        lc.toString();
        li = lc.getCubi().listIterator();
        aule = new Aule();

        Location.distanceBetween(39.356235, 16.226965,39.366869, 16.225389, result);
        System.out.println("km "+result[0]);
        inizioPonte.setLatitude(39.356235);
        inizioPonte.setLongitude(16.226965);
    }

    protected void onPause() {
        locationManager.removeUpdates(this);
    }

    protected void onResume() {
        firstPosition = true;
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        //activity per attivare il gps
        if (!locationManager.isProviderEnabled(providerId)) {
            Intent gpsOptionsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(gpsOptionsIntent);
        } else {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Dai il permesso all'applicazione di usare la geolocalizzazione
                ActivityCompat.requestPermissions( activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            }
            else {
                locationManager.requestLocationUpdates(providerId, MIN_PERIOD, MIN_DIST, this);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        distance = location.distanceTo(inizioPonte);

        if (distance != 0.0f && firstPosition && onPath((float) location.getLatitude(), (float) location.getLongitude())) {
            li = lc.getCubi().listIterator();
            while (li.hasNext()) {
                cubo = ((Cubo) li.next());
                if (distance <= cubo.inizioCubo) {
                    li.previous();
                    cubo = (Cubo) li.previous();
                    numCubo = cubo.getId();
                    firstPosition = false;
                    break;
                }
            }
        }

        if(distance >= cubo.inizioCubo && distance <= cubo.fineCubo && onPath((float) location.getLatitude(), (float) location.getLongitude())){
            numCubo = cubo.getId();
        }

        if(distance < cubo.inizioCubo && onPath((float) location.getLatitude(), (float) location.getLongitude())){
            numCubo = notExistCube;
            if(li.hasPrevious()){
                li.previous();
                cubo = (Cubo) li.previous();
            }
        }

        if(distance > cubo.fineCubo && onPath((float) location.getLatitude(), (float) location.getLongitude())) {
            numCubo = notExistCube;
            if (li.hasNext()) {
                cubo = (Cubo) li.next();
            }
        }
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

    public boolean onPath(float lat, float lon){
        if(lat > 39.356235 && (lon >= 16.2252f && lon <= 16.2271f)) //, 16.226965
            return true;
        return false;
    }

    public int getNumCubo() {
        return numCubo;
    }

}
