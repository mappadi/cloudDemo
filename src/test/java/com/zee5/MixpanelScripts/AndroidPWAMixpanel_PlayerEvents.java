package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5MPWAMixPanelBusinessLogic;

public class AndroidPWAMixpanel_PlayerEvents {
	
	private Zee5MPWAMixPanelBusinessLogic Zee5PWAMixPanelAndroidBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAMixPanelAndroidBusinessLogic = new Zee5MPWAMixPanelBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void ZeePWALogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5PWAMixPanelAndroidBusinessLogic.ZeePWALogin("E-mail",userType);
	}
	
	@Test(priority = 2)
	public void verifyBannerAutoplayEventForNewsContent() throws Exception {
		System.out.println("Verify Banner Autoplay Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyBannerAutoplayEventForNewsContent();
	}
	
	@Test(priority = 3)
	@Parameters({ "userType","keyword4"})
	public void verifyVideoViewEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video View Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoViewEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType"})
	public void verifyVideoViewEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Video View Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoViewEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 5)
	@Parameters({ "keyword1"})
	public void verifyVideoViewEventForTrailer(String keyword1) throws Exception {
		System.out.println("Verify Video View Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoViewEventForTrailer(keyword1);
	}
	
	@Test(priority = 6)
	public void verifyVideoViewEventForCarouselContent() throws Exception {
		System.out.println("Verify Video View Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoViewEventForCarouselContent();
	}
	
	@Test(priority = 7)
	public void verifyVideoViewEventForContentInTray() throws Exception {
		System.out.println("Verify Video View Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoViewEventForContentInTray();
	}
	
	@Test(priority = 8)
	@Parameters({"keyword1"})
	public void verifyVideoViewEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Video View Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoViewEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 9)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoViewEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Video View Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoViewEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 10)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoViewEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video View Event for content autoplayed from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoViewEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 11)
	@Parameters({"freeContentURL"})
	public void verifyVideoViewEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Video View Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoViewEventForContentFromSharedLink(freeContentURL);
	}	
	
	@Test(priority = 12)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifyVideoViewEventForContentInPlaylist(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Video View Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoViewEventForContentInPlaylist(userType,subtitleTrackContent);
	}
	
	
	@Test(priority = 13)
	@Parameters({"keyword1"})
	public void verifyVideoViewEventAfterRefreshingPage(String keyword1) throws Exception {
		System.out.println("Verify Video View Event after refreshing a page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoViewEventAfterRefreshingPage(keyword1);
	}
	
	@Test(priority = 14)
	@Parameters({ "userType","keyword4"})
	public void verifyVideoExitEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Exit Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoExitEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 15)
	@Parameters({ "userType"})
	public void verifyVideoExitEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Video Exit Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoExitEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 16)
	@Parameters({ "keyword1"})
	public void verifyVideoExitEventForTrailer(String keyword1) throws Exception {
		System.out.println("Verify Video Exit Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoExitEventForTrailer(keyword1);
	}
	
	@Test(priority = 17)
	public void verifyVideoExitEventForCarouselContent() throws Exception {
		System.out.println("Verify Video Exit Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoExitEventForCarouselContent();
	}
	
	@Test(priority = 18)
	public void verifyVideoExitEventForContentInTray() throws Exception {
		System.out.println("Verify Video Exit Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoExitEventForContentInTray();
	}
	
	@Test(priority = 19)
	@Parameters({"keyword1"})
	public void verifyVideoExitEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Video Exit Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoExitEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 20)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoExitEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Video Exit Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoExitEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 21)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoExitEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Exit Event for content autoplayed from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoExitEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 22)
	@Parameters({"freeContentURL"})
	public void verifyVideoExitEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Video Exit Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoExitEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 23)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifyVideoExitEventForContentInPlaylist(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Video Exit Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoExitEventForContentInPlaylist(userType,subtitleTrackContent);
	}
		
	
	@Test(priority = 24)
	@Parameters({"keyword1"})
	public void verifyVideoExitEventAfterRefreshingPage(String keyword1) throws Exception {
		System.out.println("Verify Video Exit Event after refreshing a page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoExitEventAfterRefreshingPage(keyword1);
	}
	
	
	@Test(priority = 25)
	@Parameters({ "userType", "keyword4"})
	public void verifyPauseEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Pause Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPauseEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 26)
	@Parameters({ "userType"})
	public void verifyPauseEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Pause Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPauseEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 27)
	@Parameters({ "userType", "keyword1"})
	public void verifyPauseEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Pause Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPauseEventForTrailer(userType,keyword1);
	}
	
	@Test(priority = 28)
	public void verifyPauseEventForCarouselContent() throws Exception {
		System.out.println("Verify Pause Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPauseEventForCarouselContent();
	}
	
	@Test(priority = 29)
	public void verifyPauseEventForContentInTray() throws Exception {
		System.out.println("Verify Pause Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPauseEventForContentInTray();
	}
	
	@Test(priority = 30)
	@Parameters({"keyword1"})
	public void verifyPauseEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Pause Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPauseEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 31)
	@Parameters({ "userType", "keyword"})
	public void verifyPauseEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Pause Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPauseEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 32)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifyPauseEventForContentInPlaylist(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Pause Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPauseEventForContentInPlaylist(userType,subtitleTrackContent);
	}
	
	@Test(priority = 33)
	public void verifyPauseEventForLinearContent() throws Exception {
		System.out.println("Verify Pause Event For Linear Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPauseEventForLinearContent();
	}
	
	@Test(priority = 34)
	@Parameters({ "userType", "keyword4"})
	public void verifyPauseEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Pause Event for content autoplayed from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPauseEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 35)
	@Parameters({"freeContentURL"})
	public void verifyPauseEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Pause Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyPauseEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 36)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoWatchDurationEventForFreeContentComplete(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForFreeContentComplete(userType,keyword4);
	}
	
	@Test(priority = 37)
	@Parameters({ "userType"})
	public void verifyVideoWatchDurationEventForPremiumContentComplete(String userType) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForPremiumContentComplete(userType,"Home");
	}
	
	@Test(priority = 38)
	@Parameters({ "userType", "keyword1"})
	public void verifyVideoWatchDurationEventForTrailerComplete(String userType,String keyword1) throws Exception {
		System.out.println("Verify Video Watch Duration Event  when user completely watches Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForTrailerComplete(userType,keyword1);
	}
	
	@Test(priority = 39)
	public void verifyVideoWatchDurationEventForCarouselContentComplete() throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForCarouselContentComplete();
	}
	
	@Test(priority = 40)
	public void verifyVideoWatchDurationEventForContentInTrayComplete() throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentInTrayComplete();
	}
	
	@Test(priority = 41)
	@Parameters({"keyword1"})
	public void verifyVideoWatchDurationEventForContentFromSearchPageComplete(String keyword1) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentFromSearchPageComplete(keyword1);
	}
	
	@Test(priority = 42)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageComplete(String userType,String keyword) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentFromMyWatchlistPageComplete(userType,keyword);
	}
	
	
	@Test(priority = 43)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifyVideoWatchDurationEventForContentInPlaylistComplete(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentInPlaylistComplete(userType,subtitleTrackContent);
	}
	
	@Test(priority = 44)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoWatchDurationEventForContentFromUpnextRailComplete(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Watch Duration Event When user completely watches the  auto-played content from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentFromUpnextRailComplete(userType,keyword4);
	}

	@Test(priority = 45)
	@Parameters({ "freeContentURL"})
	public void verifyVideoWatchDurationEventForContentFromSharedLinkComplete(String freeContentURL) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches the content playback shared through shared link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentFromSharedLinkComplete(freeContentURL);
	}
	
	
	@Test(priority = 46)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoWatchDurationEventForFreeContentAbrupt(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForFreeContentAbrupt(userType,keyword4);
	}
	
	@Test(priority = 47)
	@Parameters({ "userType"})
	public void verifyVideoWatchDurationEventForPremiumContentAbrupt(String userType) throws Exception {
		System.out.println("Verify Video Watch Duration when video is closed abruptly Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForPremiumContentAbrupt(userType,"Home");
	}
	
	@Test(priority = 48)
	@Parameters({ "userType", "keyword1"})
	public void verifyVideoWatchDurationEventForTrailerAbrupt(String userType,String keyword1) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForTrailerAbrupt(userType,keyword1);
	}
	
	@Test(priority = 49)
	public void verifyVideoWatchDurationEventForCarouselContentAbrupt() throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForCarouselContentAbrupt();
	}
	
	@Test(priority = 50)
	public void verifyVideoWatchDurationEventForContentInTrayAbrupt() throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentInTrayAbrupt();
	}
	
	@Test(priority = 51)
	@Parameters({"keyword1"})
	public void verifyVideoWatchDurationEventForContentFromSearchPageAbrupt(String keyword1) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentFromSearchPageAbrupt(keyword1);
	}
	
	@Test(priority = 52)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageAbrupt(String userType,String keyword) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentFromMyWatchlistPageAbrupt(userType,keyword);
	}
	
	
	@Test(priority = 53)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifyVideoWatchDurationEventForContentInPlaylistAbrupt(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentInPlaylistAbrupt(userType,subtitleTrackContent);
	}
	
	@Test(priority = 54)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoWatchDurationEventForContentFromUpnextRailAbrupt(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly on auto-played content from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentFromUpnextRailAbrupt(userType,keyword4);
	}
	
	@Test(priority = 55)
	@Parameters({"freeContentURL"})
	public void verifyVideoWatchDurationEventForContentFromSharedLinkAbrupt(String freeContentURL) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyVideoWatchDurationEventForContentFromSharedLinkAbrupt(freeContentURL);
	}
	
	@Test(priority = 56)
	@Parameters({ "userType", "keyword4"})
	public void verifyResumeEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Resume Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyResumeEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 57)
	@Parameters({ "userType"})
	public void verifyResumeEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Resume Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyResumeEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 58)
	@Parameters({ "userType", "keyword1"})
	public void verifyResumeEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Resume Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyResumeEventForTrailer(userType,keyword1);
	}
	
	@Test(priority = 59)
	public void verifyResumeEventForCarouselContent() throws Exception {
		System.out.println("Verify Resume Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyResumeEventForCarouselContent();
	}
	
	@Test(priority = 60)
	public void verifyResumeEventForContentInTray() throws Exception {
		System.out.println("Verify Resume Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyResumeEventForContentInTray();
	}
	
	@Test(priority = 61)
	@Parameters({"keyword1"})
	public void verifyResumeEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Resume Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyResumeEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 62)
	@Parameters({ "userType", "keyword"})
	public void verifyResumeEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Resume Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyResumeEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 63)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifyResumeEventForContentInPlaylist(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Resume Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyResumeEventForContentInPlaylist(userType,subtitleTrackContent);
	}
	
	
	
	@Test(priority = 64)
	public void verifyResumeEventForLinearContent() throws Exception {
		System.out.println("Verify Resume Event For Linear Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyResumeEventForLinearContent();
	}
	
	@Test(priority = 65)
	@Parameters({ "userType", "keyword4"})
	public void verifyResumeEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Resume Event for content autoplayed from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyResumeEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 66)
	@Parameters({"freeContentURL"})
	public void verifyResumeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Resume Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyResumeEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 67)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdInitializedEventForFreeContent(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Initialized Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdInitializedEventForFreeContent(userType,audioTrackContent);
	}

	@Test(priority = 68)
	@Parameters({"userType", "keyword1"})
	public void verifyAdInitializedEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Initialized Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdInitializedEventForTrailer(userType,keyword1);
	}
	
	@Test(priority = 69)
	@Parameters({ "userType"})
	public void verifyAdInitializedEventForCarouselContent(String userType) throws Exception {
		System.out.println("Verify Ad Initialized Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdInitializedEventForCarouselContent(userType);
	}
	
	@Test(priority = 70)
	@Parameters({ "userType"})
	public void verifyAdInitializedEventForContentInTray(String userType) throws Exception {
		System.out.println("Verify Ad Initialized Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdInitializedEventForContentInTray(userType);
	}
	
	@Test(priority = 71)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdInitializedEventForContentFromSearchPage(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Initialized Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdInitializedEventForContentFromSearchPage(userType,subtitleTrackContent);
	}
	
	@Test(priority = 72)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdInitializedEventForContentFromMyWatchlistPage(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Initialized Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdInitializedEventForContentFromMyWatchlistPage(userType,audioTrackContent);
	}
	
	
	@Test(priority = 73)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdInitializedEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Initialized Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdInitializedEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 74)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdInitializedEventForContentFromUpnextRail(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Initialized Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdInitializedEventForContentFromUpnextRail(userType,audioTrackContent);
	}
	
	@Test(priority = 75)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdInitializedEventForContentFromSharedLink(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Initialized Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdInitializedEventForContentFromSharedLink(userType,audioTrackURL);
	}
	
	@Test(priority = 76)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdViewEventForFreeContent(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad View Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdViewEventForFreeContent(userType,audioTrackContent);
	}

	@Test(priority = 77)
	@Parameters({ "userType","keyword1"})
	public void verifyAdViewEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad View Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdViewEventForTrailer(userType,keyword1);
	}
	
	@Test(priority = 78)
	@Parameters({ "userType"})
	public void verifyAdViewEventForCarouselContent(String userType) throws Exception {
		System.out.println("Verify Ad View Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdViewEventForCarouselContent(userType);
	}
	
	@Test(priority = 79)
	@Parameters({ "userType"})
	public void verifyAdViewEventForContentInTray(String userType) throws Exception {
		System.out.println("Verify Ad View Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdViewEventForContentInTray(userType);
	}
	
	@Test(priority = 80)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdViewEventForContentFromSearchPage(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad View Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdViewEventForContentFromSearchPage(userType,subtitleTrackContent);
	}
	
	@Test(priority = 81)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdViewEventForContentFromMyWatchlistPage(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad View Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdViewEventForContentFromMyWatchlistPage(userType,audioTrackContent);
	}
	
	
	@Test(priority = 82)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdViewEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad View Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdViewEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 83)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdViewEventForContentFromUpnextRail(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad View Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdViewEventForContentFromUpnextRail(userType,audioTrackContent);
	}
	
	@Test(priority = 84)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdViewEventForContentFromSharedLink(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad View Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdViewEventForContentFromSharedLink(userType,audioTrackURL);
	}
	
	@Test(priority = 85)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdClickEventForFreeContent(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Click Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdClickEventForFreeContent(userType,audioTrackContent);
	}

	@Test(priority = 86)
	@Parameters({"userType", "keyword1"})
	public void verifyAdClickEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Click Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdClickEventForTrailer(userType,keyword1);
	}
	
	@Test(priority = 87)
	@Parameters({ "userType"})
	public void verifyAdClickEventForCarouselContent(String userType) throws Exception {
		System.out.println("Verify Ad Click Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdClickEventForCarouselContent(userType);
	}
	
	@Test(priority = 88)
	@Parameters({ "userType"})
	public void verifyAdClickEventForContentInTray(String userType) throws Exception {
		System.out.println("Verify Ad Click Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdClickEventForContentInTray(userType);
	}
	
	@Test(priority = 89)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdClickEventForContentFromSearchPage(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Click Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdClickEventForContentFromSearchPage(userType,subtitleTrackContent);
	}
	
	@Test(priority = 90)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdClickForContentFromMyWatchlistPage(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Click Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdClickForContentFromMyWatchlistPage(userType,audioTrackContent);
	}
	
	
	@Test(priority = 91)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdClickEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Click Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdClickEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 92)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdClickEventForContentFromUpnextRail(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Click Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdClickEventForContentFromUpnextRail(userType,audioTrackContent);
	}
	
	@Test(priority = 93)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdClickEventForContentFromSharedLink(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Click Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdClickEventForContentFromSharedLink(userType,audioTrackURL);
	}
	
	@Test(priority = 94)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdForcedExitEventForFreeContent(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdForcedExitEventForFreeContent(userType,audioTrackContent);
	}

	@Test(priority = 95)
	@Parameters({"userType", "keyword1"})
	public void verifyAdForcedExitEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdForcedExitEventForTrailer(userType,keyword1);
	}
	
	@Test(priority = 96)
	@Parameters({ "userType"})
	public void verifyAdForcedExitEventForCarouselContent(String userType) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdForcedExitEventForCarouselContent(userType);
	}
	
	@Test(priority = 97)
	@Parameters({ "userType"})
	public void verifyAdForcedExitEventForContentInTray(String userType) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdForcedExitEventForContentInTray(userType);
	}
	
	@Test(priority = 98)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdForcedExitEventForContentFromSearchPage(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdForcedExitEventForContentFromSearchPage(userType,subtitleTrackContent);
	}
	
	@Test(priority = 99)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdForcedExitEventForContentFromMyWatchlistPage(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdForcedExitEventForContentFromMyWatchlistPage(userType,audioTrackContent);
	}
	
	
	@Test(priority = 100)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdForcedExitEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdForcedExitEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 101)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdForcedExitEventForContentFromUpnextRail(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdForcedExitEventForContentFromUpnextRail(userType,audioTrackContent);
	}
	
	@Test(priority = 102)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdForcedExitEventForContentFromSharedLink(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdForcedExitEventForContentFromSharedLink(userType,audioTrackURL);
	}
	
	@Test(priority = 103)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForFreeContentForceExit(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback for free content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForFreeContentForceExit(userType,audioTrackContent);
	}

	@Test(priority = 104)
	@Parameters({"userType", "keyword1"})
	public void verifyAdWatchDurationEventForTrailerForceExit(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForTrailerForceExit(userType,keyword1);
	}
	
	@Test(priority = 105)
	@Parameters({ "userType"})
	public void verifyAdWatchDurationEventForCarouselContentForceExit(String userType) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForCarouselContentForceExit(userType);
	}
	
	@Test(priority = 106)
	@Parameters({ "userType"})
	public void verifyAdWatchDurationEventForContentInTrayForceExit(String userType) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentInTrayForceExit(userType);
	}
	
	@Test(priority = 107)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdWatchDurationEventForContentFromSearchPageForceExit(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromSearchPageForceExit(userType,subtitleTrackContent);
	}
	
	@Test(priority = 108)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageForceExit(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromMyWatchlistPageForceExit(userType,audioTrackContent);
	}
	
	
	@Test(priority = 109)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentInPlaylistForceExit(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentInPlaylistForceExit(userType,audioTrackContent);
	}
	
	@Test(priority = 110)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromUpnextRailForceExit(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromUpnextRailForceExit(userType,audioTrackContent);
	}
	
	@Test(priority = 111)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdWatchDurationEventForContentFromSharedLinkForceExit(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromSharedLinkForceExit(userType,audioTrackURL);
	}
	
	@Test(priority = 112)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForFreeContentComplete(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback for free content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForFreeContentComplete(userType,audioTrackContent);
	}

	@Test(priority = 113)
	@Parameters({"userType", "keyword1"})
	public void verifyAdWatchDurationEventForTrailerComplete(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForTrailerComplete(userType,keyword1);
	}
	
	@Test(priority = 114)
	@Parameters({ "userType"})
	public void verifyAdWatchDurationEventForCarouselContentComplete(String userType) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForCarouselContentComplete(userType);
	}
	
	@Test(priority = 115)
	@Parameters({ "userType"})
	public void verifyAdWatchDurationEventForContentInTrayComplete(String userType) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentInTrayComplete(userType);
	}
	
	@Test(priority = 116)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdWatchDurationEventForContentFromSearchPageComplete(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromSearchPageComplete(userType,subtitleTrackContent);
	}
	
	@Test(priority = 117)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageComplete(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches ad playback For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromMyWatchlistPageComplete(userType,audioTrackContent);
	}
	
	
	@Test(priority = 118)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentInPlaylistComplete(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentInPlaylistComplete(userType,audioTrackContent);
	}
	
	@Test(priority = 119)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromUpnextRailComplete(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromUpnextRailComplete(userType,audioTrackContent);
	}
	
	@Test(priority = 120)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdWatchDurationEventForContentFromSharedLinkComplete(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches ad playback For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromSharedLinkComplete(userType,audioTrackURL);
	}

	@Test(priority = 121)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForFreeContentSkipAd(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback for free content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForFreeContentSkipAd(userType,audioTrackContent);
	}

	@Test(priority = 122)
	@Parameters({"userType", "keyword1"})
	public void verifyAdWatchDurationEventForTrailerSkipAd(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForTrailerSkipAd(userType,keyword1);
	}
	
	@Test(priority = 123)
	@Parameters({ "userType"})
	public void verifyAdWatchDurationEventForCarouselContentSkipAd(String userType) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForCarouselContentSkipAd(userType);
	}
	
	@Test(priority = 124)
	@Parameters({ "userType"})
	public void verifyAdWatchDurationEventForContentInTraySkipAd(String userType) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentInTraySkipAd(userType);
	}
	
	@Test(priority = 125)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdWatchDurationEventForContentFromSearchPageSkipAd(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromSearchPageSkipAd(userType,subtitleTrackContent);
	}
	
	@Test(priority = 126)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageSkipAd(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips ad playback For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromMyWatchlistPageSkipAd(userType,audioTrackContent);
	}
	
	
	@Test(priority = 127)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentInPlaylistSkipAd(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentInPlaylistSkipAd(userType,audioTrackContent);
	}
	
	@Test(priority = 128)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromUpnextRailSkipAd(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromUpnextRailSkipAd(userType,audioTrackContent);
	}
	
	@Test(priority = 129)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdWatchDurationEventForContentFromSharedLinkSkipAd(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips ad playback For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAdWatchDurationEventForContentFromSharedLinkSkipAd(userType,audioTrackURL);
	}
	
	@Test(priority = 130)
	@Parameters({ "userType", "keyword4"})
	public void verifyScrubSeekEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyScrubSeekEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 131)
	@Parameters({ "userType"})
	public void verifyScrubSeekEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyScrubSeekEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 132)
	@Parameters({ "keyword1"})
	public void verifyScrubSeekEventForTrailer(String keyword1) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyScrubSeekEventForTrailer(keyword1);
	}
	
	@Test(priority = 133)
	public void verifyScrubSeekEventForCarouselContent() throws Exception {
		System.out.println("Verify Scrub/Seek Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyScrubSeekEventForCarouselContent();
	}
	
	@Test(priority = 134)
	public void verifyScrubSeekEventForContentInTray() throws Exception {
		System.out.println("Verify Scrub/Seek Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyScrubSeekEventForContentInTray();
	}
	
	@Test(priority = 135)
	@Parameters({"keyword1"})
	public void verifyScrubSeekEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyScrubSeekEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 136)
	@Parameters({ "userType", "keyword"})
	public void verifyScrubSeekEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyScrubSeekEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	
	@Test(priority = 137)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyScrubSeekEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyScrubSeekEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 138)
	@Parameters({ "userType", "keyword4"})
	public void verifyScrubSeekEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyScrubSeekEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 139)
	@Parameters({"freeContentURL"})
	public void verifyScrubSeekEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Scrub/Seek Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyScrubSeekEventForContentFromSharedLink(freeContentURL);
	}

	@Test(priority = 140)
	@Parameters({ "userType", "keyword4"})
	public void verifyQualityChangeEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Quality Change Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyQualityChangeEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 141)
	@Parameters({ "userType"})
	public void verifyQualityChangeEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Quality Change Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyQualityChangeEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 142)
	@Parameters({ "keyword1"})
	public void verifyQualityChangeEventForTrailer(String keyword1) throws Exception {
		System.out.println("Verify Quality Change Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyQualityChangeEventForTrailer(keyword1);
	}
	
	@Test(priority = 143)
	public void verifyQualityChangeEventForCarouselContent() throws Exception {
		System.out.println("Verify Quality Change Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyQualityChangeEventForCarouselContent();
	}
	
	@Test(priority = 144)
	public void verifyQualityChangeEventForContentInTray() throws Exception {
		System.out.println("Verify Quality Change Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyQualityChangeEventForContentInTray();
	}
	
	@Test(priority = 145)
	@Parameters({"keyword1"})
	public void verifyQualityChangeEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Quality Change Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyQualityChangeEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 146)
	@Parameters({ "userType", "keyword"})
	public void verifyQualityChangeEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Quality Change Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyQualityChangeEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	
	@Test(priority = 147)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyQualityChangeEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Quality Change Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyQualityChangeEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 148)
	@Parameters({ "userType", "keyword4"})
	public void verifyQualityChangeEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Quality Change Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyQualityChangeEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 149)
	@Parameters({"freeContentURL"})
	public void verifyQualityChangeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Quality Change Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyQualityChangeEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 150)
	public void verifyQualityChangeEventForLinearContent() throws Exception {
		System.out.println("Verify Quality Change Event For Linear Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyQualityChangeEventForLinearContent();
	}
	
	@Test(priority = 151)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAudioLanguageChangeEventForFreeContent(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAudioLanguageChangeEventForFreeContent(userType,audioTrackContent);
	}
	
	@Test(priority = 152)
	@Parameters({ "userType"})
	public void verifyAudioLanguageChangeEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Audio Language Change Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAudioLanguageChangeEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 153)
	@Parameters({ "keyword1"})
	public void verifyAudioLanguageChangeEventForTrailer(String audioTrackTrailerContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAudioLanguageChangeEventForTrailer(audioTrackTrailerContent);
	}
	
	@Test(priority = 154)
	public void verifyAudioLanguageChangeEventForCarouselContent() throws Exception {
		System.out.println("Verify Audio Language Change Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAudioLanguageChangeEventForCarouselContent();
	}
	
	@Test(priority = 155)
	public void verifyAudioLanguageChangeEventForContentInTray() throws Exception {
		System.out.println("Verify Audio Language Change Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAudioLanguageChangeEventForContentInTray();
	}
	
	@Test(priority = 156)
	@Parameters({"audioTrackContent"})
	public void verifyAudioLanguageChangeEventForContentFromSearchPage(String audioTrackContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAudioLanguageChangeEventForContentFromSearchPage(audioTrackContent);
	}
	
	@Test(priority = 157)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAudioLanguageChangeEventForContentFromMyWatchlistPage(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAudioLanguageChangeEventForContentFromMyWatchlistPage(userType,audioTrackContent);
	}
	
	@Test(priority = 158)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAudioLanguageChangeEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAudioLanguageChangeEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 159)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAudioLanguageChangeEventForContentFromUpnextRail(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAudioLanguageChangeEventForContentFromUpnextRail(userType,audioTrackContent);
	}
	
	@Test(priority = 160)
	@Parameters({"audioTrackURL"})
	public void verifyAudioLanguageChangeEventForContentFromSharedLink(String audioTrackURL) throws Exception {
		System.out.println("Verify Audio Language Change Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAudioLanguageChangeEventForContentFromSharedLink(audioTrackURL);
	}
	
	@Test(priority = 161)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifySubtitleLanguageChangeEventForFreeContent(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubtitleLanguageChangeEventForFreeContent(userType,subtitleTrackContent);
	}
	
	@Test(priority = 162)
	@Parameters({ "userType"})
	public void verifySubtitleLanguageChangeEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubtitleLanguageChangeEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 163)
	@Parameters({ "keyword5"})
	public void verifySubtitleLanguageChangeEventForTrailer(String keyword5) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubtitleLanguageChangeEventForTrailer(keyword5);
	}
	
	@Test(priority = 164)
	public void verifySubtitleLanguageChangeEventForCarouselContent() throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubtitleLanguageChangeEventForCarouselContent();
	}
	
	@Test(priority = 165)
	public void verifySubtitleLanguageChangeEventForContentInTray() throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubtitleLanguageChangeEventForContentInTray();
	}
	
	@Test(priority = 166)
	@Parameters({"subtitleTrackContent"})
	public void verifySubtitleLanguageChangeEventForContentFromSearchPage(String subtitleTrackContent) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubtitleLanguageChangeEventForContentFromSearchPage(subtitleTrackContent);
	}
	
	@Test(priority = 167)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifySubtitleLanguageChangeEventForContentFromMyWatchlistPage(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubtitleLanguageChangeEventForContentFromMyWatchlistPage(userType,subtitleTrackContent);
	}
	
	@Test(priority = 168)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifySubtitleLanguageChangeEventForContentInPlaylist(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubtitleLanguageChangeEventForContentInPlaylist(userType,subtitleTrackContent);
	}
	
	@Test(priority = 169)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifySubtitleLanguageChangeEventForContentFromUpnextRail(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubtitleLanguageChangeEventForContentFromUpnextRail(userType,subtitleTrackContent);
	}
	
	@Test(priority = 170)
	@Parameters({"subtitleTrackURL"})
	public void verifySubtitleLanguageChangeEventForContentFromSharedLink(String subtitleTrackURL) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySubtitleLanguageChangeEventForContentFromSharedLink(subtitleTrackURL);
	}
	
	@Test(priority = 171)
	@Parameters({ "userType", "freeMovie2"})
	public void verifySkipIntroEventForFreeContent(String userType,String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySkipIntroEventForFreeContent(userType,freeMovie2);
	}
	
	@Test(priority = 172)
	@Parameters({ "userType"})
	public void verifySkipIntroEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Skip Intro Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySkipIntroEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 173)
	@Parameters({ "keyword5"})
	public void verifySkipIntroEventForTrailer(String keyword5) throws Exception {
		System.out.println("Verify Skip Intro Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySkipIntroEventForTrailer(keyword5);
	}
	
	@Test(priority = 174)
	public void verifySkipIntroEventForCarouselContent() throws Exception {
		System.out.println("Verify Skip Intro Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySkipIntroEventForCarouselContent();
	}
	
	@Test(priority = 175)
	public void verifySkipIntroEventForContentInTray() throws Exception {
		System.out.println("Verify Skip Intro Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySkipIntroEventForContentInTray();
	}
	
	@Test(priority = 176)
	@Parameters({"freeMovie2"})
	public void verifySkipIntroEventForContentFromSearchPage(String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySkipIntroEventForContentFromSearchPage(freeMovie2);
	}
	
	@Test(priority = 177)
	@Parameters({ "userType", "freeMovie2"})
	public void verifySkipIntroEventForContentFromMyWatchlistPage(String userType,String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySkipIntroEventForContentFromMyWatchlistPage(userType,freeMovie2);
	}
	
	@Test(priority = 178)
	@Parameters({ "userType", "freeMovie2"})
	public void verifySkipIntroEventForContentInPlaylist(String userType,String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySkipIntroEventForContentInPlaylist(userType,freeMovie2);
	}
	
	@Test(priority = 179)
	@Parameters({ "userType", "freeMovie2"})
	public void verifySkipIntroEventForContentFromUpnextRail(String userType,String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySkipIntroEventForContentFromUpnextRail(userType,freeMovie2);
	}
	
	@Test(priority = 180)
	@Parameters({"skipIntroURL"})
	public void verifySkipIntroEventForContentFromSharedLink(String skipIntroURL) throws Exception {
		System.out.println("Verify Skip Intro Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifySkipIntroEventForContentFromSharedLink(skipIntroURL);
	}
	
	@Test(priority = 181)
	@Parameters({ "userType", "keyword4"})
	public void verifyAutoSeekForwardEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekForwardEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 182)
	@Parameters({ "userType"})
	public void verifyAutoSeekForwardEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekForwardEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 183)
	@Parameters({ "keyword1"})
	public void verifyAutoSeekForwardEventForTrailer(String keyword1) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekForwardEventForTrailer(keyword1);
	}
	
	@Test(priority = 184)
	public void verifyAutoSeekForwardEventForCarouselContent() throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekForwardEventForCarouselContent();
	}
	
	@Test(priority = 185)
	public void verifyAutoSeekForwardEventForContentInTray() throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekForwardEventForContentInTray();
	}
	
	@Test(priority = 186)
	@Parameters({"keyword1"})
	public void verifyAutoSeekForwardEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekForwardEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 187)
	@Parameters({ "userType", "keyword"})
	public void verifyAutoSeekForwardEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekForwardEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 188)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifyAutoSeekForwardEventForContentInPlaylist(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekForwardEventForContentInPlaylist(userType,subtitleTrackContent);
	}
	
	@Test(priority = 189)
	@Parameters({ "userType", "keyword4"})
	public void verifyAutoSeekForwardEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekForwardEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 190)
	@Parameters({"freeContentURL"})
	public void verifyAutoSeekForwardEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekForwardEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 191)
	@Parameters({ "userType", "keyword4"})
	public void verifyAutoSeekRewindEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekRewindEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 192)
	@Parameters({ "userType"})
	public void verifyAutoSeekRewindEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekRewindEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 193)
	@Parameters({ "keyword1"})
	public void verifyAutoSeekRewindEventForTrailer(String keyword1) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekRewindEventForTrailer(keyword1);
	}
	
	@Test(priority = 194)
	public void verifyAutoSeekRewindEventForCarouselContent() throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekRewindEventForCarouselContent();
	}
	
	@Test(priority = 195)
	public void verifyAutoSeekRewindEventForContentInTray() throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekRewindEventForContentInTray();
	}
	
	@Test(priority = 196)
	@Parameters({"keyword1"})
	public void verifyAutoSeekRewindEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekRewindEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 197)
	@Parameters({ "userType", "keyword"})
	public void verifyAutoSeekRewindEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekRewindEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	
	@Test(priority = 198)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifyAutoSeekRewindEventForContentInPlaylist(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekRewindEventForContentInPlaylist(userType,subtitleTrackContent);
	}
	
	@Test(priority = 199)
	@Parameters({ "userType", "keyword4"})
	public void verifyAutoSeekRewindEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekRewindEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 200)
	@Parameters({"freeContentURL"})
	public void verifyAutoSeekRewindEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyAutoSeekRewindEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 201)
	@Parameters({ "userType", "keyword4"})
	public void verifyParentalOverlayImpressionEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayImpressionEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 202)
	@Parameters({ "userType"})
	public void verifyParentalOverlayImpressionEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayImpressionEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 203)
	@Parameters({ "keyword1", "userType"})
	public void verifyParentalOverlayImpressionEventForTrailer(String keyword1,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayImpressionEventForTrailer(keyword1,userType);
	}
	
	@Test(priority = 204)
	@Parameters({ "userType"})
	public void verifyParentalOverlayImpressionEventForCarouselContent(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayImpressionEventForCarouselContent(userType);
	}
	
	@Test(priority = 205)
	@Parameters({ "userType"})
	public void verifyParentalOverlayImpressionEventForContentInTray(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayImpressionEventForContentInTray(userType);
	}
	
	@Test(priority = 206)
	@Parameters({"keyword1", "userType"})
	public void verifyParentalOverlayImpressionEventForContentFromSearchPage(String keyword1,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayImpressionEventForContentFromSearchPage(keyword1,userType);
	}
	
	@Test(priority = 207)
	@Parameters({ "userType", "keyword"})
	public void verifyParentalOverlayImpressionEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayImpressionEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 208)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifyParentalOverlayImpressionEventForContentInPlaylist(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayImpressionEventForContentInPlaylist(userType,subtitleTrackContent);
	}
	
	@Test(priority = 209)
	@Parameters({ "userType", "keyword4"})
	public void verifyParentalOverlayImpressionEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayImpressionEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 210)
	@Parameters({"freeContentURL", "userType"})
	public void verifyParentalOverlayImpressionEventForContentFromSharedLink(String freeContentURL,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayImpressionEventForContentFromSharedLink(freeContentURL,userType);
	}
	
	@Test(priority = 211)
	@Parameters({"keyword1", "userType"})
	public void verifyParentalOverlayImpressionEventAfterPageRefresh(String keyword1,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event after refreshing the page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayImpressionEventAfterPageRefresh(keyword1,userType);
	}
	
	@Test(priority = 212)
	@Parameters({ "userType", "keyword4"})
	public void verifyParentalOverlayResultEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Free Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayResultEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 213)
	@Parameters({ "userType"})
	public void verifyParentalOverlayResultEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Premium Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayResultEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 214)
	@Parameters({ "keyword1",  "userType"})
	public void verifyParentalOverlayResultEventForTrailer(String keyword1,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Trailer Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayResultEventForTrailer(keyword1,userType);
	}
	
	@Test(priority = 215)
	@Parameters({ "userType"})
	public void verifyParentalOverlayResultEventForCarouselContent(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Carousel Content");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayResultEventForCarouselContent(userType);
	}
	
	@Test(priority = 216)
	@Parameters({ "userType"})
	public void verifyParentalOverlayResultEventForContentInTray(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content played from Tray");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayResultEventForContentInTray(userType);
	}
	
	@Test(priority = 217)
	@Parameters({"keyword1", "userType"})
	public void verifyParentalOverlayResultEventForContentFromSearchPage(String keyword1,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content From Search Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayResultEventForContentFromSearchPage(keyword1,userType);
	}
	
	@Test(priority = 218)
	@Parameters({ "userType", "keyword"})
	public void verifyParentalOverlayResultEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content From My Watchlist Page");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayResultEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 219)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifyParentalOverlayResultEventForContentInPlaylist(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content played from Playlist");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayResultEventForContentInPlaylist(userType,subtitleTrackContent);
	}
	
	@Test(priority = 220)
	@Parameters({ "userType", "keyword4"})
	public void verifyParentalOverlayResultEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content played from Upnext rail");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayResultEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 221)
	@Parameters({"freeContentURL" ,"userType"})
	public void verifyParentalOverlayResultEventForContentFromSharedLink(String freeContentURL,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For content played from Shared Link");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyParentalOverlayResultEventForContentFromSharedLink(freeContentURL,userType);
	}
	
	@Test(priority = 222)
	public void verifyMuteChangedEventForNewsContent() throws Exception {
		System.out.println("Verify Mute Changed Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyMuteChangedEventForNewsContent();
	}
	
	@Test(priority = 223)
	@Parameters({"keyword1"})
	public void verifyMuteChangedEventDuringContentPlayback(String keyword1) throws Exception {
		System.out.println("Verify Mute Changed Event During Content Playback");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyMuteChangedEventDuringContentPlayback(keyword1);
	}
	
	@AfterClass
	public void tearDown() {
		Zee5PWAMixPanelAndroidBusinessLogic.tearDown();
	}


}
