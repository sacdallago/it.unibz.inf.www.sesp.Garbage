package testing;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.City;
import controller.GarbageType;

public class CityTester {
	private City city;
	private GarbageType garbagetype;

	@Before
	public void setUp() {
		//sets up city called "city" with cap "123"
		city = new City(123,"city");
		//sets up GarbageType called "garbagetype" with color "blue"
		garbagetype = new GarbageType("garbagetype", "blue");
	}
	
	@After
	public void tearDown() {
		city = null;
		garbagetype = null;
	}
	
	@Test
	public void testCap() {
		assertEquals(123,city.getCAP());
	}
	@Test
	public void testName() {
		assertEquals("city",city.getCityName());
	}
	@Test
	public void testGarbageTypeName() {
		assertEquals("garbagetype",garbagetype.getType());
	}
	@Test
	public void testGarbageTypeColor() {
		assertEquals("blue",garbagetype.getBinColor());
	}
	@Test
	public void testGarbageTypesInCityNotNull() {
		city.insertGarbageType(garbagetype);
		assertEquals(garbagetype,city.getListOfGarbage().getFirst());
	}
	@Test
	public void testGarbageTypesInCityIsNull() {
		assertEquals(null,city.getListOfGarbage());
	}
	@Test
	public void testItemGarbageTypesInCity() {
		garbagetype.insertItem("item");
		city.insertGarbageType(garbagetype);
		assertEquals(true,city.getListOfGarbage().getFirst());
		
	}
}
