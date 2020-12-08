package com.driverInstance;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.SkipException;
import com.propertyfilereader.PropertyFileReader;
import com.utility.Utilities;
import com.zee5.ApplicasterPages.AMDOnboardingScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverInstance extends Drivertools {

	//String accessKey = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51Ijo5MDYyMDkxLCJ4cC5wIjo5MDYyMDkwLCJ4cC5tIjoxNTk3MDYwODI0NTA3LCJleHAiOjE5MTI0MjM4MjcsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.tA0zZLkX1eUK3w8vtRj80q5APoVRpWizlg7X4dXEqBw";
	//String accessKey = "eyJ4cC51IjozNzg2MTE0LCJ4cC5wIjo1ODg1MDU5LCJ4cC5tIjoiTVRVME5qUXlNRGt6TkRNME13IiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4NzkyNDY1ODUsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.p8f1-OLXcMXxGZo4Nm38ZBc7Ra9jc5nol4emWap8QWI";
	long StartTime;
	
	@SuppressWarnings("static-access")
	public DriverInstance(String Application) {
		super(Application);
		try {
			switch (getPlatform()) {
			case "Android":
				tlDriver.set((AppiumDriver<WebElement>) new AndroidDriver<WebElement>(new URL(getremoteUrl()),
						this.generateAndroidCapabilities(Application)));
				util.waitForElementDisplayed(AMDOnboardingScreen.objWaitForSplashScreenDisapear, 240);
				Date date = new Date();
				long EndTime = date.getTime();
				logger.info("App End time : "+EndTime);
				long diff = (EndTime-StartTime);
				logger.info("App launch time : "+diff);
				break;

			case "MPWA":
				tlDriver.set(((AppiumDriver<WebElement>) new AndroidDriver<WebElement>(new URL(getremoteUrl()),
						this.generateAndroidCapabilities(Application))));
				tlDriver.get().get(getURL());
//				tlDriver.set(EventFiringWebDriverFactory.getEventFiringWebDriver(tlDriver.get(), new AppiumEventListener()));
				break;

			case "Web":
				LaunchBrowser(getBrowserType());
				break;
				
			case "TV":
				tlDriver.set((AppiumDriver<WebElement>) new AndroidDriver<WebElement>(new URL(getremoteUrl()),
						this.generateAndroidCapabilities(Application)));
				break;

			default:
				throw new SkipException("Incorrect Platform...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SkipException("Device not connected OR Appium Studio service is down...");
		}
		
		Utilities util = new Utilities();
		util.initDriver();
	}

	/**
	 * @param application
	 * @return Android capabilities
	 * @throws Exception
	 */
	protected DesiredCapabilities generateAndroidCapabilities(String application) {
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
//		capabilities.setCapability("compressXml", "true");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.UDID, "5200428f464ca5a7");
		capabilities.setCapability("fullReset", false);
		capabilities.setCapability("autoAcceptAlerts", true);
		if (getPlatform().equals("MPWA")) {
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			return capabilities;
		}
		logger.info("APK INSTALLED..");
		//capabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, "android");
		//capabilities.setCapability("accessKey", accessKey);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getAppPackage());
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getappActivity());
		if (Utilities.relaunch) {
			removePermisson(getAppPackage());
		}
		Date date = new Date();
		StartTime = date.getTime();
		logger.info("App Start time : "+StartTime);
		return capabilities;
	}

	/**
	 * Function to Launch Web Browsers
	 * 
	 * @param browserName
	 */
	public void LaunchBrowser(String browserName) {
		setHandler(new PropertyFileReader("properties/AppPackageActivity.properties"));
		if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().version("0.26.0").setup();
//			tlWebDriver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().version(getDriverVersion()).setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("enable-automation");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation");
			options.addArguments("--disable-gpu");
//			options.addArguments("--headless");
//			options.addArguments("--start-maximized");
//			options.addArguments("--window-size=1616, 876");
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			tlWebDriver.set(new ChromeDriver(options));
//			WebDriverEventListener popupListener = new PopUpListener();
//			tlWebDriver.set(new EventFiringWebDriver(new ChromeDriver(options)).register(popupListener));
		}

		else if (browserName.equalsIgnoreCase("IE")) {
			tlWebDriver.set(new InternetExplorerDriver());
		}

		else if (browserName.equalsIgnoreCase("MSEdge")) {
			tlWebDriver.set(new EdgeDriver());
		}

		tlWebDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		tlWebDriver.get().get(getURL());
		tlWebDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * To Remove the permission of an application
	 * 
	 * @param packagename
	 */
	public static void removePermisson(String packagename) 
	{
		logger.info("****Clearing the App Data****");
		String cmd2 = "adb shell pm clear " + packagename;
		try {
			Runtime.getRuntime().exec(cmd2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}