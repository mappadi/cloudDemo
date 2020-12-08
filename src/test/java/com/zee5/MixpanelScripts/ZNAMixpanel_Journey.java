package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_Journey {

	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void PWAWEBMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5ApplicasterMixPanelBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		
	}
	
	@Test(priority = 2)
	public void verifyCarouselBannerClickEvent() throws Exception {
		System.out.println("Verify Carousel Banner Click Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCarouselBannerClickEvent();
	}
	
	@Test(priority = 3)
	public void verifyThumbnailClickEventFromTray() throws Exception {
		System.out.println("Verify Thumbnail Click Event form Tray");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyThumbnailClickEventFromTray();
	}
	
	@Test(priority = 4)
	@Parameters({ "keyword2" })
	public void verifyThumbnailClickEventByClickingThumbnialFromPlaybackPage(String keyword2) throws Exception {
		System.out.println("Verify Thumbnail Click Event form Tray from playback page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyThumbnailClickEventFromTrayInPlayBackPage(keyword2);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType" })
	public void verifyClearSearchHistoryEvent(String userType) throws Exception {
		System.out.println("Verify Clear Search Histroy Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.clearSearchHistoryEvent(userType);
	}
	
	@Test(priority = 6)
	public void verifyScreenViewEvent() throws Exception {
		System.out.println("Verify Screen View Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyScreenViewEvent("Shows");
	}
	
	@Test(priority = 7)
	public void verifyViewMoreSelectedEventFromTray() throws Exception {
		System.out.println("Verify View More Selected Event For content played from Tray");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyViewMoreSelectedEventFromTray();
	}
	
	@Test(priority = 8)
	@Parameters({"keyword2"})
	public void verifyViewMoreSelectedEventFromPlaybackPage(String keyword2) throws Exception {
		System.out.println("Verify View More Selected Event For content played from Playback page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyViewMoreSelectedEventFromPlaybackPage(keyword2);
	}
	
	@Test(priority = 9)
	public void verifySearchButtonClickEvent() throws Exception {
		System.out.println("Verify Search Button Click Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifySearchButtonClickEvent();
	}
	
	@Test(priority = 10)
	@Parameters({"keyword2"})
	public void verifySearchExecutedEvent(String keyword2) throws Exception {
		System.out.println("Verify Clear Search Histroy Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifySearchExecutedEvent(keyword2);
	}
	
	@Test(priority = 11)
	@Parameters({"keyword2"})
	public void verifySearchResultClickedEvent(String keyword2) throws Exception {
		System.out.println("Verify Search Result Clicked Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifySearchResultClickedEvent(keyword2);
	}
	
	@Test(priority = 12)
	public void verifyVideoQualityChangeEvent() throws Exception {
		System.out.println("Video Streaming Quality Changed");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoQualityChangeEvent();
	}
	
	@Test(priority = 13)
	public void verifyVideoAutoPlayChangeEventforDisable() throws Exception {
		System.out.println("Video Streaming AutoPlay Changed for disable");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoAutoPlayChangeEventforDisable();
	}
	
	@Test(priority = 14)
	@Parameters({"displayLanguage"})
	public void verifyDisplayLanguageChangeEvent(String displayLanguage) throws Exception {
		System.out.println("Display language Change");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDisplayLanguageChangeEvent(displayLanguage);
	}
	
	@Test(priority = 15)
	public void verifyContentLanguageChangeEvent() throws Exception {
		System.out.println("content language Change");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyContentLanguageChangeEvent();
	}
	
	@Test(priority = 16)
	public void verifydefaultsettingsRestoreEvent() throws Exception {
		System.out.println("Default settings Restore Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDefaultSettingRestoredEvent();
	}
	
	@Test(priority = 17)
	@Parameters({ "userType" })
	public void verifyParentalRestrictionEvent(String userType) throws Exception {
		System.out.println("Verify Parental Restriction Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch2(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyParentalRestrictionEvent(userType,"Age13+");
	}
	
	@Test(priority = 18)
	@Parameters({ "userType" })
	public void verifySettingsChanged(String userType) throws Exception {
		System.out.println("Verify Parental Restriction Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyParentalRestrictionEvent(userType,"Age13+");
	}
	
	@Test(priority = 19)
	@Parameters({ "userType" })
	public void verifySessionEvent(String userType) throws Exception {
		System.out.println("Verify Session Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchTillIntroScreen(true);
	}
	
	@Test(priority = 20)
	@Parameters({ "tabName" })
	public void verifyCarouselBannerSwipeEvent(String tabName) throws Exception {
		System.out.println("Verify Carousel Banner Swipe Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCarouselBannerSwipeEvent(tabName);
	}
	
	@Test(priority = 21)
	@Parameters({ "userType", "keyword2" })
	public void verifyPopUpLaunchEventForGetPremiumPopUp(String userType, String keyword2) throws Exception {
		System.out.println("Verify Pop Up Launch Event when get premium popup is displayed on playing premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopUpLaunchEventForGetPremiumPopUp(userType,keyword2);
	}
	
	@Test(priority = 22)
	@Parameters({ "userType", "keyword1" })
	public void verifyPopUpLaunchEventForCompleteProfilePopUp(String userType, String keyword1) throws Exception {
		System.out.println("Verify Pop Up Launch Event when Complete Profile popup is displayed");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopUpLaunchEventForCompleteProfilePopUp(userType, keyword1);
	}
	
	@Test(priority = 23)
	@Parameters({ "userType", "keyword1" })
	public void verifyPopUpLaunchEventForRegisterPopUp(String userType, String keyword1) throws Exception {
		System.out.println("Verify Pop Up Launch Event when get register popup is displayed");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopUpLaunchEventForRegisterPopUp(userType,keyword1);
	}
	
	@Test(priority = 24)
	@Parameters({ "userType", "keyword2" })
	public void verifyPopUpCTAsEvent(String userType, String keyword2) throws Exception {
		System.out.println("Verify Pop Up CTA's Event when user clicks on CTA button displayed on the popup");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopUpCTAsEvent(userType,keyword2);
	}
	
	@Test(priority = 25)
	@Parameters({ "userType", "tabName" })
	public void verifyCTAsEvent(String userType,String tabName) throws Exception {
		System.out.println("Verify CTAs Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCTAsEvent(userType,tabName);
	}
	
	@Test(priority = 26)
	@Parameters({ "userType" })
	public void verifyLoginUsernameEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Login Username Entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
	}
	
	@Test(priority = 27)
	@Parameters({ "userType" })
	public void verifyLoginPasswordEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Login Password Entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
	}
	
	@Test(priority = 28)
	@Parameters({ "userType" })
	public void verifyRegistrationDOBEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Registration DOB entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationDOBEnteredEvent(userType);
	}
	
	@Test(priority = 29)
	@Parameters({ "userType" })
	public void verifyRegistrationGenderEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Registration Gender entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationGenderEnteredEvent(userType);
	}
	
	@Test(priority = 30)
	@Parameters({ "userType" })
	public void verifyRegistrationUserNameEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Registration Username entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationUserNameEnteredEvent(userType);
	}
	
	@Test(priority = 31)
	@Parameters({ "userType" })
	public void verifyRegistrationPasswordEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Registration Password entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationPasswordEnteredEvent(userType);
	}
	
	@Test(priority = 32)
	@Parameters({ "userType" })
	public void verifyChangePasswordStartedEvent(String userType) throws Exception {
		System.out.println("Verify Change Password Started Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyChangePasswordStartedEvent(userType);
	}
	
	@Test(priority = 33)
	@Parameters({ "userType" })
	public void verifyChangePasswordResultEvent(String userType) throws Exception {
		System.out.println("Verify Change Password Result Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyChangePasswordResultEvent(userType);
	}
	
	@Test(priority = 34)
	public void verifyVideoAutoPlayChangeEventForEnable() throws Exception {
		System.out.println("Video Streaming AutoPlay Changed for enable");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoAutoPlayChangeEventForEnable();
	}
	
	@Test(priority = 35)
	@Parameters({ "userType", "keyword3" })
	public void verifyAddtoWatchlistFromPlaybackPage(String keyword3,String userType) throws Exception {
		System.out.println("Verify Add to Watchlist Event From Playback Page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyAddtoWatchlistFromPlaybackPageInPotrait(userType,keyword3);
	}
	
	@Test(priority = 36)
	@Parameters({ "userType", "keyword3" })
	public void verifyRemoveFomWatchlistEventFromPlaybackPage(String userType,String keyword3) throws Exception {
		System.out.println("Verify Remove From Watchlist Event From Playback Page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRemoveFromWatchListPlaybackPageInPotrait(userType,keyword3);
	}
	
	// Login through ClubUser Id
	@Test(priority = 37)
	@Parameters({ "userType", "keyword6" })
	public void verifyPopUpLaunchEventForClubUser(String userType, String keyword6) throws Exception {
	    System.out.println("Verify Pop Up Launch Event when user gets Upgrade popup for Club User");
	    Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
	    Zee5ApplicasterMixPanelBusinessLogic.verifyPopUpLaunchEventForClubUser(userType,keyword6);
	}
	
	@Test(priority = 38)
	public void verifyContentBucketSwipeEvent(String tabName) throws Exception {
		System.out.println("Verify Content Bucket Swipe Event");
		//Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyContentBucketSwipeEvent();
	}
	
	@Test(priority = 39)
	@Parameters({ "keyword2" })
	public void verifyContentBucketSwipeEventInPlayBackPage(String keyword2) throws Exception {
		System.out.println("Verify Content Bucket Swipe Event in playback page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyContentBucketSwipeEventInPlayBackPage(keyword2);
	}
	
	@Test(priority = 40)
	@Parameters({ "userType" })
	public void verifyProfileUpdateResultEvent(String userType) throws Exception {
		System.out.println("Verify Profile Update Result Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyProfileUpdateResultEvent(userType);
	}
	
	@Test(priority = 41)
	@Parameters({ "userType", "keyword3" })
	public void verifyAddtoWatchlistFromPlaybackPageInFullScreen(String keyword3,String userType) throws Exception {
		System.out.println("Verify Add to Watchlist Event From Playback Page in Full screen");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyAddtoWatchlistFromPlaybackPageInFullScreen(userType,keyword3);
	}
	
	@Test(priority = 42)
	@Parameters({ "userType", "keyword3" })
	public void verifyRemoveFomWatchlistEventFromPlaybackPageInFullScreen(String userType,String keyword3) throws Exception {
		System.out.println("Verify Remove From Watchlist Event From Playback Page in Full screen");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRemoveFromWatchlistFromPlaybackPageInFullScreen(userType,keyword3);
	}
	
	@Test(priority = 43)
	@Parameters({ "userType"})
	public void verifyRemoveFomWatchlistEventFromWatchListpage(String userType) throws Exception {
		System.out.println("Verify Remove From Watchlist Event From WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRemoveFromWatchlistFromWatchListPage(userType);
	}
	
	@Test(priority = 44)
	public void verifyVideoStreamOverWifiChangeEventWhenEnable() throws Exception {
		System.out.println("Verify Video stream  over Wifi Change event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoStreamOverWifiChangeEventForEnable();
	}
	
	@Test(priority = 45)
	public void verifyVideoStreamOverWifiChangeEventWhenDisable() throws Exception {
		System.out.println("Verify Video stream  over Wifi Change event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoStreamOverWifiChangeEventForDisable();
	}
	
	@Test(priority = 46)
	public void verifyDownloadQualityChangeEvent() throws Exception {
		System.out.println("Verify Download Quality change event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDownloadQualityChangeEvent();
	}
	
	@Test(priority = 47)
	public void verifyDownloadOverWfiEventForEnable() throws Exception {
		System.out.println("Verify Download over wifi change for Enable");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDownloadOverWifiChangeEventForEnable();
	}
	
	@Test(priority = 48)
	public void verifyDownloadOverWfiEventForDisable() throws Exception {
		System.out.println("Verify Download over wifi change for Disable");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDownloadOverWifiChangeEventForDisable();
	}
	
	@Test(priority = 49)
	@Parameters({ "userType", "DisplayLanguage" })
	public void verifyDisplayLanguageChangeFromWelcomPage(String userType, String DisplayLanguage) throws Exception {
		System.out.println("Verify Display Language change from Welcome page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchTillDisplayLanguageScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDisplayLanguageChangeFromWelcomePage(userType, DisplayLanguage);
	}
	
	@Test(priority = 50)
	@Parameters({ "userType"})
	public void verifyContentLanguageChangeFromWelcomPage(String userType) throws Exception {
		System.out.println("Verify Content Language change from Welcome page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchTillDisplayLanguageScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyContinueLanguageFromWelcomePage(userType);
	}
	
	@Test(priority = 51)
	@Parameters({ "userType" })
	public void verifyFirstAppLaunchEvent(String userType) throws Exception {
		System.out.println("Verify First App launch Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchTillIntroScreen(true);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
}