package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.naveenautomation.proxyDriver.ProxyDriver;

public class ShoppingCartPage extends Page {

	public ShoppingCartPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static final String PAGE_URL = "/opencart/index.php?route=checkout/cart";
	private static By cartMessage = By.cssSelector("#content>p");
	private static By continueBtn = By.cssSelector("a.btn-primary");

	/* Method to return the success text of Shopping Cart */
	public String getMessageForShoppingCartPage() {
		return ((ProxyDriver) wd).getText(cartMessage);
	}

	public HomePage clickContinueBtn() {
		((ProxyDriver) wd).click(continueBtn);
		return new HomePage(wd, true);
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
