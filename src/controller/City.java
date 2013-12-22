package controller;

/*
 * City Class
 * 
 * Version 0.1
 * 
 * Created 13/11/2013
 * 
 * copyright: This class and the following code is under copyright of the FUB and meant for internal use only.
 */

import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Set;


public class City {
	protected Integer CAP;
	protected String CityName;
	protected Hashtable<String ,GarbageType> garbage;
	
	/**
	 * Creates a new City object.
	 * 
	 * @param cap The ZIP/CAP of the city (usually a 5 numbers digit).
	 * @param cityName The name of the city.
	 */
	public City(Integer cap, String cityName){
		cityName=Utility.purifyString(cityName);
		Log.push("Creating new city: "+cityName+", "+cap);
		this.CAP = cap;
		this.CityName = cityName;
		garbage = new Hashtable<String ,GarbageType>();
	}
	
	/**
	 * Returns the CAP as primitive int type
	 * 
	 * @param cap The ZIP/CAP of the city (usually a 5 numbers digit).
	 * @param cityName The name of the city.
	 */
	public int getCAP(){
		return CAP.intValue();
	}
	
	/**
	 * Returns a list of garbage types defined for the city or null if none is defined
	 */
	public LinkedList<GarbageType> getListOfGarbage() {
		Set<String> keys = garbage.keySet();
		if (!keys.isEmpty()) {
			LinkedList<GarbageType> result = new LinkedList<GarbageType>();
			for (String key : keys) {
				result.add(garbage.get(key));
			}
			return result;
		} else
			return null;
	}
	
	/**
	 * Returns the city name as a string
	 */
	public String getCityName(){
		return CityName;
	}
	
	public String toString(){
		String result = "";
		result += "CAP: " + CAP;
		result += "\nCity Name: " + CityName;
		result += "\n";
		Set<String> keys = garbage.keySet();
		for(String key: keys){
			result += garbage.get(key) + "\n";
		}
		return result;
	}
	
	/**
	 * Creates a new Type (a "super-class" of garbage, like Paper, Plastic,..)
	 * 
	 * @param Type The name of the type (plastic, paper,..)
	 * @param BinColour The color of the bin where this type of garbage gets disposed to.
	 * 
	 * @return 0 if successful
	 * @return -1 if the type is already in the table
	 */
	public int createType(String Type, String BinColour){
		Type = Utility.purifyString(Type);
		BinColour = Utility.purifyString(BinColour);
		if(garbage.containsKey(Type)){
			return -1;
		} else {
			garbage.put(Type, new GarbageType(Type,BinColour));
			return 0;
		}
	}
	
	
	/**
	 * Inserts item in correct Type, if Type exists.
	 * 
	 * @param Type The name of the type (plastic, paper,..)
	 * @param cityName The name of the city.
	 * 
	 * @return 0 if successful
	 * @return -1 if the type is not in the table
	 */
	public int insertItem(String Type, String item){
		Type = Utility.purifyString(Type);
		item = Utility.purifyString(item);
		if(garbage.containsKey(Type)){
			garbage.get(Type).insertItem(item);
			return 0;
		} else {
			return -1;
		}
	}
	
	/**
	 * Changes the color of bin, if Type exists.
	 * 
	 * @param Type The name of the type (plastic, paper,..)
	 * @param BinColour The color of the bin where this type of garbage gets disposed to.
	 * 
	 * @return 0 if successful
	 * @return -1 if the type is not in the table
	 */
	public int changeColour(String Type, String BinColour){
		Type = Utility.purifyString(Type);
		BinColour = Utility.purifyString(BinColour);
		if(garbage.containsKey(Type)){
			garbage.get(Type).BinColour=BinColour;
			return 0;
		} else {
			return -1;
		}
	}
	
	/**
	 * Directly push a GarbageType object in the city
	 * 
	 * @param newType The GarbageType object
	 * 
	 * @return 0 if successful
	 * @return -1 if the type is not in the table
	 */
	public int insertGarbageType(GarbageType newType){
		if(!garbage.containsKey(newType.Type)){
			garbage.put(newType.Type, newType);
			return 0;
		} else {
			return -1;
		}
	}
	
	/**
	 * Changes the type name;
	 * 
	 * @param Type The name of the type (plastic, paper,..)
	 * @param NewType The color of the bin where this type of garbage gets disposed to.
	 * 
	 * @return 0 if successful
	 * @return -1 if the newType is already defined
	 * @return -2 if the type is not in the table
	 */
	public int changeType(String Type, String NewType){
		Type = Utility.purifyString(Type);
		NewType = Utility.purifyString(NewType);
		if(garbage.containsKey(NewType)){
			return -1;
		} else if (garbage.containsKey(Type)){
			garbage.get(Type).Type=NewType;
			garbage.put(NewType, garbage.get(Type));
			garbage.remove(Type);
			return 0;
		} else {
			return -2;
		}
	}
	
	/**
	 * Get garbage items
	 * 
	 * @param Type The name of the type (plastic, paper,..)
	 * 
	 * @return list if successful
	 * @return null otherwise
	 */
	public LinkedList<String> getItemList(String Type){
		if(garbage.containsKey(Type)){
			return garbage.get(Type).GarbageElements;
		} else {
			return null;
		}
	}
	
	/**
	 * Get bin color
	 * 
	 * @param Type The name of the type (plastic, paper,..)
	 * 
	 * @return String if successful
	 * @return null otherwise
	 */
	public String getColor(String Type){
		if(garbage.containsKey(Type)){
			return garbage.get(Type).BinColour;
		} else {
			return null;
		}
	}
}
