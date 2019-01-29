package com.krisdb.wearquicksettings2;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

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
            if (toggle.equals("wifi"))
                toggleWifi();
            else if (toggle.equals("bluetooth"))
                toggleBluetooth();
            else if (toggle.equals("mute"))
                muteVolume();
        }

        final String open = extras.getString("open");

        if (open != null) {
            if (open.equals("sound")) {
                final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            }
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

    private void toggleBluetooth()
    {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        if (adapter == null)
            Toast.makeText(this, "No Bluetooth adapter found", Toast.LENGTH_SHORT).show();
        else if (adapter.isEnabled())
        {
            adapter.disable();
            Toast.makeText(this, "Turning Bluetooth off...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            adapter.enable();
            Toast.makeText(this, "Turning Bluetooth on...", Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleWifi()
    {
        WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

        if (wifiManager.isWifiEnabled())
        {
            wifiManager.setWifiEnabled(false);
            Toast.makeText(this, "Turning WiFi off...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            wifiManager.setWifiEnabled(true);
            Toast.makeText(this, "Turning WiFi on...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


    }
}
