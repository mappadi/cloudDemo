package com.business.zee;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import com.driverInstance.CommandBase;
import com.extent.ExtentReporter;
import com.jayway.restassured.response.Response;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.TVPages.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

public class Zee5TvBusinessLogic extends Utilities {

	public Zee5TvBusinessLogic(String Application) throws InterruptedException {
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

	static String code = "";

	Set<String> hash_Set = new HashSet<String>();

	@SuppressWarnings("unused")
	private String LacationBasedLanguge;

	List<String> LocationLanguage = new ArrayList<String>();

	List<String> DefaultLanguage = new ArrayList<String>();

	List<String> SelectedCONTENTLanguageInWelcomscreen = new ArrayList<String>();

	List<String> SelectedCONTENTLanguageInHamburgerMenu = new ArrayList<String>();

	Response resp;

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

	public String carouselTitle = "";
	public String carouselDescription = "";

	String userType = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");

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

	public void TvtearDown() {
		getDriver().quit();
	}

	public void BrowsertearDown() {
		getWebDriver().quit();
	}

	public void LoadingInProgress() throws Exception {
		TVVerifyElementNotPresent(Zee5TvPlayerPage.objProgressLoader, 60);
		extent.extentLoggerPass("Progress Loader Matched", "Progress Loader Verfied");
	}

	public void AdVerify() throws Exception {
		TVVerifyElementNotPresent(Zee5TvPlayerPage.objAd, 60);
		extent.extentLoggerPass("Ad Verified", "Ad Verified");
	}

	public void WaitforPlaypauseDissappear() throws Exception {
		TVVerifyElementNotPresent(Zee5TvPlayerPage.objAd, 180);
		extent.extentLogger("Ad Verified", "Ad Verified");
	}
	
	public void TVTabSelect(String str) throws Exception {
		TVclick(Zee5TvHomePage.objSelectTab(str), str);
		Thread.sleep(2000);
		try {
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSelectTab(str)).equals("false")) {
				TVclick(Zee5TvHomePage.objSelectTab(str), str);
			} else {
				logger.info("Highlighted Tab:" + TVgetText(Zee5TvHomePage.objHighlightedTab));
				extent.extentLoggerPass("Tab", "Highlighted Tab:" + TVgetText(Zee5TvHomePage.objHighlightedTab));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void type(String array[]) throws Exception {
		String searchdata[] = array;
		int searchdatalength = searchdata.length;
		StringBuilder searchData = new StringBuilder();
		for (int j = 0; j < searchdatalength; j++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchKeyboardBtn(searchdata[j])).click();
			searchData.append(searchdata[j]);
		}
		logger.info("Searching for the content : " + searchData);
		extentLogger("search", "Searching for the content : " + searchData);
	}

	public void searchScenarios(String userType) throws Exception {
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			searchMovie();
			searchPremium();
			searchEPG();
			partlySpletSearch();
			actorSearch();
			genreSearch();
			languageSearch();
		}
		if (userType.equals("SubscribedUser")) {
			searchMovie();
			searchEPG();
			partlySpletSearch();
			actorSearch();
			genreSearch();
			languageSearch();
		}
	}

	@SuppressWarnings("unused")
	public void searchMovie() throws Exception {
		extent.HeaderChildNode("Search Button Functionality");
		waitTime(10000);
		if (verifyElementExistTv(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}

		waitTime(5000);

		TVTabSelect("Home");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(3000);
		if (verifyElementExistTv(Zee5TvSearchPage.objSearchSpaceBar, "Search page")) {
			logger.info("User is navigated to search page after clicking on search button");
			extent.extentLoggerPass("Search", "User is navigated to search page after clicking on search button");
		} else {
			logger.info("User is not navigated to serach page");
			extent.extentLoggerFail("Navigation", "User is not navigated to serach page");
		}
		String searchdata1[] = { "b", "a", "b", "l", "u", };
		String searchdata2[] = { "d", "a", "b", "l", "u" };
		String searchdata3[] = { "r", "o", "b", "o" };
		String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
		HeaderChildNode("Search Movie content and its playback functionality");
		type(searchdata1);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
		type(searchdata2);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata3);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata4);
		HeaderChildNode("Searched Movie playback functionality");
		String content = TVgetText(Zee5TvSearchPage.objEditbox);

		logger.info("Entered Search Data : " + content);
		extent.extentLogger("Search", "Entered Searched Data : " + content);

