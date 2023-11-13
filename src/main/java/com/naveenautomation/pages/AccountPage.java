package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.naveenautomation.proxyDriver.ProxyDriver;

public class AccountPage extends Page {
	private static final String PAGE_URL = "/opencart/index.php?route=account/account";

	public AccountPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static By myAccountText = By.cssSelector("#content>h2:first-of-type");
	private static By acccountInfoUpdateSuccessMessage = By.cssSelector("div.alert-success");
	private static By passwordChangeSuccessMessage = By.cssSelector("div.alert-success");
	private static By newsLetterSubscribeSuccessMessage = By.cssSelector("div.alert-success");
	private static By contactUsLink = By.cssSelector("footer>div>div>div:nth-of-type(2)>ul>li:nth-of-type(1)>a");
	private static By shoppingCartLink = By.cssSelector("div#top-links>ul>li:nth-of-type(4)>a>span");
	private static By returnLink = By.cssSelector("footer>div>div>div:nth-of-type(2)>ul>li:nth-of-type(2)>a");
	private static By editAffiliateLink = By.cssSelector("div#content>ul:nth-of-type(3)>li:nth-of-type(1)>a");
	private static By editAffiliateSuccessMessage = By.cssSelector("div.alert-success");
	private static By brandsLink = By.cssSelector("footer>div>div>div:nth-of-type(3)>ul>li:nth-of-type(1)>a");

	public String getMyAccountText() {
		return ((ProxyDriver) wd).getText(myAccountText);
	}

	/* Method to return the success text of edit info */
	public String getSuccessMessageForEditInfo() {
		return ((ProxyDriver) wd).getText(acccountInfoUpdateSuccessMessage);
	}

	public String getSuccessMessageForEditAffiliateInfo() {
		return ((ProxyDriver) wd).getText(editAffiliateSuccessMessage);
	}

	/* Method to return the success text of password change */
	public String getSuccessMessageForPwdChange() {
		return ((ProxyDriver) wd).getText(passwordChangeSuccessMessage);
	}

	/* Method to return the success text of newsletter */
	public String getSuccessMessageForNewsLetter() {
		return ((ProxyDriver) wd).getText(newsLetterSubscribeSuccessMessage);
	}

	/*
	 * Method to click on cart link and to create an instance of Shopping cart Page'
	 */
	public ShoppingCartPage clickCartLink() {
		((ProxyDriver) wd).click(shoppingCartLink);
		return new ShoppingCartPage(wd, true);
	}

	/* Method to click on Contact link and to create an instance of Contact Page' */
	public ContactPage clickContactUsLink() {
		((ProxyDriver) wd).click(contactUsLink);
		return new ContactPage(wd, true);
	}

	public AddReturnProduct clickReturnLink() {
		((ProxyDriver) wd).click(returnLink);
		return new AddReturnProduct(wd, true);
	}

	public EditAffiliatePage clickEditAffiliateLink() {
		((ProxyDriver) wd).click(editAffiliateLink);
		return new EditAffiliatePage(wd, true);
	}

	public ManufactorsPage clickOnBrandLink() {
		((ProxyDriver) wd).click(brandsLink);
		return new ManufactorsPage(wd, true);
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
