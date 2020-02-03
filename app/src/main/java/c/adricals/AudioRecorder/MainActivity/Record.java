package c.adricals.AudioRecorder.MainActivity;

import android.net.Uri;

public class Record {

    String recordName;
    String details;
    Uri uri;
    int recImage = 0;


    Record() {

    }

    Record(String name, String det,Uri uri) {
        recordName = name;
        details = det;
        this.uri = uri;

    }


}
