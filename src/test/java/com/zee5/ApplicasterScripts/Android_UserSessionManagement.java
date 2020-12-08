package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_UserSessionManagement {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void launchChromeApplication() throws Exception {
		System.out.println("Launching Web chrome");
		Utilities.setPlatform = "Web";
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void PWAWEBLogin(String userType) throws Exception {
		System.out.println("PWAWEBLogin");
		ZEE5ApplicasterBusinessLogic.ZeeWEBPWALogin(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void WebDetails(String userType) throws Exception {
		System.out.println("Collecting Web details");
		ZEE5ApplicasterBusinessLogic.webAddContentToWatchlist();
		ZEE5ApplicasterBusinessLogic.webMyProfile(userType);
		ZEE5ApplicasterBusinessLogic.webWatchList();
		ZEE5ApplicasterBusinessLogic.webReminders();
		ZEE5ApplicasterBusinessLogic.webSubscription(userType);
		ZEE5ApplicasterBusinessLogic.webTransaction();
		ZEE5ApplicasterBusinessLogic.webDisplayLanguage();
		ZEE5ApplicasterBusinessLogic.webParentalControl(userType);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void launchZee5App(String userType) throws Exception {
		System.out.println("Launching Zee5 App");
		Utilities.setPlatform = "Android";
		Utilities.relaunch = true;
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLoginForUSM(userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType" })
	public void AppDetails(String userType) throws Exception {
		System.out.println("Collecting App details and validating with Web details");
		ZEE5ApplicasterBusinessLogic.appMyProfile();
		ZEE5ApplicasterBusinessLogic.appWatchlist();
		ZEE5ApplicasterBusinessLogic.appMyReminders();
		ZEE5ApplicasterBusinessLogic.appSubscription(userType);
		ZEE5ApplicasterBusinessLogic.appTransaction(userType);
		ZEE5ApplicasterBusinessLogic.appDisplayLanguage();
		ZEE5ApplicasterBusinessLogic.appParentalControl(userType);

	}

	@Test(priority = 5)
	@Parameters({ "userType" })
	public void launchChromeApplication_2(String userType) throws Exception {
		System.out.println("Launching Web chrome for second time and logging in");
		Utilities.setPlatform = "Web";
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("Chrome");
		System.out.println("PWAWEBLogin");
		ZEE5ApplicasterBusinessLogic.ZeeWEBPWALogin(userType);
	}

	@Test(priority = 6)
	public void Web_Update() throws Exception {
		System.out.println("Updating web details");
		ZEE5ApplicasterBusinessLogic.webRemoveWatchList();
		ZEE5ApplicasterBusinessLogic.webRemoveReminders();
	}

	@Test(priority = 7)
	@Parameters({ "userType" })
	public void AppLoginSecondTime(String userType) throws Exception {
		System.out.println("Launching App second time and logging in");
		Utilities.setPlatform = "Android";
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLoginForUSM(userType);
	}

	@Test(priority = 8)
	public void App_Update() throws Exception {
		System.out.println("validation of App details with updated Web details");
		ZEE5ApplicasterBusinessLogic.appRemoveWatlist();
		ZEE5ApplicasterBusinessLogic.appRemoveReminder();
	}

	@AfterTest
	public void ApptearDown() {
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
