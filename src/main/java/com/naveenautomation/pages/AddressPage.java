package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class AddressPage extends Page {

	public AddressPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static final String PAGE_URL = "/opencart/index.php?route=account/address";
	private static By editAddressBtn = By.cssSelector("tbody>tr>td.text-right>a.btn-info");
	private static By addAddressBtn = By.cssSelector("a.btn-primary");
	private static By successAlertMessage = By.cssSelector("div.alert-success");

	public EditAddressPage clickEditAddressBtn() {
		((ProxyDriver) wd).click(editAddressBtn);
		return new EditAddressPage(wd, true);
	}
	
	public AddressAddPage clickAddAddressBtn() {
		((ProxyDriver) wd).click(addAddressBtn);
		return new AddressAddPage(wd, true);
	}

	public String getSuccessMessageForEditAddessInfo() {
		return ((ProxyDriver) wd).getText(successAlertMessage);
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
