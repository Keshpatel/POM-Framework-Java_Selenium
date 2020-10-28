package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	private WebDriver driver;
	ElementUtil elementUtil;
	
	
	// By locators = OR
	private By emailID = By.id("username");
	private By password = By.id("password");
	private By loginButton = By.id("loginBtn");
	private By signUpLink = By.linkText("Sign up");

	// Constructor of the page;
	public LoginPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver = driver;
	}

	// page Actions:
	@Step("Getting login page title")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitlePresent(Constants.Login_Page_Title, 10);
	}
	@Step("getting sign up link exist on login page")
	public boolean isSignUpLinkExist() {
		return elementUtil.doIsDisplayed(signUpLink);

	}
	@Step("User login with username : {0} and passward : {1}")
	public HomePage doLogin(String un, String pwd) {

		System.out.println("Login to Application");
		elementUtil.doSendKeys(emailID, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new HomePage(driver);
	}

}
