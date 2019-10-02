package c.adricals.AudioRecorder;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RocordingViewModel extends AndroidViewModel {

    LiveData<List<records>> recordingsList = new MutableLiveData<>();
    RecordingStorageAccess e = new RecordingsAccessImplementation();

    public RocordingViewModel(@NonNull Application application) {
        super(application);
    }


}
