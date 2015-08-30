package com.praw;

import java.util.List;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.GridView;

/**
 * @author Fredrik Hobert <fredrik.hobert@gmail.com>
 * 
 * Hämtar sparade bilder från db och visar upp i en gridview
 * 
 * */

public class ViewImages extends Activity {
	@Override  
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.image_view); 

		DatabaseHandler dbh = new DatabaseHandler(this);
		GridView gridview = (GridView) findViewById(R.id.grid); 

		List<Bitmap> images = dbh.getImages();
		
	    gridview.setAdapter(new ImageAdapter(this, images));
	}
}
