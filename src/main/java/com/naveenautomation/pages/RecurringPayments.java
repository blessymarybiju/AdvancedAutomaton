package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.naveenautomation.proxyDriver.ProxyDriver;

public class RecurringPayments extends Page {

	private static final String PAGE_URL = "/opencart/index.php?route=account/recurring";

	public RecurringPayments(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
		// TODO Auto-generated constructor stub
	}
	
	private static By recurringPaymentMessage= By.cssSelector("#content>p");
	private static By continueBtn= By.cssSelector("a.btn-primary");

	/* Method to return the text from payment */
	public String getMessageForPaymentsPage() {
		return ((ProxyDriver) wd).getText(recurringPaymentMessage);
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
