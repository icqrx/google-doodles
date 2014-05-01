package com.khuboys.googledoodles.interfaces;

import java.util.ArrayList;

import com.khuboys.googledoodles.model.GoogleDoodle;

public interface OnTaskCompleted {
	void onTaskCompleted(ArrayList<GoogleDoodle> list);
}
