package com.khuboys.googledoodles.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.khuboys.googledoodles.model.GoogleDoodle;
import com.khuboys.googledoodles.view.GoogleDoodleItemView;

public class GoogleDoodleApdater extends BaseAdapter{
	private Context mContext;
	private ArrayList<GoogleDoodle> mDoodleList = null;
	private GoogleDoodleItemView googleDoodleItemView = null;
	private int mSelectedPostition = -1;
	/**
	 * Constructor
	 * @param context
	 * @param doodleList
	 */
	public GoogleDoodleApdater(Context context, ArrayList<GoogleDoodle> doodleList) {
		mContext = context;
		
		if(doodleList != null) {
			mDoodleList = doodleList;
		} else {
			mDoodleList = new ArrayList<GoogleDoodle>();
		}
	}
	@Override
	public int getCount() {
		if(mDoodleList != null) {
			return mDoodleList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(mDoodleList != null) {
			return mDoodleList.get(position);
		}
		return null;
	}
	/**
	 * set google doodle list
	 * @param doodleList
	 */
	public void setDoodleList(ArrayList<GoogleDoodle> doodleList) {
		this.mDoodleList = doodleList;
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	/*
	 * remove item of google doodle list
	 */
	public void removeItem(GoogleDoodle item) {
		mDoodleList.remove(item);
	}
	/**
	 * 
	 * @param item
	 * @param pos
	 */
	public void insertItem(GoogleDoodle item, int pos) {
		mDoodleList.add(pos, item);
	}
	/**
	 * get selection
	 * @return
	 */
	public int getSelectedPostition() {
		return mSelectedPostition;
	}
	/**
	 * set selection
	 * @param mSelectedPostition
	 */
	public void setSelectedPostition(int mSelectedPostition) {
		this.mSelectedPostition = mSelectedPostition;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			googleDoodleItemView = new GoogleDoodleItemView(mContext);
		} else {
			googleDoodleItemView = (GoogleDoodleItemView) convertView;
		}
		if(mDoodleList == null) {
			return googleDoodleItemView;
		}
		GoogleDoodle itemInfo = mDoodleList.get(position);
		googleDoodleItemView.setData(itemInfo, position);
		return googleDoodleItemView;
	}

}
