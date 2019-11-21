package c.adricals.AudioRecorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class RecAudio {

    //private static RecAudio RecInstance;
    MediaRecorder myRecorder;
    String fileName;
    File directory = null;


    public RecAudio() {
        // myRecorder = new MediaRecorder();
        setPath();
    }

    /*public static RecAudio getRecInstance(){

        if(RecInstance == null){

            return new RecAudio();

        }else{
            return RecInstance ;
        }

    }*/

    public boolean isRecording() {
        return myRecorder == null;
    }

    public void setPath() {

        Date d = new Date();
        long time = d.getTime();
        String dirName = "/Audio Recordings";
        directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), dirName);

        if (!directory.exists()) {
            directory.mkdir();
        }

        fileName = directory.getAbsolutePath() + "/Recording " + time + ".3gp";
    }


    public void OnRecording() {

        // if(myRecorder == null){
        myRecorder = new MediaRecorder();
        // }else{
        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        myRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        myRecorder.setOutputFile(fileName);

        Log.i("path inside the object ", fileName);
        try {
            myRecorder.prepare();
            myRecorder.start();

        } catch (IOException e) {
            e.printStackTrace();
            //  Log.i("failed to prepare()", e.toString());
        }

    }

    public boolean onStopRecording() {
        if (myRecorder == null) {
            return false;
        } else {
            myRecorder.reset();
            myRecorder.release();
            myRecorder = null;
            return true;

        }
    }

    public boolean onPauseRecording() {
        if (myRecorder == null) {
            return false;
        } else {
            myRecorder.pause();
            return true;
        }


    }

    public boolean onRessumeRecording() {
        if (myRecorder == null) {
            return false;
        } else {
            myRecorder.resume();
            return true;
        }

    }


}