		int k;
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele.size(); i++) {

			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLogger("Title", "Serach result content title : " + title);
			if ((verifyElementExistTv(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
					"Searched Movie"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
				break;
			} else {
				System.out.println("No match");
			}

		}

		waitTime(8000);
		LoadingInProgress();

		String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

		waitTime(10000);

		// Consumption Page

		LoadingInProgress();
		waitTime(5000);
		AdVerify();

		waitTime(5000);

		if (verifyElementExistTv(Zee5TvPlayerPage.objPlayerSkipIntro, "SkipIntro")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(23);
			logger.info("clicked on skip intro");
			extent.extentLoggerPass("Intro", "clicked on skip intro");
		} else {
			logger.info("Skip intro is not displayed");
			extent.extentLogger("Intro", "Skip intro is not displayed");
		}
		waitTime(12000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(4000);
		if (verifyElementExistTv(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
			logger.info("User is able to play free content");
			extent.extentLoggerPass("play", "User is able to play free content");
		} else {
			logger.info("playback did not initiate");
			extent.extentLoggerFail("play", "playback did not initiate");
		}

	}
//	public void search_ScenarioNews() throws Exception {
//		HeaderChildNode("Seacrhed news content functionality");
//	getDriver().navigate().back();
//	waitTime(3000);
//	getDriver().navigate().back();
//	waitTime(3000);
//	int lenText = getDriver().findElement(tvZee5SearchPage.objEditbox).getAttribute("text").length();
//	System.out.println(lenText);
//	for (int i = 0; i < lenText; i++) {
//		getDriver().findElement(tvZee5SearchPage.objSearchBackButton).click();
//		waitTime(2000);
//	}
//	String searchdata1[] = { "n", "e", "w", "s"};
//	type(searchdata1);
//	 HeaderChildNode("Searched News playback functionality");
//		String content = TVgetText(tvZee5SearchPage.objEditbox);
//
//		logger.info("Entered Search Data : " + content);
//		extent.extentLogger("Search", "Entered Searched Data : " + content);
//
//		int k;
//		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
//		for (int i = 1; i <= ele.size(); i++) {
//
//			String title = TVgetText(tvZee5SearchPage.objSearchedTumbnailTitle(i));
//			logger.info(title);
//         extent.extentLogger("Title", "Serached title : " + title);
//			if ((verifyElementExistTv(tvZee5SearchPage.objSearchedSpecificTumbnailcard(title, "Videos"), "Searched News"))) {
//				TVclick(tvZee5SearchPage.objSearchedSpecificTumbnailcard(title, "Videos"), "serached News");
//				break;
//			} else {
//				System.out.println("No match");
//			}
//			if (TVgetText(tvZee5PlayerPage.objPlayerTitle).equals(title)) {
//				logger.info("searched content playback initiated");
//				extent.extentLogger("Player Matched", "searched content playback initiated");
//			} else
//				extent.extentLoggerFail("Player Not Matched", "Player Not Matched");
//
//		} 
//		}

	public void searchPremium() throws Exception {
		HeaderChildNode("Seacrhed Premium content playback functionality");
		getDriver().navigate().back();
		waitTime(5000);
		getDriver().navigate().back();
		waitTime(5000);
		if (verifyElementExistTv(Zee5TvSearchPage.objEditbox, "Edit box")) {
			logger.info("User is navigated to search page");
		} else {
			getDriver().navigate().back();
		}
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();
		}
		String searchdata1[] = { "g", "o", "o", "l", "i" };
		type(searchdata1);
		HeaderChildNode("Searched Premium content popup functionality");
		String content = TVgetText(Zee5TvSearchPage.objEditbox);

		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);

		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele.size(); i++) {

			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLogger("Title", "Serach result content title : " + title);
			if ((verifyElementExistTv(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
					"Searched Premium movie"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
				break;
			} else {
				logger.info("No match");
			}

		}
		LoadingInProgress();
		waitTime(8000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(4000);
		if (userType.equals("Guest")) {
			if (verifyElementExistTv(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium conent as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium conent as guest user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}

		}
		if (userType.equals("NonSubscribedUser")) {
			if (verifyElementExistTv(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
				logger.info("Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
				extent.extentLoggerPass("Popup",
						"Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}
		}
	}

	@SuppressWarnings("unused")
	public void searchEPG() throws Exception {
		HeaderChildNode("Searched EPG content playback functionality");
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		if (verifyElementExistTv(Zee5TvSearchPage.objEditbox, "Edit box")) {
			logger.info("User is navigated to search page");
		} else {
			getDriver().navigate().back();
		}
		waitTime(3000);
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();
			waitTime(2000);
		}
		String searchdata1[] = { "n", "e", "w", "s" };
		type(searchdata1);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
		verifyElementExistTv(Zee5TvSearchPage.objSearchPageNowPlayingButton, "Now playing option");
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objSearchPageNowPlayingButton, "Now playing option");
		TVclick(Zee5TvSearchPage.objSearchPageNowPlayingButton, "Now playing option");
		waitTime(3000);
		boolean epg = verifyElementExistTv(Zee5TvSearchPage.objElapsedTime, "Search result content Elapsed time");
		if (epg) {
			logger.info("EPG contents are displayed");
			extent.extentLoggerPass("Search", "EPG contents are displayed");
		} else {
			logger.info("EPG contents are not displayed");
			extent.extentLogger("Search", "EPG contents are not displayed");
		}
		verifyElementExistTv(Zee5TvSearchPage.objElapsedTime, "Elapsed time");
		verifyElementExistTv(Zee5TvSearchPage.objChalnnelName, "Channel name");
		verifyElementExistTv(Zee5TvSearchPage.objProgressBar, "Progress bar");
		verifyElementExistTv(Zee5TvSearchPage.objEPGtitle, "EPG Title");
		String channelName = TVgetText(Zee5TvSearchPage.objChalnnelName);
		String channelTitle = TVgetText(Zee5TvSearchPage.objEPGtitle);
		TVclick(Zee5TvSearchPage.objSearchedTumbnailImageEPG(channelName), "EPG news content");
		waitTime(10000);
		LoadingInProgress();
		waitTime(5000);
		AdVerify();
		waitTime(2000);
		for (int i = 0; i <= 5; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(2000);
			if (verifyElementExistTv(Zee5TvSearchPage.objGotolivebutton, "Live button in player")) {
				logger.info("User is able play Free EPG content");
				extent.extentLoggerPass("EPG", "User is able play Free EPG content");
				break;
			}
		}
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(8000);
		if (verifyElementExistTv(Zee5TvSearchPage.objEditbox, "Edit box")) {
			logger.info("User is navigated to search page");
		} else {
			getDriver().navigate().back();
		}
		int lenText2 = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText2; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();

		}
	}

	@SuppressWarnings("unused")
	public void partlySpletSearch() throws Exception {
		HeaderChildNode("Partly spelt content functionality");
		String searchdata1[] = { "g", "a", "t", "t", "i" };
		type(searchdata1);
		waitTime(3000);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= 3; i++) {

			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLoggerPass("Title", "Serach result content title : " + title);
			if (title.contains("Gatti")) {
				logger.info("Partly spelt asset is displayed in search page");
				extent.extentLoggerPass("Spelt", "Partly spelt asset is displayed in search page");
			} else {
				logger.info("Partly spelt asset is not displayed in search page");
				extent.extentLogger("Spelt", "Partly spelt asset is not displayed in search page");
			}
		}
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();

		}
	}

	@SuppressWarnings("unused")
	public void actorSearch() throws Exception {
		HeaderChildNode("Searched Actor content functionality");
		String searchdata1[] = { "r", "a", "m", "e", "s", "h" };
		type(searchdata1);
		waitTime(3000);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= 3; i++) {

			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLoggerPass("Title", "Serach result content title : " + title);
			if (title.contains("Ramesh")) {
				logger.info("Actor name is displayed in search page when user search for actor");
				extent.extentLoggerPass("Spelt", "Actor name is displayed in search page when user search for actor");
			} else {
				logger.info("Actor name is not displayed in search page when user search for actor");
				extent.extentLogger("Spelt", "Actor name is not displayed in search page when user search for actor");
			}
		}
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();

		}
	}

	@SuppressWarnings("unused")
	public void genreSearch() throws Exception {
		HeaderChildNode("Searched Genre content functionality");
		String searchdata1[] = { "c", "o", "m", "e", "d", "y" };
		type(searchdata1);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
		waitTime(3000);
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= 3; i++) {

			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLoggerPass("Title", "Serach result content title : " + title);
			if (title.contains("Comedy")) {
				logger.info("Genre search results are displayed when user search for particular genre");
				extent.extentLoggerPass("Spelt",
						"Genre search results are displayed when user search for particular genre");
			} else {
				logger.info("Genre search results are not displayed when user search for particular genre");
				extent.extentLogger("Spelt",
						"Genre search results are not displayed when user search for particular genre");
			}
		}
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();

		}
	}

	@SuppressWarnings("unused")
	public void languageSearch() throws Exception {
		HeaderChildNode("Searched language content functionality");
		String searchdata1[] = { "k", "a", "n", "n", "a", "d", "a" };
		type(searchdata1);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
		waitTime(3000);
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= 2; i++) {

			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLoggerPass("Title", "Serach result content title : " + title);
			if (title.contains("Kannada")) {
				logger.info("language search results are displayed when user search for particular language");
				extent.extentLoggerPass("Spelt",
						"language search results are displayed when user search for particular language");
			} else {
				logger.info("language search results are not displayed when user search for particular language");
				extent.extentLogger("Spelt",
						"language search results are not displayed when user search for particular language");
			}
		}
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();

		}
		waitTime(3000);
		getDriver().navigate().back();
	}

	public void login(String userType) throws Exception {
		HeaderChildNode("Authentication");
		if (userType.equals("Guest")) {
			logger.info("Guest user");
			extent.extentLoggerPass("Login", "Guest user scenarios");
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			HeaderChildNode("Login as : " + userType);
			waitTime(15000);
			verifyElementExistTv(Zee5TvWelcomePage.objalreadyRegister, "Already Register button");
			TVclick(Zee5TvWelcomePage.objalreadyRegister, "Already Register button");
			waitTime(3000);
			code = TVgetText(Zee5TvWelcomePage.objloginCode);
			logger.info("Authenticate code in TV : " + code);
			extentLoggerPass("Code", "Authenticate code in TV : " + code);
			HeaderChildNode("Switching to WEB platform to Authenticate device");
			setPlatform("Web");
			new Zee5TvBusinessLogic("zee");
			waitTime(5000);

			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

			if (userType.equals("NonSubscribedUser")) {
				String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedUserName");
				String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedPassword");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, Password, "Password field");
				waitTime(5000);

			}
			if (userType.equals("SubscribedUser")) {
				String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedUserName");
				String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedPassword");
				type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
				waitTime(5000);

			}
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(8000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
			waitTime(3000);
			checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
			type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			waitTime(3000);
			BrowsertearDown();
			setPlatform("TV");
			waitTime(10000);
			TVclick(Zee5TvWelcomePage.objcontinueButtonInLoginPage, "Continue button in TV authentication page");
			waitTime(10000);
		}
	}

	public void playbackHomepage() throws Exception {
		HeaderChildNode("Content detail page functionality through Home page");
		waitTime(10000);
		if (verifyElementExistTv(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}
		waitTime(5000);
		TVTabSelect("Home");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		for (int i = 0; i <= 10; i++) {
			waitTime(3000);
			if (verifyElementExistTv(Zee5TvWelcomePage.objHomepageTrayContent, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objHomepageTrayContent, "Tray content");
				waitTime(7000);
				if (verifyElementExistTv(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objHomepageTrayContent, "Movie page Tray content");
				}
				break;
			} else {
				Runtime.getRuntime().exec("adb shell input keyevent 20");
				waitTime(3000);
			}
		}
		waitTime(10000);
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentTitleIncontentPage, "Content Title")) {
			logger.info("Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
			extent.extentLoggerPass("title",
					"Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
		} else {
			logger.info("content title is not displayed");
			extent.extentLoggerFail("title", "content title is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentDescriptionIncontentPage, "Content Description")) {
			logger.info("Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
			extent.extentLoggerPass("title",
					"Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
		} else {
			logger.info("content Description is not displayed");
			extent.extentLoggerFail("title", "content Description is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentTypeIncontentPage, "Content type")) {
			logger.info("Content type : " + TVgetText(Zee5TvWelcomePage.objContentTypeIncontentPage));
			extent.extentLoggerPass("title",
					"Content type : " + TVgetText(Zee5TvWelcomePage.objContentTypeIncontentPage));
		} else {
			logger.info("content type is not displayed");
			extent.extentLoggerFail("title", "content type is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentgenreIncontentPage, "Content genre")) {
			logger.info("Content genre : " + TVgetText(Zee5TvWelcomePage.objContentgenreIncontentPage));
			extent.extentLoggerPass("title",
					"Content genre : " + TVgetText(Zee5TvWelcomePage.objContentgenreIncontentPage));
		} else {
			logger.info("content genre is not displayed");
			extent.extentLoggerFail("title", "content genre is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentcertificateIncontentPage, "Content certificate")) {
			logger.info("Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentcertificateIncontentPage));
			extent.extentLoggerPass("title",
					"Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentcertificateIncontentPage));
		} else {
			logger.info("content certificate is not displayed");
			extent.extentLoggerFail("title", "content certificate is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentdurationIncontentPage, "Content duration")) {
			logger.info("Content duration : " + TVgetText(Zee5TvWelcomePage.objContentdurationIncontentPage));
			extent.extentLoggerPass("title",
					"Content duration : " + TVgetText(Zee5TvWelcomePage.objContentdurationIncontentPage));
		} else {
			logger.info("content duration is not displayed");
			extent.extentLoggerFail("title", "content duration is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContenttrailerplaybackIncontentPage, "Trailer playback")) {
			logger.info("Auto trailer playback is initiated");
			extent.extentLoggerPass("trailer", "Auto trailer playback is initiated");
		} else {
			logger.info("Content does not have trailer");
			extent.extentLoggerPass("trailer", "Content does not have trailer");
		}

		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(4000);
		if (userType.equals("Guest")) {
			if (verifyElementExistTv(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium conent as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium conent as guest user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}

		}
		if (userType.equals("NonSubscribedUser")) {
			if (verifyElementExistTv(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
				logger.info("Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
				extent.extentLoggerPass("Popup",
						"Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}
		}
		if (userType.equals("SubscribedUser")) {
			LoadingInProgress();
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			if (verifyElementExistTv(Zee5TvPlayerPage.objPlayerContainer, "Consumption page")) {
				logger.info("User is able to play premium content for premium user");
				extent.extentLoggerPass("play", "User is able to play premium content for premium user");
			} else {
				logger.info("playback did not initiate");
				extent.extentLoggerFail("play", "playback did not initiate");
			}

		}
		getDriver().navigate().back();
		waitTime(5000);
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExistTv(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				LoadingInProgress();
				waitTime(15000);
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				waitTime(3000);
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				if (verifyElementExistTv(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
					logger.info("User is able to play free content");
					extent.extentLoggerPass("play", "User is able to play free content");
				} else {
					logger.info("playback did not initiate");
					extent.extentLoggerFail("play", "playback did not initiate");
				}
				getDriver().navigate().back();
				waitTime(5000);
			} else {
				logger.info("Watch trailer button is not present");
				extent.extentLoggerPass("trailer", "Watch trailer button is not present");
			}
		}
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(5000);
		for (int k = 0; k <= 8; k++) {
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			waitTime(2000);
		}
	}

	public void playbackShowspage() throws Exception {
		HeaderChildNode("Content detail page functionality through Shows page");
		TVTabSelect("Shows");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		for (int i = 0; i <= 15; i++) {
			waitTime(3000);
			if (verifyElementExistTv(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content")) {
				TVclick(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content");
				if (verifyElementExistTv(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content");
				}
				break;
			} else {
				Runtime.getRuntime().exec("adb shell input keyevent 20");
				waitTime(3000);
			}
		}
		waitTime(10000);
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentTitleIncontentPage, "Content Title")) {
			logger.info("Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
			extent.extentLoggerPass("title",
					"Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
		} else {
			logger.info("content title is not displayed");
			extent.extentLoggerFail("title", "content title is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentDescriptionIncontentPage, "Content Description")) {
			logger.info("Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
			extent.extentLoggerPass("title",
					"Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
		} else {
			logger.info("content Description is not displayed");
			extent.extentLoggerFail("title", "content Description is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentShowTypeIncontentPage, "Content type")) {
			logger.info("Content type : " + TVgetText(Zee5TvWelcomePage.objContentShowTypeIncontentPage));
			extent.extentLoggerPass("title",
					"Content type : " + TVgetText(Zee5TvWelcomePage.objContentShowTypeIncontentPage));
		} else {
			logger.info("content type is not displayed");
			extent.extentLoggerFail("title", "content type is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentShowgnereIncontentPage, "Content genre")) {
			logger.info("Content genre : " + TVgetText(Zee5TvWelcomePage.objContentShowgnereIncontentPage));
			extent.extentLoggerPass("title",
					"Content genre : " + TVgetText(Zee5TvWelcomePage.objContentShowgnereIncontentPage));
		} else {
			logger.info("content genre is not displayed");
			extent.extentLoggerFail("title", "content genre is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentShowcertificateIncontentPage, "Content certificate")) {
			logger.info("Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentShowcertificateIncontentPage));
			extent.extentLoggerPass("title",
					"Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentShowcertificateIncontentPage));
		} else {
			logger.info("content certificate is not displayed");
			extent.extentLoggerFail("title", "content certificate is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContenttrailerplaybackIncontentPage, "Trailer playback")) {
			logger.info("Auto trailer playback is initiated");
			extent.extentLoggerPass("trailer", "Auto trailer playback is initiated");
		} else {
			logger.info("Content does not have trailer");
			extent.extentLoggerPass("trailer", "Content does not have trailer");
		}

		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(4000);
		if (userType.equals("Guest")) {
			if (verifyElementExistTv(Zee5TvSearchPage.objShowsLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium conent as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium conent as guest user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}

		}
		if (userType.equals("NonSubscribedUser")) {
			if (verifyElementExistTv(Zee5TvSearchPage.objShowsLoginPopup, "Subscribe now popup")) {
				logger.info("Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
				extent.extentLoggerPass("Popup",
						"Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Functioanlity failed");
			}
		}
		if (userType.equals("SubscribedUser")) {
			LoadingInProgress();
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			if (verifyElementExistTv(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
				logger.info("User is able to play premium content for premium user");
				extent.extentLoggerPass("play", "User is able to play premium content for premium user");
			} else {
				logger.info("playback did not initiate");
				extent.extentLoggerFail("play", "playback did not initiate");
			}

		}
		getDriver().navigate().back();
		waitTime(5000);
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			// Runtime.getRuntime().exec("adb shell input keyevent 22");
			if (verifyElementExistTv(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				LoadingInProgress();
				waitTime(10000);
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				waitTime(3000);
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				if (verifyElementExistTv(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
					logger.info("User is able to play free content");
					extent.extentLoggerPass("play", "User is able to play free content");
				} else {
					logger.info("playback did not initiate");
					extent.extentLoggerFail("play", "playback did not initiate");
				}
				getDriver().navigate().back();
				waitTime(5000);
			} else {
				logger.info("Watch trailer button is not present");
				extent.extentLoggerPass("Trailer", "Watch trailer button is not present");
			}
		}
		getDriver().navigate().back();
		waitTime(5000);
		for (int k = 0; k <= 7; k++) {
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			waitTime(3000);
		}
	}

	public void playbackMoviespage() throws Exception {
		HeaderChildNode("Content detail page functionality through Movies page");
		waitTime(10000);
		TVTabSelect("Movies");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		for (int i = 0; i <= 10; i++) {
			waitTime(3000);
			if (verifyElementExistTv(Zee5TvWelcomePage.objMoviePageTrayContent, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objMoviePageTrayContent, "Tray content");
				if (verifyElementExistTv(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objMoviePageTrayContent, "Movie page Tray content");
				}
				break;
			} else {
				Runtime.getRuntime().exec("adb shell input keyevent 20");
				waitTime(3000);
			}
		}
		waitTime(10000);
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentTitleIncontentPage, "Content Title")) {
			logger.info("Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
			extent.extentLoggerPass("title",
					"Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
		} else {
			logger.info("content title is not displayed");
			extent.extentLoggerFail("title", "content title is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentDescriptionIncontentPage, "Content Description")) {
			logger.info("Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
			extent.extentLoggerPass("title",
					"Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
		} else {
			logger.info("content Description is not displayed");
			extent.extentLoggerFail("title", "content Description is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentTypeIncontentPage, "Content type")) {
			logger.info("Content type : " + TVgetText(Zee5TvWelcomePage.objContentTypeIncontentPage));
			extent.extentLoggerPass("title",
					"Content type : " + TVgetText(Zee5TvWelcomePage.objContentTypeIncontentPage));
		} else {
			logger.info("content type is not displayed");
			extent.extentLoggerFail("title", "content type is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentgenreIncontentPage, "Content genre")) {
			logger.info("Content genre : " + TVgetText(Zee5TvWelcomePage.objContentgenreIncontentPage));
			extent.extentLoggerPass("title",
					"Content genre : " + TVgetText(Zee5TvWelcomePage.objContentgenreIncontentPage));
		} else {
			logger.info("content genre is not displayed");
			extent.extentLoggerFail("title", "content genre is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentcertificateIncontentPage, "Content certificate")) {
			logger.info("Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentcertificateIncontentPage));
			extent.extentLoggerPass("title",
					"Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentcertificateIncontentPage));
		} else {
			logger.info("content certificate is not displayed");
			extent.extentLoggerFail("title", "content certificate is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentdurationIncontentPage, "Content duration")) {
			logger.info("Content duration : " + TVgetText(Zee5TvWelcomePage.objContentdurationIncontentPage));
			extent.extentLoggerPass("title",
					"Content duration : " + TVgetText(Zee5TvWelcomePage.objContentdurationIncontentPage));
		} else {
			logger.info("content duration is not displayed");
			extent.extentLoggerFail("title", "content duration is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContenttrailerplaybackIncontentPage, "Trailer playback")) {
			logger.info("Auto trailer playback is initiated");
			extent.extentLoggerPass("trailer", "Auto trailer playback is initiated");
		} else {
			logger.info("Content does not have trailer");
			extent.extentLoggerPass("trailer", "Content does not have trailer");
		}

		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(4000);
		if (userType.equals("Guest")) {
			if (verifyElementExistTv(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium conent as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium conent as guest user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}

		}
		if (userType.equals("NonSubscribedUser")) {
			if (verifyElementExistTv(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
				logger.info("Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
				extent.extentLoggerPass("Popup",
						"Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}
		}
		if (userType.equals("SubscribedUser")) {
			LoadingInProgress();
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			if (verifyElementExistTv(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
				logger.info("User is able to play premium content for premium user");
				extent.extentLoggerPass("play", "User is able to play premium content for premium user");
			} else {
				logger.info("playback did not initiate");
				extent.extentLoggerFail("play", "playback did not initiate");
			}

		}
		getDriver().navigate().back();
		waitTime(5000);
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			// Runtime.getRuntime().exec("adb shell input keyevent 22");
			if (verifyElementExistTv(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				LoadingInProgress();
				waitTime(10000);
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				waitTime(3000);
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				if (verifyElementExistTv(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
					logger.info("User is able to play free content");
					extent.extentLoggerPass("play", "User is able to play free content");
				} else {
					logger.info("playback did not initiate");
					extent.extentLoggerFail("play", "playback did not initiate");
				}
				getDriver().navigate().back();
				waitTime(5000);
			} else {
				logger.info("Watch trailer button is not present");
				extent.extentLoggerPass("Trailer", "Watch trailer button is not present");
			}
		}
		getDriver().navigate().back();
		waitTime(5000);
		for (int k = 0; k <= 8; k++) {
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			waitTime(2000);
		}

	}

	public void playbackPremiumpage() throws Exception {
		HeaderChildNode("Content detail page functionality through Premium page");
		waitTime(10000);
		TVTabSelect("Premium");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		for (int i = 0; i <= 10; i++) {
			waitTime(3000);
			if (verifyElementExistTv(Zee5TvWelcomePage.objPremiumPageTrayContent, "Tray content")) {
				waitTime(3000);
				TVclick(Zee5TvWelcomePage.objPremiumPageTrayContent, "Tray content");
				waitTime(3000);
				if (verifyElementExistTv(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
					TVclick(Zee5TvWelcomePage.objPremiumPageTrayContent, "Premium page Tray content");
				}
				break;
			} else {
				Runtime.getRuntime().exec("adb shell input keyevent 20");
				waitTime(3000);
			}
		}
		waitTime(10000);
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentTitleIncontentPage, "Content Title")) {
			logger.info("Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
			extent.extentLoggerPass("title",
					"Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
		} else {
			logger.info("content title is not displayed");
			extent.extentLoggerFail("title", "content title is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentDescriptionIncontentPage, "Content Description")) {
			logger.info("Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
			extent.extentLoggerPass("title",
					"Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
		} else {
			logger.info("content Description is not displayed");
			extent.extentLoggerFail("title", "content Description is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentTypeIncontentPage, "Content type")) {
			logger.info("Content type : " + TVgetText(Zee5TvWelcomePage.objContentTypeIncontentPage));
			extent.extentLoggerPass("title",
					"Content type : " + TVgetText(Zee5TvWelcomePage.objContentTypeIncontentPage));
		} else {
			logger.info("content type is not displayed");
			extent.extentLoggerFail("title", "content type is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentgenreIncontentPage, "Content genre")) {
			logger.info("Content genre : " + TVgetText(Zee5TvWelcomePage.objContentgenreIncontentPage));
			extent.extentLoggerPass("title",
					"Content genre : " + TVgetText(Zee5TvWelcomePage.objContentgenreIncontentPage));
		} else {
			logger.info("content genre is not displayed");
			extent.extentLoggerFail("title", "content genre is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentcertificateIncontentPage, "Content certificate")) {
			logger.info("Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentcertificateIncontentPage));
			extent.extentLoggerPass("title",
					"Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentcertificateIncontentPage));
		} else {
			logger.info("content certificate is not displayed");
			extent.extentLoggerFail("title", "content certificate is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentdurationIncontentPage, "Content duration")) {
			logger.info("Content duration : " + TVgetText(Zee5TvWelcomePage.objContentdurationIncontentPage));
			extent.extentLoggerPass("title",
					"Content duration : " + TVgetText(Zee5TvWelcomePage.objContentdurationIncontentPage));
		} else {
			logger.info("content duration is not displayed");
			extent.extentLoggerFail("title", "content duration is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContenttrailerplaybackIncontentPage, "Trailer playback")) {
			logger.info("Auto trailer playback is initiated");
			extent.extentLoggerPass("trailer", "Auto trailer playback is initiated");
		} else {
			logger.info("Content does not have trailer");
			extent.extentLoggerPass("trailer", "Content does not have trailer");
		}

		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(4000);
		if (userType.equals("Guest")) {
			if (verifyElementExistTv(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium conent as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium conent as guest user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "popup Functioanlity failed");
			}

		}
		if (userType.equals("NonSubscribedUser")) {
			if (verifyElementExistTv(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
				logger.info("Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
				extent.extentLoggerPass("Popup",
						"Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}
		}
		if (userType.equals("SubscribedUser")) {
			LoadingInProgress();
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			if (verifyElementExistTv(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
				logger.info("User is able to play premium content for premium user");
				extent.extentLoggerPass("play", "User is able to play premium content for premium user");
			} else {
				logger.info("playback did not initiate");
				extent.extentLoggerFail("play", "playback did not initiate");
			}

		}
		getDriver().navigate().back();
		waitTime(5000);
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {

			if (verifyElementExistTv(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				LoadingInProgress();
				waitTime(10000);
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				waitTime(3000);
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				if (verifyElementExistTv(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
					logger.info("User is able to play free content");
					extent.extentLoggerPass("play", "User is able to play free content");
				} else {
					logger.info("playback did not initiate");
					extent.extentLoggerFail("play", "playback did not initiate");
				}
				getDriver().navigate().back();
				waitTime(5000);
			} else {
				logger.info("Watch trailer button is not present");
				extent.extentLoggerPass("Trailer", "Watch trailer button is not present");
			}
		}
		getDriver().navigate().back();
		waitTime(5000);
		for (int k = 0; k <= 8; k++) {
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			waitTime(2000);
		}
	}

	public void playbackvideospage() throws Exception {
		HeaderChildNode("Content detail page functionality through Videos page");
		TVTabSelect("Videos");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		for (int i = 0; i <= 15; i++) {
			waitTime(3000);
			if (verifyElementExistTv(Zee5TvWelcomePage.objVideoPageTrayContent, "Videos page Tray content")) {
				TVclick(Zee5TvWelcomePage.objVideoPageTrayContent, "Videos page Tray content");
				waitTime(4000);
				if (verifyElementExistTv(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
					TVclick(Zee5TvWelcomePage.objVideoPageTrayContent, "Videos page Tray content");
				}
				break;
			} else {
				Runtime.getRuntime().exec("adb shell input keyevent 20");
				waitTime(3000);
			}
		}
		waitTime(10000);
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentTitleIncontentPage, "Content Title")) {
			logger.info("Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
			extent.extentLoggerPass("title",
					"Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
		} else {
			logger.info("content title is not displayed");
			extent.extentLoggerFail("title", "content title is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentDescriptionIncontentPage, "Content Description")) {
			logger.info("Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
			extent.extentLoggerPass("title",
					"Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
		} else {
			logger.info("content Description is not displayed");
			extent.extentLoggerFail("title", "content Description is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentShowTypeIncontentPage, "Content type")) {
			logger.info("Content type : " + TVgetText(Zee5TvWelcomePage.objContentShowTypeIncontentPage));
			extent.extentLoggerPass("title",
					"Content type : " + TVgetText(Zee5TvWelcomePage.objContentShowTypeIncontentPage));
		} else {
			logger.info("content type is not displayed");
			extent.extentLoggerFail("title", "content type is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentShowgnereIncontentPage, "Content genre")) {
			logger.info("Content genre : " + TVgetText(Zee5TvWelcomePage.objContentShowgnereIncontentPage));
			extent.extentLoggerPass("title",
					"Content genre : " + TVgetText(Zee5TvWelcomePage.objContentShowgnereIncontentPage));
		} else {
			logger.info("content genre is not displayed");
			extent.extentLoggerFail("title", "content genre is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContentShowcertificateIncontentPage, "Content certificate")) {
			logger.info("Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentShowcertificateIncontentPage));
			extent.extentLoggerPass("title",
					"Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentShowcertificateIncontentPage));
		} else {
			logger.info("content certificate is not displayed");
			extent.extentLoggerFail("title", "content certificate is not displayed");
		}
		if (verifyElementExistTv(Zee5TvWelcomePage.objContenttrailerplaybackIncontentPage, "Trailer playback")) {
			logger.info("Auto trailer playback is initiated");
			extent.extentLoggerPass("trailer", "Auto trailer playback is initiated");
		} else {
			logger.info("Content does not have trailer");
			extent.extentLoggerPass("trailer", "Content does not have trailer");
		}

		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(4000);

		LoadingInProgress();
		waitTime(2000);
		AdVerify();
		waitTime(10000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		if (verifyElementExistTv(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
			logger.info("User is navigated to consumption screen");
			extent.extentLoggerPass("play", "User is navigated to consumption screen");
		} else {
			logger.info("playback did not initiate");
			extent.extentLoggerFail("play", "playback did not initiate");
		}
		getDriver().navigate().back();
		waitTime(5000);
		getDriver().navigate().back();
		waitTime(5000);
		for (int k = 0; k <= 8; k++) {
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			waitTime(3000);
		}
		waitTime(3000);
		for (int k = 0; k <= 8; k++) {
			Runtime.getRuntime().exec("adb shell input keyevent 21");
			waitTime(3000);
		}
	}

	public void playbackNewspage() throws Exception {
		HeaderChildNode("Content detail page functionality through News page");
		TVTabSelect("News");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		for (int i = 0; i <= 15; i++) {
			waitTime(3000);
			if (verifyElementExistTv(Zee5TvWelcomePage.objLiveNewsContentinNewsPage, "News page Tray content")) {
				waitTime(5000);
				TVclick(Zee5TvWelcomePage.objLiveNewsContentinNewsPage, "News page Tray content");
				TVclick(Zee5TvWelcomePage.objLiveNewsContentinNewsPage, "News page Tray content");
				waitTime(4000);
				break;
			} else {
				Runtime.getRuntime().exec("adb shell input keyevent 20");
				waitTime(3000);
			}
		}
		waitTime(5000);
		LoadingInProgress();
		AdVerify();
		waitTime(15000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(2000);
		if (verifyElementExistTv(Zee5TvSearchPage.objGotolivebutton, "Live button in player")) {
			logger.info("User is navigated to consumption screen post tapping on content is news page");
			extent.extentLoggerPass("Page",
					"User is navigated to consumption screen post tapping on content is news page");
		} else {
			logger.info("Functionality failed");
			extent.extentLoggerFail("Play", "Playback Functionality failed");
		}
		getDriver().navigate().back();
		for (int k = 0; k <= 5; k++) {
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			waitTime(3000);
		}

	}

	public void carouselValidation(String tab) throws Exception {
		HeaderChildNode("Carousel Validation " + tab + " Tab");
		if (userType.equals("Guest")) {
			waitTime(10000);
			if (verifyElementExistTv(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
		}
		waitTime(3000);
		TVTabSelect(tab);
		waitTime(5000);
		HeaderChildNode("Auto rotation functionality");
		carouselTitle = TVgetText(Zee5TVCarousel.objCarouselTitle);
		logger.info("Carousel title before auto rotation :" + carouselTitle);
		extent.extentLoggerPass("Auto", "Carousel title before auto rotation :" + carouselTitle);
		waitTime(5000);
		TVTabSelect(tab);
		String carouselTitle2 = TVgetText(Zee5TVCarousel.objCarouselTitle);
		logger.info("Carousel title after auto rotation :" + carouselTitle2);
		extent.extentLoggerPass("Auto", "Carousel title after auto rotation :" + carouselTitle2);
		if (!carouselTitle.equals(carouselTitle2)) {
			logger.info("Auto rotation functionality successfull");
			extent.extentLoggerPass("Auto", "Auto rotation functionality successfull");
		} else {
			logger.info("Auto rotation functionality failed");
			extent.extentLoggerFail("Auto", "Auto rotation functionality failed");
		}
		HeaderChildNode("Carousel content details");
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(7000);
		TVclick(Zee5TVCarousel.objCarouselTitle, "Carousel title");
		if (verifyElementExistTv(Zee5TVCarousel.objCarouselTitle, "Carousel title")) {
			carouselTitle = TVgetText(Zee5TVCarousel.objCarouselTitle);
			logger.info("Carousel title : " + carouselTitle);
			extent.extentLoggerPass("Title", "Carousel title : " + carouselTitle);
		} else {
			logger.info("Carousel title is not displayed");
			extent.extentLoggerFail("Title", "Carousel title is not displayed");
		}

		if (verifyElementExistTv(Zee5TVCarousel.objCarouselPremiumTag, "Carousel Premium tag")) {
			logger.info("Premium tag is displayed for premium content");
			extent.extentLoggerPass("Tag", "Premium tag is displayed for premium content");
		} else {
			logger.info("Premium tag is not displayed in carousel for " + carouselTitle);
			extent.extentLoggerPass("Title", "Premium tag is not displayed in carousel for " + carouselTitle);
		}
		if (verifyElementExistTv(Zee5TVCarousel.objCarouselMetadata, "Carousel Metadata")) {
			String carouselMetadata = TVgetText(Zee5TVCarousel.objCarouselMetadata);
			logger.info("Carousel Metadata : " + carouselMetadata);
			extent.extentLoggerPass("Metadata", "Carousel Metadata : " + carouselMetadata);
		} else {
			logger.info("Carousel Metadata is not displayed for" + carouselTitle);
			extent.extentLoggerPass("Carousel", "Carousel Metadata is not displayed for" + carouselTitle);
		}
		if (verifyElementExistTv(Zee5TVCarousel.objCarouselDescription, "Carousel Description")) {
			carouselDescription = TVgetText(Zee5TVCarousel.objCarouselDescription);
			logger.info("Carousel Description : " + carouselDescription);
			extent.extentLoggerPass("Description", "Carousel Description : " + carouselDescription);
		} else {
			logger.info("Carousel Description is not displayed for " + carouselTitle);
			extent.extentLoggerFail("Carousel", "Carousel Description is not displayed for " + carouselTitle);
		}
		if (verifyElementExistTv(Zee5TVCarousel.objCarouselPlayButton, "Carousel play button")) {
			logger.info("Carousel play button is displayed");
			extent.extentLoggerPass("Carousel", "Carousel play button is displayed");
		} else {
			logger.info("Carousel play button is not displayed");
			extent.extentLoggerFail("Carousel", "Carousel play button is not displayed");
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExistTv(Zee5TVCarousel.objCarouselSubscribeButton, "Carousel Subscribe button")) {
				logger.info("carousel subscribe button is displayed");
				extent.extentLoggerPass("carousel", "carousel subscribe button is displayed");
			} else {
				logger.info("carousel subscribe button is not displayed for " + carouselTitle);
				extent.extentLoggerPass("Title", "carousel subscribe button is not displayed for " + carouselTitle);
			}
		}
		if (userType.equals("SubscribedUser")) {
			if (verifyElementExistTv(Zee5TVCarousel.objCarouselSubscribeButton, "Carousel Subscribe button")) {
				logger.info("carousel subscribe button is displayed for subscribed user");
				extent.extentLoggerFail("carousel", "carousel subscribe button is displayed for subscribed user");
			} else {
				logger.info("carousel subscribe button is not displayed for subscribed user ");
				extent.extentLoggerPass("Title", "carousel subscribe button is not displayed for subscribed user");
			}
		}
		if (carouselDescription.contains(carouselTitle)) {
			logger.info("Metadata and title macthed");
			extent.extentLoggerPass("Title", "Metadata and title macthed");
		} else {
			logger.info("Metadata and title does not macthed");
			extent.extentLogger("Title", "Metadata and title does not macthed");
		}
		HeaderChildNode("Carousel play button functionality");
		verifyElementExistTv(Zee5TVCarousel.objCarouselPlayButton, "Play button");
		TVclick(Zee5TVCarousel.objCarouselPlayButton, "Play button");
		if (tab.equals("News")) {
			waitTime(10000);
			LoadingInProgress();
			AdVerify();
			waitTime(5000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(2000);
			if (verifyElementExistTv(Zee5TvSearchPage.objGotolivebutton, "Live button in player")) {
				logger.info(
						"User is navigated to consumption screen post tapping on carousel play button is news page");
				extent.extentLoggerPass("Page",
						"User is navigated to consumption screen post tapping on carousel play button is news page");
			} else {
				logger.info("Functionality failed");
				extent.extentLoggerFail("Play", "Playback Functionality failed");
			}
		}
		if (tab.equals("Videos")) {
			waitTime(5000);
			LoadingInProgress();
			waitTime(2000);
			AdVerify();
			waitTime(10000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			if (verifyElementExistTv(Zee5TvPlayerPage.objPlayPause, "Player pause")) {
				logger.info("User is navigated to consumption screen");
				extent.extentLoggerPass("play", "User is navigated to consumption screen");
			} else {
				logger.info("playback did not initiate");
				extent.extentLoggerFail("play", "playback did not initiate");
			}
		}
		waitTime(5000);
		if (tab.equals("News") == false) {
			if (tab.equals("Videos") == false) {
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(4000);
				if (userType.equals("Guest")) {
					if (verifyElementExistTv(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium conent as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium conent as guest user");
					} else {
						logger.info("User is navigated to consumption page");
						extent.extentLoggerPass("Popup", "User is navigated to consumption page");
					}

				}
				if (userType.equals("NonSubscribedUser")) {
					if (verifyElementExistTv(Zee5TvSearchPage.objLoginPopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium conent as Nonsubscribe user");
					} else {
						logger.info("User is navigated to consumption page");
						extent.extentLoggerPass("Popup", "User is navigated to consumption page");
					}
				}
				if (userType.equals("SubscribedUser")) {
					LoadingInProgress();
					waitTime(10000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(3000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(3000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					if (verifyElementExistTv(Zee5TvPlayerPage.objPlayPause, "Player pause button")) {
						logger.info("User is able to play premium content for premium user");
						extent.extentLoggerPass("play", "User is able to play premium content for premium user");
					} else {
						logger.info("playback did not initiate");
						extent.extentLoggerFail("play", "playback did not initiate");
					}

				}
				getDriver().navigate().back();
				waitTime(8000);
			}
		}
		getDriver().navigate().back();
		waitTime(3000);
		if (verifyElementExistTv(Zee5TVCarousel.objCarouselSubscribeButton, "Subscribe button")) {
			TVclick(Zee5TVCarousel.objCarouselSubscribeButton, "Subscribe button");
			waitTime(3000);
			if (verifyElementExistTv(Zee5TVCarousel.objSubscriptionPlanPage, "My Plans page")) {
				logger.info("User is navigated to Subscription page post tapping on get premium tab in carousel");
				extent.extentLoggerPass("plan",
						"User is navigated to Subscription page post tapping on get premium tab in carousel");
				getDriver().navigate().back();
			} else {
				logger.info("Navigation failed");
				extent.extentLoggerFail("Plan", "Navigation failed");
			}
		} else {
			logger.info("carousel subscribe button is not displayed for " + carouselTitle);
			extent.extentLoggerPass("Title", "carousel subscribe button is not displayed for " + carouselTitle);
		}
		Runtime.getRuntime().exec("adb shell input keyevent 19");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 19");
	}

}
