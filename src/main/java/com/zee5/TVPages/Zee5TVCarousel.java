package com.zee5.TVPages;

import org.openqa.selenium.By;

public class Zee5TVCarousel {
	public static By objCarouselTitle = By.xpath("//*[@id='banner_title']");

	public static By objCarouselPlayButton = By.xpath("//*[@id='banner_play_button_layout']");

	public static By objCarouselSubscribeButton = By.xpath("//*[@id='subscribe_text']");
	
	public static By objCarouselPremiumTag = By.xpath("//*[@id='details_tag_textview']");
	
	public static By objCarouselMetadata = By.xpath("//*[@id='genre_text']");
	
	public static By objCarouselDescription = By.xpath("//*[@id='banner_description']");
	
	public static By objCarouselZeelogo = By.xpath("//*[@id='banner_zee_image']");

	public static By objCarouselTitleText(String str) {
		return By.xpath("//*[@id='banner_title' and @text='"+str+"']");
	}
	
	public static By objSubscriptionPlanPage = By.xpath("//*[@id='subscrptionPlan']");
	
}
