package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_ClubPack {
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void Login(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLoginForClubPack(userType);
	}

	@Test(priority = 1) // Bhavana
	@Parameters({ "userType", "SearchVODContent2", "SearchVODContent4" })
	public void ClubUpgradeToAllAccess(String userType, String SearchVODContent2, String SearchVODContent4)
			throws Exception {
		ZEE5ApplicasterBusinessLogic.ClubPackValidation(userType, SearchVODContent2, SearchVODContent4);

	}

	@Test(priority = 3) // Bhavana
	@Parameters({ "userType", "SearchVODContent3", "SearchVODContent" })
	public void ValidateSubscribeAndLoginCTAForClubContent(String userType, String SearchVODContent3,
			String SearchVODContent) throws Exception {
		ZEE5ApplicasterBusinessLogic.ValidateSubscribeAndLoginCTAForClubContent(userType, SearchVODContent3);
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLoginForClubPack(userType);
		ZEE5ApplicasterBusinessLogic.SubscribepopupForClubcontentWithTrailer(userType, SearchVODContent);
	}

	@Test(priority = 4) // Bhavana
	@Parameters({ "userType" })
	public void ValidateClubIconForLandingPages(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.validateClubIconOnContentCards(userType);
		if (userType.contains("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.ValidateClubIconForRecoTray(userType);
			ZEE5ApplicasterBusinessLogic.ValidateClubIconForEpisodes(userType);
			ZEE5ApplicasterBusinessLogic.ValidateClubIconForMovies(userType);
		}
	}

	@Test(priority = 5) // Bindu
	@Parameters({ "userType", "SearchVODContent", "ClubContent" })
	public void ClubPack(String userType, String SearchVODContent, String ClubContent) throws Exception {
		ZEE5ApplicasterBusinessLogic.ClubPack(userType, SearchVODContent, ClubContent);
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Quiting the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
