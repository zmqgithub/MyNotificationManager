package com.example.mynotificationmanager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

	public MyService() {

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		final class StartNotification implements Runnable {

			private Context context;
			private Intent intent;

			public StartNotification(Context c, Intent i) {
				context = c;
				intent = i;
			}

			public void run() {
				MyReceiver.setNext(context);
			}
		}
		new Thread(new StartNotification(this, intent)).start();
	}
}
