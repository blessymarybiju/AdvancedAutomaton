package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class ProductReturnsSuccessPage extends Page{

	public ProductReturnsSuccessPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}
	private static final String PAGE_URL = "/opencart/index.php?route=account/return/success";
	private static By submitBtn = By.cssSelector( "a.btn-primary");
	private static By productReturnSuccessMessage = By.cssSelector("#content>p");

	public String getSuccessMessageForProductReturn() {
		return ((ProxyDriver) wd).getText(productReturnSuccessMessage);
	}

	public HomePage clickContinueBtn() {
		((ProxyDriver) wd).click(submitBtn);
		return new HomePage(wd,true);
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
