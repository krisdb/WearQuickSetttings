package com.krisdb.wearquicksettings2;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ComplicationManager;
import android.support.wearable.complications.ComplicationProviderService;

public class OpenMediaVolume extends ComplicationProviderService {
    @Override
    public void onComplicationUpdate(int complicationId, int dataType, ComplicationManager complicationManager) {

        ComplicationData complicationData = null;

        Intent open = new Intent(this, ToggleService.class);
        Bundle bundle = new Bundle();
        bundle.putString("toggle", "mediavolme");
        open.putExtras(bundle);

        if (dataType == ComplicationData.TYPE_ICON)
            complicationData = Utils.setData(
                    this,
                    PendingIntent.getActivity(this, 0, new Intent(Settings.ACTION_SHOW_REGULATORY_INFO), PendingIntent.FLAG_IMMUTABLE),
                    R.drawable.ic_action_display
            );

        if (complicationData != null)
            complicationManager.updateComplicationData(complicationId, complicationData);
        else
            complicationManager.noUpdateRequired(complicationId);
    }
}
