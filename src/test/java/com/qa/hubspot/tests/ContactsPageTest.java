package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactsPageTest extends BaseTest{
	
	@BeforeClass
	public void homePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContactsPage();
	}
	
	@Test(priority=1)
	public void VerifyContactsPageTitleTest() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("contacts Page Title is: "+ title);
		Assert.assertEquals(title, Constants.Contacts_Page_Title);
	}
	
	@Test(priority=2)
	public void verifyContactsPageHeaderTest() {
		String header = contactsPage.getContactsPageHeader();
		System.out.println("header is: " + header);
		Assert.assertTrue(header.contains(Constants.Contacts_Page_Header));
	}
	
	@DataProvider()
	public Object[][] getContactsTestData() {
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}	
	
	@Test(dataProvider="getContactsTestData", priority=3)
	public void createContactTest(String emailID, String firstName, String lastName, String jobTitle) {
		contactsPage.createContact(emailID , firstName, lastName , jobTitle);
	}	

}
