package c.adricals.AudioRecorder;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public interface RecordingStorageAccess {


    void insert(records items);

    void remove(String name);

    LiveData<List<records>> search(String name);

    MutableLiveData<List<records>> getRecordings();

}

