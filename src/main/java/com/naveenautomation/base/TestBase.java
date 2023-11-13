
package com.naveenautomation.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeClass;

import com.naveenautomation.Browser.Browser;
import com.naveenautomation.environment.Environment;
import com.naveenautomation.proxyDriver.ProxyDriver;
import com.naveenautomation.utils.WebDriverEvent;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver wd;

	private final Browser DEFAULT_BROWSER = Browser.CHROME;
	private final Environment URL = Environment.PROD;
	public String emailId = "navenncxe@gmail.com";
	public String password = "abcd";
	public static Logger logger;
	public WebDriverEvent events;
	public EventFiringWebDriver e_driver;
	private static final boolean RUN_ON_GRID = false;

	@BeforeClass
	public void loggerSteup() {
		logger = Logger.getLogger(TestBase.class);
		PropertyConfigurator.configure("log4j.properties");
		BasicConfigurator.configure();
		logger.setLevel(Level.INFO);
	}

	public void intialisation() {
		if (RUN_ON_GRID) {
			wd = new RemoteWebDriver(null);
		} else {

			switch (DEFAULT_BROWSER) {
			case CHROME:
				wd = new ProxyDriver(WebDriverManager.chromedriver().create());
				break;
			case EDGE:
				wd = new ProxyDriver(WebDriverManager.edgedriver().create());
				break;
			case FIREFOX:
				wd = new ProxyDriver(WebDriverManager.firefoxdriver().create());
				break;
			default:
				throw new IllegalArgumentException();
			}
		}

		/* Loading the Page */
		wd.get(URL.getUrl());
		/* Maximize the browser window */
		wd.manage().window().maximize();
		wd.manage().deleteAllCookies();
		// wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void tearDown() {
		wd.quit();
	}

}
