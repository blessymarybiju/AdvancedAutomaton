package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class AddressAddPage extends Page {
	public AddressAddPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static final String PAGE_URL ="/opencart/index.php?route=account/address/add";

	private static By firstNameInput = By.xpath("//input[@id='input-firstname']");
	private static By lastNameInput = By.xpath("//input[@id='input-lastname']");
	private static By addressInput = By.xpath("//input[@id='input-address-1']");
	private static By cityInput = By.xpath("//input[@id='input-city']");
	private static By postCodeInput = By.xpath("//input[@id='input-postcode']");
	private static By countryDropdown = By.xpath("//select[@id='input-country']");
	private static By regionDropDown = By.xpath("//select[@id='input-zone']");
	private static By continueButton = By.xpath("//input[@class='btn btn-primary']");

	private void enterFirstName(String fName) {
		((ProxyDriver) wd).sendKeys(firstNameInput, fName);
	}
	private void enterLastName(String lName) {
		((ProxyDriver) wd).sendKeys(lastNameInput, lName);
	}

	private void enterAddress(String address) {
		((ProxyDriver) wd).sendKeys(addressInput, address);
	}

	private void enterCity(String cityName) {
		((ProxyDriver) wd).sendKeys(cityInput, cityName);
	}

	private void enterPostCode(String postalCode) {
		((ProxyDriver) wd).sendKeys(postCodeInput, postalCode);
	}

	public AddressPage clickSubmitButton() {
		enterFirstName("Neethu");
		enterLastName("S");
		enterAddress("1236 RainForest Dr");
		enterCity("Ottawa");
		enterPostCode("L4C2G4");
		((ProxyDriver) wd).selectItemFromDropDown(wd.findElement(countryDropdown),"38");
		((ProxyDriver) wd).selectItemFromDropDown(wd.findElement(regionDropDown),"Ontario");
		((ProxyDriver) wd).click(continueButton);
		return new AddressPage(wd, true);
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
