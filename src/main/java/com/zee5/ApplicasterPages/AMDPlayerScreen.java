package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDPlayerScreen {

	public static By objPlayer = By.xpath("//*[@id='controller']");
	public static By objPlayIcon = By.xpath("//*[@id='icon_play']");
	public static By objPauseIcon = By.xpath("//*[@id='icon_pause']");
	public static By objNextIcon = By.xpath("//*[@id='icon_next']");
	public static By objProgressBar = By.xpath("//*[@id='progress']");
	public static By objBackButton = By.xpath("//*[@id='icon_down']");
	public static By objChromeCastIcon = By.xpath("//*[@id='icon_cast']");
	public static By objFullscreenIcon = By.xpath("//*[@id='icon_fullscreen']");
	public static By objTotalDuration = By.xpath("//*[@id='durationText1']");
	public static By objTimer = By.xpath("//*[@id='positionText1']");
	public static By objShareIcon = By.xpath("//*[@id='native_share_button']");
	public static By objWatchlistIcon = By.xpath("//*[@id='watch_list_image']");
	public static By objDownloadIcon = By.xpath("//*[@id='downlowd_image']");
	public static By objfirstContentcardOfParticularTray(String trayTitle) {
		return By.xpath("(//*[@text="+trayTitle+"]/parent::*/parent::*/following-sibling::*/child::*/child::*/child::*/child::*)[1]");
	}
	public static By objThreeDotsOnPlayer = By.xpath("//*[@id='icon_more']");
	public static By objTitleOnPlayer = By.xpath("//*[@id='title_main']");
	public static By objPlayerScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/playerElevationParent']");
	
	public static By objQuality = By.xpath("//*[@id='icon_quality_text']");
	public static By objQualityOptions = By.xpath("//*[@id='textView2']");
	public static By objQualityOptions(int i) {
		return By.xpath("(//*[@id='textView2'])["+i+"]");
	}
	public static By objAddToWatchlist = By.xpath("//*[@id='icon_add_to_watch_list_text']");
	public static By objPlaybackRate = By.xpath("//*[@id='icon_playback_rate_text']");
	
	public static By objPremiumTextOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/static_premium_text']");
	public static By objSubscribeNowLinkOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/subscribe_now_action']");
	public static By objLoginTextOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/static_login_text']");
	public static By objLoginLinkOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/login_button']");
	public static By objGetPremiumPopUp = By.xpath("//*[@resource-id='com.graymatrix.did:id/popup_title' and @text='Subscribe']");
	
	public static By objPlaybackRateSelected = By.xpath("(//*[@id='icon_selected']//parent::*//child::*)[2]");
	public static By objPlayerLoader = By.xpath("//*[@id='player_loading_progress']");
	public static By objSharePopUp = By.xpath("//*[@text='Share using']");
	public static By objTwitter = By.xpath("//*[@text='Tweet']");
	public static By objTweetButton = By.xpath("//*[@id='button_tweet']");
	public static By objFacebook = By.xpath("//*[@text='News Feed' or @text='Facebook']");
	public static By objFacebookPost = By.xpath("//*[@text='POST' or @text='Post' or @text='Share' or @text='SHARE']");
	public static By objCopyToClipboard = By.xpath("//*[@text='Copy to clipboard']");
	public static By objRetryBtn = By.xpath("//*[@id='zretry']");
	public static By objSubtitleOption = By.xpath("//*[@id='icon_subtitle_text']");
	public static By objSkipIntro = By.xpath("//*[@id='skipintro']");
	public static By objReplay = By.xpath("//*[@id='icon_replay']");
	public static By objSubtitlePopUp = By.xpath("//*[@id='popup_title']");
	public static By objEnglishSubtitle = By.xpath("//*[@text='English']");
	public static By objSubscribeButtonBelowThePlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/get_premium_button']");
	public static By objSubtitleDefaultSelected = By.xpath("(//*[@id='icon_selected']//parent::*//child::*)[2]");
	public static By objPlaybackRate2 = By.xpath("//*[@text='2.0X']");
	public static By objLoginCTA = By.xpath("//*[@id='get_premium_login']");
	public static By objSubscribeNowButtonOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/subscribe_now_action']");
	public static By objSubtitleOptionInPotraitMode = By.xpath("//*[@resource-id='com.graymatrix.did:id/subtitlesTv']");
	public static By objSubtitleValueInPotraitMode = By.xpath("//*[@resource-id='com.graymatrix.did:id/subtitlesValueTv']");
	public static By objReplayIconOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_replay']");
	public static By objRegisterPopUp = By.xpath("//*[@id='registrationContainer']");
	public static By objUpnextContentCard = By.xpath("(//*[@resource-id='com.graymatrix.did:id/card'])[3]");
	public static By objUpnextContentCardTitle = By.xpath("(//*[@id='title_similar'])[3]");
	public static By objUpnextRail = By.xpath("//*[@id='similarcontentlistView']");
	public static By objContentTitle = By.xpath("//*[@id='title_main']");
	public static By objShareIconOnPlayer = By.xpath("//*[@id='icon_share']"); 
	public static By objcontentTitleInconsumptionPage = By.xpath("//*[@resource-id='com.graymatrix.did:id/contentCl']/child::*[@resource-id='com.graymatrix.did:id/item_primary_text']");
	public static By objCompleteProfilePopUp = By.xpath("//*[@id='tellUsMoreContainer']");
	public static By objShareNowFb = By.xpath("//*[@contentDescription='SHARE NOW']");
	public static By objWatchCreditsCTA = By.xpath("//*[@text='Watch Credits']");
	public static By objUpNext = By.xpath("//*[@text='Up Next']");
	public static By objTitleInLandscape(String title) {
		return By.xpath("//*[@id='title_main' and contains(text(), '"+title+"')]");
	}
	public static By objParentalPinPopUp = By.xpath("//*[@id='otpEditText1']");
	public static By objUpNextCard = By.xpath("//*[@resource-id='com.graymatrix.did:id/upnext']");
	public static By objFirstContentCardTitleInUpnextTray = By.xpath("((//*[@resource-id='com.graymatrix.did:id/header_primary_text' and @text='Up Next']/parent::*/parent::*/following-sibling::*)[1]/child::*/child::*/child::*[@resource-id='com.graymatrix.did:id/item_primary_text'])[1]");
	public static By objCountDownTimerInUpNextCard = By.xpath("//*[@resource-id='com.graymatrix.did:id/counddownwatchcredit']");
	
	public static By objfbLoginPage = By.xpath("//*[@content-desc='Create New Facebook Account'] | //*[@content-desc='Log In'] | //*[@text='Log In'] | //*[@text='Create New Facebook Account']");

	public static By objAd = By.xpath("(//*[contains(text(),'Ad :')]) | (//*[contains(text(),'Ad 1 of 2 :')]) | (//*[contains(text(),'Ad 2 of 2 :')])");
	
	public static By objPreviousIcon = By.xpath("//*[@id='icon_previous']");
	
	public static By objAd2 = By.xpath("(//*[contains(text(),'Ad 1')]) | //*[contains(text(),'Ad 2')] | //*[contains(text(),'Ad :')]");
	
	public static By objLoginButtonInRegisterPopUp = By.xpath("//*[@resource-id='com.graymatrix.did:id/tvLogin']");
	
	public static By objLearnMoreTextOnAdPlayBack = By.xpath("//*[contains(text(),'Learn More')]");
	
	public static By objSelectedQualityOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_selected']/following-sibling::*");

	public static By objAudioTrackOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_audioTrack_text']");
}
