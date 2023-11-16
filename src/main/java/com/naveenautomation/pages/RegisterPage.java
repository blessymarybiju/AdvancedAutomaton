package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class RegisterPage extends Page {
	private static final String PAGE_URL = "/opencart/index.php?route=account/register";

	public RegisterPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static By firstNameInput = By.xpath("//input[@id='input-firstname']");
	private static By lastNameInput = By.xpath("//input[@id='input-lastname']");
	private static By emailInput = By.xpath("//input[@id='input-email']");
	private static By phoneNumInput = By.xpath("//input[@id='input-telephone']");
	private static By passwordInput = By.xpath("//input[@id='input-password']");
	private static By confirmPasswordInput = By.xpath("//input[@id='input-confirm']");
	private static By agreeCheckbox = By.xpath("//input[@name='agree']");
	private static By continueButton = By.xpath("//input[@class='btn btn-primary']");
	private static By alertForPwdMisMatch = By.cssSelector("div.text-danger");
	private static By alertForEmptyEmailField = By.cssSelector("div.text-danger");
	private static By alertForAgreeCheckBox = By.cssSelector("div.alert-danger");

	private void enterFirstName(String fName) {
		((ProxyDriver) wd).sendKeys(firstNameInput, fName);
	}

	public String getMessageForPwdMismatch() {
		return ((ProxyDriver) wd).getText(alertForPwdMisMatch);
	}

	public String getMessageForAgreeCheckBox() {
		return ((ProxyDriver) wd).getText(alertForAgreeCheckBox);
	}

	public String getMessageForEmptyEmailInput() {
		return ((ProxyDriver) wd).getText(alertForEmptyEmailField);
	}

	private void enterLastName(String lName) {
		((ProxyDriver) wd).sendKeys(lastNameInput, lName);
	}

	private void enterPhoneNum(String number) {
		((ProxyDriver) wd).sendKeys(phoneNumInput, number);
	}

	private void enterConfirmPassword(String password) {
		((ProxyDriver) wd).sendKeys(confirmPasswordInput, password);
	}

	private void enterPassword(String password) {
		((ProxyDriver) wd).sendKeys(passwordInput, password);
	}

	private void enterEmail(String email) {
		((ProxyDriver) wd).sendKeys(emailInput, email);
	}

	private void clickAgreeCheckBox() {
		((ProxyDriver) wd).click(agreeCheckbox);
	}

	public AccountSucessPage clickSubmitButton() {
		enterFirstName("Neethu");
		enterLastName("S");
		enterEmail("neethuSilu123@email.com");
		enterPhoneNum("9874563210");
		enterPassword("abcd");
		enterConfirmPassword("abcd");
		clickAgreeCheckBox();
		((ProxyDriver) wd).click(continueButton);
		return new AccountSucessPage(wd, true);
	}

	public RegisterPage clickSubmitButtonWithoutAgreePrivacyPolicy() {
		enterFirstName("Neethu");
		enterLastName("S");
		enterEmail("neethusalu@email.com");
		enterPhoneNum("9874563210");
		enterPassword("abcd");
		enterConfirmPassword("abcd");
		((ProxyDriver) wd).click(continueButton);
		return new RegisterPage(wd, true);
	}

	public RegisterPage clickSubmitButtonWithRegisteredEmail() {
		enterFirstName("Neethu");
		enterLastName("S");
		enterEmail("neethuSai@email.com");
		enterPhoneNum("9874563210");
		enterPassword("abcd");
		enterConfirmPassword("abcd");
		clickAgreeCheckBox();
		((ProxyDriver) wd).click(continueButton);
		return new RegisterPage(wd, true);
	}

	public RegisterPage clickSubmitButtonWithDifferentPassword() {
		enterFirstName("Neethu");
		enterLastName("S");
		enterEmail("neethuSai@email.com");
		enterPhoneNum("9874563210");
		enterPassword("abcd");
		enterConfirmPassword("1234");
		clickAgreeCheckBox();
		((ProxyDriver) wd).click(continueButton);
		return new RegisterPage(wd, true);
	}

	public RegisterPage clickSubmitButtonWithEmptyField() {
		enterFirstName("Neethu");
		enterLastName("S");
		enterPhoneNum("9874563210");
		enterPassword("abcd");
		enterConfirmPassword("1234");
		clickAgreeCheckBox();
		((ProxyDriver) wd).click(continueButton);
		return new RegisterPage(wd, true);
	}

	public String getFwdPwdPageTitle() {
		return wd.getTitle();
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
