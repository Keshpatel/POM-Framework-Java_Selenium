package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.listeners.TestAllureListener;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(TestAllureListener.class)
@Epic("Epic 100 - design login page module for hub spot appication")
@Story("Us - 101: Design all features of login page - login module, sign up link and title")

public class LoginPageTest extends BaseTest{
		
	@Description("Verify login page title test.....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is:" +title);
		Assert.assertEquals(title,Constants.Login_Page_Title);
//		message: Validation error on display of invalid title .
	}
	
	@Description("Verify sign up link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=1)
	public void VerifySignUpLinkTest() {
		Assert.assertTrue(loginPage.isSignUpLinkExist());
//        message : validation error if Signup link is not available .
	}
	
	@Description("Veryfy login feature test....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));

	}	
	
}
