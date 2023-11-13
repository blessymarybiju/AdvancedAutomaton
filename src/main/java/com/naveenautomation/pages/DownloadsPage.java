package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class DownloadsPage extends Page {

	public DownloadsPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static final String PAGE_URL = "/opencart/index.php?route=account/download";
	private static By accountDownloadsMessage = By.cssSelector("#content>p");
	private static By continueBtn = By.cssSelector("a.btn-primary");

	public String getMessageForDownloadPage() {
		return ((ProxyDriver) wd).getText(accountDownloadsMessage);
	}

	public AccountPage clickContinueBtn() {
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
