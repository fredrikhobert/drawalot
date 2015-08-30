package com.praw;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Fredrik Hobert <fredrik.hobert@gmail.com>
 * 
 * Hanterar interaktion med SQLitedatabas där bilderna kan sparas och hämtas
 * 
 * */

public class DatabaseHandler extends SQLiteOpenHelper {
	
	
	public DatabaseHandler(Context context) {
		super(context, "images", null, 1);
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
        String CREATE_PRIMES_TABLE = "CREATE TABLE images (" +
        		"image BLOB )";
        db.execSQL(CREATE_PRIMES_TABLE);	
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS images");
        onCreate(db);
	}
	
	
	public void insertImage(byte[] bitmap) {
		SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("image", bitmap);
        db.insert("images", null, values);
        db.close(); 
	}
	

	public List<Bitmap> getImages() {
	    List<Bitmap> imageList = new ArrayList<Bitmap>();
        String selectQuery = "SELECT image FROM images";
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
            do {
                byte[] image;
                image = cursor.getBlob(0);
                ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
    			Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                imageList.add(theImage);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return imageList;
    }
	
}

