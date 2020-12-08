package com.business.zee;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.PWAPages.*;
import com.driverInstance.CommandBase;
import com.emailReport.GmailInbox;
import com.extent.ExtentReporter;
import com.jayway.restassured.response.Response;
import com.metadata.ResponseInstance;
import com.metadata.getResponseUpNextRail;
import com.propertyfilereader.*;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Zee5PWASanityAndroidBusinessLogic extends Utilities {

	public Zee5PWASanityAndroidBusinessLogic(String Application) throws InterruptedException {
		new CommandBase(Application);
		init();
	}

	private int timeout;

	/** Retry Count */
	private int retryCount;

	ExtentReporter extent = new ExtentReporter();

	@SuppressWarnings("unused")
	private SoftAssert softAssert = new SoftAssert();

	/** The Constant logger. */
//	final static Logger logger = Logger.getLogger("rootLogger");

	static LoggingUtils logger = new LoggingUtils();

	/** The Android getDriver(). */
	public AndroidDriver<AndroidElement> androidDriver;

	/** The Android driver. */
	public IOSDriver<WebElement> iOSDriver;

	Set<String> hash_Set = new HashSet<String>();
	GmailInbox gmail = new GmailInbox();
	@SuppressWarnings("unused")
	private String LacationBasedLanguge;

	List<String> LocationLanguage = new ArrayList<String>();

	List<String> DefaultLanguage = new ArrayList<String>();

	List<String> SelectedCONTENTLanguageInWelcomscreen = new ArrayList<String>();

	List<String> SelectedCONTENTLanguageInHamburgerMenu = new ArrayList<String>();

	String NonSubUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("NonsubscribedUserName");
	String NonSubPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("NonsubscribedPassword");

	String SubUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("SubscribedUserName");
	String SubPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("SubscribedPassword");

	Response resp;
	String PresentTitle;
	String AdValue = "AdnotPlayed";
	ArrayList<String> MastheadTitleApi = new ArrayList<String>();

	public static boolean relaunchFlag = false;
	public static boolean appliTools = false;
	public static boolean PopUp = false;

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

	/**
	 * Initiate Property File.
	 *
	 * @param byLocator the by locator
	 */

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info(
				"Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	/**
	 * Wait until Player Loading is not displayed.
	 */

	public void LoadingInProgress(By locator) throws Exception {
		verifyElementNotPresent(locator, 60);
	}

	/**
	 * Generic function to click on the Player.
	 */

	public void playerClick(By byLocator, String validationtext) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			element.click();
			logger.info("Clicked on the" + validationtext);
			extent.extentLogger("click", "Clicked on the " + " " + validationtext);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * The method s for element and clicks if present. Returns no error if not
	 * present. Implemented for random popups
	 */
	public boolean waitForElementAndClickIfPresent(By locator, int seconds, String message)
			throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				getDriver().findElement(locator).click();
				logger.info("Clicked element " + message);
				extent.extentLogger("clickedElement", "Clicked element " + message);
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		return false;
	}

	/**
	 * Function to quit the driver
	 */
	public void tearDown() {
		getDriver().quit();
	}

	/** ==================SHREENIDHI - MENU SETTINGS======================= */

	public void verificationsOfExploreOptions() throws Exception {
		extent.HeaderChildNode("Verifications of Explore dropdown options");
		waitTime(15000);
		Swipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		waitTime(2000);
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreBtn, "Explore option")) {
			click(PWAHamburgerMenuPage.objExploreBtn, "Explore option");
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Movies"), "Movies option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Shows"), "Shows option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("News"), "News option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Club"), "Club");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Premium"), "Premium option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Play"), "Play option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Kids"), "Kids option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Music"), "Music option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Live TV"), "Live TV option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Stories"), "Stories option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions(" ZEE5 Originals"),
					"ZEE5 Originals option");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Hamburger close button");
		} else {
			Swipe("DOWN", 1);
			click(PWAHamburgerMenuPage.objExploreBtn, "Explore option");
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Movies"), "Movies option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Shows"), "Shows option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("News"), "News option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Club"), "Club");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Premium"), "Premium option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Play"), "Play option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Kids"), "Kids option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Music"), "Music option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Live TV"), "Live TV option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Stories"), "Stories option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions("Videos"), "Videos option");
			waitTime(1000);
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreOptions(" ZEE5 Originals"),
					"ZEE5 Originals option");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Hamburger close button");
		}

	}

	public void navigationsFromPlanSection() throws Exception {
		extent.HeaderChildNode("Functionality of MyPlan options");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objPlanInHamburger, "Plan option");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objPlanInsideItemsBtn("Buy Subscription"),
				"Buy Subscription option in Plan section");
		waitTime(3000);
		if (verifyIsElementDisplayed(PWASubscriptionPages.objZEE5Subscription, "Subscription")) {
			logger.info("User is navigated to subscription page");
			extent.extentLogger("subscription page", "User is navigated to subscription page");
			click(PWAHomePage.objZeeLogo, "zee logo");
			waitTime(4000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPlanInsideItemsBtn("Have a prepaid code ?"),
					"Have a prepaid code ? option in Plan section");
			waitTime(3000);
			if (verifyIsElementDisplayed(PWASubscriptionPages.objZEE5Subscription, "Subscription")) {
				logger.info("User is navigated to subscription page");
				extent.extentLogger("subscription page", "User is navigated to subscription page");
				click(PWAHomePage.objZeeLogo, "zee logo");
			}
		}

	}

	public void resetToDefault() throws Exception {
		extent.HeaderChildNode("Reset Settings to default Functionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		Swipe("UP", 2);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMoreSettingInHamburger,
				"More settings in settings section");
		waitTime(2000);
		click(PWAHamburgerMenuPage.objDisplayLanguage, "Display language");
		waitTime(5000);
		click(PWAHamburgerMenuPage.objSelectLanguage, "Language icon");
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objAfterSelectedLanguage, "Language")) {
			logger.info("clicked on hindi language in Display language popup");
			extent.extentLogger("Content language", "clicked on hindi language in Display language popup");
		}
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");

		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objResetSettingsToDefault, "Reset Settings to Default");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objContentLanguage, "Content Language");
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objAfterSelectedLanguage, "Language") == false) {
			logger.info("Reset to default is success");
			extent.extentLogger("Content language", "Reset to default is success");
			click(PWAHamburgerMenuPage.objCancelButtonInContentLangugaePopup, "Cancel button");
			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "zee logo");
		} else {
			logger.info("Reset to defualt was unsuccessfull");
			extent.extentLogger("Reset to defualt", "Reset to defualt was unsuccessfull");
		}

	}

	public void parentControlFunctionality(String UserType) throws Exception {
		extent.HeaderChildNode("Parent Control Functionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
		verifyIsElementDisplayed(PWALoginPage.objPasswordField, "Password field");
		String password = "";
		if (UserType.equals("NonSubscribedUser")) {
			password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedPassword");
		} else if (UserType.equals("SubscribedUser")) {
			password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedPassword");
		}
		type(PWALoginPage.objPasswordField, password, "Password field");
		hideKeyboard();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
		waitTime(2000);
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objNoRestrictionSelected, "No Restrictions option");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objRestrictAll, "Restricted option");
		verifyElementPresent(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
		type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
		hideKeyboard();
		type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
		hideKeyboard();
		type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
		hideKeyboard();
		type(PWAHamburgerMenuPage.objParentalLockPin4, "4\n", "ParentalLockPin");
		hideKeyboard();
		waitTime(4000);
		directClickReturnBoolean(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
		waitTime(2000);
		verifyIsElementDisplayed(PWAHomePage.objZeeLogo, "Zee logo");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		verifyIsElementDisplayed(PWAHomePage.objSearchField, "Search field");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(UserType);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("consumptionsFreeContent");
		type(PWAHomePage.objSearchField, keyword + "\n", "Search");
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(10000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		waitTime(6000);
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objParentalLockPopUpInPlayer, "Parent control Popup");
		type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
		hideKeyboard();
		type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
		hideKeyboard();
		type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
		hideKeyboard();
		type(PWAHamburgerMenuPage.objParentalLockPin4player, "4\n", "ParentalLockPin");
		hideKeyboard();
		waitTime(5000);
		waitForPlayerAdToComplete("Video Player");
		playerTap();
		if (verifyIsElementDisplayed(PWAPlayerPage.playBtn, "Play icon")) {
			logger.info("Playback is played after entering parental lock");
			extent.extentLogger("Playback", "Playback is played after entering parental lock");
		} else {
			logger.error("Playback is not started after entering parental lock");
			extent.extentLoggerFail("Playback", "Playback is not started after entering parental lock");
		}
		click(PWAHomePage.objZeeLogo, "zee logo");
		waitTime(5000);

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
		verifyIsElementDisplayed(PWALoginPage.objPasswordField, "password field");
		type(PWALoginPage.objPasswordField, password, "Password field");
		hideKeyboard();
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
		waitTime(2000);
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
		click(PWAHamburgerMenuPage.objParentalLockNoRestrictionOption, "No restriction option");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objNoRestrictionSelected, "No restricted option selected");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "zee logo");
	}

	public void authenticationFunctionality() throws Exception {
		extent.HeaderChildNode("Authentication Functionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
		waitTime(3000);
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
		if (getDriver().findElement(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted).isEnabled() == false) {
			logger.info("Authenticate button is not highlighted by default");
			extent.extentLogger("Authenticate", "Authenticate button is not highlighted by default");
		}
		type(PWAHamburgerMenuPage.objAuthenticationField, "abcdef\n", "AuthenticationField");
		hideKeyboard();
		String AuthenticationField = getText(PWAHamburgerMenuPage.objAuthenticationField);
		if (AuthenticationField != null) {
			logger.info("User is able to enter the value in AuthenticationField");
			extent.extentLogger("AuthenticationField", "User is able to enter the value in AuthenticationField");
		}

		if (getDriver().findElement(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted).isEnabled()) {
			logger.info("Authenticate button is highlighted after entering the input in AuthenticationField");
			extent.extentLogger("Authenticate",
					"Authenticate button is highlighted after entering the input in AuthenticationField");
		}

		click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
		try {
			Boolean ExpiredToastMessage = getDriver().findElement(By.xpath("//*[@class='toastMessage']")).isDisplayed();
			if (ExpiredToastMessage == true) {
				extent.extentLogger("Toast", "Expired Toast message displayed");
				logger.info("Expired Toast message displayed");
			} else {
				extent.extentLogger("Toast", "Expired Toast message not displayed");
				logger.info("Expired Toast message not displayed");
			}
			getDriver().findElement(PWAHamburgerMenuPage.objAuthenticationField).clear();
			Wait(3000);
			type(PWAHamburgerMenuPage.objAuthenticationField, "&!@#$%\n", "AuthenticationField");
			hideKeyboard();
			waitTime(2000);
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			Boolean NotfounfToastMessage = getDriver().findElement(By.xpath("//*[@class='toastMessage']"))
					.isDisplayed();
			if (NotfounfToastMessage == true) {
				extent.extentLogger("Toast", "Not found Toast message displayed");
				logger.info("Not found Toast message displayed");
			} else {
				extent.extentLogger("Toast", "Not found Toast message not displayed");
				logger.info("Not found Toast message not displayed");
			}
		} catch (Exception e) {
			System.out.println("Toast message is not displayed");
		}
		verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "zee logo");
	}

	public void MenuOrSettingScenarios(String userType) throws Exception {

		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Menu or Settings verification");
			extent.extentLogger("Accessing as Guest User", "Accessing as Guest User");
			logger.info("Accessing as Guest User");
			verificationsOfExploreOptions();
			navigationsFromPlanSection();
			resetToDefault();
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Menu or Settings verification");
			extent.extentLogger("Accessing as NonSubscribedUser User", "Accessing as NonSubscribedUser User");
			logger.info("Accessing as NonSubscribedUser User");
			resetToDefault();
			parentControlFunctionality("NonSubscribedUser");
			authenticationFunctionality();
			verificationsOfExploreOptions();
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Menu or Settings verification");
			extent.extentLogger("Accessing as SubscribedUser User", "Accessing as SubscribedUser User");
			logger.info("Accessing as SubscribedUser User");
			verificationsOfExploreOptions();
			resetToDefault();
			parentControlFunctionality("SubscribedUser");
			authenticationFunctionality();
		}
	}

	@SuppressWarnings("rawtypes")
	public void playerTap() {
		int deviceWidth = getDriver().manage().window().getSize().width;
		int deviceHeight = getDriver().manage().window().getSize().height;
		int x = deviceWidth / 2;
		int y = deviceHeight / 4;
		TouchAction act = new TouchAction(getDriver());
		act.tap(PointOption.point(x, y)).perform();
		extent.extentLogger("playerTap", "Tapped on the Player");
		logger.info("Tapped on the Player");
	}

	public void ZeePWALogin(String LoginMethod, String userType) throws Exception {
		String url = getParameterFromXML("url");
		extent.HeaderChildNode("User-Type : " + userType + ", Environment: " + url);
		// Get the email and password from properties
		String email = "";
		String password = "";
		// dismissSystemPopUp();
		// waitTime(3000);
		// dismissSystemPopUp();
		// dismissAppInstallPopUp();
		// dismissStayTundedPopUp();
		// dismiss3xPopUp();
		// dismissDisplayContentLanguagePopUp();
		// dismissSystemPopUp();
		dismissAllPopUps();
		if (userType.equalsIgnoreCase("Guest")) {
			extent.extentLogger("Guest", "Accessing the application as Guest user");
		} else if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.extentLogger("Subscribed", "Accessing the application as Subscribed user");
			email = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedUserName");
			password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedPassword");
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.extentLogger("Non-Subscribed", "Accessing the application as Non-Subscribed user");
			email = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedUserName");
			password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedPassword");
		} else if (userType.equalsIgnoreCase("ClubUser")) {
			extent.extentLogger("Club", "Accessing the application as Club user");
			email = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ClubUserName");
			password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("ClubPassword");
		}
		if (userType.equalsIgnoreCase("SubscribedUser") || userType.equalsIgnoreCase("NonSubscribedUser")
				|| userType.equalsIgnoreCase("ClubUser")) {
			if (!checkElementDisplayed(PWALoginPage.objLoginBtn, "Login Button")) {
				verifyElementPresentAndClick(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			}
			waitTime(3000);
			click(PWALoginPage.objLoginBtn, "Login button");
			waitTime(3000);
			HeaderChildNode("Login - Method" + LoginMethod);
			switch (LoginMethod) {

			case "E-mail":
				dismissAppInstallPopUp();
				verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
				waitTime(10000);
				// getDriver().getKeyboard().sendKeys("Bla bla");//works
				type(PWALoginPage.objEmailField, email, "Email Field");
				hideKeyboard();
				waitTime(3000);
				dismissSystemPopUp();
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, password + "\n", "Password field");
				hideKeyboard();
				waitTime(5000);
				directClickReturnBoolean(PWALoginPage.objLoginBtnLoginPage, "Login Button");
				waitTime(10000);
				break;

			case "Mobile":
				verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
				type(PWALoginPage.objEmailField, "8792396107\n", "Email Field");
				hideKeyboard();
				verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login butotn");
				waitTime(3000);
				hideKeyboard();
				waitTime(5000);
				verifyElementPresentAndClick(PWALoginPage.objpasswordphno, "Password field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "password-field");
				type(PWALoginPage.objPasswordField, "tanisha19\n", "password-field");
				hideKeyboard();
				waitTime(2000);
				click(PWALoginPage.objproceedphno, "Proceed button");
				waitTime(5000);
				break;

			case "Facebook":
				extent.HeaderChildNode("Login through Facebook");
				verifyElementPresentAndClick(PWALoginPage.objFacebookIcon, "Facebook Icon");
				System.out.println(getDriver().getCurrentUrl());
				System.out.println(getDriver().getWindowHandles());
				getDriver().switchTo().window("CDwindow-1");
				waitTime(7000);
				if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger")) {
					click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				}

				else if (verifyIsElementDisplayed(PWALoginPage.objFacebookPageVerification, "Facebook page")) {
					verifyElementPresent(PWALoginPage.objFacebookPageVerification, "Facebook page");
					verifyElementPresent(PWALoginPage.objFacebookLoginEmail, " Email Field");
					type(PWALoginPage.objFacebookLoginEmail, "igszeetesttest@gmail.com", "Emial Field");
					verifyElementPresent(PWALoginPage.objFacebookLoginpassword, " Password Field");
					type(PWALoginPage.objFacebookLoginpassword, "Igs$123Zee\n", "Password Field");
					verifyElementPresentAndClick(PWALoginPage.objFacebookLoginButtonInFbPage, " Login Button");
					waitTime(9000);
					getDriver().switchTo().window("CDwindow-0");
					verifyIsElementDisplayed(PWALoginPage.objFbCountinueBtn, "Continue button");
					if (verifyIsElementDisplayed(PWASignupPage.objSignUpTxt, "SignUp")) {
						regestrationfromSocialMedia();
						verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
						click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
						verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
						logger.info("User Logged in Successfully");
						extent.extentLogger("Logged in", "User Logged in Successfully");
					} else {
						waitTime(3000);
						verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
						click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
						verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon");
						logger.info("User Logged in Successfully");
						extent.extentLogger("Logged in", "User Logged in Successfully");
					}

				} else if (verifyElementPresent(PWALoginPage.objFbCountinueBtn, "Continue button") == true) {
					click(PWALoginPage.objFbCountinueBtn, "Continue fb");
					if (verifyElementPresent(PWASignupPage.objSignUpTxt, "SignUp") == true) {
						regestrationfromSocialMedia();
						verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
						click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
						verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
						logger.info("User Logged in Successfully");
						extent.extentLogger("Logged in", "User Logged in Successfully");
					} else {
						waitTime(7000);
						verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
						click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
						verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
						logger.info("User Logged in Successfully");
						extent.extentLogger("Logged in", "User Logged in Successfully");
					}
				}

				break;

			case "Gmail":
				extent.HeaderChildNode("Login through Gmail");
				System.out.println(getDriver().getCurrentUrl());
				System.out.println(getDriver().getWindowHandles());
				verifyElementPresentAndClick(PWALoginPage.objGoogleIcon, "Google Icon");
				getDriver().switchTo().window("CDwindow-1");
				waitTime(4000);
				if (verifyIsElementDisplayed(PWALoginPage.objGmailEmailField, " Email Field")) {
					type(PWALoginPage.objGmailEmailField, "Zee5latest@gmail.com", "Emial Field");
					hideKeyboard();
					verifyElementPresentAndClick(PWALoginPage.objGmailNextButton, "clicked on next button");
					waitTime(3000);
					verifyElementPresent(PWALoginPage.objGmailPasswordField, " Password Field");
					type(PWALoginPage.objGmailPasswordField, "User@123\n", "Password Field");
					hideKeyboard();
					verifyElementPresentAndClick(PWALoginPage.objGmailNextButton, "clicked on next button");
					waitTime(5000);
					getDriver().switchTo().window("CDwindow-0");
					if (verifyIsElementDisplayed(PWASignupPage.objSignUpTxt, "signup")) {
						regestrationfromSocialMedia();
					}
					waitTime(5000);
					verifyElementPresent(PWAHomePage.objZeeLogo, "Zee logo");
					logger.info("User is Logged in successfully");
					extent.extentLogger("Logged in", "User is Logged in successfully");
				} else {
					waitTime(10000);
					verifyElementPresent(PWAHomePage.objZeeLogo, "Zee logo");
					logger.info("User is Logged in successfully");
					extent.extentLogger("Logged in", "User is Logged in successfully");
				}

				break;

			case "Twitter":
				extent.HeaderChildNode("Login through Twitter");
				verifyElementPresentAndClick(PWALoginPage.objTwitterIcon, "Twitter icon");
				waitTime(7000);
				System.out.println(getDriver().getWindowHandles());
				System.out.println(getDriver().getCurrentUrl());
				getDriver().switchTo().window("CDwindow-1");

				waitTime(5000);
				System.out.println(getDriver().getCurrentUrl());

				if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger")) {
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				}

				else if (verifyIsElementDisplayed(PWALoginPage.objTwitterAuthorizeButton, "Authorize app")) {
					click(PWALoginPage.objTwitterAuthorizeButton, "Authorize app");
					regestrationfromSocialMedia();
				} else if (verifyIsElementDisplayed(PWALoginPage.objTwitterEmaildField, "Twitter Email field")) {

					type(PWALoginPage.objTwitterEmaildField, "Zee5latest@gmail.com", "Email Field");
					hideKeyboard();
					verifyElementPresentAndClick(PWALoginPage.objTwitterPasswordField, "Twitter Password field");
					type(PWALoginPage.objTwitterPasswordField, "User@123\n", "Password field");
					click(PWALoginPage.objTwitterSignInButton, "Sign in button");
					waitTime(5000);
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					click(PWAHamburgerMenuPage.objLoginBtn, "Login");
					verifyElementPresentAndClick(PWALoginPage.objTwitterIcon, "Twitter icon");
				}

				if (verifyIsElementDisplayed(PWALoginPage.objTwitterAuthorizeButton, "Authorize")) {
					click(PWALoginPage.objTwitterAuthorizeButton, "Authorize");
					waitTime(7000);
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				}
				break;
			}
		}
		dismiss3xPopUp();
		dismissAppInstallPopUp();
	}

	public void regestrationfromSocialMedia() throws Exception {
		extent.HeaderChildNode("Regestration Screen");
		click(PWASignupPage.objDayPickerTab, "Day Tab");
		verifyElementPresentAndClick(PWASignupPage.objDayPickerTabValue, "Day option");
		verifyElementPresentAndClick(PWASignupPage.objMonthPickerTab, "Month Tab");
		verifyElementPresentAndClick(PWASignupPage.objMonthPickerTabValue, "Month option");
		verifyElementPresentAndClick(PWASignupPage.objYearPickerTab, "year Tab");
		verifyElementPresentAndClick(PWASignupPage.objYearPickerTabValue, "year option");
		verifyElementPresentAndClick(PWASignupPage.objGenderMaleBtn, "Gender tab");
		verifyElementPresentAndClick(PWALoginPage.objSignUpBtn, "signUp button");
		waitTime(10000);
		verifyElementPresent(PWAHomePage.objZeeLogo, "Zee logo");
		logger.info("User Logged in Successfully");
		extent.extentLogger("Logged in", "User Logged in Successfully");
	}

	public void dismissDisplayContentLanguagePopUp() throws Exception {
		try {
			WebElement displayContentLang = (new WebDriverWait(getDriver(), 60))
					.until(ExpectedConditions.presenceOfElementLocated(PWAHomePage.objContinueDisplayContentLangPopup));
			if (displayContentLang.isDisplayed() == true) {
				click(PWAHomePage.objContinueDisplayContentLangPopup, "Continue on Display Language Pop Up");
				dismissSystemPopUp();
				waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup, 10,
						"Continue on Content Language Pop Up");
			}
		} catch (Exception e) {

		}
	}

	public void dismiss3xPopUp() throws Exception {
		String url = getParameterFromXML("url");
		if (!url.contains("newpwa")) {
			getDriver().context("NATIVE_APP");
			waitTime(3000);
			directClickReturnBoolean(PWAHomePage.obj3xfasterPopUpNoThanks, "NO THANKS in 3x Pop Up");
			getDriver().context("CHROMIUM");
		}
	}

	public void dismissAppInstallPopUp() throws Exception {
		directClickReturnBoolean(PWAHomePage.objAppInstallPopUpClose, "Close in App Install Pop Up");
	}

	public void dismissStayTundedPopUp() throws Exception {
		directClickReturnBoolean(PWAHomePage.objStayTunedPopUpClose, "Close in Stay Tuned Pop Up");
	}

	/** ===================SUSHMA - SEARCH===================================== */

	public void landingOnSearchScreen() throws Exception {
		// handle mandatory pop up
		String userType = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(userType);
		extent.HeaderChildNode("User: " + userType + ": Validating that user lands on search landing screen");
		waitTime(3000);
		verifyIsElementDisplayed(PWAHomePage.objSearchBtn, "Search icon");
		click(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 20);
		if (verifyIsElementDisplayed(PWASearchPage.objSearchEditBox, "Search Edit Box")) {
			logger.info("User landed on Search landing screen post tapping on search icon");
			extent.extentLogger("Search landingscreen",
					"User landed on Search landing screen post tapping on search icon");
		}
		// from gaps
		extent.HeaderChildNode("Validating the placeholder text in search box");
		String placeHolderText = findElement(PWASearchPage.objSearchPlaceHolderText).getAttribute("placeholder")
				.toString();
		String expectedPlaceHolderText = "Search for Movies, Shows, Channels etc.";
		if (placeHolderText.equals(expectedPlaceHolderText)) {
			logger.info("Verified that placeholder text is: " + placeHolderText);
			extent.extentLogger("", "Verified that placeholder text is: " + placeHolderText);
		} else {
			logger.error("Placeholder text displayed is: '" + placeHolderText + "' instead of '"
					+ expectedPlaceHolderText + "'");
			extent.extentLoggerFail("", "Placeholder text displayed is: '" + placeHolderText + "' instead of '"
					+ expectedPlaceHolderText + "'");
		}
		// from gaps
		extent.HeaderChildNode("Validating the back button functionality in search page");
		verifyElementPresentAndClick(PWASearchPage.objBackButton, "Back in Search box");
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo")) {
			logger.info("Verified app Back button functionality");
			extent.extentLogger("", "Verified app Back button functionality");
		} else {
			logger.error("App Back button functionality verification failed");
			extent.extentLoggerFail("", "App Back button functionality verification failed");
		}
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		Back(1);
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo")) {
			logger.info("Verified system Back button functionality");
			extent.extentLogger("", "Verified system Back button functionality");
		} else {
			logger.error("System Back button functionality verification failed");
			extent.extentLoggerFail("", "System Back button functionality verification failed");
		}
		// from gaps
		extent.HeaderChildNode("Validating the presence of footer in search page");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		if (verifyIsElementDisplayed(PWASearchPage.objSearchPageFooter, "Footer menu")) {
			logger.info("Verified presence of Footer menu in Search Page");
			extent.extentLogger("", "Verified presence of Footer menu in Search Page");
		} else {
			logger.error("Failed to verify presence of Footer menu in Search Page");
			extent.extentLoggerFail("", "Failed to verify presence of Footer menu in Search Page");
		}
	}

	public void movieSearchResult(String title) throws Exception {
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		extent.HeaderChildNode("Validating that user is able to search Movie");
		type(PWASearchPage.objSearchEditBox, title + "\n", "Search bar");
		waitTime(4000);
		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);
		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
		List<WebElement> tabs = getDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		tabs.size();
		// for (int i = 1; i <= tabs.size(); i++) {
		for (int i = 1; i <= 1; i++) {
			try {
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
				String tabName = eleTab.getText();
				eleTab.click();
				logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
				extent.extentLogger("Related search results",
						tabName + " tab is displayed and clicked on " + tabName + " tab");
				if (getDriver().findElements(By.xpath(
						"(//div[@class='listingGrid']//div[@class='metaData']//h3[contains(@class,'cardTitle')]//span[@class='highLight'])"))
						.size() > 0) {
					logger.info("Related search results are displayed");
					extent.extentLogger("Related search results", "Related search results are displayed");

				} else {
					logger.error("Related search results are not displayed");
					extent.extentLoggerFail("Related search results", "Related search results are not displayed");
				}
			} catch (Exception e) {
				logger.error("Related search results are not displayed");
				extent.extentLoggerFail("Related search results", "Related search results are not displayed");
			}
		}

		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");
		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
		verifyElementPresentAndClick(PWASearchPage.objBackButton, "Back in Search box");
	}

	public void partlySpeltSearchResult(String title) throws Exception {

		extent.HeaderChildNode("Validating that user is able to search Partly spelt asset name");

		waitTime(3000);
		type(PWASearchPage.objSearchEditBox, title + "\n", "Search bar");

		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		List<WebElement> tabs = getDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		tabs.size();
		for (int i = 1; i <= tabs.size(); i++) {
			WebElement eleTab = getDriver()
					.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
			String tabName = eleTab.getText();
			eleTab.click();

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");

			if (getDriver().findElements(By.xpath(
					"(//div[@class='listingGrid']//div[@class='metaData']//h3[contains(@class,'cardTitle')]//span[@class='highLight'])"))
					.size() > 0) {
				logger.info("Related search results are displayed");
				extent.extentLogger("Related search results", "Related search results are displayed");

			} else {
				logger.info("Related search results are not displayed");
				extent.extentLogger("Related search results", "Related search results are not displayed");
			}
		}

		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");
		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
		verifyElementPresentAndClick(PWASearchPage.objBackButton, "Back in Search box");
	}

	public void emptystateScreen() throws Exception {
		extent.HeaderChildNode("Validating Empty state screen");
		click(PWAHomePage.objSearchBtn, "Search icon");
		waitTime(3000);
		type(PWASearchPage.objSearchEditBox, "Natasaarvabhowma\n", "Search bar");
		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);
		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
		List<WebElement> tabs = getDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		tabs.size();
		for (int i = 1; i <= tabs.size(); i++) {
			WebElement eleTab = getDriver()
					.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
			String tabName = eleTab.getText();
			eleTab.click();
			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");
			if (getDriver().findElements(By.xpath(
					"(//div[@class='listingGrid']//div[@class='metaData']//h3[contains(@class,'cardTitle')]//span[@class='highLight'])"))
					.size() > 0) {
				logger.info("Related search results are displayed");
				extent.extentLogger("Related search results", "Related search results are displayed");
			} else if (getDriver().findElements(By.xpath("//h3[contains(@class,'cardTitle')]")).size() > 0) {
				logger.info("Search results are displayed");
				extent.extentLogger("Search results", "Search results are displayed");
			} else {
				verifyIsElementDisplayed(PWASearchPage.objEmptyStateScreenErrormsg, "Empty state screen");
				try {
					String text = getText(PWASearchPage.emptyStateScreenText);
					if (text.equals("")) {
						logger.error("No error text is displayed in Empty State Screen");
						extent.extentLoggerFail("", "No error text is displayed in Empty State Screen");
					} else {
						logger.info("Error displayed: " + text);
						extent.extentLogger("", "Error displayed: " + text);
					}
				} catch (Exception e) {
					logger.error("Failed to fetch error text in Empty State Screen");
					extent.extentLoggerFail("", "Failed to fetch error text in Empty State Screen");
				}
			}
		}
		verifyElementPresentAndClick(PWASearchPage.objBackButton, "Back in Search box");
	}

	/**
	 * Validating that user is able search Live channel
	 */
	public void searchLiveTvChannels() throws Exception {
		extent.HeaderChildNode("Fetch Live TV Channel from Live TV tab and verify Search");
		String Channel = fetchLiveChannelname();
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, Channel + "\n", "Search bar");
		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);
		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
		verifyElementPresent(PWALiveTVPage.objrelatedChannel(Channel), "Live TV Channel " + Channel);
		verifyElementPresent(PWALiveTVPage.objrelatedChannelLiveLogo(Channel), "LIVE Logo for " + Channel);
		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
	}

	/*
	 * Validating that user is able to search content by Language
	 */
	public void searchLanguage(String language) throws Exception {
		extent.HeaderChildNode("Validating that user is able to search content by Language");

		waitTime(3000);
		type(PWASearchPage.objSearchEditBox, language + "\n", "Search bar");

		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		List<WebElement> tabs = getDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		// tabs.size();
		for (int i = 1; i <= tabs.size(); i++) {
			WebElement eleTab = getDriver()
					.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
			String tabName = eleTab.getText();
			eleTab.click();

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");
			int k = 1;
			for (k = 1; k <= 3; k++) {
				List<WebElement> AssetMetadata = getDriver().findElements(
						By.xpath("(//div[@class='clickWrapper'])[" + k + "]/child::div[@class='dateTime']/child::*"));
				// System.out.println(AssetMetadata.size());

				for (int j = 1; j <= AssetMetadata.size(); j++) {
					String metadata = getDriver().findElement(By.xpath("((//div[@class='clickWrapper'])[" + k
							+ "]/child::div[@class='dateTime']/child::*)[" + j + "]")).getText();
					// System.out.println(metadata);
					if (metadata.contains(language)) {
						logger.info("User can search a content by language");
						extent.extentLogger("Language search", "User can search a content by language");
					}

				}
			}

		}

		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");

		clearField(PWASearchPage.objSearchEditBox, "Search Bar");

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
	}

	public void searchGenre(String genre) throws Exception {
		extent.HeaderChildNode("Validating that user is able to search content by Genre");

		waitTime(3000);
		type(PWASearchPage.objSearchEditBox, genre + "\n", "Search bar");

		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		List<WebElement> tabs = getDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		// tabs.size();
		for (int i = 1; i <= tabs.size(); i++) {
			WebElement eleTab = getDriver()
					.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
			String tabName = eleTab.getText();
			eleTab.click();

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");
			int k = 1;
			for (k = 1; k <= 3; k++) {
				List<WebElement> AssetMetadata = getDriver().findElements(
						By.xpath("(//div[@class='clickWrapper'])[" + k + "]/child::div[@class='dateTime']/child::*"));
				// System.out.println(AssetMetadata.size());

				for (int j = 1; j <= AssetMetadata.size(); j++) {
					String metadata = getDriver().findElement(By.xpath("((//div[@class='clickWrapper'])[" + k
							+ "]/child::div[@class='dateTime']/child::*)[" + j + "]")).getText();
					if (metadata.contains(genre)) {
						logger.info("User can search a content by Genre");
						extent.extentLogger("Genre search", "User can search a content by Genre");
					}
				}
			}
		}

		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");

		clearField(PWASearchPage.objSearchEditBox, "Search Bar");

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
	}

	/**
	 * Function to fetch Live content from Live TV menu
	 */
	public String fetchLiveContent() throws Exception {
		navigateToAnyScreen("Live TV");
		if (waitforLiveTabToLoad()) {
			waitForElementDisplayed(PWAHomePage.objHighlightedTab("Live TV"), 10);
			waitTime(10000);
			String liveTVContentName = "";
			try {
				liveTVContentName = getText(PWALiveTVPage.objCardTitle);
			} catch (Exception e) {
				waitTime(10000);
				try {
					liveTVContentName = getText(PWALiveTVPage.objCardTitle);
				} catch (Exception e1) {
				}
			}
			logger.info("Live Show fetched from Live TV : " + liveTVContentName);
			extent.extentLogger("", "Live Show fetched from Live TV : " + liveTVContentName);
			return liveTVContentName;
		} else
			return "";
	}

	public String fetchLiveChannelname() throws Exception {
		navigateToAnyScreen("Live TV");
		waitforLiveTabToLoad();
		waitForElementDisplayed(PWAHomePage.objHighlightedTab("Live TV"), 10);
		waitTime(10000);
		String liveChannelName = "";
		try {
			liveChannelName = getText(PWALiveTVPage.objLiveChannelnameaboveCard);
		} catch (Exception e) {
			waitTime(10000);
			try {
				liveChannelName = getText(PWALiveTVPage.objLiveChannelnameaboveCard);
			} catch (Exception e1) {
			}
		}
		logger.info("Live Channel fetched from Live TV : " + liveChannelName);
		extent.extentLogger("", "Live Channel fetched from Live TV : " + liveChannelName);
		return liveChannelName;
	}

	/** =====================VINAY-LANGUAGE-MODULE======================== */

	public void LanguageModule(String userType) throws Exception {
		extent.HeaderChildNode("Verify Display Screen on tapping Language option");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		// Validate language selection option is displayed
		// click on hamburger menu
		click(PWAHomePage.objHamburgerMenu, "Humburger Menu");
		// Click on language option
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language option");
		// Verify display language screen is displayed
		waitForElementDisplayed(PWAHamburgerMenuPage.objDisplayLang, 10);
		if (getElementPropertyToString("class", PWAHamburgerMenuPage.objDisplayLang, "Display language")
				.contains("headerSelected")) {
			extent.extentLogger("Verify Display language screen is displayed",
					"Display screen is displayed on tapping language option");
			logger.info("Display screen is displayed on tapping language option");
		} else {
			extent.extentLoggerFail("Verify Display language screen is displayed",
					"Display screen is nt displayed on tapping language option");
			logger.error("Display screen is not displayed on tapping language option");
		}

		// Verify that default display language is English
		extent.HeaderChildNode("Verify default display language is English");
		String defaultLang = getElementPropertyToString("class", PWALanguageSettingsPage.objSelectedLang,
				"Default Language");
		if (defaultLang.contains("checkedHighlight")) {
			String selectedLang = getElementPropertyToString("innerText",
					PWALanguageSettingsPage.objLanguage("English"), "Language");
			if (selectedLang.equals("English")) {
				extent.extentLogger("Verify default language", "English is selected by defalut");
				logger.info(selectedLang + " language is selected by default");
			} else {
				extent.extentLoggerFail("Verify default language", "English is selected by defalut");
				logger.error("By default " + selectedLang + " is displayed");
			}
		}

		// Verify user can select desired display language
		extent.HeaderChildNode("Verify user can select desired display language");
		// Verify user can Hindi language
		click(PWALanguageSettingsPage.objLanguage("Hindi"), "Hindi display language");
		// Verify selected language
		String HindiLang = getElementPropertyToString("class", PWALanguageSettingsPage.objSelectedLang,
				"Default Language");
		if (HindiLang.contains("checkedHighlight")) {
			String selectedDisplayLang = getElementPropertyToString("innerText",
					PWALanguageSettingsPage.objLanguage("Hindi"), "Language");
			extent.extentLogger("Selected display language", selectedDisplayLang + " is selected in display screen ");
			logger.info(selectedDisplayLang + " is selected from display screen");
		} else {
			extent.extentLoggerFail("Select display language", "Unable to select the display language");
			logger.info("Unable to select the display language");
		}
		// Verify user can Marathi language
		click(PWALanguageSettingsPage.objLanguage("Marathi"), "Marathi display language");
		// Verify selected language
		String MaratiLang = getElementPropertyToString("class", PWALanguageSettingsPage.objSelectedLang,
				"Default Language");
		if (MaratiLang.contains("checkedHighlight")) {
			String selectedDisplayLang = getElementPropertyToString("innerText",
					PWALanguageSettingsPage.objLanguage("Marathi"), "Language");
			extent.extentLogger("Selected display language", selectedDisplayLang + " is selected in display screen ");
			logger.info(selectedDisplayLang + " is selected from display screen");
		} else {
			extent.extentLoggerFail("Select display language", "Unable to select the display language");
			logger.info("Unable to select the display language");
		}
		// Verify user can Telugu language
		click(PWALanguageSettingsPage.objLanguage("Telugu"), "Telugu display language");
		// Verify selected language
		getElementPropertyToString("class", PWALanguageSettingsPage.objSelectedLang, "Default Language");
		if (MaratiLang.contains("checkedHighlight")) {
			String selectedDisplayLang = getElementPropertyToString("innerText",
					PWALanguageSettingsPage.objLanguage("Telugu"), "Language");
			extent.extentLogger("Selected display language", selectedDisplayLang + " is selected in display screen ");
			logger.info(selectedDisplayLang + " is selected from display screen");
		} else {
			extent.extentLoggerFail("Select display language", "Unable to select the display language");
			logger.info("Unable to select the display language");
		}

		// Verify user can select only one Display language
		extent.HeaderChildNode("Verify user can select only one Display language");
		// Verify selected Display language is applied
		// Select Kannada display language
		click(PWALanguageSettingsPage.objLanguage("Kannada"), "Kannada display language");
		// Verify selected language
		String selectedLangInDisplayScreen = getElementPropertyToString("class",
				PWALanguageSettingsPage.objSelectedLang, "Default Language");
		if (selectedLangInDisplayScreen.contains("checkedHighlight")) {
			String selectedDisplayLang = getElementPropertyToString("innerText",
					PWALanguageSettingsPage.objLanguage("Kannada"), "Language");
			extent.extentLogger("Selected display language", selectedDisplayLang + " is selected in display screen ");
			logger.info(selectedDisplayLang + " is selected from display screen");
		} else {
			extent.extentLoggerFail("Select display language", "Unable to select the display language");
			logger.info("Unable to select the display language");
		}

		// Verify the selected language is applied in home page
		extent.HeaderChildNode("Verify the selected language is applied in home page");
		click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Display Language screen");
		click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Content Language screen");

		for (int i = 0; i < 10; i++) {
			try {
				waitTime(5000);
				String trayHeader = getElementPropertyToString("class", PWALanguageSettingsPage.objTrayHeader,
						"Tray header");
				if (trayHeader.contains("kn_regionalLang")) {
					extent.extentLogger(" Verify selected display Languge",
							"The selected display langguage is applied successfully");
					logger.info("The selected display language is applied successfully");
					break;
				} else {
					extent.extentLoggerFail(" Verify selected display Languge",
							"The selected display langguage is not applied successfully");
					logger.error("The selected display language is not applied successfully");
				}
			} catch (StaleElementReferenceException e) {
			}
		}
		// Click on Humburger menu
		click(PWAHomePage.objHamburgerMenu, "Humburger Menu");
		// Click on language option
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language option");
		waitTime(2000);
		// Select English
		click(PWALanguageSettingsPage.objEnglishLang, "English language");
		click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Display Language screen");
		click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Content Language screen");
		for (int i = 0; i < 10; i++) {
			try {
				waitTime(5000);
				click(PWAHomePage.objHamburgerMenu, "Humburger Menu");
				break;
			} catch (StaleElementReferenceException e) {
			}
		}
		extent.HeaderChildNode("Content screen is displayed on tapping Content language option");
		// Click on Hamburger menu
		// click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
		// Click on language option
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language option");
		// Click on Content language button
		waitForElementDisplayed(PWAHamburgerMenuPage.objContentLanguage, 10);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objContentLanguage, "Content language");
		// Verify user is navigated to Content Language screen post tapping content
		// language option
		if (getElementPropertyToString("class", PWAHamburgerMenuPage.objContentLanguage, "Content language")
				.contains("headerSelected")) {
			extent.extentLogger("Verify Content language screen is displayed",
					"Content screen is displayed on tapping Content language option");
			logger.info("Content screen is displayed on tapping Content language option");
		} else {
			extent.extentLoggerFail("Verify Content language screen is displayed",
					"Content language screen is not displayed on tapping content language option");
			logger.info("Content language screen is not displayed on tapping Content language option");
		}
		click(PWALanguageSettingsPage.objSelectedLang, "Selected language");
		Thread.sleep(2000);
		click(PWALanguageSettingsPage.objSelectedLang, "Selected language");

		// Verify user can select multiple Content languages
		extent.HeaderChildNode("Verify user can select multiple Content languages");
		for (int i = 1; i <= 2; i++) {
			String language = getElementPropertyToString("innerText", PWALanguageSettingsPage.objAllLangByindex(i),
					"Language");
			click(PWALanguageSettingsPage.objAllLangByindex(i), language + " Language");
		}
		int size = getDriver().findElements(PWALanguageSettingsPage.objSelectedLang).size();
		if (size > 1) {
			extent.extentLogger("Selected content languages : ", +size + "");
			extent.extentLogger("Verify user can select multiple content languages",
					"User can select multiple Content languages");
			logger.info("User can select multiple Content languages");
		} else {
			extent.extentLoggerFail("Verify user can select multiple content languages",
					"User can not select multiple Content languages");
			logger.error("User can not select multiple Content languages");
		}

		// Verify user should not be able to apply the changes if he deselect all the
		extent.HeaderChildNode("Verify apply button disabled if deselect all");
		// language.
		int selectedlang = getDriver().findElements(PWALanguageSettingsPage.objSelectedLang).size();
		for (int i = 1; i <= selectedlang; i++) {
			click(PWALanguageSettingsPage.objSelectedLang, "Selected language");

		}
		// Verify apply button is disabled
		String disabledApplyBtn = getElementPropertyToString("class", PWALanguageSettingsPage.objDisabledApplyButton,
				"Apply button");
		if (disabledApplyBtn.contains("disable")) {
			extent.extentLogger("Verify Content language screen",
					"User can not apply changes if he deselect all the content languages");
			logger.info("User can not apply changes if he deselect all the content languages");
		} else {
			extent.extentLoggerFail("Verify Content language screen",
					"User can apply changes if he deselect all the content languages");
			logger.error("User can apply changes if he deselect all the content languages");
		}

		// Verify User able to Switch to Content Language section from Display Language
		extent.HeaderChildNode("Verify navigation to content language screen");
		// and Content language
		// click on Display language
		click(PWAHamburgerMenuPage.objDisplayLang, "Display language");
		// Verify user is navigated to content language screen post tapping content
		// language screen
		click(PWAHamburgerMenuPage.objContentLanguage, "Content language");
		if (getElementPropertyToString("class", PWAHamburgerMenuPage.objContentLanguage, "Content language")
				.contains("headerSelected")) {
			extent.extentLogger("Verify Content language screen is displayed",
					"Content screen is displayed on tapping Content language option");
			logger.info("Content screen is displayed on tapping Content language option");
		} else {
			extent.extentLoggerFail("Verify Content language screen is displayed",
					"Content language screen is not displayed on tapping content language option");
			logger.error("Content language screen is not displayed on tapping Content language option");
		}
		click(PWAHamburgerMenuPage.objDisplayLang, "Display language");
		scrollLanguagePopUpsVerification("Display language Popup");
		click(PWAHamburgerMenuPage.objContentLanguage, "Content language");
		scrollLanguagePopUpsVerification("Content Language Popup");
		// Click on cancel button
		click(PWALanguageSettingsPage.objCancelBtn, "Cancel button");
	}

	public void scrollLanguagePopUpsVerification(String popup) throws Exception {
		extent.HeaderChildNode("Scroll Up/Down functionality in " + popup);
		// Scroll Up Check
		verifyElementPresent(PWALanguageSettingsPage.objEnglishLangInDLPopUp, "English radio button");
		WebElement english = getDriver().findElement(PWALanguageSettingsPage.objEnglishLangInDLPopUp);
		int beforeScrollX = english.getLocation().x;
		int beforeScrollY = english.getLocation().y;
		logger.info("Current x coordinate of English radio button: " + beforeScrollX);
		extent.extentLogger("beforeScroll", "Current x coordinate of English radio button: " + beforeScrollX);
		logger.info("Current y coordinate of English radio button: " + beforeScrollY);
		extent.extentLogger("beforeScroll", "Current y coordinate of English radio button: " + beforeScrollY);
		popUpSwipe("UP", 2);
		english = getDriver().findElement(PWALanguageSettingsPage.objEnglishLangInDLPopUp);
		int afterScrollX = english.getLocation().x;
		int afterScrollY = english.getLocation().y;
		logger.info("x coordinate of English radio button after Scroll Up: " + afterScrollX);
		extent.extentLogger("afterScroll", "x coordinate of English radio button after Scroll Up: " + afterScrollX);
		logger.info("y coordinate of English radio button after Scroll Up: " + afterScrollY);
		extent.extentLogger("afterScroll", "y coordinate of English radio button after Scroll Up: " + afterScrollY);
		if (afterScrollY < beforeScrollY) {
			logger.info("User is able to scroll up on " + popup);
			extent.extentLogger("scrollPass", "User is able to scroll up on " + popup);
		} else {
			logger.error("Scrolling Up is failed in " + popup);
			extent.extentLoggerFail("scrollFail", "Scrolling Up is failed in " + popup);
		}
		// Scroll Down check
		waitTime(2000);
		verifyElementPresent(PWALanguageSettingsPage.objEnglishLangInDLPopUp, "English radio button");
		english = getDriver().findElement(PWALanguageSettingsPage.objEnglishLangInDLPopUp);
		beforeScrollX = english.getLocation().x;
		beforeScrollY = english.getLocation().y;
		logger.info("Current x coordinate of English radio button: " + beforeScrollX);
		extent.extentLogger("beforeScroll", "Current x coordinate of English radio button: " + beforeScrollX);
		logger.info("Current y coordinate of English radio button: " + beforeScrollY);
		extent.extentLogger("beforeScroll", "Current y coordinate of English radio button: " + beforeScrollY);
		popUpSwipe("DOWN", 2);
		english = getDriver().findElement(PWALanguageSettingsPage.objEnglishLangInDLPopUp);
		afterScrollX = english.getLocation().x;
		afterScrollY = english.getLocation().y;
		logger.info("x coordinate of English radio button after Scroll Down: " + afterScrollX);
		extent.extentLogger("afterScroll", "x coordinate of English radio button after Scroll Down: " + afterScrollX);
		logger.info("y coordinate of English radio button after Scroll Down: " + afterScrollY);
		extent.extentLogger("afterScroll", "y coordinate of English radio button after Scroll Down: " + afterScrollY);
		if (afterScrollY > beforeScrollY) {
			logger.info("User is be able to scroll up on " + popup);
			extent.extentLogger("scrollPass", "User is be able to scroll up on " + popup);
		} else {
			logger.error("Scrolling Up is failed in " + popup);
			extent.extentLoggerFail("scrollFail", "Scrolling Up is failed in " + popup);
		}
	}

	@SuppressWarnings("rawtypes")
	public void popUpSwipe(String direction, int count) throws Exception {
		touchAction = new TouchAction(getDriver());
		String dire = direction;
		try {
			if (dire.equalsIgnoreCase("UP")) {
				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.6);
					int endy = (int) (size.height * 0.4);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, endy)).release().perform();
					logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeUp",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("DOWN")) {
				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.6);
					int endy = (int) (size.height * 0.4);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeDown",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			}

		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Generic function to Logout.
	 */
	public void logout() throws Exception {
		extent.HeaderChildNode("Logout");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		waitTime(2000);
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "Expander button");
		PartialSwipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("Logout"), "Logout option");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		if (verifyIsElementDisplayed(PWALoginPage.objLoginBtn, "Login button")) {
			logger.info("User is logged out successfully");
			extent.extentLogger("Logged out", "User is logged out successfully");
		}
		click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Close button");
	}

	/** ==================Yashashwini - =================================== */

	public void Zee5NewsScreen(String UserType) throws Exception {

		switch (UserType) {

		case "Guest":
			extent.HeaderChildNode("Guest user");
			extent.extentLogger("Accessing as Guest User", "Accessing as Guest User");
			logger.info("Accessing as Guest User");
			Zee5PWANewsValidation();
			Zee5PWANewsViewAll();
			Zee5PWANewsTraysValidation();
			Zee5PWANewsPlayerValidation();
			Zee5PWANewsAutoplayValidation();
			Zee5PWANewsNonLiveLandscapeDisplay();

			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("NonSubscribedUser");
			extent.extentLogger("Accessing as NonSubscribedUser User", "Accessing as NonSubscribedUser User");
			logger.info("Accessing as NonSubscribedUser User");
			ZeePWALogin("Mobile", "NonSubscribedUser");
			Zee5PWANewsValidation();
			Zee5PWANewsViewAll();
			Zee5PWANewsTraysValidation();
			Zee5PWANewsPlayerValidation();
			Zee5PWANewsAutoplayValidation();
			Zee5PWANewsNonLiveLandscapeDisplay();

			break;

		case "SubscribedUser":
			extent.HeaderChildNode("SubscribedUser");
			extent.extentLogger("Accessing as SubscribedUser User", "Accessing as SubscribedUser User");
			logger.info("Accessing as SubscribedUser User");
//				dismissDisplayContentLanguagePopUp();
			ZeePWALogin("E-mail", "SubscribedUser");
			Zee5PWANewsValidation();
			Zee5PWANewsViewAll();
			Zee5PWANewsTraysValidation2();
			Zee5PWANewsPlayerValidation();
			Zee5PWANewsAutoplayValidation();
			Zee5PWANewsNonLiveLandscapeDisplay();
		}
	}

	/*
	 * Verify whether user is able to navigateNews landing page when user tap on
	 * News from Categories listed in Home page and it is highlighted.
	 */
	public void Zee5PWANewsValidation() throws Exception {
		extent.HeaderChildNode("News Tab");
		verifyElementPresentAndClick(PWAHomePage.objPWANews, "News tab in Home_page");
		waitTime(3000);
		if (verifyElementPresent(PWAHomePage.objHighlightedTab("News"), "Highlated News Tab")) {
			logger.info("User is navigated to News landing page");
			extent.extentLogger("News landing Page", "User is navigated News page");
		}
		waitTime(3000);
	}

	/*
	 * view all button functionality.
	 */
	public void Zee5PWANewsViewAll() throws Exception {
		extent.HeaderChildNode("ViewAll landing page");

		verifyElementPresentAndClick(PWANewsPage.objViewAll, "View all arrow");
		waitTime(3000);
//		System.out.println(getDriver().getCurrentUrl());

		if ((getDriver().getCurrentUrl()).contains("live") == true) {
			System.out.println("ViewAll Wrap page displayed");
			logger.info("User navigate to ViewAll Wrap page after clicking on ViewAll");
			extent.extentLogger("News landing Page", "User navigated to ViewAll wrap");
		} else {
			System.out.println("ViewAll Wrap page not displayed");
			logger.info("User didn't navigate to ViewAll Wrap page after clicking on ViewAll");
			extent.extentLogger("News landing Page", "User didn't navigated to ViewAll wrap");
		}

		Back(1);

	}

	/*
	 * Verify the trays displayed in the "News" page. (for example): -Live News -VOD
	 */

	public void Zee5PWANewsTraysValidation() throws Exception {
		extent.HeaderChildNode("Verifing the trays displayed in News Tab");
		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);

		Response resp = ResponseInstance.getResponseForPages("News", languageSmallText);

		List<String> apiTitleList = new LinkedList<String>();

		List<String> apitotaltrays = resp.jsonPath().getList("buckets");
		System.out.println(apitotaltrays.size());
		for (int i = 1; i < apitotaltrays.size(); i++) {
			String traytitle = resp.jsonPath().getString("buckets[" + i + "].title");
			apiTitleList.add(traytitle);
		}

		List<String> uiTitleList = new LinkedList<String>();

		List<WebElement> uitotaltrays = findElements(By.xpath("//div[@class='trayHeader']"));

		for (int j = 0; j < uitotaltrays.size() - 1; j++) {
			String trayTitle = findElement(By.xpath("(//div[@class='trayHeader'])[" + (j + 1) + "]")).getText();
			uiTitleList.add(trayTitle);
			PartialSwipe("UP", 1);

			if (apiTitleList.get(j).equalsIgnoreCase(uiTitleList.get(j))) {
				logger.info("API title: " + apiTitleList.get(j) + " is verified with UI title: " + uiTitleList.get(j));
				extent.extentLogger("Tray validation",
						"API title: " + apiTitleList.get(j) + " is verified with UI title: " + uiTitleList.get(j));

			}
		}
		System.out.println("UI: " + uiTitleList);
		waitTime(3000);
	}
	// ---------------

	public void Zee5PWANewsTraysValidation2() throws Exception {
		extent.HeaderChildNode("Verifing the trays displayed in News Tab");
		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);

		Response resp = ResponseInstance.getResponseForPages("News", languageSmallText);

		List<String> apiTitleList = new LinkedList<String>();

		List<String> apitotaltrays = resp.jsonPath().getList("buckets");
		System.out.println(apitotaltrays.size());
		for (int i = 1; i < apitotaltrays.size(); i++) {
			String traytitle = resp.jsonPath().getString("buckets[" + i + "].title");
			apiTitleList.add(traytitle);
		}

		System.out.println("api: " + apiTitleList);

		String Content_Title = resp.jsonPath().getString("buckets[1].items[0].title");
		for (int i = 1; i < Content_Title.length(); i++) {
			System.out.println("Content Title is " + Content_Title + "");
			waitTime(3000);
			if (verifyIsElementDisplayed(TitleTextToXpath(Content_Title), Content_Title)) {
				System.out.println("Content title Found");
				verifyElementPresent(TitleTextToXpath(Content_Title), "Playable Content");
			}
			System.out.println("api: " + Content_Title);

		}

		List<String> uiTitleList = new LinkedList<String>();
		List<String> uiContent = new LinkedList<String>();

		List<WebElement> uitotaltrays = findElements(By.xpath("//div[@class='trayHeader']"));
		System.out.println(uitotaltrays.size());

		for (int j = 0; j < uitotaltrays.size() - 1; j++) {
			String trayTitle = findElement(By.xpath("(//div[@class='trayHeader'])[" + (j + 1) + "]")).getText();
			uiTitleList.add(trayTitle);
			PartialSwipe("UP", 1);

			if (apiTitleList.get(j).equalsIgnoreCase(uiTitleList.get(j))) {
				logger.info("API title: " + apiTitleList.get(j) + " is verified with UI title: " + uiTitleList.get(j));
				extent.extentLogger("Tray validation",
						"API title: " + apiTitleList.get(j) + " is verified with UI title: " + uiTitleList.get(j));

			}
		}
		// content validation
		List<WebElement> uiContenttray = findElements(By.xpath("//div[@class='trayContentWrap']"));

		for (int j = 0; j < uiContenttray.size() - 1; j++) {
			String trayContent = findElement(By.xpath("(//div[@class='trayContentWrap'])[" + (j + 1) + "]")).getText();
			uiContent.add(trayContent);
			PartialSwipe("UP", 1);

			if (Content_Title.equalsIgnoreCase(uiContent.get(j))) {
				logger.info("API Content title: " + apiTitleList.get(j) + " is verified with UI content: "
						+ uiContent.get(j));
				extent.extentLogger("Tray validation",
						"API content: " + apiTitleList.get(j) + " is verified with UI content: " + uiContent.get(j));

			}
		}
		waitTime(3000);
	}

	/*
	 * Verify the player control given on News landing page: Play/Pause button is
	 * shown on player 10 sec backword/forward buttons are not shown "LIVE" in red
	 * box shown near setting Setting optinon is given Full screen mode is given
	 */

	public void Zee5PWANewsPlayerValidation() throws Exception {

		extent.HeaderChildNode("NewsPage Player Validation");
		// click(PWAHomePage.objTabName("News"), "News tab");

		verifyElementPresentAndClick(PWANewsPage.objContent, "First content in the carousel");
		waitForElementDisplayed(PWANewsPage.ContentPlayer, 30);
		waitTime(5000);
		playerTap();
		waitTime(2000);
		verifyElementPresentAndClick(PWAPlayerPage.pauseBtn, "Pause icon on player screen");
		playerTap();
		waitTime(2000);
		verifyElementPresent(PWAPlayerPage.playBtn, "Play icon on player screen");
		playerTap();
		waitTime(2000);
		verifyElementNotPresent(PWAPlayerPage.rewind10SecBtn, 10);
		playerTap();
		waitTime(2000);
		verifyElementNotPresent(PWAPlayerPage.forward10SecBtn, 10);
		playerTap();
		waitTime(2000);
		verifyElementPresent(PWANewsPage.objLive, "Live mark on player");
		playerTap();
		waitTime(2000);
		verifyElementPresent(PWAPlayerPage.settingsBtn, "Settings option on player screen");
		playerTap();
		waitTime(2000);
		verifyElementPresent(PWANewsPage.objFullScreen, "Fullscreen icon");
		waitTime(2000);
		if (verifyIsElementDisplayed(PWANewsPage.objLive, "Live mark")) {
			System.out.println("Player control validation on Live News is complete");
			logger.info("User successfully validated the player controls");
			extent.extentLogger("News Player screen", "User successfully validates player controls");
		} else {
			System.out.println("Player control validation on Live News is not complete");
			logger.info("User didn't navigate to ViewAll Wrap page after clicking on ViewAll");
			extent.extentLoggerFail("News Player screen", "User failed to validates player controls");
		}

		Back(1);
	}

	// Verify whether user is navigate to consumption page with News autoplaying
	// when user tap on content in Listed collection.

	public void Zee5PWANewsAutoplayValidation() throws Exception {
		extent.HeaderChildNode("Autoplaying of content on consumption page");
		click(PWAHomePage.objTabName("News"), "News tab");

		waitTime(2000);
		verifyElementPresentAndClick(PWANewsPage.objNonLiveNews, "NonLive news content");
		waitTime(5000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(12000);
		playerTap();
		waitTime(2000);

		try {
			waitTime(2000);
			verifyElementPresent(PWANewsPage.objSeekbar, "Seek bar on NonLiveNews");
			waitTime(2000);
			getPlayerDuration();
			System.out.println("Autoplaying functionality checked");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some error occured");
		}
		if (verifyIsElementDisplayed(PWANewsPage.objSeekbar, "Seekbar verification")) {

			logger.info("Successfully Autoplaying of content on consumption page is validated");
			extent.extentLogger("News consumption screen", "User successfully validated the autoplay functionality");
		} else {

			logger.info("Failed to validate autoplaying");
			extent.extentLoggerFail("News consumption screen", "User failed to validate the autoplay functionality");
		}
		Back(1);
	}

	public By TitleTextToXpath(String Title) throws Exception {
		return By.xpath("//*[@title='" + Title + "']");

	}

	public void getPlayerDuration() {
		String duration = getElementPropertyToString("innerText", PWAPlayerPage.objPlayerCurrentDuration,
				"Player Current Duration").toString();
		if (duration != null) {
			extent.extentLogger("contentDuration", "Successfully played content till " + duration);
			logger.info("Successfully played content till " + duration);
		} else {
			extent.extentLoggerFail("durationFailed", "Failed to get Current Duration");
			logger.error("Failed to get Current Duration");
		}
	}

	// Verify News in landscape mode is playing without any error.

	public void Zee5PWANewsNonLiveLandscapeDisplay() throws Exception {
		extent.HeaderChildNode("Landscape mode display of VODNews");
		// click(PWAHomePage.objTabName("News"), "News tab");

		waitTime(2000);
		verifyElementPresentAndClick(PWANewsPage.objNonLiveNews, "NonLive news content");

		if (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Sign Up Pop Up ")) {
			click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Sign Up Pop Up close button");
		}
//		waitForPlayerLoaderToComplete();
		waitTime(8000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(12000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresentAndClick(PWAPlayerPage.maximizeBtn, "Maximize window icon");
		waitTime(15000);
		if (getDriver().findElement(PWAPlayerPage.maximizeBtn).isDisplayed() == false) {
			logger.info("Content playing in landscape mode");
			extent.extentLogger("News consumption screen", "User successfully validated the Landscape display");
		} else {
			logger.info("Content is not playing in landscape mode");
		}
		waitTime(8000);
		verifyElementPresentAndClick(PWAPlayerPage.minimizeBtn, "Minimize window icon");
		waitTime(3000);
	}

	/**
	 * =================HITESH - MUSIC PAGE========================
	 * 
	 * @throws Exception
	 */

	public void musicPageTrayValidation(String tabName) throws Exception {
		extent.HeaderChildNode("Verifing the Trays displayed in Music Page");
		navigateToAnyScreen(tabName);
		String languageSmallText = allSelectedLanguages();
		Response resp = ResponseInstance.getResponseForPages("music", languageSmallText);
		List<String> apiTitleList = new LinkedList<String>();
		List<String> apitotaltrays = resp.jsonPath().getList("buckets");
		System.out.println(apitotaltrays.size());
		for (int i = 1; i < apitotaltrays.size(); i++) {
			String traytitle = resp.jsonPath().getString("buckets[" + i + "].title");
			apiTitleList.add(traytitle);
		}
		logger.info("Trays from API: " + apiTitleList);
		extent.extentLogger("", "Trays from API: " + apiTitleList);
		for (int j = 0; j < apiTitleList.size(); j++) {
			String apititle = apiTitleList.get(j);
			try {
				findElement(By.xpath("(//div[@class='trayHeader'])//h2[.='" + apititle + "']")).getText();
				logger.info("Located Tray " + apititle + " in UI");
				extent.extentLogger("", "Located Tray " + apititle + " in UI");
			} catch (Exception e) {
				PartialSwipe("UP", 1);
				try {
					findElement(By.xpath("(//div[@class='trayHeader'])//h2[.='" + apititle + "']")).getText();
					logger.info("Located Tray " + apititle + " in UI");
					extent.extentLogger("", "Located Tray " + apititle + " in UI");
				} catch (Exception e1) {
					logger.error("Failed to locate Tray " + apititle + " in UI");
					extent.extentLoggerFail("", "Failed to locate Tray " + apititle + " in UI");
				}
			}
		}
		verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee5 Logo");
	}

	public void navigateToMusicTab(String userType) throws Exception {
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		HeaderChildNode("Verification of Subscribe Pop Up");
		navigateToAnyScreen("Music");
		if (findElements(PWAMusicPage.objPremiumTag).size() > 0) {
			findElements(PWAMusicPage.objPremiumTag).get(1).click();
			verifyElementPresent(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe pop up");
			verifyElementPresentAndClick(PWAMusicPage.objGetPremiumCloseBtn, "Close Button");
			if (verifyIsElementDisplayed(PWAMusicPage.objRecommendedTrayHeader, "Recommended tray")) {
				logger.info("Recommended tray is displayed in Music consumption page");
				extent.extentLogger("Recommended tray", "Recommended tray is displayed in Music consumption page");
			} else {
				logger.info("Recommended videos is not displayed in Music consumption page");
				extent.extentLogger("Recommended tray",
						"Recommended videos is not displayed in Music consumption page");
			}
			Back(1);
		} else {
			logger.info("There are no Premium contents available in Music Page");
			extent.extentLogger("", "There are no Premium contents available in Music Page");
		}
		HeaderChildNode("Verification of navigation after tapping on Music Card");
		String Title = "", ConsumptionTitle = "";
		try {
			Title = getText(PWAMusicPage.objMusicCardTitleInMusicTab);
			logger.info("Title of the card fetched from Music Page: " + Title);
			extent.extentLogger("", "Title of the card fetched from Music Page: " + Title);
		} catch (Exception e) {
			logger.error("Failed to fetch Title of the card from Music Page");
			extent.extentLoggerFail("", "Failed to fetch Title of the card from Music Page");
		}
		boolean clickedasset = false;
		for (int i = 0; i < 5; i++) {
			if (directClickReturnBoolean(PWAMusicPage.objMusicCardImageInMusicTab, "Music Card")) {
				clickedasset = true;
				break;
			} else {
				Swipe("UP", 1);
				waitTime(2000);
			}
		}
		if (clickedasset == true) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Sign Up pop up")) {
				click(PWAMusicPage.objWEBCloseBtnLoginPopup, "Sign Up Pop up close button");
			}
			try {
				ConsumptionTitle = getElementPropertyToString("innerText", PWAMusicPage.objConsumptionPageTitle,
						"Music Consumptions Title").toString();
				logger.info("Title of the card fetched from Music Consumptions Page: " + ConsumptionTitle);
				extent.extentLogger("", "Title of the card fetched from Music Consumptions Page: " + ConsumptionTitle);
			} catch (Exception e) {
				logger.error("Failed to fetch Title of the card from Music Consumptions Page");
				extent.extentLoggerFail("", "Failed to fetch Title of the card from Music Consumptions Page");
			}
			if (Title.contains(ConsumptionTitle)) {
				logger.info("Successfuly navigated to the right Consumptions page");
				extent.extentLogger("Consumption page", "Successfuly navigated to the right Consumptions page");
				// Verify Recommended trays in consumptions page
				if (verifyIsElementDisplayed(PWAMusicPage.objRecommendedTrayHeader, "Recommended Videos tray")) {
					logger.info("Verified that Recommended Videos tray is displayed in Music Consumptions page");
					extent.extentLogger("Recommended tray",
							"Verified that Recommended Videos tray is displayed in Music Consumptions page");
				} else {
					logger.error("Recommended Videos is not displayed in Music Consumptions page");
					extent.extentLoggerFail("Recommended tray",
							"Recommended Videos is not displayed in Music Consumptions page");
				}
			} else {
				logger.error("Failed to navigate to the right Consumptions page");
				extent.extentLoggerFail("Consumption page", "Failed to navigate to the right Consumptions page");
			}
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
				click(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up close icon");
			}
		} else {
			logger.error("Failed to click on Music card from tray");
			extent.extentLoggerFail("Consumption page", "Failed to click on Music card from tray");
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");

	}

//	=========================================================================================

	public void GetAndVerifyPWAOrientaion(String Value) {
		ScreenOrientation orientation = getDriver().getOrientation();
		String ScreenOrientation = orientation.toString();
		try {
			logger.info("The screen Orientation is " + ScreenOrientation);
			extent.extentLogger("Screen Orientation", "The screen Orientation is " + ScreenOrientation);
		} catch (Exception e) {
			logger.error("The screen Orientation is not " + ScreenOrientation);
			extent.extentLoggerFail("Screen Orientation", "The screen Orientation is not " + ScreenOrientation);
		}
	}

	/** =========================Shreenidhi - PROFILE===================== */

	/**
	 * Validation of Profile Functionality
	 */ // manas updated
	public void profileFunctionalitySanity(String userType) throws Exception {
		extent.HeaderChildNode("Profile page Functionality");
		Swipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		verifyElementPresentAndClick(PWAHomePage.objHamburgerMenu, "Hamburger menu");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIcon, "Profile icon");
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objMyAccountOptionsText("My Account"), "My Account")) {
			logger.info("User is navigated to My profile page");
			extent.extentLogger("My profile", "User is navigated to My profile page");
		}
		Back(1);
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objMyAccountOptionsText("My Account"),
				"My Account") == false) {
			logger.info("User is navigated to previous page");
			extent.extentLogger("Previous page", "User is navigated to previous page");
		}
		Swipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIcon, "profile icon");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objMyAccountOptionsText("My Account"), "My Account");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objProfilePageNameTxt, "User name");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objProfilePageUserIdTxt, "User id");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "hamburger menu");
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "expander button");
		NavigationsToMyWatchlist();
		NavigationsToMyReminders();
		NavigationsToMySubsccription(userType);
		NavigationsToMyTransactions();
		extent.HeaderChildNode("Edit page Functionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIcon, "profile icon");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objChangePasswordBtn, "Change password button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		waitTime(4000);
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objEditProfileText, "Edit profile page");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objEditProfileFirstName, "Name field");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objEditProfileEmailField, "Email field");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objEditProfileMobileNumber, "Mobile field");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objEditProfileGender, "Gender field");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objEditProfileDOB, "Date of birth field");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objEditProfileGoBackBtn, "Go back button");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "Save changes Button");
		getDriver().findElement(PWAHamburgerMenuPage.objEditProfileFirstName).clear();
		type(PWAHamburgerMenuPage.objEditProfileFirstName, "Zee5Igs\n", "First name");
		hideKeyboard();
		waitTime(3000);
		click(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "Save changes Button");
		try {
			Boolean SavedChangesToastMessage = getDriver().findElement(By.xpath("//*[@class='toastMessage']"))
					.isDisplayed();
			if (SavedChangesToastMessage == true) {
				extent.extentLogger("Toast", "Saved Changes Toast Message displayed");
				logger.info("Saved Changes Toast Message displayed");
			} else {
				extent.extentLogger("Toast", "Saved Changes Toast Message not displayed");
				logger.info("Saved Changes Toast Message not displayed");
			}
		} catch (Exception e) {
			System.out.println("Toast message is not displayed");
		}
		waitTime(5000);
		click(PWAHamburgerMenuPage.objEditProfileGoBackBtn, "Go back button");
		extent.HeaderChildNode("Change password page Functionality");
		waitTime(3000);
		click(PWAHamburgerMenuPage.objChangePasswordBtn, "Change password button");
		waitTime(4000);
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objChangePasswordText, "change password page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileGoBackBtn, "Go back button");
		waitTime(4000);
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objMyAccountOptionsText("My Account"), "My Account")) {
			logger.info("User is navigated back to my profile page");
			extent.extentLogger("My profile", "User is navigated back to my profile page");
		}
		click(PWAHamburgerMenuPage.objChangePasswordBtn, "Change password button");
		waitTime(4000);
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objChangePasswordText, "change password page");
		type(PWAHamburgerMenuPage.objChangeOldPassword, "User@123\n", "Current password field");
		hideKeyboard();
		String password = getText(PWAHamburgerMenuPage.objChangeOldPassword);
		if (password != null) {
			logger.info("User is able to enter numbers and special character");
			extent.extentLogger("Password", "User is able to enter numbers and special character");
		}
		type(PWAHamburgerMenuPage.objNewPassword, "abc\n", "password field");
		hideKeyboard();
		click(PWAHamburgerMenuPage.objUpdatePasswordButton, "update button");
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objPasswordErrorText, "Error text")) {
			logger.info("Minimun 6 characters error message is displayed");
			extent.extentLogger("Error message", "Minimun 6 characters error message is displayed");
		}
		if (getDriver().findElement(PWAHamburgerMenuPage.objUpdatePasswordButton).isEnabled() == false) {
			logger.info("Updated button is not enabled when password field is empty");
			extent.extentLogger("Update button", "Updated button is not enabled when password field is empty");
		}
		getDriver().findElement(PWAHamburgerMenuPage.objNewPassword).clear();
		waitTime(5000);
		type(PWAHamburgerMenuPage.objNewPassword, "igszee5\n", "password field");
		hideKeyboard();
		type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5\n", "Current confirm field");
		hideKeyboard();
		if (getDriver().findElement(PWAHamburgerMenuPage.objUpdatePasswordButton).isEnabled() == true) {
			logger.info("Updated button is enabled when password field is not empty");
			extent.extentLogger("Update button", "Updated button is enabled when password field is not empty");
		}
		click(PWAHomePage.objZeeLogo, "zee logo");
		logout();
	}

	/**
	 * Validation of Profile Functionality according user types.
	 */
	public void myProfileScenarios(String userType) throws Exception {

		switch (userType) {
		case "NonSubscribedUser":
			extent.HeaderChildNode("Profile Scenarios");
			extent.extentLogger("Accessing as Non-Subscribed User", "Profile Scenarios for Non-Subscribed User");
			logger.info("Profile Scenarios for Non-Subscribed User");
			profileFunctionalitySanity(userType);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Profile Scenarios");
			extent.extentLogger("Accessing as Subscribed User", "Profile Scenarios for Subscribed User");
			logger.info("Profile Scenarios for Subscribed User");
			profileFunctionalitySanity(userType);
		}
	}

	/**
	 * Function for Navigation to MyWatchlist .
	 */
	public void NavigationsToMyWatchlist() throws Exception {
		extent.HeaderChildNode("My Watchlist");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("My Watchlist"), "My watchlist");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Watchlist"), "My Watchlist page");
		Back(1);
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		waitTime(2000);
	}

	/**
	 * Function for Navigation to MyReminders .
	 */
	public void NavigationsToMyReminders() throws Exception {
		extent.HeaderChildNode("My Reminders");
		click(PWAHamburgerMenuPage.myAccount, "Expander button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("My Reminders"), "My Reminders");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Reminders"), "My Reminders page");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objMyAccountOptionsText("My Reminders"), "My Reminders");
		Back(1);
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		waitTime(2000);
	}

	/**
	 * Function for Navigation to MySubscription.
	 */
	public void NavigationsToMySubsccription(String userType) throws Exception {
		extent.HeaderChildNode("My subscriptions");
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "expander button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("My Subscription"), "My Subscriptions");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Subscription"), "My Subscriptions page");
		if (userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objSubscribitionPageActivePlan, "My Subscription")) {
				logger.error("My Subscription is displayed for Non Subscribed User");
				extent.extentLoggerFail("Plan", "My Subscription is displayed for Non Subscribed User");
			} else {
				logger.info("My Subscription is not displayed for Non Subscribed User");
				extent.extentLogger("Plan", "My Subscription is not displayed for Non Subscribed User");
			}
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objNoTranscationText, "No Transaction")) {
				logger.info("No Active plan is displayed for Non Subscribed User");
				extent.extentLogger("Plan", "No Active plan is displayed for Non Subscribed User");
			} else {
				logger.error("Active plan is displayed for Non Subscribed User");
				extent.extentLoggerFail("Plan", "Active plan is displayed for Non Subscribed User");
			}
		}
		if (userType.equals("SubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objSubscribitionPageActivePlan, "My Subscription")) {
				logger.info("My Subscription is displayed for Subscribed User");
				extent.extentLogger("Plan", "My Subscription is displayed for Subscribed User");
			} else {
				logger.error("My Subscription is not displayed for Subscribed User");
				extent.extentLoggerFail("Plan", "My Subscription is not displayed for Subscribed User");
			}
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objNoTranscationText, "No Transaction")) {
				logger.error("No Active plan is displayed for Subscribed User");
				extent.extentLoggerFail("Plan", "No Active plan is displayed for Subscribed User");
			} else {
				logger.info("Active plan is displayed for Subscribed User");
				extent.extentLogger("Plan", "Active plan is displayed for Subscribed User");
			}
		}
		Back(1);
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		waitTime(2000);
	}

	/**
	 * Function for Navigation to MyTransaction.
	 */
	public void NavigationsToMyTransactions() throws Exception {
		extent.HeaderChildNode("My Transactions");
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "expander button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("My Transactions"), "My Transactions");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Transactions"), "My Transactions page");
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objTransactionPageGrid, "Transaction")) {
			logger.info("Transaction details is displayed");
			extent.extentLogger("Transaction", "Transaction details is displayed");
		}
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objNoTranscationText, "Transaction")) {
			logger.info("No transaction text is displayed");
			extent.extentLogger("Transaction", "No transaction text is displayed");
		}
		Back(1);
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		waitTime(2000);
	}

	/** ====================SUSHMA LIVETV========================== */

	public void LiveTvScreen(String UserType) throws Exception {
		liveLandingPage();
		live();
		premiumPopUp();
		// navigateToAnyScreen("Live TV");
		ChannelGuide(UserType);
	}

	public void live() throws Exception {
		extent.HeaderChildNode("verifying that multiple languages are given to select with apply and reset button");
		verifyElementPresentAndClick(PWALiveTVPage.objLiveTvFilterOption, "Filter option");
		List<WebElement> languages = getDriver().findElements(By.xpath("//div[contains(@class,'language noSelect')]"));
		languages.size();
		if (languages.size() > 1) {
			logger.info("Multiple languages are given for selection");
			extent.extentLogger("Multiple languages", "Multiple languages are given for selection");
		} else {
			logger.info("Multiple languages are not given for selection");
			extent.extentLogger("Multiple languages", "Multiple languages are not given for selection");
		}
		verifyElementPresent(PWALiveTVPage.objApplyBtn, "Apply button");
		verifyElementPresent(PWALiveTVPage.objResetBtn, "Reset button");
		verifyElementPresentAndClick(PWALiveTVPage.objCloseLanguagePopuUpBtn,
				"Close button of Filter language setting window");
		waitTime(10000);
	}

	public void remainderOptionOnUpcomingShow() throws Exception {
		// Click on date
		click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
		FilterLanguage("Bengali");
		// Verify Share and Reminder option is available
		click(PWALiveTVPage.objBanglaShow1, "Show detail");
		verifyElementPresent(PWALiveTVPage.objShareOption, "Share option");
		verifyElementPresent(PWALiveTVPage.objRemainderButton, "Remainder option for upcoming show ");
		// Check if reminder is already active
		if (getDriver().findElement(PWALiveTVPage.objRemainderButton).getAttribute("class").contains("btnIconActive")) {
			extent.extentLogger("", "Reminder option is already selected");
			logger.info("Reminder option is already selected");
			JSClick(PWALiveTVPage.objRemainderButton, "Reminder option");
			extent.extentLogger("", "Deselected Reminder");
			logger.info("Deselected Reminder");
		}
		// Verify user can click on remainder option
		// click on Remainder option
		JSClick(PWALiveTVPage.objRemainderButton, "Reminder option");
		if (getDriver().findElement(PWALiveTVPage.objRemainderButton).getAttribute("class").contains("btnIconActive")) {
			extent.extentLogger("", "User can click on Reminder option");
			logger.info("User can click on Reminder option");
		} else {
			extent.extentLoggerFail("", "User can not click on Reminder option");
			logger.error("User can not click on Reminder option");
		}
		JSClick(PWALiveTVPage.objRemainderButton, "Reminder option");
		extent.extentLogger("", "Deselected Reminder");
		logger.info("Deselected Reminder");

		// Click on close button
		click(PWALiveTVPage.objPopupCloseButton, "Close button");
	}

	public void FilterLanguage() throws Exception {
		click(PWALiveTVPage.objFilterLanguageChannelGuide, "Filter language");
		int size = findElements(PWALiveTVPage.objSelectedlang).size();
		for (int i = 1; i <= size; i++) {
			click(PWALiveTVPage.objSelectedlang, "Selected language");
		}
		click(PWALiveTVPage.objKannadaLang, "Kannada language");
		click(PWALiveTVPage.objApplyBtn, "Apply button");
//		click(PWALiveTVPage.objApplyBtn,"Apply button");
	}

	public void premiumPopUp() throws Exception {
		extent.HeaderChildNode(
				"Verifing that Subscribe now or Login pop is displayed when user click on premium content");
		for (int scroll = 0; scroll <= 8; scroll++) {
			if (JSClick(PWALiveTVPage.objFirstPremiumCardinTray, "Premium Content")) {
				logger.info("Clicked on Premium Content Card");
				extent.extentLogger("", "Clicked on Premium Content Card");
				break;
			} else {
				Swipe("UP", 1);
				waitTime(2000);
			}
		}
		// click(PWALiveTVPage.objFirstPremiumCardinTray, "Premium Content");
		waitTime(5000);
		waitForElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, 5);
		if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
			verifyElementPresentAndClick(PWAPremiumPage.objClosePremiumPopup, "Subscribe Pop Up Close icon");
			extent.HeaderChildNode("Verifing that premium content videos in landscape mode");
			if (verifyIsElementDisplayed(PWALiveTVPage.objPlayerInlineSubscriptionLink,
					"Player inline Subscribtion link")) {
				logger.info(
						"Maximize icon is not displayed since user is getting Player inline Subscription link on Player screen");
				extent.extentLogger("Maximize icon",
						"Maximize icon is not displayed since user is getting Player inline Subscription link on Player screen");
			}
		} else {
			pauseLiveTVPlayer();
			verifyElementPresent(PWAPlayerPage.maximizeBtn, "Maximize icon");
			click(PWAPlayerPage.maximizeBtn, "Maximize icon");

			for (int i = 0; i < 5; i++) {
				if (verifyIsElementDisplayed(PWAPlayerPage.minimizeBtn, "Minimize icon")) {
					logger.info("User is able to watch Premium content in landscape mode");
					extent.extentLogger("Landscape mode", "User is able to watch Premium content in landscape mode");
					break;
				} else {
					playerTap();
				}
			}
		}
		Back(1);
		waitTime(5000);
		if (verifyIsElementDisplayed(PWAHomePage.objBackToTopArrow, "Back to Top arrow")) {
			click(PWAHomePage.objBackToTopArrow, "Back to Top arrow");
		}
		extent.HeaderChildNode("Verifing that user is able to watch the free Content");
		if (waitforLiveTabToLoad()) {
			verifyElementPresentAndClick(PWALiveTVPage.objFilterOption("FREE Channels"), "Free Channels filter");
			waitForElementDisplayed(PWALiveTVPage.objFirstfreeContentCard, 5);
			verifyElementPresentAndClick(PWALiveTVPage.objFirstfreeContentCard, "Free Content card");
			waitForElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, 5);
			if (!(verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up"))) {
				logger.info("User is able to watch the free Content");
				extent.extentLogger("Free content", "User is able to watch the free Content");
			}
			extent.HeaderChildNode("Verifing that free content videos in landscape mode");
			waitTime(10000);
			pauseLiveTVPlayer();
			verifyElementPresent(PWAPlayerPage.maximizeBtn, "Maximize icon");
			click(PWAPlayerPage.maximizeBtn, "Maximize icon");
			for (int i = 0; i < 5; i++) {
				if (verifyIsElementDisplayed(PWAPlayerPage.minimizeBtn, "Minimize icon")) {
					logger.info("User is able to watch free content in landscape mode");
					extent.extentLogger("Landscape mode", "User is able to watch free content in landscape mode");
					break;
				} else {
					playerTap();
				}
			}
			Back(1);
		}
	}

	public boolean navigateToAnyScreen(String screen) throws Exception {
		for (int i = 0; i < 3; i++) {
			try {
				if (verifyElementPresentAndClick(PWAHomePage.objTabName(screen), "Tab : " + screen))
					return true;
			} catch (Exception e) {
				try {
					swipeOnTab("Left");
					if (verifyElementPresentAndClick(PWAHomePage.objTabName(screen), "Tab : " + screen)) {
						waitTime(5000);
						return true;
					}
				} catch (Exception exc) {
					swipeOnTab("Right");
				}
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public void swipeOnTab(String dire) throws InterruptedException {
		extent.HeaderChildNode("Swipping on tab");
		touchAction = new TouchAction(getDriver());
		Dimension size = getDriver().findElement(PWAHomePage.objTabContBar).getSize();
		if (dire.equalsIgnoreCase("Left")) {
			int startx = (int) (size.width * 0.5);
			int endx = (int) (size.width * 0.1);
			int starty = size.height / 2;
			touchAction.press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(endx, starty)).release().perform();
		} else if (dire.equalsIgnoreCase("Right")) {
			int startx = (int) (size.width * 0.5);
			int endx = (int) (size.width * 0.9);
			int starty = size.height / 2;
			touchAction.press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(endx, starty)).release().perform();
		}
	}

	public void waitForElementAndClick(By locator, int seconds, String message) throws InterruptedException {
		main: for (int time = 0; time <= seconds; time++) {
			try {
				getDriver().findElement(locator).click();
				logger.info("Clicked element " + message);
				extent.extentLogger("clickedElement", "Clicked element " + message);
				break main;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.error("Failed to click element " + message);
					extent.extentLoggerFail("failedClickElement", "Failed to click element " + message);
				}
			}
		}
	}

	/** =========================SUSHMA-MOVIES MODULE==================== */
	public void swipeTumbnailToLeft(String str) throws InterruptedException {
		WebElement sourceLocator = getDriver().findElement(By.xpath("((((//div[.='" + str
				+ "']//parent::*//parent::*//parent::*)//child::*[@class='movieTrayWrapper'])//child::*[1][@class='noSelect clickWrapper'])[4])"));
		WebElement targetLocator = getDriver().findElement(By.xpath("((((//div[.='" + str
				+ "']//parent::*//parent::*//parent::*)//child::*[@class='movieTrayWrapper'])//child::*[1][@class='noSelect clickWrapper'])[2])"));

		Thread.sleep(4000);

		Actions action = new Actions(getDriver());
		action.dragAndDrop(sourceLocator, targetLocator).build().perform();
//		action.clickAndHold(sourceLocator).moveToElement(targetLocator).build().perform();
	}

	public void clickByElement(WebElement ele, String validationtext) throws Exception {
		try {
			WebElement element = ele;
			element.click();
			logger.info("Clicked on the " + validationtext);
			extent.extentLogger("click", "Clicked on the <b> " + validationtext);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * =======================SHREENIDHI MandatoryRegistration ==================
	 */
	/**
	 * Validation of Sign Up Pop Up Functionality
	 */
	@SuppressWarnings("unused")
	public void registerPopUpFunctionality() throws Exception {
		extent.HeaderChildNode("Verification of Subscribe Pop Up");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		// mandatoryRegistrationPopUp(user);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovie2");
		typeAndGetSearchResult(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Premium content: " + keyword);
		if (checkElementDisplayed(PWAPlayerPage.objWatchingATrailerMessage,
				"'You're watching a trailer' message on the player")) {
			logger.info("Subscribe Pop up is not displayed for Premium content because trailer is playing");
			extent.extentLogger("Subscribe Pop pup",
					"Subscribe Pop up is not displayed for Premium content because trailer is playing");
		} else {
			logger.error("Premium content is played with no Subscribe Pop Up displayed");
			extent.extentLogger("", "Premium content is played with no Subscribe Pop Up displayed");
		}

		extent.HeaderChildNode("Verification of Sign Up Pop Up: Entering mobile number of new Registered User");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		checkElementExist(PWAHomePage.objSearchField, "Search field");
		String keyword1 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeMovie2");
		type(PWAHomePage.objSearchField, keyword1, "Search");
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(10000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword1), "Search Result");
		waitTime(6000);
		if (checkElementDisplayed(PWAPlayerPage.objCloseRegisterDialog, "Sign Up Pop Up")) {
			logger.info("Sign Up Pop Up is verifed in portrait mode");
			extent.extentLogger("Popup", "Sign Up Pop Up is verifed in portrait mode");
			checkElementDisplayed(PWAHomePage.objPopUpMobileField, "Mobile Number field");
			type(PWAHomePage.objPopUpMobileField, "9964955239", "Mobile Number field");
			hideKeyboard();
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objSendOtp, "Send OTP button");
			if (checkElementDisplayed(PWALoginPage.objFacebookIcon, "Facebook icon") == false) {
				logger.info("Social media login is not displayed in Sign Up Pop Up");
				extent.extentLogger("Social media icon", "Social media login is not displayed in Sign Up Pop Up");
			} else {
				logger.error("Social media login is displayed in Sign Up Pop Up");
				extent.extentLoggerFail("Social media icon", "Social media login is displayed in Sign Up Pop Up");
			}
			if (checkElementDisplayed(PWAHomePage.objverifyNumberPopup, "PopUp")) {
				logger.info("Otp screen is displayed");
				extent.extentLogger("Popup", "Otp screen is displayed");
			}
			click(PWASearchPage.objCloseRegisterDialog, "Close button");
			click(PWAHomePage.objZeeLogo, "Zee logo");
			changeLanguageAndVerifyPopUp();
		} else {
			logger.info("Sign Up Pop Up is not displayed");
			extent.extentLogger("Popup", "Sign Up Pop Up is not displayed");
		}
	}

	/**
	 * Validation of Sign Up Pop Up Functionality after changing the display
	 * language
	 */
	public void changeLanguageAndVerifyPopUp() throws Exception {
		extent.HeaderChildNode("Change language and verification of pop up");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objkannadalanguage, "kannada Language button");
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply buttton");
		waitTime(3000);
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply buttton");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		checkElementExist(PWAHomePage.objSearchField, "Search field");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeMovie2");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResultChangedLanguage(keyword), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResultChangedLanguage(keyword), "Search Result");
		waitTime(6000);
		if (checkElementDisplayed(PWASearchPage.objRegisterDialogAfterchangedLanguage, "Sign Up Pop Up")) {
			logger.info("Sign Up Popup is validated after changing language");
			extent.extentLogger("Pop-Up", "Sign Up Popup is validated after changing language");
			click(PWASearchPage.objCloseRegisterDialog, "Close button");
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		PartialSwipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMoreSettingInKannada, "More setting");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objResetSettingsToDefault, "Reset Settings to Default");
		waitTime(5000);
		click(PWAHomePage.objZeeLogo, "Zee logo");
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
	}

	public void simpleTest(String UserType) {
		boolean isIconDisplayed = false;
		String languageSmallText = "en,kn";
		List<String> allMetaTitleOnCarouselAPI = ResponseInstance.traysTitleCarousel("home", languageSmallText);
		System.out.println("API Data : " + allMetaTitleOnCarouselAPI);
		for (int i = 0; i < allMetaTitleOnCarouselAPI.size(); i++) {
			String carouselTitleAPI = allMetaTitleOnCarouselAPI.get(i);
			for (int j = 0; j < 7; j++) {
				try {
					WebElement mastHeadEle = (new WebDriverWait(getDriver(), 30)).until(ExpectedConditions
							.presenceOfElementLocated(PWAHomePage.objContTitleWithPlayIconCarousel(carouselTitleAPI)));
					isIconDisplayed = mastHeadEle.isDisplayed();
					if (isIconDisplayed == true) {
						System.out.println("Content " + carouselTitleAPI + ": Play icon position for X : "
								+ mastHeadEle.getLocation().x);
						System.out.println("Content " + carouselTitleAPI + ": Play icon position for Y : "
								+ mastHeadEle.getLocation().y);
						break;
					} else {
						swipeCarouselContents(1);
					}
				} catch (Exception e) {
					System.out.println("Exception : " + e.getMessage());
				}
			}
		}
	}

	/**
	 * Validation of Complete Profile Popup Functionality
	 */
	public void completeProfilePopup() throws Exception {
		extent.HeaderChildNode("Complete profile popup functionality");
		logout();
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
		waitTime(3000);
		extent.HeaderChildNode("Login through incomplete profile account");
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
		type(PWALoginPage.objEmailField, "indaus24@gmail.com", "Email Field");
		hideKeyboard();
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
		type(PWALoginPage.objPasswordField, "123456\n", "Password field");
		hideKeyboard();
		directClickReturnBoolean(PWALoginPage.objLoginBtn, "Login Button");
		waitTime(8000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		checkElementExist(PWAHomePage.objSearchField, "Search field");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeMovie2");
		type(PWAHomePage.objSearchField, keyword + "\n", "Search");
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(10000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		waitTime(6000);
		if ((checkElementDisplayed(CompleteYourProfilePopUp.objCompleteYourProfileTxt,
				"Complete Your Profile") == true)) {
			extent.HeaderChildNode("Verification of complete profile popup in potrait mode");
			logger.info("PopUp is verifed in portrait mode");
			extent.extentLogger("Popup", "PopUp is verifed in portrait mode");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objFullName, "Full name Field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objDOBField, "DOB Field");
			type(CompleteYourProfilePopUp.objDOBField, "15101997", "DOB Field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objGenderDropDown, "Gender drop down");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objGenderfemale, "Female option");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objMobileNo, "Mobile Number");
			type(CompleteYourProfilePopUp.objMobileNo, "95839633299", "Mobile Number");
			hideKeyboard();
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objCloseBtn, "Close button in OTP Pop Up");
			click(PWAHomePage.objZeeLogo, "Zee logo");
		} else {
			logger.info("Complete Profile pop up is not displayed");
			extent.extentLogger("Complete Profile pop up", "Complete Profile pop up is not displayed");
		}
		logout();
	}

	/**
	 * Validation of Upgrage Popup Functionality for RSVOD user
	 */
	public void rsvodPopup() throws Exception {
		extent.HeaderChildNode("Functionality of Upgarde popup for RSVOD user");
		logout();
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
		waitTime(3000);
		verifyElementPresent(PWALoginPage.objEmailField, "Login page");
		extent.HeaderChildNode("Login through RSVOD User");
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
		type(PWALoginPage.objEmailField, "pwate44@mailnesia.com\n", "Email Field");
		hideKeyboard();
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
		type(PWALoginPage.objPasswordField, "1234567\n", "Password field");
		hideKeyboard();
		waitTime(5000);
		click(PWALoginPage.objLoginBtn, "Login Button");
		waitTime(10000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovie");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Field");
		waitTime(3000);
		click(PWASearchPage.objSearchResultPremiumContent, "Premium content");
		if (verifyIsElementDisplayed(PWASearchPage.objCloseRegisterDialog, "Popup") == false) {
			logger.info("Sign Up Pop Up is not displayed for premium user");
			extent.extentLogger("Popup", "Sign Up Pop Up is not displayed for premium user");
		}
		waitTime(20000);
		if (verifyIsElementDisplayed(PWASearchPage.objUpgradePopup, "Popup")) {
			extent.HeaderChildNode("Upgrade PopUp Funtionality");
			logger.info("Upgrade popup is displayed for RSVOD user");
			extent.extentLogger("Popup", "Upgrade popup is displayed for RSVOD user");
			extent.HeaderChildNode("Verification of popup in portrait mode");
			logger.info("PopUp is verifed in portrait mode");
			extent.extentLogger("Popup", "PopUp is verifed in portrait mode");
			click(PWASearchPage.objUpgradePopupCloseButton, "Close button");
			logout();
		}
	}

	public void registerPopUpScenarios(String userType) throws Exception {
		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Mandatory Registration pop up scenarios for Guest user");
			extent.extentLogger("Accessing as Guest User", "Accessing as Guest User");
			logger.info("Accessing as Guest User");
			registerPopUpFunctionality();
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Mandatory Registration pop up scenarios for Non Subscribed user");
			extent.extentLogger("Accessing as NonSubscribedUser User", "Accessing as NonSubscribedUser User");
			logger.info("Accessing as NonSubscribedUser User");
			completeProfilePopup();
			ZeePWALogin("E-mail", userType);
			break;

		case "SubscribedUser":// RSVOD tcs are no more applicable
			/*
			 * extent.
			 * HeaderChildNode("Mandatory Registration pop up scenarios for Subscribed user"
			 * ); extent.extentLogger("Accessing as SubscribedUser User",
			 * "Accessing as SubscribedUser User");
			 * logger.info("Accessing as SubscribedUser User"); rsvodPopup();
			 * ZeePWALogin("E-mail", userType);
			 */
		}
	}

	public By TextToXpath(String text) throws Exception {
		return By.xpath("//div[contains(@class,'trayContentWrap')]//*[contains(text(),'" + text + "')]");
	}

	/**
	 * ==============================SHREENIDHI NETWORK
	 * INTERUPTION==================================
	 */

	/**
	 * Validation of Naviation,playback functionality when data is turned off and on
	 */

	public void networkInterruption(String userType) throws Exception {
		extent.HeaderChildNode("Network Interpution functionality");
		// "adb shell svc data enable"
		// "adb shell svc data disable"
		// "adb shell svc wifi enable"
		// "adb shell svc wifi disable"
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell svc wifi disable");
		if (userType.equals("Guest")) {
			verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
			extent.HeaderChildNode("Validating page loading functionality when data is turned off and on");
			if (verifyIsElementDisplayed(PWALoginPage.objSpinnerInLogin, "Spinner")) {
				logger.info("Login page is not loaded when internet is turned off");
				extent.extentLogger("Login", "Login page is not loaded when internet is turned off");
			}
			Runtime.getRuntime().exec("adb shell svc wifi enable");
			waitTime(5000);
			getDriver().navigate().refresh();
			waitTime(5000);
			if (verifyIsElementDisplayed(PWALoginPage.objEmailField, "Login text")) {
				logger.info("Login page is loaded when interent is turned on");
				extent.extentLogger("Login", "Login page is loaded when interent is turned on");
			}
		} else if (userType.equals("NonSubscribedUser")) {
			extent.HeaderChildNode("Validating page loading functionality when data is turned off and on");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIcon, "Profile icon");
			if (checkElementExist(PWALoginPage.objSpinnerInLogin, "Spinner")) {
				logger.info("Profile page is not loaded when internet is turned off");
				extent.extentLogger("Profile", "Profile page is not loaded when internet is turned off");
			}
			Runtime.getRuntime().exec("adb shell svc wifi enable");
			waitTime(5000);
			getDriver().navigate().refresh();
			waitTime(5000);
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objProfilePageTitleTxt, "Profile text")) {
				logger.info("Profile page is loaded when interent is turned on");
				extent.extentLogger("Profile", "Profile page is loaded when interent is turned on");
			}
		} else if (userType.equals("SubscribedUser")) {
			extent.HeaderChildNode("Validating page loading functionality when data is turned off and on");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIcon, "Profile icon");
			if (verifyIsElementDisplayed(PWALoginPage.objSpinnerInLogin, "Spinner")) {
				logger.info("Profile page is not loaded when internet is turned off");
				extent.extentLogger("Profile", "Profile page is not loaded when internet is turned off");
			}
			Runtime.getRuntime().exec("adb shell svc wifi enable");
			waitTime(5000);
			getDriver().navigate().refresh();
			waitTime(5000);
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objProfilePageTitleTxt, "Profile text")) {
				logger.info("Profile page is loaded when interent is turned on");
				extent.extentLogger("Profile", "Profile page is loaded when interent is turned on");
			}
		}
		Back(1);
		waitTime(4000);
		extent.HeaderChildNode("Validating playback functionality when data is turned off and on");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		verifyIsElementDisplayed(PWAHomePage.objSearchField, "Search field");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("consumptionsFreeContent");
		type(PWAHomePage.objSearchField, keyword + "\n", "Search");
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(10000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		waitTime(6000);
		if (verifyIsElementDisplayed(PWASearchPage.objCloseRegisterDialog)) {
			click(PWASearchPage.objCloseRegisterDialog, "Close icon in popup");
		}
		waitTime(4000);
		waitForPlayerAdToComplete("Video Player");
		Runtime.getRuntime().exec("adb shell svc wifi disable");
		waitTime(5000);
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objPlaybackErrorMessage, "Error message")) {
			logger.info("Something went wrong error message is displayed when internet is turned off");
			extent.extentLogger("Error", "Something went wrong error message is displayed when internet is turned off");
		}
		Runtime.getRuntime().exec("adb shell svc wifi enable");
		waitTime(10000);
		waitForPlayerAdToComplete("Video Plyaer");
		waitTime(5000);
		playerTap();
		if (verifyIsElementDisplayed(PWAPlayerPage.forward10SecBtn, "Player control")) {
			logger.info("Playback is continued when internet is turned on");
			extent.extentLogger("Playback", "Playback is continued when internet is turned on");
		}
		extent.HeaderChildNode("Validating playback functionality when user switch the app");
		logger.info("Switching app from ZEE5PWA to mobile home screen");
		extent.extentLogger("App", "Switching app from ZEE5PWA to mobile home screen");
		playerTap();
		getDriver().runAppInBackground(Duration.ofSeconds(7));
		logger.info("Switching app from mobile home screen to ZEE5PWA");
		extent.extentLogger("App", "Switching app from mobile home screen to ZEE5PWA");
		String Duration1 = getText(PWAPlayerPage.objcurrenttime);
		waitTime(10000);
		playerTap();
		String Duration2 = getText(PWAPlayerPage.objcurrenttime);
		if (!Duration1.equals(Duration2)) {
			logger.info("Playback is continued when user comes out of zee5 and navigate back to zee5 after some time");
			extent.extentLogger("Zee5",
					"Playback is continued when user comes out of zee5 and navigate back to zee5 after some time");
		}
		click(PWAHomePage.objZeeLogo, "zee logo");
		waitTime(5000);
		extent.HeaderChildNode("Verification of navigation in offline mode");
		if (verifyIsElementDisplayed(PWAHomePage.objCarousel, "Carousel")) {
			logger.info("Carosusel contents are displayed in home page");
			extent.extentLogger("Naviagtion", "Carosusel contents are displayed in home page");
		}
		click(PWAHomePage.objTabName("Shows"), "Shows page");
		waitTime(3000);
		if (verifyIsElementDisplayed(PWAHomePage.objPageHighlighted("Shows"), "Shows page")) {
			logger.info("User is navigated to shows page");
			extent.extentLogger("Naviagtion", "User is navigated to shows page");
		}
		Runtime.getRuntime().exec("adb shell svc wifi disable");
		waitTime(3000);
		click(PWAHomePage.objTabName("Home"), "Home page");
		waitTime(3000);
		if (verifyIsElementDisplayed(PWAHomePage.objCarousel, "carousel")) {
			logger.info("Pre loaded content are verified in offline navigation");
			extentLogger("Preloaded", "Pre loaded content are verified in offline navigation");
		} else {
			logger.info("Pre loaded content are not verified in offline navigation");
			extentLogger("Preloaded", "Pre loaded content are not verified in offline navigation");
		}
		Runtime.getRuntime().exec("adb shell svc wifi enable");
		waitTime(3000);
		click(PWAHomePage.objZeeLogo, "zee logo");
	}

	public void networkInterruptionScenarios(String userType) throws Exception {
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Validation of Network Interruption flow for Guest user");
			networkInterruption(userType);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Validation of Network Interruption flow for Non Subscribed user");
			networkInterruption(userType);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Validation of Network Interruption flow for Subscribed user");
			networkInterruption(userType);
		}
	}

	public void waitForPlayerAdToComplete(String playerType) throws Exception {
		boolean adDisplayed = false;
		boolean playerDisplayed = false;
		int confirmCount = 0;
		waitTime(5000);
		main: for (int trial = 0; trial < 200; trial++) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objAd)) {
				adDisplayed = true;
				if (trial == 5) {
					logger.info("Ad play in progress");
					extent.extentLogger("AdPlayInProgress", "Ad play in progress");
				}
				if (Math.floorMod(trial, 40) == 0)
					System.out.println("Ad play in progress");
				Thread.sleep(1000);
			} else {
				try {
					getDriver().findElement(PWAPlayerPage.objPlayerSettings);
					playerDisplayed = true;
					Thread.sleep(1000);
					confirmCount++;
					System.out.println(confirmCount);
					if (confirmCount == 2) {
						if (adDisplayed == false) {
							logger.info("Ad did not play");
							extent.extentLogger("AdDidNotPlay", "Ad did not play");
						} else {
							logger.info("Ad play complete");
							extent.extentLogger("AdPlayComplete", "Ad play complete");
						}
						break main;
					}
				} catch (Exception e1) {
					waitTime(2000);
				}
			}
		}
		if (playerDisplayed == false && adDisplayed == false) {
			logger.info("Ad play failure");
			extent.extentLogger("failedAd", "Ad play failure");
		}
	}

	/**
	 * ====================SATISH - SUBSCRIPTION POPUP
	 * SCENARIOS==========================
	 */

	/**
	 * Search For Content And Click On First Result
	 */
	public void zeeSearchForContentAndClickOnFirstResult(String contentName) throws Exception {
		waitTime(3000);
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 20);
		type(PWASearchPage.objSearchEditBox, contentName + "\n", "Search bar");
		hideKeyboard();
		waitForElementDisplayed(PWASearchPage.objFirstSearchedAssetTitle, 20);
		waitTime(3000);
		String FirstSearchedAssetTitle = findElement(PWASearchPage.objFirstSearchedAssetTitle).getText();
		System.out.println("First Asset Title of the Search Result is: " + FirstSearchedAssetTitle);
		click(PWASearchPage.objFirstSearchedAssetTitle, "First Searched Asset Title");
	}

	/**
	 * Verify Subscription popup after trailer is played for 20 seconds
	 */
	public void zeePWAVerifySubscriptionPopupAfterTrailerPlaybackIsComplete(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest") || userType.equalsIgnoreCase("NonSubscribedUser")) {
			HeaderChildNode(
					"Verify Subscription Popup After Completion Of Trailer Playback Is Complete for non-logged in User");
			System.out.println(
					"Verify Subscription Popup After Completion Of Trailer Playback Is Complete for non-logged in User");
			click(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
			String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("premiumMovieWithTrailer");
			zeeSearchForContentAndClickOnFirstResult(keyword);
			waitForElement(PWASubscriptionPages.objSubscribePopupTitle, 30, "Subscribe Pop up Title");
			if (verifyElementPresent(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop up Title")) {
				click(PWASubscriptionPages.objPopupCloseButton, "Popup Close Button");
			}
		}
	}

	/**
	 * Navigate to Subscription Flow From Subscription Popup full screen player
	 */
	public void zeePWAVerifyNavigationToSubscriptionFlowFromSubscriptionPopupFullscreenPlayer(String userType)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest") || userType.equalsIgnoreCase("NonSubscribedUser")) {
			HeaderChildNode("Navigate to Subscription Flow From Subscription Popup in full screen player");
			System.out.println("Navigate to Subscription Flow From Subscription Popup in full screen player");
			reloadHome();
			// handle mandatory pop upPhoneNumber Field
			mandatoryRegistrationPopUp(userType);
			String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("premiumMovieWithTrailer2");
			zeeSearchForContentAndClickOnFirstResult(keyword);
			// waitTime(10000);
			// waitForPlayerAdToComplete();
			pausePlayer();
			verifyElementPresentAndClick(PWAPlayerPage.maximizeBtn, "Maximize window icon");
			waitTime(4000);
			verifyElementPresent(PWAPlayerPage.minimizeBtn, "Minimize window icon");
			verifyElementPresentAndClick(PWAPlayerPage.playBtn, "Play button");
			for (int trial = 0; trial < 3; trial++) {
				waitExplicitlyForElementPresence(PWASubscriptionPages.objSubscribePopupTitle, 60,
						"Subscribe Pop up Title");
			}
			if (verifyElementPresent(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop up Title")) {
				verifyElementPresent(PWASubscriptionPages.objDefaultSelectedPack, "Default Selected Package");
				verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Popup Proceed button");
				waitForElement(PWASubscriptionPages.objAccountInfoHighlighted, 10, "Account Info Section");
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
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
				getDriver().findElement(locator);
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
	 * ==========================SHREENIDHI
	 * ONBOARDING====================================
	 */

	/*
	 * Validation of SignUp screen using Phone number
	 */
	/*
	 * Validation of SignUp screen using Phone number
	 */
	public void phoneNumberRegistration() throws Exception {
		extent.HeaderChildNode("Phone Number Registration");
		click(PWALoginPage.objSignUpBtn, "Sign up button");
		waitTime(4000);
		checkElementDisplayed(PWALoginPage.objEmailField, "Email/PhoneNo Field");
		type(PWALoginPage.objEmailField, "7892215\n", "PhoneNumber Field");
		hideKeyboard();
		waitTime(3000);
		String PhoneNumberField = getText(PWALoginPage.objEmailField);
		if (PhoneNumberField != null) {
			logger.info("User is allowed to enter PhoneNumber");
			extentLogger("PhoneNumber", "User is allowed to enter PhoneNumber in PhoneNumber Field");
		}
		if (checkElementDisplayed(PWALoginPage.objIncorrectPhoneNumberMessage, "PhoneNumber Error Message")) {
			logger.info("When User Enter Invalid PhoneNumber Error Message is displayed");
			extent.extentLogger("Error Message", "When User Enter Invalid PhoneNumber Error Message is displayed");
		}

		int lenText = findElement(PWALoginPage.objEmailField).getAttribute("value").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(PWALoginPage.objEmailField).sendKeys(Keys.BACK_SPACE);
		}
		waitTime(5000);
		type(PWALoginPage.objEmailField, "789221521\n", "PhoneNumber Field");
		if (checkElementDisplayed(PWALoginPage.objIncorrectPhoneNumberMessage, "PhoneNumber Error Message") == false) {
			logger.info("User is allowed to enter valid PhoneNumber");
			extent.extentLogger("PhoneNumber", "User is allowed to enter valid PhoneNumber");
		}

		checkElementExist(PWALoginPage.objCountryCode, "Country code field");
		click(PWALoginPage.objCountryCode, "Country code field");
		checkElementExist(PWALoginPage.objCountryCodeDropDown, "Drop down of country code");
		click(PWALoginPage.objCountryCodeAlgeria, "Algeria country code");
		click(PWALoginPage.objCountryCode, "Country code field");
		click(PWALoginPage.objCountryCodeAndoora, "Andoora country code");
		click(PWALoginPage.objCountryCode, "Country code field");
		click(PWALoginPage.objCountryCodeIndia, "India country code");

		if (getDriver().findElement(PWASignupPage.objSendOTPButtonDisabled).isDisplayed()) {
			logger.info("Send OTP button is disabled because incorrect mobile number is entered");
			extent.extentLogger("", "Send OTP button is disabled because incorrect mobile number is entered");
		} else {
			logger.error("Send OTP button is enabled even though incorrect mobile number is entered");
			extent.extentLoggerFail("", "Send OTP button is enabled even though incorrect mobile number is entered");
		}
		type(PWALoginPage.objEmailField, "4\n", "PhoneNumber Field");
		hideKeyboard();
		click(PWASignupPage.objSendOTPButtonEnabled, "Send OTP Button");
		waitTime(3000);
		hideKeyboard();

		verifyElementPresentAndClick(PWASignupPage.objChangeNumberLink, "Change Number Option");
		waitTime(4000);
		type(PWALoginPage.objEmailField, "7892215214\n", "PhoneNumber Field");
		hideKeyboard();
		waitTime(3000);
		click(PWASignupPage.objSignUpButtonHighlighted, "Continue Button");
		waitTime(2000);
		hideKeyboard();

		checkElementDisplayed(PWASignupPage.objOTPTimer, "OTP timer");
		String OtpTimer1 = getText(PWASignupPage.objOTPTimer);
		waitTime(6000);
		String OtpTimer2 = getText(PWASignupPage.objOTPTimer);
		boolean Time = OtpTimer1.equals(OtpTimer2);
		if (Time == false) {
			logger.info("The Otp timer is in reverse order");
			extentLogger("OtpTimer", "The Otp timer is in reverse order");
		}
		if (getDriver().findElement(PWASignupPage.objResendOtpOption).isEnabled() == false) {
			logger.info("ResendOtp option is not active");
			extent.extentLogger("ResendOtp", "ResendOtp option is not active");
			waitTime(60000);
		}
		if (getDriver().findElement(PWASignupPage.objResendOtpOption).isEnabled() == true) {
			logger.info("ResendOtp option is active after 60seconds");
			extent.extentLogger("ResendOtp", "ResendOtp option is active after 60seconds");
		}

		type(PWASignupPage.objOTP1, "a", "OTP box1");
		type(PWASignupPage.objOTP2, "b", "OTP box2");
		type(PWASignupPage.objOTP3, "c", "OTP box3");
		type(PWASignupPage.objOTP4, "d\n", "OTP box4");
		hideKeyboard();
		waitTime(2000);
		if (getDriver().findElement(PWASignupPage.objVerifyButtonDisabled).isDisplayed()) {
			logger.info("Verify Button is disabled because non numeric value in otp section is entered");
			extent.extentLogger("Verify",
					"Verify Button is disabled because non numeric value in otp section is entered");
		} else {
			logger.error("Verify Button is enabled even though non numeric value in otp section is entered");
			extent.extentLoggerFail("Verify",
					"Verify Button is enabled eventhough non numeric value in otp section is entered");
		}
		type(PWASignupPage.objOTP1, "1", "OTP box1");
		type(PWASignupPage.objOTP2, "2", "OTP box2");
		type(PWASignupPage.objOTP3, "3", "OTP box3");
		type(PWASignupPage.objOTP4, "4\n", "OTP box4");
		hideKeyboard();
		waitTime(3000);

		if (getDriver().findElement(PWASignupPage.objVerifyButtonEnabled).isDisplayed()) {
			logger.info("Verify Button is highlighted");
			extent.extentLogger("Verify", "Verify Button is highlighted");
			click(PWASignupPage.objVerifyButtonEnabled, "Verify Button");
			try {
				Boolean ExpiredToastMessage = getDriver().findElement(By.xpath("//*[@class='toastMessage']"))
						.isDisplayed();
				if (ExpiredToastMessage == true) {
					extent.extentLogger("Toast", "Expired Toast message displayed");
					logger.info("Expired Toast message displayed");
				}
			} catch (Exception e) {
				System.out.println("Toast message is not displayed");
			}
		}
		Back(3);
	}

//------------------------------------------------------------------------------------------------------------------------	
	/*
	 * Validation of SignUp screen using Email-id
	 */
	public void emailRegistration() throws Exception {
		extent.HeaderChildNode("Email-Registration");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu");
		click(PWALoginPage.objSignUpBtn, "Sign up button");
		waitTime(4000);
		verifyIsElementDisplayed(PWALoginPage.objSignUpHeaderInSignUpPage, "SignUp Page");
		verifyIsElementDisplayed(PWALoginPage.objEmailField, "Email/PhoneNo Field");

		if (verifyIsElementDisplayed(PWALoginPage.objPasswordField, "Password field") == false) {
			logger.info("Password field is not displayed when email field is empty");
			extent.extentLogger("Password Field", "Password field is not displayed when email field is empty");
		}

		type(PWALoginPage.objEmailField, "zee5latest@gmail.com", "Email field");
		hideKeyboard();
		if (verifyIsElementDisplayed(PWALoginPage.objPasswordField, "Password field") == true) {
			logger.info("Password field is displayed when user enter email-id");
			extent.extentLogger("Password Field", "Password field is displayed when user enter email-id");
		}

		type(PWALoginPage.objPasswordField, "abc", "password field");
		click(PWALoginPage.objShowPasswordButton, "Show Password icon");
		hideKeyboard();
		click(PWASignupPage.objSignUpButtonNotHighlighted, "SignUp Button");
		verifyIsElementDisplayed(PWASignupPage.objPasswordErrorMessage, "Password error message");
		try {
			getDriver().findElement(PWALoginPage.objPasswordFieldFilled).clear();
			System.out.println("Cleared password field");
		} catch (Exception e) {
		}
		waitTime(5000);
		type(PWALoginPage.objPasswordFieldFilled, "user@123", "password field");
		hideKeyboard();
		if (verifyIsElementDisplayed(PWASignupPage.objPasswordHiddenField, "Password field")) {
			logger.info("Password field is hidden before tapping on password icon");
			extentLogger("Password", "Password field is hidden before tapping on password icon");
		}
		click(PWASignupPage.objPasswordIcon, "Show Password icon");
		if (verifyIsElementDisplayed(PWASignupPage.objPasswordFieldShow, "Password")) {
			logger.info("Password field is shown when user taps on password icon");
			extent.extentLogger("Password", "Password field is shown when user taps on password icon");
		}
		click(PWASignupPage.objPasswordIcon, "Show Password icon");

		calenderFunctionality();
		String SelectedDate = getText(PWALoginPage.objDateOfBirthField);
		if (SelectedDate != null) {
			logger.info("Value in date of field is entered correctly");
			extent.extentLogger("DateOfField", "Value in date of field is entered correctly");
		}

		if (getDriver().findElement(PWASignupPage.objSignUpButtonNotHighlighted).isEnabled() == false) {
			logger.info("SignUp button is not highlighted");
			extent.extentLogger("Continue button", "SignUp button is not highlighted");
		}
		click(PWASignupPage.objGenderMaleBtn, "Gender Option");

		if (getDriver().findElement(PWASignupPage.objSignUpButtonHighlighted).isEnabled()) {
			logger.info("SignUp button is highlighted");
			extent.extentLogger("Continue button", "SignUp button is highlighted");
		}
		Back(1);
	}

	// -----------------------------------------------------------------
	/*
	 * Validation of Forgot password screen using Email-id
	 */
	public void forgotPasswordEmailSanity() throws Exception {
		extent.HeaderChildNode("Verifications in Forgot Password for email id");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
		verifyIsElementDisplayed(PWALoginPage.objGoogleIcon, "Google icon");
		verifyIsElementDisplayed(PWALoginPage.objFacebookIcon, "Facebook icon");
		verifyIsElementDisplayed(PWALoginPage.objTwitterIcon, "Twitter icon");
		verifyElementPresentAndClick(PWALoginPage.objForgotPasswordTxt, "Forgot password");
		Back(1);
		waitTime(5000);
		if (verifyIsElementDisplayed(PWALoginPage.objEmailField, "Login")) {
			logger.info("User is redirected to login page");
			extent.extentLogger("Login", "User is redirected to login page");
		}
		verifyElementPresentAndClick(PWALoginPage.objForgotPasswordTxt, "Forgot password");
		waitTime(2000);
		verifyIsElementDisplayed(PWALoginPage.objEmailField, "Email field");
		type(PWALoginPage.objEmailField, "s\n", "Email field");
		hideKeyboard();
		if (getDriver().findElement(PWASignupPage.objPasswordResetLinkDisabled).isDisplayed()) {
			logger.info("Password Reset Link is not enabled when user enters invalid email id");
			extent.extentLogger("Continue button",
					"Password Reset Link is not enabled when user enters invalid email id");
		} else {
			logger.error("Password Reset Link is enabled eventhough when user enters invalid email id");
			extent.extentLoggerFail("Continue button",
					"Password Reset Link is enabled even though user enters invalid email id");
		}
		getDriver().findElement(PWALoginPage.objEmailField).clear();
		waitTime(4000);
		type(PWALoginPage.objEmailField, "hreenidhi080@gmail.com", "Email field");
		hideKeyboard();
		if (checkElementDisplayed(PWALoginPage.objForgotPasswordMessage, "Message")) {
			logger.info("Supporting description is displayed");
			extent.extentLogger("Forgot password", "Supporting description is displayed");
		}
		if (getDriver().findElement(PWASignupPage.objPasswordResetLinkEnabled).isDisplayed()) {
			logger.info("Password Reset Link is enabled when user enters valid email id");
			extent.extentLogger("Continue button", "Password Reset Link is enabled when user enters valid email id");
		}
		click(PWASignupPage.objPasswordResetLinkEnabled, "Continue button");
		try {
			Boolean ExpiredToastMessage = getDriver().findElement(By.xpath("//*[@class='toastMessage']")).isDisplayed();
			if (ExpiredToastMessage == true) {
				extent.extentLogger("Toast", "No user found Toast message displayed");
				logger.info("No user found Toast message displayed");
			}
		} catch (Exception e) {
			System.out.println("Toast message is not displayed");
		}
		Back(2);
	}

	// ----------------------------------------------------------------------------------------------------
	/*
	 * Validation of Forgot password screen using Phone number
	 */
	public void forgotPasswordMobileNumberSanity() throws Exception {
		extent.HeaderChildNode("Verifications in Forgot Password for Mobile Number");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
		verifyElementPresentAndClick(PWALoginPage.objForgotPasswordTxt, "Forgot password");
		waitTime(2000);
		type(PWALoginPage.objEmailField, "789221\n", "Email field");
		hideKeyboard();
		if (getDriver().findElement(PWASignupPage.objForgotPasswordContinueDisabled).isDisplayed()) {
			logger.info("Continue button is not enabled when user enters invalid mobile number");
			extent.extentLogger("Continue button",
					"Continue button is not enabled when user enters invalid mobile number");
		} else {
			logger.error("Continue button  is enabled eventhough when user enters invalid mobile number");
			extent.extentLoggerFail("Continue button",
					"Continue button is enabled even though user enters invalid mobile number");
		}
		getDriver().findElement(PWALoginPage.objEmailField).clear();
		type(PWALoginPage.objEmailField, "5214\n", "Email field");
		hideKeyboard();
		if (checkElementDisplayed(PWALoginPage.objForgotPasswordMessage, "Message")) {
			logger.info("Supporting description is displayed");
			extent.extentLogger("Forgot password", "Supporting description is displayed");
		}
		click(PWASignupPage.objForgotPasswordContinueEnabled, "Continue button");
		waitTime(3000);
		checkElementDisplayed(PWALoginPage.objNewPasswordField, "New password page");
		type(PWALoginPage.objNewPasswordField, "User@123\n", "Password field");
		hideKeyboard();
		type(PWALoginPage.objConfirmNewPasswordField, "User@123\n", "Confirm Password field");
		hideKeyboard();
		click(PWALoginPage.objForgotPasswordLinkButton, "Continue button");
		if (checkElementDisplayed(PWALoginPage.objOTPVerifyPage, "OTP verification page")) {
			logger.info("User is redirected to verify otp page");
			extent.extentLogger("OTP", "User is redirected to verify otp page");
		}
		Back(4);
	}

//-------------------------------------------------------------------------------------------	
	/*
	 * Login through Facebook
	 */
	public void facebookLogin() throws Exception {
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
		waitTime(5000);
		verifyElementPresentAndClick(PWALoginPage.objFacebookIcon, "Facebook Icon");
		System.out.println(getDriver().getWindowHandles());
		getDriver().switchTo().window("CDwindow-1");
		waitTime(7000);
		if (checkElementExist(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger")) {
			getDriver().switchTo().window("CDwindow-0");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
			if (verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
			}
		}

		else if (checkElementExist(PWALoginPage.objFacebookPageVerification, "Facebook page")) {
			verifyElementPresent(PWALoginPage.objFacebookLoginEmail, " Email Field");
			type(PWALoginPage.objFacebookLoginEmail, "igszeetesttest@gmail.com", "Emial Field");
			verifyElementPresent(PWALoginPage.objFacebookLoginpassword, " Password Field");
			type(PWALoginPage.objFacebookLoginpassword, "Igs$123Zee\n", "Password Field");
			verifyElementPresentAndClick(PWALoginPage.objFacebookLoginButtonInFbPage, "Login Button");
			waitTime(10000);
			getDriver().switchTo().window("CDwindow-0");
			if (checkElementExist(PWALoginPage.objSignUpHeaderInSignUpPage, "SignUp Page")) {
				regestrationfromSocialMedia();
				verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				if (verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				}
			} else {
				verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				if (verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				}
			}

		} else if (verifyElementPresent(PWALoginPage.objFbCountinueBtn, "Continue button") == true) {
			click(PWALoginPage.objFbCountinueBtn, "Continue fb");
			if (verifyElementPresent(PWALoginPage.objSignUpHeaderInSignUpPage, "SignUp page") == true) {
				regestrationfromSocialMedia();
				verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				if (verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				}
			} else {
				waitTime(7000);
				verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				if (verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				}
			}
		}
		click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Close button");
		// logout();
	}

	/*
	 * Login through Gmail
	 */
	/*
	 * Login through Gmail
	 */
	public void gmailLogin() throws Exception {
		extent.HeaderChildNode("Login through Gmail");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
		System.out.println(getDriver().getCurrentUrl());
		System.out.println(getDriver().getWindowHandles());
		verifyIsElementDisplayed(PWALoginPage.objGoogleIcon, "Google Icon");
		Actions act = new Actions(getDriver());
		act.click(getDriver().findElement(By.id("gbtn"))).build().perform();
		waitTime(1000);
		act.click(getDriver().findElement(By.id("gbtn"))).build().perform();
		waitTime(3000);
		System.out.println(getDriver().getWindowHandles());
		getDriver().switchTo().window("CDwindow-2");
		waitTime(4000);
		if (verifyIsElementDisplayed(PWALoginPage.objGmailEmailField, " Email Field")) {
			type(PWALoginPage.objGmailEmailField, "newzee5igs@gmail.com", "Emial Field");
			hideKeyboard();
			verifyElementPresentAndClick(PWALoginPage.objGmailNextButton, "clicked on next button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objGmailPasswordField, " Password Field");
			type(PWALoginPage.objGmailPasswordField, "User@123\n", "Password Field");
			hideKeyboard();
			verifyElementPresentAndClick(PWALoginPage.objGmailNextButton, "clicked on next button");
			waitTime(7000);
			getDriver().switchTo().window("CDwindow-0");
			String url = getDriver().getCurrentUrl();
			System.out.println(url);
			if (url.contains("https://accounts.google.com")) {
				getDriver().switchTo().window("CDwindow-0");
				logger.info("Google verification page displayed");
				extent.extentLogger("Otp", "Google verification page displayed");
				Back(1);
			} else {
				getDriver().switchTo().window("CDwindow-0");
				waitTime(2000);
				if (verifyIsElementDisplayed(PWALoginPage.objLoginTxt, "Login text")) {
					logger.info("Google verification page displayed");
					extent.extentLogger("Login", "Google verification page displayed");
					Back(1);
					verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					if (verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
						logger.info("User Logged in Successfully");
						extent.extentLogger("Logged in", "User Logged in Successfully");
						click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Close button");
						logout();
					}
				} else {
					verifyElementPresent(PWAHomePage.objZeeLogo, "Zee logo");
					verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					if (verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
						logger.info("User Logged in Successfully");
						extent.extentLogger("Logged in", "User Logged in Successfully");
						click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Close button");
						logout();
					}

				}
			}
		}
	}

	/*
	 * Login through Twitter
	 */
	public void twitterLogin() throws Exception {
		extent.HeaderChildNode("Login through Twitter");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
		waitTime(5000);
		verifyElementPresentAndClick(PWALoginPage.objTwitterIcon, "Twitter icon");
		waitTime(7000);
		System.out.println(getDriver().getWindowHandles());
		System.out.println(getDriver().getCurrentUrl());
		getDriver().switchTo().window("CDwindow-3");

		waitTime(5000);
		System.out.println(getDriver().getCurrentUrl());

		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
			verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");
		}

		else if (verifyIsElementDisplayed(PWALoginPage.objTwitterEmaildField, "Twitter Email field")) {

			type(PWALoginPage.objTwitterEmaildField, "Zee5latest@gmail.com", "Email Field");
			hideKeyboard();
			verifyElementPresentAndClick(PWALoginPage.objTwitterPasswordField, "Twitter Password field");
			type(PWALoginPage.objTwitterPasswordField, "User@123\n", "Password field");
			click(PWALoginPage.objTwitterSignInButton, "Sign in button");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
			click(PWAHamburgerMenuPage.objLoginBtn, "Login");
			verifyElementPresentAndClick(PWALoginPage.objTwitterIcon, "Twitter icon");
			waitTime(8000);
		}

		if (verifyIsElementDisplayed(PWALoginPage.objTwitterAuthorizeButton, "Authorize")) {
			click(PWALoginPage.objTwitterAuthorizeButton, "Authorize");
			waitTime(7000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
			if (verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
			}
		} else {
			waitTime(8000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			if (verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
				click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Close button");

			}
		}
		System.out.println(getDriver().getCurrentUrl());
		logout();
	}

	/*
	 * Validation of Calender and selecting DOB
	 */
	public void calenderFunctionality() throws Exception {
		extent.HeaderChildNode("Calender Functionality");
		if (verifyIsElementDisplayed(PWASignupPage.objYearPickerTab, "Year")) {
			click(PWASignupPage.objDayPickerTab, "Day picker");
			click(PWASignupPage.objDayPickerTabValue, "Day picker value");
			click(PWASignupPage.objMonthPickerTab, "Month Picker");
			click(PWASignupPage.objMonthPickerTabValue, "Month Picker value");
			click(PWASignupPage.objYearPickerTab, "Year Picker");
			if (verifyIsElementDisplayed(PWASignupPage.objYearPickerTabValueNotActive, "Further year") == false) {
				logger.info("User is not allowed to select future date/year in calender tab");
				extent.extentLogger("Calender", "User is not allowed to select future date/year in calender tab");
			}
			click(PWASignupPage.objYearPickerTabValue, "Year Picker value");
		} else {
			click(PWALoginPage.objDateOfBirthField, "Date Of Birth Field");
			verifyIsElementDisplayed(PWALoginPage.objCalenderPopUp, "Calender PopUp");
			click(PWALoginPage.objSelectDateInCalender, "Date in Calender");
		}
	}

	/** ===================BINDU ZEE%ORIGINALS ============================ */

	public void Zee5LandingPage() throws Exception {
		extent.HeaderChildNode("Validating user navigated to Zee5Originals landing page");
		waitTime(3000);
		navigateToAnyScreen("ZEE5 Originals");
		waitTime(3000);
		if (verifyElementPresent(PWAZee5OriginalPage.objHighlightedTab("ZEE5 Originals"), "Zee5 Originals")) {
			logger.info("Zee5 Originals is Heighlighted, User is navigated to Zee5 Originals Tab");
			extent.extentLogger("Zee5 Originals landing Page",
					"Zee5 Originals is Heighlighted, User is navigated to Zee5 Originals Tab");

		}
	}

	// ** Validating the trays present in the Zee5 landing Page **//

	public void zee5originalstrayvalidation() throws Exception {
		extent.HeaderChildNode("Verifing the trays displayed in zee5originals Tab");
		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);

		Response resp = ResponseInstance.getResponseForPages("zeeoriginals", languageSmallText);

		List<String> apiTitleList = new LinkedList<String>();

		List<String> apitotaltrays = resp.jsonPath().getList("buckets");
		System.out.println(apitotaltrays.size());
		for (int i = 1; i < apitotaltrays.size(); i++) {
			String traytitle = resp.jsonPath().getString("buckets[" + i + "].title");
			apiTitleList.add(traytitle);
		}

		System.out.println("api: " + apiTitleList);

		List<String> uiTitleList = new LinkedList<String>();

		List<WebElement> uitotaltrays = findElements(By.xpath("//div[@class='trayHeader']"));
		System.out.println(uitotaltrays.size());

		for (int j = 0; j < uitotaltrays.size() - 1; j++) {
			String trayTitle = findElement(By.xpath("(//div[@class='trayHeader'])[" + (j + 1) + "]")).getText();
			uiTitleList.add(trayTitle);
			PartialSwipe("UP", 1);

			if (apiTitleList.get(j).equalsIgnoreCase(uiTitleList.get(j))) {
				logger.info("API title: " + apiTitleList.get(j) + " is verified with UI title: " + uiTitleList.get(j));
				extent.extentLogger("Tray validation",
						"API title: " + apiTitleList.get(j) + " is verified with UI title: " + uiTitleList.get(j));

			}
		}
		System.out.println("UI: " + uiTitleList);
	}

	public String allSelectedLanguages() throws Exception {
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language button");
		waitTime(2000);
		waitForElementAndClick(PWAHamburgerMenuPage.objContentLanguageBtn, 2, "Content languages");
		waitTime(2000);
		List<WebElement> allSelectedLanguages = getDriver().findElements(PWAHamburgerMenuPage.objSelectedLanguages);
		String langtext = "";
		for (int i = 0; i < allSelectedLanguages.size(); i++) {
			// System.out.println(i);
			langtext = allSelectedLanguages.get(i).getAttribute("for").replace("content_", "") + "," + langtext;
			// System.out.println(langtext.replaceAll(",$",""));
		}
		String finalLangString = langtext.replaceAll(",$", "");
		waitForElementAndClick(PWAHamburgerMenuPage.objCancelBtnOnLangPp, 2, "cancel language Popup");
		return finalLangString;
	}

	// ** Navigating to top of screen by tapping down bottom arrow without scrolling
	// **//

	public void VerifyTrayScrollingToTheTop() throws Exception {
		extent.HeaderChildNode("Validating tray scrolling to the top of the page");
		Swipe("UP", 1);
		Swipe("UP", 1);
		Swipe("UP", 1);
		waitForElementDisplayed(PWAZee5OriginalPage.objNavigateToTop, 3);
		click(PWAZee5OriginalPage.objNavigateToTop, "Back to Top Arrow");
		waitForElementDisplayed(PWAZee5OriginalPage.objTrayTitle, 5);
		if (verifyIsElementDisplayed(PWAZee5OriginalPage.objTrayTitle, "First Tray")) {
			logger.info("On tapping of 'Right side bottom arrow' user is navigated to top of screen without scrolling");
			extent.extentLogger("Right side bottom arrow",
					"On tapping of 'Right side bottom arrow' user is navigated to top of screen without scrolling");
		}
	}

	// ** Validating to watch promo of the prremium content **//
	public void ValidatingPremiumTag() throws Exception {
		extent.HeaderChildNode("Validating Premium Tag on the Premium Content card");
		waitTime(3000);
		if (verifyIsElementDisplayed(PWAZee5OriginalPage.objPremiumCard, "Premium Card"))
		// waitTime(5000);
		{
			verifyElementPresent(PWAZee5OriginalPage.objPremiumTag, "Premium Tag of Premium card");
			waitTime(3000);
			click(PWAZee5OriginalPage.objPremiumCard, "Premium card");
			waitTime(5000);
			if (verifyIsElementDisplayed(PWAPlayerPage.objCloseRegisterDialog, "Sign Up Pop Up")) {
				waitTime(3000);
				click(PWAPlayerPage.objCloseRegisterDialog, "Sign Up Pop Up close icon");
			}

			waitTime(2000);
			if (verifyIsElementDisplayed(PWAPlayerPage.objPromo, "Watch Promo icon")) {
				click(PWAPlayerPage.objPromo, "Watch Promo icon");
				verifyIsElementDisplayed(PWAPlayerPage.objPlayerPromoMetadata, "Promo Content");
				logger.info("User can able to watch Promo from the selected content");
				extent.extentLogger("Popup Screen", "User can able to watch Promo from the selected content");
				Back(1);
			}
			Back(1);
		}
	}

	// ** Validate Subscription popup is displayed after some interval of time **//

	public void Subscriptionpopup(String tab) throws Exception {
		String user = getParameterFromXML("userType");
		// handle mandatory pop up
		extent.HeaderChildNode("Validating the Subscribe CTA with Club Icon");
		mandatoryRegistrationPopUp(user);
		// navigateToAnyScreen(tab);
		waitTime(4000);
		if (tab.equalsIgnoreCase("club")) {
			if (verifyIsElementDisplayed(PWAZee5OriginalPage.objClubCard, "Club Card")) {
				click(PWAZee5OriginalPage.objClubCard, "Club Card");
			}
		} else {
			if (verifyIsElementDisplayed(PWAZee5OriginalPage.objPremiumCard, "Premium Card")) {
				click(PWAZee5OriginalPage.objPremiumCard, "Premium Card");
			}
		}
		if (verifyIsElementDisplayed(PWAShowsPage.objShowsTitle, "Shows Details page")) {// Show details
			click(PWALandingPages.objTrayClubOrPremiumCardInShowDetails, "Premium/Club card");
		}
		if (verifyIsElementDisplayed(PWAHomePage.objKalKalturaPlayer, "Kaltura Player")) {
			if (tab.equalsIgnoreCase("club")) {
				if (!user.equals("SubscribedUser")) {
					verifyElementPresent(PWAPlayerPage.objSubscribeCTAWithClubIcon,
							"Subscribe CTA with Club Icon for Guest/Non Subscribed user");
				} else {
					if (verifyIsElementDisplayed(PWAPlayerPage.objSubscribeCTAWithClubIcon,
							"Subscribe CTA with Club Icon for Subscribed User")) {
						logger.error("Subscribe CTA with Club Icon should not be displayed for Subscribed user");
						extent.extentLoggerFail("",
								"Subscribe CTA with Club Icon should not be displayed for Subscribed user");
					}
				}
			} else {
				if (!user.equals("SubscribedUser")) {
					verifyElementPresent(PWAPlayerPage.objGetPremium,
							"Subscribe CTA with Premium Icon for Guest/Non Subscribed user");
				} else {
					if (verifyIsElementDisplayed(PWAPlayerPage.objGetPremium,
							"Subscribe CTA with Premium Icon for Subscribed User")) {
						logger.error("Subscribe CTA with Premium Icon should not be displayed for Subscribed user");
						extent.extentLoggerFail("",
								"Subscribe CTA with Premium Icon should not be displayed for Subscribed user");
					}
				}
			}
			extent.HeaderChildNode("Validating the Subscription Popup");
			if (!user.equals("SubscribedUser")) {
				// waitExplicitlyForElementPresence(PWASubscriptionPages.objSubscribePopupTitle,
				// 60, "Subscribe Pop Up");
				waitTime(10000);
				if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
					logger.info("Verified that Subscribe Pop up is displayed for Premium/Club content");
					extent.extentLogger("Subscription Popup",
							"Verified that Subscribe Pop up is displayed for Premium/Club content");
					click(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
					Back(1);
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
				} else {
					pausePlayer();
					try {
						String totalduration = getText(PWAPlayerPage.totalDurationTime);
						if (!totalduration.equals("")) {
							logger.info("Trailer is playing with total duration of " + totalduration);
							extent.extentLogger("", "Trailer is playing with total duration of " + totalduration);
							logger.info(
									"Subscribe Pop up is not displayed for Premium/Club Show, second content because trailer is still playing");
							extent.extentLogger("Subscribe Pop pup",
									"Subscribe Pop up is not displayed for Premium/Club Show, second content because trailer is still playing");
						} else {
							logger.info("Failed to get trailer total duration");
							extent.extentLogger("", "Failed to get trailer total duration");
						}
					} catch (Exception e) {
						logger.error("Failed to get trailer total duration");
						extent.extentLogger("", "Failed to get trailer total duration");
					}
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
				}

			} else {
				if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
					logger.error("Subscribe Pop up is displayed for Premium/Club Show for Subscribed User");
					extent.extentLoggerFail("",
							"Verified that Subscribe Pop up is displayed for Premium/Club Show for Subscribed User");
					click(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
				} else {
					logger.info(
							"Subscribe Pop up is not displayed for Premium/Club Show for Subscribed User, expected behavior");
					extent.extentLogger("Subscribe Pop pup",
							"Subscribe Pop up is not displayed for Premium/Club Show for Subscribed User, expected behavior");
				}
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			}
		}
	}

	/** ===============BHAVANA - SHOWS MODULE================================ */
	/*
	 * Function to verify Shows Landing page
	 */
	public void showsLandingPage() throws Exception {
		verifyElementPresentAndClick(PWAShowsPage.objShows, "Shows tab");
		waitTime(3000);
		if (verifyElementPresent(PWAHomePage.objHighlightedTab("Shows"), "Shows Tab Highlighted")) {
			logger.info("User is navigated to Shows landing page");
			extent.extentLogger("Shows landing Page", "User is navigated to Shows page");
		}
	}

	/*
	 * Function to verify View All in Shows landing page
	 */

	public void ViewAllValidtaion() throws Exception {
		extent.HeaderChildNode("ViewAll landing page");
		waitTime(6000);
		verifyElementPresentAndClick(PWAShowsPage.objViewAll, "View all arrow of Top Zee Kannada Shows");
		waitTime(8000);
		logger.info("Current URL is " + getDriver().getCurrentUrl());
		extent.extentLogger("version", "Current URL is " + getDriver().getCurrentUrl());
		waitTime(3000);
		String url = getDriver().getCurrentUrl();
		waitTime(5000);
		if (url.contains("top-zee-kannada-shows")) {
			System.out.println("ViewAll Wrap page displayed");
			logger.info("User is navigated to ViewAll Wrap page after clicking on ViewAll");
			extent.extentLogger("Shows landing Page", "User is navigated to Top Zee Kannada Shows ViewAll wrap");
		} else {
			System.out.println("ViewAll Wrap page not displayed");
			logger.info("User didn't navigate to ViewAll Wrap page after clicking on ViewAll");
			extent.extentLogger("Shows landing Page", "User didn't navigated to Top Zee Kannada Shows ViewAll wrap");
		}
		waitTime(3000);
		Back(1);
	}

	public void LinksValidation(String userType) throws Exception {
		InternalLinksValidation();
		ExternalLinksValidation();
	}

	public By TextToXpathusingclass(String text) throws Exception {
		return By.xpath("//*[@class='" + text + "']");
	}

	public void ContinuewatchingTray(String UserType) throws Exception {
		extent.HeaderChildNode("Landing page module: Continue watching tray");
		Navigate_to_HomeScreen_using_Zee5Logo();
		if (UserType == "Guest") {
			waitTime(6000);
			verifyElementNotPresent(Text_To_Xpath("Continue Watching"), 20);
		} else {
			waitTime(6000);
			Swipe_till_Text("Continue Watching");
		}
	}

	public void Navigate_to_HomeScreen_using_Zee5Logo() throws Exception {
		extent.HeaderChildNode("Navigate to HomeScreen using Zee5 Logo");
		verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee5 Logo");
		waitForElementDisplayed(PWAHomePage.objContTitleOnCarousel, 20);
		verifyElementPresent(PWAHomePage.objContTitleOnCarousel, "Carousal content title");
	}

	public By Text_To_Xpath(String text) throws Exception {
		return By.xpath("//*[contains(@text,'" + text + "')][@onScreen='true'][@top='true'][@visible='true']");
	}

	public void Swipe_till_Text(String text) throws Exception {
		waitTime(6000);
		for (int i = 0; i <= 5; i++) {
			if (verifyIsElementDisplayed(Text_To_Xpath(text), text)) {
				System.out.println("" + text + " found");
				break;
			} else {
				PartialSwipe("up", 1);
			}
		}
	}

	public By TextToXpathusingclasswithindex(String text, int i) throws Exception {
		return By.xpath("(//*[@class='" + text + "'])[" + i + "]");
	}

	/**
	 * ================================BASAVARAJ VIL
	 * MODULE==================================
	 */
	public void verifyingVodafoneNativeApp() throws Exception {
		String vodafonePhoneNumber = "8095760130";
		extent.HeaderChildNode("VodafonePlayFunctionFromNativeApp");
		// Click on Hamburger
		// waitForElement(NativeVodafonePlayPage.HamburgerBtn, 5, "Search");
		Thread.sleep(3000);
		click(NativeVodafonePlayPage.HamburgerBtn, "HamburgerBtn");
		Thread.sleep(3000);
		Swipe("UP", 1);
		Thread.sleep(3000);
		// Click on ZEE5 Icon
		verifyElementPresentAndClick(NativeVodafonePlayPage.Zee5IconinList, "Zee5 Option");
		Thread.sleep(3000);
		// Click on SearchIcon
		verifyElementPresentAndClick(NativeVodafonePlayPage.ChannelSearchIcon, "search icon");
		Thread.sleep(3000);
		// Click on Search Textbox
		verifyElementPresentAndClick(NativeVodafonePlayPage.SearchTextBox, "Search TextBox");
		Thread.sleep(3000);
		// Send Value for Saerch
		type(NativeVodafonePlayPage.SearchTextBox, "Simmba\n", "Search TextBox");
		Thread.sleep(3000);
		hideKeyboard();
		// Click on Searched Data
		verifyElementPresentAndClick(NativeVodafonePlayPage.searchedData("Simmba"), "Searched Data");
		waitTime(2000);
		if (verifyIsElementDisplayed(NativeVodafonePlayPage.VILPlayIcon, "playicon")) {
			click(NativeVodafonePlayPage.VILPlayIcon, "playicon");
		}
		// NAVIGATION TO ZEE5 PWA
		Thread.sleep(7000);
		if (verifyIsElementDisplayed(NativeVodafonePlayPage.EnterYourNumber, "EnterNumber")) {
			verifyElementPresentAndClick(NativeVodafonePlayPage.NumberTextBox, "Number text");
			type(NativeVodafonePlayPage.NumberTextBox, vodafonePhoneNumber + "\n", "Number field");
			hideKeyboard();
			Thread.sleep(5000);
			verifyElementPresentAndClick(NativeVodafonePlayPage.continueBtn, "ContinueBtn");
			Thread.sleep(5000);
			verifyIsElementDisplayed(NativeVodafonePlayPage.EnterOTPText, "OTP Text");
			verifyElementPresentAndClick(NativeVodafonePlayPage.OTPTextField, "OTP text field");
			Thread.sleep(10000);
			type(NativeVodafonePlayPage.NumberTextBox, vodafonePhoneNumber + "\n", "Number field");
			hideKeyboard();
			Thread.sleep(5000);
			verifyElementPresentAndClick(NativeVodafonePlayPage.OTPgoBtn, "GoBtn");
			Thread.sleep(8000);
		}
		Thread.sleep(7000);

		if (verifyIsElementDisplayed(PWAPlayerPage.objPlayer, "Player")) {
			System.out.println("Navigated to Consumption Page");
			System.out.println("Player title :" + getText(By.xpath("//div[@class='consumptionMetaDiv']//h1")));
		}

		System.out.println(getDriver().getContext());
		Thread.sleep(2000);
		getDriver().context("WEBVIEW_1");
		Thread.sleep(2000);
		System.out.println(getDriver().getContext());
		String str = getCurrentActivity();
		if (str.contains("chrome")) {
			System.out.println("Navigated ZEE PWA in Browser and not in Zee5 Lite app");
			logger.info("Navigated ZEE PWA in Browser and not in Zee5 Lite app");
			extent.extentLogger("<b>" + "Navigated ZEE PWA in Browser and not in Zee5 Lite app",
					"Navigated ZEE PWA in Browser and not in Zee5 Lite app");
		}
		// Navigated URL
		waitTime(2000);
		waitForElement(PWAVodafonePlayPage.HamburgerBtn, 5, "Hamburger");
		click(PWAVodafonePlayPage.HamburgerBtn, "Zee5 Hamburger");
		Thread.sleep(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyAccount, "My Account");
		if (verifyIsElementDisplayed(PWAVodafonePlayPage.MySubscription, "MySubscription") == false) {
			System.out.println("User has no option to purchase the plans");
			logger.info("User has no option to purchase the plans");
			extent.extentLogger("<b>" + "User has no option to purchase the plans",
					"User has no option to purchase the plans");
		} else {
			logger.info("User has option to purchase the plans");
			extent.extentLoggerFail("<b>" + "User has option to purchase the plans",
					"User has option to purchase the plans");
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objCloseIcon, "Close Icon");
		waitTime(2000);
		// playFreeContent
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(5000);
		verifyElementPresentAndClick(PWASearchPage.objSearchTextBox, "Search Text Box");
		waitTime(5000);
		Keyevent(32);
		Keyevent(43);
		Keyevent(32);
		Keyevent(32);
		Keyevent(41);
		Keyevent(29);
		Keyevent(42);
		Keyevent(33);
		Keyevent(62);
		Keyevent(36);
		Keyevent(49);
		Keyevent(32);
		Keyevent(35);
		Keyevent(29);
//		type(PWASearchPage.objSearchEditBox, "Doddmane ", "Search Field");
//		waitTime(2000);
//		type(PWASearchPage.objSearchEditBox, "Hudga", "Search Field");
		waitTime(5000);
		hideKeyboard();
		waitTime(3000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeMovie2");
		click(PWASearchPage.objSpecificSearch(keyword), "Searched Content");
		waitTime(10000);
		if (verifyIsElementDisplayed(PWAPlayerPage.objPlayer, "Player")) {
			System.out.println("Navigated to Consumption Page");
			System.out.println("Player title :" + getText(By.xpath("//div[@class='consumptionMetaDiv']//h1")));
		}
		waitTime(2000);
		// playFreeContent
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(5000);
		verifyElementPresentAndClick(PWASearchPage.objSearchTextBox, "Search Text Box");
		waitTime(5000);
		Keyevent(30);
		Keyevent(36);
		Keyevent(37);
		Keyevent(42);
		Keyevent(42);
		Keyevent(29);
//		type(PWASearchPage.objSearchEditBox, "Bhi", "Search Field");
//		waitTime(2000);
//		type(PWASearchPage.objSearchEditBox, "nna", "Search Field");
		waitTime(5000);
		hideKeyboard();
		waitTime(3000);
		String keyword1 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovie2");
		click(PWASearchPage.objSpecificSearch(keyword1), "Searched Show");
		waitTime(10000);
		if (verifyIsElementDisplayed(PWAPlayerPage.objPlayer, "Player")) {
			System.out.println("Navigated to Consumption Page");
			System.out.println("Player title :" + getText(By.xpath("//div[@class='consumptionMetaDiv']//h1")));
		}
		// Click on Zee5 Hamburger
		verifyElementPresentAndClick(PWAVodafonePlayPage.HamburgerBtn, "Zee5 Hamburger");
		Thread.sleep(5000);
		// Click on Zee5 Profile
		verifyElementPresentAndClick(PWAVodafonePlayPage.userProfileName, "Zee5 UserProfileName");
		Thread.sleep(3000);
		Boolean popup = verifyIsElementDisplayed(PWAVodafonePlayPage.popupCloseBtn, "Zee5 Popup");
		// getDriver().findElement(PWAVodafonePlayPage.popupCloseBtn).isDisplayed();
		if (popup == true) {
			getDriver().findElement(PWAVodafonePlayPage.popupCloseBtn).click();
		}
		Thread.sleep(3000);
		// Validating Phone number
		System.out.println("ProfileNumber : " + getDriver().findElement(PWAVodafonePlayPage.userPhoneNumber).getText());
		String profileNumber = getDriver().findElement(PWAVodafonePlayPage.userPhoneNumber).getText();
		if (profileNumber.contains(vodafonePhoneNumber)) {
			System.out.println("Logged in to Zee5 using VodafonePlay Phonenumber");
			logger.info("Logged in to Zee5 using VodafonePlay Phonenumber");
			extent.extentLogger("<b>" + "Logged in to Zee5 using VodafonePlay Phonenumber",
					"Logged in to Zee5 using VodafonePlay Phonenumber");
		}
	}

	public void Keyevent(int n) throws Exception {
		Runtime.getRuntime().exec("adb shell input keyevent " + n + "");
		Thread.sleep(2000);
	}

	public String getCurrentActivity() throws Exception {
		String cmd = "adb shell \"dumpsys window windows | grep 'mCurrentFocus'\"";
		Process process = Runtime.getRuntime().exec(cmd);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String str = br.readLine();
		return str;
	}

	public void swipeLIVETVTumbnailToLeft(String str) throws InterruptedException {
		try {
			WebElement sourceLocator = getDriver().findElement(
					By.xpath("(((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str
							+ "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper']//figure//div[@class='noSelect content'])[3])"));
			WebElement targetLocator = getDriver().findElement(
					By.xpath("(((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str
							+ "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper']//figure//div[@class='noSelect content'])[2])"));
			Thread.sleep(4000);
			Actions action = new Actions(getDriver());
			action.dragAndDrop(sourceLocator, targetLocator).build().perform();
		} catch (Exception e) {
			System.out.println("No trays to swipe");
		}
//		action.clickAndHold(sourceLocator).moveToElement(targetLocator).build().perform();
	}

	public String checkPremiumORFreeFromLIVETVPageTrayAndSelect(String str, String premiumORfree) throws Exception {
		try {
			ScrollToTheElement(TextToXpath(str));
			waitTime(5000);
			ScrollToTheElement(TextToXpath(str));
			waitTime(8000);
		} catch (Exception e) {
			Swipe("UP", 1);
			waitTime(8000);
			ScrollToTheElement(TextToXpath(str));
		}
		String ValueOfPremiumTumbnail = null;
		logger.info("Check premium and select");
		List<WebElement> tumnails = getDriver()
				.findElements(By.xpath("(((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str
						+ "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper']"));
		System.out.println("Number of Tumbnails : " + tumnails.size());
		boolean flag = false;
		for (int j = 1; j <= 5; j++) {
			for (int i = 1; i <= tumnails.size(); i++) {
				WebElement specificTumbnail = getDriver().findElement(
						By.xpath("(((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str
								+ "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper']//figure//a[@class='noSelect content'])["
								+ i + "])"));
				if (verifyIsElementDisplayed(PWAHomePage.objLIVETVIsPremiumTumbnail(str, i), "Premium") == true) {
					System.out.println("premium Tumbnail");
					// System.out.println(getAttributValue("title",
					// PWAHomePage.objTumbnailTitle(str, i)));
					ValueOfPremiumTumbnail = getAttributValue("title", PWAHomePage.objLIVETvTumbnailTitle(str, i));
					System.out.println("Premium LIVETV Thumbnail Title : " + ValueOfPremiumTumbnail);
					if (premiumORfree.equals("PREMIUM")) {
						clickByElement(specificTumbnail, "Specific Thumbnail from Premium");
						flag = true;
						break;
					}
				} else if (verifyIsElementDisplayed(PWAHomePage.objLIVETVIsPremiumTumbnail(str, i),
						"Premium") == false) {
					System.out.println("No premium Thumbnail");
					ValueOfPremiumTumbnail = getAttributValue("title", PWAHomePage.objLIVETvTumbnailTitle(str, i));
					System.out.println("Non-Premium LIVETV Thumbnail Title : " + ValueOfPremiumTumbnail);
					if (premiumORfree.equals("FREE")) {
						clickByElement(specificTumbnail, "Specific Thumbnail from Free");
						flag = true;
						break;
					}
				}
			}
			if (flag == true) {
				break;
			}
			swipeLIVETVTumbnailToLeft(str);
		}
		return ValueOfPremiumTumbnail;
	}

	/**
	 * ===============================BASAVARAJ CONTENT
	 * DETAILS============================
	 */

	@SuppressWarnings("rawtypes")
	public void ContentDetails(String userType) throws Exception {
		extent.HeaderChildNode("Verification of Episode Dropdown in Show Details Page");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		mandatoryRegistrationPopUp(userType);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("consumptionsShow");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(5000);
		hideKeyboard();
		click(PWASearchPage.objSpecificSearch(keyword), "Searched Show");
		waitTime(10000);
		extent.extentLogger("Navigated to tab", "Navigated to tab : " + getText(PWAHomePage.objSeletedTab));
		logger.info("Navigated to tab : " + getText(PWAHomePage.objSeletedTab));
		waitTime(5000);
		if (verifyIsElementDisplayed(PWAShowsPage.objShowdeatilPlayIcon, "Show Details Page")) {
			extent.extentLogger("Navigated to ShowdetailPage", "Navigated to Show Details Page");
			logger.info("Navigated to Show Details Page");
		}
		if (verifyIsElementDisplayed(PWAShowsPage.objEpisodeTrayinShowdetailPage,
				"Episode Tray below Feature carousel")) {
			extent.extentLogger("Episode Tray below Feature carousel is present",
					"Episode Tray below Feature carousel is present");
			logger.info("Episode Tray below Feature carousel is present");
		}
		if (verifyIsElementDisplayed(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown")) {
			click(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown");
			List<WebElement> objShowDetailEpisodeDropdownValuesSize = getDriver().findElements(By.xpath(
					"(((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span"));
			waitTime(3000);
			extent.extentLogger("", "Drop Down Size : " + objShowDetailEpisodeDropdownValuesSize.size());
			logger.info("Drop Down Size : " + objShowDetailEpisodeDropdownValuesSize.size());
			ArrayList<String> Listofepisode = new ArrayList<String>();
			if (objShowDetailEpisodeDropdownValuesSize.size() >= 1) {
				for (int i = 1; i < objShowDetailEpisodeDropdownValuesSize.size(); i++) {
					Listofepisode
							.add(getAttributValue("aria-label", PWAShowsPage.objShowDetailEpisodeDropdownValues(i)));
				}
				extent.extentLogger("Episode list present in Episode Dropdown",
						"Episode list in Episode Dropdown: " + Listofepisode);
				logger.info("Episodes list present in Dropdown: " + Listofepisode);
			} else {
				extent.extentLoggerFail("Episode list not present in Episode Dropdown",
						"Episode list not present in Episode Dropdown");
				logger.info("Empty Episode List");
			}
		}
		List<WebElement> objShowDetailEpisodeDropdownValuesSize = getDriver().findElements(By.xpath(
				"(((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span"));
		if (objShowDetailEpisodeDropdownValuesSize.size() > 6) {
			touchAction = new TouchAction(getDriver());
			touchAction.press(PointOption.point(350, 840)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(350, 530)).release().perform();
		}
		extent.extentLogger("Selected Episode",
				"Selected Episode Set: " + getText(PWAShowsPage.objSelectedEpisodeinDropdown));
		logger.info("Selected Episode Set: " + getText(PWAShowsPage.objSelectedEpisodeinDropdown));
		List<WebElement> objShowDetailNonSelectedEpisodeDropdownValues = getDriver().findElements(By.xpath(
				"((((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span[@aria-selected='false'])"));
		waitTime(3000);
		ArrayList<String> ListofNonSelectedEpisode = new ArrayList<String>();
		for (int i = 1; i < objShowDetailNonSelectedEpisodeDropdownValues.size(); i++) {
			ListofNonSelectedEpisode
					.add(getAttributValue("aria-label", PWAShowsPage.objShowDetailNonSelectedEpisodeDropdownValues(i)));
		}
		String nonselected = getText(PWAShowsPage.objShowDetailNonSelectedEpisodeDropdownValues(1));
		click(PWAShowsPage.objShowDetailNonSelectedEpisodeDropdownValues(1),
				"Non-Selected Episode Set: " + nonselected);
		click(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown");
		String selected = getText(PWAShowsPage.objSelectedEpisodeinDropdown);
		extent.extentLogger("", "Selected Episode : " + selected);
		logger.info("Selected Episode : " + selected);
		click(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown");
		// gaps covered
		// Verify if the episode tray gets updated bases on the slected dropdown set
		boolean match = false;
		String dropdownStartEpisode = selected.replace("Episodes", "");
		dropdownStartEpisode = dropdownStartEpisode.split("-")[0].trim();
		logger.info("Start Episode from dropdown: " + dropdownStartEpisode);
		extent.extentLogger("", "Start Episode from dropdown: " + dropdownStartEpisode);
		try {
			List<WebElement> cardmeta = findElements(
					By.xpath("//div[@data-index='0']//div[@class='showDuration']//span"));
			for (int i = 0; i < cardmeta.size(); i++) {
				String cardStartEpisode = cardmeta.get(i).getText();
				if (cardStartEpisode.contains(dropdownStartEpisode)) {
					logger.info("Episode tray first card displays metadata: " + cardStartEpisode);
					extent.extentLogger("", "Episode tray first card displays metadata: " + cardStartEpisode);
					logger.info("Episode tray updated successfully on selecting Episode Set from dropdown");
					extent.extentLogger("", "Episode tray updated successfully on selecting Episode Set from dropdown");
					match = true;
					break;
				}
			}
		} catch (Exception e) {
			extent.extentLoggerFail("", "Failed to fetch Card metadata under Episodes tray");
			logger.error("Failed to fetch Card metadata under Episodes tray");
		}
		if (match == false) {
			logger.info("Episode tray failed to update after selecting Episode set from dropdown");
			extent.extentLogger("", "Episode tray failed to update after selecting Episode set from dropdown");
		}
		scrolltillBackToArrowAppears();
		if (verifyIsElementDisplayed(PWAShowsPage.objShowdeatilPlayIcon, "ShowDetailPage")) {
			extent.extentLogger("Navigated to Top of page", "Navigated to Top of page");
			logger.info("Navigated to Top");
		}
		extent.HeaderChildNode("Verification of Share Functionality in Show Details Page");
		click(PWAShowsPage.objShareIcon, "Share icon");
		waitTime(2000);
		// facebookshare
		getDriver().context("NATIVE_APP");
		Dimension dim = getDriver().manage().window().getSize();
		int startx = (int) (dim.width * 0.6);
		int starty = (int) (dim.height * 0.7);
		int endx = (int) (startx * 0.1);
		int endy = starty;
		for (int i = 0; i < 2; i++) {
			try {
				getDriver().findElement(PWAShowsPage.objFacebookApp).click();
				break;
			} catch (Exception e) {
				TouchAction act = new TouchAction(getDriver());
				act.press(PointOption.point(startx, starty))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(endx, endy)).release().perform();
				waitTime(2000);
			}
		}
		waitTime(3000);
		waitForElementAndClick(PWAShowsPage.objFacebookPostBtn, 10, "POST button in Facebook App");
		waitTime(5000);
		getDriver().context("CHROMIUM");
		// twittershare
		click(PWAShowsPage.objShareIcon, "Share icon");
		waitTime(2000);
		getDriver().context("NATIVE_APP");
		waitTime(5000);
		for (int i = 0; i < 2; i++) {
			try {
				getDriver().findElement(PWAShowsPage.objTwitterApp).click();
				break;
			} catch (Exception e) {
				TouchAction act = new TouchAction(getDriver());
				act.press(PointOption.point(startx, starty))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(endx, endy)).release().perform();
				waitTime(2000);
			}
		}
		waitTime(5000);
		waitForElementAndClick(PWAShowsPage.objTwitterPostBtn1, 10, "Tweet button in Twitter App");
		if (verifyIsElementDisplayed(PWAShowsPage.objTwitterPostBtn1, "Tweet button in Twitter App")) {
			extent.extentLoggerFail("", "Twitter posting was unsuccessful due to character limit");
			logger.error("Twitter posting was unsuccessful due to character limit");
			directClickReturnBoolean(PWAShowsPage.objTwitterCloseBtn, "Twitter Close");
			directClickReturnBoolean(PWAShowsPage.objTwitterDeletePost, "Delete Post");
		}
		waitTime(5000);
		getDriver().context("CHROMIUM");
		extent.HeaderChildNode(
				"Verification of backend data and comparison with frontend meta data in Show Details Page");
		String contentID = verifyShowsDetailsMetaData();
		extent.HeaderChildNode("Verification of Reco Trays in Show Details Page");
		Response recoResp = ResponseInstance.getRecoTraysInDetailsPage(userType, contentID);
		ArrayList<String> recoTraysInDetailsPage = getAllRecoTraysFromDetails(recoResp);
		Swipe("DOWN", 2);
		scrolltillBackToArrowAppears();
		System.out.println(recoTraysInDetailsPage.size());
		for (int tray = 0; tray < recoTraysInDetailsPage.size(); tray++) {
			String trayTitleAPI = recoTraysInDetailsPage.get(tray);
			System.out.println("TrayTitle from API : " + trayTitleAPI);
			swipeTillTray(10, trayTitleAPI, "\"" + trayTitleAPI + "\" tray");
		}
		scrolltillBackToArrowAppears();
	}

	public String verifyShowsDetailsMetaData() throws Exception {
		// Watchhistory is showing at back-end response properly
		String contentURL = getDriver().getCurrentUrl();
		String[] abc = contentURL.split("/");
		String contentID = abc[abc.length - 1];
		logger.info("ContentID fetched from URL: " + contentID);
		extent.extentLogger("", "ContentID fetched from URL: " + contentID);
		Response resp = ResponseInstance
				.getResponse("https://gwapi.zee5.com/content/tvshow/" + contentID + "?translation=en&country=IN");
		extent.extentLogger("BackEnd data : ", "Backend data fetched: ");
		logger.info("Backend data fetched: ");
		String show = resp.jsonPath().getString("original_title");
		String id = resp.jsonPath().getString("id");
		String subtype = resp.jsonPath().getString("asset_subtype");
		String genre = resp.jsonPath().getString("genre[0].value");
		String totalSeasons = resp.jsonPath().getString("total_seasons");
		String totalEpisodes = resp.jsonPath().getString("seasons[0].total_episodes");
		String releaseDate = resp.jsonPath().getString("release_date");
		String ageRating = resp.jsonPath().getString("age_rating");
		extent.extentLogger("", "Show : " + show);
		logger.info("Show : " + show);
		extent.extentLogger("", "id : " + id);
		logger.info("Content ID : " + id);
		extent.extentLogger("", "Asset Type : " + subtype);
		logger.info("Asset Type : " + subtype);
		extent.extentLogger("", "Total Seasons : " + totalSeasons);
		logger.info("Total Seasons : " + totalSeasons);
		extent.extentLogger("", "Total Episodes : " + totalEpisodes);
		logger.info("Total Episodes : " + totalEpisodes);
		extent.extentLogger("", "Age Rating : " + ageRating);
		logger.info("Age Rating : " + ageRating);
		extent.extentLogger("", "Release Date : " + releaseDate);
		logger.info("Release Date : " + releaseDate);
		extent.extentLogger("", "Genre : " + genre);
		logger.info("Genre : " + genre);
		// Verification on front end
		String titleui = findElement(PWAPlayerPage.objContentShowTitle).getText();
		if (titleui.equals(show)) {
			extent.extentLogger("", "API Show Title: " + show + " is displayed in UI");
			logger.info("API Show Title: " + show + " is displayed in UI");
		} else {
			extent.extentLoggerFail("", "API fetched show title is " + show + " and UI displays title " + titleui);
			logger.info("API fetched show title is " + show + " and UI displays title " + titleui);
		}
		List<WebElement> meta = findElements(PWAShowsPage.metainfolist);
		ArrayList<String> metalist = new ArrayList<String>();
		for (int i = 0; i < meta.size(); i++) {
			metalist.add(meta.get(i).getText());
		}
		extent.extentLogger("", "Meta data fetched from front end : " + metalist);
		logger.info("Meta data fetched from front end : " + metalist);
		for (int i = 0; i < meta.size(); i++) {
			String currentmeta = meta.get(i).getText();
			if (i == 0) {
				if (currentmeta.contains(totalEpisodes)) {
					extent.extentLogger("", "Total Episodes : " + currentmeta + " is displayed in UI");
					logger.info("Total Episodes : " + currentmeta + " is displayed in UI");
				} else {
					extent.extentLoggerFail("",
							"API fetched number of episodes is " + totalEpisodes + " and UI displays " + currentmeta);
					logger.info(
							"API fetched number of episodes is " + totalEpisodes + " and UI displays " + currentmeta);
				}
			} else if (i == 1) {
				if (releaseDate.contains(currentmeta)) {
					extent.extentLogger("", "Release Year : " + currentmeta + " is displayed in UI");
					logger.info("Release Year : " + currentmeta + " is displayed in UI");
				} else {
					extent.extentLoggerFail("",
							"API fetched release year is " + releaseDate + " and UI displays " + currentmeta);
					logger.info("API fetched release year is " + releaseDate + " and UI displays " + currentmeta);
				}
			} else if (i == 2) {
				if (genre.contains(currentmeta)) {
					extent.extentLogger("", "Genre : " + currentmeta + " is displayed in UI");
					logger.info("Genre : " + currentmeta + " is displayed in UI");
				} else {
					extent.extentLoggerFail("", "API fetched genre is " + genre + " and UI displays " + currentmeta);
					logger.info("API fetched genre is " + genre + " and UI displays " + currentmeta);
				}
			}
		}
		if (metalist.contains(ageRating)) {
			extent.extentLogger("", "Age Rating : " + ageRating + " is displayed in UI");
			logger.info("Age Rating : " + ageRating + " is displayed in UI");
		} else {
			extent.extentLoggerFail("", "API fetched Age Rating is " + ageRating + " is not displayed in UI");
			logger.info("API fetched Age Rating is " + ageRating + " is not displayed in UI");
		}

		return contentID;
	}

	public void scrolltillBackToArrowAppears() throws Exception {
		PartialSwipe("UP", 3);
		for (int i = 1; i <= 10; i++) {
			Swipe("UP", 1);
			if (verifyIsElementDisplayed(PWAShowsPage.objBackToTopArrow, "Back to Top Arrow")) {
				waitTime(2000);
				click(PWAShowsPage.objBackToTopArrow, "BackToTop Arrow");
				break;
			}
		}
	}

	public void checkDurationandProgressVideo(String userType) throws Exception {
		extent.HeaderChildNode("Check video Duration and Progress");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("timedAnchorMovie");
		String currentDuration = "", currentDuration1 = "", totalDuration = "", totalDuration1 = "";
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Field");
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(5000);
		hideKeyboard();
		waitTime(3000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		if (!userType.equals("SubscribedUser")) {
			waitForElementAndClickIfPresent(PWASubscriptionPages.objPopupCloseButton, 5, "Close in Pop Up");
		}
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		// Content elapsed time should update with the content playback
		currentDuration = getText(PWAPlayerPage.objcurrenttime);
		extent.extentLogger("currentitme", "Current time: " + currentDuration);
		logger.info("Current time: " + currentDuration);
		totalDuration = getText(PWAPlayerPage.objtotaltime);
		extent.extentLogger("totalDuration", "Total duration: " + totalDuration);
		logger.info("Total duration: " + totalDuration);
		String progress = null;
		if (verifyIsElementDisplayed(PWAPlayerPage.objprogressBar, "ProgressBar")) {
			progress = getAttributValue("style", PWAPlayerPage.objprogressProgress);
			extent.extentLogger("progress", "Progress : " + progress);
			logger.info("Progress : " + progress);
		}
		Thread.sleep(5000);
		verifyElementPresentAndClick(PWAPlayerPage.forward10SecBtn, "10 sec forward");
		verifyElementPresentAndClick(PWAPlayerPage.playBtn, "Play btn");
		// Waiting for some time
		Thread.sleep(10000);
		Thread.sleep(10000);
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		currentDuration1 = getText(PWAPlayerPage.objcurrenttime);
		extent.extentLogger("currentitme", "Current time: " + currentDuration1);
		logger.info("Current time: " + currentDuration1);
		totalDuration1 = getText(PWAPlayerPage.objtotaltime);
		extent.extentLogger("totalDuration", "Total duration: " + totalDuration1);
		logger.info("Total duration: " + totalDuration1);
		String progress1 = getAttributValue("style", PWAPlayerPage.objprogressProgress);
		logger.info("Progress : " + progress1);
		extent.extentLogger("", "Progress : " + progress1);
		// Validate the availabilty and functionality of progress bar button
		if (!progress.equals(progress1)) {
			extent.extentLogger("Progress Bar is functional", "Progress Bar is functional");
			logger.info("Progress Bar is functional");
		} else {
			extent.extentLoggerFail("Progress Bar is not functional", "Progress Bar is not functional");
			logger.info("Progress Bar is not functional");
		}
		// Content duration should be static on the player
		if (totalDuration.contains(totalDuration1)) {
			extent.extentLogger("Content duration is static", "Content duration is static");
			logger.info("Content duration is static");
		} else {
			extent.extentLoggerFail("Content duration is not static", "Content duration is not static");
			logger.info("Content duration is not static");
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean pausePlayer() throws InterruptedException {
		waitTime(5000);
		int deviceWidth = getDriver().manage().window().getSize().width;
		int deviceHeight = getDriver().manage().window().getSize().height;
		int x = deviceWidth / 2;
		int y = deviceHeight / 4;
		boolean charmboardpresent = false;
		boolean playerPaused = false;
		TouchAction act = new TouchAction(getDriver());
		act.tap(PointOption.point(x, y)).perform();
		// charm board presence
		/*
		 * try { getDriver().findElement(PWAPlayerPage.charmBoardPresence);
		 * charmboardpresent = true; System.out.println("Charmboard is present"); }
		 * catch (Exception e) { System.out.println("Charmboard is not present");
		 * charmboardpresent = false; }
		 */
		for (int trial = 0; trial <= 20; trial++) {
			try {
				act.tap(PointOption.point(x, y)).perform();
				System.out.println("Tapped on the Player to access player controls");
				if (charmboardpresent == true) {
					try {
						getDriver().findElement(PWAPlayerPage.charmBoardPause).click();
						String time = getText(PWAPlayerPage.objcurrenttime);
						extent.extentLogger("playerPaused", "Paused the Player " + time);
						logger.info("Paused the Player " + time);
						playerPaused = true;
						break;
					} catch (Exception e) {
					}
				} else {
					try {
						getDriver().findElement(PWAPlayerPage.pauseBtn).click();
						try {
							if (verifyElementDisplayed(PWAPlayerPage.objPlayButtonOfPausedPlayer)) {
								String time = getText(PWAPlayerPage.objcurrenttime);
								extent.extentLogger("playerPaused", "Paused the Player " + time);
								logger.info("Paused the Player " + time);
								playerPaused = true;
								break;
							}
						} catch (Exception e) {
							if (trial == 20) {
								extent.extentLogger("errorOccured", "Player not paused");
								logger.info("Player not paused");
							}
						}
					} catch (Exception e) {
						try {
							if (verifyElementDisplayed(PWAPlayerPage.objPlayButtonOfPausedPlayer)) {
								String time = getText(PWAPlayerPage.objcurrenttime);
								extent.extentLogger("playerPaused", "Paused the Player " + time);
								logger.info("Paused the Player " + time);
								playerPaused = true;
								break;
							}
						} catch (Exception e1) {
							if (trial == 20) {
								extent.extentLogger("errorOccured", "Player not paused");
								logger.info("Player not paused");
							}
						}
					}
				}
			} catch (Exception e) {
				Thread.sleep(1000);
				if (trial == 4) {
					extent.extentLogger("errorOccured", "Player not paused");
					logger.info("Player not paused");
				}
			}
		}
		return playerPaused;
	}

	@SuppressWarnings("rawtypes")
	public boolean pauseLiveTVPlayer() throws InterruptedException {
		int deviceWidth = getDriver().manage().window().getSize().width;
		int deviceHeight = getDriver().manage().window().getSize().height;
		int x = deviceWidth / 2;
		int y = deviceHeight / 3;
		boolean playerPaused = false;
		for (int trial = 0; trial <= 20; trial++) {
			try {
				TouchAction act = new TouchAction(getDriver());
				act.tap(PointOption.point(x, y)).perform();
				System.out.println("Tapped on the Player to access player controls");
				try {
					getDriver().findElement(PWAPlayerPage.pauseBtn).click();
					try {
						getDriver().findElement(PWAPlayerPage.playBtn);
						extent.extentLogger("playerPaused", "Paused the Player");
						logger.info("Paused the Player");
						playerPaused = true;
						break;
					} catch (Exception e) {
						if (trial == 20) {
							extent.extentLogger("errorOccured", "Player not paused");
							logger.info("Player not paused");
						}
					}
				} catch (Exception e) {
					try {
						getDriver().findElement(PWAPlayerPage.playBtn);
						extent.extentLogger("playerPaused", "Paused the Player");
						logger.info("Paused the Player");
						playerPaused = true;
						break;
					} catch (Exception e1) {
						if (trial == 20) {
							extent.extentLogger("errorOccured", "Player not paused");
							logger.info("Player not paused");
						}
					}
				}
			} catch (Exception e) {
				Thread.sleep(1000);
				if (trial == 4) {
					extent.extentLogger("errorOccured", "Player not paused");
					logger.info("Player not paused");
				}
			}
		}
		return playerPaused;
	}

	public void checkDurationInLivetv() throws Exception {
		extent.HeaderChildNode("checkDurationInLivetv");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		String keyword = getParameterFromXML("livetv");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Field");
		waitTime(5000);
		hideKeyboard();
		waitTime(3000);
		click(PWASearchPage.objSpecificSearch(keyword), "Searched Show");
		waitTime(10000);
		waitForPlayerAdToComplete("Live Player");
		pauseLiveTVPlayer();
		if (verifyIsElementDisplayed(PWAPlayerPage.objcurrenttime, "Current time")) {
			extent.extentLoggerFail("Current time is displayed", "Current time is displayed for Live TV");
			logger.error("Current time is displayed for Live TV");
		} else {
			extent.extentLogger("Current time is not displayed", "Live TV Current time is not displayed is expected");
			logger.info("Live TV Current time is not displayed is expected");
		}
		if (verifyIsElementDisplayed(PWAPlayerPage.objtotaltime, "Total time")) {
			extent.extentLoggerFail("Total time is displayed", "Total time is displayed for Live TV");
			logger.error("Total time is displayed for Live TV");
		} else {
			extent.extentLogger("Total time is not displayed", "Live TV Total time is not displayed is expected");
			logger.info("Live TV Total time is not displayed is expected");
		}
	}

//	TIMED ANCHORS

	/**
	 * ================================BASAVARAJ TIMED
	 * ANCHORS==================================
	 * 
	 */

	public void TimedAnchors(String userType) throws Exception {
		boolean execute = true;
		extent.HeaderChildNode("Timed Anchors Functionality");
		extent.extentLogger("Timed Anchors", "Timed Anchors");
		waitTime(3000);
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeMovie2");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		String currenturl = getDriver().getCurrentUrl();
		String timedurl = currenturl + "?t=60";
		getDriver().get(timedurl);
		System.out.println("Hit URL : " + timedurl);
		extent.extentLogger("", "Hit URL : " + timedurl);
		// getDriver().get("https://newpwa.zee5.com/movies/details/doddmane-hudga/0-0-2677?t=60");
		waitTime(10000);
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Sign Up pop up")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Sign Up Pop Up close button");
			}
		}
		if (userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objCompleteProfile, "Complete Profile popup ")) {
				click(PWAPlayerPage.objCompleteProfileCloseIcon, "Complete Profile Pop up close button");
			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
				System.out.println("The content is a Premium Content");
				extent.extentLogger("", "The content is a Premium Content");
				logger.error(
						"Timed Anchors Functionality could not be tested because no free timed anchor content is available");
				extent.extentLoggerFail("",
						"Timed Anchors Functionality could not be tested because no free timed anchor content is available");
				execute = false;
			}
		}
		if (execute) {
			waitForPlayerAdToComplete("Video Player");
			System.out.println("pause");
			pausePlayer();
			String currentDuration = getText(PWAPlayerPage.objcurrenttime);
			System.out.println("Current time: " + currentDuration);
			List<WebElement> anchors = getDriver().findElements(By.xpath("(//div[@playermarkertag='timer']//div)"));
			// Verify whether important segments are marked in player for Logged In user
			if (verifyIsElementDisplayed(PWAPlayerPage.objtimedanchors, "TimedAnchor")) {
				System.out.println("TimedAnchor Present");
				System.out.println("Number of Timed Anchors segments present :" + anchors.size());
				extent.extentLogger("", "Number of Timed Anchors segments present :" + anchors.size());
				if (anchors.size() >= 2) {
					click(PWAPlayerPage.objtimedAnchor(2), "Individual timed anchor");
					Thread.sleep(7000);
					System.out.println("Clicked on Timed Anchor at position : "
							+ getAttributValue("style", PWAPlayerPage.objtimedAnchor(2)));
					extent.extentLogger("", "Clicked on Timed Anchor at position : "
							+ getAttributValue("style", PWAPlayerPage.objtimedAnchor(2)));
					String currentTime1 = getText(PWAPlayerPage.objcurrenttime);
					System.out.println(
							"Current time after clicking on the TimedAnchor :" + getText(PWAPlayerPage.objcurrenttime));
					extent.extentLogger("",
							"Current time after clicking on the TimedAnchor :" + getText(PWAPlayerPage.objcurrenttime));
					if (!getAttributValue("style", PWAPlayerPage.objtimedAnchor(1))
							.equals(getAttributValue("style", PWAPlayerPage.objtimedAnchor(2)))) {
						System.out.println("Navigated to the TimeAnchor ");
						extent.extentLogger("Navigated to the TimeAnchor ", "Navigated to the TimeAnchor ");
					} else {
						System.out.println("Not Navigated to the TimeAnchor ");
						extent.extentLoggerFail("Not Navigated to the TimeAnchor ", "Not Navigated to the TimeAnchor ");
					}
					System.out.println(getText(PWAPlayerPage.objcurrenttime));
					if (!currentDuration.equals(currentTime1)) {
						System.out.println("Clicked on TimedAnchor and Time is changed");
						extent.extentLogger("Clicked on TimedAnchor and Time is changed",
								"Clicked on TimedAnchor and Time is changed");
					} else {
						System.out.println("Clicked on TimedAnchor and Time is not changed");
						extent.extentLoggerFail("Clicked on TimedAnchor and Time is not changed",
								"Clicked on TimedAnchor and Time is not changed");
					}

					waitForPlayerAdToComplete("Video Player");
					System.out.println("pause");

					pausePlayer();

					click(PWAPlayerPage.objPlayerPlay, "Play btn");

					waitForPlayerAdToComplete("Video Player");

					if (AdValue.equals("AdPlayed")) {
						System.out.println("Ad Played as it was present before TimedAnchor");
						extent.extentLogger("Ad Played as it was present before TimedAnchor",
								"Ad Played as it was present before TimedAnchor");
					} else {
						System.out.println("Ad not played as it was not present before TimedAnchor");
						extent.extentLogger("Ad not played as it was not present before TimedAnchor",
								"Ad not played as it was not present before TimedAnchor");
					}

					System.out.println("pause");
					pausePlayer();

					if (!currentTime1.equals(getText(PWAPlayerPage.objcurrenttime))) {
						System.out.println("Content Played after click on the TimedAnchor");
						extent.extentLogger("Content Played after click on the TimedAnchor",
								"Content Played after click on the TimedAnchor");
					} else {
						System.out.println("Content not Played after click on the TimedAnchor");
						extent.extentLoggerFail("Content not Played after click on the TimedAnchor",
								"Content not Played after click on the TimedAnchor");
					}
				}
			} else {
				System.out.println("TimedAnchor is not present");
			}
		}
	}

	public void ShowsTimeperiodProvided(String userType) throws Exception {
		/*
		 * Verify whether playback for content(wrt shows) starts playing based on the
		 * timeperiod provided in the URL
		 */
		extent.HeaderChildNode(
				"Verify whether playback for content(wrt shows) starts playing based on the timeperiod provided in the URL");
		int timeperiod = 35;
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("timedAnchorEpisode");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		typeAndGetSearchResult(PWASearchPage.objSearchEditBox, keyword, "Search Edit box: " + keyword);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		waitTime(7000);
		String currenturl = getDriver().getCurrentUrl();
		String timedurl = currenturl + "?t=" + timeperiod + "";
		System.out.println("Appending time period");
		extent.extentLogger("timeperiod", "Appending time period");
		getDriver().get(timedurl);
		System.out.println("Hit URL : " + timedurl);
		extent.extentLogger("", "Hit URL : " + timedurl);
		// getDriver().get("https://newpwa.zee5.com/tvshows/details/paaru/0-6-1179/paarvathi-kisses-aditya-paaru/0-1-manual_1dr9c5e034t0?t="+timeperiod+"");
		waitTime(5000);

		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Sign Up pop up")) {
				click(PWASubscriptionPages.objPopupCloseButton, "Sign Up Pop Up close button");
			}
		}

		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
				verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		waitForPlayerAdToComplete("Video Player");
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Sign Up pop up")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Sign Up Pop Up close button");

			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
				verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		Thread.sleep(6000);
		PresentTitle = getDriver().findElement(By.xpath("(//div[@class='consumptionMetaDiv']//h1)")).getText();

		System.out.println("Show Title : " + PresentTitle);
		String currentDuration1 = getText(PWAPlayerPage.objcurrenttime);
		System.out.println("Current time after appending timedperiod in URL : " + currentDuration1);

		if (timeToSec(currentDuration1) >= timeperiod) {
			System.out.println("Playback started from Appended time");
			extent.extentLogger("Playback started from Appended time", "Playback started from Appended time");
		} else {
			System.out.println("Playback not started from Appended time.");
			extent.extentLoggerFail("Playback not started from Appended time",
					"Playback not started from Appended time");
		}
	}

	public void musicTimeperiodProvided(String userType) throws Exception {
		/*
		 * // Verify whether playback for content(wrt music and music-video) starts
		 * playing based on the timeperiod provided in the URL
		 */
		extent.HeaderChildNode(
				"Verify whether playback for content(wrt music and music-video) starts playing based on the timeperiod provided in the URL");
		int timeperiod2 = 25;
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("timedAnchorMusic");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		String currenturl = getDriver().getCurrentUrl();
		String timedurl = currenturl + "?t=" + timeperiod2 + "";
		System.out.println("Appending time period");
		extent.extentLogger("timeperiod", "Appending time period");
		getDriver().get(timedurl);
		System.out.println("Hit URL : " + timedurl);
		extent.extentLogger("", "Hit URL : " + timedurl);
		// getDriver().get("https://newpwa.zee5.com/music-videos/details/appa-lyrical-punith-shetty/0-0-manual_15l5jn9il6o8?t="+timeperiod2+"");
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Sign Up pop up")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Sign Up Pop Up close button");
			}
		}
		if (userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objCompleteProfile, "Complete Profile popup ")) {
				click(PWAPlayerPage.objCompleteProfileCloseIcon, "Complete Profile Pop up close button");

			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
				verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		Thread.sleep(6000);
		PresentTitle = getDriver().findElement(By.xpath("(//div[@class='consumptionMetaDiv']//h1)")).getText();

		System.out.println("Show Title : " + PresentTitle);
		String currentDuration2 = getText(PWAPlayerPage.objcurrenttime);
		System.out.println("Current time after appending timedperiod in URL : " + currentDuration2);

		if (timeToSec(currentDuration2) >= timeperiod2) {
			System.out.println("Playback started from Appended time");
			extent.extentLogger("Playback started from Appended time", "Playback started from Appended time");
		} else {
			System.out.println("Playback not started from Appended time.");
			extent.extentLoggerFail("Playback not started from Appended time",
					"Playback not started from Appended time");
		}
	}

	public void moviesTimeperiodProvided(String userType) throws Exception {
		/*
		 * // Verify whether playback for content(wrt movies) starts playing based on
		 * the timeperiod provided in the URL
		 */
		extent.HeaderChildNode(
				"Verify whether playback for content(wrt movies) starts playing based on the timeperiod provided in the URL");
		int timeperiod3 = 120;
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("timedAnchorMovie");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		String currenturl = getDriver().getCurrentUrl();
		String timedurl = currenturl + "?t=" + timeperiod3 + "";
		System.out.println("Appending time period");
		extent.extentLogger("timeperiod", "Appending time period");
		getDriver().get(timedurl);
		System.out.println("Hit URL : " + timedurl);
		extent.extentLogger("", "Hit URL : " + timedurl);
		// getDriver().get("https://newpwa.zee5.com/movies/details/action-film-starring-puneeth-rajkumar/0-0-2677?t="+timeperiod3+"");
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Sign Up pop up")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Sign Up Pop Up close button");

			}
		}

		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
				verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");

			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}

		waitForPlayerAdToComplete("Video Player");
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Sign Up pop up")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Sign Up Pop Up close button");

			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
				verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		Thread.sleep(3000);

		String PresentTitle = getDriver().findElement(By.xpath("(//div[@class='consumptionMetaDiv']//h1)")).getText();

		String currentDuration3 = getText(PWAPlayerPage.objcurrenttime);
		System.out.println("Current time after appending timedperiod in URL : " + currentDuration3);

		if (timeToSec(currentDuration3) >= timeperiod3) {
			System.out.println("Playback started from Appended time");
			extent.extentLogger("Playback started from Appended time", "Playback started from Appended time");
		} else {
			System.out.println("Playback not started from Appended time.");
			extent.extentLogger("Playback not started from Appended time", "Playback not started from Appended time");
		}

		Thread.sleep(3000);
		extent.HeaderChildNode("Timed Anchor for Maximum time");
		System.out.println("Verifying Timed Anchor for Maximum time");
		extent.extentLogger("", "Verifying Timed Anchor for Maximum time");
		// append max time
		int maxtime = 9000;
		timedurl = currenturl + "?t=" + maxtime + "";
		System.out.println("Appending time period");
		extent.extentLogger("timeperiod", "Appending time period");
		getDriver().get(timedurl);
		System.out.println("Hit URL : " + timedurl);
		extent.extentLogger("", "Hit URL : " + timedurl);
		// getDriver().get("https://newpwa.zee5.com/movies/details/action-film-starring-puneeth-rajkumar/0-0-2677?t=9000");
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Sign Up pop up")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Sign Up Pop Up close button");

			}
		}
		waitForPlayerAdToComplete("Video Player");
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Sign Up pop up")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Sign Up Pop Up close button");

			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
				verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");

			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
				verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");

			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objSubscribeNowLink, "Subscriptionlink")) {
				System.out.println("Subscribe now link");
			}
		} else {
			pausePlayer();
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
				verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		Thread.sleep(3000);
		String presentTitle2 = getDriver().findElement(By.xpath("(//div[@class='consumptionMetaDiv']//h1)")).getText();

		if (!PresentTitle.equals(presentTitle2)) {
			System.out.println("Navigated to other Player as the time appended is max ");
			extent.extentLogger("Navigated to other Player as the time appended is max ",
					"Navigated to other Player as the time appended is max ");
		} else {
			System.out.println("Not Navigated to other Player");
			extent.extentLogger("Not Navigated to other Player", "Not Navigated to other Player");
		}
		// getDriver().get("https://newpwa.zee5.com/movies/details/doddmane-hudga/0-0-2677?t=60");
		String url = getParameterFromXML("url");
		String urlToHit = url + "kids/kids-movies/robin-hood-enemies-forever/0-0-103560?t=60";
		getDriver().get(urlToHit);
		Thread.sleep(5000);
	}

	public void continueWatchingtrayData(String userType) {
		/*
		 * //Verifying ContinueWatching Tray Data
		 */

		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			extent.HeaderChildNode("Verifying ContinueWatching Tray Data");
			try {
				// ScrollToTheElementWEB(By.xpath("//div[@class='trayHeader']//h2[contains(text(),'Continue
				// Watching')]"));
				Thread.sleep(2000);
				click(PWAHomePage.objspecificTumbnail1("Continue Watching", 1), "Continue watching content");
				if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton,
							"Subscribe Pop Up Close button");
					extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
				} else {
					extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
				}
				waitForPlayerAdToComplete("Video Player");
				pausePlayer();
				String currentDuration4 = getText(PWAPlayerPage.objcurrenttime);
				System.out.println("Current time  : " + currentDuration4);
				String getUrl = getDriver().getCurrentUrl();
				int timeperiod4 = 120;
				String modifiedURL = getUrl + "?t=" + timeperiod4 + "";
				getDriver().get(modifiedURL);
				waitTime(5000);
				getDriver().findElement(By.xpath("//div[contains(@class,'playkit-spinner')]"));
				waitForPlayerAdToComplete("Video Player");
				pausePlayer();
				String currentDuration5 = getText(PWAPlayerPage.objcurrenttime);
				System.out.println("Current time after appending timedperiod in URL : " + currentDuration5);
				if (timeToSec(currentDuration5) >= timeperiod4) {
					System.out.println("Playback started from Appended time");
					extent.extentLogger("Playback started from Appended time", "Playback started from Appended time");
				} else {
					System.out.println("Playback not started from Appended time.");
					extent.extentLoggerFail("Playback not started from Appended time",
							"Playback not started from Appended time");
				}
			} catch (Exception e) {
				System.out.println("No Continue Watching Tray");
			}
		}
	}

	/** ==========================TANISHA -RECO MODULE========================== */
	/**
	 * Main method for validating Recommendations (Talamoos) module
	 */
	public void verificationOfRecoTalamoos(String userType) throws Exception {
		// playAnyContentAndVerifyTrendingOnZee5Tray();
		// String contentLangs = allSelectedLanguages();
		if (userType.equals("Guest")) {
			// playContentsToTriggerRecoApi(userType);
			extent.HeaderChildNode("Play different contents to trigger Recommendation API");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			selectLanguages();
			playAContentForReco("Music", getParameterFromXML("musicToTriggerReco"), userType);
			playAContentForReco("News", getParameterFromXML("newsToTriggerReco"), userType);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Home", "Trending on Zee5", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Premium", "Trending Now", true);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Shows", "Trending Shows", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Movies", "Trending Movies", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Music", "Recommended for you", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "News", "Recommended for you", false);
		} else if (userType.equals("NonSubscribedUser")) {
			// playContentsToTriggerRecoApi(userType);
			extent.HeaderChildNode("Play different contents to trigger Recommendation API");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			selectLanguages();
			playAContentForReco("Music", getParameterFromXML("musicToTriggerReco"), userType);
			playAContentForReco("News", getParameterFromXML("newsToTriggerReco"), userType);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Home", "Trending on Zee5", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Premium", "Trending Now", true);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Shows", "Trending Shows", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Movies", "Trending Movies", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Music", "Recommended for you", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "News", "Recommended for you", false);
		} else if (userType.equals("SubscribedUser")) {
			playContentsToTriggerRecoApi(userType);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Home", "Trending on Zee5", true);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Home", "You may also like", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Home", "Recommended for you", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Shows", "Recommended for you", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Premium", "Recommended for you", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Movies", "Recommended for you", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Music", "Recommended for you", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "News", "Recommended for you", false);
			verifyRecoTrayAndPlayContentWithoutAPI(userType, "Home", "Because you watched", true);
		} else {
			logger.error("Incorrect userType passed to method");
			extent.extentLogger("incorrectUser", "Incorrect userType passed to method");
		}
	}

	public String convertCamelCase(String string) {
		String[] arr = string.split(" ");
		String finalString = "";
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1, arr[i].length()).toLowerCase();
			finalString = finalString + arr[i];
			if (i < arr.length - 1)
				finalString = finalString + " ";
		}
		System.out.println(finalString);
		return "";
	}

	public void playContentsToTriggerRecoApi(String userType) throws Exception {
		extent.HeaderChildNode("Play different contents to trigger Recommendation API");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		selectLanguages();
		playAContentForReco("Music", getParameterFromXML("musicToTriggerReco"), userType);
		playAContentForReco("Movie", getParameterFromXML("movieToTriggerReco"), userType);
		playAContentForReco("Episode", getParameterFromXML("episodeToTriggerReco"), userType);
		playAContentForReco("News", getParameterFromXML("newsToTriggerReco"), userType);
	}

	public void selectLanguages() throws Exception {
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language Button");
		waitTime(2000);
		waitForElementAndClick(PWAHamburgerMenuPage.objContentLanguageBtn, 2, "Content Languages");
		waitTime(2000);
		unselectAllContentLanguages();
		clickElementWithLocator(PWAHamburgerMenuPage.objUnselectedKannadaContentLanguage);
		clickElementWithLocator(PWAHamburgerMenuPage.objUnselectedHindiContentLanguage);
		clickElementWithLocator(PWAHamburgerMenuPage.objUnselectedEnglishContentLanguage);
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
		waitTime(3000);
	}

	public void selectEnglishContentLanguage() throws Exception {
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language Button");
		waitTime(2000);
		waitForElementAndClick(PWAHamburgerMenuPage.objContentLanguageBtn, 2, "Content Languages");
		waitTime(2000);
		unselectAllContentLanguages();
		clickElementWithLocator(PWAHamburgerMenuPage.objUnselectedEnglishContentLanguage);
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
		waitTime(3000);

	}

	public void unselectAllContentLanguages() throws Exception {
		List<WebElement> selectedLanguages = getDriver().findElements(PWAHamburgerMenuPage.objSelectedLanguages);
		for (int i = 0; i < selectedLanguages.size(); i++) {
			clickElementWithWebElement(selectedLanguages.get(i));
		}
	}

	public void playAContentForReco(String contentType, String searchKey, String userType) throws Exception {
		logger.info("Playing content to initiate Reco API: " + contentType);
		extent.extentLogger("contentplay", "Playing content to initiate Reco API: " + contentType);
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, searchKey + "\n\n", "Search Edit box");
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(searchKey), 10, "Search Result");
		String contentPlayed = "";

		for (int i = 0; i < 2; i++) {
			try {
				waitTime(5000);
				click(PWASearchPage.objSearchedResult(searchKey), "Search Result");
				break;
			} catch (StaleElementReferenceException e) {
			}
		}

		if (waitForElementPresence(PWAPlayerPage.objContentTitleInConsumptionPage, 30, "Player screen")) {
			contentPlayed = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
			logger.info("Content played: " + contentPlayed);
			extent.extentLogger("contentPlayed", "Content played: " + contentPlayed);
			waitForPlayerAdToComplete("Video Player");
			logger.info("Playing content for some time to trigger Reco API");
			extent.extentLogger("contentPlayed", "Playing content for some time to trigger Reco API");
			waitTime(30000);
			waitTime(30000);
		}
	}

	public void verifyRecoTrayAndPlayContentWithoutAPI1(String userType, String tabName, String recoTrayTitle,
			boolean verifyContentDetails) throws Exception {
		extent.HeaderChildNode(tabName + " tab: Validation of \"" + recoTrayTitle + "\" tray");
		boolean trayFoundInUI = false;
		waitForElementAndClickIfPresent(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, 2, "Back to Top");
		if (navigatetoAnyScreen(tabName)) {
			trayFoundInUI = swipeTillElement(15, PWALandingPages.objTrayTitleInUIContains(recoTrayTitle),
					"\"" + recoTrayTitle + "\" tray");
			if (trayFoundInUI == true) {
				extent.HeaderChildNode(tabName + " tab: Validation of content play in \"" + recoTrayTitle + "\" tray");
				swipeTillElementAndClick(3, PWALandingPages.objFirstAssetInTrayIndex(recoTrayTitle),
						"First Asset in tray");
				String nextPageTitle = "";
				if (waitForElementPresence(PWAShowsPage.objShowsTitle, 2, "Shows Details page")) {
					nextPageTitle = getText(PWAShowsPage.objShowsTitle);
				} else if (waitForElementPresence(PWAPlayerPage.objContentTitleInConsumptionPage, 2, "Player screen")) {
					nextPageTitle = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
				}
				if (!nextPageTitle.equals("")) {
					logger.info("Navigated to the consumption/details page: \"" + nextPageTitle + "\"");
					extent.extentLogger("playerScreen",
							"Navigated to the consumption/details page: \"" + nextPageTitle + "\"");
					waitForElementAndClickIfPresent(PWASubscriptionPages.objPopupCloseButton, 5,
							"Close in Sign Up Pop Up");
					String contentURL = getDriver().getCurrentUrl();
					String[] abc = contentURL.split("/");
					String contentID = abc[abc.length - 1];
					System.out.println("ContentID fetched from URL: " + contentID);
					logger.info("ContentID fetched from URL: " + contentID);
					extent.extentLogger("", "ContentID fetched from URL: " + contentID);
					if (verifyContentDetails)
						verifyRecoTraysFromDetailsPage(userType, contentID);
				} else {
					logger.error("Failed to navigate to consumption/details page: \"" + nextPageTitle + "\"");
					extent.extentLoggerFail("playerScreen",
							"Failed to navigate to consumption/details page: \"" + nextPageTitle + "\"");
				}
			}
		}
	}

	public void verifyRecoTrayAndPlayContentWithoutAPI(String userType, String tabName, String recoTrayTitle,
			boolean verifyContentDetails) throws Exception {
		extent.HeaderChildNode(tabName + " tab: Validation of \"" + recoTrayTitle + "\" tray");
		boolean trayFoundInUI = false;
		if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
			click(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
		}
		click(PWAHomePage.objZeeLogo, "Zee logo");
		if (navigatetoAnyScreen(tabName)) {
			int noOfSwipes = 10;
			String trayTitleUI = "";
			boolean foundtrayInPage = false;
			for (int i = 0; i <= noOfSwipes; i++) {
				List<WebElement> trays = findElements(PWALandingPages.objTrayTitle);
				for (int j = 0; j < trays.size(); j++) {
					try {
						trayTitleUI = trays.get(j).getAttribute("innerText");
						if (trayTitleUI.toLowerCase().contains(recoTrayTitle.toLowerCase())) {
							logger.info("Reco tray is present in page");
							extent.extentLogger("recoPresent", "Reco tray is present in page");
							foundtrayInPage = true;
							break;
						}
					} catch (Exception e) {
					}
				}
				if (foundtrayInPage == false) {
					PartialSwipe("UP", 1);
					waitTime(5000);
				} else {
					break;
				}
			}
			if (foundtrayInPage == true) {
				trayFoundInUI = swipeTillElement(15, PWALandingPages.objTrayTitleInUIContains(trayTitleUI),
						"\"" + trayTitleUI + "\" tray");
				if (trayFoundInUI == true) {
					// handle mandatory pop up
					mandatoryRegistrationPopUp(userType);
					extent.HeaderChildNode(
							tabName + " tab: Validation of content play in \"" + trayTitleUI + "\" tray");
					swipeTillElementAndClick(3, PWALandingPages.objFirstAssetInTrayIndex(trayTitleUI),
							"First Asset in tray");
					String nextPageTitle = "";
					waitTime(5000);
					if (verifyIsElementDisplayed(PWAShowsPage.objShowsTitle, "Shows Details page")) {
						nextPageTitle = getText(PWAShowsPage.objShowsTitle);
					} else {
						/*
						 * if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle,
						 * "Subscribe Pop Up")) { click(PWASubscriptionPages.objPopupCloseButton,
						 * "Subscribe Pop Up Close button"); } if(tabName.equalsIgnoreCase("music")) {
						 * System.out.println("ad check for music");
						 * waitForPlayerAdToComplete("Video Player"); }
						 * if(tabName.equalsIgnoreCase("news")) {
						 * System.out.println("ad check for news");
						 * waitForPlayerAdToComplete("Live Player"); } pausePlayer();
						 */
						nextPageTitle = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
					}
					if (!nextPageTitle.equals("")) {
						logger.info("Navigated to the consumption/details page: \"" + nextPageTitle + "\"");
						extent.extentLogger("playerScreen",
								"Navigated to the consumption/details page: \"" + nextPageTitle + "\"");
						String contentURL = getDriver().getCurrentUrl();
						String[] abc = contentURL.split("/");
						String contentID = abc[abc.length - 1];
						// System.out.println("contentID fetched from URL: " + contentID);
						if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
							click(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
						}
						if (verifyContentDetails)
							verifyRecoTraysFromDetailsPage(userType, contentID);
					} else {
						logger.error("Failed to navigate to consumption/details page: \"" + nextPageTitle + "\"");
						extent.extentLoggerFail("playerScreen",
								"Failed to navigate to consumption/details page: \"" + nextPageTitle + "\"");
					}
				}
			} else {
				logger.error("Reco tray " + recoTrayTitle + " is not present  in " + tabName + " page");
				extent.extentLoggerFail("trayabsent",
						"Reco tray " + recoTrayTitle + " is not present in " + tabName + " page");
				click(PWAHomePage.objBackToTopArrow, "Back to Top Arrow");
			}
		}
	}

	/**
	 * Validation of Recommendation tray and playing content from recommendation
	 * tray
	 */
	@SuppressWarnings("unused")
	public void verifyRecoTrayAndPlayContent(String userType, String tabName, String recoTrayTitle, String contentLangs,
			boolean verifyContentDetails) throws Exception {
		extent.HeaderChildNode(tabName + " tab: Validation of \"" + recoTrayTitle + "\" tray");
		String trayTitleInUI = "";
		boolean detailsScreenFoundInUI = false;
		String recoTrayTitleAPI = "", firstAssetTitleAPI = "", firstAssetTypeAPI = "", firstAssetID = "";
		ArrayList<Integer> xyOfTray = new ArrayList<Integer>();
		waitTime(10000);
		Response recoResp = ResponseInstance.getRecoDataFromTab(userType, tabName, contentLangs);
		ArrayList<String> trayDetails = returnRecoTrayFirstAssetDetails(recoResp, recoTrayTitle);
		if (trayDetails != null) {
			recoTrayTitleAPI = trayDetails.get(0);
			firstAssetTitleAPI = trayDetails.get(1);
			firstAssetTypeAPI = trayDetails.get(2);
			firstAssetID = trayDetails.get(3);
			waitForElementAndClickIfPresent(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, 2, "Back to Top");
			if (navigatetoAnyScreen(tabName)) {
				trayTitleInUI = swipeTillTray(7, recoTrayTitleAPI, "\"" + recoTrayTitleAPI + "\" tray");
				Swipe("UP", 1);
				Thread.sleep(5000);
			}
		}
		if (!trayTitleInUI.equals("")) {
			extent.HeaderChildNode(tabName + " tab: Validation of content play in \"" + recoTrayTitle + "\" tray");
			if (verifyContentPlayFromRecoTray(userType, trayTitleInUI, firstAssetTitleAPI, firstAssetTypeAPI)) {
				if (verifyContentDetails)
					verifyRecoTraysFromDetailsPage(userType, firstAssetID);
			} else {
				logger.error("Failed to validate the Reco APIs in consumption/details page");
				extent.extentLoggerFail("recoInDetailsFailed",
						"Failed to validate the Reco APIs in consumption/details page");
			}
		}
	}

	public boolean swipeTillElement(int noOfSwipes, By locator, String message) throws Exception {
		for (int i = 0; i <= noOfSwipes; i++) {
			if (waitForElementPresence(locator, 1, message)) {
				return true;
			} else {
				PartialSwipe("UP", 1);
				waitTime(5000);
				if (i == noOfSwipes) {
					logger.error(message + " is not displayed");
					extent.extentLoggerFail("failedToLocate", message + " is not displayed");
				}
			}
		}
		return false;
	}

	public String swipeTillTray(int noOfSwipes, String trayTitle, String message) throws Exception {
		boolean foundTray = false;
		for (int i = 0; i <= noOfSwipes; i++) {
			String Traytitle = trayTitle;
			if (verifyIsElementDisplayed(PWALandingPages.objTrayTitleInUI(Traytitle), Traytitle)) {
				System.out.println(Traytitle + " tray is displayed in show detail page");
				extent.extentLogger("", Traytitle + " tray is displayed in show detail page");
				foundTray = true;
				break;
			} else {
				Swipe("UP", 1);
			}
		}

		if (foundTray == true) {
			extent.extentLogger("", trayTitle + " Reco tray is displayed in show detail page");
			logger.info("Reco tray is displayed in show detail page");
		} else {
			extent.extentLoggerFail("", trayTitle + "Reco tray is not displayed in show detail page");
			logger.error("Reco tray is not displayed in show detail page");
		}
//		main: for (int i = 0; i <= noOfSwipes; i++) {
//			ArrayList<WebElement> trays = new ArrayList<WebElement>();
//			trays = (ArrayList<WebElement>) getDriver().findElements(PWALandingPages.objTrayTitle);
//			for (int j = 0; j < trays.size(); j++) {
//				if (trays.get(j).getAttribute("innerText").equalsIgnoreCase(trayTitle)) {
//					trayTitleInUI = trays.get(j).getText();
//					foundTray = true;
//					break main;
//				}
//			}
//			PartialSwipe("UP", 1);
//			waitTime(2000);
//			if (i == noOfSwipes) {
//				logger.error(message + " is not displayed");
//				extent.extentLoggerFail("failedToLocate", message + " is not displayed");
//			}
//		}
//		if (foundTray == true) {
//			for (int i = 0; i <= noOfSwipes; i++) {
//				if (waitForElementPresence(PWALandingPages.objTrayTitleInUI(trayTitleInUI), 1,
//						trayTitleInUI + " tray")) {
//					return trayTitleInUI;
//				} else {
//					PartialSwipe("UP", 1);
//					waitTime(2000);
//					if (i == noOfSwipes) {
//						logger.error(message + " is not displayed");
//						extent.extentLoggerFail("failedToLocate", message + " is not displayed");
//					}
//				}
//			}
//		}
		return "";
	}

	/**
	 * Function to return reco tray title and first asset title
	 */
	public ArrayList<String> returnRecoTrayFirstAssetDetails(Response response, String requiredTray) {
		ArrayList<String> trayTitleAndFirstContent = new ArrayList<String>();
		boolean found = false;
		int numberOfTrays = 0;
		String trayTitleAPI = "", firstAssetTitleAPI = "", firstAssetTypeAPI = "", firstAssetID = "";
		try {
			numberOfTrays = response.jsonPath().get("buckets.size()");
		} catch (Exception e) {
			logger.error("API error observed");
			extent.extentLoggerFail("apValue", "API error observed");
			return null;
		}
		for (int trays = 0; trays < numberOfTrays; trays++) {
			if (response.jsonPath().get("buckets[" + trays + "].title").toString().toLowerCase()
					.contains(requiredTray.toLowerCase())) {
				trayTitleAPI = response.jsonPath().get("buckets[" + trays + "].title").toString();
				logger.info("Reco Tray fetched from API: \"" + trayTitleAPI + "\"");
				extent.extentLogger("apValue", "Reco Tray fetched from API: \"" + trayTitleAPI + "\"");

				firstAssetTitleAPI = response.jsonPath().get("buckets[" + trays + "].items[0].title").toString();
				logger.info("First Asset Title fetched from API: \"" + firstAssetTitleAPI + "\"");
				extent.extentLogger("apValue", "First Asset Title fetched from API: \"" + firstAssetTitleAPI + "\"");

				firstAssetTypeAPI = response.jsonPath().get("buckets[" + trays + "].items[0].asset_subtype").toString();
				logger.info("First Asset Type fetched from API: \"" + firstAssetTypeAPI + "\"");
				extent.extentLogger("apValue", "First Asset Type fetched from API: \"" + firstAssetTypeAPI + "\"");

				firstAssetID = response.jsonPath().get("buckets[" + trays + "].items[0].id").toString();
				logger.info("First Asset ID fetched from API: \"" + firstAssetID + "\"");
				extent.extentLogger("apValue", "First Asset ID fetched from API: \"" + firstAssetID + "\"");

				found = true;
				break;
			}
		}
		if (found == false) {
			logger.info("Required tray details not present Reco Response");
			extent.extentLogger("apValue", "Required tray details not present Reco Response");
			return null;
		}
		trayTitleAndFirstContent.add(trayTitleAPI);
		trayTitleAndFirstContent.add(firstAssetTitleAPI);
		trayTitleAndFirstContent.add(firstAssetTypeAPI);
		trayTitleAndFirstContent.add(firstAssetID);
		return trayTitleAndFirstContent;
	}

	public void verifyRecoTraysFromDetailsPage(String userType, String firstAssetID) throws Exception {
		extent.HeaderChildNode(
				"Detail/Consumption screen: Validation of Reco trays and playing content from Reco tray");
		Response recoResp = ResponseInstance.getRecoTraysInDetailsPage(userType, firstAssetID);
		ArrayList<String> recoTraysInDetailsPage = getAllRecoTraysFromDetails(recoResp);
		String trayTitleUI = "";
		for (int tray = 0; tray < recoTraysInDetailsPage.size(); tray++) {
			String trayTitleAPI = recoTraysInDetailsPage.get(tray);
			trayTitleUI = swipeTillTray(10, trayTitleAPI, "\"" + trayTitleAPI + "\" tray");
			if (tray == (recoTraysInDetailsPage.size() - 1) && !trayTitleUI.equals("")) {// Verify content play for one
																							// reco tray in content
																							// details
				PartialSwipe("UP", 1);
				String title = getElementPropertyToString("data-minutelytitle",
						PWALandingPages.objFirstAssetInTrayTitle(trayTitleUI), "First Asset title").toString();
				// handle mandatory pop up
				mandatoryRegistrationPopUp(userType);
				waitForElementAndClick(PWALandingPages.objFirstAssetInTrayIndex(trayTitleAPI), 5,
						"First asset " + title);
				String nextPageTitle = "";
				if (waitForElementPresence(PWAShowsPage.objShowsTitle, 2, "Shows Details page")) {
					nextPageTitle = getText(PWAShowsPage.objShowsTitle);
				} else if (waitForElementPresence(PWAPlayerPage.objContentTitleInConsumptionPage, 2, "Player screen")) {
					nextPageTitle = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
				}
				if (nextPageTitle.equals(title)) {
					logger.info("Navigated to the correct consumption/details page: \"" + title + "\"");
					extent.extentLogger("playerScreen",
							"Navigated to the correct consumption/details page: \"" + title + "\"");
				} else {
					logger.error("Failed to navigate to consumption/details page: \"" + title + "\"");
					extent.extentLoggerFail("playerScreen",
							"Failed to navigate to consumption/details page: \"" + title + "\"");
				}
				if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe popup")) {
					click(PWASubscriptionPages.objPopupCloseButton, "Close button of Pop Up");
				}
				verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee logo");
			}
		}
	}

	public ArrayList<String> getAllRecoTraysFromDetails(Response response) {
		int numberOfTrays = 0;
		ArrayList<String> recoTrays = new ArrayList<String>();
		try {
			numberOfTrays = response.jsonPath().get("buckets.size()");
		} catch (Exception e) {
			logger.error("API error observed");
			extent.extentLoggerFail("apValue", "API error observed");
			return null;
		}
		for (int tray = 0; tray < numberOfTrays; tray++) {
			recoTrays.add(response.jsonPath().get("buckets[" + tray + "].title").toString());

		}
		logger.info("Reco trays in details page fetched from API: " + recoTrays);
		extent.extentLogger("apValue", "Reco trays in details page fetched from API: " + recoTrays);
		return recoTrays;
	}

	public boolean verifyContentPlayFromRecoTray(String userType, String recoTrayTitleAPI, String firstAssetTitleAPI,
			String firstAssetTypeAPI) throws Exception {
		if (swipeTillElementAndClick(2, PWALandingPages.objFirstAssetInTray(recoTrayTitleAPI, firstAssetTitleAPI),
				"First Asset \"" + firstAssetTitleAPI + "\"")) {
			String nextPageTitle = "";
			if (firstAssetTypeAPI.equals("tvshow")) {
				if (waitForElementPresence(PWAShowsPage.objShowsTitle, 30, "Shows Details page"))
					nextPageTitle = getText(PWAShowsPage.objShowsTitle);
			} else {
				if (waitForElementPresence(PWAPlayerPage.objContentTitleInConsumptionPage, 30, "Player screen"))
					nextPageTitle = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
			}
			if (nextPageTitle.equals(firstAssetTitleAPI) && !nextPageTitle.equals("")) {
				logger.info("Navigated to the correct consumption/details page: \"" + nextPageTitle + "\"");
				extent.extentLogger("playerScreen",
						"Navigated to the correct consumption/details page: \"" + nextPageTitle + "\"");
				if (!userType.equals("SubscribedUser")) {
					waitForElementAndClickIfPresent(PWASubscriptionPages.objPopupCloseButton, 5,
							"Close in Subscribe Pop Up");
				}
				return true;
			} else {
				logger.error("Navigated to the incorrect consumption/details page: \"" + nextPageTitle + "\"");
				extent.extentLoggerFail("playerScreen",
						"Navigated to the incorrect consumption/details page: \"" + nextPageTitle + "\"");
				if (!userType.equals("SubscribedUser")) {
					waitForElementAndClickIfPresent(PWASubscriptionPages.objPopupCloseButton, 5,
							"Close in Subscribe Pop Up");
				}
				return false;
			}
		} else
			return false;
	}

	public boolean swipeTillElementAndClick(int noOfSwipes, By locator, String message) throws Exception {
		for (int i = 0; i <= noOfSwipes; i++) {
			if (waitforElementAndClickIfPresent(locator, 5, message))
				return true;
			else {
				Swipe("UP", 1);
				waitTime(3000);
				if (i == noOfSwipes) {
					logger.error("Failed to locate and click on " + message);
					extent.extentLoggerFail("failedToLocate", "Failed to locate and click on " + message);
				}
			}
		}
		return false;
	}

	/**
	 * The method s for element and clicks if present. Returns no error if not
	 * present. Implemented for random popups
	 */
	public boolean waitforElementAndClickIfPresent(By locator, int seconds, String message)
			throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				getDriver().findElement(locator).click();
				logger.info("Clicked on " + message);
				extent.extentLogger("clickedElement", "Clicked on " + message);
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		return false;
	}

	public boolean waitForElementPresence(By locator, int seconds, String message) throws InterruptedException {
		for (int i = 0; i <= seconds; i++) {
			try {
				getDriver().findElement(locator).isDisplayed();
				logger.info("" + message + " is displayed");
				extent.extentLogger("elementDisplayed", "" + message + " is displayed");
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		return false;
	}

	/**
	 * Function Navigate to landing page of any screen
	 * 
	 * @throws Exception
	 */

	public boolean navigatetoAnyScreen(String screen) throws Exception {
		for (int i = 0; i < 3; i++) {
			try {
				getDriver().findElement(PWAHomePage.objTabName(screen)).click();
				logger.info("Clicked on Tab " + screen);
				extent.extentLogger("tabClick", "Clicked on Tab " + screen);
				return true;
			} catch (Exception e) {
				Actions act = new Actions(getDriver());
				List<WebElement> ele = getDriver().findElements(PWAHomePage.objAllTabs);
				for (int tab = 0; tab < ele.size(); tab++) {
					act.dragAndDropBy(ele.get(tab), 25, 0);
					try {
						getDriver().findElement(PWAHomePage.objTabName(screen)).click();
						logger.info("Clicked on Tab " + screen);
						extent.extentLogger("tabClick", "Clicked on Tab " + screen);
						return true;
					} catch (Exception e1) {
					}
				}
				for (int tab = 0; tab < ele.size(); tab++) {
					act.dragAndDropBy(ele.get(tab), -25, 0);
					try {
						getDriver().findElement(PWAHomePage.objTabName(screen)).click();
						logger.info("Clicked on Tab " + screen);
						extent.extentLogger("tabClick", "Clicked on Tab " + screen);
						return true;
					} catch (Exception e2) {
					}
				}
			}
		}
		logger.error("Failed to click on Tab " + screen);
		extent.extentLoggerFail("tabClick", "Failed to click on Tab " + screen);
		return false;
	}

	/**
	 * ======================================VINAY USER ACTION
	 * MODULE==========================
	 */

	/* =======User Action module ---> My Watchlist section for subscribed user */
	public void MyWatchlistSubscribedUser() throws Exception {
		extent.HeaderChildNode("User Actions : Add To Watchlist functionality validations");
		// Select language
		selectLanguages();
		String episode1 = "Amulya joins the gym - Gattimela";
		String episode2 = "Alia learns about Pragya - Kumkum Bhagya";
		String movie1 = "Robin Hood Forever Enemies";
		String movie2 = "Simba Junior: In New York";
		String video1 = "Kill Marry Hookup with ZEE5 Stars";
		String video2 = "Dance like a man - Trailer";
		// Make sure all watch listed items are removed
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Humburger Menu");
		click(PWAHamburgerMenuPage.objMyAccount, "My Account");
		click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist");
		if (verifyIsElementDisplayed(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all button")) {
			click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all button");
			extent.extentLogger("", "Cleared Watchlist tray");
			logger.info("Cleared Watchlist tray");
		} else {
			extent.extentLogger("", "No items in Watchlist tray");
			logger.info("No items in Cleared Watchlist tray");
		}
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		// Episode 1
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, episode1 + "\n", "Search Edit box: " + episode1);
		verifyElementPresentAndClick(PWASearchPage.objSearchEpisodesTab, "Episodes tab");
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(episode1), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(episode1), "Search Result");
		waitTime(3000);
		if (checkElementDisplayed(PWAPlayerPage.watchListBtnNotAdded, "Add to Watchlist")) {
			click(PWAPlayerPage.watchListBtnNotAdded, "Add to Watch list");
		} else {
			if (checkElementDisplayed(PWAPlayerPage.watchListBtnAlreadyAdded, "Added to Watchlist")) {
				extent.extentLogger("", "Content is already added to Watchlist");
				logger.info("Content is already added to Watchlist");
			}
		}
		// Episode 2
		// handle mandatory pop up
		mandatoryRegistrationPopUp(user);
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, episode2 + "\n", "Search Edit box: " + episode2);
		verifyElementPresentAndClick(PWASearchPage.objSearchEpisodesTab, "Episodes tab");
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(episode2), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(episode2), "Search Result");
		waitTime(3000);
		if (verifyIsElementDisplayed(PWAPlayerPage.watchListBtnNotAdded, "Add to Watchlist")) {
			click(PWAPlayerPage.watchListBtnNotAdded, "Add to Watch list");
		} else {
			if (verifyIsElementDisplayed(PWAPlayerPage.watchListBtnAlreadyAdded, "Added to Watchlist")) {
				extent.extentLogger("", "Content is already added to Watchlist");
				logger.info("Content is already added to Watchlist");
			}
		}
		// Movie 1
		// handle mandatory pop up
		mandatoryRegistrationPopUp(user);
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, movie1 + "\n", "Search Edit box: " + movie1);
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(movie1), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(movie1), "Search Result");
		waitTime(3000);
		if (verifyIsElementDisplayed(PWAPlayerPage.watchListBtnNotAdded, "Add to Watchlist")) {
			click(PWAPlayerPage.watchListBtnNotAdded, "Add to Watch list");
		} else {
			if (verifyIsElementDisplayed(PWAPlayerPage.watchListBtnAlreadyAdded, "Added to Watchlist")) {
				extent.extentLogger("", "Content is already added to Watchlist");
				logger.info("Content is already added to Watchlist");
			}
		}
		// Movie 2
		// handle mandatory pop up
		mandatoryRegistrationPopUp(user);
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, movie2 + "\n", "Search Edit box: " + movie2);
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(movie2), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(movie2), "Search Result");
		waitTime(3000);
		if (verifyIsElementDisplayed(PWAPlayerPage.watchListBtnNotAdded, "Add to Watchlist")) {
			click(PWAPlayerPage.watchListBtnNotAdded, "Add to Watch list");
		} else {
			if (verifyIsElementDisplayed(PWAPlayerPage.watchListBtnAlreadyAdded, "Added to Watchlist")) {
				extent.extentLogger("", "Content is already added to Watchlist");
				logger.info("Content is already added to Watchlist");
			}
		}
		// Video 1
		// handle mandatory pop up
		mandatoryRegistrationPopUp(user);
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, video1 + "\n", "Search Edit box: " + video1);
		verifyElementPresentAndClick(PWASearchPage.objSearchVideosTab, "Videos tab");
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(video1), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(video1), "Search Result");
		waitTime(3000);
		if (verifyIsElementDisplayed(PWAPlayerPage.watchListBtnNotAdded, "Add to Watchlist")) {
			click(PWAPlayerPage.watchListBtnNotAdded, "Add to Watch list");
		} else {
			if (verifyIsElementDisplayed(PWAPlayerPage.watchListBtnAlreadyAdded, "Added to Watchlist")) {
				extent.extentLogger("", "Content is already added to Watchlist");
				logger.info("Content is already added to Watchlist");
			}
		}
		// Video 2
		// handle mandatory pop up
		mandatoryRegistrationPopUp(user);
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, video2 + "\n", "Search Edit box: " + video2);
		verifyElementPresentAndClick(PWASearchPage.objSearchVideosTab, "Videos tab");
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(video2), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(video2), "Search Result");
		waitTime(3000);
		if (verifyIsElementDisplayed(PWAPlayerPage.watchListBtnNotAdded, "Add to Watchlist")) {
			click(PWAPlayerPage.watchListBtnNotAdded, "Add to Watch list");
		} else {
			if (verifyIsElementDisplayed(PWAPlayerPage.watchListBtnAlreadyAdded, "Added to Watchlist")) {
				extent.extentLogger("", "Content is already added to Watchlist");
				logger.info("Content is already added to Watchlist");
			}
		}
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Humburger Menu");
		click(PWAHamburgerMenuPage.objMyAccount, "My Account");
		click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist");
		// Verify Episodes
		// Verify My Watchlist header is displayed
		if (verifyIsElementDisplayed(PWAAddToWatchListPage.objMyWatchlistHeader, "My Watchlist") == true) {
			extent.extentLogger("", "My Watchlist header is displayed");
			logger.info("My Watchlist header text is displayed");
		} else {
			extent.extentLoggerFail("", "My Watchlist header is not displayed");
			logger.error("My Watchlist header is not displayed");
		}
		List<WebElement> episodes = findElements(PWAAddToWatchListPage.objWatchlistedItems);
		int episodesno = episodes.size();
		extent.extentLogger("", "Number of items in Episodes tab is " + episodesno);
		logger.info("Number of items in Episodes tab is " + episodesno);
		if (episodesno == 2) {
			extent.extentLogger("", "Verified that only episodes are displayed in episodes fragment");
			logger.info("Verified that only episodes are displayed in episodes fragment");
		} else {
			extent.extentLoggerFail("", "Episodes displayed in that episodes fragment has failed");
			logger.error("Episodes displayed in episodes fragment has failed");
		}
		// Verify watchlisted episode items
		if (!verifyIsElementDisplayed(PWAAddToWatchListPage.objWatchlistedItem(episode1),
				"Watchlisted episode: " + episode1)) {
			extent.extentLoggerFail("", "Add to watchlist functionality for first Episode " + episode1 + " has failed");
			logger.error("Add to watchlist functionality for first Episode " + episode1 + " has failed");
		}
		if (!verifyIsElementDisplayed(PWAAddToWatchListPage.objWatchlistedItem(episode2),
				"Watchlisted episode: " + episode2)) {
			extent.extentLoggerFail("",
					"Add to watchlist functionality for second Episode " + episode2 + " has failed");
			logger.error("Add to watchlist functionality for second Episode " + episode2 + " has failed");
		}
		// Verify single delete functionality
		verifyElementPresentAndClick(PWAAddToWatchListPage.objWatchlistedItemCancel(episode1),
				"Remove icon for " + episode1);
		if (verifyIsElementDisplayed(PWAAddToWatchListPage.objWatchlistedItem(episode1),
				"Watchlisted episode: " + episode1)) {
			extent.extentLoggerFail("",
					"Remove Watchlisted item functionality for first Episode " + episode1 + " has failed");
			logger.error("Remove Watchlisted item functionality for first Episode " + episode1 + " has failed");
		}
		// Verify remove all functionality
		verifyElementPresentAndClick(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all button");
		if (verifyIsElementDisplayed(PWAAddToWatchListPage.objEmptyWatchlistPage,
				"Empty Watchlist page with 'Uh-Oh! Nothing to watch'")) {
			extent.extentLogger("", "Verified Remove All functionality for Episodes tab");
			logger.info("Verified Remove All functionality for Episodes tab");
		} else {
			extent.extentLoggerFail("", "Remove All functionality has failed for Episodes tab");
			logger.error("Remove All functionality has failed for Episodes tab");
		}
		// Verify Movies
		verifyElementPresentAndClick(PWAAddToWatchListPage.objMoviesTab, "Movies tab in Watchlist page");
		List<WebElement> movies = findElements(PWAAddToWatchListPage.objWatchlistedItems);
		int moviesno = movies.size();
		extent.extentLogger("", "Number of items in Movies tab is " + moviesno);
		logger.info("Number of items in Movies tab is " + moviesno);
		if (moviesno == 2) {
			extent.extentLogger("", "Verified that only movies are displayed in movies fragment");
			logger.info("Verified that only movies are displayed in movies fragment");
		} else {
			extent.extentLoggerFail("", "Movies displayed in movies fragment has failed");
			logger.error("Movies displayed in movies fragment has failed");
		}
		// Verify watchlisted movie items
		if (!verifyIsElementDisplayed(PWAAddToWatchListPage.objWatchlistedItem(movie1),
				"Watchlisted movie: " + movie1)) {
			extent.extentLoggerFail("", "Add to watchlist functionality for first Movie " + movie1 + " has failed");
			logger.error("Add to watchlist functionality for first Movie " + movie1 + " has failed");
		}
		if (!verifyIsElementDisplayed(PWAAddToWatchListPage.objWatchlistedItem(movie2),
				"Watchlisted movie: " + movie2)) {
			extent.extentLoggerFail("", "Add to watchlist functionality for second Movie " + movie2 + " has failed");
			logger.error("Add to watchlist functionality for second Movie " + movie2 + " has failed");
		}
		// Verify single delete functionality
		verifyElementPresentAndClick(PWAAddToWatchListPage.objWatchlistedItemCancel(movie1),
				"Remove icon for " + movie1);
		if (verifyIsElementDisplayed(PWAAddToWatchListPage.objWatchlistedItem(movie1),
				"Watchlisted movie: " + movie1)) {
			extent.extentLoggerFail("",
					"Remove Watchlisted item functionality for first Movie " + movie1 + " has failed");
			logger.error("Remove Watchlisted item functionality for first Movie " + movie1 + " has failed");
		}
		// Verify remove all functionality
		verifyElementPresentAndClick(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all button");
		if (verifyIsElementDisplayed(PWAAddToWatchListPage.objEmptyWatchlistPage,
				"Empty Watchlist page with 'Uh-Oh! Nothing to watch'")) {
			extent.extentLogger("", "Verified Remove All functionality for Movies tab");
			logger.info("Verified Remove All functionality for Movies tab");
		} else {
			extent.extentLoggerFail("", "Remove All functionality has failed for Movies tab");
			logger.error("Remove All functionality has failed for Movies tab");
		}
		// Verify Videos
		verifyElementPresentAndClick(PWAAddToWatchListPage.objVideoTab, "Videos tab in Watchlist page");
		List<WebElement> videos = findElements(PWAAddToWatchListPage.objWatchlistedItems);
		int videosno = videos.size();
		extent.extentLogger("", "Number of items in Videos tab is " + videosno);
		logger.info("Number of items in Videos tab is " + videosno);
		if (videosno == 2) {
			extent.extentLogger("", "Verified that only videos are displayed in videos fragment");
			logger.info("Verified that only videos are displayed in videos fragment");
		} else {
			extent.extentLoggerFail("", "Videos displayed in videos fragment has failed");
			logger.error("Videos displayed in videos fragment has failed");
		}
		// Verify watchlisted video items
		if (!verifyIsElementDisplayed(PWAAddToWatchListPage.objWatchlistedItem(video1),
				"Watchlisted video: " + video1)) {
			extent.extentLoggerFail("", "Add to watchlist functionality for first Video " + video1 + " has failed");
			logger.error("Add to watchlist functionality for first Video " + video1 + " has failed");
		}
		if (!verifyIsElementDisplayed(PWAAddToWatchListPage.objWatchlistedItem(video2),
				"Watchlisted video: " + video2)) {
			extent.extentLoggerFail("", "Add to watchlist functionality for second Video " + video2 + " has failed");
			logger.error("Add to watchlist functionality for second Video " + video2 + " has failed");
		}
		// Verify single delete functionality
		verifyElementPresentAndClick(PWAAddToWatchListPage.objWatchlistedItemCancel(video1),
				"Remove icon for " + video1);
		if (verifyIsElementDisplayed(PWAAddToWatchListPage.objWatchlistedItem(video1),
				"Watchlisted video: " + video1)) {
			extent.extentLoggerFail("",
					"Remove Watchlisted item functionality for first Video " + video1 + " has failed");
			logger.error("Remove Watchlisted item functionality for first Video " + video1 + " has failed");
		}
		// Verify remove all functionality
		verifyElementPresentAndClick(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all button");
		if (verifyIsElementDisplayed(PWAAddToWatchListPage.objEmptyWatchlistPage,
				"Empty Watchlist page with 'Uh-Oh! Nothing to watch'")) {
			extent.extentLogger("", "Verified Remove All functionality for Videos tab");
			logger.info("Verified Remove All functionality for Videos tab");
		} else {
			extent.extentLoggerFail("", "Remove All functionality has failed for Videos tab");
			logger.error("Remove All functionality has failed for Videos tab");
		}
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(user);
	}

	/*
	 * My Reminder section for subscribed user
	 */
	@SuppressWarnings("unused")
	public void MyReminder() throws Exception {
		extent.HeaderChildNode("User Actions : My Reminder functionality validations");
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);

		// Verify user is navigate to EPG section from Live TV
		navigateToAnyScreen("Live TV");
		waitTime(2000);
		// Click on Channel guide
		click(PWALiveTVPage.objChannelGuideToggle, "Channel Guide");

		waitTime(10000);
		waitTime(10000);
		if (findElement(PWALiveTVPage.objChannelGuideToggle).getAttribute("class").contains("active")) {
			extent.extentLogger("Verify Navigation", "User is navigated to EPG section post tapping Channel guide");
			logger.info("User is navigated to EPG section post tapping Channel guide");
		} else {
			extent.extentLoggerFail("Verify Navigation",
					"User did not navigated to EPG section post tapping Channel guide");
			logger.error("User did not navigated to EPG section post tapping Channel guide");
		}
		// Verify EPG section contains Title and the description of the respective show
		if (verifyElementDisplayed(PWALiveTVPage.objShowTitle)) {
			String title = getElementPropertyToString("innerText", PWALiveTVPage.objShowTitle, "Show title");
			extent.extentLogger("Verify Show title", "Show title in EPG section is : " + title);
			logger.info("Show title in EPG section is : " + title);
		} else {
			extent.extentLoggerFail("Verify Show title", "The show title in EPG section is not displayed");
			logger.error("The show title in EPG section is not displayed");

		}
		if (verifyElementDisplayed(PWALiveTVPage.objShowTimeInterval)) {
			String title = getElementPropertyToString("innerText", PWALiveTVPage.objShowTimeInterval,
					"Show time interval");
			extent.extentLogger("Verify Show title", "Show time interval in EPG section is : " + title);
			logger.info("Show time interval in EPG section is : " + title);
		} else {
			extent.extentLoggerFail("Verify Show title", "The show time interval in EPG section is not displayed");
			logger.error("The show time interval in EPG section is not displayed");

		}
		// click on any show
		click(PWALiveTVPage.objFutureChannelInfo, "Future Show");
		if (verifyIsElementDisplayed(PWALiveTVPage.objShowDesc, "Future Show description")) {
			String showDesc = getElementPropertyToString("innerText", PWALiveTVPage.objShowDesc,
					"Future Show description");
			extent.extentLogger("Verify descrption of the show", "The description of the show is :" + showDesc);
			logger.info("The description of the show is :" + showDesc);
		} else {
			extent.extentLoggerFail("Verify descrption of the show", "Show description is not displayed");
			logger.error("Show description is not displayed");
		}
		// Click on close button
		click(PWALiveTVPage.objPopupCloseButton, "Close button");
		// Click on date
		click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
		FilterLanguage("Bengali");
		// Verify Share and Reminder option is available
		click(PWALiveTVPage.objBanglaShow1, "Show detail");
		verifyElementPresent(PWALiveTVPage.objShareOption, "Share option");
		if (verifyElementPresent(PWALiveTVPage.objRemainderButton, "Reminder option for upcoming show")) {
			// Verify user can click on Reminder option
			// click on Reminder option
			click(PWALiveTVPage.objRemainderButton, "Reminder option");
			if (getDriver().findElement(PWALiveTVPage.objRemainderButton).getAttribute("class")
					.contains("btnIconActive")) {
				extent.extentLogger("Verify user can Click on Reminder option", "User can click on Reminder option");
				logger.info("User can click on Reminder option");
			} else {
				extent.extentLoggerFail("Verify user can Click on Reminder option",
						"User can not click on Reminder option");
				logger.error("User can not click on Reminder option");
			}

			// Click on close button
			click(PWALiveTVPage.objPopupCloseButton, "Close button");
			waitTime(3000);
		} else {
			extent.extentLoggerWarning("",
					"Reminder functionality could not be validated because the selected show has no Reminder option");
			logger.info(
					"Reminder functionality could not be validated because the selected show has no Reminder option");
			click(PWALiveTVPage.objCloseLanguagePopuUpBtn, "Close in Pop Up");
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
		// Verify user can not add Movies to Reminder
		// Select any movie
		click(PWALiveTVPage.objMovie, "Movie card");
		if (verifyIsElementDisplayed(PWALiveTVPage.objRemainderButton, "Reminder button for Movie card") == false) {
			extent.extentLogger("Verify Movie show don't have Reminder option",
					"Reminder option is not available for Movie card");
			logger.info("Reminder option is not available for Movie card");
		} else {
			extent.extentLoggerFail("Verify Movie show don't have Reminder option",
					"Reminder option is available for Movie card");
			logger.error("Reminder option is available for Movie card");

		}
		// Click on close button
		click(PWALiveTVPage.objPopupCloseButton, "Close button");
		Swipe("Down", 1);
		getDriver().navigate().refresh();
		click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");

		FilterLanguageUnselectOthers("Hindi", "Hindi Movies");
		waitForElement(PWALiveTVPage.objEPG, 60, "Channels");
		waitTime(10000);
		waitTime(10000);
		Swipe("UP", 1);
		waitTime(10000);
		Swipe("UP", 1);
		waitTime(10000);
		Swipe("UP", 1);
		waitTime(10000);
		String show1 = "", show2 = "";
		// First Show
		if (verifyIsElementDisplayed(PWALiveTVPage.objShowZEETVHDShow1ForReminder, "ZEE TV HD Show")) {
			show1 = getElementPropertyToString("innerText", PWALiveTVPage.objShowZEETVHDShow1ForReminder,
					"ZEE TV HD Show");
			click(PWALiveTVPage.objShowZEETVHDShow1ForReminder, "ZEE TV HD Show " + show1);
			verifyElementPresent(PWALiveTVPage.objShareOption, "Share option");
		}
		if (verifyElementPresent(PWALiveTVPage.objRemainderButton, "Reminder Option")) {
			click(PWALiveTVPage.objRemainderButton, "Reminder option");
			if (getDriver().findElement(PWALiveTVPage.objRemainderButton).getAttribute("class")
					.contains("btnIconActive")) {
				extent.extentLogger("", "Reminder option is active, click has been successful");
				logger.info("Reminder option is active, click has been successful");
			} else {
				extent.extentLoggerFail("", "Reminder option is not active, click has failed");
				logger.error("Reminder option is not active, click has failed");
			}
			click(PWALiveTVPage.objPopupCloseButton, "Close button");
			waitTime(3000);
		} else {
			extent.extentLoggerFail("", "Reminder option is not present for the Show");
			logger.error("Reminder option is not present for the Show");
		}
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		navigateToAnyScreen("Home");
		// Verify user is navigated to Reminder screen from Home page
		String activeTab = getDriver().findElement(PWAHomePage.objActiveTab).getText();
		if (activeTab.equals("Home")) {
			extent.extentLogger("Verify current tab", "User is in " + activeTab + " tab");
			logger.info("User is in Home tab");
		} else {
			extent.extentLoggerFail("Verify current tab", "User is not in Home tab");
			logger.error("User is not in Home tab");
		}
		// Click on Hamburger menu
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		// Click on My account
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyAccount, "My account");
		// Click on My Reminders
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyRemainder, "My Reminder");
		// Verify user is navigated to My Reminder screen
		if (verifyIsElementDisplayed(PWAMyRemindersPage.objMyReminderHeader, "My Reminder Header") == true) {
			extent.extentLogger("Verify the Navigation ",
					"User is Navigated to " + getDriver().findElement(PWAMyRemindersPage.objMyReminderHeader).getText()
							+ " screen from " + activeTab + "page");
			logger.info(
					"User is Navigated to " + getDriver().findElement(PWAMyRemindersPage.objMyReminderHeader).getText()
							+ " screen from " + activeTab + "page");
		} else {
			extent.extentLoggerFail("Verify Navigation ",
					"User failed to navigate from Home page to My Reminders screen");
			logger.error("User failed to navigate from Home page to My Reminders screen");
		}
		// Verify that Remove all button is displayed
		if (verifyIsElementDisplayed(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all option") == true) {
			extent.extentLogger("Verify Remove all option is displayed",
					"Remove all option is displayed in Reminder screen");
			logger.info("Remove all option is displayed in Reminder screen");
		} else {
			extent.extentLoggerFail("Verify Remove all option is displayed",
					"Remove all option is not displayed in Reminder screen");
			logger.error("Remove all option is not displayed in Reminder screen");
		}
		// Verify that added reminder contents contains title, start and end time
		// verify title is displayed

		int totalContentsInReminder = getDriver().findElements(PWAMyRemindersPage.objTotalContentsInReminder).size();
		System.out.println(totalContentsInReminder);
		for (int i = 0; i < totalContentsInReminder; i++) {
			if (findElements(PWAMyRemindersPage.objTotalContentsInReminder).get(i).isDisplayed()) {
				extent.extentLogger("Verify title", "The content name at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objTotalContentsInReminder).get(i).getText());
				logger.info("The content name at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objTotalContentsInReminder).get(i).getText());
			} else {
				extent.extentLoggerFail("Verify title", "Content title is not displayed in Reminder screen");
				logger.error("Content title is not displayed in Reminder screen");
			}
		}
		// Verify Start and end time
		int dateTime = getDriver().findElements(PWAMyRemindersPage.objDateTime).size();
		for (int i = 0; i < dateTime; i++) {
			if (findElements(PWAMyRemindersPage.objDateTime).get(i).isDisplayed()) {
				extent.extentLogger("Verify date and time", "The date and time at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objDateTime).get(i).getText());
				logger.info("The date and time at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objDateTime).get(i).getText());
			} else {
				extent.extentLoggerFail("Verify date and time", "Date and time is not displayed in Reminder screen");
				logger.error("Date and time is not displayed in Reminder screen");
			}
		}
		// Verify user is able to delete the content by tapping on cancel button
		int contentsBeforeDeleting = getDriver().findElements(PWAAddToWatchListPage.objCancelBtn).size();
		click(PWAAddToWatchListPage.objCancelBtn(1), " Cancel button");
		int contentsAfterDeleting = getDriver().findElements(PWAAddToWatchListPage.objCancelBtn).size();
		if (contentsAfterDeleting < contentsBeforeDeleting) {
			extent.extentLogger("Verify cancel button", "User successfully deleted the content from Reminder screen");
			logger.info("User Successfully deleted the content from Reminder screen");
		} else {
			extent.extentLoggerFail("Verify cancel button",
					"User can not delete the contents from the reminder screen");
			logger.error("User can not delete the contents from the reminder screen");
		}
		// Verify the Remove all functionality
		// click on Remove all
		click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all");
		// Verify contents are deleted
		if (verifyIsElementDisplayed(PWAMyRemindersPage.objNoReminderMessage, "No Reminder message") == true) {
			extent.extentLogger("Verify Remove all option",
					"User successfully deleted all the contents from the Reminder screen");
			logger.info("User successfully deleted all the contents from the Reminder screen");
		} else {
			extent.extentLoggerFail("Verify Remove all option",
					"User can not delete all the contents from the Reminder screen");
			logger.error("User can not  delete all the contents from the Reminder screen");
		}

		// Verify We have nothing to remind you message is displayed
		if (verifyIsElementDisplayed(PWAMyRemindersPage.objNoReminderMessage, "No Reminder message") == true) {
			extent.extentLogger("Verify No reminder message",
					"The message " + getDriver().findElement(PWAMyRemindersPage.objNoReminderMessage).getText()
							+ " is displayed when there is no contents are available");
			logger.info("The message " + getDriver().findElement(PWAMyRemindersPage.objNoReminderMessage).getText()
					+ " is displayed when there is no contents are available");
		} else {
			extent.extentLoggerFail("Verify No Reminder message",
					"The message We have nothing to remind you message is not displayed");
			logger.error("The message We have nothing to remind you message is not displayed");
		}

		// verify device back button functionality
		// Click on device back button
		getDriver().navigate().back();
		// Verify user is navigated to Home screen
		if (activeTab.equals("Home")) {
			extent.extentLogger("Verify current tab",
					"User is navigated to " + activeTab + " tab after pressing device back button");
			logger.info("User is navigated to previous page after clicking on device back button");
		} else {
			extent.extentLoggerFail("Verify current tab",
					"User did not navigate to previos page after clicking on device back button");
			logger.error("User did not navigate to previos page after clicking on device back button");
		}

	}

	public void MyReminderUpdated() throws Exception {
		extent.HeaderChildNode("User Actions : My Reminder functionality validations");
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		navigateToAnyScreen("Live TV");
		waitTime(2000);
		click(PWALiveTVPage.objChannelGuideToggle, "Channel Guide");
		waitTime(10000);
		waitTime(10000);
		waitTime(10000);
		// Verify EPG section contains Title and the description of the respective show
		String currentTimeSlot = getElementPropertyToString("innerText", PWALiveTVPage.objActiveTimeSlot,
				"Current Time Slot");
		extent.extentLogger("", "Current time slot is : " + currentTimeSlot);
		logger.info("Current time slot is : " + currentTimeSlot);
		if (verifyElementPresent(PWALiveTVPage.objOngoingShow(currentTimeSlot), "Ongoing Show") == true) {
			String title = getElementPropertyToString("innerText", PWALiveTVPage.objShowTitle, "Ongoing Show");
			extent.extentLogger("", "The Ongoing Show title is : " + title);
			logger.info("The Ongoing Show title is : " + title);
		}
		if (verifyElementPresent(PWALiveTVPage.objOngoingShowTiming(currentTimeSlot), "Ongoing Show Timing") == true) {
			String timing = getElementPropertyToString("innerText", PWALiveTVPage.objOngoingShowTiming(currentTimeSlot),
					"Ongoing Show timing");
			extent.extentLogger("", "The Ongoing Show Timing is : " + timing);
			logger.info("The Ongoing Show Timing is : " + timing);
		}
		String upcomingTimeSlot = getElementPropertyToString("innerText", PWALiveTVPage.objUpcominfTimeSlot,
				"Upcoming Time Slot");
		extent.extentLogger("", "Upcoming time slot is : " + upcomingTimeSlot);
		logger.info("Upcoming time slot is : " + upcomingTimeSlot);
		if (verifyElementPresent(PWALiveTVPage.objUpcomingShow(upcomingTimeSlot), "Upcoming Show") == true) {
			String title = getElementPropertyToString("innerText", PWALiveTVPage.objUpcomingShow(upcomingTimeSlot),
					"Upcoming Show title");
			extent.extentLogger("", "The Upcoming Show title is : " + title);
			logger.info("The Upcoming Show title is : " + title);
		}
		if (verifyElementPresent(PWALiveTVPage.objUpcomingShowTiming(upcomingTimeSlot),
				"Upcoming Show Timing") == true) {
			String timing = getElementPropertyToString("innerText",
					PWALiveTVPage.objUpcomingShowTiming(upcomingTimeSlot), "Upcoming Show timing");
			extent.extentLogger("", "The Upcoming Show Timing is : " + timing);
			logger.info("The Upcoming Show Timing is : " + timing);
		}
		JSClick(PWALiveTVPage.objUpcomingShow(upcomingTimeSlot), "Upcoming Show");
		if (verifyElementPresent(PWALiveTVPage.objShowDesc, "Upcoming Show Description") == true) {
			String showDesc = getElementPropertyToString("innerText", PWALiveTVPage.objShowDesc,
					"Upcoming Show Description");
			extent.extentLogger("", "The Upcoming Show Description is :" + showDesc);
			logger.info("The Upcoming Show Description is :" + showDesc);
		}
		click(PWALiveTVPage.objPopupCloseButton, "Close button"); // Click on date
		click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
		FilterLanguageUnselectOthers("Hindi", "Hindi Movies");
		waitForElement(PWALiveTVPage.objEPG, 60, "Channels");
		waitTime(10000);
		waitTime(10000);
		Swipe("UP", 1);
		waitTime(10000);
		Swipe("UP", 1);
		waitTime(10000);
		Swipe("UP", 1);
		waitTime(10000);
		String show1 = "", show2 = "";
		// First Show
		if (verifyIsElementDisplayed(PWALiveTVPage.objShowZEETVHDShow1ForReminder, "ZEE TV HD Show")) {
			show1 = getElementPropertyToString("innerText", PWALiveTVPage.objShowZEETVHDShow1ForReminder,
					"ZEE TV HD Show");
			click(PWALiveTVPage.objShowZEETVHDShow1ForReminder, "ZEE TV HD Show " + show1);
			verifyElementPresent(PWALiveTVPage.objShareOption, "Share option");
		}
		if (verifyElementPresent(PWALiveTVPage.objRemainderButton, "Reminder Option")) {
			click(PWALiveTVPage.objRemainderButton, "Reminder option");
			if (getDriver().findElement(PWALiveTVPage.objRemainderButton).getAttribute("class")
					.contains("btnIconActive")) {
				extent.extentLogger("", "Reminder option is active, click has been successful");
				logger.info("Reminder option is active, click has been successful");
			} else {
				extent.extentLoggerFail("", "Reminder option is not active, click has failed");
				logger.error("Reminder option is not active, click has failed");
			}
			click(PWALiveTVPage.objPopupCloseButton, "Close button");
			waitTime(3000);
		} else {
			extent.extentLoggerFail("", "Reminder option is not present for the Show");
			logger.error("Reminder option is not present for the Show");
		}
		// Second Show
		if (verifyIsElementDisplayed(PWALiveTVPage.objShowZEETVHDShow2ForReminder, "ZEE TV HD Show")) {
			show2 = getElementPropertyToString("innerText", PWALiveTVPage.objShowZEETVHDShow2ForReminder,
					"ZEE TV HD Show");
			click(PWALiveTVPage.objShowZEETVHDShow2ForReminder, "ZEE TV HD Show " + show2);
			verifyElementPresent(PWALiveTVPage.objShareOption, "Share option");
		}
		if (verifyElementPresent(PWALiveTVPage.objRemainderButton, "Reminder Option")) {
			click(PWALiveTVPage.objRemainderButton, "Reminder option");
			if (getDriver().findElement(PWALiveTVPage.objRemainderButton).getAttribute("class")
					.contains("btnIconActive")) {
				extent.extentLogger("", "Reminder option is active, click has been successful");
				logger.info("Reminder option is active, click has been successful");
			} else {
				extent.extentLoggerFail("", "Reminder option is not active, click has failed");
				logger.error("Reminder option is not active, click has failed");
			}
			click(PWALiveTVPage.objPopupCloseButton, "Close button");
			waitTime(3000);
		} else {
			extent.extentLoggerFail("", "Reminder option is not present for the Show");
			logger.error("Reminder option is not present for the Show");
		}
		// Verify user can not add Movies to Reminder
		// Select any movie
		Swipe("UP", 1);
		waitTime(10000);
		String movie = getElementPropertyToString("title", PWALiveTVPage.objeMovieForReminder, "Movie");
		click(PWALiveTVPage.objMovieShow, "Movie " + movie);
		if (verifyIsElementDisplayed(PWALiveTVPage.objRemainderButton, "Reminder button for movie") == false) {
			extent.extentLogger("", "Verified that Reminder option is not available for Movie");
			logger.info("Verified that Reminder option is not available for Movie");
		} else {
			extent.extentLoggerFail("", "Verified that Reminder option is available for Movie");
			logger.error("Verified that Reminder option is available for Movie");
		}
		// Click on close button
		click(PWALiveTVPage.objPopupCloseButton, "Close button");
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		navigateToAnyScreen("Home"); // Verify user is navigated to Reminder screen from Home page
		String activeTab = getDriver().findElement(PWAHomePage.objActiveTab).getText();
		if (activeTab.equals("Home")) {
			extent.extentLogger("Verify current tab", "User is in " + activeTab + " tab");
			logger.info("User is in Home tab");
		} else {
			extent.extentLoggerFail("Verify current tab", "User is not in Home tab");
			logger.error("User is not in Home tab");
		}
		// Click on Hamburger menu
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		// Click on My account
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyAccount, "My account"); // Click on My Reminders
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyRemainder, "My Reminder"); // Verify user is navigated to
																							// My Reminder screen
		if (verifyIsElementDisplayed(PWAMyRemindersPage.objMyReminderHeader, "My Reminder Header") == true) {
			extent.extentLogger("",
					"User is Navigated to " + getDriver().findElement(PWAMyRemindersPage.objMyReminderHeader).getText()
							+ " screen from " + activeTab + "page");
			logger.info(
					"User is Navigated to " + getDriver().findElement(PWAMyRemindersPage.objMyReminderHeader).getText()
							+ " screen from " + activeTab + "page");
		} else {
			extent.extentLoggerFail("Verify Navigation ",
					"User failed to navigate from Home page to My Reminders screen");
			logger.error("User failed to navigate from Home page to My Reminders screen");
		}
		// Verify that Remove all button is displayed
		if (verifyIsElementDisplayed(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all option") == true) {
			extent.extentLogger("", "Remove all option is displayed in Reminder screen");
			logger.info("Remove all option is displayed in Reminder screen");
		} else {
			extent.extentLoggerFail("", "Remove all option is not displayed in Reminder screen");
			logger.error("Remove all option is not displayed in Reminder screen");
		}
		// Verify that added reminder contents contains title, start and end time
		// verify title is displayed
		int totalContentsInReminder = getDriver().findElements(PWAMyRemindersPage.objTotalContentsInReminder).size();
		extent.extentLogger("", "Total items in Reminder screen: " + totalContentsInReminder);
		logger.info("Total items in Reminder screen: " + totalContentsInReminder);
		for (int i = 0; i < totalContentsInReminder; i++) {
			if (findElements(PWAMyRemindersPage.objTotalContentsInReminder).get(i).isDisplayed()) {
				extent.extentLogger("Verify title", "The content name at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objTotalContentsInReminder).get(i).getText());
				logger.info("The content name at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objTotalContentsInReminder).get(i).getText());
			} else {
				extent.extentLoggerFail("Verify title", "Content title is not displayed in Reminder screen");
				logger.error("Content title is not displayed in Reminder screen");
			}
		}
		// Verify Start and end time
		int dateTime = getDriver().findElements(PWAMyRemindersPage.objDateTime).size();
		for (int i = 0; i < dateTime; i++) {
			if (findElements(PWAMyRemindersPage.objDateTime).get(i).isDisplayed()) {
				extent.extentLogger("Verify date and time", "The date and time at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objDateTime).get(i).getText());
				logger.info("The date and time at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objDateTime).get(i).getText());
			} else {
				extent.extentLoggerFail("", "Date and time is not displayed in Reminder screen");
				logger.error("Date and time is not displayed in Reminder screen");
			}
		}
		// Verify user is able to delete the content by tapping on cancel button
		int contentsBeforeDeleting = getDriver().findElements(PWAAddToWatchListPage.objCancelBtn).size();
		click(PWAAddToWatchListPage.objCancelBtn(1), " Cancel button");
		int contentsAfterDeleting = getDriver().findElements(PWAAddToWatchListPage.objCancelBtn).size();
		if (contentsAfterDeleting < contentsBeforeDeleting) {
			extent.extentLogger("Verify cancel button", "User successfully deleted the content from Reminder screen");
			logger.info("User Successfully deleted the content from Reminder screen");
		} else {
			extent.extentLoggerFail("Verify cancel button",
					"User can not delete the contents from the reminder screen");
			logger.error("User can not delete the contents from the reminder screen");
		}
		// Verify the Remove all functionality
		// click on Remove all
		click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all");
		// Verify contents are deleted
		if (verifyIsElementDisplayed(PWAMyRemindersPage.objNoReminderMessage, "No Reminder message") == true) {
			extent.extentLogger("Verify Remove all option",
					"User successfully deleted all the contents from the Reminder screen");
			logger.info("User successfully deleted all the contents from the Reminder screen");
		} else {
			extent.extentLoggerFail("Verify Remove all option",
					"User can not delete all the contents from the Reminder screen");
			logger.error("User can not  delete all the contents from the Reminder screen");
		}
		// Verify We have nothing to remind you message is displayed
		if (verifyIsElementDisplayed(PWAMyRemindersPage.objNoReminderMessage, "No Reminder message") == true) {
			extent.extentLogger("Verify No reminder message",
					"The message " + getDriver().findElement(PWAMyRemindersPage.objNoReminderMessage).getText()
							+ " is displayed when there is no contents are available");
			logger.info("The message " + getDriver().findElement(PWAMyRemindersPage.objNoReminderMessage).getText()
					+ " is displayed when there is no contents are available");
		} else {
			extent.extentLoggerFail("Verify No Reminder message",
					"The message We have nothing to remind you message is not displayed");
			logger.error("The message We have nothing to remind you message is not displayed");
		}
		// verify device back button functionality
		// Click on device back button
		getDriver().navigate().back();
		// Verify user is navigated to Home screen
		if (activeTab.equals("Home")) {
			extent.extentLogger("Verify current tab",
					"User is navigated to " + activeTab + " tab after pressing device back button");
			logger.info("User is navigated to previous page after clicking on device back button");
		} else {
			extent.extentLoggerFail("Verify current tab",
					"User did not navigate to previos page after clicking on device back button");
			logger.error("User did not navigate to previos page after clicking on device back button");
		}
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	/*
	 * ==========================Guest_User_User-Actions_Module====================
	 */

	public void UserActionGuestUser() throws Exception {
		extent.HeaderChildNode("User Action module- Guest user Validataions");
		// Validate Continue watching tray is not displayed for Guest user
		waitTime(3000);
		if (verifyIsElementDisplayed(PWAHomePage.objContinueWatchingTray, "Continue Watching tray") == false) {
			extent.extentLogger("Verify Continue Watching tray",
					"Continue watching tray is not displayed for guest user");
			logger.info("Continue watching tray is not displayed for guest user");
		} else {
			extent.extentLoggerFail("Verify Continue Watching tray",
					"Continue watching tray is displaying for guest user");
			logger.info("Continue watching tray is displaying for guest user");
		}
		// Verify Add to Reminder is not displayed for guest user
		// Click on live tv tab
		navigateToAnyScreen("Live TV");
		// click on channel guide
		click(PWALiveTVPage.objChannelGuideToggle, "Channel guide");
		// Verify Reminder option is not available
		// Click on date
		click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
		FilterLanguage("Bengali");
		waitforChannelGuideToLoad();
		// Verify Share and Reminder option is available
		click(PWALiveTVPage.objBanglaShow1, "Show detail");
		if (verifyIsElementDisplayed(PWALiveTVPage.objRemainderButton, "Reminder option for upcoming show ") == false) {
			extent.extentLogger("Verify Reminder button for guest user ",
					"Reminder button is not displayed for the Guest user");
			logger.info("Reminder button is not displayed for the Guest user");
		} else {
			extent.extentLoggerFail("Verify Reminder button for guest user ",
					"Reminder button is displayed for the Guest user");
			logger.error("Reminder button is displayed for the Guest user");
		}

	}

//	public void FilterLanguage() throws Exception{
//		click(PWALiveTVPage.objFilterLanguageChannelGuide,"Filter language");
//		int size = findElements(PWALiveTVPage.objSelectedlang).size();
//		for(int i=1; i<= size; i++) {
//			click(PWALiveTVPage.objSelectedlang,"Selected language");
//		}
//		waitTime(5000);
//		click(PWALiveTVPage.objKannadaLang,"Kannada language");
//		click(PWALiveTVPage.objApplyBtn,"Apply button");
////		click(PWALiveTVPage.objApplyBtn,"Apply button");
//	}

	// ------------------------------------SMOKE
	// ------------------------------------------
	/*
	 * Function for Onboarding scenarios
	 */
	public void OnboardingScenario(String userType) throws Exception {
		dismissSystemPopUp();
		switch (userType) {
		case "Guest":
			launchCheck(userType);
			navigationToMyPlanFromHome("NewRegister");
			navigationToMyPlanFromHome("Logged in");
			playerInLineLoginCheck();
			navigationToCTAInPlayerFromSearch();
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			waitTime(3000);
			if (verifyIsElementDisplayed(PWALoginPage.objLoginBtn, "Login")) {
				extent.extentLogger("Not Logged in", "User is not logged in");
				logger.info("User is not logged in");
				noLogoutOption();
				phoneNumberRegistration();
				// emailRegistration();
				forgotPasswordEmailSanity();
				forgotPasswordMobileNumberSanity();
				extent.HeaderChildNode("Login through Facebook");
				facebookLogin();
				// gmailLogin();
				// forgotPassword();
				logout();
				getDriver().switchTo().window("CDwindow-0");
				String url = getParameterFromXML("url");
				getDriver().get(url);
			}
			break;

		case "NonSubscribedUser":
			dismissSystemPopUp();
			verificationOfLoggedIn();
			myaccountOptionsVerification(userType);
			NavigateToMyProfilePage();
			verificationsInMyProfilePage();
			editProfileFunctionality();
			subscribeCTAFunctionality();
			changePasswordFunctionality();
			break;

		case "SubscribedUser":
			dismissSystemPopUp();
			verificationOfLoggedIn();
			myaccountOptionsVerification(userType);
			NavigateToMyProfilePage();
			verificationsInMyProfilePage();
			editProfileFunctionality();
			myPlanVerification();
			changePasswordFunctionality();
		}
	}

	/**
	 * Generic function Verification Of Options displayed in MyAccount.
	 */
	public void myaccountOptionsVerification(String userType) throws Exception {
		extent.HeaderChildNode("My account options verification");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "Expander button");
		// verifications
		NavigationsToMyWatchlist();
		NavigationsToMyReminders();
		NavigationsToMySubsccription(userType);
		NavigationsToMyTransactions();
	}

	/**
	 * Function for Navigation to MyProfilePage.
	 */
	public void NavigateToMyProfilePage() throws Exception {
		extent.HeaderChildNode("Navigate To My Profile Page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIcon, "Profile icon");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
	}

	/**
	 * Function To verify the options present in MyProfilePage.
	 */
	public void verificationsInMyProfilePage() throws Exception {
		extent.HeaderChildNode("Verifications In My Profile Page");
		verifyElementPresent(PWAHamburgerMenuPage.objProfilePageNameTxt, "User name");
		verifyElementPresent(PWAHamburgerMenuPage.objProfilePageUserIdTxt, "User id");
		verifyElementPresent(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		verifyElementPresent(PWAHamburgerMenuPage.objChangePasswordBtn, "Change password button");
	}

	/**
	 * Function To check the functionality of EditProfile option .
	 */
	public void editProfileFunctionality() throws Exception {
		dismissSystemPopUp();
		extent.HeaderChildNode("Edit Profile Functionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		verifyElementPresent(PWAHamburgerMenuPage.objEditProfileText, "Edit profile page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileFirstName, "First name column");
		getDriver().findElement(PWAHamburgerMenuPage.objEditProfileFirstName).clear();
		type(PWAHamburgerMenuPage.objEditProfileFirstName, "Zee5\n", "Edit profile first name");
		hideKeyboard();
		Swipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "Save changes");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileGoBackBtn, "Back button");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
	}

	/**
	 * Function To check the Functionality of SubscribeCTA option.
	 */
	public void subscribeCTAFunctionality() throws Exception {
		extent.HeaderChildNode("SubscribeCTAFuncionality");
		if (checkElementDisplayed(PWAHamburgerMenuPage.objSubscritionBtn, "Subscribe cta")) {
			click(PWAHamburgerMenuPage.objSubscritionBtn, "Subscribe cta");
			verifyElementPresent(PWALoginPage.objsubscription, "Subscriptions page");
			getDriver().navigate().back();
			logger.info("Clicked on Back button");
			extent.extentLogger("Back button", "Clicked on Back button");
			verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
		} else {
			logger.info("Cta is not visible");
			extent.extentLogger("Cta", "Cta is not visible");
		}
	}

	/**
	 * Function To check the Functionality of ChangePassword option.
	 */
	public void changePasswordFunctionality() throws Exception {
		extent.HeaderChildNode("Change Password Functionality");
		waitTime(3000);
		Swipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objChangePasswordBtn, "Change password button");
		verifyElementPresent(PWAHamburgerMenuPage.objChangePasswordText, "Change password page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objChangeOldPassword, "Current password field");
		type(PWAHamburgerMenuPage.objChangeOldPassword, "User@123\n", "Current password field");
		hideKeyboard();
		// verifyElementPresentAndClick(PWAHamburgerMenuPage.objNewPassword, "New
		// password field");
		type(PWAHamburgerMenuPage.objNewPassword, "igszee5\n", "New password field");
		hideKeyboard();
		// verifyElementPresentAndClick(PWAHamburgerMenuPage.objNewPassword, "Confirm
		// password field");
		type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
		hideKeyboard();
		waitTime(3000);
		verifyElementPresent(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
	}

	/**
	 * Function To check the Functionality of MyPlan option.
	 */
	public void myPlanVerification() throws Exception {
		extent.HeaderChildNode("My Plan Verification In My Profile page");
		if (checkElementDisplayed(PWAHamburgerMenuPage.objMyplanText, "My plan column")) {
			verifyElementPresent(PWAHamburgerMenuPage.objMyActivePlan, "My active plan");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger is present");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIcon, "Profile icon");
			verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
		} else {
			logger.info("My plan is not displayed");
			extent.extentLogger("My Plan", "My plan is not displayed");
		}
	}

	public void NavigationsToMySubscription() throws Exception {
		extent.HeaderChildNode("My Subscription");
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "Expander button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("My Subscription"), "My Subscriptions");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Subscription"), "My Subscription page");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger button");
	}

	/**
	 * Function To check the SignIn page from MyPlans screen.
	 */
	public void navigationToMyPlanFromHome(String UserType) throws Exception {
		extent.HeaderChildNode("Guest user: Validating user navigated to signin screen from my plans screen");
		logger.info("Guest user: Validating user navigated to signin screen from my plans screen");
		verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscription button");
		waitTime(3000);
		if (verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Subscription page")) {
			logger.info("User is navigated to Subscription page");
			extent.extentLogger("Subscription page", "User is navigated to Subscription page");

			if (UserType == "Logged in") {
				navigationToSignInFromMyplans("Logged in");
			}

			if (UserType == "NewRegister") {
				navigationToSignInFromMyplans("NewRegister");
			}
		} else {
			logger.info("User is not navigated to Subscription page");
			extent.extentLogger("Subscription page", "User is not navigated to Subscription page");
		}
	}

	/**
	 * Function To check the SignIn page from MyPlans screen.
	 */
	public void navigationToSignInFromMyplans(String Usertype) throws Exception {
		Swipe("UP", 1);
		if (verifyIsElementDisplayed(PWASubscriptionPages.objadhocPopupArea, "Adoric Popup")) {
			click(PWASubscriptionPages.objadhocPopupSignUpBtn, "Adoric Popup Sign UP Button");
			waitTime(4000);
			verifyElementPresent(PWASubscriptionPages.objadhocPopupRegestrationScreen, "Sign up page");
			waitTime(3000);
			getDriver().navigate().back();
			Swipe("UP", 1);
			verifyElementPresentAndClick(PWASubscriptionPages.objSelectedSubscriptionPlanAmount, "Subscription plan");
			Swipe("UP", 1);
			verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objEmailField, "Sign in page");
			if (Usertype == "Logged in") {
				type(PWALoginPage.objEmailField, "zee5latest@gmail.com", "Email");
				hideKeyboard();
				click(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed button");
				waitTime(3000);
				verifyIsElementDisplayed(PWASubscriptionPages.objPasswordPopupInSubscriptionPage, "Password field");
				type(PWASubscriptionPages.objPasswordField, "User@123\n", "Password");
				hideKeyboard();
				// click(PWASubscriptionPages.objProceedButtonInPassword, "Proceed");
				waitTime(3000);
				if (verifyIsElementDisplayed(PWASubscriptionPages.objAccountDetailInSubscription, "Account details")) {
					logger.info("Verified subscribe flow for logged in user");
					extent.extentLogger("Verification", "Verified subscribe flow for logged in user");
				}
			}
			if (Usertype == "NewRegister") {
				type(PWALoginPage.objEmailField, RandomStringGenerator(5) + "@gmail.com", "Email");
				hideKeyboard();
				waitTime(3000);
				click(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed button");
				waitTime(3000);
				verifyIsElementDisplayed(PWASubscriptionPages.objPasswordPopupInSubscriptionPage, "Password field");
				type(PWASubscriptionPages.objPasswordField, "User@123\n", "Password");
				hideKeyboard();
				// click(PWASubscriptionPages.objProceedButtonInPassword, "Proceed");
				waitTime(10000);
				if (verifyIsElementDisplayed(PWASubscriptionPages.objAccountDetailInSubscription, "Account details")) {
					logger.info("Verified subscribe flow for logged in user");
					extent.extentLogger("Verification", "Verified subscribe flow for logged in user");
				}
			}

			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee logo");
			if (verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Home page")) {
				logger.info("User is navigated to Home page");
				extent.extentLogger("Home page", "User is navigated to Home page");
				logout();
			}
		} else {
			waitTime(3000);
			verifyElementPresentAndClick(PWASubscriptionPages.objSelectedSubscriptionPlanAmount, "Subscription plan");
			Swipe("UP", 1);
			verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objEmailField, "Sign in page");
			if (Usertype == "Logged in") {
				type(PWALoginPage.objEmailField, "zee5latest@gmail.com", "Email");
				hideKeyboard();
				click(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed button");
				waitTime(3000);
				verifyIsElementDisplayed(PWASubscriptionPages.objPasswordPopupInSubscriptionPage, "Password field");
				type(PWASubscriptionPages.objPasswordField, "User@123\n", "Password");
				hideKeyboard();
				waitTime(10000);
				// click(PWASubscriptionPages.objProceedButtonInPassword, "Proceed");
				try {
					getDriver().findElement(PWASubscriptionPages.objProceedButtonInPassword).click();
				} catch (Exception e) {
				}
				waitTime(10000);
				if (verifyIsElementDisplayed(PWASubscriptionPages.objAccountDetailInSubscription, "Account details")) {
					logger.info("Verified subscribe flow for logged in user");
					extent.extentLogger("Verification", "Verified subscribe flow for logged in user");
				}
			}

			if (Usertype == "NewRegister") {
				type(PWALoginPage.objEmailField, RandomStringGenerator(5) + "@gmail.com", "Email");
				hideKeyboard();
				click(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed button");
				waitTime(3000);
				verifyIsElementDisplayed(PWASubscriptionPages.objPasswordPopupInSubscriptionPage, "Password field");
				type(PWASubscriptionPages.objPasswordField, "User@123\n", "Password");
				hideKeyboard();
				waitTime(10000);
				if (verifyIsElementDisplayed(PWASubscriptionPages.objAccountDetailInSubscription, "Account details")) {
					logger.info("Verified subscribe flow for New Register user");
					extent.extentLogger("Verification", "Verified subscribe flow for New Register user");
				}
			}
			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee logo");
			if (verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Home page")) {
				logger.info("User is navigated to Home page");
				extent.extentLogger("Home page", "User is navigated to Home page");
				logout();
				waitTime(4000);
			}
		}
	}

	public void playerInLineLoginCheck() throws Exception {
		extent.HeaderChildNode("Login check from Player Inline popup");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovieNoTrailer");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Field");
		hideKeyboard();
		waitTime(3000);
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		click(PWASearchPage.objPremiumSearchResult(keyword), "Premium content");
		waitTime(12000);
		waitTime(12000);
		if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe pop up")) {
			click(PWASubscriptionPages.objPopupCloseButton, "Close button of Pop Up");
		}
		verifyElementPresentAndClick(PWASubscriptionPages.objLoginLinkInPlayer, "Login link");
		waitTime(5000);
		if (verifyIsElementDisplayed(PWALoginPage.objEmailField, "Login")) {
			logger.info("User is redirected to login page");
			extent.extentLogger("Login", "User is redirected to login page");
		}
		Back(1);
		waitTime(8000);
		if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe pop up")) {
			// click(PWASubscriptionPages.objGetPremiumPopupCloseButton, "close button");
			click(PWASubscriptionPages.objPopupCloseButton, "Close button of Pop Up");
		}
		click(PWAHomePage.objZeeLogo, "Zee logo");
	}

	/**
	 * Function To check the SignIn page from CTA in playerscreen and Verification
	 * of SubscribePopUP.
	 */
	public void navigationToCTAInPlayerFromSearch() throws Exception {
		extent.HeaderChildNode("Validating Subscribe popup post tapping Cta in player");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovie");
		typeAndGetSearchResult(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		hideKeyboard();
		waitTime(3000);
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		click(PWASearchPage.objSearchResultPremiumContent2, "Premium content");
		waitTime(10000);
		verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumCTAInPlater, "CTA in player");
		waitTime(5000);// added by Tanisha
		navigationToSignInFromCTAInPlayer();
	}

	/**
	 * Function To check the SignIn page from CTA in playerscreen and Verification
	 * of SubscribePopUP.
	 */
	public void navigationToSignInFromCTAInPlayer() throws Exception {
		extent.HeaderChildNode("CTA In Player");
		verifyElementPresent(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe popup");
		verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupPlan, "Subscribe popup plan");
		verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopipProceed,
				"Proceed button in Subscribe popup");
		waitTime(4000);
		verifyElementPresent(PWALoginPage.objEmailField, "Sign in page");
		verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee logo");
		if (verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Home page")) {
			logger.info("User is navigated to Home page");
			extent.extentLogger("Home page", "User is navigated to Home page");
		}
	}

	/**
	 * Function To check the Funcionality of ForgotPassword option.
	 */
	public void forgotPassword() throws Exception {
		extent.HeaderChildNode("Forgot password functionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
		waitTime(2000);
		verifyElementPresent(PWALoginPage.objEmailField, "Login page");
		verifyElementPresentAndClick(PWALoginPage.objForgotPasswordTxt, "Forgot password");
		waitTime(3000);
		verifyElementPresent(PWALoginPage.objForgotPassswordPage, "Forgot password page");
		type(PWALoginPage.objEmailField, "zee5latest@gmail.com", "Email field");
		verifyElementPresentAndClick(PWALoginPage.objForgotPasswordLinkButton, "Reset password button");
		waitTime(3000);
		((JavascriptExecutor) getDriver()).executeScript("window.open();");
		waitTime(4000);
		System.out.println(getDriver().getWindowHandles());
		getDriver().switchTo().window("CDwindow-3");
		String url = GmailInbox.readEmail("ZEE5 account password reset request");
		if (!url.isEmpty()) {
			getDriver().get(url);
			waitTime(3000);
			System.out.println(getDriver().getCurrentUrl());
			Thread.sleep(5000);
			verifyElementPresent(PWALoginPage.objForgotNextPageText, "Reset password page");
			type(PWALoginPage.objForgotNextPagePwsswordFielfd, "User@123\n", "Password");
			waitTime(3000);
			type(PWALoginPage.objForgotNextPageConfirmPasswordField, "User@123\n", "Confirm password");
			Wait(10000);
			if (verifyIsElementDisplayed(PWALoginPage.objEmailField, "Login page")) {
				type(PWALoginPage.objEmailField, "zee5latest@gmail.com", "Login");
				hideKeyboard();
				type(PWALoginPage.objPasswordField, "User@123\n", "Password");
				hideKeyboard();
				waitTime(2000);
				click(PWALoginPage.objLoginBtn, "LoginButton");
				waitTime(5000);
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objProfileIcon, "profile icon")) {
				logger.info("User is successfully changed password and logged in");
				extent.extentLogger("Logged in", "User is successfully changed password and logged in");
				click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Close button");
			} else {
				logger.info("User is not logged in");
				extent.extentLogger("Logged in", "User is not logged in");
			}
		} else {
			logger.info("User is not logged in");
			extent.extentLogger("Logged in", "User is not logged in");
		}
	}

	/**
	 * Function To check That Logout Option is not displayed in Hamburger menu.
	 */
	public void noLogoutOption() throws Exception {
		extent.HeaderChildNode("Checking no Logout option displayed for guest user");
		if (!(verifyIsElementDisplayed(PWAHamburgerMenuPage.objExploreItemBtn("Logout"), "Logout"))) {
			logger.info("Logout option is not displayed for guest user");
			extent.extentLogger("Logout option", "Logout option is not displayed");
		}

	}

	/**
	 * Function To check That user is logged in succesfully and Login,SignUP ption
	 * is not displayed for Logged in user.
	 */
	public void verificationOfLoggedIn() throws Exception {
		extent.HeaderChildNode("Verification of Logged in");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
			logger.info("User is logged in successfully");
			extent.extentLogger("Profile icon", "User is logged in successfully");
		}
		if (!(verifyIsElementDisplayed(PWALoginPage.objLoginBtn, "Login"))) {
			logger.info("Login button is not displayed for logged in user");
			extent.extentLogger("Login Button", "Login button is not displayed for logged in user");
		}
		if (!(verifyIsElementDisplayed(PWALoginPage.objSignUpBtn, "SignUp"))) {
			logger.info("SignUp button is not displayed for logged in user");
			extent.extentLogger("Sign Up Button", "SignUp button is not displayed for logged in user");
		}
		click(PWAHamburgerMenuPage.objHamburgerClose, "Close of Hamburger Menu");
	}

	/**
	 * Validation of Landing Pages
	 */
	public void ValidatingLandingPages(String UserType) throws Exception {
		FirstTimeUserTrendingonzee5(UserType);
		dismissSystemPopUp();
		landingpagePropertiesValidation(UserType);
		BackTOTop();
		landingPagesValidation(UserType, "Home");
		extent.HeaderChildNode("Verifing the trays displayed in Home Tab");
		pagesTrayValidation("Home");
	}

	/**
	 * Function to verify landing page properties
	 */
	public void landingpagePropertiesValidation(String userType) throws Exception {
		extent.HeaderChildNode("Landing Page validation");
		verifyElementPresent(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
		verifyElementPresent(PWALandingPages.obj_Pwa_Zee5Logo, "Zee5 Logo");
		verifyElementPresent(PWALandingPages.obj_Pwa_SearchBtn, "Search");
		if (userType.equalsIgnoreCase("SubscribedUser"))
			verifyElementNotPresent(PWALandingPages.obj_Pwa_Subcription_teaser_btn, 5);
		else
			verifyIsElementDisplayed(PWALandingPages.obj_Pwa_Subcription_teaser_btn, "Subscribe button");
	}

	/**
	 * Method to close Adoric pop up
	 */
	public void closeAdoricPopUp() {
		List<WebElement> popUps = getDriver().findElements(PWASubscriptionPages.adoricCloseBtn);
		int size = popUps.size();
		for (int pop = 0; pop < size; pop++) {
			try {
				popUps.get(pop).click();
				System.out.println("clicked " + pop);
			} catch (Exception e) {
				System.out.println("failed " + pop);
			}
		}
	}

	/**
	 * Validation of Trending on Zee 5 tray
	 */
	public void FirstTimeUserTrendingonzee5(String userType) throws Exception {
		extent.HeaderChildNode("Validation of Trending on ZEE5 tray for " + userType + " user");
		PartialSwipe("UP", 1);
		if (verifyIsElementDisplayed(PWALandingPages.obj_Pwa_Trending_On_Zee5, "Trending on Zee5 tray")) {
			System.out.println("Trending on ZEE5 is found for first time user");
			logger.info("Verify for first time user it should show Trending on Zee5 tray is Pass");
			extent.extentLogger("apValue", "Verify for first time user it should show Trending on Zee5 tray is Pass");
		} else {
			System.out.println("Not a first time user");
			Swipe_till_Zee5IsTrending();
		}
	}

	/**
	 * Function to swipe till tray Trending on Zee5
	 */
	public void Swipe_till_Zee5IsTrending() throws Exception {
		waitTime(5000);
		int found = 0;
		for (int i = 0; i <= 2; i++) {
			if (verifyIsElementDisplayed(PWALandingPages.obj_Pwa_Trending_On_Zee5, "Trending on Zee5 tray")) {
				System.out.println("element found");
				logger.info("Verify for first time user it should show Trending on Zee5 tray is Pass");
				extent.extentLogger("apValue",
						"Verify for first time user it should show Trending on Zee5 tray is Pass");
				found = 1;
				break;
			} else {
				Swipe("up", 1);
				waitTime(4000);
			}
			if (found == 0) {
				System.out.println("Trending on Zee5 is not found");
				logger.error("Verify for first time user it should show Trending on Zee5 tray is Fail");
				extent.extentLoggerFail("apValue",
						"Verify for first time user it should show Trending on Zee5 tray is Fail");
			}
		}
	}

	/**
	 * Method to swipe till element
	 */
	public void swipeTillElementLocated(By locator, int swipeCount, String message) throws Exception {
		for (int swipe = 0; swipe <= swipeCount; swipe++) {
			try {
				getDriver().findElement(locator);
				extent.extentLogger("swiped", "Swiped until " + message);
				logger.info("Swiped until " + message);
				break;
			} catch (Exception e) {
				if (swipe == swipeCount) {
					extent.extentLogger("swiped", "Failed to locate " + message);
					logger.info("Failed to locate " + message);
				} else {
					Swipe("UP", 1);
					Thread.sleep(3000);
				}
			}
		}
	}

	/**
	 * Validate navigation from vodafone native app to Zee5 pwa
	 */
	public void ValidateVodafonePlayFunctionFromNativeApp() throws Exception {
		extent.HeaderChildNode("VodafonePlayFunctionFromNativeApp");
		// Click on Hamburger
		verifyElementPresentAndClick(NativeVodafonePlayPage.HamburgerBtn, "VodafonePlay Hamburger");
		Thread.sleep(3000);
		Swipe("UP", 1);
		Thread.sleep(3000);
		// Click on ZEE5 Icon
		verifyElementPresentAndClick(NativeVodafonePlayPage.Zee5Icon, "Zee5 Icon");
		Thread.sleep(3000);
		// Click on SearchIcon
		verifyElementPresentAndClick(NativeVodafonePlayPage.ChannelSearchIcon, "search icon");
		Thread.sleep(3000);
		// Click on Search Textbox
		verifyElementPresentAndClick(NativeVodafonePlayPage.SearchTextBox, "Search TextBox");
		Thread.sleep(3000);
		// Send Value for Saerch
		type(NativeVodafonePlayPage.SearchTextBox, "Commando 3\n", "Search TextBox");
		Thread.sleep(3000);
		hideKeyboard();
		// Click on Searched Data
		verifyElementPresentAndClick(NativeVodafonePlayPage.searchedData("Commando 3"), "Searched Data");
		// NAVIGATION TO ZEE5 PWA
		Thread.sleep(10000);
		System.out.println("Contexts : " + getDriver().getContextHandles());
		// Changing the Context form native to webview
		getDriver().context("WEBVIEW_1");
		// Navigated URL
		System.out.println("Navigated to URL : " + getDriver().getCurrentUrl());
		logger.info("Navigated to URL : " + getDriver().getCurrentUrl());
		extent.extentLogger("<b>" + "Navigated to URL : " + getDriver().getCurrentUrl(), "Navigated to URL");
		// Selected Content title
		System.out.println(getDriver().findElement(PWAVodafonePlayPage.consumptionPageTitle).getText());
		logger.info("Navigated to URL : " + getDriver().getCurrentUrl());
		extent.extentLogger("<b>" + "Navigated to URL : " + getDriver().getCurrentUrl(), "Navigated to URL");
		// TO VERIFY THE BROWSER
		String str = getCurrentActivity();
		if (str.contains("chrome")) {
			System.out.println("Chrome browser is opened");
			logger.info("Chrome Browser is Opened");
			extent.extentLogger("<b>" + "Chrome Browser is Opened", "Chrome Browser is Opened");
		}
		// Click on Zee5 Hamburger
		verifyElementPresentAndClick(PWAVodafonePlayPage.HamburgerBtn, "Zee5 Hamburger");
		Thread.sleep(3000);
		if (verifyIsElementDisplayed(PWAVodafonePlayPage.MySubscription, "MySubscription") == false) {
			System.out.println("User has no option to purchase the plans");
			logger.info("User has no option to purchase the plans");
			extent.extentLogger("<b>" + "User has no option to purchase the plans",
					"User has no option to purchase the plans");
		}
	}

	/**
	 * Method to verify Consumptions screen tapping on any content card
	 * 
	 * @param userType
	 * @param contentType
	 * @param contentTitle
	 * @param devicePin
	 * @throws Exception
	 */
	public void verifyConsumptionsScreenTappingOnCard(String userType, String contentType, String contentTitle)
			throws Exception {
		extent.HeaderChildNode("Verify Consumption Page for Content type: " + contentType);
		String consumptionPageTitle = "";
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		if (contentType.equals("Live TV")) {
			navigateToAnyScreen("Live TV");
			String url = getDriver().getCurrentUrl();
			reloadURL(url);
			if (waitforLiveTabToLoad()) {
				waitTime(4000);
				waitForElement(PWAShowsPage.objFirstAssetTitleLiveTvCard, 30, "Content title");
				contentTitle = getText(PWAShowsPage.objFirstAssetTitleLiveTvCard);
				extent.extentLogger("", "Card Title fetched: " + contentTitle);
				logger.info("Card Title fetched: " + contentTitle);
				verifyElementPresentAndClick(PWAShowsPage.objFirstAssetTitleLiveTvCard, "Live TV Card");
				waitForElement(PWAPlayerPage.objContentTitleLiveTV, 30, "Content title");
				consumptionPageTitle = getText(PWAPlayerPage.objContentTitleLiveTV);
				if (consumptionPageTitle.contains(contentTitle)) {
					extent.extentLogger("correctNavigation",
							"Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
					logger.info("Successfully navigated to the correct Consumption page " + consumptionPageTitle);
					/*
					 * if (contentType.equals("Live TV")) pausePlayerForLiveTV(); else
					 * pausePlayerAndGetLastPlayedTime();
					 */
				} else {
					extent.extentLoggerFail("incorrectNavigation",
							"Navigated to incorrect Consumption page: " + consumptionPageTitle);
					logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
				}
			}
		} else {
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
			type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
			waitForElement(PWASearchPage.objSearchedResult(contentTitle), 30, "Search Result");
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
			// waitForElementAndClickIfPresent(PWASubscriptionPages.objPopupCloseButton, 5,
			// "Close in Pop Up");
			waitTime(4000);
			waitForElement(PWAPlayerPage.objContentTitle, 30, "Content title");
			consumptionPageTitle = getText(PWAPlayerPage.objContentTitle);
			if (consumptionPageTitle.contains(contentTitle)) {
				extent.extentLogger("correctNavigation",
						"Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
				logger.info("Successfully navigated to the correct Consumption page " + consumptionPageTitle);
				/*
				 * if (contentType.equals("Live TV")) pausePlayerForLiveTV(); else
				 * pausePlayerAndGetLastPlayedTime();
				 */
			} else {
				extent.extentLoggerFail("incorrectNavigation",
						"Navigated to incorrect Consumption page: " + consumptionPageTitle);
				logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
			}
		}
	}

	/**
	 * Method to pause the player and get the duration lapsed
	 * 
	 * @throws Exception
	 */
	public void pausePlayerAndGetLastPlayedTime() throws Exception {
		waitForPlayerAdToComplete("Video Player");
		if (pausePlayer() == true) {
			getPlayerDuration();
			Thread.sleep(4000);
		} else {
			extent.extentLoggerFail("failedToPause", "Failed to pause Player");
			logger.error("Failed to pause Player");
		}
	}

	/**
	 * Pause Player for Live TV
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void pausePlayerForLiveTV() throws Exception {
		waitForPlayerAdToComplete("Live Player");
		int deviceWidth = getDriver().manage().window().getSize().width;
		int deviceHeight = getDriver().manage().window().getSize().height;
		int x = deviceWidth / 2;
		System.out.println("x: " + x);
		int y = deviceHeight / 4;
		System.out.println("y: " + y);
		boolean playerPaused = false;
		for (int trial = 0; trial <= 4; trial++) {
			System.out.println("trial :" + trial);
			try {
				TouchAction act = new TouchAction(getDriver());
				act.tap(PointOption.point(x, y)).perform();
				extent.extentLogger("playerTap", "Tapped on Live Player");
				logger.info("Tapped on Live Player");
				try {
					getDriver().findElement(PWAPlayerPage.pauseBtn).click();
					try {
						getDriver().findElement(PWAPlayerPage.playBtn);
						extent.extentLogger("playerPaused", "Paused Live Player");
						logger.info("Paused Live Player");
						playerPaused = true;
						break;
					} catch (Exception e) {
					}
				} catch (Exception e) {
					try {
						getDriver().findElement(PWAPlayerPage.playBtn);
						extent.extentLogger("playerPaused", "Paused Live Player");
						logger.info("Paused Live Player");
						playerPaused = true;
						break;
					} catch (Exception e1) {
					}
				}
			} catch (Exception e) {
				Thread.sleep(1000);
				if (trial == 4) {
					extent.extentLoggerFail("errorOccured", "Error when handling Live Player");
					logger.error("Error when handling Live Player");
				}
			}
		}
		if (playerPaused == true) {
			try {
				getDriver().findElement(PWAPlayerPage.objLivePlayerVolume);
				extent.extentLogger("livePlayerVolume", "Located Live Player Volume");
				logger.info("Located Live Player Volume");
			} catch (Exception e) {
				extent.extentLoggerFail("livePlayerVolume", "Failed to locate Live Player Volume");
				logger.error("Failed to locate Live Player Volume");
			}
		}
	}

	/**
	 * Method to verify the Watch Latest Episode CTA in Show details page
	 * 
	 * @param contentTitle
	 * @throws Exception
	 */
	public void verifyWatchLatestEpisodeCTA(String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify Landscape for Free Content");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementAndClickIfPresent(PWASubscriptionPages.objPopupCloseButton, 10, "Close in Language Pop Up");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitForElement(PWASearchPage.objSearchNavigationTab("Shows"), 30, "Shows tab");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Shows"), "Shows tab");
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 10, "Close in Sign Up Pop Up");
		verifyElementPresent(PWAShowsPage.objShowsTitle, "Show title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAShowsPage.objShowsTitle,
				"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation", "Successfully navigated to the correct Details page");
			logger.info("Successfully navigated to the correct Details page");
			verifyElementPresent(PWAShowsPage.objWatchLatestCTA, "Watch Latest CTA button");
			String watchLatestCTAText = getElementPropertyToString("innerText", PWAShowsPage.objWatchLatestCTA,
					"Watch Latest CTA button").toString();
			if (watchLatestCTAText.equals("Watch Latest Episode")) {
				extent.extentLogger("correctButtonText", "Correct button text displayed: " + watchLatestCTAText);
				logger.info("Correct button text displayed: " + watchLatestCTAText);
			} else {
				extent.extentLoggerFail("incorrectButtonText",
						"Incorrect button text displayed: " + watchLatestCTAText);
				logger.error("Incorrect button text displayed: " + watchLatestCTAText);
			}
		} else {
			extent.extentLoggerFail("incorrectNavigation",
					"Navigated to incorrect Details page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Details page: " + consumptionPageTitle);
		}
	}

	/**
	 * Method to verify no Subscription pop up is displayed for free content
	 * 
	 * @param userType
	 * @param contentType
	 * @param contentTitle
	 * @param devicePin
	 * @throws Exception
	 */
	public void verifyNoSubscriptionPopupForFreeContent(String userType, String contentTitle) throws Exception {
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		extent.HeaderChildNode("Verify No Subscription Popup For Free Content");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		// if(userType.equals("Guest"))
		// waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 5,
		// "Close in Sign Up Pop Up");
		waitForElement(PWAPlayerPage.objContentTitle, 20, "Content title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,
				"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation",
					"Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
			logger.info("Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle)) {
				extent.extentLogger("", "Subscribe Pop Up is located");
				logger.info("Subscribe Pop Up is located");
				extent.extentLoggerFail("", "Subscribe Pop is getting displayed for free content");
				logger.error("Subscribe Pop is getting displayed for free content");
			} else {
				extent.extentLogger("", "Subscribe Pop Up is not located");
				logger.info("Subscribe Pop Up is not located");
				extent.extentLogger("", "Expected behavior: Subscribe Pop for free content is not displayed");
				logger.info("Expected behavior: Subscribe Pop for free content is not displayed");
			}
		} else {
			extent.extentLoggerFail("incorrectNavigation",
					"Navigated to incorrect Consumption page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
		}
	}

	/**
	 * The method will wait for the element to not be located for a maximum of given
	 * seconds. The method terminates immediately once the element is located and
	 * throws error.
	 */
	public void waitForElementAbsence(By locator, int seconds, String message) throws InterruptedException {
		main: for (int time = 0; time <= seconds; time++) {
			try {
				getDriver().findElement(locator);
				logger.error("Located element " + message);
				extent.extentLoggerFail("locatedElement", "Located element " + message);
				break main;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.info("Expected behavior: " + message + " is not displayed");
					extent.extentLogger("failedLocateElement", "Expected behavior: " + message + " is not displayed");
				}
			}
		}
	}

	/**
	 * Method to verify Subscription pop up is displayed for premium content
	 * 
	 * @param userType
	 * @param contentType
	 * @param contentTitle
	 * @param devicePin
	 * @throws Exception
	 */
	public void verifySubscriptionPopupForPremiumContent(String userType, String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify Subscription Popup For Premium Content");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitTime(2000);
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 45, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		if (waitForElementPresence(PWASubscriptionPages.objSubscribePopupTitle, 5, "Subscribe Pop Up")) {
			waitForElementAndClickIfPresent(PWASubscriptionPages.objPopupCloseButton, 2, "Close in Subscribe Pop Up");
		} else {
			// if(userType.equals("Guest"))
			// waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 5,
			// "Close in Sign Up Pop Up");
			waitForElement(PWAPlayerPage.objContentTitle, 20, "Content title");
			String consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,
					"Content Title").toString();
			if (consumptionPageTitle.contains(contentTitle)) {
				extent.extentLogger("correctNavigation",
						"Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
				logger.info("Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
				if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
					waitForElement(PWASubscriptionPages.objSubscribePopupTitle, 30, "Subscribe Pop Up");
					waitForElementAndClickIfPresent(PWASubscriptionPages.objPopupCloseButton, 10,
							"Subscribe Pop Up Close button");
				} else if (userType.equals("SubscribedUser")) {
					waitForElementAbsence(PWASubscriptionPages.objSubscribePopupTitle, 30, "Subscribe Pop Up");
				} else {
					extent.extentLoggerFail("incorrectUserType", "Incorrect User Type entered in script");
					logger.error("Incorrect User Type entered in script");
				}
			} else {
				extent.extentLoggerFail("incorrectNavigation",
						"Navigated to incorrect Consumption page: " + consumptionPageTitle);
				logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
			}
		}
	}

	/**
	 * Method to verify share functionality and metadata comparison between Show
	 * Details and Consumption page plays in player
	 * 
	 * @param contentTitle
	 * @throws Exception
	 */
	public void verifyCTAandMetaDataInDetailsAndConsumption(String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify Watch Latest Episode CTA");
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitForElement(PWASearchPage.objSearchNavigationTab("Shows"), 30, "Shows tab");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Shows"), "Shows tab");
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		verifyElementPresent(PWAShowsPage.objShowsTitle, "Show title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAShowsPage.objShowsTitle,
				"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation", "Successfully navigated to the correct Details page");
			logger.info("Successfully navigated to the correct Details page");
			verifyElementPresent(PWAShowsPage.objWatchLatestCTA, "Watch Latest CTA button");
			String watchLatestCTAText = getElementPropertyToString("innerText", PWAShowsPage.objWatchLatestCTA,
					"Watch Latest CTA button").toString();
			if (watchLatestCTAText.equals("Watch Latest Episode")) {
				extent.extentLogger("correctButtonText", "Correct button text displayed: " + watchLatestCTAText);
				logger.info("Correct button text displayed: " + watchLatestCTAText);
			} else {
				extent.extentLoggerFail("incorrectButtonText",
						"Incorrect button text displayed: " + watchLatestCTAText);
				logger.error("Incorrect button text displayed: " + watchLatestCTAText);
			}
			extent.HeaderChildNode("Verify Metadata in Consumptions page");
			waitTime(3000);
			click(PWAShowsPage.objEpisodesSetTray, "Episode Tray");
			waitTime(2000);
			click(PWAShowsPage.objSecondSetEpisodeTray, "Second Episode set");
			waitTime(3000);
			click(PWAShowsPage.objSecondAssetImageFirstRail, "Second card image from first rail");
			waitTime(3000);
			// Get API details
			String contentURL = getDriver().getCurrentUrl();
			System.out.println(contentURL);
			String[] abc = contentURL.split("/");
			// String contentID = abc[abc.length - 1].split("\\?")[0];
			// https://newpwa.zee5.com/tvshows/details/paaru/0-6-1179/paaru-september-10-2020/0-1-manual_16030f67bgc0
			String contentID = abc[abc.length - 1];
			System.out.println("contentID fetched from URL: " + contentID);
			Response resp = ResponseInstance.getContentDetails(contentID, "content");
			System.out.println(resp.getBody().asString());
			if (resp.getBody().asString().contains("\"error_code\":401")) {
				resp = ResponseInstance.getContentDetails(contentID, "content");
				System.out.println(resp.getBody().asString());
			}
			String titleAPI = resp.jsonPath().get("title").toString().trim();
			extent.extentLogger("apidata", "Episode title fetched from API: " + titleAPI);
			logger.info("Episode title fetched from API: " + titleAPI);

			String showtitleAPI = resp.jsonPath().get("tvshow.title").toString().trim();
			extent.extentLogger("apidata", "Show title fetched from API: " + showtitleAPI);
			logger.info("Show title fetched from API: " + showtitleAPI);

			String episodeNoAPI = resp.jsonPath().get("orderid").toString().trim();
			extent.extentLogger("apidata", "Episode number fetched from API: " + episodeNoAPI);
			logger.info("Episode number fetched from API: " + episodeNoAPI);

			String durationAPI = resp.jsonPath().get("duration").toString().trim();
			extent.extentLogger("apidata", "Duration fetched from API: " + durationAPI);
			logger.info("Duration fetched from API: " + durationAPI);

			String dateAPI = resp.jsonPath().get("hls[0]").toString().trim();
			dateAPI = dateAPI.split("/Seamless")[0];
			String[] a = dateAPI.split("/");
			dateAPI = a[a.length - 1];
			System.out.println(dateAPI);
			extent.extentLogger("apidate", "Episode date fetched from API: " + dateAPI);
			logger.info("Episode date fetched from API: " + dateAPI);
			dateAPI = convertDateFromAPI(dateAPI);
			extent.extentLogger("apidata", "Converted date from API: " + dateAPI);
			logger.info("Converted date from API: " + dateAPI);

			String genreAPI = resp.jsonPath().get("genre[0].value").toString().trim();
			extent.extentLogger("apidata", "Genre fetched from API: " + genreAPI);
			logger.info("Genre fetched from API: " + genreAPI);

			String ageRatingAPI = resp.jsonPath().get("age_rating").toString().trim();
			extent.extentLogger("apidata", "Age Rating fetched from API: " + ageRatingAPI);
			logger.info("Age Rating fetched from API: " + ageRatingAPI);

			waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 5, "Close in Sign Up Pop Up");

			String episode = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,
					"Episode title in Consumption Page").toString();
			extent.extentLogger("episode", "Episode title fetched from UI: " + episode);
			logger.info("Episode title fetched from UI: " + episode);
			if (titleAPI.equals(episode)) {
				extent.extentLogger("titleMatch", "Consumption page content Title matched with API");
				logger.info("Consumption page content Title matched with API");
			} else {
				extent.extentLoggerFail("titleMismatch", "Consumption page content Title mismatched with API");
				logger.error("Consumption page content Title mismatched with API");
			}

			String show = getText(PWAPlayerPage.objConsumptionsShowTitle);
			extent.extentLogger("show", "Show title fetched from UI: " + show);
			logger.info("Show title fetched from UI: " + show);
			if (showtitleAPI.equals(show)) {
				extent.extentLogger("showMatch", "Consumption page content Show matched with API");
				logger.info("Consumption page content Show matched with API");
			} else {
				extent.extentLoggerFail("showMismatch", "Consumption page content Show mismatched with API");
				logger.error("Consumption page content Show mismatched with API");
			}

			String episodeNo = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaEpisode,
					"Episode Number in Consumption Page").toString();
			extent.extentLogger("episodeNo", "Episode No fetched from UI: " + episodeNo);
			logger.info("Episode No fetched from UI: " + episodeNo);
			episodeNo = episodeNo.split("Episode ")[1];
			if (episodeNoAPI.equals(episodeNo)) {
				extent.extentLogger("episodeMatch", "Consumption page content Episode Number matched with API");
				logger.info("Consumption page content Episode Number matched with API");
			} else {
				extent.extentLoggerFail("episodeMismatch",
						"Consumption page content Episode Number mismatched with API");
				logger.error("Consumption page content Episode Number mismatched with API");
			}

			String episodeDate = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaDate,
					"Episode Date in Consumption Page").toString();
			extent.extentLogger("episodeDate", "Episode Date fetched from UI: " + episodeDate);
			logger.info("Episode Date fetched from UI: " + episodeDate);
			if (dateAPI.equals(episodeDate)) {
				extent.extentLogger("dateMatch", "Consumption page content Episode Date matched with API");
				logger.info("Consumption page content Episode Date matched with API");
			} else {
				extent.extentLoggerFail("dateMismatch", "Consumption page content Episode Date mismatched with API");
				logger.error("Consumption page content Episode Date mismatched with API");
			}

			String duration = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaDuration,
					"Duration in Consumption Page").toString();
			extent.extentLogger("duration", "Duration fetched from UI: " + duration);
			logger.info("Duration fetched from UI: " + duration);
			durationAPI = String.valueOf((Integer.parseInt(durationAPI) / 60));
			duration = duration.split("m")[0];
			if (durationAPI.equals(duration)) {
				extent.extentLogger("durationMatch", "Consumption page content Duration matched with API");
				logger.info("Consumption page content Duration matched with API");
			} else {
				extent.extentLoggerFail("durationMismatch", "Consumption page content Duration mismatched with API");
				logger.error("Consumption page content Duration mismatched with API");
			}

			String genre = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaGenre,
					"Genre in Consumption Page").toString();
			extent.extentLogger("genre", "Genre fetched from UI: " + genre);
			logger.info("Genre fetched from UI: " + genre);
			if (genreAPI.equals(genre)) {
				extent.extentLogger("genreMatch", "Consumption page content Genre matched with API");
				logger.info("Consumption page content Genre matched with API");
			} else {
				extent.extentLoggerFail("genreMismatch", "Consumption page content Genre mismatched with API");
				logger.error("Consumption page content Genre mismatched with API");
			}

			String ageRating = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaAgeRating,
					"Age Rating in Consumption Page").toString();
			extent.extentLogger("ageRating", "Age Rating fetched from UI: " + ageRating);
			logger.info("Age Rating fetched from UI: " + ageRating);
			if (ageRatingAPI.equals(ageRating)) {
				extent.extentLogger("ageRatingMatch", "Consumption page content Age Rating matched with API");
				logger.info("Consumption page content Age Rating matched with API");
			} else {
				extent.extentLoggerFail("ageRatingMismatch", "Consumption page content Age Rating mismatched with API");
				logger.error("Consumption page content Age Rating mismatched with API");
			}

		} else {
			extent.extentLoggerFail("incorrectNavigation",
					"Navigated to incorrect Details page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Details page: " + consumptionPageTitle);
		}
	}

	public String convertDateFromAPI(String apiDate) {
		String date = apiDate.substring(0, 2);
		if (date.charAt(0) == '0') {
			date = date.split("")[1];
		}
		System.out.println("date " + date);
		String month = apiDate.substring(2, 4);
		System.out.println("month " + month);
		if (month.equals("01"))
			month = "Jan";
		if (month.equals("02"))
			month = "Feb";
		if (month.equals("03"))
			month = "Mar";
		if (month.equals("04"))
			month = "Apr";
		if (month.equals("05"))
			month = "May";
		if (month.equals("06"))
			month = "Jun";
		if (month.equals("07"))
			month = "Jul";
		if (month.equals("08"))
			month = "Aug";
		if (month.equals("09"))
			month = "Sep";
		if (month.equals("10"))
			month = "Oct";
		if (month.equals("11"))
			month = "Nov";
		if (month.equals("12"))
			month = "Dec";
		String year = apiDate.substring(4, 8);
		System.out.println("year " + year);
		System.out.println(date + " " + month + " " + year);
		return (date + " " + month + " " + year);
	}

	/**
	 * Method to enter device pin
	 * 
	 * @throws Exception
	 */
	public void enterDevicePin(String devicePin) throws Exception {
		boolean devicePinPresent = false;
		// wait and check if device pin box appears
		for (int trial = 0; trial <= 4; trial++) {
			try {
				getDriver().findElement(PWAHomePage.objDevicePin1);
				devicePinPresent = true;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		if (devicePinPresent == true) {
			for (int trial = 0; trial <= 4; trial++) {
				try {
					getDriver().findElement(By.xpath("//input[@id='parentLockId1']"))
							.sendKeys(devicePin.substring(0, 1));
					getDriver().findElement(By.xpath("//input[@id='parentLockId2']"))
							.sendKeys(devicePin.substring(1, 2));
					getDriver().findElement(By.xpath("//input[@id='parentLockId3']"))
							.sendKeys(devicePin.substring(2, 3));
					getDriver().findElement(By.xpath("//input[@id='parentLockId4']"))
							.sendKeys(devicePin.substring(3, 4));
					logger.info("Entered Device PIN : " + devicePin);
					extent.extentLogger("devicePIN", "Entered Device PIN : " + devicePin);
					break;
				} catch (Exception e) {
					Thread.sleep(2000);
					if (trial == 4) {
						logger.error("Failed to enter device PIN");
						extent.extentLoggerFail("devicePINfail", "Failed to enter device PIN");
					}
				}
			}
		}
	}

	/**
	 * Main Function to verify player validations
	 */
	public void playerValidations(String userType) throws Exception {
		audioTrackSelection();
		extent.HeaderChildNode("Player Validation");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		verifyIsElementDisplayed(PWAHomePage.objSearchField, "Search field");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("consumptionsFreeContent");
		type(PWAHomePage.objSearchField, keyword, "Search");
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab in Search Results");
		waitTime(10000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		waitTime(6000);
		// if(userType.equals("Guest"))
		// waitForElementAndClickIfPresent(PWASubscriptionPages.objPopupCloseButton, 30,
		// "Close in Sign Up Pop Up");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,
				"Content Title").toString();
		PlayerIconVaidations();
		// Verify Player controls are not displaying after some time
		Thread.sleep(5000);
		playerControlOperations();
		playerQuality();
		screenOrientation();
		ShareFunctionality();
		AddToWatchList(userType, consumptionPageTitle);
		// WatchTrailer();
	}

	public void audioTrackSelection() throws Exception {
		HeaderChildNode("Validating the Audio Track Selection");
		// String keyword =
		// getParameterFromXML("audioTrackContent");
		String keyword = "Episode 13 - Agent Raghav";
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		typeAndGetSearchResult(PWAHomePage.objSearchField, keyword, "Search");
		waitTime(5000);
		for (int i = 0; i < 2; i++) {
			try {
				waitTime(5000);
				click(PWASearchPage.objSearchedResult(keyword), "Search Result");
				break;
			} catch (StaleElementReferenceException e) {
			}
		}
		waitTime(5000);
		directClickReturnBoolean(PWAHomePage.objCreateNewAccountPopUpClose, "Close in Sign Up Pop Up");
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		click(PWAPlayerPage.settingsBtn, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrackIcon, "Audio Track icon");
		waitTime(5000);
		int size = getDriver().findElements(PWAPlayerPage.objPlayerAudioTracksAvailable).size();
		if (size == 0) {
			logger.info("Audio Tracks unavailable");
			extent.extentLogger("quality", "Audio Tracks unavailable");
		} else {
			for (int i = 1; i <= size; i++) {
				logger.info("Audio Tracks available : "
						+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
				extent.extentLogger("audio", "Audio Tracks available : "
						+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
			}
			// Select audio tracks
			List<WebElement> unselected = findElements(PWAPlayerPage.objPlayerUnSelectedAudioTrack);
			ArrayList<String> tracks = new ArrayList<String>();
			for (int i = 0; i < unselected.size(); i++) {
				tracks.add(unselected.get(i).getText());
			}
			for (int i = 0; i < tracks.size(); i++) {
				String selectedTrackBefore = getText(PWAPlayerPage.objPlayerSelectedAudioTrack);
				logger.info("Audio Track before selection: " + selectedTrackBefore);
				extent.extentLogger("quality", "Audio Track before selection: " + selectedTrackBefore);
				String track = tracks.get(i);
				waitTime(5000);
				click(PWAPlayerPage.objPlayerUnSelectedAudioTrack(track), "Audio Track " + track);
				waitTime(5000);
				click(PWAPlayerPage.settingsBtn, "Settings icon");
				click(PWAPlayerPage.objPlayerAudioTrackIcon, "Audio Track icon");
				waitTime(5000);
				String selectedTrackAfter = getElementPropertyToString("innerText",
						PWAPlayerPage.objPlayerSelectedAudioTrack, "Selected Audio Track");
				logger.info("Audio Track after selection : " + selectedTrackAfter);
				extent.extentLogger("quality", "Quality option after selection: " + selectedTrackAfter);
				if (selectedTrackBefore.equals(selectedTrackAfter)) {
					logger.error("Audio Track selection unsuccessful");
					extent.extentLoggerFail("quality", "Audio Track selection unsuccessful");
				} else {
					logger.info("Audio Track selection successful");
					extent.extentLogger("quality", "Audio Track selection successful");
				}
			}
		}
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	/**
	 * Method to perform a direct click
	 * 
	 */
	public void directClick(By locator, String element) {
		try {
			getDriver().findElement(locator).click();
			logger.info("Clicked on element: " + element);
			extent.extentLogger("elementClicked", "Clicked on element: " + element);
		} catch (Exception e) {
			logger.error("Failed to click on element: " + element);
			extent.extentLoggerFail("elementClickFailed", "Failed to click on element: " + element);
		}
	}

	/**
	 * Function to verify Watch Trailer functionality
	 */
	public void WatchTrailer() throws Exception {
		waitForElementAndClick(PWAHomePage.objSearchBtn, 60, "Search icon");
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovie2");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		Thread.sleep(5000);
		waitForElement(PWASearchPage.objSearchedResult(keyword), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		waitForElementAndClickIfPresent(PWASubscriptionPages.objPopupCloseButton, 60, "Close in Subscribe Pop Up");
		verifyElementPresent(PWASearchPage.watchTrailer, "Watch Trailer option");
	}

	/**
	 * Function to add to watchlist
	 */
	public void AddToWatchList(String userType, String consumptionPageTitle) throws Exception {
		extent.HeaderChildNode("Add to Watch List for user: " + userType);
		// Click on Watchlist
		click(PWAPlayerPage.watchListBtn, "Add To WatchList button");
		if (userType.equals("Guest")) {
			// Verify user is Observed Login pop up
			verifyIsElementDisplayed(PWAPlayerPage.objLoginRequiredTxt, "Login Required Pop up");
			// Close the Login Popup
			click(PWAPlayerPage.objCloseBtnLoginPopup, "Close button Login Popup");
		} else {
			// Click on Hamburger menu
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			// Click on My account
			click(PWAHamburgerMenuPage.objMyAccountOption, "My Account");
			waitTime(3000);
			// Click on Watchlist
			click(PWAAddToWatchListPage.objMyWatchList, "Watch list");
			// Click on Movies tab
			Thread.sleep(3000);
			click(PWAAddToWatchListPage.objMoviesTab, "Movies tab");
			// Verify added Item is present in Watchlist
			verifyElementPresent(PWAAddToWatchListPage.objWatchListed(consumptionPageTitle),
					"Watchlisted content: " + consumptionPageTitle);
			click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove watchlist");
			waitTime(3000);
			Back(1);
		}
	}

	/**
	 * Function to verify Share Functionality
	 */
	@SuppressWarnings("rawtypes")
	public void ShareFunctionality() throws Exception {
		extent.HeaderChildNode("Share functionality Validation");
		// Verify Share option
		verifyElementPresent(PWAPlayerPage.shareBtn, "Share option");
		// Click on the Share option
		click(PWAPlayerPage.shareBtn, "Share option");
		// Verify the Share options are visible
		Thread.sleep(3000);
		System.out.println(getDriver().getContextHandles());
		getDriver().context("NATIVE_APP");
		Dimension dim = getDriver().manage().window().getSize();
		int startx = (int) (dim.width * 0.6);
		int starty = (int) (dim.height * 0.7);
		int endx = (int) (startx * 0.1);
		int endy = starty;
		for (int i = 0; i < 2; i++) {
			try {
				getDriver().findElement(PWAShowsPage.objFacebookApp).click();
				break;
			} catch (Exception e) {
				TouchAction act = new TouchAction(getDriver());
				act.press(PointOption.point(startx, starty))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(endx, endy)).release().perform();
			}
		}
		waitForElementAndClick(PWAShowsPage.objFacebookPostBtn, 10, "POST button in Facebook App");
		getDriver().context("CHROMIUM");
	}

	/**
	 * Function to verify video quality selected
	 */
	public void playerQuality() throws Exception {
		HeaderChildNode("Validating the Playback Quality");
		Thread.sleep(10000);
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		// Tap on settings Icon
		click(PWAPlayerPage.settingsBtn, "Settings icon");
		// Click on Quality icon
		click(PWAPlayerPage.qualityBtn, "Quality icon");
		waitTime(5000);
		// Select Quality from Quality menu
		int size = getDriver().findElements(PWAPlayerPage.objPlayerQualities).size();
		if (size == 0) {
			logger.info("Quality options unavailable");
			extent.extentLogger("quality", "Quality options unavailable");
		} else {
			for (int i = 1; i <= size; i++) {
				logger.info("Quality option available : "
						+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
				extent.extentLogger("quality", "Quality option available : "
						+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
			}

			// Select other qualities
			List<WebElement> unselected = findElements(PWAPlayerPage.objPlayerUnSelectedQuality);
			ArrayList<String> qualities = new ArrayList<String>();
			for (int i = 0; i < unselected.size(); i++) {
				qualities.add(unselected.get(i).getText());
			}
			for (int i = 0; i < qualities.size(); i++) {
				String selectedQualityBefore = getText(PWAPlayerPage.objPlayerSelectedQuality);
				logger.info("Quality option before selection: " + selectedQualityBefore);
				extent.extentLogger("quality", "Quality option before selection: " + selectedQualityBefore);
				String quality = qualities.get(i);
				Thread.sleep(5000);
				click(PWAPlayerPage.objPlayerUnSelectedQuality(quality), "Quality " + quality);
				Thread.sleep(5000);
				click(PWAPlayerPage.settingsBtn, "Settings icon");
				click(PWAPlayerPage.qualityBtn, "Quality icon");
				waitTime(5000);
				String selectedQualityAfter = getElementPropertyToString("innerText",
						PWAPlayerPage.objPlayerSelectedQuality, "Selected Quality");
				logger.info("Quality option after selection : " + selectedQualityAfter);
				extent.extentLogger("quality", "Quality option after selection: " + selectedQualityAfter);
				if (selectedQualityBefore.equals(selectedQualityAfter)) {
					logger.error("Quality selection unsuccessful");
					extent.extentLoggerFail("quality", "Quality selection unsuccessful");
				} else {
					logger.info("Quality selection successful");
					extent.extentLogger("quality", "Quality selection successful");
				}
			}
		}
	}

	/**
	 * Function to validate player rewind and forward functionality
	 */
	/**
	 * Function to validate player rewind and forward functionality
	 */
	public void playerControlOperations() throws Exception {
		HeaderChildNode("Validating rewind 10 seconds, forward 10 seconds and Audio icons");
		Thread.sleep(3000);
		int timeBeforeFmin = 0, timeBeforeFsec = 0, timeBeforeF = 0;
		int timeAfterFmin = 0, timeAfterFsec = 0, timeAfterF = 0;
		int timeBeforeRmin = 0, timeBeforeRsec = 0, timeBeforeR = 0;
		int timeAfterRmin = 0, timeAfterRsec = 0, timeAfterR = 0;
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		// Verify forward
		String currentDuration1 = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"current duration");
		logger.info("Time fetched before forward: " + currentDuration1);
		extent.extentLogger("timeBeforeForward", "Time fetched before forward: " + currentDuration1);
		String[] timeBeforeForward = currentDuration1.split(":");
		try {
			timeBeforeFmin = Integer.parseInt(timeBeforeForward[0]) * 60;
			timeBeforeFsec = Integer.parseInt(timeBeforeForward[1]);
			timeBeforeF = timeBeforeFmin + timeBeforeFsec;
			logger.info("Time fetched before forward in Secs: " + timeBeforeF);
			extent.extentLogger("timeBeforeForward", "Time fetched before forward in Secs: " + timeBeforeF);
		} catch (Exception e) {
			logger.error("Error in time coversion");
			extent.extentLogger("error", "Error in time coversion");
		}
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 seconds");
		Thread.sleep(2000);
		// Get the current time duration after clicking the forward button
		playerTap();
		String currentDuration2 = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"current duration");
		logger.info("Time fetched after forward: " + currentDuration2);
		extent.extentLogger("timeAfterForward", "Time fetched after forward: " + currentDuration2);
		String[] timeAfterForward = currentDuration2.split(":");
		try {
			timeAfterFmin = Integer.parseInt(timeAfterForward[0]) * 60;
			timeAfterFsec = Integer.parseInt(timeAfterForward[1]);
			timeAfterF = timeAfterFmin + timeAfterFsec;
			logger.info("Time fetched after forward in Secs: " + timeAfterF);
			extent.extentLogger("timeBeforeForward", "Time fetched after forward in Secs: " + timeAfterF);

		} catch (Exception e) {
			logger.error("Error in time coversion");
			extent.extentLogger("error", "Error in time coversion");
		}
		if (timeAfterF >= (timeBeforeF + 10) && timeAfterF <= (timeBeforeF + 15)) {
			extent.extentLogger("Verify forward button", "Playback is forwarded by 10 seconds");
			logger.info("Playback is forwarded by 10 seconds");
		} else {
			extent.extentLoggerFail("Verify forward button", "Playback forward by 10 seconds has failed");
			logger.error("Playback forward by 10 seconds has failed");
		}
		// Verify Rewind
		String currentDuration3 = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"current duration");
		logger.info("Time fetched before rewind: " + currentDuration3);
		extent.extentLogger("timeBeforeRewind", "Time fetched before rewind: " + currentDuration3);
		String[] timeBeforeRewind = currentDuration3.split(":");
		try {
			timeBeforeRmin = Integer.parseInt(timeBeforeRewind[0]) * 60;
			timeBeforeRsec = Integer.parseInt(timeBeforeRewind[1]);
			timeBeforeR = timeBeforeRmin + timeBeforeRsec;
			logger.info("Time fetched before rewind in Secs: " + timeBeforeR);
			extent.extentLogger("timeBeforeForward", "Time fetched before rewind in Secs: " + timeBeforeR);
		} catch (Exception e) {
			logger.error("Error in time coversion");
			extent.extentLogger("error", "Error in time coversion");
		}
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 seconds");
		waitTime(2000);
		// Get the current time duration after clicking the rewind button
		playerTap();
		String currentDuration4 = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"current duration");
		logger.info("Time fetched after rewind: " + currentDuration4);
		extent.extentLogger("timeAfterRewind", "Time fetched after rewind: " + currentDuration4);
		String[] timeAfterRewind = currentDuration4.split(":");
		try {
			timeAfterRmin = Integer.parseInt(timeAfterRewind[0]) * 60;
			timeAfterRsec = Integer.parseInt(timeAfterRewind[1]);
			timeAfterR = timeAfterRmin + timeAfterRsec;
			logger.info("Time fetched after rewind in Secs: " + timeAfterR);
			extent.extentLogger("timeBeforeForward", "Time fetched after rewind in Secs: " + timeAfterR);
		} catch (Exception e) {
			logger.error("Error in time coversion");
			extent.extentLogger("error", "Error in time coversion");
		}
		if (timeAfterR >= (timeBeforeR - 10) && timeAfterR < (timeBeforeR - 3)) {
			extent.extentLogger("Verify rewind button", "Playback is rewinded 10 seconds");
			logger.info("Playback is rewinded 10 seconds");
		} else {
			extent.extentLoggerFail("Verify rewind button", "Playback rewind by 10 seconds has failed");
			logger.error("Playback rewind by 10 seconds has failed");
		}
		// Verify the mute/unmute
		playerTap();
		verifyElementPresent(PWAPlayerPage.objMuteButton, "Audio Mute button");
		playerTap();
		click(PWAPlayerPage.objMuteButton, "Audio Mute button");
		verifyElementPresent(PWAPlayerPage.objUnmuteButton, "Audio Unmute button");
	}

	/**
	 * Function to verify player icons
	 */
	public void PlayerIconVaidations() throws Exception {
		waitTime(10000);
		waitForPlayerAdToComplete("Video Player");
		playerTap();
		verifyElementPresent(PWAPlayerPage.rewind10SecBtn, "Rewind 10 Seconds icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.pauseBtn, "Pause icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.forward10SecBtn, "Forward 10 Seconds icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.progressBar, "Progress bar");
		playerTap();
		verifyElementPresent(PWAPlayerPage.audioBtn, "Audio icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.totalDurationTime, "Total duration time");
		playerTap();
		verifyElementPresent(PWAPlayerPage.settingsBtn, "Settings icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.maximizeBtn, "Maximize window icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.totalDurationTime, "Total time");
		waitTime(10000);
	}

	/**
	 * Function to verify Play icon functionality
	 * 
	 * @throws Exception
	 */
	public void verifyPlayIconFunctionality(String screen) throws Exception {
		extent.HeaderChildNode("Verifying play icon functionality on carousel for : " + screen);
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		if (navigateToAnyScreen(screen)) {
			waitTime(5000);
			for (int i = 0; i < 10; i++) {
				try {
					waitForElementAndClick(PWAHomePage.objPlayBtn, 20, "Play icon");
					break;
				} catch (Exception e) {
					waitTime(1000);
					if (i == 9)
						logger.info("Failed to click");
				}
			}
			waitTime(2000);
			if (verifyElementPresent(PWAPlayerPage.objPlayerControlScreen, "Player control containing screen")) {
				logger.info("Verify play icon functionality is Pass");
				extent.extentLogger("Play btn validation", "Verify play icon functionality is Pass");
			} else {
				logger.error("Verify play icon functionality is Fail");
				extent.extentLoggerFail("Play btn validation", "Verify play icon functionality is Fail");
			}
			Back(1);
		} else {
			logger.error("Failed to validate play icon functionality on tab : " + screen);
			extent.extentLoggerFail("play", "Failed to validate play icon functionality on tab : " + screen);
		}
	}

	/**
	 * Function to verify Play icon functionality for users
	 * 
	 * @throws Exception
	 */
	public void verifyPremiumIconFunctionality(String screen, String userType) throws Exception {
		extent.HeaderChildNode("Verifying premium icon functionality On : " + screen + " for " + userType);
		boolean clicked = false;
		if (navigateToAnyScreen(screen)) {
			waitTime(5000);
			if (userType.equalsIgnoreCase("SubscribedUser")) {
				List<WebElement> getPremiumTextList = getDriver().findElements(PWAHomePage.objPlayCarousel);
				if (getPremiumTextList.size() > 0) {
					logger.info("Play button is displayed instead of Subscribe Now button for Subscribed users");
					extent.extentLogger("playbutton",
							"Play button is displayed instead of Subscribe Now button for Subscribed users");
				}
			} else {
				try {
					(new WebDriverWait(getDriver(), 40))
							.until(ExpectedConditions.elementToBeClickable(PWAHomePage.objGetPremiumGetClubButton))
							.click();
					logger.info("Clicked on Get Premium/Get Club button");
					extent.extentLogger("premiumbutton", "Clicked on Get Premium/Get Club button");
					clicked = true;
				} catch (Exception e) {
					logger.error("Failed to click on Get Premium/Get Club button");
					extent.extentLoggerFail("premiumbutton", "Failed to click on Get Premium/Get Club button");
				}
			}
			if (clicked) {
				if (userType.equalsIgnoreCase("NonSubscribedUser") || userType.equalsIgnoreCase("Guest")) {
					if (verifyElementPresent(PWAHomePage.objSubscriptionPage, "Subscription page")) {
						logger.info("Get Premium button functionality has Passed for " + screen);
						extent.extentLogger("Premium btn validation",
								"Get Premium button functionality has Passed for " + screen);
						Back(1);
					} else {
						logger.error("Get Premium button functionality has Failed for " + screen);
						extent.extentLoggerFail("Premium btn validation",
								"Get Premium button functionality has Failed for " + screen);
					}
				}
			}
		} else {
			logger.error("Failed to validate premium icon functionality on tab : " + screen);
			extent.extentLoggerFail("play", "Failed to validate premium icon functionality on tab : " + screen);
		}
	}

	/**
	 * Method to verify UI of Home page
	 */
	public void verifyUIofHomePage() throws Exception {
		extent.HeaderChildNode("Verify UI of Home Page");
		waitForElementAndClickIfPresent(PWAHamburgerMenuPage.objCloseHamburger, 20, "Close in Sign Up Pop Up");
		waitTime(5000);
		String tab = getText(PWAHomePage.objActiveTab);
		System.out.println(tab);
		if (tab.equalsIgnoreCase("Home")) {
			logger.info("Navigated to Home page");
			extent.extentLogger("Home Page", "Navigated to Home page");
		} else {
			logger.info("Not navigated to Home page");
			extent.extentLogger("Home Page", "Not navigated to Home page");
		}
		verifyElementPresent(PWAHomePage.objZeeLogo, "Zee Logo");
		verifyElementPresent(PWAHomePage.objSearchBtn, "Search button");
		verifyIsElementDisplayed(PWAHomePage.objSubscribeBtn, "Subscribe button");
		verifyElementPresentAndClick(PWAHomePage.objHamburgerMenu, "Hamburger menu");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objLoginBtn, "Login button");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objSignUpForFree, "Sign Up for free");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objCloseIcon, "Close Icon");
		waitForElementDisplayed(PWAHomePage.objDownloadIcon, 60);
		verifyIsElementDisplayed(PWAHomePage.objDownloadIcon, "Download icon");
	}

	/**
	 * PWA Subscription Suite
	 */
	public void zeePWASubscriptionSuite(String userType) throws Exception {
		if (userType.equals("SubscribedUser")) {
			ValidatingSubscriptionAndTransaction();
			ValidatingSubscribeLinks();
		} else if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			zeePWASubscriptionScenariosValidation(userType, getPlatform());
			zeePWASubscriptionFlowFromHomePageHeaderSubscribeBtn(userType, getPlatform());
		} else {
			logger.error("Incorrect userType parameter passed to method");
			extent.extentLoggerFail("incorrectUserType", "Incorrect userType parameter passed to method");
		}
	}

	/**
	 * Subscription Flow From Home Page Header Subscribe Btn Line No 89
	 */
	public void zeePWASubscriptionFlowFromHomePageHeaderSubscribeBtn() throws Exception {
		HeaderChildNode("PWA Subscription Flow From Home Page Header Subcribe Btn");
		waitTime(5000);
		verifyElementPresent(PWAHomePage.objSubscribeBtn, "Subscribe Btn in the Header");
		zeePWAGuestUserSubscriptionFlow();

	}

	/**
	 * Guest User Subscription Flow
	 */
	public void zeePWAGuestUserSubscriptionFlow() throws Exception {
		zeePWASelectPackPageValidation();
		zeePWAAccountInfoPageValidation();
		zeePWAPaymentPageValidation();

	}

	/**
	 * Non-Subscribed User Subscription Flow
	 */
	public void zeePWANonSubscribedUserSubscriptionFlow() throws Exception {
		HeaderChildNode("PWA Subscription Flow");
		zeePWASelectPackPageValidation();
		zeePWAPaymentPageValidation();

	}

	/**
	 * Payment Page Validation Validate that user is navigated to Payment options
	 * screen post successful sign in/sign up - Line No. 105
	 */
	public void zeePWAPaymentPageValidation() throws Exception {
		HeaderChildNode("Payment Page Validation and Selection of PayTm Payment option");
		// Scenario no. 103
		waitTime(8000);
		verifyElementPresent(PWASubscriptionPages.objPaymentHighlighted, "Payment Section");
		waitTime(3000);
		zeePWASelectedPackDisplayValidation();
		verifyElementPresent(PWASubscriptionPages.objAccountInfoText, "Account Info Text in Payments Section");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objAccountInfoDetails, "Account Info Details in Payments Section");
		waitTime(3000);
		Swipe("UP", 1);
		waitTime(3000);
		getDriver().context("NATIVE_APP");
		verifyElementPresent(PWASubscriptionPages.objMobileCreditDebitCardOption, "'Credit / Debit Card' option");
		Swipe("UP", 1);
		waitTime(2000);
		// verifyElementPresentAndClick(PWASubscriptionPages.objMobileWalletsOption,
		// "'Wallets' option");
		// verifyElementPresentAndClick(PWASubscriptionPages.objMobilePaytmOption,
		// "Paytm option");
		// verifyElementPresent(PWASubscriptionPages.objMobilePayTMRecurrenceMessage,"Recurrence
		// Message 'You will be charged every billing cycle until you cancel'");
		getDriver().context("CHROMIUM");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	/**
	 * Selected Pack Display Validation Validate that selected pack information is
	 * displayed on left side. - Line No. 103
	 */
	public void zeePWASelectedPackDisplayValidation() throws Exception {
		// Scenario no. 103
		// verifyElementPresent(PWASubscriptionPages.objSelectedPackText, "Selected Pack
		// Text");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objSelectedPackName, "Selected Pack Name");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objSelectedPackDuration, "Selected Pack Duration");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objSelectedPackDescription, "Selected Pack Description");
		waitTime(3000);

	}

	/**
	 * Account Info Page Validation Validate that selected pack information is
	 * displayed on left side. - Line No. 103 Validate that guest user is able to
	 * sign in/sign up from account info screen - Line No. 104
	 */
	public void zeePWAAccountInfoPageValidation() throws Exception {
		HeaderChildNode("Account Info Page Validation");
		verifyElementPresent(PWASubscriptionPages.objAccountInfoHighlighted, "Account Info Section");
		waitTime(3000);
		// Scenario no. 103
		zeePWASelectedPackDisplayValidation();
		verifyElementPresent(PWASubscriptionPages.objProceedBtnNotHighlighted,
				"Proceed Btn in Account Info Page Not Highlighted");
		// Scenario no. 104
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objEmailIDTextField, "Email ID Text Field");
		waitTime(3000);
		String email = RandomStringGenerator(6) + "@c.com";
		type(PWASubscriptionPages.objEmailIDTextField, email, "Email Id");
		hideKeyboard();
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnHighlighted,
				"Proceed Btn in Account Info Page Highlighted");
		waitTime(3000);
		// Password Popup
		verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objProceedBtnDisabled, "Disabled Proceed Btn");
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
		waitTime(3000);
		type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
		getDriver().context("NATIVE_APP");
		hideKeyboard();
		getDriver().context("CHROMIUM");
		waitTime(9000);
		verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtn, "Enabled Proceed Btn");
		try {
			getDriver().findElement(PWASubscriptionPages.objProceedBtn).click();
		} catch (Exception e) {
		} // Required for Vivo phone because keyboard shows up
		waitTime(3000);

	}

	/**
	 * Select Pack Page Validation
	 */
	public void zeePWASelectPackPageValidation() throws Exception {
		HeaderChildNode("Select Pack Page Validation");
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		waitTime(3000);
		String selectedPackCategory = findElement(PWASubscriptionPages.objPackCategoryTabSelected).getText();
		System.out.println("Selected Pack Category is: " + selectedPackCategory);
		waitTime(3000);
		String defaultSelectedPlan = findElement(PWASubscriptionPages.objSelectedSubscriptionPlanAmount).getText();
		System.out.println("Plan Selected By Default is: " + defaultSelectedPlan);
		waitTime(3000);
		ScrollToElement(PWASubscriptionPages.objContinueBtn, "Continue");
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue Btn");
		waitTime(5000);

	}

	/**
	 * Method to scroll to element
	 */
	public void ScrollToElement(By Locator, String validationText) throws Exception {

		for (int i = 1; i <= 10; i++) {
			if (verifyElementPresent(Locator, validationText) == true) {
				break;
			}
			waitTime(2000);
			swipeALittle("up", 1);
		}
	}

	/**
	 * Method to swipe a little
	 */
	public void swipeALittle(String dire, int count) throws Exception {
		if (dire.equalsIgnoreCase("UP")) {

			for (int j = 0; j < count; j++) {
				Dimension size = getDriver().manage().window().getSize();
				int starty = (int) (size.height * 0.40);
				int endy = (int) (size.height * 0.39);
				int startx = size.width / 2;
				// getDriver().swipe(startx, starty, startx, endy, 3000);
				touchAction.press(PointOption.point(startx, starty))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(10000)))
						.moveTo(PointOption.point(startx, endy)).release().perform();

				logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				extent.extentLogger("SwipeUp",
						"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

			}
		}
	}

	/**
	 * Subscription Scenarios Validation
	 */
	public void zeePWASubscriptionScenariosValidation(String userType, String platform) throws Exception {
		// Scenario no. 89
		HeaderChildNode("Scenario: Navigate to Subscription Flow From Home Page Header Subcribe Btn");
		waitTime(5000);
		click(PWAHomePage.objSubscribeBtnTopHeader, "Subscribe Button in the Header");
		waitTime(5000);
		zeeSubscriptionPageValidationAndNavigateToHomePage();
		// Scenario no. 90,98
		HeaderChildNode("Scenario: Navigate to Subscription Flow from Home Page Get Premium CTA On Carousel");
		waitTime(3000);
		for (int i = 0; i < 10; i++) {
			try {
				(new WebDriverWait(getDriver(), 40))
						.until(ExpectedConditions.elementToBeClickable(PWAHomePage.objGetPremiumGetClubButton)).click();
				logger.info("Clicked on Get Premium/Get Club button");
				extent.extentLogger("", "Clicked on Get Premium/Get Club button");
				break;
			} catch (Exception e) {
				waitTime(1000);
				if (i == 9)
					logger.info("Failed to click");
			}
		}
		waitTime(10000);
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		// Scenario no. 98
		zeePWAPromoCodeValidationInSelectPackPage();
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
		if (userType.equalsIgnoreCase("Guest")) {
			// Scenario no. 96
			HeaderChildNode(
					"Scenario: Navigate to Subscription Flow From 'Buy Subscription' option under My plans in hamburger menu");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Btn");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objBuySubscriptionOption,
					"Buy Subscribe Option in Hamburger Menu");
			zeeSubscriptionPageValidationAndNavigateToHomePage();
			// Scenario no. 97
			HeaderChildNode(
					"Scenario: Navigate to Subscription Flow From 'Have a Prepaid code' option under My plans in hamburger menu");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Btn");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHaveAPrepaidCode,
					"Have A Prepaid Code? Option in Hamburger Menu");
			waitTime(5000);
			zeeSubscriptionPageValidationAndNavigateToHomePage();
		}
		// Scenario no. 91,92,94
		HeaderChildNode(
				"Scenario: Navigate to Subscription Flow From Adoric Popup/Subscribe Pop Up On Playing Premium Content");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovieNoTrailer2");
		zeeSearchForContentAndClickOnFirstResult(keyword);
		waitTime(5000);
		zeeVerifyGetPremiumPopup();
		waitTime(5000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeeAccountInfoPageValidationAndNavigateToHomePage();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePaymentPageValidationAndNavigateToHomePage();
		}

		// Scenario no. 93
		HeaderChildNode("Scenario: Navigate to Subscription Flow From Player In-line Subscribe link on Player");
		zeeSearchForContentAndClickOnFirstResult(keyword);
		waitTime(10000);
		verifyElementPresent(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop up Title");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Popup Close Button");
		waitTime(5000);
		verifyElementPresentAndClick(PWAPlayerPage.objSubscribeNowLink, "In-Line Subscribe Link on Player");
		zeeVerifyGetPremiumPopup();
		waitTime(5000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeeAccountInfoPageValidationAndNavigateToHomePage();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePaymentPageValidationAndNavigateToHomePage();
		}

		// Scenario no. 95
		HeaderChildNode(
				"Scenario: Navigate to Subscription Flow From Subscription Get premium CTA below the player at consumption screen");
		String keyword1 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovieWithTrailer");
		zeeSearchForContentAndClickOnFirstResult(keyword1);
		waitTime(5000);
		waitForElementAndClick(PWAPlayerPage.objGetPremiumCTABelowPlayerScreen, 30,
				"Subscribe button below the Player");
		waitTime(5000);
		zeeVerifyGetPremiumPopup();
		waitTime(5000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeeAccountInfoPageValidationAndNavigateToHomePage();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePaymentPageValidationAndNavigateToHomePage();
		}
	}

	/**
	 * Promo code Validation in Select Pack Page Subscription Flow using promo codes
	 * to verify if the user is getting discounted price on plans are not - Line No
	 * 98
	 */
	public void zeePWAPromoCodeValidationInSelectPackPage() throws Exception {
		HeaderChildNode("Promo code Validation in Select Pack Page");
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objHaveACode, "'Have A Code?' field");
		waitTime(3000);
		type(PWASubscriptionPages.objHaveACode, "pnb20", "'Have A Code?' field");
		hideKeyboard();
		waitTime(5000);
		click(PWASubscriptionPages.objApplyBtn, "Apply Button");
		waitTime(5000);
		try {
			getDriver().findElement(PWASubscriptionPages.objApply).click();
		} catch (Exception e) {
		} // Added for Vivo phone
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objAppliedSuccessfullyMessage, "Applied Successfully Message");

	}

	/**
	 * PWA Subscription Page Validation
	 */
	public void zeeSubscriptionPageValidationAndNavigateToHomePage() throws Exception {
		// HeaderChildNode("PWA Subscription Page Validation and Navigate to Home
		// Page");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		waitTime(7000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
	}

	/**
	 * PWA Subscription Page Validation
	 */
	public void zeeSubscriptionPageValidationAndNavigateToHomePage(String origin) throws Exception {
		HeaderChildNode("Subscription Page Validation navigation from: " + origin);
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		waitTime(7000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
	}

	/**
	 * PWA Account Info Page Validation
	 */
	public void zeeAccountInfoPageValidationAndNavigateToHomePage() throws Exception {
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objAccountInfoHighlighted, "Account Info Section");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");

	}

	/**
	 * PWA Payment Page Validation
	 */
	public void zeePaymentPageValidationAndNavigateToHomePage() throws Exception {
		HeaderChildNode("PWA Payment Page Validation and Navigate to Home Page");
		waitTime(10000);
		verifyElementPresent(PWASubscriptionPages.objPaymentHighlighted, "Payment Section");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");

	}

	/**
	 * Verify Subscribe Pop Up
	 */
	public void zeeVerifyGetPremiumPopup() throws Exception {
		waitTime(10000);
		verifyElementPresent(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop up Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objDefaultSelectedPack, "Default Selected Package");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Popup Proceed Btn");
	}

	/**
	 * Subscription Flow From Home Page Header Subscribe Btn Line No 89
	 */
	public void zeePWASubscriptionFlowFromHomePageHeaderSubscribeBtn(String userType, String platform)
			throws Exception {
		HeaderChildNode("PWA Subscription Flow From Home Page Header Subcribe Btn");
		// Scenario no. 89
		waitTime(5000);
		click(PWAHomePage.objSubscribeBtnTopHeader, "Subscribe Btn in the Header");
//		driver.findElement(PWAHomePage.objSubscribeBtn).click();			
		waitTime(5000);
		if (userType.equals("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equals("Non Subscribed User")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout(platform);

	}

	/**
	 * Navigate back to paytm wallet and logout
	 */
	public void navigateBackFromPayTmWalletAndLogout(String platform) throws Exception {
		waitTime(5000);
		Back(1);
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
		// logout();
	}

	/**
	 * Subscription Flow From Home Page Get Premium CTA on Carousel Line No 90
	 */
	public void zeePWASubscriptionFlowFromHomePageGetPremiumCTAOnCarousel(String userType, String platform)
			throws Exception {
		HeaderChildNode("PWA Subscription Flow From Home Page Get Premium CTA On Carousel");

		// Scenario no. 90
		waitTime(5000);
		verifyElementPresent(PWAHomePage.objGetPremium, "Get Premium CTA on Carousel");
		clickDirectly(PWAHomePage.objGetPremium, "Get Premium CTA on Carousel");
		waitTime(5000);
		if (userType.equals("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equals("Non Subscribed User")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout(platform);

	}

	/**
	 * Subscription Flow From Adoric Popup Line No 91 Subscription Flow From
	 * Subcribe Btn On Playing Premium Content Line No 92 Subscription Flow From
	 * Subscribe popup on playing Before TV content Line No 94
	 */
	public void zeePWASubscriptionFlowFromGetPremiumPopupOnPlayingPremiumContent(String userType, String platform)
			throws Exception {
		HeaderChildNode("PWA Subscription Flow From Adoric Popup/Subscribe Pop Up On Playing Premium Content");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovie2");
		// Scenario no. 91,92,94
		zeeSearchForContentAndClickOnFirstResult(keyword);
		zeeVerifyGetPremiumPopup();
		waitTime(5000);
		if (userType.equals("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equals("Non Subscribed User")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout(platform);

	}

	/**
	 * Verify Subscription Links
	 */
	public void ValidatingSubscribeLinks() throws Exception {
		HeaderChildNode("Validating Subscription Link");
		Thread.sleep(10000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovie2");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Field");
		waitTime(5000);
		hideKeyboard();
		waitTime(3000);
		click(PWASearchPage.objSearchResultPremiumContent, "Premium content");
		waitTime(10000);
		try {
			waitForElementDisplayed(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer, 30);
		} catch (Exception e) {
			System.out.println("GetPremiumCTAbelowPlayer is not displayed");
		}
		// Validating GET PREMIUM CTA BUTTON below Player
		HeaderChildNode("Validating Get Premium CTA below player and Subscribe Pop Up");
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer,
				"GET PREMIUM CTA BELOW PLAYER ") == true) {
			logger.error(
					"Verify the Get premium CTA below the player at consumption screen is not displayed to subscribed user is Fail");
			extent.extentLoggerFail("ctaDisplayed",
					"Verify the Get premium CTA below the player at consumption screen is not displayed to subscribed user is Fail");
			click(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer, "GET PREMIUM CTA BELOW PLAYER");
			Thread.sleep(3000);
			verifyElementPresent(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up");
			verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
		}
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objSubscribeNowLink, "Player In-line Subscribe link");
	}

	/**
	 * Verify Subscription and Transaction
	 */
	public void ValidatingSubscriptionAndTransaction() throws Exception {
		extent.HeaderChildNode("Validation of Subscription and Transaction");
		waitTime(5000);
		waitTime(5000);
		waitTime(5000);
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[text()='Get premium']"));
		System.out.println(ele.size());
		if (ele.size() == 0) {
			System.out.println("Get Premium on Carousel is not displayed");
		} else {
			for (int i = 1; i < ele.size(); i++) {
				verifyElementExist1(ele.get(i), "Get Premium on Carousel");
			}
		}
		verifyIsElementDisplayed(PWAHomePage.objSubscribeBtn, "Subscribe Button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Button");
		Thread.sleep(3000);
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objPlans, "My Plans") == true) {
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objBuySubscription, "Buy Subscription");
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objHaveAPrepaidCode, "Have a Prepaid Code");
		}
		boolean myAccountPresent = verifyIsElementDisplayed(PWAHamburgerMenuPage.objMyAccount, "My Account");
		if (myAccountPresent == true) {
			click(PWAHamburgerMenuPage.objMyAccount, "My Account");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMySubscription, "MySubscription");
			Thread.sleep(3000);
			verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionPage, "MySubscription Page");
			boolean NoSubscriptionActivePresent = verifyIsElementDisplayed(PWAHamburgerMenuPage.objNoActiveSubscription,
					"No Active Subscription");
			if (NoSubscriptionActivePresent == true) {
				verifyIsElementDisplayed(PWAHamburgerMenuPage.objMySubscriptionItem, "MySubscription Item");
				verifyIsElementDisplayed(PWAHamburgerMenuPage.objMySubscriptionPackName, "MySubscription Name");
				verifyIsElementDisplayed(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus, "My Subscription Status");
			} else {
				if (verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionItem, "MySubscription Item") == true) {
					if (verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionPackName,
							"MySubscription Name") == true) {
						System.out.println(getText(PWAHamburgerMenuPage.objMySubscriptionPackName));
					}
					if (verifyElementPresent(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus,
							"My Subscription Status") == true) {
						System.out.println(getText(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus));
					}
				}
			}
			getDriver().navigate().back();
			Thread.sleep(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Button");
			Thread.sleep(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyAccount, "My Account");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyTransactions, "MyTransaction");
			Thread.sleep(3000);
			verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionPage, "MyTransaction Page");
			boolean NoTransactionPresent = verifyIsElementDisplayed(PWAHamburgerMenuPage.objNoTransaction,
					"No Transactions");
			if (NoTransactionPresent == true) {
				verifyIsElementDisplayed(PWAHamburgerMenuPage.objMyTransactionDate, "MyTransaction Date");
				verifyIsElementDisplayed(PWAHamburgerMenuPage.objMyTransactionPackName, "MyTransaction Name");
				verifyIsElementDisplayed(PWAHamburgerMenuPage.objMyTransactionPackStatus, "MyTransaction Status");
			} else {
				if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionDate, "MyTransaction Date") == true) {
					if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionPackName,
							"MyTransaction Name") == true) {
						System.out.println(getText(PWAHamburgerMenuPage.objMyTransactionPackName));
					}
					if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionPackStatus,
							"MyTransaction Status") == true) {
						System.out.println(getText(PWAHamburgerMenuPage.objMyTransactionPackStatus));
					}
				}
			}
		}
	}

	/** =====================TEJAS - CAROUSEL MODULE=========================== */
	public void ValidatingCarousalinalltabs(String UserType) throws Exception {

		switch (UserType) {
		case "Guest":
			extent.HeaderChildNode("User Type Guest");
			System.out.println("User Type Guest");
//			enterURLInBrowser("chrome", "https://newpwa.zee5.com");
			carouseldots("carouselDots", "home");
			carouseldots("carouselDots", "kids");
			ContinuewatchingTray(UserType);

			break;

		case "NonSubcribedUser":
			extent.HeaderChildNode("User Type Loggedin User");
			System.out.println("User Type Loggedin User");
//			ZeePWALogin("Mobile", "Nonsubscribed");
			carouseldots("carouselDots", "home");
			carouseldots("carouselDots", "kids");
			ContinuewatchingTray(UserType);
			break;

		case "SubcribedUser":
			extent.HeaderChildNode("User Type Subcribed User");
			System.out.println("User Type Subcribed User");
//			ZeePWALogin("E-mail", "Subscribed");
			carouseldots("carouselDots", "home");
			carouseldots("carouselDots", "kids");
			ContinuewatchingTray(UserType);

		}

	}

	public void newsTrayValidation() throws Exception {
		extent.HeaderChildNode("Verifing the trays displayed in News Tab");
		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);
		Response resp = ResponseInstance.getResponseForPages("news", languageSmallText);
		List<String> apiTitleList = new LinkedList<String>();
		List<String> apitotaltrays = resp.jsonPath().getList("buckets");
		System.out.println(apitotaltrays.size());
		for (int i = 1; i < apitotaltrays.size(); i++) {
			String traytitle = resp.jsonPath().getString("buckets[" + i + "].title");
			apiTitleList.add(traytitle);
		}
		logger.info("Trays fetched from API: " + apiTitleList);
		extent.extentLogger("apiTrays", "Trays fetched from API: " + apiTitleList);
		for (int j = 0; j < apiTitleList.size(); j++) {
			List<WebElement> trayTitlesUI = findElements(By.xpath("//*[@class='titleLink']"));
			ArrayList<String> trayTitleUIString = new ArrayList<String>();
			for (int i = 0; i < trayTitlesUI.size(); i++) {
				trayTitleUIString.add(trayTitlesUI.get(i).getText());
			}
			if (trayTitleUIString.contains(apiTitleList.get(j))) {
				logger.info("API title: " + apiTitleList.get(j) + " is displayed in UI");
				extent.extentLogger("Tray validation", "API title: " + apiTitleList.get(j) + " is displayed in UI");

			}
			PartialSwipe("UP", 1);
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");

	}

	public void staticPagesandFooterSectionValidation(String userType) throws Exception {
		extent.HeaderChildNode("Static Pages and Footer Validation for : " + userType);
		logger.info("Static Pages and Footer Validation for : " + userType);
		extent.extentLogger("static", "Static Pages and Footer Validation for : " + userType);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		if (userType.contentEquals("Guest") || userType.contentEquals("NonSubscribedUser")) {
			AboutUsScreenValidation();
			HelpCenterScreenValidation();
			TermsOfUseValiadtion();
			PrivacyPolicyValidation();
			BuildVersionValidation();
			FooterSectionValidation(userType);
			contentLanguagewithDisplayLanguage();
		} else if (userType.contentEquals("SubscribedUser")) {
			SubscribedUserAboutUsScreenValidation();
			HelpCenterScreenValidation();
			SubscribedUserTermsOfUseValidation();
			SubscribedUserPrivacyPolicyValidation();
			// BuildVersionValidation();
			// FooterSectionValidation(userType);
			// contentLanguagewithDisplayLanguage();
		}
	}

	public void searchScreen(String UserType) throws Exception {
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovie");
		landingOnSearchScreen();
		voiceInput();
		movieSearchResult(keyword);
		liveTv();// Live Show Search from Live TV tab
		searchLiveTvChannels();// Live Channel Search from Live TV tab
		searchLiveFromChannelGuide();// from gaps //Live Show Search from Channel Guide tab
		verifyRecentSearches();// from gaps
		searchLanguage("Kannada");
		searchGenre("Comedy");
		partlySpeltSearchResult("Natas");
		emptystateScreen();
	}

	public void verifyRecentSearches() throws Exception {
		String keywordA = "Punar Vivaha";
		String keywordB = "Gattimela";
		// 4. Validate that Recent search does not appear by only searching a keyword.
		extent.HeaderChildNode("Recent Search: Validate keyword does not get added unless Search result is clicked");
		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		type(PWASearchPage.objSearchEditBox, keywordA, "Search edit box");
		waitTime(4000);
		verifyElementPresent(PWASearchPage.objSearchedResult(keywordA), "Search Result " + keywordA);
		click(PWASearchPage.objBackButton, "Back in Search box");
		click(PWAHomePage.objSearchBtn, "Search icon");
		if (verifyIsElementDisplayed(PWASearchPage.recentSearchItem1, "First Recent Search Item")) {
			if (getText(PWASearchPage.recentSearchItem1).equals(keywordA)) {
				logger.error(keywordA + " is displayed under Recent Searches even though search result wasnt clicked");
				extent.extentLoggerFail("",
						keywordA + " is displayed under Recent Searches even though search result wasnt clicked");
			} else {
				logger.info(keywordA + " is not the first item under Recent Searches");
				extent.extentLogger("", keywordA + " is not the first item under Recent Searches");
				logger.info(
						"Verified that keyword does not get displayed under Recent Search unless Search result is clicked");
				extent.extentLogger("",
						"Verified that keyword does not get displayed under Recent Search unless Search result is clicked");
			}
		} else {
			logger.info(
					"Verified that keyword does not get displayed under Recent Search unless Search result is clicked");
			extent.extentLogger("",
					"Verified that keyword does not get displayed under Recent Search unless Search result is clicked");
		}
		// 1. Validate "Recent Searches" text.
		// 2. Validate "Clear All" text.
		// 5. Validate that Recent Search appears only if we tap on a search result.
		extent.HeaderChildNode(
				"Recent Search: Validate 'Recent Searches' text and 'Clear All' text after tapping on Search Result");
		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		type(PWASearchPage.objSearchEditBox, keywordB, "Search edit box");
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchShowsTab, "Shows tab");
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keywordB), "Search Result " + keywordB);
		waitTime(2000);
		click(PWAHomePage.objSearchBtn, "Search icon");
		verifyElementPresent(PWASearchPage.recentSearchsLabel, "Recent Searches tray");
		verifyElementPresent(PWASearchPage.objClearAllTextofRecentSearches, "Clear All text");
		if (verifyIsElementDisplayed(PWASearchPage.recentSearchItem1, "First Recent Search Item")) {
			if (getText(PWASearchPage.recentSearchItem1).equals(keywordB)) {
				logger.info(keywordB + " is displayed under Recent Searches as expected");
				extent.extentLogger("", keywordB + " is displayed under Recent Searches as expected");
			} else {
				logger.error(keywordB + " is not the first item under Recent Searches");
				extent.extentLoggerFail("", keywordB + " is not the first item under Recent Searches");
			}
		} else {
			logger.error("There is no item under Recent Searches");
			extent.extentLoggerFail("", "There is no item under Recent Searches");
		}
		// 6. Validate the order of the Recent Searches for all the 5 keywords. (latest
		// on top and descending order)
		String keyword1 = "Kundali Bhagya";
		String keyword2 = "Kumkum Bhagya";
		String keyword3 = "Paaru";
		String keyword4 = "Robin Hood";
		String keyword5 = "Jothe Jotheyali";
		String keyword6 = "Naaginn";
		extent.HeaderChildNode("Recent Search: Validate only 5 keywords get added and order of keywords");
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add(keyword1);
		keywords.add(keyword2);
		keywords.add(keyword3);
		keywords.add(keyword4);
		keywords.add(keyword5);
		keywords.add(keyword6);
		for (int i = 0; i <= 5; i++) {
			// handle mandatory pop up
			mandatoryRegistrationPopUp(user);
			String key = keywords.get(i).toString();
			logger.info("Search for keyword " + (i + 1));
			extent.extentLogger("", "Search for keyword " + (i + 1));
			clearField(PWASearchPage.objSearchEditBox, "Search Bar");
			type(PWASearchPage.objSearchEditBox, key, "Search edit box");
			verifyElementPresentAndClick(PWASearchPage.objSearchShowsTab, "Shows tab");
			waitTime(5000);
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(key), "Search Result " + key);
			waitTime(2000);
			click(PWAHomePage.objSearchBtn, "Search icon");
		}
		waitTime(5000);
		List<WebElement> recentSearchItems = findElements(PWASearchPage.recentSearchItems);
		int size = recentSearchItems.size();
		if (size == 5) {
			logger.info("5 items are displayed under Recent Searches as expected");
			extent.extentLogger("", "5 items are displayed under Recent Searches as expected");
		} else {
			logger.error(size + " items are displayed under Recent Searches instead of 5");
			extent.extentLoggerFail("", size + " items are displayed under Recent Searches instead of 5");
		}
		// verify order
		logger.info("Expected Order Required: " + keyword6 + ", " + keyword5 + ", " + keyword4 + ", " + keyword3 + ", "
				+ keyword2);
		extent.extentLogger("", "Expected Order Required: " + keyword6 + ", " + keyword5 + ", " + keyword4 + ", "
				+ keyword3 + ", " + keyword2);
		String actual = getText(PWASearchPage.recentSearchItem(1));
		String expected = keywords.get(5);
		if (actual.equals(expected)) {
			logger.info("Recent Searches Order is maintained for item 5: " + actual);
			extent.extentLogger("", "Recent Searches Order is maintained for item 5: " + actual);
		} else {
			logger.error(actual + " is displayed instead of " + expected
					+ ", Recent Searches Order not maintained for item 5");
			extent.extentLoggerFail("", actual + " is displayed instead of " + expected
					+ ", Recent Searches Order not maintained for item 5");
		}
		actual = getText(PWASearchPage.recentSearchItem(2));
		expected = keywords.get(4);
		if (actual.equals(expected)) {
			logger.info("Recent Searches Order is maintained for item 4: " + actual);
			extent.extentLogger("", "Recent Searches Order is maintained for item 4: " + actual);
		} else {
			logger.error(actual + " is displayed instead of " + expected
					+ ", Recent Searches Order not maintained for item 4");
			extent.extentLoggerFail("", actual + " is displayed instead of " + expected
					+ ", Recent Searches Order not maintained for item 4");
		}
		actual = getText(PWASearchPage.recentSearchItem(3));
		expected = keywords.get(3);
		if (actual.equals(expected)) {
			logger.info("Recent Searches Order is maintained for item 3: " + actual);
			extent.extentLogger("", "Recent Searches Order is maintained for item 3: " + actual);
		} else {
			logger.error(actual + " is displayed instead of " + expected
					+ ", Recent Searches Order not maintained for item 3");
			extent.extentLoggerFail("", actual + " is displayed instead of " + expected
					+ ", Recent Searches Order not maintained for item 3");
		}
		actual = getText(PWASearchPage.recentSearchItem(4));
		expected = keywords.get(2);
		if (actual.equals(expected)) {
			logger.info("Recent Searches Order is maintained for item 2: " + actual);
			extent.extentLogger("", "Recent Searches Order is maintained for item 2: " + actual);
		} else {
			logger.error(actual + " is displayed instead of " + expected
					+ ", Recent Searches Order not maintained for item 2");
			extent.extentLoggerFail("", actual + " is displayed instead of " + expected
					+ ", Recent Searches Order not maintained for item 2");
		}
		actual = getText(PWASearchPage.recentSearchItem(5));
		expected = keywords.get(1);
		if (actual.equals(expected)) {
			logger.info("Recent Searches Order is maintained for item 1: " + actual);
			extent.extentLogger("", "Recent Searches Order is maintained for item 1: " + actual);
		} else {
			logger.error(actual + " is displayed instead of " + expected
					+ ", Recent Searches Order not maintained for item 1");
			extent.extentLoggerFail("", actual + " is displayed instead of " + expected
					+ ", Recent Searches Order not maintained for item 1");
		}
		// 3. Validate 5 keywords get added. Search for 6 but only 5 recent searches
		// must be present.
		if (!verifyIsElementDisplayed(PWASearchPage.recentSearchItem(keywords.get(0)),
				"First searched keyword " + keywords.get(0))) {
			logger.info("Keyword searched initially is not displayed, FIFO maintained");
			extent.extentLogger("", "Keyword searched initially is not displayed, FIFO maintained");
		} else {
			logger.error(
					"Keyword searched initially is displayed even after searching 5 keywords after that, FIFO not maintained");
			extent.extentLoggerFail("",
					"Keyword searched initially is displayed even after searching 5 keywords after that, FIFO not maintained");
		}
		// 8. Click on a recent search-> the keyword must fill the search edit field and
		// search results must be displayed.
		// 9. Click on a recent search->Click on a search result and navigate back-> The
		// Recent Search keyword order must change and be displayed at the top.
		extent.HeaderChildNode("Recent Search: Validate click on Recent search Keyword functionality");
		String recent0 = getText(PWASearchPage.recentSearchItem(5));
		click(PWASearchPage.recentSearchItem(5), "Recent Searches Bottom most item: " + recent0);
		verifyElementPresentAndClick(PWASearchPage.objSearchShowsTab, "Shows tab");
		waitTime(5000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(recent0), "Search Result " + recent0);
		waitTime(2000);
		click(PWAHomePage.objSearchBtn, "Search icon");
		if (verifyElementDisplayed(PWASearchPage.recentSearchItem(recent0, 1))) {
			logger.info("Recent Searches item " + recent0 + " in top position");
			extent.extentLogger("", "Recent Searches item " + recent0 + " in top position");
			logger.info("Verified the clicked Recent Searches item takes the top most position under Recent Searches");
			extent.extentLogger("",
					"Verified the clicked Recent Searches item takes the top most position under Recent Searches");
		} else {
			logger.error("Clicked Recent Searches item has not taken the top most position, FIFO not maintained");
			extent.extentLoggerFail("",
					"Clicked Recent Searches item has not taken the top most position, FIFO not maintained");
		}
		// 7. Validate Clear All functionality-> Keywords, Recent Searches Text, Clear
		// All must not be displayed after clear all functionality is performed. Only
		// Trending search must be present after clear all functionality.
		click(PWASearchPage.objClearAllTextofRecentSearches, "Clear All text");
		waitTime(2000);
		if (verifyIsElementDisplayed(PWASearchPage.recentSearchsLabel, "Recent Searches tray")) {
			logger.error("Recent Searches tray is displayed even after clicking Clear All text");
			extent.extentLoggerFail("", "Recent Searches tray is displayed even after clicking Clear All text");
		}
		if (verifyIsElementDisplayed(PWASearchPage.objClearAllTextofRecentSearches, "Clear All text")) {
			logger.error("Clear All text is displayed even after clicking Clear All text");
			extent.extentLoggerFail("", "Clear All text is displayed even after clicking Clear All text");
		}
		recentSearchItems = findElements(PWASearchPage.recentSearchItems);
		size = recentSearchItems.size();
		if (size > 0) {
			logger.error(size + "keywords found under Recent Searches");
			extent.extentLoggerFail("", size + "keywords found under Recent Searches");
		} else {
			logger.info("No keywords found under Recent Searches");
			extent.extentLogger("", "No keywords found under Recent Searches");
		}
	}

	public void searchLiveFromChannelGuide() throws Exception {
		extent.HeaderChildNode("Fetch Live Show from Channel Guide and verify Search");
		click(PWASearchPage.objBackButton, "Back in Search box");
		reloadHome();
		navigateToAnyScreen("Live TV");
		waitforLiveTabToLoad();
		verifyElementPresentAndClick(PWALiveTVPage.objChannelGuideToggle, "Channel Guide");
		String liveshow = null;
		boolean foundShow = false;
		waitforChannelGuideToLoad();
		List<WebElement> onGoingShows = findElements(PWALiveTVPage.objOngoingLiveTvShowTitles);
		for (int i = 0; i < onGoingShows.size(); i++) {
			try {
				liveshow = onGoingShows.get(i).getText();
				System.out.println(liveshow);
				if (liveshow != null && !liveshow.equals("")) {
					foundShow = true;
					break;
				}
			} catch (Exception e) {
			}
		}
		if (foundShow == true) {
			logger.info("Live Show title fetched from Channel Guide screen : " + liveshow);
			extent.extentLogger("", "Live Show title fetched from Channel Guide screen : " + liveshow);
			click(PWAHomePage.objSearchBtn, "Search icon");
			type(PWASearchPage.objSearchEditBox, liveshow + "\n", "Search edit box");
			waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);
			verifyElementPresent(PWASearchPage.objSearchedResult(liveshow), "Search Result " + liveshow);
			verifyElementPresent(PWALiveTVPage.objLiveLabel(liveshow), "LIVE Label for Search result " + liveshow);
			verifyElementPresent(PWALiveTVPage.objLiveProgressBar(liveshow),
					"Progress Bar for Search result " + liveshow);
		} else {
			logger.error("No Ongoing Show in Channel Guid screen");
			extent.extentLoggerFail("", "No Ongoing Show in Channel Guid screen");
		}
	}

	/**
	 * Validating Search result screen
	 */
	public void searchResultScreen(String title) throws Exception {
		// gap covered
		extent.HeaderChildNode("Validating search results on entering less than 3 characters");
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, "ze\n", "Search bar");
		waitTime(2000);
		if (verifyIsElementDisplayed(PWASearchPage.objSearchResultScreen, "Search results")) {
			logger.error("Search results are displayed when user enters less than 3 characters");
			extent.extentLoggerFail("Search result screen",
					"Search results are displayed when user enters less than 3 characters");

		} else {
			logger.info("Search results are not displayed when user enters less than 3 characters, expected behavior");
			extent.extentLogger("Search result screen",
					"Search results are not displayed when user enters less than 3 characters, expected behavior");
		}
		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		// gap covered
		extent.HeaderChildNode("Validating search results on entering 3 characters");
		type(PWASearchPage.objSearchEditBox, "mum\n", "Search bar");
		waitTime(4000);
		if (verifyIsElementDisplayed(PWASearchPage.objSearchResultScreen, "Search results")) {
			logger.info("Search results are displayed when user enters 3 characters, expected behavior");
			extent.extentLogger("Search result screen",
					"Search results are displayed when user enters 3 characters, expected behavior");
		} else {
			logger.error("Search results are not displayed when user enters 3 characters");
			extent.extentLoggerFail("Search result screen",
					"Search results are not displayed when user enters 3 characters");
		}
		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		extent.HeaderChildNode("Validating search results on entering more than 3 characters");
		type(PWASearchPage.objSearchEditBox, title + "\n", "Search bar");
		waitTime(3000);
		hideKeyboard();
		Thread.sleep(5000);
		String enteredValue = getAttributValue("value", PWASearchPage.objSearchEditBox);
		if (enteredValue.length() >= 3) {
			if (verifyIsElementDisplayed(PWASearchPage.objSearchResultScreen, "Search results")) {
				logger.info("Search results are displayed when user enters more than 3 characters in the search box");
				extent.extentLogger("Search result screen",
						"Search results are displayed when user enters more than 3 characters in the search box");

			} else {
				logger.error(
						"Search results are not displayed when user enters more than 3 characters in the search box");
				extent.extentLoggerFail("Search result screen",
						"Search results are not displayed when user enters more than 3 characters in the search box");
			}
		} else {
			logger.error(
					"Search result screen is not displayed when user enters less than 3 characters in the search box.");
		}
		waitTime(5000);
		List<WebElement> tabs = getDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		tabs.size();
		for (int i = 1; i <= tabs.size(); i++) {
			WebElement eleTab = getDriver()
					.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
			String tabName = eleTab.getText();
			eleTab.click();
			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");
			if (getDriver().findElements(By.xpath(
					"(//div[@class='listingGrid']//div[@class='metaData']//h3[contains(@class,'cardTitle')]//span[@class='highLight'])"))
					.size() > 0) {
				logger.info("Related search results are displayed");
				extent.extentLogger("Related search results", "Related search results are displayed");

			} else {
				logger.info("Related search results are not displayed");
				extent.extentLogger("Related search results", "Related search results are not displayed");
			}
		}
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");
		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
		verifyElementPresentAndClick(PWASearchPage.objBackButton, "Back in Search box");
	}

	/**
	 * verifing live tv card and verifing Recent searches overlay
	 */
	public void liveTv() throws Exception {
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		extent.HeaderChildNode("Fetch Live Show from Live TV tab and verify Search");
		String liveContentName = fetchLiveContent();
		String liveContentNamemod = liveContentName;
		if (liveContentName.contains("/")) {
			liveContentNamemod = liveContentName.split("/")[0];
			System.out.println(liveContentNamemod);
		}
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, liveContentName + "\n", "Search bar");
		hideKeyboard();
		waitTime(10000);
		for (int scroll = 0; scroll < 4; scroll++) {
			if (verifyIsElementDisplayed(PWALiveTVPage.objrelatedChannel(liveContentNamemod))) {
				break;
			} else {
				Swipe("UP", 1);
				waitTime(2000);
			}
		}
		if (verifyElementPresent(PWALiveTVPage.objrelatedChannelLiveLogo(liveContentNamemod),
				"LIVE Logo for " + liveContentName)) {
			verifyElementPresent(PWALiveTVPage.objrelatedChannel(liveContentNamemod), "Live Show " + liveContentName);
			waitTime(3000);
			JSClick(PWALiveTVPage.objrelatedChannelLiveLogo(liveContentNamemod), "LIVE Logo");
			waitTime(4000);
			waitForElement(PWAPlayerPage.objContentTitleLiveTV, 20, "Content title");
			String consumptionsTitle = "";
			try {
				consumptionsTitle = getText(PWAPlayerPage.objContentTitleLiveTV);
				logger.info("Consumptions title fetched: " + consumptionsTitle);
				extent.extentLogger("", "Consumptions title fetched: " + consumptionsTitle);
			} catch (Exception e) {
			}
			if (consumptionsTitle.equals(liveContentName)) {
				logger.info("Navigated to Consumption screen successfully");
				extent.extentLogger("", "Navigated to Consumption screensuccessfully");

			} else {
				logger.error("Failed to navigate to right Consumption screen");
				extent.extentLoggerFail("", "Failed to navigate to right Consumption screen");
			}
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
				click(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
			}
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		} else {
			Back(1);
		}
	}

	/**
	 * Function to verify the UI of Live Tv And Channel Guide
	 */
	public void verifyLiveTvAndChannelGuideScreen() throws Exception {
		extent.HeaderChildNode("Verify Live TV And Channel Guide Screen");
		waitTime(5000);
		System.out.println(getText(PWAHomePage.objActiveTab));
		verifyElementPresentAndClick(PWALiveTVPage.objLiveTVMenu, "Live TV Menu");
		if (waitforLiveTabToLoad()) {
			System.out.println(getText(PWAHomePage.objActiveTab));
			String channelTitle = getDriver().findElement(PWALiveTVPage.objLiveChannelCardTitle).getText();
			System.out.println(channelTitle);
			logger.info("Live Channel Card Title fetched: " + channelTitle);
			extent.extentLogger("Live Channel Page", "Live Channel Card Title fetched: " + channelTitle);
			verifyElementPresentAndClick(PWALiveTVPage.objLiveChannelCard, "Live Channel Card");
			waitTime(10000);
			String playerPageChannelTitle = getDriver().findElement(PWALiveTVPage.objLiveChannelConsumptionPageTitle)
					.getText();
			logger.info("Title fetched from Consumption page: " + playerPageChannelTitle);
			extent.extentLogger("Live Channel Page", "Title fetched from Consumption page: " + playerPageChannelTitle);
			if (channelTitle.equalsIgnoreCase(playerPageChannelTitle)) {
				logger.info("Navigated to respective Live Channel Consumption screen: " + playerPageChannelTitle);
				extent.extentLogger("Live Channel Page",
						"Navigated to respective Live Channel Consumption screen: " + playerPageChannelTitle);
			} else {
				logger.error("Not navigated to respective Live Channel Consumption screen");
				extent.extentLoggerFail("Live Channel Page",
						"Not navigated to respective Live Channel Consumption screen");
			}
			waitTime(5000);
			Back(1);
			waitTime(5000);
		}
		verifyElementPresentAndClick(PWALiveTVPage.objChannelGuideToggle, "Channel Guide Toggle");
		if (waitforChannelGuideToLoad()) {
			verifyElementPresentAndClick(PWALiveTVPage.objLiveTVToggleInactive, "Live TV Toggle");
			click(PWALiveTVPage.objChannelGuideToggle, "Channel Guide Toggle");
			verifyElementPresentAndClick(PWALiveTVPage.objChannelDayStrip, "Channel/Day Strip");
			click(PWALiveTVPage.objUpcomingLiveProgramDate, "Upcoming Live Program Date");
			verifyElementPresentAndClick(PWALiveTVPage.objChannelGuideSortOption, "Sort Option");
			verifyElementPresent(PWALiveTVPage.objSortByPopularity, "Sort By Popularity Option");
			verifyElementPresent(PWALiveTVPage.objSortByAZ, "Sort by A-Z Option");
		}
		Back(2);
	}

	/**
	 * Function verify upnext rail
	 */
	public void UpnextRail() throws Exception {
		extent.HeaderChildNode("Validating UpNext rail");
		// Click on home page
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		// Click on search icon
		click(PWAHomePage.objSearchBtn, "Search Button");
		// Enter text
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("episodeSpoiler");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box");
		hideKeyboard();
		// Click on first content
		waitTime(3000);
		for (int i = 0; i < 2; i++) {
			try {
				waitTime(5000);
				click(PWASearchPage.objSearchedResult(keyword), "Search Result");
				break;
			} catch (StaleElementReferenceException e) {
			}
		}
		waitTime(30000);
		for (int trial = 0; trial <= 80; trial++) {
			try {
				getDriver().findElement(PWAPlayerPage.objfirstContent);
				System.out.println("Up next Rail displayed");
				extent.extentLogger("Upnext Rail", "Up next Rail displayed");
				logger.info("Up next Rail displayed");
				break;
			} catch (Exception e) {
				Thread.sleep(2000);
			}
		}
		waitTime(5000);
		waitForPlayerAdToComplete("Video Player");
		if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up"))
			verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close icon");
		String episodeName = getElementPropertyToString("innerText", PWAPlayerPage.objContentName, "Content name");
		extent.extentLogger("epnameUI", "Up Next content from UI: " + episodeName);
		logger.info("Up Next content from UI: " + episodeName);
		getResponseUpNextRail.getResponse();
		String APIData = getResponseUpNextRail.getMediaContentName();
		extent.extentLogger("epnameAPI", "Up Next content from API: " + APIData);
		logger.info("Up Next content from API: " + APIData);
		if (APIData.substring(0, 10).equals(episodeName.substring(0, 10))) {// Added substring function as temporary
																			// solution to equals problem.
			extent.extentLogger("Upnext Rail", "The first content Auto played in Upnext rail");
			logger.info("Upnext rail content is auto played");
		} else {
			extent.extentLoggerFail("Verify UpNext Rail", "Mismatch observed, Upnext content auto play is failed");
			logger.info("Mismatch observed, Upnext content playabck is failed");
		}
	}

	/*
	 * Validating if selected Content language is displayed in the Regional Pack
	 */
	public void contentLanguageVerify(String userType) throws Exception {
		if (userType.contains("Guest") || userType.contains("NonSubscribedUser")) {
			extent.HeaderChildNode("Validating if selected Content language is displayed in the Regional Pack");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language Button");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objContentLanguageBtn, "Content Language");
			// verifyElementPresentAndClick(PWAHamburgerMenuPage.objTamil, "Tamil
			// Language");
			List<WebElement> selectedLanguages = getDriver().findElements(PWAHamburgerMenuPage.objSelectedLanguages);
			System.out.println(selectedLanguages.size());
			ArrayList<String> contentLanguages = new ArrayList<String>();
			for (int i = 1; i <= selectedLanguages.size(); i++) {
				System.out.println("Selected Content Language : " + getDriver().findElement(By.xpath(
						"((//div[@class='checkboxWrap checkedHighlight'])//child::*[@class='commonName'])[" + i + "]"))
						.getText());
				contentLanguages.add(getDriver().findElement(By.xpath(
						"((//div[@class='checkboxWrap checkedHighlight'])//child::*[@class='commonName'])[" + i + "]"))
						.getText());
			}
			System.out.println(contentLanguages);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objApplyBtn, "Apply Button");
			verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscribe button");
			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACode, "Have a Code");
			hideKeyboard();
			List<WebElement> selectedRegionalLanguages = getDriver().findElements(PWASubscriptionPages.objPackTypes);
			System.out.println("selectedRegionalLanguages size : " + selectedRegionalLanguages.size());
			for (int i = 2; i <= selectedRegionalLanguages.size(); i++) {
				System.out.println(getText(PWASubscriptionPages.objPackType(i)));
				if (contentLanguages.contains(getText(PWASubscriptionPages.objPackType(i)))) {
					System.out.println("Regional pack is displayed as per the selected content language");
					getDriver().findElement(By.xpath(
							"(//span[@class='noSelect'][.='" + getText(PWASubscriptionPages.objPackType(i)) + "'])"))
							.click();
					List<WebElement> packs = getDriver().findElements(PWASubscriptionPages.objPackTitle);
					System.out.println("Number of packs available " + packs.size());
					for (int k = 1; k <= packs.size(); k++) {
						logger.info("Pack Type : " + getDriver()
								.findElement(By.xpath("((//div[@class='planDescription']))[" + k + "]")).getText());
						extent.extentLogger("Pack Type", "Pack Type : " + getDriver()
								.findElement(By.xpath("((//div[@class='planDescription']))[" + k + "]")).getText());
						logger.info("Pack Amount : "
								+ getDriver().findElement(By.xpath("(//p[@class='currency'])[" + k + "]")).getText());
						extent.extentLogger("Pack Amount", "Pack Amount : "
								+ getDriver().findElement(By.xpath("(//p[@class='currency'])[" + k + "]")).getText());
						logger.info("Pack Duration : "
								+ getDriver().findElement(By.xpath("(//p[@class='duration'])[" + k + "]")).getText());
						extent.extentLogger("Pack Duration", "Pack Duration : "
								+ getDriver().findElement(By.xpath("(//p[@class='duration'])[" + k + "]")).getText());
					}
				}
			}
			// Added for reporting
			List<WebElement> packs = findElements(PWASubscriptionPages.objPackType);
			for (int i = 0; i < packs.size(); i++) {
				logger.info(packs.get(i).getText() + " is not a Regional Pack, RSVOD packs are no more valid");
				extent.extentLoggerWarning("",
						packs.get(i).getText() + " is not a Regional Pack, RSVOD packs are no more valid");
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
	}

	/*
	 * Zee Subscription Page Validation
	 */
	public void verifyUIofZEESubscriptionPage(String userType) throws Exception {

		if (userType.contains("Guest") || userType.contains("NonSubscribedUser")) {

			extent.HeaderChildNode("Zee Subscription Page Validation");

			verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscribe button");

			extent.HeaderChildNode("Validating if Have a code section is displayed on Zee subscription screen.");

			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACode, "Have a Code section");

			String packTitle = getText(PWASubscriptionPages.objPackCategoryTabSelected);
			logger.info(packTitle);
			extent.extentLogger("Pack Title", packTitle + " is displayed");

			String packDescription = getText(PWASubscriptionPages.objPackDescription);
			logger.info(packDescription);
			extent.extentLogger("Pack Description", packDescription + " is displayed");

			extent.HeaderChildNode("Verifying the Default selected Pack ");

			String defaultSelectedPackTitle = getText(PWASubscriptionPages.objSelectedSubscriptionPlanType);
			logger.info("Default Selected Pack Type " + defaultSelectedPackTitle);
			extent.extentLogger("Default Selected Pack Type", "Default Selected Pack Type " + defaultSelectedPackTitle);

			String defaultSelectedPackAmount = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount);
			logger.info("Default Selected Pack Amount " + defaultSelectedPackAmount);
			extent.extentLogger("Default Selected Pack Amount",
					"Default Selected Pack Type " + defaultSelectedPackAmount);

			String defaultSelectedPackDuration = getText(PWASubscriptionPages.objSelectedSubscriptionPlanDuration);
			logger.info("Default Selected Pack Duration " + defaultSelectedPackDuration);
			extent.extentLogger("Default Selected Pack Duration",
					"Default Selected Pack Duration " + defaultSelectedPackDuration);

			extent.HeaderChildNode("Verifying the URL of the page");

			String url = getDriver().getCurrentUrl();
			logger.info("URL of the page : " + url);
			extent.extentLogger("URL", "URL of the page : " + url);

			extent.HeaderChildNode("Validating if the Coupon code accepts special characters");
			String code = "zee5pt20";
			type(PWASubscriptionPages.objHaveACode, code + "@&*", "Prepaid Code");
			String codeVerify = getElementPropertyToString("value", PWASubscriptionPages.objHaveACode,
					"Prepaid Code field");
			logger.info("Coupon code that got entered is " + codeVerify);
			extent.extentLogger("Coupon code", "Coupon code that got entered is " + codeVerify);
			if (code.equals(codeVerify)) {
				logger.info("Coupon code does not accept special characters");
				extent.extentLogger("Coupon code", "Coupon code does not accept special characters");
			} else {
				logger.error("Coupon code has accepted special characters");
				extent.extentLoggerFail("Coupon code", "Coupon code has accepted special characters");
			}
			verifyElementPresentAndClick(PWASubscriptionPages.objApplyBtn, "Apply Button");
			verifyElementPresent(PWASubscriptionPages.objAppliedCodeFailureMessage, "Failure message");
			String failureMsg = getText(PWASubscriptionPages.objAppliedCodeFailureMessage);
			logger.info("Failure message: " + failureMsg + " is displayed");
			extent.extentLogger("Failure Message", "Failure message: " + failureMsg + " is displayed");
			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACodeCloseBtn, "Close Button");

			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACode, "Have A Code section");
			clearField(PWASubscriptionPages.objHaveACode, "Prepaid Code");
			type(PWASubscriptionPages.objHaveACode, "pnb20", "Prepaid Code");
			verifyElementPresentAndClick(PWASubscriptionPages.objApplyBtn, "Apply Button");
			boolean ele1 = verifyElementPresent(PWASubscriptionPages.objAppliedSuccessfullyMessage,
					"Applied Successfully message");
			String successMessage = getText(PWASubscriptionPages.objAppliedSuccessfullyMessage);
			logger.info(successMessage + " is displayed");
			extent.extentLogger("Success Message", successMessage + " is displayed");
			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACodeCloseBtn, "Close Button");
			type(PWASubscriptionPages.objHaveACode, "pNB20", "Prepaid Code");
			verifyElementPresentAndClick(PWASubscriptionPages.objApplyBtn, "Apply Button");
			boolean ele2 = verifyElementPresent(PWASubscriptionPages.objAppliedSuccessfullyMessage,
					"Applied Successfully message");
			String successMsg = getText(PWASubscriptionPages.objAppliedSuccessfullyMessage);
			logger.info(successMsg);
			extent.extentLogger("Success Message", successMsg + " is displayed");
			extent.HeaderChildNode("Validating if the Coupon code is case insensitive");
			if (ele1 && ele2 == true) {
				logger.info("Coupon code is case insensitive");
				extent.extentLogger("Coupon code", "Coupon code is case insensitive");
			} else {
				logger.error("Coupon code is case sensitive");
				extent.extentLoggerFail("Coupon code", "Coupon code is case sensitive");
			}
			extent.HeaderChildNode(
					"Validating the plans with discounted amount is displayed if applied code is successful.");
			List<WebElement> discountedPackAmount = getDriver().findElements(PWASubscriptionPages.objPackAmount);
			waitTime(4000);
			System.out.println(discountedPackAmount.size());
			for (int i = 0; i < discountedPackAmount.size(); i++) {
				logger.info("Discounted Pack Amount : "
						+ getDriver().findElement(By.xpath("(//p[@class='currency'])[" + (i + 1) + "]")).getText());
				extent.extentLogger("Discounted Pack Amount", "Discounted Pack Amount : "
						+ getDriver().findElement(By.xpath("(//p[@class='currency'])[" + (i + 1) + "]")).getText());
			}
			verifyElementPresentAndClick(PWASubscriptionPages.objPackAmount1, "Discounted pack");
			verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue Button");
			paymentPageValidation();
		}

	}

	/*
	 * Validation of Complete Profile/Tell us more about you section
	 */
	public void verifyCompleteYourProfilePopUp(String userType) throws Exception {
		if (userType.contains("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"Validating if user is able to fill Complete Profile/Tell us more about you section");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objFirstName, "First Name Field");
			type(CompleteYourProfilePopUp.objFirstName, "Test\n", "First Name Field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objLastName, "Last Name Field");
			type(CompleteYourProfilePopUp.objLastName, "User\n", "Last Name Field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objDay, "Day Field");
			click(CompleteYourProfilePopUp.objDateSelector, "Date");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objMonth, "Month Field");
			click(CompleteYourProfilePopUp.objDateSelector, "Month");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objYear, "Year Field");
			click(CompleteYourProfilePopUp.objDateSelector, "Year");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objGenderFemale, "Gender Field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objMobileNo, "Mobile Number");
			type(CompleteYourProfilePopUp.objMobileNo, "95839633299\n", "Mobile Number");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objCloseBtn, "Close Button");
		}

	}

	/*
	 * Validating the UI of My Subscription Page
	 */
	public void verifyUIofMySubscriptionPage(String userType) throws Exception {

		if (userType.contains("NonSubscribedUser")) {
			extent.HeaderChildNode("Validating the UI of My Subscription Page for Non Subscribed User");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyAccount, "My Account");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMySubscription, "My Subscription");
			extent.HeaderChildNode(
					"Validating if Empty state screen is displayed when user has No Active Subscriptions");
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objEmptyStateScreen, "Empty State Screen");
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objBrowseAllPacks, "Browse All Packs");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objSubscriptionTeaserBanner,
					"Subscription Teaser Banner");
			extent.HeaderChildNode(
					"Validating if user is navigated to Zee Subscription screen post tapping on subscription teaser banner");
			if (verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee Subscription Page") == true) {
				logger.info("Navigated to Zee Subscription Page");
				extent.extentLogger("Subscription Page", "Navigated to Zee Subscription Page");
			} else {
				logger.info("Not navigated to Zee Subscription Page");
				extent.extentLogger("Subscription Page", "Not navigated to Zee Subscription Page");
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
	}

	/*
	 * Validating the UI of My Transactions Page and Active/Expired Cards
	 */
	public void validatingActiveAndExpiredCardsinMyTransactionPage(String userType) throws Exception {
		if (userType.contains("NonSubscribedUser") || userType.contains("SubscribedUser")) {
			extent.HeaderChildNode("Validating the UI of My Transactions Page");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyAccount, "My Account");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyTransactions, "MyTransaction");
			extent.HeaderChildNode(
					"Validating if Empty state screen is displayed when user doesn't have any purchase history");
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objEmptyStateScreen, "Empty State Screen");
			boolean NoTransactionPresent = verifyIsElementDisplayed(PWAHamburgerMenuPage.objNoTransaction,
					"No Transactions");
			if (NoTransactionPresent == false) {
				extent.HeaderChildNode("Validating the UI of Active/Expired Cards");
				List<WebElement> packs = getDriver().findElements(By.xpath("//p[@class='packTitle']"));
				System.out.println("No of Packs : " + packs.size());

				if (packs.size() > 1) {
					String status = getText(PWAHamburgerMenuPage.objMyTransactionPackStatus);
					logger.info("Pack Status :" + status);
					extent.extentLogger("Pack Status :", "Pack Status :" + status);

					if (status.equalsIgnoreCase("Active")) {

						String title = getText(PWAHamburgerMenuPage.objPackTitle);
						logger.info("Pack Title :" + title);
						extent.extentLogger("Pack Title :", "Pack Title :" + title);

						String price = getText(PWAHamburgerMenuPage.objPackPrice);
						logger.info("Pack Price :" + price);
						extent.extentLogger("Pack Price :", "Pack Price :" + price);

						String duration = getText(PWAHamburgerMenuPage.objPackDuration);
						logger.info("Pack Duration :" + duration);
						extent.extentLogger("Pack Duration :", "Pack Duration :" + duration);

						String renewalStatus = getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus);
						logger.info("Pack Renewal Status :" + renewalStatus);
						extent.extentLogger("Pack Renewal Status :", "Pack Renewal Status :" + renewalStatus);

					}

					String status1 = getText(PWAHamburgerMenuPage.objMyTransactionPackStatus1);
					logger.info("Pack Status :" + status1);
					extent.extentLogger("Pack Status :", "Pack Status :" + status1);

					if (status1.equalsIgnoreCase("EXPIRED")) {

						String title1 = getText(PWAHamburgerMenuPage.objPackTitle1);
						logger.info("Pack Title :" + title1);
						extent.extentLogger("Pack Title :", "Pack Title :" + title1);

						String price1 = getText(PWAHamburgerMenuPage.objPackPrice1);
						logger.info("Pack Price :" + price1);
						extent.extentLogger("Pack Price :", "Pack Price :" + price1);

						String duration1 = getText(PWAHamburgerMenuPage.objPackDuration1);
						logger.info("Pack Duration :" + duration1);
						extent.extentLogger("Pack Duration :", "Pack Duration :" + duration1);

						String renewalStatus1 = getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus1);
						logger.info("Pack Renewal Status :" + renewalStatus1);
						extent.extentLogger("Pack Renewal Status :", "Pack Renewal Status :" + renewalStatus1);
					}

				} else {

					String status = getText(PWAHamburgerMenuPage.objMyTransactionPackStatus);
					logger.info("Pack Status :" + status);
					extent.extentLogger("Pack Status :", "Pack Status :" + status);

					String title = getText(PWAHamburgerMenuPage.objPackTitle);
					logger.info("Pack Title :" + title);
					extent.extentLogger("Pack Title :", "Pack Title :" + title);

					String price = getText(PWAHamburgerMenuPage.objPackPrice);
					logger.info("Pack Price :" + price);
					extent.extentLogger("Pack Price :", "Pack Price :" + price);

					String duration = getText(PWAHamburgerMenuPage.objPackDuration);
					logger.info("Pack Duration :" + duration);
					extent.extentLogger("Pack Duration :", "Pack Duration :" + duration);

					String renewalStatus = getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus);
					logger.info("Pack Renewal Status :" + renewalStatus);
					extent.extentLogger("Pack Renewal Status :", "Pack Renewal Status :" + renewalStatus);

				}
			}

			extent.HeaderChildNode(
					"Validating if user is navigated to Zee Subscription screen post tapping on subscription teaser banner");
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objSubscriptionTeaserBanner,
					"Subscription Teaser Banner") == true) {
				click(PWAHamburgerMenuPage.objSubscriptionTeaserBanner, "Subscription Teaser Banner");
				waitTime(5000);
				if (verifyIsElementDisplayed(PWASubscriptionPages.objZEE5Subscription,
						"Zee Subscription Page") == true) {
					logger.info("Navigated to Zee Subscription Page");
					extent.extentLogger("Subscription Page", "Navigated to Zee Subscription Page");
				} else {
					logger.info("Not navigated to Zee Subscription Page");
					extent.extentLogger("Subscription Page", "Not navigated to Zee Subscription Page");
				}

			}

			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");

		}
	}

	/*
	 * Payment Page Validation
	 */
	public void paymentPageValidation() throws Exception {
		extent.HeaderChildNode("Payment Page Validation");
		if (verifyIsElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field") == true) {
			verifyElementPresentAndClick(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
			type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
			hideKeyboard();

			verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnHighlighted,
					"Proceed Button in Account Info Page Highlighted");
			// Password Popup
			verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");

			verifyElementPresent(PWASubscriptionPages.objProceedBtnDisabled, "Disabled Proceed Button");

			verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
			type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345\n", "Password Field");
			hideKeyboard();
			// verifyIsElementDisplayed(PWASubscriptionPages.objProceedBtnEnabled, "Proceed
			// Button");
			// click(PWASubscriptionPages.objProceedBtnEnabled, "Enabled Proceed Button");
			waitTime(10000);

		}
		extent.HeaderChildNode("Validating the payment gateway using Credit Card");
		Swipe("UP", 1);
		waitTime(3000);
		getDriver().context("NATIVE_APP");
		verifyElementPresentAndClick(PWASubscriptionPages.objMobileCreditDebitCardOption,
				"'Credit / Debit Card' option");
		verifyElementPresent(PWASubscriptionPages.objMobileCardNumberText, "Card Number");
		verifyElementPresent(PWASubscriptionPages.objMobileExpiryText, "Expiry field");
		verifyElementPresent(PWASubscriptionPages.objMobileCVVText, "CVV field");
		verifyElementPresent(PWASubscriptionPages.objMobileProceedToPayButton, "Proceed to Pay button");
		click(PWASubscriptionPages.objCreditDebitClose, "Credit/Debit card Close");
		getDriver().context("CHROMIUM");
		Back(1);
		extent.HeaderChildNode("Validating the payment gateway using Paytm");
		getDriver().context("NATIVE_APP");
		verifyElementPresentAndClick(PWASubscriptionPages.objMobileWalletsOption, "'Wallets' option");
		verifyElementPresentAndClick(PWASubscriptionPages.objMobilePaytmOption, "Paytm option");
		if (verifyIsElementDisplayed(PWASubscriptionPages.objMobileLinkPaytmOption, "Link PAYTM Wallet")) {
			type(PWASubscriptionPages.objMobilePaytmNumberField, "9876543210", "PayTM Mobile number field");
			hideKeyboard();
			verifyElementPresentAndClick(PWASubscriptionPages.objMobilePaytmSendOTP, "SEND OTP button");
		} else {
			verifyElementPresentAndClick(PWASubscriptionPages.objMobileProceedToPayButton, "Proceed to Pay button");
			logger.info("URL opened: " + getDriver().getCurrentUrl());
			extent.extentLogger("New web page loaded", "URL opened: " + getDriver().getCurrentUrl());
			if (getDriver().getCurrentUrl().contains("paytm")) {
				logger.info("User is navigated to Paytm gateway page");
				extent.extentLogger("New web page loaded", "User is navigated to Paytm gateway page");
			} else {
				logger.info("User didn't get navigated to Paytm gateway page");
				extent.extentLogger("New web page is not loaded", "User didn't get navigated to Paytm gateway page");
			}
			Back(1);
		}
		getDriver().context("CHROMIUM");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void newsPageValidation(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("News Page Validation");
		navigateToAnyScreen("News");
		waitTime(7000);
		waitForElement(PWANewsPage.objLiveNewsCard, 60, "News Card");
		String newsCardTitle = getElementPropertyToString("data-minutelytitle", PWANewsPage.objLiveNewsCard,
				"News Card Title");
		logger.info("News card title fetched: " + newsCardTitle);
		extent.extentLogger("", "News card title fetched: " + newsCardTitle);
		click(PWANewsPage.objLiveNewsCard, "News Card Title");
		waitTime(10000);
		if (verifyElementPresent(PWAPlayerPage.objPlayerControlScreen, "Player control containing screen")) {
			waitforNewsConsumptionsToLoad();
			String newsConsumptionsTitle = getText(PWANewsPage.objLiveNewsConsumptionsTitle);
			logger.info("News title from consumptions page: " + newsConsumptionsTitle);
			extent.extentLogger("", "News title from consumptions page: " + newsConsumptionsTitle);
			if (newsCardTitle.equals(newsConsumptionsTitle)) {
				logger.info("Navigated to correct Consumptions Page");
				extent.extentLogger("Consumption Page", "Navigated to correct Consumption Page");
			} else {
				logger.error("Navigated to incorrect Consumptions Page");
				extent.extentLoggerFail("Consumption Page", "Navigated to incorrect Consumptions Page");
			}
		} else {
			logger.error("Not navigated to Consumptions Page");
			extent.extentLoggerFail("Consumption Page", "Not navigated to Consumptions Page");
		}
		newsPlayerIconValidations();
		Back(1);
		if (verifyIsElementDisplayed(PWAPremiumPage.objViewAllBtn, "View All Button")) {
			click(PWAPremiumPage.objViewAllBtn, "View All Button");
			if (verifyIsElementDisplayed(PWAPremiumPage.objViewAllPage, "View All Page")) {
				logger.info("Navigated to View All Page");
				extent.extentLogger("View All", "Navigated to View All Page");
			} else {
				logger.info("Not navigated to View All Page");
				extent.extentLogger("View All", "Not navigated to View All Page");
			}
		}
		Back(1);
		// newsTrayValidation();
		extent.HeaderChildNode("Verifing the trays displayed in News Tab");
		pagesTrayValidation("News");
	}

	public void newsPlayerIconValidations() throws Exception {
		extent.HeaderChildNode("Validation of Player Controls");
		pauseLiveTVPlayer();
		if (verifyIsElementDisplayed(PWAPlayerPage.rewind10SecBtn, "Rewind 10 Seconds icon")) {
			logger.error("Rewind 10 Seconds icon should not be available for News");
			extent.extentLoggerFail("", "Rewind 10 Seconds icon should not be available for News");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.objPlayOrPause, "Play/Pause icon")) {
			logger.error("Play/Pause icon is not available for News");
			extent.extentLoggerFail("", "Play/Pause icon is not available for News");
		}
		if (verifyIsElementDisplayed(PWAPlayerPage.forward10SecBtn, "Forward 10 Seconds icon")) {
			logger.error("Forward 10 Seconds icon should not be available for News");
			extent.extentLoggerFail("", "Forward 10 Seconds icon should not be available for News");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.settingsBtn, "Settings icon")) {
			logger.error("Settings icon is not available for News");
			extent.extentLoggerFail("", "Settings icon is not available for News");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.objLivePlayerVolume, "Volume icon")) {
			logger.error("Volume icon is not available for News");
			extent.extentLoggerFail("", "Volume icon is not available for News");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.objLiveTag, "LIVE Tag")) {
			logger.error("LIVE Tag is not available for News");
			extent.extentLoggerFail("", "LIVE Tag is not available for News");
		}
		extent.HeaderChildNode("Validation of full screen mode");
		click(PWAPlayerPage.maximizeBtn, "Maximize button");
		waitTime(3000);
		GetAndVerifyPWAOrientaion("Landscape");
		waitTime(5000);
		click(PWAPlayerPage.minimizeBtn, "Minimize button");
		waitTime(3000);
	}

	public void GetScreenOrientation() throws Exception {
		extent.HeaderChildNode("Validating full screen mode");
		click(PWAPlayerPage.maximizeBtn, "Maximize button");
		GetAndVerifyPWAOrientaion("Landscape");
		waitTime(3000);
		playerTap();
		click(PWAPlayerPage.minimizeBtn, "Minimize button");

	}

	public void MoviesScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verifing whether user is able to navigate Movies landing page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		navigateToAnyScreen("Movies");
		waitForElementDisplayed(PWAHomePage.objHighlightedTab("Movies"), 10);
		if (verifyElementPresent(PWAHomePage.objHighlightedTab("Movies"), "Movies")) {
			logger.info("Movies tab is highlighted, user is able to navigate Movies landing page");
			extent.extentLogger("Movies landing page",
					"Movies tab is highlighted, user is able to navigate Movies landing page");
		}
		waitTime(4000);
		extent.HeaderChildNode("Verifing movies premium and free content in landscape mode");
		for (int i = 0; i < 15; i++) {
			if (verifyIsElementDisplayed(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content in tray"))
				break;
			else {
				Swipe("UP", 1);
				waitTime(3000);
			}
		}
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from tray");
		if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
			verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close icon");
			extent.HeaderChildNode("Verifing that premium content videos in landscape mode");
			if (verifyIsElementDisplayed(PWALiveTVPage.objPlayerInlineSubscriptionLink,
					"Player inline Subscribtion link")) {
				logger.info(
						"Maximize icon is not displayed since user is getting Player inline Subscription link on Player screen");
				extent.extentLogger("Maximize icon",
						"Maximize icon is not displayed since user is getting Player inline Subscription link on Player screen");
			}
			Back(1);
		} else {
			waitTime(2000);
			screenOrientation();
			Back(1);
			waitTime(3000);
		}
		chkPremiumORFreeFromVideosTabAndSelect("Trending Movies", "FREE");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void ClubScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verifing whether user is able to navigate Club landing page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		navigateToAnyScreen("Club");
		waitForElementDisplayed(PWAHomePage.objHighlightedTab("Club"), 10);
		if (verifyElementPresent(PWAHomePage.objHighlightedTab("Club"), "Club")) {
			logger.info("Club tab is highlighted, user is able to navigate to Club landing page");
			extent.extentLogger("Club landing page",
					"Club tab is highlighted, user is able to navigate to Club landing page");
		}
		extent.HeaderChildNode("Verify whether Hamburger menu overlay is displayed on clicking Hamburger menu icon");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		if (verifyElementPresentAndClick(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Hamburger close button")) {
			logger.info("Hamburger menu overlay is displayed on clicking Hamburger menu icon");
			extent.extentLogger("menu", "Hamburger menu overlay is displayed on clicking Hamburger menu icon");
		} else {
			logger.error("Hamburger menu overlay open/close failed");
			extent.extentLoggerFail("menu", "Hamburger menu overlay open/close failed");
		}
		extent.HeaderChildNode("Verify on Tap of 'Zee5' Logo user should navigate to homepage.");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		waitTime(5000);
		if (checkElementExist(PWAHamburgerMenuPage.objHighlightedTab("Home"), "Highlighted Home tab")) {
			logger.info("User is navigated to Home page tapping on Zee5 Logo");
			extent.extentLogger("menu", "User is navigated to Home page tapping on Zee5 Logo");
		} else {
			logger.error("User is not navigated to Home page tapping on Zee5 Logo");
			extent.extentLoggerFail("menu", "User is not navigated to Home page tapping on Zee5 Logo");
		}

	}

	/**
	 * Validating Full screen mode
	 */
	public void screenOrientation() throws Exception {
		extent.HeaderChildNode("Validating full screen mode");
		pausePlayer();
		click(PWAPlayerPage.maximizeBtn, "Maximize button");
		waitTime(3000);
		GetAndVerifyPWAOrientaion("Landscape");
		waitTime(5000);
		click(PWAPlayerPage.minimizeBtn, "Minimize button");
		waitTime(3000);
	}

	public String chkPremiumORFreeFromVideosTabAndSelect(String str, String premiumORfree) throws Exception {
		swipeTillElement(7, PWALandingPages.objTrayTitleInUI(str), "\"" + str + "\" tray");
		Swipe("UP", 2);
		String ValueOfPremiumTumbnail = null;
		List<WebElement> tumnails = findElements(By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//*[@class='noSelect clickWrapper' or @class='clickWrapper'])//figure"));
		System.out.println("Number of Tumbnails : " + tumnails.size());
		boolean flag = false;
		int k = 1;
		for (int j = 1; j <= 5; j++) {
			for (int i = k; i <= tumnails.size(); i++) {
				WebElement specificTumbnail = getDriver().findElement(By.xpath("((//div[@class='trayHeader']//h2[.='"
						+ str
						+ "'])//parent::*//parent::*//*[@class='noSelect clickWrapper' or @class='clickWrapper'])[" + i
						+ "]"));
				boolean elevisibility = verifyIsElementDisplayed(PWAHomePage.objVideoIsPremiumTumbnail(str, i),
						"Premium icon");
				if (elevisibility == true) {
					ValueOfPremiumTumbnail = getAttributValue("title", PWAHomePage.objVideoTumbnailTitle(str, i));
					logger.info("Premium Thumbnail Title : " + ValueOfPremiumTumbnail);
					extent.extentLogger("", "Premium Thumbnail Title : " + ValueOfPremiumTumbnail);
					if (premiumORfree.equals("PREMIUM")) {
						clickByElement(specificTumbnail, "Specific Thumbnail from Premium");
						flag = true;
						break;
					}
				} else if (elevisibility == false) {
					ValueOfPremiumTumbnail = getAttributValue("title", PWAHomePage.objVideoTumbnailTitle(str, i));
					logger.info("Non-Premium Thumbnail Title : " + ValueOfPremiumTumbnail);
					extent.extentLogger("", "Non-Premium Thumbnail Title : " + ValueOfPremiumTumbnail);
					if (premiumORfree.equals("FREE")) {
						clickByElement(specificTumbnail, "Specific Tumbnail from Non Premium");
						flag = true;
						break;
					}
				}
			}
			if (flag == true) {
				break;
			}
			swipeTumbnailToLeft(str);
			k = tumnails.size() + 1;
		}
		if (flag == false) {
			logger.error("There are no " + premiumORfree + " contents in " + str + " tray");
			extent.extentLoggerFail("", "There are no " + premiumORfree + " contents in " + str + " tray");
			return "";
		} else {
			return ValueOfPremiumTumbnail;
		}
	}

	public void liveLandingPage() throws Exception {
		extent.HeaderChildNode("Verifing whether user is able to navigate Live Tv landing page");
		navigateToAnyScreen("Live TV");
		waitforLiveTabToLoad();
		dismissSystemPopUp();
		waitForElementDisplayed(PWAHomePage.objHighlightedTab("Live TV"), 10);
		if (verifyElementPresent(PWAHomePage.objHighlightedTab("Live TV"), "Live TV")) {
			logger.info("Live Tv tab is highlighted, user is able to navigate Live Tv landing page");
			extent.extentLogger("", "Live Tv tab is highlighted, user is able to navigate Live Tv landing page");
		}
		waitForElementDisplayed(PWALiveTVPage.objFirstfreeContentCard, 10);
		extent.HeaderChildNode(
				"Verifing that On tapping of 'Right side bottom arrow' user is navigated to top of screen");
		for (int i = 0; i < 3; i++) {
			Swipe("UP", 1);
		}
		waitForElementDisplayed(PWAHomePage.objBackToTopArrow, 3);
		click(PWAHomePage.objBackToTopArrow, "Back to Top Arrow");
		waitForElementDisplayed(PWALiveTVPage.objLiveTvFilterOption, 5);
		if (verifyIsElementDisplayed(PWALiveTVPage.objLiveTvFilterOption, "Filter option")) {
			logger.info("On tapping of 'Right side bottom arrow' user is navigated to top of screen without scrolling");
			extent.extentLogger("Right side bottom arrow",
					"On tapping of 'Right side bottom arrow' user is navigated to top of screen without scrolling");
		}
	}

	/**
	 * ===============BHAVANA - SHOWS MODULE================================
	 * 
	 * /* Function to verify Landscape mode for free content in Shows page
	 */
	public void verifyLandscapeforFreeContent() throws Exception {
		HeaderChildNode("Verify Landscape mode for Free content");
		navigateToAnyScreen("Shows");
		swipeTillElement(7, PWALandingPages.objTrayTitleInUI("Trending Shows"), "Trending Shows tray");
		click(PWALandingPages.objFirstAssetInTrayIndex("Trending Shows"), "First card under Trending Shows tray");
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		waitTime(3000);
		click(PWAShowsPage.objEpisodesSetTray, "Episode Tray");
		waitTime(2000);
		click(PWAShowsPage.objSecondSetEpisodeTray, "Second Episode set");
		waitTime(3000);
		JSClick(PWAShowsPage.objFirstContentInTray, "Free Content from tray");
		waitTime(3000);
		if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop up Title")) {
			click(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
			waitTime(2000);
			logger.info("Next content begin to play");
			extent.extentLogger("Consumption Screen", "Next content begin to play");
			waitTime(5000);
		}
		waitForPlayerAdToComplete("Video Player");
		screenOrientation();
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	/*
	 * Function to verify Landscape mode for premium content in Shows page
	 */

	public void verifyLandscapeforPremiumContent() throws Exception {
		HeaderChildNode("Verify Landscape mode for Premium content");
		navigateToAnyScreen("Shows");
		chkPremiumORFreeFromVideosTabAndSelect("Trending Shows", "FREE");// Select free show and then play premium
																			// content Watch latest Episode
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		verifyElementPresentAndClick(PWAShowsPage.objLatestepisode, "Watch latest Episode CTA");
		waitTime(5000);
		waitExplicitlyForElementPresence(PWASubscriptionPages.objSubscribePopupTitle, 60, "Subscribe Pop Up");
		if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop up Title")) {
			click(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
			logger.info("Unable to verify landscape mode for Premium content for " + user);
			extent.extentLoggerWarning("", "Unable to verify landscape mode for Premium content for " + user);
			waitTime(5000);
		} else {
			waitForPlayerAdToComplete("Video Player");
			screenOrientation();
			waitTime(3000);
		}
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	/**
	 * Function to verify external link "Play and Win" in Shows landing page
	 */

	public void VerifyExternalLinkInShowsLandingPage() throws Exception {
		HeaderChildNode("Verify ExternalLink In ShowsLanding Page");
		reloadHome();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMoreSettingInHamburger,
				"More settings in settings section");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objContentLanguage, "content language");
		if (verifyIsElementDisplayed(PWALandingPages.objHindiInContentLanguageSelected, "Hindi")) {
			logger.info("Hindi language is already selected");
			extent.extentLogger("Hindi", "Hindi language is already selected");
		} else {
			verifyIsElementDisplayed(PWALandingPages.objHindiInContentLanguageNotSelected, "Hindi language");
			click(PWALandingPages.objHindiInContentLanguageNotSelected, "Hindi language");
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objApplybutton, "Apply button on Display language screen");
		waitTime(3000);
		verifyElementPresentAndClick(PWAShowsPage.objShows, "Shows tab");
		getDriver().navigate().refresh();
		waitTime(3000);
		for (int i = 0; i < 30; i++) {
			Swipe("UP", 1);
			waitTime(3000);
		}
		for (int i = 1; i <= 20; i++) {
			if (verifyIsElementDisplayed(PWALandingPages.objTrayTitleInUIContains("Play"), "Tray Play & Win")) {
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		waitTime(3000);
		if (verifyIsElementDisplayed(PWAShowsPage.objPlayAndWin, "Play and Win tray")) {
			waitTime(2000);
			click(PWAShowsPage.objGuessScore, "Guess the score & win");
			waitTime(5000);
			getDriver().context("NATIVE_APP");
			try {
				getDriver().findElement(PWALiveTVPage.objChromeOpenWith).click();
				waitTime(2000);
				getDriver().findElement(PWALiveTVPage.objChromeOpenWith).click();

			} catch (Exception e) {
			}
			getDriver().context("CHROMIUM");
			System.out.println(getDriver().getCurrentUrl());
			androidSwitchTab();
			waitTime(7000);
			logger.info("URL opened: " + getDriver().getCurrentUrl());
			extent.extentLogger("New web page loaded", "URL opened: " + getDriver().getCurrentUrl());
			if (getDriver().getCurrentUrl().contains("games")) {
				logger.info("User is navigated to games page");
				extent.extentLogger("New web page loaded", "User is navigated to games page");
			} else {
				logger.info("User didn't get navigated to games page");
				extent.extentLogger("New web page is not loaded", "UUser didn't get navigated to games page");
			}
			AndroidSwitchToParentWindow();
			Back(1);
		} else {
			logger.info("Play and Win tray is not displayed");
		}
	}

	public void ShowsValidation(String userType) throws Exception {
		if (userType.contentEquals("Guest")) {
			landingPagesValidation(userType, "Shows");
			verifyLandscapeforFreeContent();
			// verifyLandscapeforPremiumContent(); premium content cannot be viewed by guest
			// and non subscribed user
		} else if (userType.contentEquals("NonSubscribedUser")) {
			landingPagesValidation(userType, "Shows");
			verifyLandscapeforFreeContent();
			// verifyLandscapeforPremiumContent(); premium content cannot be viewed by guest
			// and non subscribed user
			VerifyExternalLinkInShowsLandingPage();
		} else if (userType.contentEquals("SubscribedUser")) {
			landingPagesValidation(userType, "Shows");
			verifyLandscapeforFreeContent();
			verifyLandscapeforPremiumContent();
			VerifyExternalLinkInShowsLandingPage();
		}
	}

	/*
	 * Function to verify internal links
	 */

	public void InternalLinksValidation() throws Exception {
		String url = "";
		// Internal Links
		extent.HeaderChildNode("Internal Links Validation");
		swipeToBottomOfPage();
		swipeALittleDownForLinks();
		verifyElementPresentAndClick(PWAHomePage.objAboutUsInFooterSection, "About Us in footer section");
		url = getDriver().getCurrentUrl();
		extent.extentLogger("", "URL navigated: " + url);
		logger.info("URL navigated: " + url);
		if (verifyIsElementDisplayed(PWAHomePage.objAboutUs, "About Us screen")) {
			logger.info("User is navigated to About Us Screen");
		}
		Back(1);
		swipeToBottomOfPage();
		swipeALittleDownForLinks();
		verifyElementPresentAndClick(PWAHomePage.objHelp, "Help Center in footer section");
		boolean applicasterOpened = false;
		getDriver().context("NATIVE_APP");
		if (checkElementDisplayed(PWAHamburgerMenuPage.objzeeAppCloseHelpCenter, "Close of Help Center Page")) {
			applicasterOpened = true;
			verifyElementPresent(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Getting Started"),
					"'Getting Started' tab of Help Center page");
			click(PWAHamburgerMenuPage.objzeeAppCloseHelpCenter, "Close of Help Center Page");
			Back(1);
			if (checkElementDisplayed(PWAHamburgerMenuPage.objzeeAppInterstitialAddClose, "Close of Interstitial Ad")) {
				click(PWAHamburgerMenuPage.objzeeAppInterstitialAddClose, "Close of Interstitial Ad");
			}
			for (int i = 0; i < 3; i++) {
				if (checkElementDisplayed(PWAHamburgerMenuPage.objzeeAppExit, "Exit of ZEE5 App")) {
					click(PWAHamburgerMenuPage.objzeeAppExit, "Exit of ZEE5 App");
					break;
				} else {
					Back(1);
					waitTime(3000);
				}
			}
		} else {
			try {
				directClickReturnBoolean(PWALiveTVPage.objChromeOpenWith, "Open with Chrome");
				waitTime(2000);
				// directClickReturnBoolean(PWALiveTVPage.objChromeOpenWith,"Open with Chrome");

			} catch (Exception e) {
			}
			getDriver().context("CHROMIUM");
			url = getDriver().getCurrentUrl();
			extent.extentLogger("", "URL navigated: " + url);
			logger.info("URL navigated: " + url);
			androidSwitchTab();
			if (verifyElementPresent(PWAHomePage.objHelpScreen, "Help Center screen")) {
				logger.info("User is navigated to Help Center Screen");
			}
			AndroidSwitchToParentWindow();
		}
		getDriver().context("CHROMIUM");
		swipeToBottomOfPage();
		swipeALittleDownForLinks();
		verifyElementPresentAndClick(PWAHomePage.objPrivacyPolicyInFooterSection, "Privacy Policy in footer section");
		url = getDriver().getCurrentUrl();
		extent.extentLogger("", "URL navigated: " + url);
		logger.info("URL navigated: " + url);
		if (verifyElementPresent(PWAHomePage.objPrivacyPolicy, "Privacy Policy screen")) {
			logger.info("User is navigated to Privacy Policy Screen");
		}
		Back(1);
		swipeToBottomOfPage();
		swipeALittleDownForLinks();
		verifyElementPresentAndClick(PWAHomePage.objTermsOfUseInfooterSection, "Terms of Use in footer section");
		url = getDriver().getCurrentUrl();
		extent.extentLogger("", "URL navigated: " + url);
		logger.info("URL navigated: " + url);
		if (verifyElementPresent(PWAHomePage.objTerms, "Terms of Use screen")) {
			logger.info("User is navigated to Terms of Use Screen");
		}
		Back(1);
	}

	/*
	 * Function to verify external links
	 */
	public void ExternalLinksValidation() throws Exception {
		extent.HeaderChildNode("External Links Validation");
		swipeToBottomOfPage();
		swipeALittleDownForLinks();
		verifyElementPresentAndClick(PWAHomePage.objInstagramIcon, "Instagram icon");
		waitTime(5000);
		androidSwitchTab();
		if (verifyIsElementDisplayed(PWAHomePage.objInstagramPage, "Instagram page follow button")) {
			logger.info("User is redirected to Instagram page");
			extent.extentLogger("", "User is redirected to Instagram page");
		} else {
			logger.error("User is not redirected to Instagram page");
			extent.extentLoggerFail("", "User is not redirected to Instagram page");
		}
		AndroidSwitchToParentWindow();
		// Twitter
		swipeToBottomOfPage();
		swipeALittleDownForLinks();
		verifyElementPresentAndClick(PWAHomePage.objTwitterIcon, "Twitter icon");
		getDriver().context("NATIVE_APP");
		try {
			getDriver().findElement(PWALiveTVPage.objTwitterOpenWith).click();
		} catch (Exception e) {
		}
		getDriver().context("CHROMIUM");
		waitTime(5000);
		getDriver().context("NATIVE_APP");
		boolean twitterAppLaunched = false;
		if (verifyIsElementDisplayed(PWAHomePage.objTwitterPage, "Twitter app Follow button")) {
			Back(1);
			twitterAppLaunched = true;
		}
		getDriver().context("CHROMIUM");
		if (twitterAppLaunched == false) {
			androidSwitchTab();
			verifyIsElementDisplayed(PWAHomePage.objTwitterPage, "Twitter page Follow button");
			AndroidSwitchToParentWindow();
		}
		swipeToBottomOfPage();
		swipeALittleDownForLinks();
		// Facebook
		verifyElementPresentAndClick(PWAHomePage.objFacebookIcon, "Facebook icon");
		waitTime(3000);
		androidSwitchTab();
		String facebook = getDriver().getCurrentUrl();
		if (facebook.contains("facebook")) {
			logger.info("User is redirected to Facebook page");
			extent.extentLogger("Facebook", "User is redirected to Facebook page");
		} else {
			logger.error("User is not navigated to Facebook page");
			extent.extentLogger("", "User is not navigated to Facebook page");
		}
		AndroidSwitchToParentWindow();
		swipeToBottomOfPage();
		swipeALittleDownForLinks();
		swipeALittleDownForLinks();
		// android play store
		verifyElementPresentAndClick(PWAHomePage.objAndroidPlayStoreIcon, "Android play store icon");
		getDriver().context("NATIVE_APP");
		if (checkElementDisplayed(PWAHomePage.objGooglePlayStore, "Android Google Play store")) {
			logger.info("User is navigated to Android Google Play Store application");
			Back(1);
		} else {
			logger.error("User is not navigated to Android Google Play Store application");
			extent.extentLogger("", "User is not navigated to Android Google Play Store application");
		}
		getDriver().context("CHROMIUM");
		AndroidSwitchToParentWindow();
		// iOS app store
		swipeToBottomOfPage();
		swipeALittleDownForLinks();
		swipeALittleDownForLinks();
		verifyElementPresentAndClick(PWAHomePage.objIoSAppStoreIcon, "iOS app store icon");
		waitTime(3000);
		androidSwitchTab();
		String apple = getDriver().getCurrentUrl();
		if (apple.contains("apple")) {
			logger.info("User is redirected to iOS App Store");
			extent.extentLogger("", "User is redirected to iOS App Store");
		} else {
			logger.error("User is not navigated to iOS App Store");
			extent.extentLogger("", "User is not navigated to iOS App Store");
		}
		Back(2);
	}

	public void Zee5OriginalsScreen(String UserType, String Tabname) throws Exception {
		landingPagesValidation(UserType, Tabname);
		pagesTrayValidation(Tabname);
		Subscriptionpopup(Tabname);
	}

	public void premiumPageValidation(String UserType, String Tabname) throws Exception {
		landingPagesValidation(UserType, Tabname);
		pagesTrayValidation(Tabname);
		Subscriptionpopup(Tabname);
	}

	public void Moviepage(String usertype, String Tabname) throws Exception {
		MoviesScreen(usertype);
		landingPagesValidation(usertype, Tabname);
		extent.HeaderChildNode("Verifing the trays displayed in Movies Tab");
		pagesTrayValidation(Tabname);
		Subscriptionpopup(Tabname);
	}

	public void Clubpage(String usertype, String Tabname) throws Exception {
		ClubScreen(usertype);
		landingPagesValidation(usertype, Tabname);
		extent.HeaderChildNode("Verifing the trays displayed in Club Tab");
		pagesTrayValidation(Tabname);
		Subscriptionpopup(Tabname);
	}

	public void VerifyCTA(String usertype) throws Exception {
		String clubShow = getParameterFromXML("clubShow");
		String premiumShow = getParameterFromXML("premiumShow");
		if (usertype.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Guest User: Verify Get Club CTA on Carousel banner for club content");
			navigateToAnyScreen("Club");
			waitExplicitlyForElementPresence(PWAHomePage.objGetClubCta, 10, "\"Get Club\" CTA on Carousel banner");
			extent.HeaderChildNode("Guest User: Verify Get Premium CTA on Carousel banner for premium content");
			navigateToAnyScreen("Premium");
			waitExplicitlyForElementPresence(PWAHomePage.objGetPremiumCta, 10,
					"\"Get Premium\" CTA on Carousel banner");
			extent.HeaderChildNode("Guest User: Verify Get Club CTA on show details banner of a club content");
			click(PWAHomePage.objSearchBtn, "Search button");
			type(PWASearchPage.objSearchEditBox, clubShow + "\n", "Search Field");
			click(PWASearchPage.objSearchShowsTab, "Shows tab");
			click(PWASearchPage.objSearchedResult(clubShow), "Searched content : " + clubShow);
			verifyElementPresent(PWAShowsPage.objGetClubCTAInShowDetails,
					"\"Get Club\" CTA on Club Show Details Page Banner");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode("Guest User: Verify Get Premium CTA on show details banner of a premium content");
			click(PWAHomePage.objSearchBtn, "Search button");
			type(PWASearchPage.objSearchEditBox, premiumShow + "\n", "Search Field");
			click(PWASearchPage.objSearchShowsTab, "Shows tab");
			click(PWASearchPage.objSearchedResult(premiumShow), "Searched content : " + premiumShow);
			verifyElementPresent(PWAShowsPage.objGetPremiumCTAInShowDetails,
					"\"Get Premium\" CTA on Premium Show Details Page Banner");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");

			// Login with club user
			extent.HeaderChildNode("Club User: Verify Play CTA on Carousel banner for club content");
			loginWithUserEmail("edpwa4@mailnesia.com", "1234567");
			navigateToAnyScreen("Club");
			verifyElementPresent(PWAHomePage.objPlayBtn, "\"Play\" CTA on Carousel banner");
			extent.HeaderChildNode("Club User: Verify Upgrade CTA on Carousel banner for premium content");
			navigateToAnyScreen("Premium");
			verifyElementPresent(PWAHomePage.objUpgradeBtn, "\"Upgrade\" CTA on Carousel banner");
			extent.HeaderChildNode(
					"Club User: Verify Watch First Episode CTA on show details banner of a club content");
			click(PWAHomePage.objSearchBtn, "Search button");
			type(PWASearchPage.objSearchEditBox, clubShow + "\n", "Search Field");
			click(PWASearchPage.objSearchShowsTab, "Shows tab");
			click(PWASearchPage.objSearchedResult(clubShow), "Searched content : " + clubShow);
			verifyElementPresent(PWAHomePage.objKalGetFirstEpisode,
					"\"Watch first episode\" CTA on Club Show Details Page Banner");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode("Club User: Verify Upgrade CTA on show details banner of a premium content");
			click(PWAHomePage.objSearchBtn, "Search button");
			type(PWASearchPage.objSearchEditBox, premiumShow + "\n", "Search Field");
			click(PWASearchPage.objSearchShowsTab, "Shows tab");
			click(PWASearchPage.objSearchedResult(premiumShow), "Searched content : " + premiumShow);
			verifyElementPresent(PWAShowsPage.objUpgradeCTAInShowDetails,
					"\"Upgrade\" CTA on Premium Show Details Page Banner");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			logout();
		}
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Non Subscribed User: Verify Get Club CTA on Carousel banner for club content");
			navigateToAnyScreen("Club");
			waitExplicitlyForElementPresence(PWAHomePage.objGetClubCta, 10, "\"Get Club\" CTA on Carousel banner");
			extent.HeaderChildNode(
					"Non Subscribed User: Verify Get Premium CTA on Carousel banner for premium content");
			navigateToAnyScreen("Premium");
			waitExplicitlyForElementPresence(PWAHomePage.objGetPremiumCta, 10,
					"\"Get Premium\" CTA on Carousel banner");
			extent.HeaderChildNode("Non Subscribed User: Verify Get Club CTA on show details banner of a club content");
			click(PWAHomePage.objSearchBtn, "Search button");
			type(PWASearchPage.objSearchEditBox, clubShow + "\n", "Search Field");
			click(PWASearchPage.objSearchShowsTab, "Shows tab");
			click(PWASearchPage.objSearchedResult(clubShow), "Searched content : " + clubShow);
			verifyElementPresent(PWAShowsPage.objGetClubCTAInShowDetails,
					"\"Get Club\" CTA on Club Show Details Page Banner");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode(
					"Non Subscribed User: Verify Get Premium CTA on show details banner of a premium content");
			click(PWAHomePage.objSearchBtn, "Search button");
			type(PWASearchPage.objSearchEditBox, premiumShow + "\n", "Search Field");
			click(PWASearchPage.objSearchShowsTab, "Shows tab");
			click(PWASearchPage.objSearchedResult(premiumShow), "Searched content : " + premiumShow);
			verifyElementPresent(PWAShowsPage.objGetPremiumCTAInShowDetails,
					"\"Get Premium\" CTA on Premium Show Details Page Banner");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Subscribed User: Verify Play CTA on Carousel banner for club content");
			navigateToAnyScreen("Club");
			verifyElementPresent(PWAHomePage.objPlayBtn, "\"Play\" CTA on Carousel banner");
			extent.HeaderChildNode("Subscribed User: Verify Play CTA on Carousel banner for premium content");
			navigateToAnyScreen("Premium");
			verifyElementPresent(PWAHomePage.objPlayBtn, "\"Play\" CTA on Carousel banner");
			extent.HeaderChildNode(
					"Subscribed User: Verify Watch first episode CTA on show details banner of a club content");
			click(PWAHomePage.objSearchBtn, "Search button");
			type(PWASearchPage.objSearchEditBox, clubShow + "\n", "Search Field");
			click(PWASearchPage.objSearchShowsTab, "Shows tab");
			click(PWASearchPage.objSearchedResult(clubShow), "Searched content : " + clubShow);
			verifyElementPresent(PWAHomePage.objKalGetFirstEpisode,
					"\"Watch first episode\" CTA on Club Show Details Page Banner");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode(
					"Subscribed User: Verify Watch first episode CTA on show details banner of a premium content");
			click(PWAHomePage.objSearchBtn, "Search button");
			type(PWASearchPage.objSearchEditBox, premiumShow + "\n", "Search Field");
			click(PWASearchPage.objSearchShowsTab, "Shows tab");
			click(PWASearchPage.objSearchedResult(premiumShow), "Searched content : " + premiumShow);
			verifyElementPresent(PWAHomePage.objKalGetFirstEpisode,
					"\"Watch first episode\" CTA on Premium Show Details Page Banner");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
	}

	public void loginWithUserEmail(String email, String pwd) throws Exception {
		String emailid = "edpwa4@mailnesia.com";
		String pass = "1234567";
		if (!checkElementDisplayed(PWALoginPage.objLoginBtn, "Login Button")) {
			verifyElementPresentAndClick(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
		}
		waitTime(3000);
		click(PWALoginPage.objLoginBtn, "Login button");
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
		waitTime(2000);
		type(PWALoginPage.objEmailField, emailid, "Email Field");
		hideKeyboard();
		waitTime(3000);
		// dismissSystemPopUp();
		verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
		type(PWALoginPage.objPasswordField, pass + "\n", "Password field");
		hideKeyboard();
		waitTime(5000);
		directClickReturnBoolean(PWALoginPage.objLoginBtnLoginPage, "Login Button");
		waitTime(10000);
	}

	public void PWAEduauraaVerification(String userType) throws Exception {
		extent.HeaderChildNode("Eduauraa validation");
		navigateToAnyScreen("Kids");
		verifyElementPresentAndClick(PWAHomePage.objEduauraaCardCarousel, "Eduauraa Card in Carousel");
		String currenturl = getDriver().getCurrentUrl();
		logger.info("Current URL :" + currenturl);
		extent.extentLogger("Tab", "Current URL :" + currenturl);
		if (currenturl.contains("collections/eduauraa")) {
			logger.info("Navigated to Collections page");
			extent.extentLogger("url", "Navigated to Collections page");
		} else {
			logger.error("Failed to navigate to Collections page");
			extent.extentLoggerFail("url", "Failed to navigate to Collections page");
		}
		navigateToAnyScreen("Kids");
		verifyElementPresent(PWAHomePage.objLearnWithEduauraaTray, "Learn with Eduauraa tray");
		verifyElementPresentAndClick(PWAHomePage.objFirstItemLearnWithEduauraaTray,
				"First card under Learn with Eduauraa tray");
		verifyElementPresentAndClick(PWALandingPages.objViewAllPageFirstContent, "First card in View All page");
		String consumptionPageTitle = getText(PWAPlayerPage.objContentTitle);
		extent.extentLogger("", "Navigated to the Consumption page: " + consumptionPageTitle);
		logger.info("Navigated to the Consumption page: " + consumptionPageTitle);
		pausePlayer();
		verifyElementPresentAndClick(PWAHomePage.objClaimOffer, "Claim Offer CTA");
		verifyElementPresent(PWAHomePage.objSubscriptionPage, "Subscription page");
	}

	public void MusicPageValidation(String userType, String Tabname) throws Exception {
		landingPagesValidation(userType, "Music");
		navigateToMusicTab(userType);
		musicPageTrayValidation(Tabname);
	}

	@SuppressWarnings("unused")
	public void landingPagesValidation(String userType, String tabName) throws Exception {
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		extent.HeaderChildNode(tabName + " Page Validation");
		verifyElementPresentAndClick(PWAHomePage.objTabName(tabName), tabName);
		waitTime(5000);
		String tab = getText(PWAHomePage.objActiveTab);
		// System.out.println(tab);
		logger.info(tab + " tab is highlighted");
		extent.extentLogger("Tab", tab + " tab is highlighted");

		// Hit API get the first tray and click on first item
		boolean trailerAvailableAPI = false, clickedasset = false, clickedviewall = false;
		String languageSmallText = allSelectedLanguages();
		Response tabResponse = ResponseInstance.getResponseForPages(tabName.toLowerCase(), languageSmallText);
		// System.out.println(tabResponse.getBody().asString());
		String trayTitle = "", trayDescription = "", firstAssetTitle = "", secondAssetTitle = "",
				first_business_type = "", firstAssetSubType = "";
		String traynumber = "", traytype = "";
		int tags = 0;
		main: for (int i = 0; i < 10; i++) {
			try {
				tags = tabResponse.jsonPath().get("buckets[" + i + "].tags.size()");
				// System.out.println("tags: "+tags);
			} catch (Exception e) {
			}
			for (int j = 0; j < tags; j++) {
				String tag = tabResponse.jsonPath().get("buckets[" + i + "].tags[" + j + "]");
				// System.out.println("tag: "+tag);
				if (tag.equalsIgnoreCase("title") || tag.equalsIgnoreCase("movies") || tag.equalsIgnoreCase("episodes")
						|| tag.equalsIgnoreCase("portrait_small")) {
					traynumber = String.valueOf(i);
					break main;
				}
			}
		}
		if (!traynumber.equals("")) {
			// tray name from api
			trayTitle = tabResponse.jsonPath().get("buckets[" + traynumber + "].title");
			logger.info("Tray from API: " + trayTitle);
			extent.extentLogger("", "Tray from API: " + trayTitle);
			// tray description from api
			trayDescription = tabResponse.jsonPath().get("buckets[" + traynumber + "].description");
			logger.info("Tray Description from API: " + trayDescription);
			extent.extentLogger("", "Tray Description from API: " + trayDescription);
			// tray first card from api
			firstAssetTitle = tabResponse.jsonPath().get("buckets[" + traynumber + "].items[0].title");
			firstAssetSubType = tabResponse.jsonPath().get("buckets[" + traynumber + "].items[0].asset_subtype");
			logger.info("Tray First card from API: " + firstAssetTitle);
			extent.extentLogger("", "Tray First card from API: " + firstAssetTitle);
			// tray second card from api
			secondAssetTitle = tabResponse.jsonPath().get("buckets[" + traynumber + "].items[1].title");
			logger.info("Tray Second card from API: " + secondAssetTitle);
			extent.extentLogger("", "Tray Second card from API: " + secondAssetTitle);
			// UI Search
			verifyElementPresent(PWALandingPages.objTrayTitleInUIContains(trayTitle),
					"Tray title : " + trayTitle + " in UI");
			verifyElementPresent(PWALandingPages.firstAssetNonRecoTray(trayTitle, firstAssetTitle),
					"Tray First card : " + firstAssetTitle + " in UI");
			verifyElementPresent(PWALandingPages.secondAssetNonRecoTray(trayTitle, secondAssetTitle),
					"Tray Second card : " + secondAssetTitle + " in UI");
			for (int i = 0; i < 5; i++) {
				if (directClickReturnBoolean(PWALandingPages.firstAssetNonRecoTray(trayTitle, firstAssetTitle),
						"Tray First Asset : " + firstAssetTitle + " in UI")) {
					clickedasset = true;
					waitTime(5000);
					break;
				} else {
					Swipe("UP", 1);
					waitTime(2000);
				}
			}
		}
		if (clickedasset == true) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Sign Up Pop Up")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Sign Up Pop Up close button");
			}
			String contentURL = getDriver().getCurrentUrl();
			String[] abc = contentURL.split("/");
			String contentID = abc[abc.length - 1];
			System.out.println("Content ID fetched from URL: " + contentID);
			Response contentdetails = ResponseInstance.getContentDetails(contentID, firstAssetSubType);
			extent.HeaderChildNode("Watch Trailer Button Validation");
			first_business_type = contentdetails.jsonPath().get("business_type");
			if (first_business_type == null)
				first_business_type = "";
			logger.info("First card business type from API: " + first_business_type);
			extent.extentLogger("", "First card business type from API: " + first_business_type);
			logger.info("First card Asset type from API: " + firstAssetSubType);
			extent.extentLogger("", "First card Asset type from API: " + firstAssetSubType);
			try {
				int totalcontentsOfTheContent = contentdetails.jsonPath().getList("related_videos_ss.items").size();
				System.out.println(totalcontentsOfTheContent);
				for (int i = 0; i < totalcontentsOfTheContent; i++) {
					if (contentdetails.jsonPath().get("related_videos_ss.items[" + i + "].asset_subtype").toString()
							.equals("trailer")) {
						logger.info("Trailer is available for the content from API details");
						extent.extentLogger("", "Trailer is available for the content from API details");
						trailerAvailableAPI = true;
						break;
					}
				}
				if (trailerAvailableAPI == false) {
					try {
						if (!contentdetails.jsonPath().get("tvshow_details.trailers[0].id").toString().equals("")) {
							logger.info("Trailer is available for the content from API details");
							extent.extentLogger("", "Trailer is available for the content from API details");
							trailerAvailableAPI = true;
						}
					} catch (Exception e1) {
					}
				}
				if (trailerAvailableAPI == false) {
					logger.info("Trailer is not available for the content from API details");
					extent.extentLogger("", "Trailer is not available for the content from API details");
					trailerAvailableAPI = false;
				}
			} catch (Exception e) {
				try {
					if (!contentdetails.jsonPath().get("tvshow_details.trailers[0].id").toString().equals("")) {
						logger.info("Trailer is available for the content from API details");
						extent.extentLogger("", "Trailer is available for the content from API details");
						trailerAvailableAPI = true;
					}
				} catch (Exception e1) {
					logger.info("Trailer is not available for the content from API details");
					extent.extentLogger("", "Trailer is not available for the content from API details");
					trailerAvailableAPI = false;
				}
			}
			waitTime(5000);
			watchTrailerButtonFunctionality(userType, trailerAvailableAPI, first_business_type);
			navigateToAnyScreen(tabName);
			waitTime(2000);
		} else {
			logger.error("Failed to click on Tray First Asset");
			extent.extentLoggerFail("", "Failed to click on Tray First Asset");
			click(PWAHomePage.objBackToTopArrow, "Back to Top");
		}
		extent.HeaderChildNode("View All button functionality");
		// check if tray is loaded
		for (int i = 1; i <= 5; i++) {
			if (verifyIsElementDisplayed(PWALandingPages.objTrayTitleInUIContains(trayTitle), "Tray")) {
				logger.info("Tray is loaded");
				extent.extentLogger("Tray load", "Tray is loaded");
				break;
			} else {
				Swipe("UP", 1);
				waitTime(2000);
			}
		}
		for (int i = 0; i < 5; i++) {
			if (directClickReturnBoolean(PWALandingPages.objTrayTitleInUIContainsViewAll(trayTitle.trim()),
					"View All Button of tray " + trayTitle)) {
				waitTime(5000);
				clickedviewall = true;
				break;
			} else {
				Swipe("UP", 1);
				waitTime(2000);
			}
		}
		if (clickedviewall == true) {
			if (verifyIsElementDisplayed(PWAPremiumPage.objViewAllPage, "View All Page")) {
				logger.info("Navigated to View All Page");
				extent.extentLogger("View All", "Navigated to View All Page");
				waitTime(3000);
				if (trayDescription != null) {
					if (verifyIsElementDisplayed(PWALandingPages.objViewAllPageTrayDescription, "Tray Description")) {
						String trayDescriptionViewAll = getElementPropertyToString("innerText",
								PWALandingPages.objViewAllPageTrayDescription, "");
						logger.info("Tray descriptionin UI is " + trayDescriptionViewAll);
						extent.extentLogger("", "Tray descriptionin UI is " + trayDescriptionViewAll);
						if (!trayDescriptionViewAll.equals(trayDescription)) {
							logger.error("Tray description in UI and API do not match");
							extent.extentLoggerFail("", "Tray descriptionin in UI and API do not match");
						} else {
							logger.info("Tray description in UI and API match");
							extent.extentLogger("", "Tray description in UI and API match");
						}
					}
				}
				String firstCardViewAll = getElementPropertyToString("data-minutelytitle",
						PWALandingPages.objViewAllPageFirstContent, "");
				String secondCardViewAll = getElementPropertyToString("data-minutelytitle",
						PWALandingPages.objViewAllPageSecondContent, "");
				if (!firstCardViewAll.equals(firstAssetTitle)) {
					logger.error("First card in tray is " + firstAssetTitle + ", but first card in View All is "
							+ firstCardViewAll + ", order mismatched in tray and View All page");
					extent.extentLoggerFail("View All",
							"First card in tray is " + firstAssetTitle + ", but first card in View All is "
									+ firstCardViewAll + ", order mismatched in tray and View All page");
				} else {
					logger.info(
							"First card " + firstCardViewAll + " in tray and View All page match, order maintained");
					extent.extentLogger("View All",
							"First card " + firstCardViewAll + " in tray and View All page match, order maintained");
				}
				if (!secondCardViewAll.equals(secondAssetTitle)) {
					logger.error("Second card in tray is " + secondAssetTitle + ", but second card in View All is "
							+ secondCardViewAll + ", order mismatched in tray and View All page");
					extent.extentLoggerFail("View All",
							"Second card in tray is " + secondAssetTitle + ", but second card in View All is "
									+ secondCardViewAll + ", order mismatched in tray and View All page");
				} else {
					logger.info(
							"Second card " + secondCardViewAll + " in tray and View All page match, order maintained");
					extent.extentLogger("View All",
							"Second card " + secondCardViewAll + " in tray and View All page match, order maintained");
				}
				PartialSwipe("UP", 1);
				PartialSwipe("UP", 1);
				PartialSwipe("UP", 1);
				PartialSwipe("UP", 1);
				waitTime(2000);
				if (verifyIsElementDisplayed(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn,
						"Back to Top in View All page")) {
					logger.info("Swiping Down is successful because Back to Top button is displayed in View All page");
					extent.extentLogger("View All",
							"Swiping Down is successful because Back to Top button is displayed in View All page");
				} else {
					logger.error(
							"Swiping Down has failed because Back to Top button is not displayed in View All page");
					extent.extentLoggerFail("View All",
							"Swiping Down has failed because Back to Top button is not displayed in View All page");
				}
				click(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top");
				waitTime(2000);
				// handle mandatory pop up
				mandatoryRegistrationPopUp(userType);
				if (directClickReturnBoolean(PWALandingPages.objViewAllPageFirstContent, "First asset")) {
					logger.info("Clicked on first asset, hence Swiping Up is successful");
					extent.extentLogger("View All", "Clicked on first asset, hence Swiping Up is successful");
					waitForElementDisplayed(PWASubscriptionPages.objPopupCloseButton, 30);
					directClickReturnBoolean(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
					String viewallnavigationtittle = getText(PWAPlayerPage.objContentTitle);
					logger.info("Navigated to Consumption page from View All Page: " + viewallnavigationtittle);
					extent.extentLogger("View All",
							"Navigated to Consumption page from View All Page: " + viewallnavigationtittle);
				} else {
					logger.error("Failed to click on first asset in View All Page, Swiping Up failed");
					extent.extentLoggerFail("View All",
							"Failed to click on first asset in View All Page, Swiping Up failed");
				}
			} else {
				logger.error("Not navigated to View All Page");
				extent.extentLoggerFail("View All", "Not navigated to View All Page");
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			navigateToAnyScreen(tabName);
			waitTime(2000);
		}
		if (tabName.equalsIgnoreCase("Club")) {
			extent.HeaderChildNode(tabName + " tab: Club tag verification");
			for (int i = 0; i < 5; i++) {
				if (findElements(PWAHomePage.objClubTag).size() > 0) {
					logger.info("Club Tag is displayed");
					extent.extentLogger("Club Tag", "Club Tag is displayed");
					break;
				} else {
					logger.info("Club Tag is not displayed");
					extent.extentLogger("Club Tag", "Club Tag is not displayed");
					PartialSwipe("UP", 1);
				}
			}
		} else {
			extent.HeaderChildNode(tabName + " tab: Premium icon verification");
			for (int i = 0; i < 5; i++) {
				if (findElements(PWAMusicPage.objPremiumTag).size() > 0) {
					logger.info("Premium tag is displayed");
					extent.extentLogger("Premium Tag", "Premium Tag is displayed");
					break;
				} else {
					logger.info("Premium tag is not displayed");
					extent.extentLogger("Premium Tag", "Premium Tag is not displayed");
					PartialSwipe("UP", 1);
				}
			}
		}
		extent.HeaderChildNode("Verify Back to Top functionality");
		for (int swipe = 0; swipe <= 3; swipe++) {
			Swipe("UP", 1);
		}
		waitTime(2000);
		if (verifyIsElementDisplayed(PWAMusicPage.objArrowToNavigateTop, "Back To Top in " + tabName + " Page")) {
			click(PWAMusicPage.objArrowToNavigateTop, "Back To Top in " + tabName + " Page");
		}
		extent.HeaderChildNode(tabName + " tab: Minutely content verification");
		waitTime(2000);
		for (int i = 0; i < 5; i++) {
			if (findElements(PWAPremiumPage.objMinuteContent).size() > 0) {
				logger.info("Minute content is displayed");
				extent.extentLogger("Minute content", "Minute content is displayed");
				break;
			} else {
				logger.info("Minute content is not displayed");
				extent.extentLogger("Minute content", "Minute content is not displayed");
				PartialSwipe("UP", 1);
			}
		}
		for (int swipe = 0; swipe <= 2; swipe++) {
			Swipe("UP", 1);
		}
		waitTime(2000);
		click(PWAMusicPage.objArrowToNavigateTop, "Back To Top in " + tabName + " Page");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void watchTrailerButtonFunctionality(String userType, boolean trailerAvailableFromAPI, String business_type)
			throws Exception {
		boolean watchTrailerbuttonPresent = false;
		boolean watchTrailerbuttonClickable = false;
		if (userType.equals("Guest") && business_type.contains("premium") && trailerAvailableFromAPI == true) {
			watchTrailerbuttonPresent = true;
			watchTrailerbuttonClickable = false;
		}
		if (userType.equals("Guest") && business_type.contains("premium") && trailerAvailableFromAPI == false) {
			watchTrailerbuttonPresent = false;
		}
		if (userType.equals("Guest") && business_type.contains("advertisement") && trailerAvailableFromAPI == true) {
			watchTrailerbuttonPresent = true;
			watchTrailerbuttonClickable = true;
		}
		if (userType.equals("Guest") && business_type.contains("advertisement") && trailerAvailableFromAPI == false) {
			watchTrailerbuttonPresent = false;
		}
		if (userType.equals("NonSubscribedUser") && business_type.contains("premium")
				&& trailerAvailableFromAPI == true) {
			watchTrailerbuttonPresent = true;
			watchTrailerbuttonClickable = false;
		}
		if (userType.equals("NonSubscribedUser") && business_type.contains("premium")
				&& trailerAvailableFromAPI == false) {
			watchTrailerbuttonPresent = false;
		}
		if (userType.equals("NonSubscribedUser") && business_type.contains("advertisement")
				&& trailerAvailableFromAPI == true) {
			watchTrailerbuttonPresent = true;
			watchTrailerbuttonClickable = true;
		}
		if (userType.equals("NonSubscribedUser") && business_type.contains("advertisement")
				&& trailerAvailableFromAPI == false) {
			watchTrailerbuttonPresent = false;
		}
		if (userType.equals("SubscribedUser") && business_type.contains("premium") && trailerAvailableFromAPI == true) {
			watchTrailerbuttonPresent = true;
			watchTrailerbuttonClickable = true;
		}
		if (userType.equals("SubscribedUser") && business_type.contains("premium")
				&& trailerAvailableFromAPI == false) {
			watchTrailerbuttonPresent = false;
		}
		if (userType.equals("SubscribedUser") && business_type.contains("advertisement")
				&& trailerAvailableFromAPI == true) {
			watchTrailerbuttonPresent = true;
			watchTrailerbuttonClickable = true;
		}
		if (userType.equals("SubscribedUser") && business_type.contains("advertisement")
				&& trailerAvailableFromAPI == false) {
			watchTrailerbuttonPresent = false;
		}
		if (watchTrailerbuttonPresent == true && watchTrailerbuttonClickable == false) {
			if (verifyIsElementDisplayed(PWAPremiumPage.objWatchTrailerBtn, "Watch Trailer Button")) {
				if (directClickReturnBoolean(PWAPremiumPage.objWatchTrailerBtn, "Watch Trailer Button")) {
					logger.error("Watch Trailer button is clickable for " + userType);
					extent.extentLoggerFail("", "Watch Trailer button is clickable for " + userType);
				} else {
					logger.info("Verified that Watch Trailer button is not clickable for " + userType);
					extent.extentLogger("Trailer",
							"Verified that Watch Trailer button is not clickable for " + userType);
				}
			} else {
				logger.error("Watch Trailer button is not displayed in UI");
				extent.extentLoggerFail("", "Watch Trailer button is not displayed in UI");
			}
		}
		if (watchTrailerbuttonPresent == true && watchTrailerbuttonClickable == true) {
			if (checkElementDisplayed(PWAPremiumPage.objWatchTrailerBtn, "Watch Trailer Button")) {
				if (directClickReturnBoolean(PWAPremiumPage.objWatchTrailerBtn, "Watch Trailer Button")) {
					logger.info("Verified that Watch Trailer button is clickable for " + userType);
					extent.extentLogger("", "Verified that Watch Trailer button is clickable for " + userType);
				} else {
					logger.error("Watch Trailer button is not clickable for " + userType);
					extent.extentLoggerFail("Trailer", "Watch Trailer button is not clickable for " + userType);
				}
			} else {
				logger.error("Watch Trailer button is not displayed in UI");
				extent.extentLoggerFail("", "Watch Trailer button is not displayed in UI");
			}
		}
		if (watchTrailerbuttonPresent == false) {
			if (!userType.equals("SubscribedUser")) {
				if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "Subscribe Pop Up Close button");
				}
			}
			if (verifyIsElementDisplayed(PWAPremiumPage.objWatchTrailerBtn, "Watch Trailer Button")) {
				logger.error("Watch Trailer button is displayed in UI");
				extent.extentLoggerFail("Trailer", "Watch Trailer button is displayed in UI");
			} else {
				logger.info("Verified that Watch Trailer button is not displayed in UI");
				extent.extentLogger("", "Verified that Watch Trailer button is not displayed in UI");
			}
		}
		if (userType.contains("NonSubscribedUser") || (userType.contains("Guest"))) {
			verifyIsElementDisplayed(PWAPremiumPage.objGetPremium, "Subscribe Button");
		}
		if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
			click(PWAHamburgerMenuPage.objPopupClose, "Subscribe Pop Up Close button");
		}
		reloadHome();
	}

	public void pagesTrayValidation(String tabName) throws Exception {
		navigateToAnyScreen(tabName);
		int endindex = 0;
		// String languageSmallText = allSelectedLanguages();
		Response resp = ResponseInstance.getResponseForPages(tabName.toLowerCase(), "en,hi,kn");
		List<String> apiTitleList = new LinkedList<String>();
		List<String> apitotaltrays;
		int trays = 0;
		if (tabName.equalsIgnoreCase("live tv")) {
			apitotaltrays = resp.jsonPath().getList("genres");
			if (apitotaltrays.size() > 5)
				trays = 5;
			else
				trays = apitotaltrays.size();
			for (int i = 0; i < trays; i++) {
				String traytitle = resp.jsonPath().getString("genres[" + i + "].value");
				apiTitleList.add(traytitle);
			}
			endindex = trays;
		} else {
			apitotaltrays = resp.jsonPath().getList("buckets");
			if (apitotaltrays.size() > 5)
				trays = 5;
			else
				trays = apitotaltrays.size();
			for (int i = 1; i < trays; i++) {
				String traytitle = resp.jsonPath().getString("buckets[" + i + "].title");
				apiTitleList.add(traytitle);
			}
			endindex = trays - 1;
		}
		logger.info("Trays from API: " + apiTitleList);
		extent.extentLogger("", "Trays from API: " + apiTitleList);
		for (int j = 0; j < endindex; j++) {
			String apititle = apiTitleList.get(j);
			for (int swipe = 0; swipe < 5; swipe++) {
				try {
					findElement(By.xpath("(//div[@class='trayHeader'])//h2[.=\"" + apititle + "\"]")).getText();
					logger.info("Located Tray " + apititle + " in UI");
					extent.extentLogger("", "Located Tray " + apititle + " in UI");
					break;
				} catch (Exception e) {
					Swipe("UP", 1);
					if (swipe == 4) {
						logger.error("Failed to locate Tray " + apititle + " in UI");
						extent.extentLoggerFail("", "Failed to locate Tray " + apititle + " in UI");
					}
				}
			}
		}
		click(PWAZee5OriginalPage.objNavigateToTop, "Back to Top Arrow");
	}

	public void dataValidationOnScrollForStories() throws Exception {
		ArrayList<String> list1titles = new ArrayList<>();
		for (int j = 0; j < 5; j++) {
			try {
				String title = findElement(By.xpath("(//h3[@class='cardTitle'])[" + j + "]")).getText();
				list1titles.add(title);
			} catch (Exception e) {
				Swipe("UP", 1);
			}
		}
		logger.info("Titles fetched from Stories page before Swiping down : " + list1titles);
		extent.extentLogger("", "Titles fetched from Stories page before Swiping down : " + list1titles);
		Swipe("UP", 5);
		ArrayList<String> list2titles = new ArrayList<>();
		for (int j = 5; j < 10; j++) {
			try {
				String title = findElement(By.xpath("(//h3[@class='cardTitle'])[" + j + "]")).getText();
				list2titles.add(title);
			} catch (Exception e) {
				Swipe("UP", 1);
			}
		}
		logger.info("Titles fetched from Stories page after Swiping down : " + list2titles);
		extent.extentLogger("", "Titles fetched from Stories page after Swiping down : " + list2titles);

		if (list1titles.equals(list2titles)) {
			logger.error("Titles fetched are same after swiping, contents failed to load");
			extent.extentLoggerFail("", "Titles fetched are same after swiping, contents failed to load");
		} else {
			logger.info("Titles fetched are different after swiping, contents have loaded successfully");
			extent.extentLoggerPass("",
					"Titles fetched are different after swiping, contents have loaded successfully");
		}
	}

	public void verifyCarouselAutoRotation() throws Exception {
		// autorotating
		extent.HeaderChildNode("Verifying autorotating of carousel pages Home screen ");
		verifyAutoroatingOnCarousel("Home");
		extent.HeaderChildNode("Verifying autorotating of carousel pages Movies screen ");
		verifyAutoroatingOnCarousel("Movies");
		extent.HeaderChildNode("Verifying autorotating of carousel pages Shows screen ");
		verifyAutoroatingOnCarousel("Shows");
		extent.HeaderChildNode("Verifying autorotating of carousel pages Premium screen ");
		verifyAutoroatingOnCarousel("Premium");
		extent.HeaderChildNode("Verifying autorotating of carousel pages Play screen ");
		verifyAutoroatingOnCarousel("Play");
		extent.HeaderChildNode("Verifying autorotating of carousel pages ZEE5 Originals screen ");
		verifyAutoroatingOnCarousel("ZEE5 Originals");
	}

	public void verifyCarouselPlayIconFunctionality() throws Exception {
		// play icon functionality
		verifyPlayIconFunctionality("ZEE5 Originals");
		verifyPlayIconFunctionality("Kids");
		verifyPlayIconFunctionality("Premium");
		verifyPlayIconFunctionality("Shows");
		verifyPlayIconFunctionality("Movies");
		verifyPlayIconFunctionality("Home");
	}

	public void verifyCarouselPremiumIconFunctionality() throws Exception {
		// premium icon functionality
		String userType = getParameterFromXML("userType");
		verifyPremiumIconFunctionality("Home", userType);
		verifyPremiumIconFunctionality("Premium", userType);
		verifyPremiumIconFunctionality("Movies", userType);
		verifyPremiumIconFunctionality("ZEE5 Originals", userType);
	}

	public void verifyCarouselMetaData() throws Exception {
		// metadata
		String languageSmallText = allSelectedLanguages();
		extent.HeaderChildNode("Verifying metadata of carousel pages on ZEE5 Originals screen");
		verifyMetadataOnCarousel("ZEE5 Originals", "zeeoriginals", languageSmallText);
		extent.HeaderChildNode("Verifying metadata of carousel pages on Play screen");
		verifyMetadataOnCarousel("Play", "play", languageSmallText);
		extent.HeaderChildNode("Verifying metadata of carousel pages on Premium screen");
		verifyMetadataOnCarousel("Premium", "premiumcontents", languageSmallText);
		extent.HeaderChildNode("Verifying metadata of carousel pages on Shows screen");
		verifyMetadataOnCarousel("Shows", "tvshows", languageSmallText);
		extent.HeaderChildNode("Verifying metadata of carousel pages on Movies screen");
		verifyMetadataOnCarousel("Movies", "movies", languageSmallText);
		extent.HeaderChildNode("Verifying metadata of carousel pages on Home screen");
		verifyMetadataOnCarousel("Home", "home", languageSmallText);
	}

	public void verifyCarouselLeftRightFunctionality() throws Exception {
		String url = getParameterFromXML("url");
		verifyLeftRightFunctionality("News", url);
	}

	/**
	 * Function to verify Autoroating on carousel
	 * 
	 * @throws Exception
	 */
	public void verifyAutoroatingOnCarousel(String screen) throws Exception {
		boolean autoplayingItemsPresent = false;
		// String languageSmallText = allSelectedLanguages();
		Response tabResponse = ResponseInstance.getResponseForPages(screen.toLowerCase(), "en,hi,kn");
		int carouselItemsCount = tabResponse.jsonPath().get("buckets[0].items.size()");
		System.out.println(carouselItemsCount);
		if (carouselItemsCount > 7)
			carouselItemsCount = 7;
		for (int i = 0; i < carouselItemsCount; i++) {
			try {
				if (tabResponse.jsonPath().get("buckets[0].items[" + i + "].tags[0]").toString().equals("Autoplay")) {
					logger.info("Autorotation could not be verified because Carousel contains auto playing items");
					extent.extentLoggerWarning("",
							"Autorotation could not be verified because Carousel contains auto playing items");
					autoplayingItemsPresent = true;
					break;
				}
			} catch (Exception e) {
			}
		}
		if (autoplayingItemsPresent == false) {
			if (navigateToAnyScreen(screen)) {
				String firstCarouselTitle = "", secondCarouselTitle = "", thirdCarouselTitle = "";
				(new WebDriverWait(getDriver(), 30))
						.until(ExpectedConditions.presenceOfElementLocated(PWAHomePage.objContTitleOnCarousel));
				firstCarouselTitle = getElementPropertyToString("innerText", PWAHomePage.objContTitleOnCarousel,
						"Carousel Content Title").toString();
				logger.info("Carousel content title fetched first time: " + firstCarouselTitle);
				extent.extentLogger("Autorotating", "Carousel content title fetched first time: " + firstCarouselTitle);
				Thread.sleep(4000);
				secondCarouselTitle = getElementPropertyToString("innerText", PWAHomePage.objContTitleOnCarousel,
						"Carousel Content Title").toString();
				logger.info("Carousel content title fetched second time: " + secondCarouselTitle);
				extent.extentLogger("Autorotating",
						"Carousel content title fetched second time: " + secondCarouselTitle);
				Thread.sleep(4000);
				thirdCarouselTitle = getElementPropertyToString("innerText", PWAHomePage.objContTitleOnCarousel,
						"Carousel Content Title").toString();
				logger.info("Carousel content title fetched third time: " + thirdCarouselTitle);
				extent.extentLogger("Autorotating", "Carousel content title fetched third time: " + thirdCarouselTitle);
				Thread.sleep(4000);
				if (firstCarouselTitle.equals(secondCarouselTitle) || secondCarouselTitle.equals(thirdCarouselTitle)) {
					logger.error("Autorotation failed");
					extent.extentLoggerFail("Autorotating", "Autorotation failed");
				} else {
					logger.info("Different carousel titles are displayed at different instances, Autorotation passed");
					extent.extentLogger("Autorotating",
							"Different carousel titles are displayed at different instances, Autorotation passed");
				}
			} else {
				logger.error("Failed to validate carousel autorotation on tab : " + screen);
				extent.extentLoggerFail("Autorotating", "Failed to validate carousel autorotation on tab : " + screen);
			}
		}
	}

	/**
	 * Verify Meta Data on Carousel
	 */
	public void verifyMetadataOnCarousel(String screen, String pageName, String languageSmallText) throws Exception {
		if (navigateToAnyScreen(screen)) {
			waitTime(5000);
			System.out.println(languageSmallText);
			String carouselTitleAPI = "";
			boolean isTitlePresent = false;
			List<String> allMetaTitleOnCarouselAPI = ResponseInstance.traysTitleCarousel(pageName, languageSmallText);
			System.out.println("API Data : " + allMetaTitleOnCarouselAPI);
			click(PWAHamburgerMenuPage.carouselFirstDot, "First Carousel Dot");
			for (int i = 0; i < allMetaTitleOnCarouselAPI.size(); i++) {
				carouselTitleAPI = allMetaTitleOnCarouselAPI.get(i);
				for (int j = 0; j < 7; j++) {
					try {
						click(PWAHamburgerMenuPage.carouselDot(i + 1), "Carousel Dot " + (i + 1) + "");
						WebElement mastHeadEle = (new WebDriverWait(getDriver(), 30)).until(ExpectedConditions
								.presenceOfElementLocated(PWAHomePage.objContTitleTextCarousel(carouselTitleAPI)));
						isTitlePresent = mastHeadEle.isDisplayed();
						if (isTitlePresent == true) {
							break;
						} else {
							swipeCarouselContents(1);
						}
					} catch (Exception e) {
						System.out.println("Exception : " + e.getMessage());
					}
				}
				if (isTitlePresent == true) {
					logger.info("API title " + carouselTitleAPI + " is present on UI");
					extent.extentLogger("Metadata validation", "API title " + carouselTitleAPI + " is present on UI");
				} else {
					logger.error("API title did not matched with UI title");
					extent.extentLoggerFail("Metadata validation", "API title did not matched with UI title");
				}
			}
		} else {
			logger.error("Failed to validate meta data on tab : " + screen);
			extent.extentLoggerFail("metadata", "Failed to validate meta data on tab : " + screen);
		}
	}

	/**
	 * Function to left and right(<>) functionality on carousel
	 * 
	 * @throws Exception
	 */
	public void verifyLeftRightFunctionality(String screen, String url) throws Exception {
		extent.HeaderChildNode("Verifying left and right functionality");
		navigateToAnyScreen(screen);
		String nextCarouselTitle = "";
		boolean autoplayed = false;
		WebDriverWait w = new WebDriverWait(getDriver(), 120);
		try {
			w.until(ExpectedConditions.visibilityOfElementLocated(PWANewsPage.objRight));
			autoplayed = true;
		} catch (Exception e) {
			logger.info("No content in News tab autoplayed, hence Left Right functionality could not be verified");
			extent.extentLoggerWarning("",
					"No content in News tab autoplayed, hence Left Right functionality could not be verified");
		}
		if (autoplayed) {
			waitForElementAndClick(PWANewsPage.objRight, 10, "Next cont");
			waitTime(1500);
			nextCarouselTitle = getDriver().findElement(PWAHomePage.objContTitleOnCarousel).getText();
			System.out.println("first :" + nextCarouselTitle);

			reloadURL(url);
			navigateToAnyScreen(screen);
			waitTime(1000);
			w.until(ExpectedConditions.visibilityOfElementLocated(PWANewsPage.objLeft));
			waitForElementAndClick(PWANewsPage.objLeft, 10, "Prev cont");
			waitTime(1500);
			String prevCarouselTitle = getDriver().findElement(PWAHomePage.objContTitleOnCarousel).getText();
			System.out.println("f2 :" + prevCarouselTitle);

			if (nextCarouselTitle.equals(prevCarouselTitle) == false) {
				softAssert.assertFalse(nextCarouselTitle.equals(prevCarouselTitle));
				logger.info("Content can be swiped right and left");
				extent.extentLogger("Swipe left and right", "Content can be swiped right and left");
			} else {
				softAssert.assertTrue(nextCarouselTitle.equals(prevCarouselTitle));
				logger.info("Content can not be swiped either right or left");
				extent.extentLoggerFail("Swipe left and right", "Content can not be swiped either right or left");
			}
		}
	}

	public void reloadURL(String url) {
		getDriver().get(url);
		waitTime(10000);
	}

	@SuppressWarnings("rawtypes")
	public void swipeCarouselContents(int noOfTimes) {
		// HeaderChildNode("Carousel swipe");
		for (int i = 0; i < noOfTimes; i++) {
			int deviceWidth = getDriver().manage().window().getSize().width;
			int deviceHeight = getDriver().manage().window().getSize().height;
			int y = deviceHeight / 4;
			waitTime(2000);
			touchAction = new TouchAction(getDriver());
			touchAction.press(PointOption.point((deviceWidth - 250), (y - 100)))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(0, (y - 100))).release().perform();
			System.out.println("Swiped : " + i);
		}
	}

	public void carouseldots(String carouselDots, String page) throws Exception {
		extent.HeaderChildNode("Carousal dots functionality");

		Response resp = ResponseInstance.getResponseForPages(page, "en,kn");
		if (resp == null) {
			System.out.println("No response");
		} else {

//		waitTime(3000);
			waitForElementDisplayed(PWAHomePage.objZeeLogo, 10);
			navigateToAnyScreen(page);
			int hits = getCount(TextToXpathusingclass(carouselDots));
			if (hits == 7) {
				System.out.println("The number of carousal dots are equal to the number of carousals present");
			} else {
				System.out.println("The number of carousal dots are not equal to the number of carousals present");
			}
			for (int i = 1; i < 3; i++) {
				int j = i - 1;
				String Carouseltitle = resp.jsonPath().getString("buckets[0].items[" + j + "].title");
				verifyElementPresentAndClick(TextToXpathusingclasswithindex("carouselDots", i),
						"clicked on carousal dot");
				System.out.println("clicked on " + i + " carousal dot");
				if (verifyIsElementDisplayed(Text_To_Xpath(Carouseltitle), "carousal metadata for " + i + " index ")) {
					System.out.println("The carousel dot for " + i
							+ "th index is navigating to the respective carousel and verified ");
				} else {
					System.out.println(
							"The carousel dot for " + i + "th index is not navigating to the respective carousel ");
				}
			}
		}
	}

	/**
	 * Method to verify Back to Top functionality
	 */
	public void BackTOTop() throws Exception {
		Swipe("UP", 2);
		waitTime(3000);
		closeAdoricPopUp();
		waitForElementDisplayed(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, 20);
		verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top");
	}

	/**
	 * Function to verify view all functionality for a tray
	 */
	public void verifyTrayViewAll(String trayTitleAPI, String contentTitleAPI) throws Exception {
		extent.HeaderChildNode("Verify See All functionality for tray");
		waitTime(3000);
		verifyElementPresentAndClick(PWALandingPages.objViewAllOfTray(trayTitleAPI),
				"View all button for tray " + trayTitleAPI);
//		waitTime(8000);
		waitForElementDisplayed(PWALandingPages.objViewAllPageTitle, 60);
		String viewAllPageTitle = getElementPropertyToString("innerText", PWALandingPages.objViewAllPageTitle,
				"View All Page Title").toString();
		if (viewAllPageTitle.equals(trayTitleAPI)) {
			extent.extentLogger("titles", "Tray title matches View All page title :" + trayTitleAPI);
			logger.info("Tray title matches View All page title :" + trayTitleAPI);
		} else {
			extent.extentLoggerFail("titles", "Mismatch observed, tray title is: " + trayTitleAPI
					+ " and View All page title is: " + viewAllPageTitle);
			logger.info("Mismatch observed, tray title is: " + trayTitleAPI + " and View All page title is: "
					+ viewAllPageTitle);
		}
		waitForElementDisplayed(PWALandingPages.objViewAllPageFirstContent, 10);
		String viewAllPageFirstContent = getElementPropertyToString("data-minutelytitle",
				PWALandingPages.objViewAllPageFirstContent, "View All Page First Content").toString();
		if (viewAllPageFirstContent.equals(contentTitleAPI)) {
			extent.extentLogger("contenttitles",
					"Tray First Content matches View All page first Content :" + contentTitleAPI);
			logger.info("Tray First Content matches View All page first Content :" + contentTitleAPI);
		} else {
			extent.extentLoggerFail("contenttitles", "Mismatch observed, Tray first Content is: " + contentTitleAPI
					+ " and View All page first Content is: " + viewAllPageFirstContent);
			logger.info("Mismatch observed, Tray first Content is: " + contentTitleAPI
					+ " and View All page first Content is: " + viewAllPageFirstContent);
		}
	}

	public void Kaltura(String userType) throws Exception {
		dismissSystemPopUp();
		String url = getParameterFromXML("url");
		extent.HeaderChildNode("Kaltura Playability");

		String keyword1 = getParameterFromXML("consumptionsFreeContent");
		searchvideoandselect(keyword1, userType, "Movie");

		Thread.sleep(2000);
		String keyword2 = getParameterFromXML("tvshow");
		searchvideoandselect(keyword2, userType, "ZeeOriginal");

		Thread.sleep(2000);
		String keyword3 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("trailerOfPremiumMovie");
		searchvideoandselect(keyword3, userType, "trailer");

		Thread.sleep(2000);
		String keyword4 = getParameterFromXML("music");
		searchvideoandselect(keyword4, userType, "music");

		Thread.sleep(2000);
		String keyword5 = getParameterFromXML("freeEpisode4");
		searchvideoandselect(keyword5, userType, "Show");

		Thread.sleep(2000);
		String keyword6 = getParameterFromXML("news");
		searchvideoandselect(keyword6, userType, "news");
		String Value = null;
		waitTime(5000);
		navigateToAnyScreen("Live TV");
		logger.info("Selecting Free content from Live TV tray and validating Kaltura playability");
		extent.extentLogger("Selecting Free content from Live TV tray and validating Kaltura playability",
				"Selecting Free content from Live TV tray and validating Kaltura playability");
		if (waitforLiveTabToLoad()) {
			Value = findElement(PWAPlayerPage.objFirstCardFreeChnnelName).getAttribute("title");
			if (Value != null) {
				logger.info("Free content from Live TV tray : " + Value);
				extent.extentLogger("", "Free content from Live TV tray : " + Value);
				click(PWAPlayerPage.objFirstCardFreeChnnels, "" + Value);
				if (verifyIsElementDisplayed(PWAHomePage.objPlaybackLIVETVTitle1, "Live TV title") == true) {
					logger.info("Navigated to Title : " + getText(PWAHomePage.objPlaybackLIVETVTitle1));
					extent.extentLogger("", "Navigated to Title : " + getText(PWAHomePage.objPlaybackLIVETVTitle1));
					pauseLiveTVPlayer();
					// added from gaps for live tv
					if (verifyIsElementDisplayed(PWAPlayerPage.progressBar, "Progress bar for Live TV")) {
						extent.extentLoggerFail("",
								"Progress bar should not be dispayed for Live TV, but is displayed");
					}
					waitTime(5000);
					if (verifyIsElementDisplayed(PWAHomePage.objKalKalturaPlayer, "Kaltura Player")) {
						extent.extentLogger("Navigated to Kaltura Player", "Navigated to Kaltura Player");
						extent.extentLogger("", "Playing : " + getText(PWAHomePage.objKalLivetvPlaying));
						extent.extentLogger("", "Channel :" + getText(PWAHomePage.objKalLivetvChannel));
						logger.info("Playing : " + getText(PWAHomePage.objKalLivetvPlaying));
						logger.info("Channel :" + getText(PWAHomePage.objKalLivetvChannel));
					} else {
						extent.extentLoggerFail("Not Navigated to Kaltura Player", "Not Navigated to Kaltura Player");
						logger.error("Not Navigated to Kaltura Player");
					}
				}
			} else {
				extent.extentLoggerFail("", "Failed to fetch title from Card of Free Channels");
				logger.error("Failed to fetch title from Card of Free Channels");
				getDriver().get(url);
			}
		}
	}

	public void searchvideoandselect(String str, String userType, String type) throws Exception {
		String url = getParameterFromXML("url");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox, str + "\n", "Search Field");
		if (type.equalsIgnoreCase("movie"))
			verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(3000);
		hideKeyboard();
		waitTime(7000);
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		if (type.equals("news")) {
			click(PWASearchPage.objSearchNewsTab, "NewsTab");
			Thread.sleep(1000);
			click(PWASearchPage.objfirstdata, "data");
		} else {
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(str), "Searched content : " + str);
		}
		waitTime(5000);
		if (type.equals("ZeeOriginal") && !userType.equals("SubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHomePage.objKalGetPremium, "Get Premium Button")) {
				click(PWAHomePage.objKalGetPremiumPlayicon, "Play Button");
			}
		}
		if (type.equals("ZeeOriginal") && userType.equals("SubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHomePage.objKalGetFirstEpisode, "Watch First Episode")) {
				click(PWAHomePage.objKalGetFirstEpisodePlayicon, "Play Button");
			}
		}
		/*
		 * if (userType.equals("Guest")) { if
		 * (verifyIsElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp,
		 * "Sign Up pop up")) { click(PWAPlayerPage.objWEBCloseBtnLoginPopup,
		 * "Sign Up pop up Close button"); } if
		 * (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle,
		 * "Subscribe Pop Up") == true) {
		 * verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose,
		 * "Subscribe Pop Up Close button"); } } if
		 * (userType.equals("NonSubscribedUser")) { if
		 * (verifyIsElementDisplayed(CompleteYourProfilePopUp.
		 * objCompleteYourProfileTxt,"Complete Your Profile pop up")) {
		 * click(CompleteYourProfilePopUp.objCloseBtn, "Close Button"); } if
		 * (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle,
		 * "Subscribe Pop Up") == true) {
		 * verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose,
		 * "Subscribe Pop Up Close button"); } }
		 */

		if (!userType.equals("SubscribedUser")) {
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle)) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "Subscribe Pop Up Close button");
			}
		}
		waitForPlayerAdToComplete("Video Player");
		if (verifyIsElementDisplayed(PWAHomePage.objKalKalturaPlayer, "Kaltura Player")) {
			extent.extentLogger("Navigated to Kaltura Player", "Navigated to Kaltura Player");
			boolean playerpaused = false;
			if (type.equals("")) {
				playerpaused = pauseLiveTVPlayer();
			} else {
				playerpaused = pausePlayer();
			}
			// gap covered
			if (playerpaused == true) {
				logger.info("Playback is verified and player has been paused successfully");
				extent.extentLogger("Playback", "Playback is verified and player has been paused successfully");
			} else {
				logger.error("Playback could not be verified because player pause is unsuccessful");
				extent.extentLoggerFail("Playback",
						"Playback could not be verified because player pause is unsuccessful");
			}
		} else {
			extent.extentLoggerFail("Not Navigated to Kaltura Player", "Not Navigated to Kaltura Player");
		}
		getDriver().get(url);

	}

	/*
	 * ===========BHAVANA STATIC PAGE============= / /** Function to verify the
	 * About Us screen
	 * 
	 */
	public void AboutUsScreenValidation() throws Exception {
		HeaderChildNode("About us screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		Swipe("UP", 2);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAboutUsOption, "About Us option");
		verifyElementPresent(PWAHamburgerMenuPage.objAboutUsTextInPage, "About Us Screen page");
		System.out.println(getDriver().getContext());
		logger.info("Current URL is " + getDriver().getCurrentUrl());
		extent.extentLogger("", "Current URL is " + getDriver().getCurrentUrl());
		String contextname = getDriver().getContext();
		if (contextname.contains("CHROMIUM")) {
			logger.info("About Us screen is opened in webview");
		}
		verifyElementPresent(PWAHamburgerMenuPage.objAboutUsInfo, "Brief information of the application");
		Back(1);
	}

	/**
	 * Function to verify the About Us screen for Subscribed user
	 * 
	 */

	public void SubscribedUserAboutUsScreenValidation() throws Exception {
		HeaderChildNode("About us screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		// Swipe("UP", 1);
		swipeToBottomOfPage();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAboutUsOption, "About Us option");
		verifyElementPresent(PWAHamburgerMenuPage.objAboutUsTextInPage, "About Us Screen page");
		String contextname = getDriver().getContext();
		if (contextname.contains("CHROMIUM")) {
			logger.info("About Us screen is opened in webview");
		}
		verifyElementPresent(PWAHamburgerMenuPage.objAboutUsInfo, "Brief information of the application");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objHyperLink, "Hyperlink on About Us Screen");
		getText(PWAHamburgerMenuPage.objHyperLink);
		logger.info("Hyperlink present on About Us screen is: " + getText(PWAHamburgerMenuPage.objHyperLink));
		extent.extentLogger("",
				"Hyperlink present on About Us screen is: " + getText(PWAHamburgerMenuPage.objHyperLink));
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHyperLink, "Hyperlink");
		getDriver().context("NATIVE");
		try {
			getDriver().findElement(PWALiveTVPage.objChromeOpenWith).click();
			waitTime(2000);

		} catch (Exception e) {
		}
		getDriver().context("CHROMIUM");
		logger.info("User is navigated to respective page: " + getDriver().getCurrentUrl());
		extent.extentLogger("", "User is navigated to respective page: " + getDriver().getCurrentUrl());
		Back(1);
	}

	/**
	 * Function to verify the Help Center screen
	 * 
	 */

	public void HelpCenterScreenValidation() throws Exception {
		HeaderChildNode("Help Center Screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpCenterOption, "Help Center option");
		System.out.println(getDriver().getContextHandles());
		getDriver().context("NATIVE_APP");
		try {
			getDriver().findElement(PWALiveTVPage.objChromeOpenWith).click();
			waitTime(2000);
			getDriver().findElement(PWALiveTVPage.objChromeOpenWith).click();
		} catch (Exception e) {
		}
		getDriver().context("CHROMIUM");
		gettingStartedVerifications();
		myAccountVerifications();
		watchingZEE5Verifications();
		myZEE5AppVerifications();
		quickLinksVerifications();
		contactUsScreenVerification();
	}

	/**
	 * Function to Validating Contact Us page in Help Center
	 * 
	 */

	public void WriteToUs() throws Exception {
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objContactUs, "Contact Us page");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objSelectYourCountry, "Select your country field");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objAutofilledcountry, "Auto filled country name");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objDropDown, "Select your country drop down");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objRegisteredMobileNumber, "Registered mobile number field");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objCountryCode, "Auto filled country code");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objEmailField, "Email ID field");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objEmailIDAsterisk, "Email ID '*' symbol");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objText, "Tell us more about you issue text message");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objContentOption, "Content radio button");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objProductOption, "Product radio button");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objEnquiryOption, "Enquiry radio button");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objFeedbackOption, "Feedback radio button");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objContentAsDefault,
				"Content radio button is selected as default");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objSelectCategory, "Select category field");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objVideoNotPlaying, "Video not palying option as default");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objErrorMessage, "Error message text");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objErrorMessageAsterisk, "Error message '*' symbol");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objSubmitButton, "SUBMIT button");

		verifyIsElementDisplayed(PWAHamburgerMenuPage.objResetButton, "RESET button");

		if (getDriver().findElement(PWAHamburgerMenuPage.objSubmitButton).isEnabled() == false) {
			logger.info("Submit is disabled by default");
			extent.extentLogger("Submit", "Submit is disabled by default");
		}

		if (getDriver().findElement(PWAHamburgerMenuPage.objResetButton).isEnabled() == true) {
			logger.info("Reset is enabled by default");
			extent.extentLogger("Reset", "Reset is enabled by default");
		}

		verifyElementPresent(PWAHamburgerMenuPage.objErrorMessageAsterisk,
				"Mandatory Email ID field is highlighted by '*' symbol");

		verifyElementPresent(PWAHamburgerMenuPage.objErrorMessageAsterisk,
				"Mandatory Error message field is highlighted by '*' symbol");

		verifyElementPresent(PWAHamburgerMenuPage.objPlatformAsterisk,
				"Mandatory Platform drop down is highlighted by '*' symbol");

		type(PWAHamburgerMenuPage.objEmailField, "bhavana9518@gmail.com", "Email Id");

		type(PWAHamburgerMenuPage.objErrorMessageField, "Video couldn't play\n", "Error message");

		if (getDriver().findElement(PWAHamburgerMenuPage.objSubmitButton).isEnabled() == true) {
			logger.info("Submit is enabled by giving mandatory inputs");
			extent.extentLogger("Submit", "Submit is enabled by giving mandatory inputs");
		}

	}

	/**
	 * Function to verify the Terms of Use in Hamburger menu
	 * 
	 */

	public void TermsOfUseValiadtion() throws Exception {
		HeaderChildNode("Terms of Use screen");
		getDriver().context("CHROMIUM");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		Swipe("UP", 1);
		waitTime(4000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objTermsOfUseOption, "Terms of Use option");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objTermsOfUseScreen, "Terms of Use screen");
		System.out.println(getDriver().getContext());
		logger.info("Current URL is " + getDriver().getCurrentUrl());
		extent.extentLogger("version", "Current URL is " + getDriver().getCurrentUrl());
		Back(1);
	}

	/**
	 * Function to verify the Terms of Use screen for Subscribed user
	 * 
	 */
	public void SubscribedUserTermsOfUseValidation() throws Exception {
		HeaderChildNode("Terms of Use screen");
		getDriver().context("CHROMIUM");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		Swipe("UP", 1);
		waitTime(4000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objTermsOfUseOption, "Terms of Use option");
		verifyElementPresent(PWAHamburgerMenuPage.objTermsOfUseScreen, "Terms of Use screen");
		String contextname = getDriver().getContext();
		if (contextname.contains("CHROMIUM")) {
			logger.info("Terms of Use screen is opened in webview");
		}
		verifyElementPresent(PWAHamburgerMenuPage.objTermsAndConditions, "Terms and conditions of application");
		verifyElementPresent(PWAHamburgerMenuPage.objOfferTermsAndConditions, "Offers Terms and conditions");
		swipeToBottomOfPage();
		scrollToTopOfPage();
		verifyElementPresent(PWAHamburgerMenuPage.objOfferDUration, "Offer duration");
		Swipe("UP", 5);
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objCashbackByAmazonPay,
				"Steps to get Cashback for payment by Amazon pay");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objCashbackByAnyBankCard,
				"Steps to get 30% Cashback on any Bank Credit/Debit card");
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objCashbackOnPaytm, "Steps to get 50% Paytm Cashback");
		Back(1);
	}

	/**
	 * Function to verify the Privacy Policy in Hamburger menu
	 * 
	 */

	public void PrivacyPolicyValidation() throws Exception {
		HeaderChildNode("Privacy Policy screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicy, "Privacy Policy option");
		verifyElementPresent(PWAHamburgerMenuPage.objPrivacyPolicyScreen, "Privacy Policy screen");
		String contextname = getDriver().getContext();
		if (contextname.contains("CHROMIUM")) {
			logger.info("Privacy Policy screen is opened in webview");
		}
		verifyElementPresent(PWAHamburgerMenuPage.objPrivacyPolicyInfo, "Legal information of the application");
		swipeToBottomOfPage();
		scrollToTopOfPage();
		verifyElementPresent(PWAHamburgerMenuPage.objSecurityInfo, "Security Information of the application");
		Back(1);
	}

	/**
	 * Function to verify the Privacy Policy screen for Subscribed user
	 * 
	 */

	public void SubscribedUserPrivacyPolicyValidation() throws Exception {
		HeaderChildNode("Privacy Policy screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicy, "Privacy Policy option");
		verifyElementPresent(PWAHamburgerMenuPage.objPrivacyPolicyScreen, "Privacy Policy screen");
		String contextname = getDriver().getContext();
		if (contextname.contains("CHROMIUM")) {
			logger.info("Privacy Policy screen is opened in webview");
		}
		verifyElementPresent(PWAHamburgerMenuPage.objLinkOnPrivacyPolicy, "Hyper link in Privacy Policy Screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLinkOnPrivacyPolicy, "Hyperlink");
		getDriver().context("NATIVE");
		try {
			getDriver().findElement(PWALiveTVPage.objChromeOpenWith).click();
			waitTime(2000);

		} catch (Exception e) {
		}
		getDriver().context("CHROMIUM");
		String link = getDriver().getCurrentUrl();
		logger.info("Navigated to : " + link);
		extent.extentLogger("", "Navigated to : " + link);
		if (link.contains("pagenotfound")) {
			logger.error("User is not able to navigate to the respective page of Hyper link");
			extent.extentLoggerFail("Hyper link", "User is not able to navigate to the respective page of Hyper link");
		}
		Back(1);
		verifyElementPresent(PWAHamburgerMenuPage.objPrivacyPolicyInfo, "Legal information of the application");
		swipeToBottomOfPage();
		click(PWAHomePage.objBackToTopArrow, "Back to Top Arrow");
		verifyElementPresent(PWAHamburgerMenuPage.objSecurityInfo, "Security Information of the application");
		Back(1);
	}

	/**
	 * Function to verify the Build version in Hamburger menu
	 * 
	 */

	public void BuildVersionValidation() throws Exception {
		HeaderChildNode("Build version");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		Swipe("UP", 1);
		verifyIsElementDisplayed(PWAHamburgerMenuPage.objBuildVersion, "Build Version");
		String version = getText(PWAHamburgerMenuPage.objBuildVersion);
		logger.info("Build version is : " + version);
		extent.extentLogger("version", "Build version is : " + version);
		click(PWAHamburgerMenuPage.objCloseHamburger, "Hamburger Close button");

	}

	/**
	 * Function to verify the Footer Section of Home page
	 * 
	 */

	public void FooterSectionValidation(String userType) throws Exception {
		HeaderChildNode("Footer Section");
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		if (userType.equals("SubscribedUser")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfile, "Profile icon");
			PartialSwipe("UP", 3);
		} else {
			verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscribe button");
			PartialSwipe("UP", 4);
		}
		verifyElementPresent(PWAHomePage.objDownloadApps, "Download Apps text");
		verifyElementPresent(PWAHomePage.objAndroidPlayStoreIcon, "Android play store icon");
		verifyElementPresent(PWAHomePage.objIoSAppStoreIcon, "iOS app store icon");//
		// Instagram
		verifyElementPresentAndClick(PWAHomePage.objInstagramIcon, "Instagram icon");
		// androidSwitchTab();

		String currentHandle = getDriver().getWindowHandle();
		System.out.println(currentHandle);
		Set<String> handles = getDriver().getWindowHandles();
		System.out.println(currentHandle);
		Iterator it = handles.iterator();
		for (int i = 0; i < handles.size(); i++) {
			String handle = it.next().toString();
			if (!handle.equals(currentHandle)) {
				getDriver().switchTo().window(handle);
				break;
			}
		}
		String currentUrl = getDriver().getCurrentUrl();

		logger.info("Current URL is " + currentUrl);
		extent.extentLogger("version", "Current URL is " + currentUrl);
		verifyElementPresent(PWAHomePage.objInstagramPage, "Instagram page Follow button");
		Back(1);
		// AndroidSwitchToParentWindow();
		getDriver().switchTo().window(currentHandle);
		// Twitter
		swipeALittleDownForLinks();
		verifyElementPresentAndClick(PWAHomePage.objTwitterIcon, "Twitter icon");
		waitTime(3000);
		getDriver().context("NATIVE_APP");
		try {
			getDriver().findElement(PWALiveTVPage.objTwitterOpenWith).click();
		} catch (Exception e) {
		}
		getDriver().context("CHROMIUM");
		waitTime(5000);
		getDriver().context("NATIVE_APP");
		boolean twitterAppLaunched = false;
		if (verifyIsElementDisplayed(PWAHomePage.objTwitterPage, "Twitter app Follow button")) {
			Back(1);
			twitterAppLaunched = true;
		}
		getDriver().context("CHROMIUM");
		if (twitterAppLaunched == false) {
			androidSwitchTab();
			verifyIsElementDisplayed(PWAHomePage.objTwitterPage, "Twitter page Follow button");
			AndroidSwitchToParentWindow();
		}
		waitTime(2000);
		// Facebook
		verifyElementPresentAndClick(PWAHomePage.objFacebookIcon, "Facebook icon");
		androidSwitchTab();
		String facebook = getDriver().getCurrentUrl();
		if (facebook.contains("facebook")) {
			logger.info("User is redirected to Facebook page");
			extent.extentLogger("Facebook", "User is redirected to Facebook page");
		} else {
			logger.error("Facebook page redirection failed");
			extent.extentLoggerFail("Facebook", "Facebook page redirection failed");
		}
		AndroidSwitchToParentWindow();
		verifyElementPresent(PWAHomePage.objCopyRightText, "Copyright text");
		verifyElementPresentAndClick(PWAHomePage.objUpArrow, "Up Arrow");
		if (getDriver().findElement(PWAHomePage.objUpArrow).isDisplayed() == false) {
			logger.info("After clicking the Up arrow top of the page is displayed");
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void FooterSectionValidationSubscribedUser() throws Exception {
		HeaderChildNode("Footer Section");
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfile, "Profile icon");
		PartialSwipe("UP", 3);
		verifyElementPresent(PWAHomePage.objDownloadApps, "Download Apps text");
		verifyElementPresent(PWAHomePage.objAndroidPlayStoreIcon, "Android play store icon");
		verifyElementPresent(PWAHomePage.objIoSAppStoreIcon, "iOS app store icon");//
		// Instagram
		verifyElementPresentAndClick(PWAHomePage.objInstagramIcon, "Instagram icon");
		androidSwitchTab();
		logger.info("Current URL is " + getDriver().getCurrentUrl());
		extent.extentLogger("version", "Current URL is " + getDriver().getCurrentUrl());
		verifyElementPresent(PWAHomePage.objInstagramPage, "Instagram page Follow button");
		Back(1);
		AndroidSwitchToParentWindow();
		// Twitter
		verifyElementPresentAndClick(PWAHomePage.objTwitterIcon, "Twitter icon");
		waitTime(3000);
		getDriver().context("NATIVE_APP");
		try {
			getDriver().findElement(PWALiveTVPage.objTwitterOpenWith).click();
		} catch (Exception e) {
		}
		getDriver().context("CHROMIUM");
		waitTime(5000);
		getDriver().context("NATIVE_APP");
		boolean twitterAppLaunched = false;
		if (verifyIsElementDisplayed(PWAHomePage.objTwitterPage, "Twitter app Follow button")) {
			Back(1);
			twitterAppLaunched = true;
		}
		getDriver().context("CHROMIUM");
		if (twitterAppLaunched == false) {
			androidSwitchTab();
			verifyIsElementDisplayed(PWAHomePage.objTwitterPage, "Twitter page Follow button");
			AndroidSwitchToParentWindow();
		}
		waitTime(2000);
		// Facebook
		verifyElementPresentAndClick(PWAHomePage.objFacebookIcon, "Facebook icon");
		androidSwitchTab();
		String facebook = getDriver().getCurrentUrl();
		if (facebook.contains("facebook")) {
			logger.info("User is redirected to Facebook page");
			extent.extentLogger("Facebook", "User is redirected to Facebook page");
		}
		AndroidSwitchToParentWindow();
		verifyElementPresent(PWAHomePage.objCopyRightText, "Copyright text");
		verifyElementPresentAndClick(PWAHomePage.objUpArrow, "Up Arrow");
		if (getDriver().findElement(PWAHomePage.objUpArrow).isDisplayed() == false) {
			logger.info("After clicking the Up arrow top of the page is displayed");
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	/**
	 * Function to verify the FAQ's in Help Center Screen under Getting started
	 * category
	 */

	public void gettingStartedVerifications() throws Exception {
		System.out.println(getDriver().getContextHandles());
		getDriver().context("NATIVE_APP");
		verifyElementPresent(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Getting Started"),
				"'Getting Started' tab");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("What is ZEE5"),
				"'What is ZEE5?'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("What is ZEE5"), "Article title 'What is ZEE5?'");
		Back(1);
		waitTime(1000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Registering with ZEE5"),
				"'Registering with ZEE5'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("Registering with ZEE5"),
				"Article title 'Registering with ZEE5'");
		Back(1);
		waitTime(1000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Purchasing a subscription"),
				"'Purchasing a subscription'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("Purchasing a subscription"),
				"Article title 'Purchasing a subscription'");
		Back(1);
		waitTime(3000);
		PartialSwipe("UP", 1);
		waitTime(3000);
		verifyElementPresentAndClick(
				PWAHamburgerMenuPage.objHelpSectioOptionsHeading("How do I watch ZEE5 on my television?"),
				"'How do I watch ZEE5 on my television'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("How do I watch ZEE5 on my television"),
				"Article title 'How do I watch ZEE5 on my television?'");
		Back(1);
		waitTime(3000);
		PartialSwipe("UP", 1);
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Renting movies on ZEEPLEX"),
				"'Renting movies on ZEEPLEX'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("Renting movies on ZEEPLEX"),
				"Article title 'Renting movies on ZEEPLEX'");
	}

	/**
	 * Function to verify the FAQ's in Help Center Screen under My Account category
	 */

	public void myAccountVerifications() throws Exception {
		Back(1);
		waitTime(3000);
		partialSwipeLoop(2);
		waitTime(3000);
		verifyElementPresent(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("My Account"), "'My Account' tab");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Managing your Subscription"),
				"'Managing your Subscription'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("Managing your Subscription"),
				"Article title 'Managing your Subscription'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(2);
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("I can"),
				"I can't sign in to ZEE5");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("t sign in to ZEE5"),
				"Article title 'I can't sign in to ZEE5'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(2);
		waitTime(3000);
		verifyElementPresentAndClick(
				PWAHamburgerMenuPage.objHelpSectioOptionsHeading("I made a payment but my subscription"),
				"'I made a payment but my subscription isn't active / My subscription is missing'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("I made a payment but my subscription"),
				"Article title 'I made a payment but my subscription isn't active / My subscription is missing'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(2);
		waitTime(3000);
		verifyElementPresentAndClick(
				PWAHamburgerMenuPage.objHelpSectioOptionsHeading("I want to update my profile information"),
				"I want to update my profile information");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("I want to update my profile information"),
				"Article title 'I want to update my profile information'");
		Back(1);
	}

	public void watchingZEE5Verifications() throws Exception {
		waitTime(2000);
		partialSwipeLoop(4);
		waitTime(3000);
		verifyElementPresent(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Watching ZEE5"), "'Watching ZEE5' tab");
		verifyElementPresentAndClick(
				PWAHamburgerMenuPage.objHelpSectioOptionsHeading("I need help with playing a video"),
				"I need help with playing a video");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("I need help with playing a video"),
				"Article title 'I need help with playing a video'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(4);
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("I need help with audio"),
				"'I need help with audio'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("I need help with audio"),
				"Article title 'I need help with audio'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(4);
		waitTime(3000);
		verifyElementPresentAndClick(
				PWAHamburgerMenuPage.objHelpSectioOptionsHeading("I need help with casting ZEE5 on my TV"),
				"'I need help with casting ZEE5 on my TV'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("I need help with casting ZEE5 on my TV"),
				"Article title 'I need help with casting ZEE5 on my TV'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(4);
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("All about downloads"),
				"'All about downloads'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("All about downloads"),
				"Article title 'All about downloads'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(4);
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("All about subtitles"),
				"'All about subtitles'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("All about subtitles"),
				"Article title 'All about subtitles'");
		Back(1);
	}

	public void myZEE5AppVerifications() throws Exception {
		waitTime(2000);
		partialSwipeLoop(5);
		waitTime(3000);
		verifyElementPresent(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("My ZEE5 App"), "'My ZEE5 App' tab");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("App Performance"),
				"App Performance");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("App Performance"),
				"Article title 'App Performance'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(5);
		waitTime(3000);
		verifyElementPresentAndClick(
				PWAHamburgerMenuPage.objHelpSectioOptionsHeading("I am unable to authenticate my TV"),
				"'I am unable to authenticate my TV'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("m unable to authenticate my TV"),
				"Article title 'I'm unable to authenticate my TV'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(5);
		waitTime(3000);
		verifyElementPresentAndClick(
				PWAHamburgerMenuPage.objHelpSectioOptionsHeading("How can I enable automatic updates for the ZEE5 app"),
				"'How can I enable automatic updates for the ZEE5 app'");
		verifyElementPresent(
				PWAHamburgerMenuPage.objArticleTitle("How can I enable automatic updates for the ZEE5 app"),
				"Article title 'How can I enable automatic updates for the ZEE5 app'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(5);
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("My ZEE5 App Version"),
				"'My ZEE5 App Version'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("How do I see my current ZEE5 app version"),
				"Article title 'How do I see my current ZEE5 app version?'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(6);
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Parental Control"),
				"'Parental Control'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("Parental Control"),
				"Article title 'Parental Control'");
		Back(1);
	}

	/**
	 * Function to verify the FAQ's in Help Center Screen under Quick Links category
	 */
	public void quickLinksVerifications() throws Exception {
		getDriver().context("NATIVE_APP");
		waitTime(2000);
		partialSwipeLoop(6);
		verifyElementPresent(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Quick Links"), "'Quick Links' tab");
		verifyElementPresentAndClick(
				PWAHamburgerMenuPage.objHelpSectioOptionsHeading("I am unable to watch the ZEEPLEX movie"),
				"I am unable to watch the ZEEPLEX movie");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("I am unable to watch the ZEEPLEX movie"),
				"Article title 'I am unable to watch the ZEEPLEX movie'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(6);
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("How Can I"), "'How Can I?'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("How Can I"), "Article title 'How Can I?'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(6);
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("ZEE5 Offers"), "'ZEE5 Offers'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("ZEE5 Offers"), "Article title 'ZEE5 Offers'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(6);
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("ZEE5 Partnerships"),
				"'ZEE5 Partnerships'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("ZEE5 Partnerships"),
				"Article title 'ZEE5 Partnerships'");
		Back(1);
		waitTime(2000);
		partialSwipeLoop(7);
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Cancel Subscription"),
				"'Cancel Subscription'");
		verifyElementPresent(PWAHamburgerMenuPage.objArticleTitle("How do I cancel my ZEE5 Subscription"),
				"Article title 'How do I cancel my ZEE5 Subscription'");
		Back(1);
		waitTime(2000);
	}

	@SuppressWarnings("rawtypes")
	public void contactUsScreenVerification() throws Exception {
		partialSwipeLoop(8);
		waitTime(3000);
		try {
			WebElement still = getDriver().findElement(By.xpath("//*[contains(@text,'Still need help')]"));
			int x = still.getLocation().getX();
			int width = still.getSize().getWidth();
			int y = still.getLocation().getY();
			int height = still.getSize().getHeight();
			int reqX = x + width + 131;
			int reqY = y + (height / 2);
			TouchAction act = new TouchAction(getDriver());
			act.tap(PointOption.point(reqX, reqY)).perform();
			waitTime(3000);
			getDriver().findElement(PWALiveTVPage.objChromeOpenWith).click();
		} catch (Exception e) {
		}
		verifyElementPresent(PWAHomePage.objcontactus, "Contact Us page");
		if (checkElementDisplayed(PWAHamburgerMenuPage.objzeeAppCloseContactUs, "Close of Contact Us Page")) {
			click(PWAHamburgerMenuPage.objzeeAppCloseContactUs, "Close of Contact Us Page");
			Back(1);
			if (checkElementDisplayed(PWAHamburgerMenuPage.objzeeAppInterstitialAddClose, "Close of Interstitial Ad")) {
				click(PWAHamburgerMenuPage.objzeeAppInterstitialAddClose, "Close of Interstitial Ad");
			}
			for (int i = 0; i < 3; i++) {
				if (checkElementDisplayed(PWAHamburgerMenuPage.objzeeAppExit, "Exit of ZEE5 App")) {
					click(PWAHamburgerMenuPage.objzeeAppExit, "Exit of ZEE5 App");
					break;
				} else {
					Back(1);
					waitTime(3000);
				}
			}
		} else {
			Back(1);
			waitTime(3000);
			Back(1);
			waitTime(5000);
		}
		getDriver().context("CHROMIUM");
		// AndroidSwitchToParentWindow();
	}

	public void partialSwipeLoop(int count) throws Exception {
		for (int i = 0; i < count; i++) {
			PartialSwipe("UP", 1);
			waitTime(1000);
		}
	}

	/**
	 * Function to verify the Display language and Static pages Content language
	 */
	public void contentLanguagewithDisplayLanguage() throws Exception {

		// Changing display language to Kannada
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguage, "Language option");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objKannadaLanguage, "Kannada option");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objApply, "Apply button on display langauge screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objApplybutton, "Apply button on content language screen");

		// About Us
		waitTime(8000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAboutUsinKannada, "About Us option in kannada");
		String title1 = getText(PWAHamburgerMenuPage.objAboutUsTextInPage);
		if (title1.contains("About Us")) {
			logger.info("Title of the page displayed: " + title1);
			extent.extentLogger("pagetitle", "Title of the page displayed: " + title1);
			logger.error("Content of the About Us page is not according to the display language");
			extent.extentLoggerFail("About Us",
					"Content of the page About Us is not according to the display language");
		}
		Back(1);

		// Terms of USe
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		Swipe("UP", 1);
		waitForElementAndClick(PWAHamburgerMenuPage.objTermsInKannada, 5, "Terms of Use option in Kannada");
		String title2 = getText(PWAHamburgerMenuPage.objTermsOfUseScreen);
		if (title2.contains("Terms of Use")) {
			logger.info("Title of the page displayed: " + title2);
			extent.extentLogger("pagetitle", "Title of the page displayed: " + title2);
			logger.error("Content of the Terms of Use page is not according to the display language");
			extent.extentLoggerFail("Terms of Use",
					"Content of the Terms of Use page is not according to the display language");
		}
		Back(1);
		// Privacy Policy
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicyInKannadA,
				"Privacy Policy option in Kannada");
		String title3 = getText(PWAHamburgerMenuPage.objPrivacyPolicyScreen);
		if (title3.contains("Privacy Policy")) {
			logger.info("Title of the page displayed: " + title3);
			extent.extentLogger("pagetitle", "Title of the page displayed: " + title3);
			logger.error("Content of the Privacy Policy page is not according to the display language");
			extent.extentLoggerFail("Privacy Policy",
					"Content of the Privacy Policy page is not according to the display language");
		}
		Back(1);
		// Changing display language to English
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguage, "Language option");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEnglishOption, "English option");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objApply, "Apply button on display langauge screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objApplybutton, "Apply button on content language screen");
	}

	@SuppressWarnings("rawtypes")
	public void ChannelGuide(String userType) throws Exception {
		extent.HeaderChildNode("Validating that user is navigated to channel guide screen");
		verifyElementPresentAndClick(PWALiveTVPage.objNothighlightedChannelGuideToggle, "Channel guide toggle");
		waitTime(5000);
		waitForElementDisplayed(PWALiveTVPage.objHighlightedChannelGuideToggle, 5);
		if (verifyElementPresent(PWALiveTVPage.objHighlightedChannelGuideToggle, "Channel guide toggle")) {

			logger.info("Channel guide toggle is highlighted, User is navigated to Channel guide screen");
			extent.extentLogger("Channel guide",
					"Channel guide toggle is highlighted, User is navigated to Channel guide screen");

		}

		if (userType.equalsIgnoreCase("SubscribedUser")) {
			remainderOptionOnUpcomingShow();
			JSClick(PWALiveTVPage.objTodayDate, "Today's date");
		}
		extent.HeaderChildNode("Validating that user is able to scroll trough the channel list");
		Swipe("UP", 1);
		Swipe("DOWN", 1);
		extent.HeaderChildNode("Validating that On going live show cards are highlighted");
		waitExplicitlyForElementPresence(PWALiveTVPage.objFirstOngoingLiveTvShowCard, 60, "Ongoing Live TV Show Card");
		String ongoingLiveTvcardClass = getAttributValue("class", PWALiveTVPage.objFirstOngoingLiveTvShowCard);
		if (ongoingLiveTvcardClass.contains("active")) {
			logger.info("On going live show cards are highlighted on channel guide screen: ");
			extent.extentLogger("On going live show card",
					"On going live show cards are highlighted on channel guide screen");
		} else {
			logger.info("On going live show cards are not highlighted on channel guide screen");
			extent.extentLogger("On going live show card",
					"On going live show cards are not highlighted on channel guide screen");
		}

		extent.HeaderChildNode("Validating that user is navigated to respective live TV consumption screen");
		System.out.println("Validating that user is navigated to respective live TV consumption screen");
		List<WebElement> ongoingshows = findElements(PWALiveTVPage.objOngoingLiveTvShowTitles);
		String onGoingLiveTvShowCardTitle = "";
		for (int i = 0; i < ongoingshows.size(); i++) {
			onGoingLiveTvShowCardTitle = ongoingshows.get(i).getAttribute("innerText");
			System.out.println(onGoingLiveTvShowCardTitle);
			if (JSClick(PWALiveTVPage.objOngoingLiveTvShowTitles(i + 1),
					"Ongoing Live TV Show card: " + onGoingLiveTvShowCardTitle)) {
				break;
			}
		}
		waitTime(7000);
		String ConsumptionScreenShowTitle = getText(PWASearchPage.objShowTitleInconsumptionPage);
		logger.info("Navigated to Consumption page:" + ConsumptionScreenShowTitle);
		extent.extentLogger("", "Navigated to consumption screen: " + ConsumptionScreenShowTitle);
		if (ConsumptionScreenShowTitle.equals("")) {
			logger.error("User is not navigated to consumption screen from Channel Guide Screen");
			extent.extentLoggerFail("Consumption Screen",
					"User is not navigated to consumption screen from Channel Guide Screen");
		} else {
			logger.info("User is navigated to consumption screen from Channel Guide screen");
			extent.extentLogger("Consumption Screen",
					"User is navigated to consumption screen from Channel Guid screen");
		}
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			Back(1);
			boolean clickedUpcomingCard = false;
			for (int i = 0; i <= 5; i++) {
				if (JSClick(PWALiveTVPage.objFirstUpcomingShowcard, "Upcoming Live Program")) {
					clickedUpcomingCard = true;
					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (clickedUpcomingCard == true) {
				waitForElementDisplayed(PWALiveTVPage.objUpcomingShowContentDialoguebox, 5);
				verifyElementPresent(PWALiveTVPage.objUpcomingShowContentDialoguebox, "Show Info PopUp");
				waitForElementDisplayed(PWALiveTVPage.objUpcomingLiveProgramShareBtn, 5);
				verifyElementPresentAndClick(PWALiveTVPage.objUpcomingLiveProgramShareBtn, "Share button");
				waitTime(3000);
				System.out.println(getDriver().getContextHandles());
				getDriver().context("NATIVE_APP");
				Dimension dim = getDriver().manage().window().getSize();
				int startx = (int) (dim.width * 0.6);
				int starty = (int) (dim.height * 0.7);
				int endx = (int) (startx * 0.1);
				int endy = starty;
				for (int i = 0; i < 2; i++) {
					try {
						Swipe("UP", 1);
						getDriver().findElement(PWAShowsPage.objFacebookApp).click();
						break;
					} catch (Exception e) {
						TouchAction act = new TouchAction(getDriver());
						act.press(PointOption.point(startx, starty))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
								.moveTo(PointOption.point(endx, endy)).release().perform();
					}
				}
				waitForElementDisplayed(PWAShowsPage.objFacebookPostBtn, 10);
				waitForElementAndClick(PWAShowsPage.objFacebookPostBtn, 10, "POST button in Facebook App");
				Back(1);
				getDriver().context("CHROMIUM");
				click(PWALiveTVPage.objUpcomingLiveProgramClose, "Close in Upcoming Live Program Pop Up");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			} else {
				logger.error("Failed to click on Upcomig Live Program");
				extent.extentLoggerFail("Consumption Screen", "Failed to click on Upcomig Live Program");
			}

		} else {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
	}

	public void navigationToConsumptionScreenThroughTrendingSearches() throws Exception {
		extent.HeaderChildNode("Validation of Trending Searches");
		click(PWAHomePage.objSearchBtn, "Search icon");
		waitTime(3000);
		if (verifyIsElementDisplayed(PWASearchPage.objTrendingSearchesTray, "Trending Searches tray")) {
			verifyIsElementDisplayed(PWASearchPage.objFirstAssetThumbnailTrendingSearch,
					"First asset thumbnail of Trending searches tray");
			verifyIsElementDisplayed(PWASearchPage.objFirstAssetTitleTrendingSearch,
					"First asset title of Trending searches tray");
			String searchScreenTitle = getElementPropertyToString("innerText",
					PWASearchPage.objFirstAssetTitleTrendingSearch, "First Asset Title Trending Search");
			logger.info("First asset title of Trending searches tray: " + searchScreenTitle);
			extent.extentLogger("", "First asset title of Trending searches tray: " + searchScreenTitle);
			// handle mandatory pop up
			String user = getParameterFromXML("userType");
			mandatoryRegistrationPopUp(user);
			click(PWASearchPage.objFirstAssetThumbnailTrendingSearch,
					"First asset thumbnail of Trending searches tray");
			waitTime(6000);
			// Show details page
			if (verifyElementDisplayed(PWAShowsPage.objShowsTitle)) {
				logger.info("Show details page is displayed");
				extent.extentLogger("", "Show details page is displayed");
				String ShowTitle = getText(PWAShowsPage.objShowsTitle);
				logger.info("Show Details page title fetched : " + ShowTitle);
				extent.extentLogger("show details screen", "Show Details page title fetched : " + ShowTitle);
				waitTime(3000);
				if (searchScreenTitle.contains(ShowTitle)) {
					logger.info("User is navigated to respective show details screen");
					extent.extentLogger("Consumption Screen", "User is navigated to respective show details screen");
				} else {
					logger.error("User is not navigated to respective show details screen");
					extent.extentLoggerFail("Consumption Screen",
							"User is not navigated to respective show details screen");
				}
			}
			// Consumptions screen
			if (verifyElementDisplayed(PWAPlayerPage.objContentTitleInConsumptionPage)) {
				logger.info("Consumptions page is displayed");
				extent.extentLogger("", "Consumptions page is displayed");
				String contentPlayed = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
				logger.info("Content played: " + contentPlayed);
				extent.extentLogger("contentPlayed", "Content played: " + contentPlayed);
				String fromShow = getText(PWAPlayerPage.objConsumptionsShowTitle);
				logger.info("Show displayed in Consumptions page: " + fromShow);
				extent.extentLogger("showDisplayed", "Show displayed in Consumptions page: " + fromShow);
				if (fromShow.contains(searchScreenTitle) || searchScreenTitle.contains(fromShow)) {
					logger.info("User is navigated to respective consumption screen");
					extent.extentLogger("Consumption Screen", "User is navigated to respective consumption screen");
				} else {
					logger.error("User is not navigated to respective consumption screen");
					extent.extentLoggerFail("Consumption Screen",
							"User is not navigated to respective consumption screen");
				}
			}
		} else {
			logger.error("Failed to verify Trending Searches tray");
			extent.extentLoggerFail("", "Failed to verify Trending Searches tray");
		}
		if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
			click(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up close icon");
		}
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void voiceInput() throws Exception {
		extent.HeaderChildNode("Validating that user is asked to give the voice input post tapping on microphone icon");
		verifyElementPresentAndClick(PWASearchPage.objVoiceSearchButton, "Voice seach icon");
		waitTime(4000);
		getDriver().context("NATIVE_APP");
		directClickReturnBoolean(PWASearchPage.objallow, "Allow in pop up");
		directClickReturnBoolean(PWASearchPage.objallowCaps, "ALLOW in pop up");
		getDriver().context("CHROMIUM");
		waitTime(6000);
		click(PWASearchPage.objVoiceSearchButton, "Voice seach icon");
		String searchBarText = getAttributValue("placeholder", PWASearchPage.objSearchEditBox);
		if (searchBarText.equalsIgnoreCase("Speak to Search on ZEE5")) {
			logger.info("'Speak to Search on ZEE5' is displayed on search edit box");
			extent.extentLogger("Voice input", "'Speak to Search on ZEE5' is displayed on search edit box");
			logger.info("User is asked to give the voice input");
			extent.extentLogger("Voice input", "User is asked to give the voice input");
		} else {
			logger.error("'Speak to Search on ZEE5' is not displayed on search edit box");
			extent.extentLoggerFail("Voice input", "'Speak to Search on ZEE5' is not displayed on search edit box");
			logger.error("User is not asked to give the voice input");
			extent.extentLoggerFail("Voice input", "User is not asked to give the voice input");
		}
	}

	public void UserActions(String userType) throws Exception {
		if (userType.equals("Guest")) {
			UserActionGuestUser();
		} else if (userType.equals("NonSubscribedUser")) {
			UserActionLoggedInUser();
		} else {
			ContinueWatching();
			MyReminder();
			MyWatchlistSubscribedUser();
		}
	}

	/* ================Logged In user -User action================ */
	public void UserActionLoggedInUser() throws Exception {
		extent.HeaderChildNode("User Actions module- Non Subscribed User Validations");
		// Verify Continue watching tray is displayed
		if (verifyIsElementDisplayed(PWAHomePage.objContinueWatchingTray, "Coninue Watching tray") == true) {
			extent.extentLogger("Verify Continue watching tray ",
					getDriver().findElement(PWAHomePage.objContinueWatchingTray).getText()
							+ " tray is displayed for Non subscribed user");
			logger.info(getDriver().findElement(PWAHomePage.objContinueWatchingTray).getText()
					+ " tray is displayed for Non subscribed user");
		} else {
			extent.extentLoggerFail("Verify continue watching tray",
					"Continue watching tray is not displayed for Non subscribed user");
			logger.info("Continue watching tray is not displayed for Non subscribed user");
		}
		// Verify Add to Watch list is displayed in Content consumption screen
		// Search any content
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		click(PWAHomePage.objSearchBtn, "Search button");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("consumptionsFreeContent");
		type(PWAHomePage.objSearchField, keyword + "\n", "Search");
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		String contentName = getElementPropertyToString("innerText", PWAPlayerPage.objContentName, "Title");
		verifyElementPresentAndClick(PWAPlayerPage.watchListBtn, "Add to Watchlist");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		click(PWAHamburgerMenuPage.objMyAccount, "My account");
		click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist");
		click(PWAAddToWatchListPage.objMoviesTab, "Movies tab");
		String ContentNameInWatchlist = getElementPropertyToString("innerText",
				PWAAddToWatchListPage.objFirstContentInWatchlist, "Content title");
		if (contentName.equals(ContentNameInWatchlist)) {
			extent.extentLogger("Verify Watchlist", "Added content is displayed in Watchlist screen");
			logger.info("Added content is displayed in Watchlist screen");
		} else {
			extent.extentLoggerFail("Verify Watchlist", "Added content is not displayed in Watchlist screen");
			logger.info("Added content is not displayed in Watchlist screen");
		}
		click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all");

	}

	public void ContinueWatching() throws Exception {
		extent.HeaderChildNode("User Actions : Continue Watching tray validations");
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);

		// Verify Progress bar is displayed for continue watching tray
		verifyElementPresent(PWAContinueWatchingTrayPage.objProgressBar, "Progress bar");
		// Verify Progress bar is updated accordingly
		String beforePlayingContent = getElementPropertyToString("style",
				PWAContinueWatchingTrayPage.objProgressBarProgress(1), "Progress bar");
		extent.extentLogger("", "Progress bar filler width before playing: " + beforePlayingContent);
		logger.info("Progress bar filler width before playing: " + beforePlayingContent);
		String[] originalRatio = beforePlayingContent.split(":");
		String progress = originalRatio[1];
		String[] exactRatio = progress.split("%");
		float progressedTime = Float.parseFloat(exactRatio[0]);
		System.out.println(progressedTime);
		// Left watch time before playing content
		String leftWatchTime = getElementPropertyToString("innerText", PWAContinueWatchingTrayPage.objTotalTimeLeft(1),
				"Left time before watching content");
		String[] leftTime = leftWatchTime.split("left");
		String timeBeforeWatchingContent = leftTime[0];
		System.out.println(timeBeforeWatchingContent);
		// Verify partially watched contents are added or updated
		// Play any content
		String playingContent = getElementPropertyToString("innerText", PWAContinueWatchingTrayPage.objCardTitle(1),
				"Content");
		// Click on the content
		click(PWAContinueWatchingTrayPage.objCardTitle(1), "Content: " + playingContent);
		Thread.sleep(180000);
		// Navigate to home page
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		Thread.sleep(4000);
		String updatedContent = getElementPropertyToString("innerText", PWAContinueWatchingTrayPage.objCardTitle(1),
				"Content");
		if (playingContent.equals(updatedContent)) {
			extent.extentLogger("Verify partially watched content",
					"Partially watched contents are added to Continue watching tray");
			logger.info("Partially watched contents are added to Continue watching tray");
		} else {
			extent.extentLoggerFail("Verify partially watched content",
					"Partially watched contents are not added to Continue watching tray");
			logger.info("Partially watched contents are not added to Continue watching tray");
		}

		// Verify Progress bar is updated after watching any content from CW tray
		String afterPlayingContent = getElementPropertyToString("style",
				PWAContinueWatchingTrayPage.objProgressBarProgress(1), "Progress bar");
		extent.extentLogger("", "Progress bar filler width after playing: " + afterPlayingContent);
		logger.info("Progress bar filler width after playing: " + afterPlayingContent);
		String[] originalRatio1 = afterPlayingContent.split(":");
		String progress1 = originalRatio1[1];
		String[] exactRatio1 = progress1.split("%");
		float progressedTimeAfterWatching = Float.parseFloat(exactRatio1[0]);
		System.out.println(progressedTimeAfterWatching);
		if (progressedTime < progressedTimeAfterWatching) {
			extent.extentLogger("Verify progress bar", "Progress bar is updated from " + progress + " to " + progress1);
			logger.info("Progress bar is updated from " + progress + " to " + progress1);
		} else {
			extent.extentLoggerFail("Verify progress bar",
					"Progress bar is not updated after watching content, before was " + progress
							+ " and now updated to " + progress1);
			logger.error("Progress bar is not updated after watching content, before was " + progress
					+ " and now updated to " + progress1);
		}

		// Verify left watch time is updated accordingly

		// Left watch time after playing content
		String leftWatchTime1 = getElementPropertyToString("innerText", PWAContinueWatchingTrayPage.objTotalTimeLeft(1),
				"Left time before watching content");
		String[] leftTime1 = leftWatchTime1.split("left");
		String timeAfterWatchingContent = leftTime1[0];
		System.out.println(timeAfterWatchingContent);
		if (!timeBeforeWatchingContent.equals(timeAfterWatchingContent)) {
			extent.extentLogger("Verify left watch time", "The left time Before watching content is " + leftTime[0]
					+ "and the left time after watching content for some time is " + leftTime1[0]);
			logger.info("The left time Before watching content is " + leftTime[0]
					+ "and the left time after watching content for some time is " + leftTime1[0]);
		} else {
			extent.extentLoggerFail("Verify Left watch time",
					"Left Watch time is not getting updated after watching content from Continue watching tray");
			logger.info("Left Watch time is not getting updated after watching content from Continue watching tray");
		}
	}

	public boolean directClickReturnBoolean(By byLocator, String validationtext) throws Exception {
		try {
			WebElement element = (new WebDriverWait(getDriver(), 1))
					.until(ExpectedConditions.presenceOfElementLocated(byLocator));
			if (element.isDisplayed() == true) {
				element.click();
				logger.info("Clicked on " + validationtext);
				extent.extentLogger("click", "Clicked on " + validationtext);
				element = null;
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public void dismissSystemPopUp() throws Exception {
		getDriver().context("NATIVE_APP");
		directClickReturnBoolean(PWASearchPage.objallow, "Allow in pop up");
		getDriver().context("CHROMIUM");
	}

	public void dismissAllPopUps() throws Exception {
		for (int trial = 0; trial < 8; trial++) {
			if (directClickReturnBoolean(PWAHomePage.objAppInstallPopUpClose, "Close in App Install Pop Up"))
				break;
			waitTime(5000);
		}
		for (int trial = 0; trial < 2; trial++) {
			try {
				waitTime(5000);
				getDriver().context("NATIVE_APP");
				directClickReturnBoolean(PWASearchPage.objallow, "Allow in pop up");
				getDriver().context("CHROMIUM");
				directClickReturnBoolean(PWAHomePage.objAppInstallPopUpClose, "Close in App Install Pop Up");
				directClickReturnBoolean(PWAHomePage.objStayTunedPopUpClose, "Close in Stay Tuned Pop Up");
				WebElement displayContentLang = (new WebDriverWait(getDriver(), 60))
						.until(ExpectedConditions.elementToBeClickable(PWAHomePage.objContinueDisplayContentLangPopup));
				if (displayContentLang.isDisplayed() == true) {
					if (directClickReturnBoolean(PWAHomePage.objContinueDisplayContentLangPopup,
							"Continue on Display Language Pop Up")) {
						dismissSystemPopUp();
						waitTime(3000);
						directClickReturnBoolean(PWAHomePage.objContinueDisplayContentLangPopup,
								"Continue on Content Language Pop Up");
						break;
					}
				}
			} catch (Exception e) {
			}
		}
		// dismissSystemPopUp();
		// waitTime(3000);
		// dismissSystemPopUp();
		// dismissAppInstallPopUp();
		// dismissStayTundedPopUp();
		// dismiss3xPopUp();
		// dismissDisplayContentLanguagePopUp();
		// dismissSystemPopUp();
	}

	public void FilterLanguage(String lang) throws Exception {
		click(PWALiveTVPage.objFilterLanguageChannelGuide, "Filter language");
		int size = findElements(PWALiveTVPage.objSelectedlang).size();
		for (int i = 1; i <= size; i++) {
			click(PWALiveTVPage.objSelectedlang, "Selected language");
		}
		click(PWALiveTVPage.objSelectLang(lang), lang + " language");
		click(PWALiveTVPage.objApplyBtn, "Apply button");
//		click(PWALiveTVPage.objApplyBtn,"Apply button");
	}

	public void FilterLanguageUnselectOthers(String language, String genre) throws Exception {
		click(PWALiveTVPage.objFilterLanguageChannelGuide, "Filter language");
		// Unselect all languages
		int size = findElements(PWALiveTVPage.objSelectedlang).size();
		for (int i = 1; i <= size; i++) {
			click(PWALiveTVPage.objSelectedlang, "Selected language to unselect");
		}
		click(PWALiveTVPage.objSelectLang(language), language + " language");
		click(PWALiveTVPage.objGenreBtn, "Genre tab");
		size = findElements(PWALiveTVPage.objSelectedlang).size();
		for (int i = 1; i <= size; i++) {
			click(PWALiveTVPage.objSelectedlang, "Selected genre to unselect");
		}
		click(PWALiveTVPage.objSelectLang(genre), genre + " genre");
		click(PWALiveTVPage.objApplyBtn, "Apply button");
	}

	@SuppressWarnings("rawtypes")
	public void swipeALittleDownForLinks() {
		Dimension size = getDriver().manage().window().getSize();
		TouchAction touchAction = new TouchAction(getDriver());
		int starty = (int) (size.height * 0.5);
		int endy = (int) (size.height * 0.7);
		int startx = size.width / 2;
		touchAction.press(PointOption.point(startx, starty))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(startx, endy))
				.release().perform();
	}

	public void reloadHome() throws Exception {
		String url = getParameterFromXML("url");
		System.out.println(getDriver());
		getDriver().get(url);
		waitTime(5000);
		// dismissAppInstallPopUp();
	}

	public boolean waitforLiveTabToLoad() {
		for (int i = 0; i < 15; i++) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objFirstCardFreeChnnelName)) {
				return true;
			} else {
				waitTime(10000);
			}
		}
		extent.extentLoggerFail("", "Live TV failed to load even after waiting for 2 minutes");
		logger.error("Live TV failed to load even after waiting for 2 minutes");
		return false;
	}

	public boolean waitforChannelGuideToLoad() {
		for (int i = 0; i < 15; i++) {
			if (verifyIsElementDisplayed(PWALiveTVPage.objChannelWrapper)) {
				return true;
			} else {
				waitTime(10000);
			}
		}
		extent.extentLoggerFail("", "Channel Guide failed to load even after waiting for 2 minutes");
		logger.info("Channel Guide failed to load even after waiting for 2 minutes");
		return false;
	}

	public boolean waitforNewsConsumptionsToLoad() throws Exception {
		for (int i = 0; i < 15; i++) {
			if (verifyIsElementDisplayed(PWANewsPage.objLiveNewsConsumptionsTitle)) {
				System.out.println(getText(PWANewsPage.objLiveNewsConsumptionsTitle));
				if (getText(PWANewsPage.objLiveNewsConsumptionsTitle).equals("N\\/A")) {
					logger.info("N/A is displayed as title, waiting for metdata to load");
					extent.extentLogger("", "N/A is displayed as title, waiting for metdata to load");
				} else {
					logger.info("News consumptions page metadata loaded");
					extent.extentLogger("", "News consumptions page metadata loaded");
					return true;
				}
			} else {
				waitTime(10000);
				System.out.println("iteration " + i);
			}
		}
		extent.extentLoggerFail("", "News consumptions page metadata failed to load even after waiting for 2 minutes");
		logger.info("News consumptions page metadata failed to load even after waiting for 2 minutes");
		return false;
	}

	public void launchCheck(String userType) throws Exception {
		HeaderChildNode("Verifying that on launch user can see signup/login option");
		verifyElementPresentAndClick(PWAHomePage.objHamburgerMenu, "Hamburger menu");
		if (userType.equals("Guest")) {
			if (checkElementExist(PWALoginPage.objSignUpBtn, "Sign up button")) {
				logger.info("Guest user can see option signup after launch");
				extentLogger("Launch", "Guest user can see option signup after launch");
			} else {
				logger.info("Something went wrong");
			}
			if (checkElementExist(PWALoginPage.objLoginBtn, "Login button")) {
				logger.info("Guest user can see option signup after launch");
				extentLogger("Launch", "Guest user can see option signup after launch");
			} else {
				logger.info("Something went wrong");
			}
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			if (!checkElementExist(PWALoginPage.objSignUpBtn, "Sign up button")) {
				logger.info(userType + "cannot see option signup after launch");
				extentLogger("Launch", userType + "cannot see option signup after launch");
			} else {
				logger.info("Something went wrong");
			}
			if (!checkElementExist(PWALoginPage.objLoginBtn, "Login button")) {
				logger.info(userType + "cannot see option login after launch");
				extentLogger("Launch", userType + "cannot see option login after launch");
			} else {
				logger.info("Something went wrong");
			}
		}
		click(PWAHamburgerMenuPage.objCloseHamburger, "Close button");
	}

	public void typeAndGetSearchResult(By locator, String keyword, String field) throws Exception {
		String last = keyword.substring(keyword.length() - 1, keyword.length());
		System.out.println(last);
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		findElement(PWASearchPage.objSearchEditBox).sendKeys(Keys.BACK_SPACE.toString());
		findElement(PWASearchPage.objSearchEditBox).sendKeys(last);
		waitTime(4000);
	}

	public boolean waitExplicitlyForElementPresence(By locator, int seconds, String element) {
		try {
			(new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.presenceOfElementLocated(locator));
			logger.info(element + " is present");
			extentLogger("", element + " is present");
			return true;
		} catch (Exception e) {
			logger.info(element + " is not present");
			extentLogger("", element + " is not present");
			return false;
		}
	}

	public boolean waitExplicitlyForElementClickable(By locator, int seconds, String element) {
		try {
			(new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(locator));
			logger.info(element + " is clickable");
			extentLogger("", element + " is claikcable");
			return true;
		} catch (Exception e) {
			logger.info(element + " is not clickable");
			extentLogger("", element + " is not clickable");
			return false;
		}
	}

	///////////////////////// HLS //////////////

	public void OnboardingScenariosHLS(String userType) throws Exception {
		switch (userType) {
		case "Guest":
			silentRegistrationViaEmail();
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			logout();
			extent.HeaderChildNode("HLS_004 : Login via User Social Login Credentials");
			facebookLogin();
			logout();
			extent.HeaderChildNode("HLS_008 : Verify user is able to play the content from the carousel");
			playContentFromCarousel("Movies");
			break;
		case "ClubUser":
			// loginWithUserEmail("edpwa4@mailnesia.com","1234567");
			extent.HeaderChildNode("HLS_009 : Verify Club user is able to play the content from the carousel");
			playContentFromCarousel("Club");
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode("HLS_010 : Verify user is able to play the content from the rails");
			navigateToAnyScreen("Club");
			verifyElementPresentAndClick(PWALandingPages.trayCard, "Card from Tray");
			if (verifyElementPresent(PWAPlayerPage.objPlayerControlScreen, "Player control containing screen")) {
				pausePlayer();
				logger.info("Verify play icon functionality is Pass");
				extent.extentLogger("Play btn validation", "Verify play icon functionality is Pass");
			} else {
				logger.error("Verify play icon functionality is Fail");
				extent.extentLoggerFail("Play btn validation", "Verify play icon functionality is Fail");
			}
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("HLS_008 : Verify user is able to play the content from the carousel");
			playContentFromCarousel("Movies");
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode("HLS_010 : Verify user is able to play the content from the rails");
			navigateToAnyScreen("Club");
			verifyElementPresentAndClick(PWALandingPages.trayCard, "Card from Tray");
			if (verifyElementPresent(PWAPlayerPage.objPlayerControlScreen, "Player control containing screen")) {
				pausePlayer();
				logger.info("Verify play icon functionality is Pass");
				extent.extentLogger("Play btn validation", "Verify play icon functionality is Pass");
			} else {
				logger.error("Verify play icon functionality is Fail");
				extent.extentLoggerFail("Play btn validation", "Verify play icon functionality is Fail");
			}
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode("HLS_011 : Verify user is able to play the content from the collection page");
			playCardFromCollections(userType, "Club");
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("HLS_008 : Verify user is able to play the content from the carousel");
			playContentFromCarousel("Movies");
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode("HLS_010 : Verify user is able to play the content from the rails");
			navigateToAnyScreen("Club");
			verifyElementPresentAndClick(PWALandingPages.trayCard, "Card from Tray");
			if (verifyElementPresent(PWAPlayerPage.objPlayerControlScreen, "Player control containing screen")) {
				pausePlayer();
				logger.info("Verify play icon functionality is Pass");
				extent.extentLogger("Play btn validation", "Verify play icon functionality is Pass");
			} else {
				logger.error("Verify play icon functionality is Fail");
				extent.extentLoggerFail("Play btn validation", "Verify play icon functionality is Fail");
			}
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode("HLS_011 : Verify user is able to play the content from the collection page");
			playCardFromCollections(userType, "Club");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
	}

	public void silentRegistrationViaEmail() throws Exception {
		extent.HeaderChildNode("HLS_003 : Silent registration via email");
		verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscribe Button");
		Swipe("UP", 1);
		verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue button");
		String generatedEmail = RandomStringGenerator(5) + "@g.com";
		type(PWALoginPage.objEmailField, generatedEmail, "Email");
		hideKeyboard();
		waitTime(3000);
		click(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed button");
		waitTime(3000);
		verifyIsElementDisplayed(PWASubscriptionPages.objPasswordPopupInSubscriptionPage, "Password field");
		type(PWASubscriptionPages.objPasswordField, "123456\n", "Password");
		hideKeyboard();
		// click(PWASubscriptionPages.objProceedButtonInPassword, "Proceed");
		waitTime(10000);
		if (verifyIsElementDisplayed(PWASubscriptionPages.objAccountDetailInSubscription, "Account details")) {
			String email = getElementPropertyToString("innerText", PWASubscriptionPages.objAccountDetailInSubscription,
					"Registered Email");
			logger.info("Registered user email is : " + email);
			extent.extentLogger("Verification", "Registered user email is : " + email);
			if (generatedEmail.equals(email)) {
				logger.info("HLS_003 : Silent registration via email has passed");
				extent.extentLoggerPass("Verification", "HLS_003 : Silent registration via email has passed");
			}
		} else {
			logger.error("HLS_003 : Silent registration via email has failed");
			extent.extentLoggerFail("Verification", "HLS_003 : Silent registration via email has failed");
		}
	}

	public void playContentFromCarousel(String screen) throws Exception {
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		if (navigateToAnyScreen(screen)) {
			waitTime(5000);
			for (int i = 0; i < 10; i++) {
				try {
					waitForElementAndClick(PWAHomePage.objPlayBtn, 20, "Play icon");
					break;
				} catch (Exception e) {
					waitTime(1000);
					if (i == 9)
						logger.info("Failed to click");
				}
			}
			waitTime(2000);
			if (verifyElementPresent(PWAPlayerPage.objPlayerControlScreen, "Player control containing screen")) {
				pausePlayer();
				logger.info("Verify play icon functionality is Pass");
				extent.extentLogger("Play btn validation", "Verify play icon functionality is Pass");
			} else {
				logger.error("Verify play icon functionality is Fail");
				extent.extentLoggerFail("Play btn validation", "Verify play icon functionality is Fail");
			}
			Back(1);
		} else {
			logger.error("Failed to validate play icon functionality on tab : " + screen);
			extent.extentLoggerFail("play", "Failed to validate play icon functionality on tab : " + screen);
		}
	}

	public void playCardFromCollections(String userType, String tabName) throws Exception {
		navigateToAnyScreen(tabName);
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		// Hit API get the first tray and click on first item
		boolean clickedviewall = false;
		// String languageSmallText = allSelectedLanguages();
		Response tabResponse = ResponseInstance.getResponseForPages(tabName.toLowerCase(), "en,hi,kn");
		// System.out.println(tabResponse.getBody().asString());
		String trayTitle = "", traynumber = "";
		int tags = 0;
		main: for (int i = 0; i < 10; i++) {
			try {
				tags = tabResponse.jsonPath().get("buckets[" + i + "].tags.size()");
				// System.out.println("tags: "+tags);
			} catch (Exception e) {
			}
			for (int j = 0; j < tags; j++) {
				String tag = tabResponse.jsonPath().get("buckets[" + i + "].tags[" + j + "]");
				// System.out.println("tag: "+tag);
				if (tag.equalsIgnoreCase("title") || tag.equalsIgnoreCase("movies") || tag.equalsIgnoreCase("episodes")
						|| tag.equalsIgnoreCase("portrait_small") || tag.equalsIgnoreCase("news_title")) {
					traynumber = String.valueOf(i);
					break main;
				}
			}
		}
		if (!traynumber.equals("")) {
			// tray name from api
			trayTitle = tabResponse.jsonPath().get("buckets[" + traynumber + "].title");
			logger.info("Tray from API: " + trayTitle);
			extent.extentLogger("", "Tray from API: " + trayTitle);
			verifyElementPresent(PWALandingPages.objTrayTitleInUIContains(trayTitle),
					"Tray title : " + trayTitle + " in UI");
			for (int i = 1; i <= 5; i++) {
				if (verifyIsElementDisplayed(PWALandingPages.objTrayTitleInUIContains(trayTitle), "Tray")) {
					logger.info("Tray is loaded");
					extent.extentLogger("Tray load", "Tray is loaded");
					break;
				} else {
					Swipe("UP", 1);
					waitTime(2000);
				}
			}
			for (int i = 0; i < 5; i++) {
				if (directClickReturnBoolean(PWALandingPages.objTrayTitleInUIContainsViewAll(trayTitle.trim()),
						"View All Button of tray " + trayTitle)) {
					waitTime(5000);
					clickedviewall = true;
					break;
				} else {
					Swipe("UP", 1);
					waitTime(2000);
				}
			}
		}
		if (clickedviewall == true) {
			if (verifyIsElementDisplayed(PWAPremiumPage.objViewAllPage, "View All Page")) {
				logger.info("Navigated to View All Page");
				extent.extentLoggerPass("View All", "Navigated to View All Page");
				if (tabName.equals("Play")) {
					Back(1);
				} else {
					waitTime(3000);
					// handle mandatory pop up
					mandatoryRegistrationPopUp(userType);
					waitTime(5000);
					if (directClickReturnBoolean(PWALandingPages.objViewAllPageFirstContent, "First asset")) {
						if (verifyIsElementDisplayed(PWAPlayerPage.objContentShowTitle, "Show Details")) {
							String viewallnavigationtittle = getText(PWAPlayerPage.objContentShowTitle);
							logger.info(
									"Navigated to Show Details page from View All Page: " + viewallnavigationtittle);
							extent.extentLogger("View All",
									"Navigated to Show Details page from View All Page: " + viewallnavigationtittle);
						} else {
							directClickReturnBoolean(PWASubscriptionPages.objPopupCloseButton, "Popup Close Button");
							pausePlayer();
							String viewallnavigationtittle = getText(PWAPlayerPage.objContentTitle);
							logger.info("Navigated to Consumption page from View All Page: " + viewallnavigationtittle);
							extent.extentLogger("View All",
									"Navigated to Consumption page from View All Page: " + viewallnavigationtittle);
						}

					} else {
						logger.error("Failed to click on first asset in View All Page");
						extent.extentLoggerFail("View All", "Failed to click on first asset in View All Page");
					}
				}
			} else {
				logger.error("Not navigated to View All Page");
				extent.extentLoggerFail("View All", "Not navigated to View All Page");
			}
		}
	}

	public void PWAHomeLandingPageHLS(String userType) throws Exception {
		extent.HeaderChildNode("HLS_012 : Verify the carousels are Auto scrolled in landing pages");
		verifyAutoroatingOnCarousel("Home");
		extent.HeaderChildNode("HLS_013 : Verify Metadata and CTA's are properly displayed in carousels");
		String languageSmallText = allSelectedLanguages();
		verifyMetadataOnCarousel("Home", "home", languageSmallText);
		extent.HeaderChildNode("HLS_014 : Verify the rails are displayed properly in the home screen");
		pagesTrayValidation("Home");
		extent.HeaderChildNode("HLS_015 : Verify On click View All CTA then screen is navigated to a particular page.");
		playCardFromCollections(userType, "Home");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		extent.HeaderChildNode("HLS_016 : Verify the Continue Watching tray");
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(PWAHomePage.objContinueWatchingTray, "Continue Watching tray") == false) {
				extent.extentLogger("", "Continue watching tray is not displayed for guest user, expected behavior");
				logger.info("Continue watching tray is not displayed for guest user, expected behavior");
			} else {
				extent.extentLoggerFail("Verify Continue Watching tray",
						"Continue watching tray is displayed for guest user");
				logger.error("Continue watching tray is displayed for guest user");
			}
		} else {
			if (verifyIsElementDisplayed(PWAHomePage.objContinueWatchingTray, "Continue Watching tray") == true) {
				extent.extentLogger("", "Continue watching tray is displayed for logged in user");
				logger.info("Continue watching tray is displayed for logged in user");
			} else {
				extent.extentLoggerFail("", "Continue watching tray is not displayed for logged in user");
				logger.error("Continue watching tray is not displayed for logged in user");
			}
		}
		extent.HeaderChildNode("HLS_017 : Verify the Premium/Club tag");
		PWAPremiumOrClubIconVerification("Home");
		extent.HeaderChildNode("HLS_021 : Verify the availability of the Footer section at bottom of the home-page.");
		Swipe("UP", 5);
		if (verifyIsElementDisplayed(PWASearchPage.objSearchPageFooter, "Footer menu")) {
			logger.info("Verified presence of Footer menu in Home Page");
			extent.extentLogger("", "Verified presence of Footer menu in Home Page");
		} else {
			logger.error("Failed to verify presence of Footer menu in Home Page");
			extent.extentLoggerFail("", "Failed to verify presence of Footer menu in Home Page");
		}
		click(PWAHomePage.objBackToTopArrow, "Back to Top arrow");
	}

	public void PWAMoviesPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_022 : Verify user navigation to the Movies page");
		PWAPagesNavigationAndTabHighlight(Tabname);
		extent.HeaderChildNode("HLS_023 : Verify the Auto rotation of carousel");
		verifyAutoroatingOnCarousel(Tabname);
		extent.HeaderChildNode("HLS_024 : Verify the rails name and content are loaded for first 2 scroll");
		pagesTrayValidation(Tabname);
		extent.HeaderChildNode("HLS_025 : Check for View all from different rails navigates to accurate screen");
		playCardFromCollections(userType, Tabname);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		extent.HeaderChildNode(
				"HLS_026 : Verify the premium/Club tag is given on top left for all premium / Club content card");
		PWAPremiumOrClubIconVerification(Tabname);
		extent.HeaderChildNode("HLS_028 : Verify user can play any Free movie Content from the page");
		selectEnglishContentLanguage();
		navigateToAnyScreen("Movies");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(usertype);
		click(PWAMoviesPage.objFreeContentCardFromTray, "Free Content from Tray");
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		String freeMovieTitle = "";
		try {
			freeMovieTitle = getElementPropertyToString("innerText", PWAMusicPage.objConsumptionPageTitle,
					"Free Movie Title in Consumptions Page").toString();
			logger.info("Free Movie Title in Consumptions Page: " + freeMovieTitle);
			extent.extentLogger("", "Free Movie Title in Consumptions Page: " + freeMovieTitle);
		} catch (Exception e) {
			logger.error("Failed to fetch Free Movie Title in Consumptions Page");
			extent.extentLoggerFail("", "Failed to fetch Free Movie Title in Consumptions Page");
		}
		Back(1);
		if (userType.equals("Guest") || userType.equals("NonSubscribed")) {
			extent.HeaderChildNode(
					"HLS_029 : Verify whether user is able to play Premium Movie Contents as a Guest/Non-Subscribed user");
		}
		if (userType.equals("ClubUser")) {
			extent.HeaderChildNode(
					"HLS_031 : Verify weather user is able to play Premium Movie Contents as Club/RSVOD user");
		}
		if (userType.equals("SubscribedUser")) {
			extent.HeaderChildNode(
					"HLS_030 : Verify the User can play the Premium Movie Contents as a Premium all access user");
		}
		selectLanguages();
		navigateToAnyScreen("Movies");
		click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
		waitTime(8000);
		if (userType.equals("Guest") || userType.equals("NonSubscribed") || userType.equals("ClubUser")) {
			if (!verifyIsElementDisplayed(PWAPlayerPage.objWatchingATrailerMessage,
					"'You're watching a trailer' message on the player")) {
				verifyElementPresent(PWALiveTVPage.objPlayerInlineSubscriptionLink, "Inline Subscribe Link");
			}
		}
		pausePlayer();
		String premiumMovieTitle = "";
		try {
			premiumMovieTitle = getElementPropertyToString("innerText", PWAMusicPage.objConsumptionPageTitle,
					"Premium Movie Title in Consumptions Page").toString();
			logger.info("Premium Movie Title in Consumptions Page: " + premiumMovieTitle);
			extent.extentLogger("", "Premium Movie Title in Consumptions Page: " + premiumMovieTitle);
		} catch (Exception e) {
			logger.error("Failed to fetch Premium Movie Title in Consumptions Page");
			extent.extentLoggerFail("", "Failed to fetch Premium Movie Title in Consumptions Page");
		}
		Back(1);
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"HLS_032 : Validate the subscription popup availability at the end of the play back");
			String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("premiumMovieWithTrailer");
			zeeSearchForContentAndClickOnFirstResult(keyword);
			waitForElement(PWASubscriptionPages.objSubscribePopupTitle, 30, "Subscribe Pop up Title");
			if (verifyElementPresent(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop up Title")) {
				click(PWASubscriptionPages.objPopupCloseButton, "Popup Close Button");
			}
		}
		Back(1);
		extent.HeaderChildNode(
				"HLS_034 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		verificationOfBackToTop(Tabname);

	}

	public void PWAShowsPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_041 : Verify user navigation to Show landing page");
		System.out.println("HLS_041 : Verify user navigation to Show landing page");
		PWAPagesNavigationAndTabHighlight(Tabname);
		extent.HeaderChildNode("HLS_042 : Verify the Auto rotation of carousel");
		System.out.println("HLS_042 : Verify the Auto rotation of carousel");
		verifyAutoroatingOnCarousel(Tabname);
		extent.HeaderChildNode("HLS_043 : Verify the rails name and content are loaded for first 2 scroll");
		System.out.println("HLS_043 : Verify the rails name and content are loaded for first 2 scroll");
		pagesTrayValidation(Tabname);
		extent.HeaderChildNode("HLS_044 : Check for View all from different rails navigates to accurate screen");
		System.out.println("HLS_044 : Check for View all from different rails navigates to accurate screen");
		playCardFromCollections(userType, Tabname);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		extent.HeaderChildNode(
				"HLS_045 : Verify the Premium/ Club tag is given on top left for all premium / Club content card");
		System.out.println(
				"HLS_045 : Verify the Premium/ Club tag is given on top left for all premium / Club content card");
		PWAPremiumOrClubIconVerification(Tabname);
		extent.HeaderChildNode(
				"HLS_047 : Verify user can Navigate to the TV show Details Screen post click on any Show Thumbnail Card");
		System.out.println(
				"HLS_047 : Verify user can Navigate to the TV show Details Screen post click on any Show Thumbnail Card");
		swipeTillTrayAndClickShow("Trending Shows");
		extent.HeaderChildNode("HLS_048 : Verify UI/UX of  the TV show Details  Screen");
		System.out.println("HLS_048 : Verify UI/UX of  the TV show Details  Screen");
		verifyShowsDetailsMetaData();
		verifyElementPresent(PWALiveTVPage.objShareOption, "Share option");
		verifyElementPresent(PWAShowsPage.objWatchPromo, "Watch Promo option");
		verifyElementPresent(PWAShowsPage.objWatchLatestCTA, "Watch Latest Episode button");
		Back(1);
		extent.HeaderChildNode("HLS_049 : Verify user can play Free content from the show page");
		System.out.println("HLS_049 : Verify user can play Free content from the show page");
		selectLanguages();
		navigatetoAnyScreen("Shows");
		swipeTillTrayAndClickContentCard("Latest Hindi Episodes");
		extent.HeaderChildNode("HLS_050 : Verify user can play Premium content from the show page");
		System.out.println("HLS_050 : Verify user can play Premium content from the show page");
		reloadHome();
		navigatetoAnyScreen("Shows");
		swipeTillTrayAndClickContentCard("Premiere Episodes");
		reloadHome();
		navigatetoAnyScreen("Shows");
		extent.HeaderChildNode(
				"HLS_052 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		verificationOfBackToTop(Tabname);
		extent.HeaderChildNode("HLS_053 : Verify the Before TV are available");
		System.out.println("HLS_53 : Verify the Before TV are available");
		verifyTrayPresence("Premiere Episodes | Before TV");
		Swipe("DOWN", 2);
		extent.HeaderChildNode("HLS_054 : Verify the  Before TV content playback as a Guest/Non-Subscribed user");
		System.out.println("HLS_054 : Verify the  Before TV content playback as a Guest/Non-Subscribed user");
		swipeTillTrayAndClickContentCard("Premiere Episodes | Before Zee TV");
	}

	public void PWANewsPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_055 : Verify  user navigation to News landing page");
		System.out.println("HLS_055 : Verify  user navigation to News landing page");
		PWAPagesNavigationAndTabHighlight(Tabname);
		extent.HeaderChildNode("HLS_056 : Verify the Auto rotation of carousel");
		System.out.println("HLS_056 : Verify the Auto rotation of carousel");
		verifyAutoroatingOnCarousel(Tabname);
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		extent.HeaderChildNode("HLS_058 : Verify the content which have Autoplay tag in news Carousel should Autoplay");
		System.out.println("HLS_058 : Verify the content which have Autoplay tag in news Carousel should Autoplay");
		boolean autoPlayed = verifyAutoPlay(Tabname);
		extent.HeaderChildNode("HLS_059 : Verify the player control Autoplay carousel content");
		System.out.println("HLS_059 : Verify the player control Autoplay carousel content");
		if (autoPlayed == true) {
			verifyElementPresent(PWANewsPage.objLive, "LIVE badge on Autoplaying carousel");
			verifyElementPresent(PWANewsPage.objVolume, "Volume Control on Autoplaying carousel");
			if (verifyElementDisplayed(PWAPlayerPage.pauseBtn)) {
				logger.error("Pause button on Autoplaying carousel should not be displayed");
				extent.extentLoggerFail("Autoplay", "Pause button on Autoplaying carousel should not be displayed");
			} else {
				logger.info("Pause button on Autoplaying carousel is not be displayed, expected behavior");
				extent.extentLogger("Autoplay",
						"Pause button on Autoplaying carousel is not displayed, expected behavior");
			}
			if (verifyElementDisplayed(PWAPlayerPage.playBtn)) {
				logger.error("Play button on Autoplaying carousel should not be displayed");
				extent.extentLoggerFail("Autoplay", "Play button on Autoplaying carousel should not be displayed");
			} else {
				logger.info("Play button on Autoplaying carousel is not be displayed, expected behavior");
				extent.extentLogger("Autoplay",
						"Play button on Autoplaying carousel is not displayed, expected behavior");
			}
			if (verifyElementDisplayed(PWAPlayerPage.forward10SecBtn)) {
				logger.error("Forward button on Autoplaying carousel should not be displayed");
				extent.extentLoggerFail("Autoplay", "Forward button on Autoplaying carousel should not be displayed");
			} else {
				logger.info("Forward button on Autoplaying carousel is not be displayed, expected behavior");
				extent.extentLogger("Autoplay",
						"Forward button on Autoplaying carousel is not displayed, expected behavior");
			}
			if (verifyElementDisplayed(PWAPlayerPage.rewind10SecBtn)) {
				logger.error("Rewind button on Autoplaying carousel should not be displayed");
				extent.extentLoggerFail("Autoplay", "Rewind button on Autoplaying carousel should not be displayed");
			} else {
				logger.info("Rewind button on Autoplaying carousel is not be displayed, expected behavior");
				extent.extentLogger("Autoplay",
						"Rewind button on Autoplaying carousel is not displayed, expected behavior");
			}
			if (verifyIsElementDisplayed(PWAPlayerPage.minimizeBtn)) {
				logger.error("Autoplaying carousel should not be in full screen mode");
				extent.extentLoggerFail("Autoplay", "Autoplaying carousel should not be in full screen mode");
			} else {
				logger.info("Autoplaying carousel is not in full screen mode, expected behavior");
				extent.extentLogger("Autoplay", "Autoplaying carousel is not in full screen mode, expected behavior");
			}
		} else {
			logger.info("Carousel card did not autoplay");
			extent.extentLogger("Autoplay", "Carousel card did not autoplay");
			logger.info("Player controls on Autoplaying carousel content could not be verified");
			extent.extentLoggerWarning("Autoplay",
					"Player controls on Autoplaying carousel content could not be verified");
		}
		extent.HeaderChildNode("HLS_057 : Verify the trays displayed in the \"News\" page.");
		System.out.println("HLS_057 : Verify the trays displayed in the \"News\" page.");
		pagesTrayValidation(Tabname);
		extent.HeaderChildNode(
				"HLS_060 : Verify the \" View All\" option given on tray right side top and functionality of View all");
		System.out.println(
				"HLS_060 : Verify the \" View All\" option given on tray right side top and functionality of View all");
		playCardFromCollections(userType, Tabname);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		extent.HeaderChildNode(
				"HLS_063 : Verify the right side bottom arrow is given to navigate top of screen without scrolling");
		System.out.println(
				"HLS_063 : Verify the right side bottom arrow is given to navigate top of screen without scrolling");
		verificationOfBackToTop(Tabname);
		extent.HeaderChildNode(
				"HLS_064 : Verify whether user is navigate to Playback page when user tap on any Live News content");
		System.out.println(
				"HLS_064 : Verify whether user is navigate to Playback page when user tap on any Live News content");
		swipeTillTrayAndClickContentCard("Live News");
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		navigatetoAnyScreen("News");
		extent.HeaderChildNode(
				"HLS_065 : Verify whether user is navigate to Playback page when user tap on any News VOD content");
		System.out.println(
				"HLS_065 : Verify whether user is navigate to Playback page when user tap on any News VOD content");
		swipeTillTrayAndClickContentCard("Trending News");
	}

	public void PWAClubPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_066 : Verify whether user is able to navigate Club Landing Page. ");
		System.out.println("HLS_066 : Verify whether user is able to navigate Club Landing Page. ");
		PWAPagesNavigationAndTabHighlight(Tabname);
		extent.HeaderChildNode(
				"HLS_081 : Verify at right side bottom \"Arrow\" is given to navigate top of screen without scrolling");
		System.out.println(
				"HLS_081 : Verify at right side bottom \"Arrow\" is given to navigate top of screen without scrolling");
		verificationOfBackToTop(Tabname);
		extent.HeaderChildNode("HLS_067 : Verify the Auto rotation of the Carousels.");
		System.out.println("HLS_067 : Verify the Auto rotation of the Carousels.");
		verifyAutoroatingOnCarousel(Tabname);
		extent.HeaderChildNode("HLS_068 : Verify the rails name and content are loaded for first 2 scroll");
		System.out.println("HLS_068 : Verify the rails name and content are loaded for first 2 scroll");
		pagesTrayValidation(Tabname);
		extent.HeaderChildNode("HLS_069 : Verify View All option and functionality");
		System.out.println("HLS_069 : Verify View All option and functionality");
		playCardFromCollections(userType, Tabname);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		extent.HeaderChildNode("HLS_071 : Verify the Club tag is given on top left for all Club content card");
		System.out.println("HLS_071 : Verify the Club tag is given on top left for all Club content card");
		PWAPremiumOrClubIconVerification(Tabname);
		extent.HeaderChildNode("HLS_072 : Verify whether user can play any free club content");
		System.out.println("HLS_072 : Verify whether user can play any free club content");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(usertype);
		boolean cardClicked = false;
		for (int i = 0; i < 10; i++) {
			if (directClickReturnBoolean(PWAMoviesPage.objFreeContentCardFromTray, "Free Content from Tray")) {
				cardClicked = true;
				break;
			} else
				Swipe("UP", 2);
		}
		if (cardClicked == true) {
			// waitTime(10000);
			// directClickReturnBoolean(PWASubscriptionPages.objPopupCloseButton, "Subscribe
			// Pop Up Close button");
			// waitForPlayerAdToComplete("Video Player");
			pausePlayer();
			String freeMovieTitle = "";
			try {
				freeMovieTitle = getElementPropertyToString("innerText", PWAMusicPage.objConsumptionPageTitle,
						"Free Movie Title in Consumptions Page").toString();
				logger.info("Free Movie Title in Consumptions Page: " + freeMovieTitle);
				extent.extentLogger("", "Free Movie Title in Consumptions Page: " + freeMovieTitle);
			} catch (Exception e) {
				logger.error("Failed to fetch Free Movie Title in Consumptions Page");
				extent.extentLoggerFail("", "Failed to fetch Free Movie Title in Consumptions Page");
			}
			reloadHome();
			navigatetoAnyScreen(Tabname);
		} else {
			logger.info("No Free Content Card displayed");
			extent.extentLoggerWarning("", "No Free Content Card displayed");
			click(PWAZee5OriginalPage.objNavigateToTop, "Back to Top Arrow");
		}
		extent.HeaderChildNode("HLS_073 : Verify whether user can play any club movie content.");
		System.out.println("HLS_073 : Verify whether user can play any club movie content.");
		reloadHome();
		navigatetoAnyScreen(Tabname);
		swipeTillTrayAndClickContentCard("Club Movies");
		reloadHome();
		navigatetoAnyScreen(Tabname);
		extent.HeaderChildNode("HLS_074 : Verify whether user can play any before tv content.");
		System.out.println("HLS_074 : Verify whether user can play any before tv content.");
		swipeTillTrayAndClickContentCard("Premiere Episodes | Before Zee TV");
		reloadHome();
		boolean verifyClub = false;
		if (userType.equals("SubscribedUser")) {
			extent.HeaderChildNode("HLS_075 : Verify whether \"Premium pack\" user can play any club content.");
			System.out.println("HLS_075 : Verify whether \"Premium pack\" user can play any club content.");
			verifyClub = true;
		}
		if (userType.equals("ClubUser")) {
			extent.HeaderChildNode("HLS_076 : Verify whether \"Club pack\" user can play any club content.");
			System.out.println("HLS_076 : Verify whether \"Club pack\" user can play any club content.");
			verifyClub = true;
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			extent.HeaderChildNode("HLS_082 : Verify Subscribe CTA with Club Icon is displayed below the player");
			System.out.println("HLS_082 : Verify Subscribe CTA with Club Icon is displayed below the player");
			verifyClub = true;
		}
		if (verifyClub == true) {
			navigatetoAnyScreen(Tabname);
			waitTime(4000);// added time to wait for first tray to load
			click(PWAHomePage.objClubContentCardFromTray, "Club Content from Tray");
			waitTime(8000);
			String clubTitle = "";
			if (verifyIsElementDisplayed(PWAShowsPage.objShowsTitle, "Shows Details page"))
				clubTitle = getText(PWAShowsPage.objShowsTitle);
			else {
				pausePlayer();
				try {
					clubTitle = getElementPropertyToString("innerText", PWAMusicPage.objConsumptionPageTitle,
							"Club Title in Consumptions Page").toString();
					logger.info("Club Title in Consumptions Page: " + clubTitle);
					extent.extentLogger("", "Club Title in Consumptions Page: " + clubTitle);
				} catch (Exception e) {
					logger.error("Failed to fetch Club Title in Consumptions Page");
					extent.extentLoggerFail("", "Failed to fetch Club Title in Consumptions Page");
				}
				if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
					verifyElementPresent(PWAPlayerPage.objSubscribeCTAWithClubIcon, "Subscribe CTA with Club Icon");
				}
			}
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
				extent.HeaderChildNode(
						"HLS_079 : Validate the subscription popup availability at the end of the play back");
				System.out.println(
						"HLS_079 : Validate the subscription popup availability at the end of the play back	");
				click(PWAPlayerPage.objPlayerPlay, "Play button");
				if (waitForElementPresence(PWASubscriptionPages.objSubscribePopupTitle, 5, "Subscribe Pop Up")) {
					click(PWASubscriptionPages.objPopupCloseButton, "Close in Subscribe Pop Up");
				} else {
					logger.error("Failed to locate Subscribe Pop Up");
					extent.extentLoggerFail("", "Failed to locate Subscribe Pop Up");
				}
			}
		}
	}

	public void PWAPremiumPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_083 : Verify whether user is able to navigate Premium landing page");
		System.out.println("HLS_083 : Verify whether user is able to navigate Premium landing page");
		PWAPagesNavigationAndTabHighlight(Tabname);
		extent.HeaderChildNode("HLS_088 : Verify the Premium tag");
		System.out.println("HLS_088 : Verify the Premium tag");
		PWAPremiumOrClubIconVerification(Tabname);
		extent.HeaderChildNode(
				"HLS_096 : Verify at right side bottom \"Arrow\" is given to navigate top of screen without scrolling");
		System.out.println(
				"HLS_096 : Verify at right side bottom \"Arrow\" is given to navigate top of screen without scrolling");
		verificationOfBackToTop(Tabname);
		extent.HeaderChildNode("HLS_084 : Verify the Auto rotation of carousel");
		System.out.println("HLS_084 : Verify the Auto rotation of carousel");
		verifyAutoroatingOnCarousel(Tabname);
		extent.HeaderChildNode("HLS_085 : Verify the rails name and content are loaded for first 2 scroll");
		System.out.println("HLS_085 : Verify the rails name and content are loaded for first 2 scroll");
		pagesTrayValidation(Tabname);
		extent.HeaderChildNode("HLS_086 : Verify View All option and functionality");
		System.out.println("HLS_086 : Verify View All option and functionality");
		playCardFromCollections(userType, Tabname);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		boolean checkPremiumPlay = false;
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"HLS_089 : Verify whether guest/non-sub user able to play any  Premium content from the Premium Landing Page");
			System.out.println(
					"HLS_089 : Verify whether guest/non-sub user able to play any  Premium content from the Premium Landing Page");
			checkPremiumPlay = true;
		}
		if (userType.equals("SubscribedUser")) {
			extent.HeaderChildNode("HLS_090 : Verify whether \"Premium pack\" user can play any Premium content.");
			System.out.println("HLS_089 : Verify whether \"Premium pack\" user can play any Premium content.");
			checkPremiumPlay = true;
		}
		if (userType.equals("ClubUser")) {
			extent.HeaderChildNode("HLS_091 : Verify whether \"Club pack\" user can play any Premium content.");
			System.out.println("HLS_091 : Verify whether \"Club pack\" user can play any Premium content.");
			checkPremiumPlay = true;
		}
		if (checkPremiumPlay == true) {
			// handle mandatory pop up
			mandatoryRegistrationPopUp(userType);
			String keyword = "Ep 3 - Joker In the Pack";
			click(PWAHomePage.objSearchBtn, "Search icon");
			type(PWAHomePage.objSearchField, keyword + "\n", "Search");
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
			pausePlayer();
			if (userType.equals("Guest") || userType.equals("ClubUser")) {
				verifyIsElementDisplayed(PWAPlayerPage.objWatchingATrailerMessage,
						"'You're watching a trailer' message on the player");
			}
			String premiumMovieTitle = "";
			try {
				premiumMovieTitle = getElementPropertyToString("innerText", PWAMusicPage.objConsumptionPageTitle,
						"Premium Movie Title in Consumptions Page").toString();
				logger.info("Premium Movie Title in Consumptions Page: " + premiumMovieTitle);
				extent.extentLoggerPass("", "Premium Movie Title in Consumptions Page: " + premiumMovieTitle);
			} catch (Exception e) {
				logger.error("Failed to fetch Premium Movie Title in Consumptions Page");
				extent.extentLoggerFail("", "Failed to fetch Premium Movie Title in Consumptions Page");
			}
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
				extent.HeaderChildNode(
						"HLS_097 : Verify Subscribe CTA with Premium Icon is displayed below the player");
				System.out.println("HLS_097 : Verify Subscribe CTA with Premium Icon is displayed below the player");
				verifyElementPresent(PWAPlayerPage.objSubscribeCTAWithPremiumIcon, "Subscribe CTA with Premium Icon");
			}
			if (userType.equals("ClubUser")) {
				click(PWAPlayerPage.objPlayerPlay, "Play button");
				if (waitForElementPresence(PWASearchPage.objUpgradePopup, 5, "Pop Up")) {
					extent.extentLoggerPass("", "Club Upgrade Pop Up for Club User");
					click(PWASubscriptionPages.objPopupCloseButton, "Club Upgrade Pop Up Close button");
				} else {
					logger.error("Failed to locate Club Upgrade Pop Up");
					extent.extentLoggerFail("", "Failed to locate Club Upgrade Pop Up");
				}
			}
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
				extent.HeaderChildNode(
						"HLS_094 : Validate the subscription popup availability at the end of the play back");
				System.out
						.println("HLS_094 : Validate the subscription popup availability at the end of the play back");
				click(PWAPlayerPage.objPlayerPlay, "Play button");
				if (waitForElementPresence(PWASubscriptionPages.objSubscribePopupTitle, 5, "Pop Up")) {
					extent.extentLoggerPass("", "Subscription Pop Up for Guest/Non-Subscribed user is displayed");
					click(PWASubscriptionPages.objPopupCloseButton, "Close in Subscribe Pop Up");
				} else {
					logger.error("Failed to locate Subscribe Pop Up");
					extent.extentLoggerFail("", "Failed to locate Subscribe Pop Up");
				}
			}
			reloadHome();
		}
	}

	public void PWAPlayPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_098 : Verify whether user is able to navigate Play Landing Page ");
		System.out.println("HLS_098 : Verify whether user is able to navigate Play Landing Page ");
		PWAPagesNavigationAndTabHighlight(Tabname);
		extent.HeaderChildNode("HLS_099 : Verify Feature carousel banners should rotate at fixed interval of time");
		System.out.println("HLS_099 : Verify Feature carousel banners should rotate at fixed interval of time");
		verifyAutoroatingOnCarousel(Tabname);
		extent.HeaderChildNode("HLS_100 : Verify the rails name and content are loaded for first 2 scroll");
		System.out.println("HLS_100 : Verify the rails name and content are loaded for first 2 scroll");
		pagesTrayValidation(Tabname);
		extent.HeaderChildNode(
				"HLS_104 : Verify at right side bottom \"Arrow\" is given to navigate top of screen without scrolling");
		System.out.println(
				"HLS_104 : Verify at right side bottom \"Arrow\" is given to navigate top of screen without scrolling");
		verificationOfBackToTop(Tabname);
		extent.HeaderChildNode("HLS_101 : Verify View All option and functionality");
		System.out.println("HLS_101 : Verify View All option and functionality");
		playCardFromCollections(userType, Tabname);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		navigateToAnyScreen(Tabname);
		extent.HeaderChildNode("HLS_103 : Verify the Joystick icon is given on top left for all Play content card");
		System.out.println("HLS_103 : Verify the Joystick icon is given on top left for all Play content card");
		PWAPremiumOrClubIconVerification(Tabname);
		extent.HeaderChildNode(
				"HLS_105 : Verify user is navigate to respective games screen in new tab on click of feature carousel banner content.");
		System.out.println(
				"HLS_105 : Verify user is navigate to respective games screen in new tab on click of feature carousel banner content.");
		click(PWAPlayPage.objPlayNowButton, "Play Now button of Carousel");
		String currentHandle = getDriver().getWindowHandle();
		Set<String> handles = getDriver().getWindowHandles();
		Iterator it = handles.iterator();
		for (int i = 0; i < handles.size(); i++) {
			String handle = it.next().toString();
			if (!handle.equals(currentHandle)) {
				getDriver().switchTo().window(handle);
				break;
			}
		}
		String currentUrl = getDriver().getCurrentUrl();
		logger.info("Current URL: " + currentUrl);
		extent.extentLogger("", "Current URL: " + currentUrl);
		if (currentUrl.contains("games")) {
			logger.info("Navigated to Games screen");
			extent.extentLogger("", "Navigated to Games screen");
		} else {
			logger.info("Failed to navigate to Games screen");
			extent.extentLogger("", "Failed to navigate to Games screen");
		}
		Back(1);
		Back(1);
		getDriver().switchTo().window(currentHandle);
		extent.HeaderChildNode(
				"HLS_106 : Verify the joystick icon is displayed on top left corner of the content card");
		System.out.println("HLS_106 : Verify the joystick icon is displayed on top left corner of the content card");
		PWAPremiumOrClubIconVerification(Tabname);
		extent.HeaderChildNode("HLS_107 : Verify the Game content should be available in the Search result");
		System.out.println("HLS_107 : Verify the Game content should be available in the Search result");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		String keyword = "BoBoiBoy Galaxy Run";
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWAHomePage.objSearchField, keyword + "\n", "Search");
		verifyElementPresent(PWASearchPage.objSearchedResult(keyword), "Search Result");
		extent.HeaderChildNode(
				"HLS_108 : Verify whether user is able to navigate to Game content from the Search result");
		System.out.println("HLS_108 : Verify whether user is able to navigate to Game content from the Search result");
		click(PWASearchPage.objSearchedResult(keyword), "Search Result");
		waitTime(3000);
		currentUrl = "";
		currentHandle = getDriver().getWindowHandle();
		handles = getDriver().getWindowHandles();
		it = handles.iterator();
		for (int i = 0; i < handles.size(); i++) {
			String handle = it.next().toString();
			System.out.println("handle: " + handle);
			if (!handle.equals(currentHandle)) {
				getDriver().switchTo().window(handle);
				break;
			}
		}
		currentUrl = getDriver().getCurrentUrl();
		logger.info("Current URL: " + currentUrl);
		extent.extentLogger("", "Current URL: " + currentUrl);
		if (currentUrl.contains("cdn.ludigames")) {
			logger.info("Navigated to Games screen");
			extent.extentLogger("", "Navigated to Games screen");
		} else {
			logger.info("Failed to navigate to Games screen");
			extent.extentLogger("", "Failed to navigate to Games screen");
		}
		Back(1);
		Back(1);
		getDriver().switchTo().window(currentHandle);
	}

	public void PWAKidsPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_109 : Verify whether user is able to navigate Kids landing page");
		System.out.println("HLS_109 : Verify whether user is able to navigate Kids landing page");
		PWAPagesNavigationAndTabHighlight(Tabname);
		extent.HeaderChildNode("HLS_110 : Verify Feature carousel banners should rotate at fixed interval of time");
		System.out.println("HLS_110 : Verify Feature carousel banners should rotate at fixed interval of time");
		verifyAutoroatingOnCarousel(Tabname);
		extent.HeaderChildNode("HLS_111 : Verify the trays displayed in the \"Kids\" page");
		System.out.println("HLS_111 : Verify the trays displayed in the \"Kids\" page");
		pagesTrayValidation(Tabname);
		extent.HeaderChildNode("HLS_112 : Verify whether \"Play text\" CTA is displaying on Kids feature carousel");
		System.out.println("HLS_112 : Verify whether \"Play text\" CTA is displaying on Kids feature carousel");
		verifyElementPresent(PWAKidsPage.objPlayButton, "Play text on Carousel card");
		extent.HeaderChildNode("HLS_115 : Verify whether user can play any Kids content from the Kids Landing Page");
		System.out.println("HLS_115 : Verify whether user can play any Kids content from the Kids Landing Page");
		swipeTillTrayAndClickContentCard("Top 10 Movies For Your Kids");
		reloadHome();
		extent.HeaderChildNode(
				"HLS_122 : Verify at right side bottom \"Arrow\" is given to navigate top of screen without scrolling");
		System.out.println(
				"HLS_122 : Verify at right side bottom \"Arrow\" is given to navigate top of screen without scrolling");
		navigateToAnyScreen(Tabname);
		verificationOfBackToTop(Tabname);
		extent.HeaderChildNode(
				"HLS_113 : Verify the \" View All\" option given on tray right side top and functionality of View all");
		System.out.println(
				"HLS_113 : Verify the \" View All\" option given on tray right side top and functionality of View all");
		playCardFromCollections(userType, Tabname);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		extent.HeaderChildNode("HLS_116 : Verify the EDR contents available in the Kids Page");
		System.out.println("HLS_116 : Verify the EDR contents available in the Kids Page");
		navigateToAnyScreen(Tabname);
		verifyElementPresent(PWAHomePage.objEduauraaCardCarousel, "Eduauraa Card in Carousel");
		extent.HeaderChildNode(
				"HLS_117 : Verify user is navigated to Consumption page if user taps on any EDR content");
		System.out.println("HLS_117 : Verify user is navigated to Consumption page if user taps on any EDR content");
		click(PWAHomePage.objEduauraaCardCarousel, "Eduauraa Card in Carousel");
		String currenturl = getDriver().getCurrentUrl();
		logger.info("Current URL :" + currenturl);
		extent.extentLogger("Tab", "Current URL :" + currenturl);
		if (currenturl.contains("collections/eduauraa")) {
			logger.info("Navigated to Collections page");
			extent.extentLogger("url", "Navigated to Collections page");
		} else {
			logger.error("Failed to navigate to Collections page");
			extent.extentLoggerFail("url", "Failed to navigate to Collections page");
		}
		Back(1);
		verifyElementPresent(PWAHomePage.objLearnWithEduauraaTray, "Learn with Eduauraa tray");
		verifyElementPresentAndClick(PWAHomePage.objFirstItemLearnWithEduauraaTray,
				"First card under Learn with Eduauraa tray");
		verifyElementPresentAndClick(PWALandingPages.objViewAllPageFirstContent, "First card in View All page");
		String consumptionPageTitle = getText(PWAPlayerPage.objContentTitle);
		extent.extentLogger("", "Navigated to the Consumption page: " + consumptionPageTitle);
		logger.info("Navigated to the Consumption page: " + consumptionPageTitle);
		pausePlayer();
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"HLS_118 : Verify on tapping on Claim offer CTA user should navigates to Select pack page");
			System.out.println(
					"HLS_118 : Verify on tapping on Claim offer CTA user should navigates to Select pack page");
			verifyElementPresentAndClick(PWAHomePage.objClaimOffer, "Claim Offer CTA");
			verifyElementPresent(PWAHomePage.objSubscriptionPage, "Subscription page");
		} else if (userType.equals("SubscribedUser")) {
			extent.HeaderChildNode(
					"HLS_120 : Verify on Tapping on \"Go TO Eduauraa\" and \"Continue\" user should navigate to Eduauraa Sign Up page");
			System.out.println(
					"HLS_120 : Verify on Tapping on \"Go TO Eduauraa\" and \"Continue\" user should navigate to Eduauraa Sign Up page");
			verifyElementPresentAndClick(PWAKidsPage.objGoToEduauraa, "Go to Eduauraa button");
			click(PWAKidsPage.objExitZee5Continue, "Continue button");
			String eduauraaLink = getDriver().getCurrentUrl();
			logger.info("Navigated to " + eduauraaLink);
			extent.extentLogger("", "Navigated to " + eduauraaLink);
			// https://lms.eduauraa.com/#/zee5/signup
			if (eduauraaLink.contains("lms.eduauraa.com") && eduauraaLink.contains("signup")) {
				logger.info("Navigated to Eduauraa Sign Up Page");
				extent.extentLogger("", "Navigated to Eduauraa Sign Up Page");
			} else {
				logger.error("Failed to navigate to Eduauraa Sign Up Page");
				extent.extentLoggerFail("", "Failed to navigate to Eduauraa Sign Up Page");
			}
		}
	}

	public void PWAMusicPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_123 : Verify whether user is able to navigate Music landing page");
		System.out.println("HLS_123 : Verify whether user is able to navigate Music landing page");
		PWAPagesNavigationAndTabHighlight(Tabname);
		extent.HeaderChildNode("HLS_124 : Verify the rails name and content are loaded for first 2 scroll");
		System.out.println("HLS_124 : Verify the rails name and content are loaded for first 2 scroll");
		pagesTrayValidation(Tabname);
		extent.HeaderChildNode(
				"HLS_128 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		System.out.println(
				"HLS_128 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		verificationOfBackToTop(Tabname);
		extent.HeaderChildNode(
				"HLS_125 : Verify the \" View All\" option given on tray right side top and functionality of View all, HLS_129 : Verify whether user is navigate to consumption page when user tap on any music content in Listed collection");
		System.out.println(
				"HLS_125 : Verify the \" View All\" option given on tray right side top and functionality of View all, HLS_129 : Verify whether user is navigate to consumption page when user tap on any music content in Listed collection");
		playCardFromCollections(userType, Tabname);
		extent.HeaderChildNode("HLS_130 : Verify that Recommended Videos are displayed right side of the player");
		System.out.println("HLS_130 : Verify that Recommended Videos are displayed right side of the player");
		swipeTillTrayAndClickContentCard("Recommended Videos");
	}

	public void PWALiveTVPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_131 : Verify whether user is able to navigate Live TV landing page ");
		System.out.println("HLS_131 : Verify whether user is able to navigate Live TV landing page ");
		PWAPagesNavigationAndTabHighlight(Tabname);
		extent.HeaderChildNode(
				"HLS_132 : Validate the availability of Live and Channel Guide navigation buttons in header section");
		System.out.println(
				"HLS_132 : Validate the availability of Live and Channel Guide navigation buttons in header section");
		verifyIsElementDisplayed(PWALiveTVPage.objLiveTVToggleActive, "Live TV toggle button");
		verifyIsElementDisplayed(PWALiveTVPage.objNothighlightedChannelGuideToggle, "Channel Guide toggle button");
		waitforLiveTabToLoad();
		extent.HeaderChildNode("HLS_133 : Verify the rails name and content are loaded for first 2 scroll");
		System.out.println("HLS_133 : Verify the rails name and content are loaded for first 2 scroll");
		pagesTrayValidation(Tabname);
		extent.HeaderChildNode(
				"HLS_135 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		System.out.println(
				"HLS_135 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		verificationOfBackToTop(Tabname);
		extent.HeaderChildNode("HLS_144 : Verify the user is able to watch the free Live Channels");
		System.out.println("HLS_144 : Verify the user is able to watch the free Live Channels");
		click(PWALiveTVPage.objFirstfreeContentCard, "Free Content card");
		waitForElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, 5);
		if (!(verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up"))) {
			logger.info("User is able to watch the free Content");
			extent.extentLogger("Free content", "User is able to watch the free Content");
		}
		waitTime(10000);
		pauseLiveTVPlayer();
		verifyElementPresent(PWAPlayerPage.maximizeBtn, "Maximize icon");
		Back(1);
		waitforLiveTabToLoad();
		extent.HeaderChildNode(
				"HLS_136 : Verify in Filter option, multiple languages are given to select with apply and reset button in Live TV Screen");
		System.out.println(
				"HLS_136 : Verify in Filter option, multiple languages are given to select with apply and reset button in Live TV Screen");
		verifyElementPresentAndClick(PWALiveTVPage.objLiveTvFilterOption, "Filter option");
		List<WebElement> languages = getDriver().findElements(By.xpath("//div[contains(@class,'language noSelect')]"));
		languages.size();
		if (languages.size() > 1) {
			logger.info("Multiple languages are given for selection");
			extent.extentLogger("Multiple languages", "Multiple languages are given for selection");
		} else {
			logger.info("Multiple languages are not given for selection");
			extent.extentLogger("Multiple languages", "Multiple languages are not given for selection");
		}
		verifyElementPresent(PWALiveTVPage.objApplyBtn, "Apply button");
		verifyIsElementDisplayed(PWALiveTVPage.objResetBtn, "Reset button");
		click(PWALiveTVPage.objCloseLanguagePopuUpBtn, "Close button of Filter language setting window");
		waitTime(10000);
		extent.HeaderChildNode(
				"HLS_137 : Validate that user is navigated to channel guide screen post tapping/Clicking on channel guide button.");
		System.out.println(
				"HLS_137 : Validate that user is navigated to channel guide screen post tapping/Clicking on channel guide button.");
		verifyElementPresentAndClick(PWALiveTVPage.objNothighlightedChannelGuideToggle, "Channel guide toggle");
		waitTime(5000);
		if (verifyElementPresent(PWALiveTVPage.objHighlightedChannelGuideToggle, "Highlighted Channel guide toggle")) {
			logger.info(", User is navigated to Channel guide screen");
			extent.extentLogger("", "Channel guide toggle is highlighted, User is navigated to Channel guide screen");
		}
		if (waitforChannelGuideToLoad()) {
			extent.HeaderChildNode("HLS_139 : Validate that Channel/Day Strip is available channel guide screen.");
			System.out.println("HLS_139 : Validate that Channel/Day Strip is available channel guide screen.");
			verifyIsElementDisplayed(PWALiveTVPage.objChannelDayStrip, "Channel/Day Strip");
			waitExplicitlyForElementPresence(PWALiveTVPage.objFirstOngoingLiveTvShowCard, 60,
					"Ongoing Live TV Show Card");
			String ongoingLiveTvcardClass = getAttributValue("class", PWALiveTVPage.objFirstOngoingLiveTvShowCard);
			if (ongoingLiveTvcardClass.contains("active")) {
				logger.info("On going live show cards are highlighted");
				extent.extentLogger("", "On going live show cards are highlighted");
			} else {
				logger.info("On going live show cards are not highlighted");
				extent.extentLogger("", "On going live show cards are not highlighted on channel guide screen");
			}
			verifyIsElementDisplayed(PWALiveTVPage.objUpcomingLiveProgramDate, "Upcoming Live Program Date");
			extent.HeaderChildNode(
					"HLS_138 : Validate that Channel guide is having \"Sort\" and \"Filters\" option on the header ");
			System.out.println(
					"HLS_138 : Validate that Channel guide is having \"Sort\" and \"Filters\" option on the header ");
			click(PWALiveTVPage.objChannelGuideSortOption, "Sort Option");
			verifyElementPresent(PWALiveTVPage.objSortByPopularity, "Sort By Popularity Option");
			verifyIsElementDisplayed(PWALiveTVPage.objSortByAZ, "Sort by A-Z Option");
			click(PWALiveTVPage.objLiveTvFilterOption, "Filter option");
			languages = getDriver().findElements(By.xpath("//div[contains(@class,'language noSelect')]"));
			languages.size();
			if (languages.size() > 1) {
				logger.info("Multiple languages are given for selection");
				extent.extentLogger("Multiple languages", "Multiple languages are given for selection");
			} else {
				logger.info("Multiple languages are not given for selection");
				extent.extentLogger("Multiple languages", "Multiple languages are not given for selection");
			}
			verifyElementPresent(PWALiveTVPage.objApplyBtn, "Apply button");
			verifyIsElementDisplayed(PWALiveTVPage.objResetBtn, "Reset button");
			click(PWALiveTVPage.objCloseLanguagePopuUpBtn, "Close button of Filter language setting window");
			waitTime(10000);
			extent.HeaderChildNode(
					"HLS_140 : Validate that user is navigated to respective live TV consumption screen post tapping on On going live show cards");
			System.out.println(
					"HLS_140 : Validate that user is navigated to respective live TV consumption screen post tapping on On going live show cards");
			List<WebElement> ongoingshows = findElements(PWALiveTVPage.objOngoingLiveTvShowTitles);
			String onGoingLiveTvShowCardTitle = "";
			for (int i = 0; i < ongoingshows.size(); i++) {
				onGoingLiveTvShowCardTitle = ongoingshows.get(i).getAttribute("innerText");
				System.out.println(onGoingLiveTvShowCardTitle);
				if (JSClick(PWALiveTVPage.objOngoingLiveTvShowTitles(i + 1),
						"Ongoing Live TV Show card: " + onGoingLiveTvShowCardTitle)) {
					break;
				}
			}
			waitTime(7000);
			String ConsumptionScreenShowTitle = getText(PWASearchPage.objShowTitleInconsumptionPage);
			logger.info("Navigated to Consumption page:" + ConsumptionScreenShowTitle);
			extent.extentLogger("", "Navigated to consumption screen: " + ConsumptionScreenShowTitle);
			if (ConsumptionScreenShowTitle.equals("")) {
				logger.error("User is not navigated to consumption screen");
				extent.extentLoggerFail("", "User is not navigated to consumption screen");
			} else {
				logger.info("User is navigated to consumption screen");
				extent.extentLogger("", "User is navigated to consumption screen");
			}
			Back(1);
		}
		if (waitforChannelGuideToLoad()) {
			extent.HeaderChildNode(
					"HLS_141 : Validate that Show info pop up is displayed when user taps on any Upcoming or Concluded live program card.");
			System.out.println(
					"HLS_141 : Validate that Show info pop up is displayed when user taps on any Upcoming or Concluded live program card.");
			click(PWALiveTVPage.objUpcomingLiveProgramDate, "Upcoming Live Program Date");
			boolean clickedUpcomingCard = false;
			waitforChannelGuideToLoad();
			System.out.println(getDriver().getContextHandles());
			List<WebElement> channels = findElements(PWALiveTVPage.objChannelsEPGOuter);
			int size = 0;
			if (channels.size() > 4)
				size = 4;
			else
				size = channels.size();
			System.out.println("size: " + size);
			for (int i = 0; i < size; i++) {
				channels.get(i).click();
				if (verifyIsElementDisplayed(PWALiveTVPage.objUpcomingShowContentDialoguebox, "Show Info Pop Up")) {
					clickedUpcomingCard = true;
					click(PWALiveTVPage.objUpcomingLiveProgramClose, "Close of Pop Up");
					break;
				}
			}
			if (clickedUpcomingCard == false) {
				logger.error("Failed to click on Upcoming Show Card");
				extent.extentLoggerFail("", "Failed to click on Upcoming Show Card");
			}
		}
		click(PWALiveTVPage.objLiveTVToggleInactive, "Live TV Toggle button");
		if (waitforLiveTabToLoad()) {
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
				extent.HeaderChildNode(
						"HLS_142 : Verify the Subscribe now or Login pop is displayed when user click on premium content");
				System.out.println(
						"HLS_142 : Verify the Subscribe now or Login pop is displayed when user click on premium content");
			}
			if (userType.equals("SubscribedUser")) {
				extent.HeaderChildNode(
						"HLS_143 : Verify the Subscribe now or Login pop is not displayed when user click on premium content");
				System.out.println(
						"HLS_143 : Verify the Subscribe now or Login pop is not displayed when user click on premium content");
			}
			for (int scroll = 0; scroll <= 8; scroll++) {
				if (directClickReturnBoolean(PWALiveTVPage.objFirstPremiumCardinTray, "Premium Content")) {
					logger.info("Clicked on Premium Content Card");
					extent.extentLogger("", "Clicked on Premium Content Card");
					break;
				} else {
					Swipe("UP", 1);
					waitTime(2000);
				}
			}
			waitTime(5000);
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
				waitForElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, 5);
				if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe Pop Up")) {
					click(PWAPremiumPage.objClosePremiumPopup, "Subscribe Pop Up Close icon");
				} else {
					logger.error("Subscribe Pop Up failed to get displayed");
					extent.extentLoggerFail("", "Subscribe Pop Up failed to get displayed");
				}
			}
			if (userType.equals("SubscribedUser")) {
				if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle,
						"Subscribe Pop Up for Subscribed User")) {
					click(PWAPremiumPage.objClosePremiumPopup, "Subscribe Pop Up Close icon");
					logger.error("Subscribe Pop Up should not be displayued for Subscribed User");
					extent.extentLoggerFail("", "Subscribe Pop Up should not be displayued for Subscribed User");
				}
			}
		}
	}

	public void PWAStoriesPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_145 : Verify whether user is able to navigate Stories landing page ");
		System.out.println("HLS_145 : Verify whether user is able to navigate Stories landing page ");
		PWAPagesNavigationAndTabHighlight(Tabname);
		navigateToAnyScreen("Stories");
		extent.HeaderChildNode("HLS_146 : Trays and data load for 2 scrolls vertically");
		System.out.println("HLS_146 : Trays and data load for 2 scrolls vertically");
		dataValidationOnScrollForStories();
		extent.HeaderChildNode(
				"HLS_147 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		System.out.println(
				"HLS_147 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		verifyElementPresentAndClick(PWALandingPages.objNavigateToTopStories, "Back to Top arrow");
		extent.HeaderChildNode("HLS_148 : Verify whether user is navigate to Story consumption page");
		System.out.println("HLS_148 : Verify whether user is navigate to Story consumption page");
		String cardTitle = getElementPropertyToString("innerText", PWAHomePage.objStoriesPageCardTitle,
				"Story title in Landing");
		logger.info("Title of the Story in Stories page: " + cardTitle);
		extent.extentLogger("", "Title of the Story in Stories page: " + cardTitle);
		click(PWAHomePage.objStoriesPageCardTitle, "Story card");
		String cardTitleConsum = getElementPropertyToString("innerText", PWAHomePage.objStoriesPageConsumptionsTitle,
				"Story title in Consumptions");
		logger.info("Title of the Story in Article page: " + cardTitleConsum);
		extent.extentLogger("", "Title of the Story in Article page: " + cardTitleConsum);
		if (cardTitle.equals(cardTitleConsum)) {
			logger.info("Successful navigation to consumption page");
			extent.extentLogger("", "Successful navigation to consumption page");
		} else {
			logger.error("Navigation failed to consumption page");
			extent.extentLoggerFail("", "Navigation failed to consumption page");
		}

	}

	public void PWAVideosPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_149 : Verify whether user is able to navigate Videos landing page");
		System.out.println("HLS_149 : Verify whether user is able to navigate Videos landing page");
		PWAPagesNavigationAndTabHighlight(Tabname);
		extent.HeaderChildNode("HLS_150 : Verify the rails name and content are loaded for first 2 scroll");
		System.out.println("HLS_150 : Verify the rails name and content are loaded for first 2 scroll");
		pagesTrayValidation(Tabname);
		extent.HeaderChildNode(
				"HLS_154 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		System.out.println(
				"HLS_154 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		verificationOfBackToTop(Tabname);
		extent.HeaderChildNode(
				"HLS_151 : Verify the \" View All\" option given on tray right side top and functionality of View all, HLS_155 : Verify whether user is navigate to consumption page when user tap on any video content in Listed collection");
		System.out.println(
				"HLS_151 : Verify the \" View All\" option given on tray right side top and functionality of View all, HLS_155 : Verify whether user is navigate to consumption page when user tap on any video content in Listed collection");
		playCardFromCollections(userType, Tabname);
		extent.HeaderChildNode("HLS_156 : Verify that Recommended Videos are displayed right side of the player");
		System.out.println("HLS_156 : Verify that Recommended Videos are displayed right side of the player");
		swipeTillTrayAndClickContentCard("Recommended Videos");
	}

	public void PWAZEE5OriginalsPageHLS(String usertype, String Tabname) throws Exception {
		extent.HeaderChildNode("HLS_157 : Verify whether user is able to navigate Zee originals landing page");
		System.out.println("HLS_157 : Verify whether user is able to navigate Zee originals landing page");
		PWAPagesNavigationAndTabHighlight(Tabname);
		extent.HeaderChildNode("HLS_158 : Verify the rails name and content are loaded for first 2 scroll");
		System.out.println("HLS_158 : Verify the rails name and content are loaded for first 2 scroll");
		pagesTrayValidation(Tabname);
		extent.HeaderChildNode(
				"HLS_162 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		System.out.println(
				"HLS_162 : Verify at right side bottom arrow is given to navigate top of screen without scrolling");
		verificationOfBackToTop(Tabname);
		extent.HeaderChildNode(
				"HLS_159 : Verify the \" View All\" option given on tray right side top and functionality of View all");
		System.out.println(
				"HLS_159 : Verify the \" View All\" option given on tray right side top and functionality of View all");
		playCardFromCollections(userType, Tabname);
		extent.HeaderChildNode(
				"HLS_163 : Verify the Club & Premium icons are displayed on the Zee originals content cards");
		System.out
				.println("HLS_163 : Verify the Club & Premium icons are displayed on the Zee originals content cards");
		PWAPremiumOrClubIconVerification(Tabname);
		if (userType.equals("SubscribedUser")) {
			extent.HeaderChildNode(
					"HLS_164 : Verify the Premium user is able to watch all the zee originals shows, HLS_166 : Verify user is able to watch the First Episode of the originals shows, HLS_167 : Verify user can Navigate to the Original shows Details Screen post click on any Show Thumbnail Card");
			System.out.println(
					"HLS_164 : Verify the Premium user is able to watch all the zee originals shows, HLS_166 : Verify user is able to watch the First Episode of the originals shows, HLS_167 : Verify user can Navigate to the Original shows Details Screen post click on any Show Thumbnail Card");
			swipeTillTrayAndClickContentCard("Best of ZEE5 Originals in Hindi");
		}
		if (userType.equals("ClubUser")) {
			extent.HeaderChildNode(
					"HLS_165 : Verify the Club user is able to watch all the zee originals shows from Zee originals page, HLS_166 : Verify user is able to watch the First Episode of the originals shows, HLS_167 : Verify user can Navigate to the Original shows Details Screen post click on any Show Thumbnail Card");
			System.out.println(
					"HLS_165 : Verify the Club user is able to watch all the zee originals shows from Zee originals page, HLS_166 : Verify user is able to watch the First Episode of the originals shows, HLS_167 : Verify user can Navigate to the Original shows Details Screen post click on any Show Thumbnail Card");
			swipeTillTrayAndClickContentCard("Best of ZEE5 Originals in Hindi");
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"HLS_166 : Verify user is able to watch the First Episode of the originals shows, HLS_167 : Verify user can Navigate to the Original shows Details Screen post click on any Show Thumbnail Card");
			System.out.println(
					"HLS_166 : Verify user is able to watch the First Episode of the originals shows, HLS_167 : Verify user can Navigate to the Original shows Details Screen post click on any Show Thumbnail Card");
			swipeTillTrayAndClickContentCard("Best of ZEE5 Originals in Hindi");
		}
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		navigateToAnyScreen(Tabname);
		if (userType.equals("Guest")) {
			extent.HeaderChildNode(
					"HLS_168 : Verify the GET CLUB CTA is displayed on the  Club originals detail screen");
			System.out.println("HLS_168 : Verify the GET CLUB CTA is displayed on the  Club originals detail screen");
			// handle mandatory pop up
			mandatoryRegistrationPopUp(userType);
			String keyword = "Kehne Ko Humsafar Hain";
			click(PWAHomePage.objSearchBtn, "Search icon");
			type(PWAHomePage.objSearchField, keyword + "\n", "Search");
			click(PWASearchPage.objSearchedResult(keyword), "Search Result");
			verifyElementPresent(PWAShowsPage.objGetClubCTAInShowDetails, "Get Club CTA in originals details");
			verifyIsElementDisplayed(PWAShowsPage.objShowdeatilPlayIcon, "Play icon in originals details");
		}
		if (userType.equals("Guest")) {
			extent.HeaderChildNode(
					"HLS_169 : Verify the GET Premium CTA is displayed on the  Club originals detail screen");
			System.out
					.println("HLS_169 : Verify the GET Premium CTA is displayed on the  Club originals detail screen");
			// handle mandatory pop up
			mandatoryRegistrationPopUp(userType);
			String keyword = "Rangbaaz";
			click(PWAHomePage.objSearchBtn, "Search icon");
			type(PWAHomePage.objSearchField, keyword + "\n", "Search");
			click(PWASearchPage.objSearchedResult(keyword), "Search Result");
			verifyElementPresent(PWAShowsPage.objGetPremiumCTAInShowDetails, "Get Premium CTA in originals details");
			verifyIsElementDisplayed(PWAShowsPage.objShowdeatilPlayIcon, "Play icon in originals details");
		}
	}

	public void PWAWatchCreditsHLS(String usertype) throws Exception {
		extent.HeaderChildNode(
				"HLS_212 : Verify that watch credits button CTA is display at the end credit rolling screen, HLS_213 : Verify that watch credits button CTA is displayed along with UPNext  Rails on the player");
		System.out.println(
				"HLS_212 : Verify that watch credits button CTA is display at the end credit rolling screen, HLS_213 : Verify that watch credits button CTA is displayed along with UPNext  Rails on the player");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		String keyword = "Episode 1 - The Awakening";
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWAHomePage.objSearchField, keyword + "\n", "Search");
		click(PWASearchPage.objSearchedResult(keyword), "Search Result: " + keyword);
		String url = getDriver().getCurrentUrl();
		url = url + "?t=1940";
		getDriver().get(url);
		waitExplicitlyForElementPresence(PWAPlayerPage.objWatchCredit, 30, "Watch Credits CTA");
		if (verifyIsElementDisplayed(PWAPlayerPage.objUpnextCard, "Up Next Card")) {
			logger.info("Watch Credits and Up Next are displayed");
			extent.extentLoggerPass("", "Watch Credits and Up Next are displayed");
		}

		extent.HeaderChildNode(
				"HLS_214 : Validate that watch credits CTA close and content playback continues when user clicks on watch credit button.");
		System.out.println(
				"HLS_214 : Validate that watch credits CTA close and content playback continues when user clicks on watch credit button.");
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWAHomePage.objSearchField, keyword + "\n", "Search");
		click(PWASearchPage.objSearchedResult(keyword), "Search Result: " + keyword);
		url = getDriver().getCurrentUrl();
		url = url + "?t=1940";
		getDriver().get(url);
		waitExplicitlyForElementPresence(PWAPlayerPage.objWatchCredit, 30, "Watch Credits CTA");
		click(PWAPlayerPage.objWatchCredit, "Watch Credits CTA");
		String consumptionsPage = getText(PWAPlayerPage.objContentTitle);
		logger.info("Playing content: " + consumptionsPage);
		extent.extentLogger("", "Playing content: " + consumptionsPage);
		if (keyword.equals(consumptionsPage)) {
			logger.info("Content playback continued tapping on Watch Credits");
			extent.extentLoggerPass("", "Content playback continued tapping on Watch Credits");
		} else {
			logger.error("Content playback did not continue tapping on Watch Credits");
			extent.extentLoggerFail("", "Content playback did not continue tapping on Watch Credits");
		}
		reloadHome();
		extent.HeaderChildNode(
				"HLS_215 : Validate that next content playback should start on the player if user don't perform any action on watch credits button, "
						+ "HLS_207 : Validate the next content is auto-played on completion of first content in the player, "
						+ "HLS_211 : Validate that upnext content playback starts on the player post end of first content.");
		System.out.println(
				"HLS_215 : Validate that next content playback should start on the player if user don't perform any action on watch credits button, "
						+ "HLS_207 : Validate the next content is auto-played on completion of first content in the player, "
						+ "HLS_211 : Validate that upnext content playback starts on the player post end of first content.");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWAHomePage.objSearchField, keyword + "\n", "Search");
		click(PWASearchPage.objSearchedResult(keyword), "Search Result: " + keyword);
		url = getDriver().getCurrentUrl();
		url = url + "?t=1940";
		getDriver().get(url);
		waitExplicitlyForElementPresence(PWAPlayerPage.objWatchCredit, 30, "Watch Credits CTA");
		extent.extentLoggerPass("", "Watch Credits CTA is displayed");
		waitTime(5000);
		logger.info("Waited for 5 seconds");
		extent.extentLogger("", "Waited for 5 seconds");
		consumptionsPage = getText(PWAPlayerPage.objContentTitle);
		logger.info("Playing content: " + consumptionsPage);
		extent.extentLogger("", "Playing content: " + consumptionsPage);
		if (keyword.equals(consumptionsPage)) {
			logger.error("Next content playback did not start on the player");
			extent.extentLoggerFail("", "Next content playback did not start on the player");
		} else {
			logger.info("Next content playback started on the player, since user did not perform any action");
			extent.extentLoggerPass("",
					"Next content playback started on the player, since user did not perform any action");
		}
	}

	public void PWAUpNextHLS(String usertype) throws Exception {
		extent.HeaderChildNode("HLS_209 : Validate that user is able to select contents from upnext rail");
		System.out.println("HLS_209 : Validate that user is able to select contents from upnext rail");
		String keyword = "Episode 1 - The Awakening";
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWAHomePage.objSearchField, keyword + "\n", "Search");
		click(PWASearchPage.objSearchedResult(keyword), "Search Result: " + keyword);
		String url = getDriver().getCurrentUrl();
		url = url + "?t=1940";
		getDriver().get(url);
		if (waitExplicitlyForElementPresence(PWAPlayerPage.objUpnextCard, 30, "Up Next Card")) {
			extent.extentLoggerPass("", "Up Next Card located successfully");
		}
		click(PWAPlayerPage.objUpnextCard, "Up Next Card");
		String consumptionsPage = getText(PWAPlayerPage.objContentTitle);
		logger.info("Playing content: " + consumptionsPage);
		extent.extentLogger("", "Playing content: " + consumptionsPage);
		if (keyword.equals(consumptionsPage)) {
			logger.error("Upnext content failed to play");
			extent.extentLoggerFail("", "Upnext content failed to play");
		} else {
			logger.info("Upnext content played successfully");
			extent.extentLoggerPass("", "Upnext content played successfully");
		}
	}

	public void PWASearchLandingHLS(String usertype) throws Exception {
		extent.HeaderChildNode(
				"HLS_184 : Validate that user lands on search landing screen post tapping/clicking on search icon");
		System.out.println(
				"HLS_184 : Validate that user lands on search landing screen post tapping/clicking on search icon");
		verifyIsElementDisplayed(PWAHomePage.objSearchBtn, "Search icon");
		click(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 20);
		if (verifyIsElementDisplayed(PWASearchPage.objSearchEditBox, "Search Edit Box")) {
			logger.info("User landed on Search landing screen post tapping on search icon");
			extent.extentLogger("", "User landed on Search landing screen post tapping on search icon");
		} else {
			logger.error("User failed to land on Search landing screen post tapping on search icon");
			extent.extentLoggerFail("", "User failed to land on Search landing screen post tapping on search icon");
		}
		extent.HeaderChildNode(
				"HLS_185 : Validate that the Trending Searches tray is available on the Search landing screen");
		System.out.println(
				"HLS_185 : Validate that the Trending Searches tray is available on the Search landing screen");
		if (verifyIsElementDisplayed(PWASearchPage.objTrendingSearchesTray, "Trending Searches tray")) {
			logger.info("Trending Searches tray is available on the Search landing screen");
			extent.extentLogger("", "Trending Searches tray is available on the Search landing screen");
		} else {
			logger.info("Trending Searches tray is not available on the Search landing screen");
			extent.extentLogger("", "Trending Searches tray is not available on the Search landing screen");
		}
		extent.HeaderChildNode(
				"HLS_186 : Validate that the Top Searches tray is available on the Search landing screen.");
		System.out.println("HLS_186 : Validate that the Top Searches tray is available on the Search landing screen.");
		if (verifyIsElementDisplayed(PWASearchPage.objTopSearchesTray, "Top Searches tray")) {
			logger.info("Top Searches tray is available on the Search landing screen");
			extent.extentLogger("", "Top Searches tray is available on the Search landing screen");
		} else {
			logger.info("Top Searches tray is not available on the Search landing screen");
			extent.extentLogger("", "Top Searches tray is not available on the Search landing screen");
		}
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		extent.HeaderChildNode(
				"HLS_187 : Validate that user is navigated to respective details/consumption screen post tapping on any Search result content");
		System.out.println(
				"HLS_187 : Validate that user is navigated to respective details/consumption screen post tapping on any Search result content");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("consumptionsFreeContent");
		type(PWAHomePage.objSearchField, keyword + "\n", "Search");
		verifyElementPresentAndClick(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(10000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result: " + keyword);
		String navigationtittle = getText(PWAPlayerPage.objContentTitle);
		logger.info("Navigated to Consumption page: " + navigationtittle);
		extent.extentLogger("View All", "Navigated to Consumption page: " + navigationtittle);
		extent.HeaderChildNode(
				"HLS_188 : Validate that user is navigated to respective details/consumption screen post tapping on any cards from Trending/Top searches");
		System.out.println(
				"HLS_188 : Validate that user is navigated to respective details/consumption screen post tapping on any cards from Trending/Top searches");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		click(PWAHomePage.objSearchBtn, "Search icon");
		click(PWASearchPage.objFirstAssetTitleTrendingSearch, "First card under Trending Searches Tray");
		waitTime(10000);
		boolean externalLink = false;
		String currentHandle = getDriver().getWindowHandle();
		System.out.println(currentHandle);
		Set<String> handles = getDriver().getWindowHandles();
		System.out.println(handles);
		Iterator it = handles.iterator();
		String externalHandle = "";
		for (int i = 0; i < handles.size(); i++) {
			String handle = it.next().toString();
			if (!handle.equals(currentHandle)) {
				externalHandle = handle;
				break;
			}
		}
		if (handles.size() > 1) {
			logger.info("Navigated to External Link");
			extent.extentLogger("", "Navigated to External Link");
			getDriver().switchTo().window(externalHandle).close();
			externalLink = true;
			getDriver().switchTo().window(currentHandle);
			waitTime(10000);
		}
		navigationtittle = "";
		if (externalLink == false) {
			if (verifyIsElementDisplayed(PWAPlayerPage.objContentShowTitle, "Show Details")) {
				navigationtittle = getText(PWAPlayerPage.objContentShowTitle);
				logger.info("Navigated to Show Details page from View All Page: " + navigationtittle);
				extent.extentLogger("View All",
						"Navigated to Show Details page from View All Page: " + navigationtittle);
			} else {
				directClickReturnBoolean(PWASubscriptionPages.objPopupCloseButton, "Popup Close Button");
				pausePlayer();
				navigationtittle = getText(PWAPlayerPage.objContentTitle);
				logger.info("Navigated to Consumption page from View All Page: " + navigationtittle);
				extent.extentLogger("View All",
						"Navigated to Consumption page from View All Page: " + navigationtittle);
			}
		}
		reloadHome();
		click(PWAHomePage.objSearchBtn, "Search icon");
		extent.HeaderChildNode("HLS_189 : Validate that related search results are available under each tab.");
		System.out.println("HLS_189 : Validate that related search results are available under each tab.");
		keyword = "pyar";
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search bar");
		waitTime(4000);
		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);
		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
		List<WebElement> tabs = getDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		tabs.size();
		// for (int i = 1; i <= tabs.size(); i++) {
		for (int i = 1; i <= tabs.size(); i++) {
			try {
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
				String tabName = eleTab.getText();
				eleTab.click();
				logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
				extent.extentLogger("", tabName + " tab is displayed and clicked on " + tabName + " tab");
				int highlightedResults = getDriver().findElements(By.xpath(
						"(//div[@class='listingGrid']//div[@class='metaData']//h3[contains(@class,'cardTitle')]//span[@class='highLight'])"))
						.size();
				if (highlightedResults > 0) {
					logger.info(highlightedResults + " Related search results are displayed");
					extent.extentLogger("", highlightedResults + " Related search results are displayed");

				} else {
					logger.info("Related search results are not displayed");
					extent.extentLogger("Related search results", "Related search results are not displayed");
				}
			} catch (Exception e) {
				logger.error("Related search results are not displayed");
				extent.extentLoggerFail("Related search results", "Related search results are not displayed");
			}
		}
		extent.HeaderChildNode(
				"HLS_192 : Verify whether the user is able to clear the searched term on Click of the close(X) icon in the search field.");
		System.out.println(
				"HLS_192 : Verify whether the user is able to clear the searched term on Click of the close(X) icon in the search field.");
		click(PWASearchPage.objSearchCloseButton, "Close in Search Edit box");
		String placeHolderText = getElementPropertyToString("placeholder", PWASearchPage.objSearchPlaceHolderText,
				"Search placeholder text");
		String expectedPlaceHolderText = "Search for Movies, Shows, Channels etc.";
		if (placeHolderText.equals(expectedPlaceHolderText)) {
			logger.info("Placeholder text is displayed, Search field is cleared");
			extent.extentLogger("", "Placeholder text is displayed, Search field is cleared");
		} else {
			logger.error("Placeholder text is not displayed, Close in Search Edit box functionality failed");
			extent.extentLoggerFail("",
					"Placeholder text is not displayed, Close in Search Edit box functionality failed");
		}
		extent.HeaderChildNode("HLS_190 : Verify the Searched contents/Term is shown to the user as Recent searches");
		System.out.println("HLS_190 : Verify the Searched contents/Term is shown to the user as Recent searches");
		String keywordB = "Gattimela";
		type(PWASearchPage.objSearchEditBox, keywordB, "Search edit box");
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchShowsTab, "Shows tab");
		// handle mandatory pop up
		String user = getParameterFromXML("userType");
		mandatoryRegistrationPopUp(user);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keywordB), "Search Result " + keywordB);
		waitTime(2000);
		click(PWAHomePage.objSearchBtn, "Search icon");
		verifyElementPresent(PWASearchPage.recentSearchsLabel, "Recent Searches tray");
		verifyElementPresent(PWASearchPage.objClearAllTextofRecentSearches, "Clear All text");
		if (verifyIsElementDisplayed(PWASearchPage.recentSearchItem1, "First Recent Search Item")) {
			if (getText(PWASearchPage.recentSearchItem1).equals(keywordB)) {
				logger.info(keywordB + " is displayed under Recent Searches as expected");
				extent.extentLogger("", keywordB + " is displayed under Recent Searches as expected");
			} else {
				logger.error(keywordB + " is not the first item under Recent Searches");
				extent.extentLoggerFail("", keywordB + " is not the first item under Recent Searches");
			}
		} else {
			logger.error("There is no item under Recent Searches");
			extent.extentLoggerFail("", "There is no item under Recent Searches");
		}

		extent.HeaderChildNode(
				"HLS_191 : Verify after click on the \"Clear All\" button user is able to clear Recent Searches.");
		System.out.println(
				"HLS_191 : Verify after click on the \"Clear All\" button user is able to clear Recent Searches.");
		click(PWASearchPage.objClearAllTextofRecentSearches, "Clear All text");
		waitTime(2000);
		if (verifyIsElementDisplayed(PWASearchPage.recentSearchsLabel, "Recent Searches tray")) {
			logger.error("Recent Searches tray is displayed even after clicking Clear All text");
			extent.extentLoggerFail("", "Recent Searches tray is displayed even after clicking Clear All text");
		}
		if (verifyIsElementDisplayed(PWASearchPage.objClearAllTextofRecentSearches, "Clear All text")) {
			logger.error("Clear All text is displayed even after clicking Clear All text");
			extent.extentLoggerFail("", "Clear All text is displayed even after clicking Clear All text");
		}
		List<WebElement> recentSearchItems = findElements(PWASearchPage.recentSearchItems);
		int size = recentSearchItems.size();
		if (size > 0) {
			logger.error(size + "keywords are found under Recent Searches even after Clear All tap");
			extent.extentLoggerFail("", size + "keywords are found under Recent Searches even after Clear All tap");
		} else {
			logger.info("Previous Recent searched Keywords are not displayed");
			extent.extentLogger("", "Previous Recent searched Keywords are not displayed");
		}

	}

	public void PWASubscriptionJourneyHLS(String usertype) throws Exception {

		if (userType.contains("Guest") || userType.contains("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"HLS_216 : Verify the user is navigating to the Subscription page by clicking on the Subscribe button across the application");
			System.out.println(
					"HLS_216 : Verify the user is navigating to the Subscription page by clicking on the Subscribe button across the application");
			verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscribe button");
			if (!verifyIsElementDisplayed(PWASubscriptionPages.objZEE5Subscription, "Subscription Page")) {
				logger.error("Subscription page navigation failed");
				extent.extentLoggerFail("", "Subscription page navigation failed");
			} else
				click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
		if (userType.contains("Guest")) {
			extent.HeaderChildNode(
					"HLS_217 : Verify screen is navigated to the subscription page when user clicks on \"Buy subscription \"option");
			System.out.println(
					"HLS_217 : Verify screen is navigated to the subscription page when user clicks on \"Buy subscription \"option");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objBuySubscriptionOption, "Buy Subscription");
			waitTime(3000);
			if (!verifyIsElementDisplayed(PWASubscriptionPages.objZEE5Subscription, "Subscription Page")) {
				logger.error("Subscription page navigation failed");
				extent.extentLoggerFail("", "Subscription page navigation failed");
			} else
				click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode(
					"HLS_218 : Verify screen is navigated to the subscription page when user clicks on \"Have a Prepaid code\"");
			System.out.println(
					"HLS_218 : Verify screen is navigated to the subscription page when user clicks on \"Have a Prepaid code\"");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPlanInsideItemsBtn("Have a prepaid code ?"),
					"Have a prepaid code? option");
			if (!verifyIsElementDisplayed(PWASubscriptionPages.objZEE5Subscription, "Subscription Page")) {
				logger.error("Subscription page navigation failed");
				extent.extentLoggerFail("", "Subscription page navigation failed");
			} else
				click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
		if (userType.contains("NonSubscribedUser")) {
			// handle mandatory pop up
			mandatoryRegistrationPopUp(userType);
			extent.HeaderChildNode(
					"HLS_219 : Verify screen is navigated to the payments page by clicking on continue from Subscribe popup on playing premium/club content.");
			System.out.println(
					"HLS_219 : Verify screen is navigated to the payments page by clicking on continue from Subscribe popup on playing premium/club content.");
			String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("premiumMovieWithTrailer");
			waitTime(3000);
			click(PWAHomePage.objSearchBtn, "Search icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search bar");
			hideKeyboard();
			click(PWASearchPage.objFirstSearchedAssetTitle, "PremiumContent");
			waitForElement(PWAPlayerPage.objGetPremium, 30, "Subscribe button");
			click(PWAPlayerPage.objGetPremium, "Subscribe button");
			verifyElementPresent(PWASubscriptionPages.objDefaultSelectedPack, "Default Selected Package");
			click(PWASubscriptionPages.objPopupProceedBtn, "Proceed button");
			if (waitForElement(PWASubscriptionPages.objPaymentHighlighted, 10, "Payment Page")) {
				extent.extentLoggerPass("",
						"Screen is navigated to the payments page by clicking on continue from Subscribe popup");
			}
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode(
					"HLS_220 : Verify the user is navigating to Subscription page by clicking on the Player In-line Subscribe link");
			System.out.println(
					"HLS_220 : Verify the user is navigating to Subscription page by clicking on the Player In-line Subscribe link");
			keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("premiumMovieNoTrailer");
			click(PWAHomePage.objSearchBtn, "Search icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Field");
			hideKeyboard();
			waitTime(3000);
			// handle mandatory pop up
			mandatoryRegistrationPopUp(userType);
			click(PWASearchPage.objPremiumSearchResult(keyword), "Premium content");
			waitTime(10000);
			if (verifyIsElementDisplayed(PWASubscriptionPages.objSubscribePopupTitle, "Subscribe pop up")) {
				click(PWASubscriptionPages.objPopupCloseButton, "Close in Subscribe pop up");
			}
			verifyElementPresent(PWALiveTVPage.objPlayerInlineSubscriptionLink, "Player inline Subscription link");
			click(PWALiveTVPage.objPlayerInlineSubscriptionLink, "Player inline Subscription link");
			click(PWASubscriptionPages.objPopupProceedBtn, "Proceed button of Subscribe Pop Up");
			if (waitForElement(PWASubscriptionPages.objPaymentHighlighted, 10, "Payment Page")) {
				extent.extentLoggerPass("",
						"Screen is navigated to the payments page by clicking on Player inline Subscription link");
			}
		}
	}

	public void PWAAccountInfoHLS(String usertype) throws Exception {

		if (userType.contains("Guest")) {
			extent.HeaderChildNode("HLS_224 : Verify all access-packs");
			System.out.println("HLS_224 : Verify all access-packs");
			verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscribe button");
			Swipe("UP", 1);
			List<WebElement> packs = getDriver().findElements(PWASubscriptionPages.objPackAmount);
			for (int i = 0; i < packs.size(); i++) {
				logger.info("Pack displayed : " + packs.get(i).getAttribute("innerText"));
				extent.extentLogger("", "Pack displayed : " + packs.get(i).getAttribute("innerText"));
			}
			extent.HeaderChildNode("HLS_225 : Verify Club pack is available under club section in Select pack page");
			System.out.println("HLS_225 : Verify Club pack is available under club section in Select pack page");
			click(PWASubscriptionPages.objClubPack, "Club Tab");
			packs = getDriver().findElements(PWASubscriptionPages.objPackAmount);
			for (int i = 0; i < packs.size(); i++) {
				logger.info("Pack displayed : " + packs.get(i).getAttribute("innerText"));
				extent.extentLogger("", "Pack displayed : " + packs.get(i).getAttribute("innerText"));
			}
			extent.HeaderChildNode(
					"HLS_221 : Validate that selected pack information is displayed on the right side of the account info screen.");
			System.out.println(
					"HLS_221 : Validate that selected pack information is displayed on the right side of the account info screen.");
			click(PWASubscriptionPages.objPremiumPack, "Premium Pack");
			click(PWASubscriptionPages.objContinueBtn, "Continue button");
			verifyElementPresent(PWASubscriptionPages.objAccountInfoHighlighted, "Account Info Page");

			extent.HeaderChildNode(
					"HLS_222 : Validate that user is navigated to Payment options screen post successful sign in");
			System.out.println(
					"HLS_222 : Validate that user is navigated to Payment options screen post successful sign in");
			String email = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedUserName");
			String password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedPassword");
			click(PWASubscriptionPages.objEmailIDTextField, "Email ID Text Field");
			waitTime(3000);
			type(PWASubscriptionPages.objEmailIDTextField, email, "Email Id");
			hideKeyboard();
			waitTime(3000);
			click(PWASubscriptionPages.objProceedBtnHighlighted, "Proceed Button in Account Info Page");
			waitTime(3000);
			click(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
			waitTime(3000);
			type(PWASubscriptionPages.objPasswordFieldHidden, password, "Password Field");
			getDriver().context("NATIVE_APP");
			hideKeyboard();
			getDriver().context("CHROMIUM");
			waitTime(9000);
			click(PWASubscriptionPages.objProceedBtn, "Proceed Button");
			try {
				getDriver().findElement(PWASubscriptionPages.objProceedBtn).click();
			} catch (Exception e) {
			} // Required for Vivo phone because keyboard shows up
			waitTime(3000);
			if (waitForElement(PWASubscriptionPages.objPaymentHighlighted, 10, "Payment Page")) {
				extent.extentLoggerPass("",
						"Screen is navigated to the payments page after logging in from Accounts Info page");
			}
			extent.HeaderChildNode(
					"HLS_223 : Verify the JUSPAY iframe loads when the user navigate to the PAYMENT page");
			System.out.println("HLS_223 : Verify the JUSPAY iframe loads when the user navigate to the PAYMENT page");
			Swipe("UP", 1);
			waitTime(5000);
			getDriver().context("NATIVE_APP");
			verifyElementPresent(PWASubscriptionPages.objMobileCreditDebitCardOption, "'Credit / Debit Card' option");
			getDriver().context("CHROMIUM");
			extent.HeaderChildNode("HLS_226 : Validate that the user is able to select any one payment option.");
			System.out.println("HLS_226 : Validate that the user is able to select any one payment option.");
			getDriver().context("NATIVE_APP");
			verifyElementPresentAndClick(PWASubscriptionPages.objMobileCreditDebitCardOption,
					"'Credit / Debit Card' option");
			waitTime(5000);
			hideKeyboard();
			verifyIsElementDisplayed(PWASubscriptionPages.objMobileCardNumberText, "Card Number");
			verifyIsElementDisplayed(PWASubscriptionPages.objMobileExpiryText, "Expiry field");
			verifyIsElementDisplayed(PWASubscriptionPages.objMobileCVVText, "CVV field");
			verifyIsElementDisplayed(PWASubscriptionPages.objMobileProceedToPayButton, "Proceed to Pay button");
			click(PWASubscriptionPages.objCreditDebitClose, "Credit/Debit card Close");
			getDriver().context("CHROMIUM");
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			logout();
		}
	}

	public void PWAMySubscriptionsHLS(String usertype) throws Exception {
		if (userType.equals("NonSubscribedUser") || userType.equals("ClubUser") || userType.equals("SubscribedUser")) {
			extent.HeaderChildNode("HLS_229 : Validate that My Subscription button is displayed under My Account menu");
			System.out.println("HLS_229 : Validate that My Subscription button is displayed under My Account menu");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objDownArrow("My Account"), "My Account Expander button");
			verifyElementPresent(PWAHamburgerMenuPage.objExploreItemBtn("My Subscription"), "My Subscriptions");
		}
		if (userType.equals("SubscribedUser") || userType.equals("ClubUser")) {
			extent.HeaderChildNode(
					"HLS_230 : Validate that all the active subscription plans are displayed in my subscription screen.");
			System.out.println(
					"HLS_230 : Validate that all the active subscription plans are displayed in my subscription screen.");
			click(PWAHamburgerMenuPage.objExploreItemBtn("My Subscription"), "My Subscriptions");
			if (verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionPackName, "My Subscription Name")) {
				logger.info("My Subscription Name displayed : " + PWAHamburgerMenuPage.objMySubscriptionPackName);
				extent.extentLogger("",
						"My Subscription Name displayed : " + PWAHamburgerMenuPage.objMySubscriptionPackName);
			}
			if (verifyElementPresent(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus, "My Subscription Status")) {
				logger.info("My Subscription Status displayed : " + PWAHamburgerMenuPage.objMYSubscriptionActiveStatus);
				extent.extentLogger("",
						"My Subscription Status displayed : " + PWAHamburgerMenuPage.objMYSubscriptionActiveStatus);
			}
		}
		if (userType.equals("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"HLS_231 : Validate that Empty state screen with subscription teaser banner is displayed when user has No Active Subscriptions");
			System.out.println(
					"HLS_231 : Validate that Empty state screen with subscription teaser banner is displayed when user has No Active Subscriptions");
			click(PWAHamburgerMenuPage.objExploreItemBtn("My Subscription"), "My Subscriptions");
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objEmptyStateScreen, "Empty State Screen");
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objBrowseAllPacks, "Browse All Packs");
			verifyElementPresent(PWAHamburgerMenuPage.objSubscriptionTeaserBanner, "Subscription Teaser Banner");
		}
	}

	public void PWAMyTransactionsHLS(String usertype) throws Exception {
		if (userType.equals("NonSubscribedUser") || userType.equals("ClubUser") || userType.equals("SubscribedUser")) {
			extent.HeaderChildNode("HLS_232 : Validate that My Transaction button is displayed under My Account menu");
			System.out.println("HLS_232 : Validate that My Transaction button is displayed under My Account menu.");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objDownArrow("My Account"), "My Account Expander button");
			verifyElementPresent(PWAHamburgerMenuPage.objExploreItemBtn("My Transactions"), "My Transactions");
		}
		if (userType.equals("SubscribedUser") || userType.equals("ClubUser")) {
			extent.HeaderChildNode(
					"HLS_233 : Validate that All the active and expired plans are listing in my transaction screen.");
			System.out.println(
					"HLS_233 : Validate that All the active and expired plans are listing in my transaction screen.");
			click(PWAHamburgerMenuPage.objExploreItemBtn("My Transactions"), "My Transactions");
			waitTime(4000);
			verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Transactions"),
					"My Transactions page");
			verifyIsElementDisplayed(PWASubscriptionPages.objActiveStatusTransaction, "Status Active");
		}
		if (userType.equals("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"HLS_234 : Validate that Empty state screen with subscription teaser banner is displayed");
			System.out
					.println("HLS_234 : Validate that Empty state screen with subscription teaser banner is displayed");
			click(PWAHamburgerMenuPage.objExploreItemBtn("My Transactions"), "My Transactions");
			verifyElementPresent(PWAHamburgerMenuPage.objEmptyStateScreen, "Empty State Screen");
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objNoTransaction, "No Transactions");
			verifyIsElementDisplayed(PWAHamburgerMenuPage.objSubscriptionTeaserBanner, "Subscription Teaser Banner");
		}
	}

	public void PWAUpgradePackJourneyHLS(String userType) throws Exception {
		if (userType.equals("ClubUser")) {
			extent.HeaderChildNode(
					"HLS_235 : Verify User is navigated to Subscription page post tap on \"Upgrade\" CTA");
			System.out.println("HLS_235 : Verify User is navigated to Subscription page post tap on \"Upgrade\" CTA");
			verifyElementPresent(PWAHomePage.objUpgradeBtnInHeader, "Upgrade button");
			click(PWAHomePage.objUpgradeBtnInHeader, "Upgrade button");
			if (!verifyIsElementDisplayed(PWASubscriptionPages.objZEE5Subscription, "Subscription Page")) {
				logger.error("Subscription page navigation failed");
				extent.extentLoggerFail("", "Subscription page navigation failed");
			} else
				click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			extent.HeaderChildNode(
					"HLS_236 : Verify upgrade pop up is displayed for Rsvod/Club user on playing Entitled premium content");
			System.out.println(
					"HLS_236 : Verify upgrade pop up is displayed for Rsvod/Club user on playing Entitled premium content");
			String keyword = "Khaali Peeli";
			waitTime(3000);
			click(PWAHomePage.objSearchBtn, "Search icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search bar");
			hideKeyboard();
			click(PWASearchPage.objFirstSearchedAssetTitle, "Premium Content");
			waitForElement(PWAPlayerPage.objGetPremium, 30, "Subscribe button");
			click(PWAPlayerPage.objGetPremium, "Subscribe button");
			verifyElementPresent(PWASearchPage.objUpgradePopup, "Upgrade Pop up");
			extent.HeaderChildNode(
					"HLS_237 : Verify On clicking on proceed button payment screen with the Plan Price, Discount, rounding off, and Payable Price displayed.");
			System.out.println(
					"HLS_237 : Verify On clicking on proceed button payment screen with the Plan Price, Discount, rounding off, and Payable Price displayed.");
			click(PWASubscriptionPages.objPopupProceedBtn, "Proceed button");
			if (waitForElement(PWASubscriptionPages.objPaymentHighlighted, 10, "Payment Page")) {
				extent.extentLoggerPass("",
						"Screen is navigated to the payments page by clicking on continue from Subscribe popup");
			}
			click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
	}

	public void PWAPlayerControlsHLS(String usertype) throws Exception {
		extent.HeaderChildNode("HLS_194 : Validate the Player Controls on Live Content playback");
		System.out.println("HLS_194 : Validate the Player Controls on Live Content playback");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		navigateToAnyScreen("News");
		waitTime(7000);
		waitForElement(PWANewsPage.objLiveNewsCard, 60, "Live News Card");
		String newsCardTitle = getElementPropertyToString("data-minutelytitle", PWANewsPage.objLiveNewsCard,
				"Live News Card Title");
		logger.info("Live News card title fetched: " + newsCardTitle);
		extent.extentLogger("", "Live News card title fetched: " + newsCardTitle);
		click(PWANewsPage.objLiveNewsCard, "News Card Title");
		waitTime(10000);
		if (verifyElementPresent(PWAPlayerPage.objPlayerControlScreen, "Player control containing screen")) {
			waitforNewsConsumptionsToLoad();
			String newsConsumptionsTitle = getText(PWANewsPage.objLiveNewsConsumptionsTitle);
			logger.info("News title from consumptions page: " + newsConsumptionsTitle);
			extent.extentLogger("", "News title from consumptions page: " + newsConsumptionsTitle);
			if (newsCardTitle.equals(newsConsumptionsTitle)) {
				logger.info("Navigated to correct Consumptions Page");
				extent.extentLogger("Consumption Page", "Navigated to correct Consumption Page");
			} else {
				logger.error("Navigated to incorrect Consumptions Page");
				extent.extentLoggerFail("Consumption Page", "Navigated to incorrect Consumptions Page");
			}
		} else {
			logger.error("Not navigated to Consumptions Page");
			extent.extentLoggerFail("Consumption Page", "Not navigated to Consumptions Page");
		}
		waitForPlayerAdToComplete("Video Player");
		pauseLiveTVPlayer();
		if (verifyIsElementDisplayed(PWAPlayerPage.rewind10SecBtn, "Rewind 10 Seconds icon")) {
			logger.error("Rewind 10 Seconds icon should not be available for News");
			extent.extentLoggerFail("", "Rewind 10 Seconds icon should not be available for News");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.objPlayOrPause, "Play/Pause icon")) {
			logger.error("Play/Pause icon is not available for News");
			extent.extentLoggerFail("", "Play/Pause icon is not available for News");
		}
		if (verifyIsElementDisplayed(PWAPlayerPage.forward10SecBtn, "Forward 10 Seconds icon")) {
			logger.error("Forward 10 Seconds icon should not be available for News");
			extent.extentLoggerFail("", "Forward 10 Seconds icon should not be available for News");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.settingsBtn, "Settings icon")) {
			logger.error("Settings icon is not available for News");
			extent.extentLoggerFail("", "Settings icon is not available for News");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.objLivePlayerVolume, "Volume icon")) {
			logger.error("Volume icon is not available for News");
			extent.extentLoggerFail("", "Volume icon is not available for News");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.objLiveTag, "LIVE Tag")) {
			logger.error("LIVE Tag is not available for News");
			extent.extentLoggerFail("", "LIVE Tag is not available for News");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.objChromecastButton, "Chromecast button")) {
			logger.error("Chromecast button is not available for News");
			extent.extentLoggerFail("", "Chromecast button is not available for News");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.maximizeBtn, "Maximize button")) {
			logger.error("Maximize button is not available for News");
			extent.extentLoggerFail("", "Maximize button is not available for News");
		}
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");

		extent.HeaderChildNode("HLS_197 : Validate the availability and functionality of the Play/Pause button");
		System.out.println("HLS_197 : Validate the availability and functionality of the Play/Pause button");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		String keyword = "Mahira arrives for the";
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWAHomePage.objSearchField, keyword + "\n", "Search");
		waitTime(5000);
		click(PWASearchPage.objSearchedResult(keyword), "Search Result");
		waitTime(5000);
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		String afterPausing = getText(PWAPlayerPage.objcurrenttime);
		waitTime(5000);
		logger.info("Waited for 5 seconds");
		extent.extentLogger("", "Waited for 5 seconds");
		waitTime(2000);
		String afterPausingWait = getText(PWAPlayerPage.objcurrenttime);
		logger.info("Player time fetched after waiting for 5 seconds: " + afterPausingWait);
		extent.extentLogger("", "Player time fetched after waiting for 5 seconds: " + afterPausingWait);
		if (afterPausing.equals(afterPausingWait)) {
			logger.info("Player Paused Successfully");
			extent.extentLogger("", "Player Paused Successfully");
		} else {
			logger.error("Player Paused Failed");
			extent.extentLoggerFail("", "Player Paused Failed");
		}
		int deviceWidth = getDriver().manage().window().getSize().width;
		int deviceHeight = getDriver().manage().window().getSize().height;
		int x = deviceWidth / 2;
		int y = deviceHeight / 4;
		boolean playerPaused = false;
		TouchAction act = new TouchAction(getDriver());
		act.tap(PointOption.point(x, y)).perform();
		click(PWAPlayerPage.playBtn, "Play button");
		waitTime(5000);
		logger.info("Waited for 5 seconds");
		extent.extentLogger("", "Waited for 5 seconds");
		act.tap(PointOption.point(x, y)).perform();
		String afterPlayingWait = getText(PWAPlayerPage.objcurrenttime);
		logger.info("Player time fetched after playing: " + afterPlayingWait);
		extent.extentLogger("", "Player time fetched after playing: " + afterPlayingWait);
		if (afterPlayingWait.equals(afterPausingWait)) {
			logger.error("Player Play Failed");
			extent.extentLoggerFail("", "Player Play Failed");
		} else {
			logger.info("Player Play Successfull");
			extent.extentLogger("", "Player Play Successfull");
		}
		act.tap(PointOption.point(x, y)).perform();
		extent.HeaderChildNode(
				"HLS_198 : Content duration and Content playback elapsed time should be displayed on the player.");
		System.out.println(
				"HLS_198 : Content duration and Content playback elapsed time should be displayed on the player.");
		pausePlayer();
		String elapsedDuration = getText(PWAPlayerPage.objcurrenttime);
		String contentDuration = getText(PWAPlayerPage.objPlayerTotalDuration);
		if (!elapsedDuration.equals("")) {
			logger.info("Elapsed Time is displayed: " + elapsedDuration);
			extent.extentLogger("", "Elapsed Time is displayed: " + elapsedDuration);
		} else {
			logger.error("Elapsed Time is not displayed");
			extent.extentLoggerFail("", "Elapsed Time is not displayed");
		}
		if (!contentDuration.equals("")) {
			logger.info("Total Duration is displayed: " + contentDuration);
			extent.extentLogger("", "Total Duration is displayed: " + contentDuration);
		} else {
			logger.error("Total Duration is not displayed");
			extent.extentLoggerFail("", "Total Duration is not displayed");
		}
		extent.HeaderChildNode("HLS_193 : Validate the Player Controls on VOD Content playback");
		System.out.println("HLS_193 : Validate the Player Controls on VOD Content playback");

		if (!verifyIsElementDisplayed(PWAPlayerPage.rewind10SecBtn, "Rewind 10 Seconds icon")) {
			logger.error("Rewind 10 Seconds is not available for VOD");
			extent.extentLoggerFail("", "Rewind 10 Seconds is not available for VOD");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.objPlayOrPause, "Play/Pause icon")) {
			logger.error("Play/Pause icon is not available for VOD");
			extent.extentLoggerFail("", "Play/Pause icon is not available for VOD");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.forward10SecBtn, "Forward 10 Seconds icon")) {
			logger.error("Forward 10 Seconds is not available for VOD");
			extent.extentLoggerFail("", "Forward 10 Seconds is not available for VOD");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.objLivePlayerVolume, "Volume icon")) {
			logger.error("Volume icon is not available for VOD");
			extent.extentLoggerFail("", "Volume icon is not available for VOD");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.settingsBtn, "Settings icon")) {
			logger.error("Settings icon is not available for VOD");
			extent.extentLoggerFail("", "Settings icon is not available for VOD");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.objChromecastButton, "Chromecast icon ")) {
			logger.error("Chromecast icon is not available for VOD");
			extent.extentLoggerFail("", "Chromecast icon is not available for VOD");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle icon")) {
			logger.error("Subtitle icon is not available for VOD");
			extent.extentLoggerFail("", "Subtitle icon is not available for VOD");
		}
		if (!verifyIsElementDisplayed(PWAPlayerPage.maximizeBtn, "Maximize button")) {
			logger.error("Maximize button is not available for VOD");
			extent.extentLoggerFail("", "Maximize button is not available for VOD");
		}
		extent.HeaderChildNode(
				"HLS_205 : Validate the availability and functionality of maximizing and minimize icons.");
		System.out.println("HLS_205 : Validate the availability and functionality of maximizing and minimize icons.");
		click(PWAPlayerPage.maximizeBtn, "Maximize button");
		verifyElementPresentAndClick(PWAPlayerPage.minimizeBtn, "Minimize button");
		extent.HeaderChildNode(
				"HLS_204 : Verify Caption/Subtitle icon is displaying next to the Settings button in the Player section");
		System.out.println(
				"HLS_204 : Verify Caption/Subtitle icon is displaying next to the Settings button in the Player section");
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		String keywordcap = "Mahira arrives for the";
		click(PWAHomePage.objSearchBtn, "Search icon");
		type(PWAHomePage.objSearchField, keywordcap + "\n", "Search");
		waitTime(10000);
		click(PWASearchPage.objSearchedResult(keywordcap), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		if (!verifyIsElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle icon")) {
			logger.error("Subtitle icon is not available for VOD");
			extent.extentLoggerFail("", "Subtitle icon is not available for VOD");
		} else {
			click(PWAPlayerPage.playBtn, "Play button");
			if (!verifyIsElementDisplayed(PWAPlayerPage.objSubtitleTextOnPlayer, "Subtitle Text")) {
				logger.info("Subtitle Text is not displayed when the subtitle setting is OFF, expected behavior");
				extent.extentLogger("",
						"Subtitle Text is not displayed when the subtitle setting is OFF, expected behavior");
			} else {
				logger.error("Subtitle Text is displayed even though subtitle setting is OFF");
				extent.extentLoggerFail("", "Subtitle Text is displayed even though subtitle setting is OFF");
			}
			pausePlayer();
			click(PWAPlayerPage.objSubtitleIcon, "Subtitle icon");
			verifyElementPresent(PWAPlayerPage.objPlayerUnSelectedAudioTrack("English"), "Subtitle English");
			click(PWAPlayerPage.objPlayerUnSelectedAudioTrack("English"), "Subtitle English");
			click(PWAPlayerPage.playBtn, "Play button");
			waitExplicitlyForElementPresence(PWAPlayerPage.objSubtitleTextOnPlayer, 60, "Subtitle Text on Player");
			click(PWAPlayerPage.objSubtitleIcon, "Subtitle icon");
			verifyElementPresent(PWAPlayerPage.objPlayerUnSelectedAudioTrack("Off"), "Off Subtitle");
			if (verifyIsElementDisplayed(PWAPlayerPage.objPlayerUnSelectedAudioTrack("English"), "Subtitle English")) {
				logger.error("English Subtitle is displayed even though setting is switched is OFF");
				extent.extentLoggerFail("", "English Subtitle is displayed even though setting is switched is OFF");
			} else {
				logger.info("English Subtitle is not displayed because setting is switched is OFF");
				extent.extentLogger("", "English Subtitle is displayed because setting is switched is OFF");
			}
		}
		extent.HeaderChildNode("HLS_195 : Content should jump 10 secs forward post tapping on 10 secs forward button.");
		System.out.println("HLS_195 : Content should jump 10 secs forward post tapping on 10 secs forward button.");
		Thread.sleep(3000);
		int timeBeforeFmin = 0, timeBeforeFsec = 0, timeBeforeF = 0;
		int timeAfterFmin = 0, timeAfterFsec = 0, timeAfterF = 0;
		int timeBeforeRmin = 0, timeBeforeRsec = 0, timeBeforeR = 0;
		int timeAfterRmin = 0, timeAfterRsec = 0, timeAfterR = 0;
		// Verify forward
		act.tap(PointOption.point(x, y)).perform();
		String currentDuration1 = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"current duration");
		logger.info("Time fetched before forward: " + currentDuration1);
		extent.extentLogger("timeBeforeForward", "Time fetched before forward: " + currentDuration1);
		String[] timeBeforeForward = currentDuration1.split(":");
		try {
			timeBeforeFmin = Integer.parseInt(timeBeforeForward[0]) * 60;
			timeBeforeFsec = Integer.parseInt(timeBeforeForward[1]);
			timeBeforeF = timeBeforeFmin + timeBeforeFsec;
			logger.info("Time fetched before forward in Secs: " + timeBeforeF);
			extent.extentLogger("timeBeforeForward", "Time fetched before forward in Secs: " + timeBeforeF);
		} catch (Exception e) {
			logger.error("Error in time coversion");
			extent.extentLogger("error", "Error in time coversion");
		}
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 seconds");
		Thread.sleep(2000);
		// Get the current time duration after clicking the forward button
		playerTap();
		String currentDuration2 = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"current duration");
		logger.info("Time fetched after forward: " + currentDuration2);
		extent.extentLogger("timeAfterForward", "Time fetched after forward: " + currentDuration2);
		String[] timeAfterForward = currentDuration2.split(":");
		try {
			timeAfterFmin = Integer.parseInt(timeAfterForward[0]) * 60;
			timeAfterFsec = Integer.parseInt(timeAfterForward[1]);
			timeAfterF = timeAfterFmin + timeAfterFsec;
			logger.info("Time fetched after forward in Secs: " + timeAfterF);
			extent.extentLogger("timeBeforeForward", "Time fetched after forward in Secs: " + timeAfterF);

		} catch (Exception e) {
			logger.error("Error in time coversion");
			extent.extentLogger("error", "Error in time coversion");
		}
		if (timeAfterF >= (timeBeforeF + 10) && timeAfterF <= (timeBeforeF + 15)) {
			extent.extentLogger("Verify forward button", "Playback is forwarded by 10 seconds");
			logger.info("Playback is forwarded by 10 seconds");
		} else {
			extent.extentLoggerFail("Verify forward button", "Playback forward by 10 seconds has failed");
			logger.error("Playback forward by 10 seconds has failed");
		}
		extent.HeaderChildNode(
				"HLS_196 : Content should jump 10 secs backward post tapping on 10 secs backward button");
		System.out.println("HLS_196 : Content should jump 10 secs backward post tapping on 10 secs backward button");
		// Verify Rewind
		String currentDuration3 = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"current duration");
		logger.info("Time fetched before rewind: " + currentDuration3);
		extent.extentLogger("timeBeforeRewind", "Time fetched before rewind: " + currentDuration3);
		String[] timeBeforeRewind = currentDuration3.split(":");
		try {
			timeBeforeRmin = Integer.parseInt(timeBeforeRewind[0]) * 60;
			timeBeforeRsec = Integer.parseInt(timeBeforeRewind[1]);
			timeBeforeR = timeBeforeRmin + timeBeforeRsec;
			logger.info("Time fetched before rewind in Secs: " + timeBeforeR);
			extent.extentLogger("timeBeforeForward", "Time fetched before rewind in Secs: " + timeBeforeR);
		} catch (Exception e) {
			logger.error("Error in time coversion");
			extent.extentLogger("error", "Error in time coversion");
		}
		playerTap();
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 seconds");
		waitTime(4000);
		// Get the current time duration after clicking the rewind button
		playerTap();
		String currentDuration4 = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"current duration");
		logger.info("Time fetched after rewind: " + currentDuration4);
		extent.extentLogger("timeAfterRewind", "Time fetched after rewind: " + currentDuration4);
		String[] timeAfterRewind = currentDuration4.split(":");
		try {
			timeAfterRmin = Integer.parseInt(timeAfterRewind[0]) * 60;
			timeAfterRsec = Integer.parseInt(timeAfterRewind[1]);
			timeAfterR = timeAfterRmin + timeAfterRsec;
			logger.info("Time fetched after rewind in Secs: " + timeAfterR);
			extent.extentLogger("timeBeforeForward", "Time fetched after rewind in Secs: " + timeAfterR);
		} catch (Exception e) {
			logger.error("Error in time coversion");
			extent.extentLogger("error", "Error in time coversion");
		}
		if (timeAfterR >= (timeBeforeR - 10) && timeAfterR < (timeBeforeR - 3)) {
			extent.extentLogger("Verify rewind button", "Playback is rewinded 10 seconds");
			logger.info("Playback is rewinded 10 seconds");
		} else {
			extent.extentLoggerFail("Verify rewind button", "Playback rewind by 10 seconds has failed");
			logger.error("Playback rewind by 10 seconds has failed");
		}
		extent.HeaderChildNode("HLS_200 : Validate the availability and functionality of the volume icon");
		System.out.println("HLS_200 : Validate the availability and functionality of the volume icon");
		// Verify the mute/unmute
		playerTap();
		verifyElementPresent(PWAPlayerPage.objMuteButton, "Audio Mute button");
		playerTap();
		click(PWAPlayerPage.objMuteButton, "Audio Mute button");
		verifyElementPresent(PWAPlayerPage.objUnmuteButton, "Audio Unmute button");
		playerTap();
		click(PWAPlayerPage.playBtn, "Play button");
		waitTime(4000);
		pausePlayer();
		extent.HeaderChildNode("HLS_201 : Validate the availability of setting options on the video players.");
		System.out.println("HLS_201 : Validate the availability of setting options on the video players.");
		click(PWAPlayerPage.settingsBtn, "Settings icon");
		click(PWAPlayerPage.qualityBtn, "Quality icon");
		waitTime(5000);
		// Select Quality from Quality menu
		int size = getDriver().findElements(PWAPlayerPage.objPlayerQualities).size();
		if (size == 0) {
			logger.info("Quality options unavailable");
			extent.extentLogger("quality", "Quality options unavailable");
		} else {
			for (int i = 1; i <= size; i++) {
				logger.info("Quality option available : "
						+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
				extent.extentLogger("quality", "Quality option available : "
						+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
			}
		}
		extent.HeaderChildNode(
				"HLS_202 : Validate the availability and functionality of quality option under settings");
		System.out.println("HLS_202 : Validate the availability and functionality of quality option under settings");
		// Select other qualities
		List<WebElement> unselected = findElements(PWAPlayerPage.objPlayerUnSelectedQuality);
		ArrayList<String> qualities = new ArrayList<String>();
		for (int i = 0; i < unselected.size(); i++) {
			qualities.add(unselected.get(i).getText());
		}
		for (int i = 0; i < 2; i++) {
			String selectedQualityBefore = getText(PWAPlayerPage.objPlayerSelectedQuality);
			logger.info("Quality option before selection: " + selectedQualityBefore);
			extent.extentLogger("quality", "Quality option before selection: " + selectedQualityBefore);
			String quality = qualities.get(i);
			Thread.sleep(5000);
			click(PWAPlayerPage.objPlayerUnSelectedQuality(quality), "Quality " + quality);
			Thread.sleep(5000);
			click(PWAPlayerPage.settingsBtn, "Settings icon");
			click(PWAPlayerPage.qualityBtn, "Quality icon");
			waitTime(5000);
			String selectedQualityAfter = getElementPropertyToString("innerText",
					PWAPlayerPage.objPlayerSelectedQuality, "Selected Quality");
			logger.info("Quality option after selection : " + selectedQualityAfter);
			extent.extentLogger("quality", "Quality option after selection: " + selectedQualityAfter);
			if (selectedQualityBefore.equals(selectedQualityAfter)) {
				logger.error("Quality selection unsuccessful");
				extent.extentLoggerFail("quality", "Quality selection unsuccessful");
			} else {
				logger.info("Quality selection successful");
				extent.extentLogger("quality", "Quality selection successful");
			}
		}
		extent.HeaderChildNode(
				"HLS_203 : Validate the availability and functionality of the Audio track option under settings");
		System.out.println(
				"HLS_203 : Validate the availability and functionality of the Audio track option under settings");
		String keywordau = "Episode 13 - Agent Raghav";
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		typeAndGetSearchResult(PWAHomePage.objSearchField, keywordau, "Search");
		waitTime(5000);
		for (int i = 0; i < 2; i++) {
			try {
				waitTime(5000);
				click(PWASearchPage.objSearchedResult(keywordau), "Search Result");
				break;
			} catch (StaleElementReferenceException e) {
			}
		}
		waitTime(5000);
		directClickReturnBoolean(PWAHomePage.objCreateNewAccountPopUpClose, "Close in Sign Up Pop Up");
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		click(PWAPlayerPage.settingsBtn, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrackIcon, "Audio Track icon");
		waitTime(5000);
		int sizeau = getDriver().findElements(PWAPlayerPage.objPlayerAudioTracksAvailable).size();
		if (sizeau == 0) {
			logger.info("Audio Tracks unavailable");
			extent.extentLogger("quality", "Audio Tracks unavailable");
		} else {
			for (int i = 1; i <= size; i++) {
				logger.info("Audio Tracks available : "
						+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
				extent.extentLogger("audio", "Audio Tracks available : "
						+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
			}
			// Select audio tracks
			List<WebElement> unselectedau = findElements(PWAPlayerPage.objPlayerUnSelectedAudioTrack);
			ArrayList<String> tracks = new ArrayList<String>();
			for (int i = 0; i < unselectedau.size(); i++) {
				tracks.add(unselectedau.get(i).getText());
			}
			for (int i = 0; i < 1; i++) {
				String selectedTrackBefore = getText(PWAPlayerPage.objPlayerSelectedAudioTrack);
				logger.info("Audio Track before selection: " + selectedTrackBefore);
				extent.extentLogger("quality", "Audio Track before selection: " + selectedTrackBefore);
				String track = tracks.get(i);
				waitTime(5000);
				click(PWAPlayerPage.objPlayerUnSelectedAudioTrack(track), "Audio Track " + track);
				waitTime(5000);
				click(PWAPlayerPage.settingsBtn, "Settings icon");
				click(PWAPlayerPage.objPlayerAudioTrackIcon, "Audio Track icon");
				waitTime(5000);
				String selectedTrackAfter = getElementPropertyToString("innerText",
						PWAPlayerPage.objPlayerSelectedAudioTrack, "Selected Audio Track");
				logger.info("Audio Track after selection : " + selectedTrackAfter);
				extent.extentLogger("quality", "Quality option after selection: " + selectedTrackAfter);
				if (selectedTrackBefore.equals(selectedTrackAfter)) {
					logger.error("Audio Track selection unsuccessful");
					extent.extentLoggerFail("quality", "Audio Track selection unsuccessful");
				} else {
					logger.info("Audio Track selection successful");
					extent.extentLogger("quality", "Audio Track selection successful");
				}
			}
		}
		extent.HeaderChildNode(
				"HLS_206 : Verify whether a player stays on top when user scrolls the page on playback.");
		System.out.println("HLS_206 : Verify whether a player stays on top when user scrolls the page on playback.");
		Swipe("UP", 4);
		playerTap();
		if (verifyIsElementDisplayed(PWAPlayerPage.playBtn, "Play button of Player")) {
			logger.info("Player stays on top when user scrolls the page on playback");
			extent.extentLogger("", "Player stays on top when user scrolls the page on playback");
		} else {
			logger.error("Player section has not remained stagnant on swiping contents up");
			extent.extentLoggerFail("quality", "Player section has not remained stagnant on swiping contents up");
		}

	}

	public boolean verifyAutoPlay(String Tabname) throws Exception {
		boolean autoplayingItemsPresent = false;
		boolean autoPlayed = false;
		int autoplayItem = 0;
		// String languageSmallText = allSelectedLanguages();
		Response tabResponse = ResponseInstance.getResponseForPages(Tabname.toLowerCase(), "en,hi,kn");
		int carouselItemsCount = tabResponse.jsonPath().get("buckets[0].items.size()");
		System.out.println(carouselItemsCount);
		if (carouselItemsCount > 7)
			carouselItemsCount = 7;
		for (int i = 0; i < carouselItemsCount; i++) {
			if (tabResponse.jsonPath().get("buckets[0].items[" + i + "].tags[0]").toString().equals("Autoplay")) {
				autoplayItem = i;
				autoplayingItemsPresent = true;
				break;
			}
		}
		if (autoplayingItemsPresent == false) {
			logger.info("Autoplay could not be verified because no Autoplaying Carousel Items");
			extent.extentLoggerWarning("Autoplay",
					"Autoplay could not be verified because no Autoplaying Carousel Items");
		} else {
			navigateToAnyScreen(Tabname);
			click(PWAHamburgerMenuPage.carouselDot(autoplayItem + 1), "Carousel Dot " + (autoplayItem + 1) + "");
			waitTime(3000);
			if (verifyElementPresent(PWANewsPage.objRight, "Right facing arrow on Carousel")) {
				logger.info("Autoplay is begun and verified by presence of Right facing arrow");
				extent.extentLoggerPass("", "Autoplay is begun and verified by presence of Right facing arrow");
				autoPlayed = true;
			} else {
				logger.error("Autoplay has failed");
				extent.extentLoggerFail("", "Autoplay has failed");
				autoPlayed = false;
			}
			if (verifyElementPresent(PWANewsPage.objLeft, "Left facing arrow on Carousel")) {
				logger.info("Autoplay is begun and verified by presence of Left facing arrow");
				extent.extentLoggerPass("", "Autoplay is begun and verified by presence of Left facing arrow");
			} else {
				logger.error("Autoplay has failed");
				extent.extentLoggerFail("", "Autoplay has failed");

			}
		}
		if (autoPlayed)
			return true;
		else
			return false;
	}

	public void verifyShowDetailsUIComponents() throws Exception {
		String nextPageTitle = "";
		if (verifyIsElementDisplayed(PWAShowsPage.objShowsTitle, "Shows Details page"))
			nextPageTitle = getText(PWAShowsPage.objShowsTitle);
		if (!nextPageTitle.equals("")) {
			logger.info("Navigated to the show details page: \"" + nextPageTitle + "\"");
			extent.extentLogger("", "Navigated to the show details page: \"" + nextPageTitle + "\"");
		} else {
			logger.error("Failed to navigate to show details page");
			extent.extentLoggerFail("playerScreen", "Failed to navigate to show details page");
		}

	}

	public void swipeTillTrayAndClickContentCard(String trayTitle) throws Exception {
		int noOfSwipes = 10;
		String trayTitleUI = "";
		boolean foundtrayInPage = false, trayFoundInUI = false;
		String userType = getParameterFromXML("userType");
		for (int i = 0; i <= noOfSwipes; i++) {
			List<WebElement> trays = findElements(PWALandingPages.objTrayTitle);
			for (int j = 0; j < trays.size(); j++) {
				try {
					trayTitleUI = trays.get(j).getAttribute("innerText");
					// System.out.println(trayTitleUI);
					// System.out.println(trayTitle);
					if (trayTitleUI.toLowerCase().contains(trayTitle.toLowerCase())) {
						logger.info(trayTitle + " tray is present");
						extent.extentLogger("", trayTitle + " tray is present");
						foundtrayInPage = true;
						break;
					}
				} catch (Exception e) {
				}
			}
			if (foundtrayInPage == false) {
				PartialSwipe("UP", 1);
				waitTime(5000);
			} else {
				break;
			}
		}
		if (foundtrayInPage == true) {
			trayFoundInUI = swipeTillElement(15, PWALandingPages.objTrayTitleInUIContains(trayTitleUI),
					"\"" + trayTitleUI + "\" tray");
			if (trayFoundInUI == true) {
				// handle mandatory pop up
				mandatoryRegistrationPopUp(userType);
				swipeTillElementAndClick(3, PWALandingPages.trayCard(trayTitleUI), "First Asset in tray");
				String nextPageTitle = "";
				waitTime(10000);
				if (verifyIsElementDisplayed(PWAPlayerPage.objContentShowTitle, "Show Details")) {
					String viewallnavigationtittle = getText(PWAPlayerPage.objContentShowTitle);
					logger.info("Navigated to Show Details page from View All Page: " + viewallnavigationtittle);
					extent.extentLogger("View All",
							"Navigated to Show Details page from View All Page: " + viewallnavigationtittle);
					click(PWAShowsPage.objFirstAssetImageFirstRail, "First card image from first rail");
				}
				verifyElementPresent(PWAHomePage.objKalKalturaPlayer, "Kaltura Player");
				if (userType.equals("Guest") || userType.equals("NonSubscribedUser") || userType.equals("ClubUser")) {
					verifyIsElementDisplayed(PWAPlayerPage.objWatchingATrailerMessage,
							"'You're watching a trailer' message on the player");
				}
				nextPageTitle = getText(PWAPlayerPage.objContentTitle);
				if (!nextPageTitle.equals("")) {
					logger.info("Navigated to Consumptions page: \"" + nextPageTitle + "\"");
					extent.extentLogger("", "Navigated to Consumptions page: \"" + nextPageTitle + "\"");
				} else {
					logger.error("Failed to navigate to Consumptions page");
					extent.extentLoggerFail("playerScreen", "Failed to navigate to Consumptions page");
				}
			}
		} else {
			logger.error(trayTitle + " tray is not present in Page");
			extent.extentLoggerFail("", trayTitle + " tray is not present in Page");
		}

	}

	public boolean verifyTrayPresence(String trayTitle) throws Exception {
		int noOfSwipes = 10;
		String trayTitleUI = "";
		boolean foundtrayInPage = false;
		for (int i = 0; i <= noOfSwipes; i++) {
			List<WebElement> trays = findElements(PWALandingPages.objTrayTitle);
			for (int j = 0; j < trays.size(); j++) {
				try {
					trayTitleUI = trays.get(j).getAttribute("innerText");
					if (trayTitleUI.toLowerCase().contains(trayTitle.toLowerCase())) {
						logger.info(trayTitle + " tray is present");
						extent.extentLogger("", trayTitle + " tray is present");
						foundtrayInPage = true;
						return true;
					}
				} catch (Exception e) {
				}
			}
			if (foundtrayInPage == false) {
				PartialSwipe("UP", 1);
				waitTime(5000);
			} else {
				break;
			}
		}
		return false;
	}

	public void swipeTillTrayAndClickShow(String trayTitle) throws Exception {
		int noOfSwipes = 10;
		String trayTitleUI = "";
		boolean foundtrayInPage = false, trayFoundInUI = false;
		for (int i = 0; i <= noOfSwipes; i++) {
			List<WebElement> trays = findElements(PWALandingPages.objTrayTitle);
			for (int j = 0; j < trays.size(); j++) {
				try {
					trayTitleUI = trays.get(j).getAttribute("innerText");
					if (trayTitleUI.toLowerCase().contains(trayTitle.toLowerCase())) {
						logger.info(trayTitle + " tray is present");
						extent.extentLogger("", trayTitle + " tray is present");
						foundtrayInPage = true;
						break;
					}
				} catch (Exception e) {
				}
			}
			if (foundtrayInPage == false) {
				PartialSwipe("UP", 1);
				waitTime(5000);
			} else {
				break;
			}
		}
		if (foundtrayInPage == true) {
			trayFoundInUI = swipeTillElement(15, PWALandingPages.objTrayTitleInUIContains(trayTitleUI),
					"\"" + trayTitleUI + "\" tray");
			if (trayFoundInUI == true) {
				// handle mandatory pop up
				mandatoryRegistrationPopUp(userType);
				swipeTillElementAndClick(3, PWALandingPages.trayCard(trayTitleUI), "First Asset in tray");
				String nextPageTitle = "";
				waitTime(5000);
				if (verifyIsElementDisplayed(PWAShowsPage.objShowsTitle, "Shows Details page"))
					nextPageTitle = getText(PWAShowsPage.objShowsTitle);
				if (!nextPageTitle.equals("")) {
					logger.info("Navigated to the show details page: \"" + nextPageTitle + "\"");
					extent.extentLogger("", "Navigated to the show details page: \"" + nextPageTitle + "\"");
				} else {
					logger.error("Failed to navigate to show details page: \"" + nextPageTitle + "\"");
					extent.extentLoggerFail("playerScreen",
							"Failed to navigate to show details page: \"" + nextPageTitle + "\"");
				}
			}
		} else {
			logger.error(trayTitle + " tray is not present in Shows Page");
			extent.extentLoggerFail("", trayTitle + " tray is not present in Shows Page");
		}

	}

	public void verificationOfBackToTop(String screen) throws Exception {
		Swipe("UP", 1);
		Swipe("UP", 1);
		waitTime(3000);
		if (verifyElementPresent(PWAZee5OriginalPage.objNavigateToTop, "Back to Top")) {
			click(PWAZee5OriginalPage.objNavigateToTop, "Back to Top Arrow");
			if (screen.equalsIgnoreCase("live tv")) {
				waitforLiveTabToLoad();
			}
			waitForElementDisplayed(PWAZee5OriginalPage.objTrayTitle, 5);
			if (verifyIsElementDisplayed(PWAZee5OriginalPage.objTrayTitle, "First Tray")) {
				logger.info(
						"On tapping of 'Right side bottom arrow' user is navigated to top of screen without scrolling");
				extent.extentLogger("Right side bottom arrow",
						"On tapping of 'Right side bottom arrow' user is navigated to top of screen without scrolling");
			}
		} else {
			logger.error("Back to Top arrow is not displayed");
			extent.extentLoggerFail("", "Back to Top arrow is not displayed");
		}
	}

	public void PWAPagesNavigationAndTabHighlight(String Tabname) throws Exception {
		click(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		navigateToAnyScreen(Tabname);
		waitForElementDisplayed(PWAHomePage.objHighlightedTab(Tabname), 10);
		if (verifyElementPresent(PWAHomePage.objHighlightedTab(Tabname), Tabname)) {
			logger.info(Tabname + " tab is highlighted, user is able to navigate to " + Tabname + " landing page");
			extent.extentLogger("",
					Tabname + " tab is highlighted, user is able to navigate to " + Tabname + " landing page");
		} else {
			logger.error(Tabname + " tab is not highlighted, user failed to navigate to " + Tabname + " landing page");
			extent.extentLoggerFail("",
					Tabname + " tab is not highlighted, user failed to " + Tabname + " landing page");
		}
	}

	public void PWAPremiumOrClubIconVerification(String Tabname) throws Exception {
		navigateToAnyScreen(Tabname);
		if (Tabname.equalsIgnoreCase("Club") || Tabname.equalsIgnoreCase("Shows")) {
			for (int i = 0; i < 5; i++) {
				if (findElements(PWAHomePage.objClubTag).size() > 0) {
					logger.info("Club Tag is displayed");
					extent.extentLogger("Club Tag", "Club Tag is displayed");
					break;
				} else {
					logger.info("Club Tag is not displayed");
					extent.extentLogger("Club Tag", "Club Tag is not displayed");
					PartialSwipe("UP", 1);
				}
			}
		} else if (Tabname.equalsIgnoreCase("Play")) {
			for (int i = 0; i < 5; i++) {
				if (findElements(PWAHomePage.objJoyStickTag).size() > 0) {
					logger.info("Joystick Tag is displayed");
					extent.extentLogger("Joystick Tag", "Joystick Tag is displayed");
					break;
				} else {
					logger.info("Joystick Tag is not displayed");
					extent.extentLogger("Joystick Tag", "Joystick Tag is not displayed");
					PartialSwipe("UP", 1);
				}
			}
		} else {
			for (int i = 0; i < 5; i++) {
				if (findElements(PWAMusicPage.objPremiumTag).size() > 0) {
					logger.info("Premium tag is displayed");
					extent.extentLogger("Premium Tag", "Premium Tag is displayed");
					break;
				} else {
					logger.info("Premium tag is not displayed");
					extent.extentLogger("Premium Tag", "Premium Tag is not displayed");
					PartialSwipe("UP", 1);
				}
			}
		}
		clickElementWithLocator(PWAHomePage.objBackToTopArrow);
	}

}