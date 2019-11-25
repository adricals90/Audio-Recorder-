package c.adricals.AudioRecorder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class RecordingViewModel extends ViewModel {

    private MutableLiveData<List<records>> list;
    private Repository repository;

    public void init() {

        if (list != null) {
            return;
        }
        RecordingsAccessImplementation recAccess = new RecordingsAccessImplementation();
        repository = new Repository(recAccess);
        list = repository.recordingsList();

    }

    public LiveData<List<records>> getRecords() {
        return list;
    }

}
