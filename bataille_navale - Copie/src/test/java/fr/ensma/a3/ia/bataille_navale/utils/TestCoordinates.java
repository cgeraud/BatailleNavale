package fr.ensma.a3.ia.bataille_navale.utils;

import mockit.*;
import mockit.integration.junit4.JMockit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class TestCoordinates {
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void test_constructor_0() {
		// Test de création normale
		Coordinates c = new Coordinates(0,0);
	}
	
	@Test
	public void test_getter() {
		Coordinates c = new Coordinates(5,0);
		Assert.assertEquals(c.getX(), 5);
	}
	
	@Test
	public void test_setter() {
		Coordinates c = new Coordinates(0,0);
		c.setX(5);
		Assert.assertEquals(c.getX(), 5);
	}
}
