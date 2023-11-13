package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.naveenautomation.proxyDriver.ProxyDriver;

public class ContactPage extends Page {

	public ContactPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);

	}

	private static final String PAGE_URL = "/opencart/index.php?route=information/contact";

	private static By enquiryTextarea = By.cssSelector("#input-enquiry");
	private static By submitBtn = By.cssSelector(".btn-primary");
	private static By enquirySuccessMessage = By.cssSelector("#content>p");

	private void enterEnquiry() {
		((ProxyDriver) wd).sendKeys(enquiryTextarea, "This is a test enquiry!!!");
	}

	public void clickSubmitBtn() {
		enterEnquiry();
		((ProxyDriver) wd).click(submitBtn);
	}

	public String getMessageForShoppingCartPage() {
		return ((ProxyDriver) wd).getText(enquirySuccessMessage);
	}

	public HomePage clickContinueBtn() {
		((ProxyDriver) wd).click(submitBtn);
		return new HomePage(wd,true);
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
