package com.zee5.TVPages;

import org.openqa.selenium.By;

public class Zee5TvSearchPage {

	public static By objSearchSpaceBar = By.xpath("//*[@id='rl_button_space']");
	
	
	public static By objSearchBackButton = By.xpath("//*[@id='rl_button_delete']");
	public static By objSearchKeyboardBtn(String str) {
		return By.xpath("//*[@class='android.widget.Button' and @text='" + str + "']");
	}

	public static By objSearchedSpecificTumbnailTitle(String str) {
		return By.xpath("//*[@id='search_result_title' and @text='" + str + "']");
	}
	
	public static By objSearchedSpecificTumbnailcard(String str, String type) {
		return By.xpath("(//*[@class='android.widget.RelativeLayout']//child::*[contains(text(),'"+ str +"')]//parent::*//parent::*[@text='"+type+"']//parent::*//parent::*//parent::*)[3]");
	}
	//*[@text="Videos"]
	
	
	
	public static By objSearchPageNowPlayingButton = By.xpath("//*[@id='popular_his_sugg' and contains(text(),'Now Playing')]");
	public static By objSearchedSpecificTumbnailmetadata(String str) {
		return By.xpath("//*[@id='search_result_title_assert']//*[@text='" + str + "']");
	}

	public static By objSearchedTumbnailTitle(int i) {
		return By.xpath("(//*[@id='search_result_title'])[" + i + "]");
	}

	public static By objSearchedTumbnailmetadata(int i) {
		return By.xpath("(//*[@id='search_result_title_assert'])[" + i + "]");
	}

	public static By objSearchedTumbnailImage(int i) {
		return By.xpath("(//*[@id='search_result_image'])[" + i + "]");
	}

	public static By objSearchedDataMainHead = By.xpath("//*[@id='details_main_head']");
	public static By objSearchedDataTitle = By.xpath("//*[@id='detail_title']");
	public static By objSearchedDataDescription = By.xpath("//*[@id='details_description_text']");
	public static By objPlayIcon = By.xpath("(//*[@id='detail_button_icon'] | //*[@id='seekbar_resume_layout'])[1]");
	
	
	public static By objResumePlayIcon = By.xpath("//*[@id='seekbar_resume_layout']");
			public static By objwatchTrailerIcon = By.xpath("(//*[@text='Watch\nTrailer']//parent::*//child::*)[1]");
	public static By objEditbox = By.xpath("//*[@id='editText']");
	
			public static By objElapsedTime = By.xpath("(//*[@id='search_title_elapsed_time'])[1]");
			
					public static By objChalnnelName = By.xpath("(//*[@id='channel_name_text'])[1]");
			
			
					public static By objProgressBar = By.xpath("(//*[@id='episode_elapsed_progress_bar_search'])[1]");
					
					
					public static By objSearchedTumbnailImageEPG(String str) {
						return By.xpath("//*[@id='channel_name_text' and @text='"+str+"']//parent::*//parent::*//parent::*//child::*//child::*[@id='imageOverlay']");
					}
					
							public static By objEPGtitle = By.xpath("(//*[@id='search_result_title'])[1]");
							
							
							public static By objGotolivebutton = By.xpath("//*[@id='switch_to_live_text']");
							
	//================================Content detail page=================================
	
	
	public static By objLoginPopup = By.xpath("//*[@id='pop_up_realtive' or @id='pop_up_relative']");
	
	
	public static By objShowsLoginPopup = By.xpath("//*[@id='pop_up_relative']");
	public static By objSubscribePopup = By.xpath("//*[@id='tv_pop_up_un_subscribe_text']");
	
}
