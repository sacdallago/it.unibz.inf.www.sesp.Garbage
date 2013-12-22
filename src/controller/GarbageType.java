package controller;

import java.util.LinkedList;

public class GarbageType {
	protected String Type;
	protected String BinColour;
	protected LinkedList<String> GarbageElements;
	
	public String toString(){
		String result = "";
		result += "Type: " + Type;
		result += "\nBin Colour: " + BinColour;
		result += "\nItems: ";
		for(String a:GarbageElements){
			result += a + ", ";
		}
		return result;
	}
	
	
	/**
	 * Creates a new Type object.
	 * 
	 * @param Type The name of the type (plastic, paper,..)
	 * @param BinColour The color of the bin where this type of garbage gets disposed to.
	 */
	public GarbageType(String Type, String BinColour){
		Log.push("Creating new GarbageType: "+Type);
		this.Type = Type;
		this.BinColour = BinColour;
		GarbageElements = new LinkedList<String>();
	}
	
	/**
	 * Inserts an item to the item list, only if the item is not already there.
	 * 
	 * @param item The item to be added (eg. 'flowers', 'kinder fetta al latte packaging')
	 * @return 0 if successful
	 * @return -1 if the item is already in the list
	 */
	public int insertItem(String item){
		if(GarbageElements.contains(item)){
			return -1;
		} else {
			GarbageElements.add(item);
			return 0;
		}
	}
	
	/**
	 * Returns the type as a String
	 */
	public String getType(){
		return Type;
	}
	
	/**
	 * Returns the BinColour as a String
	 */
	public String getBinColor(){
		return BinColour;
	}
	
	/**
	 * Returns the list of items
	 */
	public LinkedList<String> getItems(){
		return GarbageElements;
	}
}
