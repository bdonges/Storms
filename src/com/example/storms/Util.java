package com.example.storms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.simple.JSONObject;

public class Util 
{

	public String getWebURLAndroid(String urlStr) 
	{
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(urlStr);
		String text = null;
		try 
		{
			
			HttpResponse response = httpClient.execute(httpGet, localContext);
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			StringBuffer out = new StringBuffer();
			int n = 1;
			while (n>0) {
				byte[] b = new byte[4096];
				n =  in.read(b);
				if (n>0) out.append(new String(b, 0, n));
			}
			
			text = out.toString();
		} 
		catch (Exception e) 
		{
			return e.getLocalizedMessage();
		}
		return text;
	}
	
	/**
	 * connects to a url and creates a return string
	 * @param urlStr
	 * @return
	 * @throws Exception
	 */
	public String getWebURL(String urlStr) throws Exception 
	{
		StringBuilder sb = new StringBuilder();
		int responseCode = 0;
		try
		{
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
			responseCode = conn.getResponseCode();
			if (conn.getResponseCode() != 200) {
			    throw new IOException(conn.getResponseMessage());
			}
	
			// Buffer the result into a string
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
	
			conn.disconnect();
		} catch (Exception e) {
			throw new Exception("getWebUrl() - exception message: " + e.getMessage() + ", response code: " + responseCode);
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param o
	 * @param key
	 * @return
	 */
	public String getClassName(JSONObject o, String key)
	{
		try { return o.get(key).getClass().toString(); } catch (Exception e) { return "null"; }
	}
	
	/**
	 * 
	 * @param o
	 * @param key
	 * @return
	 */
	public Object getObject(JSONObject o, String key)
	{
		try { return o.get(key); } catch (Exception e) { return "null"; }
	}
	
	/**
	 * 
	 * @param o
	 * @param key
	 * @return
	 */
	public String getObjectAsString(JSONObject o, String key)
	{
		try { return o.get(key).toString(); } catch (Exception e) { return "null"; }
	}

	/**
	 * 
	 * @param jObj
	 * @param util
	 * @return
	 */
	public Hashtable <String,Temp> processJSONObject(JSONObject jObj, Util util)
	{
		Hashtable<String,Temp> hash = new Hashtable<String, Temp>();
		Set<String> set1 = jObj.keySet();
		for (Object keyObj : set1) 
		{
			Temp t1 = generateTemp(jObj, util, keyObj);
			hash.put(t1.getKey(), t1);
		}
		return hash;
	}
	
	/**
	 * 
	 * @param jObj
	 * @param util
	 * @param keyObj
	 * @return
	 */
	public Temp generateTemp(JSONObject jObj, Util util, Object keyObj)
	{
		String cName = util.getClassName(jObj, keyObj.toString());
		Object obj = util.getObject(jObj, keyObj.toString());
		String value = util.getObjectAsString(jObj, keyObj.toString());
		return new Temp(keyObj.toString(), value, cName, obj);
	}
}
