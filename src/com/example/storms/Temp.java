package com.example.storms;

public class Temp 
{

	/**
	 * empty constructor
	 */
	public Temp() {}
	
	/**
	 * loaded constructor
	 * @param key
	 * @param value
	 * @param className
	 * @param object
	 */
	public Temp(String key, String value, String className, Object object)
	{
		this.setKey(key);
		this.setValue(value);
		this.setClassName(className);
		this.setObject(object);
	}
	
	/*
	 * private variables
	 */
	private String key;
	private String value;
	private String className;
	private Object object;
	
	/*
	 * getter's and setter's
	 */
	public String getKey() {
		if (key == null)
			key = "null";
		return key;
	}
	
	public void setKey(String key) {
		if (key == null)
			key = "null";
		this.key = key;
	}
	
	public String getValue() {
		if (value == null)
			value = "null";
		return value;
	}
	
	public void setValue(String value) {
		if (value == null)
			value = "null";
		this.value = value;
	}
	
	public String getClassName() {
		if (className == null)
			className = "null";
		return className;
	}
	
	public void setClassName(String className) {
		if (className == null)
			className = "null";
		this.className = className;
	}
	
	public Object getObject() {
		if (object == null)
			object = "null";
		return object;
	}
	
	public void setObject(Object object) {
		if (object == null)
			object = "null";
		this.object = object;
	}
	
	/*
	 * additional methods
	 */
	
	public void show() {
		System.out.println(" ");
		System.out.println("temp.show()");
		System.out.println("    key:       "+this.getKey());
		System.out.println("    value:     "+this.getValue());
		System.out.println("    classname: "+this.getClassName());
		System.out.println("    object:    "+this.getObject());
		System.out.println(" ");
	}
}
