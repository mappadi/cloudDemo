package com.zee5.TVPages;

import org.openqa.selenium.By;

public class Zee5TvPlayerPage {

	public static By objAd = By.xpath("//*[contains(text(),'Ad :')]");
	public static By objProgressLoader = By.xpath("//*[@id='tv_gif_progress_loader']");
	public static By objPlayPause = By.xpath("//*[@id='new_play_pause_icon']");
	public static By objPlayPauseHighlighted = By.xpath("//*[@id='new_play_pause_icon' and @focused='true']");
	public static By objForward10 = By.xpath("//*[@id='player_10s_fwd']");
	public static By objRewind10 = By.xpath("//*[@id='player_10s_rwd']");
	public static By objPlayerTitle = By.xpath("//*[@id='player_title_text']");
	public static By objElapsedTime = By.xpath("//*[@id='time_elapsed_tv']");
	public static By objTotalTime = By.xpath("//*[@id='total_time_tv']");
	public static By objPlayerSettings = By.xpath("//*[@id='player_settings']");
	public static By objPlayerSettingsHighlighted = By.xpath("//*[@id='player_settings' and @focused='true']");
	public static By objPlayerSkipIntro = By.xpath("//*[@id='skip_button_layout']");
	public static By objPlayerSkipIntro1 = By.xpath("//*[@id='skip_button_text']");

	public static By objPlayerContainer = By.xpath("	//*[@id='container_list']");
}
