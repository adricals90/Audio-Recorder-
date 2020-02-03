package c.adricals.AudioRecorder.MainActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class RecordingViewModel extends ViewModel {

    private MutableLiveData<List<Record>> list;
    private Repository repository;

    public void init() {

        if (list != null) {
            return;
        }
        RecordingsAccessImplementation recAccess = new RecordingsAccessImplementation();
        repository = new Repository(recAccess);
        list = repository.recordingsList();

    }

    public LiveData<List<Record>> getRecords() {
        return list;
    }

}
