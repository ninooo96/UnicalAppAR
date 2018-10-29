package com.ninooo96.unicalappar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.Camera;

import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.core.Trackable;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ExternalTexture;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.ninooo96.unicalappar.common.rendering.BackgroundRenderer;
//import com.ninooo96.unicalappar.common.rendering.ObjectRenderer;
import com.ninooo96.unicalappar.common.rendering.PlaneRenderer;
import com.ninooo96.unicalappar.common.rendering.PointCloudRenderer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.hardware.SensorManager.AXIS_X;
import static android.hardware.SensorManager.AXIS_Z;
import static android.hardware.SensorManager.remapCoordinateSystem;

public class ARActivity extends AppCompatActivity implements LocationListener, SensorEventListener {

    private ArFragment arFragment;
    private ViewRenderable cuboRenderable;

    private static final String TAG = ARActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;

    //Address
    public float distance;
    private String providerId = LocationManager.GPS_PROVIDER;
    private LocationManager locationManager = null;
    private static final int MIN_DIST = 0;
    private static final int MIN_PERIOD = 100;
    private float[] result = new float[1];
    private Location inizioPonte = new Location(LocationManager.GPS_PROVIDER);
    private Cubo tmp;

    //Orientation
    public static SensorManager mSensorManager;
    public static Sensor accelerometer;
    public static Sensor magnetometer;
    public static float[] mAccelerometer = null;
    public static float[] mGeomagnetic = null;

    private boolean isTracking, isHitting;
    private boolean planeDetected;
    private ListaCubi lc;
    private ListIterator li;
    private TextView pianoT, piano1, piano2, piano3, piano4, piano5, piano6, piano7, piano8;
    private TextView auleT, aule1, aule2, aule3, aule4, aule5,aule6, aule7, aule8;
    private Aule aule;
    private TextView[] piani;
    private TextView[] listaAule;
    private double bussola;
    private int numCubo;
    private char letteraCubo='x';
    private boolean firstPosition = false;
    private int notExistCube = -1;
    private boolean cuboCambiato = true;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.ar_activity);

        //Orientation
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //        dirZ = findViewById(R.id.directionZ);


        Location.distanceBetween(39.356235, 16.226965,39.366869, 16.225389, result);
        System.out.println("km "+result[0]);
        inizioPonte.setLatitude(39.356235);
        inizioPonte.setLongitude(16.226965);

        lc = new ListaCubi(this);
        lc.toString();
        li = lc.getCubi().listIterator();

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_frag);
        arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
                    arFragment.onUpdate(frameTime);
                    onUpdate();
                });

//        pianoT = findViewById(R.id.pianoTerra);
//        piano1 = findViewById(R.id.piano1);
//        piano2 = findViewById(R.id.piano2);
//        piano3 = findViewById(R.id.piano3);
//        piano4 = findViewById(R.id.piano4);
//        piano5 = findViewById(R.id.piano5);
//        piano6 = findViewById(R.id.piano6);
//        piano7 = findViewById(R.id.piano7);
//        piano8 = findViewById(R.id.piano8);
//
//        piani = new TextView[9];
//        piani[0] = pianoT; piani[1] = piano1; piani[2] = piano2;
//        piani[3] = piano3; piani[4] = piano4; piani[5] = piano5;
//        piani[6] = piano5; piani[7] = piano7; piani[8] = piano8;
//
//        auleT = findViewById(R.id.auleT);
//        aule1 = findViewById(R.id.aule1);
//        aule2 = findViewById(R.id.aule2);
//        aule3 = findViewById(R.id.aule3);
//        aule4 = findViewById(R.id.aule4);
//        aule5 = findViewById(R.id.aule5);
//        aule6 = findViewById(R.id.aule6);
//        aule7 = findViewById(R.id.aule7);
//        aule8 = findViewById(R.id.aule8);
//
//        listaAule = new TextView[9];
//        listaAule[0]=auleT;listaAule[1]=aule1;listaAule[2]=aule2;
//        listaAule[3]=aule3;listaAule[4]=aule4;listaAule[5]=aule5;
//        listaAule[6]=aule6;listaAule[7]=aule7;listaAule[8]=aule8;
//
//
//
//
//        /** */
//        for(int j = 0; j< piani.length;j++) {
//            System.out.println("fsdsgsd");
//            listaAule[j].setVisibility(View.GONE);
//            piani[j].setVisibility(View.GONE);
//        }
        aule = new Aule();
//
//        addViewRenderable();

       arFragment.setOnTapArPlaneListener(
               (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                if(planeDetected==false)
                    return;
                addViewRenderable();
               });


    }

    private void onUpdate(){
        updateTracking();
        if(isTracking){
            boolean hitTestChanged = updateHitTest();
            if(hitTestChanged)
                planeDetected = hitTestChanged;
        }
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
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);

