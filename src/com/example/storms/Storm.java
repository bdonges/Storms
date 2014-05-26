package com.example.storms;

import org.json.simple.JSONObject;

public class Storm extends Base
{
	public Storm() {}
	
	public Storm(String id, JSONObject relativeTo, JSONObject loc, JSONObject place, JSONObject ob, JSONObject hail, JSONObject movement)
	{
		this.setId(id);
		
		// set loc variables
		this.setLatitude(getDoubleValue(loc, "lat"));
		this.setLongitude(getDoubleValue(loc, "long"));
		
		// set ob variables
		this.setRadarId(getStringValue(ob, "radarID"));
		this.setCellId(getStringValue(ob, "cellID"));
		this.setTvs(getLongValue(ob, "tvs"));
		this.setMda(getLongValue(ob, "mda"));
		this.setVil(getLongValue(ob, "vil"));
		this.setHt(getDoubleValue(ob, "ht"));
		this.setTop(getDoubleValue(ob, "top"));
		this.setTopFT(getLongValue(ob, "topFT"));
		this.setLocation(getStringValue(ob, "location"));
		this.setDateTimeISO(getStringValue(ob, "dateTimeISO"));
		
		// set ob hail variables
		this.setHailProbSevere(getLongValue(hail, "probSevere"));
		this.setHailProb(getLongValue(hail, "prob"));
		this.setMaxSizeIn(getDoubleValue(hail, "maxSizeIN"));
		this.setMaxSize(getDoubleValue(hail, "maxSize"));
		
		// set ob movement variables
		this.setDirDeg(getLongValue(movement, "dirDEG"));
		this.setDir(getStringValue(movement, "dir"));
		this.setDirToDeg(getLongValue(movement, "dirToDEG"));
		this.setDirTo(getStringValue(movement, "dirTo"));
		this.setSpeedKTS(getLongValue(movement, "speedKTS"));
		this.setSpeedMPH(getLongValue(movement, "speedMPH"));
		this.setSpeedKMH(getLongValue(movement, "speedKMH"));
		
		// set place variables
		this.setPlaceName(getStringValue(place, "name"));
		this.setPlaceState(getStringValue(place, "state"));
		this.setPlaceCountry(getStringValue(place, "country"));
		
		// set relativeTo variables
		this.setRelToLat(getDoubleValue(relativeTo, "lat"));
		this.setRelToLong(getDoubleValue(relativeTo, "long"));
		this.setRelToBearing(getIntValue(relativeTo, "bearing"));
		this.setRelToBearingENG(getStringValue(relativeTo,"bearingENG"));
		this.setRelToDistanceKM(getDoubleValue(relativeTo, "distanceKM"));
		this.setRelToDistanceMI(getDoubleValue(relativeTo, "distanceMI"));
	}
	
	// response variables
	private String id;
	
	// loc variables
	private double latitude;
	private double longitude;
	private int distanceTo;
	
	// ob variables
	private String radarId;
	private String cellId;
	private long tvs;
	private long mda;
	private long vil;
	private double ht;
	private double top;
	private long topFT;
	private String location;
	private String dateTimeISO;
	
	// ob.hail variables
	private long hailProbSevere;
	private long hailProb;
	private double maxSizeIn;
	private double maxSize;
	
	// ob.movement variables
	private long dirDeg;
	private String dir;
	private long dirToDeg;
	private String dirTo;
	private long speedKTS;
	private long speedMPH;
	private long speedKMH;
	
	// place variables
	private	String placeName;
	private String placeState;
	private String placeCountry;
	
	// relative to variables
	private double relToLat;
	private double relToLong;
	private int relToBearing;
	private String relToBearingENG;
	private double relToDistanceKM;
	private double relToDistanceMI;
	
	/*
	 * -----------------------------------------------
	 * getter's and setter's
	 * -----------------------------------------------
	 */
	
	public int getDistanceTo()
	{
		return distanceTo;
	}
	
