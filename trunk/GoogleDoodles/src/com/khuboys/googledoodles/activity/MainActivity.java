package com.khuboys.googledoodles.activity;

import java.net.URL;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khuboys.activity.googledoodles.R;
import com.khuboys.googledoodles.interfaces.OnTaskCompleted;
import com.khuboys.googledoodles.model.GoogleDoodle;
import com.khuboys.googledoodles.utils.GetJSONAsyncTask;

public class MainActivity extends ActionBarActivity implements OnTaskCompleted {
	private ImageView imgDoodle;
	private TextView txtTitle;
	private TextView txtDate;
	private ArrayList<GoogleDoodle> doodleList = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		// run asynctask
		new GetJSONAsyncTask(MainActivity.this, this).execute();
		
	}
	/**
	 * 
	 */
	public void updateUI(ArrayList<GoogleDoodle> doodleList){
		// update UI
		try {
			String imageLink = doodleList.get(0).image_url;
			String title = doodleList.get(0).title;
			imgDoodle = (ImageView)findViewById(R.id.imgDoodle);
			txtTitle = (TextView)findViewById(R.id.txtTitleDoodle);
			//txtDate = (TextView)findViewById(R.id.txtDateDoodle);
			URL url = new URL(imageLink);
			Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
			imgDoodle.setImageBitmap(bitmap);
			
			txtTitle.setText(title);
			// txtDate.setText(doodleList.get(0).);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
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

}
