package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5MPWAMixPanelBusinessLogic;

public class AndroidPWAMixpanel_Journey {

	private Zee5MPWAMixPanelBusinessLogic Zee5PWAMixPanelAndroidBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAMixPanelAndroidBusinessLogic = new Zee5MPWAMixPanelBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void ZeePWALogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5PWAMixPanelAndroidBusinessLogic.ZeePWALogin("E-mail", userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifyLogoutEvent(String userType) throws Exception {
		System.out.println("Verify Logout Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyLogoutEvent(userType);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifySessionEvent(String userType) throws Exception {
		System.out.println("Verify Logout Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySessionEvent(userType, "Email");
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifyCarouselBannerClickEvent(String userType) throws Exception {
		System.out.println("Verify Carousel Banner Click Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyCarouselBannerClickEvent(userType, "Home");
	}

	@Test(priority = 4)
	@Parameters({ "userType" })
	public void verifyThumbnailClickEventFromTray(String userType) throws Exception {
		System.out.println("Verify Carousel Banner Click Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyThumbnailClickEventFromTray(userType, "Show");
	}

	@Test(priority = 5)
	@Parameters({ "userType" })
	public void verifyThumbnailClickEventFromViewMorePage(String userType) throws Exception {
		System.out.println("Verify Carousel Banner Click Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyThumbnailClickEventFromViewMorePage("Show");
	}

	@Test(priority = 6)
	@Parameters({ "userType" })
	public void verifyThumbnailClickEventFromShowDetailPage(String userType) throws Exception {
		System.out.println("Verify Thumbnail Click Event From Show Detail Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyThumbnailClickEventFromShowDetailPage(userType);
	}

	@Test(priority = 7)
	@Parameters({ "userType", "keyword" })
	public void verifyThumbnailClickEventFromPlaybackPage(String keyword, String userType) throws Exception {
		System.out.println("Verify Thumbnail Click Event From Playback Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyThumbnailClickEventFromPlaybackPage(userType, keyword);
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void verifyParentalRestriction(String userType) throws Exception {
		System.out.println("Verify Parental Restriction Event");
		Zee5PWAMixPanelAndroidBusinessLogic.relaunch(true);
		Zee5PWAMixPanelAndroidBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalRestrictionEvent(userType, "NoRestriction");

	}

	@Test(priority = 9)
	@Parameters({ "keyword1" })
	public void verifyClearSearchHistoryEvent(String keyword1) throws Exception {
		System.out.println("Verify Clear Search Histroy Event");
		Zee5PWAMixPanelAndroidBusinessLogic.clearSearchHistoryEvent(keyword1);
	}

	@Test(priority = 10)
	public void verifyScreenViewEvent() throws Exception {
		System.out.println("Verify Screen View Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyScreenViewEvent("Shows");
	}

	@Test(priority = 11)
	@Parameters({ "userType" })
	public void verifySetReminderEventForUpcomingProgram(String userType) throws Exception {
		System.out.println("Verify Set Reminder Event For Upcoming Program");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySetReminderEventForUpcomingProgram(userType);
	}

	@Test(priority = 12)
	public void verifySearchButtonClickEvent() throws Exception {
		System.out.println("Verify Search Button Click Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySearchButtonClickEvent();
	}

	@Test(priority = 13)
	public void verifySearchExecutedEvent() throws Exception {
		System.out.println("Verify Search Executed Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySearchExecutedEvent();
	}

	@Test(priority = 14)
	@Parameters({ "keyword" })
	public void verifySearchResultClickedEvent(String keyword) throws Exception {
		System.out.println("Verify Search Result Clicked Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySearchResultClickedEvent(keyword);
	}

	@Test(priority = 15)
	@Parameters({ "userType" })
	public void verifyChangePasswordStartedEvent(String userType) throws Exception {
		System.out.println("Verify Change Password Started Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyChangePasswordStartedEvent(userType);
	}

	@Test(priority = 16)
	@Parameters({ "userType" })
	public void verifyChangePasswordResultEvent(String userType) throws Exception {
		System.out.println("Verify Change Password Result Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyChangePasswordResultEvent(userType);
	}

	@Test(priority = 17)
	public void verifyDisplayLanguageChangeEvent() throws Exception {
		System.out.println("Verify Display Language Change Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyDisplayLanguageChangeEvent();
	}

	@Test(priority = 18)
	public void verifyContentLanguageChangeEvent() throws Exception {
		System.out.println("Verify Content Language Change Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyContentLanguageChangeEvent();
	}

	@Test(priority = 19)
	public void verifyDefaultSettingRestoredEvent() throws Exception {
		System.out.println("Verify Default Setting Restored Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyDefaultSettingRestoredEvent();
	}

	@Test(priority = 20)
	@Parameters({ "userType" })
	public void verifyDeviceAuthenticationEvent(String userType) throws Exception {
		System.out.println("Verify Device Authentication Event when authentication fails in TV Authentication screen");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyDeviceAuthenticationEvent(userType);
	}

	@Test(priority = 21)
	public void verifyCarouselBannerSwipeEvent() throws Exception {
		System.out.println("Verify Carousel Banner Swipe Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyCarouselBannerSwipeEvent("Shows");
	}

	@Test(priority = 22)
	@Parameters({ "keyword" })
	public void verifyViewMoreSelectedEventFromShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify View More Selected Event For content played from Show detail page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyViewMoreSelectedEventFromShowDetailPage(keyword);
	}

	@Test(priority = 23)
	@Parameters({ "audioTrackContent", "userType" })
	public void verifyViewMoreSelectedEventFromPlaybackPage(String audioTrackContent, String userType)
			throws Exception {
		System.out.println("Verify View More Selected Event For content played from Playback page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyViewMoreSelectedEventFromPlaybackPage(audioTrackContent, userType);
	}

	@Test(priority = 24)
	public void verifyViewMoreSelectedEventFromTray() throws Exception {
		System.out.println("Verify View More Selected Event For content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyViewMoreSelectedEventFromTray();
	}

	@Test(priority = 25)
	@Parameters({ "userType", "keyword1" })
	public void verifyAddtoWatchlistFromPlaybackPage(String keyword1, String userType) throws Exception {
		System.out.println("Verify Add to Watchlist Event From Playback Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAddtoWatchlistFromPlaybackPage(userType, keyword1);
	}

	@Test(priority = 26)
	@Parameters({ "userType", "keyword" })
	public void verifyAddToWatchlistEventFromShowDetailPage(String userType, String keyword) throws Exception {
		System.out.println("Verify Add To Watchlist Event from show detail page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAddToWatchlistEventFromShowDetailPage(userType, keyword);
	}

	@Test(priority = 27)
	@Parameters({ "keyword1" })
	public void verifyShareEventFromPlaybackPage(String keyword1) throws Exception {
		System.out.println("Verify Share Event From Playback Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyShareEventFromPlaybackPage(keyword1);
	}

	@Test(priority = 28)
	public void verifyShareEventForUpcomingProgram() throws Exception {
		System.out.println("Verify Share Event for Upcoming Program");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyShareEventForUpcomingProgram();
	}

	@Test(priority = 29)
	@Parameters({ "keyword" })
	public void verifyShareEventFromShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify Share Event From Show Detail Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyShareEventFromShowDetailPage(keyword);
	}

	@Test(priority = 30)
	@Parameters({ "userType" })
	public void verifySettingChangedEventAfterAccountVerification(String userType) throws Exception {
		System.out.println("Verify Setting Changed Event when Parental Control Account Verification is done");
		Zee5PWAMixPanelAndroidBusinessLogic.relaunch(true);
		Zee5PWAMixPanelAndroidBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
		Zee5PWAMixPanelAndroidBusinessLogic.verifySettingChangedEventAfterAccountVerification(userType);
	}

	@Test(priority = 31)
	@Parameters({ "userType" })
	public void verifySettingChangedEventAfterParentalPinIsSet(String userType) throws Exception {
		System.out.println("Verify Setting Changed Event when Parental Control PIN is Set");
		Zee5PWAMixPanelAndroidBusinessLogic.relaunch(true);
		Zee5PWAMixPanelAndroidBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
		Zee5PWAMixPanelAndroidBusinessLogic.verifySettingChangedEventAfterParentalPinIsSet(userType);
	}

	@Test(priority = 32)
	@Parameters({ "userType" })
	public void verifySettingChangedEventAfterAgeIsSet(String userType) throws Exception {
		System.out.println("Verify Setting Changed Event when Parental Control Age is Set");
		Zee5PWAMixPanelAndroidBusinessLogic.relaunch(true);
		Zee5PWAMixPanelAndroidBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
		Zee5PWAMixPanelAndroidBusinessLogic.verifySettingChangedEventAfterAgeIsSet(userType);
	}

	@Test(priority = 34)
	@Parameters({ "userType" })
	public void verifyLoginUsernameEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Login Username Entered Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyLoginUsernameEnteredEvent(userType, "UserName");
	}

	@Test(priority = 35)
	@Parameters({ "userType" })
	public void verifyLoginPasswordEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Login Password Entered Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyLoginUsernameEnteredEvent(userType, "Password");
	}

	@Test(priority = 36)
	public void verifySearchCancelledEvent() throws Exception {
		System.out.println("Verify Search Cancelled Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySearchCancelledEvent();
	}

	@Test(priority = 37)
	public void verifyAdBannerImpressionEvent(String tabName) throws Exception {
		System.out.println("Verify Ad Banner Impression Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdBannerImpressionEvent("Shows");
	}

	@Test(priority = 38)
	@Parameters({ "userType" })
	public void verifyProfileUpdateResultEvent(String userType) throws Exception {
		System.out.println("Verify Profile Update Result Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyProfileUpdateResultEvent(userType);
	}

	@Test(priority = 39)
	@Parameters({ "keyword" })
	public void verifyEpisodeListChosenEventFromShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify Episode List Chosen Event in Show Detail page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyEpisodeListChosenEventFromShowDetailPage(keyword);
	}

	@Test(priority = 40)
	@Parameters({ "keyword" })
	public void verifyContentBucketSwipeEventFromShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify Content Bucket Swipe Event in Show Detail page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyContentBucketSwipeEventFromShowDetailPage(keyword);
	}

	@Test(priority = 41)
	public void verifyContentBucketSwipeEvent() throws Exception {
		System.out.println("Verify Content Bucket Swipe Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyContentBucketSwipeEvent("Shows");
	}

	@Test(priority = 42)
	@Parameters({ "userType", "keyword1" })
	public void verifyContentBucketSwipeEventInPlaybackPage(String keyword1) throws Exception {
		System.out.println("Verify Quality Change Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyContentBucketSwipeEventInPlaybackPage(keyword1);
	}
	
	@Test(priority = 43)
	@Parameters({ "userType", "keyword2" })
	public void verifyPopUpLaunchEventForGetPremiumPopUp(String userType, String keyword2) throws Exception {
		System.out.println("Verify Pop Up Launch Event when get premium popup is displayed on playing premium content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPopUpLaunchEventForGetPremiumPopUp(userType,keyword2);
	}
	
	@Test(priority = 154)
	@Parameters({ "userType", "keyword" })
	public void verifyPopUpLaunchEventForRegisterPopUp(String userType, String keyword) throws Exception {
		System.out.println("Verify Pop Up Launch Event when Native popup is displayed");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPopUpLaunchEventForRegisterPopUp(userType,keyword);
	}
	
	
	@Test(priority = 155)
	@Parameters({ "userType" })
	public void verifyPopUpLaunchEventForCompleteProfilePopUp(String userType) throws Exception {
		System.out.println("Verify Pop Up Launch Event when Complete Profile popup is displayed");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPopUpLaunchEventForCompleteProfilePopUp(userType);
	}
	
	
	// Login through ClubUser Id
	@Test(priority = 156)
	@Parameters({ "userType", "keyword6" })
	public void verifyPopUpLaunchEventForClubUser(String userType, String keyword6) throws Exception {
		System.out.println("Verify Pop Up Launch Event when user gets Upgrade popup for Club User");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPopUpLaunchEventForClubUser(userType,keyword6);
	}

	@Test(priority = 37)
	@Parameters({ "userType" })
	public void verifyCTAsEventHeader(String userType) throws Exception {
		System.out.println("Verify CTAs Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyCTAsEventHeader(userType,"Shows");
	}
	
	@Test(priority = 157)
	@Parameters({ "userType", "keyword6" })
	public void verifyPopUpCTAsEvent(String userType, String keyword6) throws Exception {
		System.out.println("Verify Pop Up CTA's Event when user clicks on CTA button displayed on the popup");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPopUpCTAsEvent(userType,keyword6);
	}
	
	@Test(priority = 303)
	@Parameters({ "userType" })
	public void verifyCTAsEventForIcons(String userType) throws Exception {
		System.out.println("Verify CTAs Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyCTAsEventForIcons(userType,"Language");
	}
	
	@Test(priority = 304)
	@Parameters({ "userType" })
	public void verifyCTAsEventForSubscribeBtn(String userType) throws Exception {
		System.out.println("Verify CTAs Event when user clicks on subscription option");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyCTAsEventForSubscribeBtn(userType);
	}
	
	
	@Test(priority = 305)
	public void verifyCTAsEventForOptionInHamburger() throws Exception {
		System.out.println("Verify CTAs Event when user clicks on any option in hamburger menu");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyCTAsEventForOptionInHamburger();
	}
	
	@Test(priority = 201)
	@Parameters({ "userType"})
	public void verifyRecommendedRailImpressionEventByScrollingPage() throws Exception {
		System.out.println("Verify Recommended Rail Impression event when user is able to see the recommended tray by scrolling down the page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyRecommendedRailImpressionEventByScrollingPage("Home","Trending");
	}
	
	
	@Test(priority = 202)
	@Parameters({ "keyword"})
	public void verifyRecommendedRailImpressionEventInShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify Recommended Rail Impression Event In Show Detail Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyRecommendedRailImpressionEventInShowDetailPage(keyword,"Trending");
	}
	
	@Test(priority = 203)
	@Parameters({ "keyword1"})
	public void verifyRecommendedRailImpressionEventInConsumptionScreen(String keyword1) throws Exception {
		System.out.println("Verify Recommended Rail Impression Event In Consumption Screen");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyRecommendedRailImpressionEventInConsumptionScreen(keyword1,"Trending");
	}
	
	@Test(priority = 160)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInParentalControlScreen(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event In Parental Control Screen");
		Zee5PWAMixPanelAndroidBusinessLogic.relaunch(true);
		Zee5PWAMixPanelAndroidBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventInParentalControlScreen(userType);
	}
	
	@Test(priority = 162)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInAuthenticateScreen(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event In Authenticate Screen");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventInAuthenticateScreen(userType);
	}
	
	
	@Test(priority = 163)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventForAddToWatchlist(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event after adding card to watchlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventForAddToWatchlist(userType);
	}
	
	
	@Test(priority = 164)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventForRemoveFomWatchlist(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event after removing card from watchlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventForRemoveFomWatchlist(userType);
	}
	
	@Test(priority = 165)
	@Parameters({ "userType", "keyword1" })
	public void verifyToastMessageImpressionEventForEmbedPopUp(String userType,String keyword1) throws Exception {
		System.out.println("Verify Toast Message Impression Event when user gets a toast message in embed popup");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventForEmbedPopUp(userType,keyword1);
	}
	
	@Test(priority = 138)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventInSignInScreen(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event In Sign In Screen");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventInSignInScreen(userType);
	}
	
	@Test(priority = 139)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventInOTPScreen(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event In OTP Screen");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventInOTPScreen(userType);
	}
	
	@Test(priority = 136)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInPaymentPage(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event in payment page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventInPaymentPage(userType);
	}
	
	@Test(priority = 142)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventInSignUpScreen(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event In Sign Up Screen");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventInSignUpScreen(userType);
	}
	
	@Test(priority = 151)
	public void verifyToastMessageImpressionEventAfterResetSettingsToDefault() throws Exception {
		System.out.println("Verify Toast Message Impression Event After Reset Settings To Default");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventAfterResetSettingsToDefault();
	}
	
	@Test(priority = 200)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventAfterUpdatingProfile(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression event when user updates the profile details");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventAfterUpdatingProfile(userType);
	}
	
	@Test(priority = 204)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventInPackSelectionPage(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression event in pack selection page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventInPackSelectionPage(userType);
	}
	
	@Test(priority = 347)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventAfterChangingPassword(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression event when user changes the password");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyToastMessageImpressionEventAfterChangingPassword(userType);
	}
	
	@AfterClass
	public void tearDown() {
		Zee5PWAMixPanelAndroidBusinessLogic.tearDown();
	}
}
