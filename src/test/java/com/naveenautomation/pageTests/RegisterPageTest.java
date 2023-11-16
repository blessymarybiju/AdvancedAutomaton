package com.naveenautomation.pageTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.naveenautomation.base.TestBase;
import com.naveenautomation.pages.LoginPage;
import com.naveenautomation.pages.RegisterPage;

public class RegisterPageTest extends TestBase {

	private LoginPage loginPage;
	private RegisterPage registerPage;

	@BeforeMethod
	public void launch() {
		intialisation();
		loginPage = new LoginPage(wd, false);
	}

	@Test
	public void validateUserCannotRegisterNewAccountWithRegistedEmail() {
		registerPage = loginPage.clickContinueRegisterButton();
		registerPage.clickSubmitButtonWithRegisteredEmail();
		Assert.assertEquals(registerPage.getFwdPwdPageTitle(), "Register Account",
				"User is able to register with already registered account");
	}

	@Test
	public void validateUserCannotRegisterNewAccountWithPwdMismatch() {
		registerPage = loginPage.clickContinueRegisterButton();
		registerPage.clickSubmitButtonWithEmptyField();
		Assert.assertEquals(registerPage.getMessageForEmptyEmailInput(), "E-Mail Address does not appear to be valid!",
				"User is able to register with already registered account");
	}
	
	@Test
	public void validateUserCannotRegisterNewAccountWithoutCheckingAgree() {
		registerPage = loginPage.clickContinueRegisterButton();
		registerPage.clickSubmitButtonWithoutAgreePrivacyPolicy();
		Assert.assertEquals(registerPage.getMessageForAgreeCheckBox(), "Warning: You must agree to the Privacy Policy!",
				"User is able to register with already registered account");
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}
}
