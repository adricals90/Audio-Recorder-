package c.adricals.AudioRecorder;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.File;
import java.io.InterruptedIOException;
import java.util.Date;


public class PlayerActivity extends AppCompatActivity {

   // MediaPlayer mediaPlayer;
   static SeekBar seekBar;
   static TextView startText;
   static TextView endText;
    Button control;
    MediaService mediaService;
    MaterialToolbar toolbar;
    static int duration = 0;
    static Handler handler;
    Uri uri;

    TextView name;
    TextView details;


    private View.OnClickListener controlListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            mediaService.playService(uri);
            duration = mediaService.getRemaining();
           // endText.setText(""+duration);
            seekBar.setMax(duration);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    int s = 0;
                    int finish = duration;
                    while (mediaService.mediaPlayer != null ){
                        try {
                            Message msg = new Message();
                            msg.what = mediaService.mediaPlayer.getCurrentPosition();
                            handler.handleMessage(msg);
                            Thread.sleep(1000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);


        seekBar = findViewById(R.id.seekBar);
        startText = findViewById(R.id.start);
        endText = findViewById(R.id.end);
        control = findViewById(R.id.controlButton);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // startText.setText("0:00");

        Intent intent = getIntent();
        uri = intent.getData();
        File file = new File(uri.getPath());

        name = findViewById(R.id.pName);
        details = findViewById(R.id.pDetails);

        name.setText(file.getName());
        details.setText(file.lastModified()+"");
        



        control.setOnClickListener(controlListener);




        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaService.mediaPlayer.seekTo(progress);
                    seekBar.setProgress(progress);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }



   public class MyHandler extends Handler{



        @Override
       public void handleMessage(Message msg){
            int currPosition = msg.what;

            seekBar.setProgress(currPosition);

            String rem = setTime(currPosition);
            startText.setText(rem);
            endText.setText(setTime(duration - currPosition));

        }
       public String setTime(int time) {
           String timeLabel = new String();
           int minutes = time / 1000 / 60;
           int seconds = time / 1000 % 60;
           timeLabel = minutes + ":";
           if (seconds < 10) {
               timeLabel += "0";
           }
           return timeLabel + seconds;
       }

   }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, MediaService.class);

        bindService(intent,connection , Context.BIND_AUTO_CREATE);
        handler = new MyHandler();

    }




    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);

    }



    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MediaService.MediaBinder binder = (MediaService.MediaBinder) service;
            mediaService = binder.getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if(mediaService != null){
                mediaService = null;
            }
        }
    };





}


