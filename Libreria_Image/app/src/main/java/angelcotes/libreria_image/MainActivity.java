package angelcotes.libreria_image;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afollestad.materialcamera.MaterialCamera;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private final static int CAMERA_RQ = 6969;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File saveFolder = new File(Environment.getExternalStorageDirectory(), "MaterialCamera Sample");
        if (!saveFolder.mkdirs())
            throw new RuntimeException("Unable to create save directory, make sure WRITE_EXTERNAL_STORAGE permission is granted.");

        new MaterialCamera(this)                       // Constructor takes an Activity
                .allowRetry(true)                          // Whether or not 'Retry' is visible during playback
                .autoSubmit(false)                         // Whether or not user is allowed to playback videos after recording. This can affect other things, discussed in the next section.
                .saveDir(saveFolder)                       // The folder recorded videos are saved to
                .primaryColorAttr(R.attr.colorPrimary)     // The theme color used for the camera, defaults to colorPrimary of Activity in the constructor
                .showPortraitWarning(true)                 // Whether or not a warning is displayed if the user presses record in portrait orientation
                .defaultToFrontFacing(false)               // Whether or not the camera will initially show the front facing camera
                .retryExits(false)                         // If true, the 'Retry' button in the playback screen will exit the camera instead of going back to the recorder
                .start(CAMERA_RQ);                         // Starts the camera activity, the result will be sent back to the current Activity
    }
}
