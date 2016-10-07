package test.launcher.mummu.nougatproject.notification_manager;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.support.v4.app.NotificationManagerCompat;

import test.launcher.mummu.nougatproject.MainActivity;
import test.launcher.mummu.nougatproject.R;

/**
 * Created by muhammed on 10/7/2016.
 */

public class NotificationManger {

    public static final String KEY_TEXT_REPLY = "key_text_reply";
    private Context context;
    private static NotificationManger mNotificationManger;

    private NotificationManger(Context context) {
        this.context = context;
    }

    public static NotificationManger getInstance(Context context) {
        return mNotificationManger == null ? new NotificationManger(context) : mNotificationManger;
    }

    public Context getContext() {
        return context;
    }

    public void createReplayNotification(String msg) {

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("Replay to " + msg)
                .build();
        Intent intent = new Intent(getContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

// Create the reply action and add the remote input.
        Notification.Action action =
                new Notification.Action.Builder(Icon.createWithResource(getContext(), R.drawable.ic_stat_name), "Replay to " + msg, pendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();


        // Build the notification and add the action.
        Notification newMessageNotification = new Notification.Builder(getContext())
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(getContext().getString(R.string.app_name))
                .setContentText(getContext().getString(R.string.app_name))
                .addAction(action).build();
// Issue the notification.
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(getContext());
        notificationManager.notify(0, newMessageNotification);
    }

    public void applyNewOne() {
        Notification repliedNotification =
                new Notification.Builder(context)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentText("Successfully replied")
                        .build();

// Issue the new notification.
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);
        notificationManager.notify(0, repliedNotification);
    }
}
