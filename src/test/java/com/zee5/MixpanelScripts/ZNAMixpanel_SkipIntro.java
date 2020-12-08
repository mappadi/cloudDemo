package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_SkipIntro {
	
	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void AndroidAppMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5ApplicasterMixPanelBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		
	}
	
	@Test(priority = 2)
	@Parameters({ "userType", "keyword3"})
	public void SkipIntroEventofPremiumContent(String userType, String keyword3) throws Exception {
		System.out.println("Skip Intro event of Premium content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SkipIntroEventForPremiumContent(userType,keyword3);
		
	}
	
	@Test(priority = 3)
	@Parameters({ "userType"})
	public void SkipIntroEventOfContentFromContentTrays(String userType) throws Exception {
		System.out.println("Skip Intro event of content from Content Trays");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SkipIntroEventOfContentFromContentTrays(userType,"Movies");
	}

	@Test(priority = 4)
	@Parameters({ "userType"})
	public void SkipIntroEventofCarouselContent(String userType) throws Exception {
		System.out.println("Skip Intro event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SkipIntroEventForCarouselContent(userType,"Home");
	}

	@Test(priority = 5)
	@Parameters({ "userType","keyword8"})
	public void SkipIntroEventFromsearchpage(String userType, String keyword8) throws Exception {
		System.out.println("Skip Intro event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SkipIntroEventOfcontentFromSearchPage(userType, keyword8);
	}
	
	@Test(priority = 6)
	@Parameters({ "userType"})
	public void SkipIntroEventFromMyWatchList(String userType) throws Exception {
		System.out.println("Skip Intro event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SkipIntroEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 7)
	@Parameters({ "userType", "keyword4"})
	public void SkipIntroEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("SkipIntro event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SkipIntroEventOfContentFromUpNextRail(userType, keyword4);
		
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
