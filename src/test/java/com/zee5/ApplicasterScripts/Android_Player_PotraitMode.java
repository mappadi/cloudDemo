package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Player_PotraitMode {

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
	@Parameters({ "userType", "searchKeyword1", "searchKeyword4", "searchKeyword5", "searchKeyword3", "searchKeyword6"})
	public void Player_Potrait(String userType, String searchKeyword1, String searchKeyword4, String searchKeyword5,
			String searchKeyword3, String searchKeyword6) throws Exception {
		ZEE5ApplicasterBusinessLogic.PlayerPotrait(searchKeyword1, userType);
		ZEE5ApplicasterBusinessLogic.premiumContentwithTrailer(userType, searchKeyword4);
		ZEE5ApplicasterBusinessLogic.premiumContentWithoutTrailer(userType, searchKeyword5);
		ZEE5ApplicasterBusinessLogic.skipIntroValidationInPotraitMode(searchKeyword3, userType);
		ZEE5ApplicasterBusinessLogic.validationOfWatchCreditsAndUpNextCard(searchKeyword6, userType);
		ZEE5ApplicasterBusinessLogic.ValidationOfReplayIconOnPlayer(searchKeyword6);
	}


	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
