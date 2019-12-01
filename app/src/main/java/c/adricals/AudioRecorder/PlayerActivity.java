package c.adricals.AudioRecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.net.URI;

public class PlayerActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    TextView startText;
    TextView endText;
    Button control;


    private View.OnClickListener controlListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            mediaPlayer.start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/Audio Recordings");

        File[] r = file.listFiles();
        mediaPlayer = MediaPlayer.create(this, Uri.fromFile(r[0]));

        seekBar = findViewById(R.id.seekBar);
        startText = findViewById(R.id.start);
        endText = findViewById(R.id.end);
        control = findViewById(R.id.controlButton);

        control.setOnClickListener(controlListener);










    }
}
