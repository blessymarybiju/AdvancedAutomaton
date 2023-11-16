package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class OrderInfoPage extends Page {

	public OrderInfoPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
		// TODO Auto-generated constructor stub
	}

	private static final String PAGE_URL = "/opencart/index.php?route=account/order/info&order_id=3740";
	private static By orderHeader=By.cssSelector("thead>tr>td");
	private static By continueBtn = By.cssSelector("div.pull-right>a.btn-primary");
	
	public String getHeaderForOdrerHistory() {
		return ((ProxyDriver) wd).getText(orderHeader);
	}
	
	public OrderHistoryPage clickContinueBtn() {
		((ProxyDriver) wd).click(continueBtn);
		return new OrderHistoryPage(wd, true);
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
