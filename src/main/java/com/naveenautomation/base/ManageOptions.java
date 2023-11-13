package com.naveenautomation.base;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.naveenautomation.Browser.Browser;

public class ManageOptions {

	private ChromeOptions getChromeOptions(boolean runHeadlessness) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incoginito");
		options.setHeadless(runHeadlessness);
		return options;

	}

	private EdgeOptions getEdgeOptions() {
		EdgeOptions options = new EdgeOptions();
		return options;

	}

	private FirefoxOptions getFireFoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		return options;

	}

	public MutableCapabilities getOption(Browser browser) {
		switch (browser) {
		case CHROME:
			return this.getChromeOptions(true);
		case FIREFOX:
			return this.getFireFoxOptions();
		case EDGE:
			return this.getEdgeOptions();
		default:
			throw new IllegalArgumentException();
		}

	}
}
