package com.abdullah.onlineradio;


import com.abdullah.onlineradio.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class RadioActivity extends Activity implements OnClickListener {

	Button playme,stopme,exitme;
	private AlertDialog.Builder finishDialog;
	String URL,st="stopped",pl="please wait ...",in="note: it may take more seconds to buffer.Press menu for more information";
	String about ="Online Radio";
	String help = "Press play button and wait for few seconds.\nBuffering is depend on your Data Connection(internet speed).\n\n";
	MediaPlayer mp = new MediaPlayer();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		Toast.makeText(RadioActivity.this, " " + in,Toast.LENGTH_SHORT).show();
		
	
		// button play
		playme = (Button)findViewById(R.id.playme);
	    playme.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			 	
				Toast.makeText(RadioActivity.this, " " + pl,Toast.LENGTH_SHORT).show();
				
				URL = "http://server-23.stream-server.nl:8438";
				
				mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
				stopme.setEnabled(true);
		    	playme.setEnabled(false);
				
				// get data from internet ...
				
				try
				{
					mp.setDataSource(URL);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				// buffer...
				
				try
				{
					mp.prepare();
				}catch(Exception e){
					e.printStackTrace();
				}
				mp.start();
				
				}
			
		});
	    
	    // button stop
	    stopme = (Button)findViewById(R.id.stopme);
	    stopme.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mp.stop();
				 	
				Toast.makeText(RadioActivity.this, "  " + st,Toast.LENGTH_SHORT).show();
				
				playme.setEnabled(true);
				stopme.setEnabled(false);
			}
		});
	   	    
    }
	
	
	@Override
	public void onBackPressed() {
		finishDialog = new AlertDialog.Builder(this)
		.setTitle("Hey")
		.setMessage("Do you want to Close ?")
		.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						finish();
						System.exit(0);
					}
				})
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
finishDialog.show();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.layout.menu, menu);
	        return true;
	    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
{
  
  switch (item.getItemId())
  {
  case R.id.menu_about:
  	    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RadioActivity.this);
	            dlgAlert.setMessage(about);
	            dlgAlert.setTitle("About Us:");
	            dlgAlert.setPositiveButton("CLOSE", null);
	            dlgAlert.setCancelable(true);
	            dlgAlert.create().show();
  	  return true;
  	  
  case R.id.menu_help:
	    AlertDialog.Builder dlgAlert1 = new AlertDialog.Builder(RadioActivity.this);
	            dlgAlert1.setMessage(help);
	            dlgAlert1.setTitle("Help:");
	            dlgAlert1.setPositiveButton("CLOSE", null);
	            dlgAlert1.setCancelable(true);
	            dlgAlert1.create().show();
	  return true;
	  
  	  
  case R.id.menu_exit:
  	   Toast.makeText(RadioActivity.this, "Good Bye...", Toast.LENGTH_SHORT).show();
  	   finish();
  	  return true;
  default:
      return super.onOptionsItemSelected(item);
  }
}
	
}
