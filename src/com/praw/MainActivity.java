package com.praw;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Fredrik Hobert <fredrik.hobert@gmail.com>
 * 
 * Startklass, hanterar interaktion med knappar 
 * 
 * */

public class MainActivity extends Activity implements DialogInterface.OnDismissListener {

	private Draw drawView;
	private Button save, newie, color, pencil, gallery;
	private AlertDialog.Builder builder;
	private AlertDialog dialog;
	private int currentColor, penSize;
	private String[] colors, pen;
	private DatabaseHandler dbh;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		drawView = (Draw) findViewById(R.id.draw);
		save = (Button) findViewById(R.id.save);
		newie = (Button) findViewById(R.id.newie);
		color = (Button) findViewById(R.id.color);
		pencil = (Button) findViewById(R.id.pencil);
		gallery = (Button) findViewById(R.id.gallery);
		dbh = new DatabaseHandler(this);
		buttonListenersSetup();
		penSize = 20;

		colors = new String[]{
				"Blå",
				"Turkos",
				"Gul",
				"Grön",
				"Rosa",
				"Grå",
				"Röd"
		};

		pen = new String[]{
				"Liten", 
				"Mellan", 
				"Stor"
		};

	}
	

	// Spara bild i db
	private void save(){
		Bitmap image = drawView.getBitmap();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] imageInByte = stream.toByteArray();
		if(dbh != null){
			dbh.insertImage(imageInByte);
		}
	}

	
	// Ny teckning
	private void newie(){
		drawView.newie();
	}


	// Ändra pensel färg
	private void color(){
		builder = new AlertDialog.Builder(this);

		builder.setTitle("Välj färg")
		.setItems(colors, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int color) {
				switch(color){
				case 0: currentColor = Color.BLUE; break;
				case 1: currentColor = Color.CYAN; break;
				case 2: currentColor = Color.YELLOW; break;
				case 3: currentColor = Color.GREEN; break;
				case 4: currentColor = Color.MAGENTA; break;
				case 5: currentColor = Color.GRAY; break;
				case 6: currentColor = Color.RED; break;
				}
			}
		});

		dialog = builder.create();
		dialog.setOnDismissListener(this);
		dialog.show();
	}


	// Ändra pensel storlek
	private void pencil(){
		builder = new AlertDialog.Builder(this);
		builder.setTitle("Välj penna")
		.setItems(pen, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int pen) {
				switch(pen){
				case 0: penSize = 10; break;
				case 1: penSize = 20; break;
				case 2: penSize = 35; break;
				}
			}
		});

		dialog = builder.create();
		dialog.setOnDismissListener(this);
		dialog.show();
	}


	@Override
	public void onDismiss(DialogInterface arg0) {
		drawView.setColor(currentColor);
		drawView.setSize(penSize);
	}


	// Visa teckningar i galleri
	private void gallery(){
		startActivity(new Intent(this, ViewImages.class));
	}


	private void buttonListenersSetup(){

		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});

		newie.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				newie();
			}
		});

		color.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				color();
			}
		});

		pencil.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pencil();
			}
		});

		gallery.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gallery();
			}
		});
	}


}
