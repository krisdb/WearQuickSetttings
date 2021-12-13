package com.krisdb.wearquicksettings2;

import android.app.PendingIntent;
import android.content.Intent;
import android.provider.Settings;
import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ComplicationManager;
import android.support.wearable.complications.ComplicationProviderService;

public class OpenApps extends ComplicationProviderService {

    @Override
    public void onComplicationUpdate(int complicationId, int dataType, ComplicationManager complicationManager) {

        ComplicationData complicationData = null;

        if (dataType == ComplicationData.TYPE_ICON)
            complicationData = Utils.setData(
                    this,
                    PendingIntent.getActivity(this, 0, new Intent(Settings.ACTION_APPLICATION_SETTINGS), PendingIntent.FLAG_IMMUTABLE),
                    R.drawable.ic_action_apps
            );

        if (complicationData != null)
            complicationManager.updateComplicationData(complicationId, complicationData);
        else
            complicationManager.noUpdateRequired(complicationId);
    }
}
