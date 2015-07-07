package de.robv.android.xposed.mods.tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView mainInfoTextView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.activity_main);
	    
	    mainInfoTextView = (TextView) findViewById(R.id.main_basic_info_text_view);
	   
	    TelephonyManager tm = (TelephonyManager) getSystemService("phone");
	    
	    
	    
	    mainInfoTextView.setText(tm.getDeviceId());
	    
	}

}
