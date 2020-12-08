package com.business.zee;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.driverInstance.CommandBase;
import com.extent.ExtentReporter;
import com.metadata.ResponseInstance;
import com.mixpanelValidation.Mixpanel;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.PWAPages.*;

public class Zee5PWAWEBMixPanelBusinessLogic extends Utilities {

	public Zee5PWAWEBMixPanelBusinessLogic(String Application) throws InterruptedException {
		new CommandBase(Application);
	}

	String URL = getParameterFromXML("url");

	private int timeout;

	/** Retry Count */
	private int retryCount;

	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
//	final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	Mixpanel mixpanel = new Mixpanel();

	String UserType = getParameterFromXML("userType");

	String Username;
	String Password;

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info(
				"Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public void ZeeWEBPWAMixPanelLogin(String LoginMethod) throws Exception {
		String userType = getParameterFromXML("userType");
		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
//			dismissDisplayContentLanguagePopUp();
//			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");
			Username = getParameterFromXML("NonsubscribedUserName");
			Password = getParameterFromXML("NonsubscribedUserPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");
			Username = getParameterFromXML("SubscribedUserName");
			Password = getParameterFromXML("SubscribedUserPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;

		case "ClubUser":
			extent.HeaderChildNode("Login as Club User");
			String ClubUsername = getParameterFromXML("ClubSubscribedUserName");
			String ClubPassword = getParameterFromXML("ClubSubscribedPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, ClubUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, ClubPassword, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;
		}
	}

	public void ZeeWEBPWAMixPanelLoginForParentalControl(String LoginMethod) throws Exception {
		String userType = getParameterFromXML("userType");
		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			dismissDisplayContentLanguagePopUp();
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");
			String SUsername = getParameterFromXML("SettingsNonsubscribedUserName");
			String SPassword = getParameterFromXML("SettingsNonsubscribedPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, SUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SPassword, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");
			String SettingsSubscribedUsername = getParameterFromXML("SettingsSubscribedUserName");
			String SettingsSubscribedPassword = getParameterFromXML("SettingsSubscribedPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, SettingsSubscribedUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SettingsSubscribedPassword, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;
		}
	}

	/**
	 * Dismiss the Display Language pop up
	 */
	public void dismissDisplayContentLanguagePopUp() throws Exception {
		extent.HeaderChildNode("Dismiss Display and Content Language Pop Ups");
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup, 90,
				"Continue on Display Language Pop Up");
		Thread.sleep(5000);
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup, 10,
				"Continue on Content Language Pop Up");
	}

	/**
	 * Function to enter url
	 */
	public void enterURLInWEBBrowser(String browser, String url) {
		extent.HeaderChildNode("Enter Browser URL");
		if (browser.equalsIgnoreCase("chrome")) {
			try {
				getWebDriver().get(url);
				extent.extentLogger("enteredURL", "Entered " + url + " in " + browser + " browser");
				logger.info("Entered " + url + " in " + browser + " browser");
			} catch (Exception e) {
				extent.extentLogger("failToEnterURL", "Failed to enter " + url + " in " + browser + " browser");
			}
		}
	}

	/**
	 * Function to select any tab
	 * 
	 */
	public boolean navigateToAnyScreenOnWeb(String screen) throws Exception {
		try {
			waitTime(8000);
			if (checkElementDisplayed(PWAHomePage.objHomeBarText(screen), screen + " Tab")) {
				click(PWAHomePage.objHomeBarText(screen), screen + " Tab");
				return true;
			} else {
				JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
				getWebDriver().findElement(PWAHomePage.objMoreMenuIcon);
				waitTime(2000);
				try {
					WebElement tab = getWebDriver().findElement(PWAHomePage.objMoreMenuTabs(screen));
					logger.info(screen + " Tab is displayed");
					extent.extentLogger("tabDisplayed", screen + " Tab is displayed");
					executor.executeScript("arguments[0].click();", tab);
					logger.info("Clicked on " + screen + " Tab");
					extent.extentLogger("tabClicked", "Clicked on " + screen + " Tab");
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		return false;
	}

	/**
	 * Function to scroll down
	 */
	public static void scrollDownWEB() {
		js.executeScript("window.scrollBy(0,250)", "");
	}

	/**
	 * Waits for player loader to complete
	 * 
	 * @throws Exception
	 */
	public void waitForPlayerLoaderToComplete() throws Exception {
		// verifyElementNotPresent(PWAPlayerPage.objPlayerLoader, 60);

		new WebDriverWait(getWebDriver(), 10)
				.until(ExpectedConditions.invisibilityOfElementLocated(PWAPlayerPage.objPlayerLoader));
	}

	/**
	 * Video Player or Live Player Ad verify
	 * 
	 * @param playerType
	 * @throws Exception
	 */
	public void waitForPlayerAdToComplete(String playerType) throws Exception {
		boolean adWasDisplayed = false;
		boolean playerDisplayed = false;
		int confirmCount = 0;
		waitTime(5000);
		main: for (int trial = 0; trial < 120; trial++) {
			try {
				findElement(PWAPlayerPage.objAd);
				adWasDisplayed = true;
				if (trial == 5) {
					logger.info("Ad play in progress");
					extent.extentLogger("AdPlayInProgress", "Ad play in progress");
					try {
						getWebDriver().findElement(PWAPlayerPage.objAd);
					} catch (Exception e) {
					}
				}
				if (Math.floorMod(trial, 15) == 0)
					System.out.println("Ad play in progress");
				Thread.sleep(1000);

//				//SkipAD
//				if(checkElementExist(PWAPlayerPage.objSkipAd, "SkipAd")){
//					Thread.sleep(5000);
//					click(PWAPlayerPage.objSkipAd, "SkipButton");					
//				}
//				else
//				{
//					System.out.println("No Skip Button Displayed");
//				}

			} catch (Exception e) {
				try {
					if (playerType.equals("Live Player")) {
						findElement(PWAPlayerPage.objLivePlayerLiveTag);
					} else if (playerType.equals("Video Player")) {
						findElement(PWAPlayerPage.objPlayerSeekBar);
					}
					playerDisplayed = true;
					confirmCount++;
					if (confirmCount == 1) {
						if (adWasDisplayed == false) {
							logger.info("Ad did not play");
							extent.extentLogger("AdDidNotPlay", "Ad did not play");
						} else {
							logger.info("Ad play complete");
							extent.extentLogger("AdPlayComplete", "Ad play complete");
						}
						break main;
					}
				} catch (Exception e1) {
				}
			}
		}
		if (playerDisplayed == false && adWasDisplayed == false) {
			logger.error("Ad play failure");
			extent.extentLogger("failedAd", "Ad play failure");
		}
	}

	/**
	 * Function Scroll to Element
	 *
	 * @param element
	 * @throws Exception
	 */
	public void ScrollToTheElementWEB(By element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("arguments[0].scrollIntoView(true);", findElement(element));
		jse.executeScript("window.scrollBy(0,-250)", "");
	}

	public void tearDown() {
		getWebDriver().quit();
	}

	public void navigateHome() {
		getWebDriver().get("https://newpwa.zee5.com/");
		getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void Back_TO_TopArrow_Web(String usertype) throws Exception {

		scrollToBottomOfPageWEB();
		if (usertype.equalsIgnoreCase("Guest")) {
			if (checkElementExist(PWAHomePage.objWhatWonderingPopUp, "Wondering popUp")) {
				waitTime(3000);
				click(PWAHomePage.objWhatWonderingPopUpCloseIcon, "Close icon");
			}
		}

		if (checkElementExist(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top")) {
			click(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top");
			System.out.println("Scrolled back to top using Back to top btn");
		}

	}

	/**
	 * The method will wait for the element to be located for a maximum of given
	 * seconds. The method terminates immediately once the element is located. The
	 * method throws error if the element could not be located within the given
	 * seconds
	 */
	public boolean waitForElement(By locator, int seconds, String message) throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				getWebDriver().findElement(locator);
				logger.info("Located element " + message);
				extent.extentLogger("locatedElement", "Located element " + message);
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.error("Failed to locate element " + message);
					extent.extentLoggerFail("failedLocateElement", "Failed to locate element " + message);
				}
			}
		}
		return false;
	}

	/**
	 * Generic function to Logout.
	 */
	public void logout() throws Exception {
		extent.HeaderChildNode("Logout");
		click(PWALandingPages.objWebProfileIcon, "Profile Icon");
		waitTime(3000);
		click(PWAHamburgerMenuPage.objMyProfileOptionsWEB("Logout"), "Logout option");
		waitTime(3000);
	}

	@SuppressWarnings("static-access")
	public void verifySkipLoginEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Skip Login Event");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objSkip, "Skip Login");
			waitTime(5000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Cross");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login");
		}
	}

	@SuppressWarnings("static-access")
	public void verifySkipLoginByClickingOnLoginInRegistrationPopUp(String userType, String keyword) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {

			extent.HeaderChildNode(
					"Verify Skip Login Event during content playback post clicking on login in registration popup");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");
			click(PWAPremiumPage.obj1stContentInShowDetailPage, "Content Card");
			waitForElement(PWALoginPage.objLoginLink, 20, "Login Link");
			click(PWALoginPage.objLoginLink, "Login Link");
			waitTime(5000);
			waitForElement(PWALoginPage.objSkip, 10, "Skip Login");
			click(PWALoginPage.objSkip, "Skip Login");
			waitTime(5000);
		
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Element", "Cross");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login");
		}
	}

	@SuppressWarnings("static-access")
	public void verifySkipLoginByClickingOnLoginInGetPremiumPopUp(String userType, String keyword2) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {

			extent.HeaderChildNode(
					"Verify Skip Login Event during content playback post clicking on login button in Get Premium popup");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword2), "Search Result");
			click(PWALoginPage.objLoginCTAInPremiumPopup, "Login CTA");
			waitForElement(PWALoginPage.objSkip, 20, "Skip Login");
			waitTime(5000);
			click(PWALoginPage.objSkip, "Skip Login");
			waitTime(5000);
			
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "movie_detail");
			mixpanel.FEProp.setProperty("Element", "Cross");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login");
		}
	}

	@SuppressWarnings("static-access")
	public void verifySkipRegistrationEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Skip Registration Event");
			click(PWALoginPage.objSignUpBtnWEB, "Sign Up For Free");
			waitTime(5000);
