package c.adricals.AudioRecorder;

import android.media.MediaRecorder;
import android.os.Environment;
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

        Date d = new Date();   //format  on May 04 09:51:52 CDT 2009

        String[] date = d.toString().split(" ");
        String hour = date[3];



        String dirName = "/Audio Recordings";
        directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), dirName);

        if (!directory.exists()) {
            directory.mkdir();
        }

        fileName = directory.getAbsolutePath() + "/Recording " + hour + ".m4a";
    }


    public void OnRecording() {

        myRecorder = new MediaRecorder();
        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        myRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        myRecorder.setOutputFile(fileName);

        try {
            myRecorder.prepare();
            myRecorder.start();

        } catch (IOException e) {
            e.printStackTrace();
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
