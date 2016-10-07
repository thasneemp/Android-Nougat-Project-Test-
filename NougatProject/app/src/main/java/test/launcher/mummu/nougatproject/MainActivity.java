package test.launcher.mummu.nougatproject;

import android.app.RemoteInput;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import test.launcher.mummu.nougatproject.notification_manager.NotificationManger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton mEnableNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();

        Bundle fromIntent = RemoteInput.getResultsFromIntent(getIntent());
        if (fromIntent != null) {
            String msg = fromIntent.getCharSequence(NotificationManger.KEY_TEXT_REPLY).toString();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            NotificationManger.getInstance(this).applyNewOne();
        }

    }

    private void setUI() {
        mEnableNotificationButton = (AppCompatButton) findViewById(R.id.enableNotificationButton);

        mEnableNotificationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        NotificationManger.getInstance(this).createReplayNotification("Muhammed Thasneem");
    }
}