//			JSClick(PWALoginPage.objSkip, "Skip Registration");
			verifyElementPresentAndClick(PWALoginPage.objSkip, "Skip Registration");
			waitTime(3000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Cross");
			mixpanel.FEProp.setProperty("Page Name", "register");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Registartion");
		}
	}

	public void verifyRegisterScreenDisplayEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Register Screen Display Event");
			click(PWALoginPage.objSignUpBtnWEB, "Sign Up For Free");
			waitTime(3000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "register");
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Register Screen Display");
		}
	}

	public void verifySubscriptionPageViewedEventViaSubscribeBtn(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Page Viewed Event");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			waitTime(3000);
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Page Viewed");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Subscription Page Viewed");
			}
		}
	}

	public void verifySubscriptionPageViewedEventViaBuySubscription(String userType) throws Exception {
		extent.HeaderChildNode(
				"Verify Subscription Page Viewed Event by clicking on Buy subscription in hamburger menu");
		if (userType.equalsIgnoreCase("Guest")) {
			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			click(PWAHamburgerMenuPage.objBuySubscription, "Buy Subscription option");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Page Viewed");
		}
	}

	public void verifySubscriptionPageViewedEventViaPrepaidCode(String userType) throws Exception {
		extent.HeaderChildNode(
				"Verify Subscription Page Viewed Event by clicking on prepaid code option in hamburger menu");
		if (userType.equalsIgnoreCase("Guest")) {
			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			click(PWAHamburgerMenuPage.objHaveAPrepaidCode, "Have a Prepaid Code? option");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Page Viewed");
		}
	}

	public void verifySubscriptionSelectedEvent(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Selected Event");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			mixpanel.FEProp.setProperty("Element", "Continue");
			mixpanel.FEProp.setProperty("Source", "home");
			String[] cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount).split(" ");
			mixpanel.FEProp.setProperty("Transaction Currency", cost[0]);
			mixpanel.FEProp.setProperty("cost", cost[1]);
			mixpanel.FEProp.setProperty("Current Subscription", "false");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);
			if (userType.equals("Guest")) {
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Selected");
			} else {
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				mixpanel.ValidateParameter(local.getItem("ID"), "Subscription Selected");
			}

		}
	}

	public void verifySubscriptionSelectedEventByClubPack(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Selected Event By selecting Club Pack");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			click(PWASubscriptionPages.objClubPack, "Club Pack");
			click(PWASubscriptionPages.objPackAmount1, "Pack");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Continue");
			String[] cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount).split(" ");
			mixpanel.FEProp.setProperty("Transaction Currency", cost[0]);
			mixpanel.FEProp.setProperty("cost", cost[1]);
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);
			if (userType.equals("Guest")) {
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Selected");
			} else {
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				mixpanel.ValidateParameter(local.getItem("ID"), "Subscription Selected");
			}
		}
	}

	public void verifyPromoCodeResultEventForValid(String userType) throws Exception {
		extent.HeaderChildNode("Verify Promo Code Result Event For Valid code");
		String promoCode = "PNB20";
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			click(PWASubscriptionPages.objHaveACode, "Have A Code section");
			type(PWASubscriptionPages.objHaveACode, promoCode, "Prepaid Code");
			click(PWASubscriptionPages.objApplyBtn, "Apply Button");
			waitTime(2000);
			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
				}
			}
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "APPLY");
			mixpanel.FEProp.setProperty("Success", "true");
			String[] cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount).split(" ");
			mixpanel.FEProp.setProperty("Transaction Currency", cost[0]);
			mixpanel.FEProp.setProperty("cost", cost[1]);
			mixpanel.FEProp.setProperty("Promo Code Type", "Product");
			mixpanel.FEProp.setProperty("Promo Code", promoCode);

			if (userType.equals("Guest")) {
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Promo Code Result");
			} else {
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				mixpanel.ValidateParameter(local.getItem("ID"), "Promo Code Result");
			}
		}
	}

	public void verifyPromoCodeResultEventForInvalid(String userType) throws Exception {
		extent.HeaderChildNode("Verify Promo Code Result Event For Invalid code");
		String promocode = "sdcrfd";
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACode, "Have A Code section");
			type(PWASubscriptionPages.objHaveACode, promocode, "Prepaid Code");
			click(PWASubscriptionPages.objApplyBtn, "Apply Button");

			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
				}
			}
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "APPLY");
			mixpanel.FEProp.setProperty("Success", "false");
			String[] cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount).split(" ");
			mixpanel.FEProp.setProperty("Transaction Currency", cost[0]);
			mixpanel.FEProp.setProperty("cost", cost[1]);
			mixpanel.FEProp.setProperty("Promo Code Type", "Product");
			mixpanel.FEProp.setProperty("Promo Code", promocode);

			if (userType.equals("Guest")) {
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Promo Code Result");
			} else {
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				mixpanel.ValidateParameter(local.getItem("ID"), "Promo Code Result");
			}
		}
	}

	public void verifyTVAuthenticationScreenDisplayEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify TV Authentication Screen Display Event");
			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authenticate Device");
			waitTime(5000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			System.out.println(local.getItem("ID"));
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "device_authentication");
			mixpanel.FEProp.setProperty("element", "Authenticate Device");
			mixpanel.ValidateParameter(local.getItem("ID"), "TV Authentication Screen Display");
		}
	}

	public void facebookLogin() throws Exception {
		extent.HeaderChildNode("Login through Facebook");
		getWebDriver().get(URL);
		verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login button");

		waitForElementDisplayed(PWALoginPage.objFacebookIcon, 10);

		click(PWALoginPage.objFacebookIcon, "Facebook Icon");
		switchToWindow(2);

		if (checkElementDisplayed(PWALandingPages.objWebProfileIcon, "Profile icon")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");

		}

		else {
			checkElementDisplayed(PWALoginPage.objFacebookPageVerificationWeb, "Facebook page");
			verifyElementPresent(PWALoginPage.objFacebookLoginEmailWeb, " Email Field");
			type(PWALoginPage.objFacebookLoginEmailWeb, "igstesttt@gmail.com", "Emial Field");
			verifyElementPresent(PWALoginPage.objFacebookLoginpasswordWeb, " Password Field");
			type(PWALoginPage.objFacebookLoginpasswordWeb, "Igs123!@#", "Password Field");
			verifyElementPresentAndClick(PWALoginPage.objFacebookLoginButtonInFbPageWeb, "Login Button");
			
			if (checkElementDisplayed(PWALoginPage.objOKBtnInFbPage, "Would you like to Continue popup")) {
				click(PWALoginPage.objOKBtnInFbPage, "OK Button");
			}
			switchToParentWindow();
			waitTime(5000);
		}
//		logout();
	}

	public void phoneNumberRegistration() throws Exception {
		extent.HeaderChildNode(
				"verifing that user is able to enter Mobile number, Password, date of birth, gender in Registration page");
		click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
		waitForElementDisplayed(PWALoginPage.objEmailField, 5);
		click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
		type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
		click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
		type(PWASignupPage.objOTP1, "1", "OTP box1");
		type(PWASignupPage.objOTP2, "2", "OTP box2");
		type(PWASignupPage.objOTP3, "3", "OTP box3");
		type(PWASignupPage.objOTP4, "4", "OTP box4");
		waitTime(3000);
		verifyElementPresentAndClick(PWASignupPage.objVerifyBtnWeb, "Verified Button");

	}

	public void twitterLogin() throws Exception {
		extent.HeaderChildNode("Login through Twitter");

		verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login button");
		waitForElementDisplayed(PWALoginPage.objLoginPageheader, 10);

		waitForElementDisplayed(PWALoginPage.objTwitterIcon, 10);
		checkElementDisplayed(PWALoginPage.objTwitterIcon, "Twitter icon");
		waitTime(1000);

		click(PWALoginPage.objTwitterIcon, "twitter Icon");
		switchToWindow(2);

		if (checkElementDisplayed(PWALandingPages.objWebProfileIcon, "Profile icon")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");

		}

		else {
			verifyElementPresent(PWALoginPage.objTwitterEmaildField, " Email Field");
			type(PWALoginPage.objTwitterEmaildField, "zee5latest@gmail.com", "Email Field");

			verifyElementPresent(PWALoginPage.objTwitterPasswordField, " Password Field");
			type(PWALoginPage.objTwitterPasswordField, "User@123", "Password Field");

			verifyElementPresentAndClick(PWALoginPage.objTwitterSignInButton, "Login Button");
			switchToParentWindow();
			
		}

	}

	public void socialLogin(String LoginMethod) throws Exception {
		switch (LoginMethod) {

		case "twitter":
			twitterLogin();
			waitTime(3000);
			break;

		case "facebook":
			facebookLogin();
			waitTime(3000);
			break;

		case "emailLogin":
			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedUserPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;

		}
	}

	public void verifyLoginInitiatedEventForValidCredentials(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event for Valid Credentials");
			socialLogin(loginMethod);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Initiated");
		}
	}

	public void verifyLoginResultEventForValidCredentials(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event for Valid Credentials");
			socialLogin(loginMethod);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
		
			System.out.println(local.getItem("ID"));
			mixpanel.ValidateParameter(local.getItem("ID"), "Login Result");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventByClickingOnLoginButton(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Screen Display Event By Clicking On Login Button");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(5000);
			
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Screen Display");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(String userType, String keyword2)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Screen Display Event By Clicking On Login Button On Player");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword2), "Search Result");

			if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
			verifyElementPresent(PWASubscriptionPages.objLoginLinkInPlayer, "Login link");
			JSClick(PWASubscriptionPages.objLoginLinkInPlayer, "Login link");
			
			waitTime(5000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();

			mixpanel.FEProp.setProperty("Source", "movie_detail");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Screen Display");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(String userType)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Login Screen Display Event By Clicking On Login Button In Registartion Screen");
			click(PWALoginPage.objSignUpBtnWEB, "Sign Up For Free");
			JSClick(PWALoginPage.objLoginLink, "Login link");

			waitTime(5000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "register");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Screen Display");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(String userType, String keyword2)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Login Screen Display Event By Clicking On Login Button In Get Premium Pop Up");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword2), "Search Result");

			waitTime(5000);
			
			if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				// ScrollToTheElementWEB(PWALoginPage.objLoginCTAInPremiumPopup);
				verifyElementPresentAndClick(PWALoginPage.objLoginCTAInPremiumPopup, "Login link");
				
			}
			waitTime(5000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "movie_detail");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Screen Display");
		}
	}

	public void verifyCarouselBannerClickEvent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Carousel Banner Click Event For content played from Carousel");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		verifyElementPresentAndClick(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

	}

	public void verifyThumbnailClickEventFromTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event For content played from trays");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		verifyElementPresentAndClick(PWAPremiumPage.objThumbnail, "Thumbnail from a tray");

		waitTime(2000);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", tabName);
		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.FEProp.setProperty("Element", "Play");
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Thumbnail Click");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Thumbnail Click");
		}
	}

	public void verifyThumbnailClickEventFromViewMorePage(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event For content played from trays");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objViewAllBtn, "View All Button");
		verifyElementPresentAndClick(PWAPremiumPage.objThumbnail, "Thumbnail from View More Page");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", tabName);
		mixpanel.FEProp.setProperty("Page Name", "view_all_top-20-on-zee5-kannada");
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Thumbnail Click");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Thumbnail Click");
		}
	}

	public void verifyThumbnailClickEventFromShowDetailPage(String keyword) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event From Show Detail Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");

		verifyElementPresentAndClick(PWAPremiumPage.obj1stContentInShowDetailPage, "Thumbnail from Show detail page");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Thumbnail Click");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Thumbnail Click");
		}
	}

	public void verifyThumbnailClickEventFromPlaybackPage(String keyword, String userType) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event From Playback Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");

		click(PWAPremiumPage.obj1stContentInShowDetailPage, "Thumbnail");
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWAPremiumPage.obj1stContentInShowDetailPage, "Thumbnail from playback page");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "show_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Thumbnail Click");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Thumbnail Click");
		}

	}

	public void verifySearchExecutedEvent() throws Exception {
		extent.HeaderChildNode("Verify Search Executed Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		String searchtxt = "Kam";
		type(PWASearchPage.objSearchEditBox, searchtxt + "\n", "Search Edit box: ");
		waitTime(4000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Search Type", "text");
		mixpanel.FEProp.setProperty("Results Returned", ResponseInstance.getresponse(searchtxt));
		mixpanel.FEProp.setProperty("Search Query", searchtxt);
		mixpanel.FEProp.setProperty("Search Success", "true");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Search Executed");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Search Executed");
		}
	}

	public void verifyScreenViewEvent(String screen) throws Exception {
		extent.HeaderChildNode("Verify Screen View Event");
		navigateToAnyScreenOnWeb(screen);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", screen + "_landing");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Screen View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Screen View");
		}
	}

	public void clearSearchHistoryEvent(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Clear Search History Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(5000);
		verifyElementPresentAndClick(PWASearchPage.objSearchCloseButton, "Clear Search Icon");
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "search");
		mixpanel.FEProp.setProperty("Setting Changed", "clear search history");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Clear Search History");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Clear Search History");
		}
	}

	public void verifyParentalRestrictionEvent(String userType, String restriction) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Parental Restriction Event");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");

			switch (restriction) {

			case "Age13+":
				click(PWAHamburgerMenuPage.objRestrict13PlusContent, "Restrict 13+ content");
				click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
				type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
				waitTime(4000);
				click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
				waitTime(3000);

				break;

			case "RestrictAll":
				click(PWAHamburgerMenuPage.objRestrictAll, "Restrict all option");
				click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
				type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
				waitTime(4000);
				click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
				waitTime(3000);
				break;

			case "NoRestriction":
				click(PWAHamburgerMenuPage.objNoRestrictionSelected, "No Restriction option");
				click(PWAHamburgerMenuPage.objContinueButton, "Continue Button");
				break;
			}

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Parent Control Setting", "A");
			mixpanel.FEProp.setProperty("Setting Changed", "A");
			mixpanel.FEProp.setProperty("Page Name", "parental_control");

			mixpanel.ValidateParameter(local.getItem("ID"), "Parental_Restriction");
		}
	}

	public void verifyShareEventFromPlaybackPage(String keyword1) throws Exception {

		extent.HeaderChildNode("Verify Share Event From Playback Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		click(PWAPlayerPage.shareBtn, "Share Option");
		click(PWAPlayerPage.facebookShareBtn, "Facebook share option");
		switchToWindow(2);
		Thread.sleep(2000);

		if (checkElementDisplayed(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
			click(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(2000);
			verifyAlert();
			waitTime(2000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		getWebDriver().close();
		switchToWindow(1);
		waitTime(3000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Sharing Platform", "Facebook");

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Share");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Share");
		}
	}

	public void verifyRemoveFomWatchlistEventFromPlaybackPage(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Remove From Watchlist Event From Playback Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");

			if (checkElementDisplayed(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon")) {
				click(PWAPlayerPage.objAddToWatchlist, "Watchlist icon");
				waitTime(4000);
				click(PWAPlayerPage.objRemoveFromWatchlist, "Remove From Watchlist icon");
			} else {
				click(PWAPlayerPage.objRemoveFromWatchlist, "Remove From Watchlist icon");
				waitTime(4000);
			}
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Remove From Watchlist");
		}
	}

	public void verifyAddtoWatchlistFromPlaybackPage(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Add to Watchlist Event From Playback Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");

			if (checkElementDisplayed(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon")) {
				click(PWAPlayerPage.objAddToWatchlist, "Watchlist icon");
			} else {
				click(PWAPlayerPage.objRemoveFromWatchlist, "Remove From Watchlist icon");
				waitTime(4000);
				click(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon");
				waitTime(4000);
			}
			waitTime(4000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			mixpanel.ValidateParameter(local.getItem("ID"), "Add To Watchlist");
		}
	}

	public void verifyAddToWatchlistEventByMouseHover(String userType) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Add to Watchlist Event by mouse hovering on a Content Card");
			waitTime(5000);
			navigateToAnyScreenOnWeb("Movies");
			waitTime(5000);
			scrollByWEB();
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInViewAllPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon")) {
				click(PWAPlayerPage.objAddToWatchlist, "Watchlist icon");
			} else {
				click(PWAPlayerPage.objRemoveFromWatchlist, "Remove From Watchlist icon");
				waitTime(3000);
				actions.moveToElement(contentCard).build().perform();
				click(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon");
				waitTime(4000);
			}
			waitTime(4000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_landing");
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			mixpanel.ValidateParameter(local.getItem("ID"), "Add To Watchlist");
		}

	}

	public void verifyRemoveFomWatchlistEventByMouseHoverInShowDetailPage(String userType, String keyword)
			throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Verify Remove from Watchlist Event by mouse hovering on a Content Card In show detail page");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon")) {
				click(PWAPlayerPage.objAddToWatchlist, "Watchlist icon");
			} else {
				click(PWAPlayerPage.objRemoveFromWatchlist, "Remove From Watchlist icon");
				waitTime(3000);
				actions.moveToElement(contentCard).build().perform();
				click(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon");
				waitTime(4000);
			}
			waitTime(4000);
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "show_detail");
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Remove From Watchlist");

		}

	}

	public void verifyShareEventByMouseHover() throws Exception {

		extent.HeaderChildNode("Verify Share Event By Mouse Hovering on a Content Card");
		waitTime(5000);
		navigateToAnyScreenOnWeb("Movies");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInViewAllPage);

		actions.moveToElement(contentCard).build().perform();
		verifyElementPresentAndClick(PWAPremiumPage.objContentCardShareBtn, "Share icon");

		click(PWAPlayerPage.facebookShareBtn, "Facebook share option");

		switchToWindow(2);
		Thread.sleep(2000);

		if (checkElementDisplayed(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");

			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(2000);
			verifyAlert();
			waitTime(2000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		getWebDriver().close();
		switchToWindow(1);
		waitTime(3000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_landing");
		mixpanel.FEProp.setProperty("Sharing Platform", "Facebook");

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Share");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Share");
		}
	}

	public void verifySearchCancelledEvent() throws Exception {
		extent.HeaderChildNode("Verify Search Cancelled Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		click(PWASearchPage.objSearchCancel, "Close Button");
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "search");
		mixpanel.FEProp.setProperty("Results Returned", "0");
		mixpanel.FEProp.setProperty("Search Query", "N/A");
		mixpanel.FEProp.setProperty("Search Type", "N/A");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Search Cancelled");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Search Cancelled");
		}
	}

	public void verifyShareEventFromShowDetailPage(String keyword) throws Exception {

		extent.HeaderChildNode("Verify Share Event From Show Detail Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");

		click(PWAPlayerPage.shareBtn, "Share Option");
		click(PWAPlayerPage.facebookShareBtn, "Facebook share option");

		switchToWindow(2);
		Thread.sleep(2000);

		if (checkElementDisplayed(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");

			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(2000);
			verifyAlert();
			waitTime(2000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		getWebDriver().close();
		switchToWindow(1);
		waitTime(3000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");
		mixpanel.FEProp.setProperty("Sharing Platform", "Facebook");

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Share");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Share");
		}
	}

	public void verifyEpisodeListChosenEventFromShowDetailPage(String keyword) throws Exception {
		extent.HeaderChildNode("Verify Episode List Chosen Event in Show Detail page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");
		waitTime(4000);
		click(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown");
		click(PWAShowsPage.objShowDetailEpisodeDropdownValues(2), "Episodes 11-20");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");
		mixpanel.FEProp.setProperty("Series", "Jodi Hakki");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Episode list Chosen");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Episode list Chosen");
		}
	}

	public void verifyContentBucketSwipeEventFromShowDetailPage(String keyword) throws Exception {
		extent.HeaderChildNode("Verify Content Bucket Swipe Event in Show Detail page");

		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");
		click(PWAPremiumPage.objRightArrowBtn, "Right Arrow Button");
		String TrayTitle = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]")).getText();
		String link = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]"))
				.getAttribute("href");
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(link);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		mixpanel.FEProp.setProperty("Carousal ID", value);
		mixpanel.FEProp.setProperty("Carousal Name", TrayTitle);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");
		mixpanel.FEProp.setProperty("Element", "right-arrow");
		mixpanel.FEProp.setProperty("Direction", "Right");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Content Bucket Swipe");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Content Bucket Swipe");
		}

	}

	public void verifyViewMoreSelectedEventFromShowDetailPage(String keyword) throws Exception {
		extent.HeaderChildNode("Verify View More Selected Event For content played from Show detail page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");
		waitTime(4000);
		verifyElementPresentAndClick(PWAPremiumPage.objViewAllBtn, "View All Button");

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Element", "View All");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");

		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "View More Selected");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "View More Selected");
		}
	}

	public void verifyViewMoreSelectedEventFromPlaybackPage(String audioTrackContent, String userType)
			throws Exception {
		extent.HeaderChildNode("Verify View More Selected Event For content played from Playback page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
		waitTime(4000);
		click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
		waitTime(4000);
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWAPremiumPage.objViewAllBtn, "View All Button");

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Element", "View All");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");

		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "View More Selected");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "View More Selected");
		}
	}

	public void verifyViewMoreSelectedEventFromTray() throws Exception {
		extent.HeaderChildNode("Verify View More Selected Event For content played from Tray");
		waitTime(5000);
		verifyElementPresentAndClick(PWAPremiumPage.objViewAllBtn, "View All Button");

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "sign_in");
		mixpanel.FEProp.setProperty("Element", "View All");
		mixpanel.FEProp.setProperty("Page Name", "home");

		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "View More Selected");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "View More Selected");
		}
	}

	public void verifyRemoveFromWatchlistEventFromMyWatchlistPage(String userType, String keyword) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Remove From Watchlist Event for Content from My Watchlist page");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword), "Search Result");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			waitTime(4000);

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");
			verifyElementPresentAndClick(PWAAddToWatchListPage.objRemoveContentsInWatchList,
					"Remove From Watchlist option");
			waitTime(3000);
			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Remove From Watchlist");
		}
	}

	/**
	 * Function to Relaunch the driver
	 */
	public void relaunch(boolean clearData) throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getWebDriver().quit();
		relaunch = clearData;
		new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.ValidateParameter(local.getItem("guestToken"), "Session");
	}

	/**
	 * Function to Relaunch the driver
	 */
	public void relaunch() throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getWebDriver().quit();
		new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
			if(!userType.equals("Guest")) {
				ZeeWEBPWAMixPanelLogin(userType);
			}
	}

	public void verifyQualityChangeEvent(String keyword1) throws Exception {

		extent.HeaderChildNode("Verify Quality Change Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitTime(5000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(1), "Quality option");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Source", "search");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "news_landing");
		mixpanel.FEProp.setProperty("Page Name", "search");
		mixpanel.FEProp.setProperty("Preview status", "Minutely");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}

	}

	public void verifyContentBucketSwipeEventInPlaybackPage(String keyword1) throws Exception {

		extent.HeaderChildNode("Verify Content Bucket Swipe Event in Playback page");

		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitTime(5000);

		click(PWAPremiumPage.objNextArrowBtn, "Right Arrow Button");
		String TrayTitle = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]")).getText();
		String link = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]"))
				.getAttribute("href");
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(link);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		mixpanel.FEProp.setProperty("Carousal ID", value);
		mixpanel.FEProp.setProperty("Carousal Name", TrayTitle);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Element", "right-arrow");
		mixpanel.FEProp.setProperty("Direction", "Right");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Content Bucket Swipe");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Content Bucket Swipe");
		}
	}

	public void verifyDisplayLanguageChangeEvent() throws Exception {
		extent.HeaderChildNode("Verify Display Language Change Event");
		verifyElementPresentAndClick(PWAHomePage.objLanguageBtn, "Language button");
		JSClick(PWALanguageSettingsPage.objFirstLanguage, "Hindi display language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		JSClick(PWALanguageSettingsPage.objAllLangByindex(1), "Hindi content language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		waitTime(5000);
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Display Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Display Language Change");
		}
	}

	public void verifyContentLanguageChangeEvent() throws Exception {
		extent.HeaderChildNode("Verify Content Language Change Event");

		verifyElementPresentAndClick(PWAHomePage.objLanguageBtn, "Language button");
		if (userType.equals("Guest")) {
			mixpanel.FEProp.setProperty("Old Content Language:",
					ResponseInstance.getUserOldSettingsDetails("", "").getProperty("content_language"));
		} else {
			mixpanel.FEProp.setProperty("Old Content Language:",
					ResponseInstance.getUserOldSettingsDetails(Username, Password).getProperty("content_language"));
		}
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		JSClick(PWALanguageSettingsPage.objAllLangByindex(1), "Hindi content language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		waitTime(3000);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Content Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Content Language Change");
		}
	}

	public void verifyContentBucketSwipeEvent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Content Bucket Swipe Event Across tabs");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objNextArrowBtn, "Right Arrow Button");
		String TrayTitle = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]")).getText();
		String link = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]"))
				.getAttribute("href");
		Pattern p = Pattern.compile("\\/([^\\/]+)\\/?$");
		Matcher m = p.matcher(link);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		mixpanel.FEProp.setProperty("Carousal ID", value);
		mixpanel.FEProp.setProperty("Carousal Name", TrayTitle);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "tv_shows_view_all");
		mixpanel.FEProp.setProperty("Element", "right-arrow");
		mixpanel.FEProp.setProperty("Direction", "Right");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Content Bucket Swipe");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Content Bucket Swipe");
		}
	}

	public void verifyCarouselBannerSwipeEvent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Carousel Banner Swipe Event Across tabs");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objRightArrowBtn, "Right Arrow Button");

		waitTime(5000);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "tv_shows_view_all");
		mixpanel.FEProp.setProperty("Element", "right-arrow");
		mixpanel.FEProp.setProperty("Direction", "Right");

		mixpanel.ValidateParameter(local.getItem("ID"), "Carousal Banner Swipe");

	}

	public void verifyAdBannerImpressionEvent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Ad Banner Impression Event Across tabs");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		checkElementDisplayed(PWAHomePage.objAdBanner, "Ad Banner");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Banner Impression");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Ad Banner Impression");
		}
	}

	public void verifyDefaultSettingRestoredEvent() throws Exception {
		extent.HeaderChildNode("Verify Default Setting Restored Event");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		click(PWAHamburgerMenuPage.objMoreSettingInHamburger, "More settings");
		click(PWAHamburgerMenuPage.objResetSettingsToDefault, "Reset Settings to Default");
		waitTime(3000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "settings");
		mixpanel.FEProp.setProperty("Setting Changed", "Default Setting Restored");
		mixpanel.FEProp.setProperty("Element", "Reset Settings to default");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Default Setting Restored");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Default Setting Restored");
		}
	}

	public void FilterLanguage(String lang) throws Exception {
		click(PWALiveTVPage.objFilterLanguageChannelGuide, "Filter language");
		int size = findElements(PWALiveTVPage.objSelectedlang).size();
		for (int i = 1; i <= size; i++) {
			click(PWALiveTVPage.objSelectedlang, "Selected language");
		}
		click(PWALiveTVPage.objSelectLang(lang), lang + " language");
		click(PWALiveTVPage.objApplyBtn, "Apply button");
	}

	public void verifyShareEventForUpcomingProgram() throws Exception {
		extent.HeaderChildNode("Verify Share Event For Upcoming Program");
		navigateToAnyScreenOnWeb("Live TV");

		waitTime(5000);
		wouldYouLikeToPopupClose();
		click(PWALiveTVPage.objChannelGuideToggle, "Channel Guide");
		click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
		FilterLanguage("Bengali");
		click(PWALiveTVPage.objBanglaShow1, "Show detail");

		click(PWAPremiumPage.objContentCardShareBtn, "Share button");
		waitTime(3000);
		click(PWALiveTVPage.objFacebookShareBtn, "Share to Facebook");
		waitTime(3000);
		verifyAlert();
		switchToWindow(2);
		if (!checkElementDisplayed(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook")) {
			click(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(4000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		waitTime(7000);
		acceptAlert();
		waitTime(3000);
		switchToParentWindow();
		waitTime(3000);
		mixpanel.FEProp.setProperty("Source", "live_tv");
		mixpanel.FEProp.setProperty("Page Name", "tv_guide");
		mixpanel.FEProp.setProperty("Sharing Platform", "Facebook");

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Share");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Share");
		}
	}

	public void verifySetReminderEventForUpcomingProgram(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Set Reminder Event");
			navigateToAnyScreenOnWeb("Live TV");
			wouldYouLikeToPopupClose();
			click(PWALiveTVPage.objChannelGuideToggle, "Channel Guide");
			click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
			FilterLanguage("Bengali");
			click(PWALiveTVPage.objBanglaShow1, "Show detail");

			if (checkElementDisplayed(PWALiveTVPage.objSetReminder, "Reminder")) {
				click(PWALiveTVPage.objSetReminder, "Reminder option");
				waitTime(3000);
			} else {
				click(PWALiveTVPage.objSetReminderOn, "Reminder option");
				waitTime(3000);
				click(PWALiveTVPage.objSetReminder, "Reminder option");
			}
			mixpanel.FEProp.setProperty("Source", "live_tv");
			mixpanel.FEProp.setProperty("Page Name", "tv_guide");
			mixpanel.FEProp.setProperty("element", "Set Reminder");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Set Reminder");
		}

	}

	public void wouldYouLikeToPopupClose() throws Exception {
		if (checkElementDisplayed(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
		}
	}

	public void verifyChangePasswordStartedEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Change Password Started Event");
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igsindia123", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igszee5", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igszee5", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igsindia123", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igsindia123", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Update");
			mixpanel.FEProp.setProperty("Page Name", "my_profile");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Change Password Started");
		}
	}

	public void verifyChangePasswordResultEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Change Password Result Event");
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igsindia123", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igszee5", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);

			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igszee5", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igsindia123", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igsindia123", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Update");
			mixpanel.FEProp.setProperty("Page Name", "my_profile");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Change Password Result");
		}
	}

	public void verifyProfileUpdateResultEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Profile Update Result Event");
			waitTime(5000);
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
			verifyElementPresent(PWAHamburgerMenuPage.objEditProfileTextWEB, "edit profile page");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileFirstName, "First name column");
			clearField(PWAHamburgerMenuPage.objEditProfileFirstName, "email field");
			type(PWAHamburgerMenuPage.objEditProfileFirstName, "Zee5", "Editprofile first name");

			verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "save changes");
			waitTime(2000);

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "my_profile");
			mixpanel.FEProp.setProperty("Partner Name", "Zee5");
			mixpanel.ValidateParameter(local.getItem("ID"), "Profile Update Result");

		}
	}


	public void verifyBannerAutoplayEventForNewsContent() throws Exception {
		extent.HeaderChildNode("Verify Banner Autoplay Event For News Content");
		navigateToAnyScreenOnWeb("News");
		waitForElementDisplayed(PWANewsPage.objBannerUnMute, 20);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "news_landing");
		mixpanel.FEProp.setProperty("Page Name", "news_landing");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video Autoplay", "true");
		mixpanel.FEProp.setProperty("Tab Name", "news_landing");
		String id = findElement(PWANewsPage.objAutoPlayContent).getAttribute("href");
		Pattern p = Pattern.compile("\\/([^\\/]+)\\/?$");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Banner Autoplay");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Banner Autoplay");
		}
	}

	public void verifyMuteChangedEventForNewsContent() throws Exception {
		extent.HeaderChildNode("Verify Mute Changed Event");
		navigateToAnyScreenOnWeb("News");
		waitForElementDisplayed(PWANewsPage.objVolume, 30);

		JSClick(PWANewsPage.objVolume, "Mute Icon");
		mixpanel.FEProp.setProperty("Source", "news_landing");
		mixpanel.FEProp.setProperty("Element", "Mute");
		mixpanel.FEProp.setProperty("Page Name", "news_landing");
		mixpanel.FEProp.setProperty("Tab Name", "news_landing");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
	
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Mute Changed");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Mute Changed");
		}
		
	}

	public void verifyResumeEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		waitTime(2000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Resume Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Element", "Resume");
			mixpanel.FEProp.setProperty("Button Type", "Player");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
			}

		}
	}

	public void verifyResumeEventForTrailer(String userType, String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Resume Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Element", "Resume");
			mixpanel.FEProp.setProperty("Button Type", "Player");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
			}

		}
	}

	public void verifyResumeEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForContentInPlaylist(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();

		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyVideoViewEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}

	}

	public void verifyVideoViewEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Video View Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			waitTime(8000);
			partialScrollDown();
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Video View", "1");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
			}

		}
	}

	public void verifyVideoViewEventForTrailer(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}

	}

	public void verifyVideoViewEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}

	}

	public void verifyVideoViewEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}

	}

	public void verifyVideoViewEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}

	}

	public void verifyVideoViewEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Video View Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Video View", "1");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
			}

		}
	}

	public void verifyVideoViewEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}

	}

	public void verifyVideoViewEventForContentInPlaylist(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
	}

	public void verifyVideoViewEventAfterRefreshingPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video View Event after refreshing a page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		getWebDriver().navigate().refresh();
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
	}

	public void verifyVideoExitEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
	}

	public void verifyVideoExitEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Video Exit Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			Back(1);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
			}
		}
	}

	public void verifyVideoExitEventForTrailer(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}

	}

	public void verifyVideoExitEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
	}

	public void verifyVideoExitEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
	}

	public void verifyVideoExitEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
	}

	public void verifyVideoExitEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Video Exit Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			Back(1);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
			}
		}
	}

	public void verifyVideoExitEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}

	}

	public void verifyVideoExitEventForContentInPlaylist(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");

		waitTime(6000);
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
	}

	public void verifyVideoExitEventAfterRefreshingPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event after refreshing a page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		getWebDriver().navigate().refresh();
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
	}

	public void verifyVideoWatchDurationEventForFreeContentAbrupt(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when video is closed abruptly For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForPremiumContentAbrupt(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode(
					"Verify Video Watch Duration Event when video is closed abruptly For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			Back(1);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
			}
		}
	}

	public void verifyVideoWatchDurationEventForTrailerAbrupt(String userType, String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when video is closed abruptly For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForCarouselContentAbrupt() throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when video is closed abruptly For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForContentInTrayAbrupt() throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}

	}

	public void verifyVideoWatchDurationEventForContentFromSearchPageAbrupt(String keyword1) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageAbrupt(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Verify Video Watch Duration Event when video is closed abruptly For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			Back(1);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
			}
		}
	}

	public void verifyVideoWatchDurationEventForContentInMegamenuAbrupt() throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();
		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForContentInPlaylistAbrupt(String userType, String keyword4)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyPopUpLaunchEventForGetPremiumPopUp(String userType, String keyword2) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Pop Up Launch Event when get premium popup is displayed on playing premium content");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword2), "Search Result");
			checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP");
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Popup launch");
		}
	}

	public void verifyPauseEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		waitTime(2000);
		click(PWAPremiumPage.obj1stContentInViewAllPage, "Content card");
		waitTime(2000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyPauseEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Pause Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Element", "Pause");
			mixpanel.FEProp.setProperty("Button Type", "Player");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
			}
		}
	}

	public void verifyPauseEventForTrailer(String userType, String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyPauseEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyPauseEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyPauseEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyPauseEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Pause Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Element", "Pause");
			mixpanel.FEProp.setProperty("Button Type", "Player");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
			}
		}
	}

	public void verifyPauseEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyPauseEventForContentInPlaylist(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyPauseEventForLinearContent() throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Linear Content");
		navigateToAnyScreenOnWeb("News");
		click(PWAPremiumPage.objWEBMastheadCarousel, "Linear Content");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

		mixpanel.FEProp.setProperty("Source", "news_landing");
		mixpanel.FEProp.setProperty("Page Name", "channel_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyResumeEventForLinearContent() throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Linear Content");
		navigateToAnyScreenOnWeb("News");
		click(PWAPremiumPage.objWEBMastheadCarousel, "Linear Content");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "news_landing");
		mixpanel.FEProp.setProperty("Page Name", "channel_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}
	}

	public void verifyQualityChangeEventForLinearContent() throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Linear Content");
		navigateToAnyScreenOnWeb("News");
		click(PWAPremiumPage.objWEBMastheadCarousel, "Linear Content");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		mixpanel.FEProp.setProperty("Source", "news_landing");
		mixpanel.FEProp.setProperty("Page Name", "channel_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Quality Change Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.settingsBtn, "Setting icon");
			click(PWAPlayerPage.qualityBtn, "Quality option");
			click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Old Quality", "Auto");
			mixpanel.FEProp.setProperty("New Quality", "Good");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
			}
		}
	}

	public void verifyQualityChangeEventForTrailer(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Quality Change Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.settingsBtn, "Setting icon");
			click(PWAPlayerPage.qualityBtn, "Quality option");
			click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Old Quality", "Auto");
			mixpanel.FEProp.setProperty("New Quality", "Good");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
			}
		}
	}

	public void verifyQualityChangeEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}

	}

	public void verifyQualityChangeEventForContentInPlaylist(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void playerScrubTillLastWeb() {
		WebElement scrubber = getWebDriver().findElement(PWAPlayerPage.objPlayerScrubber);
		WebElement progressBar = getWebDriver().findElement(PWAPlayerPage.objPlayerProgressBar);
		Actions action = new Actions(getWebDriver());
		action.clickAndHold(scrubber).moveToElement(progressBar, 350, 0).release().perform();
	}

	public void verifyResumeEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Resume Event for content autoplayed from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");

		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}
	}

	public void verifyPauseEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Pause Event for content autoplayed from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyVideoViewEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video View Event for content autoplayed from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
	}

	public void verifyVideoExitEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event for content autoplayed from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
	}

	public void verifyVideoViewEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
	}

	public void verifyVideoExitEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
	}

	public void verifyPauseEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyResumeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}
	}

	public void verifyVideoWatchDurationEventForContentFromSharedLinkAbrupt(String freeContentURL) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForContentFromSharedLinkComplete(String freeContentURL) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches the content playback shared through shared link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}

	}

	public void verifyVideoWatchDurationEventForFreeContentComplete(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration when user completely watches For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForPremiumContentComplete(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Video Watch Duration Event when user completely watches Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(6000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
			}
		}
	}

	public void verifyVideoWatchDurationEventForTrailerComplete(String userType, String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event  when user completely watches Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForCarouselContentComplete() throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when user completely watches Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForContentInTrayComplete() throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForContentFromSearchPageComplete(String keyword1) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageComplete(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Verify Video Watch Duration Event when user completely watches Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
		
			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
			}
		}
	}

	public void verifyVideoWatchDurationEventForContentInMegamenuComplete() throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
	
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		
		
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForContentInPlaylistComplete(String userType, String keyword4)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForContentFromUpnextRailComplete(String userType, String keyword4)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event When user completely watches the  auto-played content from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");

		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}

	}

	public void verifyVideoWatchDurationEventForContentFromUpnextRailAbrupt(String userType, String keyword4)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly on auto-played content from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");

		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(5000);
		Back(1);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyScrubSeekEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Scrub/Seek Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Direction", "forward");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
			}
		}
	}

	public void verifyScrubSeekEventForTrailer(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Scrub/Seek Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Direction", "forward");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
			}
		}
	}

	public void verifyScrubSeekEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForContentInPlaylist(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();

		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyMuteChangedEventDuringContentPlayback(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Mute Changed Event During Content Playback");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.audioBtn, "Mute Icon");
		waitTime(2000);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Element", "Mute");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Mute Changed");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Mute Changed");
		}
	}

	public void verifyLoginInitiatedEventForInvalidCredentials(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event post entering invalid credentials");

			switch (loginMethod) {

			case "mobileNumberLogin":
				click(PWALoginPage.objLoginBtnWEB, "Login button");
				waitForElementDisplayed(PWALoginPage.objEmailField, 5);
				click(PWALoginPage.objEmailField, "Email field");
				type(PWALoginPage.objEmailField, "7892215214", "Phone Number Field");
				click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
				break;

			case "emailLogin":
				String Username = getParameterFromXML("NonsubscribedUserName");
				String Password = getParameterFromXML("NonsubscribedInvalidPassword");
				click(PWALoginPage.objWebLoginBtn, "Login button");
				waitTime(3000);
				click(PWALoginPage.objEmailField, "Email field");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				click(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, Password, "Password field");
				waitTime(5000);
				click(PWALoginPage.objWebLoginButton, "Login Button");
				waitTime(3000);
				break;
			}
		}
	}

	public void verifyLoginResultEventForInvalidCredentials(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event post entering invalid credentials");

			switch (loginMethod) {

			case "mobileNumberLogin":
				click(PWALoginPage.objLoginBtnWEB, "Login button");
				waitForElementDisplayed(PWALoginPage.objEmailField, 5);
				click(PWALoginPage.objEmailField, "Email field");
				type(PWALoginPage.objEmailField, "7892215214", "Phone Number Field");
				click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
				type(PWASignupPage.objOTP1, "1", "OTP box1");
				type(PWASignupPage.objOTP2, "2", "OTP box2");
				type(PWASignupPage.objOTP3, "3", "OTP box3");
				type(PWASignupPage.objOTP4, "4", "OTP box4");
				waitTime(3000);
				click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
				mixpanel.FEProp.setProperty("Source", "sign_in");
				mixpanel.FEProp.setProperty("Element", "Cross");
				mixpanel.FEProp.setProperty("Page Name", "otp_page");
				mixpanel.FEProp.setProperty("Failure Reason", "Either OTP is not valid or has expired");
				mixpanel.FEProp.setProperty("Success", "false");
				mixpanel.FEProp.setProperty("method", "Social");
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				mixpanel.ValidateParameter(local.getItem("ID"), "Login Result");
				break;

			case "emailLogin":
				String Username = getParameterFromXML("NonsubscribedUserName");
				String Password = getParameterFromXML("NonsubscribedInvalidPassword");
				click(PWALoginPage.objWebLoginBtn, "Login button");
				waitTime(3000);
				click(PWALoginPage.objEmailField, "Email field");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				click(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, Password, "Password field");
				waitTime(5000);
				click(PWALoginPage.objWebLoginButton, "Login Button");
				waitTime(3000);
				break;
			}

		}
	}

	public void verifyToastMessageImpressionEventInSignInScreen(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Toast Message Impression Event In Sign In Screen");
			click(PWALoginPage.objLoginBtnWEB, "Login button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "sign_in");
			mixpanel.FEProp.setProperty("Page Name", "otp_page");
			mixpanel.FEProp.setProperty("Toast Message", "OTP has been sent to your registered Mobile Number!");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
		}
	}

	public void verifyToastMessageImpressionEventInOTPScreen(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Toast Message Impression Event In OTP Screen");
			click(PWALoginPage.objLoginBtnWEB, "Login button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
			type(PWASignupPage.objOTP1, "1", "OTP box1");
			type(PWASignupPage.objOTP2, "2", "OTP box2");
			type(PWASignupPage.objOTP3, "3", "OTP box3");
			type(PWASignupPage.objOTP4, "4", "OTP box4");
			waitTime(3000);
			click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "sign_in");
			mixpanel.FEProp.setProperty("Page Name", "otp_page");
			mixpanel.FEProp.setProperty("Toast Message", "Either OTP is not valid or has expired");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
		}
	}

	public void verifyRegistrationInitiatedEventForInvalidCredentials(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Registration Initiated Event post entering invalid credentials");
			click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "9073258519", "PhoneNumber Field");
			waitTime(3000);
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Send OTP");
			waitTime(5000);
			
			mixpanel.FEProp.setProperty("Source", "register");
			mixpanel.FEProp.setProperty("Page Name", "otp_page");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Registration Initiated");
		}
	}

	public void verifyRegistrationResultEventForInvalidCredentials(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Registration Result Event post entering invalid credentials");
			click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "9073258519", "PhoneNumber Field");
			waitTime(3000);
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Send OTP");
			waitTime(3000);
			type(PWASignupPage.objOTP1, "1", "OTP box1");
			type(PWASignupPage.objOTP2, "2", "OTP box2");
			type(PWASignupPage.objOTP3, "3", "OTP box3");
			type(PWASignupPage.objOTP4, "4", "OTP box4");
			waitTime(3000);
			click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "register");
			mixpanel.FEProp.setProperty("Page Name", "otp_page");
			mixpanel.FEProp.setProperty("Failure Reason", "Either OTP is not valid or has expired");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Registration Result");
		}
	}

	public void verifyToastMessageImpressionEventInSignUpScreen(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Toast Message Impression Event In Sign Up Screen");
			click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "register");
			mixpanel.FEProp.setProperty("Page Name", "otp_page");
			mixpanel.FEProp.setProperty("Toast Message", "OTP has been sent to your registered Mobile Number!");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
		}
	}

	public void verifySubscriptionCallReturnedEvent(String userType) throws Exception {
		extent.HeaderChildNode(
				"Subscription Call Returned Event when user makes unsuccessful transaction by quitting the payment gateway screen");

		if (!(userType.equals("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			mixpanel.FEProp.setProperty("Page Name", "payment_page");
			mixpanel.FEProp.setProperty("Source", "account_info");
			String[] cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount).split(" ");
			mixpanel.FEProp.setProperty("Transaction Currency", cost[0]);
			mixpanel.FEProp.setProperty("cost", cost[1]);
			mixpanel.FEProp.setProperty("Payment Method", "mastercard");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
				}
			}
			waitTime(10000);
			WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);

			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			type(PWASubscriptionPages.objEnterCardNumber, "5123456789012346", "Card Number");
			click(PWASubscriptionPages.objEnterExpiry, "Expiry");
			type(PWASubscriptionPages.objEnterExpiry, "0224", "Expiry");
			click(PWASubscriptionPages.objEnterCVV, "CVV");
			type(PWASubscriptionPages.objEnterCVV, "123", "CVV");
			click(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
			waitTime(10000);
			click(PWASubscriptionPages.objZeeLink, "Zee link");
			waitTime(5000);

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Subscription Call Returned");

		}
	}

	public void verifySubscriptionCallInitiatedEvent(String userType) throws Exception {
		extent.HeaderChildNode("Subscription Call Initiated Event for All access pack");

		if (!(userType.equals("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			mixpanel.FEProp.setProperty("Page Name", "payment_page");
			mixpanel.FEProp.setProperty("Source", "account_info");
			String[] cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount).split(" ");
			mixpanel.FEProp.setProperty("Transaction Currency", cost[0]);
			mixpanel.FEProp.setProperty("cost", cost[1]);
			mixpanel.FEProp.setProperty("Payment Method", "mastercard");

			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
				}
			}
			waitTime(10000);
			WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);

			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			type(PWASubscriptionPages.objEnterCardNumber, "5123456789012346", "Card Number");
			click(PWASubscriptionPages.objEnterExpiry, "Expiry");
			type(PWASubscriptionPages.objEnterExpiry, "0224", "Expiry");
			click(PWASubscriptionPages.objEnterCVV, "CVV");
			type(PWASubscriptionPages.objEnterCVV, "123", "CVV");
			click(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
			waitTime(10000);

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Subscription Call Initiated");

		}
	}

	/**
	 * Login through ClubUser Id
	 * 
	 * @param userType
	 * @param keyword6
	 * @throws Exception
	 */
	public void verifySubscriptionCallInitiatedEventClubPack(String userType) throws Exception {
		extent.HeaderChildNode("Subscription Call Initiated Event for Club pack");

		if (userType.equals("ClubUser")) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			click(PWASubscriptionPages.objClubPack, "Club Pack");
			click(PWASubscriptionPages.objPackAmount1, "Pack");
			mixpanel.FEProp.setProperty("Page Name", "payment_page");
			mixpanel.FEProp.setProperty("Source", "account_info");
			String[] cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount).split(" ");
			mixpanel.FEProp.setProperty("Transaction Currency", cost[0]);
			mixpanel.FEProp.setProperty("cost", cost[1]);
			mixpanel.FEProp.setProperty("Payment Method", "mastercard");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
				}
			}
			waitTime(10000);
			WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);

			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			type(PWASubscriptionPages.objEnterCardNumber, "5123456789012346", "Card Number");
			click(PWASubscriptionPages.objEnterExpiry, "Expiry");
			type(PWASubscriptionPages.objEnterExpiry, "0224", "Expiry");
			click(PWASubscriptionPages.objEnterCVV, "CVV");
			type(PWASubscriptionPages.objEnterCVV, "123", "CVV");
			click(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
			waitTime(10000);

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Subscription Call Initiated");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyLogoutEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			waitTime(5000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Element", "LOGOUT");
			mixpanel.FEProp.setProperty("Page Name", "home");
			String ID = local.getItem("ID");
			logout();
			waitTime(5000);
			mixpanel.ValidateParameter(ID, "Logout");

		}
	}

	public void verifySkipLoginThroughBeforeTVContent() throws Exception {
		extent.HeaderChildNode("Verify Skip Login Event gets triggered when user click on close button in login popup "
				+ "on clicking login in Get premium popup on accessing before tv content");
		navigateToAnyScreenOnWeb("Shows");
		if (checkElementDisplayed(PWAHomePage.objFirstContentCardOfTray("Before"),
				"First Content Card Of Before TV Tray")) {
			click(PWAHomePage.objFirstContentCardOfTray("Before"), "First Content Card Of Before TV Tray");
			waitTime(20000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitForElement(PWASubscriptionPages.objGetPremiumPopupTitle, 10, "Get Premium Popup Title");
			click(PWALoginPage.objLoginCTAInPremiumPopup, "Login CTA");
			waitForElement(PWALoginPage.objSkip, 10, "Skip Login");
			waitTime(5000);
			click(PWALoginPage.objSkip, "Skip Login");
			waitTime(2000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login");
		}

	}

	public void verifySettingChangedEventAfterAccountVerification(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Setting Changed Event when Parental Control Account Verification is done");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			mixpanel.FEProp.setProperty("Source", "N/A");
			mixpanel.FEProp.setProperty("Page Name", "home");
			mixpanel.FEProp.setProperty("Element", "Continue");
			mixpanel.FEProp.setProperty("Setting Changed", "Parental Control Account Verification");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Setting Changed");
		}
	}

	public void verifySettingChangedEventAfterParentalPinIsSet(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Setting Changed Event when Parental Control PIN is Set");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objRestrictAll, "Restrict all option");
			click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(2000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "parental_control");
			mixpanel.FEProp.setProperty("Setting Changed", "Parental Control Age Set");
			mixpanel.FEProp.setProperty("Element", "Set parental lock");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Setting Changed");
		}
	}

	public void verifySettingChangedEventAfterAgeIsSet(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Setting Changed Event when Parental Control Age is Set");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objRestrict13PlusContent, "Restrict 13+ Content");
			click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(2000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "parental_control");
			mixpanel.FEProp.setProperty("Setting Changed", "Parental Control Age Set");
			mixpanel.FEProp.setProperty("Element", "Set parental lock");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U/A");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Setting Changed");
		}
	}

	public void verifyToastMessageImpressionEventAfterResetSettingsToDefault() throws Exception {
		extent.HeaderChildNode("Verify Toast Message Impression Event After Reset Settings To Default");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		click(PWAHamburgerMenuPage.objMoreSettingInHamburger, "More settings");
		click(PWAHamburgerMenuPage.objResetSettingsToDefault, "Reset Settings to Default");
		waitTime(5000);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "settings");
		mixpanel.FEProp.setProperty("Toast Message", "Changes Saved Successfully");

		if (userType.equals("Guest")) {
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
		} else {
			Mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}

	}

	public void verifySearchButtonClickEvent() throws Exception {
		extent.HeaderChildNode("Verify Search Button Click Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Element", "Search");
		mixpanel.FEProp.setProperty("Page Name", "home");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Search Button Click");
		} else {
			Mixpanel.ValidateParameter(local.getItem("ID"), "Search Button Click");
		}
	}

	public void verifySearchResultClickedEvent(String keyword) throws Exception {
		extent.HeaderChildNode("Verify Search Result Clicked Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword), "Search Result");
		waitTime(5000);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Search Result Clicked");
		} else {
			Mixpanel.ValidateParameter(local.getItem("ID"), "Search Result Clicked");
		}
	}

	public void verifyPopUpLaunchEventForRegisterPopUp(String userType, String keyword) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Pop Up Launch Event when Native popup is displayed");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");
			waitTime(4000);
			click(PWAPremiumPage.obj1stContentInViewAllPage, "Content From a tray");
			waitForElement(PWALoginPage.objCloseRegisterPopup, 10, "Register Pop Up");
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Popup launch");
		}
	}

	public void verifyPopUpLaunchEventForCompleteProfilePopUp(String userType) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Pop Up Launch Event when Complete Profile popup is displayed");

			logout();
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			extent.HeaderChildNode("Login through incomplete profile account");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, "indaus24@gmail.com", "Email Field");
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, "123456", "Password field");
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
			checkElementDisplayed(PWAHomePage.objSearchField, "Search field");
			String keyword = getParameterFromXML("freeMovie2");
			type(PWAHomePage.objSearchField, keyword, "Search");
			waitTime(5000);
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
			waitTime(3000);
			click(PWAPremiumPage.obj1stContentInViewAllPage, "Content From a tray");
			waitTime(4000);
			checkElementDisplayed(CompleteYourProfilePopUp.objCompleteYourProfileTxt, "Complete Your Profile");
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Popup launch");
		}
	}

	/**
	 * Login through ClubUser Id
	 * 
	 * @param userType
	 * @param keyword6
	 * @throws Exception
	 */
	public void verifyPopUpLaunchEventForClubUser(String userType, String keyword6) throws Exception {
		if (userType.equalsIgnoreCase("ClubUser")) {
			extent.HeaderChildNode("Verify Pop Up Launch Event when user gets Upgrade popup for Club User");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword6 + "\n", "Search Edit box: " + keyword6);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword6), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword6), "Search Result");
			waitForElement(PWAHamburgerMenuPage.objPopupClose, 10, "Upgrade Pop Up");
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Popup launch");
		}
	}

	public void verifyPopUpCTAsEvent(String userType, String keyword6) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Pop Up CTA's Event when user clicks on CTA button displayed on the popup");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword6 + "\n", "Search Edit box: " + keyword6);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword6), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword6), "Search Result");

			if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Element", "Cross");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			m.find();
			ResponseInstance.getContentDetails(m.group(0));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Pop Up CTAs");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Pop Up CTAs");
			}
		}
	}

	public void verifyToastMessageImpressionEventInParentalControlScreen(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Toast Message Impression Event In Parental Control Screen");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objRestrict13PlusContent, "Restrict 13+ Content");
			click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(2000);

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "parental_control");
			mixpanel.FEProp.setProperty("Toast Message", "Changes Saved Successfully");
			if (userType.equals("Guest")) {
				Mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
			} else {
				Mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
			}
		}
	}

	public void verifyDeviceAuthenticationEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Verify Device Authentication Event when authentication fails in TV Authentication screen");
			String AuthPin = "abcdef";
			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authenticate Device");
			type(PWAHamburgerMenuPage.objAuthenticationField, AuthPin, "AuthenticationField");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			mixpanel.FEProp.setProperty("Page Name", "device_authentication");
			mixpanel.FEProp.setProperty("Failure Reason", "Device code " + AuthPin + " has expired");
			mixpanel.FEProp.setProperty("Element", "Authenticate");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			System.out.println(local.getItem("ID"));
			mixpanel.ValidateParameter(local.getItem("ID"), "Device Authentication");
		}
	}

	public void verifyToastMessageImpressionEventInAuthenticateScreen(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Toast Message Impression Event In Authenticate Screen");

			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authenticate Device");
			type(PWAHamburgerMenuPage.objAuthenticationField, "abcdef", "AuthenticationField");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "device_authentication");
			mixpanel.FEProp.setProperty("Toast Message", "Device code ABCDEF has expired");
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}
	}

	public void verifyToastMessageImpressionEventForAddToWatchlist(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Toast Message Impression Event after adding card to watchlist");
			navigateToAnyScreenOnWeb("Movies");
			waitTime(3000);
			scrollByWEB();
			waitTime(3000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInViewAllPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			} else {
				click(PWAPremiumPage.objContentCardRemoveFromWatchlistBtn, "Remove From Watchlist icon");
				waitTime(3000);
				actions.moveToElement(contentCard).build().perform();
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_landing");
			mixpanel.FEProp.setProperty("Toast Message", "Added to Watchlist");
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}

	}

	public void verifyToastMessageImpressionEventForRemoveFomWatchlist(String userType) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Toast Message Impression Event after removing card from watchlist");

			waitTime(5000);
			navigateToAnyScreenOnWeb("Movies");
			scrollByWEB();
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInViewAllPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
				waitTime(3000);
				actions.moveToElement(contentCard).build().perform();
				click(PWAPremiumPage.objContentCardRemoveFromWatchlistBtn, "Remove From Watchlist icon");

			} else {
				click(PWAPremiumPage.objContentCardRemoveFromWatchlistBtn, "Remove From Watchlist icon");
				waitTime(3000);
			}
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_landing");
			mixpanel.FEProp.setProperty("Toast Message", "Removed from Watchlist");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}

	}

	public void verifyToastMessageImpressionEventForEmbedPopUp(String userType, String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Toast Message Impression Event when user gets a toast message in embed popup");

		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		click(PWAPremiumPage.objEmbedPopUp, "Embed option");
		click(PWAPremiumPage.objEmbedCopy, "Copy");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Toast Message", "Copied to Clipboard");

		if (userType.equals("Guest")) {
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
		} else {
			Mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}

	}

	public void verifyAutoSeekForwardEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Auto Seek Forward Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Direction", "forward");
			mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
			}
		}
	}

	public void verifyAutoSeekForwardEventForTrailer(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Auto Seek Forward Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			verifyElementPresentAndClick(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Direction", "forward");
			mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
			}
		}
	}

	public void verifyAutoSeekForwardEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentInPlaylist(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Auto Seek Rewind Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Direction", "backward");
			mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
			}
		}
	}

	public void verifyAutoSeekRewindEventForTrailer(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Auto Seek Rewind Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Direction", "backward");
			mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
			}
		}
	}

	public void verifyAutoSeekRewindEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentInPlaylist(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAudioLanguageChangeEventForFreeContent(String userType, String audioTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
		waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 20, "Search Result");
		click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyAudioLanguageChangeEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Audio Language Change Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.objPlayerSettings, "Settings icon");
			if (checkElementDisplayed(PWAPlayerPage.objPlayerAudioTrack, "Audio Track") == true) {
				click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
				click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
				waitTime(5000);

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
				}

			} else {
				logger.info("Audio Track is not available for the content");
				extent.extentLogger("Audio Track", "Audio Track is not available for the content");
			}
			waitTime(5000);

		}
	}

	public void verifyAudioLanguageChangeEventForTrailer(String audioTrackTrailerContent) throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackTrailerContent + "\n",
				"Search Edit box: " + audioTrackTrailerContent);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(audioTrackTrailerContent), 10, "Search Result");
		click(PWASearchPage.objSearchResult(audioTrackTrailerContent), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyAudioLanguageChangeEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		if (checkElementDisplayed(PWAPlayerPage.objPlayerAudioTrack, "Audio Track") == true) {
			click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
			click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
			}

		} else {
			logger.info("Audio Track is not available for the content");
			extent.extentLogger("Audio Track", "Audio Track is not available for the content");
		}

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}

	}

	public void verifyAudioLanguageChangeEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		if (checkElementDisplayed(PWAPlayerPage.objPlayerAudioTrack, "Audio Track") == true) {
			click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
			click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
			}

		} else {
			logger.info("Audio Track is not available for the content");
			extent.extentLogger("Audio Track", "Audio Track is not available for the content");
		}
	}

	public void verifyAudioLanguageChangeEventForContentFromSearchPage(String audioTrackContent) throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 10, "Search Result");
		click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyAudioLanguageChangeEventForContentFromMyWatchlistPage(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Audio Language Change Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.objPlayerSettings, "Settings icon");
			if (checkElementDisplayed(PWAPlayerPage.objPlayerAudioTrack, "Audio Track") == true) {
				click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
				click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
				waitTime(5000);

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
				}

			} else {
				logger.info("Audio Track is not available for the content");
				extent.extentLogger("Audio Track", "Audio Track is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAudioLanguageChangeEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		if (checkElementDisplayed(PWAPlayerPage.objPlayerAudioTrack, "Audio Track") == true) {
			click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
			click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
			}

		} else {
			logger.info("Audio Track is not available for the content");
			extent.extentLogger("Audio Track", "Audio Track is not available for the content");
		}
	}

	public void verifyAudioLanguageChangeEventForContentInPlaylist(String userType, String audioTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);

		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyAudioLanguageChangeEventForContentFromUpnextRail(String userType, String audioTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyAudioLanguageChangeEventForContentFromSharedLink(String audioTrackURL) throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For content played from Shared Link");
		getWebDriver().get(audioTrackURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Audio Language", "Hindi");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyToastMessageImpressionEventInPaymentPage(String userType) throws Exception {
		extent.HeaderChildNode("Verify Toast Message Impression Event in payment page");

		if (!(userType.equals("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
				}
			}
			waitTime(5000);

			click(PWASubscriptionPages.objExpandLess, "Gift card option");
			click(PWASubscriptionPages.objGiftCardNumber, "Gift Card Number");
			type(PWASubscriptionPages.objGiftCardNumber, "5123456789012346", "Gift Card Number");
			click(PWASubscriptionPages.objPin, "Pin");
			type(PWASubscriptionPages.objPin, "12345678", "Pin");
			click(PWASubscriptionPages.objPay, "Pay Button");
			waitTime(5000);

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();

			if (userType.equals("Guest")) {

				mixpanel.FEProp.setProperty("Source", "account_info");
				mixpanel.FEProp.setProperty("Page Name", "payment_page");
				mixpanel.FEProp.setProperty("Toast Message", "Could not find card. Please enter valid card number");
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
			} else {

				mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
				mixpanel.FEProp.setProperty("Source", "pack_selection");
				mixpanel.FEProp.setProperty("Page Name", "payment_page");
				mixpanel.FEProp.setProperty("Toast Message", "Could not find card. Please enter valid card number");
			}
		}
	}

	public void verifySubtitleLanguageChangeEventForFreeContent(String userType, String subtitleTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n", "Search Edit box: " + subtitleTrackContent);
		waitForElement(PWASearchPage.objSearchResult(subtitleTrackContent), 20, "Search Result");
		click(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");
		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
		}
	}

	public void verifySubtitleLanguageChangeEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Subtitle Language Change Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);

			if (checkElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle option") == true) {
				click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
				click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
				waitTime(5000);

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				mixpanel.FEProp.setProperty("New Subtitle Language", "English");

				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
				}

			} else {
				logger.info("Subtitle is not available for the content");
				extent.extentLogger("Subtitle Track", "Subtitle is not available for the content");
			}

		}
	}

	public void verifySubtitleLanguageChangeEventForTrailer(String keyword5) throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword5 + "\n", "Search Edit box: " + keyword5);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword5), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword5), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
		}
	}

	public void verifySubtitleLanguageChangeEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");

		if (checkElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle option") == true) {
			click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
			click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("New Subtitle Language", "English");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
			}

		} else {
			logger.info("Subtitle is not available for the content");
			extent.extentLogger("Subtitle Track", "Subtitle is not available for the content");
		}
	}

	public void verifySubtitleLanguageChangeEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle option") == true) {
			click(PWAPlayerPage.subtitlesBtn, "Subtitle option");
			click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("New Subtitle Language", "English");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
			}

		} else {
			logger.info("Subtitle is not available for the content");
			extent.extentLogger("Subtitle Track", "Subtitle is not available for the content");
		}
	}

	public void verifySubtitleLanguageChangeEventForContentFromSearchPage(String subtitleTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n", "Search Edit box: " + subtitleTrackContent);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(subtitleTrackContent), 10, "Search Result");
		click(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifySubtitleLanguageChangeEventForContentFromMyWatchlistPage(String userType,
			String subtitleTrackContent) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Subtitle Language Change Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			if (checkElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle option") == true) {
				click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
				click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
				waitTime(5000);

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				mixpanel.FEProp.setProperty("New Subtitle Language", "English");

				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
				}

			} else {
				logger.info("Subtitle is not available for the content");
				extent.extentLogger("Subtitle Track", "Subtitle is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifySubtitleLanguageChangeEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle option") == true) {
			click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
			click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("New Subtitle Language", "English");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
			}

		} else {
			logger.info("Subtitle is not available for the content");
			extent.extentLogger("Subtitle Track", "Subtitle is not available for the content");
		}
	}

	public void verifySubtitleLanguageChangeEventForContentInPlaylist(String userType, String subtitleTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n", "Search Edit box: " + subtitleTrackContent);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);

		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
		}

	}

	public void verifySubtitleLanguageChangeEventForContentFromUpnextRail(String userType, String subtitleTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n", "Search Edit box: " + subtitleTrackContent);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
		}

	}

	public void verifySubtitleLanguageChangeEventForContentFromSharedLink(String subtitleTrackURL) throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For content played from Shared Link");
		getWebDriver().get(subtitleTrackURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
		}

	}

	public void verifySkipIntroEventForFreeContent(String userType, String freeMovie2) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, freeMovie2 + "\n", "Search Edit box: " + freeMovie2);
		waitForElement(PWASearchPage.objSearchResult(freeMovie2), 20, "Search Result");
		click(PWASearchPage.objSearchResult(freeMovie2), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Element", "Skip Intro");
		mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
		}
	}

	public void verifySkipIntroEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Skip Intro Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);

			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button")) {
				click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Element", "Skip Intro");
				mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);

				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (UserType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
				}
			} else {
				logger.info("Skip Intro is not available for the content");
				extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifySkipIntroEventForTrailer(String keyword5) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword5 + "\n", "Search Edit box: " + keyword5);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword5), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword5), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
	}

	public void verifySkipIntroEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
	}

	public void verifySkipIntroEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
	}

	public void verifySkipIntroEventForContentFromSearchPage(String freeMovie2) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, freeMovie2 + "\n", "Search Edit box: " + freeMovie2);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(freeMovie2), 10, "Search Result");
		click(PWASearchPage.objSearchResult(freeMovie2), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Element", "Skip Intro");
		mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
		}
	}

	public void verifySkipIntroEventForContentFromMyWatchlistPage(String userType, String freeMovie2) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Skip Intro Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, freeMovie2 + "\n", "Search Edit box: " + freeMovie2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(freeMovie2), 10, "Search Result");
			click(PWASearchPage.objSearchResult(freeMovie2), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
				click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Element", "Skip Intro");
				mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (UserType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
				}
			} else {
				logger.info("Skip Intro is not available for the content");
				extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifySkipIntroEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}

		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
	}

	public void verifySkipIntroEventForContentInPlaylist(String userType, String freeMovie2) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, freeMovie2 + "\n", "Search Edit box: " + freeMovie2);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(freeMovie2), "Search Result");
		mandatoryRegistrationPopUp(userType);

		click(PWAPremiumPage.objThumbnail, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "kids_movie_detail");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
		waitTime(5000);
	}

	public void verifySkipIntroEventForContentFromUpnextRail(String userType, String freeMovie2) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, freeMovie2 + "\n", "Search Edit box: " + freeMovie2);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(freeMovie2), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "kids_movie_detail");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
		waitTime(5000);
	}

	public void verifySkipIntroEventForContentFromSharedLink(String skipIntroURL) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For content played from Shared Link");
		getWebDriver().get(skipIntroURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Element", "Skip Intro");
		mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
		Matcher m = p.matcher(id);
		String value = null;
		while (m.find()) {
			value = m.group(0);
		}
		ResponseInstance.getContentDetails(value);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
		}
	}

	public void verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel() throws Exception {
		if (!(UserType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Subscription Page Viewed Event By Clicking on Get Premium CTA On Carousel");

			navigateToAnyScreenOnWeb("Premium");
			JSClick(PWAPremiumPage.objGetPremiumCTAOnCarousel, "Get Premium CTA on carousel");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Page Viewed");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Subscription Page Viewed");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForFreeContent(String userType, String keyword4) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Free Content");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");

			click(PWAHamburgerMenuPage.objRestrictAll, "Restrict All");
			click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(3000);

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
			waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
			click(PWASearchPage.objSearchResult(keyword4), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForTrailer(String keyword1, String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Trailer Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForCarouselContent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Carousel Content");
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentInTray(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Content played from Tray");
			click(PWAPremiumPage.objThumbnail, "Content From a tray");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentFromSearchPage(String keyword1, String userType)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentInPlaylist(String userType, String keyword4)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Content played from Playlist");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			mandatoryRegistrationPopUp(userType);
			waitTime(6000);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentFromUpnextRail(String userType, String keyword4)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Content played from Upnext rail");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
			
			waitTime(4000);
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);			

			waitTime(5000);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentFromSharedLink(String freeContentURL, String userType)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For content played from Shared Link");
			getWebDriver().get(freeContentURL);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventAfterPageRefresh(String keyword1, String userType)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event after refreshing the page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(5000);
			getWebDriver().navigate().refresh();

			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");

			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayResultEventForFreeContent(String userType, String keyword4) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Free Content");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
			waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
			click(PWASearchPage.objSearchResult(keyword4), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");

			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}
		}
	}

	public void verifyParentalOverlayResultEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForTrailer(String keyword1, String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Trailer Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForCarouselContent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Carousel Content");
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForContentInTray(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Content played from Tray");
			click(PWAPremiumPage.objThumbnail, "Content From a tray");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForContentFromSearchPage(String keyword1, String userType)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}
		}
	}

	public void verifyParentalOverlayResultEventForContentInPlaylist(String userType, String keyword4)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Content played from Playlist");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			mandatoryRegistrationPopUp(userType);
			waitTime(6000);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}
		}
	}

	public void verifyParentalOverlayResultEventForContentFromUpnextRail(String userType, String keyword4)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Content played from Upnext rail");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);			

			waitTime(6000);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}
		}
	}

	public void verifyParentalOverlayResultEventForContentFromSharedLink(String freeContentURL, String userType)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For content played from Shared Link");
			getWebDriver().get(freeContentURL);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
			Matcher m = p.matcher(id);
			String value = null;
			while (m.find()) {
				value = m.group(0);
			}
			ResponseInstance.getContentDetails(value);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}
		}
	}

	public void verifyToastMessageImpressionEventAfterUpdatingProfile(String userType) throws Exception {
		extent.HeaderChildNode("Verify Toast Message Impression event when user updates the profile details");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(PWAHamburgerMenuPage.objProfileIconWEB, "Profile Icon");
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "Profile");
			click(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
			click(PWAHamburgerMenuPage.objEditProfileTextWEB, "Edit profile page");
			click(PWAHamburgerMenuPage.objEditProfileFirstName, "First name column");
			clearField(PWAHamburgerMenuPage.objEditProfileFirstName, "Email field");
			type(PWAHamburgerMenuPage.objEditProfileFirstName, "Zee5", "Editprofile first name");

			click(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "Save Changes Button");
			waitTime(2000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "my_profile");
			mixpanel.FEProp.setProperty("Toast Message", "Changes Saved Successfully");
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}
	}

	public void verifyToastMessageImpressionEventAfterChangingPassword(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Toast Message Impression event when user changes the password");
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igsindia123", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igszee5", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "my_profile");
			mixpanel.FEProp.setProperty("Toast Message", "Password was changed successfully");
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");

			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igszee5", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igsindia123", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igsindia123", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);

		}
	}

