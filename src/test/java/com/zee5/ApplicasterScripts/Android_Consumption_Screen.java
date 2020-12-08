package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Consumption_Screen {

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
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLogin(userType);

	}

	@Test(priority = 1)
	@Parameters({ "userType", "SVODShow" })
	public void SVODConsumptionScreenForShowTab(String userType, String SVODShow) throws Exception {
		ZEE5ApplicasterBusinessLogic.SVODConsumptionScreen(userType, "Shows", SVODShow);
	}

//	@Test(priority = 2)
	@Parameters({ "userType", "SVODEpisode" })
	public void SVODConsumptionScreenForEpisodeTab(String userType, String SVODEpisode) throws Exception {
		ZEE5ApplicasterBusinessLogic.SVODConsumptionScreen(userType, "Episode", SVODEpisode);
	}

	@Test(priority = 3)
	@Parameters({ "userType", "SVODMovie" })
	public void SVODConsumptionScreenForMoviesTab(String userType, String SVODMovie) throws Exception {
		ZEE5ApplicasterBusinessLogic.SVODConsumptionScreen(userType, "Movies", SVODMovie);
	}

	@Test(priority = 4)
	@Parameters({ "userType", "SVODMusic" })
	public void SVODConsumptionScreenForMusicTab(String userType, String SVODMusic) throws Exception {
		ZEE5ApplicasterBusinessLogic.SVODConsumptionScreen(userType, "Music", SVODMusic);
	}

	@Test(priority = 5)
	@Parameters({ "userType" })
	public void NSVODConsumptionScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.verifyNonSVODConsumptionScreen(userType);
		ZEE5ApplicasterBusinessLogic.verifySimilarChannels();
	}
	
	@Test(priority = 6)
	@Parameters({ "userType" })
	public void MandatoryRegistrationPopUp(String userType) throws Exception {
			ZEE5ApplicasterBusinessLogic.relaunch(true);
			ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
			ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
			ZEE5ApplicasterBusinessLogic.ZeeApplicasterLogin(userType);
			ZEE5ApplicasterBusinessLogic.MandatoryPopUpScenarios(userType);
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App\n");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}

}
