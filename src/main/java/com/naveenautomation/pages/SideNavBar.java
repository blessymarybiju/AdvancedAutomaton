package com.naveenautomation.pages;

import org.openqa.selenium.WebDriver;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.naveenautomation.proxyDriver.ProxyDriver;

public class SideNavBar extends Page {

	public SideNavBar(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub

	}

	public Page OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar item) {
		List<WebElement> sideBarItems = wd.findElements(By.cssSelector("div.list-group a"));

		for (WebElement webElement : sideBarItems) {
			if (webElement.getText().trim().equalsIgnoreCase(item.getItem())) {
				((ProxyDriver) wd).click(webElement);;
				return (Page) this.waitForPageToLoad(item.getpageClass());
			}
		}
		return null;
	}

	@Override
	protected String getPageURL() {
		// TODO Auto-generated method stub
		return null;
	}

}
