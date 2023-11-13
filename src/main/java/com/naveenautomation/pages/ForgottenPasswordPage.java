package com.naveenautomation.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class ForgottenPasswordPage extends Page {

	public ForgottenPasswordPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static final String PAGE_URL = "/opencart/index.php?route=account/password";
	private static By emailInput = By.cssSelector("#input-email");
	private static By submitBtn = By.cssSelector("input[value='Continue']");
	private static By forgetPwdInvalidCredentialMessage = By.cssSelector("div.alert-danger");

	public void enterEmail(String email) {
		((ProxyDriver) wd).sendKeys(emailInput, email);
	}

	public LoginPage clickSubmitBtn() {
		((ProxyDriver) wd).click(submitBtn);
		return new LoginPage(wd, true);
	}

	public String getSuccessMessageForForgetPwd() {
		return ((ProxyDriver) wd).getText(forgetPwdInvalidCredentialMessage);
	}

	@Override
	protected void isLoaded() {

		if (!urlContains(wd.getCurrentUrl())) {
			throw new Error();
		}
	}


	@Override
	protected String getPageURL() {
		return getDomain() + PAGE_URL;
		}
}
