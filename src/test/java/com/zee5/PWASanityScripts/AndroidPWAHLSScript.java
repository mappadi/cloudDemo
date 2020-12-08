package com.zee5.PWASanityScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASanityAndroidBusinessLogic;

public class AndroidPWAHLSScript {

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
	@Parameters({ "userType" })
	public void PWAOnboardingScenariosHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.OnboardingScenariosHLS(userType); // from smoke
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void PWAHomeLandingPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAHomeLandingPageHLS(userType);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void PWAMoviesPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAMoviesPageHLS(userType, "Movies");
	}

	@Test(priority = 4)
	@Parameters({ "userType" })
	public void PWAShowsPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAShowsPageHLS(userType, "Shows");
	}

	@Test(priority = 5)
	@Parameters({ "userType" })
	public void PWANewsPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWANewsPageHLS(userType, "News");
	}

	@Test(priority = 6)
	@Parameters({ "userType" })
	public void PWAClubPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAClubPageHLS(userType, "Club");
	}

	@Test(priority = 7)
	@Parameters({ "userType" })
	public void PWAPremiumPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAPremiumPageHLS(userType, "Premium");
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void PWAPlayPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAPlayPageHLS(userType, "Play");
	}

	@Test(priority = 9)
	@Parameters({ "userType" })
	public void PWAKidsPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAKidsPageHLS(userType, "Kids");
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void PWAMusicPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAMusicPageHLS(userType, "Music");
	}

	@Test(priority = 11)
	@Parameters({ "userType" })
	public void PWALiveTVPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWALiveTVPageHLS(userType, "Live TV");
	}

	@Test(priority = 12)
	@Parameters({ "userType" })
	public void PWAStoriesPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAStoriesPageHLS(userType, "Stories");
	}

	@Test(priority = 13)
	@Parameters({ "userType" })
	public void PWAVideosPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAVideosPageHLS(userType, "Videos");
	}

	@Test(priority = 14)
	@Parameters({ "userType" })
	public void PWAZEE5OriginalsPageHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAZEE5OriginalsPageHLS(userType, "ZEE5 Originals");
	}

	@Test(priority = 15)
	@Parameters({ "userType" })
	public void PWASearchLandingHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWASearchLandingHLS(userType);
	}

	@Test(priority = 16)
	@Parameters({ "userType" })
	public void PWAPlayerControlsHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAPlayerControlsHLS(userType);
	}

	@Test(priority = 17)
	@Parameters({ "userType" })
	public void PWAUpNextHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAUpNextHLS(userType);
	}

	@Test(priority = 18)
	@Parameters({ "userType" })
	public void PWAWatchCreditsHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAWatchCreditsHLS(userType);
	}

	@Test(priority = 19)
	@Parameters({ "userType" })
	public void PWASubscriptionJourneyHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWASubscriptionJourneyHLS(userType);
	}

	@Test(priority = 20)
	@Parameters({ "userType" })
	public void PWAAccountInfoHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAAccountInfoHLS(userType);
	}

	@Test(priority = 21)
	@Parameters({ "userType" })
	public void PWAMySubscriptionsHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAMySubscriptionsHLS(userType);
	}

	@Test(priority = 22)
	@Parameters({ "userType" })
	public void PWAMyTransactionsHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAMyTransactionsHLS(userType);
	}

	@Test(priority = 23)
	@Parameters({ "userType" })
	public void PWAUpgradePackJourneyHLS(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAUpgradePackJourneyHLS(userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWASanityBusinessLogic.tearDown();
	}
}