package com.khuboys.googledoodles.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.khuboys.googledoodles.model.GoogleDoodle;
/**
 * 
 * @author QUOC NGUYEN
 *
 */
public class GoogleDoodlesJsonParser {
	
	private static final String TAG_DIRECT_NAME_URL= "name"; // direct_name_url
	private static final String TAG_TITLE = "title"; //title
	private static final String TAG_IMAGE_URL = "url"; //image_url
	private static final String TAG_RUN_DATE_ARRAY = "run_date_array"; //run_date_array
	private static final String TAG_QUERY = "query"; //run_date_array
	
	private static final String TAG = GoogleDoodlesJsonParser.class.getSimpleName();
	
	/**
	 * Constructor
	 */
	public GoogleDoodlesJsonParser() {

	}
	
	/**
	 * this function support parser data from url
	 * @param data
	 * @return
	 */
	public static ArrayList<GoogleDoodle> parseGoogleDoodlesInfo(String data) {
		GoogleDoodle googleDoodleObject = new GoogleDoodle();
		ArrayList<GoogleDoodle> doodleList = new ArrayList<GoogleDoodle>();
		try{
			//JSONObject jsonObj = new JSONObject(json);
			JSONArray jsonArray = new JSONArray(data);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject ob = jsonArray.getJSONObject(i);
				
				String directNameUrl = ob.getString(TAG_DIRECT_NAME_URL);
				String title = ob.getString(TAG_TITLE);
				String image_url = ob.getString(TAG_IMAGE_URL);
				String run_date_array = ob.getString(TAG_RUN_DATE_ARRAY);
				String query = ob.getString(TAG_QUERY);
				
				googleDoodleObject.direct_name_url = directNameUrl;
				googleDoodleObject.title = title;
				googleDoodleObject.image_url = Defs.HTTP + image_url;
				googleDoodleObject.run_date_array = run_date_array;
				googleDoodleObject.query = query;
				
				doodleList.add(googleDoodleObject);
				
				Log.w(TAG, "url: " + directNameUrl);
				Log.w(TAG, "title: " + title);
				Log.w(TAG, "image url: " + image_url);
				Log.w(TAG, "run...: " + run_date_array);
				
			}
			 
		}catch(Exception ex){
			Log.e(TAG, "" + ex.toString());
		}
		return doodleList;
	}
}
