package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Onboarding_Suite2 {
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;
	
	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true;	// Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}
	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void Login(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLoginForOnboarding();
	}
	
	@Test (priority = 1)
	@Parameters({"userType"})	//  Kushal
	public void HaveAPrepaidCodeUIVerification(String userType) throws Exception {
		System.out.println("\nVerify Have a Prepaid Code PopUp Screen");
		ZEE5ApplicasterBusinessLogic.verifyHaveAPrepaidCodePopUp();
	}
	
	@Test (priority = 2)
	@Parameters({"userType"})	//  Kushal
	public void BrowseforFreeSkipLoginRegistration(String userType) throws Exception {
		System.out.println("\nBrowse for Free - Skip Login/Register page to Home Screen");
		ZEE5ApplicasterBusinessLogic.VerifySkipLoginRegistrationScreen();
	}
	
	@Test (priority = 3)	//  Kushal
	@Parameters({"userType","RegisteredEmail","RegisteredEmailPassword"})
	public void BrowseforFreeLoginwithEmailID(String userType,String pEmailId,String pPassword) throws Exception {
		System.out.println("\nBrowse for Free - Login with EmailID");
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.VerifyLoginWithEmailId(pEmailId, pPassword);
	}
	
	@Test (priority = 4)	//  Kushal
	@Parameters({"userType","NonsubscribedUserName","NonsubscribedPassword"})
	public void InvalidPrepaidCodeValidationAfterLogin(String userType,String pEmailId,String pPassword) throws Exception {
		System.out.println("\nVerify Invalid Prepaid Code PopUp for registered user");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Deny", userType);
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLoginForOnboarding();
		ZEE5ApplicasterBusinessLogic.verifyInvalidPrepaidCodePopUpAfterLogin(pEmailId, pPassword);
	}
	
	@Test (priority = 5)	//  Kushal
	@Parameters({"userType"})
	public void InvalidPrepaidCodeValidationAfterRegistration(String userType) throws Exception {
		System.out.println("\nVerify Invalid Prepaid Code PopUp for unregistered user");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Deny", userType);
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLoginForOnboarding();
		ZEE5ApplicasterBusinessLogic.verifyInvalidPrepaidCodePopUpAfterRegistration();
	}
	

//	@Test (priority = 9)	//  Kushal  - [Need a VALID Prepaid Code to execute this test]
	@Parameters({"userType","PrepaidCode","regUserName","regPassword"})
	public void ApplyValidPrepaidCodeforRegisteredUser(String userType, String pCode, String pUserName, String pPassword) throws Exception {
		System.out.println("\nVerify Successful message for valid Prepaid Code for registered user");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.verifyCongratulationPopupAppearsforValidPrepaidCode(pCode, pUserName, pPassword);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Quiting the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