//	public void webScrollToElement(By Locator, String validationText) throws Exception {
//		for (int i = 1; i <= 10; i++) {
//			if (checkElementDisplayed(Locator, validationText)) {
//				break;
//			}
//			waitTime(2000);
//			scrollDownWEB();
//		}
//	}

	public void verifyRecommendedRailImpressionEventByScrollingPage(String tabname, String trayTitle) throws Exception {
		extent.HeaderChildNode(
				"Verify Recommended Rail Impression event when user is able to see the recommended tray by scrolling down the page");

		navigateToAnyScreenOnWeb(tabname);
		WebElement element = getWebDriver().findElement(PWAShowsPage.objTrayTitle1(trayTitle));
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		checkElementDisplayed(PWAHomePage.objRecoTray, "Recommended Rail");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "home");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Recommended Rail Impression");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Recommended Rail Impression");
		}

	}

	public void verifyRecommendedRailImpressionEventInShowDetailPage(String keyword, String trayTitle)
			throws Exception {
		extent.HeaderChildNode("Verify Recommended Rail Impression Event In Show Detail Page");

		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitForElement(PWASearchPage.objSearchResult(keyword), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword), "Search Result");
		waitTime(4000);
		WebElement element = getWebDriver().findElement(PWAShowsPage.objTrayTitle1(trayTitle));
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		checkElementDisplayed(PWAHomePage.objRecoTray, "Recommended Rail");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Recommended Rail Impression");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Recommended Rail Impression");
		}

	}

	public void verifyRecommendedRailImpressionEventInConsumptionScreen(String keyword1, String trayTitle)
			throws Exception {
		extent.HeaderChildNode("Verify Recommended Rail Impression Event In Consumption Screen");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitTime(4000);
		WebElement element = getWebDriver().findElement(PWAShowsPage.objTrayTitle1(trayTitle));
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		checkElementDisplayed(PWAHomePage.objRecoTray, "Recommended Rail");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Recommended Rail Impression");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Recommended Rail Impression");
		}

	}

	public void verifyToastMessageImpressionEventInPackSelectionPage(String userType) throws Exception {
		extent.HeaderChildNode("Verify Toast Message Impression event in pack selection page");
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			click(PWAHamburgerMenuPage.objProfileIconWEB, "Profile Icon");
			click(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Subscription"), "My Subscription");
			click(PWAHamburgerMenuPage.objBrowseAllPacks, "Browse All Packs");
			waitTime(2000);
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "pack_selection");
			mixpanel.FEProp.setProperty("Page Name", "home");
			mixpanel.FEProp.setProperty("Toast Message", "You are already subscribed to ZEE5 Club Pack");
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}
	}

	public void verifyPrepaidCodeResultEventForInvalid(String userType) throws Exception {
		extent.HeaderChildNode("Verify Prepaid Code Result Event For Invalid code");
		String promocode = "Z56MSK93rJGDyi";
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			click(PWASubscriptionPages.objHaveACode, "Have A Code section");
			type(PWASubscriptionPages.objHaveACode, promocode, "Prepaid Code");
			click(PWASubscriptionPages.objApplyBtn, "Apply Button");

			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					click(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
				}
			}
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "APPLY");
			mixpanel.FEProp.setProperty("Success", "false");
			String[] cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount).split(" ");
			mixpanel.FEProp.setProperty("Transaction Currency", cost[0]);
			mixpanel.FEProp.setProperty("cost", cost[1]);
			mixpanel.FEProp.setProperty("Promo Code Type", "Product");
			mixpanel.FEProp.setProperty("Promo Code", promocode);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Prepaid Code Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Prepaid Code Result");
			}
		}
	}

	public void verifyAdViewEventForFreeContent(String userType, String audioTrackContent) throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Free Content");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(6000);
			waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 20, "Search Result");
			click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForTrailer(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Trailer Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForCarouselContent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Carousel Content");
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentInTray(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Content played from Tray");
			click(PWAPremiumPage.objThumbnail, "Content From a tray");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentFromSearchPage(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentFromMyWatchlistPage(String userType, String audioTrackContent)
			throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Ad View Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			waitTime(4000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentInPlaylist(String userType, String audioTrackContent) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Content played from Playlist");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);

			waitTime(2000);
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdViewEventForContentFromUpnextRail(String userType, String audioTrackContent) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Content played from Upnext rail");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");

			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentFromSharedLink(String userType, String audioTrackURL) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For content played from Shared Link");
			getWebDriver().get(audioTrackURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdForcedExitEventForFreeContent(String userType, String audioTrackContent) throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Free Content");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 20, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}
			}

		} else {
			logger.info("Ad is not available for the content");
			extent.extentLogger("Ad", "Ad is not available for the content");
		}

		waitTime(5000);

	}

	public void verifyAdForcedExitEventForTrailer(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Trailer Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForCarouselContent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Carousel Content");
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentInTray(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content played from Tray");
			click(PWAPremiumPage.objThumbnail, "Content From a tray");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentFromSearchPage(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentFromMyWatchlistPage(String userType, String audioTrackContent)
			throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			waitTime(2000);
			mandatoryRegistrationPopUp(userType);
			waitTime(4000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentInPlaylist(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content played from Playlist");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdForcedExitEventForContentFromUpnextRail(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content played from Upnext rail");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");

			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentFromSharedLink(String userType, String audioTrackURL)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For content played from Shared Link");
			getWebDriver().get(audioTrackURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdClickEventForFreeContent(String userType, String audioTrackContent) throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Free Content");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 20, "Search Result");
			click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(10000);
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				waitTime(3000);
				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				waitTime(3000);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdClickEventForTrailer(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Trailer Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			}
		} else {
			logger.info("Ad is not available for the content");
			extent.extentLogger("Ad", "Ad is not available for the content");
		}

	}

	public void verifyAdClickEventForCarouselContent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Carousel Content");
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdClickEventForContentInTray(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Content played from Tray");
			click(PWAPremiumPage.objThumbnail, "Content From a tray");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdClickEventForContentFromSearchPage(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdViewClickForContentFromMyWatchlistPage(String userType, String audioTrackContent)
			throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Ad Click Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdClickEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdClickEventForContentInPlaylist(String userType, String audioTrackContent) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Content played from Playlist");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);

		}
	}

	public void verifyAdClickEventForContentFromUpnextRail(String userType, String audioTrackContent) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Content played from Upnext rail");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");

			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdClickEventForContentFromSharedLink(String userType, String audioTrackURL) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For content played from Shared Link");
			getWebDriver().get(audioTrackURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);

		}
	}

	public void verifyAdWatchDurationEventForFreeContentForceExit(String userType, String audioTrackContent)
			throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback for free content");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 20, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForTrailerForceExit(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Trailer Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForCarouselContentForceExit(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Carousel Content");
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInTrayForceExit(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Tray");
			click(PWAPremiumPage.objThumbnail, "Content From a tray");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSearchPageForceExit(String userType,
			String subtitleTrackContent) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageForceExit(String userType,
			String audioTrackContent) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInMegamenuForceExit(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInPlaylistForceExit(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Playlist");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForContentFromUpnextRailForceExit(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Upnext rail");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");

			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSharedLinkForceExit(String userType, String audioTrackURL)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For content played from Shared Link");
			getWebDriver().get(audioTrackURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForFreeContentComplete(String userType, String audioTrackContent)
			throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback for free content");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 20, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForTrailerComplete(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Trailer Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");
				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForCarouselContentComplete(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Carousel Content");
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInTrayComplete(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Tray");
			click(PWAPremiumPage.objThumbnail, "Content From a tray");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSearchPageComplete(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageComplete(String userType,
			String audioTrackContent) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches ad playback For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInMegamenuComplete(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInPlaylistComplete(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Playlist");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForContentFromUpnextRailComplete(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Upnext rail");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");

			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSharedLinkComplete(String userType, String audioTrackURL)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches ad playback For content played from Shared Link");
			getWebDriver().get(audioTrackURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAddToWatchlistEventFromShowDetailPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Add To Watchlist Event From Show Detail Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			click(PWASearchPage.objSearchResult(keyword), "Search Result");
			waitTime(5000);

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon")) {
				click(PWAPlayerPage.objAddToWatchlist, "Watchlist icon");
			} else {
				click(PWAPlayerPage.objRemoveFromWatchlist, "Remove From Watchlist icon");
				waitTime(3000);
				actions.moveToElement(contentCard).build().perform();
				click(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon");
				waitTime(4000);
			}
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "show_detail");
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			mixpanel.ValidateParameter(local.getItem("ID"), "Add To Watchlist");
		}
	}

	public void verifyAdWatchDurationEventForFreeContentSkipAd(String userType, String audioTrackContent)
			throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Watch Duration Event when user skips the ad playback for free content");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 20, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "search");
					mixpanel.FEProp.setProperty("Page Name", "episode_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
					Matcher m = p.matcher(id);
					String value = null;
					while (m.find()) {
						value = m.group(0);
					}
					ResponseInstance.getContentDetails(value);
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}

			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForTrailerSkipAd(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Trailer Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "search");
					mixpanel.FEProp.setProperty("Page Name", "movie_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
					Matcher m = p.matcher(id);
					String value = null;
					while (m.find()) {
						value = m.group(0);
					}
					ResponseInstance.getContentDetails(value);
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForCarouselContentSkipAd(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Carousel Content");
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Page Name", "movie_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
					Matcher m = p.matcher(id);
					String value = null;
					while (m.find()) {
						value = m.group(0);
					}
					ResponseInstance.getContentDetails(value);
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInTraySkipAd(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Content played from Tray");
			click(PWAPremiumPage.objThumbnail, "Content From a tray");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Page Name", "movie_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
					Matcher m = p.matcher(id);
					String value = null;
					while (m.find()) {
						value = m.group(0);
					}
					ResponseInstance.getContentDetails(value);
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSearchPageSkipAd(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "search");
					mixpanel.FEProp.setProperty("Page Name", "episode_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
					Matcher m = p.matcher(id);
					String value = null;
					while (m.find()) {
						value = m.group(0);
					}
					ResponseInstance.getContentDetails(value);
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageSkipAd(String userType, String audioTrackContent)
			throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips ad playback For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
					mixpanel.FEProp.setProperty("Page Name", "episode_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
					Matcher m = p.matcher(id);
					String value = null;
					while (m.find()) {
						value = m.group(0);
					}
					ResponseInstance.getContentDetails(value);
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInMegamenuSkipAd(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Page Name", "movie_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
					Matcher m = p.matcher(id);
					String value = null;
					while (m.find()) {
						value = m.group(0);
					}
					ResponseInstance.getContentDetails(value);
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInPlaylistSkipAd(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Content played from Playlist");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");
					mixpanel.FEProp.setProperty("Source", "episode_detail");
					mixpanel.FEProp.setProperty("Page Name", "episode_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
					Matcher m = p.matcher(id);
					String value = null;
					while (m.find()) {
						value = m.group(0);
					}
					ResponseInstance.getContentDetails(value);
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForContentFromUpnextRailSkipAd(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Content played from Upnext rail");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");

			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "episode_detail");
					mixpanel.FEProp.setProperty("Page Name", "episode_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
					Matcher m = p.matcher(id);
					String value = null;
					while (m.find()) {
						value = m.group(0);
					}
					ResponseInstance.getContentDetails(value);
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}

				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSharedLinkSkipAd(String userType, String audioTrackURL)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips ad playback For content played from Shared Link");
			getWebDriver().get(audioTrackURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Page Name", "movie_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
					Matcher m = p.matcher(id);
					String value = null;
					while (m.find()) {
						value = m.group(0);
					}
					ResponseInstance.getContentDetails(value);
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}

			waitTime(5000);
		}
	}

	public void verifyCTAsEventForIcons(String userType, String icon) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event when user clicks on Search, Language, Profile icon");
		waitTime(5000);
		if (icon.equalsIgnoreCase("Search")) {
			click(PWAHomePage.objSearchBtn, "Search Icon");
			waitTime(5000);
		}

		if (icon.equalsIgnoreCase("Language")) {
			click(PWAHomePage.objLanguageBtn, "Language button");
			waitTime(5000);
		}

		if (!(userType.equalsIgnoreCase("Guest"))) {
			if (icon.equalsIgnoreCase("Profile")) {
				click(PWALandingPages.objWebProfileIcon, "Profile Icon");
				waitTime(5000);
			}
		}

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.FEProp.setProperty("Element", "Language");

		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "CTAs");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "CTAs");
		}

	}

	public void verifyCTAsEventForSubscribeBtn(String userType) throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify CTAs Event when user clicks on subscription option");
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "N/A");
			mixpanel.FEProp.setProperty("Page Name", "home");
			mixpanel.FEProp.setProperty("Element", "Subscribe");

			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "CTAs");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "CTAs");
			}
		}
	}

	public void verifyCTAsEventForOptionInHamburger() throws Exception {
		extent.HeaderChildNode("Verify CTAs Event when user clicks on any option in hamburger menu");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		waitTime(3000);
		click(PWAHamburgerMenuPage.objMoreSettingInHamburger, "More settings");

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.FEProp.setProperty("Element", "More Settings");

		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "CTAs");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "CTAs");
		}
	}

	public void verifyCTAsEventHeader(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event");
		navigateToAnyScreenOnWeb(tabName);

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.FEProp.setProperty("Element", "Shows");

		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "CTAs");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "CTAs");
		}
	}

	public void verifyAdInitializedEventForFreeContent(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Free Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 20, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			
			waitTime(6000);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				if (UserType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

			waitTime(5000);

		}
	}

	public void verifyAdInitializedEventForTrailer(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Trailer Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}

	}

	public void verifyAdInitializedEventForCarouselContent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Carousel Content");
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentInTray(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content played from Tray");
			click(PWAPremiumPage.objThumbnail, "Content From a tray");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentFromSearchPage(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentFromMyWatchlistPage(String userType, String audioTrackContent)
			throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentInPlaylist(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content played from Playlist");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);

			waitTime(2000);
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdInitializedEventForContentFromUpnextRail(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content played from Upnext rail");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");

			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentFromSharedLink(String userType, String audioTrackURL)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For content played from Shared Link");
			getWebDriver().get(audioTrackURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyLoginScreenDisplayEventByClickingOnLoginInRegistrationPopUp(String userType, String keyword)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {

			extent.HeaderChildNode(
					"Verify Login Screen Display Event during content playback post clicking on login in registration popup");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");
			click(PWAPremiumPage.obj1stContentInShowDetailPage, "Content Card");
			waitForElement(PWALoginPage.objLoginLink, 20, "Login Link");
			click(PWALoginPage.objLoginLink, "Login Link");
			waitTime(5000);

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Screen Display");
		}
	}

	public void verifyAdClickForContentFromMyWatchlistPage(String userType, String audioTrackContent) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Ad Click Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(audioTrackContent), 10, "Search Result");

			click(PWASearchPage.objSearchResult(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();
			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				click(PWAPlayerPage.objAd, "Ad");

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
				Matcher m = p.matcher(id);
				String value = null;
				while (m.find()) {
					value = m.group(0);
				}
				ResponseInstance.getContentDetails(value);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyLoginUsernameEnteredEvent(String LoginMethod, String EventName) throws Exception {
		String userType = getParameterFromXML("userType");
		waitTime(5000);
		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
//			dismissDisplayContentLanguagePopUp();
//			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");
			Username = getParameterFromXML("NonsubscribedUserName");
			Password = getParameterFromXML("NonsubscribedUserPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");
			Username = getParameterFromXML("SubscribedUserName");
			Password = getParameterFromXML("SubscribedUserPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;
		}
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "sign_in");
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (EventName.equals("UserName")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Username Entered");
		} else {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Password Entered");
		}
	}
}
