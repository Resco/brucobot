package com.example.brucobot;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private TextView output, sub;
	private String phrase1, phrase2;
	private Button button, buttonNum;
	private ImageButton buttonIm;
	private ImageView imageview, imageview2;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar ab = getActionBar();
		ab.setDisplayShowTitleEnabled(true); 
		ab.setHomeButtonEnabled(false); 
		
		phrase1= getResources().getString(R.string.phrase1);
		phrase2= getResources().getString(R.string.phrase2);
		output = (TextView) findViewById(R.id.textView1);
		button= (Button) findViewById(R.id.button1);
		buttonNum= (Button) findViewById(R.id.button2);
		imageview = (ImageView) findViewById(R.id.imageView1);
		sub = (TextView) findViewById(R.id.textView2);
		buttonIm = (ImageButton) findViewById(R.id.imageButton1);
		imageview2 = (ImageView) findViewById(R.id.imageView2);
		
		button.setOnClickListener(this);
		buttonNum.setOnClickListener(this);
		buttonIm.setOnClickListener(this);
		registerForContextMenu(imageview);
}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.button1){
		if((int)(Math.random()*2) == 0 )
			output.setText(phrase1);
		else output.setText(phrase2);}
		else if(v.getId() == R.id.imageButton1)
		{
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, 2);
		}
		else{
			
			Intent intent = new Intent(this, InsertNumberSubActivity.class); // Explicit intent creation
			startActivityForResult(intent, 1); // Start as sub-activity for result
			
		}
			

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return true;
	}
	
	@Override  
	   public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
	super.onCreateContextMenu(menu, v, menuInfo);  
	    menu.setHeaderTitle("Prova");  
	    menu.add(0, v.getId(), 0, "Action 1");  
	    menu.add(0, v.getId(), 0, "Action 2");  
	}  
	
	   @Override  
	   public boolean onContextItemSelected(MenuItem item) {  
	           if(item.getTitle()=="Action 1"){function1(item.getItemId());}  
	       else {return false;}  
	   return true;  
	   }

	private void function1(int itemId) {
		Toast toast = Toast.makeText(getApplicationContext(), "provare", Toast.LENGTH_SHORT);
		toast.show();		
	} 
	
	 protected void onActivityResult(
	            int requestCode, 
	            int resultCode,
	            Intent pData) 
	    {
	         if ( requestCode == 1 ) 
	         {
	             if (resultCode == Activity.RESULT_OK ) 
	             {
	                 final String zData = pData.getExtras().getString( "risposta" );
	                 
	                 //..do something with our retrieved value..
	                 
	                 sub.setText(zData);
	                 //..logcats "Retrieved Value zData is returnValueAsString" 
	                 
	             }
	         }
	         else if (requestCode == 2)
	         {
	        	 if (resultCode == Activity.RESULT_OK)
	        	 {
	        		 Bundle extras = pData.getExtras();
	        	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	        	        imageview2.setImageBitmap(imageBitmap);
	        	         
	        	 }
	         }
	         
	     
	}
	
}
