package com.krisdb.wearquicksettings2;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
            requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT);
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {

    });

    public void onClickOpenApps(View v) {
        startActivity(new Intent(Settings.ACTION_APPLICATION_SETTINGS));
    }

    public void onClickOnBluetooth(View v) {
        startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
    }

    public void onClickOnOpenConnectivitySettings(View v) {
        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
    }

    public void onClickOnOpenDisplaySettings(View v) {
        startActivity(new Intent(Settings.ACTION_DISPLAY_SETTINGS));
    }

    public void onClickOnOpenSoundSettings(View v) {
        startActivity(new Intent(Settings.ACTION_SOUND_SETTINGS));
    }

    public void onClickOnOpenWifiSettings(View v) {
        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }

    public void onClickOnBatterySaverSettings(View v) {
        startActivity(new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS));
    }

    public void onClickOnToggleBluetooth(View v) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S || ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {

            final BluetoothAdapter adapter = ((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();

            if (adapter == null)
                Toast.makeText(this, "No Bluetooth adapter found", Toast.LENGTH_SHORT).show();
            else if (adapter.isEnabled()) {
                adapter.disable();
                Toast.makeText(this, "Bluetooth off", Toast.LENGTH_SHORT).show();
            } else {
                adapter.enable();
                Toast.makeText(this, "Bluetooth on", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClickOnToggleVolume(View v) {
        final AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        if (amanager != null) {
            amanager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_MUTE, 0);
            amanager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_MUTE, 0);
            amanager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
            amanager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_MUTE, 0);
            amanager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_MUTE, 0);

            Toast.makeText(this, "Volume muted", Toast.LENGTH_SHORT).show();
        }
    }

}
