package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_SampleTest {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;
	
	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void AndroidAppMixPanelLogin(String userType) throws Exception {
		System.out.println("\nLogin");
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
//		ZEE5ApplicasterBusinessLogic.navigateToLoginScreen_DisplaylangScreen();
//		ZEE5ApplicasterBusinessLogic.LoginWithEmailID("zeetest998@test.com", "123456");
//		ZEE5ApplicasterBusinessLogic.SampleSearchandContentPlayback(userType, "Kurukshetra");
//		ZEE5ApplicasterBusinessLogic.Logout(userType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuiting the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