	public void setDistanceTo(int distanceTo)
	{
		this.distanceTo = distanceTo;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public double getHt() {
		return ht;
	}

	public void setHt(double ht) {
		this.ht = ht;
	}

	public double getRelToLat() {
		return relToLat;
	}

	public void setRelToLat(double relToLat) {
		this.relToLat = relToLat;
	}

	public double getRelToLong() {
		return relToLong;
	}

	public void setRelToLong(double relToLong) {
		this.relToLong = relToLong;
	}

	public int getRelToBearing() {
		return relToBearing;
	}

	public void setRelToBearing(int relToBearing) {
		this.relToBearing = relToBearing;
	}

	public double getRelToDistanceKM() {
		return relToDistanceKM;
	}

	public void setRelToDistanceKM(double relToDistanceKM) {
		this.relToDistanceKM = relToDistanceKM;
	}

	public double getRelToDistanceMI() {
		return relToDistanceMI;
	}

	public void setRelToDistanceMI(double relToDistanceMI) {
		this.relToDistanceMI = relToDistanceMI;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getRelToBearingENG() {
		return relToBearingENG;
	}
	public void setRelToBearingENG(String relToBearingENG) {
		this.relToBearingENG = relToBearingENG;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRadarId() {
		return radarId;
	}
	public void setRadarId(String radarId) {
		this.radarId = radarId;
	}
	public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getDirTo() {
		if (dirTo == null || dirTo.equals(""))
			dirTo = "-";
		return dirTo;
	}
	public void setDirTo(String dirTo) {
		this.dirTo = dirTo;
	}

	public long getDirDeg() {
		return dirDeg;
	}

	public void setDirDeg(long dirDeg) {
		this.dirDeg = dirDeg;
	}

	public long getDirToDeg() {
		return dirToDeg;
	}

	public void setDirToDeg(long dirToDeg) {
		this.dirToDeg = dirToDeg;
	}

	public long getSpeedKTS() {
		return speedKTS;
	}

	public void setSpeedKTS(long speedKTS) {
		this.speedKTS = speedKTS;
	}

	public long getSpeedMPH() {
		return speedMPH;
	}

	public void setSpeedMPH(long speedMPH) {
		this.speedMPH = speedMPH;
	}

	public long getSpeedKMH() {
		return speedKMH;
	}

	public void setSpeedKMH(long speedKMH) {
		this.speedKMH = speedKMH;
	}

	public long getTvs() {
		return tvs;
	}

	public void setTvs(long tvs) {
		this.tvs = tvs;
	}

	public long getMda() {
		return mda;
	}

	public void setMda(long mda) {
		this.mda = mda;
	}

	public long getVil() {
		return vil;
	}

	public void setVil(long vil) {
		this.vil = vil;
	}

	public double getTop() {
		return top;
	}

	public void setTop(double top) {
		this.top = top;
	}

	public long getTopFT() {
		return topFT;
	}

	public void setTopFT(long topFT) {
		this.topFT = topFT;
	}

	public long getHailProbSevere() {
		return hailProbSevere;
	}

	public void setHailProbSevere(long hailProbSevere) {
		this.hailProbSevere = hailProbSevere;
	}

	public long getHailProb() {
		return hailProb;
	}

	public void setHailProb(long hailProb) {
		this.hailProb = hailProb;
	}

	public double getMaxSizeIn() {
		return maxSizeIn;
	}

	public void setMaxSizeIn(double maxSizeIn) {
		this.maxSizeIn = maxSizeIn;
	}

	public double getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(double maxSize) {
		this.maxSize = maxSize;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDateTimeISO() {
		return dateTimeISO;
	}
	public void setDateTimeISO(String dateTimeISO) {
		this.dateTimeISO = dateTimeISO;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceState() {
		return placeState;
	}
	public void setPlaceState(String placeState) {
		this.placeState = placeState;
	}
	public String getPlaceCountry() {
		return placeCountry;
	}
	public void setPlaceCountry(String placeCountry) {
		this.placeCountry = placeCountry;
	}
	
	/*
	 * other methods
	 */
	public void show()
	{
		System.out.println(" ");
		System.out.println("----------------------------------");
		System.out.println("  storm.show()");
		System.out.println("      id:          "+this.getId());
		System.out.println("      loc values");
		System.out.println("      lat:         "+this.getLatitude());
		System.out.println("      long:        "+this.getLongitude());
		System.out.println("      ob values");
		System.out.println("      radarID:     "+this.getRadarId());
		System.out.println("      cellID:      "+this.getCellId());
		System.out.println("      tvs:         "+this.getTvs());
		System.out.println("      mda:         "+this.getMda());
		System.out.println("      vil:         "+this.getVil());
		System.out.println("      ht:          "+this.getHt());
		System.out.println("      top:         "+this.getTop());
		System.out.println("      ft:          "+this.getTopFT());
		System.out.println("      location:    "+this.getLocation());
		System.out.println("      dateTimeISO: "+this.getDateTimeISO());
		System.out.println("      ob.hail values");
		System.out.println("      probSevere:  "+this.getHailProbSevere());
		System.out.println("      prob:        "+this.getHailProb());
		System.out.println("      maxSizeIN:   "+this.getMaxSizeIn());
		System.out.println("      maxSize:     "+this.getMaxSize());
		System.out.println("      ob.movement values");
		System.out.println("      dirDEG:      "+this.getDirDeg());
		System.out.println("      dir:         "+this.getDir());
		System.out.println("      dirToDEG:    "+this.getDirToDeg());
		System.out.println("      dirTo:       "+this.getDirTo());
		System.out.println("      speedKTS:    "+this.getSpeedKTS());
		System.out.println("      speedMPH:    "+this.getSpeedMPH());
		System.out.println("      speedKMH:    "+this.getSpeedKMH());
		System.out.println("      place values");
		System.out.println("      name:        "+this.getPlaceName());
		System.out.println("      state:       "+this.getPlaceState());
		System.out.println("      country:     "+this.getPlaceCountry());
		System.out.println("      relativeTo values");
		System.out.println("      lat:         "+this.getRelToLat());
		System.out.println("      long:        "+this.getRelToLong());
		System.out.println("      bearing:     "+this.getRelToBearing());
		System.out.println("      bearingENG:  "+this.getRelToBearingENG());
		System.out.println("      distanceKM:  "+this.getRelToDistanceKM());
		System.out.println("      distanceMI:  "+this.getRelToDistanceMI());
		System.out.println("----------------------------------");
		System.out.println(" ");
		
	}
}
