package com.praw;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author Fredrik Hobert <fredrik.hobert@gmail.com>
 * 
 * Hanterar bilderna som ska visas i ViewImages.java,
 * varje bitmap från en lista visas i en egen vy.
 * 
 * */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bitmap> images;
    
    
    public ImageAdapter(Context context, List<Bitmap> img) {
        mContext = context;
        images = img;
    }

    
    @Override
	public int getCount() {
        return images.size();
    }

    
    @Override
	public Object getItem(int position) {
        return null;
    }

    
    @Override
	public long getItemId(int position) {
        return 0;
    }

    
    // Returnerar vy för varje bitmap element i images
    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) { 
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(images.get(position));
        return imageView;
    }

   
}