package com.automattic.simplenote.widget.commands;

import static com.automattic.simplenote.widget.commands.WidgetConstants.ACTION_NOTIFY_DATA_SET_CHANGED;
import android.content.ComponentName;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.automattic.simplenote.R;
import com.automattic.simplenote.widget.SimpleNoteWidgetProvider;

/**
 * Created by richard on 9/7/14.
 */
public class NotifyDataSetChange extends WidgetCommand {

    private final Class mProviderClass;
    private final int mAdapterRes;

    public NotifyDataSetChange(Class providerClass, int adapterRes) {
        super(ACTION_NOTIFY_DATA_SET_CHANGED, false);
        mProviderClass = providerClass;
        mAdapterRes = adapterRes;
    }

    public void exec(ExecParameters params) {

        // update all widgets
        int ids[] = params.mWidgetManager.getAppWidgetIds(
                new ComponentName(params.mContext, mProviderClass));

        Toast.makeText(params.mContext, "ACTION_NOTIFY_DATA_SET_CHANGED", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "provider " + mProviderClass.getSimpleName());

        if (ids != null && ids.length > 0) {
            for (int i : ids) {
                Log.i(TAG, "Notify data set changed. widget id: " + Integer.toString(i));
                params.mWidgetManager.notifyAppWidgetViewDataChanged(i, mAdapterRes);
            }
        } else {
            Log.i(TAG, "no widgets were found for provider " +
                    mProviderClass.getSimpleName());
        }

    }

    protected RemoteViews getRemoteViews(ExecParameters params) {
        return null;
    }
}