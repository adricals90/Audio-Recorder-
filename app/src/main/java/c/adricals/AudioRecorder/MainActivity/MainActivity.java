package c.adricals.AudioRecorder.MainActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


import c.adricals.AudioRecorder.PlayerActivity;
import c.adricals.AudioRecorder.R;

import static androidx.appcompat.app.AlertDialog.*;

public class MainActivity extends AppCompatActivity{

    RecyclerView mReycleView;
    myAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    RecordingViewModel model;


    private static int PERMISSION_CODE = 1021;
    private String[] permission = {Manifest.permission.RECORD_AUDIO};

    private static String mFileName;
    Button record;
    Button stop;
    Button play;
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
                stop.setVisibility(View.GONE);
                play.setVisibility(View.GONE);


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

    private View.OnClickListener playb = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(getApplicationContext(), PlayerActivity.class);
            startActivity(i);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));


        buildRecyclerView();
        //end of recyclerView

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                startActivity(intent);
            }
        }).attachToRecyclerView(mReycleView);


        //Visualizer


        //
        record = findViewById(R.id.recordButton);
        stop = findViewById(R.id.stopButton);
        play = findViewById(R.id.playButton);


        recordAudio = new RecAudio();


        record.setOnClickListener(recordListener);
        play.setOnClickListener(playb);


        // recImplement = new RecordingsAccessImplementation();





    }

   /* @Override
    public void onStop(){
        super.onStop();
        if(recordAudio.myRecorder != null){
            recordAudio.myRecorder.release();
            recordAudio.myRecorder = null;
        }
    }*/

   public void buildRecyclerView(){

       mReycleView = findViewById(R.id.myRecycleView);
       mReycleView.setHasFixedSize(true);
       mLayoutManager = new LinearLayoutManager(this);
       mReycleView.setLayoutManager(mLayoutManager);

       model = ViewModelProviders.of(this).get(RecordingViewModel.class);
       model.init();

       model.getRecords().observe(this, new Observer<List<Record>>() {
           @Override
           public void onChanged(List<Record> Record) {
               mAdapter.notifyDataSetChanged();
           }

       });

       mAdapter = new myAdapter(this, model.getRecords().getValue());


       mReycleView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new myAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Record r = model.getRecords().getValue().get(position);
                r.recordName = "clicked";
                Uri u = r.uri;
                Toast.makeText(getApplicationContext(), "on item selected "+r.details, Toast.LENGTH_LONG).show();



                Log.i("items1111", "onItemclickecinsidasdsda "+position);


                Intent in = new Intent(getApplicationContext(),PlayerActivity.class);
                in.setData(u);
                startActivity(in);
            }
        });

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
