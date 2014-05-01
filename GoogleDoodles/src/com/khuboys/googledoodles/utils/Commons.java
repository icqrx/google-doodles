package com.khuboys.googledoodles.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Commons {
	public static Bitmap getBitmapFromURL(String src) {
		Bitmap bitmap = null;
	    try {
	    	 URL url = new URL(src);
	         //try this url = "http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg"
	         HttpGet httpRequest = null;

	         httpRequest = new HttpGet(url.toURI());

	         HttpClient httpclient = new DefaultHttpClient();
	         HttpResponse response = (HttpResponse) httpclient
	                 .execute(httpRequest);

	         HttpEntity entity = response.getEntity();
	         BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
	         InputStream input = b_entity.getContent();

	         bitmap = BitmapFactory.decodeStream(input);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return bitmap;
	}
}
