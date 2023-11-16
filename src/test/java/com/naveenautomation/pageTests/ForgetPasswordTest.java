package com.naveenautomation.pageTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.naveenautomation.base.TestBase;
import com.naveenautomation.pages.ForgottenPasswordPage;
import com.naveenautomation.pages.LoginPage;

public class ForgetPasswordTest extends TestBase {

	private LoginPage loginPage;
	private ForgottenPasswordPage forgottenPasswordPage;

	@BeforeMethod
	public void launch() {
		intialisation();
		loginPage = new LoginPage(wd,false);
	}

	@Test
	public void validateUserCannotChangePwdWithInValidCredentials() {
		forgottenPasswordPage = loginPage.clickForgetPwdLink();
		forgottenPasswordPage.enterEmail("abc@xyaz.com");
		forgottenPasswordPage.clickSubmitBtnWithInValidEmail();
		Assert.assertEquals(forgottenPasswordPage.getSuccessMessageForForgetPwd(),
				"Warning: The E-Mail Address was not found in our records, please try again!",
				"Forget password with invalid credential failed");
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}
}
