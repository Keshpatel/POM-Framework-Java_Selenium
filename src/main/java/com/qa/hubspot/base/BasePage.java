package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author kdpat
 *
 */
public class BasePage {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String flashElement;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	/**
	 * This method is used to initialize the webdriver in the basis of giver browser
	 * name	 * 
	 * @param browserName
	 * @return	 */
	public WebDriver init_driver(Properties prop) {
		flashElement = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").trim();
		System.out.println("BrowserName is : " + browserName);
		optionsManager = new OptionsManager(prop);
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver(browserName);
			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver(browserName); 
			} else {
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));	
			}
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(optionsManager.getEdgeOptions());
		} else {
		System.out.println("Please pass the correct browser name " + browserName);   }
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().get(prop.getProperty("url"));
		return getDriver();   	}

	/**
	 * This method will define desired capabilities and will initialize the driver
	 * with capability Also , This will initialize the driver with Selenium HUB.port */
	private void init_remoteWebDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());

			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());

			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * getDriver using ThreadLocal
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();			}
	/**
	 * This method is used to get the properties values from configration file
	 * 	 * @return it returns prop				*/
	public Properties init_prep() {
		prop = new Properties();
		String path = null;
		String env = null;

		try {

			env = System.getProperty("env");
			System.out.println("Running on Envirnment: " + env);

			if (env == null) {
				path = "./src/main/java/com/qa/hubspot/config/config.properties";
				System.out.println("Running on Envirnment :" + "PROD");
			} else {
				switch (env) {
				case "qa":
					path = "./src/main/java/com/qa/hubspot/config/config.qa.properties";
					break;
				case "dev":
					path = "./src/main/java/com/qa/hubspot/config/config.dev.properties";
					break;
				case "stage":
					path = "./src/main/java/com/qa/hubspot/config/config.stage.properties";
					break;
				default:
					System.out.println("Please pass correct Envirnment value");
					break;
				}
			}
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * This method is use to take screenshot.
	 * 
	 * 
	 */
	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}