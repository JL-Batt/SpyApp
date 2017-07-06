package com.example.jessebatt.spyapp;

/**
 * Created by Jesse on 10/03/2017.
 */

        import android.content.Context;
        import android.content.res.Configuration;
        import android.hardware.Camera;
        import android.view.SurfaceHolder;
        import android.view.SurfaceView;
        import android.widget.FrameLayout;

        import java.io.IOException;


public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder holdMe;
    private Camera theCamera;

    public ShowCamera(Context context, Camera camera) {
        super(context);
        theCamera = camera;
        holdMe = getHolder();
        holdMe.addCallback(this);


    }
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {


            theCamera.setPreviewDisplay(holder);
            theCamera.startPreview();

        } catch (IOException ignored) {
        }
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        if (theCamera != null) {
            theCamera.stopPreview();
            theCamera.release();
            theCamera = null;
        }
    }
}
