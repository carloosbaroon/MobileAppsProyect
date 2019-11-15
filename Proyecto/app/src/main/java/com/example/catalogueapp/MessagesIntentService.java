package com.example.catalogueapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MessagesIntentService extends IntentService {

    private static final String EXTRA_PARAM1 = "com.example.catalogueapp.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.catalogueapp.extra.PARAM2";

    public MessagesIntentService() {
        super("MessagesIntentService");
    }


    public static void startAction(Context context, int seconds, String mood) {
        Intent intent = new Intent(context, MessagesIntentService.class);
        intent.putExtra(EXTRA_PARAM1, seconds);
        intent.putExtra(EXTRA_PARAM2, mood);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();

                final int param1 = intent.getIntExtra(EXTRA_PARAM1, 0);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
            int i=0;

            try {
                while (i++ <10)
                {
                    Thread.sleep( param1*1000);
                    Toast.makeText(this, "Message Finalized", Toast.LENGTH_SHORT).show();
                }


            }catch (InterruptedException ie){
                Thread.currentThread().interrupt();

            }
            this.stopSelf();
            }
        }
    }


