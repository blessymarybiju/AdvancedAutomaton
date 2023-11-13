package com.naveenautomation.pages;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class GeneralPage extends LoadableComponent<GeneralPage> {

	protected WebDriver wd;
	private static final int DEFAULT_TIME_FOR_PAGE_LOAD = 60;

	public GeneralPage(WebDriver wd, boolean waitForPageToLoad) {
		this.wd = wd;
		if (waitForPageToLoad) {
			this.waitForDocumentCompleteState(30);
		}
	}

	@Override
	protected void load() {
		String pageURL = getPageURL();
		wd.get(pageURL);

	}

	@Override
	public GeneralPage get() {

		GeneralPage page = super.get();
		this.waitforPageToload();
		return page;
	}

	@Override
	protected abstract void isLoaded() throws Error;

	protected abstract String getPageURL();

	public void waitforPageToload() {
		this.waitForDocumentCompletestState();

	}

	protected boolean urlContains(String url) {
		try {
			String pageUrl = getPageURL();

			URL pageUrlObject = new URL(pageUrl);
			URL urlObject = new URL(url);

			String pageUrlHost = pageUrlObject.getHost();
			String urlHost = urlObject.getHost();

			System.out.println((String.format("Checking URL: Contains: %s; Actual: %s", pageUrl, url)));

			if (pageUrlHost.equalsIgnoreCase(urlHost)) {
				// Using URL.getPath() did not work with buy flow where URLs
				// contain a '#' which denotes the 'ref' portion of a URL.
				// See
				// https://docs.oracle.com/javase/tutorial/networking/urls/urlInfo.html.
				String pageUrlExclHost = pageUrl.substring(pageUrl.indexOf(pageUrlHost) + pageUrlHost.length());
				String urlExclHost = url.substring(url.indexOf(urlHost) + urlHost.length());
				return urlExclHost.toLowerCase().contains(pageUrlExclHost.toLowerCase());
			} else {
				return false;
			}

		} catch (MalformedURLException e) {

		}

		return false;
	}

	public void waitForDocumentCompletestState() {
		waitForDocumentCompleteState(DEFAULT_TIME_FOR_PAGE_LOAD);

	}

	public void waitForDocumentCompleteState(int secondsToWait) {
		new WebDriverWait(wd, secondsToWait).until((ExpectedCondition<Boolean>) driver -> {
			while (true) {
				String readyState = getDocumentReadyState();
				if (readyState.equals("complete")) {
					return true;
				}
			}
		});

	}

	private String getDocumentReadyState() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) wd;
			String val = jse.executeScript("return document.readyState").toString();
			return val;
		} catch (WebDriverException e) {
			e.printStackTrace();
			return null;
		}
	}

	public abstract String getPageUrl();

	@SafeVarargs
	public final GeneralPage waitForPageToLoad(final Class<? extends GeneralPage>... pagestoWaitFor) {

		return waitForPageToLoad(30, pagestoWaitFor);
	}

	@SafeVarargs
	protected final GeneralPage waitForPageToLoad(int timeForLoad,
			final Class<? extends GeneralPage>... pagestoWaitFor) {

		return new WebDriverWait(wd, timeForLoad).until(new ExpectedCondition<GeneralPage>() {

			@Override
			public GeneralPage apply(WebDriver input) {

				for (Class<? extends GeneralPage> page : pagestoWaitFor) {

					try {
						GeneralPage pg = page.getConstructor(WebDriver.class, Boolean.TYPE).newInstance(wd, true);
						return pg;
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}

				}
				return null;

			}
		});

	}
}