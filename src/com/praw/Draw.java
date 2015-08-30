package com.praw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Fredrik Hobert <fredrik.hobert@gmail.com>
 * 
 * Vy som hanterar rit-funktion
 * 
 * */

public class Draw extends View {

	private Canvas canvas;
	private Bitmap bitmap;
	private Paint paint;
	private Path path;	


	public Draw(Context context, AttributeSet attrs) {
		super(context, attrs);

		path = new Path();
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(20);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		
	}


	// Anpassar vy till skärmstorlek
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		canvas.drawColor(Color.WHITE);
	}


	// Ritar ut vy
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(bitmap, 0, 0, null);
		canvas.drawPath(path, paint);
	}


	// Hanterar användarinput
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float touchX = event.getX();
		float touchY = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(touchX, touchY);
			break;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(touchX, touchY);
			break;
		case MotionEvent.ACTION_UP:
			canvas.drawPath(path, paint);
			path.reset();
			break;
		default:
			return false;
		}

		invalidate();
		return true;
	}


	// Ny teckning
	public void newie(){
		canvas.drawColor(Color.WHITE);
		invalidate();
	}


	// Ändra penselfärg
	public void setColor(int color){
		paint.setColor(color);
		invalidate();
	}


	// Ändra penselstorlek
	public void setSize(int size){
		paint.setStrokeWidth(size);
		invalidate();
	}


	public Bitmap getBitmap() {
		return bitmap;
	}


}
