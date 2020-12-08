package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDNewsPage {
	
	public static By objListingScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/title']");
	
	public static By objThumbNailImg = By.xpath("(//*[@id='item_image' and @resource-id='com.graymatrix.did:id/item_image' and @index=1])[1]");
	
	public static By objConsumptionScreenTitle = By.xpath("//*[@resource-id='com.graymatrix.did:id/item_primary_text']");
	
	public static By objStartTime = By.xpath("//*[@resource-id='com.graymatrix.did:id/positionText1']");
	
	public static By objEndTime = By.xpath("//*[@resource-id='com.graymatrix.did:id/durationText1']");
	
	public static By objMetaData = By.xpath("(//*[@id='movieInfoLl']/*[@text])");
	
	public static By objNewsContent = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_image'])[2]");
	
	public static By objNewsContentCardTitle = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_primary_text'])[2]");
	
	public static By objLiveNewsTray = By.xpath("//*[@text='Live News Channels']");
	
	public static By objTopStoriesTray = By.xpath("//*[@text='Top Stories']");
	
	public static By objTodaysHeadlinesTray =By.xpath("//*[contains(@text,\"Today's Headlines\")]");
	
	public static By objCarouselCollectionContent = By.xpath("(//*[@resource-id='com.graymatrix.did:id/indicator']/child::*)[1]");
	
	public static By objEntertainmentNewsTray = By.xpath("//*[@text=' Entertainment News']");
	
	public static By objRightArrowBtn = By.xpath("(//*[@resource-id='com.graymatrix.did:id/header_arrow'])[2]");
	
	public static By objTrayHeader = By.xpath("//*[@id='item_primary_text']");
	
	public static By objThumbnailImg1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_image'])[10]");
	
	public static By objNextContentImg = By.xpath("//*[@resource-id='com.graymatrix.did:id/item_image']");
	
	public static By objWatchlistIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/watch_list_image' and (./preceding-sibling::* | ./following-sibling::*)[@text='Watchlist']]");
	
	public static By objDownlaodOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/downlowd_image' and (./preceding-sibling::* | ./following-sibling::*)[@text='Download']]");

	public static By objNewsConsumptionSrnDescription = By.xpath("//*[@resource-id='com.graymatrix.did:id/item_secondary_text']");
	public static By objChannelName = By.xpath("(//*[@class='android.widget.TextView'])[2]");
	public static By objContentGenere = By.xpath("(//*[@class='android.widget.TextView'])[3]");
	public static By objContentDuration = By.xpath("(//*[@class='android.widget.TextView'])[5]");
	public static By objShareButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/native_share_button']");
//	public static By objWatchlistIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/watch_list_image' and (./preceding-sibling::* | ./following-sibling::*)[@text='Watchlist']]");
	public static By objLoginScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and contains(text(),'Login/Register')]");
	public static By objDownarrow = By.xpath("//*[@resource-id='com.graymatrix.did:id/expandIV']");
	
//	public static By objDownlaodOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/downlowd_image' and (./preceding-sibling::* | ./following-sibling::*)[@text='Download']]");
	public static By objRelatedNewsRail = By.xpath("(//*[@text='Related News'])[1]");
	public static By objRelatedNewsRail2 = By.xpath("(//*[@text='Related News'])[2]");
	
	public static By objMoreNewsRail = By.xpath("//*[@text='More News']");
	
	public static By objListingScreenHeader = By.xpath("//*[@resource-id='com.graymatrix.did:id/title' and contains(text(),'More News')]");
    public static By objRecommendedRail = By.xpath("//*[@resource-id='com.graymatrix.did:id/header_primary_text' and contains(text(),'Recommended')]");
    public static By objLiveNewsRail = By.xpath("//*[@text='Live Channels']");
    public static By objSimilarChannelsTray = By.xpath("//*[@text='Similar Channels']");
    public static By objSimilarChannelsHeader = By.xpath("//*[@resource-id='com.graymatrix.did:id/title' and contains(text(),'Similar Channels')]");
    
    public static By objRegisterPopup = By.xpath("//*[@resource-id='com.graymatrix.did:id/tvtitle' and contains(text(),'Register')]");

    public static By objWatchlistContentTitle = By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title']");
	public static By objMetaDataOfLiveTv = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_primary_text'])[1]");
	
	public static By objConsumptionScrnMetaDataOfLiveTv = By.xpath("//*[@resource-id='com.graymatrix.did:id/item_primary_text']");
  
	public static By objLiveChannelThumbnailImage = By.xpath("((//*[@id='recyclerView']/*/*[@id='recyclerView'])[2]/*[@id='horizontal_list_10_parent'])");

    public static By objChannelNameBelowTheCard = By.xpath("((//*[@id='recyclerView']/*/*[@id='recyclerView'])[2]/*/*[@id='item_primary_text'])") ;
	
//	public static By objLiveChannelName = By.xpath("((//*[@id='recyclerView']/*/*[@id='recyclerView'])[2]/*[@id='horizontal_list_10_parent'])[1]");
	
	public static By objSwipeTray = By.xpath("(//*[@resource-id='com.graymatrix.did:id/header_primary_text'])[1]");
	
	public static By objTraycard = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_image'])[1]");
	
	public static By objBroadcastedDate = By.xpath("(//*[@class='android.widget.TextView'])[4]");
	
	public static By objShareGmail = By.xpath("//*[@text='Gmail']");
	
	public static By objSharedLink = By.xpath("//*[@resource-id='com.google.android.gm:id/subject']");
	
	public static By objMetadataofthecard = By.xpath("(//*[@id='item_primary_text'])[5]");

	public static By objLiveChannelName = By.xpath("(//*[@id='item_primary_text'])[1]");
	
	public static By EntertaintmentNewsTray = By.xpath("//*[@text=' Entertainment News']");

}
