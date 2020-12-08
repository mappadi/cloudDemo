package com.zee5.TVPages;

import org.openqa.selenium.By;

public class Zee5TvWelcomePage {

	public static By objWelcomeSkipLink = By.xpath("//*[@id='welcome_skip']");

	public static By objalreadyRegister = By.xpath("//*[@id='already_register']");

	public static By objloginCode = By.xpath("//*[@id='code_text']");
	public static By objHomepageTrayContent = By.xpath(
			"(//*[@id='row_header' and contains(text(),'Top 20 on ZEE5')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*//child::*[@id='main_image'])[1]");

	public static By objshowpageTrayContent = By.xpath(
			"(//*[@id='row_header' and contains(text(),'Trending') or contains(text(),'Top Zee ')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[1]");

	public static By objMoviePageTrayContent = By.xpath(
			"(//*[@id='row_header' and contains(text(),'Trending') or contains(text(),'Top Zee ')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");
	
			public static By objPremiumPageTrayContent = By.xpath("(//*[@id='row_header' and contains(text() ,'Premium')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");
			
					public static By objVideoPageTrayContent = By.xpath("(//*[@id='row_header' and contains(text() ,'Latest on')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");	
					
							public static By objLiveNewsContentinNewsPage = By.xpath("(//*[@id='row_header' and contains(text() ,'Live News')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");
							
							
			public static By objcontinueButtonInLoginPage = By.xpath("//*[@id='continue_button_reg']");

	public static By objContentTitleIncontentPage = By.xpath("//*[@id='detail_title']");

	public static By objContentDescriptionIncontentPage = By.xpath("//*[@id='details_description_text']");
	public static By objContentdurationIncontentPage = By.xpath("(//*[@class='android.widget.TextView'])[6]");
	public static By objContentyearIncontentPage = By.xpath("	(//*[@class='android.widget.TextView'])[7]");
	public static By objContentcertificateIncontentPage = By.xpath("(//*[@class='android.widget.TextView'])[5]");
	public static By objContentTypeIncontentPage = By.xpath("(//*[@class='android.widget.TextView'])[3]");
	public static By objContentgenreIncontentPage = By.xpath("(//*[@class='android.widget.TextView'])[4]");
	public static By objContenttrailerplaybackIncontentPage = By.xpath("//*[@id='trailer_playback_container']");

	public static By objContentShowTypeIncontentPage = By.xpath("(//*[@class='android.widget.TextView'])[2]");
	public static By objContentShowgnereIncontentPage = By.xpath("	(//*[@class='android.widget.TextView'])[3]");
	public static By objContentShowcertificateIncontentPage = By.xpath("(//*[@class='android.widget.TextView'])[4]");
	public static By objContentShowyearIncontentPage = By.xpath("(//*[@class='android.widget.TextView'])[5]");

}
