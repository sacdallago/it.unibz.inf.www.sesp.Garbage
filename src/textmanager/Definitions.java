package textmanager;

import controller.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Definitions {
	protected Document xmlDocument;
	protected List<Element> elements;

	/**
	 * Creates a Definition object that loads the definitions from the default location.
	 * This objects handles the translation from Java classes to XML file and the other way around.
	 */
	public Definitions() {
		loadXML("http://uni.christiandallago.com/XML/Garbage.xml");
		elements = xmlDocument.getRootElement().elements();
	}

	/**
	 * Creates a Definition object that loads the definitions from the given location.
	 * This objects handles the translation from Java classes to XML file and the other way around.
	 * 
	 * @param xmlpath String representing the URL of the XML file.
	 */
	public Definitions(String xmlpath) {
		loadXML(xmlpath);
		elements = xmlDocument.getRootElement().elements();
	}

	/**
	 * Internal method to load the XML file onto memory.
	 * 
	 * @param urlString String representing the URL of the XML file.
	 */
	private void loadXML(String urlString) {
		SAXReader reader = new SAXReader();
		try {
			URL url = new URL(urlString);
			xmlDocument = reader.read(url);
		} catch (MalformedURLException e) {
			System.err.println("ERROR 2101 - Wrong inserted URL format.");
		} catch (DocumentException e) {
			System.err.println("ERROR 2102 - Error while reading the document");
		}

		Log.push("Succesfully read XML: " + urlString);
	}

	/**
	 * Method to get a City in the XML constructed as City Object.
	 * 
	 * @param findCap Integer representing the CAP of the city of interest.
	 * @return Will be null if there's no city corresponding to a given cap.
	 */
	public City getCity(Integer findCap) {
		Log.push("Searching for city with CAP: " + findCap);
		for (Element e : elements) {
			Integer cap = Integer.parseInt(((Element) e.elementIterator()
					.next()).getData().toString());
			if (cap.equals(findCap)) {
				Log.push("Search successfull");
				return transformXMLToCity(e);
			}
		}
		Log.push("Search unsuccessfull");
		return null;
	}

	/**
	 * Method to check if a city is contained in the definitions.
	 * 
	 * @param findCap Integer representing the CAP of the city of interest.
	 * @return boolean. True if city is in definitions, false otherwise.
	 */
	public boolean contains(Integer findCap) {
		for (Element e : elements) {
			Integer cap = Integer.parseInt(((Element) e.elementIterator()
					.next()).getData().toString());
			if (cap.equals(findCap)) {
				Log.push("City "+ findCap.toString() +"is contained in definitions");
				return true;
			}
		}
		Log.push("City "+ findCap.toString() +"is not contained in definitions");
		return false;
	}

	/**
	 * Method to remove a city from the definitions based on the cap.
	 * 
	 * @param findCap Integer representing the CAP of the city of interest.
	 * @return boolean. True if city is in definitions and has been removed, false otherwise.
	 */
	public boolean removeCity(Integer findCap) {
		Log.push("Search and remove city: " + findCap);
		int position = 0;
		if (xmlDocument.hasContent()) {
			for (Element e : elements) {
				Integer cap = Integer.parseInt(((Element) e.elementIterator()
						.next()).getData().toString());
				if (cap.equals(findCap)) {
					elements.remove(position);
					Log.push("City removed");
					return true;
				}
				position++;
			}
			Log.push("No city to remove");
			return false;
		} else {
			Log.push("No city to remove");
			return false;
		}
	}

	/**
	 * Method to remove a city from the definitions based on a City object.
	 * 
	 * @param city The city of interest.
	 * @return boolean. True if city is in definitions and has been removed, false otherwise.
	 */
	public boolean removeCity(City city) {
		return removeCity(city.getCAP());
	}

	/**
	 * Method to add a city to the definitions in an unordered way.
	 * 
	 * @param city The city of interest.
	 */
	public void addCityUnordered(City a) {
		Log.push("Add city: " + a.getCAP());
		removeCity(a);
		elements.add(transformCityToXML(a));
	}
	
	/**
	 * Method to add a city to the definitions in an ordered way (based on ascending CAP).
	 * 
	 * @param city The city of interest.
	 */
	public void addCity(City a) {
		Log.push("Add city: " + a.getCAP());
		int position = 0;
		for(Element e : elements){
			Integer cap = Integer.parseInt(((Element) e.elementIterator()
					.next()).getData().toString());
			if(cap.intValue() == a.getCAP()){
				elements.remove(position);
				break;
			} else if(cap.intValue() > a.getCAP()){
				break;
			}
			position++;
		}
		elements.add(position, transformCityToXML(a));
	}
	

	/**
	 * Internal method to transform an XML element into a City object.
	 * 
	 * @param e The element of interest.
	 * @return A City object representing the element in the XML.
	 */
	private City transformXMLToCity(Element e) {
		Iterator<Element> j = e.elementIterator();
		Integer cap = Integer.parseInt(j.next().getData().toString());
		String cityName = j.next().getData().toString();
		City city = new City(cap, cityName);
		for (Iterator<Element> garbageType = j.next().elementIterator(); garbageType
				.hasNext();) {
			for (Iterator<Element> garbage = garbageType.next()
					.elementIterator(); garbage.hasNext();) {
				String type = garbage.next().getData().toString();
				String color = garbage.next().getData().toString();
				GarbageType newType = new GarbageType(type, color);
				for (Iterator<Element> item = garbage.next().elementIterator(); item
						.hasNext();) {
					newType.insertItem(item.next().getData().toString());
				}
				city.insertGarbageType(newType);
			}
		}
		return city;
	}

	/**
	 * Internal method to transform a City object into an XML element.
	 * 
	 * @param a The city of interest.
	 * @return An Element object to be added to the definitions or handled as needed.
	 */
	protected Element transformCityToXML(City a) {
		Element city = DocumentHelper.createElement("Obj");

		// -----First depth level
		Element cap = city.addElement("CAP");
		Element cityName = city.addElement("CityName");
		Element garbageTypes = city.addElement("GarbageTypes");

		cap.setText(Integer.toString(a.getCAP()));
		cityName.setText(a.getCityName());

		for (GarbageType l : a.getListOfGarbage()) {
			// -----Second depth level
			Element garbage = garbageTypes.addElement("Garbage");

			Element type = garbage.addElement("Type");
			Element binColour = garbage.addElement("BinColour");
			Element garbageElements = garbage.addElement("GarbageElements");

			type.setText(l.getType());
			binColour.setText(l.getBinColor());

			for (String i : l.getItems()) {
				// -----Third depth level
				Element item = garbageElements.addElement("Item");
				item.setText(i);
			}
		}
		return city;
	}

	/**
	 * Method to create a temporary XML File representing the definitions. This file can then be
	 * uploaded or handled as needed.
	 * 
	 * @return A File object containing a formatted print of the XML.
	 */
	public File writeXML() {
		File temp = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer;
		
		try {
			temp = File.createTempFile("newXML", ".xml");
			FileWriter out = new FileWriter(temp.getAbsoluteFile());
			writer = new XMLWriter(out,format);
			writer.write(xmlDocument);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	/**
	 * Method to create a list of tuples: (CityName, Cap)
	 * 
	 * @return A linked list of Strings containing the tuples, or null if no definitions exist
	 */
	public LinkedList<String> listOfCities() {
		LinkedList<String> result  = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer;
		
		if (xmlDocument.hasContent()) {
			result = new LinkedList<String>();
			for (Element e : elements) {
				Iterator<Element> j = e.elementIterator();
				Integer cap = Integer.parseInt(j.next().getData().toString());
				String cityName = j.next().getData().toString();
				result.add(cityName + ", " + cap.toString());
			}return result;
		} else {
			return result;
		}
	}
}
