package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.Constants;

public class HomePageTest extends BaseTest{

	
	
	@BeforeClass
	public void homePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title = homePage.getHomePageTitle();
		System.out.println("Home Page Title is : "+ title);
		Assert.assertEquals(title, Constants.Home_Page_Title);
	}
	
	@Test(priority=2)
	public void VerifyHeaderValueTest() throws InterruptedException {
		String header = homePage.getHeaderValue();
		System.out.println("Home Page Header is :"+ header);
		Assert.assertEquals(header,Constants.Home_Page_Header);
	}
	@Test(priority=3)
	public void getAccountNameTest() throws InterruptedException {
		String account = homePage.getAccountName();
		System.out.println("Account Name is : "+ account);
		Assert.assertEquals(account,prop.getProperty("accountname").trim());
	}
	
	
	@Test(priority=4)
	public void VerifySettingsIconTest() {
		Assert.assertTrue(homePage.isSettingIconExist());
		
	}

}


	


