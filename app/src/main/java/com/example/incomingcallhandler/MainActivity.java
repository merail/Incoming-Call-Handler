package com.example.incomingcallhandler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.example.incomingcallhandler.db.DatabaseRepository;

public class MainActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    public static final int MY_PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS = 1;
    public static final String TAG = "phoneLLL";

    public static final int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

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
//        if (!isMyServiceRunning())
//            startService(new Intent(this, IncomingCallHandlerService.class));
        }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                // You don't have permission
                checkPermission();
                Log.d("ффффффф", "фффффф");
            } else {
                Log.d("bbbbbbbb", "bbbbbbb");
                // Do as per your logic
            }
        }
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            }
        }
    }

    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (IncomingCallHandlerService.class.getName().equals(service.service.getClassName())) {
                Log.d (IncomingCallHandlerService.TAG, "running");
                return true;
            }
        }
        Log.d (IncomingCallHandlerService.TAG, "not running");
        return false;
    }
}