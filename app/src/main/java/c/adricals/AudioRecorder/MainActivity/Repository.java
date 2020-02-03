package c.adricals.AudioRecorder.MainActivity;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class Repository {


    private RecordingStorageAccess recDataAccess;

    Repository(RecordingsAccessImplementation recAccess) {
        recDataAccess = recAccess;
    }

    public MutableLiveData<List<Record>> recordingsList() {
        return recDataAccess.getRecordings();
    }

}
