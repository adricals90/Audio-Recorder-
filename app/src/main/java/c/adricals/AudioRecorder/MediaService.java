package c.adricals.AudioRecorder;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;


import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;

public class MediaService extends Service {

    private IBinder iBinder = new MediaBinder();
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }


    public void playService(Uri uri){

        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, uri);
            mediaPlayer.start();
        }

    }




    public int getRemaining(){
        if(mediaPlayer != null){
            return mediaPlayer.getDuration();
        }else{
            return 0;
        }

    }



    public class MediaBinder extends Binder{

        MediaService getService(){
            return  MediaService.this;
        }
    }
}
