package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class NewsLetterSubscriptionPage extends Page {

	public NewsLetterSubscriptionPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static final String PAGE_URL = "/opencart/index.php?route=account/newsletter";
	private static By yesRadioBtn= By.cssSelector( "label.radio-inline:first-of-type>input");
	private static By noRadioBtn= By.cssSelector( "label.radio-inline:nth-of-type(2)>input");
	private static By continueBtn= By.cssSelector("input.btn-primary");

	public void clickYesOnRadioBtn() {
		((ProxyDriver) wd).click(yesRadioBtn);
	}

	public void clickNoOnRadioBtn() {
		((ProxyDriver) wd).click(noRadioBtn);
	}
	public AccountPage clickSubmitBtn() {
		clickYesOnRadioBtn();
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
