package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class ManufactorsPage extends Page {

	public ManufactorsPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static final String PAGE_URL = "/opencart/index.php?route=product/manufacturer";
	private static By appleLink = By.cssSelector("#content>div:nth-child(4)>div>a");
	private static By canonLink = By.cssSelector("#content>div:nth-child(6)>div>a");
	private static By hpLink = By.cssSelector("#content>div:nth-child(8)>div>a");
	private static By htcLink = By.cssSelector("#content>div:nth-child(8)>div:nth-child(2)>a");
	private static By palmLink = By.cssSelector("#content>div:nth-child(10)>div>a");
	private static By sonyLink = By.cssSelector("#content>div:nth-child(12)>div>a");

	public ApplePage clickOnAppleLink() {
		((ProxyDriver) wd).click(appleLink);
		return new ApplePage(wd, true);
	}

	public CanonPage clickOnCanonLink() {
		((ProxyDriver) wd).click(canonLink);
		return new CanonPage(wd, true);
	}

	public HPPage clickOnHPLink() {
		((ProxyDriver) wd).click(hpLink);
		return new HPPage(wd, true);
	}

	public HTCPage clickOnHTCLink() {
		((ProxyDriver) wd).click(htcLink);
		return new HTCPage(wd, true);
	}

	public PalmPage clickOnPalmLink() {
		((ProxyDriver) wd).click(palmLink);
		return new PalmPage(wd, true);
	}

	public SonyPage clickOnSonyLink() {
		((ProxyDriver) wd).click(sonyLink);
		return new SonyPage(wd, true);
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
