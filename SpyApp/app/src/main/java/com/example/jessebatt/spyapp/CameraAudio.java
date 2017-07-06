package com.example.jessebatt.spyapp;

/**
 * Created by Jesse on 10/03/2017.
 */

        import android.Manifest;
        import android.content.Context;

        import android.media.MediaRecorder;
        import android.os.Environment;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.Surface;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.FrameLayout;

        import android.app.Activity;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.hardware.Camera;
        import android.hardware.Camera.PictureCallback;
        import android.widget.ImageView;
        import android.widget.Toast;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;


public class CameraAudio extends AppCompatActivity {

    private Camera cameraObject;
    private ShowCamera showCamera;
    private ImageView pictureTaken;
    private MediaRecorder recorder;
    private String folder_main = "SpyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_audio);
        pictureTaken = (ImageView) findViewById(R.id.imageView);
        cameraObject = isCameraAvailable();
        showCamera = new ShowCamera(this, cameraObject);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
        preview.addView(showCamera);
        cameraObject.setDisplayOrientation(90);

// Make directory called RecordData if it doesn't already exist
        File f = new File(Environment.getExternalStorageDirectory(),
                folder_main);
        if (!f.exists()) {
            f.mkdirs();
        }
        String path = Environment.getExternalStorageDirectory().toString();
// enter the following code statement all on one line
        String filename = path + "/" + folder_main + "/" + String.format("%d.3gp", System.currentTimeMillis());
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(filename);
        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        recorder.start(); // Recording is now started
    }

    public static Camera isCameraAvailable() {
        Camera object = null;
        try {
            object = Camera.open();

        } catch (Exception e) {
        }
        return object;
    }
    private PictureCallback captureMedia = new android.hardware.Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (bitmap == null) {
                Toast.makeText(getApplicationContext(), "Taking picture failed.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Picture taken.", Toast.LENGTH_SHORT).show();
                pictureTaken.setImageBitmap(bitmap);
                // Make directory called RecordData if it doesn't already exist
                File f = new File(Environment.getExternalStorageDirectory(),
                        folder_main);
                if (!f.exists()) {
                    f.mkdirs();
                }
                String path = Environment.getExternalStorageDirectory().toString();
// enter the following code statement all on one line
                String filename = path + "/" + folder_main + "/" + String.format("%d.jpg", System.currentTimeMillis());

                FileOutputStream outStream = null;
                try {
                    outStream = new FileOutputStream(filename);
                    outStream.write(data);
                    outStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
            }
            ///cameraObject.release();
            cameraObject.startPreview();
        }
    };

    public void show(View view) {

        Button layout2 = (Button) findViewById(R.id.capture);
        layout2.setVisibility(View.VISIBLE);
        Button layout3 = (Button) findViewById(R.id.hide);
        layout3.setVisibility(View.VISIBLE);
        Button layout4 = (Button) findViewById(R.id.hiddencapture);
        layout4.setVisibility(View.INVISIBLE);
        ImageView layout5 = (ImageView) findViewById(R.id.background1);
        layout5.setVisibility(View.INVISIBLE);
        cameraObject.startPreview();
    }

    public void hide(View view) {
        Button layout2 = (Button) findViewById(R.id.capture);
        layout2.setVisibility(View.INVISIBLE);
        Button layout3 = (Button) findViewById(R.id.hide);
        layout3.setVisibility(View.INVISIBLE);
        Button layout4 = (Button) findViewById(R.id.hiddencapture);
        layout4.setVisibility(View.VISIBLE);
        ImageView layout5= (ImageView) findViewById(R.id.background1);
        layout5.setVisibility(View.VISIBLE);


    }

    public void HiddencaptureImage(View view) {
        captureImage(view);
        hide(view);
    }

    public void captureImage (View view) {
        cameraObject.takePicture(null, null, captureMedia);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            recorder.stop();
            recorder.reset();
            recorder.release();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        recorder.stop();
        recorder.reset();
        recorder.release();
    }
}
