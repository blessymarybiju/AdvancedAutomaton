package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class OrderHistoryPage extends Page {

	public OrderHistoryPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static final String PAGE_URL = "/opencart/index.php?route=account/order";
	private static By oderHistoryBanner = By.cssSelector("#content>h1");
	private static By viewBtn = By.cssSelector("tbody>tr:nth-child(1)>td:nth-child(7)>a");
	private static By continueBtn = By.cssSelector("a.btn-primary");

	public String getBannerForOdrerHistoryPage() {
		return ((ProxyDriver) wd).getText(oderHistoryBanner);
	}

	public AccountPage clickContinueBtn() {
		((ProxyDriver) wd).click(continueBtn);
		return new AccountPage(wd, true);
	}

	public OrderInfoPage clickViewBtn() {
		((ProxyDriver) wd).click(viewBtn);
		return new OrderInfoPage(wd, true);
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
