package com.zee5.MixpanelScripts;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class PWA_Web_Onboarding {


	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void PWAWEBMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLogin(userType);
	}
//======================SkipeLogin=============================================
	@Test(priority = 2)
	@Parameters({ "userType"})
	public void verifySkipLoginEvent(String userType) throws Exception {
		System.out.println("Verify Skip Login Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginEvent(userType);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType", "keyword" })
	public void verifySkipLoginByClickingOnLoginInRegistrationPopUp(String userType, String keyword) throws Exception {
		System.out.println("Verify Skip Login Event during content playback post clicking on login in registration popup");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginByClickingOnLoginInRegistrationPopUp(userType,keyword);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "keyword2" })
	public void verifySkipLoginByClickingOnLoginInGetPremiumPopUp(String userType, String keyword2) throws Exception {
		System.out.println("Verify Skip Login Event during content playback post clicking on login button in Get Premium popup");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginByClickingOnLoginInGetPremiumPopUp(userType,keyword2);
	}
	
	@Test(priority = 5)
	public void verifySkipLoginThroughBeforeTVContent() throws Exception {
		System.out.println("Verify Skip Login Event gets triggered when user click on close button in login popup "
				+ "on clicking login in Get premium popup on accessing before tv content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginThroughBeforeTVContent();
	}
	
//==========================SkipRegistration======================================
	
	@Test(priority = 6)
	@Parameters({ "userType" })
	public void verifySkipRegistrationEvent(String userType) throws Exception {
		System.out.println("Verify Skip Registration Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipRegistrationEvent(userType);
	}
	
//==============================LoginScreenDisplay=================================
	@Test(priority = 7)
	@Parameters({ "userType" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButton(String userType) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButton(userType);
	}
	
	@Test(priority = 8)
	@Parameters({ "userType", "keyword2" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(String userType, String keyword2) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button On Player");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(userType, keyword2);
	}
	
	@Test(priority = 9)
	@Parameters({ "userType"})
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(String userType) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button In Registartion Screen");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(userType);
	}
	
	@Test(priority = 10)
	@Parameters({ "userType", "keyword2" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(String userType, String keyword2) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button In Get Premium Pop Up");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(userType, keyword2);
	}
	
//==============================Logout=============================================
	
	@Test(priority = 11)
	@Parameters({ "userType"})
	public void verifyLogoutEvent(String userType) throws Exception {
		System.out.println("Verify Logout Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyLogoutEvent(userType);
	}
	
//===========================LoginInitiate===========================================
	
	@Test(priority = 12)
	@Parameters({ "userType" })
	public void verifyLoginInitiatedEventForValidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Initiated Event for Valid Credentials");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginInitiatedEventForValidCredentials(userType,"emailLogin");
	}
	
//===========================LoginResult============================================
	
	@Test(priority = 13)
	@Parameters({ "userType" })
	public void verifyLoginResultEventForValidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Result Event for Valid Credentials");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginResultEventForValidCredentials(userType,"emailLogin");
	}
	
	@Test(priority = 14)
	@Parameters({ "userType"})
	public void verifyLoginResultEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Result Event post entering invalid credentials");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginResultEventForInvalidCredentials(userType,"mobileNumberLogin");
	}
//===============================TVAuthentication=====================================
	
	
	@Test(priority = 15)
	@Parameters({ "userType" })
	public void verifyTVAuthenticationScreenDisplayEvent(String userType) throws Exception {
		System.out.println("Verify TV Authentication Screen Display Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyTVAuthenticationScreenDisplayEvent(userType);
	}
	
//===============================RegisterScreenDisplay==================================
	
	@Test(priority = 16)
	@Parameters({ "userType" })
	public void verifyRegisterScreenDisplayEvent(String userType) throws Exception {
		System.out.println("Verify Register Screen Display Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyRegisterScreenDisplayEvent(userType);
	}
	
//============================RegistrationInitiated=======================================
	
	@Test(priority = 17)
	@Parameters({ "userType"})
	public void verifyRegistrationInitiatedEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Registration Initiated Event post entering invalid credentials");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyRegistrationInitiatedEventForInvalidCredentials(userType);
	}
	
//===========================PrepaidCodeResult============================================
	@Test(priority = 18)
	@Parameters({ "userType"})
	public void verifyPrepaidCodeResultEventForInvalid(String userType) throws Exception {
		System.out.println("Verify Prepaid Code Result Event For Invalid code");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPrepaidCodeResultEventForInvalid(userType);
	}
//=============================SubscriptionCallReturned===================================
	@Test(priority = 19)
	@Parameters({ "userType"})
	public void verifySubscriptionCallReturnedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Call Returned Event when user makes unsuccessful transaction by quitting the payment gateway screen");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionCallReturnedEvent(userType);
	}
	
//=============================SubscriptionCallInitiated==================================
	
	@Test(priority = 20)
	@Parameters({ "userType"})
	public void verifySubscriptionCallInitiatedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Call Initiated Event for All access pack");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionCallInitiatedEvent(userType);
	}
	
	
	@Test(priority = 21)
	@Parameters({ "userType"})
	public void verifySubscriptionCallInitiatedEventClubPack(String userType) throws Exception {
		System.out.println("Verify Subscription Call Initiated Event for Club pack");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionCallInitiatedEventClubPack(userType);
	}
	
//============================PromoCodeResult==============================================
	@Test(priority = 22)
	@Parameters({ "userType" })
	public void verifyPromoCodeResultEventForValid(String userType) throws Exception {
		System.out.println("Verify Promo Code Result Event For Valid code");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPromoCodeResultEventForValid(userType);
	}
	
	@Test(priority = 23)
	@Parameters({ "userType" })
	public void verifyPromoCodeResultEventForInvalid(String userType) throws Exception {
		System.out.println("Verify Promo Code Result Event For Invalid code");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPromoCodeResultEventForInvalid(userType);
	} 
	
//=============================RegistrationResultEvent=======================================
	
	@Test(priority = 24)
	@Parameters({ "userType"})
	public void verifyRegistrationResultEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Registration Result Event post entering invalid credentials");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyRegistrationResultEventForInvalidCredentials(userType);
	}
	
//===============================SubscriptionPageViewedEvent=================================
	@Test(priority = 25)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaSubscribeBtn(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on Subscribe button at header");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaSubscribeBtn(userType);
	}
	
	@Test(priority = 26)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaBuySubscription(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on Buy subscription in hamburger menu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaBuySubscription(userType);
	}
	
	@Test(priority = 27)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaPrepaidCode(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on prepaid code option in hamburger menu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaPrepaidCode(userType);
	}
	
	@Test(priority = 28)
	public void verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel() throws Exception {
		System.out.println("Verify Subscription Page Viewed Event By Clicking on Get Premium CTA On Carousel");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel();
	}
	
	@Test(priority = 29)
	@Parameters({ "userType" })
	public void verifySubscriptionSelectedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Selected Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionSelectedEvent(userType);
	}
	
	@Test(priority = 30)
	@Parameters({ "userType" })
	public void verifySubscriptionSelectedEventByClubPack(String userType) throws Exception {
		System.out.println("Verify Subscription Selected Event By selecting Club Pack");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionSelectedEventByClubPack(userType);
	}
}
