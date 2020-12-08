package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_SubtitleLanguageChange {
	
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
	@Parameters({ "userType"})
	public void SubtitleLanguageChangeEventofPremiumContent(String userType) throws Exception {
		System.out.println("\n Subtitle Language Change event of Premium content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 3)
	@Parameters({ "userType", "keyword9"})
	public void SubtitleLanguageChangeEventofTrailerContent(String userType, String keyword9) throws Exception {
		System.out.println("Subtitle Language Change event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventForTrailerContent(userType,keyword9);
		
	}

	@Test(priority = 4)
	@Parameters({ "userType"})
	public void SubtitleLanguageChangeEventofCarouselContent(String userType) throws Exception {
		System.out.println("Subtitle Language Change event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventForCarouselContent(userType,"Home");
	}

	@Test(priority = 5)
	@Parameters({ "userType","keyword9"})
	public void SubtitleLanguageChangeEventFromsearchpage(String userType, String keyword9) throws Exception {
		System.out.println("Subtitle Language Change event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventOfcontentFromSearchPage(userType, keyword9);
	}
	
	@Test(priority = 6)
	@Parameters({ "userType"})
	public void SubtitleLanguageChangeEventFromMyWatchList(String userType) throws Exception {
		System.out.println("Subtitle Language Change event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 7)
	@Parameters({ "userType", "keyword9"})
	public void SubtitleLanguageChangeEventFromUpNextRail(String userType, String keyword9) throws Exception {
		System.out.println("Subtitle Language Change event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventOfContentFromUpNextRail(userType, keyword9);
		
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
