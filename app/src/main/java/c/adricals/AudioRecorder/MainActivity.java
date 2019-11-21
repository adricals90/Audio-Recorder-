package c.adricals.AudioRecorder;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.media.audiofx.Visualizer;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static androidx.appcompat.app.AlertDialog.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView mReycleView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    RecordingViewModel model;


    private static int PERMISSION_CODE = 1021;
    private String[] permission = {Manifest.permission.RECORD_AUDIO};

    private static String mFileName;
    Button record;
    Button stop;
    // Visualizer myVisualizer;

    RecAudio recordAudio;



    MediaPlayer mPlayer;


    private View.OnClickListener recordListener = new View.OnClickListener() {

        boolean mRecord = true;

        @Override
        public void onClick(View v) {

            if (permissionCheck() == false) {
                return;
            }

            if (mRecord) {

                recordAudio.OnRecording();
                record.setText("Stop");

            } else {
                recordAudio.onStopRecording();
                record.setText("Record");
            }
            mRecord = !mRecord;

        }
    };

    private View.OnClickListener stopButton = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // recordAudio.onStopRecording();
            record.setText("Record");
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        mReycleView = findViewById(R.id.myRecycleView);
        mReycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mReycleView.setLayoutManager(mLayoutManager);

        model = ViewModelProviders.of(this).get(RecordingViewModel.class);
        model.init();
        model.getRecords().observe(this, new Observer<List<records>>() {
            @Override
            public void onChanged(List<records> records) {
                mAdapter.notifyDataSetChanged();

            }

        });

        mAdapter = new myAdapter(this, model.getRecords().getValue());
        mReycleView.setAdapter(mAdapter);

        //end of recyclerView

        //Visualizer


        //
        record = findViewById(R.id.recordButton);
        stop = findViewById(R.id.stopButton);


        recordAudio = new RecAudio();


        record.setOnClickListener(recordListener);

        // recImplement = new RecordingsAccessImplementation();




        /*File[] myfiles = recordAudio.directory.listFiles();

        for (File i : myfiles) {
            if (i.isDirectory()) {
                Log.i("directory", "this is a dir " + i.getName());
            } else {
                Log.i("file", "this is a file " + i.getName());
                myList.add(new records(i.getName(), "cache"));


            }
        }

        Log.i("file", "end of oncreate" + myfiles.length);
*/

    }

   /* @Override
    public void onStop(){
        super.onStop();
        if(recordAudio.myRecorder != null){
            recordAudio.myRecorder.release();
            recordAudio.myRecorder = null;
        }
    }*/



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
