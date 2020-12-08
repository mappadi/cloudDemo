package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_AutoSeek {
	
	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void AndroidAppMixPanelLogin(String userType) throws Exception {
		System.out.println("\nLogin");
		Zee5ApplicasterMixPanelBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		
	}
	
	@Test(priority = 2)
	@Parameters({ "userType"})
	public void AutoSeekForwardEventForPremiumContent(String userType) throws Exception {
		System.out.println("\nAutoSeek Forward event of Premium content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 3)
	@Parameters({ "userType"})
	public void AutoSeekRewindEventForPremiumContent(String userType) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "keyword3"})
	public void AutoSeekForwardEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("\nAutoSeek Forward event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventForTrailerContent(userType,keyword3);
		
	}
	
	@Test(priority = 5)
	@Parameters({ "userType", "keyword3"})
	public void AutoSeekRewindEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventForTrailerContent(userType,keyword3);
		
	}

	@Test(priority = 6)
	@Parameters({ "userType"})
	public void AutoSeekForwardEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nAutoSeek Forward event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventForCarouselContent(userType,"Home");
	}
	
	@Test(priority = 7)
	@Parameters({ "userType"})
	public void AutoSeekRewindEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventForCarouselContent(userType,"Home");
	}

	@Test(priority = 8)
	@Parameters({ "userType","keyword4"})
	public void AutoSeekForwardEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nAutoSeek forward event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 9)
	@Parameters({ "userType","keyword4"})
	public void AutoSeekRewindEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 10)
	@Parameters({ "userType"})
	public void AutoSeekForwardEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nAutoSeek forward event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 11)
	@Parameters({ "userType"})
	public void AutoSeekRewindEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventOfContentFromMyWatchListPage(userType);
	}
	
	@Test(priority = 12)
	@Parameters({ "userType", "keyword4"})
	public void AutoSeekEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("\nAutoSeek forward event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventOfContentFromUpNextRail(userType, keyword4);
	}
	
	@Test(priority = 13)
	@Parameters({ "userType", "keyword4"})
	public void AutoSeekRewindEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventOfContentFromUpNextRail(userType, keyword4);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
}
