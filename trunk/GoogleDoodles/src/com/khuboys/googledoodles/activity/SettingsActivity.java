package com.khuboys.googledoodles.activity;

import com.khuboys.activity.googledoodles.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

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
	}
}
