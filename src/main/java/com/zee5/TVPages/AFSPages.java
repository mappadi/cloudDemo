package com.zee5.TVPages;

import org.openqa.selenium.By;

public class AFSPages {

	public static By SearchInputTextField = By.id("com.graymatrix.did:id/editText");
	public static By SearchedResultText = By.xpath("//*[@resource-id='com.graymatrix.did:id/search_result_title']");
	public static By viemo = By.id("com.vimeo.android.videoapp:id/row_header");
	public static By ele110 = By.id("com.vimeo.android.videoapp:id/card_video_blooming_title");
	public static By videoTitle = By.id("lb_details_description_title");
	public static By videoSubTitle = By.id("lb_details_description_subtitle");
	public static By videoFastForwardIcon = By.xpath("//*[@content-desc='Fast Forward']");
	public static By videoCurrentTime = By.id("current_time");
	public static By videoTotalTime = By.id("total_time");
	public static By videoPlayIcon = By.id("//*[@content-desc='Play']");
//	---------------------------------------------------------------------------------------------------------------
	
	public static By ZeeWelcomeSkip = By.id("welcome_skip");
	public static By SearchIcon = By.id("search_icon_menu");
	public static By SearchKeyboardSpaceButton = By.id("rl_button_space");
	public static By SearchedMainHead = By.id("detail_title");
	public static By SearchedTitle = By.id("detail_title");
	public static By SearchedDescription = By.id("details_description_text");
	public static By metaData = By.id("rating_meta_data");
	public static By objProgressLoader = By.xpath("//*[@id='tv_gif_progress_loader']");
	public static By PlayerLoader = By.id("tv_gif_progress_loader");
	public static By PlayerAd = By.id("//*[@text='Ad : (0:20)']");
	public static By PlayedVideoTitle = By.id("player_title_text");
	public static By PlayedVideoGenreDuration = By.id("player_genre_duration");
}
