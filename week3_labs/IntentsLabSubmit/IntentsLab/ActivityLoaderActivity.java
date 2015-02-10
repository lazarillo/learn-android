package course.labs.intentslab;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ActivityLoaderActivity extends Activity {
    
	static private final int GET_TEXT_REQUEST_CODE = 1;
	static private final String URL = "http://www.google.com";
	static private final String TAG = "Lab-Intents";
    
	// For use with app chooser
	static private final String CHOOSER_TEXT = "Load " + URL + " with:";
    
	// TextView that displays user-entered text from ExplicitlyLoadedActivity runs
	private TextView mUserTextView;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader_activity);
		
		// Get reference to the textView
		mUserTextView = (TextView) findViewById(R.id.textView1);
        
		// Declare and setup Explicit Activation button
		Button explicitActivationButton = (Button) findViewById(R.id.explicit_activation_button);
		explicitActivationButton.setOnClickListener(new OnClickListener() {
            
			// Call startExplicitActivation() when pressed
			@Override
			public void onClick(View v) {
				
				startExplicitActivation();
                
			}
		});
        
		// Declare and setup Implicit Activation button
		Button implicitActivationButton = (Button) findViewById(R.id.implicit_activation_button);
		implicitActivationButton.setOnClickListener(new OnClickListener() {
            
			// Call startImplicitActivation() when pressed
			@Override
			public void onClick(View v) {
                
				startImplicitActivation();
                
			}
		});
        
	}
    
	
	// Start the ExplicitlyLoadedActivity
	
	private void startExplicitActivation() {
        
		Log.i(TAG,"Entered startExplicitActivation()");
		
		// TODO - Create a new intent to launch the ExplicitlyLoadedActivity class
		Intent explicitIntent = new Intent(ActivityLoaderActivity.this,
                ExplicitlyLoadedActivity.class);
		
		// TODO - Start an Activity using that intent and the request code defined above
        startActivityForResult(explicitIntent, GET_TEXT_REQUEST_CODE);
		
        
        
	}
    
	// Start a Browser Activity to view a web page or its URL

//    3) In ActivityLoaderActivity, implement launching an unknown Activity to view a URL.
//    a) When the “Implicit Activation” Button is clicked you should create an implicit Intent
//    indicating that you want to a view a URL. The application should use an app chooser to display
//    Activities that are capable of viewing URLs.
//    See http://developer.android.com/training/basics/intents/sending.html for more information.

	private void startImplicitActivation() {
        
		Log.i(TAG, "Entered startImplicitActivation()");
        
		// TODO - Create a base intent for viewing a URL
        Uri webpage = Uri.parse("http://www.android.com"); // I added this, have to fix it


        // (HINT:  second parameter uses Uri.parse())
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage); // I added this, have to fix it

//        Intent baseIntent = null;
		
		// TODO - Create a chooser intent, for choosing which Activity
		// will carry out the baseIntent
		// (HINT: Use the Intent class' createChooser() method)
//		Intent chooserIntent = null;
        
        
		Log.i(TAG,"Chooser Intent Action:" + webIntent.getAction());
        
        
		// TODO - Start the chooser Activity, using the chooser intent
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(webIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe) {
            startActivity(webIntent);
        }
        
	}
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        
		Log.i(TAG, "Entered onActivityResult()");
		
		// TODO - Process the result only if this method received both a
		// RESULT_OK result code and a recognized request code
		// If so, update the Textview showing the user-entered text.
        if ((requestCode == GET_TEXT_REQUEST_CODE) &&
                (resultCode == RESULT_OK)) {
            TextView textView1 = (TextView) findViewById(R.id.textView1);
            String returnString =
                    data.getExtras().getString("returnString");
            textView1.setText(returnString);
        }



    }
}
