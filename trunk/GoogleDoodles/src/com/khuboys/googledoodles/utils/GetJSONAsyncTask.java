package com.khuboys.googledoodles.utils;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.khuboys.googledoodles.interfaces.OnTaskCompleted;
import com.khuboys.googledoodles.model.GoogleDoodle;

/**
 * 
 * @author QUOC NGUYEN
 *
 */
public class GetJSONAsyncTask extends AsyncTask<GoogleDoodle, GoogleDoodle, ArrayList<GoogleDoodle>>{
	private ProgressDialog pDialog = null;
	private Context context;
	private OnTaskCompleted listener = null;
	
	private static final String TAG = GetJSONAsyncTask.class.getSimpleName();
	/**
	 * 
	 * @param mContext
	 */
	public GetJSONAsyncTask(Context mContext, OnTaskCompleted mListener){
		this.context = mContext;
		listener = mListener;
	}
	
	@Override
	protected ArrayList<GoogleDoodle> doInBackground(GoogleDoodle... params) {
		// Making a request to url and getting response
        String jsonStr = HttpRequest.makeServiceCall(Defs.URL_LINK_REQUEST, Defs.GET, null);
        
        // Parser json file
		return GoogleDoodlesJsonParser.parseGoogleDoodlesInfo(jsonStr);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		  // Showing progress dialog
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
	}

	@Override
	protected void onPostExecute(ArrayList<GoogleDoodle> result) {
		//super.onPostExecute(result);
		pDialog.dismiss();
		
		listener.onTaskCompleted(result);
	}
	
}
