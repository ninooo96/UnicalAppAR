package com.ninooo96.unicalappar;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
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
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ARActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private ViewRenderable cuboRenderable;

    private static final String TAG = ARActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;

    private com.google.ar.core.Camera camera;
    private Session session;
    private CameraManager cameraManager;
    private String cameraId;
    private CameraDevice cameraDevice;
    private int cameraFacing;
    private CameraDevice.StateCallback stateCallback;

    private BackgroundRenderer mBackgroundRenderer = new BackgroundRenderer();
//    private final ObjectRenderer virtualObject = new ObjectRenderer();
//    private final ObjectRenderer virtualObjectShadow = new ObjectRenderer();
    private final PlaneRenderer planeRenderer = new PlaneRenderer();
    private final PointCloudRenderer pointCloudRenderer = new PointCloudRenderer();
    private boolean isTracking, isHitting;
    private boolean planeDetected;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);

//        if (!checkIsSupportedDeviceOrFinish(this)) {
//            return;
//        }
        setContentView(R.layout.ar_activity);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_frag);
        arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
                    arFragment.onUpdate(frameTime);
                    onUpdate();
                });
//
//        addViewRenderable();
//        try {
////            mBackgroundRenderer.createOnGlThread(/*context=*/this);
////            session.setCameraTextureName(mBackgroundRenderer.getTextureId());
//    } catch (IOException e) {
//        e.printStackTrace();
//    }


//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},1);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//        }
//        cameraFacing = CameraCharacteristics.LENS_FACING_BACK;



        //        arFragment.getArSceneView().getArFrame().getCamera().getTrackingState();
//        arFragment.getArSceneView().getScene().addOnUpdateListener({ frameTime ->
//                arFragment.onUpdate(frameTime)
//            .onUpdate();
//        });
//        camera.setLocalRotation(Quaternion.axisAngle(Vector3.right(), -30.0f));
//        ExternalTexture texture = new ExternalTexture();

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            ViewRenderable.builder().setView(this,  R.layout.ar_activity).build()
//                    .thenAccept(renderable ->cuboRenderable = renderable);
//
//        }
//        arFragment.setOnTapArPlaneListener((HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
//            if(cuboRenderable == null)
//                return;
//
//            Anchor anchor = hitResult.createAnchor();
//            AnchorNode anchorNode = new AnchorNode(anchor);
//            anchorNode.setParent(arFragment.getArSceneView().getScene());
//
//            TransformableNode cubo = new TransformableNode(arFragment.getTransformationSystem());
//            cubo.setParent(anchorNode);
//            cubo.setRenderable(cuboRenderable);
//            cubo.select();
//        });

//        arFragment.getArSceneView()
//                .getScene()
//                .setOnTouchListener(
//                        (HitTestResult hitTestResult, MotionEvent event) -> {
//                            // If the solar system hasn't been placed yet, detect a tap and then check to see if
//                            // the tap occurred on an ARCore plane to place the solar system.
//                            if (!hasPlacedSolarSystem) {
//                                return gestureDetector.onTouchEvent(event);
//                            }
//
//                            // Otherwise return false so that the touch event can propagate to the scene.
//                            return false;
//                        });
       /** arFragment.setOnTapArPlaneListener(
                (ArFragment fragment, Anchor anchor, ViewRenderable renderable) -> {
                    addNodeToScene(arFragment, anchor, renderable);
                });*/

    }

//    @Override
//    protected void onResume(){
//        super.onResume();
////        Intent int1 = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);
////        startActivityForResult(int1,1);
//
//
//        try {
//            session = new Session(this);
//        } catch (UnavailableArcoreNotInstalledException e) {
//            e.printStackTrace();
//        } catch (UnavailableApkTooOldException e) {
//            e.printStackTrace();
//        } catch (UnavailableSdkTooOldException e) {
//            e.printStackTrace();
//        }
//        try {
//            session.resume();
//        } catch (CameraNotAvailableException e) {
//            e.printStackTrace();
//        }
//
//
//        arFragment.onResume();
////        frame.getCamera()..getTrackingState() == TrackingState.TRACKING;
//        try {
//            Frame frame = session.update();
//            camera = frame.getCamera();
//            mBackgroundRenderer.draw(frame);
//        } catch (CameraNotAvailableException e) {
//            e.printStackTrace();
//        }
//    }

    private void onUpdate(){
        updateTracking();
        if(isTracking){
            boolean hitTestChanged = updateHitTest();
            if(hitTestChanged)
                planeDetected = hitTestChanged;
        }
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
        View view = findViewById(android.R.id.content);
        return new Point(view.getWidth()/2, view.getHeight()/2);
    }

    private void addViewRenderable(){
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ViewRenderable.builder().setView(this,  R.layout.info_cubo).build()
                    .thenAccept(renderable ->
                            addNodeToScene(fragment, anchor, renderable  ));

        }
    }
//    private void placeViewRenderable(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            ViewRenderable.builder().setView(this,  R.layout.ar_activity).build()
//                    .thenAccept(renderable ->cuboRenderable = renderable);
//
//        }
//    }

    private void addNodeToScene(ArFragment fragment, Anchor anchor, ViewRenderable renderable){
        AnchorNode anchorNode = new AnchorNode(anchor);

        TransformableNode transformableNode = new TransformableNode(fragment.getTransformationSystem());
        transformableNode.setRenderable(renderable);
        transformableNode.setParent(anchorNode);
        fragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }

    public void hitOnScreen(View view) {
        System.out.println("CIAOOOOOOOOO");
        Toast.makeText(this, "hitOnScreen",Toast.LENGTH_LONG).show();
//        if(planeDetected)
            addViewRenderable();
    }
//    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
//            Log.e(TAG, "Sceneform requires Android N or later");
//            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
//            activity.finish();
//            return false;
//        }
//        String openGlVersionString =
//                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
//                        .getDeviceConfigurationInfo()
//                        .getGlEsVersion();
//        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
//            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
//            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
//                    .show();
//            activity.finish();
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
//
//    }
//
//    @Override
//    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
//
//    }
//
//    @Override
//    public void onDrawFrame(GL10 gl10) {
//
//    }
}
