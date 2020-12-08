package com.zee5.PWASanityScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASanityAndroidBusinessLogic;

public class AndroidPWASanityScript {

	private Zee5PWASanityAndroidBusinessLogic Zee5PWASanityBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWASanityBusinessLogic = new Zee5PWASanityAndroidBusinessLogic("Chrome");
	}

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void Login(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.ZeePWALogin("E-mail", userType);
		Zee5PWASanityBusinessLogic.selectLanguages();
	}

	@Test(priority = 1)
	@Parameters({ "userType" }) // SHREENIDHI
	public void PWAMandatoryRegistration(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.registerPopUpScenarios(userType);
	}

	@Test(priority = 2) // SHREENIDHI
	@Parameters({ "userType" })
	public void PWAOnboardingScenarios(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.OnboardingScenario(userType); // from smoke
	}

	@Test(priority = 3)
	@Parameters({ "userType" }) // SHREENIDHI
	public void PWAMenuOrSettingScenarios(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.MenuOrSettingScenarios(userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType" }) // MANAS
	public void PWACarousel() throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.verifyCarouselAutoRotation();
		Zee5PWASanityBusinessLogic.verifyCarouselPlayIconFunctionality();
		Zee5PWASanityBusinessLogic.verifyCarouselPremiumIconFunctionality();
		Zee5PWASanityBusinessLogic.verifyCarouselMetaData();
		Zee5PWASanityBusinessLogic.verifyCarouselLeftRightFunctionality();
		System.out.println(">>>> 4th method completed");
	}

	@Test(priority = 5) // VINAY
	@Parameters({ "userType" })
	public void PWALanguageModule(String UserType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.LanguageModule(UserType);
	}

	@Test(priority = 6)
	@Parameters({ "userType" }) // SHREENIDHI
	public void PWANetworkInterruptions(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.networkInterruptionScenarios(userType);
	}

	@Test(priority = 7) // SHREENIDHI
	@Parameters({ "userType" })
	public void PWAProfile(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.myProfileScenarios(userType);
	}

	@Test(priority = 8) // BASAVARAJ
	@Parameters({ "userType" })
	public void PWAKalturaPlayability(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.Kaltura(userType);
		// smoke Vinay
		Zee5PWASanityBusinessLogic.playerValidations(userType);
		// Zee5PWASanityBusinessLogic.UpnextRail();
	}

	@Test(priority = 9) // BASAVARAJ
	@Parameters({ "userType", "consumptionsEpisode", "consumptionsShow", "consumptionsFreeContent",
			"consumptionsPremiumContent" })
	public void PWAContentDetails(String userType, String consumptionsEpisode,
			String consumptionsShow, String consumptionsFreeContent, String consumptionsPremiumContent)
			throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.ContentDetails(userType);
		Zee5PWASanityBusinessLogic.checkDurationInLivetv();
		Zee5PWASanityBusinessLogic.checkDurationandProgressVideo(userType);
		// smoke Tanisha
		Zee5PWASanityBusinessLogic.verifyConsumptionsScreenTappingOnCard(userType, "Episode", consumptionsEpisode); 
		Zee5PWASanityBusinessLogic.verifyConsumptionsScreenTappingOnCard(userType, "Live TV", "");
		Zee5PWASanityBusinessLogic.verifyNoSubscriptionPopupForFreeContent(userType, consumptionsFreeContent);
		Zee5PWASanityBusinessLogic.verifySubscriptionPopupForPremiumContent(userType, consumptionsPremiumContent);
		Zee5PWASanityBusinessLogic.verifyCTAandMetaDataInDetailsAndConsumption(consumptionsShow);
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void PWAMoviesPageValidation(String userType) throws Exception // SUSHMA MOVIES
	{
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.Moviepage(userType, "Movies");
	}

	@Test(priority = 11)
	@Parameters({ "userType" }) // HITESH
	public void PWAMusicPageValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.MusicPageValidation(userType, "Music");
	}

	@Test(priority = 12)
	@Parameters({ "userType" }) // MANASA
	public void PWAPremiumPageValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.premiumPageValidation(userType, "Premium");
	}

	@Test(priority = 13)
	@Parameters({ "userType" }) // BHAVANA
	public void PWAShowsPageValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.ShowsValidation(userType);
	}

	@Test(priority = 14)
	@Parameters({ "userType" }) // MANASA
	public void PWALandingPagesHomeValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.ValidatingLandingPages(userType);// Tejas
		Zee5PWASanityBusinessLogic.verifyUIofHomePage(); // manasa default home

	}

	@Test(priority = 15)
	@Parameters({ "userType" }) // YASHASHWINI
	public void PWANewsPageValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.newsPageValidation(userType, "News");
	}

	@Test(priority = 16) // BINDU
	@Parameters({ "userType" })
	public void PWAZeeOriginalsPageValidation(String UserType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.Zee5OriginalsScreen(UserType, "ZEE5 Originals");
	}

	@Test(priority = 17) // BINDU
	@Parameters({ "userType" })
	public void PWAClubPageValidation(String UserType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.Clubpage(UserType, "Club");
	}
	
	@Test(priority = 17) 
	@Parameters({ "userType" })
	public void PWAEduauraaVerification(String UserType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAEduauraaVerification(UserType);
	}


	@Test(priority = 18) // TANISHA
	@Parameters({ "userType" })
	public void PWARecoTalamoosModule(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.verificationOfRecoTalamoos(userType);
	}

	@Test(priority = 19) // BHAVANA
	@Parameters({ "userType" })
	public void PWAStaticPagesInMenuAndFooterSection(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.staticPagesandFooterSectionValidation(userType);
	}

	@Test(priority = 20)
	@Parameters({ "userType", "searchModuleSearchKey", "url" }) // SUSHMA SEARCH
	public void PWASearch(String userType, String searchKey, String url) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.searchScreen(userType);
		Zee5PWASanityBusinessLogic.searchResultScreen(searchKey);
		Zee5PWASanityBusinessLogic.navigationToConsumptionScreenThroughTrendingSearches();
	}

	@Test(priority = 21)
	@Parameters({ "userType" })
	public void PWALiveTV(String userType) throws Exception // SUSHMA LIVE TV
	{
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.LiveTvScreen(userType);
		// smoke
		Zee5PWASanityBusinessLogic.verifyLiveTvAndChannelGuideScreen();

	}

	@Test(priority = 22)
	@Parameters({ "userType" }) // SATHISH
	public void PWASubscriptionPopUp(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.zeePWAVerifySubscriptionPopupAfterTrailerPlaybackIsComplete(userType);
		Zee5PWASanityBusinessLogic
				.zeePWAVerifyNavigationToSubscriptionFlowFromSubscriptionPopupFullscreenPlayer(userType);
		// smoke
		Zee5PWASanityBusinessLogic.zeePWASubscriptionSuite(userType);
	}

	@Test(priority = 23)
	@Parameters({ "userType" }) // MANASA
	public void PWASubscriptionPageValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.contentLanguageVerify(userType);
		Zee5PWASanityBusinessLogic.verifyUIofZEESubscriptionPage(userType);
		Zee5PWASanityBusinessLogic.verifyUIofMySubscriptionPage(userType);
		Zee5PWASanityBusinessLogic.validatingActiveAndExpiredCardsinMyTransactionPage(userType);
	}

	@Test(priority = 24)
	@Parameters({ "userType" }) // BHAVANA
	public void PWAExternalLinks(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.LinksValidation(userType);
	}

	@Test(priority = 25)
	@Parameters({ "userType" }) // VINAY
	public void PWAUserActions(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.UserActions(userType);
	}

	@Test(priority = 26) // BASAVARAJ
	@Parameters({ "userType" })
	public void PWATimedAnchors(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.ShowsTimeperiodProvided(userType);
		Zee5PWASanityBusinessLogic.musicTimeperiodProvided(userType);
		Zee5PWASanityBusinessLogic.TimedAnchors(userType);
		Zee5PWASanityBusinessLogic.moviesTimeperiodProvided(userType);
		Zee5PWASanityBusinessLogic.continueWatchingtrayData(userType);

	}

	// @Test(priority = 27) // BASAVARAJ
	public void PWAVIL() throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.verifyingVodafoneNativeApp();
	}

	@AfterClass
	public void tearDown() {
		Zee5PWASanityBusinessLogic.tearDown();
	}
}