package c.adricals.AudioRecorder;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RocordingViewModel extends androidx.lifecycle.ViewModel {

    LiveData<List<records>> recordingsList = new MutableLiveData<>();
    RecordingStorageAccess e = new RecodingsAccessImplementation();


    public void setRecordingsList(LiveData<List<records>> recordingsList) {
        this.recordingsList = recordingsList;
    }
}
