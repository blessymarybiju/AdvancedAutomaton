package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class AddReturnProduct extends Page {

	public AddReturnProduct(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private String orderId = "1111", pName = "apple", pid = "108";
	private static final String PAGE_URL = "/opencart/index.php?route=account/return/add";
	private static By orderIdInput = By.id("input-order-id");
	private static By productNameInput = By.id("input-product");
	private static By productIdInput = By.id("input-model");
	private static By orderErrorRadioButton = By.xpath("//input[@value=\"3\"]");
	private static By submitButton = By.xpath("//input[@value=\"Submit\"]");

	private void enterOrderId(String oId) {
		((ProxyDriver) wd).sendKeys(orderIdInput, oId);
	}

	private void enterOrderName(String productName) {
		((ProxyDriver) wd).sendKeys(productNameInput, productName);
	}

	private void enterProductId(String productId) {
		((ProxyDriver) wd).sendKeys(productIdInput, productId);
	}

	private void clickOrderErrorRadioButton() {
		((ProxyDriver) wd).click(orderErrorRadioButton);
	}

	public ProductReturnsSuccessPage clickSubmitButton() {
		enterOrderId(orderId);
		enterOrderName(pName);
		enterProductId(pid);
		clickOrderErrorRadioButton();
		((ProxyDriver) wd).click(submitButton);
		return new ProductReturnsSuccessPage(wd, true);
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
