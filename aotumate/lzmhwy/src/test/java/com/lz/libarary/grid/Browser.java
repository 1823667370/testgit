package com.lz.libarary.grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {

	private String browserName;
	private String version;
	private String[] platform;
	private String hubURL;

	public String getBrowserName() {
		return browserName;
	}

	public Browser() {
		this.browserName = "firefox";
		//this.version = "43";
		//this.platform = new String[] { "windows 7" };
		this.hubURL = "http://106.75.75.112:5555/wd/hub";
	}

	public Browser(String browser) {

		this.browserName = browser;
		//this.version = "42";
		//this.platform = new String[] { "windows 7" };
		//this.hubURL = "http://106.75.75.112:6666/wd/hub";
		this.hubURL = "http://106.75.75.112:5555/wd/hub";

	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String[] getPlatform() {
		return platform;
	}

	public void setPlatform(String[] platform) {
		this.platform = platform;
	}

	public String getHubURL() {
		return hubURL;
	}

	public void setHubURL(String hubURL) {
		this.hubURL = hubURL;
	}

	public static WebDriver getRemoteDriver(Browser remoteBrowser) {
		DesiredCapabilities capability = null;
		if (remoteBrowser.getBrowserName().contains("firefox")) {
			capability = DesiredCapabilities.firefox();
		} else if (remoteBrowser.getBrowserName().contains("chrome")) {
			capability = DesiredCapabilities.chrome();
		}

		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(
					new URL(remoteBrowser.getHubURL()), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		capability.setBrowserName(remoteBrowser.getBrowserName());
		capability.setVersion(remoteBrowser.getVersion());
	//	capability.setCapability(remoteBrowser.getPlatform()[0]);
		driver.manage().window().maximize();
		return driver;
	}

}
