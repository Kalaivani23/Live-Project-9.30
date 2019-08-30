package com.stepDefinition;

import com.resourses.BaseClass;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseClass {

	@Before
	public void launch() {
		launchBrowser("https://adactin.com/HotelApp/");
	}

	@After
	public void close() {
		closeBrowser();
	}
}
