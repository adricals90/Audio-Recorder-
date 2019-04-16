package c.adricals.AudioRecorder;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface RecordingStorageAccess {


    void insert(records items);

    void remove(String name);

    LiveData<List<records>> search(String name);

    LiveData<List<records>> getRecordings();

}

