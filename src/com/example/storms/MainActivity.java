package com.example.storms;

import java.util.Vector;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static String logtag = "android_app";
	private TableLayout storm_table;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button buttonStart = (Button)findViewById(R.id.button1);
        buttonStart.setOnClickListener(startListener);

        storm_table = (TableLayout)findViewById(R.id.myTableLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    protected void onStart() {
    	super.onStart();
    }
    
    protected void onResume() {
    	super.onResume();
    }
    
    protected void onPause() {
    	super.onPause();
    }
    
    protected void onStop() {
    	super.onStop();
    }
    
    protected void onDestroy() {
    	super.onDestroy();
    }
    
    private Vector<Storm> storms_;
    
    private void updateStormGrid() 
    {

    	int rotatingCnt = 0;
    	int hailCnt = 0;
     	
    	storm_table.removeAllViews();
    	//Log.d(logtag, " ");
    	//Log.d(logtag, "updateStormGrid");
    	//Log.d(logtag, "number of storms found: " + storms_.size());
    	if (storms_.size() > 0) 
    	{
    		
    		// summary row
    		TableRow frow = new TableRow(this);   		
    		TextView frt2 = new TextView(this);
    		frt2.setText("Total storms found: " + storms_.size());
    		frt2.setTypeface(null,1);
    		frt2.setTextSize(12);
    		frow.addView(frt2);
    		storm_table.addView(frow, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    		
    		// empty row
    		storm_table.addView(createEmptyRow(), new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    		
	    	for (int i = 0; i < storms_.size(); i++) 
	    	{
	    		
	    		Storm storm = (Storm)storms_.get(i);
	    		
	    		// create main row
	    		TableRow r1 = new TableRow(this);
	    		TextView r1t2 = new TextView(this);
	    		r1t2.setText(i+1 + ". "+ storm.getLocation() + " heading " + storm.getDirTo() + " at " + storm.getSpeedMPH() +" MPH ("+storm.getDistanceTo()+" m away)");
	    		r1t2.setTypeface(null,1);
	    		r1t2.setTextSize(12);
	    		r1.addView(r1t2);
	    		storm_table.addView(r1, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

	    		// if rotation, add here
	    		if (storm.getMda() > 0) 
	    		{
		        	TableRow r2 = new TableRow(this);
		        	TextView r2t2 = new TextView(this);
		    		r2t2.setText("    Possible Rotation, mda is " + storm.getMda());
		    		r2t2.setTypeface(null,1);
		    		r2t2.setTextSize(12);
		    		r2.addView(r2t2);
		    		storm_table.addView(r2, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		    		rotatingCnt++;
	    		}
	    		
	    		// if rotation, add here
	    		if (storm.getHailProb() > 0 || storm.getHailProbSevere() > 0) 
	    		{
		        	TableRow r3 = new TableRow(this);
		        	TextView r3t2 = new TextView(this);
		    		r3t2.setText("     Possible Hail.  Chance Severe: " + storm.getHailProbSevere() +"%, Chance: " + storm.getHailProb() +"%");
		    		r3t2.setTypeface(null,1);
		    		r3t2.setTextSize(12);
		    		r3.addView(r3t2);
		    		storm_table.addView(r3, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		    		hailCnt++;
	    		}
	    		
	    		// empty row
	    		storm_table.addView(createEmptyRow(), new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    	}
	    	
    	} 
    	else 
    	{
    		TableRow r1 = new TableRow(this);
    		TextView r1t2 = new TextView(this);
    		r1t2.setText("No Storms Nearby");
    		r1t2.setTypeface(null,1);
    		r1t2.setTextSize(12);
    		r1.addView(r1t2);
    		storm_table.addView(r1, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    	}
    }
    
    private TableRow createEmptyRow()
    {
    	// empty row
		TableRow r = new TableRow(this);
		TextView v = new TextView(this);
		v.setText(" ");
		v.setTypeface(null,1);
		v.setTextSize(12);
		r.addView(v);
		return r;
    }
    
    public boolean isNetworkAvailable() 
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        // print info
        //Log.d(logtag, "  wifi - connected:      " + wifiInfo.isConnected() + ", available: " + wifiInfo.isAvailable());
        //Log.d(logtag, "  mobile - connected:    " + mobileInfo.isConnected() + ", available: " + mobileInfo.isAvailable());
        //Log.d(logtag, "  network - connected:   " + networkInfo.isConnected() + ", available: " + networkInfo.isAvailable());
        
        // if no network is available networkInfo will be null otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) 
        {
            return true;
        }
        return false;
    } 
    
    private void updateWithNewLocation() 
    {
    	
    	LocationManager lmgr = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	lmgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, locationListener);
    	Location location = lmgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    	TextView myLocationText = (TextView)findViewById(R.id.textView2);
    	String latLongString = "";
    	storms_ = new Vector<Storm>();
    	storms_.clear();
    	
    	if (location != null) 
    	{
    		
    		double lat = location.getLatitude();
    		double lng = location.getLongitude();
    		latLongString = "lat: " + Double.toString(lat).substring(0, 5) + ", long: " + Double.toString(lng).	substring(0,5);
    		
    		//latLongString = "lat: 34.67, long: -81.78";
    		//lat = 34.67;
    		//lng = -81.78;
    		
        	Storms s = new Storms(lat,lng);
        	try 
        	{
        		
        		if (isNetworkAvailable()) 
        		{
	        		s.getNearby();
	        		storms_ = s.storms_;
	        		int numOfStorms = 0;
	        		
	        		if (storms_.size() > 0) 
	        		{
	        			numOfStorms = storms_.size();
	        		}
	        		
	       		} 
        		else 
	       		{
	        			Log.d(logtag, "network connection not available");
	        	}
        	} 
        	catch (Exception e) 
        	{
        		Log.d(logtag,"exception - message: " + e.getMessage());
        		System.out.println("exception - message: " + e.getMessage());
        		e.printStackTrace();
        	}
    		
    	} 
    	else 
    	{
    		latLongString = "no location found";
    	} 	
    	
    	myLocationText.setText("Current Position:\n" + latLongString);
    }
    
    private OnClickListener startListener = new OnClickListener() 
    {
    	public void onClick(View v) 
    	{
    		Log.d(logtag,"onCLick() called - get storms button");
    		updateWithNewLocation();
    		updateStormGrid();
    		Log.d(logtag,"onClick() ended - get storms button");
    	}
    };

    private final LocationListener locationListener = new LocationListener() 
    {
    	
    	public void onLocationChanged(Location location) 
    	{
    		//updateWithNewLocation();
    	}
    	
    	public void onProviderDisabled(String provider) 
    	{
    		//updateWithNewLocation();
    	}
    	
    	public void onProviderEnabled(String provider) 
    	{
    	}
    	
    	public void onStatusChanged(String provider, int status, Bundle extras) 
    	{
    	}
    	
    };
    
}
