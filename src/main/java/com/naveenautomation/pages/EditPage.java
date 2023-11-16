package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class EditPage extends Page {
	public EditPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
		// TODO Auto-generated constructor stub
	}

	private static final String PAGE_URL = "/opencart/index.php?route=account/edit";
	private static By firstNameInput = By.id("input-firstname");
	private static By lastNameInput = By.id("input-lastname");
	private static By emailInput = By.id("input-email");
	private static By telePhoneInput = By.id("input-telephone");
	private static By submitBtn = By.cssSelector("input[value='Continue']");
	private static By backBtn = By.cssSelector("a.btn-default");
	private static By emptyLastNameAlert = By.cssSelector("div.text-danger");
	
	public AccountPage clickBackBtn() {
		((ProxyDriver) wd).click(backBtn);
		return new AccountPage(wd, true);
	}

	public String getAlertForEmptyLastName() {
		return ((ProxyDriver) wd).getText(emptyLastNameAlert);
	}


	public void enterFName(String fname) {
		((ProxyDriver) wd).clear(firstNameInput);
		((ProxyDriver) wd).click(firstNameInput);
		((ProxyDriver) wd).sendKeys(firstNameInput, fname);
	}

	public void enterLName(String lname) {
		((ProxyDriver) wd).clear(lastNameInput);
		((ProxyDriver) wd).click(lastNameInput);
		((ProxyDriver) wd).sendKeys(lastNameInput, lname);
	}

	public void enterEmail(String email) {
		((ProxyDriver) wd).clear(emailInput);
		((ProxyDriver) wd).click(emailInput);
		((ProxyDriver) wd).sendKeys(emailInput, email);
	}

	public void enterTelephone(String number) {
		((ProxyDriver) wd).clear(telePhoneInput);
		((ProxyDriver) wd).click(telePhoneInput);
		((ProxyDriver) wd).sendKeys(telePhoneInput, number);
	}

	public AccountPage clickSubmitBtn() {
		((ProxyDriver) wd).click(submitBtn);
		return new AccountPage(wd, true);
	}
	
	public EditPage clickSubmitBtnWithEmptyField() {
		((ProxyDriver) wd).clear(lastNameInput);
		((ProxyDriver) wd).click(submitBtn);
		return new EditPage(wd, true);
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
