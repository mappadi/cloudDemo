package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_QualityChange {
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
	public void QualityChangeEventofPremiumContent(String userType) throws Exception {
		System.out.println("\nQuality Change event of Premium content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 3)
	@Parameters({ "userType", "keyword3"})
	public void QualityChangeEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("Quality Change event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventForTrailerContent(userType,keyword3);
		
	}
	
	@Test(priority = 4)
	@Parameters({ "userType"})
	public void QualityChangeEventofCarouselContent(String userType) throws Exception {
		System.out.println("Quality Change event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventForCarouselContent(userType,"Home");
	}
	
	@Test(priority = 5)
	@Parameters({ "userType","keyword4"})
	public void QualityChangeEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("Quality Change event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 6)
	@Parameters({ "userType"})
	public void QualityChangeEventFromMyWatchList(String userType) throws Exception {
		System.out.println("Quality Change event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventOfContentFromMyWatchListPage(userType);
	}
	
	@Test(priority = 7)
	@Parameters({ "userType", "keyword4"})
	public void QualityChangeEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("Quality Change event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventOfContentFromUpNextRail(userType, keyword4);
	}

	@Test(priority = 8)
	@Parameters({ "userType"})
	public void QualityChangeEventForLinearContent(String userType) throws Exception {
		System.out.println("Quality Change event for Linear Content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventForLinearContent(userType, "Live TV");
		
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
