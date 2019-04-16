package c.adricals.AudioRecorder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.media.audiofx.Visualizer;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static androidx.appcompat.app.AlertDialog.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView mReycleView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<records> myList = new ArrayList<records>();
    private static int PERMISSION_CODE = 1021;
    private String[] permission = {Manifest.permission.RECORD_AUDIO};

    private static String mFileName;
    MediaRecorder myRecorder;
    Button record;
    Button stop;
    Visualizer myVisualizer;


    MediaPlayer mPlayer;


    private View.OnClickListener recordListener = new View.OnClickListener() {

        boolean mrecord = true;

        @Override
        public void onClick(View v) {

            if (mrecord) {
                startRecording();
                record.setText("STOP");

            } else {
                stopRecording();
                record.setText("RECORD");

            }
            mrecord = !mrecord;

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recyclerView data and setup

        for (int i = 1; i <= 20; i++) {

            myList.add(new records("Recorder Name ", "Details :"));

        }

        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));


        mReycleView = findViewById(R.id.myRecycleView);
        mReycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mReycleView.setLayoutManager(mLayoutManager);
        mAdapter = new myAdapter(myList);
        mReycleView.setAdapter(mAdapter);

        //end of recyclerView

        //Visualizer


        //
        record = findViewById(R.id.recordButton);
        stop = findViewById(R.id.stopButton);

        mFileName = getExternalCacheDir().getAbsolutePath() + "/recording.3gp";

        record.setOnClickListener(recordListener);


    }

    public void stopRecording() {
        myRecorder.stop();
        myRecorder.release();

    }


    public void startRecording() {

        if (permissionCheck()) {

            myRecorder = new MediaRecorder();
            myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            myRecorder.setOutputFile(mFileName);
            myRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);


            Toast.makeText(this, "Permission Granted ", Toast.LENGTH_LONG).show();

            try {
                myRecorder.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }

            myRecorder.start();
        }


    }


    public void onRecord() {


    }


    public boolean permissionCheck() {

        Boolean granted = false;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {

                AlertDialog.Builder alert = new Builder(this);
                alert.setMessage("We need permission to proceed");
                alert.show();

            } else {
                ActivityCompat.requestPermissions(this, permission, PERMISSION_CODE);
            }

            return granted = false;

        } else {


            Toast.makeText(this, "Permission Granted ", Toast.LENGTH_LONG).show();

            return granted = true;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionCheck();
            } else {
                Toast.makeText(this, "Permission was not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
