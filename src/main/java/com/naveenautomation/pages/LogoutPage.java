package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class LogoutPage extends Page {

	public LogoutPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
		// TODO Auto-generated constructor stub
	}
	private static final String PAGE_URL = "/opencart/index.php?route=account/logout";
	private static By submitBtn = By.cssSelector( "a.btn-primary");
	private static By logoutSuccessMessage = By.cssSelector("#content>h1");

	/* Method to return the success message from logout page */
	public String getSuccessMessageForLogout() {
		return ((ProxyDriver) wd).getText(logoutSuccessMessage);
	}

	public HomePage clickContinueBtn() {
		((ProxyDriver) wd).click(submitBtn);
		return new HomePage(wd,true);
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
