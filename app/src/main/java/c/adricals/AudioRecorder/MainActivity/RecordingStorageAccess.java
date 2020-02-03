package c.adricals.AudioRecorder.MainActivity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public interface RecordingStorageAccess {


    void insert(Record items);

    void remove(String name);

    LiveData<List<Record>> search(String name);

    MutableLiveData<List<Record>> getRecordings();

}

