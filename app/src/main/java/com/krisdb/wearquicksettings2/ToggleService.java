package com.krisdb.wearquicksettings2;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ToggleService extends IntentService {

    public ToggleService() {
        super("ToggleService");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, startId, startId);

        Bundle extras = intent.getExtras();

        final String toggle = extras.getString("toggle");

        if (toggle != null) {
            if (toggle.equals("bluetooth"))
                toggleBluetooth();
            else if (toggle.equals("mute"))
                muteVolume();
            else if (toggle.equals("mediavolme"))
                openMediaVolume();
        }
        return START_STICKY;
    }

    private void muteVolume() {
        AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        if (amanager != null) {
            amanager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_MUTE, 0);
            amanager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_MUTE, 0);
            amanager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
            amanager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_MUTE, 0);
            amanager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_MUTE, 0);

            Toast.makeText(this, "Volume muted", Toast.LENGTH_SHORT).show();
        }
    }

    private void openMediaVolume() {
        AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        if (amanager != null)
            amanager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
    }

    private void toggleBluetooth()
    {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        if (adapter == null)
            Toast.makeText(this, "No Bluetooth adapter found", Toast.LENGTH_SHORT).show();
        else if (adapter.isEnabled())
        {
            adapter.disable();
            Toast.makeText(this, "Bluetooth off", Toast.LENGTH_SHORT).show();
        }
        else
        {
            adapter.enable();
            Toast.makeText(this, "Bluetooth on", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
