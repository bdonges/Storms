package com.example.storms;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Radar 
{

	public static void main(String[] args)
	{
		try
		{
			Radar radar = new Radar();
			radar.get();
		}
		catch (Exception e)
		{
			System.err.println("exception - message: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private String timestamp_;
	private String time_;
	private int number_;
	
	public void get() throws Exception
	{
		Util util = new Util();
		String rawFileData = util.getWebURL("http://tile.aerisapi.com/JWSvxLslupBqgTjYSNbN0_skNxVBAap0InNuGmAjupcdyIdAxZMX6iFDVIRuis/radar.json");
		System.out.println("rawData: " + rawFileData);
		
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(rawFileData);
		JSONObject jsonObj = (JSONObject)obj;
		
		Hashtable<String,Temp> h = util.processJSONObject(jsonObj, util);
		Enumeration<String> e = h.keys();
		
		// step 1 - get time and timestamp values for first radar number
		while (e.hasMoreElements())
		{
			String key = e.nextElement();
			Temp tmp = h.get(key);
			processResponse(tmp, util);
		}
		
		// step 1 - get radar image
		System.out.println("http://tile1.aerisapi.com/JWSvxLslupBqgTjYSNbN0_skNxVBAap0InNuGmAjupcdyIdAxZMX6iFDVIRuis/radar/8/41/23/"+time_+".png");
		
	}
	
	/**
	 * returns timestamp and time values for first radar number (number 0)
	 * @param temp
	 * @param util
	 */
	private void processResponse(Temp temp, Util util)
	{
		JSONArray jAry = (JSONArray)temp.getObject();
		
		for (Object o : jAry)
		{
			JSONObject jObj = (JSONObject)o;
			
			if (Integer.parseInt(jObj.get("number").toString()) == 0) 
			{
				number_ = 0;
				timestamp_ = jObj.get("timestamp").toString();
				time_ = jObj.get("time").toString();
			}
		}
	}
		
}
