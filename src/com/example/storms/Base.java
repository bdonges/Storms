package com.example.storms;

import org.json.simple.JSONObject;

public class Base 
{
	
	/**
	 * gets an int value from the json object
	 * @param o
	 * @param key
	 * @return
	 */
	public int getIntValue(JSONObject o, String key)
	{
		try { return Integer.valueOf(o.get(key).toString()); } catch (Exception e) { return 0; }
	}
	
	/**
	 * gets a double value from the json object
	 * @param o
	 * @param key
	 * @return
	 */
	public double getDoubleValue(JSONObject o, String key)
	{
		try { return Double.parseDouble(o.get(key).toString()); } catch (Exception e) { return 0.00; }
	}
	
	/**
	 * gets a string value from the json object
	 * @param o
	 * @param key
	 * @return
	 */
	public String getStringValue(JSONObject o, String key)
	{
		try { return (String)o.get(key); } catch (Exception e) {return ""; }
	}
	
	/**
	 * 
	 * @param o
	 * @param key
	 * @return
	 */
	public long getLongValue(JSONObject o, String key)
	{
		try { return ((Number)o.get(key)).longValue(); } catch (Exception e) { return 0; }
	}
}
