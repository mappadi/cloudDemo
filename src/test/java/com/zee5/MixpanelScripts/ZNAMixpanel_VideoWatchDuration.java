package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_VideoWatchDuration {
	
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
	public void VideoWatchDurationEventofPremiumContentComplete(String userType) throws Exception {
		System.out.println("\nVideo Watch Duration event of Premium content when user completely watches the content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForPremiumContentComplete(userType,"Home");
		
	}
	
	@Test(priority = 3)
	@Parameters({ "userType"})
	public void VideoWatchDurationEventofPremiumContentAbrupt(String userType) throws Exception {
		System.out.println("\nVideo Watch Duration event of Premium content when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForPremiumContentAbrupt(userType,"Home");
		
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "keyword3"})
	public void VideoWatchDurationEventofTrailerContentComplete(String userType, String keyword3) throws Exception {
		System.out.println("\nVideo Watch Duration event of Trailer content when user completely watches the content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForTrailerContentComplete(userType,keyword3);
		
	}
	
	@Test(priority = 5)
	@Parameters({ "userType", "keyword3"})
	public void VideoWatchDurationEventofTrailerContentAbrupt(String userType, String keyword3) throws Exception {
		System.out.println("\nVideo Watch Duration event of Trailer content when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForTrailerContentAbrupt(userType,keyword3);
		
	}
	
	@Test(priority = 6)
	public void VideoWatchDurationEventofCarouselContentComplete() throws Exception {
		System.out.println("\nVideo Watch Duration event of Carousel content when user completely watches the content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForCarouselContentComplete("Home");
	}
	
	@Test(priority = 7)
	public void VideoWatchDurationEventofCarouselContentAbrupt() throws Exception {
		System.out.println("Video Watch Duration event of Carousel content when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForCarouselContentAbrupt("Home");
	}
	
	@Test(priority = 8)
	@Parameters({ "userType","keyword4"})
	public void VideoWatchDurationEventFromsearchpageComplete(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from Search page when user completely watches the content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfcontentFromSearchPageComplete(userType, keyword4);
	}
	
	@Test(priority = 9)
	@Parameters({ "userType","keyword4"})
	public void VideoWatchDurationEventFromsearchpageAbrupt(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from Search page when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfcontentFromSearchPageAbrupt(userType, keyword4);
	}
	
	@Test(priority = 10)
	@Parameters({ "userType"})
	public void VideoWatchDurationEventFromMyWatchListComplete(String userType) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from My WatchList page when user completely watches the content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfContentFromMyWatchListPageComplete(userType);		
	}
	
	@Test(priority = 11)
	@Parameters({ "userType"})
	public void VideoWatchDurationEventFromMyWatchListAbrupt(String userType) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from My WatchList page when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfContentFromMyWatchListPageAbrupt(userType);
		
	}
	
	@Test(priority = 12)
	@Parameters({ "userType", "keyword4"})
	public void VideoWatchDurationEventFromUpNextRailComplete(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from Upnext Rail when user completely watches the content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfContentFromUpNextRailComplete(userType, keyword4);
	}
	
	@Test(priority = 13)
	@Parameters({ "userType", "keyword4"})
	public void VideoWatchDurationEventFromUpNextRailAbrupt(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from Upnext Rail when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfContentFromUpNextRailAbrupt(userType, keyword4);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
