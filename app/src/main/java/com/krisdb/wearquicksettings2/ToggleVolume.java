package com.krisdb.wearquicksettings2;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ComplicationManager;
import android.support.wearable.complications.ComplicationProviderService;

public class ToggleVolume extends ComplicationProviderService {
    @Override
    public void onComplicationUpdate(int complicationId, int dataType, ComplicationManager complicationManager) {

        ComplicationData complicationData = null;

        Intent toggle = new Intent(this, ToggleService.class);
        Bundle bundle = new Bundle();
        bundle.putString("toggle", "mute");
        toggle.putExtras(bundle);

        if (dataType == ComplicationData.TYPE_ICON)
            complicationData = Utils.setData(
                    this,
                    PendingIntent.getService(this, 0, toggle, PendingIntent.FLAG_IMMUTABLE),
                    R.drawable.ic_action_mute
            );

        if (complicationData != null)
            complicationManager.updateComplicationData(complicationId, complicationData);
        else
            complicationManager.noUpdateRequired(complicationId);
    }
}
