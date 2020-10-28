package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;

public class BaseTest {
	
	public Properties prop;
	WebDriver driver;
	public BasePage basePage;
	public HomePage homePage;
	public LoginPage loginPage;
	public ContactsPage contactsPage;
	
	@Parameters({"browser"})	
	@BeforeTest

	public void setUp(String browserName) {
		System.out.println("browser Name is :" + browserName);
		basePage = new BasePage();
		prop = basePage.init_prep();
		prop.setProperty("browser", browserName);
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
	}
	public void setup(String browserName) {
	System.out.println("Browser Name is :" + browserName);
	basePage = new BasePage();
	prop = basePage.init_prep();
	prop.setProperty("browser", browserName);
	driver = basePage.init_driver(prop);
	loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}