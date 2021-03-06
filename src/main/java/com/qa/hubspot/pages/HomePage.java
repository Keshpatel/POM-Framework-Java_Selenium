package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage{
	
	private WebDriver driver;
	ElementUtil elementUtil;
	
	
	//locator 
	private By header = By.tagName("h1");
	private By accountName=By.cssSelector("span.account-name");
	private By SettingsIcon = By.id("navSetting");
	private By contactsParentManu = By.id("nav-primary-contacts-branch");
	private By contactsSubMenu = By.id("nav-secondary-contacts");
	
	//Constructor
	public HomePage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver = driver;
	}
	
	//Methods
	public String getHomePageTitle() {
		return elementUtil.waitForTitlePresent(Constants.Home_Page_Title, 10);
	}
	
	public String getHeaderValue(){
			if(elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);
		}
		return null;
	}

	public String getAccountName() {
		if(elementUtil.doIsDisplayed(accountName)) {	
			return elementUtil.doGetText(accountName);
		}
		return null;
	}
	
	public boolean isSettingIconExist() {
		return elementUtil.doIsDisplayed(SettingsIcon);		
	}
	
	
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	private void clickOnContacts() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		elementUtil.waitForElementPresent(contactsParentManu, 10);
		elementUtil.doClick(contactsParentManu);
		elementUtil.waitForElementPresent(contactsSubMenu, 5);
		elementUtil.doClick(contactsSubMenu);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
