package com.ninooo96.unicalappar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Trackable;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ARActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private ViewRenderable cuboRenderable ;
    private CompletableFuture<ViewRenderable> views;

    //Address
    private Position position;

    //Orientation
    private Orientation orientation;

    private boolean isTracking, isHitting;
    private boolean planeDetected;
    private TextView nomeCubo;
    private TextView pianoT, piano1, piano2, piano3, piano4, piano5, piano6, piano7, piano8;
    private TextView auleT, aule1, aule2, aule3, aule4, aule5,aule6, aule7, aule8;
    private ListaAule aule;
    private TextView[] piani;
    private TextView[] listaAule;
    private double bussola;
    private int numCubo;
    private char letteraCubo='x';


    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.ar_activity);

        position = new Position(this,this);
        orientation = new Orientation(this);
        numCubo = position.getNumCubo();
        letteraCubo = orientation.getLetteraCubo();

        views = ViewRenderable.builder().setView(this, R.layout.info_cubo).build();
        CompletableFuture.allOf(views)
                .handle((notUsed, throwable) ->{
                    try{
                        cuboRenderable = views.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                });

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_frag);
        arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
                    arFragment.onUpdate(frameTime);
                    onUpdate();
                });

        aule = new ListaAule();

        arFragment.setOnTapArPlaneListener(
               (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                if(planeDetected==false)
                    return;
                   views = ViewRenderable.builder().setView(this, R.layout.info_cubo).build();
                   CompletableFuture.allOf(views)
                           .handle((notUsed, throwable) ->{
                               try{
                                   cuboRenderable = views.get();
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               } catch (ExecutionException e) {
                                   e.printStackTrace();
                               }
                               return null;
                           });
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

        orientation.onResume();
        position.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        orientation.onPause();
        position.onPause();
    }

    private boolean updateHitTest(){
        Frame frame = arFragment.getArSceneView().getArFrame();
        Point point = getScreenCenter();
        List<HitResult> hits;
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
        Frame frame = arFragment.getArSceneView().getArFrame();
        Point point = getScreenCenter();
        if(frame != null){
            List<HitResult> hits = frame.hitTest((float) point.x, (float) point.y);
            for(HitResult hit : hits){
                Trackable trackable = hit.getTrackable();
                if((trackable instanceof Plane) && ((Plane) trackable).isPoseInPolygon(hit.getHitPose())){
                    addNodeToScene(arFragment, hit.createAnchor());
                    break;
                }
            }
        }
    }

    @SuppressLint("NewApi")
    private void addNodeToScene(ArFragment fragment, Anchor anchor){
        AnchorNode anchorNode = new AnchorNode(anchor);
        Node infoCubi = new Node();
        infoCubi.setParent(arFragment.getArSceneView().getScene());
        infoCubi.setParent(anchorNode);
        infoCubi.setRenderable(cuboRenderable);
        infoCubi.onActivate();
        infoCubi.setLocalPosition(new Vector3(0f, 0.5f, 0f));
        View tmp1 = cuboRenderable.getView();

        nomeCubo = tmp1.findViewById(R.id.nome_cubo);

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

        if(!aule.getAule().containsKey(position.getNumCubo()+""+ orientation.getLetteraCubo())) {
            if(position.getNumCubo()>46 || orientation.getLetteraCubo()=='x') Toast.makeText(this, "Non stai inquadrando un cubo dell'Unical", Toast.LENGTH_LONG).show();
            else Toast.makeText(this,"Non ho informazioni su questo cubo"+ position.getNumCubo()+""+ orientation.getLetteraCubo(),Toast.LENGTH_LONG).show();
            return;
        }
        String titolo = "CUBO "+ position.getNumCubo()+""+ Character.toUpperCase(orientation.getLetteraCubo());

        nomeCubo.setText(titolo);
        for (int j = 0; j < piani.length; j++) {
            listaAule[j].setVisibility(View.INVISIBLE);
            piani[j].setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i < piani.length; i++) {
            StringBuilder sb = new StringBuilder();
            int ind = 0;
            while (ind < aule.getAule().get(position.getNumCubo()+""+ orientation.getLetteraCubo())[i].size()) {
                sb.append(aule.getAule().get(position.getNumCubo()+""+ orientation.getLetteraCubo())[i].get(ind));
                sb.append(", ");
                ind++;
            }
            if (ind != 0) sb.delete(sb.length() - 2, sb.length());
            else
                continue; //se ind==0 significa che in quel piano non ci sono aule, quindi non attivo le textview
            piani[i].setText(sb.toString());
            listaAule[i].setText(" Piano "+ ((i==0)?"T":i+"") + " ");
            piani[i].setVisibility(View.VISIBLE);
            listaAule[i].setVisibility(View.VISIBLE);
        }

        fragment.getArSceneView().getScene().addChild(anchorNode);
    }
}
