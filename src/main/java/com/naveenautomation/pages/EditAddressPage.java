package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class EditAddressPage extends Page{
	

public EditAddressPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}


private static final String PAGE_URL = "/opencart/index.php?route=account/address/edit&address_id=4029";
private static By firstNameInput = By.id("input-firstname");
private static By lastNameInput = By.id("input-lastname");
private static By addressInput = By.id("input-address-1");
private static By submitBtn = By.cssSelector("input[value='Continue']");

public void enterFName(String fname) {
	((ProxyDriver) wd).clear(firstNameInput);
	((ProxyDriver) wd).click(firstNameInput);
	((ProxyDriver) wd).sendKeys(firstNameInput, fname);
}

public void enterLName(String lname) {
	((ProxyDriver) wd).click(lastNameInput);
	((ProxyDriver) wd).sendKeys(lastNameInput, lname);
}

public void enterAddress(String address) {
	((ProxyDriver) wd).click(addressInput);
	((ProxyDriver) wd).sendKeys(addressInput, address);
}

public AddressPage clickSubmitBtn() {
	((ProxyDriver) wd).click(submitBtn);
	return new AddressPage(wd, true);
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
