package com.khuboys.googledoodles.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.khuboys.activity.googledoodles.R;
import com.khuboys.googledoodles.utils.Defs;
import com.khuboys.googledoodles.utils.GoogleDoodlesJsonParser;
import com.khuboys.googledoodles.utils.HttpRequest;

/**
 * This class app setting, e.x : image, text contents ....  
 * @author QUOC NGUYEN
 *
 */

public class SettingsActivity extends PreferenceActivity{
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		
		new GetJSON().execute();
	}
	
	/**
	 * This class make a aysntask to get data from url
	 * @author QUOC NGUYEN
	 *
	 */
	private class GetJSON extends AsyncTask<Void, Void, Void>{
		ProgressDialog pDialog = null;
		@Override
		protected Void doInBackground(Void... params) {
			
			// Making a request to url and getting response
	        String jsonStr = HttpRequest.makeServiceCall(Defs.URL_LINK_REQUEST, Defs.GET, null);
	        
	        // Parser json file
	        GoogleDoodlesJsonParser.parseGoogleDoodlesInfo(jsonStr);
	        
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			  // Showing progress dialog
            pDialog = new ProgressDialog(SettingsActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
	}
}
