package edu.uit.MusicPlayer.Adapter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class NotificationAdapter {
    public void initChannel(Context mContext) {
        NotificationManager
                mNotificationManager =
                (NotificationManager) mContext
                        .getSystemService(Context.NOTIFICATION_SERVICE);
        // The id of the channel.
        String id = "edu.uit.MusicPlayer";
        // The user-visible name of the channel.
        CharSequence name = "Media playback";
        // The user-visible description of the channel.
        String description = "Media playback controls";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
        // Configure the notification channel.
        mChannel.setDescription(description);
        mChannel.setShowBadge(false);
        mChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        mNotificationManager.createNotificationChannel(mChannel);
    }

}
