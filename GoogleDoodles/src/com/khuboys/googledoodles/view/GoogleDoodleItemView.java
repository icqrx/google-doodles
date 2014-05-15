package com.khuboys.googledoodles.view;

import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khuboys.activity.googledoodles.R;
import com.khuboys.googledoodles.model.GoogleDoodle;
/**
 * 
 * @author QUOC NGUYEN
 *
 */
public class GoogleDoodleItemView extends LinearLayout {
	private ImageView imgDoodle;
	private TextView txtTitle;
	private TextView txtDate;
	
	private GoogleDoodle mGoogleDoodleItem;
	/**
	 * Constructor
	 * @param context
	 */
	public GoogleDoodleItemView(Context context) {
		super(context);
		LayoutInflater inflate = LayoutInflater.from(getContext());
		inflate.inflate(R.layout.doodle_item_view, this, true);
		if (imgDoodle == null) {
			imgDoodle = (ImageView) findViewById(R.id.imgDoodle);
		}
		if (txtTitle == null) {
			txtTitle = (TextView) findViewById(R.id.txtTitleDoodle);
		}
		if (txtDate == null) {
			txtDate = (TextView) findViewById(R.id.txtDateDoodle);
		}
	}
	
	/**
	 *  Set data for google doodle items view 
	 */
	public void setData (GoogleDoodle doodleItem, int position) {
		mGoogleDoodleItem = doodleItem;
		
		String imageLink = mGoogleDoodleItem.image_url;
		String title = mGoogleDoodleItem.title;
		
		if (mGoogleDoodleItem == null) {
			return;
		}
		
		if (imgDoodle != null) {
			try {
				URL url;
				url = new URL(imageLink);
//				Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//				imgDoodle.setImageBitmap(bitmap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (txtTitle != null) {
			txtTitle.setText(title);
		}
		
//		if (txtDate != null) {
//			txtDate.setText(mItemInfo.getPackageQuantity());
//		}
	}

}
