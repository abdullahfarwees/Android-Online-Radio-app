package unikappsfactroy.sampleradio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button playme,stopme,exitme;
	private AlertDialog.Builder finishDialog;
	String URL,st="stopped",pl="please wait ...",in="note: it may take more seconds to buffer...";
	
	MediaPlayer mp = new MediaPlayer();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(MainActivity.this, " " + in,Toast.LENGTH_SHORT).show();
		
	
		// button play
		playme = (Button)findViewById(R.id.playme);
	    playme.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			 	
				Toast.makeText(MainActivity.this, " " + pl,Toast.LENGTH_SHORT).show();
				
				URL = "http://www.quran-radio.org:8000";
				
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
				 	
				Toast.makeText(MainActivity.this, "  " + st,Toast.LENGTH_SHORT).show();
				
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
	
}
