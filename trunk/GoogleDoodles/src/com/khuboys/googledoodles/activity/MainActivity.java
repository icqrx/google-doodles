package com.khuboys.googledoodles.activity;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;

import com.costum.android.widget.PullAndLoadListView;
import com.costum.android.widget.PullAndLoadListView.OnLoadMoreListener;
import com.khuboys.activity.googledoodles.R;
import com.khuboys.googledoodles.adapter.GoogleDoodleApdater;
import com.khuboys.googledoodles.asynctask.GetJSONAsyncTask;
import com.khuboys.googledoodles.interfaces.OnTaskCompleted;
import com.khuboys.googledoodles.model.GoogleDoodle;

public class MainActivity extends ListActivity implements OnTaskCompleted {

	private ArrayList<GoogleDoodle> doodleList = null;
	private GoogleDoodleApdater googleDoodleAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// run asynctask
		new GetJSONAsyncTask(MainActivity.this, this).execute();
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * note : TODO QUOC
	 */
	@Override
	public void onTaskCompleted(ArrayList<GoogleDoodle> list) {
		doodleList = list;
		
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				updateUI(doodleList);
			}
		});
	}
	
	/**
	 * 
	 * @param doodleList
	 */
	protected void updateUI(ArrayList<GoogleDoodle> doodleList) {
		googleDoodleAdapter = new GoogleDoodleApdater(MainActivity.this, doodleList);
		setListAdapter(googleDoodleAdapter);
		
//		((PullAndLoadListView) getListView()).setOnLoadMoreListener(new OnLoadMoreListener() {
//			
//			@Override
//			public void onLoadMore() {
//				new LoadMoreDataTask().execute();
//			}
//		});
	}

	/**
	 * Inner class for load more data task
	 * @author QUOC NGUYEN
	 *
	 */
	private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			if (isCancelled()) {
				return null;
			}

//			for (int i = 0; i < doodleList.size(); i++)
//				doodleList.add(doodleList.get(i));

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			//mListItems.add("Added after load more");

			// We need notify the adapter that the data have been changed
			((BaseAdapter) getListAdapter()).notifyDataSetChanged();

			// Call onLoadMoreComplete when the LoadMore task, has finished
			((PullAndLoadListView) getListView()).onLoadMoreComplete();

			super.onPostExecute(result);
		}

		@Override
		protected void onCancelled() {
			// Notify the loading more operation has finished
			((PullAndLoadListView) getListView()).onLoadMoreComplete();
		}
	}
}
