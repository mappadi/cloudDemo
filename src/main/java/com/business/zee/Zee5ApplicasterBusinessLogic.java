package com.business.zee;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.validator.routines.EmailValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import com.deviceDetails.DeviceDetails;
import com.driverInstance.CommandBase;
import com.emailReport.GmailInbox;
import com.extent.ExtentReporter;
import com.jayway.restassured.response.Response;
import com.metadata.ResponseInstance;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.ApplicasterPages.*;
import com.zee5.PWAPages.*;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class Zee5ApplicasterBusinessLogic extends Utilities {

	public Zee5ApplicasterBusinessLogic(String Application) {
		new CommandBase(Application);
		init();
	}

	private int timeout;

	/** Retry Count */
	private int retryCount;
	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
//	final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver;

	/** The Android driver. */
	public IOSDriver<WebElement> iOSDriver;

	/** Array of WebPWA */
	static ArrayList<String> WebPWAMyProfile = new ArrayList<String>();
	static ArrayList<String> WebPWAWatchList = new ArrayList<String>();
	static ArrayList<String> WebPWAReminders = new ArrayList<String>();
	static ArrayList<String> WebPWASubscription = new ArrayList<String>();
	static ArrayList<String> webPWATransaction = new ArrayList<String>();
	static String webDisplayLanguage = null;
	static String appDisplayLanguage = null;
	static String webUpdatedFirstName = null;

	/** Array of App */
	static ArrayList<String> AppMyProfile = new ArrayList<String>();
	static HashSet<String> contentsInWatchList = new HashSet<String>();
	static HashSet<String> contentsInReminders = new HashSet<String>();
	static ArrayList<String> AppSubscription = new ArrayList<String>();
	static ArrayList<String> AppTransaction = new ArrayList<String>();

	@Override
	public int getTimeout() {
		return timeout;
	}

	@Override
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	GmailInbox gmail = new GmailInbox();

	String SVODEpisode = getParameterFromXML("SVODEpisode");

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
//		logger.info("Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	/**
	 * Function to Relaunch the driver
	 */
	public void relaunch(boolean clearData) throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getDriver().quit();
		relaunch = clearData;
		new Zee5ApplicasterBusinessLogic("zee");
	}

	/**
	 * Function to quit the driver
	 */
	public void tearDown() {
		getDriver().quit();
	}

	String pUserType = getParameterFromXML("userType");
	String RegisteredEmail = getParameterFromXML("RegisteredEmail");
	String RegisteredEmailPassword = getParameterFromXML("RegisteredEmailPassword");
	String UnRegisteredMobile = getParameterFromXML("UnRegisteredMobile");
	String RegisteredMobile = getParameterFromXML("RegisteredMobile");
	String RegisteredMobilePassword = getParameterFromXML("RegisteredMobilePassword");
	String PromoCode = getParameterFromXML("PromoCode");
	String NonsubscribedUserName = getParameterFromXML("NonsubscribedUserName");
	String NonsubscribedPassword = getParameterFromXML("NonsubscribedPassword");
	String SubscribedUserName = getParameterFromXML("SubscribedUserName");
	String SubscribedPassword = getParameterFromXML("SubscribedPassword");
	String FirstName = getParameterFromXML("FirstName");
	String LastName = getParameterFromXML("LastName");

	String RSVODUser = getParameterFromXML("RSVODUser");
	String RSVODPassword = getParameterFromXML("RSVODPassword");

	String content1 = getParameterFromXML("searchcontent1");
	String content2 = getParameterFromXML("searchcontent2");
	String content3 = getParameterFromXML("searchcontent3");
	String content4 = getParameterFromXML("searchcontent4");
	String content5 = getParameterFromXML("searchcontent5");
	String content6 = getParameterFromXML("searchcontent6");
	String pVideoQuality = getParameterFromXML("VideoQuality");
	String pMovie = getParameterFromXML("MovieName");
	String RSVODContent = getParameterFromXML("RSVODContent");

	// Retrieve the Mobile Device Name
	String getOEMName = DeviceDetails.OEM;

	public void accessDeviceLocationPopUp(String permission, String userType) throws Exception {
		extent.HeaderChildNode("Access Device Location PopUp");
		extent.extentLogger("User Type", "UserType : " + userType);
		logger.info("UserType : " + userType);
		System.out.println("Access Device Location PopUp");
		//PartialSwipe("UP", 1);
		click(AMDOnboardingScreen.objEnv, "Env button");
		Wait(10000);
		click(AMDOnboardingScreen.objAccept, "Allow only while using the app");
		//Wait(10000);
//		Back(1);
//		Swipe("UP", 4);
		click(AMDOnboardingScreen.objCountry, "Country list");
		click(AMDOnboardingScreen.objContinue, "Continue button");
		click(AMDOnboardingScreen.objContinue, "Continue button");
		
//		if (checkElementExist(AMDOnboardingScreen.objAllowBtn)) {
//			Wait(5000);
//			verifyElementPresent(AMDOnboardingScreen.objAllowBtn, "Allow button");
//			verifyElementPresent(AMDOnboardingScreen.objDenyBtn, "Deny button");
//
//			if (permission.equalsIgnoreCase("Allow")) {
//				click(AMDOnboardingScreen.objAllowBtn, "Allow button");
//			} else {
//				click(AMDOnboardingScreen.objDenyBtn, "Deny button");
//			}
//		}
	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: SHREE NIDHI
	 * -------------------------------
	 * 
	 * List of Functions Created - contentLanguage(String userType) -
	 * mobileRegistration(String loginThrough) - subscribeNowSceanrios() -
	 * unRegisteredEmailSubscribe() - subscribeFLowMobileNumber() -
	 * passwordScenario(String UserType) - otpScenarios() -
	 * checkScreenAfterRelaunch(String userType, String ScreenName)
	 * =============================================================================
	 * ========
	 */

	public void contentLanguage(String userType) throws Exception {
		extent.HeaderChildNode("Content language screen functionlity");
		extent.extentLogger("User Type", "UserType : " + userType);
		logger.info("UserType : " + userType);
		verifyElementPresentAndClick(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button in display language");
		verifyElementExist(AMDOnboardingScreen.objScreenTitle, "Screen header");
		verifyElementExist(AMDOnboardingScreen.objContentLanguagePageTitle, "Page title");
		verifyElementExist(AMDOnboardingScreen.objContentLanguageContainer, "Content language");
		verifyElementExist(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in content language screen");
		verifyElementExist(AMDOnboardingScreen.objBackBtn, "Back button in content language screen");
		click(AMDOnboardingScreen.objSelectContentLang("English"), "Unselected English language");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in content language screen");
		waitTime(3000);
		if (checkElementExist(AMDOnboardingScreen.objLoginLnk, "Login button")) {
			logger.info("User is navigated to intro screen");
			extent.extentLogger("Navigation", "user is navigated to intro screen");
			Back(1);
		}
		PartialSwipe("UP", 1);
		click(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Unselected Kannada language");
		PartialSwipe("UP", 1);
		if (checkElementExist(AMDOnboardingScreen.objSelectContentLang("Malayalam"), "Malayalam language")) {
			click(AMDOnboardingScreen.objSelectContentLang("Malayalam"), "Malayalam language");
			logger.info("Clicked on malayalam language");
			click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in content language screen");
		} else {
			Swipe("UP", 1);
			verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Malayalam"), "Malayalam language");
			click(AMDOnboardingScreen.objSelectContentLang("Malayalam"), "Malayalam language");
			logger.info("Clicked on malayalam language");
			click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in content language screen");
		}
		waitTime(2000);
		String ContentLanguagetitle = getText(AMDOnboardingScreen.objContentLanguagePageTitle);
		logger.info(ContentLanguagetitle);
		if (ContentLanguagetitle.contains("please select at least one more language")) {
			logger.info("Select atleast one language screen is displayed");
			extent.extentLogger("Screen", "Select atleast one language screen is displayed");
		}
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Hindi"), "Hindi language");
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("English"), "English language");
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Marathi"), "Marathi language");
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Telugu"), "Telugu language");
		Swipe("UP", 1);
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Kannada language");
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Tamil"), "Tamil language");
		Swipe("UP", 1);
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Bengali"), "Bengali language");
		verifyElementExist(AMDOnboardingScreen.objBackBtn, "Back button in content language screen");
		click(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Kannada language");
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn,
				"Continue button in content language screen");
		waitTime(2000);
		if (checkElementExist(AMDOnboardingScreen.objLoginLnk, "Login button")) {
			logger.info("User is navigated to intro screen");
			extent.extentLogger("Navigation", "user is navigated to intro screen");
		}
	}

	public void mobileRegistration(String loginThrough, String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Mobile Registration From Intro screen loginlink");
			click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");
			click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in content language screen");
			navigateToRegisterScreen(loginThrough);
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "EmailField");
			type(AMDLoginScreen.objEmailIdField, UnRegisteredMobile, "Mobile");
			hideKeyboard();
			click(AMDLoginScreen.objProceedBtn, "Proceed icon");
			registerForFreeScreen("Mobile");
			waitTime(3000);
			otpScenarios();
			Back(1);
			waitTime(3000);
			hideKeyboard();
			Back(1);
			int lenText = findElement(AMDLoginScreen.objEmailIdField).getAttribute("value").length();
			for (int i = 0; i < lenText; i++) {
				getDriver().findElement(AMDLoginScreen.objEmailIdField).sendKeys(Keys.BACK_SPACE);
			}
			type(AMDLoginScreen.objEmailIdField, RegisteredMobile, "Mobile number field");
			click(AMDLoginScreen.objProceedBtn, "Proceed button");
			type(AMDLoginScreen.objPasswordField, RegisteredMobilePassword, "Password field");
			hideKeyboard();
			click(AMDLoginScreen.objLoginBtn, "Login button");
			verifyElementExist(AMDHomePage.objHomeTab, "Home tab");
			click(AMDHomePage.objMoreMenu, "More Menu");
			Swipe("UP", 1);
			click(AMDHomePage.objLogout, "Logout");
			click(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
			click(AMDHomePage.objHome, "Home tab");
		}
	}

	public void subscribeNowSceanrios(String userType) throws Exception {
		extent.HeaderChildNode("navigation to Intro screen");

		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in content language screen");
		if (userType.equals("Guest")) {
			verifyElementPresentAndClick(AMDOnboardingScreen.objSubscribeNowBtn, "Subscribe now button");
			subscribePageValidation();
			passwordScenario("Registered");
			unRegisteredEmailSubscribe();
			subscribeFLowMobileNumber();
		}
		if (userType.equals("NonSubscribedUser")) {
			extent.HeaderChildNode("Relaunch functionality");
			verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login button");
			click(AMDLoginScreen.objEmailIdField, "Email field");
			verifyElementExist(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, NonsubscribedUserName, "Email field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			waitTime(2000);
			type(AMDLoginScreen.objPasswordField, NonsubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
			verifyElementExist(AMDHomePage.objHomeTab, "Home tab");
			relaunch(false);
			waitTime(2000);
			if (checkElementExist(AMDHomePage.objHomeTab, "Home tab")) {
				logger.info("When " + userType
						+ " relaunch the app Display/Content language, Intro screen and Login/Register screen is skipped");
				extent.extentLoggerPass("Relaunch", "When " + userType
						+ " relaunch the app Display/Content language, Intro screen and Login/Register screen is skipped");
			} else {
				logger.error("When " + userType
						+ " relaunch the app Display/Content language, Intro screen and Login/Register screen is not skipped");
				extent.extentLoggerFail("Relaunch", "When " + userType
						+ " relaunch the app Display/Content language, Intro screen and Login/Register screen is not skipped");
			}
		}
		if (userType.equals("SubscribedUser")) {
			extent.HeaderChildNode("Relaunch functionality");
			verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login button");
			click(AMDLoginScreen.objEmailIdField, "Email field");
			verifyElementExist(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUserName, "Email field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			waitTime(2000);
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
			verifyElementExist(AMDHomePage.objHomeTab, "Home tab");
			relaunch(false);
			waitTime(2000);
			if (checkElementExist(AMDHomePage.objHomeTab, "Home tab")) {
				logger.info("When " + userType
						+ " relaunch the app Display/Content language, Intro screen and Login/Register screen is skipped");
				extent.extentLogger("Relaunch", "When " + userType
						+ " relaunch the app Display/Content language, Intro screen and Login/Register screen is skipped");
			} else {
				logger.error("When " + userType
						+ " relaunch the app Display/Content language, Intro screen and Login/Register screen is not skipped");
				extent.extentLoggerFail("Relaunch", "When " + userType
						+ " relaunch the app Display/Content language, Intro screen and Login/Register screen is not skipped");
			}
		}
	}

	public void unRegisteredEmailSubscribe() throws Exception {
		extent.HeaderChildNode("SubscribeNow Functionality for UnRegistered email-id");
		System.out.println("\nSubscribeNow Functionality for UnRegistered email-id");
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in subscription page");
		verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in subscribe page");
		verifyElementExist(AMDSubscibeScreen.objAdbanner, "Carosual in subscription page");
		verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo code in subscribe page");
		verifyElementPresent(AMDSubscibeScreen.objApply, "Apply button is subscribe page");
		Swipe("UP", 3);
		click(AMDSubscibeScreen.objContinueBtn, "Continue button");
		verifyElementExist(AMDSubscibeScreen.objAccountInfoHeader, "Account info screen");
		hideKeyboard(); // Added by Kushal
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header");
		click(AMDSubscibeScreen.objEmailID, "Email id");
		type(AMDSubscibeScreen.objEmailID, RandomStringGenerator(5) + "@gmail.com", "Email");
		hideKeyboard();
		click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		passwordScenario("UnRegistered");

	}

	public void subscribeFLowMobileNumber() throws Exception {
		extent.HeaderChildNode("SubscribeNow Functionality for UnRegistered Mobile number");
		System.out.println("\nSubscribeNow Functionality for UnRegistered Mobile number");
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in subscription page");
		verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in subscribe page");
//		closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000); // INTERSTITIAL AD - HANDLED HERE
		verifyElementExist(AMDSubscibeScreen.objAdbanner, "Carosual in subscription page");
		verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo code in subscribe page");
		verifyElementPresent(AMDSubscibeScreen.objApply, "Apply button is subscribe page");
		PartialSwipe("UP", 4);
		click(AMDSubscibeScreen.objContinueBtn, "Continue button");
		verifyElementExist(AMDSubscibeScreen.objAccountInfoHeader, "Account info screen");
		hideKeyboard();
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header");
		click(AMDSubscibeScreen.objEmailID, "Email id");
		type(AMDSubscibeScreen.objEmailID, UnRegisteredMobile, "Email");
		hideKeyboard();
		click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		verifyElementExist(AMDSubscibeScreen.objVerifyOTPScreen, "OTP screen");
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");

		click(AMDRegistrationScreen.objOTPField3, "OTP field 3");
		// Added a generic object to handle the numeric keyboard across various devices
//		if (verifyElementExist(AMDRegistrationScreen.objNumericKeys, "Numeric Keypad")) {
//			logger.info("Numeric keyboard is displayed in OTP screen");
//			extent.extentLogger("Numeric", "Numeric keyboard is displayed in OTP screen");
//		}
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");

		hideKeyboard();
		waitTime(2000);
		if (findElement(AMDSubscibeScreen.objVerifyOTPScreenProceed).isEnabled()) {
			logger.info("Verify Button is highlighted");
			extent.extentLoggerPass("Verify", "Verify Button is highlighted");
		} else {
			logger.error("Verify Button is not highlighted");
			extent.extentLoggerFail("Verify", "Verify Button is not highlighted");
		}
		Back(1);

		extent.HeaderChildNode("SubscribeNow Functionality for Registered Mobile number");
		int lenText = findElement(AMDLoginScreen.objEmailIdField).getAttribute("value").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(AMDLoginScreen.objEmailIdField).sendKeys(Keys.BACK_SPACE);
		}
		click(AMDSubscibeScreen.objEmailID, "Email");
		type(AMDSubscibeScreen.objEmailID, RegisteredMobile, "Email");

		hideKeyboard();
		click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		verifyElementExist(AMDSubscibeScreen.objEnterPassword, "Enter Password PopUp");
		verifyElementExist(AMDSubscibeScreen.objPasswordTextField, "Password field");
		verifyElementPresentAndClick(AMDSubscibeScreen.objGetOTP, "Get OTP");
		verifyElementExist(AMDSubscibeScreen.objVerifyOTPScreen, "OTP screen");

		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");

		// Added a generic object to handle the numeric keyboard across various devices
		click(AMDRegistrationScreen.objOTPField3, "OTP field 3");
//		if (verifyElementExist(AMDRegistrationScreen.objNumericKeys, "Numeric Keypad")) {
//			logger.info("Numeric keyboard is displayed in OTP screen");
//			extent.extentLogger("Numeric", "Numeric keyboard is displayed in OTP screen");
//		}
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");

		hideKeyboard();
		waitTime(2000);
		if (findElement(AMDSubscibeScreen.objVerifyOTPScreenProceed).isEnabled()) {
			logger.info("Verify Button is highlighted");
			extent.extentLoggerPass("Verify", "Verify Button is highlighted");
		} else {
			logger.info("Verify Button is not highlighted");
			extent.extentLoggerPass("Verify", "Verify Button is not highlighted");
		}
		Back(1);
		click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		passwordScenario("Registered");
		Wait(2000);
		Back(1);
//		closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000); // INTERSTITIAL AD - HANDLED HERE
	}

	public void closeInterstitialAd(By byLocator, int iTimeOut) throws Exception {
		try {

			if (checkcondition(byLocator)) {
				click(byLocator, "Interstitial Ad_Close button");
			}

		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
	}

	public void passwordScenario(String UserType) throws Exception {

		System.out.println("\nPassword Scenario");
		verifyElementExist(AMDSubscibeScreen.objPasswordTextField, "Password field");
		click(AMDSubscibeScreen.objPasswordTextField, "Password");
		type(AMDSubscibeScreen.objPasswordTextField, "Use", "Password field");
		boolean var = verifyIsElementDisplayed(AMDSubscibeScreen.objPasswordErrorMessage);
		if (var == true) {
			logger.info("“Password must be a minimum of 6 characters” error message is displayed");
			extentLoggerPass("Password", "“Password must be a minimum of 6 characters” error message is displayed");
		}
		type(AMDSubscibeScreen.objPasswordTextField, "r", "Password field");
		hideKeyboard();
		System.out.println("DEVICE NAME : " + getOEMName);
		if (getOEMName.contains("vivo")) {
			hidePwdKeyboard();
		}
		verifyElementExist(AMDSubscibeScreen.objPasswordErrorMessage, "Error message");
		if (verifyIsElementDisplayed(AMDSubscibeScreen.objPasswordHidden)) {
			logger.info("Passowrd is not shown");
			extent.extentLoggerPass("password", "Passowrd is not shown");
		} else {
			logger.error("Passowrd is shown");
			extent.extentLoggerFail("password", "Passowrd is shown");
		}
		click(AMDSubscibeScreen.objShowIcon, "Toggle password icon");
		if (verifyIsElementDisplayed(AMDSubscibeScreen.objPasswordDisplayed)) {
			logger.info("Passowrd is shown after clicking on toggle passowrd icon");
			extent.extentLoggerPass("password", "Passowrd is shown after clicking on toggle passowrd icon");
		} else {
			logger.error("Passowrd is not shown after clicking on toggle passowrd icon");
			extent.extentLoggerFail("password", "Passowrd is not shown after clicking on toggle passowrd icon");
		}
		click(AMDSubscibeScreen.objShowIcon, "Toggle password icon");
		if (UserType == "Registered") {
			verifyElementExist(AMDSubscibeScreen.objForgotPassword, "forgot password");
			Wait(1000);
			click(AMDSubscibeScreen.objForgotPassword, "forgot password");
			verifyElementExist(AMDSubscibeScreen.objForgotPasswordPageHeader, "Forgot password page");
			Back(1);
			click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		}
		if (UserType == "UnRegistered") {
			logger.info("Forgot password is not displayed for unregistered user");
			verifyElementExist(AMDSubscibeScreen.objTermsandPrivacyLink, "Terms and privacy links");
		}
		verifyElementExist(AMDSubscibeScreen.objPasswordTextField, "Password field");
		click(AMDSubscibeScreen.objPasswordTextField, "Password");
		type(AMDSubscibeScreen.objPasswordTextField, RegisteredMobilePassword, "Password field");
		hideKeyboard();
		System.out.println("DEVICE NAME : " + getOEMName);
		if (getOEMName.contains("vivo")) {
			hidePwdKeyboard();
		}
		verifyElementPresentAndClick(AMDSubscibeScreen.objProceedPWDScreen, "Proceed button in password popup");
		waitTime(3000);
		Swipe("DOWN", 3);
		verifyElementExist(AMDSubscibeScreen.objPaymentText, "Payment page");
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in Payment page");
		verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in Payment page");
		verifyElementExist(AMDSubscibeScreen.objSelectedPackText, "Selected Pack details");
		Back(2);
		click(AMDHomePage.objMoreMenu, "More Menu");
		Swipe("UP", 2);
		click(AMDHomePage.objLogout, "Logout");
		click(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
		click(AMDHomePage.objHome, "Home tab");
		verifyElementPresentAndClick(AMDHomePage.objSubscribeTeaser, "Subscribe tab");
	}

	public void otpScenarios() throws Exception {
		verifyElementExist(AMDRegistrationScreen.objOTPScreen, "OTP screen");
		verifyElementExist(AMDRegistrationScreen.objOTPTimer, "OTP timer");

		if (getDriver().findElement(AMDLoginScreen.objResendOtpLink).isDisplayed()) {
			logger.info("Didn't get OTP text is displayed with Resend CTA");
			extentLoggerPass("Resend button", "Didn't get OTP text is displayed with Resend CTA");
		} else {
			logger.info("Didn't get OTP text is not displayed with Resend CTA");
			extentLoggerFail("Resend button", "Didn't get OTP text is not displayed with Resend CTA");
		}

		String OTPTimer1 = getText(AMDRegistrationScreen.objOTPTimer);
		logger.info(OTPTimer1);
		click(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(10000);
		String OTPTimer2 = getText(AMDRegistrationScreen.objOTPTimer);
		logger.info(OTPTimer2);
		boolean Time = OTPTimer1.equals(OTPTimer2);
		if (Time == false) {
			logger.info("The Otp timer is in reverse order");
			extentLoggerPass("OtpTimer", "The Otp timer is in reverse order");
		} else {
			logger.info("The Otp timer is not in reverse order");
			extentLoggerPass("OtpTimer", "The Otp timer is not in reverse order");
		}
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
//		if (verifyElementExist(AMDRegistrationScreen.objNumericKeyBoard, "Alphakeyboard")) {
//			logger.info("Numeric keyboard is displayed in OTP screen");
//			extent.extentLogger("Numeric", "Numeric keyboard is displayed in OTP screen");
//		}
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		waitTime(2000);
		if (findElement(AMDRegistrationScreen.objVerifyOtpButton).isEnabled()) {
			logger.info("Verify Button is highlighted");
			extent.extentLoggerPass("Verify", "Verify Button is highlighted");
		} else {
			logger.error("Verify Button is not highlighted");
			extent.extentLoggerFail("Verify", "Verify Button is not highlighted");
		}
	}

	public void checkScreenAfterRelaunch(String userType, String ScreenName) throws Exception {
		extent.HeaderChildNode("Validating that" + ScreenName + "is not present when app is relaunched");
		verifyElementPresentAndClick(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue");
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn, "Continue");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login button");
		click(AMDLoginScreen.objEmailIdField, "Email field");
		verifyElementExist(AMDLoginScreen.objEmailIdField, "Email field");

		if (userType.equals("NonSubscribedUser")) {
			type(AMDLoginScreen.objEmailIdField, NonsubscribedUserName, "Email field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			waitTime(2000);
			type(AMDLoginScreen.objPasswordField, NonsubscribedPassword, "Password field");
		} else if (userType.equals("SubscribedUser")) {
			type(AMDLoginScreen.objEmailIdField, SubscribedUserName, "Email field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			waitTime(2000);
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
		}

		if (userType.equalsIgnoreCase("Guest")) {
			verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip button");
		} else {
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
		}

		verifyElementExist(AMDHomePage.objHomeTab, "Home tab");
		relaunch(false);
		waitTime(2000);

		if (userType.contains("Guest")) {
			if (checkElementExist(AMDOnboardingScreen.objBrowseForFreeBtn, "BrowseForFree Screen")) {
				logger.info("When " + userType + " relaunch the app " + ScreenName + " is skipped");
				extent.extentLoggerPass("Relaunch",
						"When" + userType + " relaunch the app" + ScreenName + " is skipped");
			} else {
				logger.error("When " + userType + " relaunch the app " + ScreenName + " is displayed");
				extent.extentLoggerFail("Relaunch",
						"When" + userType + " relaunch the app" + ScreenName + " is displayed");
			}
		} else if (checkElementExist(AMDHomePage.objHomeTab, "Home tab")) {
			logger.info("When " + userType + " relaunch the app Display/Content language and Intro screen is skipped");
			extent.extentLoggerPass("Relaunch",
					"When " + userType + " relaunch the app Display/Content language and Intro screen is skipped");
		} else {
			logger.error("When " + userType + " relaunch the app user is not redirected to Home page");
			extent.extentLoggerFail("Relaunch",
					"When " + userType + " relaunch the app user is not redirected to Home page");
		}
	}

	public void registerForFreeScreen(String user) throws Exception {
		extent.HeaderChildNode("Register for free Page");
		System.out.println("\nRegister for free Page");
		if (user.equals("Email")) {
			type(AMDRegistrationScreen.objEmailIDTextField, generateRandomString(5) + "@gmail.com", "Email field");
			click(AMDRegistrationScreen.objProceedBtn, "Proceed button");
		} else if (user.equals("Mobile")) {
			logger.info("Mobile registration");
		}
		verifyElementExist(AMDRegistrationScreen.objScreenTitle, "Register for free title");
		type(AMDRegistrationScreen.objFirstNameTxtField, FirstName, "First name field");
		hideKeyboard();
		type(AMDRegistrationScreen.objLastNameTxtField, LastName, "Last name field");
		hideKeyboard();
		type(AMDRegistrationScreen.objDOBTxtField, "01/01/1990", "DOB");
		verifyElementPresentAndClick(AMDRegistrationScreen.objGederTxtField, "Gender field");
		verifyElementPresentAndClick(AMDRegistrationScreen.objMale, "Gender male");
		click(AMDRegistrationScreen.objPasswordTxtField, "Passowrd");
		type(AMDRegistrationScreen.objPasswordTxtField, "123456", "Password field");
		click(AMDRegistrationScreen.objFirstNameTxtField, "First name"); // Clicking on First Name field to get devices

		hideKeyboard();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(3000);
		if (user.equals("Email")) {
			boolean verifyHomePage = verifyElementExist(AMDHomePage.objHomeTab, "Home Screen");
			if (verifyHomePage) {
				logger.info("New User Registerd to ZEE5 App successfully");
				extent.extentLoggerPass("Registration", "New User Registerd to ZEE5 App successfully");
			} else {
				logger.error("New User failed to Register to ZEE5 App");
				extent.extentLoggerFail("Registration", "New User failed to Register to ZEE5 App");
			}
		} else if (user.equals("Mobile")) {
			logger.info("Mobile registration");
		} else {
			logger.info("Prepaid PopUp after registration");
		}
	}

	public void subscribeEntryPointsValidations(String userType) throws Exception {

		if (userType.equals("Guest")) {
			HeaderChildNode("Entry points");
			waitTime(10000);
			verifyElementExist(AMDHomePage.objGetPremiumCTAOnCarosel, "Get Premium CTA on carosel");
			click(AMDHomePage.objGetPremiumCTAOnCarosel, "Get Premium CTA on carosel");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
			waitTime(5000);
			click(AMDHomePage.objShowsTab, "Shows Tab");
			waitTime(5000);
//			closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000); // INTERSTITIAL AD - HANDLED HERE
			Swipe("UP", 1);
			waitTime(5000);
			boolean beforTV = verifyIsElementDisplayed(AMDHomePage.objBeforeTVTray);
			if (beforTV) {
				waitTime(5000);
				String beforeTVtrayName = findElement(AMDGenericObjects.objTrayTitle("Before")).getText();
				click(AMDGenericObjects.objViewAllBtn(beforeTVtrayName), "View All_Before TV Show");
				waitTime(4000);
				click(AMDHomePage.objBeforeTVContent, "BeforeTV content");
				waitTime(5000);
				verifyElementPresentAndClick(AMDHomePage.objGetClubInConsumptionScreen, "Get Premium icon");
				verifyElementExist(AMDHomePage.objGetPremiumPopUP, "PremiumPopUp");
				Swipe("UP", 1);
				click(AMDHomePage.objGetPremiumPopUPProceedButton, "Proceed button");
				verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
				Back(2);

			} else {
				logger.info("Before TV tray is not displayed");
				extent.extentLoggerWarning("TV", "Before TV tray is not displayed");
				click(AMDHomePage.HomeIcon, "Home Tab");
			}

			waitTime(3000);
			click(AMDHomePage.HomeIcon, "Home Tab");
			click(AMDHomePage.MoreMenuIcon, "More Menu");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("Buy Subscription"), "Buy Subscription");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			subscribePageValidation();
			passwordScenario("Registered");
			unRegisteredEmailSubscribe();
			subscribeFLowMobileNumber();
			waitTime(10000);
//			click(AMDHomePage.objSearchBtn, "Search button");
//			waitTime(5000);
//			click(AMDSearchScreen.objSearchEditBox, "Search box");
//			type(AMDSearchScreen.objSearchBoxBar, "Natasaarvabhowma trailer\n", "Search box");
//			hideKeyboard();
//			waitTime(6000);
//			click(AMDSearchScreen.objContentNameInPlayer("Natasaarvabhowma - Trailer"), "Search result");
//			waitTime(5000);
//			verifyElementExist(AMDSearchScreen.objContentNameInPlayer("Natasaarvabhowma - Trailer"),
//					"Content name below the player");
//			waitTime(30000);
//			verifyElementExist(AMDHomePage.objGetPremiumPopUP, "PremiumPopUp");
//			click(AMDHomePage.objGetPremiumPopUPProceedButton, "Proceed button");
//			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
		}

		else if (userType.equals("NonSubscribedUser")) {
			HeaderChildNode("Entry points");
			verifyElementExist(AMDHomePage.objHomeBtn, "Home button");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDHomePage.objMyProfileIcon, "profile icon");

			if (verifyIsElementDisplayed(AMDHomePage.objEditProfile)) {
				logger.info("User is logged in successfully");
				extent.extentLoggerPass("Edit", "User is logged in successfully");
			} else {
				logger.error("User is not logged in successfully");
				extent.extentLoggerFail("Edit", "User is not logged in successfully");
			}
			Back(1);
			click(AMDHomePage.objHomeBtn, "Home tab");
			waitTime(4000);
			verifyElementExist(AMDHomePage.objGetPremiumCTAOnCarosel, "Get Premium CTA on carosel");
			click(AMDHomePage.objGetPremiumCTAOnCarosel, "Get Premium CTA on carosel");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
//			waitTime(5000);                 NEED CLARIFICATION
//			verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
//			waitTime(5000);
//			PartialSwipe("UP", 1);
//			waitTime(5000);
//			if (verifyElementExist(AMDHomePage.objBeforeTVTray, "BeforeTV tray")) {
//				Wait(10000);
//				click(AMDHomePage.objBeforeTVTray, "BeforeTV tray");
//				waitTime(4000);
//				click(AMDHomePage.objBeforeTVContent, "BeforeTV content");
//				waitTime(5000);
//				verifyElementExist(AMDHomePage.objGetPremiumPopUP, "PremiumPopUp");
//				click(AMDHomePage.objGetPremiumPopUPProceedButton, "Proceed button");
//				verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
//
//			} else {
//				logger.info("Before TV tray is not displayed");
//				extent.extentLogger("TV", "Before TV tray is not displayed");
//			}
//			Back(1);
//			waitTime(3000);
//			Back(1);
			click(AMDHomePage.objHomeBtn, "Home Tab");
			click(AMDHomePage.objMoreMenuBtn, "More Menu");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("Buy Subscription"), "Buy Subscription");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("My Subscription"), "My Subscription");
			verifyElementPresentAndClick(AMDHomePage.objSubscribeNowInMySubscription, "Subscribe now CTA");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
			waitTime(3000);
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("My Transactions"), "My Transactions");
			verifyElementPresentAndClick(AMDHomePage.objSubscribeNowInMyTransaction, "Subscribe now CTA");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
			waitTime(3000);
			Back(1);
			click(AMDHomePage.objHomeBtn, "Home Tab");
			click(AMDHomePage.objSubscribeTeaser, "Subscribe button");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in subscription page");
			verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in subscribe page");
			verifyElementExist(AMDSubscibeScreen.objAdbanner, "Carosual in subscription page");
			verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo code in subscribe page");
			verifyElementPresent(AMDSubscibeScreen.objApply, "Apply button is subscribe page");
			if (checkElementExist(AMDSubscibeScreen.objApplybuttonNotHighlighted, "Apply button")) {
				logger.info("Apply button is not highlighted by default");
				extent.extentLoggerPass("Highlighted", "Apply button is not highlighted by default");
			}
			click(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, PromoCode, "Promo code");
			hideKeyboard();
			click(AMDSubscibeScreen.objApply, "Apply button");
			verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeappliedText, "Promo code applied successfully text");
			if (checkElementExist(AMDSubscibeScreen.objApplyPromoCodeappliedText, "Promo")) {
				logger.info("Discounted price is displayed after promo code is applied");
				extent.extentLoggerPass("Promo", "Discounted price is displayed after promo code is applied");
			} else {
				logger.error("Discounted price is not displayed after promo code is applied");
				extent.extentLoggerFail("Promo", "Discounted price is not displayed after promo code is applied");
			}
			click(AMDSubscibeScreen.objCancelPromoCode, "Cancel promo");
			click(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, "zee5flat5000", "Promo code");
			hideKeyboard();
			click(AMDSubscibeScreen.objApply, "Apply button");
			verifyElementExist(AMDSubscibeScreen.objInvalidPromoCodeText, "Invalid promo code error message");
			Swipe("UP", 2);
			PartialSwipe("UP", 2);
			verifyElementExist(AMDSubscibeScreen.objDescriptionText, "Premium Description in subscribe page");
			Swipe("DOWN", 1);
			verifyElementExist(AMDSubscibeScreen.objPremiumTab, "Premium pack tab");
			verifyElementExist(AMDSubscibeScreen.objClubTab, "Club pack tab");
			Swipe("UP", 1);
			int size = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).size();
			for (int i = 0; i < size; i++) {
				boolean isDisplayed = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).isDisplayed();
				if (isDisplayed) {
					String pack = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).getText();
					extent.extentLoggerPass("Packs", "Available Pack " + i + " : " + pack);
					logger.info("Available Pack " + i + " : " + pack);
				} else {
					extent.extentLoggerFail("Packs", "No Packs are available");
					logger.info("No Packs are available");
				}
			}
			Swipe("UP", 1);
			verifyElementExist(AMDSubscibeScreen.objContinueBtn, "Continue button in subscribe page");
			if (getDriver().findElement(AMDSubscibeScreen.objContinueBtn).isEnabled()) {
				logger.info("Continue button is highlighted");
				extent.extentLoggerPass("Highlighted", "Continue button is highlighted");
			}
			Swipe("DOWN", 1);
//			click(AMDSubscibeScreen.objRSVODPremiumPack, "RSVOD Pack tab");
//			verifyElementPresentAndClick(AMDSubscibeScreen.objRSVODPack1, "RSVOD Plan for 30 days");
//			PartialSwipe("UP", 2);
//			verifyElementExist(AMDSubscibeScreen.objRSVODPack2, "RSVOD Plan for 365 days");
			int size3 = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).size();
			for (int i = 0; i < size3; i++) {
				boolean isDisplayed = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).isDisplayed();
				if (isDisplayed) {
					String pack = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).getText();
					extent.extentLoggerPass("Packs", "Available Pack " + i + " : " + pack);
					logger.info("Available Pack " + i + " : " + pack);
				} else {
					extent.extentLoggerFail("Packs", "No Packs are available");
					logger.info("No Packs are available");
				}
			}
			Swipe("Down", 1);
			verifyElementExist(AMDSubscibeScreen.objDescriptionText, "Premium Description in subscribe page");
			Swipe("Up", 2);
			if (getDriver().findElement(AMDSubscibeScreen.objContinueBtn).isEnabled()) {
				logger.info("Continue button is highlighted");
				extent.extentLoggerPass("Highlighted", "Continue button is highlighted");
			}
			click(AMDSubscibeScreen.objContinueBtn, "Continue button");
			verifyElementExist(AMDSubscibeScreen.objPaymentText, "Payment page");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in Payment page");
			verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in Payment page");
			Back(1);
			waitTime(3000);
			Back(1);
			waitTime(5000);
			click(AMDHomePage.objSearchBtn, "Search button");
			waitTime(5000);
			click(AMDSearchScreen.objSearchEditBox, "Search box");
			type(AMDSearchScreen.objSearchBoxBar, "Natasaarvabhowma trailer\n", "Search box");
			hideKeyboard();
			waitTime(6000);
			click(AMDSearchScreen.objContentNameInPlayer("Natasaarvabhowma - Trailer"), "Search result");
			waitTime(5000);
			verifyIsElementDisplayed(AMDSearchScreen.objContentNameInPlayer("Natasaarvabhowma - Trailer"));
			waitTime(30000);
			verifyElementExist(AMDHomePage.objGetPremiumPopUP, "PremiumPopUp");
			Swipe("UP", 1);
			click(AMDHomePage.objGetPremiumPopUPProceedButton, "Proceed button");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");

		}

		if (userType.equals("SubscribedUser")) {

			extent.HeaderChildNode("Subscribed user with All Access pack validations");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDHomePage.objMyProfileIcon, "profile icon");

			if (verifyIsElementDisplayed(AMDHomePage.objEditProfile)) {
				logger.info("User is logged in successfully");
				extent.extentLoggerPass("Edit", "User is logged in successfully");
			} else {
				logger.error("User is not logged in successfully");
				extent.extentLoggerFail("Edit", "User is not logged in successfully");
			}
			Back(1);
			click(AMDHomePage.objHomeBtn, "Home tab");
			waitTime(4000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuBtn, "More Menu");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("My Subscription"), "My Subscription");
			verifyElementExist(AMDHomePage.objPackAmount, "Purchased pack details");
			verifyElementExist(AMDHomePage.objCancelRenewal, "Cancel Renewal option");
			verifyElementPresentAndClick(AMDHomePage.objBrowseAllPack, "Browse all packs button");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
			waitTime(2000);
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("Buy Subscription"), "Buy Subscription");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in subscription page");
			verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in subscribe page");
			verifyElementExist(AMDSubscibeScreen.objAdbanner, "Carosual in subscription page");
			verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo code in subscribe page");
			verifyElementPresent(AMDSubscibeScreen.objApply, "Apply button is subscribe page");
			if (verifyIsElementDisplayed(AMDSubscibeScreen.objApplybuttonNotHighlighted)) {
				logger.info("Apply button is not highlighted by default");
				extent.extentLoggerPass("Highlighted", "Apply button is not highlighted by default");
			}

			click(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, "zee5flat5000", "Promo code");
			hideKeyboard();
			click(AMDSubscibeScreen.objApply, "Apply button");
			verifyElementExist(AMDSubscibeScreen.objInvalidPromoCodeText, "Invalid promo code error message");
			Swipe("UP", 1);
			PartialSwipe("UP", 1);
			verifyElementExist(AMDSubscibeScreen.objDescriptionText, "Premium Description in subscribe page");
			verifyElementExist(AMDSubscibeScreen.objPremiumTab, "Premium pack tab");
//			verifyElementExist(AMDSubscibeScreen.objClubTab , "Club pack tab");
			Swipe("UP", 1);
//			verifyElementExist(AMDSubscibeScreen.obj30daysPack, "30 days premium plan tab");
//			verifyElementExist(AMDSubscibeScreen.obj180daysPack, "180 days premium plan tab");
//			verifyElementExist(AMDSubscibeScreen.obj365daysPack, "365 days premium plan tab");
			int size = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).size();
			for (int i = 0; i < size; i++) {
				boolean isDisplayed = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).isDisplayed();
				if (isDisplayed) {
					String pack = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).getText();
					extent.extentLoggerPass("Packs", "Available Pack " + i + " : " + pack);
					logger.info("Available Pack " + i + " : " + pack);
				} else {
					extent.extentLoggerFail("Packs", "No Packs are available");
					logger.info("No Packs are available");
				}
			}
			verifyElementExist(AMDSubscibeScreen.objContinueBtn, "Continue button in subscribe page");
			if (getDriver().findElement(AMDSubscibeScreen.objContinueBtn).isEnabled()) {
				logger.info("Continue button is highlighted");
				extent.extentLoggerPass("Highlighted", "Continue button is highlighted");
			}
			click(AMDSubscibeScreen.objContinueBtn, "Continue button");
			if (checkElementExist(AMDHomePage.objHomeBtn, "Home tab")) {
				logger.info(
						"Subscribed user with all access pack is navigated to home page after tapping on buy subscription continue button");
				extent.extentLoggerPass("Home",
						"Subscribed user with all access pack is navigated to home page after tapping on buy subscription continue button");
			} else {
				logger.error(
						"Subscribed user with all access pack is not navigated to home page after tapping on buy subscription continue button");
				extent.extentLoggerFail("Home",
						"Subscribed user with all access pack is not navigated to home page after tapping on buy subscription continue button");
			}
			click(AMDHomePage.objMoreMenu, "More menu");
			Swipe("UP", 2);
			click(AMDHomePage.objLogout, "Logout");
			click(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
			Swipe("Down", 2);
			click(AMDMoreMenu.objProfile, "Profile");
			extent.HeaderChildNode("RSVOD user Validations");
			LoginWithEmailID(RSVODUser, RSVODPassword);
			waitTime(5000);
			click(AMDHomePage.objSearchBtn, "Search button");
			waitTime(5000);
			click(AMDSearchScreen.objSearchEditBox, "Search box");
			type(AMDSearchScreen.objSearchBoxBar, RSVODContent, "Search field");
			hideKeyboard();
			waitTime(7000);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result premium content");
			waitTime(5000);
			verifyElementExist(AMDSearchScreen.objContentNameInPlayer(RSVODContent), "Content name in player");
			click(AMDHomePage.objGetClubInConsumptionScreen, "Upgrade button");
			verifyElementExist(AMDSearchScreen.objUpgradePopup, "Upgrade popup for RSVOD user");
			verifyElementExist(AMDSearchScreen.objUpgradePopupDescription, "Upgrade popup description");
			logger.info(getText(AMDSearchScreen.objUpgradePopupDescription));
			int size4 = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).size();
			for (int i = 0; i < size4; i++) {
				boolean isDisplayed = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).isDisplayed();
				if (isDisplayed) {
					String pack = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).getText();
					extent.extentLoggerPass("Packs", "Available Pack " + i + " : " + pack);
					logger.info("Available Pack " + i + " : " + pack);
				} else {
					extent.extentLoggerFail("Packs", "No Packs are available");
					logger.info("No Packs are available");
				}
			}
			Swipe("UP", 1);
			PartialSwipe("UP", 1);
			verifyElementExist(AMDSearchScreen.objTermsOfUse, "Terms of use link");
			verifyElementExist(AMDSearchScreen.objPrivacyPolicy, "Privacy policy");
			if (findElement(AMDSearchScreen.objUpgradePopupProceedButton).isEnabled()) {
				logger.info("Proceed button is enebled when user select any pack in upgrade popup");
				extent.extentLoggerPass("Proceed",
						"Proceed button is enebled when user select any pack in upgrade popup");
			}
			click(AMDSearchScreen.objUpgradePopupProceedButton, "Proceed");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Swipe("DOWN", 1);
			verifyElementExist(AMDSearchScreen.objPlanPrice, "Plan price detials in Subscribe page");
			verifyElementExist(AMDSearchScreen.objDiscount, "Discount price detials in Subscribe page");
			verifyElementExist(AMDSearchScreen.objRoundoff, "Round off price detials in Subscribe page");
			verifyElementExist(AMDSearchScreen.objTotalPayableAmount, "Total payable price detials in Subscribe page");
			verifyElementExist(AMDSearchScreen.objAccountInfo, "Account info detials in Subscribe page");
//			Swipe("UP", 1);
//			verifyElementExist(AMDSearchScreen.objYouWillBeChargedInfo, "Recurring amount detials in Subscribe page");
		}
	}

	public void subscribeAllaccessFunctionality() throws Exception {

		click(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for free");

		click(AMDLoginScreen.objEmailIdField, "Email field");
		type(AMDLoginScreen.objEmailIdField, SubscribedUserName, "Email field");
		hideKeyboard();
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		click(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginButton, "Login button");
		waitTime(1000);
		verifyElementExist(AMDHomePage.objHomeBtn, "Home button");
		click(AMDHomePage.objMoreMenu, "More menu");
		click(AMDHomePage.objMyProfileIcon, "profile icon");

		if (checkElementExist(AMDHomePage.objEditProfile, "Edit profile")) {
			logger.info("User is logged in successfully");
			extent.extentLoggerPass("Edit", "User is logged in successfully");
		} else {
			logger.error("User is logged in successfully");
			extent.extentLoggerFail("Edit", "User is logged in successfully");
		}
		Back(1);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("Buy Subscription"), "Buy Subscription");
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
		Swipe("UP", 1);
		waitTime(3000);
		Swipe("UP", 1);
//	verifyElementPresentAndClick(AMDSubscibeScreen.obj365daysPack, "365 days all access pack");
		int size = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).size();
		for (int i = 0; i < size; i++) {
			boolean isDisplayed = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).isDisplayed();
			if (isDisplayed) {
				String pack = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).getText();
				extent.extentLoggerPass("Packs", "Available Pack " + i + " : " + pack);
				logger.info("Available Pack " + i + " : " + pack);
			} else {
				extent.extentLoggerFail("Packs", "No Packs are available");
				logger.info("No Packs are available");
			}
		}
		Swipe("UP", 1);
		click(AMDSubscibeScreen.objContinueBtn, "Continue button");
		if (checkElementExist(AMDHomePage.objHomeBtn, "Home tab")) {
			logger.info(
					"Subscribed user is navigated to home page after tapping on all access plan subscription continue button");
			extent.extentLoggerPass("Home",
					"Subscribed user is navigated to home page after tapping on all access plan subscription continue button");
		} else {
			logger.error(
					"Subscribed user is not navigated to home page after tapping on all access plan subscription continue button");
			extent.extentLoggerFail("Home",
					"Subscribed user is not navigated to home page after tapping on all access plan subscription continue button");
		}
	}

	public void subscribePageValidation() throws Exception {
		System.out.println("\nVerifying Subscribe Page");
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in subscription page");
		verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in subscribe page");
		verifyElementExist(AMDSubscibeScreen.objAdbanner, "Carosual in subscription page");
		verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo code in subscribe page");
		verifyElementPresent(AMDSubscibeScreen.objApply, "Apply button is subscribe page");
		if (verifyIsElementDisplayed(AMDSubscibeScreen.objApplybuttonNotHighlighted)) {
			logger.info("Apply button is highlighted by default");
			extent.extentLoggerPass("Highlighted", "Apply button is highlighted by default");
		} else {
			logger.error("Apply button is not highlighted by default");
			extent.extentLoggerFail("Highlighted", "Apply button is not highlighted by default");
		}
		click(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo");
		type(AMDSubscibeScreen.objApplyPromoCodeTextbox, PromoCode, "Promo code");
		hideKeyboard();
		click(AMDSubscibeScreen.objApply, "Apply button");
		verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeappliedText, "Promo code applied successfully text");
		if (verifyIsElementDisplayed(AMDSubscibeScreen.objApplyPromoCodeappliedText)) {
			logger.info("Discounted price is displayed after promo code is applied");
			extent.extentLoggerPass("Promo", "Discounted price is displayed after promo code is applied");
		} else {
			logger.error("Discounted price is not displayed after promo code is applied");
			extent.extentLoggerFail("Promo", "Discounted price is not displayed after promo code is applied");
		}
		click(AMDSubscibeScreen.objCancelPromoCode, "Cancel promo");
		click(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo");
		type(AMDSubscibeScreen.objApplyPromoCodeTextbox, "zee5flat5000", "Promo code");
		hideKeyboard();
		click(AMDSubscibeScreen.objApply, "Apply button");
		verifyElementExist(AMDSubscibeScreen.objInvalidPromoCodeText, "Invalid promo code error message");
		Swipe("UP", 1);
		verifyElementExist(AMDSubscibeScreen.objDescriptionText, "Premium Description in subscribe page");
		Swipe("DOWN", 1);
		verifyElementExist(AMDSubscibeScreen.objPremiumTab, "Premium  pack tab");
		verifyElementExist(AMDSubscibeScreen.objClubTab, "Club pack tab");
		Swipe("UP", 3);
		waitTime(3000);
		int size = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).size();
		for (int i = 0; i < size; i++) {
			boolean isDisplayed = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).isDisplayed();
			if (isDisplayed) {
				String pack = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).getText();
				extent.extentLoggerPass("Packs", "Available Pack " + i + " : " + pack);
				logger.info("Available Pack " + i + " : " + pack);
			} else {
				extent.extentLoggerFail("Packs", "No Packs are available");
				logger.info("No Packs are available");
			}
		}
//		PartialSwipe("UP", 2);

		verifyElementPresent(AMDSubscibeScreen.objContinueBtn, "Continue button in subscribe page");
		if (getDriver().findElement(AMDSubscibeScreen.objContinueBtn).isEnabled()) {
			logger.info("Continue button is highlighted");
			extent.extentLoggerPass("Highlighted", "Continue button is highlighted");
		}
		Swipe("DOWN", 1);
		click(AMDSubscibeScreen.objClubTab, "Club Pack tab");
		PartialSwipe("UP", 1);
		waitTime(3000);
		int size1 = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).size();
		for (int i = 0; i < size1; i++) {
			boolean isDisplayed = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).isDisplayed();
			if (isDisplayed) {
				String pack = getDriver().findElements(AMDSubscibeScreen.objRSVODPack2).get(i).getText();
				extent.extentLoggerPass("Packs", "Available Pack " + i + " : " + pack);
				logger.info("Available Pack " + i + " : " + pack);
			} else {
				extent.extentLoggerFail("Packs", "No Packs are available");
				logger.info("No Packs are available");
			}
		}
		verifyElementExist(AMDSubscibeScreen.objDescriptionText, "Premium Description in subscribe page");
		Swipe("UP", 1);
		if (getDriver().findElement(AMDSubscibeScreen.objContinueBtn).isEnabled()) {
			logger.info("Continue button is highlighted");
			extent.extentLoggerPass("Highlighted", "Continue button is highlighted");
		}
		click(AMDSubscibeScreen.objContinueBtn, "Continue button");
		verifyElementExist(AMDSubscibeScreen.objAccountInfoHeader, "Account info screen");
		hideKeyboard();// ADDED By Kushal
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header");
		verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in Account info screen");
		verifyElementExist(AMDSubscibeScreen.objSelectedPackDesc, "Selected pack description in account info screen");
		verifyElementExist(AMDSubscibeScreen.objEmailID, "Email id field in Account info screen");
		Swipe("UP", 1);
		verifyElementExist(AMDSubscibeScreen.objORSeperator, "OR seperator in Account info screen");
		verifyElementExist(AMDSubscibeScreen.objFacebookIcon, "FB icon in Account info screen");
		verifyElementExist(AMDSubscibeScreen.objGoogleIcon, "Google icon in Account info screen");
		verifyElementExist(AMDSubscibeScreen.objTwitterIcon, "Twitter icon in Account info screen");
		verifyElementExist(AMDSubscibeScreen.objProceedButtonNothighlighted, "Proceed button dehighlighted by default");
		click(AMDSubscibeScreen.objEmailID, "Email");
		type(AMDSubscibeScreen.objEmailID, RegisteredEmail, "Email field");
		hideKeyboard();
		String Email = getText(AMDSubscibeScreen.objEmailID);
		if (Email != null) {
			logger.info("User can enter email/mobile number in email id field");
			extent.extentLoggerPass("Email", "User can enter email/mobile number in email id field");
		} else {
			logger.error("User can not enter email/mobile number in email id field");
			extent.extentLoggerFail("Email", "User can not enter email/mobile number in email id field");
		}
//		verifyElementExist(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		verifyElementExist(AMDSubscibeScreen.objEnterPassword, "Enter Password PopUp");
	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: BINDU
	 * -------------------------------
	 * 
	 * List of Functions Created - BrowseForFreeAndMobileRegistration() -
	 * VerifyLoginPage() - SearchBox(String userType) -
	 * verifySearchLandingScreen(String userType) - verifySearchOption(String
	 * userType)
	 * =============================================================================
	 * ========
	 */

	public void BrowseForFreeAndMobileRegistration() throws Exception {

		extent.HeaderChildNode("Validating BrowseForFree Button on the intro screen");
		waitTime(5000);
		verifyElementPresent(AMDLoginScreen.objBrowseForFreeBtn, "BrowseForFreee Button");
		waitTime(5000);
		String BrowseForFree = getText(AMDLoginScreen.objBrowseForFreeBtn);
		System.out.println(BrowseForFree);
		if (BrowseForFree.concat("English") != null) {
			logger.info("Browse For Free text is displayed in Selected Language");
			extent.extentLogger("Login/Register Screen", "Browse For Free text is displayed in Selected Language");
		} else {
			logger.info("Browse For Free text is not displayed in Selected Language");
			extent.extentLogger("Login/Register Screen", "Browse For Free text is not displayed in Selected Language");

		}

		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "BrowseForFreee Button");
		waitTime(6000);
		logger.info("User navigated to Login/Register screen tapping on the Browser For Free Button");
		extent.extentLogger("Login/Register Screen",
				"User navigated to Login/Register screen tapping on the Browser For Free Button");

		extent.HeaderChildNode("Validating user navigated to OTP Verification Screen");
		verifyElementPresent(AMDLoginScreen.objEmailIdField, "Email Field");
		click(AMDLoginScreen.objEmailIdField, "Email Field");
		hideKeyboard();
		type(AMDLoginScreen.objEmailIdField, "1234567891", "Email Field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
		waitTime(4000);
		verifyElementPresentAndClick(AMDRegistrationScreen.objFirstNameTxtField, "FirstName Field");
		hideKeyboard();
		type(AMDRegistrationScreen.objFirstNameTxtField, "Zeetest", "FirstName Field");
		verifyElementPresentAndClick(AMDRegistrationScreen.objLastNameTxtField, "LastName Field");
		hideKeyboard();
		type(AMDRegistrationScreen.objLastNameTxtField, "Test", "LastName Field");

		verifyElementPresentAndClick(AMDRegistrationScreen.objDOBTxtField, "Date Of Birth Field");

		// verifyElementPresentAndClick(AMDRegistrationScreen.objDateAccept, "Date Of
		// Birth");

		verifyElementPresentAndClick(AMDRegistrationScreen.objDateSelection, "Date in calender popup");
		click(AMDRegistrationScreen.objOkButtonInCalenderPopUp, "Ok button");

//    verifyElementPresentAndClick(AMDEditProfileScreen.objGenderDropdown, "Gender Dropdown");
//    waitTime(3000);
//    verifyElementPresentAndClick(AMDRegistrationScreen.objFemale, "Female");        
//    waitTime(2000);

		verifyElementPresentAndClick(AMDRegistrationScreen.objGederTxtField, "Gender field");
		verifyElementPresentAndClick(AMDRegistrationScreen.objMale, "Gender male");

		verifyElementPresentAndClick(AMDRegistrationScreen.objPasswordTxtField, "Password Field");
		waitTime(2000);
		hideKeyboard();
		type(AMDRegistrationScreen.objPasswordTxtField, "adcbdefg", "Password Field");
		waitTime(2000);

//    verifyElementPresentAndClick(AMDRegistrationScreen.objRegister, "Register Field");
//    waitTime(6000);
//    verifyElementPresent(AMDRegistrationScreen.objVerifyOTPScreen, "OTP Verificatin Page");

		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(3000);

		logger.info("User navigated to OTP Verification Page");
		extent.extentLogger("OTP Verification Screen", "User navigated to OTP Verification screen");
		otpScenarios();
		Back(1);
		waitTime(3000);
		hideKeyboard();
		Back(1);
		int lenText = findElement(AMDLoginScreen.objEmailIdField).getAttribute("value").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(AMDLoginScreen.objEmailIdField).sendKeys(Keys.BACK_SPACE);
		}
		type(AMDLoginScreen.objEmailIdField, RegisteredMobile, "Mobile number field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		type(AMDLoginScreen.objPasswordField, RegisteredMobilePassword, "Password field");
		verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
		verifyElementExist(AMDHomePage.objHomeTab, "Home tab");

	}

	public void VerifyLoginPage() throws Exception {
		extent.HeaderChildNode(
				"Validating the Navigation to Login or Register Screen Tapping on the Login link available in Menu Screen");
		System.out.println(
				"\nValidating the Navigation to Login or Register Screen Tapping on the Login link available in Menu Screen");

		if (pUserType.equalsIgnoreCase("Guest")) {
			waitTime(2000);
			verifyElementPresent(AMDLoginScreen.objMenu, "Menu icon");
			click(AMDLoginScreen.objMenu, "Menu icon");
			verifyElementPresent(AMDLoginScreen.objProfileIcon, "Login Button");
			click(AMDLoginScreen.objProfileIcon, "Login Button");
			if (verifyIsElementDisplayed(AMDLoginScreen.objLoginPage)) {
				logger.info(
						"User is navigated to Login/register Screen Tapping on the Login link present in the Menu Screen");
				extent.extentLoggerPass("Login/Register Screen",
						"User is navigated to Login/register Screen Tapping on the Login link present in the Menu Screen");
			} else {

				logger.error(
						"User not  navigated to Login/register Screen Tapping on the Login link present in the Menu Screen");
				extent.extentLoggerFail("Login/Register Screen",
						"User not navigated to Login/register Screen Tapping on the Login link present in the Menu Screen");
			}

			extent.HeaderChildNode("Validating UI/UX of Login Page");
			System.out.println("\nValidating UI/UX of Login Page");

			WebElement element = findElement(AMDLoginScreen.objLoginOrRegisterPageTitle);
			int leftX = element.getLocation().getX();
			int rightX = leftX + element.getSize().getWidth();
			int middleX = (rightX + leftX) / 2;
			Dimension size = getDriver().manage().window().getSize();
			if (middleX == Integer.valueOf((size.width) / 2)) {
				logger.info("Login/Register screen title is displayed at center of the screen");
				extent.extentLoggerPass("Screen Title",
						"Login/Register screen title is displayed at center of the screen");
			} else {
				logger.error("Login/Register screen title is not displayed at center of the screen");
				extent.extentLoggerFail("Screen Title",
						"Login/Register screen title is not displayed at center of the screen");
			}

			WebElement elementBackBtn = findElement(AMDLoginScreen.objBackBtn);
			int BackBtnleftX = elementBackBtn.getLocation().getX();
			int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
			int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

			if (BackBtnmiddleX <= 200) {
				logger.info("Back button is displayed at top left of the screen");
				extent.extentLoggerPass("Back button", "Back button is displayed at top left of the screen");
			} else {
				logger.error("Back button is not displayed at top left of the screen");
				extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
			}

			WebElement elementSkipBtn = findElement(AMDLoginScreen.objLoginLnk);
			int SkipBtnRightX = elementSkipBtn.getLocation().getX();
			System.out.println(SkipBtnRightX);
			Dimension sizee = getDriver().manage().window().getSize();
			System.out.println(sizee.getWidth());
			int sizeee = sizee.getWidth() - 300;
			System.out.println(sizeee);

			if (SkipBtnRightX >= sizeee) {
				logger.info("Skip button is displayed at top right of the screen");
				extent.extentLoggerPass("Skip button", "Skip button is displayed at top right of the screen");
			} else {
				logger.error("Skip button is not displayed at top right of the screen");
				extent.extentLoggerFail("Skip button", "Skip button is not displayed at top right of the screen");
			}

			verifyElementPresent(AMDLoginScreen.objGoogleBtn, "Google Button");
			verifyElementPresent(AMDLoginScreen.objfbBtn, "Facebook Button");
			verifyElementPresent(AMDLoginScreen.objtwitterBtn, "Twitter Button");

			extent.HeaderChildNode("Validating EmailID/Mobile No field is displayed on Login/Register screen");
			System.out.println("\nValidating EmailID/Mobile No field is displayed on Login/Register screen");
			if (verifyElementPresent(AMDLoginScreen.objEmailIdField, "Email Field")) {
				logger.info("EmailID/Mobile No. field is dispalyed");
				extent.extentLoggerPass("Login/Register Screen", "EmailID/Mobile No. field is dispalyed");
			} else {
				logger.error("EmailID/Mobile No. field is not dispalyed");
				extent.extentLoggerFail("Login/Register Screen", "EmailID/Mobile No. field is not dispalyed");
			}

			extent.HeaderChildNode("Validating usen can enter EmailID or Mobile NO");
			System.out.println("\nValidating usen can enter EmailID or Mobile NO");
			click(AMDLoginScreen.objEmailIdField, "Email Field");
			hideKeyboard();

			type(AMDLoginScreen.objEmailIdField, "zeetest123@gmail.com", "Email Field");
			if (verifyIsElementDisplayed(AMDLoginScreen.objProceedBtn)) {
				logger.info("Proceed button is displayed , user entered the correct EmailID format");
				extent.extentLoggerPass("Login/Register Screen",
						"Proceed button is displayed , user entered the correct EmailID format");
			} else {
				logger.error("Proceed button is not displayed , user not entered the correct EmailID format");
				extent.extentLogger("Login/Register Screen",
						"Proceed button is not displayed , user not entered the correct EmailID format");

			}

			// Check Proceed Button in highlighted

			if (getAttributValue("clickable", AMDRegistrationScreen.objProceedBtn).equals("true")) {
				logger.info("Proceed CTA is displayed and is highlated");
				extent.extentLoggerPass("Proceed button", "Proceed CTA is displayed and is highlighted");
			} else {
				logger.error("Proceed CTA is not activated");
				extent.extentLoggerFail("Proceed button", "Proceed CTA is not activated");
			}

			extent.HeaderChildNode("Validating UI/UX of Login Page post changing the Display Language");
			System.out.println("\nValidating UI/UX of Login Page post changing the Display Language");
			waitTime(4000);
			click(AMDLoginScreen.objBackBtn, "Back Button");
			click(AMDLoginScreen.objSettings, "Settings Button");
			SwipeUntilFindElement(AMDLoginScreen.objDisplayLang, "Up");
			click(AMDLoginScreen.objDisplayLang, "Display Language");
			waitTime(3000);
			click(AMDLoginScreen.objLangHindi, "Display Language Hindi");
			click(AMDLoginScreen.objLanguageContinueBtn, "Display Language Continue");
			waitTime(2000);
			click(AMDLoginScreen.objBackBtn, "Back Button");
			waitTime(6000);
			click(AMDLoginScreen.objMenuHindi, "Menu icon");
			click(AMDLoginScreen.objProfileIcon, "Profile Icon");
			waitTime(3000);

			verifyElementPresent(AMDLoginScreen.objLoginTextChanged, "Login Text in other language");

			Dimension size1 = getDriver().manage().window().getSize();
			if (middleX == Integer.valueOf((size1.width) / 2)) {
				logger.info("Login/Register screen title is displayed at center of the screen");
				extent.extentLoggerPass("Screen Title",
						"Login/Register screen title is displayed at center of the screen");
			} else {
				logger.error("Login/Register screen title is not displayed at center of the screen");
				extent.extentLoggerFail("Screen Title",
						"Login/Register screen title is not displayed at center of the screen");
			}

			WebElement elementBackBtn1 = findElement(AMDLoginScreen.objBackBtn);
			int BackBtnleftX1 = elementBackBtn1.getLocation().getX();
			int BAckBtnrightX1 = BackBtnleftX1 + elementBackBtn1.getSize().getWidth();
			int BackBtnmiddleX1 = (BAckBtnrightX1 + BackBtnleftX1) / 2;

			if (BackBtnmiddleX1 <= 200) {
				logger.info("Back button is displayed at top left of the screen");
				extent.extentLoggerPass("Back button", "Back button is displayed at top left of the screen");
			} else {
				logger.error("Back button is not displayed at top left of the screen");
				extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
			}

			WebElement elementSkipBtn1 = findElement(AMDLoginScreen.objLoginLnk);
			int SkipBtnRightX1 = elementSkipBtn1.getLocation().getX();
			System.out.println(SkipBtnRightX1);
			Dimension sizee1 = getDriver().manage().window().getSize();
			System.out.println(sizee1.getWidth());
			int sizeee1 = sizee1.getWidth() - 300;
			System.out.println(sizeee1);

			if (SkipBtnRightX1 >= sizeee1) {
				logger.info("Skip button is displayed at top right of the screen");
				extent.extentLoggerPass("Skip button", "Skip button is displayed at top right of the screen");
			} else {
				logger.error("Skip button is not displayed at top right of the screen");
				extent.extentLoggerFail("Skip button", "Skip button is not displayed at top right of the screen");
			}
			verifyElementPresent(AMDLoginScreen.objGoogleBtn, "Google Button");
			verifyElementPresent(AMDLoginScreen.objfbBtn, "Facebook Button");
			verifyElementPresent(AMDLoginScreen.objtwitterBtn, "Twitter Button");

			click(AMDLoginScreen.objBackBtn, "Back Button");
			click(AMDLoginScreen.objSettingsHindi, "Settings Button");
			SwipeUntilFindElement(AMDLoginScreen.objDisplayLangHindi, "Up");
			click(AMDLoginScreen.objDisplayLangHindi, "Display Language");
			waitTime(3000);
			click(AMDLoginScreen.objLangEnglish, "Display Language English");
			click(AMDLoginScreen.objLanguageContinueBtn, "Display Language Continue");
			waitTime(2000);
			click(AMDLoginScreen.objBackBtn, "Back Button");
		} else {
			logger.info("Login/Register Screen is NOT applicable for " + pUserType);
			extent.extentLoggerPass("Login/Register Screen",
					"Login/Register Screen is NOT applicable for " + pUserType);
		}
	}

	public void SearchBox(String userType) throws Exception {
		extent.HeaderChildNode("validating the UI/UX of search Landing screen");
		System.out.println("\nValidating the UI/UX of search Landing screen");
		verifyElementPresent(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		waitTime(6000);

		verifyElementExist(AMDSearchScreen.objHomeOption, "Bottom bar Home Option");
		verifyElementExist(AMDSearchScreen.objUpcomingOption, "Bottom bar Upcoming Option");
		verifyElementExist(AMDSearchScreen.objDownloadsOption, "Bottom bar Downloads Option");
		verifyElementExist(AMDSearchScreen.objMoreOption, "Bottom bar More Option");

		WebElement elementBackBtn = findElement(AMDLoginScreen.objBackBtn);
		int BackBtnleftX = elementBackBtn.getLocation().getX();
		int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
		int BackBtnmiddleX1 = (BAckBtnrightX + BackBtnleftX) / 2;

		if (BackBtnmiddleX1 <= 200) {
			logger.info("Back button is displayed at top left of the screen");
			extent.extentLoggerPass("Back button", "Back button is displayed at top left of the screen");
		} else {
			logger.error("Back button is not displayed at top left of the screen");
			extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
		}

		WebElement elementVoiceIcon = findElement(AMDSearchScreen.objVoiceicon);
		int VoiceIconRightX = elementVoiceIcon.getLocation().getX();
		System.out.println(VoiceIconRightX);
		Dimension sizee = getDriver().manage().window().getSize();
//		System.out.println(sizee.getWidth());
		int sizeee = sizee.getWidth() - 300;
		System.out.println(sizee.getWidth() + " X " + sizeee);

		if (VoiceIconRightX >= sizeee) {
			logger.info("Microphone search icon is displayed at top right of the Search screen");
			extent.extentLoggerPass("Microphone icon",
					"Microphone search icon is displayed at top right of the Search screen");
		} else {
			logger.error("Microphone icon is not displayed at top right of the Search screen");
			extent.extentLoggerFail("Microphone icon",
					"Microphone icon is not displayed at top right of the Search screen");
		}

		click(AMDSearchScreen.objVoiceicon, "Microphone Icon");
		// wait(2000);
		String MicrophoneAccessPopup = getDriver().findElement(AMDSearchScreen.objMicroPhone).getText();
		System.out.println(MicrophoneAccessPopup);

		if (MicrophoneAccessPopup.equalsIgnoreCase(
				"ZEE5 requires your permission to access your device's microphone to enable voice search. The voice data is not stored with ZEE5.")) {
			logger.info("Device Microphone access permission pop up is displayed");
			extent.extentLoggerPass("Voice Search Icon", "Device Microphone access permission pop up is displayed");
		} else {
			logger.error("Device Microphone access permission pop up is not displayed");
			extent.extentLoggerFail("Voice Search icon", "Device Microphone access permission pop up is not displayed");
		}

		verifyElementExist(AMDSearchScreen.objVoiceSearchBackButton, "Back button");
		click(AMDSearchScreen.objVoiceSearchBackButton, "Back button");

		waitTime(2000);

		verifyElementExist(AMDSearchScreen.objsearchBox, "Search Box");
		logger.info("Search box is available on top section of search landing screen");
		extent.extentLoggerPass("Search box", "Search box is available on top section of search landing screen");

		String SearchBoxText = getDriver().findElement(AMDSearchScreen.objsearchBox).getText();
		logger.info(SearchBoxText);

		if (SearchBoxText.equalsIgnoreCase("Search for Movies, Shows, Channels etc.")) {
			logger.info("Water Marked Text is displayed by default in Search Box");
			extent.extentLoggerPass("Search box", "Water Marked Text is displayed by default in Search Box");
		} else {
			logger.error("Water Marked Text is not displayed by default in Search Box");
			extent.extentLoggerFail("Search Box", "Water Marked Text is not displayed by default in Search Box");
		}

		verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");
		if (verifyElementPresent(AMDLoginScreen.objHomeTab, "Home Tab")) {
			logger.info("User navigated to previous screen on tapping of back button available on the search screen");
			extent.extentLoggerPass("Previous screen",
					"User navigated to previous screen on tapping of back button available on the search screen");
		} else {
			logger.error(
					"User is not navigated to previous screen on tapping of back button available on the search screen");
			extent.extentLoggerFail("Previous screen",
					"User is not navigated to previous screen on tapping of back button available on the search screen");
		}

		if (userType.equalsIgnoreCase("Guest")) {

			selectContentLang_MoreMenu("Hindi");
		}

		verifyElementPresent(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchIcon, "Search icon");

		waitTime(2000);
		if (checkElementExist(AMDSearchScreen.objTrendingSearchOverlay, "Trending Search Overlay")) {
			logger.info("Trending search overlay is displayed");
			extent.extentLoggerPass("Search screen", "Trending search overlay is displayed");
		} else {
			logger.info("Trending search overlay is not displayed");
			extent.extentLoggerFail("Search screen", "Trending search overlay is not displayed");
		}
		waitTime(2000);
		if (checkElementExist(AMDSearchScreen.objTopSearchOverlay, "Top search Overlay")) {
			logger.info("Top search overlay is displayed");
			extent.extentLoggerPass("Search screen", "Top search overlay is displayed");
		} else {
			logger.info("Top search overlay is not displayed");
			extent.extentLoggerFail("Search screen", "Top search overlay is not displayed");
		}
		if (userType.equals("Guest")) {
			deselectContentLang_MoreMenuAndSelectDefaultLanguage("Hindi");
		}

//		click(AMDSearchScreen.objSearchEditBox, "Search box");
//		if(verifyElementExist(AMDSearchScreen.objVirtualKeyboard, "Keyboard"))
//		{
//			logger.info("Keyboard is displayed");
//			extent.extentLogger("Search screen","Keyboard is displayed");
//		}
//		else
//		{
//			logger.error("Keyboard is not displayed");
//			extent.extentLoggerFail("Search Screen", "Keyboard is not displayed");
//			
//		}
//		
//		hideKeyboard();	
		click(AMDSearchScreen.objHomeOption, "Bottom bar Home Option");
	}

	public void verifySearchLandingScreen(String userType) throws Exception {
		extent.HeaderChildNode("Validating " + userType + " user navigates to Search landing screen");
		System.out.println("\nValidating " + userType + "user navigates to Search landing screen");
		waitTime(10000);
		verifyElementPresent(AMDLoginScreen.objHomeTab, "Home Tab");
		boolean liveTV = false;

		int noOfTabs = getCount(AMDHomePage.objTitle);
		for (int i = 1; i <= 10; i++) {
			String tabName = null;
			if (i == noOfTabs) {
				if (!liveTV) {
					i = noOfTabs - 1;
				}
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				tabName = eleTab.getText();
				System.out.println(tabName);
				eleTab.click();

			} else {
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				tabName = eleTab.getText();
				System.out.println(tabName);
				eleTab.click();
			}

			waitTime(2000);

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLoggerPass("Search Icon", tabName + " tab is displayed and clicked on " + tabName + " tab");

			verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
			waitTime(2000);

			if (verifyElementDisplayed(AMDSearchScreen.objSearchEditBox)) {
				logger.info("User navigated to Search Landing screen");
				extent.extentLoggerPass("Search Screen", "User navigated to Search Landing screen");
			} else {
				logger.error("User not navigated to Search Landing screen");
				extent.extentLoggerFail("Search Screen", "User not navigated to Search Landing screen");
			}

			click(AMDLoginScreen.objBackBtn, "Back Button");
			waitTime(2000);

			if (liveTV) {
				break;
			}
			if (tabName.equalsIgnoreCase("Live TV")) {
				liveTV = true;
			}
		}
		click(AMDSearchScreen.objHomeOption, "Bottom bar Home Option");

	}

	public void verifySearchOption(String userType) throws Exception {
		extent.HeaderChildNode("Verify Search Icon on Landing pages as : " + userType);
		System.out.println("\nVerify Search Icon on Landing pages as " + userType);
		waitTime(10000);
		verifyElementPresent(AMDLoginScreen.objHomeTab, "Home Tab");
		boolean liveTV = false;

		int noOfTabs = getCount(AMDHomePage.objTitle);
		System.out.println("HOME PAGE HEADERS: " + noOfTabs);
		for (int i = 1; i <= 10; i++) {

			String tabName = null;
			if (i == noOfTabs) {
				if (!liveTV) {
					i = noOfTabs - 1;
				}
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				tabName = eleTab.getText();
				System.out.println(tabName);
				eleTab.click();

			} else {
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				tabName = eleTab.getText();
				System.out.println(tabName);
				eleTab.click();
			}

			waitTime(2000);

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLoggerPass("Search Icon", tabName + " tab is displayed and clicked on " + tabName + " tab");

			WebElement elementSearchIcon = findElement(AMDSearchScreen.objSearchIcon);
			int SearchIconRightX = elementSearchIcon.getLocation().getX();
			System.out.println(SearchIconRightX);
			Dimension sizee = getDriver().manage().window().getSize();
//			System.out.println(sizee.getWidth());
			int sizeee = sizee.getWidth() - 300;
			System.out.println(sizee.getWidth() + " X " + sizeee);

			if (SearchIconRightX >= sizeee) {
				logger.info("Search icon is displayed at top right of the " + tabName + " tab ");
				extent.extentLoggerPass("Search icon",
						"Search icon is displayed at top right of the " + tabName + " tab ");
			} else {
				logger.error("Search icon is not displayed at top right of the " + tabName + " tab ");
				extent.extentLoggerFail("Search icon",
						"Search icon is not displayed at top right of the " + tabName + " tab ");
			}
			if (liveTV) {
				break;
			}
			if (tabName.equalsIgnoreCase("Live TV")) {
				liveTV = true;
			}

		}

//		verifyElementPresent(AMDLoginScreen.objMenu, "Menu icon");
//		click(AMDLoginScreen.objMenu, "Menu icon");
//		
//       if(verifyElementExist(AMDSearchScreen.objSearchIcon, "Search Icon"))
//       {
//        
//        logger.info("Search icon is displayed at top right of the More Screen");
//		extent.extentLogger("Search icon", "Search icon is displayed at top right of the More Screen");
//       }
//       else
//       {
//    	   logger.info("Search icon is not displayed at top right of the More Screen");
//			   extent.extentLoggerFail("Search icon", "Search icon is not displayed at top right of the More Screen");
//       }
//		
		click(AMDSearchScreen.objHomeOption, "Bottom bar Home Option");

	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: SUSHMA
	 * -------------------------------
	 * 
	 * List of Functions Created - DisplayLanguagePopUp(String userType, String
	 * displaylanguage1, String displaylanguage2) -
	 * DisplayLanguagePopUpValidation(String, String) - loginOrRegisterScreen(String
	 * inValidPhnNo, String validPhnNo, String loginThrough, String userType) -
	 * loginScreen(String validPassword) - OtpScreen(String otp1, String otp2,
	 * String otp3, String otp4) - TopSearches() -
	 * =============================================================================
	 * ========
	 */

	public void DisplayLanguagePopUpValidation(String displayLanguageSelection1, String displayLanguageSelection2)
			throws Exception {

		extent.HeaderChildNode("Display Language PopUp Validation");

		verifyElementPresent(AMDLoginScreen.objDisplayLanguageScreenTitle, "Display language screen Header");
		verifyElementPresent(AMDLoginScreen.objPageTitle, "Display language page title");
		verifyElementPresent(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");
		verifyElementPresent(AMDOnboardingScreen.objBackBtn, "Back button");
		verifyElementPresent(AMDLoginScreen.objSelectedDisplayLanguage, "Selected display language");
		verifyElementPresent(AMDLoginScreen.objSelectedDisplayLanguage,
				"Tick mark is appeared for selected display language");
		String selectedlanguage = getText(AMDLoginScreen.objSelectedDisplayLanguage);
		logger.info(selectedlanguage + " language is selected by Default");
		extentLoggerPass("Selected language", selectedlanguage + " language is selected by Default");

		click(AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection2), "language");
		verifyElementPresent(AMDLoginScreen.objSelectedDisplayLanguage, "Selected display language");
		int totalSelectedLanguages = getDriver().findElements(AMDLoginScreen.objSelectedDisplayLanguage).size();

		if (totalSelectedLanguages == 1) {
			logger.info("User is able to select only one language from the display language list");
			extentLoggerPass("Select one language",
					"User is able to select only one language from the display language list");
		}

		click(AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1), "English language");

		String pos1 = getAttributValue("bounds", AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1));
		String pos2 = null;

		HashSet<String> h = new HashSet<String>();

		for (int i = 0; i < 3; i++) {
			int totallangs = getDriver().findElements(By.xpath("//*[@id='display_language_content']")).size();
			for (int j = 1; j <= totallangs; j++) {
				String lang = getDriver().findElement(By.xpath("(//*[@id='display_language_content'])[" + j + "]"))
						.getText();
				h.add(lang);
			}
			Swipe("UP", 1);
			pos2 = getAttributValue("bounds", AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1));
		}
		System.out.println(h.size());
		if (h.size() == 11) {
			logger.info("Display language screen is displayed with all the display languages");
			extentLoggerPass("Display Languages",
					"Display language screen is displayed with all the display languages");
		} else {
			logger.info("Display language screen is not displayed with all the display languages");
			extentLoggerFail("Display Languages",
					"Display language screen is not displayed with all the display languages");
		}
		if (pos1 != pos2) {
			logger.info("User is able to scroll up and down in the language list");
			extentLoggerPass("Swipe", "User is able to scroll up and down in the language list");
		} else {
			logger.info("User is not able to scroll up and down in the language list");
			extentLoggerFail("Swipe", "User is not able to scroll up and down in the language list");
		}

		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");
		verifyElementPresent(AMDLoginScreen.objContentLanguageScreenTitle, "Content Language screen");
		Back(1);
//	verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn, "Content Language PopUp Continue button");
//	relaunch();
//	if(getDriver().findElement(AMDOnboardingScreen.objBrowseForFreeBtn).isDisplayed())
//	{
//		logger.info("Display langauge screen is displayed only when user launch the app for the first time");
//		extentLogger("Display language", "Display langauge screen is displayed only when user launch the app for the first time");
//	}
	}

	public void loginOrRegisterScreen(String inValidPhnNo, String validPhnNo, String loginThrough, String userType)
			throws Exception {
		navigateToRegisterScreen(loginThrough);
		extent.HeaderChildNode("Validating Login/Registration screen");
		System.out.println("\nValidating Login/Registration screen");
		if (userType.equalsIgnoreCase("Guest")) {
			WebElement element = findElement(AMDLoginScreen.objLoginOrRegisterPageTitle);
			int leftX = element.getLocation().getX();
			int rightX = leftX + element.getSize().getWidth();
			int middleX = (rightX + leftX) / 2;
			Dimension size = getDriver().manage().window().getSize();
			if (middleX == Integer.valueOf((size.width) / 2)) {
				logger.info("Login/Register screen title is displayed at center of the screen");
				extent.extentLoggerPass("Screen Title",
						"Login/Register screen title is displayed at center of the screen");
			} else {
				logger.error("Login/Register screen title is not displayed at center of the screen");
				extent.extentLoggerFail("Screen Title",
						"Login/Register screen title is not displayed at center of the screen");
			}

			WebElement elementBackBtn = findElement(AMDLoginScreen.objBackBtn);
			int BackBtnleftX = elementBackBtn.getLocation().getX();
			int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
			int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

			if (BackBtnmiddleX <= 200) {
				logger.info("Back button is displayed at top left of the screen");
				extent.extentLoggerPass("Back button", "Back button is displayed at top left of the screen");
			} else {
				logger.error("Back button is not displayed at top left of the screen");
				extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
			}

			WebElement elementSkipBtn = findElement(AMDLoginScreen.objLoginLnk);
			int SkipBtnRightX = elementSkipBtn.getLocation().getX();
			System.out.println(SkipBtnRightX);
			Dimension sizee = getDriver().manage().window().getSize();
			System.out.println(sizee.getWidth());
			int sizeee = sizee.getWidth() - 300;
			System.out.println(sizeee);

			if (SkipBtnRightX >= sizeee) {
				logger.info("Skip button is displayed at top right of the screen");
				extent.extentLoggerPass("Skip button", "Skip button is displayed at top right of the screen");
			} else {
				logger.error("Skip button is not displayed at top right of the screen");
				extent.extentLoggerFail("Skip button", "Skip button is not displayed at top right of the screen");
			}

			verifyElementPresent(AMDLoginScreen.objGoogleBtn, "Goole icon");
			verifyElementPresent(AMDLoginScreen.objfbBtn, "Facebook icon");
			verifyElementPresent(AMDLoginScreen.objtwitterBtn, "Twitter icon");

			type(AMDLoginScreen.objEmailIdField, inValidPhnNo, "Email Id or Mobile Number field");
			hideKeyboard();
			verifyElementPresent(AMDLoginScreen.objErrorTxt, "Invalid Mobile number error message");

//			int ele=findElements(AMDLoginScreen.objProceedBtn).size();
			if (verifyElementPresent(AMDLoginScreen.objContinueWithTxt, "Continue with social login")) {
				logger.info("Proceed button is not displayed when user enters invalid mobile number");
				extentLoggerPass("Proceed button",
						"Proceed button is not displayed when user enters invalid mobile number");
			}
			clearField(AMDLoginScreen.objEmailIdField, "Email Id");

			type(AMDLoginScreen.objEmailIdField, validPhnNo, "Email Id or Mobile Number field");
			hideKeyboard();
			verifyElementPresent(AMDLoginScreen.objProceedBtn, "Proceed button");

			boolean proceedbtn = getDriver().findElement(AMDLoginScreen.objProceedBtn).isEnabled();

			if (proceedbtn == true) {
				logger.info("Proceed button is highlighted when user enter valid Mobile number");
				extentLoggerPass("ProceedButton", "Proceed button is highlighted when user enter valid Mobile number");
			}
			click(AMDLoginScreen.objProceedBtn, "Proceed button");
			if (verifyIsElementDisplayed(AMDLoginScreen.objLoginScreenTitle)) {
				logger.info("Proceed button is functional");
				extentLoggerPass("Proceed button functionality", "Proceed button is functional");
			} else if (verifyElementDisplayed(AMDLoginScreen.objRegistrationScreenTitle)) {
				logger.info("Proceed button is functional");
				extentLoggerPass("Proceed button functionality", "Proceed button is functional");
			} else {
				logger.info("Proceed button is not functional");
				extentLoggerFail("Proceed button functionality", "Proceed button is not functional");
			}
		} else {
			logger.info("Validation of Login/Registration screen is NOT applicable for " + userType);
			extentLoggerPass("Login/Registration",
					"Validation of Login/Registration screen is NOT applicable for " + userType);
		}
	}

	public void loginScreen(String validPassword) throws Exception {
		extent.HeaderChildNode("Login screen");
		verifyElementPresent(AMDLoginScreen.objPasswordField, "Password field");

		WebElement element = findElement(AMDLoginScreen.objLoginScreenTitle);
		int leftX = element.getLocation().getX();
		int rightX = leftX + element.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		Dimension size = getDriver().manage().window().getSize();
		if (middleX == Integer.valueOf((size.width) / 2)) {
			logger.info("Login screen title is displayed at center of the screen");
			extent.extentLoggerPass("Screen Title", "Login screen title is displayed at center of the screen");
		} else {
			logger.error("Login screen title is not displayed at center of the screen");
			extent.extentLoggerFail("Screen Title", "Login screen title is not displayed at center of the screen");
		}

		WebElement elementBackBtn = findElement(AMDLoginScreen.objBackBtn);
		int BackBtnleftX = elementBackBtn.getLocation().getX();
		int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
		int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

		if (BackBtnmiddleX <= 200) {
			logger.info("Back button is displayed at top left of the screen");
			extent.extentLoggerPass("Back button", "Back button is displayed at top left of the screen");
		} else {
			logger.error("Back button is not displayed at top left of the screen");
			extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
		}

		click(AMDLoginScreen.objBackBtn, "Back button");
		verifyElementPresent(AMDLoginScreen.objLoginOrRegisterPageTitle, "Login/Register screen title");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");

		String cursorAvailability = getAttributValue("focused", AMDLoginScreen.objPasswordField);
		if (cursorAvailability.equalsIgnoreCase("true")) {
			logger.info("Cursor is displayed on the Password field by default");
			extentLoggerPass("Cursor", "Cursor is displayed on the Password field by default");
		} else {
			logger.info("Cursor is not displayed on the Password field by default");
			extentLoggerFail("Cursor", "Cursor is not displayed on the Password field by default");
		}

		String showpassword = getAttributValue("checked", AMDLoginScreen.objShowPwdBtn);
		if (showpassword.equalsIgnoreCase("false")) {
			logger.info("Password field text is hidden by default");
			extentLoggerPass("Password hidden", "Password field text is hidden by default");
		} else {
			logger.info("Password field text is not hidden by default");
			extentLoggerFail("Password hidden", "Password field text is not hidden by default");
		}

		if (!(findElement(AMDLoginScreen.objLoginBtn).isEnabled())) {

			logger.info("Login CTA is displayed and is dehighlighted by default");
			extentLoggerPass("Login button", "Login CTA is displayed and is dehighlighted by default");
		} else {
			logger.info("Login CTA is displayed and is not dehighlighted by default");
			extentLoggerFail("Login button", "Login CTA is displayed and is not dehighlighted by default");
		}

		verifyElementPresentAndClick(AMDLoginScreen.objForgetPwdBtn, "Forgot password link");
		verifyElementPresent(AMDLoginScreen.objForgotPasswordScreenTitle, "Forfot Password screen");
		click(AMDLoginScreen.objBackBtn, "Back button");

		type(AMDLoginScreen.objPasswordField, validPassword, "Password field");
//		hideKeyboard();
		click(AMDLoginScreen.objShowPwdBtn, "Show password icon");

//		findElement(
//				By.xpath("//*[@resource-id='com.graymatrix.did:id/text_input_password_toggle' and @checked='true']"));

		findElement(By.xpath("//*[@id='text_input_end_icon' and @contentDescription ='Show password']"));

		logger.info("User can hide or unhide password using eye icon");
		extentLoggerPass("Password field", "User can hide or unhide password using eye icon");

		click(AMDLoginScreen.objShowPwdBtn, "Show password icon");
		hideKeyboard();
		String password = getText(AMDLoginScreen.objPasswordField);
		if (password.length() >= 6) {
			logger.info("Password field accepts minimum of six characters");
			extentLoggerPass("Password field", "Password field accepts minimum of six characters");

			if (getDriver().findElement(AMDLoginScreen.objLoginBtn).isEnabled()) {
				logger.info("Login button is highlighted when user enters a minimum of 6 characters in password field");
				extentLoggerPass("Login button",
						"the Login button is highlighted when user enters a minimum of 6 characters in password field");
			} else {
				logger.error(
						"Login button is not highlighted when user enters a minimum of 6 characters in password field");
				extentLoggerFail("Login button",
						"the Login button is not highlighted when user enters a minimum of 6 characters in password field");
			}
		} else {
			logger.info("Password field is not accepting minimum of six characters");
			extentLoggerFail("Password field", "Password field is not accepting minimum of six characters");
		}
		hideKeyboard();
		click(AMDGenericObjects.objText("OR"), "OR text");
	}

	public void OtpScreen(String otp1, String otp2, String otp3, String otp4) throws Exception {
		extent.HeaderChildNode("Otp screen Validation");
		verifyElementPresentAndClick(AMDLoginScreen.objLoginWithOTPLink, "Login with OTP Link");

		WebElement element = findElement(AMDLoginScreen.objOtpScreenTitle);
		int leftX = element.getLocation().getX();
		int rightX = leftX + element.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		Dimension size = getDriver().manage().window().getSize();
		if (middleX == Integer.valueOf((size.width) / 2)) {
			logger.info("Verify mobile screen title is displayed at center of the screen");
			extent.extentLoggerPass("Screen Title", "Verify mobile screen title is displayed at center of the screen");
		} else {
			logger.error("Verify mobile screen title is not displayed at center of the screen");
			extent.extentLoggerFail("Screen Title",
					"Verify mobile screen title is not displayed at center of the screen");
		}

		WebElement elementBackBtn = findElement(AMDLoginScreen.objBackBtn);
		int BackBtnleftX = elementBackBtn.getLocation().getX();
		int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
		int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

		if (BackBtnmiddleX <= 200) {
			logger.info("Back button is displayed at top left of the screen");
			extent.extentLoggerPass("Back button", "Back button is displayed at top left of the screen");
		} else {
			logger.error("Back button is not displayed at top left of the screen");
			extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
		}

		String cursorAvailability = getAttributValue("focused", AMDLoginScreen.objOtpEditBox1);
		if (cursorAvailability.equalsIgnoreCase("true")) {
			logger.info("Cursor is displayed on the first otp field by default");
			extentLoggerPass("Cursor", "Cursor is displayed on the first otp field by default");
		} else {
			logger.info("Cursor is not displayed on the first otp field by default");
			extentLoggerFail("Cursor", "Cursor is not displayed on the first otp field by default");
		}
		click(AMDRegistrationScreen.objOTPField1, "First otp field");
		hideKeyboard();

		if (!(getDriver().findElement(AMDLoginScreen.objVerifyBtn).isEnabled())) {
			logger.info("Verify CTA is dehighlighted by default");
			extentLoggerPass("Verify button", "Verify CTA is dehighlighted by default");
		} else {
			logger.info("Verify CTA is not dehighlighted by default");
			extentLoggerFail("Verify button", "Verify CTA is not dehighlighted by default");
		}

		if (getDriver().findElement(AMDLoginScreen.objResendOtpLink).isDisplayed()) {
			logger.info("Didn't get OTP text is displayed with Resend CTA");
			extentLoggerPass("Resend button", "Didn't get OTP text is displayed with Resend CTA");
		} else {
			logger.info("Didn't get OTP text is not displayed with Resend CTA");
			extentLoggerFail("Resend button", "Didn't get OTP text is not displayed with Resend CTA");
		}

		if (getDriver().findElement(AMDLoginScreen.objOtp).isDisplayed()) {
			if (verifyIsElementDisplayed(AMDLoginScreen.objCountDownTimer)) {
				logger.info("OTP is sent to the registered mobile number");
				extent.extentLoggerPass("Otp", "OTP is sent to the registered mobile number");
			}

		}

		type(AMDLoginScreen.objOtpEditBox1, otp1, "first otp field");
		hideKeyboard();
		if (getDriver().findElement(AMDLoginScreen.objVerifyBtn).isEnabled() == false) {
			logger.info("Verify CTA is not highlighted");
			extentLoggerPass("Verify button", "Verify CTA is not highlighted");
		}

		type(AMDLoginScreen.objOtpEditBox2, otp2, "second otp field");
		hideKeyboard();
		if (getDriver().findElement(AMDLoginScreen.objVerifyBtn).isEnabled() == false) {
			logger.info("Verify CTA is not highlighted");
			extentLoggerPass("Verify button", "Verify CTA is not highlighted");
		}

		type(AMDLoginScreen.objOtpEditBox3, otp3, "third otp field");
		hideKeyboard();
		if (getDriver().findElement(AMDLoginScreen.objVerifyBtn).isEnabled() == false) {
			logger.info("Verify CTA is not highlighted");
			extentLoggerPass("Verify button", "Verify CTA is not highlighted");
		}

		type(AMDLoginScreen.objOtpEditBox4, otp4, "fourth otp field");
		hideKeyboard();
		if (getDriver().findElement(AMDLoginScreen.objVerifyBtn).isEnabled()) {
			logger.info("Verify CTA is highlighted");
			extentLoggerPass("Verify button", "Verify CTA is highlighted");
		} else {
			logger.info("Verify CTA is not highlighted");
			extentLoggerFail("Verify button", "Verify CTA is not highlighted");
		}

		click(AMDLoginScreen.objBackBtn, "Back button");
		verifyElementPresent(AMDLoginScreen.objLoginScreenTitle, "Login screen title");
		Back(1);
	}

	public void TopSearches(String usertype) throws Exception {
		extent.HeaderChildNode("Top Searches module");
		waitTime(5000);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search icon");
		if (usertype.equalsIgnoreCase("NonSubscribedUser") || usertype.equalsIgnoreCase("SubscribedUser")) {

			int noOfTrays = findElements(AMDSearchScreen.objNoOftraysInSearchpage).size();
			System.out.println(noOfTrays);
			boolean TopSearchFound = false;
			for (int i = 1; i <= noOfTrays; i++) {
				String traytitle = getDriver()
						.findElement(
								By.xpath("(//*[@resource-id='com.graymatrix.did:id/header_primary_text'])[" + i + "]"))
						.getText();
//				String traytitle = getDriver()
//						.findElement(
//								By.xpath("(//*[@resource-id='com.graymatrix.did:id/header_primary_text'])[3]"))
//						.getText();
//				System.out.println(traytitle);

				if (traytitle.equalsIgnoreCase("Top Searches")) {
					TopSearchFound = true;
					if (checkElementExist(AMDSearchScreen.objTopSearches, "Top searches tray")) {
						logger.info("Top searches section is displayed below Trending searches carousel");
						extent.extentLoggerPass("Search Screen",
								"Top searches section is displayed below Trending searches carousel");
					} else {
						logger.info("Top searches section is not displayed below Trending searches carousel");
						extent.extentLoggerFail("Search Screen",
								"Top searches section is not displayed below Trending searches carousel");
					}

//					checkElementExist(AMDSearchScreen.objContentCardTitleOfTopSearchesTray,
//							"Content card title of Top searches tray");

					// getText(AMDSearchScreen.objContentCardTitleOfTopSearchesTray);

//					click(AMDSearchScreen.objContentCardTitleOfTopSearchesTray, "Content card of Top searches tray");
//					waitForElementDisplayed(AMDSearchScreen.objConsumptionScreenTitle, 10);
//					
//					verifyElementPresent(AMDSearchScreen.objConsumptionScreenTitle, "Title in Consumption screen");
//					
//
//						String consumptionScreenTitle = getText(AMDSearchScreen.objConsumptionScreenTitle);
//						if(contentCardTitleofTopSearches.equalsIgnoreCase(consumptionScreenTitle))
//						{
//						    logger.info("user navigated to respective consumption/Landing screen post tapping on any Top searches carousel");	
//						    extent.extentLogger("Title", "user navigated to respective consumption/Landing screen post tapping on any Top searches carousel");
//						}
//						else
//						{
//							logger.info("user is not navigated to respective consumption/Landing screen post tapping on any Top searches carousel");	
//						    extent.extentLoggerFail("Title", "user is not navigated to respective consumption/Landing screen post tapping on any Top searches carousel");
//						}

					break;
				}
			}
			if (TopSearchFound == false) {
				logger.error("Top searches is not displayed");
				extentLoggerFail("Top searches tray", "Top searches is not displayed");
			}
			Back(1);
		} else {
			Back(1);
			selectContentLang_MoreMenu("Hindi");
		}
	}

	public void TrendingSearches() throws Exception {
		extent.HeaderChildNode("Trending Searches module");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search icon");
		waitTime(5000);
		int noOfTrays = findElements(AMDSearchScreen.objNoOftraysInSearchpage).size();
		System.out.println(noOfTrays);
		boolean TrendingSearchFound = false;
		for (int i = 1; i <= noOfTrays; i++) {
			String traytitle = getDriver()
					.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/header_primary_text'])[" + i + "]"))
					.getText();
			if (traytitle.equalsIgnoreCase("Trending Searches")) {
				TrendingSearchFound = true;
//				    verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
//				    type(AMDSearchScreen.objSearchBar, "Milana", "Search bar");
//					
//					verifyElementPresentAndClick(AMDSearchScreen.objSearchResultFirstContent, "content");
//					verifyElementExist(AMDSearchScreen.objConsumptionScreenTitle, "Title in Consumption screen");
//					Back(2);
//					waitTime(3000);
//					verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search icon");
//					
				checkElementExist(AMDSearchScreen.objTrendingSearches, "Trending Searches tray");
				checkElementExist(AMDSearchScreen.objContentCardTitleOfTrendingSearchesTray,
						"Content card title of Trending searches tray");

//			     String contentCardTitleofTrendingSearches =	getText(AMDSearchScreen.objContentCardTitleOfTrendingSearchesTray);
//			     logger.info(contentCardTitleofTrendingSearches);
//
//					click(AMDSearchScreen.objContentCardTitleOfTrendingSearchesTray, "Content card of Trending searches tray");
//					waitForElementDisplayed(AMDSearchScreen.objConsumptionScreenTitle, 10);
//					
//					verifyElementPresent(AMDSearchScreen.objConsumptionScreenTitle, "Title in Consumption screen");
//					
//						String consumptionScreenTitle = getText(AMDSearchScreen.objConsumptionScreenTitle);
//						if(contentCardTitleofTrendingSearches.equalsIgnoreCase(consumptionScreenTitle))
//						{
//						    logger.info("user navigated to respective consumption/Landing screen post tapping on any Trending searches carousel");	
//						    extent.extentLoggerPass("Title", "user navigated to respective consumption/Landing screen post tapping on any Trending searches carousel");
//						}
//						else
//						{
//							logger.info("user is not navigated to respective consumption/Landing screen post tapping on any Trending searches carousel");	
//						    extent.extentLoggerFail("Title", "user is not navigated to respective consumption/Landing screen post tapping on any Trending searches carousel");
//						}
//
//				break;
//				
			}
		}
		if (TrendingSearchFound == false) {
			logger.error("Trending searches is not displayed");
			extentLoggerFail("Trending searches tray", "Trending searches is not displayed");
		}
		Back(1);
	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: VINAY
	 * ---------------------------------
	 * 
	 * List of Functions Created - IntroScreen(String userType) - -
	 * =============================================================================
	 * ========
	 */
	public void IntroScreen(String userType) throws Exception {
		extent.HeaderChildNode("Validating Intro screen module");
		// Verify user is navigated to intro screen
		if (userType.equals("Guest")) {
			if (findElement(AMDOnboardingScreen.objScreenTitle).getText().equals("ZEE5")) {
				String ZEE5 = getDriver().findElement(AMDOnboardingScreen.objScreenTitle).getText();
				extent.extentLoggerPass("Verify user is navigated to Login/Registration screen",
						"User is navigated to " + ZEE5 + " Screen");
				logger.info("User is navigated to " + ZEE5 + " Screen after clicking on Browse for free");
			} else {
				extent.extentLoggerFail("Verify user is navigated to Login/Registration screen",
						"Intro screeen in not displayed");
				logger.error("Intro screeen in not displayed");
			}
			// Verify back button is displayed
			verifyElementPresent(AMDOnboardingScreen.objBackBtn, "Back button");
			// Verify user is navigated to Content language screeen post tapping back button
			click(AMDOnboardingScreen.objBackBtn, "Back button");
			String contentLang = getDriver().findElement(AMDOnboardingScreen.objScreenTitle).getText();
			if (contentLang.equals("Content Language")) {
				extent.extentLoggerPass("Verify user is navigated to Content language screen",
						"User is navigated to " + contentLang + " scree");
				logger.error("User is navigated to " + contentLang + " screen");
			} else {
				extent.extentLogger("Verify user is navigated to Content language screen",
						"Failed to navigate into content language screen");
				logger.info("Failed to navigate into content language screen");
			}

			// Covered few TC's and Updated Code by Kushal

			click(AMDOnboardingScreen.objBackBtn, "Back button");
			verifyElementPresent(AMDGenericObjects.objCheckTitle("Display Language"), "Display language screen");
			click(AMDOnboardingScreen.objSelectDisplayLang("Kannada"), "Kannada language");
			click(AMDOnboardingScreen.objDiplay_ContinueBtn, "[Display Language] Continue Button");

			String strLang = findElement(AMDOnboardingScreen.objScreenTitle).getText();
			if (strLang != "Content Language") {

				extent.extentLoggerPass("Content language screen", "Content language is displayed in Kannada language");
				logger.info("Title of the screen : " + strLang + " is displayed in selected language");
			} else {
				extent.extentLoggerFail("Content language screen",
						"Failed to display the content language in selected language");
				logger.error("Failed to display the content language in selected language");
			}

			click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue Button");

			if (findElement(AMDOnboardingScreen.objBrowseForFreeBtn).getText() != "Browse for Free") {
				String strBrwsforFree = findElement(AMDOnboardingScreen.objBrowseForFreeBtn).getText();
				extent.extentLoggerPass("Intro screen",
						"Browse for free button is displayed in selected launguage: " + strBrwsforFree);
				logger.info("Browse for free button is displayed in selected launguage : " + strBrwsforFree);
			} else {
				extent.extentLoggerFail("Intro screen",
						"Browse for free button failed to displayed in selected launguage");
				logger.error("Browse for free button failed to displayed in selected launguage");
			}
			String strSubsNow = findElement(AMDOnboardingScreen.objSubscribeNowBtn).getText();
			if (strSubsNow != "Subscribe Now") {
				extent.extentLoggerPass("Intro screen",
						"Subscribe Now button is displayed in selected launguage: " + strSubsNow);
				logger.info("Subscribe Now button is displayed in selected launguage : " + strSubsNow);
			} else {
				extent.extentLoggerFail("Intro screen",
						"Subscribe Now button failed to displayed in selected launguage: " + strSubsNow);
				logger.error("Subscribe Now button failed to displayed in selected launguage: " + strSubsNow);
			}
			String strHavePrepaidCode = findElement(AMDOnboardingScreen.objHavePrepaidBtn).getText();
			if (strHavePrepaidCode != "Have a prepaid code") {
				extent.extentLoggerPass("Intro screen",
						"Have a Prepaid code CTA is displayed in selected launguage: " + strHavePrepaidCode);
				logger.info("Have a Prepaid code CTA is displayed in selected launguage: " + strHavePrepaidCode);
			} else {
				extent.extentLoggerFail("Intro screen",
						"Have a Prepaid code CTA failed to displayed in selected launguage: " + strHavePrepaidCode);
				logger.error(
						"Have a Prepaid code CTA failed to displayed in selected launguage: " + strHavePrepaidCode);
			}
			String strLoginCTA = findElement(AMDOnboardingScreen.objLoginLnk).getText();
			if (strHavePrepaidCode != "Login") {
				extent.extentLoggerPass("Intro screen", "Login CTA is displayed in selected launguage: " + strLoginCTA);
				logger.info("Login CTA is displayed in selected launguage: " + strLoginCTA);
			} else {
				extent.extentLoggerFail("Intro screen",
						"Login CTA failed to displayed in selected launguage: " + strLoginCTA);
				logger.error("Login CTA failed to displayed in selected launguage: " + strLoginCTA);
			}
			Back(2);
			if (findElement(AMDOnboardingScreen.objSelectedDisplayLang).getText() != "English") {
				click(AMDOnboardingScreen.objSelectDisplayLang("English"), "English language");
				click(AMDOnboardingScreen.objDiplay_ContinueBtn, "[Display Language] Continue Button");
			}
			// ***********************************************************

			click(AMDOnboardingScreen.objContent_ContinueBtn, "[Content Language] Continue button");
			// Verify Login button is displayed
			verifyElementExist(AMDOnboardingScreen.objLoginLnk, "Login button");

			// Verify user is navigated to Login Registration screen
			click(AMDOnboardingScreen.objLoginLnk, "Login button");
			hideKeyboard();
			// Click operation is used for the device which do not identify the keyboard
			click(AMDLoginScreen.objContinueWithTxt, "Or Continue with");
			if (findElement(AMDOnboardingScreen.objScreenTitle).getText().equals("Login/Register")) {
				extent.extentLoggerPass("Verify Navigation on clicking Login button",
						"User is navigated to" + findElement(AMDOnboardingScreen.objScreenTitle).getText() + " screen");
				logger.info("User is navigated to " + findElement(AMDOnboardingScreen.objScreenTitle).getText()
						+ " screen");
			} else {
				extent.extentLoggerFail("Verify Navigation on clicking Login button",
						"Failed to navigate into Login/Register screen post tapping Login button");
				logger.error("Failed to navigate into Login/Register screen post tapping Login button");
			}
			click(AMDOnboardingScreen.objBackBtn, "Back button");
			// Verify that content feature carousel is displayed
			verifyElementExist(AMDOnboardingScreen.objFeatureCarousel, "Feature carousel rail");
			// Verify that Preminum member benifits section is displayed
			verifyElementExist(AMDOnboardingScreen.objBenefitsTextSection, "Benifits of premium member section");
			// Verify that pagination dots are displayed
			verifyElementExist(AMDOnboardingScreen.objDotsIndicator, "Pagination dots");
			// Verify Browse for free is displayed
			verifyElementExist(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for free");

			// Verify user is navigated to Login/Registration page post tapping Browse for
			// free
			click(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for free");
			if (findElement(AMDOnboardingScreen.objScreenTitle).getText().equals("Login/Register")) {
				extent.extentLoggerPass("Verify Navigation on clicking Browse for Free button",
						"User is navigated to" + findElement(AMDOnboardingScreen.objScreenTitle).getText() + " screen");
				logger.info("User is navigated to " + findElement(AMDOnboardingScreen.objScreenTitle).getText()
						+ " screen");
			} else {
				extent.extentLoggerFail("Verify Navigation on clicking Browse for Free button",
						"Failed to navigate into Login/Register screen post tapping Browse for Free button");
				logger.error("Failed to navigate into Login/Register screen post tapping Browse for Free button");
			}
			click(AMDOnboardingScreen.objBackBtn, "Back button");

			// Verify that Subscribe Now button is available
			verifyElementPresentAndClick(AMDOnboardingScreen.objSubscribeNowBtn, "Subcribe Now button");

			// Verify user is navigated to Subscribe now screen

			waitTime(2000);
			if (findElement(AMDOnboardingScreen.objScreenTitle).getText().equals("Subscribe")) {
				extent.extentLoggerPass("Verify Navigation on clicking Subscribe Now button",
						"User is navigated to" + findElement(AMDOnboardingScreen.objScreenTitle).getText() + " screen");
				logger.info("User is navigated to " + findElement(AMDOnboardingScreen.objScreenTitle).getText()
						+ " screen");
			} else {
				extent.extentLoggerFail("Verify Navigation on clicking Subcribe button",
						"Failed to navigate into Subscribe screen post tapping Subscribe Now button");
				logger.error("Failed to navigate into Subscribe screen post tapping Subcribe Now button");
			}
			click(AMDOnboardingScreen.objBackBtn, "Back button");

			// Verify that Have a prepaid code? is displayed
			verifyElementPresentAndClick(AMDOnboardingScreen.objHavePrepaidBtn, "Have a preapaid code? button");

			// Verify Prepaid code screen is displayed after tapping on Prepaid code button
			if (findElement(AMDOnboardingScreen.objPrepaidPopupLabel).isDisplayed()) {
				extent.extentLoggerPass("Verify navigation post tapping Prepaid code button", "User is navigated to "
						+ findElement(AMDOnboardingScreen.objPrepaidPopupLabel).getText() + " Screen");
				logger.info("User is navigated to " + findElement(AMDOnboardingScreen.objPrepaidPopupLabel).getText()
						+ " Screen");
			} else {
				extent.extentLoggerFail("Verify navigation post tapping Prepaid code button",
						"Failed to navigate into Prepaid screen post tapping Have a prepaid code button");
				logger.error("Failed to navigate into Prepaid screen post tapping Have a prepaid code button");
			}
			Back(1);
		} else {
			extent.extentLoggerPass("Intro Screen", "Intro Screen is not displayed for " + userType);
			logger.info("Intro Screen is not displayed for " + userType);
			System.out.println("Intro Screen is not displayed for Susbcribed/Non-Subscribed user");
		}
	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: HITESH
	 * -------------------------------
	 * 
	 * Functions Created list - navigateToRegisterScreen(String loginThrough) -
	 * validateRegister(String firstName, String secoundName) -
	 * checkRegisterButton() - deepLinks(String tabName) - relaunch() -
	 * registerForFreeScreen()
	 * =============================================================================
	 * ========
	 */

	public void navigateToRegisterScreen(String loginThrough) throws Exception {
		if (loginThrough.equals("BrowseForFree")) {
			HeaderChildNode("Validate Browse for Free Button functionality");
			verifyElementExist(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free button");
			logger.info("Browse for Free button is displayed in language : "
					+ getText(AMDOnboardingScreen.objBrowseForFreeBtn));
			extent.extentLoggerPass("Browse for Free button", "Browse for Free button is displayed in language : "
					+ getText(AMDOnboardingScreen.objBrowseForFreeBtn));

			verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");

			if (verifyIsElementDisplayed(AMDLoginScreen.objLoginLnk)) {
				logger.info("Login/Register Screen is displayed");
				extent.extentLoggerPass("Login/Register Screen", "Login/Register Screen is displayed");
			} else {
				logger.error("Login/Register Screen is not displayed");
				extent.extentLoggerFail("Login/Register Screen", "Login/Register Screen is not displayed");
			}

		} else if (loginThrough.equals("login")) {
			HeaderChildNode("Validate Login/Register Screen");
			click(AMDLoginScreen.objLoginLnk, "Login link");
		}

	}

	public void validateRegister(String firstName, String secoundName) throws Exception {
		extent.HeaderChildNode("Validate Registration screen UI/UX");
		System.out.println("\nValidate Registration screen UI/UX");
		waitTime(5000);
		String pEmailID = generateRandomString(5) + "@gmail.com";
		type(AMDRegistrationScreen.objEmailIDTextField, pEmailID, "Email field");
		verifyElementPresentAndClick(AMDRegistrationScreen.objProceedBtn, "Proceed button");
		verifyElementPresent(AMDRegistrationScreen.objScreenTitle, "Register for Free screen");
		HeaderChildNode("Validate fields of Register screen");
		WebElement element = findElement(AMDRegistrationScreen.objScreenTitle);
		int leftX = element.getLocation().getX();
		int rightX = leftX + element.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		Dimension size = getDriver().manage().window().getSize();
		if (middleX == Integer.valueOf((size.width) / 2)) {
			logger.info("Register for Free Title is displayed at center of the screen");
			extent.extentLoggerPass("Screen Title", "Register for Free Title is displayed at center of the screen");
		} else {
			logger.error("Register for Free Title is not displayed at center of the screen");
			extent.extentLoggerFail("Screen Title", "Register for Free Title is not displayed at center of the screen");
		}

		WebElement elementBackBtn = findElement(AMDRegistrationScreen.objBackBtn);
		int BackBtnleftX = elementBackBtn.getLocation().getX();
		int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
		int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

		if (BackBtnmiddleX <= 200) {
			logger.info("Back button is displayed at top left of the screen");
			extent.extentLoggerPass("Back button", "Back button is displayed at top left of the screen");
		} else {
			logger.error("Back button is not displayed at top left of the screen");
			extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
		}

		if (getAttributValue("focused", AMDRegistrationScreen.objFirstNameTxtField).equals("true")) {
			logger.info("First Name field is focused");
			extent.extentLoggerPass("First Name field", "First Name field is focused");
		} else {
			logger.error("First Name field is not focused");
			extent.extentLoggerFail("First Name field", "First Name field is not focused");
		}

		hideKeyboard();
		checkRegisterButton();
		verifyElementExist(AMDRegistrationScreen.objEmailIDTextField, "Email ID or Mobile number");

		if (!findElement(AMDRegistrationScreen.objEmailIDTextField).isEnabled()) {
			logger.info("“Email ID or Mobile number” field is grayed out");
			extent.extentLoggerPass("“Email ID or Mobile number”", "“Email ID or Mobile number” field is grayed out");
		} else {
			logger.error("“Email ID or Mobile number” field is not grayed out");
			extent.extentLoggerFail("“Email ID or Mobile number”",
					"“Email ID or Mobile number” field is not grayed out");
		}

		String EmailBeforeType = getText(AMDRegistrationScreen.objEmailIDTextField);
		type(AMDRegistrationScreen.objEmailIDTextField, pEmailID, "Email field");
		String EmailAfterType = getText(AMDRegistrationScreen.objEmailIDTextField);
		if (EmailBeforeType.equalsIgnoreCase(EmailAfterType)) {
			logger.info("User is not allowed to edit the Email field");
			extent.extentLoggerPass("Email field", "User is not allowed to edit the Email field");
		} else {
			logger.error("User is allowed to edit the Email field");
			extent.extentLoggerFail("Email field", "User is allowed to edit the Email field");
		}

		verifyElementExist(AMDRegistrationScreen.objFirstNameTxtField, "First Name field");
		verifyElementExist(AMDRegistrationScreen.objLastNameTxtField, "Last Name field");

		type(AMDRegistrationScreen.objFirstNameTxtField, firstName, "First Name field");
		checkRegisterButton();
		type(AMDRegistrationScreen.objLastNameTxtField, secoundName, "Last Name field");
		checkRegisterButton();
		click(AMDRegistrationScreen.objDOBTxtField, "Date of Birth");

		verifyElementPresentAndClick(AMDRegistrationScreen.objGederTxtField, "Gender field");
		verifyElementExist(AMDRegistrationScreen.objSelectGenderText, "Select your Gender screen");

		WebElement GenderTotleElement = findElement(AMDRegistrationScreen.objSelectGenderText);
		int GenderTotleleftX = GenderTotleElement.getLocation().getX();
		int GenderTotlerightX = GenderTotleleftX + GenderTotleElement.getSize().getWidth();
		int GenderTotlemiddleX = (GenderTotlerightX + GenderTotleleftX) / 2;
		Dimension windowSize = getDriver().manage().window().getSize();
		if (GenderTotlemiddleX == Integer.valueOf((windowSize.width) / 2)) {
			logger.info("Select your gender screen title is displayed at the center of the screen");
			extent.extentLoggerPass("Screen Title",
					"Select your gender screen title is displayed at the center of the screen");
		} else {
			logger.error("Select your gender screen title is not displayed at the center of the screen");
			extent.extentLoggerFail("Screen Title",
					"Select your gender screen title is not displayed at the center of the screen");
		}

		click(AMDRegistrationScreen.objMale, "Male");
		click(AMDRegistrationScreen.objGederTxtField, "Gender Field");
		waitTime(1000);
		boolean checkTickMark = verifyElementExist(AMDRegistrationScreen.objSelecteGender,
				"Tick mark on the selected option");
		if (checkTickMark) {
			String selectedGender = getText(AMDRegistrationScreen.objSelectedGenderName);
			logger.info("Selected Gender : " + selectedGender);
			extent.extentLoggerPass("Select Gender", "Selected Gender : " + selectedGender);
		} else {
			logger.info("Gender is not selected");
			extent.extentLoggerFail("Gender", "Gender is not selected");
		}

		WebElement CloseIconElement = findElement(AMDRegistrationScreen.objXMark);
		int CloseIconupperY = CloseIconElement.getLocation().getY();
		int CloseIconlowerY = CloseIconupperY + CloseIconElement.getSize().getHeight();
		int CloseIconmiddleY = (CloseIconupperY + CloseIconlowerY) / 2;

		Dimension windowsSize = getDriver().manage().window().getSize();

		if (CloseIconmiddleY >= ((windowsSize.getHeight() / 2) + 100)) {
			logger.info("X icon appears on the bottom of the screen");
			extent.extentLoggerPass("Select Gender", "X icon appears on the bottom of the screen");
		} else {
			logger.error("X icon is not appears on the bottom of the screen");
			extent.extentLoggerFail("Select Gender", "X icon is not appears on the bottom of the screen");
		}
		click(AMDRegistrationScreen.objMale, "Gender : Male");
		checkRegisterButton();

		verifyElementExist(AMDRegistrationScreen.objPasswordTxtField, "Set Password");
		click(AMDRegistrationScreen.objPasswordTxtField, "Set Password");
		type(AMDRegistrationScreen.objPasswordTxtField, generateRandomString(4), "entered 5 charecter in set password");
		hideKeyboard();
		verifyElementExist(AMDRegistrationScreen.objPasswordErrorMsg,
				"“Password must be a minimum of 6 characters” error message");
		type(AMDRegistrationScreen.objPasswordTxtField, generateRandomString(6), "entered 6 charecter in set password");

		for (int i = 0; i < 2; i++) {
			String eyeIcon = getAttributValue("checked", AMDRegistrationScreen.objEyeIcon);
			if (eyeIcon.equals("false")) {
				logger.info("Password is hide");
				extent.extentLoggerPass("Password hide eye icon", "Password is hide");
			} else {
				logger.info("Password is visible");
				extent.extentLoggerPass("Password hide eye icon", "Password is visible");
			}
			click(AMDRegistrationScreen.objEyeIcon, "Eye Icon");
		}
		click(AMDRegistrationScreen.objFirstNameTxtField, "First name"); // Clicking on First Name field to get device
																			// keys

		hideKeyboard();
		checkRegisterButton();
		verifyElementPresent(AMDRegistrationScreen.objTermsOfUseAndPrivacyPolicy, "Terms and condition text ");
	}

	public void checkRegisterButton() throws Exception {
		if (getAttributValue("clickable", AMDRegistrationScreen.objRegisterBtn).equals("false")) {
			logger.info("Register CTA is displayed and is dehighlated by default");
			extent.extentLoggerPass("Register button", "Register CTA is displayed and is dehighlated by default");
		} else {
			logger.error("Register CTA is Activated");
			extent.extentLoggerFail("Register button", "Register CTA is Activated");
		}
	}

	public void deepLinks(String tabName) {
		try {
			getDriver().close();
			waitTime(5000);
			String cmd3 = "adb shell am start -W -a android.intent.action.VIEW -d  \"https://www.zee5.com/" + tabName
					+ "\" com.graymatrix.did";
			Process process = Runtime.getRuntime().exec(cmd3);
			new BufferedReader(new InputStreamReader(process.getInputStream()));
			waitTime(12000);
			HeaderChildNode("DeepLink");
			if (tabName.contains("Home")) {
				verifyElementExist(AMDHomePage.objHomeTab, "Home screen");
				if (!verifyElementExist(AMDLoginScreen.objPageTitle, "Display language")) {
					if (!verifyElementExist(AMDOnboardingScreen.objContentLanguagePageTitle, "Content language")) {
						logger.info("Display Language and Content Language not displayed for deeplink");
						extent.extentLogger("Language popup",
								"Display Language and Content Language not displayed for deeplink");
					}
				}

			} else {
				if (checkElementExist(AMDLoginScreen.objLoginLnk, "Login/Register Screen")) {
					logger.info("Login/Register Screen is displayed for deeplink");
					extent.extentLoggerPass("Language popup", "Login/Register Screen is displayed for deeplink");
				}
			}
			waitTime(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: MANASA
	 * ---------------------------------
	 * 
	 * List of Functions Created - ZeeApplicasterLogin(String LoginMethod) -
	 * offlineScreenValidation() - socialLoginValidation(String loginThrough) -
	 * =============================================================================
	 * =========
	 */

	public void ZeeApplicasterLogin(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Login Functionality");

		String UserType = getParameterFromXML("userType");
		if (UserType.equals("Guest")) {
			extent.extentLogger("userType", "UserType : Guest");
		}

		verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
		waitTime(3000);

		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitTime(1000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip link");
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");

			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, Password, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");

			String SubscribedUsername = getParameterFromXML("SubscribedUserName");
			String SubscribedPassword = getParameterFromXML("SubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		}
	}

	public void offlineScreenValidation() throws Exception {

		extent.HeaderChildNode("Offline Screen Validation");
		System.out.println("\nOffline Screen Validation");

		TurnOFFWifi();
		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
		}

		if (pUserType.contains("Guest")) {
			verifyElementPresentAndClick(AMDHomePage.objMoviesTab, "Movies tab");
		}

		verifyElementExist(AMDOfflineScreen.objYouAreOffline, "You are Offline");
		verifyElementExist(AMDOfflineScreen.objTryAgain, "Try Again");
		verifyElementExist(AMDOfflineScreen.objGoToDownloads, "Go to Downloads");
		verifyElementExist(AMDHomePage.objHome, "Home");
		verifyElementExist(AMDHomePage.objUpcoming, "Upcoming");
		verifyElementExist(AMDHomePage.objDownload, "Download");
		verifyElementExist(AMDHomePage.objMoreMenu, "More Menu");

		click(AMDHomePage.objUpcoming, "Upcoming");
		verifyElementExist(AMDOfflineScreen.objYouAreOffline, "You are Offline");
		click(AMDHomePage.objMoreMenu, "More Menu");
		verifyElementExist(AMDOfflineScreen.objYouAreOffline, "You are Offline");

		// verifyElementPresentAndClick(AMDOfflineScreen.objGoToDownloads, "Go to
		// Downloads");

		verifyElementPresentAndClick(AMDHomePage.objDownload, "Download");
		if (verifyElementExist(AMDOfflineScreen.objDownloadScreen, "Download Section")) {
			logger.info("Navigated to Download Section");
			extent.extentLogger("Download Section", "Navigated to Download Section");
		} else {
			logger.info("Not navigated to Download Section");
			extent.extentLogger("Download Section", "Not navigated to Download Section");
		}
		verifyElementPresentAndClick(AMDHomePage.objHome, "Home Tab");
		verifyElementPresentAndClick(AMDOfflineScreen.objTryAgain, "Try Again");
		TurnONWifi();
		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
		}
		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.objUpcoming, "Upcoming tab");

		waitForElementDisplayed(AMDUpcomingPage.objContentCardTitle, 10);
		if (verifyElementExist(AMDUpcomingPage.objContentCardTitle, "Upcoming Page Content Card")) {
			logger.info("Appropriate page is loaded");
			extent.extentLogger("Page", "Appropriate page is loaded");
		} else {
			logger.info("Appropriate page is not loaded");
			extent.extentLogger("Page", "Appropriate page is not loaded");
		}
	}

	public void Wifi_TurnOFFnON() throws Exception {
		Runtime.getRuntime()
				.exec("adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
		waitTime(2000);
//		Runtime.getRuntime().exec("adb shell input keyevent 23");
		click(AMDGenericObjects.objWifiToggle, "Wifi-Toggle button");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell monkey -p com.graymatrix.did -c android.intent.category.LAUNCHER 1");
	}

	public void socialLoginValidation(String loginThrough, String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Social Login Validation");
			verifyElementPresentAndClick(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");
			verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn,
					"Continue button in content language screen");
			navigateToRegisterScreen(loginThrough);
			// verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
			verifyElementPresentAndClick(AMDLoginScreen.objGoogleBtn, "Gmail icon");

//			if (checkElementExist(AMDLoginScreen.objGmailSignIn, "Gmail Sign In")) {
//				verifyElementPresentAndClick(AMDLoginScreen.objGmailEmailField, "Email Field");
//				type(AMDLoginScreen.objGmailEmailField, "zeetest55@gmail.com", "Email Field");
//				verifyElementPresentAndClick(AMDLoginScreen.objGmailNextBtn, "Next Button");
//				verifyElementPresentAndClick(AMDLoginScreen.objGmailPasswordField, "Password Field");
//				type(AMDLoginScreen.objGmailPasswordField, "zeetest123", "Password Field");
//				verifyElementPresentAndClick(AMDLoginScreen.objGmailNextBtn, "Next Button");
			//
//				if (checkElementExist(AMDLoginScreen.objGmailAddPhoneNumber, "Add Phone Number")) {
//					verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip Button");
//				}
//				if (checkElementExist(AMDLoginScreen.objAgreeBtn, "Agree Button")) {
//					click(AMDLoginScreen.objAgreeBtn, "Agree Button");
//				}
			//
//				if (checkElementExist(AMDLoginScreen.objAcceptBtn, "Accept Button")) {
//					click(AMDLoginScreen.objAcceptBtn, "Accept Button");
//				}
//			}
			//
//			if (checkElementExist(AMDLoginScreen.objGmailAccount, "Gmail Account")) {
//				click(AMDLoginScreen.objGmailAccount, "Gmail Account");
//				waitTime(5000);
//			}
//			if (checkElementExist(AMDOnboardingScreen.objTellUsMore, "More info Screen")) {
//				if (checkElementExist(AMDLoginScreen.objEmailIdField, "Email Id field")) {
//					type(AMDLoginScreen.objEmailIdField, "zeetest@gmail.com", "Email Id field");
//				}
//				verifyElementPresentAndClick(AMDLoginScreen.objDOB, "Date of Birth");
//				verifyElementPresentAndClick(AMDLoginScreen.objDate, "Date");
//				verifyElementPresentAndClick(AMDLoginScreen.objDateOK, "OK button");
//				verifyElementPresentAndClick(AMDLoginScreen.objGender, "Gender Field");
//				verifyElementPresentAndClick(AMDLoginScreen.objGenderMale, "Male");
//				verifyElementExist(AMDLoginScreen.objSubmitButton, "Submit Button");
//				Back(1);
//			}

			if (checkElementExist(AMDHomePage.objHome, "Home Tab")) {
				logger.info("User logged in successfully");
				extent.extentLoggerPass("Login", "User logged in successfully");

				verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
				Swipe("UP", 2);

				verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout");
				verifyElementPresentAndClick(AMDMoreMenu.objLogoutBtn, "Logout Button");
				waitTime(5000);
				Swipe("Down", 2);
				verifyElementPresentAndClick(AMDMoreMenu.objProfile, "Login/Register");

			} else {
				logger.info("Pre conditions not met, Account is not logged In");
				extentLoggerWarning("Gmail", "Pre conditions not met, Account is not logged In");
				Back(1);
			}

			verifyElementPresentAndClick(AMDLoginScreen.objtwitterBtn, "Twitter icon");
			waitTime(5000);

			if (checkElementExist(AMDLoginScreen.objTwitterAutorizeAllowBtn, "Authorize app")) {
				click(AMDLoginScreen.objTwitterAutorizeAllowBtn, "Authorize app");
			}

//			if (checkElementExist(AMDLoginScreen.objTwitterEmail, "Email Id field")) {
//				click(AMDLoginScreen.objTwitterEmail, "Email Id field");
//				type(AMDLoginScreen.objTwitterEmail, "zee5latest@gmail.com", "Email Id field");
//				verifyElementPresentAndClick(AMDLoginScreen.objTwitterPassword, "Password Field");
//				type(AMDLoginScreen.objTwitterPassword, "User@123", "Password field");
//				verifyElementPresentAndClick(AMDLoginScreen.objTwitterLoginBtn, "Login Button");
//				waitTime(5000);
//				if (checkElementExist(AMDHomePage.objHome, "Home Tab")) {
//					logger.info("User logged in successfully");
//					extent.extentLoggerPass("Login", "User logged in successfully");
//					verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
//					Swipe("UP", 2);
//					verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout");
//					verifyElementPresentAndClick(AMDMoreMenu.objLogoutBtn, "Logout Button");
//					waitTime(5000);
//					Swipe("Down", 2);
//					verifyElementPresentAndClick(AMDMoreMenu.objProfile, "Login/Register");
//				}
//			}
			//
//			if (checkElementExist(AMDOnboardingScreen.objTellUsMore, "More info Screen")) {
//				if (checkElementExist(AMDLoginScreen.objEmailIdField, "Email Id field")) {
//					type(AMDLoginScreen.objEmailIdField, "zeetest@gmail.com", "Email Id field");
//				}
//				verifyElementPresentAndClick(AMDLoginScreen.objDOB, "Date of Birth");
//				verifyElementPresentAndClick(AMDLoginScreen.objDate, "Date");
//				verifyElementPresentAndClick(AMDLoginScreen.objDateOK, "OK button");
//				verifyElementPresentAndClick(AMDLoginScreen.objGender, "Gender Field");
//				verifyElementPresentAndClick(AMDLoginScreen.objGenderMale, "Male");
//				verifyElementExist(AMDLoginScreen.objSubmitButton, "Submit Button");
//			}
			if (checkElementExist(AMDHomePage.objHome, "Home Tab")) {
				logger.info("User logged in successfully");
				extent.extentLogger("Login", "User logged in successfully");
				verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
				Swipe("UP", 2);
				verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout");
				verifyElementPresentAndClick(AMDMoreMenu.objLogoutBtn, "Logout Button");
				waitTime(5000);
				Swipe("Down", 2);
				verifyElementPresentAndClick(AMDMoreMenu.objProfile, "Login/Register");
			} else {
				logger.info("Pre conditions not met, Account is not logged In");
				extentLoggerWarning("Twitter", "Pre conditions not met, Account is not logged In");
				Back(1);
			}
			verifyElementPresentAndClick(AMDLoginScreen.objfbBtn, "Facebook icon");
			waitTime(5000);
//			if (checkElementExist(AMDLoginScreen.objFBLogIntoAnotherAccount, "Log Into Another Account")) {
//				click(AMDLoginScreen.objFBLogIntoAnotherAccount, "Log Into Another Account");
//				if (checkElementExist(AMDLoginScreen.objFBEmail, "Email Id field")) {
//					click(AMDLoginScreen.objFBEmail, "Email Id field");
//					type(AMDLoginScreen.objFBEmail, "igszeefive@gmail.com", "Email Id field");
//					verifyElementPresentAndClick(AMDLoginScreen.objFBPassword, "Password Field");
//					type(AMDLoginScreen.objFBPassword, "zeefive@igs", "Password field");
//					verifyElementPresentAndClick(AMDLoginScreen.objFBLoginBtn, "Login Button");
//				}
//			} else if (checkElementExist(AMDLoginScreen.objFBEmail, "Email Id field")) {
//				click(AMDLoginScreen.objFBEmail, "Email Id field");
//				type(AMDLoginScreen.objFBEmail, "igszeefive@gmail.com", "Email Id field");
//				verifyElementPresentAndClick(AMDLoginScreen.objFBPassword, "Password Field");
//				type(AMDLoginScreen.objFBPassword, "zeefive@igs", "Password field");
//				verifyElementPresentAndClick(AMDLoginScreen.objFBLoginBtn, "Login Button");
//			}
			if (checkElementExist(AMDHomePage.objHome, "Home Tab")) {
				logger.info("User logged in successfully");
				extent.extentLoggerPass("Login", "User logged in successfully");
				verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
				Swipe("UP", 1);

				verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout");
				verifyElementPresentAndClick(AMDMoreMenu.objLogoutBtn, "Logout Button");
				waitTime(5000);
				Swipe("Down", 2);
				verifyElementPresentAndClick(AMDMoreMenu.objProfile, "Login/Register");
			} else {
				logger.info("Pre conditions not met, Account is not logged In");
				extentLoggerWarning("Facebook", "Pre conditions not met, Account is not logged In");
				Back(1);
			}
//			if (checkElementExist(AMDOnboardingScreen.objTellUsMore, "More info Screen")) {
//				if (checkElementExist(AMDLoginScreen.objEmailIdField, "Email Id field")) {
//					type(AMDLoginScreen.objEmailIdField, "zeetest@gmail.com", "Email Id field");
//				}
//				verifyElementPresentAndClick(AMDLoginScreen.objDOB, "Date of Birth");
//				verifyElementPresentAndClick(AMDLoginScreen.objDate, "Date");
//				verifyElementPresentAndClick(AMDLoginScreen.objDateOK, "OK button");
//				verifyElementPresentAndClick(AMDLoginScreen.objGender, "Gender Field");
//				verifyElementPresentAndClick(AMDLoginScreen.objGenderMale, "Male");
//				verifyElementExist(AMDLoginScreen.objSubmitButton, "Submit Button");
//				Back(1);
//			}
		}
	}

	public void searchResultsAllTabs(String searchModuleKeyword) throws Exception {
		extent.HeaderChildNode("Validating that user is able to find the searched content in all the tabs");
		waitTime(5000);
		type(AMDSearchScreen.objSearchBoxBar, searchModuleKeyword + "\n", "Search bar");
		// getDriver().getKeyboard().sendKeys(searchModuleKeyword);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 20);
		boolean allTabHighlighted = findElement(AMDSearchScreen.objAllTab).isSelected();
		if (allTabHighlighted == true) {
			logger.info("All Tab is highlighted by default");
			extent.extentLoggerPass("All Tab", "All Tab is highlighted by default");
		} else {
			logger.error("All Tab is not highlighted by default");
			extent.extentLoggerFail("All Tab", "All Tab is not highlighted by default");
		}
		waitTime(5000);
		swipeByElements(findElement(AMDSearchScreen.objMusicTabIndx), findElement(AMDSearchScreen.objShowsTabIndx));
		waitTime(3000);
		swipeByElements(findElement(AMDSearchScreen.objMusicTabIndx), findElement(AMDSearchScreen.objVideosTab));
		logger.info("User is able to scroll through the tabs");
		extent.extentLoggerPass("Tabs", "User is able to scroll through the tabs");
		hideKeyboard();
		waitTime(5000);
		List<WebElement> tabs = getDriver().findElements(AMDSearchScreen.objTabs);
		System.out.println(tabs.size());
		boolean Videos = false;
		for (int i = 1; i <= tabs.size(); i++) {
			String tabName = null;

			System.out.println("i : " + i);

			if (i == 5) {

				if (!Videos) {
					i = 4;
				}

				WebElement eleTab = findElement(
						By.xpath("(//*[@id='clearSearch']//following::*[@id='title'])[" + i + "]"));
				tabName = findElement(By.xpath("(//*[@id='clearSearch']//following::*[@id='title'])[" + i + "]"))
						.getText();

				System.out.println(tabName);
				eleTab.click();

			} else {
				WebElement eleTab = findElement(
						By.xpath("(//*[@id='clearSearch']//following::*[@id='title'])[" + i + "]"));
				tabName = findElement(By.xpath("(//*[@id='clearSearch']//following::*[@id='title'])[" + i + "]"))
						.getText();
				System.out.println(tabName);
				eleTab.click();
			}
			waitTime(5000);
			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLoggerPass("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");

			if (verifyIsElementDisplayed(AMDMoreMenu.objSearchResult(searchModuleKeyword))) {
				String searchResults = findElement(AMDMoreMenu.objSearchResult(searchModuleKeyword)).getText();
				System.out.println("Search result : " + searchResults);

				if (searchResults.contains(searchModuleKeyword)) {
					logger.info("Related search results are displayed in " + tabName + " tab");
					extent.extentLoggerPass("Related search results",
							"Related search results are displayed in " + tabName + " tab");
				} else {
					logger.info("Related search results are not displayed in  " + tabName + " tab");
					extent.extentLoggerPass("Related search results",
							"Related search results are not displayed in  " + tabName + " tab");
				}

			} else {
				logger.info("Related search results are not displayed in  " + tabName + " tab");
				extent.extentLoggerPass("Related search results",
						"Related search results are not displayed in  " + tabName + " tab");
			}

			if (Videos) {
				break;
			}
			if (tabName.equalsIgnoreCase("Music")) {
				Videos = true;
			}
		}
		Back(1);
		if (checkElementExist(AMDSearchScreen.objMicrophoneIcon, "Microphone Icon")) {
			logger.info(
					"Microphone icon is displayed when user navigates back to Search landing screen from Search Result screen");
			extent.extentLoggerPass("Microphone icon",
					"Microphone icon is displayed when user navigates back to Search landing screen from Search Result screen");
		} else {
			logger.error(
					"Microphone icon is not displayed when user navigates back to Search landing screen from Search Result screen");
			extent.extentLoggerFail("Microphone icon",
					"Microphone icon is not displayed when user navigates back to Search landing screen from Search Result screen");
		}
	}

	@SuppressWarnings("deprecation")
	public void noSearchResults(String invalidSearchKeyword) throws Exception {
		extent.HeaderChildNode(
				"Validating the category tab is not displayed if the searched keyword don’t have results for the particular category");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search Icon");

		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		getDriver().getKeyboard().sendKeys(invalidSearchKeyword);
		hideKeyboard();
		// type(AMDSearchScreen.objSearchBoxBar, invalidSearchKeyword + "\n", "Search
		// bar");
		if (checkElementExist(AMDSearchScreen.objNoSearchResults, "No Search Results message")) {
			String noResults = getText(AMDSearchScreen.objNoSearchResults);
			logger.info(noResults + " message is displayed");
			extent.extentLoggerPass("Search Results message", noResults + " message is displayed");

			logger.info("Searched keyword does not have results for the particular category");
			extent.extentLoggerPass("Search Results message",
					"Searched keyword does not have results for the particular category");
		} else {
			logger.error("Searched keyword has results for the particular category");
			extent.extentLoggerFail("Search Results message",
					"Searched keyword has results for the particular category");

		}
		click(AMDSearchScreen.objClearSearch, "Clear Search");
	}

	public void swipeByElements(WebElement webElement, WebElement webElement2) {

		touchAction = new TouchAction<>(getDriver());

		int startX = webElement.getLocation().getX() + (webElement.getSize().getWidth() / 2);
		int startY = webElement.getLocation().getY() + (webElement.getSize().getHeight() / 2);

		int endX = webElement2.getLocation().getX() + (webElement2.getSize().getWidth() / 2);
		int endY = webElement2.getLocation().getY() + (webElement2.getSize().getHeight() / 2);
		touchAction.press(PointOption.point(startX, startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(endX, endY))
				.release().perform();
	}

	@SuppressWarnings("deprecation")
	public void searchPageValidation(String partlySpeltSearchKeyword) throws Exception {
		extent.HeaderChildNode("Search Result Screen Validation");
		getDriver().getKeyboard().sendKeys(partlySpeltSearchKeyword);
		type(AMDSearchScreen.objSearchBoxBar, partlySpeltSearchKeyword + "\n", "Search bar");
		verifyElementExist(AMDSearchScreen.objSearchResultPage, "Search Result Screen");

		if (verifyIsElementDisplayed(AMDSearchScreen.objRecentSearch)) {
			extent.extentLoggerPass("Recent Search Overlay",
					"Recent Search Overlay is not available in search results screen");
			logger.info("Recent Search Overlay is not available in search results screen");
		} else {
			extent.extentLoggerFail("Recent Search Overlay",
					"Recent Search Overlay is available in search results screen");
			logger.error("Recent Search Overlay is available in search results screen");
		}

		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 20);

//		String enteredValue = getAttributValue("value", AMDSearchScreen.objSearchBoxBar);
		System.out.println(partlySpeltSearchKeyword.length());
		waitTime(20000);
		if (partlySpeltSearchKeyword.length() >= 3) {

			System.out.println(getDriver().findElements(AMDSearchScreen.objRelatedSearchResult).size());

			if (getDriver().findElements(AMDSearchScreen.objRelatedSearchResult).size() > 0) {
				logger.info("Search result screen is displayed once user enters 3rd character in the search box.");
				extent.extentLoggerPass("Search result screen",
						"Search result screen is displayed once user enters 3rd character in the search box.");

			} else {
				logger.error(
						"Search result screen is not displayed when user enters less than 3 characters in the search box.");
				extent.extentLoggerFail("Search result screen",
						"Search result screen is not displayed when user enters less than 3 characters in the search box.");
			}
		}

		PartialSwipe("UP", 2);
		logger.info("User is able to scroll down the search results");
		extent.extentLoggerPass("Search results", "User is able to scroll down the search results");
		verifyElementPresentAndClick(AMDSearchScreen.objClearSearch, "Clear Search");
		hideKeyboard();
	}

	public void voiceSearchDenyValidation() throws Exception {
		extent.HeaderChildNode("Voice Search Access Deny Validation");
		System.out.println("Voice Search Access Deny Validation");
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search Icon");
		verifyElementPresentAndClick(AMDSearchScreen.objMicrophoneIcon, "Microphone icon");
		verifyElementPresentAndClick(AMDSearchScreen.objProceedBtn, "Proceed Button");
		if (verifyElementExist(AMDSearchScreen.objAudioPermissionPopUp, "Audio Permission Popup")) {
			verifyElementPresentAndClick(AMDSearchScreen.objDeny, "Deny Option");
			if (checkElementExist(AMDSearchScreen.objMicrophoneIcon, "Microphone Icon")) {
				logger.info("Search landing screen is displayed after denying audio permission");
				extent.extentLoggerPass("Search landing screen",
						"Search landing screen is displayed after denying audio permission");
			} else {
				logger.error("Search landing screen is not displayed after denying audio permission");
				extent.extentLoggerFail("Search landing screen",
						"Search landing screen is not displayed after denying audio permission");
			}

//			verifyElementPresentAndClick(AMDSearchScreen.objMicrophoneIcon, "Microphone icon");
//			verifyElementExist(AMDSearchScreen.objVoiceSearchPermission,"Microphone access permission popup");
//			verifyElementExist(AMDSearchScreen.objAudioPermissionPopUp,"Audio Permission Popup");
		}
	}

	public void voiceSearchValidation() throws Exception {
		extent.HeaderChildNode("Voice Search Validation");
		System.out.println("Voice Search Validation");
		// verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search Icon");

		WebElement elementMicrophoneIcon = findElement(AMDSearchScreen.objMicrophoneIcon);
		int iconRightX = elementMicrophoneIcon.getLocation().getX();
		System.out.println(iconRightX);
		Dimension sizee = getDriver().manage().window().getSize();
		System.out.println(sizee.getWidth());
		int sizeee = sizee.getWidth() - 300;
		System.out.println(sizeee);

		if (iconRightX >= sizeee) {
			logger.info("Microphone icon is displayed on right side of the search box");
			extent.extentLoggerPass("Microphone icon", "Microphone icon is displayed on right side of the search box");
		} else {
			logger.error("Microphone icon is not displayed on right side of the search box");
			extent.extentLoggerFail("Microphone icon",
					"Microphone icon is not displayed on right side of the search box");
		}
		click(AMDSearchScreen.objMicrophoneIcon, "Microphone icon");
		if (checkElementExist(AMDSearchScreen.objVoiceSearchPermission, "Microphone access permission popup")) {
			checkElementExist(AMDSearchScreen.objMicrophoneIconLogo, "Microphone icon");
			checkElementExist(AMDSearchScreen.objTermsAndConditions, "Terms of Use and Privacy Policy Message");
			checkElementExist(AMDSearchScreen.objProceedBtn, "Proceed Button");
			checkElementExist(AMDSearchScreen.objBackBtn, "Back button");

			WebElement elementBackBtn = findElement(AMDSearchScreen.objBackBtn);
			int BackBtnleftX = elementBackBtn.getLocation().getX();
			int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
			int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

			if (BackBtnmiddleX <= 200) {
				logger.info("Back button is displayed at top left of the screen");
				extent.extentLoggerPass("Back button", "Back button is displayed at top left of the screen");
			} else {
				logger.error("Back button is not displayed at top left of the screen");
				extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
			}

			click(AMDSearchScreen.objBackBtn, "Back button");

			if (verifyElementDisplayed(AMDSearchScreen.objSearchEditBox)) {
				logger.info(
						"User navigated to Search Landing screen on tapping back button in microphone access permission screen");
				extent.extentLoggerPass("Search Screen",
						"User navigated to Search Landing screen on tapping back button in microphone access permission screen");
			} else {
				logger.error(
						"User not navigated to Search Landing screen on tapping back button in microphone access permission screen");
				extent.extentLoggerFail("Search Screen",
						"User not navigated to Search Landing screen on tapping back button in microphone access permission screen");
			}

			click(AMDSearchScreen.objMicrophoneIcon, "Microphone icon");
			waitTime(2000);
			click(AMDSearchScreen.objProceedBtn, "Proceed Button");
			if (checkElementExist(AMDSearchScreen.objAudioPermissionPopUp, "Audio Permission Popup")) {
				verifyElementPresentAndClick(AMDSearchScreen.objAllow, "Allow Option");
				checkElementExist(AMDSearchScreen.objVoiceSearchScreen, "Voice Search Screen");
				checkElementExist(AMDSearchScreen.objMicrophoneLogoInVoiceSearch, "Microphone icon");
				checkElementExist(AMDSearchScreen.objSeeUrTextMsg, "See your text here message");
				verifyElementPresentAndClick(AMDSearchScreen.objCloseBtn, "Close Button");
//				verifyElementPresentAndClick(AMDSearchScreen.objMicrophoneIcon, "Microphone icon");
//				verifyElementExist(AMDSearchScreen.objVoiceSearchPermission,"Microphone access permission popup");
//				verifyElementExist(AMDSearchScreen.objAudioPermissionPopUp,"Audio Permission Popup");
			}
		}
	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: KUSHAL
	 * ---------------------------------
	 * 
	 * List of Functions Created - navigateToLoginScreen_DisplaylangScreen() -
	 * navigateToIntroScreen_DisplaylangScreen() - LoginWithEmailID(String pEmailId,
	 * String pPassword) - verifyUIPresentInLoginPage() -
	 * verifyLoginScreenUIFunctionality() - VerifySkipLoginRegistrationScreen() -
	 * VerifyLoginWithEmailId(String pUserName, String pPassword) -
	 * verifyHaveAPrepaidCodePopUp() -
	 * verifyInvalidPrepaidCodePopUpAfterLogin(String pEmailID, String pPassword) -
	 * verifyInvalidPrepaidCodePopUpAfterRegistration() -
	 * verifyCongratulationPopupAppearsforValidPrepaidCode(String pCode, String
	 * pUserName, String pPassword)
	 * =============================================================================
	 * =========
	 */

	public void navigateToLoginScreen_DisplaylangScreen() throws Exception {
		extent.HeaderChildNode("Navigation to Login Screen");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
		verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
	}

	public void navigateToIntroScreen_DisplaylangScreen() throws Exception {
		extent.HeaderChildNode("Navigation to Intro Screen");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
		verifyElementPresent(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
	}

	public void LoginWithEmailID(String pEmailId, String pPassword) throws Exception {
//		extent.HeaderChildNode("Log into ZEE5 with registered Email account");
		verifyElementPresent(AMDLoginScreen.objEmailIdLabel, "Login/Register screen");
		type(AMDLoginScreen.objEmailIdField, pEmailId, "Email-Id/Phone");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresent(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, pPassword, "Password");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login Button");
	}

	public void verifyUIPresentInLoginPage() throws Exception {
		verifyElementPresent(AMDLoginScreen.objBackBtn, "Back button");
		verifyElementPresent(AMDLoginScreen.objLoginLnk, "Skip button");
		verifyElementPresent(AMDLoginScreen.objGoogleBtn, "Google login button");
		verifyElementPresent(AMDLoginScreen.objfbBtn, "facebook login button");
		verifyElementPresent(AMDLoginScreen.objtwitterBtn, "twitter login button");
	}

	public void verifyLoginScreenUIFunctionality() throws Exception {
		extent.HeaderChildNode("Veirfy UI elements present in Login Screen");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Login"), "Login Screen");

		String pwdEncript = getAttributValue("checked", AMDLoginScreen.objShowPwdBtn);
		if (pwdEncript.contains("false")) {
			extent.extentLoggerPass("Default Show Password", "Password is Encripted");
			logger.info("Password is Encripted");
		} else {
			extent.extentLoggerFail("Default Show Password", "Password is not Encripted");
			logger.error("Password is not Encripted");
		}

		click(AMDLoginScreen.objShowPwdBtn, "Show Password");
		String checkPwdVisible = getAttributValue("checked", AMDLoginScreen.objShowPwdBtn);
		if (checkPwdVisible.contains("true")) {
			extent.extentLoggerPass("Click Show Password", "Password is Visible");
			logger.info("Password is Visible");
		} else {
			extent.extentLoggerFail("Click Show Password", "Password is not Visible");
			logger.error("Password is not Visible");
		}
		hideKeyboard();
		verifyElementPresentAndClick(AMDLoginScreen.objForgetPwdBtn, "Forgot Password CTA");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Forgot Password"), "Forgot Password Screen");
		click(AMDLoginScreen.objBackBtn, "Back button");
	}

	public void VerifySkipLoginRegistrationScreen() throws Exception {
		extent.HeaderChildNode("Browse for Free - Skip Login/Register screen to Home page");

		String pUserType = getParameterFromXML("userType");
		if (pUserType.contains("Guest")) {
			verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
			verifyElementPresent(AMDGenericObjects.objScreenTitleName("Login/Register"), "Login/Register title");
			verifyUIPresentInLoginPage();
			verifyElementPresent(AMDLoginScreen.objEmailIdLabel, "Email ID or Mobile Number");
			type(AMDLoginScreen.objEmailIdField, "testmessage.com", "EmailId");
			verifyElementPresent(AMDLoginScreen.objErrInvalidID, getText(AMDLoginScreen.objErrInvalidID));
			clearField(AMDLoginScreen.objEmailIdField, "EmailId");
			type(AMDLoginScreen.objEmailIdField, "987654321", "Phone Number");
			verifyElementPresent(AMDLoginScreen.objErrInvalidID, getText(AMDLoginScreen.objErrInvalidID));
			clearField(AMDLoginScreen.objEmailIdField, "EmailId");
			type(AMDLoginScreen.objEmailIdField, "sample@zee5.com", "EmailId");
			hideKeyboard();
			verifyElementPresent(AMDLoginScreen.objProceedBtn, "Proceed button");
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip button");
			verifyElementPresent(AMDHomePage.objHome, "Home Page");
		} else {
			boolean landingPage = verifyIsElementDisplayed(AMDHomePage.HomeIcon);
			if (landingPage) {
				logger.info("Intro screen is skipped for " + pUserType);
				extent.extentLoggerPass("Intro Skipped", "Intro screen is skipped and User landed in Home screen");
			} else {
				logger.info("Intro screen is Not skipped for " + pUserType);
				extent.extentLoggerFail("Intro Skipped", "Intro screen is NOT skipped for " + pUserType);
			}
		}
	}

	public void VerifyLoginWithEmailId(String pUserName, String pPassword) throws Exception {
		extent.HeaderChildNode("Login With Email-ID");

		String pUserType = getParameterFromXML("userType");
		if (pUserType.contains("Guest")) {
			verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
			verifyElementPresent(AMDGenericObjects.objScreenTitleName("Login/Register"), "Login/Register");
			type(AMDLoginScreen.objEmailIdField, pUserName, "Email-ID");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			if (getText(AMDLoginScreen.objEmailIdField).equalsIgnoreCase(pUserName)) {
				extent.extentLoggerPass("Email-Id Retained", "Email Id is retained in Login screen EmailId Field");
				logger.info("Email Id is retained in Login screen EmailId Field");
			} else {
				extent.extentLoggerFail("Email-Id Retained",
						"Email Id failed to retained in Login screen EmailId Field");
				logger.error("Email Id failed to retained in Login screen EmailId Field");
			}
			String getPropertyValue = getAttributValue("enabled", AMDLoginScreen.objEmailIdField);
			if (getPropertyValue.equalsIgnoreCase("false")) {
				extent.extentLoggerPass("EmailId field is disabled/grayed out", " User cannot edit emailid field");
				logger.info("EmailId field is disabled/grayed out and user cannot edit emailid field");
			} else {
				extent.extentLoggerFail("EmailId field is not grayed out", " User is able to edit emailid field");
				logger.error("EmailId field is not grayed out and user is able to edit emailid field");
			}
			verifyLoginScreenUIFunctionality();

			verifyElementPresent(AMDLoginScreen.objPasswordField, "Password field");
			type(AMDLoginScreen.objPasswordField, "xcvzc", "Password");
			verifyElementPresent(AMDLoginScreen.objErrorTxtMsg, "Password must be a minimum of 6 characters");
			clearField(AMDLoginScreen.objPasswordField, "Password");
			type(AMDLoginScreen.objPasswordField, pPassword, "Password");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			Wait(2000);
			if (verifyElementPresent(AMDHomePage.objHomeTab, "Home page")) {
				extent.extentLoggerPass("Login with EmailId", pUserName + " : is logged in successfully");
				logger.info(pUserName + " is logged in  successfully");
			} else {
				extent.extentLoggerFail("Login with EmailId", pUserName + " failed to login");
				logger.error(pUserName + " failed to login");
			}
		} else {
			boolean landingPage = verifyIsElementDisplayed(AMDHomePage.HomeIcon);
			if (landingPage) {
				logger.info("Intro screen is skipped for " + pUserType);
				extent.extentLoggerPass("Intro Skipped", "Intro screen is skipped and User landed in Home screen");
			} else {
				logger.info("Intro screen is Not skipped for " + pUserType);
				extent.extentLoggerFail("Intro Skipped", "Intro screen is NOT skipped for " + pUserType);
			}
		}
	}

	public void verifyHaveAPrepaidCodePopUp() throws Exception {
		extent.HeaderChildNode("Verify Have a prepaid code PopUp UI is displayed");
		String pUserType = getParameterFromXML("userType");
		if (pUserType.contains("Guest")) {

			click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
			click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
			verifyElementPresentAndClick(AMDOnboardingScreen.objHavePrepaidBtn, "Have a prepaid code? CTA");
			verifyElementPresent(AMDOnboardingScreen.objPrepaidPopupLabel, "Prepaid Code PopUp Header");
			verifyElementPresent(AMDOnboardingScreen.objWhatIsPrepaidCodeBtn, "What is a Prepaid Code?");
			verifyElementPresent(AMDOnboardingScreen.objApplyBtn, "Apply button");

			String getPropertyValue = getAttributValue("enabled", AMDOnboardingScreen.objApplyBtn);
			if (getPropertyValue.equalsIgnoreCase("false")) {
				extent.extentLoggerPass("Apply button", "Apply Button is by default dehighlighted");
				logger.info("Apply Button is by default dehighlighted");
			} else {
				extent.extentLoggerFail("Apply buttont", "Apply Button fails to dehighlight by default");
				logger.error("Apply Button failed to dehighlight by default");
			}

			verifyElementPresent(AMDOnboardingScreen.objPrepaidCodeField, "Prepaid Code Field");
			if (getText(AMDOnboardingScreen.objPrepaidCodeField).equalsIgnoreCase("Prepaid Code")) {
				extent.extentLoggerPass("Prepaid Code field", "Prepaid Code text is displayed in the edit field");
				logger.info("Prepaid Code text is displayed in the edit field");
			} else {
				extent.extentLoggerFail("Prepaid Code field", "Prepaid Code text is not displayed in the edit field");
				logger.error("Prepaid Code text is not displayed in the edit field");
			}

			verifyElementPresentAndClick(AMDOnboardingScreen.objWhatIsPrepaidCodeBtn, "What is Prepaid Code? CTA");
			verifyElementPresent(AMDGenericObjects.objCheckTitle("About Prepaid Code"), "About Prepaid Code Screen");
			verifyElementPresent(AMDOnboardingScreen.objDescriptionTxt, "About Prepaid code description");
			verifyElementPresentAndClick(AMDOnboardingScreen.objBackBtn, "Back Button");
			verifyElementPresent(AMDOnboardingScreen.objPrepaidPopupLabel, "Prepaid Popup");
			verifyElementPresentAndClick(AMDOnboardingScreen.objPopUpDivider, "Popup Horizontal line");
			verifyElementPresentAndClick(AMDOnboardingScreen.objHavePrepaidBtn, "Have a prepaid code? CTA");

			click(AMDOnboardingScreen.objPrepaidCodeField, "Prepaid code field");
			Wait(1000);
			type(AMDOnboardingScreen.objPrepaidCodeField, "ZNA2020", "Prepaid Code field");
			hideKeyboard();
			String getPropertyValue2 = getAttributValue("enabled", AMDOnboardingScreen.objApplyBtn);
			if (getPropertyValue2.equalsIgnoreCase("true")) {
				extent.extentLoggerPass("Apply button", "Apply Button is highlighted");
				logger.info("Apply Button is highlighted");
			} else {
				extent.extentLoggerFail("Apply buttont", "Apply Button fails to highlight after entering code");
				logger.error("Apply Button fails to highlight after entering code");
			}

			click(AMDOnboardingScreen.objApplyBtn, "Apply button");
			verifyElementPresent(AMDOnboardingScreen.objFaceIcon, "Oops! label");
			String textMessage = getText(AMDOnboardingScreen.objSuccessDesc);
			if (textMessage.contains("You are not logged in")) {
				extent.extentLoggerPass("Invalid Code Message",
						"You are not logged in to apply prepaid code... message");
				logger.info("You are not logged in to apply prepaid code... message is displayed");
			} else {
				extent.extentLoggerFail("Invalid Code Message",
						"You are not logged in to apply prepaid code... message");
				logger.error("You are not logged in to apply prepaid code... message is not displayed");
			}
			verifyElementPresent(AMDOnboardingScreen.objRegisterBtn, "Register button");
			verifyElementPresentAndClick(AMDOnboardingScreen.objLoginBtn, "Login button");
			verifyElementPresent(AMDLoginScreen.objEmailIdField, "Login/Register screen");
			Back(1);
			click(AMDGenericObjects.objPopUpDivider, "Close PopUp");
		} else {
			waitTime(3000);
			boolean landingPage = verifyIsElementDisplayed(AMDHomePage.HomeIcon);
			if (landingPage) {
				logger.info("Intro screen is skipped for " + pUserType);
				extent.extentLoggerPass("Intro Skipped", "Intro screen is skipped and User landed in Home screen");
			} else {
				logger.info("Intro screen is Not skipped for " + pUserType);
				extent.extentLoggerFail("Intro Skipped", "Intro screen is NOT skipped for " + pUserType);
			}
		}
	}

	public void verifyInvalidPrepaidCodePopUpAfterLogin(String pEmailID, String pPassword) throws Exception {

		extent.HeaderChildNode("Verify Invalid prepaid code PopUp UI is displayed after successful login");
		System.out.println("\nVerify Invalid prepaid code PopUp UI is displayed after successful login");
		String pUserType = getParameterFromXML("userType");
		if (pUserType.contains("Guest")) {
			click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
			click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
			verifyElementPresentAndClick(AMDOnboardingScreen.objHavePrepaidBtn, "Have a prepaid code? CTA");
			click(AMDOnboardingScreen.objPrepaidCodeField, "Prepaidcode Field");
			type(AMDOnboardingScreen.objPrepaidCodeField, "CODE2020", "Prepaid Code field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDOnboardingScreen.objApplyBtn, "Apply button");
			verifyElementPresentAndClick(AMDOnboardingScreen.objLoginBtn, "Login button");
			LoginWithEmailID(pEmailID, pPassword);
			waitTime(5000);
			verifyElementPresent(AMDOnboardingScreen.objSuccessTitle("Oops!"), "Invalid Prepaid Code Pop up");
			String getPopUpDesc = getText(AMDOnboardingScreen.objSuccessDesc);
			if (getPopUpDesc.contains("Please Provide Valid Coupon code")) {
				extent.extentLoggerPass("Invalid Pop up", getPopUpDesc + "  message is displayed");
				logger.info(getPopUpDesc + "is displayed in PopUp screen");
			} else {
				extent.extentLoggerFail("Invalid Pop up", "Please provide valid coupon code message is not displayed");
				logger.error("Please provide valid coupon code is not displayed");
			}

			verifyElementPresentAndClick(AMDOnboardingScreen.objDoneBtn, "Done button");
			if (verifyElementPresent(AMDHomePage.objHome, "Home Screen")) {
				extent.extentLoggerPass("Login with registered user", pEmailID + " :  is logged in Successfully");
				logger.info(pEmailID + " is logged in  successfully");
			} else {
				extent.extentLoggerFail("Login with registered user", pEmailID + " failed to login");
				logger.error(pEmailID + " failed to login");
			}
		} else {
			boolean landingPage = verifyIsElementDisplayed(AMDHomePage.objHome);
			if (landingPage) {
				logger.info("Intro screen is skipped for " + pUserType);
				extent.extentLoggerPass("Intro Skipped", "Intro screen is skipped and User landed in Home screen");
			} else {
				logger.info("Intro screen is Not skipped for " + pUserType);
				extent.extentLoggerFail("Intro Skipped", "Intro screen is NOT skipped for " + pUserType);
			}
		}
	}

	public void verifyInvalidPrepaidCodePopUpAfterRegistration() throws Exception {

		extent.HeaderChildNode("Verify Invalid prepaid code PopUp UI is displayed after successful login");
		System.out.println("\nVerify Invalid prepaid code PopUp UI is displayed after successful login");
		String pUserType = getParameterFromXML("userType");
		if (pUserType.contains("Guest")) {
			click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
			click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
			verifyElementPresentAndClick(AMDOnboardingScreen.objHavePrepaidBtn, "Have a prepaid code? CTA");
			click(AMDOnboardingScreen.objPrepaidCodeField, "Prepaidcode Field");
			type(AMDOnboardingScreen.objPrepaidCodeField, "CODE2020", "Prepaid Code field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDOnboardingScreen.objApplyBtn, "Apply button");
			verifyElementPresentAndClick(AMDOnboardingScreen.objRegisterBtn, "Register button");
			Wait(1000);
			type(AMDRegistrationScreen.objEmailIDTextField, generateRandomString(6) + "@yopmail.com", "Email field");
			verifyElementPresentAndClick(AMDRegistrationScreen.objProceedBtn, "Proceed button");

			// ---Register new user
			registerForFreeScreen("Prepaid");

			verifyElementPresent(AMDOnboardingScreen.objSuccessTitle("Oops!"), "Invalid Prepaid Code Pop up");
			String getPopUpDesc = getText(AMDOnboardingScreen.objSuccessDesc);
			if (getPopUpDesc.contains("Please Provide Valid Coupon code")) {
				extent.extentLoggerPass("Invalid Pop up", getPopUpDesc + " is displayed in PopUp screen");
				logger.info(getPopUpDesc + "is displayed in PopUp screen");
			} else {
				extent.extentLoggerFail("Invalid Pop up", "Please provide valid coupon code is not displayed");
				logger.error("Please provide valid coupon code is not displayed");
			}

			verifyElementPresentAndClick(AMDOnboardingScreen.objDoneBtn, "Done button");
			if (verifyElementPresent(AMDHomePage.objHome, "Home Screen")) {
				extent.extentLoggerPass("Register New user", "User is registered to ZEE5 successfully");
				logger.info("User is registered to ZEE5 successfully");
			} else {
				extent.extentLoggerFail("Register New user", "User failed to register");
				logger.error("User failed to register");
			}
		} else {
			boolean landingPage = verifyIsElementDisplayed(AMDHomePage.HomeIcon);
			if (landingPage) {
				logger.info("Intro screen is skipped for " + pUserType);
				extent.extentLoggerPass("Intro Skipped", "Intro screen is skipped and User landed in Home screen");
			} else {
				logger.info("Intro screen is Not skipped for " + pUserType);
				extent.extentLoggerFail("Intro Skipped", "Intro screen is NOT skipped for " + pUserType);
			}
		}
	}

	// --------- Need a VALID Prepaid Code to verify Congratulations Popup screen
	// -----------

	public void verifyCongratulationPopupAppearsforValidPrepaidCode(String pCode, String pUserName, String pPassword)
			throws Exception {
		extent.HeaderChildNode("Verify Congratulation PopUp is displayed for valid Prepaid Code");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
		click(AMDOnboardingScreen.objHavePrepaidBtn, "Have a prepaid code? CTA");
		click(AMDOnboardingScreen.objPrepaidCodeField, "Prepaidcode Field");
		type(AMDOnboardingScreen.objPrepaidCodeField, pCode, "Valid Prepaid Code");
		hideKeyboard();
		verifyElementPresentAndClick(AMDOnboardingScreen.objApplyBtn, "Apply button");
		click(AMDOnboardingScreen.objLoginBtn, "Login button");
		LoginWithEmailID(pUserName, pPassword);

		verifyElementPresent(AMDOnboardingScreen.objSuccessTitle("Congratulations!"), "Congratulations! Pop up screen");
		String getPopUpDesc = getText(AMDOnboardingScreen.objSuccessDesc);
		if (getPopUpDesc.contains("Prepaid code applied successfully")) {
			extent.extentLoggerPass("Congrats Pop up", getPopUpDesc + " - message is displayed");
			logger.info(getPopUpDesc + " is displayed in PopUp screen");
		} else {
			extent.extentLoggerFail("Congrats Pop up", "Message - Prepaid code applied successfully is not displayed");
			logger.error("Message - Prepaid code applied successfully is not displayed");
		}

		verifyElementPresentAndClick(AMDOnboardingScreen.objWatchNowBtn, "Watch Now button");

		if (verifyElementPresent(AMDHomePage.objHomeTab, "Home page")) {
			extent.extentLoggerPass("Login with EmailId", pUserName + " : is logged in successfully");
			logger.info(pUserName + " is logged in  successfully");
		} else {
			extent.extentLoggerFail("Login with EmailId", pUserName + " failed to login");
			logger.error(pUserName + " failed to login");
		}
	}

	/**
	 * Author : Sushma
	 * 
	 * @throws Exception
	 */
	public void homeLandingScreen(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Navigating to Home screen and verifing the Subscribe icon");
		System.out.println("Home Landing screen and verifing the subscribe icon");

		// Selecting HOME tab from Top Navigation
		SelectTopNavigationTab(tabName);

		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase(tabName)) {
			logger.info(userType + " is able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLoggerPass(tabName + " Tab", userType + " is able to navigate to " + tabName
					+ " screen by tapping on " + tabName + " tab displayed in the top navigation bar");
		} else {
			logger.error(userType + " is not able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLoggerFail(tabName + " Tab", userType + " is not able to navigate to " + tabName
					+ " screen by tapping on " + tabName + " tab displayed in the top navigation bar");
		}
		waitTime(10000);
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is dislayed");
				extent.extentLoggerPass("Subscribe icon", "Subscribe icon is dislayed");
			} else {
				logger.error("subscribe icon is not dislayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is NOT dislayed");
			}
		} else {
			if (verifyElementIsNotDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("subscribe icon is NOT dislayed");
				extent.extentLoggerPass("Subscribe icon", "Subscribe icon is NOT dislayed");
			} else {
				logger.error("subscribe icon is dislayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is dislayed");
			}
		}

		String courselContentTitle = carouselValidationWithApi(userType, "homepage");
		carouselValidation(userType, tabName, courselContentTitle);
		click(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content");

		waitTime(5000);
		if (verifyIsElementDisplayed(AMDHomePage.objSubscribePopup)) {
//			Back(1);
			click(AMDGenericObjects.objPopUpDivider, "Pop Up divider");
		}
		if (userType.equalsIgnoreCase("Guest")) {
			if (verifyIsElementDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen)) {
				if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
					logger.error(
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
					extentLoggerFail("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
				} else {
					logger.info(
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
					extentLoggerPass("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
				}
			} else {
				if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
					logger.info(
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerPass("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
				} else {
					logger.error(
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerFail("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
				}
			}
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen)) {
				if (verifyIsElementDisplayed(AMDShowsScreen.objSubscribeNowlink)) {
					logger.error(
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
					extentLoggerFail("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
				} else {
					logger.info(
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
					extentLoggerPass("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
				}
			} else {
				if (verifyIsElementDisplayed(AMDShowsScreen.objSubscribeNowlink)) {
					logger.info(
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerPass("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
				} else {
					logger.error(
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerFail("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
				}
			}
		} else {
			logger.info("Content playback is initiated for the SubscribedUser post tapping on Premium Content");
			extentLoggerPass("Trailer",
					"Content playback is initiated for the SubscribedUser post tapping on Premium Content");
		}
		Back(1);
		extent.HeaderChildNode("Verifing the availability of trays in the screen");
		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Continue Watching"), AMDHomePage.objCarouselConetentCard,
				"Continue watching tray", "MastheadCarousel", userType, tabName);
		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Trending on"), AMDHomePage.objCarouselConetentCard,
				"Trending on Zee5 tray", "MastheadCarousel", userType, tabName);
		findingTrayInscreen(25, AMDHomePage.objTrayTitle("Trending Trailers"), AMDHomePage.objCarouselConetentCard,
				"Trending Trailers and Teasers tray", "Mastheadcarousel", userType, tabName);
		if (userType.equalsIgnoreCase("Guest")) {
			selectContentLang_MoreMenu2("English,Malayalam");
			waitTime(5000);
			closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
			findingTrayInscreen(25, AMDHomePage.objTrayTitle("Live Channels"), AMDHomePage.objCarouselConetentCard,
					"Live Channels tray", "Mastheadcarousel", userType, tabName);
			findingTrayInscreen(25, AMDHomePage.objTrayTitle("Malayalam Movie Bonanza"),
					AMDHomePage.objCarouselConetentCard, "Malayalam Movie Bonanza tray", "Mastheadcarousel", userType,
					tabName);
			deselectContentLang_MoreMenuAndSelectDefaultLanguage("English,Malayalam");
		}
		verifyContinueWatchingTray(userType, tabName);
		RemoveContentCardFromCWRail(userType);
	}

	public void selectContentLang_MoreMenu2(String planguage) throws Exception {

		click(AMDHomePage.HomeIcon, "Home button");
		click(AMDHomePage.MoreMenuIcon, "More Menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings CTA");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Settings"), "Settings Screen");
		Swipe("UP", 1);
		SwipeUntilFindElement(AMDMoreMenu.objContentLang, "UP");
		verifyElementPresentAndClick(AMDMoreMenu.objContentLang, "Content language");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Content Language"), "Content language screen");

		// ***** UnSelecting default content languages *****
		if (pUserType.contains("Guest")) {
			click(AMDOnboardingScreen.objSelectContentLang("English"), "English");
			PartialSwipe("UP", 2);
			waitTime(1000);
			click(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Kannada");
			Swipe("DOWN", 1);
		}

		// ***** Selecting required language *****
		if (planguage.contains(",")) {
			Swipe("DOWN", 1);
			String[] pLanguages = planguage.split(",");
			int n = pLanguages.length;
			for (int i = 0; i < n; i++) {

				for (int j = 0; j < 5; j++) {
					if (getDriver().findElements(AMDOnboardingScreen.objSelectContentLang(pLanguages[i])).size() == 0) {
						PartialSwipe("UP", 1);
					} else {
						verifyElementPresentAndClick(AMDOnboardingScreen.objSelectContentLang(pLanguages[i]),
								pLanguages[i]);
						break;
					}
				}
			}
		} else {
			outerLoop: for (int i = 1; i <= 4; i++) {
				int totalLanguages = getCount(AMDOnboardingScreen.objContentLangBtns);
				for (int j = 1; j <= totalLanguages; j++) {
					String visibleLang = getText(AMDOnboardingScreen.objgetContentLangName(j));
					if (planguage.equalsIgnoreCase(visibleLang)) {
						verifyElementPresentAndClick(AMDOnboardingScreen.objSelectContentLang(planguage), planguage);
						break outerLoop;
					}
				}
				PartialSwipe("UP", 1);
			}
		}
		waitTime(1000);
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button");
		waitTime(1000);
//	click(AMDGenericObjects.objBackBtn, "Back button");
		Back(1);
		click(AMDHomePage.HomeIcon, "Home button");
	}

	public static String carouselValidationWithApi(String userType, String pagenameforApi) {
		Response respPage = ResponseInstance.getResponseForApplicasterPages(userType, pagenameforApi);
//	System.out.println(respPage);

		List<String> bucketsSize = respPage.jsonPath().getList("buckets");
		logger.info("bucketsSize: " + bucketsSize.size());
		String carouselContentTitle = null;
		main: for (int i = 0; i < bucketsSize.size(); i++) {
			String description = respPage.jsonPath().getString("buckets[" + i + "].short_description");

			if ((description.equalsIgnoreCase("Home Page Slider")) | (description.equalsIgnoreCase("Movies Banner"))) {
				List<String> carouselItems = respPage.jsonPath().getList("buckets[" + i + "].items");
				logger.info("carouselItems: " + carouselItems.size());

				for (int j = 0; j < 7; j++) {
					carouselContentTitle = respPage.jsonPath().getString("buckets[" + i + "].items[" + j + "].title");
					logger.info(carouselContentTitle);

					String CarouselContentBusinessType = respPage.jsonPath()
							.getString("buckets[" + i + "].items[" + j + "].business_type");
					logger.info(CarouselContentBusinessType);

					if (CarouselContentBusinessType.equalsIgnoreCase("premium_downloadable")) {
						break main;
					}
				}
			}
		}
		return carouselContentTitle;
	}

	public void deselectContentLang_MoreMenuAndSelectDefaultLanguage(String planguage) throws Exception {

		click(AMDHomePage.HomeIcon, "Home button");
		click(AMDHomePage.MoreMenuIcon, "More Menu");
		Swipe("UP", 1);
		click(AMDMoreMenu.objSettings, "Settings CTA");
		verifyElementDisplayed(AMDGenericObjects.objScreenTitleName("Settings"));
		Swipe("UP", 1);
		click(AMDMoreMenu.objContentLang, "Content language");
		verifyElementDisplayed(AMDGenericObjects.objScreenTitleName("Content Language"));

		// ***** deSelecting selected language *****
		if (planguage.contains(",")) {
			Swipe("DOWN", 1);
			String[] pLanguages = planguage.split(",");
			int n = pLanguages.length;
			System.out.println(n);
			for (int i = 0; i < n; i++) {
				int totalLanguages = getCount(AMDOnboardingScreen.objContentLangBtns);
				for (int j = 1; j <= totalLanguages; j++) {
					String visibleLang = getText(AMDOnboardingScreen.objgetContentLangName(j));
					if (pLanguages[i].equalsIgnoreCase(visibleLang)) {
						click(AMDOnboardingScreen.objSelectContentLang(pLanguages[i]), pLanguages[i]);
					}
				}
				PartialSwipe("UP", 3);
			}
		} else {
			outerLoop: for (int i = 1; i <= 4; i++) {
				int totalLanguages = getCount(AMDOnboardingScreen.objContentLangBtns);
				for (int j = 1; j <= totalLanguages; j++) {
					String visibleLang = getText(AMDOnboardingScreen.objgetContentLangName(j));
					if (planguage.equalsIgnoreCase(visibleLang)) {
						click(AMDOnboardingScreen.objSelectContentLang(planguage), planguage);
						break outerLoop;
					}
				}
				PartialSwipe("UP", 1);
			}
		}

		// ***** Selecting default content languages *****
		if (pUserType.contains("Guest")) {
			Swipe("DOWN", 4);
			click(AMDOnboardingScreen.objSelectContentLang("English"), "English");
			PartialSwipe("UP", 2);
			waitTime(1000);
			click(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Kannada");
			Swipe("DOWN", 1);
		}
		waitTime(1000);
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button");
		waitTime(1000);
//	click(AMDGenericObjects.objBackBtn, "Back button");
		Back(1);
		click(AMDHomePage.HomeIcon, "Home button");
	}

	public void carouselValidation(String UserType, String tabName, String contentTitle) throws Exception {

		extent.HeaderChildNode("Carousel validations for tab :" + tabName + "\"");
		waitForElementDisplayed(AMDHomePage.objCarouselDots, 10);
		waitTime(10000);

		if (verifyElementDisplayed(AMDHomePage.objBannerAd)) {
			verifyElementPresent(AMDHomePage.objCarouselUnitwithmastHeadAdbanner,
					"Carousel unit as first unit on " + tabName + " screen");
		} else {
			verifyElementPresent(AMDHomePage.objCarouselUnitwhenNomastHeadAdbanner,
					"Carousel unit as first unit on " + tabName + " screen");
		}

		// Validating Carousel manual swipe
		String width = getAttributValue("width", AMDHomePage.objCarouselConetentCard);

		String bounds = getAttributValue("bounds", AMDHomePage.objCarouselConetentCard);
		String b = bounds.replaceAll(",", " ").replaceAll("]", " ");
		String height = b.split(" ")[1];

		waitTime(3000);
		String Carouseltitle1 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle1);
		extentLoggerPass("Carousel Title", Carouseltitle1);
		carouselCardsSwipe("LEFT", 1, width, height);

		String Carouseltitle2 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle2);
		extentLoggerPass("Carousel Title", Carouseltitle2);
		carouselCardsSwipe("RIGHT", 1, width, height);

		String Carouseltitle3 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle3);
		extentLoggerPass("Carousel Title", Carouseltitle3);

		if (!Carouseltitle1.equalsIgnoreCase(Carouseltitle2)) {
			logger.info(UserType + " is able to manually swipe banners from right to left or vice versa.");
			extent.extentLoggerPass("Carousel swipe",
					UserType + " is able to manually swipe banners from right to left or vice versa.");
		} else if (Carouseltitle3.equalsIgnoreCase(Carouseltitle1)) {
			logger.info(UserType + " is able to manually swipe banners from right to left or vice versa.");
			extent.extentLoggerPass("Carousel swipe",
					UserType + " is able to manually swipe banners from right to left or vice versa.");
		} else {
			logger.error(UserType + " is not able to manually swipe banners from right to left or vice versa.");
			extent.extentLoggerFail("Carousel swipe",
					UserType + " is NOT able to manually swipe banners from right to left or vice versa.");
		}

		// Validating Pagination dot, Play icon and GetPremium on Carousel
		int noofCarouselContents = findElements(AMDHomePage.objCarouselDots).size();
		for (int i = 1; i <= noofCarouselContents; i++) {
			logger.info(getText(AMDHomePage.objCarouselTitle1));

			// To Verify PAGINATION DOT displayed
			if (checkElementExist(AMDHomePage.objCarouselDots, "Pagination dot")) {
				logger.info("Pagination dot is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerPass("Pagination dot",
						"Pagination dot is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			} else {
				logger.error(
						"Pagination dot is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerFail("Pagination dot",
						"Pagination dot is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			}
			// To Verify PLAY ICON displayed
			if (checkElementExist(AMDHomePage.objPlayBtn, "Play icon")) {
				logger.info("Play icon is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerPass("Play icon",
						"Play icon is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			} else {
				logger.error("Play icon is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerFail("Play icon",
						"Play icon is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			}
			carouselCardsSwipe("LEFT", 1, width, height);
		}

		if ((UserType.equalsIgnoreCase("Guest")) | (UserType.equalsIgnoreCase("NonSubscribedUser"))) {
			for (int i = 1; i <= noofCarouselContents; i++) {

				if (tabName.equals("Music")) {
					extent.extentLogger("Verify Get Premium tag",
							"Get Premium tag is not configured for " + tabName + " tab");
					logger.info("Get Premium tag is not configured for " + tabName + " tab");
					break;

				} else {
					verifyElementExist(AMDHomePage.objPremiumBtn, "GetPremium tag");
					logger.info(getText(AMDHomePage.objCarouselTitle1));
					carouselCardsSwipe("LEFT", 1, width, height);
				}

				click(AMDHomePage.objPremiumBtn, "GetPremium tag");
				verifyElementPresent(AMDSubscibeScreen.objSubscribeHeader, "Subscription screen");
				Back(1);
				Swipe("Down", 1);
			}
		} else {
			for (int i = 1; i <= noofCarouselContents; i++) {

				System.out.println(getText(AMDHomePage.objCarouselTitle1));
				if (verifyElementIsNotDisplayed(AMDHomePage.objPremiumBtn)) {
					logger.info("GetPremium tag is NOT displayed");
					extentLoggerPass("Get premium Tag", "GetPremium tag is NOT displayed");
				} else {
					logger.error("GetPremium tag is displayed");
					extentLoggerFail("Get premium Tag", "GetPremium tag is displayed");
				}
				carouselCardsSwipe("LEFT", 1, width, height);
			}
		}

		// navigation to consumption screen of selected content

		verifyElementPresentAndClick(AMDHomePage.objContentTitle(contentTitle), "Carousel content");

		waitTime(5000);
		if (verifyIsElementDisplayed(AMDHomePage.objSubscribePopup)) {
			Back(1);
			// click(AMDGenericObjects.objPopUpDivider, "Pop Up divider");
		}

		verifyElementPresent(AMDHomePage.objConsumptionScreenTitle, "Consumption screen");
		String consumptionScreenTitle = getText(AMDHomePage.objConsumptionScreenTitle);

		if (contentTitle.equalsIgnoreCase(consumptionScreenTitle)) {
			logger.info("Consumption Screen is displayed for the selected content");
			extent.extentLoggerPass("Consumption screen", "Consumption Screen is displayed for the selected content");
		} else {
			logger.error("Consumption Screen is not displayed for the selected content");
			extent.extentLoggerFail("Consumption screen",
					"Consumption Screen is not displayed for the selected content");
		}
		Back(1);

		// Validating Carousel Auto scroll
		String title1 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(title1);
		extentLoggerPass("Carousel Title", title1);
		waitTime(4000);
		String title2 = getText(AMDHomePage.objCarouselTitle2);
		logger.info(title2);
		extentLoggerPass("Carousel Title", title2);
		waitTime(4000);
		String title3 = getText(AMDHomePage.objCarouselTitle3);
		logger.info(title3);
		extentLoggerPass("Carousel Title", title3);

		if (!(title1.equalsIgnoreCase(title2))) {
			if (!(title2.equalsIgnoreCase(title3))) {
				logger.info(
						"Banners available in feature carousel unit rotates from right to left at a fixed time interval");
				extentLoggerPass("Carousel unit Autorotation",
						"Banners available in feature carousel unit rotate from right to left at a fixed time interval");
			} else {
				logger.error(
						"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
				extentLoggerFail("Carousel unit Autorotation",
						"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
			}
		} else {
			logger.error(
					"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
			extentLoggerFail("Carousel unit Autorotation",
					"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
		}
	}

	@SuppressWarnings("rawtypes")
	public void carouselCardsSwipe(String direction, int count, String width, String height) throws Exception {
		touchAction = new TouchAction(getDriver());

		try {

			int yCordinate;
			if (verifyElementIsNotDisplayed(AMDHomePage.objAdBannerAboveCarousel)) {
				yCordinate = (int) ((Integer.valueOf(height)) * 0.4);
			} else {
				yCordinate = (int) ((Integer.valueOf(height)) * 0.5);
			}

			if (direction.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {

					int startx = (Integer.valueOf(width));
					startx = (int) (startx * 0.8);
					int endx = (int) (startx * 0.1);

					int starty = (Integer.valueOf(height)) + yCordinate;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");
					extent.extentLoggerPass("SwipeLeft",
							"Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");

					System.out.println("\n<<< Swipe <<<");
					System.out.println("[X,Y]: [" + startx + "," + starty + "] ===> [" + endx + "," + starty + "]");
				}
			} else if (direction.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					int startx = (int) ((Integer.valueOf(width)) * 0.1);
					int endx = (int) ((Integer.valueOf(width)) * 0.8);
					int starty = (Integer.valueOf(height)) + yCordinate;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();

					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");
					extent.extentLoggerPass("SwipeRight",
							"Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");

					System.out.println("\n>>> Swipe >>>");
					System.out.println("[X,Y]: [" + startx + "," + starty + "] ===> [" + endx + "," + starty + "]");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public void carouselSwipe(String direction, int count, String width, String height) throws Exception {
		touchAction = new TouchAction(getDriver());

		try {
			if (direction.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {

					int startx = (Integer.valueOf(width)) - 200;
					int endx = 100;
					int starty = (Integer.valueOf(height)) + 300;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");
					extent.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");
				}
			} else if (direction.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					int startx = 100;
					int endx = (Integer.valueOf(width)) - 200;
					int starty = (Integer.valueOf(height)) + 300;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();

					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeRight",
							"Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");
				}
			}
		} catch (Exception e) {
			logger.error(e);

		}
	}

	public void findingTrayInscreen(int j, By byLocator1, By byLocator2, String str1, String str2, String userType,
			String tabName) throws Exception {
		boolean tray = false;
		for (int i = 0; i < j; i++) {
			if (!((verifyIsElementDisplayed(byLocator1)))) {
				Swipe("UP", 1);
			} else {
				verifyElementExist(byLocator1, str1);
				tray = true;
				if (tabName.equalsIgnoreCase("Home")) {
					if (str1.equalsIgnoreCase("Continue watching tray")) {

						Response respCW = ResponseInstance.getRespofCWTray(userType);

						List<String> ApinoOfContentsInCW = respCW.jsonPath().getList("array");
						logger.info("no.of contents in CW tray in Api " + ApinoOfContentsInCW.size());

						ArrayList<String> listOfContentsInCW = new ArrayList<String>();

						for (int k = 0; k < ApinoOfContentsInCW.size(); k++) {

							String title = respCW.jsonPath().getString("[" + k + "].title");
							listOfContentsInCW.add(title);
						}

						logger.info(listOfContentsInCW);

						for (int p = 0; p < ApinoOfContentsInCW.size(); p++) {

							verifyElementExist(AMDHomePage.objContentTitleOfCWTray(listOfContentsInCW.get(p)),
									"content title");

							if (verifyElementDisplayed(AMDHomePage.objLeftTimeOfFirstContentOfCWTray)) {
								logger.info("Left watch time info on cards is available");
								extent.extentLoggerPass("Left watch time info",
										"Left watch time info on cards is available");
							} else {
								logger.error("Left watch time info on cards is not available");
								extent.extentLoggerFail("Left watch time info",
										"Left watch time info on cards is not available");
							}
							if (verifyElementDisplayed(AMDHomePage.objProgressBarOfFirstContentOfCWTray)) {
								logger.info("Progress bar is displayed to indicate the content watched portion");
								extent.extentLoggerPass("Progress bar",
										"Progress bar is displayed to indicate the content watched portion");
							} else {
								logger.error("Progress bar is not displayed to indicate the content watched portion");
								extent.extentLoggerFail("Progress bar",
										"Progress bar is not displayed to indicate the content watched portion");
							}
							if (p != (ApinoOfContentsInCW.size() - 1)) {
								SwipeRail(AMDHomePage.objContentTitleOfCWTray(listOfContentsInCW.get(p + 1)));
							}
						}
					}
				}
				break;
			}
		}
		if (tray == false) {
			if (userType.equalsIgnoreCase("Guest")) {
				if (str1.equalsIgnoreCase("Continue watching tray")) {
					logger.info(str1 + " is not displayed for Guest user");
					extent.extentLoggerPass("Tray", str1 + " is not displayed for Guest user");
				} else {
					logger.error(str1 + " is not displayed");
					extent.extentLoggerWarning("Tray", str1 + " is not displayed");
				}
			} else {
				if (tabName.equalsIgnoreCase("Home")) {

					if (str1.equalsIgnoreCase("Continue watching tray")) {

						Response respCW = ResponseInstance.getRespofCWTray(userType);

						List<String> ApinoOfContentsInCW = respCW.jsonPath().getList("array");
						logger.info("no.of contents in CW tray in Api " + ApinoOfContentsInCW.size());

						if (ApinoOfContentsInCW.size() == 0) {

							logger.info(str1 + " is not displayed for this user");
							extent.extentLoggerPass("Tray", str1 + " is not displayed for this user");
						} else {
							logger.error(str1 + " is not displayed for this user");
							extent.extentLoggerWarning("Tray", str1 + " is not displayed for this user");
						}
					}
					logger.error(str1 + " is not displayed");
					extent.extentLoggerWarning("Tray", str1 + " is not displayed");
				} else {
					logger.error(str1 + " is not displayed");
					extent.extentLoggerWarning("Tray", str1 + " is not displayed");
				}
			}
		}
		for (int i = 0; i < j; i++) {
			if (!(verifyIsElementDisplayed(byLocator2))) {
				Swipe("DOWN", 1);
			} else {
				verifyElementExist(byLocator2, str2);
				break;
			}
		}
	}

	public void adBannerVerify(String tabname) throws Exception {
		extent.HeaderChildNode("Masthead and Display Ad verification");
		System.out.println("Masthead and Display Ad verification");
		waitTime(20000);
		closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 10000);

		getDriver().findElement(By.xpath("//*[@id='title' and @text='" + tabname + "']")).click();
		waitTime(10000);

		if (verifyElementDisplayed(AMDHomePage.objBannerAd)) {
			logger.info("Masthead and Display ads are displayed properly in " + tabname + " screen");
			extent.extentLogger("Ads", "Masthead and Display ads are displayed properly in " + tabname + " screen");
		} else {
			logger.info("Masthead and Display ads are not displayed properly in " + tabname + " screen");
			extent.extentLoggerFail("Ads",
					"Masthead and Display ads are not displayed properly in " + tabname + " screen");
		}
	}

	/**
	 * Author : Bhavana Module : Download Screen Validation
	 */

	public void EmptystateScreenValidation(String userType) throws Exception {
		extent.HeaderChildNode("Downloads screen Empty-state validation as " + userType);
		System.out.println("\nDownloads screen Empty-state validation as: " + userType);
		click(AMDHomePage.objBottomNavigation("Downloads"), "Downloads tab");
		waitTime(3000);
		if (verifyElementExist(AMDDownloadPage.objDwnloadsHeader,
				"Downloads header at the top on center of the screen")) {
			extent.extentLogger("Downloads tab",
					"User is navigated to Downloads screen on tapping Downloads button present in the bottom navigation bar");
			logger.info(
					"User is navigated to Downloads screen on tapping Downloads button present in the bottom navigation bar");
		} else {
			extent.extentLoggerFail("Downloads tab", "User fails to navigate to Downloads screen");
			logger.error("User fails to navigate to Downloads screen");
		}
		Back(1);
		click(AMDHomePage.objBottomNavigation("Downloads"), "Downloads tab");

		if (verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn,
				"Browse to Download CTA in Empty-state screen to download")) {
			extent.extentLogger("Downloads screen",
					"User is navigated to Empty-state screen when no downloaded contents are present");
			logger.info("User is navigated to Empty-state screen when no downloaded contents are present");
		} else {
			extent.extentLoggerFail("Downloads screen",
					"User fails to navigate to Empty-state screen when no downloaded contents are present");
			logger.error("User fails to navigate to Empty-state screen when no downloaded contents are present");
		}
		verifyElementPresentAndClick(AMDDownloadPage.objBrowseToDownloadBtn,
				"Browse to Download CTA in Empty-state screen");
		waitTime(3000);
		String getPropertyshows = getAttributValue("enabled", AMDDownloadPage.objshowstab);
		if (getPropertyshows.equalsIgnoreCase("true")) {
			extent.extentLogger("Shows tab",
					"“Browse to Download” button is be tappable in the Empty-state screen to Download the content ");
			logger.info(
					"“Browse to Download” button is be tappable in the Empty-state screen to Download the contents");
		} else {
			extent.extentLoggerFail("Shows tab", "User fails to tap the 'Browse to Download' button");
			logger.error("User fails to tap the 'Browse to Download' button");
		}
		click(AMDHomePage.objBottomNavigation("Downloads"), "Downloads tab");
	}

	public void validationofDownloadingContent() throws Exception {
		extent.HeaderChildNode("Validating funtionality of Pause and Continue Download CTA call-Out option");
		System.out.println("\nValidating funtionality of Pause and Continue Download CTA call-Out option");

		DownloadContent(content2, pVideoQuality, true);
		if (checkElementExist(AMDDownloadPage.objDownloadingText)) {
			extent.extentLoggerPass("Donwloading Content", "Downloading content is displayed in Downloads screen");
			logger.info("Downloading content is displayed in Donwloads screen");
		} else {
			extent.extentLoggerFail("Donwloading Content", "Downloading content is not displayed in Downloads screen");
			logger.error("Downloading content is not displayed in Downloads screen");
		}
		verifyElementExist(AMDDownloadPage.objDownloadingText, "'Downloading' text");
		click(AMDDownloadPage.objDownloadingText, "Downloading text");
		verifyElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading circular bar");
		click(AMDDownloadPage.objDownloadingCircularBar, "Downloading circular bar");
		if (checkElementExist(AMDDownloadPage.objPauseDownloadoption, "Call out with Pause option")) {
			extent.extentLoggerPass("Pause", "Pause option is displayed when user taps Downloding content");
			logger.info("Pause option is displayed when user taps Downloding content");
		} else {
			extent.extentLoggerFail("Pause", "Pause option is NOT displayed when user taps Downloding content");
			logger.error("Pause option is NOT displayed when user taps Downloding content");
		}
		waitTime(2000);
		if (checkElementExist(AMDDownloadPage.objCancelDownloadOption, "Call out with Cancel Download option")) {
			extent.extentLoggerPass("Pause", "Cancel Download is displayed when user taps Downloding content");
			logger.info("Cancel Download is displayed when user taps Downloding content");
		} else {
			extent.extentLoggerFail("Pause", "Cancel Download is NOT displayed when user taps Downloding content");
			logger.error("Cancel Download NOT is displayed when user taps Downloding content");
		}
		click(AMDDownloadPage.objPauseDownloadoption, "Pause option");
		verifyElementExist(AMDDownloadPage.objPausedText, "'Paused' text");
		if (checkElementExist(AMDDownloadPage.objPausedBar, "Paused bar")) {
			extent.extentLoggerPass("Pause", "User is able to Pause the Downloading content on tapping 'Pause' option");
			logger.info("User is able to Pause the Downloading content on tapping 'Pause' option");
		} else {
			extent.extentLoggerFail("Pause",
					"User is fails to Pause the Downloading content on tapping 'Pause' option");
			logger.error("User fails to Pause the Downloading content on tapping 'Pause' option");
		}
		click(AMDDownloadPage.objPausedBar, "Paused bar");
		verifyElementPresentAndClick(AMDDownloadPage.objContinueOption, "Continue option");
		if (checkElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading circular bar")) {
			extent.extentLoggerPass("Re-start", "User is able to re-start the Paused content");
			logger.info("User is able to re-start the Paused content");
		} else {
			extent.extentLoggerFail("Re-start", "User fails to re-start the Paused content");
			logger.error("User fails to re-start the Paused content");
		}
	}

	public void DownloadContent(String str) throws Exception {
		System.out.println("\nInitiate Download : " + str);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search Icon");
//		verifyElementPresentAndClick(AMDDownloadPage.objSearchIcon, "Search Icon");
		waitTime(3000);
		click(AMDSearchScreen.objSearchEditBox, "Search edit");
		type(AMDSearchScreen.objSearchBoxBar, str, "Search Field");
		waitTime(3000);
		hideKeyboard();
		click(AMDSearchScreen.objSelectFirstEpisodeResult, "Searched Show");
		waitTime(5000);
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadIcon, "Download button");
		verifyElementExist(AMDDownloadPage.objDownloadVideoQualityPopup, "Download video quality Pop up");
		click(AMDDownloadPage.objStartDownloadCTA, "Start Download CTA");
		waitTime(2000);
		Back(1);
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
	}

	public void pauseAllAndCancelDownload() throws Exception {
		extent.HeaderChildNode("Validating Call-Out options with Pause All and Cancel Download CTA");
		System.out.println("\nValidating Call-Out options with Pause All and Cancel Download CTA");

		verifyElementPresentAndClick(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon");
		verifyElementExist(AMDDownloadPage.objCallOutwithPauseAll, "Call out with Pause and Cancel Download CTAs");
		verifyElementExist(AMDDownloadPage.objPauseAllOption, "Pause All CTA");
		verifyElementExist(AMDDownloadPage.objCancelDownloadOption, "Cancel Download CTA");
		click(AMDDownloadPage.objPauseAllOption, "Pause All CTA");
		if (checkElementExist(AMDDownloadPage.objPausedText, "'Paused' text")) {
			extent.extentLoggerPass("Pause All", "Queued contents are Paused and in Paused state");
			logger.info("Queued contents are Paused and in Paused state");
		}
		click(AMDDownloadPage.objPausedIcon("Comedy Kiladigalu Championship Episode 24"), "Paused icon");
		click(AMDDownloadPage.objRetryCTA, "Continue option");
		int totalEpisodesList = getDriver().findElements(AMDDownloadPage.objNoOfEpisodeList).size();
		logger.info(totalEpisodesList);
		click(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon");
		click(AMDDownloadPage.objCancelDownloadOption, "Cancel Download CTA");
		waitTime(3000);
		int totalEpisodesList2 = getDriver().findElements(AMDDownloadPage.objNoOfEpisodeList).size();
		logger.info(totalEpisodesList2);
		if (totalEpisodesList != totalEpisodesList2) {
			extent.extentLoggerPass("Cancel Download", "Downloading content is deleted");
			logger.info("Downloading content is deleted");
		} else {
			extent.extentLoggerFail("Cancel Download",
					"Downloading content is not deleted on tapping Cancel Download CTA");
			logger.error("Downloading content is not deleted on tapping Cancel Download CTA");
		}
	}

	public void DownloadsSection() throws Exception {
		extent.HeaderChildNode("Validating Downloads Page section");
		System.out.println("\nValidating Downloads Page section");

		verifyElementExist(AMDDownloadPage.objTitleoftheShow, "Title of the show");
		System.out.println("Title of the Show is " + getText(AMDDownloadPage.objTitleoftheShow));
		logger.info("Title of the Show is " + getText(AMDDownloadPage.objTitleoftheShow));
		extent.extentLoggerPass("Title", "Title of the Show is " + getText(AMDDownloadPage.objTitleoftheShow));
		verifyElementExist(AMDDownloadPage.objNoOfEpisodes, "Number of Episodes");
		System.out.println("Number of Episodes are " + getText(AMDDownloadPage.objNoOfEpisodes));
		logger.info("Number of Episodes are " + getText(AMDDownloadPage.objNoOfEpisodes));
		extent.extentLoggerPass("Episodes", "Number of Episodes are " + getText(AMDDownloadPage.objNoOfEpisodes));
		verifyElementExist(AMDDownloadPage.objSizeOfEpiodes, "Size of Episodes");
		System.out.println("Size of Episodes is " + getText(AMDDownloadPage.objSizeOfEpiodes));
		logger.info("Size of Episodes is " + getText(AMDDownloadPage.objSizeOfEpiodes));
		extent.extentLoggerPass("Size", "Size of Episodes is " + getText(AMDDownloadPage.objSizeOfEpiodes));
		verifyElementExist(AMDDownloadPage.objRightArrowinDownloads, "Right Arrow");
		verifyElementExist(AMDDownloadPage.objThumbnailOfShows, "Thumbnail of the Show");
		waitTime(2000);
		click(AMDDownloadPage.objTitleoftheShow, "Title of the show");
		if (checkElementExist(AMDDownloadPage.objDownloadMoreCTA, "Download More CTA")) {
			extent.extentLoggerPass("Download More", "Show name is Tappable");
			logger.info("Show name is Tappable");
		} else {
			extent.extentLoggerFail("Download More", "Show name NOT is Tappable");
			logger.error("Show name is NOT Tappable");
		}
		Back(1);
		click(AMDDownloadPage.objThumbnailOfShows, "Thumbnail of the Show");
		if (checkElementExist(AMDDownloadPage.objDownloadMoreCTA, "Download More CTA")) {
			extent.extentLoggerPass("Download More", "Thumbnail is Tappable");
			logger.info("Thumbnail is Tappable");
		} else {
			extent.extentLoggerFail("Download More", "Thumbnail is NOT Tappable");
			logger.error("Thumbnail is NOT Tappable");
		}
		Back(1);
		click(AMDDownloadPage.objRightArrowinDownloads, "Right Arrow");
		if (checkElementExist(AMDDownloadPage.objDownloadMoreCTA, "Download More CTA")) {
			extent.extentLoggerPass("Download More", "Right Arrow Tappable");
			logger.info("Right Arrow is Tappable");
		} else {
			extent.extentLoggerFail("Download More", "Right Arrow Tappable");
			logger.error("Right Arrow is Tappable");
		}
	}

	public void LatestEpisode() throws Exception {
		waitTime(3000);
		extent.HeaderChildNode("Validating New Episode");
		System.out.println("Validating New Episode");
		if (checkElementExist(AMDDownloadPage.objNewEpisodeContent, "New Episode on top")) {
			extent.extentLogger("New Epiosde on top ", "New Episode on top is displayed");
			logger.info("New Episode on top is displayed");
		} else {
			extent.extentLoggerFail("New Epiosde on top ", "New Episode on top is NOT displayed");
			logger.error("New Episode on top is NOT displayed");
		}
		String titleOfNewEpisode = getText(AMDDownloadPage.objtitleofNewEpisode);
		System.out.println(titleOfNewEpisode);
		if (checkElementExist(AMDDownloadPage.objNewEpisodeTag, "'New Episode' text")) {
			extent.extentLogger("New Epiosde text", "New Episode text is displayed");
			logger.info("New Episode text is displayed");
		} else {
			extent.extentLoggerFail("New Epiosde text", "New Episode text is NOT displayed");
			logger.error("New Episode text is NOT displayed");
		}
		if (checkElementExist(AMDDownloadPage.objThumbnailOfLatestEpisode, "Thumbnail of Latest Episode")) {
			extent.extentLogger("Thumbnail of Latest Episode", "Thumbnail of Latest Episode is displayed");
			logger.info("Thumbnail of Latest Episode is displayed");
		} else {
			extent.extentLoggerFail("Thumbnail of Latest Episode", "Thumbnail of Latest Episode NOT is displayed");
			logger.error("Thumbnail of Latest Episode is NOT displayed");
		}
		verifyElementExist(AMDDownloadPage.objDownloadNowbbtn, "Download Now button");
		verifyElementExist(AMDDownloadPage.objClosebtn, "Cancel button (X)");
		waitTime(2000);
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadNowbbtn, "Download Now button");
		if (checkElementExist(AMDDownloadPage.objDownloadVideoQualityPopup, "Download popup")) {
			extent.extentLogger("Download Now ", "Download Now button is functional");
			logger.info("Download Now button is functional");
		} else {
			extent.extentLoggerFail("Download Now ", "Download Now button is NOT functional");
			logger.error("Download Now button is NOT functional");
		}
		Back(1);
		click(AMDDownloadPage.objClosebtn, "Cancel button (X)");
		if ((checkElementExist(AMDDownloadPage.objNewEpisodeTag, "New Episode")) == false) {
			extent.extentLogger("Cancel button", "Cancel button is Tappable");
			logger.info("Cancel button is Tappable");
		} else {
			extent.extentLoggerFail("Cancel button", "Cancel button is NOT Tappable");
			logger.error("Cancel button is NOT Tappable");
		}
		if ((checkElementExist(AMDDownloadPage.objThumbnailOfLatestEpisode, "Latest Episode")) == false) {
			extent.extentLogger("New Episode", "Latest Epiosde is not displayed on the top");
			logger.info("Latest Epiosde is not displayed on the top");
		} else {
			extent.extentLoggerFail("New Episode", "Latest Epiosde is not displayed on the top");
			logger.error("Latest Epiosde is not displayed on the top");
		}
		waitTime(2000);
		Back(1);
		click(AMDDownloadPage.objRightArrowinDownloads, "Right Arrow");
		waitTime(2000);
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadNowbbtn, "Download Now button");
		click(AMDDownloadPage.objDownloadVideoQualityPopup, "Download video quality Pop up");
		click(AMDDownloadPage.objStartDownloadCTA, "Start Download CTA");
		waitTime(2000);
		verifyElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon");
		Back(1);
		verifyElementExist(AMDDownloadPage.objDownloadingText, "Downloading text");
		click(AMDDownloadPage.objDownloadingText, "Downloading text");
		if (checkElementExist(AMDDownloadPage.objEpisodesList, "Episoed List")) {
			extent.extentLogger("Episodes list", "Episodes list is displayed");
			logger.info("Episodes list is displayed");
		} else {
			extent.extentLoggerFail("Episodes list", "Episodes list is NOT displayed");
			logger.error("Episodes list is NOT displayed");
		}
		if ((checkElementExist(AMDDownloadPage.objClosebtn, "Cross (X) icon")) == false) {
			extent.extentLogger("Close button",
					"Cross (X) icon is not displayed when New Episode download is in progress");
			logger.info("Cross (X) icon is not displayed when New Episode download is in progress");
		} else {
			extent.extentLogger("Close button", "Cross (X) icon is displayed when New Episode download is in progress");
			logger.info("Cross (X) icon is displayed when New Episode download is in progress");
		}
		if (checkElementExist(AMDDownloadPage.objtitleofNewEpisode, titleOfNewEpisode)) {
			extent.extentLogger("Episodes list", "New Episode on top of the screen is displayed");
			logger.info("New Episode on top is displayed");
		} else {
			extent.extentLoggerFail("Episodes list", "New Episode on top of the screen is NOT displayed");
			logger.error("New Episode on top is NOT displayed");
		}
	}

	public void DownloadingOffline() throws Exception {
		extent.HeaderChildNode("Validation Downloads in Offline mode");
		System.out.println("\nValidation Downloads in Offline mode");

		// *** Verifying download in offline Mode
		setWifiConnectionToONOFF("Off");

		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
			waitTime(2000);
		}
		waitForElementDisplayed(AMDDownloadPage.objDownloadFailedText, 2000);
		verifyElementExist(AMDDownloadPage.objDownloadFailedText, "Download Failed text");
		verifyElementExist(AMDDownloadPage.objDownloadErrorText, "Download Error text");
		click(AMDDownloadPage.objDownloadErrorText, "Download Error text");
		verifyElementExist(AMDDownloadPage.objRetryCTA, "Retry CTA Call-Out");
		verifyElementExist(AMDDownloadPage.objCancelDownloadOption, "Cancel Download CTA Call-Out");

		setWifiConnectionToONOFF("ON");
		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
			waitTime(7000);
			waitForElementDisplayed(AMDDownloadPage.objRetryCTA, 2000);
		}
		click(AMDDownloadPage.objRetryCTA, "Call out with Retry CTA");
		waitTime(5000);
		if (checkElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading circular bar")) {
			extent.extentLoggerPass("Re-start",
					"User is able to tap the Retry CTA and resumed downloading the content");
			logger.info("User is able to tap the Retry CTA and resumed downloading the content");
		} else {
			extent.extentLoggerFail("Re-start",
					"User fails to tap the Retry CTA and fail to resume downloading the content");
			logger.error("User fails to tap the Retry CTA and fail to resume downloading the content");
		}
	}

	public void verifyDownloadsSubscribeUser() throws Exception {
		extent.HeaderChildNode("Verify the download screen");
		System.out.println("\nVerify the download screen");
		verifyElementExist(AMDHomePage.objDownloadBtn, "Downloads tab at the bottom navigation bar");
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		waitTime(2000);
		String SubscriptionExpierstext = getDriver().findElement(AMDDownloadPage.objPackExpiredText).getText();
		System.out.println(SubscriptionExpierstext);
		if (SubscriptionExpierstext.equalsIgnoreCase("Your Subscription expires in 10 days")) {
			logger.info("Your Premium subscription expires in XX days text message is displayed");
			extent.extentLogger("Download Screen",
					"Your Premium subscription expires in XX days text message is displayed");
		} else {
			logger.error("Your Premium subscription expires in XX days text message is displayed");
			extent.extentLoggerFail("Download Screen",
					"Your Premium subscription expires in XX days text message is displayed");
		}
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search Icon");
		waitTime(3000);
		click(AMDSearchScreen.objSearchEditBox, "Search edit");
		type(AMDSearchScreen.objSearchBoxBar, "Dil Bechara - Title Track", "Search Field");
		waitTime(5000);
		hideKeyboard();
		click(AMDDownloadPage.objSearchedContent, "Searched Show");
		waitTime(3000);
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadIcon, "Download button");
		waitTime(2000);
		click(AMDDownloadPage.objDownloadVideoQualityPopup, "Download video quality Pop up");
		click(AMDDownloadPage.objStartDownloadCTA, "Start Download CTA");
		waitTime(1000);
		Back(1);
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		verifyElementExist(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen ");
		waitTime(2000);
		verifyElementExist(AMDDownloadPage.objDownloadedTickMark, "Tick Mark on the downloaded content");
		click(AMDDownloadPage.objDownloadedTickMark, "Tick Mark on the downloaded content");
		verifyElementExist(AMDDownloadPage.objDeleteDownloadOption, "Delete downloaded content");
		click(AMDDownloadPage.objDeleteDownloadOption, "Delete downloaded content");
		waitTime(4000);
		if (verifyElementExist(AMDDownloadPage.objDownloadTextIcon, "Download Icon")) {
			logger.info("Content is deleted by tapping on delete download");
			extent.extentLogger("Download Screen", "Content is deleted by tapping on delete download");
		} else {
			logger.error("Content is deleted by tapping on delete download");
			extent.extentLoggerFail("Download Screen", "Content is deleted by tapping on delete download");
		}
		verifyElementExist(AMDDownloadPage.objRemaindMeLater, "Remaind Me Later");
		click(AMDDownloadPage.objRemaindMeLater, "Remaind Me Later");
		if (checkElementExist(AMDDownloadPage.objPackExpiredText, "Premium pack expires Text")) {
			logger.info("Premium pack expires text is not displayed by tapping on Remaind Me Later");
			extent.extentLogger("Download Screen",
					"Premium pack expires text is not displayed by tapping on Remaind Me Later");
		} else {
			logger.error("Premium pack expires text is not displayed by tapping on Remaind Me Later");
			extent.extentLoggerFail("Download Screen",
					"Premium pack expires text is not displayed by tapping on Remaind Me Later");
		}

	}

	public void DownloadScreenValidation(String userType) throws Exception {
		System.out.println("\nDownload Landing Screen Validation");
		if (userType.contentEquals("NonSubscribedUser") || userType.contentEquals("SubscribedUser")) {

			EmptystateScreenValidation(userType);
			verifyDownloadsWithSingleTire();
			verifyMovieContentInDownloadsScreen(pMovie, pVideoQuality);
			validationofDownloadingContent();

			int totalEpisodesList = getDriver().findElements(AMDDownloadPage.objNoOfEpisodeList).size();
			logger.info("Content Cards: " + totalEpisodesList);
			// --- Download check in Offline Mode
			DownloadingOffline();

			extent.HeaderChildNode("Verify Deleted Content from Downloads screen");
			System.out.println("\nVerify Deleted Content from Downloads screen");

			click(AMDDownloadPage.objDownloadingCircularBar, "Downloading circular bar");
			click(AMDDownloadPage.objCancelDownloadOption, "Cancel Download CTA");

			int totalEpisodesList2 = getDriver().findElements(AMDDownloadPage.objNoOfEpisodeList).size();
			logger.info("Content Cards: " + totalEpisodesList2);
			if (totalEpisodesList != totalEpisodesList2) {
				extent.extentLoggerPass("Cancel Download", "Downloading content is deleted");
				logger.info("Downloading content is deleted");
			} else {
				extent.extentLoggerFail("Cancel Download", "Downloading content is NOT deleted");
				logger.error("Downloading content is NOT deleted");
			}
			waitTime(2000);
			Back(1);
			String getProperty1 = getAttributValue("enabled", AMDHomePage.objDownloadBtn);
			if (getProperty1.equalsIgnoreCase("true")) {
				extent.extentLoggerPass("Downloads Tab",
						"Downloads active page without content downloading is displayed");
				logger.info("Downloads active page without content downloading is displayed");
			} else {
				extent.extentLoggerFail("Downloads tab",
						"No Downloads active page without content downloading is NOT displayed");
				logger.error("No Downloads active page without content downloading is NOT displayed");
			}

			extent.HeaderChildNode("Verify multiple downloading content in Downloads screen");
			System.out.println("\nVerify multiple downloading content in Downloads screen");

			// **** Download Episode content3
			DownloadContent(content3, pVideoQuality, true);
			if (checkElementExist(AMDDownloadPage.objDownloadingText)) {
				extent.extentLoggerPass("Downloading Content", "Downloading content is displayed in Downloads screen");
				logger.info("Downloading content is displayed in Donwloads screen");
			} else {
				extent.extentLoggerFail("Downloading Content",
						"Downloading content is not displayed in Downloads screen");
				logger.error("Downloading content is not displayed in Downloads screen");
			}

			// **** Download Episode content4
			DownloadContent(content4, "Better", true);
			if (checkElementExist(AMDDownloadPage.objDownloadingText)) {
				extent.extentLoggerPass("Donwloading Content", "Downloading content is displayed in Downloads screen");
				logger.info("Downloading content is displayed in Donwloads screen");
			} else {
				extent.extentLoggerFail("Donwloading Content",
						"Downloading content is not displayed in Downloads screen");
				logger.error("Downloading content is not displayed in Downloads screen");
			}
			if (checkElementExist(AMDDownloadPage.objShowsDownloadPage)) {
				extent.extentLoggerPass("ShowsList", "Shows list is displayed in the upfront tab");
				logger.info("Shows list is displayed in the upfront tab");
			} else {
				extent.extentLoggerFail("ShowsList", "Shows list is NOT displayed in the upfront tab");
				logger.error("Shows list is NOT displayed in the upfront tab");
			}
			waitTime(3000);
			click(AMDDownloadPage.objDownloadingText, "Downloading text");
			if (checkElementExist(AMDDownloadPage.objDownloadingCircularBar)) {
				extent.extentLoggerPass("Queued", "User is able to Download only one content at a time");
				logger.info("User is able to Download only one content at a time");
			} else {
				extent.extentLoggerFail("Queued", "User fails to Download only one content at a time");
				logger.error("User fails to Download only one content at a time");
			}
			if (checkElementExist(AMDDownloadPage.objQueuedbar("Comedy Kiladigalu Championship Episode 9"),
					"Paused icon")) {
				extent.extentLoggerPass("Queued", "Contents are Queued up in a line ");
				logger.info("Contents are Queued up in a line ");
			} else {
				extent.extentLoggerFail("Queued", "Contents are NOT Queued up in a line ");
				logger.error("Contents are NOT Queued up in a line ");
			}
			pauseAllAndCancelDownload();
			click(AMDDownloadPage.objPausedBar, "Paused icon");
			click(AMDDownloadPage.objRetryCTA, "Continue option");
			waitTime(2000);
			Back(1);

			// **** Download Episode content5
			DownloadContent(content5, pVideoQuality, true);
			waitTime(1000);
			click(AMDDownloadPage.objDownloadingText, "Downloading text");
			if (checkElementExist(AMDDownloadPage.objDownloadingCircularBar)) {
				extent.extentLoggerPass("Downloading", "Incomplete Downloads are available");
				logger.info("Incomplete Downloads are available");
			}
			click(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon");
			if (checkElementExist(AMDDownloadPage.objCallOutwithPauseAll)) {
				extent.extentLoggerPass("Downloading", "Incomplete Downloads are NOT allowed to be Play");
				logger.info("Incomplete Downloads are NOT allowed to be Play");
			} else {
				extent.extentLoggerFail("Downloading", "Incomplete Downloads are allowed to be Play");
				logger.error("Incomplete Downloads are allowed to be Play");
			}
//			switchNetworkWifiToData();
			Back(2);

			// **** Download Episode content6
			DownloadContent(content6, "Best", true);
			DownloadsSection();
			LatestEpisodeOnTheTop();
			click(AMDHomePage.HomeIcon, "Home Icon");
		}
		if (userType.contentEquals("SubscribedUser")) {
			ZNALogoutMethod();
			ValidateSubscriptionExpireBanner();
		}
	}

	public void DownloadContent(String contentName, String Quality, boolean checkAlwaysAskOption) throws Exception {
		System.out.println("\nInitiate Download : " + contentName);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search Icon");
		waitTime(3000);
		click(AMDSearchScreen.objSearchEditBox, "Search edit");
		type(AMDSearchScreen.objSearchBoxBar, contentName, "Search Field");
		waitTime(3000);
		hideKeyboard();
		click(AMDSearchScreen.objSelectFirstEpisodeResult, "Searched Show");
		waitForElementDisplayed(AMDDownloadPage.objPauseIconOnPlayer, 2000);
		waitTime(3000);
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadIcon, "Download button");
		waitTime(2000);
		DownloadVideoQualityPopUp(Quality, checkAlwaysAskOption);
		waitTime(2000);
		Back(1);
		click(AMDHomePage.objBottomNavigation("Downloads"), "Downloads tab");
	}

	public void DownloadVideoQualityPopUp(String Quality, boolean checkAlwaysAsk) throws Exception {

		verifyElementExist(AMDDownloadPage.objDownloadVideoQualityPopup, "Download Video Quality PopUp screen");
		click(AMDDownloadPage.objDownloadQualityOptions(Quality), Quality);

		String getValue = getAttributValue("checked", AMDDownloadPage.objAlwaysAskCheckBox);
		if (checkAlwaysAsk) {
			if (!getValue.contains("true")) {
				click(AMDDownloadPage.objAlwaysAskCheckBox, "Always ask quality for every download");
			}
		} else {
			if (getValue.contains("true")) {
				click(AMDDownloadPage.objAlwaysAskCheckBox, "Always ask quality for every download");
			}
		}
		click(AMDDownloadPage.objStartDownloadCTA, "Start Download CTA");
	}

	public void switchNetworkWifiToData() throws Exception {
		extent.HeaderChildNode("Validating Downloading resumes after switching network");
		System.out.println("\nValidating Downloading resumes after switching network");

		// Switching Network from Wifi -> Data
		Runtime.getRuntime().exec("adb shell svc wifi disable");
		Runtime.getRuntime().exec("adb shell svc data enable");
		if (checkElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon")) {
			extent.extentLogger("Downloading", "User is able to continue the download on shuffling wifi to data");
			logger.info("User is able to continue the download on shuffling wifi to data");
		}
		waitTime(2000);
		// Switching Network from Data -> Wifi
		Runtime.getRuntime().exec("adb shell svc data disable");
		Runtime.getRuntime().exec("adb shell svc wifi enable");
		if (checkElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon")) {
			extent.extentLogger("Downloading", "User is able to continue the download on shuffling data to wifi");
			logger.info("User is able to continue the download on shuffling wifi to data");
		}
	}

	public void LatestEpisodeOnTheTop() throws Exception {
		extent.HeaderChildNode("Validating New Episode Overlay on the Top");
		System.out.println("\nValidating New Episode Overlay on the Top");

		if (checkElementExist(AMDDownloadPage.objNewEpisodeContent)) {
			extent.extentLoggerPass("New Epiosde on top ", "New Episode content card is displayed on the Top");
			logger.info("New Episode on top is displayed");
		} else {
			extent.extentLoggerWarning("New Epiosde on top ", "New Episode content card is NOT displayed on the Top");
			logger.info("New Episode on top is NOT displayed");
		}

		if (checkElementExist(AMDDownloadPage.objNewEpisodeTag)) {
			String titleOfNewEpisode = getText(AMDDownloadPage.objtitleofNewEpisode);
			extent.extentLoggerPass("New Epiosde title", "Episode title is: " + titleOfNewEpisode);
			logger.info("Episode title is: " + titleOfNewEpisode);
		} else {
			extent.extentLoggerWarning("New Epiosde title", "Episode title is NOT displayed");
			logger.error("Episode title is NOT displayed");
		}

		if (checkElementExist(AMDDownloadPage.objThumbnailOfLatestEpisode)) {
			extent.extentLoggerPass("Thumbnail of Latest Episode", "Thumbnail Image of Latest Episode is displayed");
			logger.info("Thumbnail of Latest Episode is displayed");
		} else {
			extent.extentLoggerWarning("Thumbnail of Latest Episode", "Thumbnail of Latest Episode NOT is displayed");
			logger.error("Thumbnail Image of Latest Episode is NOT displayed");
		}
		verifyElementExist(AMDDownloadPage.objDownloadNowbbtn, "Download Now CTA");
		verifyElementPresentAndClick(AMDDownloadPage.objClosebtn, "Close button (X)");
		Back(1);
		click(AMDDownloadPage.objRightArrowinDownloads, "Right Arrow");
		if (checkElementExist(AMDDownloadPage.objDownloadNowbbtn)) {
			extent.extentLoggerPass("Latest Epiosde", "Latest Epiosde is displayed on the top");
			logger.info("Latest Epiosde is not displayed on the top");
		} else {
			extent.extentLoggerWarning("Latest Epiosde", "Latest Epiosde is NOT displayed on the top");
			logger.error("Latest Epiosde is displayed on the top");
		}
		Back(1);
	}

	public void verifyMovieContentInDownloadsScreen(String MovieName, String Quality) throws Exception {
		extent.HeaderChildNode("Validating the downloading content in Movies tab");
		System.out.println("\nValidating the downloading content in Movies tab");

		DownloadContent(MovieName, Quality, true);
		String getPropertyValue = getAttributValue("enabled", AMDDownloadPage.objmoviestab);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			extent.extentLoggerPass("MOVIES Tab is highlighted",
					"User is taken to MOVIES tab by default, since download is in-progress..");
			logger.info("User is taken to MOVIES tab by default, since download is in-progress..");
		} else {
			extent.extentLoggerFail("MOVIES Tab",
					"User is NOT taken to MOVIES tab by default, even after initiating Movie content download");
			logger.error("User is NOT taken to MOVIES tab by default, even after initiating Movie content download");
		}

		if (checkElementExist(AMDDownloadPage.objDownloadingCircularBar)) {
			String getMovieName = getText(AMDDownloadPage.objDownloadedContent);
			click(AMDDownloadPage.objDownloadedContent, getMovieName);
			click(AMDDownloadPage.objCancelDownloadOption, "Cancel CTA");
			extent.extentLoggerPass("Downloading Movie",
					getMovieName + " movie is downloading in the Downloads screen");
			logger.info(getMovieName + " movie is downloading in Downloads screen");
		} else {
			extent.extentLoggerFail("Donwloading Movie",
					"Downloading " + MovieName + " is NOT displayed in the Downloads screen");
			logger.error("Downloading " + MovieName + " is NOT displayed in Downloads screen");
		}
	}

	/**
	 * Author : Bhavana Module : Download Screen Validation
	 */
	public void DownloadScreenUIUXValidation(String userType) throws Exception {
		extent.HeaderChildNode("Verify the UI/UX of Download landing screen as " + userType);
		System.out.println("\nVerify the UI/UX of Download landing screen as " + userType);
		waitTime(5000);
		verifyElementExist(AMDHomePage.objBottomNavigation("Downloads"), "Downloads tab at the bottom navigation bar");
		click(AMDHomePage.objBottomNavigation("Downloads"), "Downloads tab");
		waitTime(3000);
		verifyElementExist(AMDDownloadPage.objDwnloadsHeader, "Downloads header at the top on center of the screen");
		verifyElementExist(AMDDownloadPage.objshowstab, "Shows tab in Downloads landing screen");
		verifyElementExist(AMDDownloadPage.objmoviestab, "Movies tab in Downlaods landing screen");
		verifyElementExist(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen ");
		String getPropertyValue = getAttributValue("enabled", AMDDownloadPage.objshowstab);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			extent.extentLoggerPass("Shows tab", "Shows tab is by default highlighted");
			logger.info("Shows tab is by default highlighted");
		} else {
			extent.extentLoggerFail("Shows tab", "Shows tab fails to highlight by default");
			logger.error("Shows tab fails to highlight by default");
		}
		click(AMDDownloadPage.objshowstab, "Shows tab in Downloads landing screen");
		verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Shows tab");
		click(AMDDownloadPage.objmoviestab, "Movies tab in Downlaods landing screen");
		verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Movies tab");
		click(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen");
		verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Videos tab");
	}

	public void BrowseToDownloadFunctionality(String userType) throws Exception {
		extent.HeaderChildNode("Verify Browse to Download CTA functionality as " + userType);
		System.out.println("\nVerify Browse to Download CTA functionality as " + userType);
		String getSelectedTabName;
		click(AMDDownloadPage.objshowstab, "Shows tab in Downloads landing screen");
		click(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Shows tab");
		waitTime(3000);
		getSelectedTabName = getText(AMDHomePage.objSelectedTab);
		if (getSelectedTabName.equalsIgnoreCase("Shows")) {
			extent.extentLoggerPass("Shows tab", "User is navigated to Shows landing page");
			logger.info("User is navigated to Shows landing page");
		} else {
			extent.extentLoggerFail("Shows tab", "User fails to navigate to Shows landing page and instead displayed : "
					+ getSelectedTabName + " landing screen");
			logger.error("User fails to navigate to Shows landing page and instead displayed : " + getSelectedTabName
					+ " landing screen");
		}
		click(AMDHomePage.objBottomNavigation("Downloads"), "Downloads tab");
		verifyElementPresentAndClick(AMDDownloadPage.objmoviestab, "Movies tab in Downlaods landing screen");
		verifyElementPresentAndClick(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Movies tab");
		waitTime(3000);
		getSelectedTabName = getText(AMDHomePage.objSelectedTab);
		if (getSelectedTabName.equalsIgnoreCase("Movies")) {
			extent.extentLoggerPass("Movies tab", "User is navigated to Movies landing page");
			logger.info("User is navigated to Movies landing page");
		} else {
			extent.extentLoggerFail("Movies tab",
					"User fails to navigate to Movies landing page and instead displayed : " + getSelectedTabName
							+ " landing screen");
			logger.error("User fails to navigate to Movies landing page and instead displayed : " + getSelectedTabName
					+ " landing screen");
		}
		click(AMDHomePage.objBottomNavigation("Downloads"), "Downloads tab");
		verifyElementPresentAndClick(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen");
		verifyElementPresentAndClick(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Videos tab");
		waitTime(3000);
		getSelectedTabName = getText(AMDHomePage.objSelectedTab);
		if (getSelectedTabName.equalsIgnoreCase("Videos")) {
			extent.extentLoggerPass("Videos tab", "User is navigated to videos landing page");
			logger.info("User is navigated to Videos landing page");
		} else {
			extent.extentLoggerFail("Movies tab",
					"User fails to navigate to Videos landing page and instead displayed : " + getSelectedTabName
							+ " landing screen");
			logger.error("User fails to navigate to Videos landing page and instead displayed : " + getSelectedTabName
					+ " landing screen");
		}
	}

	public void ZNALogoutMethod() throws Exception {
		verifyElementExist(AMDHomePage.objHomeTab, "Home tab");
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		waitTime(2000);
		PartialSwipe("UP", 2);
		verifyElementPresentAndClick(AMDHomePage.objLogout, "Logout");
		verifyElementPresentAndClick(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
		verifyElementPresentAndClick(AMDHomePage.HomeIcon, "Home Icon");
	}

	public void ValidateSubscriptionExpireBanner(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Verify the download screen");
		System.out.println("\nVerify the download screen");
		ZeeApplicasterLogin(LoginMethod);
		verifyElementExist(AMDHomePage.objDownloadBtn, "Downloads tab at the bottom navigation bar");
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		waitTime(2000);
		String SubscriptionExpiersText = getDriver().findElement(AMDDownloadPage.objPackExpiredText).getText();
		System.out.println(SubscriptionExpiersText);
		if (SubscriptionExpiersText.contains("Your Subscription expires in")) {
			logger.info(SubscriptionExpiersText + "text message is displayed");
			extent.extentLogger("Download Screen", SubscriptionExpiersText + "text message is displayed");
		} else {
			logger.error(SubscriptionExpiersText + "text message is displayed");
			extent.extentLoggerFail("Download Screen", SubscriptionExpiersText + "text message is not displayed");
		}
		waitTime(2000);
		verifyElementExist(AMDDownloadPage.objRemaindMeLater, "Remind Me Later");
		click(AMDDownloadPage.objRemaindMeLater, "Remind Me Later");
		if (verifyElementNotPresent(AMDDownloadPage.objPackExpiredText, 2)) {
			logger.info("Premium pack expires text is not displayed by tapping on Remaind Me Later");
			extent.extentLogger("Download Screen",
					"Premium pack expires text is not displayed by tapping on Remaind Me Later");
		}
	}

	public void verifyDownloadsWithSingleTire() throws Exception {
		extent.HeaderChildNode(
				"Validating Video DownloadScreen and Content playback of downloaded Video with Single tier content");
		System.out.println(
				"\nValidating Video DownloadScreen and Content playback of downloaded Video with Single tier content");

		verifyElementPresentAndClick(AMDSearchScreen.objDownloadsOption, "Downloading Icon");
		waitTime(2000);
		DownloadContent(content1, "Good", true);
		String DownloadedContentText = getDriver().findElement(AMDDownloadPage.objDownloadedVideoContent).getText();
		System.out.println(DownloadedContentText);
		if (DownloadedContentText.contains(content1)) {
			logger.info("Downloaded Video content is displayed in the Upfront screen of the Videos Tab");
			extent.extentLoggerPass("Download Screen",
					"Downloaded Video content is displayed in the Upfront screen of the Videos Tab");
		} else {
			logger.error("Downloaded Video content is NOT displayed in the Upfront screen of the Videos Tab");
			extent.extentLoggerFail("Download Screen",
					"Downloaded Video content is NOT displayed in the Upfront screen of the Videos Tab");
		}
		Back(1);
		waitTime(3000);
		click(AMDSearchScreen.objDownloadsOption, "Downloading Icon");
		String getPropertyValue = getAttributValue("enabled", AMDDownloadPage.objvideostab);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			extent.extentLoggerPass("Videos tab",
					"Videos tab is by default highlighted, User is taken to VIDEOS tab by default, hence Shows and Movies tab do not have any downloaded content");
			logger.info(
					"Videos tab is by default highlighted, User is taken to VIDEOS tab by default, hence Shows and Movies tab do not have any downloaded content");
		} else {
			extent.extentLoggerFail("Videos tab",
					"Videos tab fails to highlight by default, User is NOT taken to VIDEOS tab by default, even though Shows/Movies has no downloaded content");
			logger.error(
					"Videos tab fails to highlight by default, User is NOT taken to VIDEOS tab by default, even though Shows/Movies has no downloaded content");
		}

		verifyElementPresentAndClick(AMDDownloadPage.objvideostab, "Videos tab in Downloads screen");
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadedVideoContent, content1);
		verifyElementPresentAndClick(AMDDownloadPage.objPlayDownloadedContent, "Play Call-out");

		if (verifyElementDisplayed(AMDDownloadPage.objPauseIconOnPlayer)) {
			extent.extentLoggerPass("Video Content", "Playback Started for Video Content : " + content1);
			logger.info("Playback Started for Video Content : " + content1);
		} else {
			logger.error("Playback failed to Start for Video Content - " + content1);
			extent.extentLoggerFail("Download Screen", "Playback failed to Start for the Video Content - " + content1);
		}
		Back(1);

		verifyElementPresentAndClick(AMDDownloadPage.objmoviestab, "Movies tab in Downlaods landing screen");
		if (checkElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Movies tab")) {
			extent.extentLoggerPass("Movies tab", "Movies Tab not having any downloaded content");
			logger.info("Movies Tab not having any downloaded content");
		}

		verifyElementPresentAndClick(AMDDownloadPage.objshowstab, "Shows tab in Downloads landing screen");
		if (verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Shows tab")) {
			extent.extentLoggerPass("Shows tab", "Shows Tab not having any downloaded content");
			logger.info("Shows Tab not having any downloaded content");
		}
	}

	public void ValidateSubscriptionExpireBanner() throws Exception {
		extent.HeaderChildNode("Validating Subscription Expiry banner in Donwloads Screen");
		System.out.println("\nValidating Subscription Expiry banner in Donwloads Screen");
		click(AMDHomePage.objMoreMenu, "More Menu");
		click(AMDMoreMenu.objProfile, "Profile");

		String SubscribedUserWith15daysExpiryUsername = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("SubscribedUserWith15daysExpiryUsername");
		String SubscribedUserWith15daysExpiryPassword = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("SubscribedUserWith15daysExpiryPassword");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

		LoginWithEmailID(SubscribedUserWith15daysExpiryUsername, SubscribedUserWith15daysExpiryPassword);
		waitTime(3000);
		verifyElementExist(AMDHomePage.objDownloadBtn, "Downloads tab at the bottom navigation bar");
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		click(AMDDownloadPage.objmoviestab, "Movies tab");

		if (verifyIsElementDisplayed(AMDDownloadPage.objPackExpiredText)) {
			String SubscriptionExpiersText = getDriver().findElement(AMDDownloadPage.objPackExpiredText).getText();
			System.out.println(SubscriptionExpiersText);
			if (SubscriptionExpiersText.contains("Your Subscription expires in")) {
				logger.info(SubscriptionExpiersText + "  is displayed");
				extent.extentLoggerPass("Downloads Screen", SubscriptionExpiersText + " is displayed");
			} else {
				logger.error("Your Subscription expires in X-days is NOT displayed");
				extent.extentLoggerFail("Downloads Screen", "Your Subscription expires in X-days is NOT displayed");
			}
		} else {
			logger.error("Subscription is about to Expire message is NOT displayed");
			extent.extentLoggerWarning("Your Subscription expires",
					"Subscription is about to Expire message is NOT displayed");
		}

		waitTime(2000);
		verifyElementExist(AMDDownloadPage.objRemaindMeLater, "Remind Me Later");
		click(AMDDownloadPage.objRemaindMeLater, "Remind Me Later");
		if (checkElementExist(AMDDownloadPage.objIcon)) {
			logger.info("Premium pack expires message is not displayed on tapping - Remaind Me Later");
			extent.extentLoggerPass("Downloads Screen",
					"Premium pack expires text is not displayed on tapping - Remaind Me Later");
		}
		click(AMDHomePage.HomeIcon, "Home Icon");
	}

	/**
	 * Author : Bindu Module : Shows Screen
	 */
	public void verifyShowsScreen(String userType, String TabName) throws Exception {
		extent.HeaderChildNode("Verify user navigates to shows screen");
		System.out.println("\nVerify User navigated to Shows Screen");

		// Selecting Shows tab from Top Navigation
		verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
		waitTime(2000);

		String getPropertyValue = getAttributValue("enabled", AMDHomePage.objShowsTab);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			logger.info(userType
					+ " is able to navigate to Shows screen by tapping on Shows tab displayed in the top navigation bar");
			extent.extentLoggerPass("Shows Tab", userType
					+ " is able to navigate to Shows screen by tapping on Shows tab displayed in the top navigation bar");
		} else {
			logger.error(userType
					+ " is not able to navigate to Shows screen by tapping on Shows tab displayed in the top navigation bar");
			extent.extentLoggerFail("Shows Tab", userType
					+ " is not able to navigate to Shows screen by tapping on Shows tab displayed in the top navigation bar");
		}

		if ((userType.equalsIgnoreCase("Guest")) | (userType.equalsIgnoreCase("NonSubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is dislayed on the header of the Shows Landing Screen");
				extent.extentLoggerPass("Subscribe icon",
						"Subscribe icon is dislayed on the header of the Shows Landing Screen");
			} else {
				logger.error("Subscribe icon is not dislayed on the header of the Shows Landing Screen");
				extent.extentLoggerFail("Subscribe icon",
						"Subscribe icon is not dislayed on the header of the Shows Landing Screen");
			}
		} else {

			if (verifyElementIsNotDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is not dislayed on the header of the Shows Landing Screen");
				extent.extentLoggerPass("Subscribe icon",
						"Subscribe icon is not dislayed on the header of the Shows Landing Screen");

			} else {
				logger.error("Subscribe icon is dislayed on the header of the Shows Landing Screen");
				extent.extentLoggerFail("Subscribe icon",
						"Subscribe icon is dislayed on the header of the Shows Landing Screen");
			}
		}
		carouselValidationforShowsAndNews(userType, "Shows");
		String CarouselTitle = ShowsScreenValidationwithApiData(userType);
		if (CarouselTitle == null) {
			logger.info("No premium content present in the carousel to validate Content Playback");
			extent.extentLoggerPass("Content Playback",
					"No premium content present in the carousel to validate Content Playback");
		} else {
			verifyElementPresentAndClick(AMDHomePage.objContentTitle(CarouselTitle), "Carousel content");
			if (userType.equalsIgnoreCase("Guest")) {
				if (verifyIsElementDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen)) {
					if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
						logger.error(
								"Content playback is not initiated for the user post tapping on premium content which is having trailer");
						extentLoggerFail("Trailer",
								"Content playback is not initiated for the user post tapping on premium content which is having trailer");
					} else {
						logger.info(
								"Content playback is initiated for the user post tapping on premium content which is having trailer");
						extentLoggerPass("Trailer",
								"Content playback is initiated for the user post tapping on premium content which is having trailer");
					}
				} else {
					if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
						logger.info(
								"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
						extentLoggerPass("Trailer",
								"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
					} else {
						logger.error(
								"Content playback is initiated for the user post tapping on premium content which is not having trailer");
						extentLoggerFail("Trailer",
								"Content playback is initiated for the user post tapping on premium content which is not having trailer");
					}
				}
			} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen)) {
					if (verifyIsElementDisplayed(AMDShowsScreen.objSubscribeNowlink)) {
						logger.error(
								"Content playback is not initiated for the user post tapping on premium content which is having trailer");
						extentLoggerFail("Trailer",
								"Content playback is not initiated for the user post tapping on premium content which is having trailer");
					} else {
						logger.info(
								"Content playback is initiated for the user post tapping on premium content which is having trailer");
						extentLoggerPass("Trailer",
								"Content playback is initiated for the user post tapping on premium content which is having trailer");
					}
				} else {
					if (verifyIsElementDisplayed(AMDShowsScreen.objSubscribeNowlink)) {
						logger.info(
								"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
						extentLoggerPass("Trailer",
								"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
					} else {
						logger.error(
								"Content playback is initiated for the user post tapping on premium content which is not having trailer");
						extentLoggerFail("Trailer",
								"Content playback is initiated for the user post tapping on premium content which is not having trailer");
					}
				}
			} else {
				logger.info("Content playback is initiated for the SubscribedUser post tapping on Premium Content");
				extentLoggerPass("Trailer",
						"Content playback is initiated for the SubscribedUser post tapping on Premium Content");
			}
			Back(1);
		}
		extent.HeaderChildNode("verify the trays present in Shows Landing Screen");
		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Continue Watching"), AMDHomePage.objCarouselConetentCard,
				"Continue watching tray", "MastheadCarousel", userType, "Shows");
		findingTrayInscreen(20, AMDHomePage.objTrayTitle("Before"), AMDHomePage.objCarouselConetentCard,
				"Before TV tray", "MastheadCarousel", userType, "Shows");
		findingTrayInscreen(25, AMDHomePage.objTrayTitle("Trending Shows"), AMDHomePage.objCarouselConetentCard,
				"Trending shows tray", "MastheadCarousel", userType, "Shows");
		verifyContinueWatchingTray(userType, TabName);
		RemoveContentCardFromCWRail(userType);
	}

	public void RemoveContentCardFromCWRail(String userType) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser") | userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("verify user can able to delete content in continue watching tray");
			// Swipe("UP",1);
			boolean ContinueWatchingTray = verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTray);
			if (ContinueWatchingTray) {
				boolean CWTrayContent = verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTrayContentCard);
				if (CWTrayContent) {
					String CWTrayFirstContent = getText(AMDHomePage.objCWTrayContent);
					logger.info(CWTrayFirstContent);
					verifyElementPresentAndClick(AMDHomePage.objRemoveItem,
							"Remove Icon On Continue Watching Tray Content");
					waitTime(2000);
					boolean CWTrayDeletedContent = verifyIsElementDisplayed(
							AMDHomePage.objContentTitleOfCWTray(CWTrayFirstContent));
					if (!(CWTrayDeletedContent)) {
						logger.info("Content Deleted from the Contnue Watching tray");
						extent.extentLoggerPass("Delete Content", "Content Deleted from the Contnue Watching tray");
					} else {
						logger.info("Failed to delete Content from Continue Watching tray");
						extent.extentLoggerFail("Delete Content",
								"Failed to delete Content from Continue Watching tray");
					}
				}
			} else {
				logger.info("Continue Watching tray not displayed");
				extent.extentLoggerWarning("Continue Watching Tray", "Continue Watching tray not displayed");
			}
		}
	}

	public void verifyCarouselCollectionListingscreen(String userType) throws Exception {
		extent.HeaderChildNode(
				"Verify user navigated to collection Listing screen post tapping on any collection available on carousel");
		System.out.println(
				"Verify user navigated to collection Listing screen post tapping on any collection available on carousel");
		if (userType.equals("Guser")) {
			selectContentLang_MoreMenu("Hindi");
			verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
		}
		waitTime(1000);

		click(AMDNewsPage.objCarouselCollectionContent, "Carousel content");
		verifyElementPresent(AMDNewsPage.objListingScreen, "Screen Header");
		logger.info("Listing Collection Screen is displayed post tapping on any collection available on corousel");
		extent.extentLogger("Listing Collection screen",
				"Listing Collection Screen is displayed post tapping on any collection available on corousel");
		verifyElementPresent(AMDLoginScreen.objBackBtn, "Back Button");
		click(AMDLoginScreen.objBackBtn, "Back Button");
		if (userType.equals("Guest")) {
			selectContentLang_MoreMenu("Hindi");
			verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
		}
	}

	public void verifyConsumptionScreen(String userType) throws Exception {
		extent.HeaderChildNode(
				"Verify user navigated to consumption screen post tapping on any where on the banner section");
		System.out
				.println("Verify user navigated to consumption scree post tapping on any where on the banner section");
		PartialSwipe("UP", 1);
		verifyElementPresentAndClick(AMDShowsScreen.objcontentCard, "Banner card");
		waitTime(6000);

		if (checkElementExist(AMDShowsScreen.objPlayer, "Player")) {
			logger.info("User is navigated to Consumption Screen");
			extent.extentLogger("Consumption Screen", "User is navigated to Consumption Screen");

		} else {
			logger.info("User is not navigated to Consumption Screen");
			extent.extentLoggerFail("Consumption Screen", "User is not navigated to Consumption Screen");
		}

		Back(1);
		extent.HeaderChildNode("Verify Watch Next tray is available on Shows screen");
		System.out.println("\nVerify Watch Nest Tray is available on Shows screen");
		PartialSwipe("UP", 1);

		if (checkElementExist(AMDShowsScreen.objWatchNextTray, "Watch Next Tray")) {
			logger.info("Watch Next tray is displayed in Shows landing screen");
			extent.extentLogger("Shows Screen", "Watch Next tray is displayed in Shows landing screen");
		} else {
			logger.info("Watch Next tray is not displayed in Shows landing screen");
			extent.extentLoggerFail("Shows Screen", "Watch Next tray is not displayed in Shows landing screen");
		}
	}

	public void verifyConsumptionScreenOfBeforeTVContent(String userType) throws Exception {
		extent.HeaderChildNode("Before TV tray validation");
		System.out.println("\nBefore TV tray validation");

		// Selecting SHOWS tab from Top Navigation
		verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
		waitTime(6000);

		for (int i = 1; i < 25; i++) {
			if (!(verifyIsElementDisplayed(AMDHomePage.objBeforeTVTray))) {
				Swipe("UP", 1);
				// break;
			} else {
				waitTime(5000);
				verifyElementPresent(AMDHomePage.objBeforeTVTray, "BeforeTV tray");
				waitTime(4000);

				if (userType.equals("SubscribedUser")) {
					click(AMDHomePage.objBeforeTVTray, "BeforeTV tray");
					waitTime(2000);
					click(AMDShowsScreen.objBeforeTVContent1, "BeforeTV content");
					waitTime(10000);
					if (checkElementExist(AMDPlayerScreen.objPlayerScreen, "Player screen")) {
						logger.info(
								"Content playback is initiated for the subscribed user on tapping any Before Tv Content");
						extent.extentLoggerPass("Consumption Screen",
								"Content playback is initiated for the subscribed user on tapping any Before Tv Content");
					} else {
						logger.info(
								"Content playback is not initiated for the subscribed user on tapping any Before Tv Content");
						extent.extentLoggerFail("Consumption Screen",
								"Content playback is not initiated for the subscribed user on tapping any Before Tv Content");
					}
					Back(2);
				}
				break;
			}
		}
	}

	@SuppressWarnings("unused")
	public String ShowsScreenValidationwithApiData(String userType) throws Exception {
		extent.HeaderChildNode("Shows Premium Content validation with API Data");
		// verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
		Response resp = ResponseInstance.getResponseForApplicasterPages(userType, "tvshows");
		List<String> apitotaltrays = resp.jsonPath().getList("buckets");
		System.out.println(apitotaltrays.size());
		String CarouselTitle = null;
		boolean Carousel = false;
		for (int i = 0; i < 7; i++) {

			String businessType = resp.jsonPath().getString("buckets[" + i + "].items");
			if (businessType.equalsIgnoreCase("premium_downloadable")) {
				Carousel = true;
				CarouselTitle = resp.jsonPath().getString("buckets[" + i + "].items[" + i + "].title");
				System.out.println(CarouselTitle);
				break;
			}
		}
		return CarouselTitle;
	}

	/**
	 * Author : Manasa
	 */
	public void upcomingSectionValidation() throws Exception {
		extent.HeaderChildNode("Upcoming Screen Validation");
		// waitTime(5000);
		waitForElementDisplayed(AMDHomePage.objHomeTab, 10);
		verifyElementPresentAndClick(AMDHomePage.objUpcoming, "Upcoming tab");

		WebElement element = findElement(AMDHomePage.objUpcoming);
		int leftX = element.getLocation().getX();
		int rightX = leftX + element.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		Dimension size = getDriver().manage().window().getSize();
		if (middleX == Integer.valueOf((size.width) / 2)) {
			logger.info("Upcoming text is displayed at the center of the screen");
			extent.extentLoggerPass("Title", "Upcoming text is displayed at center of the screen");
		} else {
			logger.error("Upcoming text is not displayed at center of the screen");
			extent.extentLoggerFail("Title", "Upcoming text is not displayed at center of the screen");
		}

		WebElement searchIcon = findElement(AMDHomePage.objSearchBtn);
		int searchIconRightX = searchIcon.getLocation().getX();
		System.out.println(searchIconRightX);
		Dimension sizee = getDriver().manage().window().getSize();
		System.out.println(sizee.getWidth());
		int sizeee = sizee.getWidth() - 300;
		System.out.println(sizeee);

		if (searchIconRightX >= sizeee) {
			logger.info("Search icon is displayed at top right of the screen");
			extent.extentLoggerPass("Search icon", "Search icon is displayed at top right of the screen");
		} else {
			logger.error("Search icon is not displayed at top right of the screen");
			extent.extentLoggerFail("Search icon", "Search icon is not displayed at top right of the screen");
		}
		Response resp = ResponseInstance.getResponseForUpcomingPage();

		String titleWithTrailer = resp.jsonPath().getString("buckets[0].items[0].original_title");
		System.out.println("API Title " + titleWithTrailer);
		verifyElementPresentAndClick(AMDUpcomingPage.objContentCard1, "Content Card");
		String titleConsumptionScreen = getText(AMDUpcomingPage.objContentCardTitle1);
		System.out.println(titleConsumptionScreen);
		if (titleConsumptionScreen.contains(titleWithTrailer)) {
			logger.info("Navigated to appropriate consumption screen on tapping anywhere on any content card");
			extent.extentLoggerPass("Title",
					"Navigated to appropriate consumption screen on tapping anywhere on any content card");
			logger.info("Trailer/Teaser playback is played");
			extent.extentLoggerPass("Trailer/Teaser", "Trailer/Teaser playback is played");

		} else {
			logger.error("Not navigated to appropriate consumption screen on tapping anywhere on any content card");
			extent.extentLoggerFail("Title",
					"Not navigated to appropriate consumption screen on tapping anywhere on any content card");
			logger.error("Trailer/Teaser playback is not played");
			extent.extentLoggerFail("Trailer/Teaser", "Trailer/Teaser playback is not played");
		}
		checkElementExist(AMDMoreMenu.objDownloadIcon, "Download icon");
		Back(1);
		verifyElementPresentAndClick(AMDUpcomingPage.objContentCardTitle, "Metadata");
		if (titleConsumptionScreen.contains(titleWithTrailer)) {
			logger.info("Navigated to appropriate consumption screen on tapping anywhere on the metadata");
			extent.extentLoggerPass("Title",
					"Navigated to appropriate consumption screen on tapping anywhere on the metadata");
		} else {
			logger.info("Not navigated to appropriate consumption screen on tapping anywhere on the metadata");
			extent.extentLoggerFail("Title",
					"Not navigated to appropriate consumption screen on tapping anywhere on the metadata");
		}
		Back(1);
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search icon");
		if (verifyIsElementDisplayed(AMDSearchScreen.objMicrophoneIcon)) {
			logger.info("Search landing screen is displayed after denying audio permission");
			extent.extentLoggerPass("Search landing screen",
					"Search landing screen is displayed after denying audio permission");
		} else {
			logger.error("Search landing screen is not displayed after denying audio permission");
			extent.extentLogger("Search landing screen",
					"Search landing screen is not displayed after denying audio permission");
		}
		Back(2);
	}

	public void moreSectionValidation() throws Exception {
		extent.HeaderChildNode("More Screen Validation");
		System.out.println("\nMore Section Validation");

		Runtime.getRuntime().exec("adb shell svc wifi disable");
		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
		}

		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu tab");

		verifyElementExist(AMDOfflineScreen.objYouAreOffline, "You are Offline");
		verifyElementExist(AMDOfflineScreen.objDescription, "Please connect to the internet and try again");
		verifyElementExist(AMDOfflineScreen.objTryAgain, "Try Again");
		verifyElementExist(AMDOfflineScreen.objGoToDownloads, "Go to Downloads");

		Runtime.getRuntime().exec("adb shell svc wifi enable");
		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
		}
		waitTime(5000);
		verifyElementPresentAndClick(AMDOfflineScreen.objTryAgain, "Try Again");
		waitTime(2000);
		Swipe("DOWN", 1);
		verifyElementExist(AMDMoreMenu.objProfile, "Profile icon");
		verifyElementExist(AMDMoreMenu.objBuySubscription, "Buy Subscription option");
		verifyElementExist(AMDMoreMenu.objMySubscription, "My Subscription option");
		verifyElementExist(AMDMoreMenu.objMyTransactions, "My Transactions option");
		verifyElementExist(AMDMoreMenu.objWatchlist, "Watchlist option");
		verifyElementExist(AMDMoreMenu.objMyRemainders, "My Reminders option");
		verifyElementExist(AMDMoreMenu.objHaveaPrepaidCode, "Have a prepaid code option");
		Swipe("UP", 1);
		verifyElementExist(AMDMoreMenu.objSettings, "Settings option");
		verifyElementExist(AMDMoreMenu.objInviteAFriend, "Invite a Friend option");
		verifyElementExist(AMDMoreMenu.objAboutUs, "About Us option");
		verifyElementExist(AMDMoreMenu.objHelpCentre, "Help Centre option");
		Swipe("UP", 1);
		verifyElementExist(AMDMoreMenu.objTermsOfUse, "Terms of Use");
		verifyElementExist(AMDMoreMenu.objPrivacyPolicy, "Privacy policy");
		verifyElementExist(AMDMoreMenu.objBuildVersion, "Build Version");
	}

	@SuppressWarnings("deprecation")
	public void parentalPinValidation(String userType, String searchKeyword) throws Exception {
		extent.HeaderChildNode("Parental Pin Validation");
		System.out.println("\nParental Pin Validation");

		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(2000);
			click(AMDSettingsScreen.objDownloadOverWifiToggle, "Wifi Off toggle");
			Back(1);
			waitTime(3000);
			Back(1);
			click(AMDHomePage.objSearchinUpcoming, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, searchKeyword + "\n", "Search bar");
			waitTime(2000);
			hideKeyboard();
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDMoreMenu.objSearchResult(searchKeyword), "Search result");

			click(AMDMoreMenu.objDownloadIcon, "Download icon");
			click(AMDMoreMenu.objDataSaver, "Data Saver option");
			click(AMDMoreMenu.objStartDownload, "Start Download");

			String wifi = "";
			String cmd = "adb shell dumpsys \"wifi | grep 'Wi-Fi is'\"";
			Process p = Runtime.getRuntime().exec(cmd);
			System.out.println(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			wifi = br.readLine();
			logger.info("Wifi status :: " + wifi.toString());

			if (wifi.equalsIgnoreCase("Wi-Fi is enabled")) {
				logger.info("Content is downloading on Wifi network");
				extent.extentLoggerPass("Download", "Content is downloading on Wifi network");
			} else {
				logger.error("Content is not downloading on Wifi network");
				extent.extentLoggerFail("Download", "Content is not downloading on Wifi network");
			}
			Back(1);

			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);

			String state = getText(AMDMoreMenu.objNoRestriction);
			System.out.println(state);
			if (state.equalsIgnoreCase("No Restriction")) {
				logger.info(state + " is selected by default");
				extent.extentLoggerPass("Parental Pin", state + " is selected by default");

			} else {
				logger.error(state + " is not selected by default");
				extent.extentLoggerFail("Parental Pin", state + " is not selected by default");

			}

			click(AMDMoreMenu.objRestrictAllContent, "Restrict All Content option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);

			verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
			type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
			hideKeyboard();
			waitTime(4000);
			click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
			waitTime(2000);

			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			Back(1);
			waitTime(3000);
			Back(1);

			verifyElementPresentAndClick(AMDHomePage.objDownload, "Downloads tab");
			verifyElementPresentAndClick(AMDDownloadPage.objvideostab, "Videos tab");
			waitForElementDisplayed(AMDMoreMenu.objDownloadDoneIcon, 20);
			click(AMDDownloadPage.objDownloadedContent, "Downloaded Content");
			click(AMDDownloadPage.objPlayDownloadedContent, "Play Downloaded Content");

			waitTime(2000);
			Back(1);
			verifyElementPresentAndClick(AMDDownloadPage.objEnterPinCTA, "Enter Pin CTA");

			type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
			hideKeyboard();
			click(AMDMoreMenu.objSetPinContinueBtn, "Continue button");
			waitTime(5000);
			Back(1);
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");

			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			click(AMDMoreMenu.objNoRestriction, "No Restriction");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			Back(1);
		} else {
			logger.info("Parental Pin Validation is NOT applicable for " + userType);
			extent.extentLogger("Parental Pin", "Parental Pin Validation is NOT applicable for " + userType);
		}
	}

	/**
	 * Author : Vinay Module : Premium tab screen validations
	 * 
	 * @param UserType
	 */

	/** ===========Premium tab screen validations=========== */

	public void PremiumTabScreen(String UserType) throws Exception {

		extent.HeaderChildNode("Verifying Premium tab screen");
		// Verify user is navigated to Premium tab
		SelectTopNavigationTab("Premium");
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase("Premium")) {
			extent.extentLoggerPass("Verify user is navigated to Premium tab",
					"User is navigated to Premium tab on clicking premium tab");
			logger.info("User is navigated to Premium tab on clicking premium tab");
		} else {
			extent.extentLoggerFail("Verify user is navigated to Premium tab",
					"Failed to navigate to Premium tab on clicking premium tab");
			logger.error("Failed to navigate to Premium tab on clicking premium tab");
		}

		// Verify Subscribe icon is displayed
		// verifyElementPresent(AMDHomePage.objSubscribeIcon, "Subscribe icon in premium
		// tab");
		if (!(UserType.equalsIgnoreCase("SubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("subscribe icon is dislayed");
				extent.extentLoggerPass("Subscribe icon", "subscribe icon is dislayed");
			} else {
				logger.error("subscribe icon is not dislayed");
				extent.extentLoggerFail("Subscribe icon", "subscribe icon is not dislayed");
			}
		}
		// Verify continue watching tray is not displayed for guest user
		/*
		 * if(UserType.equals("Guest")) {
		 * if(verifyElementExist(AMDHomePage.objContinueWatchingTray,
		 * "Continue Watching") == false) {
		 * extent.extentLogger("Verify continue watching tray ",
		 * "Continue Watching tray is not displayed for guest user");
		 * logger.info("Continue Watching tray is not displayed for guest user");
		 * 
		 * }else { extent.extentLoggerFail("Verify continue watching tray ",
		 * "Continue Watching tray is displayed for guest user");
		 * logger.info("Continue Watching tray is displayed for guest user"); } }
		 */
		// Verify Trending now tray is displayed
		// verifyElementPresent(AMDHomePage.objTrendingNowTray, "Trending Now tray");
		String carouselContent = getText(AMDHomePage.objCarouselTitle1);
		carouselValidation(UserType, "Premium", carouselContent);
	}

	/** ========Eduauraa tab validations======== */
	public void EduauraaTabScreen(String UserType) throws Exception {

		extent.HeaderChildNode("Verifying Eduauraa tab screen");
		System.out.println("Verifying Eduauraa tab screen");

		// Selecting Marathi language as content language
		if (UserType.contains("Guest")) {
			selectContentLang_MoreMenu2("Marathi");
		}

		// Verify user is navigated to Premium tab
		SelectTopNavigationTab("Eduauraa");
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase("Eduauraa")) {
			extent.extentLoggerPass("Verify user is navigated to Eduauraa tab",
					"User is navigated to Eduauraa tab on clicking Eduauraa tab");
			logger.info("User is navigated to Eduauraa tab on clicking Eduauraa tab");
		} else {
			extent.extentLoggerFail("Verify user is navigated to Eduauraa tab",
					"Failed to navigate to Eduauraa tab on clicking Eduauraa tab");
			logger.info("Failed to navigate to Eduauraa tab on clicking Eduauraa tab");
		}

		// Verify Subscribe icon is displayed
		if (UserType.contains("SubscribedUser")) {
			extent.extentLoggerPass("Subscribe Icon", "Subscribe Icon is not displayed for " + UserType);
			logger.info("Subscribe Icon is not displayed for " + UserType);
		} else {
			verifyElementPresent(AMDHomePage.objSubscribeIcon, "Subscribe icon in Kids tab");
		}
		// Verify Trending on ZEE5 tray is displayed
		// verifyElementPresent(AMDHomePage.objTrendingOnZee5Tray, "Trending on Zee5");
		// Verify Play icon is displayed
		String carouselContent = getText(AMDHomePage.objCarouselTitle1);
		carouselValidation(UserType, "Eduauraa", carouselContent);
		if (UserType.contains("Guest")) {
			ResetSettings();
		}
	}

	public void ResetSettings() throws Exception {
		click(AMDHomePage.MoreMenuIcon, "More Menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings CTA");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Settings"), "Settings Screen");
		Swipe("UP", 1);
		SwipeUntilFindElement(AMDMoreMenu.objResetDefault, "UP");
		verifyElementPresentAndClick(AMDMoreMenu.objResetDefault, "Reset settings to Default");
		click(AMDMoreMenu.objYesBtnResetDefault, "Yes button");
		waitTime(1000);
		Back(1);
		click(AMDHomePage.HomeIcon, "Home button");
	}

	/**
	 * Author : Bindu Module : News Landing Page
	 */
	public void verifyNewsLandingScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verify user navigates to News screen");
		System.out.println("\nVerify User navigated to News Screen");

		// Navigate to required page from top navigation
		waitForElementDisplayed(AMDHomePage.objHomeTab, 2000);
		SelectTopNavigationTab("News");

		getText(AMDHomePage.objSelectedTab);
		String getPropertyValue = getAttributValue("enabled", AMDHomePage.objNewsTab);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			logger.info(
					"user is able to navigate to News screen by tapping on News tab displayed in the top navigation bar");
			extent.extentLoggerPass("Shows Tab",
					"user is able to navigate to News screen by tapping on News tab displayed in the top navigation bar");
		} else {
			logger.error(
					"user is not able to navigate to News screen by tapping on News tab displayed in the top navigation bar");
			extent.extentLoggerFail("News Tab",
					"user is not able to navigate to News screen by tapping on News tab displayed in the top navigation bar");
		}

		if ((userType.equalsIgnoreCase("Guest")) | (userType.equalsIgnoreCase("NonSubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is dislayed on the header of the News Landing Screen");
				extent.extentLoggerPass("Subscribe icon",
						"Subscribe icon is dislayed on the header of the News Landing Screen");
			} else {
				logger.error("Subscribe icon is not dislayed on the header of the News Landing Screen");
				extent.extentLoggerFail("Subscribe icon",
						"Subscribe icon is not dislayed on the header of the News Landing Screen");
			}
		} else {

			if (verifyElementIsNotDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is not dislayed on the header of the News Landing Screen");
				extent.extentLoggerPass("Subscribe icon",
						"Subscribe icon is not dislayed on the header of the News Landing Screen");

			} else {
				logger.error("Subscribe icon is dislayed on the header of the News Landing Screen");
				extent.extentLoggerFail("Subscribe icon",
						"Subscribe icon is dislayed on the header of the News Landing Screen");
			}
		}
		carouselValidationforShowsAndNews(userType, "News");
	}

	public void carouselValidationforShowsAndNews(String UserType, String tabName) throws Exception {
		extent.HeaderChildNode("Carousel validation");
		System.out.println("\nCarousel validation");

		waitForElementDisplayed(AMDHomePage.objCarouselDots, 10);
		if (!UserType.equalsIgnoreCase("SubscribedUser")) {
			waitTime(10000);
		}

		if ((verifyElementIsNotDisplayed(AMDHomePage.objBannerAd))) {
			verifyElementPresent(AMDHomePage.objCarouselUnitwhenNomastHeadAdbanner,
					"Carousel unit as first unit on " + tabName + " screen");
		} else {
			verifyElementPresent(AMDHomePage.objCarouselUnitwithmastHeadAdbanner,
					"Carousel unit as first unit on " + tabName + " screen");
		}

		// Validating Carousel manual swipe
		String width = getAttributValue("width", AMDHomePage.objCarouselConetentCard);

		String bounds = getAttributValue("bounds", AMDHomePage.objCarouselConetentCard);
		String b = bounds.replaceAll(",", " ").replaceAll("]", " ");
		String height = b.split(" ")[1];
		// System.out.println(height);
		waitTime(4000);

		carouselCardsSwipe("RIGHT", 1, width, height);
		String Carouseltitle1 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle1);
		extentLoggerPass("Carousel Title", Carouseltitle1);

		carouselCardsSwipe("LEFT", 1, width, height);
		String Carouseltitle2 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle2);
		extentLoggerPass("Carousel Title", Carouseltitle2);

		carouselCardsSwipe("RIGHT", 1, width, height);
		String Carouseltitle3 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle3);
		extentLoggerPass("Carousel Title", Carouseltitle3);

		// Verifying if the carousel banneer can be manually swipe
		if (!Carouseltitle1.equalsIgnoreCase(Carouseltitle2)) {
			logger.info(UserType + " is able to manually swipe banners from right to left or vice versa.");
			extent.extentLoggerPass("Carousel swipe",
					UserType + " is able to manually swipe banners from right to left or vice versa.");
		} else if (Carouseltitle3.equalsIgnoreCase(Carouseltitle1)) {
			logger.info(UserType + " is able to manually swipe banners from right to left or vice versa.");
			extent.extentLoggerPass("Carousel swipe",
					UserType + " is able to manually swipe banners from right to left or vice versa.");
		} else {
			logger.error(UserType + " is not able to manually swipe banners from right to left or vice versa.");
			extent.extentLoggerFail("Carousel swipe",
					UserType + " is NOT able to manually swipe banners from right to left or vice versa.");
		}

		// Validating Pagination dot, Play icon and GetPremium on Carousel
		int noofCarouselContents = findElements(AMDHomePage.objCarouselDots).size();
		for (int i = 0; i < noofCarouselContents; i++) {
			logger.info(getText(AMDHomePage.objCarouselTitle1));

			// To Verify PAGINATION DOT displayed
			if (checkElementExist(AMDHomePage.objCarouselDots, "Pagination dot")) {
				logger.info("Pagination dot is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerPass("Pagination dot",
						"Pagination dot is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			} else {
				logger.error(
						"Pagination dot is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerFail("Pagination dot",
						"Pagination dot is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			}
			// To Verify PLAY ICON displayed
			if (checkElementExist(AMDHomePage.objPlayBtn, "Play icon")) {
				logger.info("Play icon is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerPass("Play icon",
						"Play icon is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			} else {
				logger.error("Play icon is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerFail("Play icon",
						"Play icon is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			}
			carouselCardsSwipe("LEFT", 1, width, height);
		}

		if ((tabName.equals("Kids")) | (tabName.equals("Music"))) {
			extent.extentLoggerPass("Verify Get Premium tag",
					"Get Premium tag is not configured for " + tabName + " tab");
			logger.info("Get Premium tag is not configured for " + tabName + " tab");
		}
//		else {
//			if ((UserType.equalsIgnoreCase("Guest")) | (UserType.equalsIgnoreCase("NonSubscribedUser"))) {
//				for (int i = 0; i < noofCarouselContents; i++) {
//
//					logger.info(getText(AMDHomePage.objCarouselTitle1));
//					verifyElementExist(AMDHomePage.objGetPremium, "GetPremium tag");
//					carouselSwipe("LEFT", 1, width, height);
//				}
//				click(AMDHomePage.objGetPremium, "GetPremium tag");
//				verifyElementPresent(AMDSubscibeScreen.objSubscribeHeader, "Subscription screen");
//				Back(1);
//			}
		else {
			for (int i = 0; i < noofCarouselContents; i++) {

				System.out.println(getText(AMDHomePage.objCarouselTitle1));
				if (verifyElementIsNotDisplayed(AMDHomePage.objGetPremium)) {
					logger.info("Get Premium tag is not displayed");
					extent.extentLoggerPass("GetPremium tag", "Get Premium tag is not displayed");
				} else {
					logger.error("Get Premium tag is displayed");
					extent.extentLoggerFail("GetPremium tag", "Get Premium tag is displayed");
				}
				carouselCardsSwipe("LEFT", 1, width, height);
			}
		}

		// navigation to consumption screen of selected content
		String CarouselTitle = getText(AMDHomePage.objCarouselPlayIconContentCard);
		click(AMDHomePage.objCarouselPlayIconContentCard, "Carousel content");

		if (checkElementExist(AMDHomePage.objSubscribePopup, "Subscribe Popup")) {
			Back(1);
		}
		waitTime(5000);

		verifyElementPresent(AMDHomePage.objConsumptionScreenTitle, "Consumption screen");
		String consumptionScreenTitle = getText(AMDHomePage.objConsumptionScreenTitle);
		System.out.println("\n" + CarouselTitle);
		System.out.println(consumptionScreenTitle);

		if (CarouselTitle.equalsIgnoreCase(consumptionScreenTitle)) {
			logger.info("Consumption Screen is displayed for the selected content");
			extent.extentLoggerPass("Consumption screen", "Consumption Screen is displayed for the selected content");
		} else {
			logger.error("Consumption Screen is not displayed for the selected content");
			extent.extentLoggerFail("Consumption screen",
					"Consumption Screen is not displayed for the selected content");
		}
		Back(1);
		Swipe("DOWN", 1);
		// Validating Carousel Auto scroll
		String title1 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(title1);
		extentLoggerPass("Carousel Title", title1);
		waitTime(4000);
		String title2 = getText(AMDHomePage.objCarouselTitle2);
		logger.info(title2);
		extentLoggerPass("Carousel Title", title2);
		waitTime(4000);
		String title3 = getText(AMDHomePage.objCarouselTitle3);
		logger.info(title3);
		extentLoggerPass("Carousel Title", title3);

		if (!(title1.equalsIgnoreCase(title2))) {
			if (!(title2.equalsIgnoreCase(title3))) {
				logger.info(
						"Banners available in feature carousel unit rotates from right to left at a fixed time interval");
				extentLoggerPass("Carousel unit Autorotation",
						"Banners available in feature carousel unit rotate from right to left at a fixed time interval");
			} else {
				logger.error(
						"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
				extentLoggerFail("Carousel unit Autorotation",
						"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
			}
		} else {
			logger.error(
					"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
			extentLoggerFail("Carousel unit Autorotation",
					"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
		}
	}

	public void verifyTraysInNewsScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verify Trays Present in News Landing Screen");
		System.out.println("Verify Trays Present in News Landing Screen");
		waitTime(6000);
		if (userType.equals("Guest")) {
			selectContentLang_MoreMenu("Hindi");
		}
		click(AMDHomePage.objPremiumTab, "Premium tab");
		verifyElementPresentAndClick(AMDHomePage.objNewsTab, "News Tab");

		findingTrayInscreen(25, AMDHomePage.objTrayTitle("Today's Headlines"), AMDHomePage.objCarouselConetentCard,
				"Today's Headlines tray", "MastheadCarousel", userType, "News");
		findingTrayInscreen(25, AMDHomePage.objTrayTitle("Entertainment News"), AMDHomePage.objCarouselConetentCard,
				"Entertainment News tray", "MastheadCarousel", userType, "News");
		findingTrayInscreen(10, AMDHomePage.objTrayTitle("Live News"), AMDHomePage.objCarouselConetentCard,
				"Live News tray", "MastheadCarousel", userType, "News");
		findingTrayInscreen(25, AMDHomePage.objTrayTitle("Top Stories"), AMDHomePage.objCarouselConetentCard,
				"Top Stories tray", "MastheadCarousel", userType, "News");

		if (userType.equals("Guest")) {
			deselectContentLang_MoreMenuAndSelectDefaultLanguage("Hindi");
		}
		click(AMDHomePage.objPremiumTab, "Premium tab");
	}

	public void verifyListingCollectionScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verify user navigated to Listing collection Screen");
		System.out.println("Verify user navigated to Listing collection Screen");

		if (userType.equalsIgnoreCase("Guest")) {
			verifyElementPresentAndClick(AMDHomePage.objNewsTab, "News Tab");
			waitTime(10000);
			PartialSwipe("UP", 2);
			verifyElementPresent(AMDNewsPage.objRightArrowBtn, "Right arrow");
			click(AMDNewsPage.objRightArrowBtn, "Right arrow");
			waitTime(4000);
			String Header = getDriver().findElement(AMDNewsPage.objListingScreen).getText();
			logger.info(Header);
			if (checkElementExist(AMDNewsPage.objListingScreen, "Listing Screen Header")) {
				logger.info(Header
						+ " : User navigated to collection listing screen tapping on the arrow present on the right side of the tray"
						+ userType);
				extent.extentLoggerPass("Listing Screen", Header
						+ " : User navigated to collection listing screen tapping on the arrow present on the right side of the tray"
						+ userType);
			} else {

				logger.info("User fails to navigate to collection listing screen");
				extent.extentLoggerFail("Listing Screen", "User fails to navigate to collection listing screen");
			}
			if (!(verifyIsElementDisplayed(AMDNewsPage.objMetaData))) {
				logger.info("MetaData like Title,Year,Duration is not displayed in listing collection screen");
				extent.extentLoggerPass("Listing Collection screen",
						"MetaData like Title,Year,Duration is not displayed in listing collection screen");
			} else {
				logger.error("MetaData like Title,Year,Duration is displayed in listing collection screen");
				extent.extentLoggerFail("Listing Collection screen",
						"MetaData like Title,Year,Duration is displayed in listing collection screen");
			}
			Swipe("UP", 1);
			if (checkElementExist(AMDNewsPage.objNextContentImg, "Next Content")) {
				logger.info(
						"User can able to swipe the screen to view the next content in the Collection Listing Screen");
				extent.extentLoggerPass("Listing Collection screen",
						"User can able to swipe the screen to view the next content");
			} else {
				logger.error("No content present on the Collection Listing Screen after swiping the screen");
				extent.extentLoggerFail("Listing Collection screen",
						"No content present on the Collection Listing Screen after swiping the screen");
			}
			Swipe("DOWN", 1);
//			verifyElementPresent(AMDNewsPage.objThumbnailImg1, "Thumbnail Image");
			// waitTime(6000);
			verifyElementPresentAndClick(AMDNewsPage.objThumbnailImg1, "Thumbnail Image");
			waitTime(2000);
			if (verifyElementPresent(AMDNewsPage.objConsumptionScreenTitle, "Consumption Screen Title")) {
				logger.info(
						"User can able to Tap on Thumbnail imagee and user navigated to consumption screen tapping on thumbnail image in listing screen");
				extent.extentLoggerPass("Consumption Screen",
						"User can able to Tap on Thumbnail imagee and user navigated to consumption screen tapping on thumbnail image in listing screen");
			} else {
				logger.error(
						"User cannot able to Tap on Thumbnail imagee and user not navigated to consumption screen tapping on thumbnail image in listing screen");
				extent.extentLoggerFail("Consumption Screen",
						"User cannot able to Tap on Thumbnail imagee and user not navigated to consumption screen tapping on thumbnail image in listing screen");
			}
			Back(1);
			verifyElementPresent(AMDLoginScreen.objBackBtn, "Back Button");
			click(AMDLoginScreen.objBackBtn, "Back Button");
			waitTime(2000);
			if (verifyElementPresent(AMDHomePage.objNewsTab, "News Tab")) {
				logger.info("User navigated to News Landing screen tapping on back icon available on listing screen");
				extent.extentLoggerPass("Listing Screen",
						"User navigated to News Landing screen tapping on back icon available on listing screen");
			} else {
				logger.error(
						"User not navigated to News Landing screen tapping on back icon available on listing screen");
				extent.extentLoggerFail("Listing Screen",
						"User not navigated to News Landing screen tapping on back icon available on listing screen");
			}
		} else {
			logger.info("Listing Collection Screen validation is not applicable for " + userType);
			extent.extentLoggerWarning("Listing Screen",
					"Listing Collection Screen validation is not applicable for " + userType);
		}
	}

	/**
	 * Author : Sushma Module : Movies
	 */
	public void moviesLandingScreen(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Navigating to Home screen and verifing the Subscribe icon");
		System.out.println("Movies Landing screen and checking the Subscribe icon");

		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		waitForElementDisplayed(AMDHomePage.objMoviesTab, 2000);

		// Selecting MOVIES tab from Top Navigation
		SelectTopNavigationTab(tabName);

		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase(tabName)) {
			logger.info(userType + " is able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLoggerPass(tabName + " Tab", userType + " is able to navigate to " + tabName
					+ " screen by tapping on " + tabName + " tab displayed in the top navigation bar");
		} else {
			logger.error(userType + " is not able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLoggerFail(tabName + " Tab", userType + " is not able to navigate to " + tabName
					+ " screen by tapping on " + tabName + " tab displayed in the top navigation bar");
		}
		waitTime(10000);
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is dislayed");
				extent.extentLoggerPass("Subscribe icon", "Subscribe icon is dislayed");
			} else {
				logger.error("Subscribe icon is not dislayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is not dislayed");
			}
		} else {
			if (verifyElementIsNotDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("subscribe icon is not dislayed");
				extent.extentLoggerPass("Subscribe icon", "subscribe icon is not dislayed");
			} else {
				logger.error("subscribe icon is dislayed");
				extent.extentLoggerFail("Subscribe icon", "subscribe icon is dislayed");
			}
		}

		waitTime(3000);
		String courselContentTitle = carouselValidationWithApi(userType, "movies");
		carouselValidation(userType, tabName, courselContentTitle);
		click(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content");

		waitTime(5000);
		if (userType.equalsIgnoreCase("Guest")) {
			if (verifyIsElementDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen)) {
				if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
					logger.error(
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
					extentLoggerFail("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
				} else {
					logger.info(
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
					extentLoggerPass("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
				}
			} else {
				if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
					logger.info(
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerPass("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
				} else {
					logger.error(
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerFail("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
				}
			}
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen)) {
				if (verifyIsElementDisplayed(AMDShowsScreen.objSubscribeNowlink)) {
					logger.error(
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
					extentLoggerFail("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
				} else {
					logger.info(
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
					extentLoggerPass("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
				}
			} else {
				if (verifyIsElementDisplayed(AMDShowsScreen.objSubscribeNowlink)) {
					logger.info(
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerPass("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
				} else {
					logger.error(
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerFail("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
				}
			}
		} else {
			logger.info("Content playback is initiated for the SubscribedUser post tapping on Premium Content");
			extentLoggerPass("Trailer",
					"Content playback is initiated for the SubscribedUser post tapping on Premium Content");
		}
		Back(1);
		extent.HeaderChildNode("Verifing the availability of tray in the screen");
		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Continue Watching"), AMDHomePage.objCarouselConetentCard,
				"Continue watching tray", "MastheadCarousel", userType, tabName);
		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Trending Movies"), AMDHomePage.objCarouselConetentCard,
				"Trending Movies tray", "MastheadCarousel", userType, tabName);
		verifyContinueWatchingTray(userType, tabName);
		RemoveContentCardFromCWRail(userType);
	}

	/**
	 * Author : Kushal
	 */
	public void selectContentLang_MoreMenu(String planguage) throws Exception {

		click(AMDHomePage.HomeIcon, "Home button");
		click(AMDHomePage.MoreMenuIcon, "More Menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings CTA");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Settings"), "Settings Screen");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objContentLang, "Content language");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Content Language"), "Content language screen");

		// ****** UnSelecting default content languages ******
		if (pUserType.contains("Guest")) {
			click(AMDOnboardingScreen.objSelectContentLang("English"), "English");
			PartialSwipe("UP", 2);
			waitTime(1000);
			click(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Kannada");
			Swipe("DOWN", 1);
		}

		// ****** Selecting required language ******
		if (planguage.contains(",")) {
			Swipe("DOWN", 1);
			String[] pLanguages = planguage.split(",");
			int n = pLanguages.length;
			for (int i = 0; i < n; i++) {
				int totalLanguages = getCount(AMDOnboardingScreen.objContentLangBtns);
				for (int j = 1; j <= totalLanguages; j++) {
					String visibleLang = getText(AMDOnboardingScreen.objgetContentLangName(j));
					if (pLanguages[i].equalsIgnoreCase(visibleLang)) {
						verifyElementPresentAndClick(AMDOnboardingScreen.objSelectContentLang(pLanguages[i]),
								pLanguages[i]);
					}
				}
				PartialSwipe("UP", 1);
			}
		} else {
			outerLoop: for (int i = 1; i <= 4; i++) {
				int totalLanguages = getCount(AMDOnboardingScreen.objContentLangBtns);
				for (int j = 1; j <= totalLanguages; j++) {
					String visibleLang = getText(AMDOnboardingScreen.objgetContentLangName(j));
					if (planguage.equalsIgnoreCase(visibleLang)) {
						verifyElementPresentAndClick(AMDOnboardingScreen.objSelectContentLang(planguage), planguage);
						break outerLoop;
					}
				}
				PartialSwipe("UP", 1);
			}
		}

		waitTime(1000);
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button");
		waitTime(1000);
//	click(AMDGenericObjects.objBackBtn, "Back button");
		Back(1);
		click(AMDHomePage.HomeIcon, "Home button");
	}

	/**
	 * Author :Manasa Module : UpComing Screen
	 */

	public void upcomingContentValidationWithAPIData() throws Exception {

		extent.HeaderChildNode("Upcoming Content Validation With API Data");
		waitForElementDisplayed(AMDHomePage.objHomeTab, 10);
		verifyElementPresentAndClick(AMDHomePage.objUpcoming, "Upcoming tab");
		Response resp = ResponseInstance.getResponseForUpcomingPage();
		List<String> apiTitle = new LinkedList<String>();
		List<String> apiMetadata = new LinkedList<String>();
		List<String> contentList = resp.jsonPath().getList("buckets[0].items");
		System.out.println(contentList.size());

		for (int i = 0; i < contentList.size(); i++) {
			String title = resp.jsonPath().getString("buckets[0].items[" + i + "].title");
			System.out.println("Show Title " + title);

			apiTitle.add(title);

			String metadata = resp.jsonPath().getString("buckets[0].items[" + i + "].description");
			System.out.println("API Metadata " + metadata);
			apiMetadata.add(metadata);

			String releaseDate = resp.jsonPath().getString("buckets[0].items[" + i + "].release_date");
			String[] releaseDateSplit = releaseDate.split("T");

			String contentName = apiTitle.get(i);
			System.out.println(contentName);

			String convertedXpath = titleToXpath(contentName);
			System.out.println(convertedXpath);

			String contentDescription = apiMetadata.get(i);

			String UIMetadata = getDriver().findElementByXPath(AMDUpcomingPage.objContentCardTitle(convertedXpath))
					.getText();

			System.out.println(UIMetadata);

			if (checkElementExist(AMDUpcomingPage.objTitle(convertedXpath), "Show Title : " + title)) {
				logger.info("Title on the content card matches with Api data");
				extent.extentLoggerPass("Title", "Title on the content card matches with Api data");
			} else {
				logger.info("Title on the content card does not match with Api data");
				extent.extentLoggerFail("Title", "Title on the content card does not match with Api data");
			}

			if (UIMetadata.contains(contentDescription)) {
				logger.info("Metadata on the content card matches with Api data");
				extent.extentLoggerPass("Metadata", "Metadata on the content card matches with Api data");
			} else {
				logger.error("Metadata on the content card does not match with Api data");
				extent.extentLoggerFail("Metadata", "Metadata on the content card does not match with Api data");
			}

			String genre = getDriver().findElementByXPath(AMDUpcomingPage.objContentGenre(convertedXpath)).getText();
			logger.info("Content Genre : " + genre);
			extent.extentLoggerPass("Genre", "Content Genre : " + genre);

//			String certificate = getDriver().findElementByXPath(AMDUpcomingPage.objContentCertificate(convertedXpath))
//					.getText();
//			logger.info("Content Certificate : " + certificate);
//			extent.extentLoggerPass("Certificate", "Content Certificate : " + certificate);

			System.out.println("Release Date " + releaseDateSplit[0]);
			logger.info("Release Date " + releaseDateSplit[0]);
			extent.extentLoggerPass("Release Date", "Release Date " + releaseDateSplit[0]);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date upcomingReleaseDate = sdf.parse(releaseDateSplit[0]);
			Date dateToday = sdf.parse(date());

			if (upcomingReleaseDate.compareTo(dateToday) <= 0) {
				logger.error("Previously released content is listed in the upcoming section");
				extent.extentLoggerFail("Release Date",
						"Previously released content is listed in the upcoming section");

			} else {
				logger.info("Previously released content is not listed in the upcoming section");
				extent.extentLoggerPass("Release Date",
						"Previously released content is not listed in the upcoming section");
			}
			swipeByElements(findElement(AMDUpcomingPage.objGenre), findElement(AMDHomePage.objSearchinUpcoming));
			// swipeByElements(findElement(AMDUpcomingPage.objGenre),
			// findElement(AMDHomePage.objSearchBtn));
			waitTime(3000);
		}
	}

	public String titleToXpath(String trayTitle) throws Exception {
		String xPath = null;
		StringBuffer Sbuffer1 = new StringBuffer();
		// System.out.println(trayTitle);
		if (trayTitle.contains("'")) {
			// System.out.println("Length of the title is: "+trayTitle.length());
			String[] S = trayTitle.split("'");
			// System.out.println("The size of the array is: "+S.length);
			// for(String x : S)
			// System.out.println(x);
			//
			Sbuffer1.append("//*[@text=concat(\"");
			Sbuffer1.append(S[0]);
			// System.out.println(Sbuffer1);
			for (int i = 1; i < S.length; i++) {
				Sbuffer1.append("\",\"'\",\"" + S[i]);
			}
			Sbuffer1.append("\")]");
			// System.out.println("The Xpath is:"+Sbuffer1.toString());
			xPath = Sbuffer1.toString();

		} else {
			Sbuffer1.append("//*[@text=\"" + trayTitle + "\"]");
			// System.out.println("The Xpath is:"+Sbuffer1.toString());
			xPath = Sbuffer1.toString();
		}
		return xPath;
	}

	public static String date() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		strDate = formatter.format(date);
		System.out.println(strDate);

		return strDate;
	}

	/**
	 * Author : Hitesh Module : Live TV
	 * 
	 * @throws Exception
	 */
	public void LiveTV(String UserType, String tabName) throws Exception {
		extent.HeaderChildNode("Verifying Live TV Landing screen");
		System.out.println("\nVerifying Live TV Landing screen");
		// swipeByElements(findElement(AMDHomePage.objNewsTab),
		// findElement(AMDHomePage.objHomeTab));
		SelectTopNavigationTab(tabName);
		waitTime(2000);
		if (verifyElementDisplayed(AMDLiveTVScreen.objChannelGuide)) {
			logger.info("Navigated to Live TV Screen");
			extentLoggerPass("Navigation to Live TV", "Navigated to Live TV Screen");
		} else {
			logger.info("Unable to navigated to Live TV Screen");
			extentLoggerFail("Navigation to Live TV", "Unable to navigated to Live TV Screen");
		}

		if (UserType.equals("Guest")) {
			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
		} else if (UserType.equals("NonSubscribedUser")) {
			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
		}
//		else if (UserType.equals("SubscribedUser")) {
//			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
//		}
		verifyElementExist(AMDLiveTVScreen.objChannelGuide, "Channel Guide toggle option");
		click(AMDLiveTVScreen.objChannelGuide, "Channel Guide");
		if (findElement(AMDLiveTVScreen.objChannelGuide).isSelected()) {
			logger.info("Channel Guide is Selected");
			extentLoggerPass("Verify Toogle options", "Channel Guide is Selected");
		} else {
			logger.error("Channel Guide is not Selected");
			extentLoggerFail("Verify Toogle options", "Channel Guide is not Selected");
		}
		click(AMDLiveTVScreen.onjLiveTvToggle, "Channel Guide");

		for (int i = 0; i < findElements(AMDLiveTVScreen.objContentInLiveTV).size(); i++) {
			if (verifyElementDisplayed(AMDLiveTVScreen.objImageIcon)) {
				logger.info("Image Icon is Displayed");
				extentLoggerPass("Image icon", "Image Icon is Displayed");
			} else {
				logger.error("Image Icon is Not Displayed");
				extentLoggerFail("Image icon", "Image Icon is Not Displayed");
			}
		}

		verifyElementPresentAndClick(AMDLiveTVScreen.objFirstContent, "Live Tv content");
		waitForAdToFinishInAmd();
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementExist(AMDLiveTVScreen.objLiveTV, "Live Icon on Player");
		Back(1);
		int i = 0;
		for (i = 0; i < 5; i++) {
			if (verifyIsElementDisplayed(AMDLiveTVScreen.objTray("FREE Channels"), "FREE Channels tray")) {
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		Swipe("DOWN", i);
		i = 0;
		for (i = 0; i < 5; i++) {
			if (verifyIsElementDisplayed(AMDLiveTVScreen.objTray("Music"), "Music tray")) {
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		Swipe("DOWN", i);
		i = 0;
		for (i = 0; i < 5; i++) {
			if (verifyIsElementDisplayed(AMDLiveTVScreen.objTray("News"), "News tray")) {
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		Swipe("DOWN", i);
		VerifyDuplicateTrays(AMDLiveTVScreen.objTray("FREE Channels"), "FREE Channels tray");
		VerifyDuplicateTrays(AMDLiveTVScreen.objTray("Music"), "Music tray");
		VerifyDuplicateTrays(AMDLiveTVScreen.objTray("News"), "News tray");
	}

	public void channelGuideScreenValidation(String UserType) throws Exception {
		extent.HeaderChildNode("Verifying Channel Guide screen in Live TV Landing screen");
		System.out.println("\nVerifying Channel Guide screen in Live TV Landing screen");

		verifyElementPresentAndClick(AMDHomePage.objLiveTvTab, "Live Tv");
		verifyElementExist(AMDLiveTVScreen.objChannelGuide, "Channel Guide toggle option");
		click(AMDLiveTVScreen.objChannelGuide, "Channel Guide");
		if (findElement(AMDLiveTVScreen.objChannelGuide).isSelected()) {
			logger.info("Channel Guide is Selected");
			extentLoggerPass("Verify Toogle options", "Channel Guide is Selected");
		} else {
			logger.error("Channel Guide is not Selected");
			extentLoggerFail("Verify Toogle options", "Channel Guide is not Selected");
		}
		if (UserType.equals("Guest")) {
			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
		} else if (UserType.equals("NonSubscribedUser")) {
			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
		}
//		else if (UserType.equals("SubscribedUser")) {
//			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
//		}
		if (findElements(AMDLiveTVScreen.objChannelLogo).size() > 0) {
			logger.info("list of channels are displayed in Channel Guide screen");
			extentLoggerPass("Channel Guide Screen", "list of channels are displayed in Channel Guide screen");
		} else {
			logger.error("list of channels are not displayed in Channel Guide screen");
			extentLoggerFail("Channel Guide Screen", "list of channels are not displayed in Channel Guide screen");
		}
		verifyElementPresentAndClick(AMDHomePage.objMusicTab, "Music Tab");
		if (findElement(AMDHomePage.objMusicTab).isSelected()) {
			logger.info("User is able to navigate to any screen from channel guide screen");
			extentLoggerPass("Screen", "User is able to navigate to any screen from channel guide screen");
		} else {
			logger.error("User is not able to navigate to any screen from channel guide screen");
			extentLoggerFail("Screen", "User is not able to navigate to any screen from channel guide screen");
		}
	}

	public void VerifyDuplicateTrays(By tray, String trayName) {
		for (int i = 0; i < 4; i++) {
			if ((findElements(tray).size()) > 1) {
				logger.error("Duplicate tray :" + trayName);
				extentLoggerFail("Duplicate Tray", "Duplicate tray :" + trayName);
			} else {
				logger.info("No Duplicate tray :" + trayName);
				extentLoggerPass("Duplicate Tray", "No Duplicate tray :" + trayName);
			}
			Swipe("UP", 1);
		}
		for (int i = 0; i < 4; i++) {
			Swipe("Down", 1);
		}
	}

	/**
	 * Author : Manasa Module :
	 */
	public void verifySubscriptionReminderInDownloads() throws Exception {
		extent.HeaderChildNode("Verify Subscription Reminder In Downloads");
		System.out.println("\nVerify Subscription Reminder In Downloads");
		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.objDownload, "Downloads tab");
		String subscriptionExpiry = getText(AMDDownloadPage.objSubscriptionExpiry);
		logger.info("Subscription Expiry Message : " + subscriptionExpiry + " is displayed");
		extent.extentLogger("Subscription Expiry Message",
				"Subscription Expiry Message : " + subscriptionExpiry + " is displayed");
		verifyElementExist(AMDDownloadPage.objSubscriptionExpiryMessage, "Subscription Renewal Message");
		verifyElementPresentAndClick(AMDDownloadPage.objRemindMeLaterCTA, "Remind Me Later CTA");
//		if(verifyElementExist(AMDDownloadPage.objSubscriptionExpiryMessage, "Subscription Reminder Message")) {
//			logger.info("Subscription Reminder Message is hidden");
//			extent.extentLogger("Subscription Reminder Message","Subscription Reminder Message is hidden");
//		}else {
//			logger.info("Subscription Reminder Message is not hidden");
//			extent.extentLogger("Subscription Reminder Message","Subscription Reminder Message is not hidden");	
//		}
	}

	/**
	 * Author : Vinay Module : Music Landing screen
	 * 
	 */
	public void MusicLandingScreen(String userType) throws Exception {
		extent.HeaderChildNode("Top Navigation Music Landing screen");
		waitTime(3000);
		// Verify user is navigated to Music landing screen
		SelectTopNavigationTab("Music");
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		// Verify user is navigated to Music tab
		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase("Music")) {
			extent.extentLoggerPass("Verify user is navigated to Music tab",
					"User is navigated to Music tab on clicking Music tab");
			logger.info("User is navigated to Music tab on clicking Music tab");
		} else {
			extent.extentLoggerFail("Verify user is navigated to Music tab",
					"Failed to navigate to Music tab on clicking Music tab");
			logger.error("Failed to navigate to Music tab on clicking Music tab");
		}

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is dislayed");
				extent.extentLoggerPass("Subscribe icon", "Subscribe icon is dislayed");
			} else {
				logger.error("Subscribe icon is not dislayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is not dislayed");
			}
		}

		String carouselContent = getText(AMDHomePage.objCarouselTitle1);
		carouselValidation(userType, "Music", carouselContent);
	}

	/**
	 * Author : Manasa Module : ZeeOriginals
	 */
	public void zee5OriginalsLandingScreen(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Navigating to Zee5 Originals screen and verifing the Subscribe icon");
		System.out.println("Zee5 Originals Landing screen and verifing the subscribe icon");
//		 swipeByElements(findElement(AMDHomePage.objNewsTab),
//		 findElement(AMDHomePage.objHomeTab));
		waitTime(2000);
		SelectTopNavigationTab(tabName);
		// verifyElementPresentAndClick(AMDHomePage.objZee5OriginalsTab, tabName + "
		// Tab");
		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase(tabName)) {
			logger.info("User is able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLoggerPass(tabName + " Tab", "User is able to navigate to " + tabName
					+ " screen by tapping on " + tabName + " tab displayed in the top navigation bar");
		} else {
			logger.error("User is not able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLoggerFail(tabName + " Tab", "User is not able to navigate to " + tabName
					+ " screen by tapping on " + tabName + " tab displayed in the top navigation bar");
		}
		waitTime(10000);
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is displayed");
				extent.extentLoggerPass("Subscribe icon", "Subscribe icon is displayed");
			} else {
				logger.error("Subscribe icon is not displayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is not displayed");
			}
		} else {
			if (verifyElementIsNotDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is not displayed");
				extent.extentLoggerPass("Subscribe icon", "Subscribe icon is not displayed");

			} else {
				logger.error("Subscribe icon is displayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is displayed");
			}
		}

		String courselContentTitle = zeeOriginalsCarouselValidationWithApi(userType, "zeeoriginals");
		carouselValidation(userType, tabName, courselContentTitle);
		verifyElementPresentAndClick(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDHomePage.objSubscribePopup)) {
//			Back(1);
			click(AMDGenericObjects.objPopUpDivider, "Pop Up divider");
		}
		if (userType.equalsIgnoreCase("Guest")) {
			if (verifyIsElementDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen)) {
				if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
					logger.error(
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
					extentLoggerFail("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
				} else {
					logger.info(
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
					extentLoggerPass("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
				}
			} else {
				if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
					logger.info(
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerPass("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
				} else {
					logger.error(
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerFail("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
				}
			}
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen)) {
				if (verifyIsElementDisplayed(AMDShowsScreen.objSubscribeNowlink)) {
					logger.error(
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
					extentLoggerFail("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is having trailer");
				} else {
					logger.info(
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
					extentLoggerPass("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is having trailer");
				}
			} else {
				if (verifyIsElementDisplayed(AMDShowsScreen.objSubscribeNowlink)) {
					logger.info(
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerPass("Trailer",
							"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
				} else {
					logger.error(
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
					extentLoggerFail("Trailer",
							"Content playback is initiated for the user post tapping on premium content which is not having trailer");
				}
			}
		} else {
			logger.info("Content playback is initiated for the SubscribedUser post tapping on Premium Content");
			extentLoggerPass("Trailer",
					"Content playback is initiated for the SubscribedUser post tapping on Premium Content");
		}
		Back(1);
		extent.HeaderChildNode("Verifying the availability of trays in the screen");
		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Best of ZEE5 Originals"), AMDHomePage.objCarouselConetentCard,
				"Best of ZEE5 Originals", "MastheadCarousel", userType, tabName);
		// PartialSwipe("UP", 1);
		findingTrayInscreen(25, AMDHomePage.objTrayTitle("ZEE5 Original Music"), AMDHomePage.objCarouselConetentCard,
				"ZEE5 Original Music tray", "Mastheadcarousel", userType, tabName);
	}

	public static String zeeOriginalsCarouselValidationWithApi(String userType, String pagenameforApi) {
		Response respPage = ResponseInstance.getResponseForApplicasterPages(userType, pagenameforApi);
		List<String> items = respPage.jsonPath().getList("buckets[0].items");
		logger.info("bucketsSize: " + items.size());
		String carouselContentTitle = null;
		main: for (int i = 0; i < items.size(); i++) {
			carouselContentTitle = respPage.jsonPath().getString("buckets[0].items[" + i + "].title");
			logger.info(carouselContentTitle);

			String CarouselContentBusinessType = respPage.jsonPath()
					.getString("buckets[0].items[" + i + "].business_type");
			logger.info(CarouselContentBusinessType);
			if (CarouselContentBusinessType.equalsIgnoreCase("premium_downloadable")) {
				break main;
			}
		}
		return carouselContentTitle;
	}

	/**
	 * Author : Sushma Module : Settings
	 */
	public void settings(String usertype) throws Exception {
		extent.HeaderChildNode("Settings screen validation");
		System.out.println("\nSettings screen validation");

		// Setting screen validation
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		if (checkElementExist(AMDMoreMenu.objSettingsScreenTitle, "Setting screen title")) {
			logger.info("Settings screen is displayed when user taps on the Settings option from the More menu");
			extentLoggerPass("Settings",
					"Settings screen is displayed when user taps on the Settings option from the More menu");
		} else {
			logger.error("Settings screen is not displayed when user taps on the Settings option from the More menu");
			extentLoggerFail("Settings",
					"Settings screen is not displayed when user taps on the Settings option from the More menu");
		}
		verifyElementPresent(AMDMoreMenu.objVideoStreamingMenuTitle, "Video streaming menu");
		verifyElementPresent(AMDMoreMenu.objDownloadsMenuTitle, "Downloads menu");

		String pos1 = getAttributValue("bounds", AMDMoreMenu.objDownloadsMenuTitle);
		String pos2 = null;
		PartialSwipe("UP", 1);
		pos2 = getAttributValue("bounds", AMDMoreMenu.objDownloadsMenuTitle);
		if (pos1 != pos2) {
			logger.info("Settings screen is scrollable");
			extentLoggerPass("Swipe", "Settings screen is scrollable");
		} else {
			logger.error("Settings screen is not scrollable");
			extentLoggerFail("Swipe", "Settings screen is not scrollable");
		}

		Swipe("UP", 2);
		verifyElementPresent(AMDMoreMenu.objLanguageMenuTitle, "Languages menu");
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			verifyElementPresent(AMDMoreMenu.objParentalControl, "Parental Control menu");
			verifyElementPresent(AMDSettingsScreen.objAuthenticateDevice, "Authenticate Device menu");
		}
		verifyElementPresent(AMDMoreMenu.objSearchHistroyLabel, "Search histroy Label");
		verifyElementPresent(AMDMoreMenu.objResetSettingsToDefault, "Reset Settings to default Label");
		Swipe("DOWN", 2);

		// Back button validation
		click(AMDMoreMenu.objBackButtonInSettingsScreen, "Back button in settings screen");
		if (verifyElementPresent(AMDMoreMenu.objSettings, "Settings option")) {
			logger.info(
					"on tapping on the Back button from settings screen, user is navigated to the screen from which More screen was launched");
			extentLoggerPass("Back button",
					"on tapping on the Back button from settings screen, user is navigated to the screen from which More screen was launched");
		} else {
			logger.error(
					"on tapping on the Back button from settings screen, user is not navigated to the screen from which More screen was launched");
			extentLoggerFail("Back button",
					"on tapping on the Back button from settings screen, user is not navigated to the screen from which More screen was launched");
		}

		click(AMDMoreMenu.objSettings, "Settings option");
	}

	@SuppressWarnings("unused")
	public void videoStreamingValidation(String userType) throws Exception {
		extent.HeaderChildNode("Video Streaming validation");
		System.out.println("\nVideo Streaming validation");
		// Video streaming menus validation
		verifyElementPresent(AMDMoreMenu.objvideoQualityOption, "Video Quality option");
		verifyElementPresent(AMDMoreMenu.objVideoStreamOverWifiOnlyOption, "Stream over wifi only option");
		verifyElementPresent(AMDMoreMenu.objVideoAutoPlay, "Video Autoplay option");

		click(AMDMoreMenu.objVideo_Quality("Auto"), "Video quality option");

		// video quality screen validation
		verifyElementPresent(AMDMoreMenu.objVideoQualityScreenTitle, "Video quality screen title");
		verifyElementPresent(AMDSettingsScreen.objVideoQualityBest, "option Best");
		verifyElementPresent(AMDSettingsScreen.objVideoQualityBetter, "option Better");
		verifyElementPresent(AMDSettingsScreen.objVideoQualityGood, "option Good");
		verifyElementPresent(AMDSettingsScreen.objVideoQualityDatasaver, "option Datasaver");
		verifyElementPresent(AMDMoreMenu.objAutoOption, "option Auto");
		verifyElementPresent(AMDMoreMenu.objCloseButtonInVideoQualityScreen, "Close button");
		if (verifyElementDisplayed(AMDMoreMenu.objSelectedVideoQualityOption("Auto"))) {
			logger.info("the default selection in the Select Video Quality is 'Auto' option");
			extentLoggerPass("Default selected Video quality option",
					"the default selection in the Select Video Quality is 'Auto' option");
		} else {
			logger.error("the default selection in the Select Video Quality is not 'Auto' option");
			extentLoggerFail("Default selected Video quality option",
					"the default selection in the Select Video Quality is not 'Auto' option");
		}

		click(AMDMoreMenu.objCloseButtonInVideoQualityScreen, "Close button");
		if (verifyElementDisplayed(AMDMoreMenu.objvideoQualityOption)) {
			logger.info("'X' button in Select Video Quality screen is functional");
			extentLoggerPass("Close button", "'X' button in Select Video Quality screen is functional");
		} else {
			logger.error("'X' button in Select Video Quality screen is not functional");
			extentLoggerFail("Close button", "'X' button in Select Video Quality screen is not functional");
		}

		click(AMDMoreMenu.objVideo_Quality("Auto"), "Video quality option");
		click(AMDSettingsScreen.objVideoQualityBetter, "option Better");
		if (verifyElementDisplayed(AMDMoreMenu.objVideo_Quality("Better"))) {
			logger.info("Select Video Quality screen with tick mark is displayed");
			extentLoggerPass("Video quality option", "Select Video Quality screen with tick mark is displayed");
		} else {
			logger.error("Select Video Quality screen with tick mark is not displayed");
			extentLoggerFail("Video quality option", "Select Video Quality screen with tick mark is not displayed");
		}

		click(AMDMoreMenu.objVideo_Quality("Better"), "Video quality option");
		click(AMDMoreMenu.objAutoOption, "option Auto");

		// Stream over wifi only option validation
		WebElement wifitoggle = findElement(AMDMoreMenu.objVideo_WifiOnly);
		int wifitoggleX = wifitoggle.getLocation().getX();
		int wifitoggleY = wifitoggle.getLocation().getY();

		WebElement wifitext = findElement(AMDMoreMenu.objVideoStreamOverWifiOnlyOption);
		int wifitextX = wifitext.getLocation().getX();
		int wifitextY = wifitoggle.getLocation().getY();

		if (wifitextY < (wifitoggleY + 10)) {
			if (wifitoggleX > wifitextX) {
				logger.info("On/Off toggle is displayed at the right side of the Stream over WiFi only option");
				extent.extentLoggerPass("wifi On/Off toggle",
						"On/Off toggle is displayed at the right side of the Stream over WiFi only option");
			} else {
				logger.error("On/Off toggle is not displayed at the right side of the Stream over WiFi only option");
				extent.extentLoggerFail("wifi On/Off toggle",
						"On/Off toggle is not displayed at the right side of the Stream over WiFi only option");
			}
		}

		String wifitoggleStatus = getText(AMDMoreMenu.objVideo_WifiOnly);
		if (wifitoggleStatus.equalsIgnoreCase("OFF")) {
			logger.info("the default state of the 'Stream over WiFi only' option is in off state.");
			extentLoggerPass("Stream over WiFi only",
					"the default state of the 'Stream over WiFi only' option is in off state.");
		} else {
			logger.error("the default state of the 'Stream over WiFi only' option is not in off state.");
			extentLoggerFail("Stream over WiFi only",
					"the default state of the 'Stream over WiFi only' option is not in off state.");
		}

		Back(1);
		waitTime(3000);
		Back(1);
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		}

		verifyElementExist(AMDHomePage.objHomeTab, "Home Tab");
		waitTime(10000);
		String email = null, password = null;
		if (userType.contains("NonSubscribedUser")) {
			email = getParameterFromXML("SettingsNonsubscribedUserName");
			password = getParameterFromXML("SettingsNonsubscribedPassword");
		} else if (userType.contains("SubscribedUser")) {
			email = getParameterFromXML("SettingsSubscribedUserName");
			password = getParameterFromXML("SettingsSubscribedPassword");
		}
		String courselContentTitle = getCarouselTitleFromAPI(userType, "homepage", email, password);
//		String courselContentTitle = carouselValidationWithApi(userType, "homepage");
		System.out.println(courselContentTitle);
		if (courselContentTitle != null) {
			waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(courselContentTitle), 10, "Carousel content");
			for (int i = 0; i < 2; i++) {
				if (verifyElementPresent(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content")) {
					break;
				}

			}
//			click(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content");
		} else {
			click(AMDGenericObjects.objCarouselTitle("Shivaji Surathkal"), "Carousel content");
		}

		String wifi = "";
		String cmd = "adb shell dumpsys \"wifi | grep 'Wi-Fi is'\"";
		Process p = Runtime.getRuntime().exec(cmd);
		System.out.println(cmd);
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		wifi = br.readLine();
		logger.info("Wifi status :: " + wifi.toString());

		if (wifi.equalsIgnoreCase("Wi-Fi is enabled")) {
			if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
				waitTime(3000);
				if (verifyIsElementDisplayed(AMDHomePage.objSubscribePopup)) {
					Back(1);
				}
				if (verifyElementDisplayed(AMDHomePage.objPlayerScreen)) {
					logger.info("Content playback is playing on Wifi network");
					extent.extentLoggerPass("Play", "Content playback is playing on Wifi network");
				} else {
					logger.error("Content playback is not playing on Wifi network");
					extent.extentLoggerFail("Play", "Content playback is not playing on Wifi network");
				}
			} else {
				waitTime(10000);
				click(AMDHomePage.objPlayerScreen, "Player screen");
//				waitForElementDisplayed(AMDHomePage.objMaximizeIcon, 10);
				if (verifyIsElementDisplayed(AMDConsumptionScreen.objShareBtn)) {
					logger.info("Content playback is playing on Wifi network");
					extent.extentLoggerPass("Play", "Content playback is playing on Wifi network");
				} else {
					logger.error("Content playback is not playing on Wifi network");
					extent.extentLoggerFail("Play", "Content playback is not playing on Wifi network");
				}
			}
		} else {
			logger.info("Content playback is not playing on Wifi network");
			extent.extentLoggerFail("Play", "Content playback is not playing on Wifi network");
		}
		Back(1);
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		click(AMDMoreMenu.objVideo_WifiOnly, "wifi toggle");
		Back(1);
		waitTime(3000);
		Back(1);
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		}

		verifyElementExist(AMDHomePage.objHomeTab, "Home Tab");
		waitTime(10000);
		if (courselContentTitle != null) {
			for (int i = 0; i < 2; i++) {
				if (verifyElementPresent(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content")) {
					break;
				}

			}
			click(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content");
		} else {
			click(AMDGenericObjects.objCarouselTitle("Shivaji Surathkal"), "Carousel content");
		}

		String wifii = "";
		String cmdd = "adb shell dumpsys \"wifi | grep 'Wi-Fi is'\"";
		Process pp = Runtime.getRuntime().exec(cmdd);
		System.out.println(cmdd);
		BufferedReader brr = new BufferedReader(new InputStreamReader(pp.getInputStream()));
		wifii = brr.readLine();
		logger.info("Wifi status :: " + wifii.toString());

		if (wifii.equalsIgnoreCase("Wi-Fi is enabled")) {
			if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
				waitTime(3000);
				if (verifyIsElementDisplayed(AMDHomePage.objSubscribePopup)) {
					Back(1);
				}
				if (verifyElementDisplayed(AMDHomePage.objPlayerScreen)) {
					logger.info("Content playback is playing only on Wifi network");
					extent.extentLoggerPass("Play", "Content playback is playing only on Wifi network");
				} else {
					logger.error("Content playback is not playing only on Wifi network");
					extent.extentLoggerFail("Play", "Content playback is not playing only on Wifi network");
				}
			} else {
				waitTime(10000);
				click(AMDHomePage.objPlayerScreen, "Player screen");
//				waitForElementDisplayed(AMDHomePage.objMaximizeIcon, 10);
				if (verifyIsElementDisplayed(AMDConsumptionScreen.objShareBtn)) {
					logger.info("Content playback is playing only on Wifi network");
					extent.extentLoggerPass("Play", "Content playback is playing only on Wifi network");
				} else {
					logger.error("Content playback is not playing only on Wifi network");
					extent.extentLoggerFail("Play", "Content playback is not playing only on Wifi network");
				}
			}
		} else {
			logger.error("Content playback is not playing on Wifi network");
			extent.extentLoggerFail("Play", "Content playback is not playing on Wifi network");
		}
		Back(1);
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		click(AMDMoreMenu.objVideo_WifiOnly, "wifi toggle");

		// Auto play option valdation
		WebElement autoPlaytoggle = findElement(AMDMoreMenu.objVideo_Autoply);
		int autoPlaytoggleX = wifitoggle.getLocation().getX();
		int autoPlaytoggleY = wifitoggle.getLocation().getY();

		WebElement autoPlaytext = findElement(AMDMoreMenu.objVideoStreamOverWifiOnlyOption);
		int autoPlaytextX = wifitext.getLocation().getX();
		int autoPlaytextY = wifitoggle.getLocation().getY();

		if (autoPlaytextY < (autoPlaytoggleY + 10)) {
			if (autoPlaytoggleX > autoPlaytextX) {
				logger.info("On/Off toggle is displayed at the right side of the 'Auto Play' option");
				extent.extentLoggerPass("Auto Play On/Off toggle",
						"On/Off toggle is displayed at the right side of the 'Auto Play' option");
			} else {
				logger.error("On/Off toggle is not displayed at the right side of the 'Auto Play' option");
				extent.extentLoggerFail("Auto Play On/Off toggle",
						"On/Off toggle is not displayed at the right side of the 'Auto Play' option");
			}
		}
		String elementAutoPlayToggleStatus = getText(AMDMoreMenu.objVideo_Autoply);
		if (elementAutoPlayToggleStatus.equalsIgnoreCase("ON")) {
			logger.info("the default state of the 'Auto Play' option is in ON state");
			extentLoggerPass("Video Auto Play", "the default state of the 'Auto Play' option is in ON state");
		} else {
			logger.info("the default state of the 'Auto Play' option is not in ON state");
			extentLoggerFail("Video Auto Play", "the default state of the 'Auto Play' option is not in ON state");
		}
	}

	public static String getCarouselTitleFromAPI(String userType, String pagenameforApi, String pUsername,
			String pPassword) {
		Response respPage = ResponseInstance.getResponseForApplicasterPagesVersion2(userType, pagenameforApi, pUsername,
				pPassword);
		List<String> bucketsSize = respPage.jsonPath().getList("buckets");
		logger.info("bucketsSize: " + bucketsSize.size());
		String carouselContentTitle = null;
		main: for (int i = 0; i < bucketsSize.size(); i++) {
			String description = respPage.jsonPath().getString("buckets[" + i + "].short_description");

			if ((description.equalsIgnoreCase("Home Page Slider")) | (description.equalsIgnoreCase("Movies Banner"))) {
				List<String> carouselItems = respPage.jsonPath().getList("buckets[" + i + "].items");
				logger.info("carouselItems: " + carouselItems.size());

				for (int j = 0; j < 7; j++) {
					carouselContentTitle = respPage.jsonPath().getString("buckets[" + i + "].items[" + j + "].title");
					logger.info(carouselContentTitle);

					String CarouselContentBusinessType = respPage.jsonPath()
							.getString("buckets[" + i + "].items[" + j + "].business_type");
					logger.info(CarouselContentBusinessType);

					if (CarouselContentBusinessType.equalsIgnoreCase("premium_downloadable")) {
						break main;
					}
				}
			}
		}
		return carouselContentTitle;
	}

	public void settings_Language(String displayLanguageSelection1, String displayLanguageSelection2) throws Exception {
		extent.HeaderChildNode("Display language settings validation");
		System.out.println("\nDisplay language settings validation");

		waitTime(3000);
		Swipe("UP", 1);
		// Display language option validation
		WebElement selectedDisplayLanguages = findElement(AMDMoreMenu.objDisplayLang);
		int selectedDisplayLanguagesX = selectedDisplayLanguages.getLocation().getX();
		int selectedDisplayLanguagesY = selectedDisplayLanguages.getLocation().getY();

		WebElement displayLanguagetext = findElement(AMDMoreMenu.objDisplayLanguageOption);
		int displayLanguagetextX = displayLanguagetext.getLocation().getX();
		int displayLanguagetextY = displayLanguagetext.getLocation().getY();

		if (displayLanguagetextY < (selectedDisplayLanguagesY + 10)) {
			if (selectedDisplayLanguagesX > displayLanguagetextX) {
				logger.info("Selected Display language is displayed at the right side of the option");
				extent.extentLogger("Display Language",
						"Selected Display language is displayed at the right side of the option");
			} else {
				logger.error("Selected Display language is not displayed at the right side of the option");
				extent.extentLoggerFail("Display Language",
						"Selected Display language is not displayed at the right side of the option");
			}
		}

		// Display language screen functionality validation
		click(AMDMoreMenu.objDisplayLang, "Display language");

		verifyElementPresent(AMDLoginScreen.objSelectedDisplayLanguage, "Selected display language");
		String selectedlanguage1 = getText(AMDLoginScreen.objSelectedDisplayLanguage);

		click(AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection2), "language");

		WebElement selectedDisplayLanguage = findElement(AMDLoginScreen.objSelectedDisplayLanguage);
		int selectedDisplayLanguageX = selectedDisplayLanguage.getLocation().getX();
		int selectedDisplayLanguageY = selectedDisplayLanguage.getLocation().getY();

		WebElement tickMark = findElement(AMDLoginScreen.objTickmarkforSelectedDisplayLanguage);
		int tickMarkX = tickMark.getLocation().getX();
		int tickMarkY = tickMark.getLocation().getY();

		if (tickMarkY < (selectedDisplayLanguageY + 20)) {
			if (tickMarkX < selectedDisplayLanguageX) {
				logger.info("Selected Display Language screen with tick mark at the left side is displayed");
				extent.extentLogger("Selected Display Language",
						"Selected Display Language screen with tick mark at the left side is displayed");
			} else {
				logger.error("Selected Display Language screen with tick mark at the left side is not displayed");
				extent.extentLoggerFail("Selected Display Language",
						"Selected Display Language screen with tick mark at the left side is not displayed");
			}
		}

		String selectedlanguage2 = getText(AMDLoginScreen.objSelectedDisplayLanguage);

		if (selectedlanguage1 != selectedlanguage2) {
			logger.info("Display Language screen is functional");
			extentLogger("Display language screen", "Display Language screen is functional");
		} else {
			logger.info("Display Language screen is not functional");
			extentLoggerFail("Display language screen", "Display Language screen is not functional");
		}

		int totalSelectedLanguages = getDriver().findElements(AMDLoginScreen.objSelectedDisplayLanguage).size();
		logger.info(totalSelectedLanguages);

		if (totalSelectedLanguages == 1) {
			logger.info("user can select only one display language");
			extentLogger("Select one language", "user can select only one display language");
		}

		// Validation of swipe functionality in the display language screen
		click(AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1), "language");

		String pos1 = getAttributValue("bounds", AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1));
		String pos2 = null;
		Swipe("UP", 1);
		pos2 = getAttributValue("bounds", AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1));
		if (pos1 != pos2) {
			logger.info("Display Language screen is scrollable");
			extentLogger("Swipe", "Display Language screen is scrollable");
		} else {
			logger.info("Display Language screen is not scrollable");
			extentLoggerFail("Swipe", "Display Language screen is not scrollable");
		}

		verifyElementPresent(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue CTA");
		Back(1);
		Swipe("UP", 1);

		extent.HeaderChildNode("Content language settings validation");

		// content language option validation
		WebElement selectedContentLanguages = findElement(AMDMoreMenu.objContentLang);
		int selectedContentLanguagesX = selectedContentLanguages.getLocation().getX();
		int selectedContentLanguagesY = selectedContentLanguages.getLocation().getY();

		WebElement contentLanguagetext = findElement(AMDMoreMenu.objContentLanguageOption);
		int contentLanguagetextX = contentLanguagetext.getLocation().getX();
		int contentLanguagetextY = contentLanguagetext.getLocation().getY();

		if (contentLanguagetextY < (selectedContentLanguagesY + 10)) {
			if (selectedContentLanguagesX > contentLanguagetextX) {
				logger.info("Selected Content language is displayed at the right side of the option");
				extent.extentLogger("Content Language",
						"Selected Content language is displayed at the right side of the option");
			} else {
				logger.error("Selected Content language is not displayed at the right side of the option");
				extent.extentLoggerFail("Content Language",
						"Selected Content language is not displayed at the right side of the option");
			}
		}

		click(AMDMoreMenu.objContentLang, "Content language");

		verifyElementExist(AMDOnboardingScreen.objContentLanguageContainer, "Content language screen");

		String position1 = getAttributValue("bounds", AMDOnboardingScreen.objSelectContentLang("Telugu"));
		String position2 = null;
		PartialSwipe("UP", 1);
		position2 = getAttributValue("bounds", AMDOnboardingScreen.objSelectContentLang("Telugu"));
		if (position1 != position2) {
			logger.info("Content Language screen is scrollable");
			extentLogger("Content language", "Content Language screen is scrollable");
		} else {
			logger.info("Content Language screen is not scrollable");
			extentLoggerFail("Content language", "Content Language screen is not scrollable");
		}
		Back(1);
	}

	/**
	 * Author : Bhavana
	 */

	public void AboutUsScreenValidation(String userType) throws Exception {
		extent.HeaderChildNode("Verifying About Us screen as " + userType);
		System.out.println("Verifying About Us screen as " + userType);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objAboutUs, "About Us option in More menu");
		verifyElementExist(AMDMoreMenu.objAboutUsHeader, "About Us Header");
		if (checkElementExist(AMDMoreMenu.objAboutUsHeader, "About Us Header")) {
			logger.info("User is navigated to About Us screen");
			extent.extentLogger("About Us", "User is navigated to About Us screen");
		} else {
			logger.error("User is unable to navigate to About Us screen");
			extent.extentLoggerFail("About Us", "User is unable to navigate to About Us screen");
		}
		verifyElementExist(AMDMoreMenu.objcloseButton, "Close button in About Us Screen");
		verifyElementExist(AMDMoreMenu.objAboutUsDescription,
				"Breif Description about the Application in About Use Screen");

		PartialSwipe("UP", 2);
		verifyElementExist(AMDMoreMenu.objHyperLinkInAboutUsScreen, "Hyper link in About Us screen");
//		String str = getElementPropertyToString("content-desc",AMDMoreMenu.objHyperLinkInAboutUsScreen,"Hyper Link");
//		System.out.println("Hyper link in About us Sceen is " + str);
		click(AMDMoreMenu.objHyperLinkInAboutUsScreen, "Hyper link");
		if (verifyIsElementDisplayed(AMDMoreMenu.objPageNotFoundMsg)) {
			logger.error(
					"On clicking the Hyper link, user is unable to navigate to the respective page of Hyper link [Jira-Id: ZNA-2528]");
			extent.extentLoggerFail("Hyper Link",
					"On clicking the Hyper link, User is unable to navigate to the respective page of Hyper link [Jira-Id: ZNA-2528]");
		} else {
			logger.info("User is navigated to the respective page of the Hyper link");
			extent.extentLoggerPass("Hyper link", "User is navigated to the respective page of the Hyper link");
		}
		Back(2);
	}

	@SuppressWarnings("unused")
	public void LogoutValidation(String userType) throws Exception {
		extent.HeaderChildNode("Validation of Logout option as " + userType);
		System.out.println("Validation of Logout option as " + userType);
		click(AMDHomePage.objMoreMenu, "More menu");
		Swipe("UP", 2);
		if (userType.contentEquals("Guest")) {
			if (verifyElementIsNotDisplayed(AMDMoreMenu.objLogout)) {
				logger.info("Logout option is NOT displayed for " + userType + "user");
				extent.extentLoggerPass("Log out", "Logout option is NOT displayed for " + userType + "user");
			} else {
				logger.error("Logout option is displayed for " + userType + "user");
				extent.extentLoggerFail("Log out", "Logout option is displayed for " + userType + "user");
			}
		}
		if (userType.contentEquals("NonSubscribedUser") || userType.contentEquals("SubscribedUser")) {
			verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout option in More menu");
			verifyElementPresent(AMDMoreMenu.objLogoutPopup, "Logout Confirmation Popup");
			verifyElementPresent(AMDMoreMenu.objCancelButton, "Cancel button");
			verifyElementPresent(AMDMoreMenu.objLogoutButton, "Logout button");
			String getPropertyValue = getAttributValue("enabled", AMDMoreMenu.objCancelButton);
			if (getPropertyValue.equalsIgnoreCase("true")) {
				logger.info("Cancel button is by default highlighted");
				extent.extentLoggerPass("Cancel button", "Cancel button is by default highlighted");
			} else {
				logger.error("Cancel button is not  highlighted by default");
				extent.extentLoggerFail("Cancel button", "Cancel button is not highlighted by default");
			}
			click(AMDMoreMenu.objCancelButton, "Cancel button");
			String getPropertyValue2 = getAttributValue("enabled", AMDHomePage.objMoreMenu);
			if (getPropertyValue.equalsIgnoreCase("true")) {
				logger.info("Cancel button is tappable and functional");
				extent.extentLoggerPass("Cancel button", "Cancel button is tappable and functional");
			} else {
				logger.error("Cancel button is NOT tappable and NOT functional");
				extent.extentLoggerFail("Cancel button", "Cancel button is NOT tappable and NOT functional");
			}
			click(AMDMoreMenu.objLogout, "Logout option in More menu");
			click(AMDMoreMenu.objLogoutButton, "Logout button");
			Swipe("DOWN", 1);
			String value = getText(AMDMoreMenu.objProfileHeader);
			if (value.equalsIgnoreCase("Guest")) {
				logger.info("Logout button is tappable and user is logged out successfully");
				extent.extentLoggerPass(" Logout button",
						"Logout button is tappable and user is logged out successfully");
			} else {
				logger.error("Logout button is NOT tappable and user is unable to logout");
				extent.extentLoggerFail(" Logout button", "Logout button is NOT tappable and user is unable to logout");
			}
		}
	}

	public void TermsOfUseScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verifying Terms of Use screen as " + userType);
		System.out.println("Verifying Terms of Use screen as " + userType);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		waitTime(2000);
		Swipe("UP", 2);
		verifyElementPresentAndClick(AMDMoreMenu.objTermsOfUse, "Terms of Use option in More menu");
		verifyElementPresent(AMDMoreMenu.objTermsOfUseHeader, "Terms Of Use Header");
		if (verifyIsElementDisplayed(AMDMoreMenu.objTermsOfUseHeader)) {
			logger.info("User is navigated to Terms of Use screen");
			extent.extentLogger("Terms of Use", "User is navigated to Terms of Use screen");
		} else {
			logger.error("User is unable to navigate to Terms of Use screen");
			extent.extentLoggerFail("Terms of Use", "User is unable to navigate to Terms of Use screen");
		}
		verifyElementPresent(AMDMoreMenu.objcloseButton, "Close button in Terms of Use Screen");
		verifyElementPresent(AMDMoreMenu.objTermsDescription,
				"Breif information of the Application in Terms Of Use Screen");
		Swipe("UP", 10);
		SwipeUntilFindElement(AMDMoreMenu.objsupportHyperlinkInTermsOfUse, "UP");
		// hyperlink 1
		String str1 = getElementPropertyToString("content-desc", AMDMoreMenu.objsupportHyperlinkInTermsOfUse,
				"Hyper link");
		System.out.println(str1);
		verifyElementPresent(AMDMoreMenu.objsupportHyperlinkInTermsOfUse, str1 + " Hyperlink");
		click(AMDMoreMenu.objsupportHyperlinkInTermsOfUse, "Hyperlink " + str1);
		waitTime(2000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objInternetErrormsg)) {
			logger.error("On clicking the Hyperlink " + str1
					+ " User is unable to navigate to the respective page of Hyperlink [ZNA-2533]");
			extent.extentLoggerFail("HyperLink", "On clicking the Hyperlink " + str1
					+ " User is unable to navigate to the respective page of Hyperlink [ZNA-2533]");
		} else {
			logger.info("User is navigated to the respective page of the Hyperlink " + str1);
			extent.extentLogger("Hyperlink", "User is navigated to the respective page of the Hyperlink " + str1);
		}
		Back(1);
		/*
		 * Swipe("UP", 7); // hyperlink 2 //String str2 =
		 * getText(AMDMoreMenu.objfeedbackLinkInTermsOfUse); String str2 =
		 * getElementPropertyToString("content-desc",
		 * AMDMoreMenu.objfeedbackLinkInTermsOfUse, "Feedback Link");
		 * System.out.println(str2);
		 * verifyElementPresent(AMDMoreMenu.objfeedbackLinkInTermsOfUse, str2 +
		 * " Hyperlink"); click(AMDMoreMenu.objfeedbackLinkInTermsOfUse, "Hyperlink " +
		 * str2); if (verifyIsElementDisplayed(AMDMoreMenu.objInternetErrormsg)) {
		 * logger.error("On clicking the Hyperlink " + str2 +
		 * " User is unable to navigate to the respective page of Hyperlink");
		 * extent.extentLoggerFail("HyperLink", "On clicking the Hyperlink " + str2 +
		 * " User is unable to navigate to the respective page of Hyperlink"); } else {
		 * logger.info("User is navigated to the respective page of the Hyperlink " +
		 * str2); extent.extentLogger("Hyperlink",
		 * "User is navigated to the respective page of the Hyperlink " + str2); }
		 * Back(1); // hyper link 3 Swipe("UP", 49); String str3 =
		 * getElementPropertyToString("content-desc",AMDMoreMenu.
		 * objsubscribelinkInTermsofUse, "Subscribe link");
		 * 
		 * System.out.println(str3);
		 * verifyElementExist(AMDMoreMenu.objsubscribelinkInTermsofUse, str3 +
		 * " Hyperlink"); click(AMDMoreMenu.objsubscribelinkInTermsofUse, "Hyperlink " +
		 * str3); if (verifyIsElementDisplayed(AMDSubscibeScreen.objSubscribeHeader)) {
		 * logger.info("On clicking the Hyperlink " + str3 +
		 * " User is navigated succesfully to the respective page: " +
		 * getText(AMDSubscibeScreen.objSubscribeHeader));
		 * extent.extentLogger("HyperLink", "On clicking the Hyperlink " + str3 +
		 * " User is navigated succesfully to the respective page: " +
		 * getText(AMDSubscibeScreen.objSubscribeHeader)); } else { logger.
		 * error("User is unable to navigate to the respective page of the Hyperlink " +
		 * str3); extent.extentLoggerFail("Hyperlink",
		 * "User is unable to navigate to the respective page of the Hyperlink " +
		 * str3); } Back(1); // hyper link 4 String str4 =
		 * getText(AMDMoreMenu.objzee5HyperlinkinTermsOfUse); System.out.println(str4);
		 * verifyElementExist(AMDMoreMenu.objzee5HyperlinkinTermsOfUse, str4 +
		 * " Hyperlink"); click(AMDMoreMenu.objzee5HyperlinkinTermsOfUse, "Hyperlink " +
		 * str4); if (verifyIsElementDisplayed(AMDHomePage.objHomeTab)) {
		 * logger.info("On clicking the Hyperlink " + str4 +
		 * " User is navigated succesfully to the respective page: " +
		 * getText(AMDHomePage.objHomeTab)); extent.extentLogger("HyperLink",
		 * "On clicking the Hyperlink " + str4 +
		 * " User is navigated succesfully to the respective page: " +
		 * getText(AMDHomePage.objHomeTab)); } else { logger.
		 * error("User is unable to navigate to the respective page of the Hyperlink " +
		 * str4); extent.extentLoggerFail("Hyperlink",
		 * "User is unable to navigate to the respective page of the Hyperlink " +
		 * str4); }
		 */
		Back(1);
	}

	public boolean SwipeUntilFindElement(By locator, String direction) throws Exception {

		boolean checkLocator, eletFound = false;
		if (direction.equalsIgnoreCase("UP")) {
			for (int i = 1; i < 25; i++) {
				PartialSwipe("UP", 1);
				checkLocator = verifyIsElementDisplayed(locator);
				if (checkLocator) {
					eletFound = true;
					break;
				}
			}
		}

		if (direction.equalsIgnoreCase("DOWN")) {
			for (int i = 1; i < 25; i++) {
				PartialSwipe("DOWN", 1);
				checkLocator = verifyIsElementDisplayed(locator);
				if (checkLocator) {
					eletFound = true;
					break;
				}
			}
		}
		return eletFound;
	}

	/**
	 * Author : MANASA Module : Download
	 */
	public void downloadSettingsValidation() throws Exception {
		extent.HeaderChildNode("Verify if Quality in Downloads is set to Ask Everytime by default");
		System.out.println("\nDownload settings validation");
//		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
//		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		String quality = getText(AMDMoreMenu.objDownloads_Quality);
		System.out.println(quality);
		if (quality.equalsIgnoreCase("Ask each time")) {
			logger.info("Download Quality is set to " + quality + " by default");
			extent.extentLoggerPass("Download Quality", "Download Quality is set to " + quality + " by default");

		} else {
			logger.error("Download Quality is not set to Ask Each Time  by default");
			extent.extentLoggerFail("Download Quality", "Download Quality is not set to Ask Each Time  by default");
		}

		String state = getText(AMDMoreMenu.objDownloads_WifiOnly);
		System.out.println(state);
		if (state.equalsIgnoreCase("OFF")) {
			logger.info("Download over WiFi only option is in  " + state + " state by default");
			extent.extentLoggerPass("On/Off", "Download over WiFi only option is in  " + state + " state by default");

		} else {
			logger.error("Download over WiFi only option is not in OFF state by default ");
			extent.extentLoggerFail("On/Off", "Download over WiFi only option is not in OFF state by default ");
		}

		WebElement onOffToggle = findElement(AMDMoreMenu.objDownloads_WifiOnly);
		int toggleRightX = onOffToggle.getLocation().getX();
		System.out.println(toggleRightX);
		Dimension sizee = getDriver().manage().window().getSize();
		System.out.println(sizee.getWidth());
		int sizeee = sizee.getWidth() - 300;

		if (toggleRightX >= sizeee) {
			logger.info("On/Off toggle is displayed at right side of the option");
			extent.extentLoggerPass("On/Off Toggle", "On/Off toggle is displayed at right side of the option");
		} else {
			logger.error("On/Off toggle is not displayed at right side of the option");
			extent.extentLoggerFail("On/Off Toggle", "On/Off toggle is not displayed at right side of the option");
		}
		verifyElementPresentAndClick(AMDMoreMenu.objDownloads_Quality, "Download Quality Settings option");
		extent.HeaderChildNode("To verify if Select Download Video Quality screen is displayed");
		if (checkElementExist(AMDSettingsScreen.objSelectVideoQualityLabel, "Select Video Download Quality")) {
			logger.info("Navigated to Select Download Video Quality screen");
			extent.extentLoggerPass("Quality", "Navigated to Select Download Video Quality screen");
		} else {
			logger.error("Not navigated to Select Download Video Quality screen");
			extent.extentLoggerFail("Quality", "Not navigated to Select Download Video Quality screen");
		}
		verifyElementExist(AMDSettingsScreen.objVideoQualityBest, "Best Quality Option");
		verifyElementExist(AMDSettingsScreen.objVideoQualityBetter, "Better Quality Option");
		verifyElementExist(AMDSettingsScreen.objVideoQualityGood, "Good Quality Option");
		verifyElementExist(AMDSettingsScreen.objVideoQualityDatasaver, "Data Saver Quality Option");
		verifyElementExist(AMDSettingsScreen.objVideoQualityAskEachTime, "Ask Each Time Option");
		verifyElementExist(AMDSettingsScreen.objTickMark, "Tick mark");
		verifyElementPresentAndClick(AMDSettingsScreen.objXButton, "X Button");
	}

	public void searchHistoryValidation(String userType) throws Exception {

		extent.HeaderChildNode("Search History Validation");
		System.out.println("\nSearch History Validation");
		waitTime(3000);
		Swipe("UP", 2);
		waitTime(2000);
		WebElement clear = findElement(AMDSettingsScreen.objClearSearchHistory);
		int clearRightX = clear.getLocation().getX();
		Dimension sizee = getDriver().manage().window().getSize();
		int sizeee = sizee.getWidth() / 2;
		if (clearRightX >= sizeee) {
			logger.info("Clear option is displayed at the right side of the Search History");
			extent.extentLoggerPass("Clear", "Clear option is displayed at the right side of the Search History");
		} else {
			logger.error("Clear option is not displayed at the right side of the Search History");
			extent.extentLoggerFail("Clear", "Clear option is not displayed at the right side of the Search History");
		}
		verifyElementPresentAndClick(AMDSettingsScreen.objClearSearchHistory, "Clear Search History");
	}

	public void authenticateDeviceValidation(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Authenticate Device Validation");
			verifyElementPresentAndClick(AMDSettingsScreen.objAuthenticateDevice, "Authenticate Device");

			if (checkElementExist(AMDSettingsScreen.objAuthenticateScreen, "Authenticate Screen")) {
				logger.info("Navigated to Authenticate Screen");
				extent.extentLoggerPass("Authenticate Screen", "Navigated to Authenticate Screen");
			} else {
				logger.error("Not navigated to Authenticate Screen");
				extent.extentLoggerFail("Authenticate Screen", "Not navigated to Authenticate Screen");
			}
			verifyElementPresentAndClick(AMDSettingsScreen.objAuthenticateCloseBtn, "Close Button");
		}
	}

	public void resetSettingsValidation(String userType) throws Exception {
		extent.HeaderChildNode("Reset Settings Validation");
		System.out.println("\nReset Settings Validation");
		verifyElementExist(AMDSettingsScreen.objResetSettings, "Reset Setting to Default");
		verifyElementPresentAndClick(AMDSettingsScreen.objDefaultSetting, "Default Setting Link");
		verifyElementExist(AMDSettingsScreen.objResetSettingPopUp,
				"Are you sure you want to reset your settings?  popup");
		verifyElementExist(AMDSettingsScreen.objNoCTA, "No CTA");
		verifyElementExist(AMDSettingsScreen.objYesCTA, "Yes CTA");
		verifyElementPresentAndClick(AMDSettingsScreen.objNoCTA, "No CTA");
		if (verifyElementIsNotDisplayed(AMDSettingsScreen.objResetSettingPopUp)) {
			logger.info("No CTA is functional & popup disappeared");
			extentLoggerPass("ResetSettingPopUp", "No CTA is functional & popup disappeared");
		} else {
			logger.error("No CTA is not functional & popup not disappeared");
			extentLoggerFail("ResetSettingPopUp", "No CTA is not functional & popup not disappeared");
		}
		verifyElementPresentAndClick(AMDSettingsScreen.objDefaultSetting, "Default Setting Link");
		verifyElementPresentAndClick(AMDSettingsScreen.objYesCTA, "Yes CTA");
		if (verifyElementIsNotDisplayed(AMDSettingsScreen.objResetSettingPopUp)) {
			logger.info("Yes CTA is functional");
			extentLoggerPass("ResetSettingPopUp", "Yes CTA is functional");
		} else {
			logger.error("Yes CTA is not functional");
			extentLoggerFail("ResetSettingPopUp", "Yes CTA is not functional");
		}
//		verifyElementExist(AMDSettingsScreen.objLoadingAnimator, "Loading Animator");
//		verifyElementExist(AMDSettingsScreen.objUpdateSettingsMessage,
//				"Please wait while we update your settings message");

	}

	/**
	 * Author : Bindu Module : Exit_PopUp
	 */
	public void verifyDisplayLanguageScreenExitPopup(String userType) throws Exception {
		extent.HeaderChildNode("Verify Display Language Screen");
		System.out.println("\nVerify Display Language Screen Exit Popup");
		if (userType.equalsIgnoreCase("Guest")) {

			relaunch(true);
			accessDeviceLocationPopUp("Allow", userType);
			verifyElementExist(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
			Back(1);
			waitTime(1000);

			extent.HeaderChildNode("Verify Exit Popup comprises of text message");
			verifyElementExist(AMDOnboardingScreen.objExitPopup, "Exit Popup is displayed");
			String Exitpopup = getDriver().findElement(AMDOnboardingScreen.objExitPopup).getText();
			System.out.println(Exitpopup);
			if (Exitpopup.equalsIgnoreCase("Are you sure you want to exit ZEE5?")) {
				logger.info("Exit popup is displayed with message Are you sure you want to exit Zee5?");
				extent.extentLoggerPass("Display Language Screen",
						"Exit popup is displayed with message Are you sure you want to exit Zee5?");
			} else {
				logger.error("Exit popup is not displayed with message Are you sure you want to exit Zee5?");
				extent.extentLoggerFail("Display Language Screen",
						"Exit popup is displayed with message Are you sure you want to exit Zee5?");
			}
			verifyElementExist(AMDOnboardingScreen.objExitYes, "Exit Popup with Yes CTA");
			verifyElementExist(AMDOnboardingScreen.objExitNo, "Exit Popup with No CTA");

			extent.HeaderChildNode("Verify Exip popup closes on tapping on NO CTA");
			click(AMDOnboardingScreen.objExitNo, "Exit Popup with No CTA");
			// waitTime(2000);
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("Exit Popup closes when user taps on No CTA");
				extent.extentLoggerPass("Display Language Screen", "Exit popup closes when user taps on No CTA");
			} else {
				logger.error("Exit Popup not closed when user taps on No CTA");
				extent.extentLoggerFail("Display Language Screen", "Exit Popup not closed when user taps on No CTA");
			}

			extent.HeaderChildNode("Verify Exit Popup closes by pulling down the Popup manually");
			Back(1);
			verifyElementPresentAndClick(AMDOnboardingScreen.objExitPopupDivider, "ExitPopup Divider");
			waitTime(2000);
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("Exit popup closes by pulling down the popup manually");
				extent.extentLoggerPass("Display Language Screen",
						"Exit popup closes by pulling down the popup manually");
			} else {
				logger.error("Exit popup not closed by pulling down the popup manually");
				extent.extentLoggerFail("Display Language Screen",
						"Exit popup not closed by pulling down the popup manually");
			}

			extent.HeaderChildNode(
					"Verify Exit Popup closes by tapping on the Horizontal line bar which appears on the popup");
			Back(1);
			verifyElementPresentAndClick(AMDOnboardingScreen.objExitPopupHorizontalLinebar,
					"Exit Popup Horizontal LineBar");
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("Exit Popup closes by tapping on the Horizontal Line Bar");
				extent.extentLoggerPass("Display Language Screen",
						"Exit Popup closes by tapping on the Horizontal Line Bar");
			} else {
				logger.error("Exit Popup not closed by tapping on the Horizontal Line Bar");
				extent.extentLoggerFail("Display Language Screen",
						"Exit Popup not closed by tapping on the Horizontal Line Bar");
			}

			extent.HeaderChildNode("Verify Exit Popup closes by tapping on the Screen");
			Back(1);
			verifyElementPresentAndClick(AMDOnboardingScreen.objExitPopupDivider, "ExitPopup Divider");
			waitTime(2000);
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("Exit Popup closes by tapping on the screen");
				extent.extentLoggerPass("Display Language Screen", "Exit Popup closes by tapping on the screen");
			} else {
				logger.error("Exit Popup not closed by tapping on the screen");
				extent.extentLoggerFail("Display Language Screen", "Exit Popup not closed by tapping on the screen");
			}

			extent.HeaderChildNode(" Verify Exit Popuop displayed on tapping device back button");
			Back(1);
			if (checkElementExist(AMDOnboardingScreen.objExitPopup, "Exit Popup is displayed")) {
				logger.error("Exit Popup appears when user taps on device back button");
				extent.extentLoggerPass("Display Language Screen",
						"Exit Popup appears when user taps on device back button");
			} else {
				logger.info("Exit Popup not appeared when user taps on device back button");
				extent.extentLoggerFail("Display Language Screen",
						"Exit Popup not appeared when user taps on device back button");

			}

			extent.HeaderChildNode("Verify Exit Popup closes when user taps on device back button");
			Back(1);
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("Exit Popup closes by tapping on the device back button");
				extent.extentLoggerPass("Display Language Screen",
						"Exit Popup closes by tapping on the device back button");
			} else {
				logger.error("Exit Popup not closed by tapping on the device back button");
				extent.extentLoggerFail("Display Language Screen",
						"Exit Popup not closed by tapping on the device back button");
			}

			extent.HeaderChildNode("Verify that user exists the app on tapping the Yes CTA in the Exit Popup");
			Back(1);
			verifyElementPresentAndClick(AMDOnboardingScreen.objExitYes, "Exit Popup with Yes CTA");
			waitTime(2000);
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("User exists from the app when user taps on Yes CTA");
				extent.extentLoggerPass("Display Language Screen",
						"User exists from the app when user taps on Yes CTA");
			} else {
				logger.error("User not exists from the app when user taps on Yes CTA");
				extent.extentLoggerFail("Display Language Screen",
						"User not exists from the app when user taps on Yes CTA");
			}
			relaunch(true);
		} else {
			logger.info("Display Language Screen validation is not applicable for " + userType);
			extent.extentLoggerPass("Display Language Screen",
					"Display Language Screen validation is not applicable for " + userType);
		}
	}

	public void LoginAfterLogout(String userType) throws Exception {

		if (userType.contentEquals("NonSubscribedUser")) {
			click(AMDMoreMenu.objProfile, "Profile");
			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, Password, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
		}

		if (userType.contentEquals("SubscribedUser")) {
			click(AMDMoreMenu.objProfile, "Profile");

			String SubscribedUsername = getParameterFromXML("SubscribedUserName");
			String SubscribedPassword = getParameterFromXML("SubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
		}
	}

	public void logoutOfflineValidation(String userType) throws Exception {

		if (userType.contentEquals("NonSubscribedUser") || userType.contentEquals("SubscribedUser")) {
			extent.HeaderChildNode("Offline Validation of Logout option as " + userType);
			System.out.println("Offline Validation of Logout option as " + userType);
			click(AMDHomePage.objMoreMenu, "More menu");
			Swipe("UP", 2);
			click(AMDMoreMenu.objLogout, "Logout option in More menu");
			setWifiConnectionToONOFF("Off");
			verifyElementExist(AMDMoreMenu.objNetworkerrormsg, "Internet connectivity error message");
			if (checkElementExist(AMDMoreMenu.objNetworkerrormsg, "Internet connectivity error message")) {
				logger.info(
						"Internet connectivity ERROR message is displayed on clicking Logout button without Internet");
				extent.extentLoggerPass(" Logout button",
						"Internet connectivity ERROR message is displayed on clicking Logout button without Internet");
			} else {
				logger.error(
						"Internet connectivity ERROR message is NOT displayed on clicking Logout button without Internet");
				extent.extentLoggerFail(" Logout button",
						"Internet connectivity ERROR message is NOT displayed on clicking Logout button without Internet");
			}
			setWifiConnectionToONOFF("On");
			Back(1);
			click(AMDHomePage.HomeIcon, "Home Icon");
		}
	}

	public void PrivacyPolicyScreen(String userType) throws Exception {
		extent.HeaderChildNode("Validation of Privacy Policy Screen as " + userType);
		System.out.println("Validation of Privacy Policy Screen as " + userType);
		click(AMDHomePage.objMoreMenu, "More menu");
		Swipe("UP", 2);
		verifyElementPresentAndClick(AMDMoreMenu.objPrivacyPolicy, "Privacy Policy option in More menu");
		waitTime(4000);
		verifyElementPresent(AMDMoreMenu.objPrivacyPolicyHeader, "Privacy Policy Header");
		if (verifyIsElementDisplayed(AMDMoreMenu.objPrivacyPolicyHeader)) {
			logger.info("User is navigated to Privacy Policy screen");
			extent.extentLogger("Privacy Policy", "User is navigated to Privacy Policy screen");
		} else {
			logger.error("User is unable to navigate to Privacy Policy screen");
			extent.extentLoggerFail("Privacy Policy", "User is unable to navigate to Privacy Policy screen");
		}
		verifyElementPresent(AMDMoreMenu.objcloseButton, "Close button in Privacy Policy Screen");
		verifyElementPresent(AMDMoreMenu.objPrivacyDescription,
				"Breif Description about the Application in Privacy Policy Screen");
		verifyElementPresent(AMDMoreMenu.objHyperlinkInPrivacyPolicy, "Hyper link in Privacy Policy");
		String str1 = getElementPropertyToString("content-desc", AMDMoreMenu.objHyperlinkInPrivacyPolicy, "Hyper Link");
		System.out.println(str1);
		verifyElementExist(AMDMoreMenu.objHyperlinkInPrivacyPolicy, str1 + " Hyperlink");
		click(AMDMoreMenu.objHyperlinkInPrivacyPolicy, "Hyperlink " + str1);
		if (verifyIsElementDisplayed(AMDMoreMenu.objPrivacyPolicyPageWithinbrowser)) {
			logger.info("On clicking the Hyperlink " + str1 + " , User is able to navigate to the respective page");
			extent.extentLogger("HyperLink",
					"On clicking the Hyperlink " + str1 + " , User is able to navigate to the respective page");
		} else {
			logger.error("User is unable to navigate to the respective page of the Hyperlink [ZNA-2533] " + str1);
			extent.extentLoggerFail("Hyperlink",
					"User is unable to navigate to the respective page of the Hyperlink [ZNA-2533] " + str1);
		}
		waitTime(2000);
		Back(1);
		/*
		 * Swipe("UP", 23); String str2 =
		 * getElementPropertyToString("content-desc",AMDMoreMenu.
		 * objsupportlinkInprivacypolicy,"Support link"); System.out.println(str2);
		 * verifyElementPresent(AMDMoreMenu.objsupportlinkInprivacypolicy, str2 +
		 * " Hyperlink"); waitTime(2000);
		 * click(AMDMoreMenu.objsupportlinkInprivacypolicy, "Hyperlink " + str2); //
		 * defect if (verifyIsElementDisplayed(AMDMoreMenu.objInternetErrormsg)) {
		 * logger.error("On clicking the Hyperlink " + str2 +
		 * " User is unable to navigate to the respective page of Hyperlink");
		 * extent.extentLoggerFail("HyperLink", "On clicking the Hyperlink " + str2 +
		 * " User is unable to navigate to the respective page of Hyperlink"); } else {
		 * logger.info("User is navigated to the respective page of the Hyperlink " +
		 * str2); extent.extentLogger("Hyperlink",
		 * "User is navigated to the respective page of the Hyperlink " + str2); }
		 * Back(1);
		 */
		click(AMDMoreMenu.objcloseButton, "Close button");
		// click(AMDHomePage.HomeIcon, "Home icon");
	}

	public void staticPagesInDisplayLanguage() throws Exception {
		extent.HeaderChildNode("Static Page Verification in selected language");
		System.out.println("\nStatic Page Verification in selected language");
		click(AMDHomePage.objMoreMenu, "More menu");
		waitTime(2000);
		SwipeUntilFindElement(AMDMoreMenu.objSettings, "UP");
		click(AMDMoreMenu.objSettings, "Settings");
		Swipe("UP", 2);
		click(AMDMoreMenu.objDisplayLang, "Display Language");
		click(AMDOnboardingScreen.objSelectDisplayLang("Kannada"), "Kannada language");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "[Display Language] Continue Button");
		click(AMDMoreMenu.objBackbtnInSettings, "Back button");
		click(AMDHomePage.objMoreMenu, "More menu");
		waitTime(1000);
		Swipe("UP", 2);
		// About Us
		boolean aboutUsInKannada = verifyElementPresentAndClick(AMDMoreMenu.objAboutUsInKannada,
				"About Us option in Kannada");
		if (aboutUsInKannada) {
			logger.info("About Us option is displayed in Kannada language script");
			extent.extentLoggerPass("About Us", "About Us option is displayed in Kannada language script");
		} else {
			logger.error("About Us option is NOT displayed in Kannada language script");
			extent.extentLoggerFail("About Us", "About Us option is NOT displayed in Kannada language script");
		}

		if (checkElementExist(AMDMoreMenu.objTextInAboutUsScreen)) {
			logger.error("Content of the About Us Page is not according to the display language set by user");
			extent.extentLoggerFail("About Us",
					"Content of the About Us Page is not according to the display language set by user");
		} else {
			logger.info("Content of the About Us page is according to display language set by user");
			extent.extentLoggerPass("About Us",
					"Content of the About Us page is according to display language set by user");
		}
		click(AMDMoreMenu.objcloseButton, "Close button");
		Swipe("UP", 1);
		// Check for Help Center option in selected language
		verifyElementPresentAndClick(AMDMoreMenu.objHelpCenterInKannada, "Help Center option in Kannada");
		waitTime(3000);
		String title = getText(AMDMoreMenu.objHelpCenterHeader);
		if (title.contains("Help Center")) {
			logger.info("Title of the page displayed: " + title);
			extent.extentLogger("pagetitle", "Title of the page displayed: " + title);
			logger.error("Content of Help Center page is not according to the display language set by user");
			extent.extentLoggerFail("Help Center",
					"Content of Help Center page is not according to the display language set by user");
		} else {
			logger.info("Content of the Help Center page is according to display language set by user");
			extent.extentLoggerPass("About Us",
					"Content of the Help Center page is according to display language set by user");
		}
		click(AMDMoreMenu.objcloseButton, "Close button");
		Swipe("UP", 1);
		// Terms of Use
		verifyElementPresentAndClick(AMDMoreMenu.objTermsInKannada, "Terms of Use option in Kannada");
		waitTime(2000);
		String title2 = getText(AMDMoreMenu.objTermsOfUseHeader);
		if (title2.contains("Terms of Use")) {
			logger.info("Title of the page displayed: " + title2);
			extent.extentLogger("pagetitle", "Title of the page displayed: " + title2);
			logger.error("Content of the Terms of Use page is not according to the display language set by user");
			extent.extentLoggerFail("Terms of Use",
					"Content of the Terms of Use page is not according to the display language set by user");
		} else {
			logger.info("Content of the Terms of Use page is according to display language set by user");
			extent.extentLoggerPass("About Us",
					"Content of the Terms of Use page is according to display language set by user");
		}
		click(AMDMoreMenu.objcloseButton, "Close button");
		// Privacy Policy
		verifyElementPresentAndClick(AMDMoreMenu.objPrivacyPolicyInKannada, "Privacy Policy option in Kannada");
		String title3 = getText(AMDMoreMenu.objPrivacyPolicyHeader);
		if (title3.contains("Privacy Policy")) {
			logger.info("Title of the page displayed: " + title3);
			extent.extentLogger("pagetitle", "Title of the page displayed: " + title3);
			logger.info("Content of the Privacy Policy page is according to display language set by user");
			extent.extentLoggerPass("About Us",
					"Content of the Privacy Policy page is according to display language set by user");
		} else {
			logger.error("Content of the Privacy Policy page is not according to the display language set by user");
			extent.extentLoggerFail("Privacy Policy",
					"Content of the Privacy Policy page is not according to the display language set by user");
		}
		click(AMDMoreMenu.objcloseButton, "Close button");
		// Changing to English
		click(AMDMoreMenu.objSettingsInKannada, "Settings");
		Swipe("UP", 1);
		click(AMDMoreMenu.objDisplayLang, "Display Language");
		click(AMDOnboardingScreen.objSelectDisplayLang("English"), "English language");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "[Display Language] Continue Button");
		Back(1);
	}

	/**
	 * Author : Kushal
	 * 
	 * @throws Exception
	 */

	public void ListingCollectionValidationFromLandingScreen(String userType) throws Exception {
		extent.HeaderChildNode("Listing Collection validation from Landing Screen");
		System.out.println("\nListing Collection validation from Landing Screen");

		click(AMDHomePage.objHome, "Home button");

		waitTime(5000); // To Load the landing page completely
		boolean liveTV = false, loadingIcon = false, navigationFlag = false;
		int noOfTabs = getCount(AMDHomePage.objTitle);
		String getTrayName = null, getPageTitle;

		System.out.println("\nTop Nagivation Tabs: " + noOfTabs);
		for (int i = 1; i <= 10; i++) {
			String tabName = null;
			if (i == noOfTabs) {
				if (!liveTV) {
					i = noOfTabs - 1;
				}
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				eleTab.click();
				tabName = getText(AMDHomePage.objSelectedTab);
			} else {
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				eleTab.click();
				tabName = getText(AMDHomePage.objSelectedTab);
			}

			System.out.println(getText(AMDHomePage.objSelectedTab) + " Landing Screen");

			waitTime(3000);
			if (verifyIsElementDisplayed(AMDGenericObjects.objFirstTrayTitle)) {
				loadingIcon = false;
			} else if (verifyIsElementDisplayed(AMDGenericObjects.objPageLoadingIcon)) {
				loadingIcon = true;
				System.out.println("\nContinuous Loading screen displayed");
			}

			if (!loadingIcon) {
				if (userType.equalsIgnoreCase("Guest")) {
					getTrayName = getText(AMDGenericObjects.objFirstTrayTitle);
					click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
				} else {
					if (verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTray)
							| verifyIsElementDisplayed(AMDHomePage.objBannerAd)) {
						Swipe("UP", 1);
						waitTime(2000);
					}
					PartialSwipe("UP", 1);
					waitTime(2000);
					int noOfTrays = getCount(AMDGenericObjects.objNoOfTrays);
					if (noOfTrays > 0) {
						if (tabName.contains("Live TV") | tabName.contains("News")) {
							getTrayName = getText(AMDGenericObjects.objFirstTrayTitle);
							click(AMDGenericObjects.objFirstTrayTitle, getText(AMDGenericObjects.objFirstTrayTitle));
						} else {
							if (tabName.contains("Music")) {
								PartialSwipe("UP", 2);
								waitTime(2000);
							}
							getTrayName = getText(AMDGenericObjects.objTrayTitleByIndx(noOfTrays));
							click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
						}

					}
				}

				getPageTitle = getText(AMDHomePage.objTitle);
				if (getTrayName.contains(getPageTitle)) {
					navigationFlag = true;
					extent.extentLoggerPass("Listing Collection", tabName + ": " + userType
							+ " is able to navigate to listing collection: " + getTrayName + " Screen");
					logger.info(
							userType + " User is able to navigate to listing collection: " + getTrayName + " Screen");
				} else {
					extent.extentLoggerFail("Listing Collection", tabName + ": " + userType
							+ " failed to navigate to listing collection: " + getTrayName + " Screen");
					logger.error(
							userType + " User failed to navigate to listing collection: " + getTrayName + " Screen");
				}

				// Navigates back to landing screen
				if (navigationFlag) {
					Back(1);
				}
			} else {
				extent.extentLoggerFail("Listing Collection", tabName + ": Failed to load the Page");
				logger.error(tabName + ": FAILED to Load the page");
			}

			// Following code is to break the loop after last tab validation in the landing
			// screen
			if (liveTV) {
				break;
			}
			if (tabName.equalsIgnoreCase("Live TV")) {
				liveTV = true;
			}
		}
	}

	@SuppressWarnings("unused")
	public void ListingCollectionValidationFromConsumptionScreen(String userType) throws Exception {
		extent.HeaderChildNode("Listing Collection Screen validation from ConsumptionScreen");
		System.out.println("\nListing Collection Screen validation from ConsumptionScreen");

		click(AMDHomePage.objHome, "Home button");

		waitTime(5000); // To Load the landing page completely
		boolean liveTV = false, loadingIcon = false;
		int noOfTabs = getCount(AMDHomePage.objTitle);
		String getTrayName = null, getPageTitle;
		String width = null, height = null, bounds, getboundvalue;

		System.out.println("\nTop Nagivation Tabs: " + noOfTabs);
		for (int i = 1; i <= 10; i++) {
			String tabName = null, pageNameAPI = null;
			boolean navigationFlag = false;
			if (i == noOfTabs) {
				if (!liveTV) {
					i = noOfTabs - 1;
				}
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				eleTab.click();
				tabName = getText(AMDHomePage.objSelectedTab);
			} else {
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				eleTab.click();
				tabName = getText(AMDHomePage.objSelectedTab);
			}

			System.out.println(getText(AMDHomePage.objSelectedTab) + " Landing Screen");

			waitTime(6000);
			if (verifyIsElementDisplayed(AMDGenericObjects.objFirstTrayTitle)) {
				loadingIcon = false;
				if (!tabName.equalsIgnoreCase("Live TV")) {
					width = getAttributValue("width", AMDHomePage.objCarouselConetentCard);
					bounds = getAttributValue("bounds", AMDHomePage.objCarouselConetentCard);
					getboundvalue = bounds.replaceAll(",", " ").replaceAll("]", " ");
					height = getboundvalue.split(" ")[1];
				}

			} else if (verifyIsElementDisplayed(AMDGenericObjects.objPageLoadingIcon)) {
				loadingIcon = true;
				System.out.println("\nContinuous Loading screen displayed");
			}

			switch (tabName) {
			case "Home":
				pageNameAPI = "homepage";
				break;

			case "Shows":
				pageNameAPI = "tvshows";
				break;

			case "Movies":
				pageNameAPI = "movies";
				break;

			case "Premium":
				pageNameAPI = "premiumcontents";
				break;

			case "Club":
				pageNameAPI = null;
				break;

			case "News":
				pageNameAPI = "626";
				break;

			case "Kids":
				pageNameAPI = "3673";
				break;

			case "Music":
				pageNameAPI = "2707";
				break;

			case "Live TV":
				pageNameAPI = null;
				break;

			case "ZEE5 Originals":
				pageNameAPI = "zeeoriginals";
				break;

			default:
				break;
			}

			if (!loadingIcon) {

//				if(pageNameAPI!=null) {
//					String courselContentTitle = carouselValidationWithApi(userType, pageNameAPI);
//					System.out.println("API Card : "+courselContentTitle);
//					
//					for(int k=1;k<=7; k++) {
//						if (verifyIsElementDisplayed(AMDGenericObjects.objCarouselTitle(courselContentTitle))) {
//							click(AMDGenericObjects.objCarouselTitle(courselContentTitle), courselContentTitle);
//							break;
//						} else {
//							carouselCardsSwipe("LEFT", 1, width, height);
//						}
//					}
//				}else {
//					click(AMDHomePage.objCarouselTitle, getText(AMDHomePage.objCarouselTitle));
//				}

				if (tabName.equalsIgnoreCase("Live TV")) {
					waitTime(2000);
					waitForElementDisplayed(AMDGenericObjects.objNoOfTrays, 3000);
					click(AMDHomePage.objFirstChannelCard, "First available channel");
				} else if (tabName.equalsIgnoreCase("Kids")) {
					click(AMDHomePage.objKidsContentCard, "Kids Content card");
				} else {
					click(AMDHomePage.objCarouselTitle, getText(AMDHomePage.objCarouselTitle));
					waitForElementDisplayed(AMDGenericObjects.objFirstTrayTitle, 3000);
				}
				if (userType.contains("Guest") | userType.contains("NonSubscribedUser")) {
					waitTime(3000);
					if (verifyIsElementDisplayed(AMDGenericObjects.objPopUpDivider)) {
						click(AMDGenericObjects.objPopUpDivider, "Subcription Pop Up");
						extent.extentLoggerPass("Subscription PopUp", userType
								+ "Subcription PopUp is displayed in the comsumption screen and popup is closed");
						logger.info("Subcription PopUp is displayed in the comsumption screen and popup is closed");
					}
					registerPopUpClose();
					completeProfilePopUpClose(pUserType);
				}
				if (tabName.contains("News") | tabName.contains("Live TV")) {
					extent.extentLoggerPass("Listing Trays",
							"Listing Trays are unavailable in NEWS Consumption screen");
					logger.info("Listing Trays are unavailable in NEWS Consumption screen");
					navigationFlag = true;
				} else {

					if (userType.equalsIgnoreCase("Guest")) {
						getTrayName = getText(AMDGenericObjects.objFirstTrayTitle);
						click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
					} else {
						PartialSwipe("UP", 1);
						waitTime(2000);
						int noOfTrays = getCount(AMDGenericObjects.objNoOfTrays);
						if (noOfTrays > 0) {
							if (tabName.contains("Live TV")) {
								getTrayName = getText(AMDGenericObjects.objFirstTrayTitle);
							} else {
								getTrayName = getText(AMDGenericObjects.objTrayTitleByIndx(noOfTrays));
							}
							click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
						}
					}

					getPageTitle = getText(AMDHomePage.objTitle);
					if (getTrayName.contains(getPageTitle)) {
						navigationFlag = true;
						extent.extentLoggerPass("Listing Collection", tabName + ": " + userType
								+ " is able to navigate to listing collection: " + getTrayName + " Screen");
						logger.info(tabName + ": " + userType + " User is able to navigate to listing collection: "
								+ getTrayName + " Screen");
					} else {
						extent.extentLoggerFail("Listing Collection", tabName + ": " + userType
								+ " failed to navigate to listing collection: " + getTrayName + " Screen");
						logger.error(tabName + ": " + userType + " User failed to navigate to listing collection: "
								+ getTrayName + " Screen");
					}
				}
				waitTime(2000);
				// Navigates back to landing screen
				if (navigationFlag) {
					Back(1);
				}
			} else {
				extent.extentLoggerFail("Listing Collection", tabName + ": Failed to load the Page");
				logger.error(tabName + ": FAILED to Load the page");
			}

			// Following code is to break the loop after last tab validation in the
			// landingscreen
			if (liveTV) {
				break;
			}
			if (tabName.equalsIgnoreCase("Live TV")) {
				liveTV = true;
			}
		}
	}

	/**
	 * Author : Bindu
	 * 
	 * @param userType
	 * @throws Exception
	 */
	public void verifyExitPopupInAnyOfTheLandingScreen(String userType) throws Exception {

		if (userType.equalsIgnoreCase("Guest")) {
			accessDeviceLocationPopUp("Allow", userType);
			navigateToIntroScreen_DisplaylangScreen();
			navigateToHomecreenFromIntroScreen();
		}
		extent.HeaderChildNode("Validating ExitPopup in any of the Landing Screen");
		System.out.println("\nverify Landing Screen Exit Popup");
		verifyElementPresent(AMDHomePage.objHomeTab, "Home Tab");
		Back(1);
		if (checkElementExist(AMDOnboardingScreen.objExitPopup, "Exit Popup")) {
			logger.info("Exit Popup appears when user taps on device back button");
			extent.extentLoggerPass("Landing Screen", "Exit Popup appears when user taps on device back button");
		} else {
			logger.error("Exit Popup not appeared when user taps on device back button");
			extent.extentLoggerFail("Landing Screen", "Exit Popup not appeared when user taps on device back button");

		}

		extent.HeaderChildNode("Verify Exit Popup comprises of text message");
		verifyElementExist(AMDOnboardingScreen.objExitPopup, "Exit Popup is displayed");
		String Exitpopup = getDriver().findElement(AMDOnboardingScreen.objExitPopup).getText();
		System.out.println(Exitpopup);
		if (Exitpopup.equalsIgnoreCase("Are you sure you want to exit ZEE5?")) {
			logger.info("Exit popup is displayed with message Are you sure you want to exit Zee5?");
			extent.extentLoggerPass("Landing Screen",
					"Exit popup is displayed with message Are you sure you want to exit Zee5?");
		} else {
			logger.error("Exit popup is not displayed with message Are you sure you want to exit Zee5?");
			extent.extentLoggerFail("Landing Screen",
					"Exit popup is displayed with message Are you sure you want to exit Zee5?");
		}
		verifyElementExist(AMDOnboardingScreen.objExitYes, "Exit Popup with Yes CTA");
		verifyElementExist(AMDOnboardingScreen.objExitNo, "Exit Popup with No CTA");

		extent.HeaderChildNode("verify Exit Popup closes when user taps on No CTA");
		click(AMDOnboardingScreen.objExitNo, "Exit Popup with No CTA");
		// waitTime(2000);
		if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
			logger.info("Exit Popup closes when user taps on No CTA");
			extent.extentLoggerPass("Landing Screen", "Exit popup closes when user taps on No CTA");
		} else {
			logger.error("Exit Popup not closed when user taps on No CTA");
			extent.extentLoggerFail("Landing Screen", "Exit Popup not closed when user taps on No CTA");
		}

		extent.HeaderChildNode("Verify Exit Popup closes by pulling down the Popup manually");
		Back(1);
		verifyElementPresentAndClick(AMDOnboardingScreen.objExitPopupDivider, "ExitPopup Divider");
		waitTime(2000);
		if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
			logger.info("Exit popup closes by pulling down the popup manually");
			extent.extentLoggerPass("Landing Screen", "Exit popup closes by pulling down the popup manually");
		} else {
			logger.error("Exit popup not closed by pulling down the popup manually");
			extent.extentLoggerFail("Landing Screen", "Exit popup not closed by pulling down the popup manually");
		}

		extent.HeaderChildNode(
				"Verify Exit Popup closes by tapping on the Horizontal line bar which appears on the popup");
		Back(1);
		click(AMDOnboardingScreen.objExitPopupHorizontalLinebar, "Exit Popup Horizontal LineBar");
		if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
			logger.info("Exit Popup closes by tapping on the Horizontal Line Bar");
			extent.extentLoggerPass("Landing Screen", "Exit Popup closes by tapping on the Horizontal Line Bar");
		} else {
			logger.error("Exit Popup not closed by tapping on the Horizontal Line Bar");
			extent.extentLoggerFail("Landing Screen", "Exit Popup not closed by tapping on the Horizontal Line Bar");
		}

		extent.HeaderChildNode("Verify Exit Popup closes by tapping on the Screen");
		Back(1);
		verifyElementPresentAndClick(AMDOnboardingScreen.objExitPopupDivider, "ExitPopup Divider");
		waitTime(2000);
		if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
			logger.info("Exit Popup closes by tapping on the screen");
			extent.extentLoggerPass("Landing Screen", "Exit Popup closes by tapping on the screen");
		} else {
			logger.error("Exit Popup not closed by tapping on the screen");
			extent.extentLoggerFail("Landing Screen", "Exit Popup not closed by tapping on the screen");
		}

		extent.HeaderChildNode("Verify Exit Popup closes when user taps on device back button");
		Back(2);
		if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
			logger.info("Exit Popup closes by tapping on the device back button");
			extent.extentLoggerPass("Landing Screen", "Exit Popup closes by tapping on the device back button");
		} else {
			logger.error("Exit Popup not closed by tapping on the device back button");
			extent.extentLoggerFail("Landing Screen", "Exit Popup not closed by tapping on the device back button");
		}
		extent.HeaderChildNode("Verify that user exists the app on tapping the Yes CTA in the Exit Popup");
		Back(1);
		verifyElementPresentAndClick(AMDOnboardingScreen.objExitYes, "Exit Popup with Yes CTA");
		waitTime(2000);
		if (verifyElementIsNotDisplayed(AMDOnboardingScreen.objExitPopup)) {
			logger.info("User Exits from the app when user taps on Yes CTA");
			extent.extentLoggerPass("Landing Screen", "User Exits from the app when user taps on Yes CTA");
		} else {
			logger.error("User fails to exit from the app when user taps on Yes CTA");
			extent.extentLoggerFail("Landing Screen", "User fails to exit from the app when user taps on Yes CTA");
		}
	}

	/**
	 * Author : Hitesh Module : More Screen Screen : My Watchlist
	 * 
	 * @throws Exception
	 */

	public void myWatchList(String userType) throws Exception {
		HeaderChildNode("Validate My Watchlist");
		switch (userType) {
		case "Guest":
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "My Watchlist");
			if (verifyElementDisplayed(AMDLoginScreen.objLoginOrRegisterPageTitle)) {
				logger.info("User navigated to login or register screen post taping on my watchlist");
				extent.extentLogger("watchlist",
						"User navigated to login or register screen post taping on my watchlist");
			} else {
				logger.error("User is not navigated to login or register screen post taping on my watchlist");
				extent.extentLoggerFail("watchlist",
						"User is not navigated to login or register screen post taping on my watchlist");
			}
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back button");
			if (verifyElementDisplayed(AMDMoreMenu.objWatchlist)) {
				logger.info("User is navigate back to more menu");
				extent.extentLogger("watchlist", "User is navigate back to more menu");
			} else {
				logger.error("User is not navigate back to more menu");
				extent.extentLoggerFail("watchlist", "User is not navigate back to more menu");
			}
			break;
		case "NonSubscribedUser":
			VerifyWatchListScreen();
			break;
		case "SubscribedUser":
			VerifyWatchListScreen();
			break;
		}
	}

	@SuppressWarnings("deprecation")
	public void VerifyWatchListScreen() throws Exception {
		waitTime(3000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		click(AMDMoreMenu.objWatchlist, "My Watchlist");
		if (verifyIsElementDisplayed(AMDWatchlistPage.objTabs(1))) {
			String selectedTab = findElement(AMDWatchlistPage.objSelectedTab).getText();
			if (selectedTab.equals("Shows")) {
				logger.info("Shows tab is selected default");
				extent.extentLogger("watchlist", "Shows tab is selected default");
			} else {
				logger.error("Shows tab is not selected as default tab");
				extent.extentLoggerFail("watchlist", "Shows tab is not selected as default tab");
			}
		}
		if (verifyIsElementDisplayed(AMDWatchlistPage.objWatchlistTitle)) {
			logger.info("User navigated to my watchlist screen");
			extent.extentLogger("watchlist", "User navigated to my watchlist screen");
		} else {
			logger.error("User not navigated to my watchlist screen");
			extent.extentLoggerFail("watchlist", "User not navigated to my watchlist screen");
		}
		if (verifyIsElementDisplayed(AMDWatchlistPage.objNoReminderIcon)) {
			logger.info("watchlist screen displayed with Empty Watchlist icon");
			extent.extentLogger("watchlist", "watchlist screen displayed with Empty Watchlist icon");
			verifyElementPresent(AMDWatchlistPage.objNoReminderIcon, "No Reminder Icon");
			verifyElementPresent(AMDWatchlistPage.objNoReminderTxt, "No Reminder Text");
			Back(2);
			click(AMDHomePage.objSearchBtn, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			getDriver().getKeyboard().sendKeys("Aditya-Paarvathi challenge themselves for fun - Paaru");
			click(AMDSearchScreen.objSearchResultFirstContent, "content");
			waitTime(3000);
			verifyElementPresentAndClick(AMDWatchlistPage.objWatchlistIcon, "Watchlist icon");
			waitTime(3000);
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "My Watchlist");
			Back(1);
		} else {
			logger.info("watchlist screen has some content");
			extent.extentLogger("watchlist", "watchlist screen has some content");
		}
		click(AMDWatchlistPage.objBackBtn, "Back button");
		if (verifyIsElementDisplayed(AMDMoreMenu.objWatchlist)) {
			logger.info("User successfully navigated to previous screen");
			extent.extentLogger("watchlist", "User successfully navigated to previous screen");
		} else {
			logger.info("User not navigated to previous screen");
			extent.extentLogger("watchlist", "User not navigated to previous screen");
		}
		click(AMDMoreMenu.objWatchlist, "watchlist");
		if (verifyIsElementDisplayed(AMDWatchlistPage.objNoReminderIcon)) {

		}
		verifyElementPresentAndClick(AMDWatchlistPage.objEditBtn, "Edit button");
		verifyElementPresent(AMDWatchlistPage.objSelectAllIcon, "Select All");
		click(AMDWatchlistPage.objSelectAllIcon, "Select All");
		for (int i = 1; i <= findElements(AMDWatchlistPage.objSelectCheckBox).size(); i++) {
			if (findElement(AMDWatchlistPage.objSelectContentByIndex(i)).getAttribute("checked").equals("true")) {
				logger.info(i + "st content is selected");
				extent.extentLogger("watchlist", i + "st content is selected");
			} else {
				logger.error(i + "st content is not selected");
				extent.extentLoggerFail("watchlist", i + "st content is not selected");
			}
		}
		if (verifyIsElementDisplayed(AMDWatchlistPage.objDeleteAllBtn)) {
			logger.info("Clicking Select All button will select all the contents in the current screen for deletion");
			extent.extentLogger("watchlist", "User successfully navigated to previous screen");
		} else {
			logger.info("User not navigated to previous screen");
			extent.extentLogger("watchlist", "User not navigated to previous screen");
		}
		click(AMDWatchlistPage.objCloseIcon, "Close icon");
		for (int i = 1; i <= findElements(AMDWatchlistPage.objNumberOfTabs).size(); i++) {
			String tabName = findElement(AMDWatchlistPage.objTabs(i)).getText();
			if (i == 1) {
				logger.info(tabName + " is selected by default");
				extent.extentLogger("watchlist", tabName + " is selected by default");
			} else {
				click(AMDWatchlistPage.objTabs(i), i + "nd tab");
				if (findElement(AMDWatchlistPage.objTabs(i)).isSelected()) {
					logger.info(tabName + " is selected");
					extent.extentLogger("watchlist", tabName + " is selected");
				} else {
					logger.error(tabName + " is not selected");
					extent.extentLoggerFail("watchlist", tabName + " is not selected");
				}
			}
		}
		click(AMDWatchlistPage.objTabs(1), "Shows tab");

		for (int i = 1; i < findElements(AMDWatchlistPage.objContentThumbnail).size(); i++) {
			String title = findElement(AMDWatchlistPage.objIterateTitle(i)).getText();
			String episode = findElement(AMDWatchlistPage.objIterateEpisode(i)).getText();
			logger.info(title + " is added to watchlist, it contains " + episode);
			extent.extentLogger("watchlist", title + " is added to watchlist, it contains " + episode);
		}
		verifyElementPresentAndClick(AMDWatchlistPage.objIterateEpisode(1), "First content in shows tab");
		String duration = findElement(AMDWatchlistPage.objDurationtxt).getText();
		logger.info("Duration of episode is : " + duration);
		extent.extentLogger("watchlist", "Duration of episode is : " + duration);
		click(AMDWatchlistPage.objBackBtn, "Back button");
		if (verifyIsElementDisplayed(AMDWatchlistPage.objTitle)) {
			logger.info("User navigated to previous screen");
			extent.extentLogger("watchlist", "User navigated to previous screen");
		} else {
			logger.error("User is not navigated to previous screen");
			extent.extentLoggerFail("watchlist", "User is not navigated to previous screen");
		}
		findElements(AMDWatchlistPage.objForwardIcon).get(0).click();
		verifyElementPresentAndClick(AMDWatchlistPage.objEditBtn, "Edit button");
		if (verifyIsElementDisplayed(AMDWatchlistPage.objSelectAllIcon)) {
			logger.info("Content of the Screen is editable");
			extent.extentLogger("watchlist", "Content of the Screen is editable");
		} else {
			logger.error("Content of the Screen is not editable");
			extent.extentLoggerFail("watchlist", "Content of the Screen is not editable");
		}
		for (int i = 1; i <= findElements(AMDWatchlistPage.objSelectCheckBox).size(); i++) {
			if (findElement(AMDWatchlistPage.objSelectContentByIndex(i)).isDisplayed()) {
				logger.info("Check box is displayed for the " + i + "st content");
				extent.extentLogger("watchlist", "Check box is displayed for the " + i + "st content");
			} else {
				logger.error("Check box is not displayed for the " + i + "st content");
				extent.extentLoggerFail("watchlist", "Check box is not displayed for the " + i + "st content");
			}
		}
		click(AMDWatchlistPage.objCloseIcon, "close icon");
		if (verifyIsElementDisplayed(AMDWatchlistPage.objDurationtxt)) {
			String contentDuration = findElement(AMDWatchlistPage.objDurationtxt).getText();
			String contentTitle = findElement(AMDWatchlistPage.objTitleTxt).getText();
			logger.info("Content Duration : " + contentDuration + "\n Content title : " + contentTitle);
			extent.extentLogger("watchlist",
					"Content Duration : " + contentDuration + "\n Content title : " + contentTitle);
		} else {
			logger.error("Content Duration and Content title is not displayed");
			extent.extentLoggerFail("watchlist", "Content Duration and Content title is not displayed");
		}
		verifyElementPresentAndClick(AMDWatchlistPage.objTitleTxt, "First content of Shows screen");
		waitTime(3000);
		if (verifyIsElementDisplayed(AMDWatchlistPage.objPlayerScreen)) {
			logger.info("Navigated to Consumption screen");
			extent.extentLogger("watchlist", "Navigated to Consumption screen");
		} else {
			logger.error("unable to navigated to Consumption screen");
			extent.extentLoggerFail("watchlist", "unable to navigated to Consumption screen");
		}
		Back(3);
	}

	/**
	 * Author : Hitesh Module : More Screen Screen : My Reminders
	 */

	public void myReminders(String userType) throws Exception {
		switch (userType) {
		case "Guest":
			HeaderChildNode("Validate My Reminder");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objMyRemainders, "My Remainders");
			if (verifyIsElementDisplayed(AMDLoginScreen.objLoginOrRegisterPageTitle)) {
				logger.info("User navigated to login or register screen post taping on My Remainders");
				extent.extentLogger("My Remainders",
						"User navigated to login or register screen post taping on My Remainders");
			} else {
				logger.error("User is not navigated to login or register screen post taping on My Remainders");
				extent.extentLoggerFail("My Remainders",
						"User is not navigated to login or register screen post taping on My Remainders");
			}
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back button");
			if (verifyIsElementDisplayed(AMDMoreMenu.objMyRemainders)) {
				logger.info("User is navigate back to more menu");
				extent.extentLogger("watchlist", "User is navigate back to more menu");
			} else {
				logger.error("User is not navigate back to more menu");
				extent.extentLoggerFail("watchlist", "User is not navigate back to more menu");
			}
			break;
		case "NonSubscribedUser":
			validateMyReminder();
			break;
		case "SubscribedUser":
			validateMyReminder();
			break;
		}
	}

	public void validateMyReminder() throws Exception {
		HeaderChildNode("Validate My Reminder screen");
		waitTime(3000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		click(AMDMoreMenu.objMyRemainders, "My Remainders");
		if (verifyElementDisplayed(AMDMyReminderPage.objReminberHeaderTitle)) {
			logger.info("User navigated to  My Reminder  screen post taping on My Remainders");
			extent.extentLogger("My Remainders", "User navigated to  My Reminder  screen post taping on My Remainders");
		} else {
			logger.error("User is not navigated to  My Reminder  screen post taping on My Remainders");
			extent.extentLoggerFail("My Remainders",
					"User is not navigated to My Reminder screen post taping on My Remainders");
		}

		if (!verifyIsElementDisplayed(AMDMyReminderPage.objNoReminderIcon)) {
			verifyElementPresent(AMDMyReminderPage.objEditBtn, "Edit button");
			for (int i = 1; i < findElements(AMDMyReminderPage.objTitleOfContentTxt).size(); i++) {
				if (findElements(AMDMyReminderPage.objTitleOfContentTxt).get(i).isDisplayed()) {
					logger.info("Title of the content in reminder screen is displayed");
					extent.extentLogger("My Remainders", "Title of the content in reminder screen is displayed");
				} else {
					logger.error("Title of the content in reminder screen is not displayed");
					extent.extentLoggerFail("My Remainders",
							"Title of the content in reminder screen is not displayed");
				}
				if (findElements(AMDMyReminderPage.objEpisodeDatetxt).get(i).isDisplayed()) {
					logger.info("Date of the content in reminder screen is displayed");
					extent.extentLogger("My Remainders", "Date of the content in reminder screen is displayed");
				} else {
					logger.error("Date of the content in reminder screen is not displayed");
					extent.extentLoggerFail("My Remainders", "Date of the content in reminder screen is not displayed");
				}
			}
			click(AMDMyReminderPage.objEditBtn, "Edit button");
			verifyElementPresent(AMDMyReminderPage.objSelectAllIcon, "Select All");
			click(AMDMyReminderPage.objSelectAllIcon, "Select All");
			if (findElements(AMDMyReminderPage.objCheckBox).size() > 1) {
				findElements(AMDMyReminderPage.objCheckBox).get(0).click();
			}
			verifyElementPresent(AMDMyReminderPage.objDeleteAllIcon, "Delete Icon");
			for (int i = 1; i <= findElements(AMDMyReminderPage.objCheckBox).size(); i++) {
				if (findElements(AMDMyReminderPage.objCheckBox).get(0).getAttribute("checked").equals("true")) {
					logger.info("Check box is checked");
					extent.extentLogger("My Remainders", "Check box is checked");
				} else {
					logger.info("Check box is not checked");
					extent.extentLogger("My Remainders", "Check box is not checked");
				}
				findElements(AMDMyReminderPage.objCheckBox).get(0).click();
			}
			click(AMDMyReminderPage.objCloseIcon, "Close icon");
			if (verifyElementDisplayed(AMDMyReminderPage.objEditBtn)) {
				logger.info("User navigated to edit screen");
				extent.extentLogger("My Remainders", "User navigated to edit screen");
			} else {
				logger.error("User not navigated to edit screen ");
				extent.extentLoggerFail("My Remainders", "User not navigated to edit screen ");
			}
		} else {
			logger.info("Reminder is empty");
			extent.extentLoggerWarning("My Remainders", "Reminder is empty");
			verifyElementPresent(AMDMyReminderPage.objNoReminderIcon, "No Reminder icon");
			verifyElementPresent(AMDMyReminderPage.objNoReminderTxt, "No Reminder text");
		}
		click(AMDGenericObjects.objBackBtn, "Back button");
		// Back(3);
	}

	/**
	 * Author : Hitesh Module : More Screen Screen : Have a prepaid code
	 * 
	 * @throws Exception
	 */
	public void Haveaprepaidcode(String userType) throws Exception {
		HeaderChildNode("Validate Have a prepaid code");
		verifyElementPresentAndClick(AMDMoreMenu.objHaveaPrepaidCode, "Have a prepaid code");
		verifyElementPresent(AMDMoreMenu.objPrepaidCodePopUp, "Have a prepaid code pop up");
		Back(1);
	}

	/**
	 * Author : Hitesh Module : More Screen Screen : Have a prepaid code
	 * 
	 * @throws Exception
	 */
	public void Settings(String userType) throws Exception {
		HeaderChildNode("Validate Settings");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings");
		verifyElementPresent(AMDSettingsScreen.objSettingsScreenTitle, "Settings screen");
		Back(1);
	}

	/**
	 * Author : Bhavana Module : More screen
	 */
	public void HelpCenterScreen(String userType) throws Exception {
		extent.HeaderChildNode("Validation of Help Center Screen as " + userType);
		System.out.println("Validation of Help Center Screen as " + userType);
		click(AMDHomePage.objMoreMenu, "More menu");
		Swipe("UP", 2);
		click(AMDMoreMenu.objHelpCentre, "Help Center option in More menu");
		waitTime(5000);
		verifyElementExist(AMDMoreMenu.objHelpCenterHeader, "Help Center Header");
		if (checkElementExist(AMDMoreMenu.objHelpCenterHeader, "Help Center Header")) {
			logger.info("User is navigated to Help Center screen");
			extent.extentLoggerPass("Help Center", "User is navigated to Help Center screen");
		} else {
			logger.error("User is unable to navigate to Help Center screen");
			extent.extentLoggerFail("Help Center", "User is unable to navigate to Help Center screen");
		}
		verifyElementPresentAndClick(AMDMoreMenu.objcloseButton, "Close button in Help Center Screen");
		if (checkElementExist(AMDHomePage.objMoreMenu, "More menu")) {
			logger.info("User is navigated to the Previous screen on clicking the Close button in Help Center screen");
			extent.extentLoggerPass("Help Center",
					"User is navigated to the Previous screen on clicking the Close button in Help Center screen");
		} else {
			logger.error(
					"User is unable to navigate to the Previous screen on clicking the Close button in Help Center screen");
			extent.extentLoggerFail("Help Center",
					"User is unable to navigate to the Previous screen on clicking the Close button in Help Center screen");
		}
		click(AMDMoreMenu.objHelpCentre, "Help Center option");
		waitTime(5000);
		verifyElementPresent(AMDMoreMenu.objSearchBarInHelpCenter, "Search bar to enter help queries");
		GettingStartedValidation();
		PartialSwipe("UP", 1);
		MyAccountValidation();
		WatchingZEE5Validation();
		PartialSwipe("UP", 1);
		MyZEE5AppValidation();
		QuickLinksValidation();
		click(AMDMoreMenu.objcloseButton, "Close button");
//		click(AMDHomePage.HomeIcon, "Home icon");
	}

	public void GettingStartedValidation() throws Exception {

		verifyElementExist(AMDMoreMenu.objGettingStartedHeader, "'Getting Started' Header");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("What is ZEE5"), "'What is ZEE5?'");
		waitTime(5000);
		if (checkElementExist(AMDMoreMenu.objArticleTitle("What is ZEE5"), "Article title 'What is ZEE5?'")) {
			logger.info("User is navigated to 'What is ZEE5?' page");
			extent.extentLoggerPass("Article", "User is navigated to 'What is ZEE5?' page");
		} else {
			extent.extentLoggerFail("Verify navigation", "Unable to navigate to page What is ZEE5?");
			logger.info("Unable to navigate to page What is ZEE5?");
		}
		Back(1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Registering with ZEE5"), "'Registering with ZEE5'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("Registering with ZEE5"))) {
			logger.info("User is navigated to 'Registering with ZEE5' page");
			extent.extentLoggerPass("Article", "User is navigated to 'Registering with ZEE5' page");
		} else {
			logger.info("User is not navigated to 'Registering with ZEE5' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'Registering with ZEE5' page");
		}
		Back(1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Purchasing a subscription"),
				"'Purchasing a subscription'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("Purchasing a subscription"))) {
			logger.info("User is navigated to 'Purchasing a subscription' page");
			extent.extentLoggerPass("Article", "User is navigated to 'Purchasing a subscription' page");
		} else {
			logger.info("User is not navigated to 'Purchasing a subscription' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'Purchasing a subscription' page");
		}
		Back(1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("How do I watch ZEE5"),
				"'How do I watch ZEE5 on my television'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("How do I watch ZEE5 on my television"))) {
			logger.info("User is navigated to 'How do I watch ZEE5 on my television?' page");
			extent.extentLoggerPass("Article", "User is navigated to 'How do I watch ZEE5 on my television?' page");
		} else {
			logger.info("User is not navigated to 'How do I watch ZEE5 on my television?' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'How do I watch ZEE5 on my television?' page");
		}
		Back(1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Renting movies on ZEEPLEX"),
				"'Renting movies on ZEEPLEX'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("Renting movies on ZEEPLEX"))) {
			logger.info("User is navigated to 'Renting movies on ZEEPLEX' page");
			extent.extentLoggerPass("Article", "User is navigated to 'Renting movies on ZEEPLEX' page");
		} else {
			logger.info("User is not navigated to 'Renting movies on ZEEPLEX' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'Renting movies on ZEEPLEX' page");
		}
		Back(1);
		waitTime(3000);
	}

	public void MyAccountValidation() throws Exception {

		SwipeUntilFindElement(AMDMoreMenu.objMyAccountHeader, "UP");

		verifyElementPresent(AMDMoreMenu.objMyAccountHeader, "'My Account' Header");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Managing your Subscription"),
				"'Managing your Subscription'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("Managing your Subscription"))) {
			logger.info("User is navigated to 'Managing your Subscription' page");
			extent.extentLoggerPass("Article", "User is navigated to 'Managing your Subscription' page");
		} else {
			logger.info("User is not navigated to 'Managing your Subscription' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'Managing your Subscription' page");
		}
		Back(1);
		waitTime(3000);

		PartialSwipe("Up", 1);
		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("I can"), "UP");

		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("I can"), "I can't sign in to ZEE5");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("sign in to ZEE5"))) {
			logger.info("User is navigated to 'I can't sign in to ZEE5' page");
			extent.extentLoggerPass("Article", "User is navigated to 'I can't sign in to ZEE5' page");
		} else {
			logger.info("User is not  navigated to 'I can't sign in to ZEE5' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'I can't sign in to ZEE5' page");
		}
		Back(1);
		waitTime(3000);
//		Swipe("Up", 1);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("I made a payment but"), "UP");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("I made a payment but"),
				"'I made a payment but my subscription isn't active / My subscription is missing'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("I made a payment but my subscription"))) {
			logger.info(
					"User is navigated to 'I made a payment but my subscription isn't active / My subscription is missing' page");
			extent.extentLoggerPass("Article",
					"User is navigated to 'I made a payment but my subscription isn't active / My subscription is missing' page");
		} else {
			logger.info(
					"User is not navigated to 'I made a payment but my subscription isn't active / My subscription is missing' page");
			extent.extentLoggerFail("Article",
					"User is not navigated to 'I made a payment but my subscription isn't active / My subscription is missing' page");
		}
		Back(1);
		waitTime(3000);
//		Swipe("Up", 1);
		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("I want to update my"), "UP");

		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("I want to update my"),
				"I want to update my profile information");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("I want to update my profile information"))) {
			logger.info("User is navigated to 'I want to update my profile information' page");
			extent.extentLoggerPass("Article", "User is navigated to 'I want to update my profile information' page");
		} else {
			logger.info("User is not navigated to 'I want to update my profile information' page");
			extent.extentLoggerFail("Article",
					"User is not navigated to 'I want to update my profile information' page");
		}
		Back(1);
		waitTime(3000);
	}

	public void QuickLinksValidation() throws Exception {
		Swipe("Up", 4);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("Quick Links"), "UP");

		verifyElementPresent(AMDMoreMenu.objQueriesHeader("Quick Links"), " 'Quick Links' tab");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("How Can I"), "'How Can I'");
		waitTime(3000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("How Can I"))) {
			logger.info("User is navigated to 'How Can I?' page");
			extent.extentLoggerPass("Article", "User is navigated to 'How Can I?' page");
		} else {
			logger.info("User is not navigated to 'How Can I?' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'How Can I?' page");
		}
		Back(1);
		waitTime(2000);
		Swipe("Up", 4);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("I am unable to watch the ZEEPLEX movie"), "UP");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("I am unable to watch the ZEEPLEX movie"),
				"'I am unable to watch the ZEEPLEX movie'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("I am unable to watch the ZEEPLEX movie"))) {
			logger.info("User is navigated to 'I am unable to watch the ZEEPLEX movie' page");
			extent.extentLoggerPass("Article", "User is navigated to 'I am unable to watch the ZEEPLEX movie' page");
		} else {
			logger.info("User is not navigated to 'I am unable to watch the ZEEPLEX movie' page");
			extent.extentLoggerFail("Article",
					"User is not navigated to 'I am unable to watch the ZEEPLEX movie' page");
		}
		Back(1);
		waitTime(2000);
		Swipe("Up", 4);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("ZEE5 Offers"), "UP");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("ZEE5 Offers"), "'Zee5 offers'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("ZEE5 Offers"))) {
			logger.info("User is navigated to 'ZEE5 Offers' page");
			extent.extentLoggerPass("Article", "User is navigated to 'ZEE5 Offers' page");
		} else {
			logger.info("User is not navigated to 'ZEE5 Offers' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'ZEE5 Offers' page");
		}
		Back(1);
		waitTime(2000);
		Swipe("Up", 4);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("ZEE5 Partnerships"), "UP");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("ZEE5 Partnerships"), "'ZEE5 Partnerships'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("ZEE5 Partnerships"))) {
			logger.info("User is navigated to 'ZEE5 Partnerships' page");
			extent.extentLoggerPass("Article", "User is navigated to 'ZEE5 Partnerships' page");
		} else {
			logger.info("User is not navigated to 'ZEE5 Partnerships' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'ZEE5 Partnerships' page");
		}
		Back(1);
		waitTime(2000);
		Swipe("Up", 4);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("Cancel Subscription"), "UP");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Cancel Subscription"), "'Cancel Subscription'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("How do I cancel my ZEE5 Subscription"))) {
			logger.info("User is navigated to 'How do I cancel my ZEE5 Subscription?' page");
			extent.extentLoggerPass("Article", "User is navigated to 'How do I cancel my ZEE5 Subscription?' page");
		} else {
			logger.info("User is not navigated to 'How do I cancel my ZEE5 Subscription?' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'How do I cancel my ZEE5 Subscription?' page");
		}
		Back(1);
		waitTime(2000);
	}

	public void WatchingZEE5Validation() throws Exception {
		Swipe("Up", 1);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("Watching ZEE5"), "UP");

		verifyElementPresent(AMDMoreMenu.objQueriesHeader("Watching ZEE5"), " 'Watching ZEE5' tab");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("I need help with playing a video"),
				"'I need help with playing a video'");
		waitTime(3000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("I need help with playing a video"))) {
			logger.info("User is navigated to 'I need help with playing a video' page");
			extent.extentLoggerPass("Article", "User is navigated to 'I need help with playing a video' page");
		} else {
			logger.info("User is not navigated to 'I need help with playing a video' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'I need help with playing a video' page");
		}
		Back(1);
		waitTime(2000);
		// Swipe("Up", 3);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("I need help with audio"), "UP");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("I need help with audio"),
				"'I need help with audio'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("I need help with audio"))) {
			logger.info("User is navigated to 'I need help with audio' page");
			extent.extentLoggerPass("Article", "User is navigated to 'I need help with audio' page");
		} else {
			logger.info("User is not navigated to 'I need help with audio' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'I need help with audio' page");
		}
		Back(1);
		waitTime(2000);
		Swipe("Up", 2);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("I need help with casting ZEE5 on my TV"), "UP");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("I need help with casting ZEE5 on my TV"),
				"'I need help with casting ZEE5 on my TV'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("I need help with casting ZEE5 on my TV"))) {
			logger.info("User is navigated to 'I need help with casting ZEE5 on my TV' page");
			extent.extentLoggerPass("Article", "User is navigated to 'I need help with casting ZEE5 on my TV' page");
		} else {
			logger.info("User is not navigated to 'I need help with casting ZEE5 on my TV' page");
			extent.extentLoggerFail("Article",
					"User is not navigated to 'I need help with casting ZEE5 on my TV' page");
		}
		Back(1);
		waitTime(2000);
		Swipe("Up", 2);
		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("All about downloads"), "UP");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("All about downloads"), "'All about downloads'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("All about downloads"))) {
			logger.info("User is navigated to 'All about downloads' page");
			extent.extentLoggerPass("Article", "User is navigated to 'All about downloads' page");
		} else {
			logger.info("User is not navigated to 'All about downloads' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'All about downloads' page");
		}
		Back(1);
		waitTime(2000);
		Swipe("Up", 2);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("All about subtitles"), "UP");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("All about subtitles"), "'All about subtitles'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("All about subtitles"))) {
			logger.info("User is navigated to 'All about subtitles' page");
			extent.extentLoggerPass("Article", "User is navigated to 'All about subtitles' page");
		} else {
			logger.info("User is not navigated to 'All about subtitles' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'All about subtitles' page");
		}
		Back(1);
		waitTime(2000);
	}

	public void MyZEE5AppValidation() throws Exception {
		Swipe("Up", 3);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("My ZEE5 App"), "UP");

		verifyElementPresent(AMDMoreMenu.objQueriesHeader("My ZEE5 App"), " 'My ZEE5 App' tab");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("App Performance"), "'App Performance'");
		waitTime(3000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("App Performance"))) {
			logger.info("User is navigated to 'App Performance' page");
			extent.extentLoggerPass("Article", "User is navigated to 'How Can I?' page");
		} else {
			logger.info("User is not navigated to 'App Performance' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'App Performance' page");
		}
		Back(1);
		waitTime(2000);
		Swipe("Up", 3);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("I am unable to authenticate my TV"), "UP");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("I am unable to authenticate my TV"),
				"'I am unable to authenticate my TV'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("authenticate my TV"))) {
			logger.info("User is navigated to 'I am unable to authenticate my TV' page");
			extent.extentLoggerPass("Article", "User is navigated to 'I am unable to authenticate my TV' page");
		} else {
			logger.info("User is not navigated to 'I am unable to authenticate my TV' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'I am unable to authenticate my TV' page");
		}
		Back(1);
		waitTime(2000);
		Swipe("Up", 3);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("How can I enable automatic updates"), "UP");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("How can I enable automatic updates"),
				"'How can I enable automatic updates for the ZEE5 app?'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("How can I enable automatic updates"))) {
			logger.info("User is navigated to 'How can I enable automatic updates for the ZEE5 app?' page");
			extent.extentLoggerPass("Article",
					"User is navigated to 'How can I enable automatic updates for the ZEE5 app?' page");
		} else {
			logger.info("User is not navigated to 'How can I enable automatic updates for the ZEE5 app?' page");
			extent.extentLoggerFail("Article",
					"User is not navigated to 'How can I enable automatic updates for the ZEE5 app?' page");
		}
		Back(1);
		waitTime(2000);
		Swipe("Up", 3);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("My ZEE5 App Version"), "UP");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("My ZEE5 App Version"), "'My ZEE5 App Version'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("How do I see my current ZEE5 app version?"))) {
			logger.info("User is navigated to 'My ZEE5 App Version' page");
			extent.extentLoggerPass("Article", "User is navigated to 'My ZEE5 App Version' page");
		} else {
			logger.info("User is not navigated to 'My ZEE5 App Version' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'My ZEE5 App Version' page");
		}
		Back(1);
		waitTime(2000);

		Swipe("Up", 3);

		SwipeUntilFindElement(AMDMoreMenu.objQueriesHeader("Parental Control"), "UP");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Parental Control"), "'Parental Control'");
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objArticleTitle("Parental Control"))) {
			logger.info("User is navigated to 'Parental Control' page");
			extent.extentLoggerPass("Article", "User is navigated to 'Parental Control' page");
		} else {
			logger.info("User is not navigated to 'Parental Control' page");
			extent.extentLoggerFail("Article", "User is not navigated to 'Parental Control' page");
		}
		Back(1);
		waitTime(2000);
	}

	/**
	 * Author : Kushal Module : listing screen
	 */

	public void ListingCollectionVerification(String pUserType) throws Exception {
		extent.HeaderChildNode("Listing Collection validation as: " + pUserType);
		System.out.println("\nListing Collection validation as: " + pUserType);

		String getTrayName = null, getPageTitle;

		click(AMDHomePage.objHome, "Home button");
		waitForElementDisplayed(AMDHomePage.objCarouselTitle, 2000);

		waitTime(2000);
		if (verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTray)
				| verifyIsElementDisplayed(AMDHomePage.objBannerAd)) {
			Swipe("UP", 1);
			waitTime(2000);
		}
		// Swipe Tray content cards
		SwipeRailContentCards(AMDGenericObjects.objFirstTrayTitle);

		if (pUserType.equalsIgnoreCase("Guest")) {
			getTrayName = getText(AMDGenericObjects.objFirstTrayTitle);
			click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
		} else {
			if (verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTray)
					| verifyIsElementDisplayed(AMDHomePage.objBannerAd)) {
				Swipe("UP", 1);
				waitTime(2000);
			}
			PartialSwipe("UP", 1);
			waitTime(2000);
			int noOfTrays = getCount(AMDGenericObjects.objNoOfTrays);
			if (noOfTrays > 0) {
				getTrayName = getText(AMDGenericObjects.objTrayTitleByIndx(noOfTrays));
				click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
			}
		}

		getPageTitle = getText(AMDHomePage.objTitle);
		if (getTrayName.contains(getPageTitle)) {
			extent.extentLoggerPass("Listing Collection", "Collection screen is dislayed for: " + getPageTitle);
			logger.info("Collection screen is dislayed for: " + getPageTitle);
		} else {
			extent.extentLoggerFail("Listing Collection", "Collection screen is not dislayed");
			logger.error("Collection screen is not dislayed");
		}

		if (verifyElementDisplayed(AMDHomePage.objBackIcon)) {
			logger.info("Back button is displayed in listing Collection screen");
			extent.extentLoggerPass("Listing Collection Screen",
					"Back button is displayed in listing Collection screen");
		} else {
			logger.error("Back button is not displayed in the listing Collection screen");
			extent.extentLoggerFail("Listing Collection Screen",
					"Back button is not displayed in the listing Collection screen");
		}

		if (verifyElementDisplayed(AMDGenericObjects.objPremiumTags)) {
			logger.info("Premium tags are displayed on top-left of content cards in listing Collection screen");
			extent.extentLoggerPass("Premium Tags",
					"Premium tags are displayed on top-left of content cards in listing Collection screen");
		} else {
			logger.info("Premium tags are displayed on top-left of content cards in listing Collection screen");
			extent.extentLoggerFail("Premium Tags",
					"Premium tags are not displayed on top-left of content cards in listing Collection screen");
		}

		// To click on any content card displayed under collection screen
		findElements(AMDGenericObjects.objPremiumTags).get(0).click();
		waitTime(2000);
		if (verifyIsElementDisplayed(AMDGenericObjects.objPopUpDivider)) {
			click(AMDGenericObjects.objPopUpDivider, "Subcription Pop Up");
			extent.extentLoggerPass("Subscription PopUp",
					"Subcription PopUp is displayed in the comsumption screen and popup is closed");
			logger.info("Subcription PopUp is displayed in the comsumption screen and popup is closed");
		}

		if (verifyElementDisplayed(AMDConsumptionScreen.objWatchlistBtn)) {
			extent.extentLoggerPass("Consumption Screen",
					"Consumption screen appeared on selecting content card from listing collection");
			logger.info("Consumption screen appeared on selecting content card from listing collection");
		} else {
			logger.error("Consumption screen failed to appear on selecting content card from listing collection");
			extent.extentLoggerFail("Consumption Screen",
					"Consumption screen failed to appear on selecting content card from listing collection");
		}
		Back(1);
		getPageTitle = getText(AMDHomePage.objTitle);
		if (getTrayName.contains(getPageTitle)) {
			extent.extentLoggerPass("Listing Collection",
					"Collection listing screen is dislayed on navigating back from Consumption screen");
			logger.info("Collection listing screen is dislayed on navigating back from Consumption screen");
		} else {
			extent.extentLoggerFail("Listing Collection",
					"Collection listing screen failed to display on navigating back from Consumption screen");
			logger.error("Collection listing screen failed to display on navigating back from Consumption screen");
		}
		Back(1);
	}

	/**
	 * Author : Yashaswini Module : More screen Screen : Invite a friend
	 */
	public void Invite_a_Friend(String userType) throws Exception {
		extent.HeaderChildNode("Validation of Invite a Friend Screen as " + userType);

		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objInviteAFriend, "Invite a Friend in More menu");
		waitTime(2000);

		if (checkElementExist(AMDMoreMenu.objshareOptions, "Share option panel")) {
			logger.info("User is navigated share options screen");
			extent.extentLogger("Share through options screen", "User is navigated to share options screen");
		} else {
			logger.info("User unable to navigate share options screen");
			extent.extentLogger("Share through options screen", "User is not navigated to share options screen");
		}

		int shareOptions = getDriver().findElements(AMDMoreMenu.objShareOptions).size();
		if (shareOptions == 0) {
			extent.extentLoggerFail("Verify share options", "Share Options are not available");
			logger.info("Share Options are not available");
		} else {
			for (int i = 1; i <= shareOptions; i++) {
				String shareOptionName = getText(AMDMoreMenu.objShareOptions(i));
				logger.info("Share Option : " + shareOptionName + " is available to share");
				extent.extentLogger("Social site displayed",
						"Share Option : " + shareOptionName + " is available to share");

			}
		}

		Back(1);
	}

	/**
	 * Author : Bindu Module : More Screen Screen : My Transactions
	 */
	public void verifyMyTransactions(String userType) throws Exception {

		extent.HeaderChildNode("Verify My transactions Screen");
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			ZNALogoutMethod();
			click(AMDHomePage.objMoreMenu, "More Menu");
			click(AMDMoreMenu.objProfile, "Profile");
			waitTime(2000);

			String NonsubscribedUserWithInactivePackUserName = Reporter.getCurrentTestResult().getTestContext()
					.getCurrentXmlTest().getParameter("NonsubscribedUserWithInActivePackUsername");
			String NonsubscribedUserWithInActivePackPassword = Reporter.getCurrentTestResult().getTestContext()
					.getCurrentXmlTest().getParameter("NonsubscribedUserWithInActivePackPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

			LoginWithEmailID(NonsubscribedUserWithInactivePackUserName, NonsubscribedUserWithInActivePackPassword);
		}

		waitTime(2000);
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
		waitTime(2000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objMyTransactions)) {
			logger.info("My Transactions option is availabel on More button");
			extent.extentLogger("MoreMenu Screen", "My Transactions option is availabel on More button");

		} else {
			logger.info("My Transactions option is not availabel on More button");
			extent.extentLoggerFail("MoreMenu Screen", "My Transactions option is not availabel on More button");
		}
		click(AMDMoreMenu.objMyTransactions, "My TransactionsOption");
		waitTime(2000);

		if (verifyIsElementDisplayed(AMDMoreMenu.objMyTransactionsHeader)) {

			logger.info("User navigated to My Transactions Page on tapping My Transactions");
			extent.extentLogger("MyTransactions Screen",
					"User navigated to My Transactions Page on tapping My Transactions");
		} else {
			logger.info("User not navigated to My Transactions Page on tapping My Transactions");
			extent.extentLoggerFail("MyTransactions Screen",
					"User not navigated to My Transactions Page on tapping My Transactions");
		}
		MyTransactionsWithDetails();

		extent.HeaderChildNode("Verify Date of Transaction for every transaction done by the user");
		String TransactionDate = getDriver().findElement(AMDMoreMenu.objTransactionDate1).getText();
		logger.info(TransactionDate);
		if (checkElementExist(AMDMoreMenu.objTransactionDate1, "Transaction date")) {
			logger.info(
					TransactionDate + " : Date of Transaction is shown for the transactions done by the " + userType);
			extent.extentLogger("MyTransactions Screen",
					TransactionDate + " :Date of Transaction is shown for the transactions done by the " + userType);
		} else {

			logger.info(TransactionDate + " : Date of Transaction is not shown for the transactions done by the "
					+ userType);
			extent.extentLoggerFail("MyTransactions Screen", TransactionDate
					+ " :Date of Transaction is not shown for the transactions done by the " + userType);
		}

		extent.HeaderChildNode("verify My Transactions pack details");
		String TransactionPackName = getDriver().findElement(AMDMoreMenu.objTransactionPackName1).getText();
		logger.info(TransactionPackName);
		if (checkElementExist(AMDMoreMenu.objTransactionPackName1, "Transaction Pack name")) {
			logger.info(TransactionPackName + " : Transaction pack name is displayed for the " + userType);
			extent.extentLogger("MyTransactions Screen",
					TransactionPackName + " : Transaction pack name is displayed for the " + userType);
		} else {

			logger.info(TransactionPackName + " : Transaction pack name is not displayed for the " + userType);
			extent.extentLoggerFail("MyTransactions Screen",
					TransactionPackName + " : Transaction pack name is not displayed for the " + userType);
		}

		String TransactionPackduration = getDriver().findElement(AMDMoreMenu.objTransactionPackDuration1).getText();
		logger.info(TransactionPackduration);
		if (checkElementExist(AMDMoreMenu.objTransactionPackDuration1, "Transaction Pack duration")) {
			logger.info(TransactionPackduration + " : Transaction pack duration is displayed for the " + userType);
			extent.extentLogger("MyTransactions Screen",
					TransactionPackduration + " : Transaction pack duration is displayed for the " + userType);
		} else {

			logger.info(TransactionPackduration + " : Transaction pack duration is not displayed for the " + userType);
			extent.extentLoggerFail("MyTransactions Screen",
					TransactionPackduration + " : Transaction pack duration is not displayed for the " + userType);
		}

		String TransactionPackRental = getDriver().findElement(AMDMoreMenu.objTransactionPackRental1).getText();
		logger.info(TransactionPackRental);
		if (checkElementExist(AMDMoreMenu.objTransactionPackRental1, "Transaction PackRental")) {
			logger.info(TransactionPackRental + " : Transaction pack rental is displayed for the " + userType);
			extent.extentLogger("MyTransactions Screen",
					TransactionPackRental + " : Transaction pack rental is displayed for the " + userType);
		} else {

			logger.info(TransactionPackRental + " : Transaction pack rental is not displayed for the " + userType);
			extent.extentLoggerFail("MyTransactions Screen",
					TransactionPackRental + " : Transaction pack rental is not displayed for the " + userType);
		}

		extent.HeaderChildNode("Verify Payment mode mentioned for the Transaction done by the user");
		String TransactionPackPaymentMode = getDriver().findElement(AMDMoreMenu.objTransactionPackPaymentMode1)
				.getText();
		logger.info(TransactionPackPaymentMode);
		if (checkElementExist(AMDMoreMenu.objTransactionPackPaymentMode1, "Transaction Pack PaymentMode")) {
			logger.info(
					TransactionPackPaymentMode + " : Transaction pack payment mode is displayed for the " + userType);
			extent.extentLogger("MyTransactions Screen",
					TransactionPackPaymentMode + " : Transaction pack payment mode is displayed for the " + userType);
		} else {

			logger.info(TransactionPackPaymentMode + " : Transaction pack payment mode is not displayed for the "
					+ userType);
			extent.extentLoggerFail("MyTransactions Screen", TransactionPackPaymentMode
					+ " : Transaction pack payment mode is not displayed for the " + userType);
		}

		String TransactionPackCountryDetails = getDriver().findElement(AMDMoreMenu.objTransactionPackCountry1)
				.getText();
		logger.info(TransactionPackCountryDetails);
		if (checkElementExist(AMDMoreMenu.objTransactionPackCountry1, "Transaction Pack Country Details")) {
			logger.info(TransactionPackCountryDetails + " : Transaction pack Country details is displayed for the "
					+ userType);
			extent.extentLogger("MyTransactions Screen", TransactionPackCountryDetails
					+ " : Transaction pack Country details is displayed for the " + userType);
		} else {

			logger.info(TransactionPackCountryDetails + " : Transaction pack Country details is not displayed for the "
					+ userType);
			extent.extentLoggerFail("MyTransactions Screen", TransactionPackCountryDetails
					+ " : Transaction pack Country details is not displayed for the " + userType);
		}

		String TransactionPackAutoRenewal = getDriver().findElement(AMDMoreMenu.objTransactionPackAutoRenewal1)
				.getText();
		logger.info(TransactionPackAutoRenewal);
		if (checkElementExist(AMDMoreMenu.objTransactionPackAutoRenewal1, "Transaction Pack Auto-renewal status")) {
			logger.info(TransactionPackAutoRenewal + " : Transaction pack Auto-renewal details is displayed for the "
					+ userType);
			extent.extentLogger("MyTransactions Screen", TransactionPackAutoRenewal
					+ " : Transaction pack Auto-renewal details is displayed for the " + userType);
		} else {

			logger.info(TransactionPackAutoRenewal
					+ " : Transaction pack Auto-renewal details is not displayed for the " + userType);
			extent.extentLoggerFail("MyTransactions Screen", TransactionPackAutoRenewal
					+ " : Transaction pack Auto-renewal details is not displayed for the " + userType);
		}

		extent.HeaderChildNode("Verify status of Transaction is Shown according to the duration of the pack");
		String TransactionPackStatus = getDriver().findElement(AMDMoreMenu.objTransactionPackStatus1).getText();
		logger.info(TransactionPackStatus);
		if (checkElementExist(AMDMoreMenu.objTransactionPackStatus1, "Transaction Pack Status")) {
			logger.info(TransactionPackStatus + " : Transaction pack Status details is displayed for the " + userType);
			extent.extentLogger("MyTransactions Screen",
					TransactionPackStatus + " : Transaction pack Status details is displayed for the " + userType);
		} else {

			logger.info(
					TransactionPackStatus + " : Transaction pack Status details is not displayed for the " + userType);
			extent.extentLoggerFail("MyTransactions Screen",
					TransactionPackStatus + " : Transaction pack Status details is not displayed for the " + userType);
		}

		extent.HeaderChildNode("Verify download Invoice CTA below My transactions");
		if (checkElementExist(AMDMoreMenu.objDownloadInvoice1, "Download invoice")) {
			logger.info("Download invoice CTA is displayed under My Transactions Screen");
			extent.extentLogger("MyTransactions Screen",
					"Download invoice CTA is displayed under My Transactions Screen");
		} else {
			logger.info("Download invoice CTA is not displayed under My Transactions Screen");
			extent.extentLoggerFail("MyTransactions Screen",
					"Download invoice CTA is not displayed under My Transactions Screen");
		}
		click(AMDMoreMenu.objDownloadInvoice1, "Download invoice");
		Back(3);
		verifyElementPresentAndClick(AMDSearchScreen.objHomeOption, "Bottom bar Home Option");

		ZNALogoutMethod();
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			ValidateMyTransactionScreenWithSubscribeNowCTA();
			ZNALogoutMethod();
		}
		ValidateDiscountPack(userType);
	}

	public void MyTransactionsWithDetails() throws Exception {
		extent.HeaderChildNode("Verify My Transactions with details");
		for (int i = 0; i < findElements(AMDMoreMenu.objTransactionDate).size(); i++) {
			if (findElements(AMDMoreMenu.objTransactionDate).get(i).isDisplayed()) {
				logger.info("Date of transaction in My Transaction page is displayed");
				extent.extentLoggerPass("My Transactions", "Date of transaction in My Transaction page is displayed");
			} else {
				logger.error("Date of transaction in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions", "Date of transaction in My Transaction page is displayed");
			}
		}
		for (int j = 0; j < findElements(AMDMoreMenu.objTransactionPackName).size(); j++) {
			if (findElements(AMDMoreMenu.objTransactionPackName).get(j).isDisplayed()) {
				logger.info("Transaction pack name in My Transaction page is displayed");
				extent.extentLoggerPass("My Transactions", "Transaction pack name in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack name in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions", "Transaction pack name in My Transaction page is displayed");
			}
		}
		for (int k = 0; k < findElements(AMDMoreMenu.objTransactionPackDuration).size(); k++) {
			if (findElements(AMDMoreMenu.objTransactionPackDuration).get(k).isDisplayed()) {
				logger.info("Transaction pack duration in My Transaction page is displayed");
				extent.extentLoggerPass("My Transactions",
						"Transaction pack duration in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack duration in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions",
						"Transaction pack duration in My Transaction page is displayed");
			}
		}
		Swipe("UP", 1);

		for (int l = 0; l < findElements(AMDMoreMenu.objTransactionPackRental).size(); l++) {
			if (findElements(AMDMoreMenu.objTransactionPackRental).get(l).isDisplayed()) {
				logger.info("Transaction pack rental details in My Transaction page is displayed");
				extent.extentLoggerPass("My Transactions",
						"Transaction pack rental details in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack rental details in My Transaction page is displayed");
				extent.extentLoggerFail("My Transaction",
						"Transaction pack rental details in My Transaction page is displayed");
			}
		}
		for (int m = 0; m < findElements(AMDMoreMenu.objTransactionPackPaymentMode).size(); m++) {
			if (findElements(AMDMoreMenu.objTransactionPackPaymentMode).get(m).isDisplayed()) {
				logger.info("Transaction pack payment mode details in My Transaction page is displayed");
				extent.extentLoggerPass("My Transactions",
						"Transaction pack payment mode details in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack payment mode details in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions",
						"Transaction pack payment mode details in My Transaction page is displayed");
			}
		}

		for (int n = 0; n < findElements(AMDMoreMenu.objTransactionPackCountry).size(); n++) {
			if (findElements(AMDMoreMenu.objTransactionPackCountry).get(n).isDisplayed()) {
				logger.info("Transaction pack Country details in My Transaction page is displayed");
				extent.extentLoggerPass("My Transactions",
						"Transaction pack Country details in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack Country details in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions",
						"Transaction pack Country details in My Transaction page is displayed");
			}
		}
		for (int o = 0; o < findElements(AMDMoreMenu.objTransactionPackAutoRenewal).size(); o++) {
			if (findElements(AMDMoreMenu.objTransactionPackAutoRenewal).get(o).isDisplayed()) {
				logger.info("Transaction pack auto renewal details in My Transaction page is displayed");
				extent.extentLoggerPass("My Transactions",
						"Transaction pack auto renewal details in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack auto renewal details in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions",
						"Transaction pack auto renewal details in My Transaction page is displayed");
			}
		}
		for (int p = 0; p < findElements(AMDMoreMenu.objTransactionPackStatus).size(); p++) {
			if (findElements(AMDMoreMenu.objTransactionPackStatus).get(p).isDisplayed()) {
				logger.info("Transaction pack status in My Transaction page is displayed");
				extent.extentLoggerPass("My Transactions",
						"Transaction pack status in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack status in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions",
						"Transaction pack status in My Transaction page is displayed");
			}
		}
		for (int q = 0; q < findElements(AMDMoreMenu.objDownloadInvoice).size(); q++) {
			if (findElements(AMDMoreMenu.objDownloadInvoice).get(q).isDisplayed()) {
				logger.info("Download Invoice CTA My in Transaction page is displayed");
				extent.extentLoggerPass("My Transactions", "Download Invoice CTA in My Transaction page is displayed");
			} else {
				logger.error("Download Invoice CTA in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions", "Download Invoice CTA My in Transaction page is displayed");
			}
		}
		Swipe("DOWN", 1);
	}

	public void ValidateMyTransactionScreenWithSubscribeNowCTA() throws Exception {
		extent.HeaderChildNode("Verrify Subscribe Now CTA in My Transactions Screen");
		click(AMDHomePage.objMoreMenu, "More Menu");
		click(AMDMoreMenu.objProfile, "Profile");
		waitTime(2000);

		String NonsubscribedUserWithNoTransactionUsername = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("NonsubscribedUserWithNoTransactionUsername");
		String NonsubscribedUserWithNoTransactionPassword = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("NonsubscribedUserWithNoTransactionPassword");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

		LoginWithEmailID(NonsubscribedUserWithNoTransactionUsername, NonsubscribedUserWithNoTransactionPassword);
		waitTime(2000);
		click(AMDHomePage.MoreMenuIcon, "More Menu");
		waitTime(2000);
		click(AMDMoreMenu.objMyTransactions, "My TransactionsOption");
		waitTime(2000);

		if (verifyElementExist(AMDMoreMenu.objSubNowCTA, "Subscribe Now CTA")) {
			logger.info(
					"Subscribe Now CTA is displayed under My Transactions Screen for no transaction done by the registered user");
			extent.extentLoggerPass("MyTransactions Screen",
					"Subscribe Now CTA is displayed under My Transactions Screen for no transaction done by the registered user");
		} else {
			logger.error(
					"Subscribe Now CTA is not displayed under My Transactions Screen for no transaction done by the registered user");
			extent.extentLoggerFail("MyTransactions Screen",
					"Subscribe Now CTA is not displayed under My Transactions Screen for no transaction done by the registered user");
		}
		Back(2);
	}

	public void ValidateDiscountPack(String userType) throws Exception {
		extent.HeaderChildNode("Verify Discount rentals shown for the purchased pack");
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			click(AMDHomePage.objMoreMenu, "More Menu");
			click(AMDMoreMenu.objProfile, "Profile");
			waitTime(2000);

			String PackwithDiscountAmountUsername = getParameterFromXML("PackWithDiscountUsername");
			String PackwithDiscountAmountPassword = getParameterFromXML("PackWithDiscountPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

			LoginWithEmailID(PackwithDiscountAmountUsername, PackwithDiscountAmountPassword);

			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
			waitTime(2000);
			verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My TransactionsOption");
			waitTime(2000);

			String TransactionPackRental = getDriver().findElement(AMDMoreMenu.objTransactionPackRental1).getText();
			logger.info(TransactionPackRental);
			if (checkElementExist(AMDMoreMenu.objTransactionPackRental1, "Transaction PackRental")) {
				logger.info(TransactionPackRental + " : Discount Transaction pack rental is displayed for the user ");
				extent.extentLoggerPass("MyTransactions Screen",
						TransactionPackRental + " : Discount Transaction pack rental is displayed for the user ");
			} else {

				logger.error(
						TransactionPackRental + " : Discount Transaction pack rental is not displayed for the user ");
				extent.extentLoggerFail("MyTransactions Screen",
						TransactionPackRental + " : Discount Transaction pack rental is not displayed for the user ");
			}
			Back(1);
		} else {
			logger.info("Discount rental pack details is not applicable for the " + userType);
			extent.extentLoggerPass("MyTransactions Screen",
					"Discount rental pack details is not applicable for the " + userType);
		}

		Swipe("Up", 1);
		relaunch(true);
		accessDeviceLocationPopUp("Allow", userType);
		navigateToIntroScreen_DisplaylangScreen();
		ZeeApplicasterLogin(userType);
	}

	public void ValidateMyTransactionWithInActivePack() throws Exception {
		extent.HeaderChildNode("Validating My Transactions Screen with Active Pack");
		click(AMDHomePage.objMoreMenu, "More Menu");
		click(AMDMoreMenu.objProfile, "Profile");
		waitTime(2000);

		String NonsubscribedUserWithInactivePackUserName = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("NonsubscribedUserWithInActivePackUsername");
		String NonsubscribedUserWithInActivePackPassword = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("NonsubscribedUserWithInActivePackPassword");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

		LoginWithEmailID(NonsubscribedUserWithInactivePackUserName, NonsubscribedUserWithInActivePackPassword);
	}

	public void navigateToHomecreenFromIntroScreen() throws Exception {
		System.out.println("\nNavigation to Home Screen");

		if (pUserType.equals("Guest")) {
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
			waitTime(3000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip link");
			verifyElementPresent(AMDHomePage.objHomeTab, "Home landing screen");
		} else {
			logger.info(pUserType + " is already in the Home screen");
			extent.extentLoggerPass("Home Screen", pUserType + " is already in the Home screen");
		}
	}

	public void SelectTopNavigationTab(String pTabname) throws Exception {
		System.out.println("\nSelecting " + pTabname + " from Top navigation tabs");

		verifyElementPresentAndClick(AMDHomePage.objHome, "Home button");
		int noOfTabs = getCount(AMDHomePage.objTitle);
		System.out.println("\nTop Navigation Tabs: " + noOfTabs);
		for (int k = 1; k <= noOfTabs; k++) {
			if (verifyIsElementDisplayed(AMDGenericObjects.objPageTitle(pTabname))) {
				click(AMDGenericObjects.objPageTitle(pTabname), pTabname);
				break;
			} else {
				List<WebElement> element = getDriver().findElements(By.xpath("//*[@id='title']"));
				element.get(noOfTabs - 1).click();
				waitTime(1000);
			}
		}
	}

	/**
	 * Author : Manasa Module : Settings
	 */

	public void DownloadOverWiFiOnlyONValidation(String userType, String searchKeyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Download over WiFi only ON state Validation");
			System.out.println("\nDownload over WiFi only ON state Validation");

//			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu tab");
//			verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
			waitTime(2000);
			verifyElementPresentAndClick(AMDSettingsScreen.objDownloadOverWifiToggle, "Wifi On toggle");
			Back(1);
			waitTime(2000);
			Back(1);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, searchKeyword1 + "\n", "Search bar");
			waitTime(2000);
			hideKeyboard();
			// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDMoreMenu.objSearchResult(searchKeyword1), "Search result");

			verifyElementPresentAndClick(AMDMoreMenu.objDownloadIcon, "Download icon");
			verifyElementPresentAndClick(AMDMoreMenu.objDataSaver, "Data Saver option");
			verifyElementPresentAndClick(AMDMoreMenu.objStartDownload, "Start Download");

			String wifi = "";
			String cmd = "adb shell dumpsys \"wifi | grep 'Wi-Fi is'\"";
			Process p = Runtime.getRuntime().exec(cmd);
			System.out.println(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			wifi = br.readLine();
			logger.info("Wifi status :: " + wifi.toString());

			if (wifi.equalsIgnoreCase("Wi-Fi is enabled")) {
				logger.info("Content is downloading on Wifi network");
				extent.extentLoggerPass("Download", "Content is downloading on Wifi network");
			} else {
				logger.error("Content is not downloading on Wifi network");
				extent.extentLoggerFail("Download", "Content is not downloading on Wifi network");
			}
			Back(1);
		}
	}

	/**
	 * Author : Sushma Module : Player
	 */
	public void PlayerPotrait(String searchKeyword, String usertype) throws Exception {
		extent.HeaderChildNode("Potrait mode validation");
		System.out.println("\nPotrait mode validation");

		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword + "\n", "Search bar");
		hideKeyboard();
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
			// click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		if (verifyElementExist(AMDPlayerScreen.objPlayerScreen, "Player screen")) {
			waitTime(6000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			waitTime(1000);
			verifyElementPresent(AMDPlayerScreen.objPauseIcon, "Pause icon");
			waitTime(7000);
			if (verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon)) {
				logger.info("Player controls are not auto hide after keeping the player ideal for few sec's");
				extentLoggerFail("Player controls Auto hide",
						"Player controls are not auto hide after keeping the player ideal for few sec's");
			} else {
				logger.info("Player controls are auto hide after keeping the player ideal for few sec's");
				extentLogger("Player controls Auto hide",
						"Player controls are auto hide after keeping the player ideal for few sec's");
			}
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			waitTime(1000);
			verifyElementPresent(AMDPlayerScreen.objPlayIcon, "Play icon");
			verifyElementPresent(AMDPlayerScreen.objNextIcon, "Next icon");
			click(AMDPlayerScreen.objNextIcon, "Next icon");
			if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
				waitForAdToFinishInAmd();
				// click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			registerPopUpClose();
			completeProfilePopUpClose(usertype);
			waitTime(3000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementExist(AMDPlayerScreen.objPlayer, "Player screen");
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			waitTime(1000);
			verifyElementPresent(AMDPlayerScreen.objPreviousIcon, "Previous icon");
			waitTime(1000);
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
			waitTime(5000);
			GetAndVerifyOrientation("Landscape");
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Minimize icon");
			waitTime(5000);

			// -----------verifying Elapsed timer--------//
			String time1 = getText(AMDPlayerScreen.objTimer);
			int elapsedTime = timeToSec(time1);
			String time2 = getText(AMDPlayerScreen.objTotalDuration);
			int totalTime = timeToSec(time2);
			if (elapsedTime < totalTime) {
				logger.info("Elapsed timer is displayed");
				extentLoggerPass("Elapsed timer", "Elapsed timer is displayed");
			} else {
				logger.info("Elapsed timer is NOT displayed");
				extentLoggerFail("Elapsed timer", "Elapsed timer is NOT displayed");
			}

			WebElement elementElapsedBtn = findElement(AMDPlayerScreen.objTimer);
			int etimeBtnleftX = elementElapsedBtn.getLocation().getX();
			int etimeBtnrightX = etimeBtnleftX + elementElapsedBtn.getSize().getWidth();
			int etimemiddleX = (etimeBtnrightX + etimeBtnleftX) / 2;

			if (etimemiddleX <= 200) {
				logger.info("Elapsed timer on left corner is displayed");
				extent.extentLoggerPass("Elapsed time", "Elapsed timer on left corner is displayed");
			} else {
				logger.error("Elapsed timer on left corner is not displayed");
				extent.extentLoggerFail("Elapsed timer", "Elapsed timer on left corner is not displayed");
			}

			// -----------verifying Total Content Duration---------//
			WebElement elementTotDur = findElement(AMDPlayerScreen.objTotalDuration);
			int eleTotDurRightX = elementTotDur.getLocation().getX();
			Dimension sizee = getDriver().manage().window().getSize();
			int sizeee = sizee.getWidth() - 500;
			if (eleTotDurRightX >= sizeee) {
				logger.info("Content duration on right corner is displayed");
				extent.extentLoggerPass("Content duration", "Content duration on right corner is displayed");
			} else {
				logger.info("Content duration on right corner is not displayed");
				extent.extentLoggerFail("Content duration", "Content duration on right corner is not displayed");
			}

			if (findElement(AMDPlayerScreen.objChromeCastIcon).isEnabled() == false) {
				logger.info("Chrome cast icon is displayed in disable state");
				extent.extentLoggerPass("Chrome cast", "Chrome cast icon is displayed in disable state");
			} else {
				logger.error("Chrome cast icon is not displayed in disable state");
				extent.extentLoggerFail("Chrome cast", "Chrome cast icon is not displayed in disable state");
			}
			click(AMDPlayerScreen.objPlayIcon, "Play icon");
			verifyPlaybackAfterLockAndUnlock();
			waitTime(5000);
			// click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyPlaybackAfterMinimzeAndMaximizeAppFromBackground();
			waitTime(5000);
			// click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
			// verifyPlaybackAfterNetworkInterruption();

			// waitTime(5000);
//			click(AMDPlayerScreen.objPlayerScreen, "Player screen");

			// ForwardAndRewindThePlayerByDoubleTapping(1);
			// waitTime(3000);
			// ForwardAndRewindThePlayerByDoubleTapping(2);

			// ------verifying the seek functionality of the Progressbar------//
			// click(AMDPlayerScreen.objPlayIcon, "Play icon");
			waitTime(10000);
			seekVideo(AMDPlayerScreen.objProgressBar, usertype);
		}
		Back(1);
	}

	public void verifyPlaybackAfterNetworkInterruption() throws Exception {
		extent.HeaderChildNode("Validation of content playback after Network Interruption");
		System.out.println("\nValidation of content playback after Network Interruption");
		String time1 = getText(AMDPlayerScreen.objTimer);
		int startTime = timeToSec(time1);
		System.out.println(startTime);
		logger.info("Time captured before network interruption : " + startTime);
		extentLogger("Time", "Time captured before network interruption : " + startTime);
		TurnOFFWifi();
		waitTime(5000);
		verifyElementPresent(AMDPlayerScreen.objRetryBtn, "Retry CTA");
		TurnONWifi();
		waitTime(5000);
		click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
		String time2 = getText(AMDPlayerScreen.objTimer);
		int elapsedTime = timeToSec(time2);
		logger.info("Time captured after network is connected : " + elapsedTime);
		extentLogger("Time", "Time captured after network is connected : " + elapsedTime);
		if (elapsedTime > startTime) {
			logger.info("Content playback is resumed after network is connected");
			extentLoggerPass("Elapsed time", "Content playback is resumed after network is connected");
		} else {
			logger.info("Content playback is not resumed after network is connected");
			extentLoggerFail("Elapsed time", "Content playback is not resumed after network is connected");
		}
	}

	public void movieWithoutTrailer() throws Exception {
		extent.HeaderChildNode("InLineSubscription link");
		waitTime(2000);
		clearField(AMDSearchScreen.objSearchEditBox, "Search Box");
		waitTime(2000);
		type(AMDSearchScreen.objSearchBoxBar, "Chemistry of Kariyappa" + "\n", "Search bar");
		hideKeyboard();
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		verifyElementPresentAndClick(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(3000);
		verifyElementExist(AMDPlayerScreen.objPremiumTextOnPlayer, "Subscription required text");
		verifyElementPresentAndClick(AMDPlayerScreen.objSubscribeNowLinkOnPlayer, "Subscribe Now Link");
		if (checkElementExist(AMDPlayerScreen.objGetPremiumPopUp, "Get Premium popUp")) {
			logger.info("User is navigated to Get premium popup post tapping on Subscribe Now Link");
			extentLogger("GetPremium popUp",
					"User is navigated to Get premium popup post tapping on Subscribe Now Link");
		} else {
			logger.info("User is not navigated to Get premium popup post tapping on Subscribe Now Link");
			extentLoggerFail("GetPremium popUp",
					"User is not navigated to Get premium popup post tapping on Subscribe Now Link");
		}
		Back(1);
		verifyElementExist(AMDPlayerScreen.objLoginTextOnPlayer, "Login required text");
		verifyElementPresentAndClick(AMDPlayerScreen.objLoginLinkOnPlayer, "Login link");
		if (checkElementExist(AMDLoginScreen.objLoginOrRegisterPageTitle, "Login/Register screen")) {
			logger.info("User is navigated to Login/Register screen post tapping on Login link");
			extentLogger("Login/Register screen",
					"User is navigated to Login/Register screen post tapping on Login link");
		} else {
			logger.info("User is navigated to Login/Register screen post tapping on Login link");
			extentLoggerFail("Login/Register screen",
					"User is navigated to Login/Register screen post tapping on Login link");
		}
		Back(1);
		waitTime(2000);
		Back(1);
	}

	public void seekVideoTillLast(By byLocator1) throws Exception {

		String beforeSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time before seeking : " + timeToSec(beforeSeek));
		extent.extentLogger("Seek", "Current time before seeking in seconds: " + timeToSec(beforeSeek));

		WebElement element = getDriver().findElement(byLocator1);
		Dimension size = element.getSize();
		int startx = (int) (size.width);
		int startX = startx + 180;
		System.out.println(startX);
		SwipeAnElement(element, startX, 0);

		waitTime(2000);
		String afterSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time after seeking in seconds : " + timeToSec(afterSeek));
		extent.extentLogger("Seek", "Current time after seeking in seconds : " + timeToSec(afterSeek));

		String totalDur = findElement(AMDPlayerScreen.objTotalDuration).getText();
		if (timeToSec(afterSeek) > (timeToSec(totalDur) - 120)) {
			logger.info("Seeked the video till last");
			extentLoggerPass("Seeking the video till last", "Seeked the video till last");
			logger.info("Seek bar is functional");
			extent.extentLogger("Seek", "Seek bar is functional");
		} else {
			logger.info("Not seeked the video till last");
			extentLoggerFail("Seeking the video till last", "Not seeked the video till last");
			logger.info("Seek bar is not functional");
			extent.extentLoggerFail("Seek", "Seek bar is not functional");
		}
	}

	@SuppressWarnings("rawtypes")
	public void ForwardAndRewindThePlayerByDoubleTapping(int n) throws Exception {

		System.out.println("\nVerification of Forward and Rewind of a playback");
		Dimension sizee = getDriver().manage().window().getSize();

		ScreenOrientation orientation = getDriver().getOrientation();
		String ScreenOrientation = orientation.toString();
		int FwdXValue;
		if (ScreenOrientation.equalsIgnoreCase("Landscape")) {
			FwdXValue = (int) (sizee.getHeight() * 0.9);
		} else {
			FwdXValue = (int) (sizee.getWidth() * 0.9);
		}

		int YValue = Integer.valueOf(getAttributValue("y", AMDPlayerScreen.objNextIcon));
		touchAction = new TouchAction(getDriver());

		click(AMDPlayerScreen.objPlayerScreen, "Player screen");

		// --------verifying Fast rewind funcionality of the player------//
		String time11 = getText(AMDPlayerScreen.objTimer);
		System.out.println(time11);
		int timebeforeRewind = timeToSec(time11);
		logger.info("Time captured before rewind playback in seconds: " + timebeforeRewind + " sec");
		extentLogger("Rewind", "Time captured before rewind playback in seconds: " + timebeforeRewind + " sec");

		for (int i = 0; i < n; i++) {
			touchAction.press(PointOption.point(100, YValue)).release().perform().press(PointOption.point(100, YValue))
					.release().perform();
		}

		verifyElementExist(AMDPlayerScreen.objTimer, "Elapsed timer");
		String time12 = getText(AMDPlayerScreen.objTimer);
		System.out.println(time12);
		int timeAfterRewind = timeToSec(time12);
		logger.info("Time captured after rewind playback in seconds: " + timeAfterRewind + " sec");
		extentLogger("Rewind", "Time captured after rewind playback in seconds: " + timeAfterRewind + " sec");
		int diffInTime = timebeforeRewind - timeAfterRewind;
		if ((diffInTime == (n * 10)) | (diffInTime >= (n * 8))) {
			logger.info("User is able to fast rewind the playback on double tapping " + n
					+ " time in the player screen for " + (n * 10) + "secs");
			extentLoggerPass("Rewind the playback", "User is able to fast rewind the playback on double tapping " + n
					+ " time in the player screen for " + (n * 10) + "secs");
		} else {
			logger.info("User is NOT able to fast rewind the playback on double tapping " + n
					+ " time in the player screen for " + (n * 10) + "secs");
			extentLoggerFail("Rewind the playback", "User is NOT able to fast rewind the playback on double tapping "
					+ n + " time in the player screen for " + (n * 10) + "secs");
		}
		waitTime(5000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");

		// --------verifying Fast forward funcionality of the player------//
		String time21 = getText(AMDPlayerScreen.objTimer);
		System.out.println(time21);
		int timebeforeforward = timeToSec(time21);
		logger.info("Time captured before forwarding playback in seconds: " + timebeforeforward + " sec");
		extentLogger("Forward", "Time captured before forwarding playback in seconds: " + timebeforeforward + " sec");
		System.out.println("Value :" + FwdXValue + " X " + YValue);
		for (int i = 0; i < n; i++) {
			touchAction.press(PointOption.point(FwdXValue, YValue)).release().perform()
					.press(PointOption.point(FwdXValue, YValue)).release().perform();
		}

		verifyElementExist(AMDPlayerScreen.objTimer, "Elapsed timer");
		String time22 = getText(AMDPlayerScreen.objTimer);
		System.out.println(time22);
		int timeAfterforward = timeToSec(time22);
		logger.info("Time captured after forwarding playback in seconds: " + timeAfterforward + " sec");
		extentLogger("Forward", "Time captured after forwarding playback in seconds: " + timeAfterforward + " sec");
		diffInTime = timeAfterforward - timebeforeforward;
		System.out.println("TIME DIFFERENCE: " + diffInTime);
		if (diffInTime >= ((n) * 10)) {
			logger.info("User is able to fast forward the playback on double tapping " + n
					+ " time in the player screen for " + ((n) * 10) + "secs");
			extentLoggerPass("Forward the playback", "User is able to fast forward the playback on double tapping " + n
					+ " time in the player screen for " + ((n) * 10) + "secs");
		} else {
			logger.info("User is NOT able to fast forward the playback on double tapping " + n
					+ " time in the player screen for " + ((n) * 10) + "secs");
			extentLoggerFail("Forward the playback", "User is NOT able to fast forward the playback on double tapping "
					+ n + " time in the player screen for " + ((n) * 10) + "secs");
		}
	}

	public void skipIntroValidation(String searchKeyword3) throws Exception {
		extent.HeaderChildNode("Validation of Skip Intro CTA");
		waitTime(5000);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword3 + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		verifyElementPresentAndClick(AMDMoreMenu.objSearchResult(searchKeyword3), "Search result");
		waitTime(5000);

		click(AMDPlayerScreen.objPlayerScreen, "Player screen");

		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(2000);
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Maximize Icon");

		String time1 = getText(AMDPlayerScreen.objTimer);
		int startTime = timeToSec(time1);

		logger.info("Time before clicking on Skip Intro CTA : " + startTime);
		extentLogger("Time", "Time before clicking on Skip Intro CTA : " + startTime);

		verifyElementPresentAndClick(AMDPlayerScreen.objSkipIntro, "Skip Intro CTA");
		waitTime(2000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");

		String time2 = getText(AMDPlayerScreen.objTimer);
		int elapsedTime = timeToSec(time2);

		logger.info("Time after clicking on Skip Intro CTA : " + elapsedTime);
		extentLogger("Time", "Time after clicking on Skip Intro CTA : " + elapsedTime);

		if (elapsedTime > startTime) {
			logger.info("Introduction playback of the content is skipped");
			extentLogger("Elapsed time", "Introduction playback of the content is skipped");
		} else {
			logger.info("Introduction playback of the content is not skipped");
			extentLoggerFail("Elapsed time", "Introduction playback of the content is not skipped");
		}

	}

	/**
	 * Author : Sushma
	 */

	public void validationOfWatchCreditsAndUpNextCard(String searchKeyword, String usertype) throws Exception {
		extent.HeaderChildNode("Validation of Watch Credits CTA and UpNext Card");
		System.out.println("\nValidation of Watch Credits CTA and UpNext Card");

		click(AMDSearchScreen.objSearchBoxBar, "Search Box");
		clearField(AMDSearchScreen.objSearchBoxBar, "Search box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDMoreMenu.objSearchResult(searchKeyword), "Search result");
		verifyElementExist(AMDPlayerScreen.objPlayer, "Player screen");
		waitTime(7000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		Swipe("UP", 1);
		String firstTitleInUpnextTray = null;
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			firstTitleInUpnextTray = getText(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray);
		}
		waitTime(2000);
		seekVideoTillLast(AMDPlayerScreen.objProgressBar);
		click(AMDPlayerScreen.objPlayIcon, "Play icon");
		verifyElementPresent(AMDPlayerScreen.objWatchCreditsCTA, "Watch Credits CTA");

		if (usertype.equalsIgnoreCase("Guest")) {
			verifyElementPresent(AMDPlayerScreen.objUpNextCard, "Upnext card");

			if (verifyIsElementDisplayed(AMDPlayerScreen.objCountDownTimerInUpNextCard)) {
				String timerOnUpnextcard = getText(AMDPlayerScreen.objCountDownTimerInUpNextCard);
				logger.info("Timer on Upnext card: " + timerOnUpnextcard);
				extentLogger("Timer on Upnext card", "Timer on Upnext card: " + timerOnUpnextcard);
				waitTime(5000);
				if (verifyIsElementDisplayed(AMDPlayerScreen.objCountDownTimerInUpNextCard)) {
					logger.info("Timer on Upnext card is not exceeded");
					extentLogger("Timer on Upnext card", "Timer on Upnext card is not exceeded");
				} else {
					logger.info("Timer on Upnext card is exceeded");
					extentLogger("Timer on Upnext card", "Timer on Upnext card is exceeded");
				}
			}

			verifyElementExist(AMDPlayerScreen.objPlayer, "Player screen");
			Swipe("DOWN", 1);
			verifyElementDisplayed(AMDPlayerScreen.objcontentTitleInconsumptionPage);
			String title = getText(AMDPlayerScreen.objcontentTitleInconsumptionPage);

			if (firstTitleInUpnextTray.equalsIgnoreCase(title)) {
				logger.info("User is jumped to the next content when timer exceeds");
				extentLoggerPass("Upnext card", "User is jumped to the next content when timer exceeds");
			} else {
				logger.info("User is not jumped to the next content when timer exceeds");
				extentLoggerFail("Upnext card", "User is not jumped to the next content when timer exceeds");
			}
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementPresentAndClick(AMDPlayerScreen.objBackButton, "Back button");
			if (verifyIsElementDisplayed(AMDMoreMenu.objSearchResult(searchKeyword))) {
				logger.info("User is navigated back to the previous screen from where the content is accessed");
				extentLoggerPass("back button",
						"User is navigated back to the previous screen from where the content is accessed");
			} else {
				logger.error("User is navigated back to the previous screen from where the content is accessed");
				extentLoggerFail("back button",
						"User is navigated back to the previous screen from where the content is accessed");
			}

			click(AMDMoreMenu.objSearchResult(searchKeyword), "Search result");
			verifyElementExist(AMDPlayerScreen.objPlayer, "Player screen");
			waitTime(7000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			waitTime(2000);
			seekVideoTillLast(AMDPlayerScreen.objProgressBar);
			click(AMDPlayerScreen.objPlayIcon, "Play icon");
			JSClick(AMDPlayerScreen.objUpNextCard, "Upnext card");
			verifyElementExist(AMDPlayerScreen.objPlayer, "Player screen");
			Swipe("DOWN", 1);
			verifyElementDisplayed(AMDPlayerScreen.objcontentTitleInconsumptionPage);
			title = getText(AMDPlayerScreen.objcontentTitleInconsumptionPage);

			if (firstTitleInUpnextTray.equalsIgnoreCase(title)) {
				logger.info("User is navigated to the next content post tapping on Upnext card");
				extentLoggerPass("Upnext card", "User is navigated to the next content post tapping on Upnext card");
			} else {
				logger.info("User is not navigated to the next content post tapping on Upnext card");
				extentLoggerFail("Upnext card",
						"User is not navigated to the next content post tapping on Upnext card");
			}
		}
		Back(1);
	}

	/**
	 * Author : Sushma Module : Player
	 */

	public void ValidationOfReplayIconOnPlayer(String searchKeyword) throws Exception {
		extent.HeaderChildNode("Validation of Replay icon on Player");
		System.out.println("\nValidation of Replay icon on Player");

		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		click(AMDMoreMenu.objSettings, "Settings option");

		String elementAutoPlayToggleStatus = getText(AMDMoreMenu.objVideo_Autoply);
		if (elementAutoPlayToggleStatus.equalsIgnoreCase("ON")) {
			click(AMDMoreMenu.objVideo_Autoply, "Auto play");
		}
		Back(1);
		waitTime(3000);
		Back(1);
		waitTime(3000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword + "\n", "Search bar");
		hideKeyboard();
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");

		verifyElementExist(AMDPlayerScreen.objPlayer, "Player screen");
		waitTime(7000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		Swipe("DOWN", 1);
		String contentTitle1 = getText(AMDPlayerScreen.objcontentTitleInconsumptionPage);

		seekVideoTillLast(AMDPlayerScreen.objProgressBar);

		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
//		click(AMDPlayerScreen.objPlayIcon, "Play icon");
		verifyElementExist(AMDPlayerScreen.objReplayIconOnPlayer, "Replay icon");
		click(AMDPlayerScreen.objReplayIconOnPlayer, "Replay icon");
		waitTime(3000);
		Swipe("DOWN", 1);
		String contentTitle2 = getText(AMDPlayerScreen.objcontentTitleInconsumptionPage);
		if (contentTitle1.equalsIgnoreCase(contentTitle2)) {
			logger.info("same content playback is started again by tapping on Replay icon");
			extentLoggerPass("Replay icon", "same content playback is started again by tapping on Replay icon");
		} else {
			logger.info("same content playback is not started again by tapping on Replay icon");
			extentLoggerFail("Replay icon", "same content playback is not started again by tapping on Replay icon");
		}
		Back(1);
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		click(AMDMoreMenu.objSettings, "Settings option");
		elementAutoPlayToggleStatus = getText(AMDMoreMenu.objVideo_Autoply);
		click(AMDMoreMenu.objVideo_Autoply, "Auto play");
		Back(1);
		waitTime(3000);
		Back(1);
	}

	/**
	 * Author : Manasa Module : Player
	 */
	public void playerValidationInFullScreenMode(String userType, String searchKeyword2) throws Exception {
		extent.HeaderChildNode("Player Validation in Fullscreen Mode");
		System.out.println("\nPlayer Validation in Fullscreen Mode");
		waitTime(3000);
		click(AMDHomePage.objMoreMenu, "More Menu");
		waitTime(1000);
		click(AMDMoreMenu.objSettings, "Settings option");
		String getPropertyvalue = getAttributValue("text", AMDMoreMenu.objVideo_Autoply);
		if (getPropertyvalue.equalsIgnoreCase("ON")) {
			click(AMDMoreMenu.objVideo_Autoply, "Autoplay TrunOff");
			logger.info("AutoPlay Option is Turned OFF");
			extentLoggerPass("Autoplay Option", "AutoPlay Option is Turned OFF");
		} else {
			logger.info("AutoPlay Option was already Turned OFF");
			extentLoggerPass("Autoplay Option", "AutoPlay Option was already Turned OFF");
		}
		Back(2);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword2 + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDMoreMenu.objSearchResult(searchKeyword2), "Search result");
		if (!userType.contains("SubscribedUser")) {
			waitTime(5000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);
			LoadingInProgress();
			adPlay();
			waitTime(5000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}

		if (verifyElementExist(AMDPlayerScreen.objPlayer, "Player screen")) {
			waitTime(3000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementPresent(AMDPlayerScreen.objPauseIcon, "Pause icon");
			// Hard wait to check if player controls disappear
			waitTime(7000);
			if (verifyIsElementDisplayed(AMDPlayerScreen.objPlayer)) {
				logger.info("Player controls does not auto hide after keeping the player idle for few sec's");
				extentLoggerFail("Player controls Auto hide",
						"Player controls does not auto hide after keeping the player idle for few sec's");
			} else {
				logger.info("Player controls auto hide after keeping the player idle for few sec's");
				extentLoggerPass("Player controls Auto hide",
						"Player controls auto hide after keeping the player idle for few sec's");
			}
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");

			verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");

			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Maximize Icon");
			waitTime(2000);
			GetAndVerifyOrientation("Landscape");
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Minimize Icon");
			waitTime(2000);
			GetAndVerifyOrientation("Portrait");
			click(AMDPlayerScreen.objFullscreenIcon, "Maximize Icon");
			ForwardAndRewindThePlayerByDoubleTapping(1);
			waitTime(5000);
			ForwardAndRewindThePlayerByDoubleTapping(2);

			verifyElementPresent(AMDPlayerScreen.objProgressBar, "Progress/Seek Bar");
			verifyElementPresent(AMDPlayerScreen.objNextIcon, "Next Icon");
			verifyElementPresent(AMDPlayerScreen.objChromeCastIcon, "Chromecast Icon");

			if (findElement(AMDPlayerScreen.objChromeCastIcon).isEnabled() == false) {
				logger.info("Chrome cast icon is displayed in disable state");
				extent.extentLoggerPass("Chrome cast", "Chrome cast icon is displayed in disable state");
			} else {
				logger.error("Chrome cast icon is not displayed in disable state");
				extent.extentLoggerFail("Chrome cast", "Chrome cast icon is not displayed in disable state");
			}

			verifyElementPresent(AMDPlayerScreen.objTitleOnPlayer, "Content Title on player");

			WebElement elementElapsedBtn = findElement(AMDPlayerScreen.objTimer);
			int etimeBtnleftX = elementElapsedBtn.getLocation().getX();
			int etimeBtnrightX = etimeBtnleftX + elementElapsedBtn.getSize().getWidth();
			int etimemiddleX = (etimeBtnrightX + etimeBtnleftX) / 2;

			if (etimemiddleX <= 200) {
				logger.info("Elapsed timer is displayed on left corner ");
				extent.extentLoggerPass("Elapsed time", "Elapsed timer is displayed on left corner ");
			} else {
				logger.error("Elapsed timer is not displayed on left corner ");
				extent.extentLoggerFail("Elapsed timer", "Elapsed timer is not displayed on left corner ");
			}

			WebElement elementTotDur = findElement(AMDPlayerScreen.objTotalDuration);
			int eleTotDurRightX = elementTotDur.getLocation().getX();
			Dimension sizee = getDriver().manage().window().getSize();
			int sizeee = sizee.getWidth() - 500;
			if (eleTotDurRightX >= sizeee) {
				logger.info("Content duration is displayed on right corner");
				extent.extentLoggerPass("Content duration", "Content duration is displayed on right corner");
			} else {
				logger.error("Content duration is not displayed  on right corner");
				extent.extentLoggerFail("Content duration", "Content duration is not displayed  on right corner");
			}

			verifyElementPresentAndClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Player option with 3 dots");
			String quality = getText(AMDPlayerScreen.objQuality);
			if (quality.contains("Auto")) {
				logger.info("Video Quality is set to " + quality + " by default");
				extent.extentLoggerPass("Video Quality", "Video Quality is set to " + quality + " by default");

			} else {
				logger.error("Video Quality is not set to Auto by default");
				extent.extentLoggerFail("Video Quality", "Video Quality is not set to Auto by default");
			}

			click(AMDPlayerScreen.objQuality, "Quality");

			List<WebElement> qualityOptions = findElements(AMDPlayerScreen.objQualityOptions);
			System.out.println(qualityOptions.size());
			for (int i = 0; i < qualityOptions.size(); i++) {
				String options = getText(AMDPlayerScreen.objQualityOptions(i + 1));
				logger.info("Quality option : " + options);
				extent.extentLoggerPass("Video Quality", "Quality option : " + options);
			}
			Back(1);
			click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
			click(AMDPlayerScreen.objThreeDotsOnPlayer, "Player option with 3 dots");
			verifyElementPresentAndClick(AMDPlayerScreen.objAddToWatchlist, "Add to Watchlist option");

			if (userType.equalsIgnoreCase("Guest")) {
				if (verifyElementExist(AMDOnboardingScreen.objScreenTitle, "Login/Register Page")) {
					logger.info("Navigated to Login/Registration screen");
					extent.extentLoggerPass("Login page", "Navigated to Login/Registration screen");
					waitTime(2000);
					click(AMDLoginScreen.objLoginLnk, "Skip link");
					// Back(1);
				} else {
					logger.error("Not Navigated to Login/Registration screen");
					extent.extentLoggerFail("Login page", "Not navigated to Login/Registration screen");
				}
			}
			waitTime(5000);
			click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
			click(AMDPlayerScreen.objThreeDotsOnPlayer, "Player option with 3 dots");
			verifyElementPresentAndClick(AMDPlayerScreen.objPlaybackRate, "Playback Rate option");

			List<WebElement> playbackRate = findElements(AMDPlayerScreen.objQualityOptions);

			for (int i = 0; i < playbackRate.size(); i++) {
				String options = getText(AMDPlayerScreen.objQualityOptions(i + 1));
				logger.info("Playback Rate option : " + options);
				extent.extentLoggerPass("Playback Rate", "Playback Rate option : " + options);
			}
			String rate = getText(AMDPlayerScreen.objPlaybackRateSelected);
			System.out.println(rate);
			if (rate.equalsIgnoreCase("1.0X")) {
				logger.info("Playback rate is set to " + rate + " by default");
				extent.extentLoggerPass("Playback Rate", "Playback rate is set to " + rate + " by default");
			} else {
				logger.error("Playback rate is not set to 1.0X by default");
				extent.extentLoggerFail("Playback rate", "Playback rate is not set to 1.0X by default");
			}

			Back(1);
			click(AMDPlayerScreen.objPlayerScreen, "Player Frame");

			verifyPlaybackAfterMinimzeAndMaximizeAppFromBackground();
			waitTime(5000);
			click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
			verifyPlaybackAfterLockAndUnlock();
			waitTime(5000);
			// click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
			// verifyPlaybackAfterNetworkInterruption();
			// waitTime(5000);
			click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
			click(AMDPlayerScreen.objShareIconOnPlayer, "Share Icon");
			verifyElementPresent(AMDPlayerScreen.objSharePopUp, "Share Pop Up/Share Overlay");
			checkElementExist(AMDPlayerScreen.objCopyToClipboard, "Copy to clipboard");

			boolean facebookIcon = verifyElementDisplayed(AMDPlayerScreen.objFacebook);
			if (facebookIcon) {
				click(AMDPlayerScreen.objFacebook, "Facebook Icon");
				logger.info("Selected Facebook icon from Share screen");
				extent.extentLoggerPass("Facebook Icon", "Selected Facebook icon from Share screen");
			} else {
				logger.error("Facebook icon is NOT available in the Share screen");
				extent.extentLoggerWarning("Facebook Icon", "Facebook icon is NOT available to Share");
			}

			waitTime(5000);
			boolean fbLoginPage = verifyElementDisplayed(AMDPlayerScreen.objfbLoginPage);
			if (fbLoginPage) {
				logger.error("Facebook account is not logged into this device");
				extent.extentLoggerWarning("Facebook Account", "Facebook account is not logged into this device");
				Back(1);
			} else {
				boolean fbPost = verifyElementDisplayed(AMDPlayerScreen.objFacebookPost);
				if (fbPost) {
					click(AMDPlayerScreen.objFacebookPost, "Post Icon");
					logger.info("Selected Post icon from Facebook page");
					extent.extentLoggerPass("Post Icon", "Selected Post icon from Facebook page");
				} else {
					logger.error("Post Icon is not displayed");
					extent.extentLoggerWarning("Post Icon", "Post Icon is not displayed");
				}
			}

			waitTime(5000);
			click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
			click(AMDPlayerScreen.objShareIconOnPlayer, "Share Icon");

			boolean twitter = verifyIsElementDisplayed(AMDPlayerScreen.objTwitter);
			if (twitter) {
				click(AMDPlayerScreen.objTwitter, "Twitter Icon");
				logger.info("Selected twitter icon from Share screen");
				extent.extentLoggerPass("Twitter Icon", "Selected twitter icon from Share screen");

				waitTime(5000);
				boolean twitterPage = verifyIsElementDisplayed(AMDPlayerScreen.objTweetButton);
				if (twitterPage) {
					click(AMDPlayerScreen.objTweetButton, "Tweet button");
					logger.info("Selected twitter button to post from twitter page");
					extent.extentLoggerPass("Twitter button", "Selected twitter button to post from twitter page");
				} else {
					logger.info("Twitter account is NOT logged into this device");
					extent.extentLoggerWarning("Twitter button", "Twitter account is NOT logged into this device");
				}

			} else {
				logger.info("Twitter icon is NOT available in the Share screen");
				extent.extentLoggerWarning("Twitter icon", "Twitter icon is NOT available in the Share screen");
			}
			waitTime(5000);
			Back(1);
			click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
			seekVideoTillLast(AMDPlayerScreen.objProgressBar);
			// click(AMDPlayerScreen.objPlayIcon, "Play Icon");
			waitForElementDisplayed(AMDPlayerScreen.objReplay, 5);
			verifyElementPresentAndClick(AMDPlayerScreen.objReplay, "Replay Icon");
			if (!userType.contains("SubscribedUser")) {
				waitTime(2000);
				adPlay();
				waitTime(5000);
			}
			click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
			if (verifyIsElementDisplayed(AMDPlayerScreen.objTitleInLandscape(searchKeyword2))) {
				logger.info("Same content playback started again post tapping on Replay icon");
				extent.extentLoggerPass("Replay", "Same content playback started again post tapping on Replay icon");
			} else {
				logger.error("Same content playback did not start post tapping on Replay icon");
				extent.extentLoggerFail("Replay", "Same content playback did not start post tapping on Replay icon");
			}
		}
		Back(2);
		click(AMDHomePage.objMoreMenu, "More Menu");
		waitTime(1000);
		click(AMDMoreMenu.objSettings, "Settings option");
		waitTime(2000);
		click(AMDMoreMenu.objVideo_Autoply, "Autoplay");
		Back(2);
	}

	public void watchCreditsValidationInLandscapeMode(String searchKeyword9, String userType) throws Exception {
		extent.HeaderChildNode("Validation of Watch Credits CTA");
		System.out.println("\nValidation of Watch Credits CTA");
		waitTime(5000);
		click(AMDHomePage.HomeIcon, "Home button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword9 + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		verifyElementPresentAndClick(AMDMoreMenu.objSearchResult(searchKeyword9), "Search result");
		if (!userType.contains("SubscribedUser")) {
			registerPopUpClose();
			completeProfilePopUpClose(userType);
			LoadingInProgress();
			adPlay();
			waitTime(5000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		waitTime(1000);
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(2000);
		click(AMDPlayerScreen.objFullscreenIcon, "Maximize Icon");
		String title = getText(AMDPlayerScreen.objContentTitle);
		if (title != null) {
			logger.info("Title of the Content displayed in landscape mode: " + title);
			extentLoggerPass("Title", "Title of the Content displayed in landscape mode: " + title);

			seekVideoTillLast(AMDPlayerScreen.objProgressBar);
		} else {
			logger.info("Title of the Content is NOT displayed in landscape mode");
			extentLoggerFail("Title", "Title of the Content is NOT displayed in landscape mode");
		}
//		click(AMDPlayerScreen.objPlayIcon, "Play icon");
		verifyElementPresent(AMDPlayerScreen.objWatchCreditsCTA, "Watch Credits CTA");
		verifyElementPresent(AMDPlayerScreen.objUpNextCard, "Upnext card");
//		String timerOnUpnextcard = getText(AMDPlayerScreen.objCountDownTimerInUpNextCard);
//		logger.info("Timer on Upnext card: "+timerOnUpnextcard);
//		extentLogger("Timer on Upnext card", "Timer on Upnext card: "+timerOnUpnextcard);
		waitTime(5000);
		waitTime(15000);
		LoadingInProgress();
		verifyElementExist(AMDPlayerScreen.objPlayer, "Player screen");
		String title1 = getText(AMDPlayerScreen.objContentTitle);
		logger.info("Title of the Upnext Content : " + title1);
		extentLogger("Title", "Title of the Upnext Content : " + title1);
		if (!(title.equals(title1))) {
			logger.info("Content autoplayed/jumped to the next content when timer exceeds");
			extentLoggerPass("Upnext card", "Content autoplayed/jumped to the next content when timer exceeds");
		} else {
			logger.info("Content did not autoplay/jump to the next content when timer exceeds");
			extentLoggerFail("Upnext card", "Content did not autoplay/jump to the next content when timer exceeds");
		}
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
	}

	public void adPlay() throws Exception {
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDPlayerScreen.objAd)) {
			logger.info("Ad is playing");
			extentLogger("Ad", "Ad is playing");
			verifyElementNotPresent(AMDPlayerScreen.objAd, 180);
			logger.info("Ad is completed");
			extentLogger("Ad", "Ad is completed");
		} else {
			logger.info("Ad is not played");
			extentLogger("Ad", "Ad is not played");
		}
	}

	@SuppressWarnings("deprecation")
	public void parentalPinValidationInLandscapeMode(String userType, String searchKeyword1) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Parental Pin Validation In Landscape Mode");

			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			waitTime(1000);
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(1000);
			SwipeUntilFindElement(AMDMoreMenu.objParentalControl, "UP");
			click(AMDMoreMenu.objParentalControl, "Parental Control");

			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("USMNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			click(AMDMoreMenu.objRestrictAllContent, "Restrict All Content option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);

			verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
			type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
			hideKeyboard();
			waitTime(4000);
			click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
			waitTime(2000);

			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			Back(1);
			waitTime(3000);
			Back(1);

			waitTime(5000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, searchKeyword1 + "\n", "Search bar");
			waitTime(2000);
			hideKeyboard();
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDMoreMenu.objSearchResult(searchKeyword1), "Search result");
			waitTime(2000);
			switchtoLandscapeMode();
			waitTime(2000);
			boolean checkParentalPopUp = verifyElementPresent(AMDPlayerScreen.objParentalPinPopUp,
					"Parental Pin Popup");
			if (checkParentalPopUp) {
				logger.info("Parental Pin Popup is displayed");
				extentLoggerPass("Parental Pin Popup", "Parental Pin Popup is displayed");
				Back(2);
			} else {
				logger.info("Parental Pin Popup is NOT displayed");
				extentLoggerFail("Parental Pin Popup", "Parental Pin Popup is NOT displayed");
			}

			switchtoPortraitMode();
			Back(2);
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			waitTime(1000);
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			SwipeUntilFindElement(AMDMoreMenu.objParentalControl, "UP");
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");

			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("USMNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			click(AMDMoreMenu.objNoRestriction, "No Restriction");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			Back(1);
		}
	}

	public void LoadingInProgress() throws Exception {
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDPlayerScreen.objPlayerLoader)) {
			logger.info("Loading in progress");
			extentLogger("Loader", "Loading in progress");

			verifyElementNotPresent(AMDPlayerScreen.objPlayerLoader, 20);
		}
	}

	/**
	 * Seek by element
	 * 
	 * @param byLocator
	 */
	public void seekVideo(By byLocator, String usertype) throws Exception {
		// click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
		String beforeSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time before seeking : " + timeToSec(beforeSeek));
		extent.extentLogger("Seek", "Current time before seeking in seconds: " + timeToSec(beforeSeek));
		WebElement element = getDriver().findElement(byLocator);
		Dimension size = element.getSize();
		int startx = (int) (size.width);
		// click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
		SwipeAnElement(element, startx, 0);
		logger.info("Scrolling the seek bar");
		extent.extentLogger("Seek", "Scrolling the seek bar");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
			// click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		waitTime(2000);
		String afterSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time after seeking in seconds : " + timeToSec(afterSeek));
		extent.extentLogger("Seek", "Current time after seeking in seconds : " + timeToSec(afterSeek));
		if (timeToSec(afterSeek) > timeToSec(beforeSeek)) {
			logger.info("Seek bar is functional");
			extent.extentLoggerPass("Seek", "Seek bar is functional");
		} else {
			logger.info("Seek bar is not functional");
			extent.extentLoggerFail("Seek", "Seek bar is not functional");
		}
	}

	public void SwipeAnElement(WebElement element, int posx, int posy) {
		AndroidTouchAction touch = new AndroidTouchAction(getDriver());
		touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(posx, posy))
				.release().perform();
	}

	public static int timeToSec(String s) {
		String[] t = s.split(":");
		int num = 0;
		// System.out.println(t.length);
		if (t.length == 2) {
			num = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]); // minutes since 00:00
		}
		if (t.length == 3) {
			num = ((Integer.parseInt(t[0]) * 60) * 60) + Integer.parseInt(t[1]) * 60 + Integer.parseInt(t[2]);
		}
		return num;
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
//				if(verifyElementExist(PWAPlayerPage.objSkipAd, "SkipAd")){
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
			logger.error("Ad did not play");
			extent.extentLogger("Ad", "Ad did not play");
		}
	}

	/**
	 * Get the Mobile Orientation
	 */
	public void GetAndVerifyOrientation(String Orientation) {
		ScreenOrientation orientation = getDriver().getOrientation();
		String ScreenOrientation = orientation.toString();
		System.out.println(ScreenOrientation);
		if (Orientation.equalsIgnoreCase(ScreenOrientation)) {
			logger.info("The screen Orientation is " + ScreenOrientation);
			extent.extentLogger("Screen Orientation", "The screen Orientation is " + ScreenOrientation);
		} else {
			logger.error("The screen Orientation is not " + ScreenOrientation);
			extent.extentLoggerFail("Screen Orientation", "The screen Orientation is not " + ScreenOrientation);
		}
	}

	/**
	 * Author : Vinay Module : More screen Screen : Account details , Buy
	 * subscription, My subscription , My Transaction
	 */
	public void AccountDetails(String userType) throws Exception {
		if (userType.equals("Guest")) {
			AccountDetailsGuestUser(userType);
		} else {
			AccountDetailsLoggedInUser(userType);
		}
	}

	public void AccountDetailsGuestUser(String userType) throws Exception {
		/*
		 * Account Details section
		 */
		extent.HeaderChildNode("Account Details Validation for User type : " + userType);
		click(AMDHomePage.MoreMenuIcon, "More icon");
		// Verify Login/Register for better experience is displayed in Profile screen
		verifyElementPresent(AMDMoreMenu.objLoginRegisterText, "Login/Register for best experience text");
		// Verify user is navigated to Login/Register screen post tapping Login/Register
		// link
		click(AMDMoreMenu.objLoginRegisterText, "Login/Registet link");
		String header = getText(AMDGenericObjects.objgetScreenTitle);
		if (header.equals("Login/Register")) {
			extent.extentLoggerPass("Verify navigation", "User is navigated to " + header
					+ " screen post tapping Login/Register link from more menu screen ");
			logger.info("User is navigated to " + header
					+ " screen post tapping Login/Register link from more menu screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated to Login/Register screen post tapping Login/Register link from more menu screen ");
			logger.info(
					"User is not navigated to Login/Register screen post tapping Login/Register link from more menu screen");
		}

		// Verify user is navigated back to previous screen on clicking back button
		// click back button
		click(AMDGenericObjects.objBackBtn, "Back button");
		waitTime(2000);
		Swipe("DOWN", 1);
		if (verifyElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLoggerPass("Verify navigation",
					"User is navigate back to the previous screen post tapping back button from Login/Register screen");
			logger.info(
					"User is navigate back to the previous screen post tapping back button from Login/Register screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the previous screen post tapping back button from Login/Register screen");
			logger.info(
					"User is not navigated back to the previous screen post tapping back button from Login/Register screen");
		}
	}

	public void MoreScreenGuestUserValidations(String userType) throws Exception {
		/*
		 * Account Details section
		 */
		extent.HeaderChildNode("Account Details Validation for User type : " + userType);
		// Click on More button
		click(AMDHomePage.MoreMenuIcon, "More icon");
		// Verify Login/Register for better experience is displayed in Profile screen
		verifyElementPresent(AMDMoreMenu.objLoginRegisterText, "Login/Register for best experience text");
		// Verify user is navigated to Login/Register screen post tapping Login/Register
		// link
		click(AMDMoreMenu.objLoginRegisterText, "Login/Registet link");
		String header = getText(AMDGenericObjects.objgetScreenTitle);
		if (header.equals("Login/Register")) {
			extent.extentLogger("Verify navigation", "User is navigated to " + header
					+ " screen post tapping Login/Register link from more menu screen ");
			logger.info("User is navigated to " + header
					+ " screen post tapping Login/Register link from more menu screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated to Login/Register screen post tapping Login/Register link from more menu screen ");
			logger.info(
					"User is not navigated to Login/Register screen post tapping Login/Register link from more menu screen");
		}

		// Verify user is navigated back to previous screen on clicking back button
		// click back button
		click(AMDGenericObjects.objBackBtn, "Back button");
		if (checkElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLogger("Verify navigation",
					"User is navigate back to the previous screen post tapping back button from Login/Register screen");
			logger.info(
					"User is navigate back to the previous screen post tapping back button from Login/Register screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the previous screen post tapping back button from Login/Register screen");
			logger.info(
					"User is not navigated back to the previous screen post tapping back button from Login/Register screen");
		}

		BuySubscription(userType);

		/*
		 * My Subscription section
		 */
		extent.HeaderChildNode("My Subscription screen validations for user type : " + userType);
		// Verify User is navigated to Login/Register screen post tapping My
		// Subscription
		extent.HeaderChildNode("My Subscription screen");
		verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
		if (header.equals("Login/Register")) {
			extent.extentLogger("Verify navigation",
					"User is navigated to " + header + " screen post tapping My Subscription from more menu screen ");
			logger.info(
					"User is navigated to " + header + " screen post tapping My Subscription from more menu screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated to Login/Register screen post tapping My Subscription from more menu screen ");
			logger.info(
					"User is not navigated to Login/Register screen post tapping My Subscription from more menu screen");
		}

		// Verify user is navigated back to More menu screen on clicking back button
		// click back button
		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		if (checkElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLogger("Verify navigation",
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
		}

		/*
		 * My Transactions section
		 */
		extent.HeaderChildNode("My Transactions screen validations for user type : " + userType);
		// Verify User is navigated to Login/Register screen post tapping My
		// Transactions
		extent.HeaderChildNode("My Transactions screen");
		verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My Transactions");
		if (header.equals("Login/Register")) {
			extent.extentLogger("Verify navigation",
					"User is navigated to " + header + " screen post tapping My Transactions from more menu screen ");
			logger.info(
					"User is navigated to " + header + " screen post tapping My Transactions from more menu screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated to Login/Register screen post tapping My Transactions from more menu screen ");
			logger.info(
					"User is not navigated to Login/Register screen post tapping My Transactions from more menu screen");
		}

		// Verify user is navigated back to More menu screen on clicking back button
		// click back button
		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		if (checkElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLogger("Verify navigation",
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
		}

	}

	/**
	 * Buy subscription section
	 */
	public void BuySubscription(String userType) throws Exception {
		extent.HeaderChildNode("Buy Subscription screen validations for user type :" + userType);
		// Verify User is navigated to subscribe screen post tapping Buy Subscription
		verifyElementPresentAndClick(AMDMoreMenu.objBuySubscription, "Buy Subscription");
		String screenTitle = getText(AMDGenericObjects.objgetScreenTitle);
		if (screenTitle.equals("Subscribe")) {
			extent.extentLoggerPass("Verify Navigation",
					"User is navigated to Subscribe screen post tapping Buy subscription from more menu");
			logger.info("User is navigated to Subscribe screen post tapping Buy subscription from more menu");
		} else {
			extent.extentLoggerFail("Verify Navigation",
					"User is not navigated to Subscribe screen post tapping Buy subscription from more menu");
			logger.info("User is not navigated to Subscribe screen post tapping Buy subscription from more menu");
		}
		// Verify the back button functionality

		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		Swipe("Down", 1);
		if (verifyElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLoggerPass("Verify navigation",
					"User is navigate back to More menu screen post tapping back button from Subscribe screen");
			logger.info("User is navigate back to More menu screen post tapping back button from Subscribe screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the More menu screen post tapping back button from Subscribe screen");
			logger.info(
					"User is not navigated back to the More menu screen post tapping back button from Subscribe screen");
		}
	}

	public void AccountDetailsLoggedInUser(String userType) throws Exception {
		extent.HeaderChildNode("More Menu - Profile validations for user type : " + userType);
		// Verify that Profile details are displayed
		click(AMDMoreMenu.objMoreMenuIcon, "More tab screen");

		// Verify user name is displayed
		boolean userName = verifyIsElementDisplayed(AMDMoreMenu.objUserName);
		if (userName) {
			String profileName = getText(AMDMoreMenu.objUserName);
			extent.extentLoggerPass("Verify profile name is displayed",
					"Profile name for the logged in user is :" + profileName);
			logger.info("Profile name for the logged in user is :" + profileName);
		} else {
			extent.extentLoggerFail("Verify profile name is displayed",
					"Profile name is not displayed for the logged in user");
			logger.info("Profile name is not diaplayed for the logged in user");
		}
		// Verify Email id displayed
		boolean registerLogin = verifyIsElementDisplayed(AMDMoreMenu.objLoginRegisterText);
		if (registerLogin) {
			String EmailID = getText(AMDMoreMenu.objLoginRegisterText);
			extent.extentLoggerPass("Verify Email ID is displayed", "Email ID for the logged in user is :" + EmailID);
			logger.info("Email ID for the logged in user is :" + EmailID);
		} else {
			extent.extentLoggerFail("Verify Email ID is displayed", "Email ID is not displayed for the logged in user");
			logger.info("Emial ID is not diaplayed for the logged in user");
		}
		// click on profile
		verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Profile Icon");
		if (userType.equals("SubscribedUser")) {
			extent.HeaderChildNode("Plan Info unit validations for" + userType);
			// verify plan info unit is displayed
			boolean accessPackDetails = verifyIsElementDisplayed(AMDMyProfileScreen.aobjAccessPackDetailsInfo);
			if (accessPackDetails) {
				extent.extentLoggerPass("Verify access details pack unit",
						"Plan Info unit is displayed for the Subscribed user");
				logger.info("Plan Info unit is displayed for the Subscribed user");

				// Verify Plan Info unit
				String planType = getText(AMDMyProfileScreen.objPlanType);
				String duration = getText(AMDMyProfileScreen.objPlanDuration);
				String price = getText(AMDMyProfileScreen.objPlanPrice);
				String validity = getText(AMDMyProfileScreen.objPackStatus);
				extent.extentLogger("Verify the plan type", "The Paln type :" + planType);
				logger.info("The Paln type :" + planType);
				extent.extentLogger("Verify the plan duration", "The  duration of the Paln  :" + duration);
				logger.info("The  duration of the Paln  : " + duration);
				extent.extentLogger("Verify the plan price", "The Paln price :" + price);
				logger.info("The Paln price :" + price);
				extent.extentLogger("Verify the plan validity", "The Paln validity :" + validity);
				logger.info("The Paln validity :" + validity);

				// Verify Details CTA
				verifyElementPresentAndClick(AMDMyProfileScreen.objDetailsCTA, "Details CTA");
				// Verify user is navigated to My subscription page
				String header = getText(AMDGenericObjects.objgetScreenTitle);
				if (header.equals("My Subscriptions")) {
					extent.extentLoggerPass("Verify Navigation", "User is navigated to  screen : " + header);
					logger.info("User is navigated to  screen : " + header);
				} else {
					extent.extentLoggerFail("Verify Navigation",
							"User is navigated not navigated to My Subscription screen");
					logger.error("User is navigated not navigated to My Subscription screen");
				}

			} else {
				extent.extentLoggerFail("Verify access details pack unit",
						"Plan Info unit is not displayed for the Subscribed user");
				logger.info("Plan Info unit is  not displayed for the Subscribed user");
			}
			if (userType.equals("SubscribedUser")) {
				int PacksName = getDriver().findElements(AMDProfileScreen.objPackName).size();
				if (PacksName == 1) {
					String Pack = getText(AMDProfileScreen.objPackName(1));
					if (Pack.equalsIgnoreCase("Premium Pack")) {
						extent.extentLogger("Pack Name", "Only Premium pack is activated for the logged in user");
						logger.info("Only Premium pack is activated for the logged in user");
					} else {
						extent.extentLogger("Pack Name", "Only RSVOD pack is activated for the logged in user");
						logger.info("Only RSVOD pack is activated for the logged in user");
					}
					Back(1);
				} else {
					for (int i = 1; i <= PacksName; i++) {
						String Pack = getText(AMDProfileScreen.objPackName(i));
						extent.extentLogger("PackName", "Pack Name : " + Pack);
						logger.info("Pack Name : " + Pack);
					}
					Back(1);
					String packNameInAccountDetails = getText(AMDProfileScreen.objSubsPlanNameTxt);
					if (packNameInAccountDetails.contains("Premium Pack")) {
						extent.extentLoggerPass("Priority",
								"Premium pack is displayed over RSVOD pack in Account details screen");
						logger.info("Premium pack is displayed over RSVOD pack in Account details screen");
					} else {
						extent.extentLoggerFail("Priority",
								"Premium pack is not displayed over RSVOD pack in Account details screen");
						logger.info("Premium pack is not displayed over RSVOD pack in Account details screen");
					}
				}
			}
		}
		ChangePasswordScreen();
		MobileAndOTPScreenValidations();
		Back(1);
		Swipe("Down", 1);
		MyProfileScreen(userType);
		EditProfileScreen(userType);
		SocialLoginValidationsForEditProfileScreen(userType);
		Back(1);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDHomePage.objLogout, "Logout");
		verifyElementPresentAndClick(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
		Swipe("DOWN", 1);
		click(AMDMoreMenu.objLoginRegisterText, "Login/Register link");
		if (userType.equals("NonSubscribedUser")) {
			LoginWithEmailID(NonsubscribedUserName, NonsubscribedPassword);
		} else if (userType.equals("SubscribedUser")) {
			LoginWithEmailID(SubscribedUserName, SubscribedPassword);
		}
		click(AMDMoreMenu.objMoreMenuIcon, "More tab screen");
	}

	/**
	 * Change Password screen
	 */
	public void ChangePasswordScreen() throws Exception {
		// Verify Change password screen
		extent.HeaderChildNode("Change Password Screen validations");
		// click(AMDMoreMenu.objLoginRegisterText,"Profile");
		verifyElementPresentAndClick(AMDMyProfileScreen.objChangePassword, "Change Password");
		// Verify the header
		String header = getText(AMDGenericObjects.objgetScreenTitle);
		if (header.equals("Change Password")) {
			extent.extentLoggerPass("Verify Header", "The header of the screen is : " + header);
			logger.info("The header of the screen is : " + header);
		} else {
			extent.extentLoggerFail("Verify Header", "Change Password header is not displayed");
			logger.info("Change Password header is not displayed");
		}
		// verify back button is displayed
		verifyElementPresent(AMDGenericObjects.objBackBtn, "Back button");
		// Verify Current, New and Confirm fields are displayed
		verifyElementPresent(AMDChangePasswordScreen.objCurrentPwdField, "Current Password field");
		verifyElementPresent(AMDChangePasswordScreen.objNewPwdField, "New Password field");
		verifyElementPresent(AMDChangePasswordScreen.objConfirmPwdField, "Confirm Password field");
		// Verify user can enter password in all the text fields
		// Old Password field
		VerifySpecialCharactersInString(AMDChangePasswordScreen.objCurrentPwdField, "1234%23abcd", "Current Password");
		// New Password field
		VerifySpecialCharactersInString(AMDChangePasswordScreen.objNewPwdField, "&*&1234abcd", "New Password");
		// Confirm password field
		VerifySpecialCharactersInString(AMDChangePasswordScreen.objConfirmPwdField, "1234@343%slv", "Confirm Password");
		Back(1);
	}

	/**
	 * My Profile screen validations
	 */

	public void MyProfileScreen(String userType) throws Exception {
		extent.HeaderChildNode("My Profile Screen validations");
		// Verify that profile detail screen is displayed post tapping Profile
		// click on profile
		click(AMDMoreMenu.objLoginRegisterText, "Profile");
		// Verify user is navigated to My Profile screen
		String headerTitle = getText(AMDGenericObjects.objgetScreenTitle);
		if (headerTitle.equals("My Profile")) {
			extent.extentLoggerPass("Verify Navigation",
					"User is navigated to " + headerTitle + " post tapping profile form more menu");
			logger.info("User is navigated to " + headerTitle + " post tapping profile form more menu");
		} else {
			extent.extentLoggerFail("Verify Navigation",
					"User is not navigated to My Profile screen post tapping profile form more menu");
			logger.info("User is not navigated to My Profile screen post tapping profile form more menu");
		}
		// Verify user profile information
		// Verify user name is displayed
		if (verifyElementDisplayed(AMDMyProfileScreen.objUserName)) {
			String profileName = getText(AMDMyProfileScreen.objUserName);
			extent.extentLoggerPass("Verify Profile Name is displayed",
					"Profile Name :" + profileName + " is displayed for the logged in user");
			logger.info("Profile Name :" + profileName + " is displayed for the logged in user");
		} else {
			extent.extentLoggerFail("Verify Profile Name is displayed",
					"Profile Name is not displayed for logged in user");
			logger.info("Profile Name is not displayed for logged in user");
		}
		// Verify user ID is displayed
		if (verifyElementDisplayed(AMDMyProfileScreen.objUserID)) {
			String userID = getText(AMDMyProfileScreen.objUserID);
			extent.extentLoggerPass("Verify User ID is displayed",
					"User ID :" + userID + " is displayed for the logged in user");
			logger.info("User ID :" + userID + " is displayed for the logged in user");
		} else {
			extent.extentLoggerFail("Verify User ID is displayed", "User ID is not displayed for logged in user");
			logger.info("User ID is not displayed for logged in user");
		}
		// Verify Edit CTA button
		if (verifyElementDisplayed(AMDMyProfileScreen.objEditProfileButton)) {
			extent.extentLoggerPass("Verify Edit CTA", "Edit CTA is displayed in My Profile screen");
			logger.info("Edit CTA is displayed in My Profile screen");
		} else {
			extent.extentLoggerFail("Verify Edit CTA", "Edit CTA is not displayed in My Profile screen");
			logger.info("Edit CTA is  not displayed in My Profile screen");
		}
		// Verify user is navigated to Edit profile screen post tapping Edit CTA button
		// Click on Edit CTA
		click(AMDMyProfileScreen.objEditProfileButton, "Edit CTA button");
		// Verify user is navigated to Edit profile screen
		String headerName = getText(AMDGenericObjects.objgetScreenTitle);
		if (headerName.equals("Edit Profile")) {
			extent.extentLoggerPass("Verify User navigation", "User is navigated to " + headerName + " screen");
			logger.info("User is navigated to " + headerName + " screen");
		} else {
			extent.extentLoggerFail("Verify User navigation", "User is not navigated screen");
			logger.info("User is not navigated to Edit Profile screen");
		}
		Back(1);
	}

	public void MobileAndOTPScreenValidations() throws Exception {
		extent.HeaderChildNode("Mobile Screen and OTP validataions");
		// Verify user can see the Country code drop down
		// Click on edit
		verifyElementPresentAndClick(AMDMyProfileScreen.objEditProfileButton, "Edit CTA");
		verifyElementPresent(AMDEditProfileScreen.objMobileNOCountry, "Country drop down");
		// Verify different country code are displayed
		click(AMDEditProfileScreen.objMobileNOCountry, "Country drop down");
		int size = findElements(By.xpath("//*[@id='selector_content']")).size();
		for (int i = 1; i <= size; i++) {
			if (verifyIsElementDisplayed(AMDEditProfileScreen.objDifferentCountries(i))) {
				extent.extentLoggerPass("Verify Country codes",
						"Country code : " + getText(AMDEditProfileScreen.objDifferentCountries(i)) + " is displayed");
				logger.info(
						"Country code :" + getText(AMDEditProfileScreen.objDifferentCountries(i)) + " is displayed");
			} else {
				extent.extentLoggerFail("Verify Country code", "Country code is not displayed in drop down menu");
				logger.info("Country code is not displayed in drop down menu");
			}

		}
		click(AMDEditProfileScreen.objClosedropDownBtn, "Close button");
		hideKeyboard();
		// Verify user can edit the Mobile number field
		String OldNUmber = getText(AMDEditProfileScreen.objMobileNumberField);
		click(AMDEditProfileScreen.objMobileNumberField, "Mobile number field");
		type(AMDEditProfileScreen.objMobileNumberField, "9591340917", "Mobile Number Field");
		hideKeyboard();
		String NewNumber = getText(AMDEditProfileScreen.objMobileNumberField);
		if (!OldNUmber.equals(NewNumber)) {
			extent.extentLoggerPass("Verify Mobile Number field", "User can edit Mobile number field");
			extent.extentLoggerPass("Verify Mobile Number field",
					"Old Mobile number is : " + OldNUmber + " is updated to new number :" + NewNumber);
			logger.info("Old Mobile number is : " + OldNUmber + " is updated to new number :" + NewNumber);
		} else {
			extentLoggerFail("Verify Mobile Number field", "User can not edit the mobile number field");
			logger.info("User can not edit the mobile number field");
		}
		// Set Text
		clearField(AMDEditProfileScreen.objFirstNameField, "FirstName");
		type(AMDEditProfileScreen.objFirstNameField, "FirstName", "First Name");
		hideKeyboard();
		// Verify Save Changes is highlighted
		String saveChanges = getElementPropertyToString("enabled", AMDEditProfileScreen.objSaveChanges,
				"Save Changes CTA");
		if (saveChanges.equals("true")) {
			extent.extentLoggerPass("Verify Save Changes CTA", "Save Changes CTA is Highlighted");
			logger.info("Save Changes CTA is Highlighted");
		} else {
			extent.extentLoggerFail("Verify Save Changes CTA", "Save Changes CTA is not Highlighted");
			logger.info("Save Changes CTA is not Highlighted");
		}
		click(AMDEditProfileScreen.objSaveChanges, "Save Changes CTA");
		// Verify user is navigated to Verify Mobile screen
		String header = getText(AMDGenericObjects.objgetScreenTitle);
		extent.extentLoggerPass("Verify user navigation", "User is navigated to " + header);
		logger.info("User is navigated to " + header);
		// Verify the OTP Validations

		verifyElementExist(AMDRegistrationScreen.objOTPTimer, "OTP timer");
		String OTPTimer1 = getText(AMDRegistrationScreen.objOTPTimer);
		logger.info(OTPTimer1);
		click(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(10000);
		String OTPTimer2 = getText(AMDRegistrationScreen.objOTPTimer);
		logger.info(OTPTimer2);
		boolean Time = OTPTimer1.equals(OTPTimer2);
		if (Time == false) {
			logger.info("The Otp timer is displayed in Verify mobile screen");
			extentLoggerPass("OtpTimer", "The Otp timer is displayed in Verify mobile screen");
		} else {
			logger.info("The Otp timer is not displayed in Verify mobile screen");
			extentLoggerFail("OtpTimer", "The Otp timer is not displayed in Verify mobile screen");
		}
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		waitTime(2000);
		if (findElement(AMDRegistrationScreen.objVerifyOtpButton).isEnabled() == true) {
			logger.info("Verify Button is highlighted");
			extent.extentLoggerPass("Verify", "Verify Button is highlighted");
		}
		Back(1);
	}

	public void VerifySpecialCharactersInString(By by, String pwd, String field) {
		getDriver().findElement(by).sendKeys(pwd);
		String text = getDriver().findElement(by).getText();
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher passWord = p.matcher(text);
		boolean b = passWord.find();
		if (b) {
			System.out.println("There is a special character in my string");
			extent.extentLogger("Verify password field",
					"User can enter Numbers, Special characters and Characters into " + field + " field");
			logger.info("User can enter Numbers, Special characters and Characters into " + field + " Passworrd field");
		} else {
			extent.extentLogger("Verify password field",
					"User can not enter Numbers, Special characters and Characters into " + field + " field");
			logger.info("User can not enter Numbers, Special characters and Characters into" + field + " field");
		}
	}

	/**
	 * Edit Profile screen validations
	 */
	public void EditProfileScreen(String userType) throws Exception {
		extent.HeaderChildNode("Edit Profile screen validations for user type :" + userType);
		verifyElementPresentAndClick(AMDMyProfileScreen.objEditProfileButton, "Edit CTA");
		// Verify header
		verifyElementPresent(AMDEditProfileScreen.objEditProfileTitle, "Edit Profile header");
		// Text field First name
		verifyElementPresent(AMDEditProfileScreen.objFirstNameField, "First Name text field");
		// Text field Last name
		verifyElementPresent(AMDEditProfileScreen.objLastNameField, "Last Name text field");
		// Email text field
		verifyElementPresent(AMDEditProfileScreen.objEmailIDField, "Email text field");
		// Mobile number text field
		verifyElementPresent(AMDEditProfileScreen.objMobileNoField, "Mobile number text field");
		// Date of Birth field
		verifyElementPresent(AMDEditProfileScreen.objDOBTxtField, " DOB text field");
		// Gender field
		verifyElementPresent(AMDEditProfileScreen.objGenderDropdown, "Gender field");
		// Verify Save changes button
		verifyElementPresent(AMDEditProfileScreen.objSaveChanges, "Save Changes button");
		// Verify Save changes button is disabled
		String disabled = getElementPropertyToString("enabled", AMDEditProfileScreen.objSaveChanges, "Save Changes");
		if (disabled.equals("false")) {
			extent.extentLoggerPass("Verify Save Changes is disabled", "Save Changes CTA is disabled");
			logger.info("Save Changes CTA is disabled");
		} else {
			extent.extentLoggerFail("Verify Save Changes is disabled", "Save Changes CTA is enabled");
			logger.info("Save Changes CTA is enabled");
		}
		// Verify First name is displayed
		String firstName = getText(AMDEditProfileScreen.objFirstNameField);
		if (!firstName.equalsIgnoreCase("null")) {
			extent.extentLoggerPass("Verify First name is displayed",
					"First name :" + firstName + " is displayed in the First name text field");
			logger.info("First name :" + firstName + " is displayed in the First name text field");
		} else {
			extent.extentLoggerFail("Verify First name is displayed",
					"First name is not displayed in the First name text field");
			logger.info("First name is not displayed in the First name text field");
		}
		// Verify Last name is displayed for the registered user
		String lastName = getText(AMDEditProfileScreen.objLastNameField);
		if (!lastName.equalsIgnoreCase("null")) {
			extent.extentLoggerPass("Verify Last name is displayed",
					"Last name :" + lastName + " is displayed in the First name text field");
			logger.info("Last name :" + lastName + " is displayed in the Last name text field");
		} else {
			extent.extentLoggerFail("Verify Last name is displayed",
					"Last name is not displayed in the Last name text field");
			logger.info("Last name is not displayed in the Last name text field");
		}
		// Back(1);
		// Verify the back button functionality
		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		String headerTitle = getText(AMDGenericObjects.objgetScreenTitle);
		if (headerTitle.equals("My Profile")) {
			extent.extentLoggerPass("Verify Navigation",
					"User is navigated to " + headerTitle + " post tapping back button from Edit Profile screen");
			logger.info("User is navigated to " + headerTitle + " post tapping back button from Edit Profile screen");
		} else {
			extent.extentLoggerFail("Verify Navigation",
					"User is not navigated to My Profile screen post tapping back button from Edit Profile screen");
			logger.info("User is not navigated to My Profile post tapping back button from Edit Profile screen");
		}
		Back(1);

	}

	/**
	 * Social Login Validations
	 */
	public void SocialLoginValidationsForEditProfileScreen(String userType) throws Exception {
		extent.HeaderChildNode("Social Login Validations for Edit Profile screen");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDHomePage.objLogout, "Logout");
		verifyElementPresentAndClick(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
		Swipe("DOWN", 1);
		click(AMDMoreMenu.objLoginRegisterText, "Login/Register link");
		LoginMethod("SocialLogin");
		click(AMDMoreMenu.objMoreMenuIcon, "More tab screen");
		click(AMDMoreMenu.objLoginRegisterText, "Profile");
		click(AMDMyProfileScreen.objEditProfileButton, "Edit profile");

		// Social Login validations

		VerifyFieldsAreEditable(AMDEditProfileScreen.objFirstNameField, "First Name", "NewFirstName");
		VerifyFieldsAreEditable(AMDEditProfileScreen.objLastNameField, "Last Name", "NewLastName");
		// Verify Email field is in proper format
		String email = getText(AMDEditProfileScreen.objEmailFieldForEmailLogin);
		boolean isEmailValid = emailValidator(email);
		if (isEmailValid) {
			extent.extentLoggerPass("Veridy email", "Email field is in proper format");
			logger.info("Email field is in proper format");

		} else {
			extent.extentLoggerFail("Verify Email", "Email field is not in proper format");
			logger.info("Email field is not in proper format");
		}
		VerifyFieldsAreEditable(AMDEditProfileScreen.objEmailFieldForEmailLogin, "Email ID", "newemail@gmail.com");

		// Verify DOB field is editable

		String OldDate = getText(AMDEditProfileScreen.objDOBTxtField);
		// Verify the given DOB is displayed in DD/MM/YYYY format
		/*
		 * //boolean dateFormat = isValidDateFormat("DD/MM/YYYY",
		 * OldDate,Locale.ENGLISH); if(dateFormat) {
		 * extent.extentLogger("Verify DOB field"
		 * ,"The DOB field is displayed in DD/MM/YYYY format");
		 * logger.info("The DOB field is displayed in DD/MM/YYYY format"); }else {
		 * extent.extentLoggerFail("Verify DOB format"
		 * ,"The DOB field is not displayed in DD/MM/YYYY format");
		 * logger.info("The DOB field is not displayed in DD/MM/YYYY format"); }
		 */
		click(AMDEditProfileScreen.objDOBTxtField, "DOB field");
		// Verify the calendar is displayed on clicking calendar option
		verifyElementPresent(AMDEditProfileScreen.objCalenderPopUp, "Calendar option");

		// Select New date
		type(AMDEditProfileScreen.objYearField, "1950", "Year field");
		click(AMDEditProfileScreen.objOkBtnCalender, "OK button");
		String newDate = getText(AMDEditProfileScreen.objDOBTxtField);
		if (!OldDate.equals(newDate)) {
			extent.extentLoggerPass("Verify DOB field", "DOB field can be editable");
			extent.extentLoggerPass("DOB", "The DOB : " + OldDate + " is updated to new DOB : " + newDate);
			logger.info("DOB field can be editable");
			logger.info("The DOB : " + OldDate + " is updated to new DOB : " + newDate);
		} else {
			extent.extentLoggerFail("Verify DOB field", "DOB field can not be editable");
			logger.info("DOB field can not be editable");
		}
		// Verify the Gender field is editable
		String oldGender = getText(AMDEditProfileScreen.objGederTxtField);
		click(AMDEditProfileScreen.objGederTxtField, "Gender field");
		if (oldGender.equals("Male")) {
			click(AMDEditProfileScreen.objFemale, "Female gender option");
		} else {
			click(AMDEditProfileScreen.objMale, "Male gender option");
		}
		String NewGender = getText(AMDEditProfileScreen.objGederTxtField);
		if (!oldGender.equals(NewGender)) {
			extent.extentLoggerPass("Verify Gender field", "Gender field can be editable");
			extent.extentLoggerPass("Gender", "The gender : " + oldGender + " is updated to new gender : " + NewGender);
			logger.info("Gender field can be editable");
			logger.info("The gender : " + oldGender + " is updated to new gender : " + NewGender);
		} else {
			extent.extentLoggerFail("Verify Gender field", "Gender field can not be editable");
			logger.info("Gender field can not be editable");
		}
		Back(1);
	}

	public void LoginMethod(String loginMethod) throws Exception {

		switch (loginMethod) {
		case "Email":
			extent.HeaderChildNode("Login through Email ID");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, "zee5latest@gmail.com", "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, "User@123", "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;
		case "Mobile":
			extent.HeaderChildNode("Login through registered Mobile number");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, "9880710182", "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, "User@123", "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SocialLogin": // extent.HeaderChildNode("Social Media login - Google account login");
			verifyElementPresentAndClick(AMDLoginScreen.objGoogleBtn, "Gmail icon");
			boolean isAccount = verifyIsElementDisplayed(AMDLoginScreen.objGmailAccount);
			if (isAccount) {
				click(AMDLoginScreen.objGmailAccount, "Gmail Account");
				waitTime(5000);
			} else if (verifyElementPresent(AMDLoginScreen.objGmailSignIn, "Gmail Sign In")) {
				verifyElementPresentAndClick(AMDLoginScreen.objGmailEmailField, "Email Field");
				type(AMDLoginScreen.objGmailEmailField, "zeetest55@gmail.com", "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objGmailNextBtn, "Next Button");
				verifyElementPresentAndClick(AMDLoginScreen.objGmailPasswordField, "Password Field");
				type(AMDLoginScreen.objGmailPasswordField, "zeetest123", "Password Field");
				verifyElementPresentAndClick(AMDLoginScreen.objGmailNextBtn, "Next Button");

				if (verifyIsElementDisplayed(AMDLoginScreen.objGmailAddPhoneNumber)) {
					verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip Button");
				}
				if (verifyIsElementDisplayed(AMDLoginScreen.objAgreeBtn)) {
					click(AMDLoginScreen.objAgreeBtn, "Agree Button");
				}
				if (verifyIsElementDisplayed(AMDLoginScreen.objAcceptBtn)) {
					click(AMDLoginScreen.objAcceptBtn, "Accept Button");
				}
			}
			if (verifyIsElementDisplayed(AMDHomePage.objHome)) {
				logger.info("User logged in successfully");
				extent.extentLogger("Login", "User logged in successfully using Google account");
			}
			break;
		}
	}

	public void VerifyFieldsAreEditable(By by, String fieldType, String text) {
		String oldText = getDriver().findElement(by).getText();
		getDriver().findElement(by).click();
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(text);
		String newText = getDriver().findElement(by).getText();
		if (oldText.equals(newText)) {
			extent.extentLoggerFail("Verify Fields are editable", "The field " + fieldType + " is not editable");
			logger.info("The field " + fieldType + " is not editable");
		} else {
			extent.extentLoggerPass("Verify Fields are editable", "The field " + fieldType + " is editable");
			extent.extentLoggerPass("Verify the fields",
					"The old text is : " + oldText + " updated to New text : " + newText);
			logger.info("The old text is : " + oldText + " updated to New text : " + newText);
			logger.info("The field " + fieldType + " is editable");
		}
	}

	public static boolean emailValidator(String email) {

		// Get an EmailValidator
		EmailValidator validator = EmailValidator.getInstance();
		// Validate specified String containing an email address
		if (!validator.isValid(email)) {
			return false;
		}
		return true;
	}

	public void verifyMySubscription(String userType) throws Exception {
		extent.HeaderChildNode("Verify My Subscription Screen");
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");

		if (verifyIsElementDisplayed(AMDMoreMenu.objMySubscription)) {
			logger.info("My subscription option is available on More menu screen");
			extent.extentLogger("Mysubscription Screen", "My subscription option is available on More menu screen");
		} else {
			logger.info("My subscription option is not available on More menu screen");
			extent.extentLoggerFail("Mysubscription Screen",
					"My subscription option is not available on More menu screen");
		}

		verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
		waitTime(2000);
		if (verifyIsElementDisplayed(AMDMoreMenu.objMySubscriptionsHeader)) {
			logger.info("User navigated to My Subscriptions Page on tapping My Subscriptions");
			extent.extentLogger("MySubscriptions Screen",
					"User navigated to My Subscriptions Page on tapping My Subscriptions");
		} else {
			logger.info("User navigated to My Subscriptions Page on tapping My Subscriptions");
			extent.extentLoggerFail("MySubscriptions Screen",
					"User navigated to My Subscriptions Page on tapping My Subscriptions");
		}

		if (verifyIsElementDisplayed(AMDMoreMenu.objsubscriptionPackStatus)) {
			String SubscriptionPackStatus = getDriver().findElement(AMDMoreMenu.objsubscriptionPackStatus).getText();
			logger.info(
					SubscriptionPackStatus + " : My Subscription pack Status details is displayed for the " + userType);
			extent.extentLogger("MySubscription Screen",
					SubscriptionPackStatus + " : My Subscription pack Status details is displayed for the " + userType);
		} else {

			logger.info(" SubscriptionPackStatus details is not displayed for the " + userType);
			extent.extentLoggerFail("MySubscription Screen",
					"SubscriptionPackStatus details is not displayed for the " + userType);
		}

		verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");
		if (checkElementExist(AMDHomePage.MoreMenuIcon, "More Menu")) {
			logger.info("User navigated to prevoius screen on tapping back icon present in My Subscriptions screen");
			extent.extentLogger("MySubscriptions Screen",
					"User navigated to prevoius screen on tapping back icon present in My Subscriptions screen");
		} else {
			logger.info("User navigated to prevoius screen on tapping back icon present in My Subscriptions screen");
			extent.extentLoggerFail("MySubscriptions Screen",
					"User navigated to prevoius screen on tapping back icon present in My Subscriptions screen");
		}

		extent.HeaderChildNode("verify My Subscription pack details");
		verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
		String SubscriptionPackPrice = getDriver().findElement(AMDMoreMenu.objSubscriptionPackPrice).getText();

		if (checkElementExist(AMDMoreMenu.objSubscriptionPackPrice, "My subscription Pack Price")) {
			logger.info(SubscriptionPackPrice + " : My Subscription pack price is displayed for the " + userType);
			extent.extentLogger("MySubscription Screen",
					SubscriptionPackPrice + " : My Subscription pack price is displayed for the " + userType);
		} else {

			logger.info(SubscriptionPackPrice + " : My Subscription pack price is displayed for the " + userType);
			extent.extentLoggerFail("MySubscription Screen",
					SubscriptionPackPrice + " : My Subscription pack price is displayed for the " + userType);
		}

		String SubscriptionPackDuration = getDriver().findElement(AMDMoreMenu.objSubscriptionPackDuration).getText();

		if (checkElementExist(AMDMoreMenu.objSubscriptionPackDuration, "My subscription Pack Duration")) {
			logger.info(SubscriptionPackDuration + " : My Subscription pack duration is displayed for the " + userType);
			extent.extentLogger("MySubscription Screen",
					SubscriptionPackDuration + " : My Subscription pack duration is displayed for the " + userType);
		} else {

			logger.info(SubscriptionPackDuration + " : My Subscription pack duration is displayed for the " + userType);
			extent.extentLoggerFail("MySubscription Screen",
					SubscriptionPackDuration + " : My Subscription pack duration is displayed for the " + userType);
		}

		Swipe("Up", 1);

		String SubscriptionPackExpiryDate = getDriver().findElement(AMDMoreMenu.objSubscriptionPackExpiryDate)
				.getText();

		if (checkElementExist(AMDMoreMenu.objSubscriptionPackExpiryDate, "My subscription Pack Expiry Date")) {
			logger.info(SubscriptionPackExpiryDate + " : My Subscription pack expiry date is displayed for the "
					+ userType);
			extent.extentLogger("MySubscription Screen",
					SubscriptionPackExpiryDate + " : My Subscription pack expiry date is displayed for the" + userType);
		} else {

			logger.info(SubscriptionPackExpiryDate + " : My Subscription pack expiry date is displayed for the "
					+ userType);
			extent.extentLoggerFail("MySubscription Screen", SubscriptionPackExpiryDate
					+ " : My Subscription pack expiry date is displayed for the " + userType);
		}

		Swipe("DOWN", 1);

		String SubscriptionPackCountryDetails = getDriver().findElement(AMDMoreMenu.objSubscriptionPackCountry)
				.getText();

		if (checkElementExist(AMDMoreMenu.objSubscriptionPackCountry, "My subscription Pack Country")) {
			logger.info(SubscriptionPackCountryDetails + " : My Subscription pack Country details is displayed for the "
					+ userType);
			extent.extentLogger("MySubscription Screen", SubscriptionPackCountryDetails
					+ " : My Subscription pack Country details is displayed for the " + userType);
		} else {

			logger.info(SubscriptionPackCountryDetails + " : My Subscription pack Country details is displayed for the "
					+ userType);
			extent.extentLoggerFail("MySubscription Screen", SubscriptionPackCountryDetails
					+ " : My Subscription pack Country details is displayed for the " + userType);
		}

		String SubscriptionPackPaymentMode = getDriver().findElement(AMDMoreMenu.objSubscriptionPackPaymentMode)
				.getText();

		if (checkElementExist(AMDMoreMenu.objSubscriptionPackPaymentMode, "My subscription Pack Payment Mode")) {
			logger.info(SubscriptionPackPaymentMode + " : My Subscription pack payment mode is displayed for the "
					+ userType);
			extent.extentLogger("MySubscription Screen", SubscriptionPackPaymentMode
					+ " : My Subscription pack payment mode is displayed for the " + userType);
		} else {

			logger.info(SubscriptionPackPaymentMode + " : My Subscription pack payment mode is displayed for the "
					+ userType);
			extent.extentLoggerFail("MySubscription Screen", SubscriptionPackPaymentMode
					+ " : My Subscription pack payment mode is displayed for the " + userType);
		}

		String SubscriptionPackOfferings = getDriver().findElement(AMDMoreMenu.objSubscriptionPackOfferings).getText();

		if (checkElementExist(AMDMoreMenu.objSubscriptionPackOfferings, "My subscription pack Offerings")) {
			logger.info(
					SubscriptionPackOfferings + " : My Subscription pack offerings is displayed for the " + userType);
			extent.extentLogger("MySubscription Screen",
					SubscriptionPackOfferings + " : My Subscription pack offerings is displayed for the " + userType);
		} else {

			logger.info(
					SubscriptionPackOfferings + " : My Subscription pack offerings is displayed for the" + userType);
			extent.extentLoggerFail("MySubscription Screen",
					SubscriptionPackOfferings + " : My Subscription pack offerings is displayed for the" + userType);
		}
		Back(2);
		verifySubscribeToMultipleAccessPacks(userType);
	}

	@SuppressWarnings("unused")
	public void verifySubscribeToMultipleAccessPacks(String userType) throws Exception {

		if (userType.equalsIgnoreCase("SubscribedUser")) {
			ZNALogoutMethod();

			click(AMDHomePage.objMoreMenu, "More Menu");
			click(AMDMoreMenu.objProfile, "Profile");
			waitTime(2000);

			String SubscriptionPackwithMultipleAccessPaclsUsername = Reporter.getCurrentTestResult().getTestContext()
					.getCurrentXmlTest().getParameter("SubscriptionPackwithMultipleAccessPaclsUsername");
			String SubscriptionPackwithMultipleAccessPaclsPassword = Reporter.getCurrentTestResult().getTestContext()
					.getCurrentXmlTest().getParameter("SubscriptionPackwithMultipleAccessPaclsPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

			LoginWithEmailID(SubscriptionPackwithMultipleAccessPaclsUsername,
					SubscriptionPackwithMultipleAccessPaclsPassword);
			waitTime(2000);
			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
			waitTime(2000);
			verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
			waitTime(4000);
		}

		extent.HeaderChildNode("Verify user can able to see the packs by Swiping card left to right");
		String width = getAttributValue("width", AMDMoreMenu.objMySubscriptionPack);
		String bounds = getAttributValue("bounds", AMDMoreMenu.objMySubscriptionPack);
		String b = bounds.replaceAll(",", " ").replaceAll("]", " ");
		String height = b.split(" ")[1];

		Swipe("LEFT", 1);
		// carouselCardsSwipe("LEFT", 1, width, height);
		// carouselSwipe("Right", 1, width, height);

		waitTime(2000);
		Swipe("UP", 1);
		verifyElementExist(AMDMoreMenu.objsubscriptionPackDotBelowTheCard, "Dot below the Subscription Pack Card");

		String getPropertyValue = getAttributValue("enabled", AMDMoreMenu.objsubscriptionPackDotBelowTheCard);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			logger.info(userType
					+ " User can able to swipe the card, dot below the card is moved as per the position of the card");
			extent.extentLogger("MySubscription Screen", userType
					+ " User can able to swipe the card, dot below the card is moved as per the position of the card");
		} else {
			logger.info(userType
					+ " User cannot able to swipe the card, dot below the card is not moved as per the position of the card");
			extent.extentLoggerFail("MySubscription Screen", userType
					+ " User cannot able to swipe the card, dot below the card is not moved as per the position of the card");
		}

		Swipe("RIGHT", 1);
		// carouselCardsSwipe("Right", 1, width, height);
		// carouselSwipe("Left", 1, width, height);
		Swipe("UP", 1);
		String getPropertyValue1 = getAttributValue("enabled", AMDMoreMenu.objsubscriptionPackDotBelowTheSecondCard);
		if (getPropertyValue1.equalsIgnoreCase("true")) {
			logger.info(userType
					+ " User can able to swipe the card in reverse direction, dot below the card is moved as per the position of the card");
			extent.extentLogger("MySubscription Screen", userType
					+ " User can able to swipe the card in reverse direction, dot below the card is moved as per the position of the card");
		} else {
			logger.info(userType
					+ " User cannot able to swipe the card in reverse direction, dot below the card is not moved as per the position of the card");
			extent.extentLoggerFail("MySubscription Screen", userType
					+ " User cannot able to swipe the card in reverse direction, dot below the card is not moved as per the position of the card");
		}
		Swipe("LEFT", 1);
		Swipe("UP", 1);

		extent.HeaderChildNode("Verify Cancel Renewal CTA is available");
		if (checkElementExist(AMDMoreMenu.objSubscriptionPackCancelRenewal, "Cancel Renewal CTA")) {
			logger.info("Cancel Renewal CTA is available based on the pack Selection");
			extent.extentLogger("MySubscription Screen", "Cancel Renewal CTA is available based on the pack Selection");
		} else {

			logger.info("Cancel Renewal CTA is not available based on the pack Selection");
			extent.extentLogger("MySubscription Screen",
					"Cancel Renewal CTA is not available based on the pack Selection");
		}

		extent.HeaderChildNode("Verify Browse All Packs CTA is displayed on the Screen");
		if (checkElementExist(AMDMoreMenu.objBrowseAllPacks, "Browse All Packs CTA")) {
			logger.info("Browse All Packs CTA is present in the bottom of the Screen");
			extent.extentLogger("MySubscription Screen", "Browse All Packs CTA is present in the bottom of the Screen");
		} else {

			logger.info("Browse All Packs CTA is present in the bottom of the Screen");
			extent.extentLogger("MySubscription Screen", "Browse All Packs CTA is present in the bottom of the Screen");
		}
		Back(2);
	}

	/**
	 * My Subscription screen validations
	 */
	public void MySubscriptionValidations(String userType) throws Exception {

		extent.HeaderChildNode("My Subscriptions screen validations for user type :" + userType);
		// Verify User is navigated to Login/Register screen post tapping My
		// Subscription
		verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
		String header = getText(AMDGenericObjects.objgetScreenTitle);
		if (header.equals("My Subscriptions")) {
			extent.extentLogger("Verify navigation",
					"User is navigated to " + header + " screen post tapping My Subscription from more menu screen ");
			logger.info(
					"User is navigated to " + header + " screen post tapping My Subscription from more menu screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated to My Subscriptions screen post tapping My Subscription from more menu screen ");
			logger.info(
					"User is not navigated to My Subscriptions screen post tapping My Subscription from more menu screen");
		}
		// Verify Subscribe now and No Active subscription is displayed
		if (verifyIsElementDisplayed(AMDMoreMenu.objNoActivePlans)) {
			String NoactivePlans = getText(AMDMoreMenu.objNoActivePlans);
			extent.extentLogger("Verify No active Subscription",
					"The message : " + NoactivePlans + " is displayed in My Subscription screen");
			logger.info("The message : " + NoactivePlans + " is displayed in My Subscription screen");
		} else {
			extent.extentLoggerFail("Verify No active Subscription",
					"The message No Active Subscription is not displayed in My Subscription screen");
			logger.info("The message No Active Subscription is not displayed in My Subscription screen");
		}
		if (verifyIsElementDisplayed(AMDMoreMenu.objSubscribeNowCTA)) {
			extent.extentLogger("Verify No active Subscription",
					"Subscribe Now CTA is displayed in My Subscription screen");
			logger.info("Subscribe Now CTA is displayed in My Subscription screen");
		} else {
			extent.extentLoggerFail("Verify No active Subscription",
					"Subscribe Now CTA is not displayed in My Subscription screen");
			logger.info("Subscribe Now CTA is not displayed in My Subscription screen");
		}
		// Verify User is navigated to subscribe screen post tapping Subscribe Now CTA
		verifyElementPresentAndClick(AMDMoreMenu.objSubscribeNowCTA, "Subscribe Now CTA");
		String screenTitle = getText(AMDGenericObjects.objgetScreenTitle);
		if (screenTitle.equals("Subscribe")) {
			extent.extentLogger("Verify Navigation",
					"User is navigated to Subscribe screen post tapping Subscribe Now CTA from My Subscription screen");
			logger.info(
					"User is navigated to Subscribe screen post tapping Subscribe Now CTA from My Subscription screen");
		} else {
			extent.extentLoggerFail("Verify Navigation",
					"User is not navigated to Subscribe screen post tapping Subscribe Now CTA from My Subscription screen");
			logger.info(
					"User is not navigated to Subscribe screen post tapping Subscribe Now CTA from My Subscription screen");
		}
		Back(1);
		// Verify user is navigated back to More menu screen on clicking back button
		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		if (checkElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLogger("Verify navigation",
					"User is navigate back to the More menu screen post tapping back button from My Subscription screen");
			logger.info(
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the More menu screen post tapping back button from My Subscription screen");
			logger.info(
					"User is not navigated back to the More menu screen post tapping back button from My Subscription screen");
		}
	}

	/**
	 * Author : Manasa
	 */
	public void ZeeApplicasterLoginForSettings(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Login Functionality");
		String UserType = getParameterFromXML("userType");
		if (UserType.equals("Guest")) {
			extent.extentLogger("userType", "UserType : Guest");
		}
		verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
		waitTime(3000);

		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitTime(1000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip link");
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User for Settings");
			String SUsername = getParameterFromXML("SettingsNonsubscribedUserName");
			String SPassword = getParameterFromXML("SettingsNonsubscribedPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User for Settings");
			String SettingsSubscribedUsername = getParameterFromXML("SettingsSubscribedUserName");
			String SettingsSubscribedPassword = getParameterFromXML("SettingsSubscribedPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SettingsSubscribedUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SettingsSubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;
		}
	}

	/**
	 * Author : Manasa
	 */
	public void verifyPlaybackAfterMinimzeAndMaximizeAppFromBackground() throws Exception {
		extent.HeaderChildNode("Validation of content playback after minimize and maximize app from background");
		System.out.println("\nValidation of content playback after minimize and maximize app from background");

		String time1 = getText(AMDPlayerScreen.objTimer);
		int startTime = timeToSec(time1);

		logger.info("Time before minimizing app from background : " + startTime);
		extentLogger("Time", "Time before minimizing app from background : " + startTime);

		getDriver().runAppInBackground(Duration.ofSeconds(10));

		waitTime(5000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		String time2 = getText(AMDPlayerScreen.objTimer);
		int elapsedTime = timeToSec(time2);

		logger.info("Time after maximizing app from background : " + elapsedTime);
		extentLogger("Time", "Time after maximizing app from background : " + elapsedTime);

		if (elapsedTime > startTime) {
			logger.info("Content playback is resumed after maximizing the app from background");
			extentLoggerPass("Elapsed time", "Content playback is resumed on maximizing the app from background");
		} else {
			logger.error("Content playback is not resumed after maximizing the app from background");
			extentLoggerFail("Time", "Content playback is resumed on maximizing the app from background");
		}
	}

	public void verifyPlaybackAfterLockAndUnlock() throws Exception {
		extent.HeaderChildNode("Validation of content playback after lock and unlocking the device screen");
		System.out.println("\nValidation of content playback after lock and unlocking the device screen");

		String time1 = getText(AMDPlayerScreen.objTimer);
		int startTime = timeToSec(time1);

		logger.info("Time before locking the device screen : " + startTime);
		extentLogger("Time", "Time before locking the device screen : " + startTime);
		adbKeyevents(26);
		waitTime(7000);
		adbKeyevents(26);
		waitTime(3000);
		Swipe("Up", 1);
		waitTime(5000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		String time2 = getText(AMDPlayerScreen.objTimer);
		int elapsedTime = timeToSec(time2);

		logger.info("Time after unlocking the device screen : " + elapsedTime);
		extentLogger("Time", "Time before unlocking the device screen : " + elapsedTime);

		if (elapsedTime > startTime) {
			logger.info("Content playback is resumed after unlocking the device screen");
			extentLoggerPass("Elapsed time", "Content playback is resumed after unlocking the device screen");
		} else {
			logger.error("Content playback is not resumed after unlocking the device screen");
			extentLoggerFail("Elapsed time", "Content playback is not resumed after unlocking the device screen");
		}
	}

	/**
	 * @param keyevent pass the android key event value to perform specific action
	 * 
	 */
	public void adbKeyevents(int keyevent) {

		try {
			String cmd = "adb shell input keyevent" + " " + keyevent;
			Runtime.getRuntime().exec(cmd);
			logger.info("Performed the Keyevent" + keyevent);
			extent.extentLogger("adbKeyevent", "Performed the Keyevent" + keyevent);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void premiumContentwithTrailer(String userType, String searchKeyword) throws Exception {
		extent.HeaderChildNode("verifing Get Premium popup at the end of the non premium trailer content playback");
		System.out.println("\nverifing Get Premium popup at the end of the non premium trailer content playback");

		click(AMDSearchScreen.objSearchBoxBar, "Search Box");
		clearField(AMDSearchScreen.objSearchBoxBar, "Search box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword + "\n", "Search bar");
		hideKeyboard();
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(3000);
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		waitTime(1000);
		if (checkElementExist(AMDPlayerScreen.objPlayerScreen, "Player screen")) {
			waitTime(5000);
			if (verifyElementExist(AMDPlayerScreen.objSubtitleOptionInPotraitMode, "Subtitle option")) {
				String defaultValue = getText(AMDPlayerScreen.objSubtitleValueInPotraitMode);
				if (defaultValue.equalsIgnoreCase("Off")) {
					logger.info("By default Subttle is in Off state");
					extentLogger("Subtitles", "By default Subttle is in Off state");
					click(AMDPlayerScreen.objSubtitleValueInPotraitMode, "Subtitle option");
					click(AMDPlayerScreen.objEnglishSubtitle, "English subtitle");
					String Value = getText(AMDPlayerScreen.objSubtitleValueInPotraitMode);
					if (Value.equalsIgnoreCase("en")) {
						logger.info("English subtitle is selected");
						extentLoggerPass("Subtitles", "English subtitle is selected");
					} else {
						logger.info("English subtitle is not selected");
						extentLoggerFail("Subtitles", "English subtitle is not selected");
					}
				}
			}
			if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
				waitTime(5000);
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
				waitTime(1000);
				click(AMDPlayerScreen.objPauseIcon, "Pause icon");
				seekVideoTillLast(AMDPlayerScreen.objProgressBar);
				click(AMDPlayerScreen.objPlayIcon, "Play icon");
				verifyElementExist(AMDPlayerScreen.objGetPremiumPopUp,
						"Get Premium popup at the end of the non premium trailer content playback");
				if (userType.equalsIgnoreCase("Guest")) {
					Swipe("UP", 1);
					verifyElementExist(AMDPlayerScreen.objLoginCTA, "Login CTA");
				}
				waitTime(2000);
				Back(1);
			}
			waitTime(6000);
			Back(1);
		} else {
			logger.info("Player screen is not displayed");
			extentLoggerFail("Player screen", "Player screen is not displayed");
		}
		// To Navigate back to from Consumption screen
		if (verifyIsElementDisplayed(AMDConsumptionScreen.objShareBtn)) {
			Back(1);
		}
	}

	public void premiumContentWithoutTrailer(String userType, String searchKeyword) throws Exception {
		System.out.println("\nPremium content without Trailer");

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verifing Premium content without Trailer");

//			click(AMDHomePage.HomeIcon, "Home");
			waitTime(2000);
			click(AMDSearchScreen.objSearchBoxBar, "Search Box");
			clearField(AMDSearchScreen.objSearchBoxBar, "Search box");

			type(AMDSearchScreen.objSearchBoxBar, searchKeyword + "\n", "Search bar");
			hideKeyboard();
			// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(3000);
			if (userType.equalsIgnoreCase("NonSubscribedUser")) {
				verifyElementExist(AMDPlayerScreen.objSubscribeNowButtonOnPlayer, "Subscribe CTA");
			} else if (userType.equalsIgnoreCase("Guest")) {
				verifyElementExist(AMDPlayerScreen.objPremiumTextOnPlayer, "Subscription required text");
				verifyElementPresentAndClick(AMDPlayerScreen.objSubscribeNowLinkOnPlayer, "Subscribe Now Link");
				if (verifyElementExist(AMDPlayerScreen.objGetPremiumPopUp, "Get Premium popUp")) {
					logger.info("User is navigated to Get premium popup post tapping on Subscribe Now Link");
					extentLoggerPass("GetPremium popUp",
							"User is navigated to Get premium popup post tapping on Subscribe Now Link");
				} else {
					logger.info("User is not navigated to Get premium popup post tapping on Subscribe Now Link");
					extentLoggerFail("GetPremium popUp",
							"User is not navigated to Get premium popup post tapping on Subscribe Now Link");
				}
				Back(1);
				verifyElementExist(AMDPlayerScreen.objLoginTextOnPlayer, "Login required text");
				verifyElementPresentAndClick(AMDPlayerScreen.objLoginLinkOnPlayer, "Login link");
				if (verifyElementExist(AMDLoginScreen.objLoginOrRegisterPageTitle, "Login/Register screen")) {
					logger.info("User is navigated to Login/Register screen post tapping on Login link");
					extentLoggerPass("Login/Register screen",
							"User is navigated to Login/Register screen post tapping on Login link");
				} else {
					logger.info("User is navigated to Login/Register screen post tapping on Login link");
					extentLoggerFail("Login/Register screen",
							"User is navigated to Login/Register screen post tapping on Login link");
				}
				Back(1);
			}
			waitTime(5000);
			Back(1);
		}
		// To Navigate back to from Consumption screen
		if (verifyIsElementDisplayed(AMDConsumptionScreen.objShareBtn)) {
			Back(1);
		}
	}

	public void premiumContentWithoutTrailerInLandscapeMode(String userType, String searchKeyword5) throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verifying Premium content without Trailer in Landscape mode");
			System.out.println("\nVerifying Premium content without Trailer in Landscape mode");
			verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, searchKeyword5 + "\n", "Search bar");
			waitTime(2000);
			hideKeyboard();
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDMoreMenu.objSearchResult(searchKeyword5), "Search result");
			waitTime(2000);
			verifyElementPresent(AMDPlayerScreen.objSubscribeButtonBelowThePlayer, "Subscribe CTA");
			switchtoLandscapeMode();

			waitTime(3000);

			verifyElementPresent(AMDPlayerScreen.objPremiumTextOnPlayer, "Subscription required text");
			verifyElementPresentAndClick(AMDPlayerScreen.objSubscribeNowLinkOnPlayer, "Subscribe Now Link");
			if (verifyElementExist(AMDPlayerScreen.objGetPremiumPopUp, "Get Premium popUp")) {
				logger.info("User is navigated to Get premium popup post tapping on Subscribe Now Link");
				extentLoggerPass("GetPremium popUp",
						"User is navigated to Get premium popup post tapping on Subscribe Now Link");
			} else {
				logger.error("User is not navigated to Get premium popup post tapping on Subscribe Now Link");
				extentLoggerFail("GetPremium popUp",
						"User is not navigated to Get premium popup post tapping on Subscribe Now Link");
			}
			Back(1);
			if (userType.equals("Guest")) {
				verifyElementExist(AMDPlayerScreen.objLoginTextOnPlayer, "Login required text");
				verifyElementPresentAndClick(AMDPlayerScreen.objLoginLinkOnPlayer, "Login link");
				if (verifyElementExist(AMDLoginScreen.objLoginOrRegisterPageTitle, "Login/Register screen")) {
					logger.info("User is navigated to Login/Register screen post tapping on Login link");
					extentLoggerPass("Login/Register screen",
							"User is navigated to Login/Register screen post tapping on Login link");
				} else {
					logger.error("User is navigated to Login/Register screen post tapping on Login link");
					extentLoggerFail("Login/Register screen",
							"User is navigated to Login/Register screen post tapping on Login link");
				}
				click(AMDLoginScreen.objLoginLnk, "Skip link");
			}
			switchtoPortraitMode();
			waitTime(2000);
			Back(3);
		}
	}

	public void subtitleAndPlaybackRateValidation(String searchKeyword4, String userType) throws Exception {
		extent.HeaderChildNode("Validation of Subtitle option and Playback Rate");
		System.out.println("\nValidation of Subtitle option and Playback Rate");
		waitTime(5000);

		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword4 + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDMoreMenu.objSearchResult(searchKeyword4), "Search result");
		waitTime(2000);
		if (!userType.contains("SubscribedUser")) {
			waitTime(5000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);
			LoadingInProgress();
			adPlay();
			waitTime(5000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		click(AMDPlayerScreen.objFullscreenIcon, "Maximize Icon");
		waitTime(1000);
		verifyElementPresent(AMDPlayerScreen.objNextIcon, "Next icon");
		click(AMDPlayerScreen.objThreeDotsOnPlayer, "Three dots option");
		verifyElementPresentAndClick(AMDPlayerScreen.objSubtitleOption, "Subtitle option");
		String defaultSelected = getText(AMDPlayerScreen.objSubtitleDefaultSelected);
		if (defaultSelected.equalsIgnoreCase("Off")) {
			logger.info("Subtitle is set to " + defaultSelected + " by default");
			extent.extentLoggerPass("Subtitle", "Subtitle is set to " + defaultSelected + " by default");

		} else {
			logger.error("Subtitle is not set to off by default");
			extent.extentLoggerFail("Subtitle", "Subtitle is not set to off by default");
		}

		verifyElementPresentAndClick(AMDPlayerScreen.objEnglishSubtitle, "English subtitle language");
		waitTime(2000);
		extent.HeaderChildNode("Playback Rate Validation");

		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		String time1 = getText(AMDPlayerScreen.objTimer);
		int startTime = timeToSec(time1);

		logger.info("Time before increasing the Playback rate : " + startTime + " sec");
		extentLogger("Time", "Time before increasing the Playback rate : " + startTime + " sec");

		// The following wait methods will is used to capture the elapsed Time after
		// waiting for 10 Sec playback
		waitTime(5000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		click(AMDPlayerScreen.objThreeDotsOnPlayer, "Three dots option");
		click(AMDPlayerScreen.objPlaybackRate, "Playback Rate option");
		click(AMDPlayerScreen.objPlaybackRate2, "Playback Rate 2.0X option");
		waitTime(5000);
		int playbackTimeinSec = startTime + 10;

		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		String time2 = getText(AMDPlayerScreen.objTimer);
		int elapsedTime = timeToSec(time2);

		logger.info("Time after increasing the Playback rate : " + elapsedTime + " sec");
		extentLogger("Time", "Time after increasing the Playback rate : " + elapsedTime + " sec");

		if (elapsedTime != playbackTimeinSec) {
			logger.info("Content playback Rate is fast forwarded based on the speed set");
			extentLoggerPass("Elapsed time", "Content playback Rate is fast forwarded based on the speed set");
		} else {
			logger.info("Content playback Rate is NOT fast forwarded based on the speed set");
			extentLoggerFail("Elapsed time", "Content playback Rate is NOT fast forwarded based on the speed set");
		}

		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		seekVideoTillLast(AMDPlayerScreen.objProgressBar);
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			waitForElementDisplayed(AMDPlayerScreen.objGetPremiumPopUp, 30);
			verifyElementPresent(AMDPlayerScreen.objGetPremiumPopUp,
					"Get Premium popup along with Login CTA at the end of the non premium trailer content playback");
			Back(1);
		}
		Back(3);
	}

	/**
	 * Author : Sushma
	 * 
	 * @param searchKeyword
	 * @throws Exception
	 */
	public void skipIntroValidationInPotraitMode(String searchKeyword, String usertype) throws Exception {
		extent.HeaderChildNode("Validation of Skip Intro CTA");
		System.out.println("\nValidation of Skip Intro CTA");
		waitTime(5000);

		click(AMDSearchScreen.objSearchBoxBar, "Search Box");
		clearField(AMDSearchScreen.objSearchBoxBar, "Search box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}

		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 10);
//		waitTime(7000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		waitTime(1000);
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(2000);
		String time1 = getText(AMDPlayerScreen.objTimer);
		int startTime = timeToSec(time1);
		logger.info("Time before clicking on Skip Intro CTA : " + startTime);
		extentLogger("Time", "Time before clicking on Skip Intro CTA : " + startTime);
		click(AMDPlayerScreen.objPlayIcon, "Play icon");
		boolean var = verifyIsElementDisplayed(AMDPlayerScreen.objSkipIntro, "Skip Intro CTA");
		if (var == true) {
			waitTime(2000);
			click(AMDPlayerScreen.objSkipIntro, "Skip Intro CTA");
			waitTime(5000);
			click(AMDPlayerScreen.objPlayerScreen, "Play screen");

			String time2 = getText(AMDPlayerScreen.objTimer);
			int elapsedTime = timeToSec(time2);

			logger.info("Time after clicking on Skip Intro CTA : " + elapsedTime + " sec");
			extentLogger("Time", "Time after clicking on Skip Intro CTA : " + elapsedTime + " sec");

			if (elapsedTime > startTime) {
				logger.info("Introduction playback of the content is skipped");
				extentLoggerPass("Elapsed time", "Introduction playback of the content is skipped");
			} else {
				logger.error("Introduction playback of the content is not skipped");
				extentLoggerFail("Elapsed time", "Introduction playback of the content is not skipped");
			}
		} else {
			logger.info("Introduction playback of the content is already skipped");
			extentLogger("Elapsed time", "Introduction playback of the content is already skipped");
		}
		Back(1);
	}

	public void completeProfilePopUpClose(String userType) throws Exception {

		waitTime(5000);
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(AMDPlayerScreen.objCompleteProfilePopUp)) {
				logger.info("Complete Profile Pop Up is displayed");
				extent.extentLogger("Complete Profile Pop Up", "Complete Profile Pop Up is displayed");
				Back(1);
			}
		}
	}

	/**
	 * Author : Manasa
	 * 
	 * @param searchKeyword3
	 * @throws Exception
	 */
	public void skipIntroValidationInLandscapeMode(String searchKeyword3, String userType) throws Exception {
		extent.HeaderChildNode("Validation of Skip Intro CTA In Landscape Mode");
		System.out.println("Validation of Skip Intro CTA In Landscape Mode");
		waitTime(5000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword3 + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDMoreMenu.objSearchResult(searchKeyword3), "Search result");
		waitTime(5000);
		if (!userType.contains("SubscribedUser")) {
			registerPopUpClose();
			completeProfilePopUpClose(userType);
			LoadingInProgress();
			adPlay();
			waitTime(5000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(2000);
		click(AMDPlayerScreen.objFullscreenIcon, "Maximize Icon");

		String time1 = getText(AMDPlayerScreen.objTimer);
		int startTime = timeToSec(time1);

		logger.info("Time before clicking on Skip Intro CTA : " + startTime + " sec");
		extentLogger("Time", "Time before clicking on Skip Intro CTA : " + startTime + " sec");

		boolean var = verifyIsElementDisplayed(AMDPlayerScreen.objSkipIntro, "Skip Intro CTA");
		if (var == true) {
			waitTime(2000);
		     click(AMDPlayerScreen.objSkipIntro, "Skip Intro CTA");
			waitTime(3000);
			click(AMDPlayerScreen.objPlayIcon, "Play icon");

			String time2 = getText(AMDPlayerScreen.objTimer);
			int elapsedTime = timeToSec(time2);

			logger.info("Time after clicking on Skip Intro CTA : " + elapsedTime + " sec");
			extentLogger("Time", "Time after clicking on Skip Intro CTA : " + elapsedTime + " sec");

			if (elapsedTime > startTime) {
				logger.info("Introduction playback of the content is skipped");
				extentLoggerPass("Elapsed time", "Introduction playback of the content is skipped");
			} else {
				logger.error("Introduction playback of the content is not skipped");
				extentLoggerFail("Elapsed time", "Introduction playback of the content is not skipped");
			}
		} else {
			logger.info("Introduction playback of the content is already skipped");
			extentLogger("Elapsed time", "Introduction playback of the content is already skipped");
		}

		Back(2);
		if (verifyIsElementDisplayed(AMDMoreMenu.objSearchResult(searchKeyword3))) {
			logger.info(
					"User is navigated back to the previous screen from where the content is accessed post tapping on Back button in Player screen");
			extentLoggerPass("Back button",
					"User is navigated back to the previous screen from where the content is accessed post tapping on Back button in Player screen");
		} else {
			logger.error(
					"User is not navigated back to the previous screen from where the content is accessed post tapping on Back button in Player screen");
			extentLoggerFail("Back button",
					"User is not navigated back to the previous screen from where the content is accessed post tapping on Back button in Player screen");
		}
		Back(1);
	}

	/**
	 * Author : Vinay Module : More screen Screen : My Subscription
	 */

	public void MySubscription(String userType) throws Exception {

		if (userType.equals("Guest")) {
			MySubscriptionGuestUser(userType);
		} else if (userType.equals("SubscribedUser")) {
			verifyMySubscription(userType);
		} else if (userType.equals("NonSubscribedUser")) {
			MySubscriptionValidations(userType);
		}
	}

	/**
	 * My Subscription section
	 */

	public void MySubscriptionGuestUser(String userType) throws Exception {
		extent.HeaderChildNode("My Subscription screen validations for user type : " + userType);
		// Verify User is navigated to Login/Register screen post tapping My
		// Subscription
		extent.HeaderChildNode("My Subscription screen");
		verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
		String header = getText(AMDGenericObjects.objgetScreenTitle);
		if (header.equals("Login/Register")) {
			extent.extentLoggerPass("Verify navigation",
					"User is navigated to " + header + " screen post tapping My Subscription from more menu screen ");
			logger.info(
					"User is navigated to " + header + " screen post tapping My Subscription from more menu screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated to Login/Register screen post tapping My Subscription from more menu screen ");
			logger.info(
					"User is not navigated to Login/Register screen post tapping My Subscription from more menu screen");
		}

		// Verify user is navigated back to More menu screen on clicking back button
		// click back button
		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		if (checkElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLoggerPass("Verify navigation",
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
		}
	}

	/**
	 * Author : Vinay Module : More screen
	 * 
	 * @param userType
	 * @throws Exception
	 */
	public void MyTransactions(String userType) throws Exception {
		if (userType.equals("Guest")) {
			MyTransactionsGuestUser(userType);
		} else if (userType.equals("NonSubscribedUser") | userType.equals("SubscribedUser")) {
			verifyMyTransactions(userType);
		}
	}

	public void MyTransactionsGuestUser(String userType) throws Exception {

		extent.HeaderChildNode("My Transactions screen validations for user type : " + userType);
		// Verify User is navigated to Login/Register screen post tapping My
		// Transactions

		verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My Transactions");
		String header = getText(AMDGenericObjects.objgetScreenTitle);
		if (header.equals("Login/Register")) {
			extent.extentLoggerPass("Verify navigation",
					"User is navigated to " + header + " screen post tapping My Transactions from more menu screen ");
			logger.info(
					"User is navigated to " + header + " screen post tapping My Transactions from more menu screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated to Login/Register screen post tapping My Transactions from more menu screen ");
			logger.info(
					"User is not navigated to Login/Register screen post tapping My Transactions from more menu screen");
		}

		// Verify user is navigated back to More menu screen on clicking back button
		// click back button
		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		if (checkElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLoggerPass("Verify navigation",
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
		}
	}

	/**
	 * Author : Sushma
	 * 
	 * @param keyword
	 * @param userType
	 * @throws Exception
	 */
	public void NextIconAndReplayIcon(String keyword, String userType) throws Exception {
		extent.HeaderChildNode("Validation of NextIcon");
		waitTime(3000);
		// Back(1);
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");

		String elementAutoPlayToggleStatus = getText(AMDMoreMenu.objVideo_Autoply);
		if (elementAutoPlayToggleStatus.equalsIgnoreCase("ON")) {
			click(AMDMoreMenu.objVideo_Autoply, "Auto play");
		}
		Back(1);
		waitTime(3000);
		Back(1);
		waitTime(3000);

		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		verifyElementPresentAndClick(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(5000);
		if (userType.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
			waitTime(5000);
		}

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			if (verifyIsElementDisplayed(AMDPlayerScreen.objAd)) {
				System.out.println("ifff");
				verifyElementNotPresent(AMDPlayerScreen.objAd, 180);
			} else {
				System.out.println("elsee");
			}
		}

		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 10);
		waitTime(5000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		String contentTitle1 = getText(AMDPlayerScreen.objContentTitle);
		verifyElementPresent(AMDPlayerScreen.objNextIcon, "Next icon");
		seekVideoTillLast(AMDPlayerScreen.objProgressBar);
		click(AMDPlayerScreen.objPlayIcon, "Play icon");

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			verifyElementExist(AMDPlayerScreen.objGetPremiumPopUp, "Get Premium popup");
			Back(1);
		}

		waitTime(2000);
		verifyElementPresentAndClick(AMDPlayerScreen.objReplayIconOnPlayer, "Replay icon");
		waitTime(3000);
		String contentTitle2 = getText(AMDPlayerScreen.objContentTitle);
		if (contentTitle1.equalsIgnoreCase(contentTitle2)) {
			logger.info("same content playback is started again by tapping on Replay icon");
			extentLoggerPass("Replay icon", "same content playback is started again by tapping on Replay icon");
		} else {
			logger.info("same content playback is not started again by tapping on Replay icon");
			extentLoggerFail("Replay icon", "same content playback is not started again by tapping on Replay icon");
		}
	}

	public void registerPopUpClose() throws Exception {
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDPlayerScreen.objRegisterPopUp)) {
			logger.info("Register Pop Up is displayed");
			extent.extentLogger("Register Pop Up", "Register Pop Up is displayed");
			Back(1);
		}
	}

	public void upnextRailValidationInLandscapeMode(String searchKeyword8) throws Exception {
		extent.HeaderChildNode("Validation of Upnext Rail in Landscape Mode");
		System.out.println("\nValidation of Upnext Rail in Landscape Mode");
		waitTime(5000);

		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword8 + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDMoreMenu.objSearchResult(searchKeyword8), "Search result");

		if (!pUserType.contains("SubscribedUser")) {
			waitTime(5000);
			LoadingInProgress();
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		waitTime(1000);
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		click(AMDPlayerScreen.objFullscreenIcon, "Maximize Icon");
		waitTime(2000);
		swipeByElements(findElement(AMDPlayerScreen.objUpnextContentCard), findElement(AMDPlayerScreen.objPlayIcon));
		waitTime(3000);

		verifyElementPresent(AMDPlayerScreen.objUpnextRail, "Upnext Rail below Progress Bar");

		String upnextCardTitle = getText(AMDPlayerScreen.objUpnextContentCardTitle);
		logger.info("Upnext Card Title : " + upnextCardTitle);
		extent.extentLogger("Upnext", "Upnext Card Title : " + upnextCardTitle);

		verifyElementPresentAndClick(AMDPlayerScreen.objUpnextContentCard, "Upnext Content card");

		String contentTitle = getText(AMDPlayerScreen.objContentTitle);
		logger.info("Upnext Card Title : " + contentTitle);
		extent.extentLogger("Upnext", "Upnext Card Title : " + contentTitle);

		if (contentTitle.contains(upnextCardTitle)) {
			logger.info("Jumped to next content post tapping on Upnext Card");
			extent.extentLoggerPass("Upnext", "Jumped to next content post tapping on Upnext Card");
		} else {
			logger.error("Did not jump to next content post tapping on Upnext Card");
			extent.extentLoggerFail("Upnext", "Did not jump to next content post tapping on Upnext Card");
		}
		Back(2);
	}

	/**
	 * Author : Hitesh Module : BeforeTV
	 * 
	 * @throws Exception
	 */
	public void ValidateBeforeTV(String userType) throws Exception {
		System.out.println("Validating BeforeTv Tray");
		HeaderChildNode("Validating BeforeTv Tray");
		if (ResponseInstance.BeforeTV(userType, "Home")) {
			waitTime(5000);
			waitForElementDisplayed(AMDGenericObjects.objNoOfTrays, 150);
			findTray(AMDHomePage.objBeforeTVTray);
			click(AMDHomePage.objBeforeTVViewAllArraowIcon, "View All icon");
			waitTime(2000);
			if (verifyIsElementDisplayed(AMDHomePage.objViewAllScreen)) {
				logger.info("View All screen is displayed");
				extent.extentLoggerPass("ViewAll", "View All screen is displayed");
				Back(1);
				findTray(AMDHomePage.objBeforeTVTray);
				click(AMDHomePage.objFirstContentOfBeforeTvTray, "First content");
				waitTime(2000);
				if (verifyIsElementDisplayed(AMDConsumptionScreen.objconfirmationPopUp)) {
					click(AMDConsumptionScreen.objOkBtn, "Ok button in add this device to yoour list pop up");
				}
				verifyElementExist(AMDConsumptionScreen.objDownloadbtn, "Download icon");
				if (!userType.equals("SubscribedUser")) {
					if (verifyElementExist(AMDConsumptionScreen.getClubCTA, "Get Club CTA")) {
						click(AMDConsumptionScreen.getClubCTA, "Get Club CTA");
						verifyElementExist(AMDConsumptionScreen.objPopUpSubscribed, "Subscribe PopUp");
						waitTime(2000);
						if (userType.equals("Guest")) {
							Swipe("UP", 1);
							verifyElementExist(AMDConsumptionScreen.objLoginCTA, "Login CTA");
							verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
							waitTime(2000);
							if (verifyIsElementDisplayed(AMDLoginScreen.objLoginOrRegisterPageTitle, "LoginPage")) {
								logger.info("Navigate to login/register screen");
								extent.extentLoggerPass("Login Screen", "Navigate to login/register screen");
							} else {
								logger.error("Not Navigate to login/register screen");
								extent.extentLoggerFail("Login screen", "Not Navigate to login/register screen");
							}
						}
					}
				}
			} else {
				logger.error("View All screen is not displayed");
				extent.extentLoggerFail("ViewAll", "View All screen is not displayed");
			}
		}
	}

	public void findTray(By byLocator) {
		for (int i = 0; i < 10; i++) {
			if (!(verifyIsElementDisplayed(byLocator))) {
				Swipe("UP", 1);
			} else {
				PartialSwipe("UP", 1);
				logger.info("Before Tv tray is located");
				extent.extentLoggerPass("Tray", "Before Tv tray is located");
				break;
			}
		}
	}

	public void waitForAdToFinishInAmd() {
		waitTime(20000);
		if (verifyIsElementDisplayed(AMDPlayerScreen.objAd2)) {
			logger.info("Ad is playing");
			extentLogger("Ad", "Ad is playing");
			verifyElementNotPresent(AMDPlayerScreen.objAd2, 200);
			logger.info("Ad is completed");
			extentLogger("Ad", "Ad is completed");
		} else {
			logger.info("Ad is not played");
			extentLogger("Ad", "Ad is not played");
		}
	}

	/**
	 * Author : Bindu Module : club pack
	 */
	public void ClubPackValidation(String userType, String searchcontent, String SearchVODContent4) throws Exception {
		extent.HeaderChildNode("Club Pack Upgrade validation");
		System.out.println("\nClub Pack Upgrade validation");

		if (userType.equals("SubscribedUser")) {
			verifyElementExist(AMDClubPack.objupgradeIcon, "Upgrade button on left side of header section");
			verifyElementExist(AMDClubPack.objCrownIcon, "Upgrade button with Crown Icon");
			click(AMDClubPack.objupgradeIcon, "Upgrade Icon");
			waitTime(4000);
			if (verifyIsElementDisplayed(AMDClubPack.objBuySubscriptionScreen)) {
				logger.info("User is navigated to Buy scubscription screen");
				extent.extentLoggerPass("Buy Scubscription screen", "User is navigated to Buy subscription screen");
			} else {
				logger.error("User fails to navigate to Buy subscription screen");
				extent.extentLoggerFail("Buy Scubscription screen",
						"User fails to navigate to Buy subscription screen");
			}
			Back(1);
			waitTime(2000);
			if (verifyIsElementDisplayed(AMDClubPack.objUpgradeCTAOnCarousel)) {
				click(AMDClubPack.objUpgradeCTAOnCarousel, "Upgrade CTA on carousel Banner");
			} else {
				waitForElementDisplayed(AMDClubPack.objUpgradeCTAOnCarousel, 3);
				click(AMDClubPack.objUpgradeCTAOnCarousel, "Upgrade CTA on carousel Banner");
			}
			waitTime(3000);
			if (verifyIsElementDisplayed(AMDClubPack.objBuySubscriptionScreen)) {
				logger.info("User is navigated to Buy scubscription screen on tapping Upgrade CTA on carousel banner");
				extent.extentLoggerPass("Buy Scubscription screen",
						"User is navigated to Buy scubscription screen on tapping Upgrade CTA on carousel banner");
			} else {
				logger.error(
						"User fails to navigate to Buy subscription screen on tapping Upgrade CTA on carousel banner");
				extent.extentLoggerFail("Buy Scubscription screen",
						"User fails to navigate to Buy subscription screen on tapping Upgrade CTA on carousel banner");
			}
			Back(1);
			verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
			waitTime(2000);
			verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBar, searchcontent, "Search bar");
			waitTime(2000);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Searched result");
			waitTime(5000);
			verifyElementExist(AMDClubPack.objUpgradeCTABelowPlayer, "Upgrade CTA below the player");
			if (verifyIsElementDisplayed(AMDClubPack.objYouneedpremiumtextonPlayer)) {
				String text = getText(AMDClubPack.objYouneedpremiumtextonPlayer);
				System.out.println(text);
				logger.info(text + " is displayed on player with Upgrade CTA");
				extent.extentLoggerPass("Consumption screen", text + " is displayed on player with Upgrade CTA");
			} else {
				logger.error(getText(AMDClubPack.objYouneedpremiumtextonPlayer)
						+ " is NOT displayed on player with Upgrade CTA");
				extent.extentLoggerFail("Consumption screen", getText(AMDClubPack.objYouneedpremiumtextonPlayer)
						+ " is NOT displayed on player with Upgrade CTA");
			}
			click(AMDClubPack.objUpgradeCTABelowPlayer, "Upgrade CTA below the player");
			ValidateUIOfUpgradePopup();
			Back(2);
			click(AMDHomePage.HomeIcon, "Home Icon");
			UpgradepopupForPremiumcontentWithTrailer(SearchVODContent4);
		} else {
			logger.info("Club Upgrade is not applicable for " + userType);
			extent.extentLogger("Club Upgrade", "Club Upgrade is not applicable for " + userType);
		}
	}

	public void LoginWithClubUser() throws Exception {
		waitTime(2000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		click(AMDMoreMenu.objProfileHeader, "profile header");
		String Username = getParameterFromXML("ClubUserName");
		String Password = getParameterFromXML("ClubPassword");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
		type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
		type(AMDLoginScreen.objPasswordField, Password, "Password field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
		waitTime(3000);
		verifyElementPresentAndClick(AMDHomePage.objHome, "Home tab");
	}

	public void ValidateUIOfUpgradePopup() throws Exception {
		extent.HeaderChildNode("Validating Upgrade popup");
		System.out.println("Validating Upgrade popup ");
		if (verifyIsElementDisplayed(AMDClubPack.objUpgradepopuptitle)) {
			String title = getText(AMDClubPack.objUpgradepopuptitle);
			logger.info("Title : " + title + " is displayed");
			extent.extentLoggerPass("Upgrade popup", "Title : " + title + " is displayed");
		} else {
			logger.error("Title : " + getText(AMDClubPack.objUpgradepopuptitle) + " is NOT displayed");
			extent.extentLoggerFail("Upgrade pop up",
					"Title : " + getText(AMDClubPack.objUpgradepopuptitle) + " is NOT displayed");
		}
		String Plan1 = getText(AMDClubPack.objplan1);
		System.out.println(Plan1);
		logger.info("Plan 1 " + Plan1 + " is displayed");
		extent.extentLogger("Upgrade popup", "Plan 1 " + Plan1 + " is displayed");

		String Plan2 = getText(AMDClubPack.objplan2);
		System.out.println(Plan2);
		logger.info("Plan 2 " + Plan2 + " is displayed");
		extent.extentLogger("Upgrade popup", "Plan 2 " + Plan2 + " is displayed");

		if (findElement(AMDClubPack.objplan2).isEnabled()) {
			logger.info(Plan2 + " is highlighted by default");
			extent.extentLoggerPass("Upgrade popup", Plan2 + " is highlighted by default");
		} else {
			logger.error(Plan2 + " is NOT highlighted by default");
			extent.extentLoggerFail("Upgrade popup", Plan2 + " is NOT highlighted by default");
		}

		verifyElementExist(AMDClubPack.objProceedbutton, "Proceed button");
		Swipe("UP", 2);
		verifyElementExist(AMDClubPack.objPremiumPlanDescinUpgradepopup, "Premium pack note");
		verifyElementExist(AMDClubPack.objClubpackDescinupgradepopup, "Club pack note");
		SwipeUntilFindElement(AMDClubPack.objTermsofuseinUpgradepopup, "UP");
		verifyElementExist(AMDClubPack.objTermsofuseinUpgradepopup, "Terms of Use");
		verifyElementExist(AMDClubPack.objprivacypolicyinUpgradePopup, "Privacy Policy");
	}

	public void ClubPack(String userType, String SearchVODContent, String ClubContent) throws Exception {
		if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
			verifyClubPack(userType, SearchVODContent);
			verifyClubIconOnAllSeaerchScreenTabs(ClubContent, SearchVODContent);
			verifyAllAccessAndClubPacks();
		} else if (userType.equals("SubscribedUser")) {
			verifyClubPackAccountInfoScreen(userType);
		}
	}

	public void verifyAllAccessAndClubPacks() throws Exception {
		extent.HeaderChildNode("verify Two kinds of packs offered for the user");
		System.out.println("\nVerify Two kinds of packs offreed for the user");
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objBuySubscription, "Buy Subscription option");
		waitTime(6000);
		Swipe("UP", 1);
		if (verifyElementPresent(AMDClubPage.objPremiumTab, "Premium tab")) {
			extent.extentLoggerPass("verify Premium Tab", "Premium Tab is displayed under Buy Subscription Screen");
			logger.info("Premium Tab is displayed under Buy Subscription Screen");
		} else {
			extent.extentLoggerFail("verify Premium Tab", "Premium Tab is not displayed under Buy Subscription Screen");
			logger.info("Premium Tab is not displayed under Buy Subscription Screen");
		}

		if (verifyIsElementDisplayed(AMDClubPage.objAllAccessPack)) {
			String PremiumPlan = getText(AMDClubPage.objAllAccessPack);
			logger.info(PremiumPlan + " : Premium Plan is displayed in Premium Tab");
			extent.extentLoggerPass("Premium Plan", PremiumPlan + " : Premium Plan is displayed in Premium Tab");
		} else {
			logger.info("Premium Plan is not displayed in Premium Tab");
			extent.extentLoggerFail("Premium Plan", "Premium Plan is not displayed in Premium Tab");
		}

		click(AMDClubPage.objClubTab, "Club tab");
		if (verifyElementPresent(AMDClubPage.objClubTab, "Club tab")) {
			extent.extentLoggerPass("verify Club Tab", "Club Tab is displayed under Buy Subscription Screen");
			logger.info("Club Tab is displayed under Buy Subscription Screen");
		} else {
			extent.extentLoggerFail("verify club Tab", "Club Tab is not displayed under Buy Subscription Screen");
			logger.info("Club Tab is not displayed under Buy Subscription Screen");
		}

		if (verifyIsElementDisplayed(AMDClubPage.objClubPack)) {
			String ClubPack = getText(AMDClubPage.objClubPack);
			logger.info(ClubPack + " : Club Plan is displayed in Club Tab");
			extent.extentLoggerPass("Club Plan", ClubPack + " : Club Plan is displayed in Club Tab");
		} else {
			extent.extentLoggerFail("Club Plan", "ClubPlan is not displayed in Club Tab");
			logger.info("ClubPlan is not displayed in Club Tab");
		}
		Back(1);
		verifyElementPresentAndClick(AMDSearchScreen.objHomeOption, "Bottom bar home option");
	}

	public void verifyClubPack(String userType, String SearchVODContent) throws Exception {
		extent.HeaderChildNode("Verify club icon Content Card");
		System.out.println("\nVerify club icon Content Card");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
		waitTime(2000);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, SearchVODContent + "\n", "Search bar");
		hideKeyboard();
		waitTime(2000);

		boolean SearchedContent = verifyIsElementDisplayed(AMDGenericObjects.objSearchcontentTitle("Ammana Mane"));
		if (SearchedContent) {
			String Content = getText(AMDGenericObjects.objSearchcontentTitle("Ammana Mane"));
			logger.info(Content);
			boolean clubIcon = verifyIsElementDisplayed(AMDClubPage.objClubIcon);
			if (clubIcon) {
				extent.extentLoggerPass("Club Icon", "Club icon is displayed on club VOD Content card");
				logger.info("Club icon is displayed on club VOD Content card");
			} else {
				extent.extentLoggerFail("Club Icon", "Club icon is not displayed on club VOD Content card");
				logger.info("Club icon is not displayed on club VOD Content card");
			}
		}
		click(AMDClubPage.objClubIcon, "Club Content");
		waitTime(2000);
		if (userType.equalsIgnoreCase("Guest")) {

			verifyElementPresentAndClick(AMDNewsPage.objDownlaodOption, "download Option");
			String Loginscreenheader = getText(AMDGenericObjects.objgetScreenTitle);
			if (Loginscreenheader.equals("Login/Register")) {
				extent.extentLoggerPass("Download Option",
						userType + " user is navigated to " + Loginscreenheader + " screen on tapping download option");
				logger.info(
						userType + " user is navigated to " + Loginscreenheader + " screen on tapping Download Option");
			} else {
				extent.extentLoggerFail("Download Option",
						"Failed to navigate into respective screen after clicking Download Option");
				logger.info("Failed to navigate into respective screen after clicking Download Option");
			}
			waitTime(4000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");

			SwipeUntilFindElement(AMDNewsPage.objWatchlistIcon, "DOWN");
			Back(1);
			click(AMDClubPage.objClubIcon, "Club Content");
			waitTime(2000);
			verifyElementPresentAndClick(AMDNewsPage.objWatchlistIcon, "WatchList Option");

			String header = getText(AMDGenericObjects.objgetScreenTitle);
			if (header.equals("Login/Register")) {
				extent.extentLoggerPass("Watchlist",
						userType + " user is navigated to " + header + " screen on tapping Watchlist option");
				logger.info(userType + " user is navigated to " + header + " screen on tapping Watchlist option");
			} else {
				extent.extentLoggerFail("Watchlist",
						"Failed to navigate into respective screen after clicking Watchlist");
				logger.info("Failed to navigate into respective screen after clicking Watchlist");
			}
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			verifyElementPresentAndClick(AMDNewsPage.objDownlaodOption, "download Option");
			waitTime(5000);
			boolean DownloadVideoQualityPopup = verifyIsElementDisplayed(AMDClubPage.objDownloadVideoQualityPopup);
			if (DownloadVideoQualityPopup) {
				String DownloadQualityPopup = getText(AMDClubPage.objDownloadVideoQualityPopup);
				logger.info(userType + " user is navigated to " + DownloadQualityPopup
						+ " screen on tapping Download Option");
				extent.extentLoggerPass("Download Option", userType + " user is navigated to " + DownloadQualityPopup
						+ " screen on tapping download option");
			} else {
				extent.extentLoggerFail("Download Option",
						"Failed to navigate into respective screen after clicking Download Option");
				logger.info("Failed to navigate into respective screen after clicking Download Option");
			}
		}
		Back(2);
	}

	public void verifyClubIconOnAllSeaerchScreenTabs(String ClubContent, String SearchVODContent) throws Exception {
		extent.HeaderChildNode("verify Club Icon on all search screen tabs");
		System.out.println("\nVerify Club Icon on all search screen tabs");
		type(AMDSearchScreen.objSearchBoxBar, ClubContent + "\n", "Search bar");
		hideKeyboard();
		waitTime(10000);

		boolean AllTab = verifyIsElementDisplayed(AMDSearchScreen.objAllTab);
		if (AllTab) {
			boolean SearchResults = verifyIsElementDisplayed(AMDSearchScreen.objSearchedClubContent);
			if (SearchResults) {
				String SearchedContent = getText(AMDSearchScreen.objSearchedClubContent);

				boolean clubIcon = verifyIsElementDisplayed(AMDClubPage.objClubIcon);
				if (clubIcon) {
					extent.extentLoggerPass("Club Icon",
							"Club icon is displayed in All Tab for the Searched content : " + SearchedContent + " ");
					logger.info("Club icon is displayed in All Tab for the Searched content : " + SearchedContent + "");
				} else {
					extent.extentLoggerFail("Club Icon",
							"Club icon is not displayed in All Tab for the Searched content");
					logger.info("Club icon is not displayed in All Tab for the Searched content");
				}
			}
		}
		boolean EpisodesTab = verifyIsElementDisplayed(AMDSearchScreen.objEpsiodesTab);
		if (EpisodesTab) {
			click(AMDSearchScreen.objEpsiodesTab, "Episodes Tab");
			boolean SearchResults = verifyIsElementDisplayed(AMDSearchScreen.objSearchedClubContent);
			if (SearchResults) {
				String SearchedContent = getText(AMDSearchScreen.objSearchedClubContent);

				boolean clubIcon = verifyIsElementDisplayed(AMDClubPage.objClubIcon);
				if (clubIcon) {
					extent.extentLoggerPass("Club Icon",
							"Club icon is displayed in Episodes Tab for the Searched content : " + SearchedContent
									+ " ");
					logger.info("Club icon is displayed in Episodes Tab for the Searched content : " + SearchedContent
							+ " ");
				} else {
					extent.extentLoggerFail("Club Icon",
							"Club icon is not displayed in Episodes Tab for the Searched content");
					logger.info("Club icon is not displayed in Episodes Tab for the Searched content");
				}

			}
		}

		boolean showsTab = verifyIsElementDisplayed(AMDSearchScreen.objSearchShowsTab);
		if (showsTab) {
			click(AMDSearchScreen.objSearchShowsTab, "Shows Tab");
			boolean SearchResults = verifyIsElementDisplayed(AMDSearchScreen.objSearchedClubContent);
			if (SearchResults) {
				String SearchedContent = getText(AMDSearchScreen.objSearchedClubContent);

				boolean clubIcon = verifyIsElementDisplayed(AMDClubPage.objClubIcon);
				if (clubIcon) {
					extent.extentLoggerPass("Club Icon",
							"Club icon is displayed in Shows Tab for the Searched content : " + SearchedContent + " ");
					logger.info(
							"Club icon is displayed in Shows Tab for the Searched content : " + SearchedContent + " ");
				} else {
					extent.extentLoggerFail("Club Icon",
							"Club icon is not displayed in Shows Tab for the Searched content");
					logger.info("Club icon is not displayed in Shows Tab for the Searched content");
				}
			}
		}
		verifyElementPresentAndClick(AMDSearchScreen.objClearSearch, "Clear Search Field");
		type(AMDSearchScreen.objSearchBoxBar, SearchVODContent + "\n", "Search bar");
		hideKeyboard();
		boolean MoviesTab = verifyIsElementDisplayed(AMDSearchScreen.objSearchMoviesTab);
		if (MoviesTab) {
			click(AMDSearchScreen.objSearchMoviesTab, "Movies Tab");
			boolean SearchResults = verifyIsElementDisplayed(AMDSearchScreen.objSearchedClubContent);
			if (SearchResults) {
				String SearchedContent = getText(AMDSearchScreen.objSearchedClubContent);

				boolean clubIcon = verifyIsElementDisplayed(AMDClubPage.objClubIcon);
				if (clubIcon) {
					extent.extentLoggerPass("Club Icon",
							"Club icon is displayed in Movies Tab for the Searched content : " + SearchedContent + " ");
					logger.info(
							"Club icon is displayed in Movies Tab for the Searched content : " + SearchedContent + " ");
				} else {
					extent.extentLoggerFail("Club Icon",
							"Club icon is not displayed in Movies Tab for the Searched content");
					logger.info("Club icon is not displayed in Movies Tab for the Searched content");
				}
			}
		}
		verifyElementPresentAndClick(AMDSearchScreen.objClearSearch, "Clear Search Field");
	}

	public void verifyTwokindsOfPacks() throws Exception {
		extent.HeaderChildNode("verify Two kinds of packs offered for the user");
		System.out.println("\nVerify Two kinds of packs offreed for the user");
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objBuySubscription, "Buy Subscription option");
		waitTime(6000);
		Swipe("UP", 1);
		if (verifyElementPresent(AMDClubPage.objPremiumTab, "Premium tab")) {
			extent.extentLoggerPass("verify Premium Tab", "Premium Tab is displayed under Buy Subscription Screen");
			logger.info("Premium Tab is displayed under Buy Subscription Screen");
		} else {
			extent.extentLoggerFail("verify Premium Tab", "Premium Tab is not displayed under Buy Subscription Screen");
			logger.info("Premium Tab is not displayed under Buy Subscription Screen");
		}

		if (verifyIsElementDisplayed(AMDClubPage.objAllAccessPack)) {
			String PremiumPlan = getText(AMDClubPage.objAllAccessPack);
			logger.info(PremiumPlan + " : Premium Plan is displayed in Premium Tab");
			extent.extentLoggerPass("Premium Plan", PremiumPlan + " : Premium Plan is displayed in Premium Tab");
		} else {
			logger.info("Premium Plan is not displayed in Premium Tab");
			extent.extentLoggerFail("Premium Plan", "Premium Plan is not displayed in Premium Tab");
		}

		click(AMDClubPage.objClubTab, "Club tab");
		if (verifyElementPresent(AMDClubPage.objClubTab, "Club tab")) {
			extent.extentLoggerPass("verify Club Tab", "Club Tab is displayed under Buy Subscription Screen");
			logger.info("Club Tab is displayed under Buy Subscription Screen");
		} else {
			extent.extentLoggerFail("verify club Tab", "Club Tab is not displayed under Buy Subscription Screen");
			logger.info("Club Tab is not displayed under Buy Subscription Screen");
		}

		if (verifyIsElementDisplayed(AMDClubPage.objClubPack)) {
			String ClubPack = getText(AMDClubPage.objClubPack);
			logger.info(ClubPack + " : Club Plan is displayed in Club Tab");
			extent.extentLoggerPass("Club Plan", ClubPack + " : Club Plan is displayed in Club Tab");
		} else {
			extent.extentLoggerFail("Club Plan", "ClubPlan is not displayed in Club Tab");
			logger.info("ClubPlan is not displayed in Club Tab");
		}
		Back(1);
	}

	public void verifyClubPackAccountInfoScreen(String userType) throws Exception {
		extent.HeaderChildNode("verify Account Info screen for Club Pack");
		System.out.println("\nVerify Account Info screen for Club Pack");

		click(AMDMoreMenu.objMoreMenuIcon, "More tab screen");
		// ###### Kushal #####
		int getWaiverAmt = GetWaiverAmountForClubPackUpdgrade();

		verifyElementPresentAndClick(AMDMoreMenu.objBuySubscription, "Buy Subscription");
		waitTime(3000);
		SwipeUntilFindElement(AMDClubPage.objContinueButton, "UP");
		verifyElementPresentAndClick(AMDClubPage.objContinueButton, "Continue Button");

		waitTime(5000);
		Swipe("DOWN", 1);
		String[] getPlanPrice = getText(AMDSubscibeScreen.objPlanPrice).replace("INR ", "").trim().split("\\.");
		String getTotalPayable = getText(AMDSubscibeScreen.objTotalPayable).replace("INR ", "").trim();

		int newPlanPrice = Integer.parseInt(getPlanPrice[0]);
		int TotalPayableAmount = (newPlanPrice - getWaiverAmt);
		int PayableAmountUI = Integer.parseInt(getTotalPayable);

		if (PayableAmountUI == TotalPayableAmount) {
			logger.info("Calculated Total Payable amount and displayed amount in UI is correct with Waiver of INR "
					+ getWaiverAmt);
			extent.extentLoggerPass("Total Payable Amount",
					"Calculated Total Payable amount and displayed amount in UI is correct with Waiver of INR "
							+ getWaiverAmt);
		} else {
			logger.error("Calculated Total Payable amount " + TotalPayableAmount + " and displayed amount "
					+ PayableAmountUI + "in UI is incorrect with Waiver of INR " + getWaiverAmt);
			extent.extentLoggerFail("Total Payable Amount",
					"Calculated Total Payable amount " + TotalPayableAmount + " and displayed amount " + PayableAmountUI
							+ "in UI is incorrect with Waiver of INR " + getWaiverAmt);
		}

		waitTime(2000);
		Swipe("DOWN", 2);
		verifyElementPresent(AMDLoginScreen.objBackBtn, "Back Button in Account Info Screen");
		verifyElementPresent(AMDClubPage.objSelectedPackSection, "Selected pack Section in Account Info Screen");
		PartialSwipe("UP", 1);
		verifyElementPresent(AMDClubPage.objAccountInfoSection, "Account Info Section in Account Info Screen");
		PartialSwipe("UP", 1);
		verifyElementPresent(AMDClubPage.objPaymentOptionsSection, "Payment Option Section in Account Info Screen");
		// PartialSwipe("UP",1);
		verifyElementPresent(AMDClubPage.objAccountInfoScreenContinueButton, "Continue Button in Account Info Screen");

		extent.HeaderChildNode("verify Selected Pack section Info for Club Pack");
		System.out.println("\nVerify Selected Pack section Info for Club Pack");
		// SwipeUntilFindElement(AMDClubPage.objSelectedPackName, "DOWN");
		Swipe("DOWN", 2);
		if (verifyElementDisplayed(AMDClubPage.objSelectedPackName)) {
			String SelectedPackName = getText(AMDClubPage.objSelectedPackName);
			extent.extentLoggerPass("Verify Selected Pack Name",
					"Selected Pack Name \"" + SelectedPackName + "\" : is displayed in Account Info");
			logger.info("Selected Pack Name \"" + SelectedPackName + "\" : is displayed in Account Info");
		} else {
			extent.extentLoggerFail("Verify Selected Pack Name",
					"Selected Pack name is not displayed in Account Info screen");
			logger.info("Selected Pack name is not displayed in Account Info screen");
		}

		if (verifyElementDisplayed(AMDClubPage.objSelectedPackName)) {
			String SelectedPackValidity = getText(AMDClubPage.objSelectedPackValidity);
			extent.extentLoggerPass("Verify Selected Pack validity",
					"Selected Pack validity \"" + SelectedPackValidity + "\" : is displayed in Account Info");
			logger.info("Selected Pack validity \"" + SelectedPackValidity + "\" : is displayed in Account Info");
		} else {
			extent.extentLoggerFail("Verify Selected Pack validity",
					"Selected Pack validity is not displayed in Account Info screen");
			logger.info("Selected Pack validity is not displayed in Account Info screen");
		}

		if (verifyElementDisplayed(AMDClubPage.objPlanPriceINR)) {
			String SelectedPlanPriceINR = getText(AMDClubPage.objPlanPriceINR);
			extent.extentLoggerPass("Verify Selected Pack Price INR",
					"Selected Plan Price INR \"" + SelectedPlanPriceINR + "\" : is displayed in Account Info");
			logger.info("Selected Pack Price INR \"" + SelectedPlanPriceINR + "\" : is displayed in Account Info");
		} else {
			extent.extentLoggerFail("Verify Selected Pack Price INR",
					"Selected Plan Price INR is not displayed in Account Info screen");
			logger.info("Selected Plan Price INR is not displayed in Account Info screen");
		}

		if (verifyElementDisplayed(AMDClubPage.objDiscountPlanINR)) {
			String DiscountPlanINR = getText(AMDClubPage.objDiscountPlanINR);
			extent.extentLoggerPass("Verify Discount INR",
					"Discount INR \"" + DiscountPlanINR + "\" : is displayed in Account Info");
			logger.info("Discount INR \"" + DiscountPlanINR + "\" : is displayed in Account Info");
		} else {
			extent.extentLoggerFail("Verify Discount INR", "Discount INR is not displayed in Account Info screen");
			logger.info("Discount INR is not displayed in Account Info screen");
		}

		if (verifyElementDisplayed(AMDClubPage.objRoundOffValue)) {
			String RoundOffValue = getText(AMDClubPage.objRoundOffValue);
			extent.extentLoggerPass("Verify Round Off",
					"Round Off \"" + RoundOffValue + "\" : is displayed in Account Info");
			logger.info("Round Off \"" + RoundOffValue + "\" : is displayed in Account Info");
		} else {
			extent.extentLoggerFail("Verify Round Off", "Discount INR is not displayed in Account Info screen");
			logger.info("Round Off is not displayed in Account Info screen");
		}

		if (verifyElementDisplayed(AMDClubPage.objRevisedBillingSection)) {
			String RevisiedBillingCycleInfo = getText(AMDClubPage.objRevisedBillingSection);
			extent.extentLoggerPass("Verify Revisied Billing Cycle Info",
					"Revisied Billing Cycle Info \"" + RevisiedBillingCycleInfo + "\" : is displayed in Account Info");
			logger.info(
					"Revisied Billing Cycle Info \"" + RevisiedBillingCycleInfo + "\" : is displayed in Account Info");
		} else {
			extent.extentLoggerFail("Verify Revisied Billing Cycle Info",
					"Revisied Billing Cycle Info is not displayed in Account Info screen");
			logger.info("Revisied Billing Cycle Info is not displayed in Account Info screen");
		}

		if (verifyElementDisplayed(AMDClubPage.objPostDiscountInINR)) {
			String PostDiscountInINR = getText(AMDClubPage.objPostDiscountInINR);
			extent.extentLoggerPass("Verify Post Discount In INR",
					"Post Discount In INR \"" + PostDiscountInINR + "\" : is displayed in Account Info");
			logger.info("Post Discount In INR \"" + PostDiscountInINR + "\" : is displayed in Account Info");
		} else {
			extent.extentLoggerFail("Verify Post Discount In INR",
					"Post Discount INR is not displayed in Account Info screen");
			logger.info("Post Discount INR is not displayed in Account Info screen");
		}

		if (verifyElementDisplayed(AMDClubPage.objEmailIdSection)) {
			String EmailID = getText(AMDClubPage.objEmailIdSection);
			extent.extentLoggerPass("Verify EmailID", "Email ID \"" + EmailID + "\" : is displayed in Account Info");
			logger.info("Email ID \"" + EmailID + "\" : is displayed in Account Info");
		} else {
			extent.extentLoggerFail("Verify EmailID", "Post Discount INR is not displayed in Account Info screen");
			logger.info("Email ID is not displayed in Account Info screen");
		}
		verifyPaymentOptions(userType);
	}

	public void verifyPaymentOptions(String userType) throws Exception {
		extent.HeaderChildNode(
				"verify continue button highlighted on selecting any payment option in Account Info Screen");
		System.out.println(
				"\nVerify continue button highlighted on selecting any payment option in Account Info Screene");
		SwipeUntilFindElement(AMDClubPage.objSelectPaymentOption, "UP");
		verifyElementPresentAndClick(AMDClubPage.objSelectPaymentOption, "Payment option");
		SwipeUntilFindElement(AMDClubPage.objAccountInfoScreenContinueButton, "UP");
		boolean ContinueButton = verifyIsElementDisplayed(AMDClubPage.objAccountInfoScreenContinueButton);
		if (ContinueButton) {
			waitTime(2000);
			if (getAttributValue("clickable", AMDClubPage.objAccountInfoScreenContinueButton).equals("true")) {
				logger.info("Continue button is highlighted on selecting Payment Option");
				extent.extentLoggerPass("Continue button",
						"Continue button is highlighted on selecting Payment Option");
			} else {
				logger.info("Continue button is not highlighted on selecting Payment Option");
				extent.extentLoggerFail("Continue button",
						"Continue button is not highlighted on selecting Payment Option");
			}
			Back(1);
			waitTime(2000);
			Back(2);
			verifyElementPresentAndClick(AMDSearchScreen.objHomeOption, "Bottom bar home option");
		}
	}

	public void ZeeApplicasterLoginForOnboarding() throws Exception {
		extent.HeaderChildNode("Login Type Functionality");
		String pUserType = getParameterFromXML("userType");

		extent.extentLogger("Login Type", "Login Type : " + pUserType);

		switch (pUserType) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitTime(1000);
			break;

		case "NonSubscribedUser":
			navigateToIntroScreen_DisplaylangScreen();
			extent.HeaderChildNode("Login as NonSubscribed User");
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, Password, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			navigateToIntroScreen_DisplaylangScreen();
			extent.HeaderChildNode("Login as Subscribed User");
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
			String SubscribedUsername = getParameterFromXML("SubscribedUserName");
			String SubscribedPassword = getParameterFromXML("SubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;
		}
	}

	public void verifyNonSVODConsumptionScreen(String userType) throws Exception {
		extent.HeaderChildNode("verify user navigated to Consumption Screen post tapping on any News Content");
		System.out.println("\nVerify user navigated to Consumption Screen post tapping on any News Content");
		SelectTopNavigationTab("News");
		waitTime(6000);

		findTrayInScreen(AMDNewsPage.EntertaintmentNewsTray);
		click(AMDNewsPage.EntertaintmentNewsTray, "Entertainment News Tray");
		waitTime(2000);

		String Cardtitle = getText(AMDNewsPage.objMetadataofthecard);
		logger.info(Cardtitle);
		click(AMDNewsPage.objMetadataofthecard, "News Card");

		String ConsumptionScreenTitle = getText(AMDNewsPage.objConsumptionScreenTitle);
		logger.info(ConsumptionScreenTitle);
		if (Cardtitle.equalsIgnoreCase(ConsumptionScreenTitle)) {
			logger.info("User navigated to correct consumption screen tapping on News Content card");
			extent.extentLoggerPass("Consumption Screen",
					"User navigated to correct consumption screen tapping on News Content card");
		} else {
			logger.info("User not navigated to correct consumption screen tapping on News Content card");
			extent.extentLoggerFail("Consumption Screen",
					"User not navigated to correct consumption screen tapping on News Content card");
		}

		waitTime(2000);
		extent.HeaderChildNode(
				"Verify mandatory Registraion popup Should not be displayed when user play any news content");

		boolean MandatoryRegisprationPopup = (!(verifyIsElementDisplayed(AMDNewsPage.objRegisterPopup)));
		if (MandatoryRegisprationPopup) {
			logger.info("Mandatory Registration popup is not displyed when user play any news content");
			extent.extentLoggerPass("Consumption Screen",
					"Mandatory Registration popup is not displyed when user play any news content");
		} else {

			logger.info("Mandatory Registration popup is displyed when user play any news content");
			extent.extentLoggerFail("Consumption Screen",
					"Mandatory Registration popup is displyed when user play any news content");
		}
		verifyMetaDataOfConsumptionScreen();
		verifyShareOption();

		extent.HeaderChildNode("Verify that channel description on tapping Expand Button");
		boolean desc = verifyIsElementDisplayed(AMDConsumptionScreen.objExpandDesc);
		if (desc) {
			extent.extentLoggerPass("Expand Description", "Expand button to expand  description is displayed");
			click(AMDConsumptionScreen.objExpandDesc, "Expand Description button");
			String description = getText(AMDConsumptionScreen.objContentDesc);
			extent.extentLoggerPass("Description", "The content description is :\n" + description);
			logger.info("The content description is :\n" + description);
			click(AMDConsumptionScreen.objExpandDesc, "Expand Description button");
		} else {
			extent.extentLoggerFail("Expand button", "Expand button is not displayed");
			logger.info("Expand button is not displayed");
		}

//	 	extent.HeaderChildNode("Verify that channel description gets collapse on tapping uparrow");
//	    boolean Uparrow = verifyIsElementDisplayed(AMDConsumptionScreen.objExpandDesc);
//		 if(Uparrow) {
//			 extent.extentLoggerPass("Expand Description", "Expand button to expand  description is displayed");
//			 click(AMDConsumptionScreen.objExpandDesc,"Expand Description button");
//			 waitTime(2000);
//			 String description = getText(AMDConsumptionScreen.objContentDesc);
//			 extent.extentLoggerPass("Description",  "Minimum content description is :\n" +description);
//			 logger.info("Minimum content description is :\n" +description);
//		
//			}else {
//		 		logger.info("Minimum content description is not displayed below the player on tapping the Up Arrow");
//		 		extent.extentLoggerFail("Consumption Screen", "Minimum content description is not displayed below the player on tapping the Up Arrow");
//		 	}

		extent.HeaderChildNode("Download Icon Should not display for the News/LiveTV Content");
		boolean DownloadIcon = (!verifyIsElementDisplayed(AMDNewsPage.objDownlaodOption));
		if (DownloadIcon) {
			logger.info("Download Icon is not displayed for any News Content");
			extent.extentLoggerPass("Consumption Screen", "Download Icon is not displayed for any News Content");
		} else {

			logger.info("Download Icon is displayed for any News Content");
			extent.extentLoggerFail("Consumption Screen", "Download Icon is displayed for any News Content");
		}

		extent.HeaderChildNode(
				"Verify the functionality of Watchlist Option displayed below the News Consumption Screen");
		verifyElementPresent(AMDNewsPage.objWatchlistIcon, "WatchList Option");
		click(AMDNewsPage.objWatchlistIcon, "WatchList Icon");
		if (userType.equalsIgnoreCase("Guest")) {
			String header = getText(AMDGenericObjects.objgetScreenTitle);
			if (header.equals("Login/Register")) {
				extent.extentLoggerPass("Watchlist",
						userType + " user is navigated to " + header + " screen on tapping Watchlist option");
				logger.info(userType + " user is navigated to " + header + " screen on tapping Watchlist option");
			} else {
				extent.extentLoggerFail("Watchlist",
						"Failed to navigate into respective screen after clicking Watchlist");
				logger.info("Failed to navigate into respective screen after clicking Watchlist");
			}
			waitTime(4000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");

		} else {
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
			click(AMDMoreMenu.objWatchlist, "My Watchlist");
			verifyElementPresentAndClick(AMDDownloadPage.objvideostab, "Videos tab in Watchlist Screen");
			String WatchlistScreenTitle = getText(AMDNewsPage.objWatchlistContentTitle);
			logger.info(WatchlistScreenTitle);
			if (Cardtitle.equalsIgnoreCase(WatchlistScreenTitle)) {
				logger.info("Added to Watchlist content is displayed in the Watchlist screen");
				extent.extentLoggerPass("Add to Watchlist",
						"Added to Watchlist content is displayed in the Watchlist screen");
			} else {
				logger.info("Added to Watchlist content is not displayed in the screen");
				extent.extentLoggerFail("Add to Watchlist",
						"Added to Watchlist content is not displayed in the Watchlist screen");
			}

			Back(2);
			SelectTopNavigationTab("News");
			waitTime(6000);

			findTrayInScreen(AMDNewsPage.EntertaintmentNewsTray);
			click(AMDNewsPage.EntertaintmentNewsTray, "Entertainment News Tray");
			waitTime(2000);

			click(AMDNewsPage.objMetadataofthecard, "News Card");
			waitTime(4000);
			click(AMDNewsPage.objWatchlistIcon, "WatchList Icon");
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
			click(AMDMoreMenu.objWatchlist, "My Watchlist");
			verifyElementPresentAndClick(AMDDownloadPage.objvideostab, "Videos tab in Watchlist Screen");

			boolean WatchlistIconContentTitle = (!(verifyIsElementDisplayed(AMDNewsPage.objWatchlistContentTitle)));
			if (WatchlistIconContentTitle) {
				extent.extentLoggerPass("Removed From Watchlist", "Content is removed from the Watchlist Screen");
				logger.info("Content is removed from the Watchlist Screen");
			} else {
				extent.extentLoggerFail("Removed From Watchlist", "Content is not removed from the Watchlist Screen");
				logger.info("Content is not removed from the Watchlist Screen");
			}
			Back(1);
			verifyElementPresentAndClick(AMDSearchScreen.objHomeOption, "Bottom bar home option");
		}
		waitTime(2000);
		verifyRailsSwipe(userType);
	}

	public void findTrayInScreen(By byLocator) {
		for (int i = 0; i < 25; i++) {
			if (!(verifyIsElementDisplayed(byLocator))) {
				Swipe("UP", 1);
			} else {
				PartialSwipe("UP", 1);
				logger.info("Entertainment News tray is located");
				extent.extentLoggerPass("Tray", "Entertainment News tray is located");
				break;
			}
		}
	}

	public void verifyMetaDataOfConsumptionScreen() throws Exception {
		extent.HeaderChildNode("Verify the options available in the news consumption screen");
		System.out.println("\nVerify the options available in the news consumption screen");

		boolean Description = (verifyIsElementDisplayed(AMDNewsPage.objNewsConsumptionSrnDescription));
		if (Description) {
			String NewsDescription = getText(AMDNewsPage.objNewsConsumptionSrnDescription);
			logger.info(
					NewsDescription + " : Short Description of the News Content is displayed in Consumption Screen");
			extent.extentLoggerPass("Short Description",
					NewsDescription + " : Short Description of the News Content is displayed in Consumption Screen");
		} else {
			logger.info("Short Description is not displayed in News Consumption Screen");
			extent.extentLoggerFail("Short Description",
					"Short Description is not displayed in News Consumption Screen");
		}

		boolean NewsChannel = (verifyIsElementDisplayed(AMDNewsPage.objChannelName));
		if (NewsChannel) {
			String ChannelName = getText(AMDNewsPage.objChannelName);
			logger.info(ChannelName + " : Channel name is displayed in News Consumption Screen");
			extent.extentLoggerPass("Channel Name",
					ChannelName + " : Channel name is displayed in News Consumption Screen");
		} else {
			logger.info("Channel name is not displayed in News Consumption Screen");
			extent.extentLoggerFail("Channel Name", "Channel name is not displayed in News Consumption Screen");
		}

		boolean Genere = (verifyIsElementDisplayed(AMDNewsPage.objContentGenere));
		if (Genere) {
			String ContentGenere = getText(AMDNewsPage.objContentGenere);
			logger.info(ContentGenere + " : Genere type is displayed in News Consumption Screen");
			extent.extentLoggerPass("Genere Type",
					ContentGenere + " : Genere type is displayed in News Consumption Screen");
		} else {
			logger.info("Genere type is not displayed in News Consumption Screen");
			extent.extentLoggerFail("Genere Type", "Genere type is not displayed in News Consumption Screen");
		}

		boolean Duration = (verifyIsElementDisplayed(AMDNewsPage.objContentDuration));
		if (Duration) {
			String ContentDuration = getText(AMDNewsPage.objContentDuration);
			logger.info(ContentDuration + " : Content Duration is displayed in News Consumption Screen");
			extent.extentLoggerPass("Content Duration",
					ContentDuration + " :Content Duration is displayed in News Consumption Screen");
		} else {
			logger.info("Content Duration is not displayed in News Consumption Screen");
			extent.extentLoggerFail("Content Duration", "Content Duration is not displayed in News Consumption Screen");
		}

		boolean Date = (verifyIsElementDisplayed(AMDNewsPage.objBroadcastedDate));
		if (Date) {
			String BroadcastedDate = getText(AMDNewsPage.objBroadcastedDate);
			logger.info(BroadcastedDate + " : Broadcasted Date is displayed in News Consumption Screen");
			extent.extentLoggerPass("Broadcasted Date",
					BroadcastedDate + " :Broadcasted Date is displayed in News Consumption Screen");
		} else {
			logger.info("Broadcasted Date is not displayed in News Consumption Screen");
			extent.extentLoggerFail("Broadcasted Date", "Content Duration is not displayed in News Consumption Screen");
		}

		boolean shareCTA = verifyIsElementDisplayed(AMDConsumptionScreen.objShareBtn);
		if (shareCTA) {
			extent.extentLoggerPass("Share CTA", "Share CTA is displayed in News Consumption Screen");
			logger.info("Share CTA is displayed in News Consumption Screen");
		} else {
			extent.extentLoggerFail("Share CTA", "Share CTA is not displayed in News Consumption Screen");
			logger.info("Share CTA is not displayed in News Consumption Screen");
		}
		boolean watchListCTA = verifyIsElementDisplayed(AMDConsumptionScreen.objWatchlistBtn);
		if (watchListCTA) {
			extent.extentLoggerPass("Watchlist", "Watchlist CTA is displayed in News Consumption Screen");
			logger.info("Watchlist CTA is displayed in News Consumption Screen");
		} else {
			extent.extentLoggerPass("Watchlist", "Watchlist CTA is not displayed in News Consumption Screen");
			logger.info("Watchlist CTA is not displayed in News Consumption Screen");
		}

	}

	public void verifyChannelNames() throws Exception {
		extent.HeaderChildNode("Verify Appropriate Content Title");
		List<WebElement> ThumbnailCard = getDriver().findElements(By.xpath("//*[@id='item_primary_text']"));
		logger.info(ThumbnailCard.size());
		for (int i = 1; i <= ThumbnailCard.size() - 2; i++) {

			WebElement cardname = getDriver().findElement(By.xpath("(//*[@id='item_primary_text'])[" + i + "]"));
			String tabName = cardname.getText();
			logger.info(tabName);
			cardname.click();
			waitTime(10000);
			String ConsumptionScreenTitle = getText(AMDNewsPage.objConsumptionScreenTitle);
			logger.info(ConsumptionScreenTitle);
			if (tabName.equalsIgnoreCase(ConsumptionScreenTitle)) {
				logger.info("Appropriate Channel name is displayed below the Content Thumbnail Card");
				extent.extentLoggerPass("Channel Name",
						"Appropriate Channel name is displayed below the Content Thumbnail Card");

			} else {
				logger.info("Appropriate Channel name is not displayed below the Content Thumbnail Card");
				extent.extentLoggerFail("Channel Name",
						"Appropriate Channel name is not displayed below the Content Thumbnail Card");
			}
			Back(1);

		}
		Back(1);

	}

	public void verifySimilarChannels() throws Exception {
		extent.HeaderChildNode("Verify the options available in the live tv consumption screen");
		SelectTopNavigationTab("Live TV");
		waitTime(30000);

		String LiveChannelName = getText(AMDNewsPage.objLiveChannelName);
		logger.info(LiveChannelName);

		click(AMDNewsPage.objLiveChannelName, "Live Channel");
		waitTime(20000);
		String ConsumptionScreenMetaData = getText(AMDNewsPage.objConsumptionScrnMetaDataOfLiveTv);
		if (LiveChannelName.equalsIgnoreCase(ConsumptionScreenMetaData)) {
			logger.info("Appropriate playback will be initiated for the clicked content");
			extent.extentLoggerPass("Consumption Screen",
					"Appropriate playback will be initiated for the clicked content");
		} else {
			logger.info("Appropriate playback is not initiated for the clicked content");
			extent.extentLoggerFail("Consumption Screen",
					"Appropriate playback is not initiated for the clicked content");
		}

		boolean shareCTA = verifyIsElementDisplayed(AMDConsumptionScreen.objShareBtn);
		if (shareCTA) {
			extent.extentLoggerPass("Share CTA", "Share CTA is displayed in Live TV Consumption Screen");
			logger.info("Share CTA is displayed in News Consumption Screen");
		} else {
			extent.extentLoggerFail("Share CTA", "Share CTA is not displayed in Live TV Consumption Screen");
			logger.info("Share CTA is not displayed in Live TV Consumption Screen");
		}

//		boolean Description = (verifyIsElementDisplayed(AMDNewsPage.objNewsConsumptionSrnDescription));
//		if (Description) {
//			String LiveNewsDescription = getText(AMDNewsPage.objNewsConsumptionSrnDescription);
//			logger.info(LiveNewsDescription
//					+ " : Short Description of the Live TV Content is displayed in Consumption Screen");
//			extent.extentLoggerPass("Short Description", LiveNewsDescription
//					+ " : Short Description of the Live TV Content is displayed in Consumption Screen");
//		} else {
//			logger.info("Short Description is not displayed in Live TV Consumption Screen");
//			extent.extentLoggerFail("Short Description",
//					"Short Description is not displayed in Live TV Consumption Screen");
//		}
//
//		boolean NewsChannel = (verifyIsElementDisplayed(AMDNewsPage.objChannelName));
//		if (NewsChannel) {
//			String ChannelName = getText(AMDNewsPage.objChannelName);
//			logger.info(ChannelName + " : Channel name is displayed in Live TV Consumption Screen");
//			extent.extentLoggerPass("Channel Name",
//					ChannelName + " : Channel name is displayed in Live TV Consumption Screen");
//		} else {
//			logger.info("Channel name is not displayed in Live TV Consumption Screen");
//			extent.extentLoggerFail("Channel Name", "Channel name is not displayed in Live TV Consumption Screen");
//		}

		boolean WatchlistIcon = (!(verifyIsElementDisplayed(AMDNewsPage.objWatchlistIcon)));
		if (WatchlistIcon) {
			logger.info("Watchlist option is not displayed for Live TV Consumption Screen ");
			extent.extentLoggerPass("Consumption Screen",
					"Watchlist option is not displayed for Live TV Consumption Screen ");
		} else {

			logger.info("Watchlist option is displayed for Live TV Consumption Screen ");
			extent.extentLoggerFail("Consumption Screen",
					"Watchlist option is displayed for Live TV Consumption Screen ");
		}

		extent.HeaderChildNode("Download Icon Should not display for the LiveTV Content");
		boolean DownloadIcon = (!verifyIsElementDisplayed(AMDNewsPage.objDownlaodOption));
		if (DownloadIcon) {
			logger.info("Download Icon is not displayed for any Live TV Content");
			extent.extentLoggerPass("Consumption Screen", "Download Icon is not displayed for any Live TV Content");
		} else {

			logger.info("Download Icon is displayed for any Live TV Content");
			extent.extentLoggerFail("Consumption Screen", "Download Icon is displayed for any Live TV Content");
		}

		extent.HeaderChildNode(
				"Verify similar Channels rail is displayed and navigate to Listing screen on tapping rail title");
		Swipe("UP", 1);
		boolean SimilarChannelsRail = verifyIsElementDisplayed(AMDGenericObjects.objCarouselTitle("Similar Channels"));
		if (SimilarChannelsRail) {

			logger.info("Similar Channels rail is displayed below the player in LiveTV Consumption Screen");
			extent.extentLoggerPass("Consumption Screen",
					"Similar Channels rail is displayed below the player in LiveTV Consumption Screen");
		} else {

			logger.info("Similar Channels rail is not displayed below the player in LiveTV Consumption Screen");
			extent.extentLoggerFail("Consumption Screen",
					"Similar Channels rail is not displayed below the player in LiveTV Consumption Screen");
		}

		if (SimilarChannelsRail) {
			click(AMDNewsPage.objSimilarChannelsTray, "Similar Channels Rail");
			String Channelheader = getText(AMDNewsPage.objSimilarChannelsHeader);
			if (Channelheader.equals("Similar Channels")) {

				extent.extentLoggerPass("Verify Navigation", "User is navigated to " + Channelheader
						+ " screen post tapping on the rail name displayed below the consumption screen");
				logger.info("User is navigated to " + Channelheader
						+ " screen post tapping on the rail name displayed below the consumption screen");
			} else {
				extent.extentLoggerFail("Verify navigation",
						"User is not navigated to listing screen post tapping rail name displayed below the consumption screen ");
				logger.info(
						"User is not navigated to listing screen post tapping rail name displayed below the consumption screen ");
			}
		}
		Back(1);
		verifyChannelNames();
	}

	public void verifyRailsSwipe(String userType) throws Exception {

		if (userType.equals("NonSubscribedUser") | userType.equals("SubscribedUser")) {
			SelectTopNavigationTab("News");
			waitTime(6000);
			findTrayInScreen(AMDNewsPage.EntertaintmentNewsTray);
			click(AMDNewsPage.EntertaintmentNewsTray, "Entertainment News Tray");
			waitTime(2000);
			click(AMDNewsPage.objMetadataofthecard, "News Card");
			waitTime(8000);
		}

		// Swipe rail content cards left/right to access any related content
		extent.HeaderChildNode("verify user can swipe left/right to access related contents from the rail");
		PartialSwipe("UP", 1);
		String firstContentName = getText(AMDGenericObjects.objContentNameInTray(1));
		logger.info(firstContentName);
		SwipeRailContentCards(AMDNewsPage.objSwipeTray);
		String secondContentName = getText(AMDGenericObjects.objContentNameInTray(3));
		logger.info(secondContentName);
		if (!firstContentName.equals(secondContentName)) {
			extent.extentLoggerPass("Swipe",
					"User can Swipe the content cards to access related contents from the rail");
			logger.info("User can Swipe the content cards to access related contents from the rail");
		} else {
			extent.extentLoggerFail("Swipe",
					"User cannot Swipe the content cards to access related contents from the rail");
			logger.info("User cannot Swipe the content cards to access related contents from the rail");
		}

		Back(1);
		verifyElementPresentAndClick(AMDSearchScreen.objHomeOption, "Bottom bar home option");
	}

	public void verifyShareOption() throws Exception {
		extent.HeaderChildNode("Verify the functionality of share option displayed below the player");
		verifyElementPresentAndClick(AMDNewsPage.objShareButton, "Share button in consumption screen");
		waitTime(4000);
		verifyElementPresentAndClick(AMDNewsPage.objShareGmail, "GMail Share option");
		waitTime(4000);
		hideKeyboard();
		String SharedLink = getText(AMDNewsPage.objSharedLink);
		logger.info(SharedLink);
		if (SharedLink.contains("https://www.zee5.com")) {
			logger.info("User can able to share content link across GMail sharing options");
			extent.extentLoggerPass("Share Content",
					"User can able to share content link across GMail sharing options");
		} else {
			logger.info("User not shared the content link across GMail sharing options");
			extent.extentLoggerFail("Share Content", "User not shared the content link across GMail sharing options");
		}
		Back(1);
	}

	/**
	 * Author : Vinay Module : Consumption Screen
	 */

	public void SVODConsumptionScreen(String userType, String tabName, String contentName) throws Exception {

		extent.HeaderChildNode("Verifying SVOD contents on Consumption screen for tab \"" + tabName
				+ "\" and Playing the content : " + contentName);
		waitTime(5000);

		String cotentName1 = "";
		if (tabName.equals("Episode")) {
			if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
				cotentName1 = SVODConsumptionScreenForEpisode(userType);
				contentName = cotentName1;
			} else {
				verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
				verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
				type(AMDSearchScreen.objSearchBoxBar, contentName + "\n", "Search bar");
				hideKeyboard();
				click(AMDSearchScreen.objEpisodeSearch, "Search result");
			}
		} else {
			verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
			verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, contentName + "\n", "Search bar");
			hideKeyboard();
		}
		if (tabName.equals("Music")) {
			click(AMDSearchScreen.objSecondSearchResult(contentName), "Search result");
		} else {
			click(AMDSearchScreen.objFirstSearchResult(contentName), "Search result");
		}

		if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
			if (tabName.equals("Episode") | tabName.equals("Movies")) {
				click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			}
		}

		boolean isContentNameDisplayed = verifyIsElementDisplayed(AMDConsumptionScreen.objContentName);
		if (isContentNameDisplayed) {
			String contentNameInConsumptionScreen = getText(AMDConsumptionScreen.objContentName);
			extent.extentLogger("Verify content name", "Content name is available in consumption screen");
			logger.info("Content name is available in consumption screen");
			if (tabName.equals("Shows")) {
				extent.extentLogger("Verify navigation",
						"User is playing SVOD content \"" + contentName + "\" for tab \"" + tabName + "\"");
				logger.info("User is playing SVOD content \"" + contentName + "\" for tab \"" + tabName + "\"");
				if (contentName.equals(contentNameInConsumptionScreen)) {
					extent.extentLoggerPass("Verify Content name",
							"User is navigated to respective consumption screen for  SVOD content '" + contentName
									+ "' for tab " + tabName);
					logger.info("User is navigated to respective consumption screen for  SVOD content '" + contentName
							+ "' for tab " + tabName);
				} else {
					extent.extentLoggerFail("Verify content name",
							"Failed to navigate into respective consumtption screen for SVOD content '" + contentName
									+ "' for tab " + tabName);
					logger.info("Failed to navigate into respective consumtption screen for  SVOD content '"
							+ contentName + "' for tab " + tabName);
				}
			} else if (tabName.equals("Movies")) {
				extent.extentLogger("Verify navigation",
						"User is playing SVOD content \"" + contentName + "\" for tab \"" + tabName + "\"");
				logger.info("User is playing SVOD content \"" + contentName + "\" for tab \"" + tabName + "\"");
				if (contentName.equals(contentNameInConsumptionScreen)) {
					extent.extentLoggerPass("Verify Content name",
							"User is navigated to respective consumption screen for  SVOD content '" + contentName
									+ "' for tab " + tabName);
					logger.info("User is navigated to respective consumption screen for  SVOD content " + contentName
							+ " for tab " + tabName);
				} else {
					extent.extentLoggerFail("Verify content name",
							"Failed to navigate into respective consumtption screen for  SVOD content '" + contentName
									+ "' for tab " + tabName);
					logger.info("Failed to navigate into respective consumtption screen for  SVOD content '"
							+ contentName + "' for tab " + tabName);
				}

			} else if (tabName.equals("Music")) {
				extent.extentLogger("Verify navigation",
						"User is playing SVOD content \"" + contentName + "\" for tab \"" + tabName + "\"");
				logger.info("User is playing SVOD content \"" + contentName + "\" for tab \"" + tabName + "\"");
				if (contentName.equals(contentNameInConsumptionScreen)) {
					extent.extentLoggerPass("Verify Content name",
							"User is navigated to respective consumption screen for  SVOD content " + contentName
									+ " for tab " + tabName);
					logger.info("User is navigated to respective consumption screen for  SVOD content " + contentName
							+ " for tab " + tabName);
				} else {
					extent.extentLoggerFail("Verify content name",
							"Failed to navigate into respective consumption screen for  SVOD content '" + contentName
									+ "' for tab " + tabName);
					logger.info("Failed to navigate into respective consumption screen for  SVOD content '"
							+ contentName + "' for tab " + tabName);
				}
			} else if (tabName.equals("Episode")) {
				extent.extentLogger("Verify navigation",
						"User is playing SVOD content \"" + contentName + "\" for tab \"" + tabName + "\"");
				logger.info("User is playing SVOD content \"" + contentName + "\" for tab \"" + tabName + "\"");
				if (userType.equals("SubscribedUser")) {
					if (contentName.equals(contentNameInConsumptionScreen)) {
						extent.extentLoggerPass("Verify Content name",
								"User is navigated to respective consumption screen for  SVOD content '" + contentName
										+ "' for tab " + tabName);
						logger.info("User is navigated to respective consumption screen for  SVOD content "
								+ contentName + " for tab " + tabName);
					} else {
						extent.extentLoggerFail("Verify content name",
								"Failed to navigate into respective consumtption screen for  SVOD content '"
										+ contentName + "' for tab " + tabName);
						logger.info("Failed to navigate into respective consumtption screen for  SVOD content '"
								+ contentName + "' for tab " + tabName);
					}
				} else {
					if (cotentName1.equals(contentNameInConsumptionScreen)) {
						extent.extentLoggerPass("Verify Content name",
								"User is navigated to respective consumption screen for  SVOD content '" + cotentName1
										+ "' for tab " + tabName);
						logger.info("User is navigated to respective consumption screen for  SVOD content "
								+ cotentName1 + " for tab " + tabName);
					} else {
						extent.extentLoggerFail("Verify content name",
								"Failed to navigate into respective consumtption screen for  SVOD content '"
										+ cotentName1 + "' for tab " + tabName);
						logger.info("Failed to navigate into respective consumtption screen for  SVOD content '"
								+ cotentName1 + "' for tab " + tabName);
					}

				}
			}
		}
		MetadataSVODContentConsumptionScreen(tabName, contentName, userType);
		VerifyCTAsInConsumptionScreen(userType, tabName, contentName);
		ShareValidationConsumptionScreen(tabName, userType);
		WatchList(userType, tabName, contentName);
		DownloadFunctionality(userType, tabName, contentName);
		WatchTrailer(userType, tabName, contentName);
		AudioLanguage(userType, contentName, tabName);
		SubTitles(userType, contentName, tabName);
		ValidateConfiguredRails(userType, tabName);
		AvailableTraysInTabs(tabName, userType);
		if (tabName.equals("Episode")) {
			Back(2);
			click(AMDHomePage.objHomeBtn, "Home icon");
		} else {
			Back(3);
		}
		// click(AMDHomePage.objHome,"Home Icon");
		waitTime(3000);
	}

	public void VerifyCTAsInConsumptionScreen(String userType, String tabName, String contentName) throws Exception {

		HeaderChildNode("Verify the availability of CTA's in Consumption screen for the tab :\"" + tabName + "\"");
		boolean shareCTA = verifyIsElementDisplayed(AMDConsumptionScreen.objShareBtn);
		if (shareCTA) {
			extent.extentLoggerPass("Share CTA", "Share CTA is available for the content : " + contentName);
			logger.info("Share CTA is available for the content : " + contentName);
		} else {
			extent.extentLogger("Share CTA",
					"Share CTA is not available for the content '" + contentName + "' for user : " + userType);
			logger.info("Share CTA is not available for the content '" + contentName + "' for user type : " + userType);
		}
		boolean watchListCTA = verifyIsElementDisplayed(AMDConsumptionScreen.objWatchlistBtn);
		if (watchListCTA) {
			extent.extentLoggerPass("Watchlist", "Watchlist CTA is available for the content : " + contentName);
			logger.info("Watchlist CTA is available for the content : " + contentName);
		} else {
			extent.extentLogger("Watchlist CTA",
					"Watchlist CTA is not available for the content '" + contentName + "' for user : " + userType);
			logger.info(
					"Watchlist CTA is not available for the content '" + contentName + "' for user type : " + userType);
		}
		boolean castCTA = verifyIsElementDisplayed(AMDConsumptionScreen.objCastBtn);
		if (castCTA) {
			extent.extentLoggerPass("Cast CTA", "Cast CTA is available for the content : " + contentName);
			logger.info("Cast CTA is available for the content : " + contentName);
		} else {
			extent.extentLogger("Cast CTA",
					"Cast CTA is not available for the content '" + contentName + "' for user : " + userType);
			logger.info("Cast CTA is not available for the content '" + contentName + "' for user type : " + userType);
		}
		boolean downloadCTA = verifyIsElementDisplayed(AMDConsumptionScreen.objDownloadBtn);
		if (downloadCTA) {
			extent.extentLoggerPass("Download CTA", "Download CTA is available for the content : " + contentName);
			logger.info("Download CTA is available for the content : " + contentName);
		} else {
			extent.extentLogger("Download CTA",
					"Download CTA is not available for the content '" + contentName + "' for user : " + userType);
			logger.info(
					"Download CTA is not available for the content '" + contentName + "' for user type : " + userType);
		}
		boolean watchTrailerCTA = verifyIsElementDisplayed(AMDConsumptionScreen.objWatchTrialer);
		if (watchTrailerCTA) {
			extent.extentLoggerPass("Watch Trailer CTA",
					"Watch Trailer CTA is available for the content : " + contentName);
			logger.info("Watch Trailer CTA is available for the content : " + contentName);
		} else {
			extent.extentLogger("Watch Trailer CTA",
					"Watch Trailer CTA is not available for the content '" + contentName + "' for user : " + userType);
			logger.info("Watch Trailer CTA is not available for the content '" + contentName + "' for user type : "
					+ userType);
		}
	}

//Verify Metadata in Consumption screen for SVOD contents
	public void MetadataSVODContentConsumptionScreen(String tabName, String contentName, String userType)
			throws Exception {
		extent.HeaderChildNode("Verifying Metadata in consumption screen for the tab : \"" + tabName + "\"");
		// Verify Content name
		boolean isContenName = verifyIsElementDisplayed(AMDConsumptionScreen.objContentName);
		if (isContenName) {
			String contentName1 = getText(AMDConsumptionScreen.objContentName);
			extent.extentLoggerPass("Verify Content Name",
					"Content Name \"" + contentName1 + "\" is displayed in consumption screen");
			logger.info("Content Name \"" + contentName1 + "\" is displayed in consumption screen");
		} else {
			extent.extentLogger("Verify content name", "Content name is not displayed in consumption screen");
			logger.info("Content name is not displayed in consumption screen");
		}

		if (tabName.equals("Shows") | tabName.equals("Episode")) {
			// Verify No of Episodes
			boolean isGenre = verifyIsElementDisplayed(AMDConsumptionScreen.objMainGenre);
			if (isGenre) {
				String NoOfEpisode = getText(AMDConsumptionScreen.objMainGenre);
				extent.extentLoggerPass("Verify No of episodes",
						"Number of available episodes for the content '" + contentName + "' is : " + NoOfEpisode);
				logger.info("Number of available episodes for the content '" + contentName + "' is : " + NoOfEpisode);
			} else {
				extent.extentLogger("Verify No of episodes",
						"Number of available episodes are not displayed for the content '" + contentName + "'");
				logger.info("Number of available episodes are not displayed for the content '" + contentName + "'");
			}

		} else if (tabName.equals("Movies")) {

			// Verify content type for Movie
			boolean isMovieType = verifyIsElementDisplayed(AMDConsumptionScreen.objMainGenre);
			if (isMovieType) {
				String type = getText(AMDConsumptionScreen.objMainGenre);
				extent.extentLoggerPass("Verify Type of the Movie content",
						"The type of the Movie content is : \"" + type + "\"");
				logger.info("The type of the Movie content is : \"" + type + "\"");
			} else {
				extent.extentLoggerFail("Verify Type of the Movie content",
						"Type of the Movie content is not available for the content '" + contentName + "'");
				logger.info("Type of the Movie content is not available for the content '" + contentName + "'");
			}

		} else if (tabName.equals("Music")) {
			// Verify content type for Music content
			boolean isMusicType = verifyIsElementDisplayed(AMDConsumptionScreen.objMainGenre);
			if (isMusicType) {
				String type = getText(AMDConsumptionScreen.objMainGenre);
				extent.extentLoggerPass("Verify Type of the Music content",
						"The type of the Music content is  :\"" + type + "\"");
				logger.info("The type of the Music content is  :\"" + type + "\"");
			} else {
				extent.extentLoggerFail("Verify Type of the Music content",
						"Type of the Music content is not available for the content '" + contentName + "'");
				logger.info("Type of the Music content is not available for the content '" + contentName + "'");
			}
		}

		// Verify Release Year
		boolean isYearDisplayed = verifyIsElementDisplayed(AMDConsumptionScreen.objReleasYear);
		if (isYearDisplayed) {
			String ReleaseYear = getText(AMDConsumptionScreen.objReleasYear);
			extent.extentLoggerPass("Verify Release year", "Release year for the content is :\"" + ReleaseYear + "\"");
			logger.info("Release year for the content is :\"" + ReleaseYear + "\"");
		} else {
			extent.extentLogger("Verify Release year",
					"Release year is not displayed for the content '" + contentName + "'");
			logger.info("Release year is not displayed for the content '" + contentName + "'");

		}

		// Verify Genre
		boolean isGenre = verifyIsElementDisplayed(AMDConsumptionScreen.objGenre);
		if (isGenre) {
			String genre = getText(AMDConsumptionScreen.objGenre);
			extent.extentLoggerPass("Verify Genre", "Genre for the content is : \"" + genre + "\"");
			logger.info("Genre for the content is : \"" + genre + "\"");
		} else {
			extent.extentLogger("Verify Genre", "Genre is not displayed for the content '" + contentName + "'");
			logger.info("Genre is not displayed for the content '" + contentName + "'");

		}

		// Verify Total Duration
		boolean isTotalDuration = verifyIsElementDisplayed(AMDConsumptionScreen.objDuratation);
		if (isTotalDuration) {
			String Duration = getText(AMDConsumptionScreen.objDuratation);
			extent.extentLoggerPass("Verify Total duration",
					"Total duration for the content is : \"" + Duration + "\"");
			logger.info("Total duration for the content is : \"" + Duration + "\"");
		} else {
			extent.extentLogger("Verify Total duration",
					"Total duration is not displayed for the content '" + contentName + "'");
			logger.info("Total duration is not displayed for the content '" + contentName + "'");

		}

		// Verify Audio Language
		boolean isAudio = verifyIsElementDisplayed(AMDConsumptionScreen.objAudioLanguage);
		if (isAudio) {
			extent.extentLoggerPass("Verify Audio Language", "Audio Language option is displayed");
			logger.info("Audio Language option is displayed");
		} else {
			extent.extentLoggerFail("Verify Audio Language",
					"Audio Language option is not displayed for the content '" + contentName + "'");
			logger.info("Audio Language option is not displayed for the content '" + contentName + "'");

		}

		// Verify Subtitle
		boolean isSubtitle = verifyIsElementDisplayed(AMDConsumptionScreen.objSubtitle);
		if (isSubtitle) {
			extent.extentLoggerPass("Verify Subtitle", "Subtitle option is displayed");
			logger.info("Subtitle option is displayed");
		} else {
			extent.extentLoggerFail("Verify Subtitle",
					"Subtitle option is not displayed for the content '" + contentName + "'");
			logger.info("Subtitle option is not displayed for the content '" + contentName + "'");

		}
		// Verify Description
		boolean isDescription = verifyIsElementDisplayed(AMDConsumptionScreen.objContentDesc);
		if (isDescription) {
			extent.extentLoggerPass("Verify Description", "Content Description is displayed");
			logger.info("Content Description is displayed");

			boolean desc = verifyIsElementDisplayed(AMDConsumptionScreen.objExpandDesc);
			if (desc) {
				extent.extentLoggerPass("Expand Description", "Expand button to expand  description is displayed");
				click(AMDConsumptionScreen.objExpandDesc, "Expand Description button");
				String description = getText(AMDConsumptionScreen.objContentDesc);
				extent.extentLoggerPass("Description", "The content description is :\n" + description);
				logger.info("The content description is :\n" + description);
				click(AMDConsumptionScreen.objExpandDesc, "Description button");

			} else {
				extent.extentLoggerFail("Expand button", "Expand button is not displayed");
				logger.info("Expand button is not displayed");
			}
		} else {
			extent.extentLoggerFail("Verify Description", "Content Description  is not displayed");
			logger.info("Content Description  is not displayed");

		}

	}

	public void ShareValidationConsumptionScreen(String tabName, String userType) throws Exception {
		extent.HeaderChildNode("Verify Share CTA functionality");
		click(AMDConsumptionScreen.objShareBtn, "Share button");
		boolean isShareOption = verifyIsElementDisplayed(AMDMoreMenu.objshareOptions);
		if (isShareOption) {
			logger.info("User is navigated share options screen");
			extent.extentLoggerPass("Share through options screen", "User is navigated to share options screen");
			int shareOptions = getDriver().findElements(AMDMoreMenu.objShareOptions).size();
			if (shareOptions == 0) {
				extent.extentLoggerFail("Verify share options", "Share Options are not available");
				logger.info("Share Options are not available");
			} else {
				for (int i = 1; i <= shareOptions; i++) {
					String shareOptionName = getText(AMDMoreMenu.objShareOptions(i));
					logger.info("Share Option : " + shareOptionName + " is available to share");
					extent.extentLogger("Share Option ",
							"Share Option : " + shareOptionName + " is available to share");
				}
			}
		} else {
			logger.info("Share Options are not displayed after clicking on Share CTA");
			extent.extentLoggerFail("Share through options screen",
					"Share Options are not displayed after clicking on Share CTA");
		}
		Back(1);
	}

	public void WatchList(String userType, String tabName, String contentName) throws Exception {
		extent.HeaderChildNode("Verify Watchlist CTA Functionality");
		if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
			if (tabName.equals("Episode") | tabName.equals("Movies")) {
				click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			}
		}
		if (userType.equals("Guest")) {
			if (tabName.equals("Music")) {
				extent.extentLoggerFail("Watch list", "For the selected SVOD Music content '" + contentName
						+ "' user can not click on the Watchlist CTA");
				logger.info("For the selected SVOD Music content '" + contentName
						+ "' user can not click on the Watchlist CTA");
			} else {
				verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist button");
				String header = getText(AMDGenericObjects.objgetScreenTitle);
				if (header.equals("Login/Register")) {
					extent.extentLoggerPass("Watchlist",
							userType + " user is navigated to " + header + " screen after tapping Watchlist CTA");
					logger.info(userType + " user is navigated to " + header + " screen after tapping Watchlist CTA");
					click(AMDGenericObjects.objBackBtn, "Back button");
					waitTime(3000);
//					Back(1);
				} else {
					extent.extentLoggerFail("Watchlist",
							"Failed to navigate into respective screen after clicking Watchlist");
					logger.info("Failed to navigate into respective screen after clicking Watchlist");
				}
			}
			waitTime(3000);

		} else if (userType.equals("NonSubscribedUser")) {
			if (tabName.equals("Music")) {
				extent.extentLoggerFail("Watch list", "For the selected SVOD Music content '" + contentName
						+ "' user can not click on the Watchlist CTA for user type : " + userType);
				logger.info("For the selected SVOD Music content '" + contentName
						+ "' user can not click on the Watchlist CTA for user type : " + userType);
			} else if (tabName.equals("Shows") | tabName.equals("Episode")) {
				verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist button");
				Back(1);
				watchListScreen(contentName, tabName);
				waitTime(3000);
			}
		} else if (userType.equals("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist button");
			Back(1);
			watchListScreen(contentName, tabName);
		}
		waitTime(3000);
	}

	public void watchListScreen(String contentName, String tabName) throws Exception {

		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		verifyElementPresentAndClick(AMDMoreMenu.objWatchlist, "Watch list in More menu");
		if (tabName.equals("Episode")) {
			String name = contentName;
			String[] spilt = name.split("-");
			String epName = spilt[1];
			String[] epp = epName.split(" ");
			String episodeName = epp[1];
			ArrayList<String> contentsInWatchList = new ArrayList<>();
			int size = getDriver().findElements(AMDWatchlistPage.objContentNames).size();
			for (int i = 1; i <= size; i++) {
				String titleName = getText(AMDWatchlistPage.objContentName(i));
				contentsInWatchList.add(titleName);
			}
			waitTime(5000);
			boolean isContentPresent = contentsInWatchList.contains(episodeName);
			if (isContentPresent) {
				extent.extentLoggerPass("Verify Watchlist", "The added content is displayed in Watchlist screen");
				logger.info("The added content is displayed in Watchlist screen");
			} else {
				extent.extentLoggerFail("Verify Watchlist", "The added content is not displayed in Watchlist screen");
				logger.info("The added content is not displayed in Watchlist screen");
			}
		} else {
			// Verify added contents are displayed
			ArrayList<String> contentsInWatchList = new ArrayList<>();
			int size = getDriver().findElements(AMDWatchlistPage.objContentNames).size();
			for (int i = 1; i <= size; i++) {
				String titleName = getText(AMDWatchlistPage.objContentName(i));
				contentsInWatchList.add(titleName);
			}
			waitTime(5000);
			boolean isContentPresent = contentsInWatchList.contains(contentName);
			if (isContentPresent) {
				extent.extentLoggerPass("Verify Watchlist", "The added content is displayed in Watchlist screen");
				logger.info("The added content is displayed in Watchlist screen");
			} else {
				extent.extentLoggerFail("Verify Watchlist", "The added content is not displayed in Watchlist screen");
				logger.info("The added content is not displayed in Watchlist screen");
			}
		}
		boolean isNoContents = verifyIsElementDisplayed(AMDWatchlistPage.objNoContentsMessage);
		if (!isNoContents) {
			click(AMDWatchlistPage.objEditBtn, "Edit button");
			click(AMDWatchlistPage.objSelectAllIcon, "Select all icon");
			click(AMDWatchlistPage.objDeleteAllBtn, "Delete All icon");
			isNoContents = verifyIsElementDisplayed(AMDWatchlistPage.objNoContentsMessage);
			if (isNoContents) {
				extent.extentLoggerPass("Remove Content from watchlist", "Content is removed from the watchlist");
				logger.info("Content is removed from the watchlist");
			} else {
				extent.extentLoggerFail("Remove Content from watchlist", "Content is not removed from the watchlist");
				logger.info("Content is not removed from the watchlist");
			}
		} else {
			extent.extentLogger("Remove Content from watchlist", "There is no content to remove from Watchlist screen");
			logger.info("There is no content to remove from Watchlist screen");
		}
		Back(2);
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, contentName + "\n", "Search bar");
		hideKeyboard();
		if (tabName.equals("Music")) {
			click(AMDSearchScreen.objSecondSearchResult(contentName), "Search result");
		} else if (tabName.equals("Episode")) {
			click(AMDSearchScreen.objEpisodeSearch, "Search result");
		} else {
			click(AMDSearchScreen.objFirstSearchResult(contentName), "Search result");
		}
	}

	public void DownloadFunctionality(String userType, String tabName, String contentName) throws Exception {
		extent.HeaderChildNode("Verify Download CTA Functionality");

		if (userType.equals("Guest")) {
			boolean isDwnld = verifyIsElementDisplayed(AMDConsumptionScreen.objDownloadBtn);
			if (isDwnld) {
				click(AMDConsumptionScreen.objDownloadBtn, "Download button");
				String header = getText(AMDGenericObjects.objgetScreenTitle);
				if (header.equals("Login/Register")) {
					extent.extentLoggerPass("Download", userType + " user is navigated to " + header
							+ " screen post tapping Download CTA form consumption screen");
					logger.info(userType + " user is navigated to " + header
							+ " screen post tapping Download CTA form consumption screen");
					waitTime(3000);
					Back(1);
					if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
						if (tabName.equals("Episode") | tabName.equals("Movies")) {
							click(AMDPlayerScreen.objPauseIcon, "Pause icon");
						}
					}
				} else {
					extent.extentLoggerFail("Download",
							"Failed to navigate into respective screen after clicking Download CTA");
					logger.info("Failed to navigate into respective screen after clicking download CTA");
				}
			} else {
				extent.extentLogger("Download CTA", "Download CTA is not available");
				logger.info("Download CTA is not available");
			}

		} else if (userType.equals("SubscribedUser")) {

			boolean isDwnld = verifyIsElementDisplayed(AMDConsumptionScreen.objDownloadBtn);
			if (isDwnld) {
				click(AMDConsumptionScreen.objDownloadBtn, "Download button");
				waitTime(5000);
				boolean popUp = verifyIsElementDisplayed(AMDConsumptionScreen.objPopUp);
				if (popUp) {
					String popUptitle = getText(AMDConsumptionScreen.objPopUp);
					extent.extentLoggerPass("Verify header", popUptitle + " is displayed");
					logger.info(popUptitle + " pop up is displayed");
					if (tabName.equals("Music")) {
						return;
					} else if (tabName.equals("Shows") | tabName.equals("Episode") | tabName.equals("Movies")) {
						click(AMDConsumptionScreen.objStartDowloadBtn, "Start Download button");
						waitTime(2000);
						click(AMDConsumptionScreen.objDowloadStatus, "Download Icon");
						boolean isPauseDwld = verifyIsElementDisplayed(AMDConsumptionScreen.objPauseDownload);
						if (isPauseDwld) {
							extent.extentLoggerPass("Pause download", "Pause Download call-out option is available");
							logger.info("Pause Download call-out option is available");
						} else {
							extent.extentLoggerFail("Pause download",
									"Pause Download call-out option is not available");
							logger.info("Pause Download call-out option is not available");
						}
						boolean isCancelDwld = verifyIsElementDisplayed(AMDConsumptionScreen.objPauseDownload);
						if (isCancelDwld) {
							extent.extentLoggerPass("Cancel download", "Cancel Download call-out option is available");
							logger.info("Cancel Download call-out option is available");
						} else {
							extent.extentLoggerFail("Cancel download",
									"Cancel Download call-out option is not available");
							logger.info("Cancel Download call-out option is not available");
						}
						boolean isGoToDwld = verifyIsElementDisplayed(AMDConsumptionScreen.objPauseDownload);
						if (isGoToDwld) {
							extent.extentLoggerPass("Go to downloads", "Go to Download call-out option is available");
							logger.info("Go to Download call-out option is available");
						} else {
							extent.extentLoggerFail("Go to download",
									"Go to Download call-out option is not available");
							logger.info("Go to Download call-out option is not available");
						}
						if (tabName.equals("Shows") | tabName.equals("Episode")) {
							click(AMDConsumptionScreen.objCancelDownload, "Cancel Download call-out");
						} else if (tabName.equals("Movies")) {
							waitTime(2000);
							click(AMDConsumptionScreen.objGotoDownloads, "Go to Download call-out");

							String isSelected = getElementPropertyToString("selected", AMDDownloadPage.objmoviestab,
									"Movies tab");
							System.out.println(isSelected);
							if (isSelected.equals("true")) {
								extent.extentLoggerPass("Go to Download call-out",
										"User is navigated to '" + getText(AMDDownloadPage.objmoviestab)
												+ "'in download screen from tab " + tabName);
								logger.info("User is navigated to '" + getText(AMDDownloadPage.objmoviestab)
										+ "'in download screen from tab " + tabName);
							} else {
								extent.extentLoggerFail("Go to Download call-out",
										"Failed to navigate to respective download screen after click on Goto download call-out from tab "
												+ tabName);
								logger.info(
										"Failed to navigate to respective download screen after click on Goto download call-out from tab "
												+ tabName);
							}

							click(AMDDownloadPage.objDownloadProgressIcon, "Download status");
							click(AMDDownloadPage.objCancelDownloadOption, "Cancel download");

							waitTime(3000);
							Back(1);
							click(AMDHomePage.objHomeBtn, "Home tab");
							waitTime(3000);
							verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
							verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
							type(AMDSearchScreen.objSearchBoxBar, contentName + "\n", "Search bar");
							hideKeyboard();
							if (tabName.equals("Music")) {
								click(AMDSearchScreen.objSecondSearchResult(contentName), "Search result");
							} else if (tabName.equals("Episode")) {
								click(AMDSearchScreen.objEpisodeSearch, "Search result");
							} else {
								click(AMDSearchScreen.objFirstSearchResult(contentName), "Search result");
							}
							waitTime(2000);
						}

					}
				} else {
					extent.extentLoggerFail("Verify header", "Download PopUp is not displayed");
					logger.info("Download PopUp is not displayed");
				}
			}
		} else if (userType.equals("NonSubscribedUser")) {
			boolean isDwnld = verifyIsElementDisplayed(AMDConsumptionScreen.objDownloadBtn);
			if (isDwnld) {
				click(AMDConsumptionScreen.objDownloadBtn, "Download button");
				waitTime(5000);
				boolean popUp = verifyIsElementDisplayed(AMDConsumptionScreen.objPopUp);
				if (popUp) {
					String popUptitle = getText(AMDConsumptionScreen.objPopUp);
					extent.extentLoggerPass("Verify header", popUptitle + " is displayed");
					logger.info(popUptitle + " pop up is displayed");
					Back(1);
					if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
						if (tabName.equals("Episode") | tabName.equals("Movies")) {
							click(AMDPlayerScreen.objPauseIcon, "Pause icon");
						}
					}

				} else {
					extent.extentLoggerFail("Verify header", "PopUp title is not displayed");
					logger.info("PopUp title is not displayed");
				}
			} else {
				extent.extentLogger("Download CTA", "Download CTA is not available");
				logger.info("Download CTA is not available");
			}

		}
	}

	public void WatchTrailer(String userType, String tabName, String contentName) throws Exception {
		HeaderChildNode("Verify Watch Trailer Functionality");
		// Verify watch trailer
		boolean isWatchTrailer = verifyIsElementDisplayed(AMDConsumptionScreen.objWatchTrialer);
		if (isWatchTrailer) {
			click(AMDConsumptionScreen.objWatchTrialer, "Watch Trialer");
			waitTime(2000);
			String contentTitle = getText(AMDConsumptionScreen.objContentName);
			if (contentTitle.contains("Trailer")) {
				extent.extentLoggerPass("Watch Trailer",
						"User is navigated to Trailer consumption screen from the tab " + tabName);
				logger.info("User is navigated to Trailer consumption screen from the tab " + tabName);
			} else {
				extent.extentLoggerFail("Watch Trailer", "Failed to navigated to Trailer consumption screen");
				logger.info("Failed to navigated to Trailer consumption screen");
			}

			// Verify download CTA is not available in Trialer consumption screen
			boolean isDownload = verifyIsElementDisplayed(AMDConsumptionScreen.objDownloadBtn);
			if (!isDownload) {
				extent.extentLoggerPass("Download",
						"Download option is not available in Trailer/Promo consumption screen for content : '"
								+ contentName + "'");
				logger.info("Download option is not available in Trailer/Promo consumption screen for content : '"
						+ contentName + "'");
			} else {
				extent.extentLoggerFail("Download",
						"Download option is available in Trailer/Promo consumption screen for content : '" + contentName
								+ "'");
				logger.info("Download option is available in Trailer/Promo consumption screen for content : '"
						+ contentName + "'");
			}

			Back(1);
			if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
				if (tabName.equals("Episode") | tabName.equals("Movies")) {
					click(AMDPlayerScreen.objPauseIcon, "Pause icon");
				}
			}

		} else {
			extent.extentLogger("Watch Trailer", "Watch Trailer CTA is not available");
			logger.info("Watch Trailer CTA is not available");
		}
	}

	public void AudioLanguage(String userType, String contentName, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Audio Language functionality");
		waitTime(2000);
		boolean isCurrentLang = verifyIsElementDisplayed(AMDConsumptionScreen.objCurrentAudioLanguage);
		if (isCurrentLang) {
			extent.extentLogger("Tab name", "Verifying audio language for the content :" + contentName);
			logger.info("Verifying audio language for the content :" + contentName);
			extent.extentLoggerPass("Default Audio Language", "By default the selected audio language is : "
					+ getText(AMDConsumptionScreen.objCurrentAudioLanguage));
			logger.info("By default the selected audio language is : "
					+ getText(AMDConsumptionScreen.objCurrentAudioLanguage));

			// Verify the Audio language
			click(AMDConsumptionScreen.objCurrentAudioLanguage, "Audio Language");
			boolean isPopUp = verifyIsElementDisplayed(AMDConsumptionScreen.objPopUp);
			if (isPopUp) {
				extent.extentLoggerPass("Verify Audio Language", "Audio Language Pop up is displayed");
				int size = getDriver().findElements(AMDConsumptionScreen.objSubtitleAndAudioLangItems).size();
				if (size == 0) {
					extent.extentLogger("Verify the audio languages",
							"There is no Audio languages availabel for the content : " + contentName);
					logger.info("There is no Audio languages availabel for the content : " + contentName);
				} else {
					for (int i = 1; i <= size; i++) {
						String AudioLanguageName = getText(AMDConsumptionScreen.objSubtitleAndAudioLangItems(i));
						extent.extentLogger("Verify Audio language", "Audio Language :" + AudioLanguageName);
						logger.info("Audio Language :" + AudioLanguageName);
					}
					Back(1);
					boolean pauseIcon = verifyIsElementDisplayed(AMDPlayerScreen.objPauseIcon);
					if (pauseIcon) {
						extent.extentLoggerPass("Verify Playback",
								"Playback is Auto played after closing the Audio Language Popup");
						logger.info("Playback is Auto played after closing the Audio Language Popup");
					} else {
						extent.extentLoggerFail("Verify Playback",
								"Failed to Resume the playback after closing Audio Language Popup");
						logger.info("Failed to Resume the playback after closing Audio Language Popup");
					}
				}
			} else {
				extent.extentLoggerFail("Audio language", "The Audio language Pop up is not displayed");
				logger.info("The Audio language Pop up is not displayed");
			}

		} else {
			extent.extentLogger("Audio Language",
					"No default audio language is available for the content :" + contentName);
			logger.info("No default audio language is available for the content :" + contentName);
		}

	}

	public void SubTitles(String userType, String contentName, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Subtitles functionality for the tab : \"" + tabName + "\"");
		if (tabName.equals("Music")) {
			boolean isCurrentLang = verifyIsElementDisplayed(AMDConsumptionScreen.objCurrentSubTitle);
			if (isCurrentLang) {
				extent.extentLoggerFail("Music tab", "Subtitle is available for the Music content");
				logger.info("Subtitle is available for the Music content");
			} else {
				extent.extentLoggerPass("Music tab", "Subtitle is not available for Music tab");
				logger.info("Subtitle is not available for Music tab");
			}
		} else {
			boolean isCurrentSub = verifyIsElementDisplayed(AMDConsumptionScreen.objCurrentSubTitle);
			if (isCurrentSub) {
				String defaultSubtitle = getText(AMDConsumptionScreen.objCurrentSubTitle);
				if (defaultSubtitle.equalsIgnoreCase("Off")) {
					extent.extentLogger("Tab name", "Verifying Subtitle option  for the content : " + contentName);
					extent.extentLoggerPass("Default Subtitle ",
							"By default the selected Subtitle  is : " + defaultSubtitle);
					logger.info("By default the selected Subtitle  is : " + defaultSubtitle);
				} else {
					extent.extentLoggerFail("Verify default subtitle", "By default Subtitle is not displayed as 'Off'");
					logger.info("By default Subtitle is not displayed as 'Off'");
				}
				// Verify Subtitle
				click(AMDConsumptionScreen.objCurrentSubTitle, "Subtitle");
				boolean isPopUp = verifyIsElementDisplayed(AMDConsumptionScreen.objPopUp);
				if (isPopUp) {
					extent.extentLoggerPass("Verify Sutitle PopUp", "Subtitles Pop up is displayed");
					int size = getDriver().findElements(AMDConsumptionScreen.objSubtitleAndAudioLangItems).size();
					if (size == 0) {
						extent.extentLogger("Verify the Subtiles",
								"There is no Subtitles available for the content : " + contentName);
						logger.info("There is no Subtitles availabel for the content : " + contentName);
					} else {
						for (int i = 1; i <= size; i++) {
							String SubtitleName = getText(AMDConsumptionScreen.objSubtitleAndAudioLangItems(i));
							extent.extentLogger("Verify Subtitle ", "Subtitle :" + SubtitleName);
							logger.info("Subtitle :" + SubtitleName);
						}
						Back(1);
						boolean pauseIcon = verifyIsElementDisplayed(AMDPlayerScreen.objPauseIcon);
						if (pauseIcon) {
							extent.extentLoggerPass("Verify Playback",
									"Playback is Auto played after closing the Subtitle Popup");
							logger.info("Playback is Auto played after closing the Subtitle Popup");
						} else {
							extent.extentLoggerFail("Verify Playback",
									"Failed to Resume the playback after closing Subtitle Popup");
							logger.info("Failed to Resume the playback after closing Subtitle Popup");
						}
					}
				} else {
					extent.extentLoggerFail("Subtitle", "The Subtitle Pop up is not displayed");
					logger.info("The Subtitle Pop up is not displayed");
				}
			} else {
				extent.extentLogger("Default subtitle ", "Subtitle is not available for the content :" + contentName);
				logger.info("Subtitle is not available for the content :" + contentName);
			}
		}
	}

	public void ValidateConfiguredRails(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Configured trays for the tab : \"" + tabName + "\"");
		if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
			if (tabName.equals("Episode") | tabName.equals("Movies")) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
				click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			}
		}

		if (tabName.equals("Shows") | tabName.equals("Episode")) {

			boolean upnextRail = verifyIsElementDisplayed(AMDGenericObjects.objCarouselTitle("Up Next"));
			if (upnextRail) {
				int size = getDriver().findElements(AMDGenericObjects.objContentNameInTray).size();
				ArrayList<String> contentName = new ArrayList<>();
				for (int i = 1; i <= size; i++) {
					contentName.add(getText(AMDGenericObjects.objContentNameInTray(i)));
				}
				for (int j = 0; j < contentName.size(); j++) {
					extent.extentLoggerPass("Content name",
							"The content Name at index " + (j + 1) + " is " + contentName.get(j));
					logger.info("The content Name at index " + (j + 1) + " is " + contentName.get(j));
				}

				String firstContentName = getText(AMDGenericObjects.objContentNameInTray(1));
				SwipeRailContentCards(AMDGenericObjects.objCarouselTitle("Up Next"));
				String secondContentName = getText(AMDGenericObjects.objContentNameInTray(2));
				if (!firstContentName.equals(secondContentName)) {
					extent.extentLoggerPass("Swipe", "User can Swipe the content cards from 'Up Next Rail'");
					logger.info("User can Swipe the content cards from 'Up Next Rail'");
				} else {
					extent.extentLoggerFail("Swipe", "User can not Swipe the content cards from 'Up Next Rail'");
					logger.info("User can not Swipe the content cards from 'Up Next Rail'");
				}
				extent.extentLoggerPass("Verify tray",
						"Upnext Rail is displayed in consumption screen for the tab :" + tabName);
				logger.info("Upnext Rail is displayed in consumption screen for the tab :" + tabName);

				// Verify the navigation
//			 waitTime(3000);
//			 waitForElementDisplayed(AMDConsumptionScreen.objArrow, 10000); 
//			 verifyElementPresentAndClick(AMDGenericObjects.objViewAllBtn("Up Next"),"Right Arrow in Upnext tray");
//			 
//			 String header =  getText(AMDGenericObjects.objCheckTitle("Up Next"));
//			 if(header.equalsIgnoreCase("Up Next")) {
//				 extent.extentLoggerPass("Collection screen", 
//						 "User is navigated to Upnext collection screen by tapping Arrow mark from Upnext Rail");
//				 logger.info("User is navigated to Upnext collection screen by tapping Arrow mark from Upnext Rail");
//			 }else {
//				 extent.extentLogger("Collection screen", 
//						 "Failed to navigated to Upnext collection screen by tapping Arrow mark from Upnext Rail");
//				 logger.info("Failed to navigated to Upnext collection screen by tapping Arrow mark from Upnext Rail");
//			 } 
			}
		}

		if (tabName.equals("Movies")) {
//		 
			boolean relatedMovies = verifyIsElementDisplayed(AMDGenericObjects.objCarouselTitle("Related Videos"));
			if (relatedMovies) {
				int size = getDriver().findElements(AMDGenericObjects.objContentNameInTray).size();
				ArrayList<String> contentName = new ArrayList<>();
				for (int i = 1; i <= size; i++) {
					contentName.add(getText(AMDGenericObjects.objContentNameInTray(i)));
				}
				for (int j = 0; j < contentName.size(); j++) {
					extent.extentLoggerPass("Content name",
							"The content Name at index " + (j + 1) + " is " + contentName.get(j));
					logger.info("The content Name at index " + (j + 1) + " is " + contentName.get(j));
				}
				extent.extentLoggerPass("Verify tray",
						"Related Videos tray is displayed in consumption screen for the tab :" + tabName);
				logger.info("Related Videos tray is displayed in consumption screen for the tab :" + tabName);
				// Verify the navigation
//			 waitTime(3000);
//			 waitForElementDisplayed(AMDConsumptionScreen.objArrow, 10000); 
//			 verifyElementPresentAndClick(AMDConsumptionScreen.objArrow,"Right Arrow in Related Videos tray");
//			 String header =  getText(AMDGenericObjects.objCheckTitle("Related Videos"));
//			 if(header.equalsIgnoreCase("Related Videos")) {
//				 extent.extentLoggerPass("Collection screen", 
//						 "User is navigated to the Related Videos collection screen by tapping Arrow mark from Related Videos");
//				 logger.info("User is navigated to Related Videos collection screen by tapping Arrow mark from Related Videos");
//			 }else {
//				 extent.extentLogger("Collection screen", 
//						 "Failed to navigated to collection screen by tapping Arrow mark from Related Videos Rail");
//				 logger.info("Failed to navigated to collection screen by tapping Arrow mark from Related Videos Rail");
//			 } 
			}
		}
		if (tabName.equals("Music")) {

			boolean recommendedRail = verifyIsElementDisplayed(AMDGenericObjects.objCarouselTitle("Recommended"));
			if (recommendedRail) {
				int size = getDriver().findElements(AMDGenericObjects.objContentNameInTray).size();
				ArrayList<String> contentName = new ArrayList<>();
				for (int i = 1; i <= size; i++) {
					contentName.add(getText(AMDGenericObjects.objContentNameInTray(i)));
				}
				for (int j = 0; j < contentName.size(); j++) {
					extent.extentLoggerPass("Content name",
							"The content Name at index " + (j + 1) + " is " + contentName.get(j));
					logger.info("The content Name at index " + (j + 1) + " is " + contentName.get(j));
				}
				extent.extentLoggerPass("Verify tray",
						"Recommended Rail is displayed in consumption screen for the tab :" + tabName);
				logger.info("Recommended Rail is displayed in consumption screen for the tab :" + tabName);
				extent.extentLoggerPass("Verify tray",
						"Recommended tray is displayed in consumption screen for the tab :" + tabName);
				logger.info("Recommended tray is displayed in consumption screen for the tab :" + tabName);

				String firstContentName = getText(AMDGenericObjects.objContentNameInTray(1));
				SwipeRailContentCards(AMDGenericObjects.objCarouselTitle("Recommended"));
				String secondContentName = getText(AMDGenericObjects.objContentNameInTray(2));
				if (!firstContentName.equals(secondContentName)) {
					extent.extentLoggerPass("Swipe", "User can Swipe the content cards from 'Recommended'");
					logger.info("User can Swipe the content cards from 'Recommended'");
				} else {
					extent.extentLoggerFail("Swipe", "User can not Swipe the content cards from 'Recommended'");
					logger.info("User can not Swipe the content cards from 'Recommended'");
				}

				// Verify the navigation
//			 waitTime(3000);
//			 waitForElementDisplayed(AMDConsumptionScreen.objArrow, 10000); 
//			 verifyElementPresentAndClick(AMDConsumptionScreen.objArrow,"Right Arrow in Recommended tray");
//			 String header =  getText(AMDGenericObjects.objCheckTitle("Recommended"));
//			 if(header.equalsIgnoreCase("Recommended")) {
//				 extent.extentLoggerPass("Collection screen", 
//						 "User is navigated to the Recommended collection screen by tapping Arrow mark from Recommended");
//				 logger.info("User is navigated to Recommended collection screen by tapping Arrow mark from Recommended");
//			 }else {
//				 extent.extentLogger("Collection screen", 
//						 "Failed to navigated to Recommended collection screen by tapping Arrow mark from Recommended Rail");
//				 logger.info("Failed to navigated to Recommended collection screen by tapping Arrow mark from Recommended Rail");
//			 } 
			} else {
				extent.extentLogger("Recommended Rail", "Recommended Rail is not displayed in Music tab");
				logger.info("Recommended Rail is not displayed in Music tab");
			}
		}
	}

	/**
	 * Author : Bhavana Module : Club package
	 */

	public void clubPackFeaturesValidation(String userType, String searchcontent) throws Exception {
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			System.out.println("Validating Subscribe CTA present on player for club content without trailer");
			verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
			waitTime(2000);
			verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBar, searchcontent, "Search bar");
			waitTime(2000);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Searched result");
			waitTime(5000);
			verifyElementExist(AMDClubPack.objSubscribeinfoOnPlayer, "Subscribe info on the player for Club content");
			if (verifyIsElementDisplayed(AMDClubPack.objSubscribeinfoOnPlayer)) {
				String text = getText(AMDClubPack.objSubscribeinfoOnPlayer);
				System.out.println(text);
				logger.info(text + " is displayed on player for club content");
				extent.extentLoggerPass("Consumption screen", text + " is displayed on player for club content");
			} else {
				logger.error(
						getText(AMDClubPack.objSubscribeinfoOnPlayer) + " is NOT displayed on player for club content");
				extent.extentLoggerFail("Consumption screen",
						getText(AMDClubPack.objSubscribeinfoOnPlayer) + " is NOT displayed on player for club content");
			}
			verifyElementExist(AMDClubPack.objSubscribetoClubCTAOnPlayer,
					"Subscribe to Club CTA on player for club content");
			verifyElementExist(AMDClubPack.objLoginCTAOnPlayer, "Login CTA on player for club content");
			verifyElementExist(AMDClubPack.objGetClubCTABelowPlayer, "Get Club CTA below the player");
			if (verifyIsElementDisplayed(AMDClubPack.objSubscribetoClubCTAOnPlayer)) {
				click(AMDClubPack.objSubscribetoClubCTAOnPlayer, "Subscribe to Club CTA on player for club content");
			}
			waitTime(3000);
			verifyElementExist(AMDClubPack.objSubscribePopup, "Subscribe popup");
			verifyElementExist(AMDClubPack.objPlanlistonSubscribePopup, "Pack list");
			verifyElementExist(AMDClubPack.objClubPackPlan, "Club Pack plan");
			verifyElementExist(AMDClubPack.objClubIconforClubPlan, "Only Club Icon for Club pack plan");
			verifyElementExist(AMDClubPack.objPremiumIconInSubscribePopup, "Premium Icon for All Acess plan");
			verifyElementExist(AMDClubPack.objClubIconInSubscribePopup, "Club Icon for All Acess plan");

			String Plan1 = getText(AMDClubPack.objPack1InSubscribePopup);
			System.out.println(Plan1);
			logger.info("Plan 1 " + Plan1 + " is displayed");
			extent.extentLogger("Subscribe to Club popup", "Plan 1 " + Plan1 + " is displayed");

			String Plan2 = getText(AMDClubPack.objPack2InSubscribePopup);
			System.out.println(Plan2);
			logger.info("Plan 2 " + Plan2 + " is displayed");
			extent.extentLogger("Subscribe to Club popup", "Plan 2 " + Plan2 + " is displayed");

			String Plan3 = getText(AMDClubPack.objPack3InSubscribePopup);
			System.out.println(Plan3);
			logger.info("Plan 3 " + Plan3 + " is displayed");
			extent.extentLogger("Subscribe to Club popup", "Plan 3 " + Plan3 + " is displayed");

			String Plan4 = getText(AMDClubPack.objPack4InSubscribePopup);
			System.out.println(Plan4);
			logger.info("Plan 4 " + Plan4 + " is displayed");
			extent.extentLogger("Subscribe to Club popup", "Plan 4 " + Plan4 + " is displayed");

			String Plan5 = getText(AMDClubPack.objPack5InSubscribePopup);
			System.out.println(Plan5);
			logger.info("Plan 5 " + Plan5 + " is displayed");
			extent.extentLogger("Subscribe to Club popup", "Plan 5 " + Plan5 + " is displayed");

			if (verifyIsElementDisplayed(AMDClubPack.objProceedButtonInSubscribePopup)) {
				click(AMDClubPack.objProceedButtonInSubscribePopup, "Proceed Button in Subcribe popup");
			}
			waitTime(4000);
			if (verifyIsElementDisplayed(AMDClubPack.objAcountInfoInSubscribePage)) {
				logger.info("User is navigated to Acount info screen on selecting club plan");
				extent.extentLoggerPass("Subscribe popup",
						"User is navigated to Acount info screen on selecting club plan");
			} else {
				logger.error("User is unable to navigate to Acount info screen on selecting club plan");
				extent.extentLoggerFail("Subscribe popup",
						"User is unable to navigate to Acount info screen on selecting club plan");
			}
			Back(1);
			click(AMDClubPack.objLoginCTAOnPlayer, "Login CTA on player for club content");
			waitTime(4000);
			if (verifyIsElementDisplayed(AMDClubPack.objLoginScreen)) {
				String Username = getParameterFromXML("ClubUserName");
				String Password = getParameterFromXML("ClubPassword");
				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
				type(AMDLoginScreen.objPasswordField, Password, "Password field");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
				waitTime(5000);
			}
			if (verifyIsElementDisplayed(AMDPlayerScreen.objPlayerScreen)) {
				logger.info("User is navigated back to consumption screen on successfull login");
				extent.extentLoggerPass("Consumption screen",
						"User is navigated back to consumption screen on successfull login");
			} else {
				logger.error("User fails to navigate back to consumption screen on successfull login");
				extent.extentLoggerFail("Consumption screen",
						"User fails to navigate back to consumption screen on successfull login");
			}
			Back(1);
			click(AMDHomePage.HomeIcon, "Home Icon");
			ZNALogoutMethod();
			waitTime(4000);
			validateSubscribepopupForPremiumContent("Prema Baraha");
		}
	}

	public void validateSubscribepopupForPremiumContent(String searchcontent) throws Exception {
		System.out.println("Validating Subscribe popup for Premium content");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
		waitTime(2000);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBar, searchcontent, "Search bar");
		waitTime(2000);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Searched result");
		waitTime(5000);
		click(AMDClubPack.objSubscribetoPremiumCTAOnPlayer, "Subscribe to Premium CTA");
		verifyElementExist(AMDClubPack.objSubscribePopup, "Subscribe popup");
		String Plan1 = getText(AMDClubPack.objPack1InSubscribePopup);
		System.out.println(Plan1);
		logger.info("Plan 1 " + Plan1 + " is displayed");
		extent.extentLogger("Subscribe to Premium popup", "Plan 1 " + Plan1 + " is displayed");

		String Plan2 = getText(AMDClubPack.objPack2InSubscribePopup);
		System.out.println(Plan2);
		logger.info("Plan 2 " + Plan2 + " is displayed");
		extent.extentLogger("Subscribe to Premium popup", "Plan 2 " + Plan2 + " is displayed");

		String Plan3 = getText(AMDClubPack.objPack3InSubscribePopup);
		System.out.println(Plan3);
		logger.info("Plan 3 " + Plan3 + " is displayed");
		extent.extentLogger("Subscribe to Premium popup", "Plan 3 " + Plan3 + " is displayed");

		String Plan4 = getText(AMDClubPack.objPack4InSubscribePopup);
		System.out.println(Plan4);
		logger.info("Plan 4 " + Plan4 + " is displayed");
		extent.extentLogger("Subscribe to Premium popup", "Plan 4 " + Plan4 + " is displayed");
		Back(2);
		click(AMDHomePage.HomeIcon, "Home Icon");
	}

	/**
	 * Author : Kushal
	 */

	public void ZeeApplicasterLoginForClubPack(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Login Functionality");
		String UserType = getParameterFromXML("userType");
		if (UserType.equals("Guest")) {
			extent.extentLogger("userType", "UserType : Guest");
		}
		verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
		waitTime(3000);

		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitTime(1000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip link");
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as Non-Subscribed User for Settings");
			String SUsername = getParameterFromXML("NonsubscribedUserName");
			String SPassword = getParameterFromXML("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User for Settings");
			String ClubUsername = getParameterFromXML("ClubUserName");
			String ClubPassword = getParameterFromXML("ClubPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, ClubUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, ClubPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;
		}
	}

	/**
	 * Author : Bhavana
	 * 
	 * @param userType
	 * @param searchcontent
	 * @throws Exception
	 */
	public void ValidateSubscribeAndLoginCTAForClubContent(String userType, String searchcontent) throws Exception {
		if ((userType.equals("Guest")) | (userType.equals("NonSubscribedUser"))) {
			System.out.println("Validating Subscribe CTA present on player for club content without trailer");
			HeaderChildNode("Validating Subscribe CTA present on player for club content without trailer");
			verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
			waitTime(2000);
			verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBar, searchcontent, "Search bar");
			waitTime(2000);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Searched result");
			waitTime(5000);
			verifyElementExist(AMDClubPack.objSubscribeinfoOnPlayer, "Subscribe info on the player for Club content");
			boolean objSubinfo = verifyIsElementDisplayed(AMDClubPack.objSubscribeinfoOnPlayer);
			if (objSubinfo) {
				String text = getText(AMDClubPack.objSubscribeinfoOnPlayer);
				System.out.println(text);
				logger.info(text + " is displayed on player for club content");
				extent.extentLoggerPass("Consumption screen", text + " is displayed on player for club content");
			} else {
				logger.error(
						getText(AMDClubPack.objSubscribeinfoOnPlayer) + " is NOT displayed on player for club content");
				extent.extentLoggerFail("Consumption screen",
						getText(AMDClubPack.objSubscribeinfoOnPlayer) + " is NOT displayed on player for club content");
			}
			verifyElementExist(AMDClubPack.objSubscribetoClubCTAOnPlayer,
					"Subscribe to Club CTA on player for club content");
			if (userType.equals("Guest")) {
				verifyElementExist(AMDClubPack.objLoginCTAOnPlayer, "Login CTA on player for club content");
			}
			verifyElementExist(AMDClubPack.objGetClubCTABelowPlayer, "Get Club CTA below the player");
			boolean objsubToClub = verifyIsElementDisplayed(AMDClubPack.objSubscribetoClubCTAOnPlayer);
			if (objsubToClub) {
				click(AMDClubPack.objSubscribetoClubCTAOnPlayer, "Subscribe to Club CTA on player for club content");
			}
			waitTime(3000);
			verifyElementExist(AMDClubPack.objSubscribePopup, "Subscribe popup");
			verifyElementExist(AMDClubPack.objPlanlistonSubscribePopup, "Pack list");
			verifyElementExist(AMDClubPack.objClubPackPlan, "Club Pack plan");
			verifyElementExist(AMDClubPack.objClubIconforClubPlan, "Only Club Icon for Club pack plan");
			verifyElementExist(AMDClubPack.objPremiumIconInSubscribePopup, "Premium Icon for All Acess plan");
			verifyElementExist(AMDClubPack.objClubIconInSubscribePopup, "Club Icon for All Acess plan");
			if (getAttributValue("clickable", AMDClubPack.objPremiumIconInSubscribePopup).equals("false")) {
				logger.info("Premium Icon is not clickable on Get premium popup");
				extent.extentLoggerPass("Popup", "Premium Icon is not clickable on Get premium popup");
			} else {
				logger.error("Premium Icon is clickable on Get premium popup");
				extent.extentLoggerFail("popup", "Premium Icon is clickable on Get premium popup");
			}
			if (getAttributValue("clickable", AMDClubPack.objClubIconInSubscribePopup).equals("false")) {
				logger.info("Club Icon is not clickable on Get premium popup");
				extent.extentLoggerPass("Popup", "Club Icon is not clickable on Get premium popup");
			} else {
				logger.error("Club Icon is clickable on Get premium popup");
				extent.extentLoggerFail("popup", "Club Icon is clickable on Get premium popup");
			}

			String Plan1 = getText(AMDClubPack.objPack1InSubscribePopup);
			System.out.println(Plan1);
			logger.info("Plan 1 " + Plan1 + " is displayed");
			extent.extentLogger("Subscribe to Club popup", "Plan 1 " + Plan1 + " is displayed");

			String Plan2 = getText(AMDClubPack.objPack2InSubscribePopup);
			System.out.println(Plan2);
			logger.info("Plan 2 " + Plan2 + " is displayed");
			extent.extentLogger("Subscribe to Club popup", "Plan 2 " + Plan2 + " is displayed");

			String Plan3 = getText(AMDClubPack.objPack3InSubscribePopup);
			System.out.println(Plan3);
			logger.info("Plan 3 " + Plan3 + " is displayed");
			extent.extentLogger("Subscribe to Club popup", "Plan 3 " + Plan3 + " is displayed");

			String Plan4 = getText(AMDClubPack.objPack4InSubscribePopup);
			System.out.println(Plan4);
			logger.info("Plan 4 " + Plan4 + " is displayed");
			extent.extentLogger("Subscribe to Club popup", "Plan 4 " + Plan4 + " is displayed");

			String Plan5 = getText(AMDClubPack.objPack5InSubscribePopup);
			System.out.println(Plan5);
			logger.info("Plan 5 " + Plan5 + " is displayed");
			extent.extentLogger("Subscribe to Club popup", "Plan 5 " + Plan5 + " is displayed");

			SwipeUntilFindElement(AMDGenericObjects.objText("Terms of Use"), "Up");
			boolean objproceed = verifyIsElementDisplayed(AMDClubPack.objProceedButtonInSubscribePopup);
			if (objproceed) {
				click(AMDClubPack.objProceedButtonInSubscribePopup, "Proceed Button in Subcribe popup");
			}
			waitTime(4000);
			if (userType.equals("Guest")) {
				boolean objAccountinfo = verifyIsElementDisplayed(AMDClubPack.objAcountInfoInSubscribePage);
				if (objAccountinfo) {
					logger.info("User is navigated to Account info screen on selecting club plan");
					extent.extentLoggerPass("Subscribe popup",
							"User is navigated to Account info screen on selecting club plan");
				} else {
					logger.error("User is unable to navigate to Account info screen on selecting club plan");
					extent.extentLoggerFail("Subscribe popup",
							"User is unable to navigate to Account info screen on selecting club plan");
				}
			}
			if (userType.equals("NonSubscribedUser")) {
				Swipe("DOWN", 1);
				waitTime(1000);
				boolean objpayment = verifyIsElementDisplayed(AMDClubPack.objpaymentScreenInSubscribepopup);
				if (objpayment) {
					logger.info("User is navigated to Payment options screen on selecting club plan");
					extent.extentLoggerPass("Subscribe popup",
							"User is navigated to Payment options screen on selecting club plan");
				} else {
					logger.error("User is unable to navigate to Payment options screen on selecting club plan");
					extent.extentLoggerFail("Subscribe popup",
							"User is unable to navigate to Payment options screen on selecting club plan");
				}
			}
			Back(1);
			if (userType.equals("Guest")) {
				HeaderChildNode("Validating Login CTA present on player for club content");
				click(AMDClubPack.objLoginCTAOnPlayer, "Login CTA on player for club content");
				waitTime(4000);
				boolean objlogin = verifyIsElementDisplayed(AMDClubPack.objLoginScreen);
				if (objlogin) {
					String Username = getParameterFromXML("ClubUserName");
					String Password = getParameterFromXML("ClubPassword");
					verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
					type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
					verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
					verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
					type(AMDLoginScreen.objPasswordField, Password, "Password field");
					hideKeyboard();
					verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
					waitTime(12000);
				}
				boolean objPlayer = verifyIsElementDisplayed(AMDPlayerScreen.objPlayerScreen);
				if (objPlayer) {
					logger.info("User is navigated back to consumption screen on successfull login");
					extent.extentLoggerPass("Consumption screen",
							"User is navigated back to consumption screen on successfull login");
				} else {
					logger.error("User fails to navigate back to consumption screen on successfull login");
					extent.extentLoggerFail("Consumption screen",
							"User fails to navigate back to consumption screen on successfull login");
				}
				Back(1);
				click(AMDHomePage.HomeIcon, "Home Icon");
				ZNALogoutMethod();
				waitTime(4000);
			} else {
				logger.info("Login CTA for club content is not applicable for " + userType);
				extent.extentLogger("Login CTA", "Login CTA for club content is not applicable for " + userType);
				Back(1);
			}
			click(AMDHomePage.HomeIcon, "Home Icon");
			validateSubscribepopupForPremiumContent("Prema Baraha");
		} else {
			logger.info("Validation of Subscribe and Login CTA for club content is not applicable for " + userType);
			extent.extentLogger("Subscribe CTA",
					"Validation of Subscribe and Login CTA for club content is not applicable for " + userType);
		}
	}

	public void UpgradepopupForPremiumcontentWithTrailer(String searchContent) throws Exception {
		extent.HeaderChildNode("Validating Upgrade popup for Premium content having Trailer");
		System.out.println("Validating Upgrade popup for Premium content having Trailer");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
		waitTime(2000);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBar, searchContent, "Search bar");
		waitTime(2000);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Searched result");
		waitTime(2000);
//		scrubVideoToLast(AMDPlayerScreen.objProgressBar);
		scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
		boolean objupgradePopup = verifyIsElementDisplayed(AMDClubPack.objUpgradepopuptitle);
		if (objupgradePopup) {
			logger.info("Upgarde popup is displayed at the end of trailer for Premium content ");
			extent.extentLoggerPass("Consumption screen",
					"Upgarde popup is displayed at the end of trailer for Premium content");
		} else {
			logger.error("Upgarde popup is NOT displayed at the end of trailer for Premium content");
			extent.extentLoggerFail("Consumption screen",
					"Upgarde popup is NOT displayed at the end of trailer for Premium content");
		}
		Back(2);
		click(AMDHomePage.HomeIcon, "Home Icon");
	}

	public void SubscribepopupForClubcontentWithTrailer(String userType, String searchContent) throws Exception {
		extent.HeaderChildNode("Validating subscribe popup for Club content having Trailer");
		System.out.println("Validating subscribe popup for Club content having Trailer");

		if (userType.equalsIgnoreCase("Guest") | userType.equalsIgnoreCase("NonSubscribedUser")) {
			verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
			waitTime(2000);
			verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBar, searchContent, "Search bar");
			waitTime(2000);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Searched result");
			waitTime(5000);
//			scrubVideoToLast(AMDPlayerScreen.objProgressBar);
			scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
			boolean objSubscribePopup = verifyIsElementDisplayed(AMDClubPack.objSubscribePopup);
			if (objSubscribePopup) {
				logger.info("Subscribe popup is displayed at the end of trailer for Club content ");
				extent.extentLoggerPass("Consumption screen",
						"Subscribe popup is displayed at the end of trailer for Club content");
			} else {
				logger.error("Subscribe popup is NOT displayed at the end of trailer for Club content");
				extent.extentLoggerFail("Consumption screen",
						"Subscribe popup is NOT displayed at the end of trailer for Club content");
			}
			Back(2);
			click(AMDHomePage.HomeIcon, "Home Icon");
		} else {
			logger.info("Validtation of Subscribe popup for club content is not applicable for " + userType);
			extent.extentLogger("Subscribe CTA",
					"Validation of Subscribe popup for club content is not applicable for " + userType);
		}
	}

	public void scrubVideoToLast(By byLocator1) throws Exception {
		String beforeSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time before seeking : " + timeToSec(beforeSeek));
		extent.extentLogger("Seek", "Current time before seeking in seconds: " + timeToSec(beforeSeek));

		WebElement element = getDriver().findElement(byLocator1);
		Dimension size = element.getSize();
		int startx = (int) (size.width);
		int startX = startx + 180;
		System.out.println(startX);
		SwipeAnElement(element, startX, 0);
		waitTime(2000);

	}

	public void validateClubIconOnContentCards(String userType) throws Exception {
		extent.HeaderChildNode("Validating Club icon on Content tray and Content listing screen");
		System.out.println("Validating Club icon on Content tray and Content listing screen");

		if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
			waitTime(3000);
			SelectTopNavigationTab("ZEE5 Originals");
			waitTime(5000);
			SwipeUntilFindElement(AMDClubPack.objBestOfZee5OriginalsTray, "UP");
			boolean result = verifyIsElementDisplayed(AMDClubPack.objClubIconOnFirstCardOfTray);
			if (result) {
				logger.info("Club icon is displayed on club content card");
				extent.extentLoggerPass("Landing page", "Club icon is displayed on club content card");
			} else {
				logger.error("Club icon is NOT displayed on club content card");
				extent.extentLoggerFail("Landing screen", "Club icon is NOT displayed on club content card");
			}
			waitTime(3000);
			click(AMDClubPack.objBestOfZee5OriginalsTray, "Best of ZEE5 Originals in Kannada tray");
			waitTime(3000);
			boolean result2 = verifyIsElementDisplayed(AMDClubPack.objclubIconInContentListingScreen);
			if (result2) {
				logger.info("Club icon is displayed in content listing screen");
				extent.extentLoggerPass("Landing page", "Club icon is displayed in content listing screen");
			} else {
				logger.error("Club icon is NOT displayed in content listing screen");
				extent.extentLoggerFail("Landing screen", "Club icon is NOT displayed in content listing screen");
			}
			Back(1);
			click(AMDHomePage.HomeIcon, "Home Icon");
		} else {
			logger.info("Validating Club icon on Landing screen is not applicable for " + userType);
			extent.extentLoggerPass("Landing page",
					"Validating Club icon on Landing screen is not applicable for " + userType);
		}
	}

	public void ValidateClubIconForRecoTray(String userType) throws Exception {
		extent.HeaderChildNode("Validating Club Icon for Reco rails/tray");
		System.out.println("Validating Club Icon for Reco rails/tray");

		if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
			waitTime(6000);
			SwipeUntilFindElement(AMDClubPack.objRecoMovieTray, "UP");
			waitTime(3000);
			boolean reco = verifyIsElementDisplayed(AMDClubPack.objClubicononRecoTrays);
			if (reco) {
				logger.info("Club icon is displayed on club content card under Reco Tray");
				extent.extentLoggerPass("Landing page", "Club icon is displayed on club content card under Reco Tray");
			} else {
				logger.error("Club icon is NOT displayed on club content card under Reco Tray");
				extent.extentLoggerFail("Landing screen",
						"Club icon is NOT displayed on club content card under Reco Tray");
			}
		} else {
			logger.info("Validating Club icon for Reco tray is not applicable for " + userType);
			extent.extentLoggerPass("Landing page",
					"Validating Club icon for Reco tray is not applicable for " + userType);
		}
		click(AMDHomePage.HomeIcon, "Home Icon");
	}

	public void ZeeWEBPWALogin(String LoginMethod) throws Exception {
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
			String Username = getParameterFromXML("USMNonsubscribedUserName");
			String Password = getParameterFromXML("USMNonsubscribedPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(8000);
			verifyElementPresent(AMDUserSessionManagement.objLoginPageHeader, "Login page");
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
			String SubscribedUsername = getParameterFromXML("SubscribedUserName");
			String SubscribedPassword = getParameterFromXML("SubscribedPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(8000);
			verifyElementPresent(AMDUserSessionManagement.objLoginPageHeader, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;
		}
	}

	public void dismissDisplayContentLanguagePopUp() throws Exception {
		extent.HeaderChildNode("Dismiss Display and Content Language Pop Ups");
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup, 90,
				"Continue on Display Language Pop Up");
		Thread.sleep(5000);
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup, 10,
				"Continue on Content Language Pop Up");
	}

	public void webAddContentToWatchlist() throws Exception {
		extent.HeaderChildNode("Adding Content to Watchlist");
		JSClick(AMDUserSessionManagement.objMoviesTab, "Movies tab");
		waitTime(10000);
		partialScrollDown();
		Actions actions = new Actions(getWebDriver());
		WebElement contentcard = getWebDriver()
				.findElement(By.xpath("(//div[@class='slick-list']//div[@class='content'])[1]"));
		actions.moveToElement(contentcard).perform();
		JSClick(PWAPremiumPage.objContentCardWatchlistBtn, "WatchList icon on content card");
	}

	public void webMyProfile(String usertype) throws Exception {
		extent.HeaderChildNode("My Profile in Web");

		click(PWALandingPages.objWebProfileIcon, "Profile Icon");
		waitTime(5000);
		click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon in My profile dropdown");
		Actions actions = new Actions(getWebDriver());
		WebElement contentcard = getWebDriver().findElement(PWAHomePage.objZeeLogo);
		actions.moveToElement(contentcard).perform();

		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			verifyElementPresent(AMDUserSessionManagement.objSubscriptionBannerInMyProfileSection,
					"Subscription banner under My profile section");
		} else {
			boolean var = checkElementExist(AMDUserSessionManagement.objMyPlanProfile, "Premium Pack");
			if (var == true) {
				String packPrice = getText(AMDUserSessionManagement.objPremiumPackPrice);
				WebPWAMyProfile.add(packPrice);
				String packValidityDate = getText(AMDUserSessionManagement.objPremiumPackValidityDate);
				WebPWAMyProfile.add(packValidityDate);
			}
		}

		click(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		boolean name = verifyElementExist(PWAHamburgerMenuPage.objEditProfileFirstName, "Name field");
		if (name == true) {
			String WebFirstName = getAttributValue("value", PWAHamburgerMenuPage.objEditProfileFirstName);
			WebPWAMyProfile.add(WebFirstName);
		}
		boolean gender = verifyElementExist(AMDUserSessionManagement.objGenderValue, "Gender field");
		if (gender == true) {
			String WebGender = getText(AMDUserSessionManagement.objGenderValue);
			WebPWAMyProfile.add(WebGender);
		}

		verifyElementExist(PWAHamburgerMenuPage.objEditProfileDOB, "Date of birth field");
		String Day = getText(AMDUserSessionManagement.objDateOfBirthValue("Day"));
		int lenOfDate = Day.length();
		if (lenOfDate == 1) {
			Day = "0" + Day;
		}
		String Month = getText(AMDUserSessionManagement.objDateOfBirthValue("Month"));
		int monthNum = convertMonthToNumber(Month);
		System.out.println(monthNum);
		String monthNumber = Integer.toString(monthNum);
		int lenOfMonth = monthNumber.length();
		System.out.println(lenOfMonth);
		if (lenOfMonth == 1) {
			monthNumber = "0" + monthNumber;
		}
		String year = getText(AMDUserSessionManagement.objDateOfBirthValue("Year"));
		String WebConvertedDOB = Day + "/" + monthNumber + "/" + year;
		WebPWAMyProfile.add(WebConvertedDOB);
		logger.info(WebPWAMyProfile);

	}

	public int convertMonthToNumber(String month) {

		ArrayList<String> list = new ArrayList<String>();
		list.add("MONTH");
		list.add("JAN");
		list.add("FEB");
		list.add("MAR");
		list.add("APR");
		list.add("MAY");
		list.add("JUN");
		list.add("JUL");
		list.add("AUG");
		list.add("SEP");
		list.add("OCT");
		list.add("NOV");
		list.add("DEC");

		int number = list.indexOf(month);
		return number;
	}

	public void webWatchList() throws Exception {
		extent.HeaderChildNode("My watchList in Web");
		JSClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Watchlist"), "My watchlist");
		waitTime(4000);
		click(AMDUserSessionManagement.objMoviesTabInMyWatchlist, "Movies tab");
		int contentsInMoviesTab = getWebDriver().findElements(AMDUserSessionManagement.objcontentsInAllTheTabs).size();
		System.out.println(contentsInMoviesTab);
		if (contentsInMoviesTab >= 0) {
			for (int i = 1; i <= contentsInMoviesTab; i++) {
				String contentTitle = getWebDriver()
						.findElement(By.xpath("(//h3[@class='cardTitle overflowEllipsis ']/child::a)[" + i + "]"))
						.getText();
				WebPWAWatchList.add(contentTitle);
			}
		} else {
			logger.info("No contents in Movies tab");
			extentLogger("Videos tab", "No contents in Movies tab");
		}

		click(AMDUserSessionManagement.objVideosTabInMyWatchlist, "Videos tab");
		int contentsInVideosTab = getWebDriver().findElements(AMDUserSessionManagement.objcontentsInAllTheTabs).size();
		System.out.println(contentsInVideosTab);
		if (contentsInVideosTab >= 0) {

			for (int i = 1; i <= contentsInVideosTab; i++) {
				String contentTitle = getWebDriver()
						.findElement(By.xpath("(//h3[@class='cardTitle overflowEllipsis ']/child::a)[" + i + "]"))
						.getText();
				WebPWAWatchList.add(contentTitle);
			}
		} else {
			logger.info("No contents in Vidoes tab");
			extentLogger("Videos tab", "No contents in videos tab");
		}

		logger.info(WebPWAWatchList);
	}

	public void webReminders() {
		extent.HeaderChildNode("My Reminders in Web");
		JSClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Reminders"), "My reminders");
		waitTime(4000);
		int contentsInRemindersScreen = getWebDriver().findElements(AMDUserSessionManagement.objcontentsInAllTheTabs)
				.size();
		System.out.println(contentsInRemindersScreen);
		if (contentsInRemindersScreen >= 0) {

			for (int i = 1; i <= contentsInRemindersScreen; i++) {
				String contentTitle = getWebDriver()
						.findElement(By.xpath("(//h3[@class='cardTitle overflowEllipsis ']/child::a)[" + i + "]"))
						.getText();
				WebPWAReminders.add(contentTitle);
			}
			logger.info("contents in Reminders page: " + WebPWAReminders);
		} else {
			logger.info("No contents in My Remainders page");
			extentLogger("Reminders", "No contents in My Remainders page");
		}
	}

	public void webSubscription(String usertype) throws Exception {
		extent.HeaderChildNode("My Subscription in Web");
		JSClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Subscription"), "My Subscription");

		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			verifyElementPresent(AMDUserSessionManagement.objNoActiveSubscriptionText, "No Active Subscription");
			scrollByWEB();
			verifyElementPresent(AMDUserSessionManagement.objSubscriptionBannerInMyProfileSection, "Subscribe now CTA");
			JSClick(AMDUserSessionManagement.objSubscriptionBannerInMyProfileSection, "Subscribe now CTA");
		} else {
			verifyElementExist(AMDUserSessionManagement.objPremiumPackInMySubscriptionScreen, "Premium Pack");
			String Price = getText(AMDUserSessionManagement.objPemiumPackPriceInMySubscriptionScreen);
			String packPrice = "INR " + Price;
			WebPWASubscription.add(packPrice);
			// String packvalidity =
			// getText(AMDUserSessionManagement.objPremiumPackDuration);
//		WebPWASubscription.add(packvalidity);
			String dateOfPurchase = getText(AMDUserSessionManagement.objPremiumPackdateOfPurchase);
			WebPWASubscription.add(dateOfPurchase);
			String status = getText(AMDUserSessionManagement.objPackdetailsAtMySubscriptionpage("Status"));
			WebPWASubscription.add(status);
			String packCountry = getText(AMDUserSessionManagement.objPackdetailsAtMySubscriptionpage("Pack Country"));
			WebPWASubscription.add(packCountry);
			String paymentMode = getText(AMDUserSessionManagement.objPackdetailsAtMySubscriptionpage("Payment Mode"));
			WebPWASubscription.add(paymentMode);
			String autoRenewal = getText(AMDUserSessionManagement.objPackdetailsAtMySubscriptionpage("Auto Renewal"));
			WebPWASubscription.add(autoRenewal);
//		String dateOfExpire = getText(AMDUserSessionManagement.objPackExpieryDate);
//		WebPWASubscription.add(dateOfExpire);
			click(AMDUserSessionManagement.objBrowseAllPacksBtn, "Browse All Packs");
			logger.info(WebPWASubscription);
		}

		verifyElementExist(PWASubscriptionPages.objZEE5Subscription, "Subscription page");
		waitTime(2000);
		Back(1);
	}

	public void webTransaction() throws Exception {
		extent.HeaderChildNode("My Transaction in Web");
		JSClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Transactions"), "My Transactions");
		waitTime(4000);
		boolean empty = verifyElementDisplayed(AMDUserSessionManagement.objNoTransactionsText);
		if (empty == true) {
			logger.info("No Transactions for this user");
			extentLogger("My Transactions", "No Transactions for this user");
		} else if (getText(AMDUserSessionManagement.objPackDetails("Status")).equalsIgnoreCase("EXPIRED")) {
			logger.info("Transaction Pack is expired for this user");
			extentLogger("My Transactions", "Transaction Pack is expired for this user");

			String packName = getText(AMDUserSessionManagement.objTransactionPackName);
			webPWATransaction.add(packName);
			String packCountry = getText(AMDUserSessionManagement.objPackDetails("Pack Country"));
			webPWATransaction.add(packCountry);
			String packDuration = getText(AMDUserSessionManagement.objPackDetails("Duration"));
			webPWATransaction.add(packDuration);
			String paymentMode = getText(AMDUserSessionManagement.objPackDetails("Payment Mode"));
			webPWATransaction.add(paymentMode);
			String autoRenewal = getText(AMDUserSessionManagement.objPackDetails("Auto Renewal"));
			webPWATransaction.add(autoRenewal);
		} else {
			logger.info("Transaction Pack is Active for this user");
			extentLogger("My Transactions", "Transaction Pack is Active for this user");
			String packName = getText(AMDUserSessionManagement.objTransactionPackName);
			webPWATransaction.add(packName);
			String Price = getText(AMDUserSessionManagement.objTransactionPackPrice);
			String packPrice = "INR " + Price;
			webPWATransaction.add(packPrice);
			String packCountry = getText(AMDUserSessionManagement.objPackDetails("Pack Country"));
			webPWATransaction.add(packCountry);
			String packDuration = getText(AMDUserSessionManagement.objPackDetails("Duration"));
			webPWATransaction.add(packDuration);
			String paymentMode = getText(AMDUserSessionManagement.objPackDetails("Payment Mode"));
			webPWATransaction.add(paymentMode);
			String autoRenewal = getText(AMDUserSessionManagement.objPackDetails("Auto Renewal"));
			webPWATransaction.add(autoRenewal);
			verifyElementExist(AMDUserSessionManagement.objDownloadInvoiceBtn, "Download Invoice button");
		}
		logger.info(webPWATransaction);
	}

	public void webDisplayLanguage() throws Exception {
		extent.HeaderChildNode("Display Language in Web");

		click(PWAHamburgerMenuPage.objLanguageBtnWEB, "language button");
		waitTime(3000);
		checkElementExist(PWAHamburgerMenuPage.objDisplayLang, "Display Language header");
		webDisplayLanguage = getText(AMDUserSessionManagement.objselectedDisplayLanguage);
		logger.info(webDisplayLanguage);
		extentLogger("Display Language", webDisplayLanguage + " is selected as display language");
		waitTime(3000);
		click(PWAHamburgerMenuPage.objLanguageBtnWEB, "language button");
	}

	public void webParentalControl(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Parental control in Web");

			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = getParameterFromXML("USMNonsubscribedPassword");
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
			checkElementDisplayed(PWAHamburgerMenuPage.objNoRestrictionSelected, "No restricted option selected");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objRestrictAll, "Restrict all option");
			verifyElementPresent(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(2000);
		}

	}

	public void ZeeApplicasterLoginForUSM(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Login Functionality");
		String UserType = getParameterFromXML("userType");
		if (UserType.equals("Guest")) {
			extent.extentLogger("userType", "UserType : Guest");
		}
		verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
		waitTime(3000);

		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitTime(1000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip link");
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User for User Session Management");
			String SUsername = getParameterFromXML("USMNonsubscribedUserName");
			String SPassword = getParameterFromXML("USMNonsubscribedPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User for User Session Management");
			String SubscribedUsername = getParameterFromXML("SubscribedUserName");
			String SubscribedPassword = getParameterFromXML("SubscribedPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;
		}
	}

	public void appMyProfile() throws Exception {
		extent.HeaderChildNode("My Profile in App");
		click(AMDMoreMenu.objMoreMenuIcon, "More tab screen");
		click(AMDMoreMenu.objUserName, "User name");
		click(AMDMyProfileScreen.objEditProfileButton, "Edit CTA button");
		String AppFirstName = getText(AMDEditProfileScreen.objFirstNameField);
		AppMyProfile.add(AppFirstName);
		String AppGender = getText(AMDEditProfileScreen.objGederTxtField);
		AppMyProfile.add(AppGender);
		String AppDOB = getText(AMDEditProfileScreen.objDOBTxtField);
		AppMyProfile.add(AppDOB);

		logger.info(WebPWAMyProfile);
		logger.info(AppMyProfile);

		for (int i = 0; i < WebPWAMyProfile.size(); i++) {
			if (WebPWAMyProfile.get(i).equalsIgnoreCase(AppMyProfile.get(i))) {
				logger.info("Web Value: " + WebPWAMyProfile.get(i) + " is same as App value: " + AppMyProfile.get(i));
				extentLoggerPass("Profile details",
						"Web Value: " + WebPWAMyProfile.get(i) + " is same as App value: " + AppMyProfile.get(i));
			} else {
				logger.info(
						"Web Value: " + WebPWAMyProfile.get(i) + " is not same as App value: " + AppMyProfile.get(i));
				extentLoggerFail("Profile details",
						"Web Value: " + WebPWAMyProfile.get(i) + " is not same as App value: " + AppMyProfile.get(i));
			}
		}

		Back(1);
		waitTime(3000);
		Back(1);

	}

	public void appWatchlist() throws Exception {
		extent.HeaderChildNode("My watchlist in App");
		click(AMDMoreMenu.objWatchlist, "Watchlist option");
		click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
		boolean contentsInMoviesTab = verifyIsElementDisplayed(
				AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
		if (contentsInMoviesTab == true) {
			for (int i = 0; i < 3; i++) {
				int totalContents = getDriver()
						.findElements(AMDUserSessionManagement.objcontentTitleInWatchListAndReminders).size();
				for (int j = 1; j <= totalContents; j++) {
					String content = getDriver()
							.findElement(By.xpath(
									"(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[" + j + "]"))
							.getText();
					contentsInWatchList.add(content);
				}
				Swipe("UP", 1);
			}
		} else {
			verifyIsElementDisplayed(AMDUserSessionManagement.objNothingToWatchOrReminder, "Nothing to watch text");
		}

		click(AMDUserSessionManagement.objVideosTabUnderWatchList, "Videos Tab");
		boolean contnetsInVideosTab = verifyIsElementDisplayed(
				AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
		if (contnetsInVideosTab == true) {
			for (int i = 0; i < 3; i++) {
				int totalContents = getDriver()
						.findElements(AMDUserSessionManagement.objcontentTitleInWatchListAndReminders).size();
				for (int j = 1; j <= totalContents; j++) {
					String content = getDriver()
							.findElement(By.xpath(
									"(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[" + j + "]"))
							.getText();
					contentsInWatchList.add(content);
				}
				Swipe("UP", 1);
			}

		} else {
			verifyIsElementDisplayed(AMDUserSessionManagement.objNothingToWatchOrReminder, "Nothing to watch text");
		}
		logger.info(WebPWAWatchList);
		List<String> contentsInWatchListScreen = new ArrayList<String>(contentsInWatchList);
		logger.info(contentsInWatchListScreen);
		boolean value = true;

		for (int i = 0; i < WebPWAWatchList.size(); i++) {
			if (contentsInWatchListScreen.contains(WebPWAWatchList.get(0))) {
				continue;
			} else {
				value = false;
				logger.info("Web Value is not same as App value");
				extentLoggerFail("Watchlist", "Web Value is not same as App value");
				break;
			}
		}
		if (value == true) {
			logger.info("Web Value is same as App value");
			extentLoggerPass("Watchlist", "Web Value is same as App value");
		}
		Back(1);
	}

	public void appMyReminders() throws Exception {
		extent.HeaderChildNode("My Reminders in App");
		click(AMDMoreMenu.objMyRemainders, "My Reminders option");
		boolean reminders = verifyIsElementDisplayed(AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
		if (reminders == true) {
			for (int i = 0; i < 3; i++) {
				int totalContents = getDriver()
						.findElements(AMDUserSessionManagement.objcontentTitleInWatchListAndReminders).size();
				for (int j = 1; j <= totalContents; j++) {
					String content = getDriver()
							.findElement(By.xpath(
									"(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[" + j + "]"))
							.getText();
					contentsInReminders.add(content);
				}
				Swipe("UP", 1);
			}
			logger.info(WebPWAReminders);
			List<String> contentsInReminderScreen = new ArrayList<String>(contentsInReminders);
			System.out.println(contentsInReminderScreen);
			for (int i = 0; i < WebPWAReminders.size(); i++) {

				if (WebPWAReminders.get(i).equalsIgnoreCase(contentsInReminderScreen.get(i))) {
					logger.info("Web Value: " + WebPWAReminders.get(i) + " is same as App value: "
							+ contentsInReminderScreen.get(i));
					extentLoggerPass("Reminders details", "Web Value: " + WebPWAReminders.get(i)
							+ " is same as App value: " + contentsInReminderScreen.get(i));
				} else {
					logger.info("Web Value: " + WebPWAReminders.get(i) + " is not same as App value: "
							+ contentsInReminderScreen.get(i));
					extentLoggerFail("Reminders details", "Web Value: " + WebPWAReminders.get(i)
							+ " is not same as App value: " + contentsInReminderScreen.get(i));
				}
			}
		} else {
			verifyIsElementDisplayed(AMDUserSessionManagement.objNothingToWatchOrReminder,
					"Nothing to remind you text");
		}

		Back(1);
	}

	public void appSubscription(String usertype) throws Exception {
		extent.HeaderChildNode("My Subscription in App");
		click(AMDMoreMenu.objMySubscription, "My Subscription option");
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			verifyElementPresent(AMDMoreMenu.objNoActivePlans, "No Active Subscription");
			verifyElementPresent(AMDMoreMenu.objSubscribeNowCTA, "Subscribe now CTA");
			click(AMDMoreMenu.objSubscribeNowCTA, "Subscribe now CTA");
		} else {
			waitTime(3000);
			verifyIsElementDisplayed(AMDUserSessionManagement.objSubscriptionPackName, "Premium Pack");
			String packPrice = getText(AMDMoreMenu.objSubscriptionPackPrice);
			AppSubscription.add(packPrice);
//		String packvalidity = getText(AMDMoreMenu.objSubscriptionPackDuration);
//		AppSubscription.add(packvalidity);
			String DOP = getText(AMDUserSessionManagement.objPurchaseDate);
			String dateOfPurchase = DOP.replace(":", " :");
			AppSubscription.add(dateOfPurchase);
			String status = getText(AMDMoreMenu.objsubscriptionPackStatus);
			AppSubscription.add(status);
			String packCountry = getText(AMDMoreMenu.objSubscriptionPackCountry);
			AppSubscription.add(packCountry);
			String paymentMode = getText(AMDMoreMenu.objSubscriptionPackPaymentMode);
			AppSubscription.add(paymentMode);
			String autoRenewal = getText(AMDUserSessionManagement.objAutoRenewalvalue);
			AppSubscription.add(autoRenewal);
//		String dateOfExpire = getText(AMDMoreMenu.objSubscriptionPackExpiryDate);
//		AppSubscription.add(dateOfExpire);
			click(AMDMoreMenu.objBrowseAllPacks, "Browse All Packs");
			logger.info(AppSubscription);
			logger.info(WebPWASubscription);
			for (int i = 0; i < WebPWASubscription.size(); i++) {
				if (WebPWASubscription.get(i).equalsIgnoreCase(AppSubscription.get(i))) {
					logger.info("Web Value: " + WebPWASubscription.get(i) + " is same as App value: "
							+ AppSubscription.get(i));
					extentLoggerPass("Subscription details", "Web Value: " + WebPWASubscription.get(i)
							+ " is same as App value: " + AppSubscription.get(i));
				} else {
					logger.info("Web Value: " + WebPWASubscription.get(i) + " is not same as App value: "
							+ AppSubscription.get(i));
					extentLoggerFail("Subscription details", "Web Value: " + WebPWASubscription.get(i)
							+ " is not same as App value: " + AppSubscription.get(i));
				}
			}
		}

		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscription page");
		waitTime(2000);
		Back(1);
		waitTime(2000);
		Back(1);
	}

	public void appTransaction(String usertype) throws Exception {
		extent.HeaderChildNode("My Transaction in App");

		click(AMDMoreMenu.objMyTransactions, "My Transactions");
		boolean emptyScreen = checkElementExist(AMDUserSessionManagement.objNoTransaction, "No Transactions text");
		if (emptyScreen == true) {
			logger.info("No Transactions for this user");
			extentLogger("My Transactions", "No Transactions for this user");
		} else if (getText(AMDMoreMenu.objTransactionPackStatus).equalsIgnoreCase("Inactive")) {
			logger.info("Transaction Pack is expired for this user");
			extentLogger("My Transactions", "Transaction Pack is expired for this user");

			String packName = getText(AMDMoreMenu.objTransactionPackName);
			AppTransaction.add(packName);
			String packCountry = getText(AMDMoreMenu.objTransactionPackCountry);
			AppTransaction.add(packCountry);
			String packDuration = getText(AMDMoreMenu.objTransactionPackDuration);
			AppTransaction.add(packDuration);
			String paymentMode = getText(AMDMoreMenu.objTransactionPackPaymentMode);
			AppTransaction.add(paymentMode);
			String autoRenewal = getText(AMDMoreMenu.objTransactionPackAutoRenewal);
			AppTransaction.add(autoRenewal);
		} else {
			logger.info("Transaction Pack is Active for this user");
			extentLogger("My Transactions", "Transaction Pack is Active for this user");
			String packName = getText(AMDMoreMenu.objTransactionPackName);
			AppTransaction.add(packName);
			String packPrice = getText(AMDUserSessionManagement.objTransPackPrice);
			AppTransaction.add(packPrice);
			String packCountry = getText(AMDMoreMenu.objTransactionPackCountry);
			AppTransaction.add(packCountry);
			String packDuration = getText(AMDMoreMenu.objTransactionPackDuration);
			AppTransaction.add(packDuration);
			String paymentMode = getText(AMDMoreMenu.objTransactionPackPaymentMode);
			AppTransaction.add(paymentMode);
			String autoRenewal = getText(AMDMoreMenu.objTransactionPackAutoRenewal);
			AppTransaction.add(autoRenewal);
			verifyElementExist(AMDMoreMenu.objDownloadInvoice, "Download Invoice button");
		}
		logger.info(AppTransaction);
		logger.info(webPWATransaction);
		for (int i = 0; i < webPWATransaction.size(); i++) {
			if (webPWATransaction.get(i).equalsIgnoreCase(AppTransaction.get(i))) {
				logger.info(
						"Web Value: " + webPWATransaction.get(i) + " is same as App value: " + AppTransaction.get(i));
				extentLoggerPass("Transaction details",
						"Web Value: " + webPWATransaction.get(i) + " is same as App value: " + AppTransaction.get(i));
			} else {
				logger.info("Web Value: " + webPWATransaction.get(i) + " is not same as App value: "
						+ AppTransaction.get(i));
				extentLoggerFail("Transaction details", "Web Value: " + webPWATransaction.get(i)
						+ " is not same as App value: " + AppTransaction.get(i));
			}
		}

		Back(1);
	}

	public void appDisplayLanguage() throws Exception {
		extent.HeaderChildNode("Display Language in App");

		click(AMDMoreMenu.objSettings, "Settings option");
		waitTime(3000);
		Swipe("UP", 1);
		verifyIsElementDisplayed(AMDMoreMenu.objDisplayLang, "Display language");
		appDisplayLanguage = getText(AMDMoreMenu.objDisplayLang);

		logger.info(appDisplayLanguage + " is selected as display language");
		extentLogger("Display Language", appDisplayLanguage + " is selected as display language");

		logger.info(webDisplayLanguage + " is selected as display language");
		extentLogger("Display Language", webDisplayLanguage + " is selected as display language");

		if (webDisplayLanguage.equalsIgnoreCase(appDisplayLanguage)) {
			logger.info("Web Value: " + webDisplayLanguage + " is same as App value: " + appDisplayLanguage);
			extentLoggerPass("Display language details",
					"Web Value: " + webDisplayLanguage + " is same as App value: " + appDisplayLanguage);
		} else {
			logger.info("Web Value: " + webDisplayLanguage + " is not same as App value: " + appDisplayLanguage);
			extentLoggerFail("Display language details",
					"Web Value: " + webDisplayLanguage + " is not same as App value: " + appDisplayLanguage);
		}

	}

	@SuppressWarnings("deprecation")
	public void appParentalControl(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Parental control in App");

			click(AMDMoreMenu.objParentalControl, "Parental control option");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			String password = getParameterFromXML("USMNonsubscribedPassword");
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);
			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			boolean parentalControl = checkElementDisplayed(
					AMDUserSessionManagement.objSelectedRestrictAllContentOption, "Restrict All Content");
			if (parentalControl == true) {
				logger.info("Parental control set in any one platform is reflected across all platforms");
				extent.extentLoggerPass("Parental Control",
						"Parental control set in any one platform is reflected across all platforms");
			} else {
				logger.info("Parental control set in any one platform is not reflected across all platforms");
				extent.extentLoggerFail("Parental Control",
						"Parental control set in any one platform is not reflected across all platforms");
			}

			click(AMDMoreMenu.objNoRestriction, "No restriction option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");

		}

	}

	public void webUpdateProfileDetails() throws Exception {
		extent.HeaderChildNode("Updation of My profile in Web");

		click(PWALandingPages.objWebProfileIcon, "Profile Icon");
		waitTime(5000);
		click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon in My profile dropdown");
		Actions actions = new Actions(getWebDriver());
		WebElement contentcard = getWebDriver().findElement(PWAHomePage.objZeeLogo);
		actions.moveToElement(contentcard).perform();
		verifyElementPresent(AMDUserSessionManagement.objSubscriptionBannerInMyProfileSection,
				"Subscription banner under My profile section");
		click(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");

		boolean var = verifyElementDisplayed(PWAHamburgerMenuPage.objEditProfileFirstName);
		if (var == true) {
			click(PWAHamburgerMenuPage.objEditProfileFirstName, "First name field");
			int lenText = findElement(PWAHamburgerMenuPage.objEditProfileFirstName).getAttribute("value").length();
			for (int i = 0; i < lenText; i++) {
				getWebDriver().findElement(PWAHamburgerMenuPage.objEditProfileFirstName).sendKeys(Keys.BACK_SPACE);
			}
			waitTime(5000);
			type(PWAHamburgerMenuPage.objEditProfileFirstName, "yopp", "First name field");
			waitTime(3000);
			scrollByWEB();
			click(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "Save changes button");
			waitTime(3000);
			webUpdatedFirstName = getAttributValue("value", PWAHamburgerMenuPage.objEditProfileFirstName);
			logger.info("First name is updated as " + webUpdatedFirstName);
		}

	}

	@SuppressWarnings("unused")
	public void webRemoveWatchList() throws Exception {
		extent.HeaderChildNode("Removal of contents in My watchList screen in Web");
		waitTime(3000);
		click(PWALandingPages.objWebProfileIcon, "Profile Icon");
		waitTime(5000);
		JSClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Watchlist"), "My watchlist");
		waitTime(4000);
		boolean EmptyWatchlist = checkElementExist(AMDUserSessionManagement.objMoviesTabInMyWatchlist);
		if (EmptyWatchlist == false) {
			logger.info("no contents are there to remove in My Watchlist screen");
			extentLogger("My Watchlist", "no contents are there to remove in My Watchlist screen");
		} else {
			click(AMDUserSessionManagement.objMoviesTabInMyWatchlist, "Movies tab");
			boolean removeAllBtn = verifyElementDisplayed(PWAHamburgerMenuPage.objRemoveAllBtn);
			if (removeAllBtn == true) {
				click(PWAHamburgerMenuPage.objRemoveAllBtn, "Remove All button");
				boolean webEmptyWatchlist = checkElementExist(AMDUserSessionManagement.objEmptyWatchListMessage);
				logger.info("no contents are there in Movies tab");
				extentLogger("My Watchlist", "no contents are there in Movies tab");
			} else {
				logger.info("no contents are there to remove in Movies tab");
				extentLogger("My Watchlist", "no contents are there to remove in Movies tab");
			}
			boolean videos = checkElementExist(AMDUserSessionManagement.objVideosTabInMyWatchlist);
			if (videos == true) {
				click(AMDUserSessionManagement.objVideosTabInMyWatchlist, "Videos tab");
				boolean removeAllBtnn = verifyElementDisplayed(PWAHamburgerMenuPage.objRemoveAllBtn);
				if (removeAllBtnn == true) {
					click(PWAHamburgerMenuPage.objRemoveAllBtn, "Remove All button");
					boolean webEmptyWatchlist = checkElementExist(AMDUserSessionManagement.objEmptyWatchListMessage);
					logger.info("no contents are there in Videos tab");
					extentLogger("My Watchlist", "no contents are there in Videos tab");
				} else {
					logger.info("no contents are there to remove in Videos tab");
					extentLogger("My Watchlist", "no contents are there to remove in Videos tab");
				}
			}

		}
	}

	@SuppressWarnings("unused")
	public void webRemoveReminders() throws Exception {
		extent.HeaderChildNode("Removal of contents in My reminders screen in Web");
		JSClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Reminders"), "My Reminders");
		waitTime(4000);
		boolean removeAllBtn = verifyElementDisplayed(PWAHamburgerMenuPage.objRemoveAllBtn);
		if (removeAllBtn == true) {
			click(PWAHamburgerMenuPage.objRemoveAllBtn, "Remove All button");
			boolean webEmptyReminders = checkElementExist(AMDUserSessionManagement.objEmptyRemindersmessage);
		} else {
			logger.info("no contents are there to remove in My reminders screen");
			extentLogger("My Reminders", "no contents are there to remove in My reminders screen");
		}
	}

	public void appUpdatedFirstName() throws Exception {
		extent.HeaderChildNode("Valdation of Updated Profile");

		click(AMDMoreMenu.objMoreMenuIcon, "More tab screen");
		click(AMDMoreMenu.objUserName, "User name");
		click(AMDMyProfileScreen.objEditProfileButton, "Edit CTA button");
		String AppUpdatedFirstName = getText(AMDEditProfileScreen.objFirstNameField);
		logger.info(AppUpdatedFirstName);

		if (webUpdatedFirstName.equalsIgnoreCase(AppUpdatedFirstName)) {
			logger.info("the profile details updated in PWA/Web is reflected in android");
			extentLoggerPass("Updated details", "the profile details updated in PWA/Web is reflected in android");
		} else {
			logger.info("the profile details updated in PWA/Web is not reflected in android");
			extentLoggerFail("Updated details", "the profile details updated in PWA/Web is not reflected in android");
		}

		Back(1);
		waitTime(3000);
		Back(1);
	}

	public void appRemoveWatlist() throws Exception {
		extent.HeaderChildNode("Validation of Updated Watchlist");
		click(AMDMoreMenu.objMoreMenuIcon, "More tab screen");
		click(AMDMoreMenu.objWatchlist, "Watchlist option");
		click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
		boolean noMoviesContent = checkElementExist(AMDUserSessionManagement.objNothingToWatchOrReminder,
				"Nothing to watch text");
		if (noMoviesContent == true) {
			logger.info(
					"contents removed from Movies Tab in watchlist screen in any one of the platform is reflected in other platforms too");
			extentLoggerPass("Movies",
					"contents removed from Movies Tab in watchlist screen in any one of the platform is reflected in other platforms too");
		} else {
			logger.info(
					"contents removed from Movies Tab in watchlist screen in any one of the platform is not reflected in other platforms too");
			extentLoggerFail("Movies",
					"contents removed from Movies Tab in watchlist screen in any one of the platform is not reflected in other platforms too");
		}

		click(AMDUserSessionManagement.objVideosTabUnderWatchList, "Videos Tab");
		boolean noVideoContent = checkElementExist(AMDUserSessionManagement.objNothingToWatchOrReminder,
				"Nothing to watch text");
		if (noVideoContent == true) {
			logger.info(
					"contents removed from videos Tab in watchlist screen in any one of the platform is reflected in other platforms too");
			extentLoggerPass("Videos",
					"contents removed from videos Tab in watchlist screen in any one of the platform is reflected in other platforms too");
		} else {
			logger.info(
					"contents removed from videos Tab in watchlist screen in any one of the platform is not reflected in other platforms too");
			extentLoggerFail("Videos",
					"contents removed from videos Tab in watchlist screen in any one of the platform is not reflected in other platforms too");
		}
		Back(1);
	}

	public void appRemoveReminder() throws Exception {
		extent.HeaderChildNode("validation of Updated Reminders");

		click(AMDMoreMenu.objMyRemainders, "My Reminders option");
		boolean noReminders = checkElementExist(AMDUserSessionManagement.objNothingToWatchOrReminder,
				"Nothing to remind u text");
		if (noReminders == true) {
			logger.info(
					"contents removed from My Remainders screen in any one of the platform is reflected in other platform too");
			extentLoggerPass("Reminders",
					"contents removed from My Remainders screen in any one of the platform is reflected in other platform too");
		} else {
			logger.info(
					"contents removed from My Remainders screen in any one of the platform is not reflected in other platform too");
			extentLoggerFail("Reminders",
					"contents removed from My Remainders screen in any one of the platform is not reflected in other platform too");
		}

	}

	/**
	 * Author : Kushal
	 */
	public int GetWaiverAmountForClubPackUpdgrade() throws Exception {

		click(AMDMoreMenu.objProfile, "My Profile");
		waitTime(5000);

		String activePack = getText(AMDMyProfileScreen.objPlanPrice).replace("INR ", "").trim();
		String activePackValidity = getText(AMDMyProfileScreen.objPlanDuration).replace("For", "").replace(" days", "")
				.trim();

		click(AMDMyProfileScreen.objDetailsCTA, "Details");
		waitTime(2000);

		String SubscribedDate = getText(AMDMySubscriptionPage.objDateOfPurchase).replace("Date of Purchase: ", "")
				.trim();
		System.out.println(SubscribedDate);

		int nPackPrice = Integer.parseInt(activePack);
		int nPackValidity = Integer.parseInt(activePackValidity);

		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		Date DateOfPurchase = formatter.parse(SubscribedDate);
		Date currentDate = new Date();

		long difference = currentDate.getTime() - DateOfPurchase.getTime();
		float NoOfDaysUsed = difference / (1000 * 60 * 60 * 24);

		// Formula to calculate the WAIVER Amount
		int perDayPrice = nPackPrice / nPackValidity;
		int WaiverAmount = (int) (nPackPrice - NoOfDaysUsed) * perDayPrice;

		logger.info("My Active Pack: " + nPackPrice);
		logger.info("My Pack Validity: " + nPackValidity);
		logger.info("Date of Purchase: " + DateOfPurchase);
		logger.info("Number of Days pack used: " + NoOfDaysUsed);
		logger.info("Waiver Amount: " + WaiverAmount);

		logger.info("For an Active pack of INR " + nPackPrice + " with validity of " + nPackValidity
				+ " days; the calculated Waiver Amount is " + WaiverAmount);
		extent.extentLoggerPass("Waiver Amount for Active Pack", "For an Active pack of INR " + nPackPrice
				+ " with validity of " + nPackValidity + " days; the calculated Waiver Amount is " + WaiverAmount);

		Back(2);
		return WaiverAmount;
	}

	public void AvailableTraysInTabs(String tabName, String userType) throws Exception {
		extent.HeaderChildNode("Verify the available Trays in Consumption screen for tab \"" + tabName + "\"");

		ArrayList<String> TrayName = new ArrayList<>();
		int count = 0;
		int VisibleTraySize = getDriver().findElements(AMDGenericObjects.objTrayTitle).size();
		System.out.println(VisibleTraySize);
		for (int i = 1; i <= VisibleTraySize; i++) {
			TrayName.add(getText(AMDGenericObjects.objTrayTitleByIndex(i)));
			Swipe("Up", 1);
			waitTime(2000);
			VisibleTraySize = getDriver().findElements(AMDGenericObjects.objTrayTitle).size();
			System.out.println(TrayName);
			for (int j = 1; j <= VisibleTraySize; j++) {
				TrayName.add(getText(AMDGenericObjects.objTrayTitleByIndex(j)));

			}
			count++;
			if (count == 5) {
				System.out.println(count);
				break;
			}
		}

//	   Swipe("Down", count);
		Set<String> set = new HashSet<>(TrayName);
		TrayName.clear();
		TrayName.addAll(set);
		extent.extentLogger("Verify TrayNames", "Available Trays in consumption screen for tab : " + tabName + "\n");
		for (int i = 0; i < TrayName.size(); i++) {
			extent.extentLoggerPass("Verify TrayNames", TrayName.get(i) + "\n");
			logger.info(TrayName.get(i));
		}

	}

	/**
	 * Author : Bhavana
	 */
	public void ValidateClubIconForEpisodes(String userType) throws Exception {
		extent.HeaderChildNode("Validating Club Icon for Episodes");
		System.out.println("Validating Club Icon for Episodes");

		if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
			waitTime(6000);
			click(AMDHomePage.objShowsTab, "Shows tab");
			waitTime(10000);
			SwipeUntilFindElement(AMDClubPack.objBeforeZeeKannadaTray, "UP");
			verifyElementExist(AMDClubPack.objClubIconOnFirstCardOfTray, "Club Icon on Episodes tray");
			waitTime(2000);
			click(AMDClubPack.objBeforeZeeKannadaTray, "Before Zee Kannada tray");
			waitTime(2000);
			boolean result = verifyIsElementDisplayed(AMDClubPack.objclubIconInContentListingScreen);
			if (result) {
				logger.info("Club icon is displayed on club content card displayed on Episodes listing screeen");
				extent.extentLoggerPass("Landing page",
						"Club icon is displayed on club content card displayed on Episodes listing screeen");
			} else {
				logger.error("Club icon is NOT displayed on club content card displayed on Episodes listing screeen");
				extent.extentLoggerFail("Landing screen",
						"Club icon is NOT displayed on club content card displayed on Episodes listing screeen");
			}
			Back(1);
			click(AMDHomePage.HomeIcon, "Home Icon");
		} else {
			logger.info("Validating Club icon for Episodes is not applicable for " + userType);
			extent.extentLoggerPass("Landing page",
					"Validating Club icon for Episodes is not applicable for " + userType);
		}
	}

	public void ValidateClubIconForMovies(String userType) throws Exception {
		extent.HeaderChildNode("Validating Club Icon for Movies");
		System.out.println("Validating Club Icon for Movies");

		if (userType.equals("Guest") | userType.equals("NonSubscribedUser")) {
			waitTime(6000);
			click(AMDHomePage.objMoviesTab, "Movies tab");
			waitTime(10000);
			SwipeUntilFindElement(AMDClubPack.objKannadaFamilyDrama, "UP");
			waitTime(2000);
			click(AMDClubPack.objKannadaFamilyDrama, "Kannada Family Dramas tray");
			waitTime(3000);
			boolean result = verifyIsElementDisplayed(AMDClubPack.objclubIconInContentListingScreen);
			if (result) {
				logger.info("Club icon is displayed on club content card displayed on Movies listing screeen");
				extent.extentLoggerPass("Landing page",
						"Club icon is displayed on club content card displayed on Movies listing screeen");
			} else {
				logger.error("Club icon is NOT displayed on club content card displayed on Movies listing screeen");
				extent.extentLoggerFail("Landing screen",
						"Club icon is NOT displayed on club content card displayed on Movies listing screeen");
			}
			Back(1);
			click(AMDHomePage.HomeIcon, "Home Icon");
		} else {
			logger.info("Validating Club icon for Movies is not applicable for " + userType);
			extent.extentLoggerPass("Landing page",
					"Validating Club icon for Movies is not applicable for " + userType);
		}
	}

	public void scrubProgressBarTillEnd(By byLocator1) throws Exception {
		String beforeSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time before seeking : " + timeToSec(beforeSeek));
		extent.extentLogger("Seek", "Current time before seeking in seconds: " + timeToSec(beforeSeek));
		click(AMDPlayerScreen.objPauseIcon, "Pause");
		WebElement element = getDriver().findElement(byLocator1);
		String xDuration = getAttributValue("x", AMDPlayerScreen.objTotalDuration);
		int endX = Integer.parseInt(xDuration) - 30;
		SwipeAnElement(element, endX, 0);
		String afterSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time after seeking : " + timeToSec(afterSeek));
		extent.extentLogger("Seek", "Current time after seeking in seconds: " + timeToSec(afterSeek));
		click(AMDPlayerScreen.objPlayIcon, "Play");
		waitTime(6000);
	}

	/**
	 * Author : Manasa
	 */
	public void nextAndPreviousIconValidation(String searchKeyword8) throws Exception {
		extent.HeaderChildNode("Validation of Next and Prevoius icons");
		System.out.println("\n Validation of Next and Prevoius icons");
		waitTime(5000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword8 + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDMoreMenu.objSearchResult(searchKeyword8), "Search result");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 10);
		waitTime(5000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		click(AMDPlayerScreen.objFullscreenIcon, "Maximize Icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objNextIcon, "Next Icon");
		verifyElementPresent(AMDPlayerScreen.objPreviousIcon, "Previous Icon");

		waitTime(1000);
		Back(4);
	}

	/**
	 * Author : Kushal
	 */
	public void ZEE5AppLogin(String pUserType) throws Exception {

		if (!pUserType.equals("Guest")) {
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
			waitTime(3000);
		}

		switch (pUserType) {
		case "Guest":
			extent.HeaderChildNode(
					"Validating the Navigation to Login or Register Screen Tapping on the Login link available in Intro Screen");

			verifyElementPresent(AMDLoginScreen.objLoginLnk, "Login Link");
			click(AMDLoginScreen.objLoginLnk, "Login Link");
			if (verifyIsElementDisplayed(AMDLoginScreen.objLoginPage)) {
				logger.info(
						"User navigated to Login/Register Screen Tapping on the Login link present on the Intro Screen");
				extent.extentLoggerPass("Login/Register Screen",
						"User is navigated to Login/register Screen Tapping on the Login link present on the Intro Screen");
			} else {
				logger.error(
						"User is not navigated to Login/Register Screen Tapping on the Login link present on the Intro Screen");
				extent.extentLoggerFail("Login/Register Screen",
						"User is not navigated to Login/register Screen Tapping on the Login link present on the Intro Screen");
			}
			waitTime(1000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip link");
			waitTime(5000);
			if (verifyElementPresent(AMDLoginScreen.objHomeTab, "Home Tab")) {
				logger.info("User navigated to Home Tab by clicking on the Skip button");
				extent.extentLoggerPass("Home Tab", "User navigated to Home Tab by clicking on the Skip button");
			} else {
				logger.error("User not navigated to Home Tab by clicking on the Skip button");
				extent.extentLoggerFail("Home Tab", "User not navigated to Home Tab by clicking on the Skip button");
			}
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");

			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, Password, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(5000);
			if (verifyIsElementDisplayed(AMDHomePage.HomeIcon)) {
				logger.info(Username + " logged into to ZEE5 App Successfully");
				extent.extentLoggerPass("Registered User", Username + " logged into to ZEE5 App Successfully");
			}
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");

			String SubscribedUsername = getParameterFromXML("SubscribedUserName");
			String SubscribedPassword = getParameterFromXML("SubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(5000);
			if (verifyIsElementDisplayed(AMDHomePage.HomeIcon)) {
				logger.info(SubscribedUsername + " logged into to ZEE5 App Successfully");
				extent.extentLoggerPass("Registered User",
						SubscribedUsername + " logged into to ZEE5 App Successfully");
			}
			break;
		}
	}

	public void loggerForNonGuestUserTypes(String featureName) {
		extent.HeaderChildNode(featureName);
		logger.info(featureName + " is NOT applicable for " + pUserType);
		extent.extentLoggerPass(featureName, featureName + " is NOT applicable for " + pUserType);
	}

	/**
	 * Author : Sushma
	 */
	public void verifyContinueWatchingTray(String userType, String TabName) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser") | userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("verify Continue Watching tray");
			waitForElementDisplayed(AMDHomePage.objContinueWatchingTray, 30);
			boolean ContinueWatchingTray = verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTray);
			if (ContinueWatchingTray) {
				// Swipe("UP", 1);
				String CWTrayFirstContentTitle = getText(AMDHomePage.objCWTrayContent);
				logger.info(CWTrayFirstContentTitle);
				int leftTimeOnCWTrayContent = timeToSec(getText(AMDHomePage.objLeftTimeOfFirstContentOfCWTray));
				click(AMDHomePage.objContinueWatchingTrayContentCard, "CWTRay Content");
				waitTime(2000);
				if (userType.equals("NonSubscribedUser")) {
					waitForAdToFinishInAmd();
					completeProfilePopUpClose(userType);
				}
				waitForElementDisplayed(AMDPlayerScreen.objPlayer, 15);
				waitTime(6000);
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
				click(AMDPlayerScreen.objPauseIcon, "Pause icon");
				int elapsedTime = timeToSec(getText(AMDPlayerScreen.objTimer));
				int totalTime = timeToSec(getText(AMDPlayerScreen.objTotalDuration));
				int remainingTime = totalTime - elapsedTime;

				if (leftTimeOnCWTrayContent < (remainingTime + 10)) {
					logger.info("Content playback is started from the last view point");
					extent.extentLoggerPass("Continue watching Tray",
							"Content playback is started from the last view point");
				} else {
					logger.error("Content playback is not started from the last view point");
					extent.extentLoggerFail("Continue watching Tray",
							"Content playback is not started from the last view point");
				}

				if (elapsedTime < totalTime) {
					logger.info(
							"Continue watching tray includes only those movies/shows which have not reached the start time of end credit.");
					extent.extentLoggerPass("Continue watching Tray",
							"Continue watching tray includes only those movies/shows which have not reached the start time of end credit.");
				} else {
					logger.error(
							"Continue watching tray includes not only those movies/shows which have not reached the start time of end credit.");
					extent.extentLoggerFail("Continue watching Tray",
							"Continue watching tray includes not only those movies/shows which have not reached the start time of end credit.");
				}
				Back(1);
			} else {
				logger.info("Continue watching tray not displayed in " + TabName + " Screen");
				extent.extentLoggerWarning("Continue watching Tray",
						"Continue watching tray not displayed in " + TabName + " Screen");
			}
		}
	}

	/**
	 * Author : Bhavana
	 */
	public void SettingsVideoStreamingAndAutoPlay(String userType, String searchKeyword1) throws Exception {
		extent.HeaderChildNode("Validation of Settings for Video Streaming and Autoplay as " + userType);
		System.out.println("\n Validation of Settings for Video Streaming and Autoplay as " + userType);
		waitTime(5000);
		click(AMDHomePage.objMoreMenu, "More menu");
		click(AMDMoreMenu.objSettings, "Setting button");
		verifyElementExist(AMDSettingsScreen.objVideoQualityDefaultvalue, "Video Quality option in Settings");
		boolean defaultvalue = verifyIsElementDisplayed(AMDSettingsScreen.objVideoQualityDefaultvalue);
		String value = findElement(AMDSettingsScreen.objVideoQualityDefaultvalue).getText();
		System.out.println(value);
		if (defaultvalue) {
			logger.info("Default video quality in Select video quality settings is displayed : " + value);
			extent.extentLoggerPass("Settings",
					"Default video quality in Select video quality settings is displayed : " + value);
		} else {
			logger.error("Default video quality is NOT displayed");
			extent.extentLogger("Settings", "Default video quality is NOT displayed");
		}
		Back(2);
		waitTime(5000);
		click(AMDHomePage.objSearchBtn, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword1 + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDMoreMenu.objSearchResult(searchKeyword1), "Search result");
		waitTime(5000);
		if (!userType.contains("SubscribedUser")) {
			waitTime(8000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);
			LoadingInProgress();
			waitForAdToFinishInAmd();
			waitTime(2000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		click(AMDPlayerScreen.objFullscreenIcon, "Maximize Icon");
		waitTime(2000);
		click(AMDPlayerScreen.objThreeDotsOnPlayer, "Three dots option");
		String value2 = findElement(AMDSettingsScreen.objQualityOptionOnPlayer).getText();
		verifyElementExist(AMDSettingsScreen.objQualityOptionOnPlayer, "Video quality on player settings " + value2);
		if (value2.contains(value)) {
			logger.info(
					"Default video quality on player settings is as per the selection in Select video quality settings : "
							+ value2);
			extent.extentLoggerPass("Settings",
					"Default video quality on player settings is as per the selection in Select video quality settings : "
							+ value2);
		} else {
			logger.error(
					"Default video quality on player settings is NOT as per the selection in Select video quality settings : "
							+ value2);
			extent.extentLoggerFail("Settings",
					"Default video quality on player settings is NOT as per the selection in Select video quality settings : "
							+ value2);
		}
		click(AMDSettingsScreen.objQualityOptionOnPlayer, "Video Quality");
		String videoOption = findElement(AMDSettingsScreen.objoptionsInVideoQuality).getText();
		System.out.println(videoOption);
		click(AMDSettingsScreen.objoptionsInVideoQuality, "Option " + videoOption);
		waitTime(3000);
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		click(AMDPlayerScreen.objThreeDotsOnPlayer, "Three dots option");
		String DefaultOption = findElement(AMDSettingsScreen.objQualityOptionOnPlayer).getText();
		System.out.println(DefaultOption);
		if (DefaultOption.contains(videoOption)) {
			logger.info("Video Quality is Functional in the player");
			extent.extentLoggerPass("Settings", "Video Quality is Functional in the player");
		} else {
			logger.error("Video Quality is NOT Functional in the player");
			extent.extentLoggerFail("Settings", "Video Quality is NOT Functional in the player");
		}
		Back(3);
		click(AMDHomePage.objMoreMenu, "More menu");
		click(AMDMoreMenu.objSettings, "Settings button");
		String value3 = findElement(AMDSettingsScreen.objVideoQualityDefaultvalue).getText();
		if (DefaultOption.contains(value3) == false) {
			logger.info(
					"Settings changed from the player does not change the video settings set in the 'Select Video Quality' Settings screen.");
			extent.extentLoggerPass("Settings",
					"Settings changed from the player does not change the video settings set in the 'Select Video Quality' Settings screen");
		} else {
			logger.error(
					"settings changed from the player changes the video settings set in the 'Select Video Quality' Settings screen.");
			extent.extentLoggerFail("Settings",
					"settings changed from the player changes the video settings set in the 'Select Video Quality' Settings screen");
		}
		verifyElementExist(AMDSettingsScreen.objAutoPlayToggleSwitch, "Autoplay Toggle Switch");
		String value4 = findElement(AMDSettingsScreen.objAutoPlayToggleON).getText();
		System.out.println(value4);
		boolean autoPlayON = findElement(AMDSettingsScreen.objAutoPlayToggleON).isDisplayed();
		if (autoPlayON) {
			logger.info("Autoplay toggle switch is " + value4);
			extent.extentLogger("Settings", "Autoplay toggle switch is " + value4);
		} else {
			click(AMDSettingsScreen.objAutoPlayToggleSwitch, "Autoplay Toggle Switch");
			logger.info("Autoplay toggle switch is " + value4);
			extent.extentLogger("Settings", "Autoplay toggle switch is " + value4);
		}
		Back(2);
		waitTime(5000);
		click(AMDHomePage.objSearchBtn, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword1 + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDMoreMenu.objSearchResult(searchKeyword1), "Search result");
		waitTime(8000);
		if (!userType.contains("SubscribedUser")) {
			waitTime(8000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);
			LoadingInProgress();
			waitForAdToFinishInAmd();
			waitTime(5000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		// scrubVideoToLast(AMDPlayerScreen.objProgressBar);
		scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
		waitTime(2000);
		verifyElementExist(AMDPlayerScreen.objcontentTitleInconsumptionPage, "Next content");
		waitTime(5000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		waitTime(5000);
		boolean player = verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		if (player) {
			logger.info("When Autoplay switch is " + value4 + " User is automatically moved to the next content");
			extent.extentLoggerPass("Settings",
					"When Autoplay switch is " + value4 + " User is automatically moved to the next content");
		} else {
			logger.error("When Autoplay switch is " + value4 + " User is unable to move to the next content");
			extent.extentLoggerFail("Settings",
					"When Autoplay switch is " + value4 + " User is unable to move to the next content");
		}
		Back(2);
		click(AMDHomePage.objMoreMenu, "More menu");
		click(AMDMoreMenu.objSettings, "Settings button");
		click(AMDSettingsScreen.objAutoPlayToggleSwitch, "Autoplay switch");
		boolean autoPlayOFF = findElement(AMDSettingsScreen.objAutoPlayToggleOFF).isDisplayed();
		String value5 = findElement(AMDSettingsScreen.objAutoPlayToggleOFF).getText();
		if (autoPlayOFF) {
			logger.info("Autoplay toggle switch is " + value5);
			extent.extentLogger("Settings", "Autoplay toggle switch is " + value5);
		}
		Back(2);
		waitTime(5000);
		click(AMDHomePage.objSearchBtn, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword1 + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDMoreMenu.objSearchResult(searchKeyword1), "Search result");
		waitTime(5000);
		if (!userType.contains("SubscribedUser")) {
			waitTime(8000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);
			LoadingInProgress();
			waitForAdToFinishInAmd();
			waitTime(5000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		// scrubVideoToLast(AMDPlayerScreen.objProgressBar);
		scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
		waitTime(2000);
		verifyElementExist(AMDPlayerScreen.objReplayIconOnPlayer, "Replay Icon");
		boolean replay = findElement(AMDPlayerScreen.objReplayIconOnPlayer).isDisplayed();
		if (replay) {
			logger.info("When Autoplay switch is " + value5 + " User can not automatically move to the next content");
			extent.extentLoggerPass("Settings",
					"When Autoplay switch is " + value5 + " User can not automatically move to the next content");
		} else {
			logger.error("When Autoplay switch is " + value5 + "User can automatically move to the next content");
			extent.extentLoggerFail("Settings",
					"When Autoplay switch is " + value5 + " User can automatically move to the next content");
		}
		Back(3);
	}

	/**
	 * Author : Kushal
	 */
	public void ZeeApplicasterLoginForKidsSection(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Login Functionality");
		String UserType = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");
		if (UserType.equals("Guest")) {
			extent.extentLogger("userType", "UserType : Guest");
		}
		verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
		waitTime(3000);

		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitTime(1000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip link");
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User for Settings");
			String pUsername = getParameterFromXML("NonSubsUserKids");
			String pPassword = getParameterFromXML("NonSubsPwdKids");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, pUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, pPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User for Settings");

			String pClubPackUser = getParameterFromXML("SubsUserClubPack");
			String pClubPackPwd = getParameterFromXML("SubsPwdClubPack");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, pClubPackUser, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, pClubPackPwd, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;
		}
	}

	/**
	 * Author : Vinay
	 */
	public void MandatoryPopUpScenarios(String userType) throws Exception {
		extent.HeaderChildNode("Verifying Mandatory PopUp screen on Consumption screen");
		System.out.println("\nVerifying Mandatory PopUp screen on Consumption screen");
		waitTime(5000);

		if (userType.equals("SubscribedUser")) {
			extent.extentLogger("Mandatory PopUp Screen", "Mandatory PopUp Screen is Not Applicable for " + userType);
			logger.info("Mandatory PopUp Screen is Not Applicable for " + userType);
		} else {
			verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
			verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, SVODEpisode, "Search bar");
			hideKeyboard();
			click(AMDSearchScreen.objEpisodeSearch, "Search result");
			extent.extentLogger("", "Playing first content");
			System.out.println("Playing first content");
			waitTime(5000);
			waitForAdToFinishInAmd();
			boolean isRegisterPopUp = verifyIsElementDisplayed(AMDConsumptionScreen.objRegisterPopUp);
			if (isRegisterPopUp) {
				extent.extentLoggerPass("1st Register pop up after ad",
						"Register Pop up is displayed after ad completing for playing first free content");
				logger.info("Register Pop up is displayed after ad completing for playing first free content");
				Back(1);
			} else {
				extent.extentLoggerFail("1st Register pop up after ad",
						"Register Pop up is not displayed after ad completing for playing first free content");
				logger.info("Register Pop up is not displayed after ad completing for playing first free content");
			}

			click(AMDPlayerScreen.objPlayerScreen, "Player");
			scrubVideo(AMDPlayerScreen.objProgressBar);

			for (int i = 1; i <= 5; i++) {

				extent.extentLogger("Content Playing", "Playing content " + (i + 1));
				logger.info("Playing content " + i);
				waitTime(5000);
				if (i == 5) {
					click(AMDConsumptionScreen.objFirstUpNextContent, "Next content");
					waitTime(5000);
					boolean isRegisterPopUpFor5Content = verifyIsElementDisplayed(
							AMDConsumptionScreen.objRegisterPopUp);
					if (isRegisterPopUpFor5Content) {
						extent.extentLoggerPass("Mandatory Register pop up after ad",
								"Mandatory Register Pop up is displayed after  playing 5 SVOD contents");
						logger.info("Mandatory Register Pop up is displayed after  playing 5  SVOD contents");

						click(AMDConsumptionScreen.objLoginBtnOnPopup, "Login button on Register pop up");
						String header = getText(AMDGenericObjects.objgetScreenTitle);
						if (header.equals("Login/Register")) {
							extent.extentLoggerPass("Login screen",
									"User is navigated to Login/Register screen post tapping Login button from Register pop up");
							logger.info(
									"User is navigated to Login/Register screen post tapping Login button from Register pop up");
						} else {
							extent.extentLoggerFail("Login screen",
									"Failed to Navigate into Login/Register screen post tapping login from Register pop up");
							logger.info(
									"Failed to Navigate into Login/Register screen post tapping login from Register pop up");
						}
						hideKeyboard();
						Back(2);
						boolean isRegisterOnPlayerScreen = verifyIsElementDisplayed(
								AMDConsumptionScreen.objRegisterBtnOnPlayer);
						if (userType.equals("Guest")) {
							if (isRegisterOnPlayerScreen) {
								extent.extentLoggerPass("Register text",
										"Register button is displayed on Player screen");
								logger.info("Register button is displayed on Player screen");
							} else {
								extent.extentLoggerFail("Register pop up",
										"Register button is not displayed on Player screen and Playback is initiated");
								logger.info(
										"Register button is not displayed on Player screen and Playback is initiated");
							}
						} else {
							if (isRegisterOnPlayerScreen) {
								extent.extentLoggerPass("Complete Profile",
										"Complete Profile button is displayed on Player screen");
								logger.info("Complete Profile button is displayed on Player screen");
							} else {
								extent.extentLoggerFail("Complete Profile pop up",
										"Complete Profile button is not displayed on Player screen and Playback is initiated");
								logger.info(
										"Complete Profile button is not displayed on Player screen and Playback is initiated");
							}

						}

					} else {
						extent.extentLoggerFail("Mandatory Register pop up after ad",
								"Mandatory Register Pop up is not displayed after  playing 5  free contents");
						logger.info("Mandatory Register Pop up is not displayed after  playing 5  free contents");
					}
				} else {

					click(AMDConsumptionScreen.objFirstUpNextContent, "Next content");
					waitTime(5000);
					waitForAdToFinishInAmd();
					waitTime(2000);
					click(AMDPlayerScreen.objPlayerScreen, "Player");
//				click(AMDPlayerScreen.objPauseIcon,"Pause icon");
					scrubVideo(AMDPlayerScreen.objProgressBar);
					waitTime(5000);
				}
			}
		}
	}

	public void scrubVideo(By byLocator1) throws Exception {

		WebElement element = getDriver().findElement(byLocator1);
		Dimension size = element.getSize();
		int startx = (int) (size.width);
		int startX = startx + 100;
		System.out.println(startX);
		SwipeAnElement(element, startX, 0);
		waitTime(2000);

	}

	public String SVODConsumptionScreenForEpisode(String userType) throws Exception {
		String episodeName = "";

		click(AMDHomePage.objShowsTab, "Shows tab");
		SwipeUntilFindElement(AMDGenericObjects.objTrayTitle("Before"), "Up");
		String beforeTVtrayName = findElement(AMDGenericObjects.objTrayTitle("Before")).getText();
		click(AMDGenericObjects.objViewAllBtn(beforeTVtrayName), "View All_Before TV Show");
		episodeName = getText(AMDConsumptionScreen.objBeforeTVContentName);
		click(AMDConsumptionScreen.objBeforeTVContentName, "BeforeTV content");

		return episodeName;

	}
	
	public void SampleSearchandContentPlayback(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Pause Event of content from search page");
		waitForElementDisplayed(AMDHomePage.objSearchBtn, 30);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(4000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objSkipIntro, "Skip Intro");	
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(2000);
		Back(1);
	}
	
	public void Logout(String userType) throws Exception {
		extent.HeaderChildNode("Logout from App " + userType);
		System.out.println("Logout from App  " + userType);
		click(AMDHomePage.objMoreMenu, "More menu");
		Swipe("UP", 2);
		if (userType.contentEquals("Guest")) {
			if (verifyElementIsNotDisplayed(AMDMoreMenu.objLogout)) {
				logger.info("Logout option is NOT displayed for " + userType + "user");
				extent.extentLoggerPass("Log out", "Logout option is NOT displayed for " + userType + "user");
			} else {
				logger.error("Logout option is displayed for " + userType + "user");
				extent.extentLoggerFail("Log out", "Logout option is displayed for " + userType + "user");
			}
		}
		if (userType.contentEquals("NonSubscribedUser") || userType.contentEquals("SubscribedUser")) {
			verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout option in More menu");
			verifyElementPresent(AMDMoreMenu.objLogoutPopup, "Logout Confirmation Popup");
			verifyElementPresent(AMDMoreMenu.objCancelButton, "Cancel button");
			verifyElementPresent(AMDMoreMenu.objLogoutButton, "Logout button");
			click(AMDMoreMenu.objLogoutButton, "Logout button");
		}
	}	
}