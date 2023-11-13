package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.naveenautomation.proxyDriver.ProxyDriver;

public class ChangePasswordPage extends Page {
	private static final String PAGE_URL = "/opencart/index.php?route=account/password";

	public ChangePasswordPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static By password = By.cssSelector("#input-password");
	private static By confirmPassword = By.cssSelector("#input-confirm");
	private static By continueBtn = By.cssSelector(".btn-primary");

	private void enterPwd(String pwd) {
		((ProxyDriver) wd).sendKeys(password, pwd);
	}

	private void enterConfirmPwd(String confirmPwd) {
		((ProxyDriver) wd).sendKeys(confirmPassword, confirmPwd);
	}

	public AccountPage clickSubmitBtn(String pwd, String confirmPwd) {
		enterPwd(pwd);
		enterConfirmPwd(confirmPwd);
		((ProxyDriver) wd).click(continueBtn);
		return new AccountPage(wd, true);
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
