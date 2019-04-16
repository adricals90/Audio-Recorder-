package c.adricals.AudioRecorder;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RecodingsAccessImplementation implements RecordingStorageAccess {

    RecodingsAccessImplementation() {

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
        return null;
    }
}
