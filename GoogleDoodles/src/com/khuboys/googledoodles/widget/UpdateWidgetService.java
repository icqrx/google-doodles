package com.khuboys.googledoodles.widget;

import java.util.ArrayList;
import java.util.Random;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.webkit.WebView.FindListener;
import android.widget.RemoteViews;

import com.khuboys.activity.googledoodles.R;
import com.khuboys.googledoodles.interfaces.OnTaskCompleted;
import com.khuboys.googledoodles.model.GoogleDoodle;

public class UpdateWidgetService extends Service implements OnTaskCompleted {
	private static final String LOG = "UpdateWidgetService";
	private ArrayList<GoogleDoodle> doodleList = null;
	@Override
	public void onStart(Intent intent, int startId) {
		// Create some random data

				AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
						.getApplicationContext());

				int[] allWidgetIds = intent
						.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

				ComponentName thisWidget = new ComponentName(getApplicationContext(),
						MyWidgetProvider.class);
				int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
				Log.w(LOG, "From Intent" + String.valueOf(allWidgetIds.length));
				Log.w(LOG, "Direct" + String.valueOf(allWidgetIds2.length));

				for (int widgetId : allWidgetIds) {
					// Create some random data
					int number = (new Random().nextInt(100));

					RemoteViews remoteViews = new RemoteViews(this
							.getApplicationContext().getPackageName(),
							R.layout.widget_layout);
					
					Log.w("WidgetExample", String.valueOf(number));
					// Set the text
					remoteViews.setTextViewText(R.id.update,
							"Random: " + String.valueOf(number));

					// Register an onClickListener
					Intent clickIntent = new Intent(this.getApplicationContext(),
							MyWidgetProvider.class);

					clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
					clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
							allWidgetIds);

					PendingIntent pendingIntent = PendingIntent.getBroadcast(
							getApplicationContext(), 0, clickIntent,
							PendingIntent.FLAG_UPDATE_CURRENT);
					remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
					appWidgetManager.updateAppWidget(widgetId, remoteViews);
				}
				stopSelf();
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onTaskCompleted(ArrayList<GoogleDoodle> list) {
		doodleList = list;
	}

}
