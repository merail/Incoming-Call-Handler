package com.example.incomingcallhandler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.incomingcallhandler.db.DatabaseRepository;

public class MainActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    public static final int MY_PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS = 1;
    public static final String TAG = "phoneLLL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // We do not have this permission. Let's ask the user
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.READ_CALL_LOG},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }

//        Log.d(TAG, "onCreate: ");
//        DatabaseRepository repository = new DatabaseRepository(getApplication());
//        repository.addNumber("+79172821249", "rail, тут информация");

        DatabaseRepository.getInstance(getApplication());
    }


}