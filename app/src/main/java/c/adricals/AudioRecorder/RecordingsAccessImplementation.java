package c.adricals.AudioRecorder;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RecordingsAccessImplementation implements RecordingStorageAccess {

    private MutableLiveData<List<records>> recordingsList = null;
    File root;
    File[] items;


    RecordingsAccessImplementation() {
        //  root = new File(Environment.getDownloadCacheDirectory().getPath());
        items = Environment.getDownloadCacheDirectory().listFiles();

        //path /storage/self/primary/Android/data/c.adricals.audiorecorder/cache
        //boolean m = root.isDirectory();


        // Log.i("get path test ", Boolean.toString(m));
        // files(items);
        files(items);

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
    public LiveData<List<records>> getRecordings() {
        File[] files = null;
        List<records> list = null;

        if (root != null) {

            files = root.listFiles();


            for (File i : files) {
                records rec = new records();
                rec.recordName = i.getName();

                list.add(rec);
                Log.i("files in ", i.toString());


            }

            recordingsList.setValue(list);
        }
        Log.i("not a directory ", null);


        return recordingsList;
    }

    public void files(File[] files) {

        if (root == null) {
            return;
        }


        for (File i : files) {


            Log.i("directory ", i.getName());


        }

    }


}
