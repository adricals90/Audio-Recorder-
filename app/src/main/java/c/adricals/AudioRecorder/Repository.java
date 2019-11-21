package c.adricals.AudioRecorder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class Repository {


    private RecordingStorageAccess recDataAccess;

    Repository(RecordingsAccessImplementation recAccess) {
        recDataAccess = recAccess;
    }

    public MutableLiveData<List<records>> recordingsList() {
        return recDataAccess.getRecordings();
    }

}
