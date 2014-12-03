package com.example.mynotificationmanager;

import java.util.Calendar;
import java.util.Random;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	
	public MyReceiver() {
		
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		context.startService(new Intent(context, MyService.class).putExtras(intent));
		Toast.makeText(context, "Start   "+intent.getStringExtra("myname"), Toast.LENGTH_SHORT).show();
		NotifyMe(context,intent.getStringExtra("myname"));
		
	}
	
	public static void setNext(Context context) {
		Calendar calendar = Calendar.getInstance();
		set(context, (short) 0, calendar);
	}
	
	private static void set(Context context, short timeIndex, Calendar actualTime) {
		Intent intent = new Intent(context, MyReceiver.class);
		String[]str = {"Sher","Ali","ZMQ","Software","Development","Technologies"};
		intent.putExtra("myname", str[new Random().nextInt(str.length)]);
		
		AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, actualTime.getTimeInMillis()+5000, PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT));	
		
	}
	
	@SuppressWarnings("deprecation")
	public void NotifyMe(Context context,String msg) {
		final NotificationManager mgr=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
	        @SuppressWarnings("deprecation")
			Notification note=new Notification(R.drawable.ic_launcher,"Sher Ali",System.currentTimeMillis());
	        PendingIntent i=PendingIntent.getActivity(context, 0,new Intent(context, MainActivity.class),0);	         
	        note.setLatestEventInfo(context, msg, "sherali_ali@yahoo.com", i);
	        mgr.notify(0, note);
	}
}
