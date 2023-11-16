package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class HTCPage extends Page {

	public HTCPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
		// TODO Auto-generated constructor stub
	}

	private static final String PAGE_URL = "/opencart/index.php?route=product/manufacturer/info&manufacturer_id=5";
	private static By naveenLogo = By.cssSelector("img[title='naveenopencart']");

	public String getHTCPageTitle() {
		return wd.getTitle();
	}
	
	public HomePage clickNaveenLogo() {
		((ProxyDriver)wd).click(naveenLogo);
		return new  HomePage(wd, true);
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

