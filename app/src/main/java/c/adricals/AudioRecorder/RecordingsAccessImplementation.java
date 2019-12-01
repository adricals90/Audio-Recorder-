package c.adricals.AudioRecorder;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RecordingsAccessImplementation implements RecordingStorageAccess {

    private List<records> list;
    private MutableLiveData<List<records>> recordingsList = null;
    File root = null;

    RecordingsAccessImplementation() {
        recordingsList = new MutableLiveData<>();
        list = new LinkedList<records>();
        root = new File(Environment.getExternalStorageDirectory().getPath() + "/Audio Recordings");

        if (root.exists()) {
            File[] items = root.listFiles();
            records temp;
            for (File recordFile : items) {

                double recLength = round(recordFile.length() * Math.pow(10, -6), 3);
                String dateFormated = getDate(new Date(recordFile.lastModified()).toString());

                String details = dateFormated + " " + recLength + " MB";

                temp = new records(recordFile.getName(), details);
                list.add(temp);
            }
            Log.d("error", "RecordingsAccessImplementation: " + items.length);
        }

        recordingsList.setValue(list);

    }

    public String getDate(String date) {

        String[] d = date.toString().split(" ");  // [on, May, 04, 09:51:52, CDT, 2009 ]
        return new String(d[1] + " " + d[2]);

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public void insert(records items) {

    }

    @Override
    public void remove(String name) {

    }

    @Override
    public LiveData<List<records>> search(String name) {

        return null;
    }

    @Override
    public MutableLiveData<List<records>> getRecordings() {


        return recordingsList;
    }

}
