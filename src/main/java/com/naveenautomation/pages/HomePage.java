package com.naveenautomation.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

	private static final String PAGE_URL = "/opencart/index.php?route=common/home";

	public HomePage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	public String getHomePageTitle() {
		return wd.getTitle();
	}

	@Override
	protected void isLoaded() {

		if (!urlContains(wd.getCurrentUrl())) {
			throw new Error();
		}
	}

	@Override
	protected String getPageURL() {
		return getDomain() + PAGE_URL;	}
}
