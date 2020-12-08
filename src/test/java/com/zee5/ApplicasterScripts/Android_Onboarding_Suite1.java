package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Onboarding_Suite1 {
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}
	
	
	@Test(priority = 1)
	@Parameters({"userType"})
	public void verificationOfLoginPage(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
			ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
			ZEE5ApplicasterBusinessLogic.ZEE5AppLogin(userType);
			ZEE5ApplicasterBusinessLogic.VerifyLoginPage();
	}

	@Test(priority = 2) // Sushma==Scenarios only for guest user
	@Parameters({ "userType", "displayLanguageSelection1", "displayLanguageSelection2" })
	public void DisplayLanguagePopUp(String userType, String displayLanguageSelection1,
			String displayLanguageSelection2) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			System.out.println("\nVerify Display Language Screen");
			ZEE5ApplicasterBusinessLogic.relaunch(true);
			ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
			ZEE5ApplicasterBusinessLogic.DisplayLanguagePopUpValidation(displayLanguageSelection1,
				displayLanguageSelection2);
		}else {
			ZEE5ApplicasterBusinessLogic.loggerForNonGuestUserTypes("Display Language PopUp");
		}
	}
	
	@Test(priority = 3) // Shreenidhi==Scenarios only for guest user
	@Parameters({ "userType" })
	public void ContentLanguage(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			System.out.println("\nVerify Content Language Screen");
			ZEE5ApplicasterBusinessLogic.contentLanguage(userType);
			ZEE5ApplicasterBusinessLogic.relaunch(true);
			ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
			ZEE5ApplicasterBusinessLogic.checkScreenAfterRelaunch(userType, "Content language");
		}else {
			ZEE5ApplicasterBusinessLogic.loggerForNonGuestUserTypes("Content Language");
		}
	}

	@Test(priority = 4)
	@Parameters({ "userType" }) // Vinay==Scenarios only for Guest user
	public void IntroScreenModule(String userType) throws Exception {
		System.out.println("\nVerify Intro Screen");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.IntroScreen(userType);
	}

	@Test(priority = 5) // Sushma and Vinay==Scenarios only for Guest user
	@Parameters({ "inValidPhnNumber", "validPhnNumber", "validPassword", "otp1", "otp2", "otp3", "otp4", "loginThrough",
			"userType" })
	public void LoginRegistrationMobileLogin(String inValidPhnNumber, String validPhnNumber, String validPassword,
			String otp1, String otp2, String otp3, String otp4, String loginThrough, String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			System.out.println("\nVerifying Login flow with valid & invalid credentials");
			ZEE5ApplicasterBusinessLogic.loginOrRegisterScreen(inValidPhnNumber, validPhnNumber, loginThrough, userType);
			ZEE5ApplicasterBusinessLogic.loginScreen(validPassword);
			ZEE5ApplicasterBusinessLogic.OtpScreen(otp1, otp2, otp3, otp4);
		}else {
			ZEE5ApplicasterBusinessLogic.loggerForNonGuestUserTypes("Login Registration Screen");
		}
	}
	
	@Test(priority = 6) // Hitesh==Scenarios only for Guest user
	@Parameters({ "userType", "FirstName", "LastName", "loginThrough" })
	public void ValidateRegistrationFields(String userType, String FirstName, String lastName, String loginThrough) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			System.out.println("\nValidate New Registration screen");
			ZEE5ApplicasterBusinessLogic.relaunch(false);
			ZEE5ApplicasterBusinessLogic.navigateToRegisterScreen(loginThrough);
			ZEE5ApplicasterBusinessLogic.validateRegister(FirstName, lastName);
			ZEE5ApplicasterBusinessLogic.relaunch(false);
			ZEE5ApplicasterBusinessLogic.navigateToRegisterScreen(loginThrough);
			ZEE5ApplicasterBusinessLogic.registerForFreeScreen("Email");
		}else {
			ZEE5ApplicasterBusinessLogic.loggerForNonGuestUserTypes("Validate ZEE5 Registration");
		}
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
