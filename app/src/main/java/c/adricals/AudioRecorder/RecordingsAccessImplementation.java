package c.adricals.AudioRecorder;

import android.os.Environment;
import android.util.Log;

import java.io.File;
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
        root = new File(Environment.getExternalStorageDirectory().getPath() + "/Audio Recordings");
        File[] items = root.listFiles();

        list = new LinkedList<records>();
        records temp;
        for (File recordFile : items) {
            temp = new records(recordFile.getName(), "details");
            list.add(temp);
        }
        Log.d("error", "RecordingsAccessImplementation: " + items.length);


        recordingsList.setValue(list);

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
