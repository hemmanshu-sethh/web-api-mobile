package com.framework;

import java.net.URL;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class WebDriverFactory {

	private static String browser;

	public WebDriver getDriver(Map<String, String> getEnv) {
		browser = getEnv.get("browser");

		System.out.println("-----------" + getEnv.toString());
		if (getEnv.get("seleniumserver").equalsIgnoreCase("local")) {

			if (getEnv.get("platform").equalsIgnoreCase("web")) {
				if (browser.equalsIgnoreCase("firefox")) {
					return getFirefoxDriver();
				} else if (browser.equalsIgnoreCase("chrome")) {
					return getChromeDriver();
				} else if (browser.equalsIgnoreCase("firefox")) {
					return getChromeDriver();
				} else if (browser.equalsIgnoreCase("Safari")) {
					return getSafariDriver();
				} else if (browser.equalsIgnoreCase("edge")) {
					return getEdgeDriver();
				} else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
						|| (browser.equalsIgnoreCase("internet explorer"))) {
					return getInternetExplorerDriver();
				}
			}
			if (getEnv.get("platform").equalsIgnoreCase("android")) {
				try {
					return getAppiumDriver(getEnv);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}

		}

		if (getEnv.get("seleniumserver").equalsIgnoreCase("remote")) {
			return setRemoteDriver(getEnv);
		}
		return new FirefoxDriver();
	}

	private WebDriver setRemoteDriver(Map<String, String> selConfig) {
		DesiredCapabilities cap = null;
		browser = selConfig.get("browser");
		if (browser.equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox();
		} else if (browser.equalsIgnoreCase("chrome")) {
			cap = DesiredCapabilities.chrome();
		} else if (browser.equalsIgnoreCase("Safari")) {
			cap = DesiredCapabilities.safari();
		} else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
				|| (browser.equalsIgnoreCase("internet explorer"))) {
			cap = DesiredCapabilities.internetExplorer();
		}
		String seleniuhubaddress = selConfig.get("seleniumserverhost");
		URL selserverhost = null;
		try {
			selserverhost = new URL(seleniuhubaddress);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		cap.setJavascriptEnabled(true);
		return new RemoteWebDriver(selserverhost, cap);
	}

	private static WebDriver getAppiumDriver(Map<String, String> getEnv) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getEnv.get("androidversion"));
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getEnv.get("androideviceName"));
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, getEnv.get("androideviceName"));
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,getEnv.get("browser"));
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		return new AppiumDriver<>(url, capabilities);
	}

	private static WebDriver getChromeDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-web-security");
		options.addArguments("enable-automation");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-extensions");
		options.addArguments("--dns-prefetch-disable");
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		return new ChromeDriver(options);
	}

	private static WebDriver getInternetExplorerDriver() {
		InternetExplorerOptions ieoptions = new InternetExplorerOptions();
		ieoptions.setCapability("ignoreZoomSetting", true);
		return new InternetExplorerDriver(ieoptions);
	}

	private static WebDriver getEdgeDriver() {
		EdgeOptions edgeoptions = new EdgeOptions();
		return new EdgeDriver(edgeoptions);
	}

	private static WebDriver getSafariDriver() {
		return new SafariDriver();
	}

	private static WebDriver getFirefoxDriver() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.cache.disk.enable", false);
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setCapability("marionette", true);
		firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
		return new FirefoxDriver(firefoxOptions);
	}

	public static void chromeVersion() throws IOException {

		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("reg query " + "HKEY_CURRENT_USER\\Software\\Google\\Chrome\\BLBeacon " + "/v version");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		// Read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		// Read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			System.out.println(s);
		}
	}
}
