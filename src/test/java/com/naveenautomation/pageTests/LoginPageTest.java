
package com.naveenautomation.pageTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.naveenautomation.base.TestBase;
import com.naveenautomation.pages.ForgottenPasswordPage;
import com.naveenautomation.pages.LoginPage;

public class LoginPageTest extends TestBase {

	private LoginPage loginPage;
	private ForgottenPasswordPage forgottenPasswordPage;

	@BeforeMethod
	public void launch() {
		intialisation();
		loginPage = new LoginPage(wd, false);

	}

	@Test
	public void validateUserCanNotLoginWithInValidCredentials() {
		loginPage.SubmitLogin("navenncxe@gmail.com", "ABCD");
		Assert.assertEquals(loginPage.getAlertText(), "Warning: No match for E-Mail Address and/or Password.",
				"User logged in");
	}

	@Test
	public void validateUserCanAccessFwdPwdWithValidCredentials() {
		forgottenPasswordPage = loginPage.clickForgetPwdLink();
		forgottenPasswordPage.enterEmail(emailId);
		loginPage = forgottenPasswordPage.clickSubmitBtn();
		Assert.assertEquals(loginPage.getConfirmationTextForForgetPwd(),
				"An email with a confirmation link has been sent your email address.", "Confirmation email not send");
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}
}
