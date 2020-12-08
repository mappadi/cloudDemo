package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5MPWAMixPanelBusinessLogic;

public class AndroidPWAMixpanel_OnboardingEvents {

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
	public void verifyLoginScreenDisplayEventByClickingOnLoginButton(String userType) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButton(userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType", "keyword2" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(String userType, String keyword2)
			throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button On Player");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(userType,
				keyword2);
	}

	@Test(priority = 5)
	@Parameters({ "userType" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(String userType)
			throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button In Registartion Screen");
		Zee5PWAMixPanelAndroidBusinessLogic
				.verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(userType);
	}

	@Test(priority = 6)
	@Parameters({ "userType", "keyword" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginInRegistrationPopUp(String userType, String keyword)
			throws Exception {
		System.out.println(
				"Verify Login Screen Display Event during content playback post clicking on login in registration popup");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginInRegistrationPopUp(userType,
				keyword);
	}

	@Test(priority = 7)
	@Parameters({ "userType", "keyword2" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(String userType, String keyword2)
			throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button In Get Premium Pop Up");
		Zee5PWAMixPanelAndroidBusinessLogic
				.verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(userType, keyword2);
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void verifyLoginInitiatedEventForValidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Initiated Event for Valid Credentials");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyLoginInitiatedEventForValidCredentials(userType, "E-mail");
	}

	@Test(priority = 9)
	@Parameters({ "userType" })
	public void verifyLoginInitiatedEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Initiated Event post entering invalid credentials");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyLoginInitiatedEventForInvalidCredentials(userType, "E-mail");
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void verifyLoginResultEventForValidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Result Event for Valid Credentials");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyLoginResultEventForValidCredentials(userType, "E-mail");
	}

	@Test(priority = 11)
	@Parameters({ "userType" })
	public void verifyLoginResultEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Result Event post entering invalid credentials");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyLoginResultEventForInvalidCredentials(userType, "E-mail");
	}

	@Test(priority = 12)
	@Parameters({ "userType" })
	public void verifyTVAuthenticationScreenDisplayEvent(String userType) throws Exception {
		System.out.println("Verify TV Authentication Screen Display Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyTVAuthenticationScreenDisplayEvent(userType);
	}

	@Test(priority = 13)
	@Parameters({ "userType" })
	public void verifyRegisterScreenDisplayEvent(String userType) throws Exception {
		System.out.println("Verify Register Screen Display Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyRegisterScreenDisplayEvent(userType);
	}

	@Test(priority = 14)
	@Parameters({ "userType" })
	public void verifyRegistrationInitiatedEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Registration Initiated Event post entering invalid credentials");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyRegistrationInitiatedEventForInvalidCredentials(userType);
	}

	@Test(priority = 15)
	@Parameters({ "userType" })
	public void verifyRegistrationResultEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Registration Result Event post entering invalid credentials");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyRegistrationResultEventForInvalidCredentials(userType);
	}

	@Test(priority = 16)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaBuySubscription(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on Buy subscription in hamburger menu");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubscriptionPageViewedEventViaBuySubscription(userType);
	}

	@Test(priority = 17)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaPrepaidCode(String userType) throws Exception {
		System.out
				.println("Verify Subscription Page Viewed Event by clicking on prepaid code option in hamburger menu");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubscriptionPageViewedEventViaPrepaidCode(userType);
	}

	@Test(priority = 18)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaSubscribeBtn(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on Subscribe button at header");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubscriptionPageViewedEventViaSubscribeBtn(userType);
	}

	@Test(priority = 19)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event By Clicking on Get Premium CTA On Carousel");
		Zee5PWAMixPanelAndroidBusinessLogic
				.verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel(userType);
	}

	@Test(priority = 20)
	@Parameters({ "userType" })
	public void verifySubscriptionSelectedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Selected Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubscriptionSelectedEvent(userType);
	}

	@Test(priority = 21)
	@Parameters({ "userType" })
	public void verifySubscriptionSelectedEventByClubPack(String userType) throws Exception {
		System.out.println("Verify Subscription Selected Event By selecting Club Pack");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubscriptionSelectedEventByClubPack(userType);
	}

	@Test(priority = 22)
	@Parameters({ "userType" })
	public void verifyPrepaidCodeResultEventForInvalid(String userType) throws Exception {
		System.out.println("Verify Prepaid Code Result Event For Invalid code");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPrepaidCodeResultEventForInvalid(userType);
	}

	@Test(priority = 23)
	@Parameters({ "userType" })
	public void verifyPromoCodeResultEventForValid(String userType) throws Exception {
		System.out.println("Verify Promo Code Result Event For Valid code");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPromoCodeResultEventForValid(userType);
	}

	@Test(priority = 24)
	@Parameters({ "userType" })
	public void verifyPromoCodeResultEventForInvalid(String userType) throws Exception {
		System.out.println("Verify Promo Code Result Event For Invalid code");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPromoCodeResultEventForInvalid(userType);
	}

	@Test(priority = 25)
	@Parameters({ "userType" })
	public void verifySubscriptionCallInitiatedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Call Initiated Event for All access pack");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubscriptionCallInitiatedEvent(userType);
	}

	// Login through ClubUser id
	@Test(priority = 26)
	@Parameters({ "userType" })
	public void verifySubscriptionCallInitiatedEventClubPack(String userType) throws Exception {
		System.out.println("Verify Subscription Call Initiated Event for Club pack");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubscriptionCallInitiatedEventClubPack(userType);
	}

	@Test(priority = 27)
	@Parameters({ "userType" })
	public void verifySubscriptionCallReturnedEvent(String userType) throws Exception {
		System.out.println(
				"Verify Subscription Call Returned Event when user makes unsuccessful transaction by quitting the payment gateway screen");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubscriptionCallReturnedEvent(userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAMixPanelAndroidBusinessLogic.tearDown();
	}

}