//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

                System.out.println("ciaoo");

            }
            else {
                locationManager.requestLocationUpdates(providerId, MIN_PERIOD, MIN_DIST, this);
            }

        }
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

    private void metodo1() {
        System.out.println("Plane detected");
    }

    private boolean updateHitTest(){
        Frame frame = arFragment.getArSceneView().getArFrame();
        Point point = getScreenCenter();
        List<HitResult> hits = new ArrayList<>();
        boolean wasHitting = isHitting;
        isHitting = false;
        if(frame!=null){
            hits = frame.hitTest((float) point.x, (float) point.y);
            for(HitResult hit : hits){
                Trackable trackable = hit.getTrackable();
                if((trackable instanceof Plane) && ((Plane) trackable).isPoseInPolygon(hit.getHitPose())){
                    isHitting = true;
                    break;
                }
            }
        }
        return wasHitting != isHitting;
    }

    private boolean updateTracking(){
        Frame frame = arFragment.getArSceneView().getArFrame();
        boolean wasTracking = isTracking;
        isTracking = frame.getCamera().getTrackingState() == TrackingState.TRACKING;
        return isTracking != wasTracking;
    }

    private Point getScreenCenter(){
        View view2 = findViewById(android.R.id.content);
        return new Point(view2.getWidth()/2, view2.getHeight()/2);
    }

    private void addViewRenderable(){
//        if(!aule.getAule().containsKey(numCubo+letteraCubo)) {
//            Toast.makeText(this,"Non esiste questi cubo",Toast.LENGTH_LONG).show();
//            return;
//        }
//        for(int i = 0; i < piani.length; i++){
//            StringBuilder sb = new StringBuilder();
//            int ind = 0;
//            while(ind < aule.getAule().get(numCubo+letteraCubo)[i].size()){
//                sb.append(aule.getAule().get(numCubo+letteraCubo)[i].get(ind));
//                sb.append(", ");
//                ind++;
//            }
//            if(ind!=0) sb.delete(sb.length()-2, sb.length());
//            else continue; //se ind==0 significa che in quel piano non ci sono aule, quindi non attivo le textview
//            piani[i].setText(sb.toString());
//            listaAule[i].setText(i+" ");
//            piani[i].setVisibility(View.VISIBLE);
//            listaAule[i].setVisibility(View.VISIBLE);
//        }


//        pianoT.setText(aule.getAule().get("numCubo + orientamento")[0].);
        Frame frame = arFragment.getArSceneView().getArFrame();
        Point point = getScreenCenter();
        if(frame != null){
            List<HitResult> hits = frame.hitTest((float) point.x, (float) point.y);
            for(HitResult hit : hits){
                Trackable trackable = hit.getTrackable();
                if((trackable instanceof Plane) && ((Plane) trackable).isPoseInPolygon(hit.getHitPose())){
                    placeViewRenderable(arFragment, hit.createAnchor());
                    break;
                }
            }
        }
    }

    private void placeViewRenderable(ArFragment fragment, Anchor anchor){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            CompletableFuture<ViewRenderable> view = ViewRenderable.builder().setView(this,  R.layout.info_cubo).build();
//                    view.thenAccept(renderable ->
//                            addNodeToScene(fragment, anchor, renderable  ));
//            try {
//                view.thenAccept(
//                        (Consumer<? super ViewRenderable>) addNodeToScene(fragment, anchor, view.get()));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }

//            try {
//                View tmp1 = view.get().getView();
//
//                pianoT = tmp1.findViewById(R.id.pianoTerra);
//                piano1 = tmp1.findViewById(R.id.piano1);
//                piano2 = tmp1.findViewById(R.id.piano2);
//                piano3 = tmp1.findViewById(R.id.piano3);
//                piano4 = tmp1.findViewById(R.id.piano4);
//                piano5 = tmp1.findViewById(R.id.piano5);
//                piano6 = tmp1.findViewById(R.id.piano6);
//                piano7 = tmp1.findViewById(R.id.piano7);
//                piano8 = tmp1.findViewById(R.id.piano8);
//
//                piani = new TextView[9];
//                piani[0] = pianoT; piani[1] = piano1; piani[2] = piano2;
//                piani[3] = piano3; piani[4] = piano4; piani[5] = piano5;
//                piani[6] = piano5; piani[7] = piano7; piani[8] = piano8;
//
//                auleT = tmp1.findViewById(R.id.auleT);
//                aule1 = tmp1.findViewById(R.id.aule1);
//                aule2 = tmp1.findViewById(R.id.aule2);
//                aule3 = tmp1.findViewById(R.id.aule3);
//                aule4 = tmp1.findViewById(R.id.aule4);
//                aule5 = tmp1.findViewById(R.id.aule5);
//                aule6 = tmp1.findViewById(R.id.aule6);
//                aule7 = tmp1.findViewById(R.id.aule7);
//                aule8 = tmp1.findViewById(R.id.aule8);
//
//                listaAule = new TextView[9];
//                listaAule[0]=auleT;listaAule[1]=aule1;listaAule[2]=aule2;
//                listaAule[3]=aule3;listaAule[4]=aule4;listaAule[5]=aule5;
//                listaAule[6]=aule6;listaAule[7]=aule7;listaAule[8]=aule8;
//
//
//
//
//                /** */
//                for(int j = 0; j< piani.length;j++) {
//                    System.out.println("fsdsgsd");
//                    listaAule[j].setVisibility(View.GONE);
//                    piani[j].setVisibility(View.GONE);
//                }
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//
//            if(!aule.getAule().containsKey(numCubo+""+letteraCubo)) {
//                Toast.makeText(this,"Non esiste questo cubo",Toast.LENGTH_LONG).show();
//                return;
//            }
//            for(int i = 0; i < piani.length; i++){
//                StringBuilder sb = new StringBuilder();
//                int ind = 0;
//                while(ind < aule.getAule().get(numCubo+""+letteraCubo)[i].size()){
//                    sb.append(aule.getAule().get(numCubo+""+letteraCubo)[i].get(ind));
//                    sb.append(", ");
//                    ind++;
//                }
//                if(ind!=0) sb.delete(sb.length()-2, sb.length());
//                else continue; //se ind==0 significa che in quel piano non ci sono aule, quindi non attivo le textview
//                piani[i].setText(sb.toString());
//                listaAule[i].setText(i+" ");
//                piani[i].setVisibility(View.VISIBLE);
//                listaAule[i].setVisibility(View.VISIBLE);
//                Toast.makeText(this,"CIAOOOO", Toast.LENGTH_LONG);
//            }

//            view.thenAccept (
//                renderable ->{
//                    try {
//                        renderable = view.get();
//                        addNodeToScene(fragment, anchor,  renderable);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                });

//            try {
//                view.thenAccept(
//                        (Consumer<? super ViewRenderable>) addNodeToScene(fragment, anchor, view.get()));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }


//        }
        addNodeToScene(fragment, anchor  );
    }

    @SuppressLint("NewApi")
    private void addNodeToScene(ArFragment fragment, Anchor anchor){
        AnchorNode anchorNode = new AnchorNode(anchor);

//        TransformableNode transformableNode = new TransformableNode(fragment.getTransformationSystem());
//        transformableNode.setRenderable(renderable);
//        transformableNode.setParent(anchorNode);
        System.out.println("UFAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        ViewRenderable.builder().setView(this, R.layout.info_cubo).build()
                .thenAccept(renderable -> cuboRenderable = renderable);
//        CompletableFuture.allOf(view)
//                .handle((notUsed, throwable) ->{
//                    System.out.println(view.getNumberOfDependents());
//                            try{
//                                cuboRenderable = view.get();
//                                System.out.println("MANNAIAAAAAA");
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            } catch (ExecutionException e) {
//                                e.printStackTrace();
//                            }
//                            return null;
//                });
//        view.thenAccept(viewRenderable -> {
            Node infoCubi = new Node();
            infoCubi.setParent(arFragment.getArSceneView().getScene());
            infoCubi.setParent(anchorNode);
            infoCubi.setRenderable(cuboRenderable);

            infoCubi.setLocalPosition(new Vector3(0.0f, 0.0f, 0.5f));
//            infoCubi.select();
//            infoCubi.
            View tmp1 = cuboRenderable.getView();

            pianoT = tmp1.findViewById(R.id.pianoTerra);
            piano1 = tmp1.findViewById(R.id.piano1);
            piano2 = tmp1.findViewById(R.id.piano2);
            piano3 = tmp1.findViewById(R.id.piano3);
            piano4 = tmp1.findViewById(R.id.piano4);
            piano5 = tmp1.findViewById(R.id.piano5);
            piano6 = tmp1.findViewById(R.id.piano6);
            piano7 = tmp1.findViewById(R.id.piano7);
            piano8 = tmp1.findViewById(R.id.piano8);

            piani = new TextView[9];
            piani[0] = pianoT; piani[1] = piano1; piani[2] = piano2;
            piani[3] = piano3; piani[4] = piano4; piani[5] = piano5;
            piani[6] = piano5; piani[7] = piano7; piani[8] = piano8;

            auleT = tmp1.findViewById(R.id.auleT);
            aule1 = tmp1.findViewById(R.id.aule1);
            aule2 = tmp1.findViewById(R.id.aule2);
            aule3 = tmp1.findViewById(R.id.aule3);
            aule4 = tmp1.findViewById(R.id.aule4);
            aule5 = tmp1.findViewById(R.id.aule5);
            aule6 = tmp1.findViewById(R.id.aule6);
            aule7 = tmp1.findViewById(R.id.aule7);
            aule8 = tmp1.findViewById(R.id.aule8);

            listaAule = new TextView[9];
            listaAule[0]=auleT;listaAule[1]=aule1;listaAule[2]=aule2;
            listaAule[3]=aule3;listaAule[4]=aule4;listaAule[5]=aule5;
            listaAule[6]=aule6;listaAule[7]=aule7;listaAule[8]=aule8;




            /** */


        if(!aule.getAule().containsKey("32b")) {
            Toast.makeText(this,"Non esiste questo cubo",Toast.LENGTH_LONG).show();
            return;
        }
        while(cuboCambiato) {
            cuboCambiato = false;//Cancellare
            for (int j = 0; j < piani.length; j++) {
                System.out.println("fsdsgsd");
                listaAule[j].setVisibility(View.GONE);
                piani[j].setVisibility(View.GONE);
            }

            for (int i = 0; i < piani.length; i++) {
                StringBuilder sb = new StringBuilder();
                int ind = 0;
                while (ind < aule.getAule().get("32b")[i].size()) {
                    sb.append(aule.getAule().get("32b")[i].get(ind));
                    sb.append(", ");
                    ind++;
                }
                if (ind != 0) sb.delete(sb.length() - 2, sb.length());
                else
                    continue; //se ind==0 significa che in quel piano non ci sono aule, quindi non attivo le textview
                piani[i].setText(sb.toString());
                listaAule[i].setText(i + " ");
                piani[i].setVisibility(View.VISIBLE);
                listaAule[i].setVisibility(View.VISIBLE);
                Toast.makeText(this, "CIAOOOO", Toast.LENGTH_LONG);
//                setContentView(R.layout.info_cubo);
            }
        }


        fragment.getArSceneView().getScene().addChild(anchorNode);

//        transformableNode.select();
    }

    public void hitOnScreen(View view) {
        System.out.println("CIAOOOOOOOOO");
        Toast.makeText(this, "hitOnScreen",Toast.LENGTH_LONG).show();
//        if(planeDetected)
            addViewRenderable();
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
                bussola = 180 * orientation[0] / Math.PI;
                System.out.println(bussola+" dirz");

                if(bussola<-1 && bussola>-150)
                    letteraCubo='c';
                else if(bussola>50 && bussola>150){
                    letteraCubo='b';
                }

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        updateGUI(location);
        Location test = new Location(providerId);
        test.setLatitude(39.357263);//39.357263, 16.226821
        test.setLongitude(16.226821);
        distance = location.distanceTo(inizioPonte);


        if (distance != 0.0f && firstPosition == false) {
            li = lc.getCubi().listIterator();
            while (li.hasNext()) {
                tmp = ((Cubo) li.next());
                if (distance <= tmp.inizioCubo) {
                    li.previous();
                    tmp = (Cubo) li.previous();
                    numCubo = tmp.id;
                    System.out.println(numCubo + " NUMCUBO");
                    firstPosition = true;
                    break;
                }
            }
        }

        if(distance >= tmp.inizioCubo && distance <= tmp.fineCubo && cuboCambiato){
            numCubo = tmp.id;
            System.out.println(numCubo+" NUMC 2");
            cuboCambiato = true;//False
        }

        if(distance < tmp.inizioCubo){
            numCubo = notExistCube;
            if(li.hasPrevious()){
                li.previous();
                tmp = (Cubo) li.previous();
                cuboCambiato = true;
            }
        }

        if(distance > tmp.fineCubo) {
            numCubo = notExistCube;
            if (li.hasNext()) {
                tmp = (Cubo) li.next();
                cuboCambiato = true;
            }

        }
//            try {
        Toast.makeText(this, distance+" distance " + (numCubo+""+letteraCubo) +" cubo", Toast.LENGTH_LONG).show();
//            }catch(Exception e){
//                System.out.println();
//                e.printStackTrace();
//            }





//        bearing.setText(distance+"");
        System.out.println(distance+" Distanza dall'inizio del ponte");
//        String cubo = getCubo();
//        Toast t = Toast.makeText(this, cubo ,Toast.LENGTH_LONG);
//
    }

    private void updateGUI(Location location) {
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
//            addr.setText(address);
//            /**Guardare appunti sul quadernino. loc sono le coordinate del polo nord magnetico*/
//            bear = location.bearingTo(loc);
////        bearing.setText("Bearing: "+Float.toString(bear));
//            System.out.println(bear+"  "+address);
//        TextView txt= (TextView) findViewById(R.id.locationText);
//        txt.setText(msg);

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
}
