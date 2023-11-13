package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class EditAffiliatePage extends Page {
	private static final String PAGE_URL = "/opencart/index.php?route=account/affiliate/edit";
	private static By chequePayeeNameInput = By.id("input-cheque");
	private static By continueBtn = By.cssSelector(".btn-primary");

	public EditAffiliatePage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private void enterChequePayeeName(String name) {
		((ProxyDriver) wd).clear(chequePayeeNameInput);
		((ProxyDriver) wd).sendKeys(chequePayeeNameInput, name);
	}

	public AccountPage clickSubmitBtn(String name) {
		enterChequePayeeName(name);
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
