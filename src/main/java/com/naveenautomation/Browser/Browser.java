package com.naveenautomation.Browser;

public enum Browser {
	CHROME("Chrome"), 
	EDGE("Edge"), 
	FIREFOX("Firefox"), 
	SAFARI("Safari");

	String browser;

	private Browser(String browser) {
		this.browser = browser;
	}

}
