package com.zee5.Zee5TvScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5TvBusinessLogic;

public class TvZee5ATVScripts {

	private Zee5TvBusinessLogic Zee5TvBusiness;

	@BeforeTest
	public void Before() throws InterruptedException {
		// Utilities.relaunch = true;
		Zee5TvBusiness = new Zee5TvBusinessLogic("zeeTV");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void tvLogin(String userType) throws Exception {
		Zee5TvBusiness.login(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void searchScenarios(String userType) throws Exception {
		Zee5TvBusiness.searchScenarios(userType);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType" })
	public void playback(String userType) throws Exception {
		Zee5TvBusiness.playbackHomepage();
		Zee5TvBusiness.playbackShowspage();
//		Zee5TvBusiness.playbackMoviespage();
//		Zee5TvBusiness.playbackNewspage();
//		Zee5TvBusiness.playbackPremiumpage();
//		Zee5TvBusiness.playbackvideospage();
	
	}
	@Test(priority = 4)
	@Parameters({ "userType" })
	public void carousel(String tab) throws Exception {
		Zee5TvBusiness.carouselValidation("Home");
		Zee5TvBusiness.carouselValidation("Shows");
//		Zee5TvBusiness.carouselValidation("Movies");
//		Zee5TvBusiness.carouselValidation("News");
//		Zee5TvBusiness.carouselValidation("Premium");
//		Zee5TvBusiness.carouselValidation("Videos");
	}

	@AfterTest
	public void After() {
		System.out.println("Tear Down");
		Zee5TvBusiness.TvtearDown();
	}

}
