package com.krisdb.wearquicksettings2;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.support.wearable.complications.ComplicationData;

public class Utils {

    static ComplicationData setData(final Context ctx, final PendingIntent intent, final int resId)
    {
        return  new ComplicationData.Builder(ComplicationData.TYPE_ICON)
                .setIcon(Icon.createWithResource(ctx, resId))
                .setBurnInProtectionIcon(Icon.createWithResource(ctx, R.drawable.ic_burnin))
                .setTapAction(intent)
                .build();
    }

}
