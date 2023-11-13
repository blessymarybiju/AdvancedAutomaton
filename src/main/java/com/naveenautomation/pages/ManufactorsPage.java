package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class ManufactorsPage extends Page {

	public ManufactorsPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static final String PAGE_URL = "/opencart/index.php?route=product/manufacturer";
	private static By appleLink = By.cssSelector("#content>div:nth-child(4)>div>a");

	public ApplePage clickOnAppleLink() {
		((ProxyDriver) wd).click(appleLink);
		return new ApplePage(wd, true);
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
