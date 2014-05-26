package com.example.storms;

import java.util.Enumeration;
import java.util.Hashtable;

public class Data 
{

	public Data() 
	{
		kv = new Hashtable<String, Object>();
	}
	
	private Hashtable<String, Object> kv;
	private String rawData;
	
	public void put(String key, Object value) { kv.put(key, value); }
	public void setRawData(String s) { this.rawData = s; }
	
	public Object get(String key) { return kv.get(key); }
	public String getRawData() { return this.rawData; }
	
	/**
	 * shows all data in the kv hash
	 */
	public void show()
	{
		Enumeration<String> e = kv.keys();
		while (e.hasMoreElements())
		{
			String key = e.nextElement();
			String val = (String)kv.get(key);
			System.out.println("key: "+key+", val: "+val);
		}
	}
	
}
