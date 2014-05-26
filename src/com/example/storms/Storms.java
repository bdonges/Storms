package com.example.storms;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Storms 
{

	private double lat_;
	private double lng_;
	public Vector<Storm> storms_;
	
	public Storms(double lat, double lng)
	{
		this.lat_ = lat;
		this.lng_ = lng;
		storms_ = new Vector<Storm>();
	}
	
	public void getNearby() throws Exception
	{
		Data data = new Data();
		Util util = new Util();

		String radiusMI = "25";
		
		// overriding lat and lng values to test
		//lat_ = 30.24;
		//lng_ = -93.01;
		
		//closest by lat / long (HTTPUrlConnection)
		data.setRawData(util.getWebURL("http://api.aerisapi.com/stormcells/closest?p="+lat_+","+lng_+"&radius="+radiusMI+"miles&limit=10&client_id=JWSvxLslupBqgTjYSNbN0&client_secret=skNxVBAap0InNuGmAjupcdyIdAxZMX6iFDVIRuis"));
		//System.out.println(data.getRawData());
		
		JSONParser parser=new JSONParser();
		
		Object obj = parser.parse(data.getRawData());
		JSONObject jsonObj = (JSONObject)obj;
		
		Hashtable<String,Temp> h = util.processJSONObject(jsonObj, util);
		Enumeration<String> e = h.keys();	

		while (e.hasMoreElements())
		{
			String key = e.nextElement();
			Temp tmp = h.get(key);
			if (key.equals("response")) 
			{
				data = processResponse(data, tmp, util);
			}
			else
			{
				data.put(tmp.getKey(), tmp.getValue());
			}	
		}

		if (data.get("success").toString().equalsIgnoreCase("true"))
		{
			storms_.clear();
			storms_ = (Vector<Storm>)data.get("STORMS");
			for (int i = 0; i < storms_.size(); i++)
			{
				Storm storm = storms_.get(i);
			}
		}
	}
	
	/**
	 * 
	 * @param data
	 * @param temp
	 * @param util
	 * @return
	 */
	private Data processResponse(Data data, Temp temp, Util util)
	{
		JSONArray jAry = (JSONArray)temp.getObject();
		Vector<Storm> v = new Vector<Storm>();
		
		for (Object o : jAry)
		{
			JSONObject jObj = (JSONObject)o;
			Hashtable<String,Temp> h = util.processJSONObject(jObj, util);
						
			JSONObject relativeTo = (JSONObject)h.get("relativeTo").getObject();
			JSONObject loc = (JSONObject)h.get("loc").getObject();
			JSONObject place = (JSONObject)h.get("place").getObject();
			JSONObject ob = (JSONObject)h.get("ob").getObject();
			JSONObject hail = (JSONObject)ob.get("hail");
			JSONObject movement = (JSONObject)ob.get("movement");
			
			Storm s = new Storm((String)h.get("id").getValue(), relativeTo, loc, place, ob, hail, movement);
			s.setDistanceTo(distanceBetweenPoints(lat_, lng_, s.getLatitude(), s.getLongitude()));
			v.add(s);
		}
		
		data.put("STORMS", v);
		return data;
	}
    
    private int distanceBetweenPoints(double userLat, double userLng, double stormLat, double stormLng)
    {
    	double latDistance = Math.toRadians(userLat - stormLat);
    	double lngDistance = Math.toRadians(userLng - stormLng);

    	double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
    	           (Math.cos(Math.toRadians(userLat))) *
    	           (Math.cos(Math.toRadians(stormLat))) *
    	           (Math.sin(lngDistance / 2)) *
    	           (Math.sin(lngDistance / 2));

    	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    	//6,371 kilometres (3,959 mi). 
    	int AVERAGE_RADIUS_OF_EARTH = 3959;
    	
    	return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));
    }
	
	private void showHash(Hashtable <String,Temp> h)
	{
		Enumeration<String> e = h.keys();
		while (e.hasMoreElements())
		{
			String k = e.nextElement();
			Temp t = h.get(k);
			System.out.println("    k: " + k + ", v: " + t.getClassName());
		}
	}
	
}
