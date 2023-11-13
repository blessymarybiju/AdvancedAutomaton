package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class AccountSucessPage extends Page {

	public AccountSucessPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
		// TODO Auto-generated constructor stub
	}
	
	private static final String PAGE_URL = "/opencart/index.php?route=account/success";
	private static By submitBtn = By.cssSelector( "a.btn-primary");
	private static By accountCreationSuccessMessage = By.cssSelector("#content>h1");

	public String getSuccessMessageForAccountCreation() {
		return ((ProxyDriver) wd).getText(accountCreationSuccessMessage);
	}

	public AccountPage clickContinueBtn() {
		((ProxyDriver) wd).click(submitBtn);
		return new AccountPage(wd,true);
	}


	@Override
	protected void isLoaded() throws Error {
		if (!urlContains(wd.getCurrentUrl())) {
			throw new Error();
		}		
	}

	@Override
	protected String getPageURL() {
		return getDomain() + PAGE_URL;
	}

}
