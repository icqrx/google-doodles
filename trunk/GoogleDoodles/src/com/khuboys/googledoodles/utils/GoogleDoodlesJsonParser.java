package com.khuboys.googledoodles.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.khuboys.googledoodles.model.GoogleDoodle;

public class GoogleDoodlesJsonParser {
	
	private static final String TAG_DIRECT_NAME_URL= "name"; // direct_name_url
	private static final String TAG_TITLE = "title"; //title
	private static final String TAG_IMAGE_URL = "url"; //image_url
	private static final String TAG_RUN_DATE_AWAY = "run_date_array"; //run_date_array

	private static final String TAG = GoogleDoodlesJsonParser.class.getSimpleName();
	
	public GoogleDoodlesJsonParser() {

	}
	
	public ArrayList<GoogleDoodle> parseStatusInfo(String data) {
		JSONArray orders = null;
		ArrayList<GoogleDoodle> statusList = new ArrayList<GoogleDoodle>();
		try{
			JSONObject json = new JSONObject(data);
			
		}catch(Exception ex){
			Log.e(TAG, "" + ex.toString());
		}
		return statusList;
	}
}
